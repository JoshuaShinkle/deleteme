package net.sqlcipher.database;

import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;

/* loaded from: classes2.dex */
public class SQLiteDirectCursorDriver implements SQLiteCursorDriver {
    private Cursor mCursor;
    private SQLiteDatabase mDatabase;
    private String mEditTable;
    private SQLiteQuery mQuery;
    private String mSql;

    public SQLiteDirectCursorDriver(SQLiteDatabase sQLiteDatabase, String str, String str2) {
        this.mDatabase = sQLiteDatabase;
        this.mEditTable = str2;
        this.mSql = str;
    }

    @Override // net.sqlcipher.database.SQLiteCursorDriver
    public void cursorClosed() {
        this.mCursor = null;
    }

    @Override // net.sqlcipher.database.SQLiteCursorDriver
    public void cursorDeactivated() {
    }

    @Override // net.sqlcipher.database.SQLiteCursorDriver
    public void cursorRequeried(android.database.Cursor cursor) {
    }

    public Cursor query(SQLiteDatabase.CursorFactory cursorFactory, Object[] objArr) throws Throwable {
        SQLiteQuery sQLiteQuery = new SQLiteQuery(this.mDatabase, this.mSql, 0, objArr);
        try {
            sQLiteQuery.bindArguments(objArr);
            if (cursorFactory == null) {
                this.mCursor = new SQLiteCursor(this.mDatabase, this, this.mEditTable, sQLiteQuery);
            } else {
                this.mCursor = cursorFactory.newCursor(this.mDatabase, this, this.mEditTable, sQLiteQuery);
            }
            this.mQuery = sQLiteQuery;
            try {
                return this.mCursor;
            } catch (Throwable th) {
                th = th;
                sQLiteQuery = null;
                if (sQLiteQuery != null) {
                    sQLiteQuery.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    @Override // net.sqlcipher.database.SQLiteCursorDriver
    public void setBindArguments(String[] strArr) {
        int length = strArr.length;
        int i9 = 0;
        while (i9 < length) {
            int i10 = i9 + 1;
            this.mQuery.bindString(i10, strArr[i9]);
            i9 = i10;
        }
    }

    public String toString() {
        return "SQLiteDirectCursorDriver: " + this.mSql;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x003d  */
    @Override // net.sqlcipher.database.SQLiteCursorDriver
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Cursor query(SQLiteDatabase.CursorFactory cursorFactory, String[] strArr) throws Throwable {
        int length;
        int i9 = 0;
        SQLiteQuery sQLiteQuery = new SQLiteQuery(this.mDatabase, this.mSql, 0, strArr);
        if (strArr == null) {
            length = 0;
        } else {
            try {
                length = strArr.length;
            } catch (Throwable th) {
                th = th;
                if (sQLiteQuery != null) {
                }
                throw th;
            }
        }
        while (i9 < length) {
            int i10 = i9 + 1;
            sQLiteQuery.bindString(i10, strArr[i9]);
            i9 = i10;
        }
        if (cursorFactory == null) {
            this.mCursor = new SQLiteCursor(this.mDatabase, this, this.mEditTable, sQLiteQuery);
        } else {
            this.mCursor = cursorFactory.newCursor(this.mDatabase, this, this.mEditTable, sQLiteQuery);
        }
        this.mQuery = sQLiteQuery;
        try {
            return this.mCursor;
        } catch (Throwable th2) {
            th = th2;
            sQLiteQuery = null;
            if (sQLiteQuery != null) {
                sQLiteQuery.close();
            }
            throw th;
        }
    }
}
