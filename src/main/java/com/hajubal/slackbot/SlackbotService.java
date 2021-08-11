package com.hajubal.slackbot;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SlackbotService {

    static final Logger logger = LoggerFactory.getLogger(SlackbotService.class);

    List<String> outOfStackList = new ArrayList<>();
    
    /**
     * targetUrl response body에 str 문자열이 포함되었는지 여부
     * 
     * @param targetUrl
     * @param strs
     * @return
     * @throws Exception
     */
    @Deprecated
    public boolean hasItem(String targetUrl, String... strs) throws Exception {
        URL url = new URL(targetUrl);

        URLConnection con = url.openConnection();

        BufferedReader isr = new BufferedReader(new InputStreamReader(con.getInputStream()));

        StringBuilder sb = new StringBuilder();
        String token = null;

        while((token = isr.readLine()) != null) {
            sb.append(token);
        }

        for(String str : strs) {
            if(sb.toString().contains(str)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 루리웹 알뜰 게시판에 충전거치대 매물 확인
     * 
     * @throws Exception
     */
    public void checkRuliweb() throws Exception {
        String url = "https://m.ruliweb.com/ps/board/1020";

        Document document = Jsoup.connect(url).get();

        Elements elements = document.select("div.title.row>a.subject_link.deco");

        elements.forEach(action -> {
            String text = action.text();

            if((text.contains("충전거치대") || text.contains("충전 거치대")) && !text.contains("품절")) {
                String attr = action.attr("href");
                
                if(this.outOfStackList.contains(attr) == false) {
                    this.outOfStackList.add(attr);
                    try {
                        SlackbotMessageService.getInstance().sendMessage("매물 등장!!! " + url);
                    } catch (Exception e) {
                        this.logger.error(e.getMessage(), e.getCause());
                    }

                    return;
                }
            }
        });
    }

    /**
     * 파트너샵에 충전거지대 매물 확인
     * 
     * @throws Exception
     */
    public void checkPartenerShop() throws Exception {
        String url = "https://partnershopplus.com/shop/item.php?it_id=1617693034";

        Document document = Jsoup.connect(url).get();

        Elements elements = document.select("p#sit_ov_soldout");

        if(elements.size() == 0) {
            SlackbotMessageService.getInstance().sendMessage("매물 등장!!! " + url);
        }
    }
}