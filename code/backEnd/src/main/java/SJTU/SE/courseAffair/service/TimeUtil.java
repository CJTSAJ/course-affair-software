package SJTU.SE.courseAffair.service;


import java.util.Date;
import java.util.concurrent.ScheduledFuture;

import SJTU.SE.courseAffair.Entity.FormEntity;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class TimeUtil {
    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @Autowired
    private RestTemplate restTemplate;

    private ScheduledFuture<?> future;

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        return new ThreadPoolTaskScheduler();
    }

    @Async
    public void startCron(String constr) {
        future = threadPoolTaskScheduler.schedule(new MyRunnable(), new CronTrigger(constr));
        System.out.println("DynamicTask.startCron()");

    }

    private class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("DynamicTask.MyRunnable.run()，" + new Date());
            for(int i = 0; i < Group.send.size();i++) {
                FormEntity temp = Group.send.get(i);
                String urlString = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=11_Fx9WO_Bax7_I1vMYFpb7swu3WPPbDnbQMcJPGR6fufJGhnTwvTFqVicn8r6BjIaXKYvh1AB4IWApFEDWVS46aFKaBTMf9mGSDBuZRjBIymVkun6YzsZSbazwo-r1ZDKduZpjSkoj3vpBPYLTUYZhACAIMN";
                JSONObject request = new JSONObject();
                request.put("touser", temp.getSId());
                request.put("template_id", "qkiK5Sh8u47NoKnlcqvBgMqzJ1p-2QsETyGgCxGhbc8");
                request.put("form_id", temp.getFormId());
                JSONObject data = new JSONObject();
                JSONObject data1 = new JSONObject();
                data1.put("value", "111111");
                data.put("keyword1",data1);
                JSONObject data2 = new JSONObject();
                data2.put("value", "222222");
                data.put("keyword1",data1);
                JSONObject data3 = new JSONObject();
                data3.put("value", "333333");
                data.put("keyword1",data1);
                request.put("data",data);

                // set headers
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
                HttpEntity<String> entity = new HttpEntity<String>(request.toJSONString(), headers);

                // send request and parse result
                restTemplate.exchange(urlString, HttpMethod.POST, entity, String.class);
            }

        }
    }

}
