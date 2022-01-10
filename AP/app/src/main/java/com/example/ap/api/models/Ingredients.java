package com.example.ap.api.models;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.example.ap.dao.DaoSession;
import com.example.ap.dao.IngredientsDao;


@Entity(nameInDb = "ingredients", active = true, generateConstructors = true,generateGettersSetters = true)
public class Ingredients {
    @org.greenrobot.greendao.annotation.Id(autoincrement = false)
    @SerializedName("id")
    public long Id;
    @SerializedName("type")
    public String Type ;
    @SerializedName("name")
    public String Name ;
    @SerializedName("cakeId")
    public int CakeId;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 16573020)
    private transient IngredientsDao myDao;
    @Generated(hash = 1879938783)
    public Ingredients(long Id, String Type, String Name, int CakeId) {
        this.Id = Id;
        this.Type = Type;
        this.Name = Name;
        this.CakeId = CakeId;
    }
    @Generated(hash = 1338416856)
    public Ingredients() {
    }
    public long getId() {
        return this.Id;
    }
    public void setId(long Id) {
        this.Id = Id;
    }
    public String getType() {
        return this.Type;
    }
    public void setType(String Type) {
        this.Type = Type;
    }
    public String getName() {
        return this.Name;
    }
    public void setName(String Name) {
        this.Name = Name;
    }
    public int getCakeId() {
        return this.CakeId;
    }
    public void setCakeId(int CakeId) {
        this.CakeId = CakeId;
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
    @Generated(hash = 720273589)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getIngredientsDao() : null;
    }

}
