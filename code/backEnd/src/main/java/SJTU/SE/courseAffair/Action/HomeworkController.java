package SJTU.SE.courseAffair.Action;

import SJTU.SE.courseAffair.Dao.HomeworkRepository;
import SJTU.SE.courseAffair.Dao.HwGradeRepository;
import SJTU.SE.courseAffair.Entity.HomeworkEntity;
import SJTU.SE.courseAffair.Entity.HwGradeEntity;
import SJTU.SE.courseAffair.service.TimeUtil;
import SJTU.SE.courseAffair.service.Group;
import SJTU.SE.courseAffair.service.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;
import net.sf.json.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
public class HomeworkController {
    @Autowired
    private HomeworkRepository homeworkRepository;
    @Autowired
    private HwGradeRepository hwGradeRepository;
    @Autowired
    private TimeUtil timeUtil;
    @Autowired
    private RestTemplate restTemplate;

    @CrossOrigin
    @RequestMapping(value="/getHomework",method=RequestMethod.POST)
    public JSONArray getAll(@RequestBody JSONObject data){
        String gid = data.getString("openGId");
        List<HomeworkEntity> list = homeworkRepository.findByHomeworkGroupId(gid);

        /*错误判断：如果没有homework*/
        if(list.size() == 0) {
            System.out.println("homework为空");
            return null;
            }

        ArrayList<JSONArray> Json = new ArrayList<JSONArray>();
        for( int i = 0 ; i < list.size() ; i++) {//内部不锁定，效率最高，但在多线程要考虑并发操作的问题。
            HomeworkEntity temp = list.get(i);

            System.out.println(temp);
            ArrayList<String> arrayList = new ArrayList<String>();
            arrayList.add(temp.getHomeworkContent());
            DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            DateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            arrayList.add(sdf.format(temp.getHwdate()));
            arrayList.add(sdf1.format(temp.getDeadline()));
            arrayList.add(Integer.toString(temp.getHomeworkId()));
            Json.add(JSONArray.fromObject(arrayList));
        }
        JSONArray result = JSONArray.fromObject(Json.toArray());
        //System.out.println(list.get(0).getNotificationContent());
        return result;
    }

    @CrossOrigin
    @RequestMapping(value="/addHomework",method=RequestMethod.POST)
    public void save(@RequestBody JSONObject data){
        String content=data.getString("content");
        String openGId=data.getString("openGId");
        String deadline=data.getString("deadline");
        String openid=data.getString("openid");
        Group.openGId  = openGId;
        Timestamp time= new Timestamp(System.currentTimeMillis());
        HomeworkEntity homework = new HomeworkEntity();
        homework.setHomeworkContent(content);
        homework.setHomeworkGroupId(openGId);
        homework.setHwdate(time);
        homework.setPublisherId(openid);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        java.util.Date date = null;
        try {
            date = sf.parse(deadline);
            }
            catch (ParseException e) {
            e.printStackTrace();
            }
        SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy");
        SimpleDateFormat sdf1 = new SimpleDateFormat("MM");
        SimpleDateFormat sdf2= new SimpleDateFormat("dd");
        SimpleDateFormat sdf3 = new SimpleDateFormat("HH");
        SimpleDateFormat sdf4= new SimpleDateFormat("mm");
        String year = sdf0.format(date);
        String month = sdf1.format(date);
        String day = sdf2.format(date);
        String hour = sdf3.format(date);
        String minute = sdf4.format(date);
        Integer dint = Integer.parseInt(day);
        if(dint == 1) {
            int mint = Integer.parseInt(month);
            switch (mint) {
                case 2: case 4: case 6: case 8: case 9: case 11:
                    day = String.valueOf(31);
                    break;
                case 5: case 7: case 10: case 12:
                    day = String.valueOf(30);
                    break;
                case 3:
                    day = String.valueOf(28);
                    break;
            }
            month = String.valueOf(mint-1);
        }
        else {
            day = String.valueOf(dint-1);
        }
        String conStr = "0 "+minute+" "+hour+" "+day+" "+month+" ?";
        timeUtil.startCron(conStr);
        Timestamp dateSQL = new Timestamp(date.getTime());
        homework.setDeadline(dateSQL);
        homeworkRepository.save(homework);

        }

    @CrossOrigin
    @RequestMapping(value="/getHwGrade",method=RequestMethod.POST)
    public String getGrade(@RequestBody JSONObject data) {
        String openid = data.getString("openid");
        String openGId = data.getString("openGId");
        int homeworkid = data.getInteger("homeworkid");
        List<HwGradeEntity> res = hwGradeRepository.findByStudentIdAndStudentGroupIdAndHomeworkId(openid,openGId,homeworkid);
        if (res.size() == 0)
            return null;
        return res.get(0).getGrade();

    }

    @RequestMapping(value="/test")
    public String test() {
        String urlString = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=11_IsdVX3ncQe7z2OrRxc7jAqE8KhXvQeuelbvCeDa2-UWXhEOD43i4x5euDym1EEWpynnSGmoeFJ_okl2BaM3Xiv1qF_iNmqc4FUPFLkfrihc4wOaah0ct1qDDieCUJTC1oaVCTmaINwWK3F62NWNeAEAGPG";
        JSONObject request = new JSONObject();
        request.put("offset", 0);
        request.put("count", 20);


        /*String result = HttpRequest.httpsRequest(urlString, "POST", request.toJSONString());
        return result;*/
        // set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<>();
        paramMap.add("offset",0 );
        paramMap.add("count",1);

        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<MultiValueMap<String, Object>>(paramMap, headers);

        // send request and parse result
        ResponseEntity<String> loginResponse = restTemplate.exchange(urlString, HttpMethod.POST, entity, String.class);
        if (loginResponse.getStatusCode() == HttpStatus.OK) {
            return loginResponse.getBody();
        } else if (loginResponse.getStatusCode() == HttpStatus.UNAUTHORIZED) {
            return "Trying fail.";
            // nono... bad credentials
        }
        return null;
    }


}
