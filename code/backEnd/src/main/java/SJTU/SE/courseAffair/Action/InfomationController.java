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
        String opengid = data.getString("opengid");
        String name = data.getString("name");
        String studentID = data.getString("studentID");
        String formid = data.getString("formId");
        StudentEntity stu = new StudentEntity();
        stu.setStudentId(openid);
        stu.setStudentGroupId(opengid);
        stu.setSname(name);
        stu.setSno(studentID);
        studentRepository.save(stu);
        FormEntity form = new FormEntity();
        form.setSId(openid);
        form.setFormId(formid);
        formRepository.save(form);
    }
}
