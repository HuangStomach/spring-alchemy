package com.huangstomach.springalchemy.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.huangstomach.springalchemy.property.PlayerProperties;

@Component
@EnableConfigurationProperties(PlayerProperties.class)
public class ScheduledTasks {
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private final PlayerProperties playerProperties;

    public ScheduledTasks(PlayerProperties playerProperties) {
        this.playerProperties = playerProperties;
    }

    @Scheduled(fixedRate = 60000)
    public void reportCurrentTime() {
        log.info("The time is now {}, the player is {}!", 
            dateFormat.format(new Date()), playerProperties.getName());
    }
}
