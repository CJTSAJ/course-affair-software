package SJTU.SE.courseAffair.Action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSFindIterable;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;

import SJTU.SE.courseAffair.Dao.FileRepository;
import SJTU.SE.courseAffair.Dao.StudentRepository;
import SJTU.SE.courseAffair.Dao.TaRepository;
import SJTU.SE.courseAffair.Dao.TeacherRepository;
import SJTU.SE.courseAffair.Entity.FileEntity;
import net.sf.json.JSONObject;

@RestController
public class uploadController {
	
	@Autowired
	private GridFsOperations operations;
	
	@Autowired
	private FileRepository fileRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private TaRepository taRepository;
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
    private MongoTemplate mongoTemplate;
	
	@Autowired  
	private MongoDbFactory mongoDbFactory;
	
	File tempPathFile;
	
	@CrossOrigin
    @RequestMapping(value = "upload", method = RequestMethod.POST)
	public void uploadFile(@RequestParam("file") MultipartFile file, 
			@RequestParam("opengid") String opengid, @RequestParam("openid") String openid) 
            		throws Exception{
		System.out.println("upload");
		System.out.println(opengid);
		System.out.println(openid);
		
		
		String filename = file.getOriginalFilename();
		ObjectId objectId = operations.store(file.getInputStream(), filename); //存入mongodb
		
		FileEntity fileEntity = new FileEntity();
		fileEntity.setFilename(filename);
		fileEntity.setObjectId(objectId.toString());
		fileEntity.setOpengid(opengid);
		fileEntity.setOpenid(openid);
		
		Timestamp time= new Timestamp(System.currentTimeMillis());
		fileEntity.setUploadDate(time);
		
		fileRepository.save(fileEntity);
		System.out.println("bingo1");
	}
	
	@CrossOrigin
    @RequestMapping(value = "getAllFile", method = RequestMethod.POST)
	public List<FileEntity> getAllFile(@RequestBody JSONObject data) {
		System.out.println("getAllFile");
		String opengid = data.getString("opengid");
		List<FileEntity> allFile = fileRepository.findByOpengid(opengid);
		/*GridFSFile gridFSFile = operations.findOne(new Query().addCriteria(Criteria.where("_id").is("5b583cf20c7e0b44d0c24217")));
		System.out.println(gridFSFile.getFilename());*/
		return allFile;
	}
	
	@CrossOrigin
    @RequestMapping(value = "downloadFile", method = RequestMethod.GET)
	public void downloadFile(String objectid, HttpServletResponse response) throws ServletException, IOException{
		/*MongoClient mongoClient = new MongoClient("127.0.0.1", 27017); 
        DB mongoDatabase = mongoClient.getDB("uploadFile");
        DBCollection collection = mongoDatabase.getCollection("fs");
		DB db = mongoTemplate.getDb();
        GridFS gridFS= new GridFS(db,"fs");*/
		DB db = mongoDbFactory.getLegacyDb();
		/*MongoClient mongoClient = new MongoClient("127.0.0.1", 27017); 
        DB mongoDatabase = mongoClient.getDB("uploadFile");*/
		GridFS gridFS= new GridFS(db,"fs");
		ObjectId objId = new ObjectId(objectid);
        GridFSDBFile gridFSDBFile =(GridFSDBFile)gridFS.findOne(objId);
		response.setContentType("application/octet-stream");
		// 获取原文件名
        String name = (String) gridFSDBFile.get("filename");
        String fileName = new String(name.getBytes("utf-8"), "ISO8859-1");
		response.addHeader("Content-Disposition", "attachment; filename=\""
                + fileName + "\"");
		//FileOutputStream fos;
		System.out.println(objectid);
		//GridFSFile gridFSFile = operations.findOne(new Query().addCriteria(Criteria.where("_id").is(objectid)));
		OutputStream stream = response.getOutputStream();
		//GridFSDBFile gridFSDBFile = operations.findOne(new Query().addCriteria(Criteria.where("_id").is(objectid)));
		
		//stream.write();
		gridFSDBFile.writeTo(stream);
		stream.flush();
		stream.close();
	}
}
