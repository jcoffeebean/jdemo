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
import java.util.concurrent.Executor;
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
		try {
			Map result = future.get(50, TimeUnit.SECONDS);
			System.out.println(result);
		} catch (InterruptedException e) {
			System.out.println("方法执行中断");
		} catch (ExecutionException e) {
			System.out.println("Excution异常");
			future.cancel(true);
		} catch (TimeoutException e) {
			System.out.println("方法执行时间超时");
			// future.cancel(true);
		}
		System.out.println("sas");
		executor.shutdown();
	}

	public String getValue() {
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
		throw new RuntimeException();
//		return "ssssssssssssssss";
	}
}
