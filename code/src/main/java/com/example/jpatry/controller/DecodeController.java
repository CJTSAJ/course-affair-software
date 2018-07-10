package com.example.jpatry.controller;
import com.example.jpatry.Utils.AesCbcUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import net.sf.json.JSONObject;




@RestController
public class DecodeController {

    @RequestMapping(value = "/decodeUser", method = RequestMethod.POST)
    public String decodeUser(@RequestBody JSONObject data) {

        //////////////// 2、对encryptedData加密数据进行AES解密 ////////////////
        String encryptedData = data.getString("encryptedData");
        System.out.println(encryptedData);
        String iv = data.getString("iv");
        System.out.println(iv);
        String session_key = data.getString("session_key");
        System.out.println(session_key);

        try {
            String result = AesCbcUtil.decrypt(encryptedData, session_key, iv, "UTF-8");
            System.out.println(result);
            if (null != result && result.length() > 0) {
                JSONObject userInfoJSON = JSONObject.fromObject(result);
                String openGID = userInfoJSON.getString("openGId");
                return openGID;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;


    }}
