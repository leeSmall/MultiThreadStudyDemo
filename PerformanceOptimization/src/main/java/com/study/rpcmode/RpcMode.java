package com.study.rpcmode;

import com.study.aim.Consts;
import com.study.aim.MakeSrcDoc;
import com.study.aim.ProblemBank;
import com.study.service.DocService;
import com.study.vo.PendingDocVo;

import java.util.List;
import java.util.concurrent.*;

/**
 * 
 * 优化后：服务的拆分，rpc服务
 */
public class RpcMode {

    //生成文档的线程池
    private static ExecutorService docMakeService
            = Executors.newFixedThreadPool(Consts.THREAD_COUNT_BASE*2);
    //上传文档的线程池
    private static ExecutorService docUploadService
            = Executors.newFixedThreadPool(Consts.THREAD_COUNT_BASE*2);
    private static CompletionService docCompletionService
            = new ExecutorCompletionService(docMakeService);
    private static CompletionService uploadCompletionService
            = new ExecutorCompletionService(docUploadService);


    //生成单个文档的任务
    private static class MakeDocTask implements Callable<String>{
        private PendingDocVo pendingDocVo;

        public MakeDocTask(PendingDocVo pendingDocVo) {
            this.pendingDocVo = pendingDocVo;
        }

        @Override
        public String call() throws Exception {
            long start = System.currentTimeMillis();
            //一篇文档是由很多的题目组成的，每个题目相互之间是独立的，所以并行的、异步的处理每个题目。
            String localName = DocService.makeAsyn(pendingDocVo);
            System.out.println("文档"+localName+"生成耗时："
                    +(System.currentTimeMillis()-start)+"ms");
            return localName;
        }
    }


    //上传单个文档的任务
    private static class UploadDocTask implements Callable<String>{
        private String localName;

        public UploadDocTask(String localName) {
            this.localName = localName;
        }

        @Override
        public String call() throws Exception {
            long  start = System.currentTimeMillis();
            String remoteUrl = DocService.upLoadDoc(localName);
            System.out.println("已上传至["+remoteUrl+"]耗时："
                    +(System.currentTimeMillis()-start)+"ms");
            return remoteUrl;
        }
    }

    public static void main(String[] args)
            throws InterruptedException, ExecutionException {
        System.out.println("题库开始初始化...........");
        ProblemBank.initBank();
        System.out.println("题库初始化完成。");

        //需要生成的总文档
        List<PendingDocVo> docList = MakeSrcDoc.makeDoc(2);

        long startTotal = System.currentTimeMillis();

        //每份文档的生成是非常独立的，所以吧文档的生成放到线程池里面去完成
        for(PendingDocVo doc:docList){
            docCompletionService.submit(new MakeDocTask(doc));
        }

        //每份文档的上传是非常独立的，所以吧文档的生成放到线程池里面去完成
        for(PendingDocVo doc:docList){
            Future<String> futureLocalName = docCompletionService.take();
            uploadCompletionService.submit(new UploadDocTask(futureLocalName.get()));
        }

        for(PendingDocVo doc:docList){
            //把上传后的网络存储地址拿到
            uploadCompletionService.take().get();
        }
        System.out.println("共耗时："+(System.currentTimeMillis()-startTotal)+"ms");
    }
}
