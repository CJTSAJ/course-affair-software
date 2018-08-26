package SJTU.SE.courseAffair.Action;


import SJTU.SE.courseAffair.Dao.NotificationRepository;
import SJTU.SE.courseAffair.Dao.StudentRepository;
import SJTU.SE.courseAffair.Dao.TaRepository;
import SJTU.SE.courseAffair.Dao.TeacherRepository;
import SJTU.SE.courseAffair.Entity.HomeworkEntity;
import SJTU.SE.courseAffair.Entity.NotificationEntity;
import SJTU.SE.courseAffair.Entity.StudentEntity;
import SJTU.SE.courseAffair.Entity.TaEntity;
import SJTU.SE.courseAffair.Entity.TeacherEntity;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/hibernate")
@EnableAutoConfiguration
public class HibernateController {


    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private TaRepository taRepository;
    @Autowired
    private StudentRepository studentRepository;



    @RequestMapping("getById")
    @ResponseBody
    public NotificationEntity getById(int Id){
        return notificationRepository.findByNotificationId(Id);
    }

    @CrossOrigin
    @RequestMapping(value = "/getNotice", method = RequestMethod.POST)
    public JSONArray getAll(@RequestBody String opengid){
    	System.out.println(opengid);
    	Map<String, Object> modelMap = new HashMap<String, Object>();
    	List<NotificationEntity> list = notificationRepository.findByNotificationGroupIdOrderByNotificationDateDesc(opengid);

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
    	    String openid = temp.getNotificationPublisherId();
    	    List<TeacherEntity> teacherList = teacherRepository.findByTeacherIdAndTeacherGroupId(openid,opengid);
    	    if(teacherList.size()>0) {
    	        String teacherName = teacherList.get(0).getTeacherName();
    	        arrayList.add(teacherName);
            }
            else {
    	        List<TaEntity> taList = taRepository.findByTaidAndTaGroupId(openid,opengid);
    	        if(taList.size()>0) {
    	            String taName = taList.get(0).getTaName();
    	            arrayList.add(taName);
                }
                else {
    	            List<StudentEntity> stuList = studentRepository.findByStudentIdAndStudentGroupId(openid,opengid);
    	            if(stuList.size()>0) {
    	                String stuName = stuList.get(0).getSname();
    	                arrayList.add(stuName);
                    }
                }
            }
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
        Timestamp time= new Timestamp(System.currentTimeMillis());
        NotificationEntity notificationEntity= new NotificationEntity();
        notificationEntity.setNotificationContent(content);
        notificationEntity.setNotificationDate(time);
        notificationEntity.setNotificationGroupId(openGId);
        notificationEntity.setNotificationPublisherId(openid);
        notificationRepository.save(notificationEntity);
        }
    
    @CrossOrigin
    @RequestMapping(value="/getRecentNotification",method=RequestMethod.POST)
    public List<NotificationEntity> getRecentHomework(@RequestBody JSONObject data) {
    	String opengid = data.getString("opengid");
    	return notificationRepository.findRecentNotification(opengid);
    }

    /*@RequestMapping(value = "addNotice", method = RequestMethod.GET)
    public void save(String openid, String content, HttpServletResponse response,Model model)
    		throws IOException{
    	response.setContentType("text/html;charset=utf-8");          
        /* 设置响应头允许ajax跨域访问 */  
        /*response.setHeader("Access-Control-Allow-Origin", "*");
        /* 星号表示所有的异域请求都可以接受， */  
        //response.setHeader("Access-Control-Allow-Methods", "GET,POST");
    	/*String openid = request.getParameter("openid");
    	String content = request.getParameter("content");*/
    	/*System.out.println("addNotice");
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
