package com.study;

import com.study.aim.MakeSrcDoc;
import com.study.vo.PendingDocVo;
import com.study.aim.ProblemBank;
import com.study.service.DocService;

import java.util.List;

/**
 * 
 * 优化前
 */
public class SingleWeb {
    public static void main(String[] args) {

        System.out.println("题库开始初始化...........");
        ProblemBank.initBank();
        System.out.println("题库初始化完成。");

        //需要生成的总文档
        List<PendingDocVo> docList = MakeSrcDoc.makeDoc(2);
        long startTotal = System.currentTimeMillis();
        for(PendingDocVo doc:docList){
            System.out.println("开始处理文档："+doc.getDocName()+".......");
            long start = System.currentTimeMillis();
            String localName = DocService.makeDoc(doc);
            System.out.println("文档"+localName+"生成耗时："
                    +(System.currentTimeMillis()-start)+"ms");
            start = System.currentTimeMillis();
            String remoteUrl = DocService.upLoadDoc(localName);
            System.out.println("已上传至["+remoteUrl+"]耗时："
                    +(System.currentTimeMillis()-start)+"ms");
        }
        System.out.println("共耗时："+(System.currentTimeMillis()-startTotal)+"ms");
    }
}
