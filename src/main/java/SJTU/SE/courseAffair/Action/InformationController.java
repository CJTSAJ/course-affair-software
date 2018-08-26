package SJTU.SE.courseAffair.Action;

import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
import SJTU.SE.courseAffair.service.ExcelUtil;
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
    		json.put("studentid", student.getSno());
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
    
    @CrossOrigin
    @RequestMapping(value="/getAllStudentExcel",method=RequestMethod.GET)
    public void getAllStudentExcel(String opengid, HttpServletResponse response) throws Exception{
    	String[] title = {"学号", "姓名"};
    	String fileName = "students.xls";
    	
    	List<StudentEntity> list = studentRepository.findByStudentGroupId(opengid);
    	int len = list.size();
    	String [][] content = new String[len][2];
    	
    	for(int i = 0; i < len; i++) {
    		StudentEntity temp = list.get(i);
    		
    		content[i][0] = temp.getSno();
    		content[i][1] = temp.getSname();
    	}
    	
    	HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook("学生信息表", title, content, null);
    	
    	try {
    		response.setCharacterEncoding("UTF-8");
    		response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
    		OutputStream stream = response.getOutputStream();
    		wb.write(stream);
    		stream.flush();
    		stream.close();
    	}catch (Exception e) {
    		e.printStackTrace();
    	}
    }
}
