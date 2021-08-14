package com.hajubal.slackbot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class SlackbotApplication {
    static final long INTERVAL_MIN = 1l;

    static final Logger logger = LoggerFactory.getLogger(SlackbotApplication.class);

	public static void main(String[] args) throws InterruptedException {
		
        SlackbotService slackbotService = new SlackbotService();

        int count = 0;

        while(true) {
            try {
                slackbotService.checkRuliweb();
                slackbotService.checkPartenerShop();
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }

            logger.info("Work count: {}", ++count);

            Thread.sleep(60000l * INTERVAL_MIN);
        }
	}

    

    

    
}
