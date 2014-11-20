/**
 * @author taowei20061122@163.com 
 * @createtime 2014年8月28日
 * @version V1.0
 */
package study;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MethodTimeOut {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		FutureTask<Map> future = new FutureTask<Map>(
				new Callable<Map>() {
					public Map call() throws Exception {
						Map data = new HashMap();
						MethodTimeOut m = new MethodTimeOut();
						data.put("1", m.getValue());
						return data;
					}
				});
		executor.submit(future);
		Map result = null;
		try {
			result = future.get(1, TimeUnit.SECONDS);
			System.out.println(result);
		} catch (InterruptedException e) {
			System.out.println("方法执行中断");
		} catch (ExecutionException e) {
			System.out.println("Excution异常");
			future.cancel(true);
		} catch (TimeoutException e) {
			System.out.println("方法执行时间超时");
//			future.cancel(true);
			try {
				System.out.println("Main: " + Thread.currentThread().getName());
				Thread.currentThread().sleep(2000);
				try {
					result = future.get(1000, TimeUnit.SECONDS);
				} catch (ExecutionException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (TimeoutException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
		System.out.println(result);
		executor.shutdown();
	}

	public String getValue() {
		try {
			System.out.println("MethodT: " + Thread.currentThread().getName());
			Thread.currentThread().sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
//		throw new RuntimeException();
		return "ssssssssssssssss";
	}
}
