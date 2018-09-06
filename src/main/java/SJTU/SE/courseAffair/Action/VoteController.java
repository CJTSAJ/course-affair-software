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
public class VoteController {
    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private VoteChoiceRepository voteChoiceRepository;

    @Autowired
    private VoteChooseRepository voteChooseRepository;

    @CrossOrigin
    @RequestMapping(value = "getVote", method = RequestMethod.POST)
    public JSONArray getAll(@RequestBody String opengid){
        System.out.println("getVote");
        List<VoteEntity> list = voteRepository.findByVoteGroupId(opengid);
        if(list.size() == 0) {
            System.out.println("vote为空");
            return null;
        }
        ArrayList<JSONArray> Json = new ArrayList<JSONArray>();
        for (VoteEntity temp : list) {//内部不锁定，效率最高，但在多线程要考虑并发操作的问题。
            ArrayList<String> arrayList = new ArrayList<String>();
            arrayList.add(String.valueOf(temp.getStartTime()));
            arrayList.add(String.valueOf(temp.getEndTime()));
            arrayList.add(String.valueOf(temp.getVoteId()));
            arrayList.add(String.valueOf(temp.getVoteTitle()));
            Json.add(JSONArray.fromObject(arrayList));
        }
        return JSONArray.fromObject(Json.toArray());
    }

    @CrossOrigin
    @RequestMapping(value = "getVoteDetail", method = RequestMethod.POST)
    public JSONArray getVoteDetail(@RequestBody JSONObject data){
        System.out.println("getVoteDetail");
        int voteId = data.getInt("voteId");
        String student_groupId = data.getString("student_groupId");
        String studentId = data.getString("studentId");
        List<VoteEntity> listVote = voteRepository.findByVoteId(voteId);
        String content = listVote.get(0).getVoteContent();
        ArrayList<JSONArray> Json = new ArrayList<JSONArray>();
        ArrayList<String> arrayListForContent = new ArrayList<String>();
        arrayListForContent.add(content);
        Json.add(JSONArray.fromObject(arrayListForContent));
        List<VoteChoiceEntity> listChoice = voteChoiceRepository.findByVoteId(voteId);
        for (VoteChoiceEntity temp : listChoice) {
            ArrayList<String> arrayList = new ArrayList<String>();
            arrayList.add(String.valueOf(temp.getVoteChoiceNo()));
            arrayList.add(temp.getVoteChoiceContent());
            Json.add(JSONArray.fromObject(arrayList));
        }
        VoteChooseEntity voteChooseEntity = voteChooseRepository.findByVoteIdAndStudentGroupIdAndStudentId(voteId, student_groupId, studentId);
        if(voteChooseEntity != null) {
            ArrayList<String> arrayListForChoose = new ArrayList<String>();
            arrayListForChoose.add(String.valueOf(voteChooseEntity.getVoteChoose()));
            Json.add(JSONArray.fromObject(arrayListForChoose));
        }
        else{
            ArrayList<String> arrayListForChoose = new ArrayList<String>();
            arrayListForChoose.add("-1");
            Json.add(JSONArray.fromObject(arrayListForChoose));
        }
        return JSONArray.fromObject(Json.toArray());
    }

    @CrossOrigin
    @RequestMapping(value = "submitVote", method = RequestMethod.POST)
    public String submitVote(@RequestBody JSONObject data){
        System.out.println("submitVote");
        int voteId = data.getInt("voteId");
        String student_groupId = data.getString("student_groupId");
        String studentId = data.getString("studentId");
        int choose = data.getInt("answer");
        VoteChooseEntity voteChooseEntity = voteChooseRepository.findByVoteIdAndStudentGroupIdAndStudentId(voteId, student_groupId, studentId);
        if(voteChooseEntity == null){
            VoteChooseEntity voteChoose = new VoteChooseEntity();
            voteChoose.setStudentGroupId(student_groupId);
            voteChoose.setStudentId(studentId);
            voteChoose.setVoteId(voteId);
            voteChoose.setVoteChoose(choose);
            voteChooseRepository.save(voteChoose);
            return "success";
        }
        else{
            return "false";
        }
    }

