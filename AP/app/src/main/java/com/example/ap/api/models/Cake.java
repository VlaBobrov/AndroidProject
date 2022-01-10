package com.example.ap.api.models;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.example.ap.dao.DaoSession;
import com.example.ap.dao.CakeDao;

@Entity(nameInDb = "cake", active = true, generateConstructors = true,generateGettersSetters = true)
public class Cake {
    @SerializedName("id")
    @org.greenrobot.greendao.annotation.Id(autoincrement = false)
    public long Id;
    @SerializedName("name")
    public String Name;
    @SerializedName("weight")
    public int Weight;
    @SerializedName("price")
    public int Price;
    @SerializedName("description")
    public String Description;
    @SerializedName("pathToImage")
    public String PathToImage;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 966741207)
    private transient CakeDao myDao;
    @Generated(hash = 1425843028)
    public Cake(long Id, String Name, int Weight, int Price, String Description, String PathToImage) {
        this.Id = Id;
        this.Name = Name;
        this.Weight = Weight;
        this.Price = Price;
        this.Description = Description;
        this.PathToImage = PathToImage;
    }
    @Generated(hash = 370572369)
    public Cake() {
    }
    public long getId() {
        return this.Id;
    }
    public void setId(long Id) {
        this.Id = Id;
    }
    public String getName() {
        return this.Name;
    }
    public void setName(String Name) {
        this.Name = Name;
    }
    public int getWeight() {
        return this.Weight;
    }
    public void setWeight(int Weight) {
        this.Weight = Weight;
    }
    public int getPrice() {
        return this.Price;
    }
    public void setPrice(int Price) {
        this.Price = Price;
    }
    public String getDescription() {
        return this.Description;
    }
    public void setDescription(String Description) {
        this.Description = Description;
    }
    public String getPathToImage() {
        return this.PathToImage;
    }
    public void setPathToImage(String PathToImage) {
        this.PathToImage = PathToImage;
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
    @Generated(hash = 1634282231)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getCakeDao() : null;
    }

 
}
