package com.example.ap.api.models;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.example.ap.dao.DaoSession;
import com.example.ap.dao.HistoryDao;


@Entity(nameInDb = "history", active = true, generateConstructors = true,generateGettersSetters = true)
public class History {
    @org.greenrobot.greendao.annotation.Id(autoincrement = false)
    @SerializedName("id")
    public long Id;
    @SerializedName("cakeId")
    public int CakeId;
   @SerializedName("date")
    public String Date;
    @SerializedName("done")
    public boolean Done;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1462128466)
    private transient HistoryDao myDao;
    @Generated(hash = 970660862)
    public History(long Id, int CakeId, String Date, boolean Done) {
        this.Id = Id;
        this.CakeId = CakeId;
        this.Date = Date;
        this.Done = Done;
    }
    @Generated(hash = 869423138)
    public History() {
    }
    public long getId() {
        return this.Id;
    }
    public void setId(long Id) {
        this.Id = Id;
    }
    public int getCakeId() {
        return this.CakeId;
    }
    public void setCakeId(int CakeId) {
        this.CakeId = CakeId;
    }
    public String getDate() {
        return this.Date;
    }
    public void setDate(String Date) {
        this.Date = Date;
    }
    public boolean getDone() {
        return this.Done;
    }
    public void setDone(boolean Done) {
        this.Done = Done;
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 851899508)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getHistoryDao() : null;
    }

}
