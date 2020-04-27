package GreenDao;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

public class CoverManager extends Application {
    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    public static CoverManager instances;
    private CoverInfoDao mCoverInfoDao;
    private CollectInfoDao mCollectInfoDao;
    @Override
    public void onCreate() {
        super.onCreate();
        instances = this;
        setDatabase();
    }
    public static CoverManager getInstances(){
        return instances;
    }
    private void setDatabase(){
        mHelper=new DaoMaster.DevOpenHelper(this,"cover",null);
        db=mHelper.getWritableDatabase();
        mDaoMaster=new DaoMaster(db);
        mDaoSession=mDaoMaster.newSession();
        mCoverInfoDao=mDaoSession.getCoverInfoDao();
        mCollectInfoDao=mDaoSession.getCollectInfoDao();
    }
    public CoverInfo search_CoverInfo(String name){
        return mCoverInfoDao.queryBuilder().where(CoverInfoDao.Properties.Name.eq(name)).build().unique();
    }
    public CollectInfo search_CollectInfo(String name){
        return mCollectInfoDao.queryBuilder().where(CollectInfoDao.Properties.Name.eq(name)).build().unique();
    }

    public List<CoverInfo> searchCoverInfo(){
        return mCoverInfoDao.queryBuilder().list();
    }
    public List<CollectInfo> searchCollectInfo(){return mCollectInfoDao.queryBuilder().list();}

    public void delete_CoverInfo(String name){
        mCoverInfoDao.deleteByKey(name);
    }
    public void delete_CollectInfo(String name){mCollectInfoDao.deleteByKey(name);}

    public void add(CoverInfo coverInfo){
        mCoverInfoDao.insertOrReplace(coverInfo);
    }
    public void add(CollectInfo collectInfo){
        mCollectInfoDao.insert(collectInfo);
    }

    public CoverInfo getCoverInfo(String name){
        return mCoverInfoDao.queryBuilder().where(CoverInfoDao.Properties.Name.eq(name)).build().unique();
    }
    public CollectInfo getCollectInfo(String name){
        return mCollectInfoDao.queryBuilder().where(CollectInfoDao.Properties.Name.eq(name)).build().unique();
    }

    public void update(CoverInfo coverInfo){
        mCoverInfoDao.update(coverInfo);
    }
    public void update(CollectInfo collectInfo){mCollectInfoDao.update(collectInfo);}
}
