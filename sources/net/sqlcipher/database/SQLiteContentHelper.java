package net.sqlcipher.database;

import android.content.res.AssetFileDescriptor;
import android.os.MemoryFile;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import net.sqlcipher.Cursor;

/* loaded from: classes2.dex */
public class SQLiteContentHelper {
    public static AssetFileDescriptor getBlobColumnAsAssetFile(SQLiteDatabase sQLiteDatabase, String str, String[] strArr) throws NoSuchMethodException, SecurityException, FileNotFoundException {
        ParcelFileDescriptor parcelFileDescriptor;
        try {
            MemoryFile memoryFileSimpleQueryForBlobMemoryFile = simpleQueryForBlobMemoryFile(sQLiteDatabase, str, strArr);
            if (memoryFileSimpleQueryForBlobMemoryFile == null) {
                throw new FileNotFoundException("No results.");
            }
            try {
                Method declaredMethod = memoryFileSimpleQueryForBlobMemoryFile.getClass().getDeclaredMethod("getParcelFileDescriptor", new Class[0]);
                declaredMethod.setAccessible(true);
                parcelFileDescriptor = (ParcelFileDescriptor) declaredMethod.invoke(memoryFileSimpleQueryForBlobMemoryFile, new Object[0]);
            } catch (Exception e9) {
                Log.i("SQLiteContentHelper", "SQLiteCursor.java: " + e9);
                parcelFileDescriptor = null;
            }
            return new AssetFileDescriptor(parcelFileDescriptor, 0L, memoryFileSimpleQueryForBlobMemoryFile.length());
        } catch (IOException e10) {
            throw new FileNotFoundException(e10.toString());
        }
    }

    private static MemoryFile simpleQueryForBlobMemoryFile(SQLiteDatabase sQLiteDatabase, String str, String[] strArr) {
        Cursor cursorRawQuery = sQLiteDatabase.rawQuery(str, strArr);
        if (cursorRawQuery == null) {
            return null;
        }
        try {
            if (!cursorRawQuery.moveToFirst()) {
                return null;
            }
            byte[] blob = cursorRawQuery.getBlob(0);
            if (blob == null) {
                return null;
            }
            MemoryFile memoryFile = new MemoryFile(null, blob.length);
            memoryFile.writeBytes(blob, 0, 0, blob.length);
            return memoryFile;
        } finally {
            cursorRawQuery.close();
        }
    }
}
