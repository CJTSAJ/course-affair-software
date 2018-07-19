package SJTU.SE.courseAffair.Action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import SJTU.SE.courseAffair.Dao.TaRepository;
import SJTU.SE.courseAffair.Dao.TeacherRepository;
import SJTU.SE.courseAffair.Entity.StudentEntity;
import SJTU.SE.courseAffair.Entity.TaEntity;
import SJTU.SE.courseAffair.Entity.TeacherEntity;
import SJTU.SE.courseAffair.Dao.StudentRepository;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/*
 * 前端发来openid和opengid，获取该用户身份
 * 若不存在该用户返回[false]
 * 否则返回[true,身份(老师/助教/学生)]
 * */
@RestController
public class IdentityController {
	@Autowired
    private TeacherRepository teacherRepository;
	@Autowired
    private TaRepository taRepository;
	@Autowired
    private  StudentRepository studentRepository;
	
	@CrossOrigin
    @RequestMapping(value="/getIdentity",method=RequestMethod.POST)
	public JSONObject getIdentity(@RequestBody JSONObject data) {
		String openid = data.getString("openid");
		String opengid = data.getString("opengid");
		
		List<StudentEntity> student = studentRepository.findByStudentIdAndStudentGroupId(openid, opengid);
		List<TaEntity> ta = taRepository.findByTaidAndTaGroupId(openid, opengid);
		List<TeacherEntity> teacher = teacherRepository.findByTeacherIdAndTeacherGroupId(openid, opengid);
		
		JSONObject json = new JSONObject();
		if(student.size() == 1) {
			json.put("isExist", "true");
			json.put("identity", "student");
		}
		else if(ta.size() == 1) {
			json.put("isExist", "true");
			json.put("identity", "ta");
		}
		else if(teacher.size() == 1) {
			json.put("isExist", "true");
			json.put("identity", "teacher");
		}
		/*不存在该用户*/
		else {
			json.put("isExist", "false");
		}
		
		return json;
	}
	
	@CrossOrigin
    @RequestMapping(value="/addManager",method=RequestMethod.POST)
	public void addManager(@RequestBody JSONObject data){
		String opengid = data.getString("opengid");
		JSONArray openidArray = data.getJSONArray("openidArray");
		
		TaEntity ta = new TaEntity();
		for(int i = 0; i < openidArray.size(); i++) {
			
			/*添加到ta表*/
			String openid = String.valueOf(openidArray.get(i));
			
			System.out.println(openid);
			ta.setTaGroupId(opengid);
			ta.setTaid(openid);
			
			List<StudentEntity> temp = studentRepository.findByStudentIdAndStudentGroupId(openid, opengid);
			ta.setTaName(temp.get(0).getSname());
			ta.setTaNo(temp.get(0).getSno());
			
			taRepository.save(ta);
			
			/*从student表删除*/
			studentRepository.deleteByStudentIdAndStudentGroupId(openid, opengid);
		}
	}
	
	@CrossOrigin
    @RequestMapping(value="/getTeacherAndTa",method=RequestMethod.POST)
	public JSONObject getTeacherAndTa(@RequestBody JSONObject data) {
		String opengid = data.getString("opengid");
		
		List<TaEntity> taList = taRepository.findByTaGroupId(opengid);
		List<TeacherEntity> teacherList = teacherRepository.findByTeacherGroupId(opengid);
		
		JSONArray ta = JSONArray.fromObject(taList);
		JSONArray teacher = JSONArray.fromObject(teacherList);
		
		JSONObject json = new JSONObject();
		json.put("teacher", teacher);
		json.put("ta", ta);
		return json;
	}
	
	@CrossOrigin
    @RequestMapping(value="/deleteTa",method=RequestMethod.POST)
	public void deleteTa(@RequestBody JSONObject data) {
		String opengid = data.getString("opengid");
		String openid = data.getString("openid");
		
		List<TaEntity> ta = taRepository.findByTaidAndTaGroupId(openid, opengid);
		StudentEntity stu = new StudentEntity();
		
		/*插入到studentB表中*/
		stu.setSname(ta.get(0).getTaName());
		stu.setSno(ta.get(0).getTaNo());
		stu.setStudentGroupId(ta.get(0).getTaGroupId());
		stu.setStudentId(ta.get(0).getTaid());
		studentRepository.save(stu);
		
		/*从TA表中删除*/
		taRepository.deleteByTaidAndTaGroupId(openid, opengid);
		
	}
}
