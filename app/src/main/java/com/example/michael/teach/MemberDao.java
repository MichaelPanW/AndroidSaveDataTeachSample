package com.example.michael.teach;

import android.content.Context;

public class MemberDao extends BaseDao{
    private static MemberDao sInst;

    public static class Schema {
        public static final String TableName = "member";
        /**
         * Define table schema
         */
        public static class Columns {
            public static final String Id = "_id";
            public static final String Name = "name";
        };
    }

    public MemberDao(Context context) {
        super(context);
    }

    public synchronized static MemberDao getInstance(Context context) {
        if (sInst == null) {
            sInst = new MemberDao(context);
        }
        return sInst;
    }

    public static String getCraeteTableSQL() {
        return "CREATE TABLE IF NOT EXISTS `"
                + Schema.TableName + "`(" + "`"
                + Schema.Columns.Id + "` TEXT KEY UNIQUE," + "`"
                + Schema.Columns.Name + "` TEXT)";
    }
}

class BaseDao {
    protected SQLiteDatabaseHelper mSQLite;

    public BaseDao(Context context) {
        mSQLite = new SQLiteDatabaseHelper(context);
    }
}