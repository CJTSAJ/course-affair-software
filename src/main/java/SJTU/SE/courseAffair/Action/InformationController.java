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
import SJTU.SE.courseAffair.Entity.StudentEntity;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RestController
public class InformationController {
	@Autowired
    private StudentRepository studentRepository;
    @Autowired
    private FormRepository formRepository;
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
}
