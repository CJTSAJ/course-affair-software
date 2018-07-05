package SJTU.SE.courseAffair.Action;


import SJTU.SE.courseAffair.Dao.UserRepository;
import SJTU.SE.courseAffair.Entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/hibernate")
@EnableAutoConfiguration
public class HibernateController {


    @Autowired
    private UserRepository userRepository;


    @RequestMapping("getAll")
    @ResponseBody
    public List<UserEntity> getAll(){
        return userRepository.getAllBy();
    }
}
