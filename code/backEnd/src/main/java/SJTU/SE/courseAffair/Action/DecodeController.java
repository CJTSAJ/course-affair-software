package SJTU.SE.courseAffair.Action;

import SJTU.SE.courseAffair.service.AesCbcUtil;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/decode")
@EnableAutoConfiguration
public class DecodeController {



    @ResponseBody
    @RequestMapping(value = "/decodeGid", method = RequestMethod.GET)
    public String decodeGid(String encryptedData, String iv, String session_key) throws Exception {
        System.out.println("Get");
        System.out.println(encryptedData);
        System.out.println(iv);
        System.out.println(session_key);
        String Gid = AesCbcUtil.decrypt(encryptedData, session_key, iv, "UTF-8");
        System.out.println(Gid);
        return Gid;
    }
}