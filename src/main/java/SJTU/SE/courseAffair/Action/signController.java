package SJTU.SE.courseAffair.Action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import SJTU.SE.courseAffair.Dao.SignInRecordRepository;
import SJTU.SE.courseAffair.Dao.SignRepository;
import SJTU.SE.courseAffair.Dao.StudentRepository;
import SJTU.SE.courseAffair.Entity.SignInEntity;
import SJTU.SE.courseAffair.Entity.SignInRecordEntity;
import SJTU.SE.courseAffair.Entity.StudentEntity;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RestController
public class signController {
	@Autowired
    private SignRepository signRepository;
	@Autowired
	SignInRecordRepository signInRecordRepository;
	@Autowired
    private StudentRepository studentRepository;
	
	@CrossOrigin
    @RequestMapping(value="/getSignRecord",method=RequestMethod.POST)
	public JSONObject getSignRecord(@RequestBody JSONObject data) {
		int id = data.getInt("id");
		List<SignInRecordEntity> signRecord = signInRecordRepository.findBySignInId(id);
		List<StudentEntity> students = studentRepository.findByStudentGroupId(signRecord.get(0).getStudentGroupId());
		
		int signLen = signRecord.size();
		int studentLen = students.size();
		List<JSONObject> success = new ArrayList();
		for(int i = 0; i < signLen; i++) {
			List<StudentEntity> temp = studentRepository.findByStudentIdAndStudentGroupId(students.get(i).getStudentId(), students.get(i).getStudentGroupId());
			JSONObject json = new JSONObject();
			json.put("sno", temp.get(0).getSno());
			json.put("sname", temp.get(0).getSname());
			success.add(json);
			String openid = signRecord.get(i).getStudentId();
			for(int j = 0; j < studentLen; j++) {
				System.out.println(students.get(j));
				if(students.get(j).getStudentId().equals(openid)) {
					students.remove(j);
					studentLen--;
					j--;
				}
			}
		}
		
		JSONObject result = new JSONObject();
		result.put("success", success);
		result.put("fail", students);
		return result;
	}
	@CrossOrigin
    @RequestMapping(value="/getAllSign",method=RequestMethod.POST)
	public List<SignInEntity> getAllSign(@RequestBody JSONObject data) {
		String opengid = data.getString("opengid");
		List<SignInEntity> signs = signRepository.findBySignInGroupId(opengid);
		return signs;
	}
	
	@CrossOrigin
    @RequestMapping(value="/sign",method=RequestMethod.POST)
	public JSONObject studentSign(@RequestBody JSONObject data) {
		String openid = data.getString("openid");
		String opengid = data.getString("opengid");
		String signCode = data.getString("signCode");
		double latitude = data.getDouble("latitude");
		double longitude = data.getDouble("longitude");
		
		System.out.println(latitude + " " + longitude);
		JSONObject json = new JSONObject();
		
		SignInEntity sign = signRepository.findRecentSignByGoupid(opengid);

		Timestamp currentTime= new Timestamp(System.currentTimeMillis());
		
		/*时间差不超过10分钟*/
		long deltTime = currentTime.getTime() - sign.getSignDate().getTime();
		System.out.println(deltTime);
		
		/*距离差不超过100米*/
		String startLonLat = String.valueOf(longitude) + "," + String.valueOf(latitude);
		String endLonLat = String.valueOf(longitude) + "," + String.valueOf(sign.getLatitude());
		Long deltDistance = getDistance(startLonLat, endLonLat);
		System.out.println(deltDistance);
		
		if(deltTime > 600000) {
			json.put("result", "fail");
			json.put("reason", "time");
		}else if(Integer.parseInt(signCode) != Integer.parseInt(sign.getSignCode())) {
			json.put("result", "fail");
			json.put("reason", "signCode");
		}else if(deltDistance > 100){
			json.put("result", "fail");
			json.put("reason", "distance");
		}else {
			List<SignInRecordEntity> temp = signInRecordRepository.findByStudentIdAndStudentGroupIdAndSignInId(openid, opengid, sign.getSignInId());
			if(temp.size() == 0) {
				json.put("result", "success");
				/*插入record表中*/
				SignInRecordEntity signInRecord = new SignInRecordEntity();
				signInRecord.setSignInId(sign.getSignInId());
				signInRecord.setStudentGroupId(opengid);
				signInRecord.setStudentId(openid);
				
				signInRecordRepository.save(signInRecord);
			}else {
				json.put("result", "fail");
				json.put("reason", "done");
			}
		}
		return json;
	}
	
	@CrossOrigin
    @RequestMapping(value="/addSign",method=RequestMethod.POST)
	public void addSign(@RequestBody JSONObject data) {
		String opengid = data.getString("opengid");
		String signCode = data.getString("signCode");
		double latitude = data.getDouble("latitude");
		double longitude = data.getDouble("longitude");
		
		//CoordinateConverter converter  = new CoordinateConverter();
		
		SignInEntity signIn = new SignInEntity();
		signIn.setLatitude(latitude);
		signIn.setLongitude(longitude);
		signIn.setSignCode(signCode);
		Timestamp signDate= new Timestamp(System.currentTimeMillis());
		signIn.setSignDate(signDate);
		signIn.setSignInGroupId(opengid);
		
		signRepository.save(signIn);
	}
	
	@RequestMapping(value="/testMap",method=RequestMethod.GET)
	public Long test() {
		String startLonLat = "121.44917,31.028815";
		String endLonLat = "121.448465,31.028633";
		Long result = getDistance(startLonLat,endLonLat);
		System.out.println(result);
		return result;
	}
	
	private static Long getDistance(String startLonLat, String endLonLat){
        //返回起始地startAddr与目的地endAddr之间的距离，单位：米
        Long result = new Long(0);
        String queryUrl = "http://restapi.amap.com/v3/distance?key=5866a861c514947f8147e38c8ccaf2aa&origins="+startLonLat+"&destination="+endLonLat;
        String queryResult = getResponse(queryUrl);
        
        JSONObject jo = new JSONObject().fromObject(queryResult);
        
        JSONArray ja = jo.getJSONArray("results");
 
        result = Long.parseLong(new JSONObject().fromObject(ja.getString(0)).get("distance").toString());
        return result;
//        return queryResult;
    }
	private static String getResponse(String serverUrl){
        //用JAVA发起http请求，并返回json格式的结果
        StringBuffer result = new StringBuffer();
        try {
            URL url = new URL(serverUrl);
            URLConnection conn = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
 
            String line;
            while((line = in.readLine()) != null){
                result.append(line);
            }
            in.close();
 
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

}
