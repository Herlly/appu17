package GreenDao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.greendao.converter.PropertyConverter;

import java.util.List;

public class convert implements PropertyConverter<List<String>, String> {

    @Override
    public List<String> convertToEntityProperty(String databaseValue) {
        if(databaseValue==null)return null;
        TypeToken<List<String>> typeToken=new TypeToken<List<String>>(){};
        return new Gson().fromJson(databaseValue,typeToken.getType());
    }

    @Override
    public String convertToDatabaseValue(List<String> entityProperty) {
        if(entityProperty==null||entityProperty.size()==0)return null;
        else {
            String s=new Gson().toJson(entityProperty);
            return s;
        }
    }
}