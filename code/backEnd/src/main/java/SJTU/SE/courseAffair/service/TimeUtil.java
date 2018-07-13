package SJTU.SE.courseAffair.service;


import java.util.Date;
import java.util.concurrent.ScheduledFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
            /*for(int i = 0; i < Group.send.size();i++) {
                FormEntity temp = Group.send.get(i);
                String urlString = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=11_Fx9WO_Bax7_I1vMYFpb7swu3WPPbDnbQMcJPGR6fufJGhnTwvTFqVicn8r6BjIaXKYvh1AB4IWApFEDWVS46aFKaBTMf9mGSDBuZRjBIymVkun6YzsZSbazwo-r1ZDKduZpjSkoj3vpBPYLTUYZhACAIMN";
                JSONObject request = new JSONObject();
                request.put("touser", temp.getStudentId());
                request.put("template_id", "");
                request.put("form_id", temp.getFormId());
                JSONObject data1 = new JSONObject();
                data1.put("value", "xxx");


                // set headers
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
                HttpEntity<String> entity = new HttpEntity<String>(request.toString(), headers);

                // send request and parse result
                restTemplate.exchange(urlString, HttpMethod.POST, entity, String.class);
            }*/

        }
    }

}
