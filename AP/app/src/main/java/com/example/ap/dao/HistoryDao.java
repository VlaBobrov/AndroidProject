package com.example.ap.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.example.ap.api.models.History;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "history".
*/
public class HistoryDao extends AbstractDao<History, Long> {

    public static final String TABLENAME = "history";

    /**
     * Properties of entity History.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, long.class, "Id", true, "_id");
        public final static Property CakeId = new Property(1, int.class, "CakeId", false, "CAKE_ID");
        public final static Property Date = new Property(2, String.class, "Date", false, "DATE");
        public final static Property Done = new Property(3, boolean.class, "Done", false, "DONE");
    }

    private DaoSession daoSession;


    public HistoryDao(DaoConfig config) {
        super(config);
    }
    
    public HistoryDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"history\" (" + //
                "\"_id\" INTEGER PRIMARY KEY NOT NULL ," + // 0: Id
                "\"CAKE_ID\" INTEGER NOT NULL ," + // 1: CakeId
                "\"DATE\" TEXT," + // 2: Date
                "\"DONE\" INTEGER NOT NULL );"); // 3: Done
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"history\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, History entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
        stmt.bindLong(2, entity.getCakeId());
 
        String Date = entity.getDate();
        if (Date != null) {
            stmt.bindString(3, Date);
        }
        stmt.bindLong(4, entity.getDone() ? 1L: 0L);
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, History entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
        stmt.bindLong(2, entity.getCakeId());
 
        String Date = entity.getDate();
        if (Date != null) {
            stmt.bindString(3, Date);
        }
        stmt.bindLong(4, entity.getDone() ? 1L: 0L);
    }

    @Override
    protected final void attachEntity(History entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 0);
    }    

    @Override
    public History readEntity(Cursor cursor, int offset) {
        History entity = new History( //
            cursor.getLong(offset + 0), // Id
            cursor.getInt(offset + 1), // CakeId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // Date
            cursor.getShort(offset + 3) != 0 // Done
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, History entity, int offset) {
        entity.setId(cursor.getLong(offset + 0));
        entity.setCakeId(cursor.getInt(offset + 1));
        entity.setDate(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setDone(cursor.getShort(offset + 3) != 0);
     }
    
    @Override
    protected final Long updateKeyAfterInsert(History entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(History entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(History entity) {
        throw new UnsupportedOperationException("Unsupported for entities with a non-null key");
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}