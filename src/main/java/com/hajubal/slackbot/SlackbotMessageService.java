package com.hajubal.slackbot;

import java.util.Properties;
import com.slack.api.Slack;
import com.slack.api.webhook.Payload;
import com.slack.api.webhook.WebhookResponse;
import java.io.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SlackbotMessageService {

    static final Logger logger = LoggerFactory.getLogger(SlackbotMessageService.class);
    private Properties webhookProp;

    private static SlackbotMessageService slackMessageService = null;

    private SlackbotMessageService() {
        webhookProp = new Properties();

        try {
            webhookProp.load(this.getClass().getResourceAsStream("/slackinfo.properties"));
        } catch(Exception e) {
            logger.error(e.getMessage());
        }
    }

    public static SlackbotMessageService getInstance() throws Exception {
        if(slackMessageService == null) {
            slackMessageService = new SlackbotMessageService();
        }
        return slackMessageService;
    }

    /**
     * 지정된 slack webhook url로 message 전송
     * 
     * @param message
     * @throws IOException
     */
    public WebhookResponse sendMessage(String message) throws Exception {
        Slack slack = Slack.getInstance();
        //ApiTestResponse response = slack.methods().apiTest(r -> r.foo("bar"));
        //System.out.println(response);

        String webhookUrl = webhookProp.getProperty("webhookurl");

        if(webhookUrl == null) {
            throw new Exception("webhookUrl not set in slackinfo.properties");
        }

        Payload payload = Payload.builder().text(message).build();

        WebhookResponse webhookResponse = slack.send(webhookUrl, payload);

        logger.info(webhookResponse.toString()); 

        return webhookResponse;
    }
}