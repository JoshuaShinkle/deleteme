package net.sqlcipher.database;

import android.os.SystemClock;
import android.util.Log;
import net.sqlcipher.CursorWindow;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes2.dex */
public class SQLiteQuery extends SQLiteProgram {
    private static final String TAG = "Cursor";
    private String[] mBindArgs;
    private Object[] mObjectBindArgs;
    private int mOffsetIndex;

    public SQLiteQuery(SQLiteDatabase sQLiteDatabase, String str, int i9, String[] strArr) {
        super(sQLiteDatabase, str);
        this.mOffsetIndex = i9;
        this.mBindArgs = strArr;
    }

    private final native int native_column_count();

    private final native String native_column_name(int i9);

    private final native int native_fill_window(CursorWindow cursorWindow, int i9, int i10, int i11, int i12, int i13);

    public void bindArguments(Object[] objArr) {
        if (objArr == null || objArr.length <= 0) {
            return;
        }
        for (int i9 = 0; i9 < objArr.length; i9++) {
            Object obj = objArr[i9];
            if (obj == null) {
                bindNull(i9 + 1);
            } else if (obj instanceof Double) {
                bindDouble(i9 + 1, ((Double) obj).doubleValue());
            } else if (obj instanceof Float) {
                bindDouble(i9 + 1, Double.valueOf(((Number) obj).floatValue()).doubleValue());
            } else if (obj instanceof Long) {
                bindLong(i9 + 1, ((Long) obj).longValue());
            } else if (obj instanceof Integer) {
                bindLong(i9 + 1, Long.valueOf(((Number) obj).intValue()).longValue());
            } else if (obj instanceof Boolean) {
                bindLong(i9 + 1, ((Boolean) obj).booleanValue() ? 1L : 0L);
            } else if (obj instanceof byte[]) {
                bindBlob(i9 + 1, (byte[]) obj);
            } else {
                bindString(i9 + 1, obj.toString());
            }
        }
    }

    @Override // net.sqlcipher.database.SQLiteProgram
    public void bindDouble(int i9, double d9) {
        this.mBindArgs[i9 - 1] = Double.toString(d9);
        if (this.mClosed) {
            return;
        }
        super.bindDouble(i9, d9);
    }

    @Override // net.sqlcipher.database.SQLiteProgram
    public void bindLong(int i9, long j9) {
        this.mBindArgs[i9 - 1] = Long.toString(j9);
        if (this.mClosed) {
            return;
        }
        super.bindLong(i9, j9);
    }

    @Override // net.sqlcipher.database.SQLiteProgram
    public void bindNull(int i9) {
        this.mBindArgs[i9 - 1] = null;
        if (this.mClosed) {
            return;
        }
        super.bindNull(i9);
    }

    @Override // net.sqlcipher.database.SQLiteProgram
    public void bindString(int i9, String str) {
        this.mBindArgs[i9 - 1] = str;
        if (this.mClosed) {
            return;
        }
        super.bindString(i9, str);
    }

    public int columnCountLocked() {
        acquireReference();
        try {
            return native_column_count();
        } finally {
            releaseReference();
        }
    }

    public String columnNameLocked(int i9) {
        acquireReference();
        try {
            return native_column_name(i9);
        } finally {
            releaseReference();
        }
    }

    public int fillWindow(CursorWindow cursorWindow, int i9, int i10) {
        SystemClock.uptimeMillis();
        this.mDatabase.lock();
        try {
            acquireReference();
            try {
                try {
                    cursorWindow.acquireReference();
                    int iNative_fill_window = native_fill_window(cursorWindow, cursorWindow.getStartPosition(), cursorWindow.getRequiredPosition(), this.mOffsetIndex, i9, i10);
                    if (SQLiteDebug.DEBUG_SQL_STATEMENTS) {
                        Log.d(TAG, "fillWindow(): " + this.mSql);
                    }
                    return iNative_fill_window;
                } finally {
                    cursorWindow.releaseReference();
                }
            } catch (IllegalStateException unused) {
                releaseReference();
                this.mDatabase.unlock();
                return 0;
            } catch (SQLiteDatabaseCorruptException e9) {
                this.mDatabase.onCorruption();
                throw e9;
            }
        } finally {
            releaseReference();
            this.mDatabase.unlock();
        }
    }

    public void requery() {
        String[] strArr = this.mBindArgs;
        if (strArr != null) {
            int length = strArr.length;
            try {
                Object[] objArr = this.mObjectBindArgs;
                if (objArr != null) {
                    bindArguments(objArr);
                    return;
                }
                int i9 = 0;
                while (i9 < length) {
                    int i10 = i9 + 1;
                    super.bindString(i10, this.mBindArgs[i9]);
                    i9 = i10;
                }
            } catch (SQLiteMisuseException e9) {
                StringBuilder sb = new StringBuilder("mSql " + this.mSql);
                for (int i11 = 0; i11 < length; i11++) {
                    sb.append(StringUtils.SPACE);
                    sb.append(this.mBindArgs[i11]);
                }
                sb.append(StringUtils.SPACE);
                throw new IllegalStateException(sb.toString(), e9);
            }
        }
    }

    public String toString() {
        return "SQLiteQuery: " + this.mSql;
    }

    public SQLiteQuery(SQLiteDatabase sQLiteDatabase, String str, int i9, Object[] objArr) {
        super(sQLiteDatabase, str);
        this.mOffsetIndex = i9;
        this.mObjectBindArgs = objArr;
        this.mBindArgs = new String[objArr != null ? objArr.length : 0];
    }
}
