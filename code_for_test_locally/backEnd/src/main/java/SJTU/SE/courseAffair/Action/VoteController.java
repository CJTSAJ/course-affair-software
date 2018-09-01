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
        Collections.reverse(Json);
        return JSONArray.fromObject(Json.toArray());
    }

    @CrossOrigin
    @RequestMapping(value = "getVoteDetail", method = RequestMethod.POST)
    public JSONArray getTestDetail(@RequestBody JSONObject data){
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
        return JSONArray.fromObject(Json.toArray());
    }
}
