package com.google.zxing.client.android.history;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.Log;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.firebase.messaging.Constants;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.google.zxing.client.android.Intents;
import com.google.zxing.client.android.PreferencesActivity;
import com.google.zxing.client.android.result.ResultHandler;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* loaded from: classes2.dex */
public final class HistoryManager {
    private static final String[] COLUMNS = {MimeTypes.BASE_TYPE_TEXT, Constants.ScionAnalytics.MessageType.DISPLAY_NOTIFICATION, "format", "timestamp", "details"};
    private static final String[] COUNT_COLUMN = {"COUNT(1)"};
    private static final String[] ID_COL_PROJECTION = {TtmlNode.ATTR_ID};
    private static final String[] ID_DETAIL_COL_PROJECTION = {TtmlNode.ATTR_ID, "details"};
    private static final int MAX_ITEMS = 2000;
    private static final String TAG = "HistoryManager";
    private final Activity activity;
    private final boolean enableHistory;

    public HistoryManager(Activity activity) {
        this.activity = activity;
        this.enableHistory = PreferenceManager.getDefaultSharedPreferences(activity).getBoolean(PreferencesActivity.KEY_ENABLE_HISTORY, true);
    }

    private static void close(Cursor cursor, SQLiteDatabase sQLiteDatabase) {
        if (cursor != null) {
            cursor.close();
        }
        if (sQLiteDatabase != null) {
            sQLiteDatabase.close();
        }
    }

    private void deletePrevious(String str) throws Throwable {
        SQLiteDatabase writableDatabase;
        try {
            writableDatabase = new DBHelper(this.activity).getWritableDatabase();
            try {
                writableDatabase.delete("history", "text=?", new String[]{str});
                close(null, writableDatabase);
            } catch (Throwable th) {
                th = th;
                close(null, writableDatabase);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            writableDatabase = null;
        }
    }

    private static String massageHistoryField(String str) {
        return str == null ? "" : str.replace("\"", "\"\"");
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x00b3 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static Uri saveHistory(String str) throws Throwable {
        OutputStreamWriter outputStreamWriter;
        File file = new File(new File(Environment.getExternalStorageDirectory(), "BarcodeScanner"), "History");
        OutputStreamWriter outputStreamWriter2 = null;
        if (!file.exists() && !file.mkdirs()) {
            Log.w(TAG, "Couldn't make dir " + file);
            return null;
        }
        File file2 = new File(file, "history-" + System.currentTimeMillis() + ".csv");
        try {
            outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file2), Charset.forName("UTF-8"));
            try {
                try {
                    outputStreamWriter.write(str);
                    Uri uri = Uri.parse("file://" + file2.getAbsolutePath());
                    try {
                        outputStreamWriter.close();
                    } catch (IOException unused) {
                    }
                    return uri;
                } catch (IOException e9) {
                    e = e9;
                    Log.w(TAG, "Couldn't access file " + file2 + " due to " + e);
                    if (outputStreamWriter != null) {
                        try {
                            outputStreamWriter.close();
                        } catch (IOException unused2) {
                        }
                    }
                    return null;
                }
            } catch (Throwable th) {
                th = th;
                outputStreamWriter2 = outputStreamWriter;
                if (outputStreamWriter2 != null) {
                    try {
                        outputStreamWriter2.close();
                    } catch (IOException unused3) {
                    }
                }
                throw th;
            }
        } catch (IOException e10) {
            e = e10;
            outputStreamWriter = null;
        } catch (Throwable th2) {
            th = th2;
            if (outputStreamWriter2 != null) {
            }
            throw th;
        }
    }

