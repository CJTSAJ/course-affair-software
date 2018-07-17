package SJTU.SE.courseAffair.service;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ScheduledFuture;

import SJTU.SE.courseAffair.Entity.FormEntity;
import SJTU.SE.courseAffair.Entity.HomeworkEntity;
import SJTU.SE.courseAffair.service.Group;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
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
            System.out.println("要给"+Group.send.size()+"个人发消息");
            HomeworkEntity homework = Group.homework;
            DateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String deadline = sdf1.format(homework.getDeadline());
            String content = homework.getHomeworkContent();
            for(int i = Group.send.size()-1; i > -1;i--) {
                System.out.println("要给"+Group.send.size()+"个人发消息");
                FormEntity temp = Group.send.get(i);
                String urlString = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token="+Group.accessToken;//access_token=11_N0kx4EuXLCizdGkce4j2Bqq5V-j7iINJrlftGkH-O5lk-y7nKo6nS7EY4FbstpciFyk6kyWsKkQJxn9VLhoq2toEpOecXJJ_PqqKRFvSGwvyq05ZHyhm4r9miBZa4ybklB-pvYdD1uyahiomOBTbAAAUHZ";
                JSONObject request = new JSONObject();
                request.put("touser", temp.getStuId());
                System.out.println("发给openid:"+temp.getStuId());
                request.put("template_id", "qkiK5Sh8u47NoKnlcqvBgMqzJ1p-2QsETyGgCxGhbc8");
                request.put("form_id", temp.getFormId());
                System.out.println("使用formId:"+temp.getFormId());
                JSONObject data = new JSONObject();
                JSONObject data1 = new JSONObject();
                data1.put("value", "课程名称");
                data.put("keyword1",data1);
                JSONObject data2 = new JSONObject();
                data2.put("value", deadline);
                data.put("keyword2",data2);
                JSONObject data3 = new JSONObject();
                data3.put("value", content);
                data.put("keyword3",data3);
                request.put("data",data);
                System.out.println("参数组装完成");

                // set headers
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
                HttpEntity<String> entity = new HttpEntity<String>(request.toJSONString(), headers);

                // send request and parse result
                ResponseEntity<String> loginResponse = restTemplate.exchange(urlString, HttpMethod.POST, entity, String.class);
                System.out.println("已经发了消息");
                if (loginResponse.getStatusCode() == HttpStatus.OK) {
                    System.out.println(loginResponse.getBody());
                } else if (loginResponse.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                    System.out.println("Trying fail.");
                    // nono... bad credentials
                }
                Group.send.remove(i);
                System.out.println("Group里还有"+Group.send.size()+"个FormEntity");
            }

        }
    }

}
