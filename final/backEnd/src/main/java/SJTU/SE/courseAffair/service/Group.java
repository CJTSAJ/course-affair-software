package SJTU.SE.courseAffair.service;

import SJTU.SE.courseAffair.Entity.FormEntity;
import SJTU.SE.courseAffair.Entity.HomeworkEntity;

import java.util.ArrayList;
import java.util.List;

public class Group {
    public static String openGId;
    public static String accessToken;
    public static List<FormEntity> send = new ArrayList<FormEntity>();
    public static HomeworkEntity homework = new HomeworkEntity();
    /*Robust version*/
    public static List<HomeworkEntity> homeworkList = new ArrayList<HomeworkEntity>();
    public static List<List<FormEntity>> sends = new ArrayList<List<FormEntity>>();
    /*Robust version end*/
}
