package com.hajubal.slackbot;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.Assert;

public class SlackbotApplicationTest {

    @Test
    public void sendMessageTest() throws Exception {
        SlackbotMessageService.getInstance().sendMessage("Message Test.");
        assert(true);
    }

    @Test
    public void ruliwebTest() throws Exception {
        Document document = Jsoup.connect("https://m.ruliweb.com/ps/board/1020").get();

        Elements elements = document.select("div.title.row>a.subject_link.deco");

        elements.forEach(action -> {
            if(action.text().contains("충전거치대") || action.text().contains("충전 거치대")) {
                System.out.println(action.attr("href"));
            }
        });

        assert(true);
    }

    @Test
    public void partenerShopTest() throws Exception {
        String url = "https://partnershopplus.com/shop/item.php?it_id=1617693034";

        Document document = Jsoup.connect(url).get();

        Elements elements = document.select("p#sit_ov_soldout");

        assert(true);
    }
}