/**
 * Project Name:maven_web
 * File Name:teeee.java
 * Package Name:com.cn.test
 * Date:2016年9月2日下午1:07:10
 * Copyright (c) 2016, All Rights Reserved.
 *
*/

package com.cn.test;
/**
 * ClassName:teeee <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年9月2日 下午1:07:10 <br/>
 * @author   TH
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
import java.io.ByteArrayOutputStream;
import java.util.concurrent.CountDownLatch;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.asynchttpclient.AsyncHttpClientConfig;
/**
 * This example demonstrates a fully asynchronous execution of multiple HTTP
 * exchanges where the result of an individual operation is reported using a
 * callback interface.
 */
public class AsyncClientHttpExchangeFutureCallback {
    public static void main(final String[] args) throws Exception {
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(3000).setConnectTimeout(3000).build();
        CloseableHttpAsyncClient httpclient = HttpAsyncClients.custom()
                .setDefaultRequestConfig(requestConfig).build();
        try {
            httpclient.start();
            final HttpGet[] requests = new HttpGet[] {
                    new HttpGet("http://www.google.com/")};
            final CountDownLatch latch = new CountDownLatch(requests.length);
            for (final HttpGet request : requests) {
                httpclient.execute(request, new FutureCallback<HttpResponse>() {
                    //无论完成还是失败都调用countDown()
                    public void completed(HttpResponse response) {
                        latch.countDown();
                        System.out.println(response.getStatusLine().getStatusCode());
                        System.out.println(request.getRequestLine() + "->"
                                + response.getStatusLine());
                    }
                    public void failed(Exception ex) {
                    	System.out.println("failed");
                    	System.out.println(request.getRequestLine() + "->failed" + ex);
                        latch.countDown();
                    }
                    public void cancelled() {
                        latch.countDown();
                        System.out.println(request.getRequestLine()
                                + " cancelled");
                    }
                });
            }
            latch.await();
            System.out.println("Shutting down");
        } finally {
            httpclient.close();
        }
        System.out.println("Done");
    }
    
    
}
