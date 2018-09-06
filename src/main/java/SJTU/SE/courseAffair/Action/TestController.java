package SJTU.SE.courseAffair.Action;


import SJTU.SE.courseAffair.Dao.*;
import SJTU.SE.courseAffair.Entity.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@RestController
@EnableAutoConfiguration
public class TestController {
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private ChoiceRepository choiceRepository;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private CorrectAnswerRepository correctAnswerRepository;
    @Autowired
    private TestGradeRepository testGradeRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TaRepository taRepository;
    
    @CrossOrigin
    @RequestMapping(value = "getStudentGrade", method = RequestMethod.POST)
    public JSONObject getStudentGrade(@RequestBody JSONObject data) {
    	int testid = data.getInt("testid");
    	String opengid = data.getString("opengid");
    	List<TestGradeEntity> grade = testGradeRepository.findByTestId(testid);
    	List<StudentEntity> students = studentRepository.findByStudentGroupId(opengid);
    	int len = grade.size();
    	int size = students.size();
    	List<JSONObject> result = new ArrayList();
    	JSONObject result_ = new JSONObject();
    	for(int i = 0; i < len; i++) {
    		JSONObject json = new JSONObject();
    		List<StudentEntity> temp = studentRepository.findByStudentIdAndStudentGroupId(grade.get(i).getStudentId(), grade.get(i).getStudentGroupId());
    		if(temp.size() == 0) {
    			List<TaEntity> ta = taRepository.findByTaidAndTaGroupId(grade.get(i).getStudentId(), grade.get(i).getStudentGroupId());
    			json.put("name", ta.get(0).getTaName());
    			json.put("id", ta.get(0).getTaNo());
    		}else {
    			json.put("name", temp.get(0).getSname());
        		json.put("id", temp.get(0).getSno());
    		}
    		json.put("grade", grade.get(i).getGrade());
    		result.add(json);
    		
    		for(int j = 0; j < size; j++) {
    			if(students.get(j).getStudentId().equals(grade.get(i).getStudentId())) {
    				students.remove(j);
    				size--;
    				j--;
    			}
    		}
    	}
    	if(len == 0) {
    		result_.put("success", "null");
    		result_.put("fail", students);
    		return result_;
    	}else {
    		result_.put("success", result);
    		result_.put("fail", students);
    		return result_;
    	}
    }

    @CrossOrigin
    @RequestMapping(value = "getTest", method = RequestMethod.POST)
    public JSONArray getAll(@RequestBody String opengid){
        System.out.println("getTest");
        List<TestEntity> list = testRepository.findByTestGroupId(opengid);
        if(list.size() == 0) {
            System.out.println("test为空");
            return null;
        }
        ArrayList<JSONArray> Json = new ArrayList<JSONArray>();
        for (TestEntity temp : list) {//内部不锁定，效率最高，但在多线程要考虑并发操作的问题。
            ArrayList<String> arrayList = new ArrayList<String>();
            arrayList.add(temp.getTestContent());
            arrayList.add(String.valueOf(temp.getStartTime()));
            arrayList.add(String.valueOf(temp.getEndTime()));
            arrayList.add(String.valueOf(temp.getTestId()));
            Json.add(JSONArray.fromObject(arrayList));
        }
        Collections.reverse(Json);
        return JSONArray.fromObject(Json.toArray());
    }

    @CrossOrigin
    @RequestMapping(value = "getTestDetail", method = RequestMethod.POST)
    public JSONArray getTestDetail(@RequestBody JSONObject data){
        System.out.println("getTestDetail");
        int testId = data.getInt("testId");
        String student_groupId = data.getString("student_groupId");
        String studentId = data.getString("studentId");
        List<QuestionEntity> list = questionRepository.findByTestId(testId);
        if(list.size() == 0) {
            System.out.println("test为空");
            return null;
        }
        ArrayList<JSONArray> Json = new ArrayList<JSONArray>();
        for(QuestionEntity temp : list){
            ArrayList<String> arrayList = new ArrayList<String>();
            arrayList.add(String.valueOf(temp.getQuestionId()));
            AnswerEntity answerEntity = answerRepository.findByTestIdAndStudentGroupIdAndStudentIdAndQuestionId(testId, student_groupId, studentId, temp.getQuestionId());
            arrayList.add(String.valueOf(temp.getPoint()));
            arrayList.add(temp.getQuestionContent());
            if(answerEntity != null) {
                arrayList.add(String.valueOf(answerEntity.getAnswer()));
            }
            else{
                arrayList.add("-1");
            }
            List<ChoiceEntity> listChoice = choiceRepository.findByTestIdAndQuestionId(testId, temp.getQuestionId());
            ArrayList<JSONArray> JsonChoice = new ArrayList<JSONArray>();
            for(ChoiceEntity tempChoice:listChoice){
                ArrayList<String> arrayListChoice = new ArrayList<String>();
                arrayListChoice.add(String.valueOf(tempChoice.getChoiceNo()));
                arrayListChoice.add(tempChoice.getChoiceContent());
                JsonChoice.add(JSONArray.fromObject(arrayListChoice));
            }
            arrayList.add(String.valueOf(JsonChoice));
            Json.add(JSONArray.fromObject(arrayList));
        }
        return JSONArray.fromObject(Json.toArray());
    }

