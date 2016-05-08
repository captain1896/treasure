package com.buzz.retry;

import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class RetryUtilTest {
    public static void main(String[] args) {
        try {
            RetryUtil.retry(3, 50L, TimeUnit.MILLISECONDS, () -> {
                new InquirySolrService().commitBean("content...");
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
