package SJTU.SE.courseAffair.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.alibaba.fastjson.JSONObject;


@Component
public class HttpRequest {
    @Autowired
    private RestTemplate restTemplate;

    @Scheduled(initialDelay = 2000,fixedRate = 7200000)
    public void getAccessToken(){
        String urlString = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxfb56f7506a576cf3&secret=5e961359ec8cd3648d6f9de665a4b698";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        // send request and parse result
        ResponseEntity<String> loginResponse = restTemplate.exchange(urlString, HttpMethod.GET, entity, String.class);
        System.out.println("已经请求了AccessToken");
        if (loginResponse.getStatusCode() == HttpStatus.OK) {
            System.out.println(loginResponse.getBody());
            JSONObject data = JSONObject.parseObject(loginResponse.getBody());
            String accessToken = data.getString("access_token");
            System.out.println("提取到的AccessToken:"+accessToken);
            Group.accessToken = accessToken;

        }

    }


}

