package com.huangstomach.springalchemy.state;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.availability.ReadinessState;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

// 可以监听应用程序的状态
@Component
public class MyReadinessStateExporter {
    private static final Logger log = LoggerFactory.getLogger(MyReadinessStateExporter.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @EventListener
    public void onStateChange(AvailabilityChangeEvent<ReadinessState> event) {
        switch (event.getState()) {
            case ACCEPTING_TRAFFIC:
                log.info("ACCEPTING_TRAFFIC State", dateFormat.format(new Date()));
                break;
            case REFUSING_TRAFFIC:
                log.info("REFUSING_TRAFFIC State", dateFormat.format(new Date()));
                break;
        }
    }
}
