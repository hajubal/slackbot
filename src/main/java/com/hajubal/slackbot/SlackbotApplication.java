package com.hajubal.slackbot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SlackbotApplication {
    static final long INTERVAL_MIN = 1l;

    static final Logger logger = LoggerFactory.getLogger(SlackbotApplication.class);

	public static void main(String[] args) throws Exception {
		
        SlackbotService slackbotService = new SlackbotService();

        int count = 0;

        while(true) {
            slackbotService.checkRuliweb();
            slackbotService.checkPartenerShop();

            logger.info("Work count: {}", ++count);

            Thread.sleep(60000l * INTERVAL_MIN);
        }
	}

    

    

    
}
