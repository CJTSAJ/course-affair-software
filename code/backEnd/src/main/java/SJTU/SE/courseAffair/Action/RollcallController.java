package SJTU.SE.courseAffair.Action;
import SJTU.SE.courseAffair.Dao.StudentRepository;
import SJTU.SE.courseAffair.Entity.StudentEntity;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Random;

@RestController
public class RollcallController {
    @Autowired
    private StudentRepository studentRepository;


    @CrossOrigin
    @RequestMapping(value="/pick",method=RequestMethod.POST)
    public JSONObject pick(@RequestBody JSONObject data) {
        Random ran=new Random();
        String opengid = data.getString("openGId");
        List<StudentEntity> list = studentRepository.findByStudentGroupId(opengid);
        int no = ran.nextInt(list.size());
        JSONObject stu = new JSONObject();
        stu.put("studentId",list.get(no).getSno());
        stu.put("studentName",list.get(no).getSname());
        return stu;
    }
}
