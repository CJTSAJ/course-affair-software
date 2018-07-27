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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.math.BigDecimal;

@RestController
public class ImportController {
    @Autowired
    private InfoRepository infoRepository;

    @CrossOrigin
    @RequestMapping(value="/import",method=RequestMethod.POST)
    public void save(@RequestParam("file") MultipartFile file, @RequestParam("groupid") String opengid){
        System.out.println("opengid:"+opengid);
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
        System.out.println("总共有"+lastRowNum+"行数据");
        //从第一行开始第一行一般是标题
        for (int j = 1; j <= lastRowNum; j++) {
            InfoEntity infoEntity = new InfoEntity();
            infoEntity.setInfoGroupId(opengid);
            Row row = sheet.getRow(j);
            if (row.getCell(0) != null) {
                String sno = String.valueOf(new BigDecimal(row.getCell(0).getNumericCellValue()));
                infoEntity.setInfoStuId(sno);
                System.out.println("学号:"+sno);
            }
            if (row.getCell(1) != null) {
                String sname = row.getCell(1).getStringCellValue();
                infoEntity.setInfoStuName(sname);
            }
            infoRepository.save(infoEntity);
        }
    }
}
