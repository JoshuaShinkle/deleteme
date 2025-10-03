package com.mixpanel.android.mpmetrics;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import java.io.File;
import java.io.FilenameFilter;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p006a5.C0035c;
import p256z4.C6826b;

/* loaded from: classes2.dex */
public class MPDbAdapter {

    /* renamed from: b */
    public static final Map<Context, MPDbAdapter> f15719b = new HashMap();

    /* renamed from: c */
    public static final String f15720c;

    /* renamed from: d */
    public static final String f15721d;

    /* renamed from: e */
    public static final String f15722e;

    /* renamed from: f */
    public static final String f15723f;

    /* renamed from: g */
    public static final String f15724g;

    /* renamed from: h */
    public static final String f15725h;

    /* renamed from: i */
    public static final String f15726i;

    /* renamed from: j */
    public static final String f15727j;

    /* renamed from: a */
    public final C4475a f15728a;

    public enum Table {
        EVENTS("events"),
        PEOPLE("people"),
        ANONYMOUS_PEOPLE("anonymous_people"),
        GROUPS("groups");

        private final String mTableName;

        Table(String str) {
            this.mTableName = str;
        }

        /* renamed from: a */
        public String m17889a() {
            return this.mTableName;
        }
    }

    /* renamed from: com.mixpanel.android.mpmetrics.MPDbAdapter$a */
    public static class C4475a extends SQLiteOpenHelper {

        /* renamed from: b */
        public final File f15734b;

        /* renamed from: c */
        public final C6826b f15735c;

        /* renamed from: d */
        public final Context f15736d;

        /* renamed from: com.mixpanel.android.mpmetrics.MPDbAdapter$a$a */
        public class a implements FilenameFilter {
            public a() {
            }

            @Override // java.io.FilenameFilter
            public boolean accept(File file, String str) {
                return str.startsWith("com.mixpanel.android.mpmetrics.MixpanelAPI_");
            }
        }

        public C4475a(Context context, String str) {
            super(context, str, (SQLiteDatabase.CursorFactory) null, 7);
            this.f15734b = context.getDatabasePath(str);
            this.f15735c = C6826b.m25465k(context);
            this.f15736d = context;
        }

