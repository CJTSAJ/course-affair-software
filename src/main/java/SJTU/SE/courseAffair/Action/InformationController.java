package SJTU.SE.courseAffair.Action;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import SJTU.SE.courseAffair.Dao.FormRepository;
import SJTU.SE.courseAffair.Dao.StudentRepository;
import SJTU.SE.courseAffair.Dao.TeacherRepository;
import SJTU.SE.courseAffair.Entity.FormEntity;
import SJTU.SE.courseAffair.Entity.StudentEntity;
import SJTU.SE.courseAffair.Entity.TeacherEntity;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RestController
public class InformationController {
	@Autowired
    private StudentRepository studentRepository;
    @Autowired
    private FormRepository formRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @CrossOrigin
    @RequestMapping(value="/getStudentByGid",method=RequestMethod.POST)
    public JSONArray getStudentByGid(@RequestBody JSONObject data) {
    	String opengid = data.getString("opengid");
    	
    	List<StudentEntity> list = studentRepository.findByStudentGroupId(opengid);
    	
    	JSONArray josnArray = new JSONArray();
    	for(StudentEntity student : list) {
    		JSONObject json = new JSONObject();
    		json.put("studentName", student.getSname());
    		json.put("openid", student.getStudentId());
    		josnArray.add(json);
    	}
    	
    	return josnArray;
    }
    
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
        if (!formid.contains("mock")) {
            FormEntity form = new FormEntity();
            form.setStuId(openid);

            form.setFormId(formid);
            formRepository.save(form);
        }
    }
    
    @CrossOrigin
    @RequestMapping(value="/registerTeacher",method=RequestMethod.POST)
    public void registerTeacher(@RequestBody JSONObject data) {
    	String openid = data.getString("openid");
    	String opengid = data.getString("opengid");
    	String name = data.getString("name");
    	
    	TeacherEntity teacher = new TeacherEntity();
    	teacher.setTeacherGroupId(opengid);
    	teacher.setTeacherId(openid);
    	teacher.setTeacherName(name);
    	teacherRepository.save(teacher);
    }
}
