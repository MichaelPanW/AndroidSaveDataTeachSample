package com.example.michael.teach;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteDatabaseHelper extends SQLiteOpenHelper {
    private static final String mDatabaseName = "my_app";
    private static final int mVersion = 1;
    private Context context ;

    public SQLiteDatabaseHelper(Context context) {
        super(context, mDatabaseName, null, mVersion);
        this.context = context;
    }

    public SQLiteDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory, mVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MemberDao.getCraeteTableSQL());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MemberDao.Schema.TableName) ;
        db.execSQL(MemberDao.getCraeteTableSQL());
    }
    public long insert(String Name)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(MemberDao.Schema.Columns.Name, Name);
        long row=db.insert(MemberDao.Schema.TableName, null, cv);
        return row;
    }
    public Cursor select()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(MemberDao.Schema.TableName, null,
                null, null, null, null,  " _id desc");
        return cursor;
    }
    public void delete(int id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String where=MemberDao.Schema.Columns.Id+"=?";
        String[] whereValue={Integer.toString(id)};
        db.delete(MemberDao.Schema.TableName, where, whereValue);
    }

    public void update(int id,String Name)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String where=MemberDao.Schema.Columns.Id+"=?";
        String[] whereValue={Integer.toString(id)};
        ContentValues cv=new ContentValues();
        cv.put(MemberDao.Schema.Columns.Name, Name);
        db.update(MemberDao.Schema.TableName, cv, where, whereValue);
    }



}