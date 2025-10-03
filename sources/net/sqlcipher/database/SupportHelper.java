package net.sqlcipher.database;

import android.content.Context;
import net.sqlcipher.database.SQLiteDatabase;
import p151o0.InterfaceC5389a;
import p151o0.InterfaceC5390b;

/* loaded from: classes2.dex */
public class SupportHelper implements InterfaceC5390b {
    private final boolean clearPassphrase;
    private byte[] passphrase;
    private SQLiteOpenHelper standardHelper;

    /* renamed from: net.sqlcipher.database.SupportHelper$1 */
    public class C53831 extends SQLiteOpenHelper {
        final /* synthetic */ InterfaceC5390b.a val$configuration;

        public C53831(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory, int i9, SQLiteDatabaseHook sQLiteDatabaseHook, InterfaceC5390b.a aVar) {
            super(context, str, cursorFactory, i9, sQLiteDatabaseHook);
        }

        @Override // net.sqlcipher.database.SQLiteOpenHelper
        public void onConfigure(SQLiteDatabase sQLiteDatabase) {
            throw null;
        }

        @Override // net.sqlcipher.database.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            throw null;
        }

        @Override // net.sqlcipher.database.SQLiteOpenHelper
        public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i9, int i10) {
            throw null;
        }

        @Override // net.sqlcipher.database.SQLiteOpenHelper
        public void onOpen(SQLiteDatabase sQLiteDatabase) {
            throw null;
        }

        @Override // net.sqlcipher.database.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i9, int i10) {
            throw null;
        }
    }

    public SupportHelper(InterfaceC5390b.a aVar, byte[] bArr, SQLiteDatabaseHook sQLiteDatabaseHook, boolean z8) {
        throw null;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.standardHelper.close();
    }

    public String getDatabaseName() {
        return this.standardHelper.getDatabaseName();
    }

    public InterfaceC5389a getReadableDatabase() {
        return getWritableDatabase();
    }

    public InterfaceC5389a getWritableDatabase() {
        try {
            SQLiteDatabase writableDatabase = this.standardHelper.getWritableDatabase(this.passphrase);
            if (this.clearPassphrase && this.passphrase != null) {
                int i9 = 0;
                while (true) {
                    byte[] bArr = this.passphrase;
                    if (i9 >= bArr.length) {
                        break;
                    }
                    bArr[i9] = 0;
                    i9++;
                }
            }
            return writableDatabase;
        } catch (SQLiteException e9) {
            byte[] bArr2 = this.passphrase;
            if (bArr2 != null) {
                boolean z8 = true;
                for (byte b9 : bArr2) {
                    z8 = z8 && b9 == 0;
                }
                if (z8) {
                    throw new IllegalStateException("The passphrase appears to be cleared. This happens by default the first time you use the factory to open a database, so we can remove the cleartext passphrase from memory. If you close the database yourself, please use a fresh SupportFactory to reopen it. If something else (e.g., Room) closed the database, and you cannot control that, use SupportFactory boolean constructor option to opt out of the automatic password clearing step. See the project README for more information.", e9);
                }
            }
            throw e9;
        }
    }

    public void setWriteAheadLoggingEnabled(boolean z8) {
        this.standardHelper.setWriteAheadLoggingEnabled(z8);
    }
}
