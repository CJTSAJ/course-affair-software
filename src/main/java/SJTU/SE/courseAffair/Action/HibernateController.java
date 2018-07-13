package SJTU.SE.courseAffair.Action;


import SJTU.SE.courseAffair.Dao.NotificationRepository;
import SJTU.SE.courseAffair.Entity.NotificationEntity;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/hibernate")
@EnableAutoConfiguration
public class HibernateController {


    @Autowired
    private NotificationRepository notificationRepository;


    @RequestMapping("getAll")
    @ResponseBody
    public List<NotificationEntity> getAll(){
        return notificationRepository.getAllBy();
    }

    @RequestMapping("getById")
    @ResponseBody
    public NotificationEntity getById(int Id){
        return notificationRepository.findByNotificationId(Id);
    }
    
    @CrossOrigin
    @RequestMapping(value = "getNotice", method = RequestMethod.POST)
    public JSONArray getAll(@RequestBody String opengid){
    	System.out.println(opengid);
    	//Map<String, Object> modelMap = new HashMap<String, Object>();
    	List<NotificationEntity> list = notificationRepository.findByNotificationGroupId(opengid);
    	
    	if(list.size()==0) {
    		System.out.println("notice为空");
    		return null;
    	}
    	ArrayList<JSONArray> Json = new ArrayList<JSONArray>();
    	for( int i = 0 ; i < list.size() ; i++) {//内部不锁定，效率最高，但在多线程要考虑并发操作的问题。
    		NotificationEntity temp = list.get(i);
    		
    		System.out.println(temp);
    	    ArrayList<String> arrayList = new ArrayList<String>();
    	    arrayList.add(temp.getNotificationContent());
    	    DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	    arrayList.add(sdf.format(temp.getNotificationDate()));
    	    Json.add(JSONArray.fromObject(arrayList));
    	}
    	JSONArray result = JSONArray.fromObject(Json.toArray());
    	System.out.println(list.get(0).getNotificationContent());
        return result;
    }
    
    @CrossOrigin
    @RequestMapping(value="/addNotice",method = RequestMethod.POST)
    public void save(@RequestBody JSONObject data){
        String openid=data.getString("openid");
        String content=data.getString("content");
        String openGId=data.getString("openGId");
        System.out.println("openid" + openid + "content" + content + "openGId" + openGId);
        Timestamp time= new Timestamp(System.currentTimeMillis());
        NotificationEntity notificationEntity= new NotificationEntity();
        notificationEntity.setNotificationContent(content);
        notificationEntity.setNotificationDate(time);
        notificationEntity.setNotificationGroupId(openGId);
        notificationEntity.setNotificationPublisherId(openid);
        notificationRepository.save(notificationEntity);
    }
    /*@RequestMapping(value = "addNotice", method = RequestMethod.GET)
    public void save(String openid, String content, HttpServletResponse response,Model model)
    		throws IOException{
    	response.setContentType("text/html;charset=utf-8");          
        response.setHeader("Access-Control-Allow-Origin", "*");  
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
    	System.out.println("addNotice");
    	System.out.println(content);
    	System.out.println(openid);
        Timestamp time= new Timestamp(System.currentTimeMillis());
        NotificationEntity notificationEntity= new NotificationEntity();
        notificationEntity.setNotificationContent(content);
        notificationEntity.setNotificationDate(time);
        notificationEntity.setNotificationGroupId(openid);
        notificationEntity.setNotificationPublisherId(openid);
        userRepository.save(notificationEntity);
    }*/

}
