package SJTU.SE.courseAffair.Entity;

import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "file", schema = "course_affair_db")
public class FileEntity {
	private String objectId;
	private String filename;
	private String opengid;
	private String openid;
	private Timestamp uploadDate;
	
	@Id
    @Column(name = "objectid", nullable = false, length = 24)
    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }
    
    @Basic
    @Column(name = "filename", nullable = true, length = 128)
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
    
    @Basic
    @Column(name = "opengid", nullable = true, length = 29)
    public String getOpengid() {
        return opengid;
    }

    public void setOpengid(String opengid) {
        this.opengid = opengid;
    }
    
    @Basic
    @Column(name = "openid", nullable = true, length = 28)
    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
    
    @Basic
    @Column(name = "uploadDate", nullable = true)
    public Timestamp getUploadDate() {
    	return uploadDate;
    }
    
    public void setUploadDate(Timestamp uploadDate) {
    	this.uploadDate = uploadDate;
    }
}
