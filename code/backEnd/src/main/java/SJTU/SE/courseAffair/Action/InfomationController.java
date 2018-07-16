package SJTU.SE.courseAffair.Action;
import SJTU.SE.courseAffair.Dao.FormRepository;
import SJTU.SE.courseAffair.Dao.StudentRepository;
import SJTU.SE.courseAffair.Entity.FormEntity;
import SJTU.SE.courseAffair.Entity.StudentEntity;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;


@RestController
public class InfomationController {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private FormRepository formRepository;

    @CrossOrigin
    @RequestMapping(value="/login",method=RequestMethod.POST)
    public void saveInfo(@RequestBody JSONObject data) {
        String openid = data.getString("openid");
        System.out.println("存储学生信息");
        System.out.println("openid:"+openid);
        String opengid = data.getString("opengid");
        System.out.println("opengid:"+opengid);
        String name = data.getString("name");
        System.out.println("name:"+name);
        String studentID = data.getString("studentID");
        System.out.println("studentID:"+studentID);
        String formid = data.getString("formId");
        System.out.println("formid:"+formid);
        StudentEntity stu = new StudentEntity();
        stu.setStudentId(openid);
        stu.setStudentGroupId(opengid);
        stu.setSname(name);
        stu.setSno(studentID);
        studentRepository.save(stu);
        FormEntity form = new FormEntity();
        form.setStuId(openid);
        form.setFormId(formid);
        formRepository.save(form);
    }
}
