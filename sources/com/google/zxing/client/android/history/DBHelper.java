package com.google.zxing.client.android.history;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/* loaded from: classes2.dex */
final class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "barcode_scanner_history.db";
    private static final int DB_VERSION = 5;
    static final String DETAILS_COL = "details";
    static final String DISPLAY_COL = "display";
    static final String FORMAT_COL = "format";
    static final String ID_COL = "id";
    static final String TABLE_NAME = "history";
    static final String TEXT_COL = "text";
    static final String TIMESTAMP_COL = "timestamp";

    public DBHelper(Context context) {
        super(context, DB_NAME, (SQLiteDatabase.CursorFactory) null, 5);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) throws SQLException {
        sQLiteDatabase.execSQL("CREATE TABLE history (id INTEGER PRIMARY KEY, text TEXT, format TEXT, display TEXT, timestamp INTEGER, details TEXT);");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i9, int i10) throws SQLException {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS history");
        onCreate(sQLiteDatabase);
    }
}