    public void addHistoryItem(Result result, ResultHandler resultHandler) throws Throwable {
        SQLiteDatabase writableDatabase;
        if (!this.activity.getIntent().getBooleanExtra(Intents.Scan.SAVE_HISTORY, true) || resultHandler.areContentsSecure() || !this.enableHistory) {
            return;
        }
        if (!PreferenceManager.getDefaultSharedPreferences(this.activity).getBoolean(PreferencesActivity.KEY_REMEMBER_DUPLICATES, false)) {
            deletePrevious(result.getText());
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(MimeTypes.BASE_TYPE_TEXT, result.getText());
        contentValues.put("format", result.getBarcodeFormat().toString());
        contentValues.put(Constants.ScionAnalytics.MessageType.DISPLAY_NOTIFICATION, resultHandler.getDisplayContents().toString());
        contentValues.put("timestamp", Long.valueOf(System.currentTimeMillis()));
        try {
            writableDatabase = new DBHelper(this.activity).getWritableDatabase();
            try {
                writableDatabase.insert("history", "timestamp", contentValues);
                close(null, writableDatabase);
            } catch (Throwable th) {
                th = th;
                close(null, writableDatabase);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            writableDatabase = null;
        }
    }

    public void addHistoryItemDetails(String str, String str2) throws Throwable {
        SQLiteDatabase writableDatabase;
        String string;
        String string2;
        Cursor cursor = null;
        try {
            writableDatabase = new DBHelper(this.activity).getWritableDatabase();
            try {
                Cursor cursorQuery = writableDatabase.query("history", ID_DETAIL_COL_PROJECTION, "text=?", new String[]{str}, null, null, "timestamp DESC", "1");
                try {
                    if (cursorQuery.moveToNext()) {
                        string = cursorQuery.getString(0);
                        string2 = cursorQuery.getString(1);
                    } else {
                        string = null;
                        string2 = null;
                    }
                    if (string != null) {
                        if (string2 != null) {
                            if (string2.contains(str2)) {
                                str2 = null;
                            } else {
                                str2 = string2 + " : " + str2;
                            }
                        }
                        if (str2 != null) {
                            ContentValues contentValues = new ContentValues();
                            contentValues.put("details", str2);
                            writableDatabase.update("history", contentValues, "id=?", new String[]{string});
                        }
                    }
                    close(cursorQuery, writableDatabase);
                } catch (Throwable th) {
                    th = th;
                    cursor = cursorQuery;
                    close(cursor, writableDatabase);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
            writableDatabase = null;
        }
    }

    public CharSequence buildHistory() throws Throwable {
        SQLiteDatabase writableDatabase;
        Cursor cursorQuery = null;
        try {
            writableDatabase = new DBHelper(this.activity).getWritableDatabase();
            try {
                cursorQuery = writableDatabase.query("history", COLUMNS, null, null, null, null, "timestamp DESC");
                DateFormat dateTimeInstance = DateFormat.getDateTimeInstance(2, 2);
                StringBuilder sb = new StringBuilder(CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT);
                while (cursorQuery.moveToNext()) {
                    sb.append('\"');
                    sb.append(massageHistoryField(cursorQuery.getString(0)));
                    sb.append("\",");
                    sb.append('\"');
                    sb.append(massageHistoryField(cursorQuery.getString(1)));
                    sb.append("\",");
                    sb.append('\"');
                    sb.append(massageHistoryField(cursorQuery.getString(2)));
                    sb.append("\",");
                    sb.append('\"');
                    sb.append(massageHistoryField(cursorQuery.getString(3)));
                    sb.append("\",");
                    long j9 = cursorQuery.getLong(3);
                    sb.append('\"');
                    sb.append(massageHistoryField(dateTimeInstance.format(new Date(j9))));
                    sb.append("\",");
                    sb.append('\"');
                    sb.append(massageHistoryField(cursorQuery.getString(4)));
                    sb.append("\"\r\n");
                }
                close(cursorQuery, writableDatabase);
                return sb;
            } catch (Throwable th) {
                th = th;
                close(cursorQuery, writableDatabase);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            writableDatabase = null;
        }
    }

    public HistoryItem buildHistoryItem(int i9) throws Throwable {
        SQLiteDatabase readableDatabase;
        Cursor cursorQuery = null;
        try {
            readableDatabase = new DBHelper(this.activity).getReadableDatabase();
            try {
                cursorQuery = readableDatabase.query("history", COLUMNS, null, null, null, null, "timestamp DESC");
                cursorQuery.move(i9 + 1);
                String string = cursorQuery.getString(0);
                String string2 = cursorQuery.getString(1);
                String string3 = cursorQuery.getString(2);
                HistoryItem historyItem = new HistoryItem(new Result(string, null, null, BarcodeFormat.valueOf(string3), cursorQuery.getLong(3)), string2, cursorQuery.getString(4));
                close(cursorQuery, readableDatabase);
                return historyItem;
            } catch (Throwable th) {
                th = th;
                close(cursorQuery, readableDatabase);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            readableDatabase = null;
        }
    }

    public List<HistoryItem> buildHistoryItems() throws Throwable {
        SQLiteDatabase readableDatabase;
        DBHelper dBHelper = new DBHelper(this.activity);
        ArrayList arrayList = new ArrayList();
        Cursor cursorQuery = null;
        try {
            readableDatabase = dBHelper.getReadableDatabase();
            try {
                cursorQuery = readableDatabase.query("history", COLUMNS, null, null, null, null, "timestamp DESC");
                while (cursorQuery.moveToNext()) {
                    String string = cursorQuery.getString(0);
                    String string2 = cursorQuery.getString(1);
                    String string3 = cursorQuery.getString(2);
                    arrayList.add(new HistoryItem(new Result(string, null, null, BarcodeFormat.valueOf(string3), cursorQuery.getLong(3)), string2, cursorQuery.getString(4)));
                }
                close(cursorQuery, readableDatabase);
                return arrayList;
            } catch (Throwable th) {
                th = th;
                close(cursorQuery, readableDatabase);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            readableDatabase = null;
        }
    }

    public void clearHistory() throws Throwable {
        SQLiteDatabase writableDatabase;
        try {
            writableDatabase = new DBHelper(this.activity).getWritableDatabase();
            try {
                writableDatabase.delete("history", null, null);
                close(null, writableDatabase);
            } catch (Throwable th) {
                th = th;
                close(null, writableDatabase);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            writableDatabase = null;
        }
    }

    public void deleteHistoryItem(int i9) throws Throwable {
        SQLiteDatabase writableDatabase;
        Cursor cursorQuery;
        Cursor cursor = null;
        try {
            writableDatabase = new DBHelper(this.activity).getWritableDatabase();
            try {
                cursorQuery = writableDatabase.query("history", ID_COL_PROJECTION, null, null, null, null, "timestamp DESC");
            } catch (Throwable th) {
                th = th;
            }
        } catch (Throwable th2) {
            th = th2;
            writableDatabase = null;
        }
        try {
            cursorQuery.move(i9 + 1);
            writableDatabase.delete("history", "id=" + cursorQuery.getString(0), null);
            close(cursorQuery, writableDatabase);
        } catch (Throwable th3) {
            th = th3;
            cursor = cursorQuery;
            close(cursor, writableDatabase);
            throw th;
        }
    }

    public boolean hasHistoryItems() throws Throwable {
        SQLiteDatabase readableDatabase;
        Cursor cursorQuery = null;
        try {
            readableDatabase = new DBHelper(this.activity).getReadableDatabase();
            try {
                cursorQuery = readableDatabase.query("history", COUNT_COLUMN, null, null, null, null, null);
                cursorQuery.moveToFirst();
                boolean z8 = cursorQuery.getInt(0) > 0;
                close(cursorQuery, readableDatabase);
                return z8;
            } catch (Throwable th) {
                th = th;
                close(cursorQuery, readableDatabase);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            readableDatabase = null;
        }
    }

    public void trimHistory() throws Throwable {
        Cursor cursorQuery;
        Throwable th;
        SQLiteDatabase writableDatabase;
        SQLiteException e9;
        try {
            writableDatabase = new DBHelper(this.activity).getWritableDatabase();
        } catch (SQLiteException e10) {
            cursorQuery = null;
            e9 = e10;
            writableDatabase = null;
        } catch (Throwable th2) {
            cursorQuery = null;
            th = th2;
            writableDatabase = null;
        }
        try {
            cursorQuery = writableDatabase.query("history", ID_COL_PROJECTION, null, null, null, null, "timestamp DESC");
            try {
                try {
                    cursorQuery.move(2000);
                    while (cursorQuery.moveToNext()) {
                        String string = cursorQuery.getString(0);
                        Log.i(TAG, "Deleting scan history ID " + string);
                        writableDatabase.delete("history", "id=" + string, null);
                    }
                } catch (SQLiteException e11) {
                    e9 = e11;
                    Log.w(TAG, e9);
                    close(cursorQuery, writableDatabase);
                }
            } catch (Throwable th3) {
                th = th3;
                close(cursorQuery, writableDatabase);
                throw th;
            }
        } catch (SQLiteException e12) {
            cursorQuery = null;
            e9 = e12;
        } catch (Throwable th4) {
            cursorQuery = null;
            th = th4;
            close(cursorQuery, writableDatabase);
            throw th;
        }
        close(cursorQuery, writableDatabase);
    }
}
