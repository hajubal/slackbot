package com.hajubal.slackbot;

import org.junit.Assert;
import org.junit.Test;

public class SlackbotApplicationTest {

    @Test
    public void sendMessageTest() throws Exception {
        SlackbotMessageService.getInstance().sendMessage("Message Test.");
        assert(true);
    }
}