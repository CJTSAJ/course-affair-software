package SJTU.SE.courseAffair.Action;

import SJTU.SE.courseAffair.Dao.HomeworkRepository;
import SJTU.SE.courseAffair.Dao.HwGradeRepository;
import SJTU.SE.courseAffair.Entity.HomeworkEntity;
import SJTU.SE.courseAffair.Entity.HwGradeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
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

    @CrossOrigin
    @RequestMapping(value="/getHomework",method=RequestMethod.POST)
    public JSONArray getAll(@RequestBody JSONObject data){
        String gid = data.getString("openGId");
        List<HomeworkEntity> list = homeworkRepository.findByHomeworkGroupId(gid);

        ArrayList<JSONArray> Json = new ArrayList<JSONArray>();
        for( int i = 0 ; i < list.size() ; i++) {//内部不锁定，效率最高，但在多线程要考虑并发操作的问题。
            HomeworkEntity temp = list.get(i);

            System.out.println(temp);
            ArrayList<String> arrayList = new ArrayList<String>();
            arrayList.add(temp.getHomeworkContent());
            DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            arrayList.add(sdf.format(temp.getHwdate()));
            arrayList.add(sdf.format(temp.getDeadline()));
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
        Timestamp time= new Timestamp(System.currentTimeMillis());
        HomeworkEntity homework = new HomeworkEntity();
        homework.setHomeworkContent(content);
        homework.setHomeworkGroupId(openGId);
        homework.setHwdate(time);
        homework.setPublisherId(openid);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        java.util.Date date = null;
        try {
            date = sf.parse(deadline);
            }
            catch (ParseException e) {
            e.printStackTrace();
            }
        Timestamp dateSQL = new Timestamp(date.getTime());
        homework.setDeadline(dateSQL);
        homeworkRepository.save(homework);
        }

    @CrossOrigin
    @RequestMapping(value="/getHwGrade",method=RequestMethod.POST)
    public String getGrade(@RequestBody JSONObject data) {
        String openid = data.getString("openid");
        String openGId = data.getString("openGId");
        int homeworkid = data.getInt("homeworkid");
        List<HwGradeEntity> res = hwGradeRepository.findByStudentIdAndStudentGroupIdAndHomeworkId(openid,openGId,homeworkid);
        if (res.size() == 0)
            return null;
        return res.get(0).getGrade();

    }


}
