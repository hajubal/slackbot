package com.hajubal.slackbot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SlackbotApplication {
    static final long INTERVAL_MIN = 5l;

    static final Logger logger = LoggerFactory.getLogger(SlackbotApplication.class);

	public static void main(String[] args) throws Exception {
		
        SlackbotService slackbotService = new SlackbotService();

        int count = 0;

        while(true) {

            if(slackbotService.checkRuliweb() && slackbotService.checkPartenerShop()) {
                break;
            } else {
                logger.info("아직 매물 없음. count: {}", ++count);
            }

            Thread.sleep(60000l * INTERVAL_MIN);
        }
	}

    

    

    
}
