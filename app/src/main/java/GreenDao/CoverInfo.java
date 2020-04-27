package GreenDao;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class CoverInfo {
    //收藏页面
    @Id
    private String name;
    private String path;
    private String time;
    private String index;
    private String comic_Id;
    @Generated(hash = 892293266)
    public CoverInfo(String name, String path, String time, String index,
            String comic_Id) {
        this.name = name;
        this.path = path;
        this.time = time;
        this.index = index;
        this.comic_Id = comic_Id;
    }
    @Generated(hash = 201154234)
    public CoverInfo() {
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPath() {
        return this.path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getIndex() {
        return this.index;
    }
    public void setIndex(String index) {
        this.index = index;
    }
    public String getComic_Id() {
        return this.comic_Id;
    }
    public void setComic_Id(String comic_Id) {
        this.comic_Id = comic_Id;
    }



    
    

}
