package SJTU.SE.courseAffair.Action;

import SJTU.SE.courseAffair.Dao.InfoRepository;
import SJTU.SE.courseAffair.Entity.InfoEntity;
import net.sf.json.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ImportController {
    @Autowired
    private InfoRepository infoRepository;

    @CrossOrigin
    @RequestMapping(value="/import",method=RequestMethod.POST)
    public void save(MultipartFile file,String opengid){
        String originalFilename = file.getOriginalFilename();
        Workbook workbook = null;
        try {
            if (originalFilename.endsWith(".xls")) {
                workbook = new HSSFWorkbook(file.getInputStream());
            } else if (originalFilename.endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(file.getInputStream());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        int numOfSheet = workbook.getNumberOfSheets();
        if (numOfSheet!=1)
            return;
        Sheet sheet = workbook.getSheetAt(0);
        int lastRowNum = sheet.getLastRowNum();
        //从第一行开始第一行一般是标题
        for (int j = 1; j <= lastRowNum; j++) {
            InfoEntity infoEntity = new InfoEntity();
            infoEntity.setInfoGroupId(opengid);
            Row row = sheet.getRow(j);
            if (row.getCell(0) != null) {
                String sno = row.getCell(0).getStringCellValue();
                infoEntity.setInfoStuId(sno);
            }
            if (row.getCell(1) != null) {
                String sname = row.getCell(1).getStringCellValue();
                infoEntity.setInfoStuName(sname);
            }
            infoRepository.save(infoEntity);
        }
    }
}