    @CrossOrigin
    @RequestMapping(value = "getVoteResult", method = RequestMethod.POST)
    public JSONArray getVoteResult(@RequestBody JSONObject data){
        System.out.println("getVoteResult");
        int voteId = data.getInt("voteId");
        String student_groupId = data.getString("student_groupId");
        String studentId = data.getString("studentId");
        List<VoteEntity> listVote = voteRepository.findByVoteId(voteId);
        String content = listVote.get(0).getVoteContent();
        ArrayList<JSONArray> Json = new ArrayList<JSONArray>();
        ArrayList<String> arrayListForContent = new ArrayList<String>();
        arrayListForContent.add(content);
        Json.add(JSONArray.fromObject(arrayListForContent));
        List<VoteChoiceEntity> listChoice = voteChoiceRepository.findByVoteId(voteId);
        for (VoteChoiceEntity temp : listChoice) {
            ArrayList<String> arrayList = new ArrayList<String>();
            arrayList.add(String.valueOf(temp.getVoteChoiceNo()));
            arrayList.add(temp.getVoteChoiceContent());
            int chooseNum = voteChooseRepository.countByVoteIdAndVoteChoose(voteId, temp.getVoteChoiceNo());
            arrayList.add(String.valueOf(chooseNum));
            Json.add(JSONArray.fromObject(arrayList));
        }
        VoteChooseEntity voteChooseEntity = voteChooseRepository.findByVoteIdAndStudentGroupIdAndStudentId(voteId, student_groupId, studentId);
        if(voteChooseEntity != null) {
            ArrayList<String> arrayListForChoose = new ArrayList<String>();
            arrayListForChoose.add(String.valueOf(voteChooseEntity.getVoteChoose()));
            Json.add(JSONArray.fromObject(arrayListForChoose));
        }
        else{
            ArrayList<String> arrayListForChoose = new ArrayList<String>();
            arrayListForChoose.add("-1");
            Json.add(JSONArray.fromObject(arrayListForChoose));
        }
        return JSONArray.fromObject(Json.toArray());
    }
    @CrossOrigin
    @RequestMapping(value = "submitVoteEdit", method = RequestMethod.POST)
    public String submitVoteEdit(@RequestBody JSONObject data){
        System.out.println("submitVoteEdit");
        String groupId = data.getString("openGid");
        String startTime = data.getString("startTime");
        String time = data.getString("time");
        String titleContent = data.getString("titleContent");
        String voteContent = data.getString("voteContent");
        JSONArray choiceContent  = data.getJSONArray("choiceContent");
        if(groupId == null){
            return "缺少GroupId";
        }
        if(titleContent == null){
            return "缺少标题";
        }
        Timestamp startTimeStamp = Timestamp.valueOf(startTime);
        long startLong  = startTimeStamp.getTime();
        String[] timeSplit = time.split(":");
        long timeLong = Integer.parseInt(timeSplit[0]) * 3600 * 1000 + Integer.parseInt(timeSplit[1]) * 60 * 1000 + Integer.parseInt(timeSplit[2]) * 1000;
        long endLong = startLong + timeLong;
        Timestamp endTimeStamp = new Timestamp(new Date(endLong).getTime());

        VoteEntity voteEntity = new VoteEntity();
        voteEntity.setEndTime(endTimeStamp);
        voteEntity.setStartTime(startTimeStamp);
        voteEntity.setVoteGroupId(groupId);
        voteEntity.setVoteContent(voteContent);
        voteEntity.setVoteTitle(titleContent);
        voteRepository.save(voteEntity);
        VoteEntity cur = voteRepository.findByVoteGroupIdAndEndTimeAndStartTimeAndVoteContentAndVoteTitle(groupId, endTimeStamp, startTimeStamp, voteContent, titleContent);
        int voteId = cur.getVoteId();
        for (int i = 0; i < choiceContent.getJSONArray(0).size(); i++) {
            if(choiceContent.getJSONArray(0).getString(i).equals("null")){
                voteRepository.delete(voteEntity);
                return "第"+ (i + 1) + "个选项为空";
            }
            VoteChoiceEntity voteChoiceEntity = new VoteChoiceEntity();
            voteChoiceEntity.setVoteId(voteId);
            voteChoiceEntity.setVoteChoiceNo(i);
            voteChoiceEntity.setVoteChoiceContent(choiceContent.getJSONArray(0).getString(i));
            voteChoiceRepository.save(voteChoiceEntity);
        }
        return "投票上传成功";
    }

}