    @CrossOrigin
    @RequestMapping(value = "getQuestionDetail", method = RequestMethod.POST)
    public JSONArray getQuestionDetail(@RequestBody JSONObject data){
        System.out.println("choices");
        int testId = data.getInt("testId");
        int questionId = data.getInt("questionId");
        List<ChoiceEntity> list = choiceRepository.findByTestIdAndQuestionId(testId, questionId);
        if(list.size() == 0) {
            System.out.println("choice为空");
            return null;
        }
        ArrayList<JSONArray> Json = new ArrayList<JSONArray>();
        for(ChoiceEntity temp : list){
            ArrayList<String> arrayList = new ArrayList<String>();
            arrayList.add(String.valueOf(temp.getChoiceNo()));
            arrayList.add(temp.getChoiceContent());
            Json.add(JSONArray.fromObject(arrayList));
        }
        return JSONArray.fromObject(Json.toArray());
    }

    @CrossOrigin
    @RequestMapping(value = "submitTest", method = RequestMethod.POST)
    public String submitTest(@RequestBody JSONObject data){
        int testId = data.getInt("testId");
        int grade = 0;
        JSONArray questionId = data.getJSONArray("questionId");
        JSONArray answer = data.getJSONArray("answer");
        JSONArray point = data.getJSONArray("point");
        String student_groupId = data.getString("student_groupId");
        String studentId = data.getString("studentId");
        List<AnswerEntity> result = answerRepository.findByTestIdAndStudentGroupIdAndStudentId(testId,student_groupId,studentId);
        if(result.isEmpty()){
            for(int i = 0; i < questionId.size(); i++){
                AnswerEntity answerEntity = new AnswerEntity();
                answerEntity.setStudentGroupId(student_groupId);
                answerEntity.setStudentId(studentId);
                answerEntity.setTestId(testId);
                int questionIdTemp = questionId.getInt(i);
                answerEntity.setQuestionId(questionIdTemp);
                int answerTemp = answer.getInt(i);
                answerEntity.setAnswer(answerTemp);
                CorrectAnswerEntity correctAnswerEntity = correctAnswerRepository.findByTestIdAndQuestionId(testId, questionIdTemp);
                int correctAnswer = correctAnswerEntity.getCorrectAns();
                if(answerTemp == correctAnswer){
                    grade += point.getInt(i);
                    answerEntity.setIfRight(true);
                }
                else{
                    answerEntity.setIfRight(false);
                }
                answerRepository.save(answerEntity);
            }
            TestGradeEntity testGradeEntity = new TestGradeEntity();
            testGradeEntity.setTestId(testId);
            testGradeEntity.setStudentGroupId(student_groupId);
            testGradeEntity.setStudentId(studentId);
            testGradeEntity.setGrade(grade);
            testGradeRepository.save(testGradeEntity);
            return "success";
        }
        else{
            return "false";
        }

    }

