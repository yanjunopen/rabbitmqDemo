package com.example.slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Created by Administrator on 2017/11/18 0018.
 */
public class Service {

    public static void main(String[] args) throws InterruptedException {
        Logger LOGGER=LoggerFactory.getLogger(Service.class);

        for (int i = 0; i< 10; i++){
            Thread.sleep(100);
            LOGGER.debug("from debug main args={}, time={}", args, System.currentTimeMillis());
            LOGGER.info("from info main args={}, time={}", args, System.currentTimeMillis());
            LOGGER.warn("from warn main args={}, time={}", args, System.currentTimeMillis());
            LOGGER.error("from error main args={}, time={}", args, System.currentTimeMillis());
        }
    }
}
