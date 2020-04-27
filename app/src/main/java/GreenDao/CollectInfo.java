package GreenDao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.converter.PropertyConverter;

import java.util.List;

@Entity
public class CollectInfo {
    //历史页面
    @Id
    private String name;
    private String path;
    private String time;
    private String Info;
    private String index;
    private String comic_Id;
    @Convert(columnType = String.class,converter = convert.class)
    private List<String> tags;
    @Generated(hash = 1744628106)
    public CollectInfo(String name, String path, String time, String Info,
            String index, String comic_Id, List<String> tags) {
        this.name = name;
        this.path = path;
        this.time = time;
        this.Info = Info;
        this.index = index;
        this.comic_Id = comic_Id;
        this.tags = tags;
    }
    @Generated(hash = 781720191)
    public CollectInfo() {
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
    public List<String> getTags() {
        return this.tags;
    }
    public void setTags(List<String> tags) {
        this.tags = tags;
    }
    public String getInfo() {
        return this.Info;
    }
    public void setInfo(String Info) {
        this.Info = Info;
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
