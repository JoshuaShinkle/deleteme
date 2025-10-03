package net.sqlcipher.database;

import android.util.Log;
import p151o0.InterfaceC5391c;

/* loaded from: classes2.dex */
public abstract class SQLiteProgram extends SQLiteClosable implements InterfaceC5391c {
    private static final String TAG = "SQLiteProgram";
    boolean mClosed = false;
    private SQLiteCompiledSql mCompiledSql;

    @Deprecated
    protected SQLiteDatabase mDatabase;
    final String mSql;

    @Deprecated
    protected long nHandle;

    @Deprecated
    protected long nStatement;

    public SQLiteProgram(SQLiteDatabase sQLiteDatabase, String str) {
        this.nHandle = 0L;
        this.nStatement = 0L;
        this.mDatabase = sQLiteDatabase;
        String strTrim = str.trim();
        this.mSql = strTrim;
        sQLiteDatabase.acquireReference();
        sQLiteDatabase.addSQLiteClosable(this);
        this.nHandle = sQLiteDatabase.mNativeHandle;
        strTrim = strTrim.length() >= 6 ? strTrim.substring(0, 6) : strTrim;
        if (!strTrim.equalsIgnoreCase("INSERT") && !strTrim.equalsIgnoreCase("UPDATE") && !strTrim.equalsIgnoreCase("REPLAC") && !strTrim.equalsIgnoreCase("DELETE") && !strTrim.equalsIgnoreCase("SELECT")) {
            SQLiteCompiledSql sQLiteCompiledSql = new SQLiteCompiledSql(sQLiteDatabase, str);
            this.mCompiledSql = sQLiteCompiledSql;
            this.nStatement = sQLiteCompiledSql.nStatement;
            return;
        }
        SQLiteCompiledSql compiledStatementForSql = sQLiteDatabase.getCompiledStatementForSql(str);
        this.mCompiledSql = compiledStatementForSql;
        if (compiledStatementForSql == null) {
            SQLiteCompiledSql sQLiteCompiledSql2 = new SQLiteCompiledSql(sQLiteDatabase, str);
            this.mCompiledSql = sQLiteCompiledSql2;
            sQLiteCompiledSql2.acquire();
            sQLiteDatabase.addToCompiledQueries(str, this.mCompiledSql);
            if (SQLiteDebug.DEBUG_ACTIVE_CURSOR_FINALIZATION) {
                Log.v(TAG, "Created DbObj (id#" + this.mCompiledSql.nStatement + ") for sql: " + str);
            }
        } else if (!compiledStatementForSql.acquire()) {
            long j9 = this.mCompiledSql.nStatement;
            this.mCompiledSql = new SQLiteCompiledSql(sQLiteDatabase, str);
            if (SQLiteDebug.DEBUG_ACTIVE_CURSOR_FINALIZATION) {
                Log.v(TAG, "** possible bug ** Created NEW DbObj (id#" + this.mCompiledSql.nStatement + ") because the previously created DbObj (id#" + j9 + ") was not released for sql:" + str);
            }
        }
        this.nStatement = this.mCompiledSql.nStatement;
    }

    private final native void native_clear_bindings();

    private void releaseCompiledSqlIfNotInCache() {
        if (this.mCompiledSql == null) {
            return;
        }
        synchronized (this.mDatabase.mCompiledQueries) {
            if (this.mDatabase.mCompiledQueries.containsValue(this.mCompiledSql)) {
                this.mCompiledSql.release();
            } else {
                this.mCompiledSql.releaseSqlStatement();
                this.mCompiledSql = null;
                this.nStatement = 0L;
            }
        }
    }

    public void bindBlob(int i9, byte[] bArr) {
        if (bArr == null) {
            throw new IllegalArgumentException("the bind value at index " + i9 + " is null");
        }
        if (this.mClosed) {
            throw new IllegalStateException("program already closed");
        }
        if (this.mDatabase.isOpen()) {
            acquireReference();
            try {
                native_bind_blob(i9, bArr);
                return;
            } finally {
                releaseReference();
            }
        }
        throw new IllegalStateException("database " + this.mDatabase.getPath() + " already closed");
    }

    public void bindDouble(int i9, double d9) {
        if (this.mClosed) {
            throw new IllegalStateException("program already closed");
        }
        if (this.mDatabase.isOpen()) {
            acquireReference();
            try {
                native_bind_double(i9, d9);
                return;
            } finally {
                releaseReference();
            }
        }
        throw new IllegalStateException("database " + this.mDatabase.getPath() + " already closed");
    }

    public void bindLong(int i9, long j9) {
        if (this.mClosed) {
            throw new IllegalStateException("program already closed");
        }
        if (this.mDatabase.isOpen()) {
            acquireReference();
            try {
                native_bind_long(i9, j9);
                return;
            } finally {
                releaseReference();
            }
        }
        throw new IllegalStateException("database " + this.mDatabase.getPath() + " already closed");
    }

    public void bindNull(int i9) {
        if (this.mClosed) {
            throw new IllegalStateException("program already closed");
        }
        if (this.mDatabase.isOpen()) {
            acquireReference();
            try {
                native_bind_null(i9);
                return;
            } finally {
                releaseReference();
            }
        }
        throw new IllegalStateException("database " + this.mDatabase.getPath() + " already closed");
    }

    public void bindString(int i9, String str) {
        if (str == null) {
            throw new IllegalArgumentException("the bind value at index " + i9 + " is null");
        }
        if (this.mClosed) {
            throw new IllegalStateException("program already closed");
        }
        if (this.mDatabase.isOpen()) {
            acquireReference();
            try {
                native_bind_string(i9, str);
                return;
            } finally {
                releaseReference();
            }
        }
        throw new IllegalStateException("database " + this.mDatabase.getPath() + " already closed");
    }

    public void clearBindings() {
        if (this.mClosed) {
            throw new IllegalStateException("program already closed");
        }
        if (this.mDatabase.isOpen()) {
            acquireReference();
            try {
                native_clear_bindings();
                return;
            } finally {
                releaseReference();
            }
        }
        throw new IllegalStateException("database " + this.mDatabase.getPath() + " already closed");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (!this.mClosed && this.mDatabase.isOpen()) {
            this.mDatabase.lock();
            try {
                releaseReference();
                this.mDatabase.unlock();
                this.mClosed = true;
            } catch (Throwable th) {
                this.mDatabase.unlock();
                throw th;
            }
        }
    }

    @Deprecated
    public void compile(String str, boolean z8) {
    }

    public String getSqlString() {
        return this.mSql;
    }

    public final long getUniqueId() {
        return this.nStatement;
    }

    public final native void native_bind_blob(int i9, byte[] bArr);

    public final native void native_bind_double(int i9, double d9);

    public final native void native_bind_long(int i9, long j9);

    public final native void native_bind_null(int i9);

    public final native void native_bind_string(int i9, String str);

    @Deprecated
    public final native void native_compile(String str);

    @Deprecated
    public final native void native_finalize();

    @Override // net.sqlcipher.database.SQLiteClosable
    public void onAllReferencesReleased() {
        releaseCompiledSqlIfNotInCache();
        this.mDatabase.releaseReference();
        this.mDatabase.removeSQLiteClosable(this);
    }

    @Override // net.sqlcipher.database.SQLiteClosable
    public void onAllReferencesReleasedFromContainer() {
        releaseCompiledSqlIfNotInCache();
        this.mDatabase.releaseReference();
    }
}
