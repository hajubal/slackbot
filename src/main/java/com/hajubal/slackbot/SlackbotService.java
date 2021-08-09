package com.hajubal.slackbot;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SlackbotService {

    static final Logger logger = LoggerFactory.getLogger(SlackbotService.class);
    
    /**
     * targetUrl response body에 str 문자열이 포함되었는지 여부
     * 
     * @param targetUrl
     * @param strs
     * @return
     * @throws Exception
     */
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
     * @return
     * @throws Exception
     */
    public boolean checkRuliweb() throws Exception {
        
        boolean result = hasItem("https://m.ruliweb.com/ps/board/1020", "충전거치대", "충전 거치대");

        if(result) {
            SlackbotMessageService.getInstance().sendMessage("매물 등장!!! https://m.ruliweb.com/ps/board/1020");
        }

        return result;
    }

    /**
     * 파트너샵에 충전거지대 매물 확인
     * 
     * @return
     * @throws Exception
     */
    public boolean checkPartenerShop() throws Exception {

        boolean result = hasItem("https://partnershopplus.com/shop/item.php?it_id=1617693034", "sit_ov_soldout");

        if(!result) {
            SlackbotMessageService.getInstance().sendMessage("매물 등장!!! https://partnershopplus.com/shop/item.php?it_id=1617693034");
        }

        return !result;
    }
}
