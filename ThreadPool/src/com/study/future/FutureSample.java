package com.study.future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class FutureSample {
	public static void main(String[] args) {
		FutureSample futureSample = new FutureSample();
		// 创建任务集合
		List<FutureTask<Integer>> taskList = new ArrayList<>();
		// 另一种方式
		// List<Future<Integer>> futureList = new ArrayList<>();
		ExecutorService exec = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 10; i++) {
			// 传入Callable对象创建FutureTask对象
			FutureTask<Integer> ft = new FutureTask<Integer>(new ComputeTask(i, "task_" + i));
			taskList.add(ft);
			exec.submit(ft);
			// Future<Integer> result = exec.submit(new ComputeTask(i,"task_"+i));
			// futureList.add(result);
		}
		System.out.println("主线程已经提交任务，做自己的事！");

		// 开始统计各计算线程计算结果
		int totalResult = 0;
		for (FutureTask<Integer> ft : taskList) {
			try {
				// FutureTask的get方法会自动阻塞,直到获取计算结果为止
				totalResult = totalResult + ft.get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		System.out.println("total = " + totalResult);
		exec.shutdown();

	}
}