        /* renamed from: f */
        public boolean m17892f() {
            if (this.f15734b.exists()) {
                return this.f15734b.length() > Math.max(this.f15734b.getUsableSpace(), (long) this.f15735c.m25480m()) || this.f15734b.length() > ((long) this.f15735c.m25479l());
            }
            return false;
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) throws SQLException {
            C0035c.m147i("MixpanelAPI.Database", "Creating a new Mixpanel events DB");
            sQLiteDatabase.execSQL(MPDbAdapter.f15720c);
            sQLiteDatabase.execSQL(MPDbAdapter.f15721d);
            sQLiteDatabase.execSQL(MPDbAdapter.f15722e);
            sQLiteDatabase.execSQL(MPDbAdapter.f15723f);
            sQLiteDatabase.execSQL(MPDbAdapter.f15724g);
            sQLiteDatabase.execSQL(MPDbAdapter.f15725h);
            sQLiteDatabase.execSQL(MPDbAdapter.f15726i);
            sQLiteDatabase.execSQL(MPDbAdapter.f15727j);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i9, int i10) throws JSONException, SQLException {
            C0035c.m147i("MixpanelAPI.Database", "Upgrading app, replacing Mixpanel events DB");
            if (i9 >= 4 && i10 <= 7) {
                if (i9 == 4) {
                    m17894x(sQLiteDatabase);
                    m17895y(sQLiteDatabase);
                    m17896z(sQLiteDatabase);
                }
                if (i9 == 5) {
                    m17895y(sQLiteDatabase);
                    m17896z(sQLiteDatabase);
                }
                if (i9 == 6) {
                    m17896z(sQLiteDatabase);
                    return;
                }
                return;
            }
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Table.EVENTS.m17889a());
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Table.PEOPLE.m17889a());
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Table.GROUPS.m17889a());
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Table.ANONYMOUS_PEOPLE.m17889a());
            sQLiteDatabase.execSQL(MPDbAdapter.f15720c);
            sQLiteDatabase.execSQL(MPDbAdapter.f15721d);
            sQLiteDatabase.execSQL(MPDbAdapter.f15722e);
            sQLiteDatabase.execSQL(MPDbAdapter.f15723f);
            sQLiteDatabase.execSQL(MPDbAdapter.f15724g);
            sQLiteDatabase.execSQL(MPDbAdapter.f15725h);
            sQLiteDatabase.execSQL(MPDbAdapter.f15726i);
            sQLiteDatabase.execSQL(MPDbAdapter.f15727j);
        }

        /* renamed from: w */
        public void m17893w() {
            close();
            this.f15734b.delete();
        }

        /* renamed from: x */
        public final void m17894x(SQLiteDatabase sQLiteDatabase) throws JSONException, SQLException {
            int i9;
            String string;
            StringBuilder sb = new StringBuilder();
            sb.append("ALTER TABLE ");
            Table table = Table.EVENTS;
            sb.append(table.m17889a());
            sb.append(" ADD COLUMN ");
            sb.append("automatic_data");
            sb.append(" INTEGER DEFAULT 0");
            sQLiteDatabase.execSQL(sb.toString());
            StringBuilder sb2 = new StringBuilder();
            sb2.append("ALTER TABLE ");
            Table table2 = Table.PEOPLE;
            sb2.append(table2.m17889a());
            sb2.append(" ADD COLUMN ");
            sb2.append("automatic_data");
            sb2.append(" INTEGER DEFAULT 0");
            sQLiteDatabase.execSQL(sb2.toString());
            sQLiteDatabase.execSQL("ALTER TABLE " + table.m17889a() + " ADD COLUMN token STRING NOT NULL DEFAULT ''");
            sQLiteDatabase.execSQL("ALTER TABLE " + table2.m17889a() + " ADD COLUMN token STRING NOT NULL DEFAULT ''");
            StringBuilder sb3 = new StringBuilder();
            sb3.append("SELECT * FROM ");
            sb3.append(table.m17889a());
            Cursor cursorRawQuery = sQLiteDatabase.rawQuery(sb3.toString(), null);
            while (cursorRawQuery.moveToNext()) {
                try {
                    String string2 = new JSONObject(cursorRawQuery.getString(cursorRawQuery.getColumnIndex("data") >= 0 ? cursorRawQuery.getColumnIndex("data") : 1)).getJSONObject("properties").getString("token");
                    sQLiteDatabase.execSQL("UPDATE " + Table.EVENTS.m17889a() + " SET token = '" + string2 + "' WHERE _id = " + cursorRawQuery.getInt(cursorRawQuery.getColumnIndex("_id") >= 0 ? cursorRawQuery.getColumnIndex("_id") : 0));
                } catch (JSONException unused) {
                    sQLiteDatabase.delete(Table.EVENTS.m17889a(), "_id = 0", null);
                }
            }
            Cursor cursorRawQuery2 = sQLiteDatabase.rawQuery("SELECT * FROM " + Table.PEOPLE.m17889a(), null);
            while (cursorRawQuery2.moveToNext()) {
                try {
                    string = new JSONObject(cursorRawQuery2.getString(cursorRawQuery2.getColumnIndex("data") >= 0 ? cursorRawQuery2.getColumnIndex("data") : 1)).getString("$token");
                    i9 = cursorRawQuery2.getInt(cursorRawQuery2.getColumnIndex("_id") >= 0 ? cursorRawQuery2.getColumnIndex("_id") : 0);
                } catch (JSONException unused2) {
                    i9 = 0;
                }
                try {
                    sQLiteDatabase.execSQL("UPDATE " + Table.PEOPLE.m17889a() + " SET token = '" + string + "' WHERE _id = " + i9);
                } catch (JSONException unused3) {
                    sQLiteDatabase.delete(Table.PEOPLE.m17889a(), "_id = " + i9, null);
                }
            }
        }

        /* renamed from: y */
        public final void m17895y(SQLiteDatabase sQLiteDatabase) throws SQLException {
            sQLiteDatabase.execSQL(MPDbAdapter.f15722e);
            sQLiteDatabase.execSQL(MPDbAdapter.f15726i);
        }

        /* renamed from: z */
        public final void m17896z(SQLiteDatabase sQLiteDatabase) throws SQLException {
            sQLiteDatabase.execSQL(MPDbAdapter.f15723f);
            sQLiteDatabase.execSQL(MPDbAdapter.f15727j);
            File file = new File(this.f15736d.getApplicationInfo().dataDir, "shared_prefs");
            if (file.exists() && file.isDirectory()) {
                for (String str : file.list(new a())) {
                    SharedPreferences sharedPreferences = this.f15736d.getSharedPreferences(str.split("\\.xml")[0], 0);
                    String string = sharedPreferences.getString("waiting_array", null);
                    if (string != null) {
                        try {
                            JSONArray jSONArray = new JSONArray(string);
                            sQLiteDatabase.beginTransaction();
                            for (int i9 = 0; i9 < jSONArray.length(); i9++) {
                                try {
                                    try {
                                        JSONObject jSONObject = jSONArray.getJSONObject(i9);
                                        String string2 = jSONObject.getString("$token");
                                        ContentValues contentValues = new ContentValues();
                                        contentValues.put("data", jSONObject.toString());
                                        contentValues.put("created_at", Long.valueOf(System.currentTimeMillis()));
                                        contentValues.put("automatic_data", Boolean.FALSE);
                                        contentValues.put("token", string2);
                                        sQLiteDatabase.insert(Table.ANONYMOUS_PEOPLE.m17889a(), null, contentValues);
                                    } catch (JSONException unused) {
                                    }
                                } catch (Throwable th) {
                                    sQLiteDatabase.endTransaction();
                                    throw th;
                                }
                            }
                            sQLiteDatabase.setTransactionSuccessful();
                            sQLiteDatabase.endTransaction();
                        } catch (JSONException unused2) {
                        }
                        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
                        editorEdit.remove("waiting_array");
                        editorEdit.apply();
                    }
                }
            }
        }
    }

    static {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE ");
        Table table = Table.EVENTS;
        sb.append(table.m17889a());
        sb.append(" (_id INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sb.append("data");
        sb.append(" STRING NOT NULL, ");
        sb.append("created_at");
        sb.append(" INTEGER NOT NULL, ");
        sb.append("automatic_data");
        sb.append(" INTEGER DEFAULT 0, ");
        sb.append("token");
        sb.append(" STRING NOT NULL DEFAULT '')");
        f15720c = sb.toString();
        StringBuilder sb2 = new StringBuilder();
        sb2.append("CREATE TABLE ");
        Table table2 = Table.PEOPLE;
        sb2.append(table2.m17889a());
        sb2.append(" (_id INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sb2.append("data");
        sb2.append(" STRING NOT NULL, ");
        sb2.append("created_at");
        sb2.append(" INTEGER NOT NULL, ");
        sb2.append("automatic_data");
        sb2.append(" INTEGER DEFAULT 0, ");
        sb2.append("token");
        sb2.append(" STRING NOT NULL DEFAULT '')");
        f15721d = sb2.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append("CREATE TABLE ");
        Table table3 = Table.GROUPS;
        sb3.append(table3.m17889a());
        sb3.append(" (_id INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sb3.append("data");
        sb3.append(" STRING NOT NULL, ");
        sb3.append("created_at");
        sb3.append(" INTEGER NOT NULL, ");
        sb3.append("automatic_data");
        sb3.append(" INTEGER DEFAULT 0, ");
        sb3.append("token");
        sb3.append(" STRING NOT NULL DEFAULT '')");
        f15722e = sb3.toString();
        StringBuilder sb4 = new StringBuilder();
        sb4.append("CREATE TABLE ");
        Table table4 = Table.ANONYMOUS_PEOPLE;
        sb4.append(table4.m17889a());
        sb4.append(" (_id INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sb4.append("data");
        sb4.append(" STRING NOT NULL, ");
        sb4.append("created_at");
        sb4.append(" INTEGER NOT NULL, ");
        sb4.append("automatic_data");
        sb4.append(" INTEGER DEFAULT 0, ");
        sb4.append("token");
        sb4.append(" STRING NOT NULL DEFAULT '')");
        f15723f = sb4.toString();
        f15724g = "CREATE INDEX IF NOT EXISTS time_idx ON " + table.m17889a() + " (created_at);";
        f15725h = "CREATE INDEX IF NOT EXISTS time_idx ON " + table2.m17889a() + " (created_at);";
        f15726i = "CREATE INDEX IF NOT EXISTS time_idx ON " + table3.m17889a() + " (created_at);";
        f15727j = "CREATE INDEX IF NOT EXISTS time_idx ON " + table4.m17889a() + " (created_at);";
    }

    public MPDbAdapter(Context context) {
        this(context, "mixpanel");
    }

    /* renamed from: q */
    public static MPDbAdapter m17878q(Context context) {
        MPDbAdapter mPDbAdapter;
        Map<Context, MPDbAdapter> map = f15719b;
        synchronized (map) {
            Context applicationContext = context.getApplicationContext();
            if (map.containsKey(applicationContext)) {
                mPDbAdapter = map.get(applicationContext);
            } else {
                mPDbAdapter = new MPDbAdapter(applicationContext);
                map.put(applicationContext, mPDbAdapter);
            }
        }
        return mPDbAdapter;
    }

    /* renamed from: a */
    public boolean m17879a() {
        return this.f15728a.m17892f();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:15:0x007b A[PHI: r0
      0x007b: PHI (r0v5 android.database.Cursor) = (r0v4 android.database.Cursor), (r0v7 android.database.Cursor) binds: [B:14:0x0079, B:24:0x0096] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v3, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r0v9 */
    /* renamed from: j */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public int m17880j(JSONObject jSONObject, String str, Table table) throws Throwable {
        Cursor cursorRawQuery;
        if (m17879a()) {
            C0035c.m141c("MixpanelAPI.Database", "There is not enough space left on the device or the data was over the maximum size limit so it was discarded");
            return -2;
        }
        String strM17889a = table.m17889a();
        Cursor cursor = null;
        cursor = null;
        ?? r02 = 0;
        try {
            try {
                try {
                    SQLiteDatabase writableDatabase = this.f15728a.getWritableDatabase();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("data", jSONObject.toString());
                    contentValues.put("created_at", Long.valueOf(System.currentTimeMillis()));
                    contentValues.put("token", str);
                    writableDatabase.insert(strM17889a, null, contentValues);
                    cursorRawQuery = writableDatabase.rawQuery("SELECT COUNT(*) FROM " + strM17889a + " WHERE token='" + str + "'", null);
                } catch (Throwable th) {
                    th = th;
                    if (r02 != 0) {
                        r02.close();
                    }
                    this.f15728a.close();
                    throw th;
                }
            } catch (SQLiteException unused) {
                cursorRawQuery = null;
            } catch (OutOfMemoryError unused2) {
            }
            try {
                cursorRawQuery.moveToFirst();
                int i9 = cursorRawQuery.getInt(0);
                cursorRawQuery.close();
                this.f15728a.close();
                return i9;
            } catch (SQLiteException unused3) {
                C0035c.m141c("MixpanelAPI.Database", "Could not add Mixpanel data to table");
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                } else {
                    cursor = cursorRawQuery;
                }
                this.f15728a.m17893w();
                if (cursor != null) {
                    cursor.close();
                }
                this.f15728a.close();
                return -1;
            } catch (OutOfMemoryError unused4) {
                cursor = cursorRawQuery;
                C0035c.m141c("MixpanelAPI.Database", "Out of memory when adding Mixpanel data to table");
                if (cursor != null) {
                }
                this.f15728a.close();
                return -1;
            }
        } catch (Throwable th2) {
            th = th2;
            r02 = jSONObject;
        }
    }

    /* renamed from: k */
    public void m17881k(Table table, String str) {
        String strM17889a = table.m17889a();
        try {
            try {
                this.f15728a.getWritableDatabase().delete(strM17889a, "token = '" + str + "'", null);
            } catch (SQLiteException e9) {
                C0035c.m142d("MixpanelAPI.Database", "Could not clean timed-out Mixpanel records from " + strM17889a + ". Re-initializing database.", e9);
                this.f15728a.m17893w();
            }
        } finally {
            this.f15728a.close();
        }
    }

    /* renamed from: l */
    public void m17882l(long j9, Table table) {
        String strM17889a = table.m17889a();
        try {
            try {
                this.f15728a.getWritableDatabase().delete(strM17889a, "created_at <= " + j9, null);
            } catch (SQLiteException e9) {
                C0035c.m142d("MixpanelAPI.Database", "Could not clean timed-out Mixpanel records from " + strM17889a + ". Re-initializing database.", e9);
                this.f15728a.m17893w();
            }
        } finally {
            this.f15728a.close();
        }
    }

    /* renamed from: m */
    public void m17883m(String str, Table table, String str2) {
        String strM17889a = table.m17889a();
        try {
            try {
                try {
                    this.f15728a.getWritableDatabase().delete(strM17889a, new StringBuffer("_id <= " + str + " AND token = '" + str2 + "'").toString(), null);
                } catch (SQLiteException e9) {
                    C0035c.m142d("MixpanelAPI.Database", "Could not clean sent Mixpanel records from " + strM17889a + ". Re-initializing database.", e9);
                    this.f15728a.m17893w();
                }
            } catch (Exception e10) {
                C0035c.m142d("MixpanelAPI.Database", "Unknown exception. Could not clean sent Mixpanel records from " + strM17889a + ".Re-initializing database.", e10);
                this.f15728a.m17893w();
            }
        } finally {
            this.f15728a.close();
        }
    }

    /* renamed from: n */
    public void m17884n() {
        this.f15728a.m17893w();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:44:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x012f  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0136 A[ADDED_TO_REGION] */
    /* renamed from: o */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public String[] m17885o(Table table, String str) throws Throwable {
        Cursor cursorRawQuery;
        Cursor cursorRawQuery2;
        Object objValueOf;
        String string;
        String string2;
        StringBuffer stringBuffer;
        String strM17889a = table.m17889a();
        SQLiteDatabase readableDatabase = this.f15728a.getReadableDatabase();
        Cursor cursor = null;
        try {
            StringBuffer stringBuffer2 = new StringBuffer("SELECT * FROM " + strM17889a + " WHERE token = '" + str + "' ");
            stringBuffer = new StringBuffer("SELECT COUNT(*) FROM " + strM17889a + " WHERE token = '" + str + "' ");
            StringBuilder sb = new StringBuilder();
            sb.append("ORDER BY created_at ASC LIMIT ");
            sb.append(Integer.toString(this.f15728a.f15735c.m25475g()));
            stringBuffer2.append(sb.toString());
            cursorRawQuery2 = readableDatabase.rawQuery(stringBuffer2.toString(), null);
        } catch (SQLiteException e9) {
            e = e9;
            cursorRawQuery2 = null;
            cursorRawQuery = null;
        } catch (Throwable th) {
            th = th;
            cursorRawQuery = null;
        }
        try {
            cursorRawQuery = readableDatabase.rawQuery(stringBuffer.toString(), null);
            try {
                try {
                    cursorRawQuery.moveToFirst();
                    objValueOf = String.valueOf(cursorRawQuery.getInt(0));
                } catch (SQLiteException e10) {
                    e = e10;
                    objValueOf = null;
                }
                try {
                    JSONArray jSONArray = new JSONArray();
                    string2 = null;
                    while (cursorRawQuery2.moveToNext()) {
                        if (cursorRawQuery2.isLast()) {
                            string2 = cursorRawQuery2.getString(cursorRawQuery2.getColumnIndex("_id") >= 0 ? cursorRawQuery2.getColumnIndex("_id") : 0);
                        }
                        try {
                            jSONArray.put(new JSONObject(cursorRawQuery2.getString(cursorRawQuery2.getColumnIndex("data") >= 0 ? cursorRawQuery2.getColumnIndex("data") : 1)));
                        } catch (JSONException unused) {
                        }
                    }
                    string = jSONArray.length() > 0 ? jSONArray.toString() : null;
                    this.f15728a.close();
                    cursorRawQuery2.close();
                    cursorRawQuery.close();
                } catch (SQLiteException e11) {
                    e = e11;
                    C0035c.m142d("MixpanelAPI.Database", "Could not pull records for Mixpanel out of database " + strM17889a + ". Waiting to send.", e);
                    this.f15728a.close();
                    if (cursorRawQuery2 != null) {
                        cursorRawQuery2.close();
                    }
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                    string = null;
                    string2 = null;
                    if (string2 != null) {
                    }
                    return null;
                }
            } catch (Throwable th2) {
                th = th2;
                cursor = cursorRawQuery2;
                this.f15728a.close();
                if (cursor != null) {
                    cursor.close();
                }
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
                throw th;
            }
        } catch (SQLiteException e12) {
            e = e12;
            cursorRawQuery = null;
            objValueOf = cursorRawQuery;
            C0035c.m142d("MixpanelAPI.Database", "Could not pull records for Mixpanel out of database " + strM17889a + ". Waiting to send.", e);
            this.f15728a.close();
            if (cursorRawQuery2 != null) {
            }
            if (cursorRawQuery != null) {
            }
            string = null;
            string2 = null;
            if (string2 != null) {
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
            cursorRawQuery = null;
        }
        if (string2 != null || string == null) {
            return null;
        }
        return new String[]{string2, string, objValueOf};
    }

    /* renamed from: p */
    public File m17886p() {
        return this.f15728a.f15734b;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v10 */
    /* JADX WARN: Type inference failed for: r5v11 */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v3, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r5v4 */
    /* JADX WARN: Type inference failed for: r5v7 */
    /* JADX WARN: Type inference failed for: r5v9 */
    /* renamed from: r */
    public int m17887r(String str, String str2) throws Throwable {
        Cursor cursorRawQuery;
        SQLiteDatabase writableDatabase;
        if (m17879a()) {
            C0035c.m141c("MixpanelAPI.Database", "There is not enough space left on the device or the data was over the maximum size limit so it was discarded");
            return -2;
        }
        ?? r52 = 0;
        r52 = 0;
        Cursor cursor = null;
        int i9 = -1;
        try {
            try {
                try {
                    writableDatabase = this.f15728a.getWritableDatabase();
                    cursorRawQuery = writableDatabase.rawQuery(new StringBuffer("SELECT * FROM " + Table.ANONYMOUS_PEOPLE.m17889a() + " WHERE token = '" + str + "'").toString(), null);
                } catch (Throwable th) {
                    th = th;
                    if (r52 != 0) {
                        r52.close();
                    }
                    this.f15728a.close();
                    throw th;
                }
            } catch (SQLiteException e9) {
                e = e9;
                cursorRawQuery = null;
            }
            try {
                writableDatabase.beginTransaction();
                while (cursorRawQuery.moveToNext()) {
                    try {
                        try {
                            ContentValues contentValues = new ContentValues();
                            contentValues.put("created_at", Long.valueOf(cursorRawQuery.getLong(cursorRawQuery.getColumnIndex("created_at") >= 0 ? cursorRawQuery.getColumnIndex("created_at") : 2)));
                            contentValues.put("automatic_data", Integer.valueOf(cursorRawQuery.getInt(cursorRawQuery.getColumnIndex("automatic_data") >= 0 ? cursorRawQuery.getColumnIndex("automatic_data") : 3)));
                            contentValues.put("token", cursorRawQuery.getString(cursorRawQuery.getColumnIndex("token") >= 0 ? cursorRawQuery.getColumnIndex("token") : 4));
                            JSONObject jSONObject = new JSONObject(cursorRawQuery.getString(cursorRawQuery.getColumnIndex("data") >= 0 ? cursorRawQuery.getColumnIndex("data") : 1));
                            jSONObject.put("$distinct_id", str2);
                            contentValues.put("data", jSONObject.toString());
                            writableDatabase.insert(Table.PEOPLE.m17889a(), null, contentValues);
                            int i10 = cursorRawQuery.getInt(cursorRawQuery.getColumnIndex("_id") >= 0 ? cursorRawQuery.getColumnIndex("_id") : 0);
                            writableDatabase.delete(Table.ANONYMOUS_PEOPLE.m17889a(), "_id = " + i10, null);
                            i9++;
                        } catch (JSONException unused) {
                        }
                    } finally {
                        writableDatabase.endTransaction();
                    }
                }
                writableDatabase.setTransactionSuccessful();
                cursorRawQuery.close();
            } catch (SQLiteException e10) {
                e = e10;
                C0035c.m142d("MixpanelAPI.Database", "Could not push anonymous updates records from " + Table.ANONYMOUS_PEOPLE.m17889a() + ". Re-initializing database.", e);
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                } else {
                    cursor = cursorRawQuery;
                }
                this.f15728a.m17893w();
                r52 = cursor;
                if (cursor != null) {
                    cursor.close();
                    r52 = cursor;
                }
                this.f15728a.close();
                return i9;
            }
            this.f15728a.close();
            return i9;
        } catch (Throwable th2) {
            th = th2;
            r52 = str;
        }
    }

    /* renamed from: s */
    public int m17888s(Map<String, String> map, String str) throws Throwable {
        Cursor cursorRawQuery;
        SQLiteDatabase writableDatabase;
        if (m17879a()) {
            C0035c.m141c("MixpanelAPI.Database", "There is not enough space left on the device or the data was over the maximum size limit so it was discarded");
            return -2;
        }
        int i9 = 0;
        Cursor cursor = null;
        try {
            try {
                writableDatabase = this.f15728a.getWritableDatabase();
                cursorRawQuery = writableDatabase.rawQuery(new StringBuffer("SELECT * FROM " + Table.EVENTS.m17889a() + " WHERE token = '" + str + "'").toString(), null);
            } catch (SQLiteException e9) {
                e = e9;
                cursorRawQuery = null;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            try {
                writableDatabase.beginTransaction();
                int i10 = 0;
                while (cursorRawQuery.moveToNext()) {
                    try {
                        try {
                            try {
                                ContentValues contentValues = new ContentValues();
                                JSONObject jSONObject = new JSONObject(cursorRawQuery.getString(cursorRawQuery.getColumnIndex("data") >= 0 ? cursorRawQuery.getColumnIndex("data") : 1));
                                JSONObject jSONObject2 = jSONObject.getJSONObject("properties");
                                for (Map.Entry<String, String> entry : map.entrySet()) {
                                    jSONObject2.put(entry.getKey(), entry.getValue());
                                }
                                jSONObject.put("properties", jSONObject2);
                                contentValues.put("data", jSONObject.toString());
                                int i11 = cursorRawQuery.getInt(cursorRawQuery.getColumnIndex("_id") >= 0 ? cursorRawQuery.getColumnIndex("_id") : 0);
                                writableDatabase.update(Table.EVENTS.m17889a(), contentValues, "_id = " + i11, null);
                                i10++;
                            } catch (JSONException unused) {
                            }
                        } catch (SQLiteException e10) {
                            e = e10;
                            i9 = i10;
                            C0035c.m142d("MixpanelAPI.Database", "Could not re-write events history. Re-initializing database.", e);
                            if (cursorRawQuery != null) {
                                cursorRawQuery.close();
                            } else {
                                cursor = cursorRawQuery;
                            }
                            this.f15728a.m17893w();
                            if (cursor != null) {
                                cursor.close();
                            }
                            this.f15728a.close();
                            return i9;
                        }
                    } finally {
                        writableDatabase.endTransaction();
                    }
                }
                writableDatabase.setTransactionSuccessful();
                cursorRawQuery.close();
                this.f15728a.close();
                return i10;
            } catch (SQLiteException e11) {
                e = e11;
            }
        } catch (Throwable th2) {
            th = th2;
            cursor = cursorRawQuery;
            if (cursor != null) {
                cursor.close();
            }
            this.f15728a.close();
            throw th;
        }
    }

    public MPDbAdapter(Context context, String str) {
        this.f15728a = new C4475a(context, str);
    }
}