    @CrossOrigin
    @RequestMapping(value = "submitTestEdit", method = RequestMethod.POST)
    public String submitTestEdit(@RequestBody JSONObject data){
        System.out.println("submitTestEdit");
        String testGroupId = data.getString("openGid");
        String startTime = data.getString("startTime");
        String time = data.getString("time");
        String titleContent = data.getString("titleContent");
        if(testGroupId == null){
            return "缺少GroupId";
        }
        if(titleContent == null){
            return "缺少标题";
        }

        System.out.println(titleContent);
        System.out.println(startTime);
        Timestamp startTimeStamp = Timestamp.valueOf(startTime);
        long startLong  = startTimeStamp.getTime();

        String[] timeSplit = time.split(":");

        long timeLong = Integer.parseInt(timeSplit[0]) * 3600 * 1000 + Integer.parseInt(timeSplit[1]) * 60 * 1000 + Integer.parseInt(timeSplit[2]) * 1000;

        long endLong = startLong + timeLong;
        Timestamp endTimeStamp = new Timestamp(new Date(endLong).getTime());

        System.out.println(startTimeStamp);
        System.out.println(endTimeStamp);

        JSONArray questionContent = data.getJSONArray("questionContent");
        JSONArray choiceContent  = data.getJSONArray("choiceContent");
        JSONArray correctAnswer = data.getJSONArray("correctAnswer");
        TestEntity testEntity = new TestEntity();
        testEntity.setTestGroupId(testGroupId);
        testEntity.setTestContent(titleContent);
        testEntity.setStartTime(startTimeStamp);
        testEntity.setEndTime(endTimeStamp);
        testRepository.save(testEntity);
        TestEntity cur = testRepository.findByTestGroupIdAndStartTimeAndEndTimeAndTestContent(testGroupId, startTimeStamp, endTimeStamp, titleContent);
        int testId = cur.getTestId();
        for (int i = 0; i < questionContent.size(); i++){
            QuestionEntity questionEntity = new QuestionEntity();
            questionEntity.setTestId(testId);
            questionEntity.setPoint(100/questionContent.size());
            System.out.println(questionContent.getString(i));
            System.out.println(questionContent.getString(i).length());
            if(questionContent.getString(i).length() == 0 || questionContent.getString(i).equals("null")){
                testRepository.delete(testEntity);
                return "第"+(i+1)+"个问题内容为空";
            }
            questionEntity.setQuestionContent(questionContent.getString(i));
            questionEntity.setQuestionId(i);
            System.out.println(choiceContent.getString(i));
            List<ChoiceEntity> choiceEntityList = new ArrayList<ChoiceEntity>();
            for(int j = 0; j < choiceContent.getJSONArray(i).size(); j++){
                ChoiceEntity choiceEntity = new ChoiceEntity();
                choiceEntity.setChoiceNo(j);
                choiceEntity.setQuestionId(i);
                choiceEntity.setTestId(testId);
                if(choiceContent.getJSONArray(i).getString(j).equals("null")){
                    testRepository.delete(testEntity);
                    return "第"+(i+1)+"个问题存在选项内容为空";
                }
                choiceEntity.setChoiceContent(choiceContent.getJSONArray(i).getString(j));
                choiceEntityList.add(choiceEntity);
            }
            CorrectAnswerEntity correctAnswerEntity = new CorrectAnswerEntity();
            correctAnswerEntity.setTestId(testId);
            correctAnswerEntity.setQuestionId(i);
            if(correctAnswer.getString(i).equals("-1")){
                testRepository.delete(testEntity);
                return "第"+(i+1)+"个问题未确定正确选项";
            }
            correctAnswerEntity.setCorrectAns(Integer.parseInt(correctAnswer.getString(i)));
            for(ChoiceEntity choiceEntityTemp : choiceEntityList){
                choiceRepository.save(choiceEntityTemp);
            }
            questionRepository.save(questionEntity);
            correctAnswerRepository.save(correctAnswerEntity);
        }
        return "测试上传成功";
    }

    @CrossOrigin
    @RequestMapping(value = "getTestResult", method = RequestMethod.POST)
    public JSONArray getTestResult(@RequestBody JSONObject data){
        System.out.println("getTestResult");
        int testId = data.getInt("testId");
        String student_groupId = data.getString("student_groupId");
        String studentId = data.getString("studentId");
        List<QuestionEntity> questionEntityList = questionRepository.findByTestId(testId);
        ArrayList<JSONArray> Json = new ArrayList<JSONArray>();
        for(int i = 0; i < questionEntityList.size(); i++){
            ArrayList<String> arrayList = new ArrayList<String>();
            QuestionEntity temp = questionEntityList.get(i);
            int qId = temp.getQuestionId();
            arrayList.add(String.valueOf(qId));
            arrayList.add(String.valueOf(temp.getPoint()));
            arrayList.add(temp.getQuestionContent());
            AnswerEntity answerEntity = answerRepository.findByTestIdAndStudentGroupIdAndStudentIdAndQuestionId(testId, student_groupId, studentId, qId);
            if(answerEntity != null){
                arrayList.add(String.valueOf(answerEntity.getAnswer()));
                arrayList.add(String.valueOf(answerEntity.getIfRight()));
            }
            else{
                arrayList.add("-1");
                arrayList.add("false");
            }
            CorrectAnswerEntity correctAnswerEntity = correctAnswerRepository.findByTestIdAndQuestionId(testId, qId);
            arrayList.add(String.valueOf(correctAnswerEntity.getCorrectAns()));
            TestGradeEntity testGradeEntity = testGradeRepository.findByTestIdAndStudentGroupIdAndStudentId(testId, student_groupId, studentId);
            if(testGradeEntity != null) {
                arrayList.add(String.valueOf(testGradeEntity.getGrade()));
            }
            else{
                arrayList.add("0");
            }
            List<ChoiceEntity> listChoice = choiceRepository.findByTestIdAndQuestionId(testId,qId);
            ArrayList<JSONArray> JsonChoice = new ArrayList<JSONArray>();
            for(ChoiceEntity tempChoice:listChoice){
                ArrayList<String> arrayListChoice = new ArrayList<String>();
                arrayListChoice.add(String.valueOf(tempChoice.getChoiceNo()));
                arrayListChoice.add(tempChoice.getChoiceContent());
                JsonChoice.add(JSONArray.fromObject(arrayListChoice));
            }
            arrayList.add(String.valueOf(JsonChoice));
            Json.add(JSONArray.fromObject(arrayList));
        }
        return JSONArray.fromObject(Json.toArray());
    }


}
