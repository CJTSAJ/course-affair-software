package SJTU.SE.courseAffair.Action;

import SJTU.SE.courseAffair.Dao.FormRepository;
import SJTU.SE.courseAffair.Dao.HomeworkRepository;
import SJTU.SE.courseAffair.Dao.HwGradeRepository;
import SJTU.SE.courseAffair.Dao.StudentRepository;
import SJTU.SE.courseAffair.Dao.TaRepository;
import SJTU.SE.courseAffair.Dao.TeacherRepository;
import SJTU.SE.courseAffair.Entity.FormEntity;
import SJTU.SE.courseAffair.Entity.HomeworkEntity;
import SJTU.SE.courseAffair.Entity.HwGradeEntity;
import SJTU.SE.courseAffair.Entity.StudentEntity;
import SJTU.SE.courseAffair.Entity.TaEntity;
import SJTU.SE.courseAffair.Entity.TeacherEntity;
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
import java.util.Date;
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
    @Autowired
    private FormRepository formRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private TaRepository taRepository;


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
            DateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
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
        List<StudentEntity> students = studentRepository.findByStudentGroupId(openGId);
        //System.out.println("student表里有"+students.size()+"条数据");
        List<FormEntity> ftemp = new ArrayList<FormEntity>();
        for(int i = 0;i<students.size();i++){
            String sId = students.get(i).getStudentId();
            //System.out.println("要发给openid:"+students.get(i).getStudentId());
            //System.out.println("form表里有"+formRepository.count()+"条数据");
            List<FormEntity> forms = formRepository.findByStuId(sId);
            //List<FormEntity> forms = formRepository.findAll();
            //System.out.println("form表里有"+forms.size()+"条数据");
            if(forms.size()>0) {
                FormEntity temp = forms.get(0);
                //System.out .println("输出form: stuId:"+temp.getStuId()+" formId:"+temp.getFormId());
                //temp.setStuId(openid);
                Group.send.add(temp);
                ftemp.add(temp);
                formRepository.delete(temp);
            }
            /*FormEntity tem = new FormEntity();
            tem.setStuId(openid);
            tem.setFormId("81333494665d63114a9523ad0b219f4a");
            Group.send.add(tem);*/
        }

        Timestamp time= new Timestamp(System.currentTimeMillis());
        int homeworkTotal = homeworkRepository.findAll().size();
        HomeworkEntity homework = new HomeworkEntity();
        //homework.setHomeworkId(homeworkTotal);
        homework.setHomeworkContent(content);
        homework.setHomeworkGroupId(openGId);
        homework.setHwdate(time);
        homework.setPublisherId(openid);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = null;
        try {
            date = sf.parse(deadline);
            }
            catch (ParseException e) {
            e.printStackTrace();
            }
        /*Robust version*/
        if (Group.homeworkList.size()>0) {
            int j;
            for (j = 0;j<Group.homeworkList.size();j++) {
                String dtmpStr = sf.format(Group.homeworkList.get(j).getDeadline());
                Date dtmp = null;
                try {
                    dtmp = sf.parse(dtmpStr);
                }
                catch (ParseException e) {
                    e.printStackTrace();
                }
                if (dtmp.getTime()>date.getTime()) {
                    Group.homeworkList.add(j,homework);
                    break;
                }
            }
            if(j == Group.homeworkList.size()) {
                Group.homeworkList.add(homework);
                Group.sends.add(ftemp);
            }
            else {
                Group.sends.add(j,ftemp);
            }
        }
        else {
            Group.homeworkList.add(homework);
            Group.sends.add(ftemp);
        }
        /*Robust version end*/
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
        Group.homework = homework;
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
    
    @CrossOrigin
    @RequestMapping(value="/getRecentHomework",method=RequestMethod.POST)
    public List<JSONObject> getRecentHomework(@RequestBody JSONObject data) {
    	String opengid = data.getString("opengid");
    	List<HomeworkEntity> homework = homeworkRepository.findRecentHomework(opengid);
    	DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
    	int len = homework.size();
    	List<JSONObject> result = new ArrayList();
    	for(int i = 0; i < len; i++) {
    		JSONObject temp = new JSONObject();
    		temp.put("deadline", sdf.format(homework.get(i).getDeadline()));
    		temp.put("date", sdf.format(homework.get(i).getHwdate()));
    		temp.put("content", homework.get(i).getHomeworkContent());
    		temp.put("id", homework.get(i).getHomeworkId());
    		List<TeacherEntity> teacher = teacherRepository.findByTeacherIdAndTeacherGroupId(homework.get(i).getPublisherId(), opengid);
    		if(teacher.size() != 0) {
    			temp.put("publisher", teacher.get(0).getTeacherName());
    		}else {
    			List<TaEntity> ta = taRepository.findByTaidAndTaGroupId(homework.get(i).getPublisherId(), opengid);
    			if(ta.size() != 0) {
    				temp.put("publisher", ta.get(0).getTaName());
    			}else {
    				List<StudentEntity> stu = studentRepository.findByStudentIdAndStudentGroupId(homework.get(i).getPublisherId(), opengid);
    				temp.put("publisher", stu.get(i).getSname());
    			}
    			
    		}
    		result.add(temp);
    	}
    	return result;
    }
    
    @CrossOrigin
    @RequestMapping(value="/addFormid",method=RequestMethod.POST)
    public void addFromid(@RequestBody JSONObject data) {
    	String openid = data.getString("openid");
    	String formid = data.getString("formid");
    	FormEntity form = new FormEntity();
    	form.setFormId(formid);
    	form.setStuId(openid);
    	
    	formRepository.save(form);
    }

    @RequestMapping(value="/homeworkTest")
    public void htest() {
        String openid = studentRepository.findAll().get(0).getStudentId();
        FormEntity tem = new FormEntity();
        tem.setStuId(openid);
        tem.setFormId("55e1efa14474ff5baa074e3418f65643");
        formRepository.save(tem);
        FormEntity tem1 = new FormEntity();
        tem1.setStuId(openid);
        tem1.setFormId("0678dd85a9de035614ba137674b5c86c");
        formRepository.save(tem1);
    }


}
