package net.sqlcipher;

import android.util.Log;
import java.io.File;
import net.sqlcipher.database.SQLiteDatabase;

/* loaded from: classes2.dex */
public final class DefaultDatabaseErrorHandler implements DatabaseErrorHandler {
    private final String TAG = DefaultDatabaseErrorHandler.class.getSimpleName();

    private void deleteDatabaseFile(String str) {
        if (str.equalsIgnoreCase(SQLiteDatabase.MEMORY) || str.trim().length() == 0) {
            return;
        }
        Log.e(this.TAG, "deleting the database file: " + str);
        try {
            new File(str).delete();
        } catch (Exception e9) {
            Log.w(this.TAG, "delete failed: " + e9.getMessage());
        }
    }

    @Override // net.sqlcipher.DatabaseErrorHandler
    public void onCorruption(SQLiteDatabase sQLiteDatabase) {
        Log.e(this.TAG, "Corruption reported by sqlite on database, deleting: " + sQLiteDatabase.getPath());
        if (sQLiteDatabase.isOpen()) {
            Log.e(this.TAG, "Database object for corrupted database is already open, closing");
            try {
                sQLiteDatabase.close();
            } catch (Exception e9) {
                Log.e(this.TAG, "Exception closing Database object for corrupted database, ignored", e9);
            }
        }
        deleteDatabaseFile(sQLiteDatabase.getPath());
    }
}
