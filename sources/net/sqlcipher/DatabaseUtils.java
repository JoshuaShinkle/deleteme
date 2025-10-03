package net.sqlcipher;

import android.content.ContentValues;
import android.content.OperationApplicationException;
import android.os.Parcel;
import android.text.TextUtils;
import android.util.Log;
import com.google.common.base.Ascii;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.Collator;
import java.util.HashMap;
import java.util.Map;
import net.sqlcipher.database.SQLiteAbortException;
import net.sqlcipher.database.SQLiteConstraintException;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteDatabaseCorruptException;
import net.sqlcipher.database.SQLiteDiskIOException;
import net.sqlcipher.database.SQLiteException;
import net.sqlcipher.database.SQLiteFullException;
import net.sqlcipher.database.SQLiteProgram;
import net.sqlcipher.database.SQLiteStatement;

/* loaded from: classes2.dex */
public class DatabaseUtils {
    private static final boolean DEBUG = false;
    private static final boolean LOCAL_LOGV = false;
    private static final String TAG = "DatabaseUtils";
    private static final String[] countProjection = {"count(*)"};
    private static final char[] HEX_DIGITS_LOWER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static Collator mColl = null;

    public static class InsertHelper {
        public static final int TABLE_INFO_PRAGMA_COLUMNNAME_INDEX = 1;
        public static final int TABLE_INFO_PRAGMA_DEFAULT_INDEX = 4;
        private HashMap<String, Integer> mColumns;
        private final SQLiteDatabase mDb;
        private final String mTableName;
        private String mInsertSQL = null;
        private SQLiteStatement mInsertStatement = null;
        private SQLiteStatement mReplaceStatement = null;
        private SQLiteStatement mPreparedStatement = null;

        public InsertHelper(SQLiteDatabase sQLiteDatabase, String str) {
            this.mDb = sQLiteDatabase;
            this.mTableName = str;
        }

        private void buildSQL() {
            StringBuilder sb = new StringBuilder(128);
            sb.append("INSERT INTO ");
            sb.append(this.mTableName);
            sb.append(" (");
            StringBuilder sb2 = new StringBuilder(128);
            sb2.append("VALUES (");
            Cursor cursorRawQuery = null;
            try {
                cursorRawQuery = this.mDb.rawQuery("PRAGMA table_info(" + this.mTableName + ")", (String[]) null);
                this.mColumns = new HashMap<>(cursorRawQuery.getCount());
                int i9 = 1;
                while (cursorRawQuery.moveToNext()) {
                    String string = cursorRawQuery.getString(1);
                    String string2 = cursorRawQuery.getString(4);
                    this.mColumns.put(string, Integer.valueOf(i9));
                    sb.append("'");
                    sb.append(string);
                    sb.append("'");
                    if (string2 == null) {
                        sb2.append("?");
                    } else {
                        sb2.append("COALESCE(?, ");
                        sb2.append(string2);
                        sb2.append(")");
                    }
                    sb.append(i9 == cursorRawQuery.getCount() ? ") " : ", ");
                    sb2.append(i9 == cursorRawQuery.getCount() ? ");" : ", ");
                    i9++;
                }
                cursorRawQuery.close();
                sb.append((CharSequence) sb2);
                this.mInsertSQL = sb.toString();
            } catch (Throwable th) {
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
                throw th;
            }
        }

        private SQLiteStatement getStatement(boolean z8) {
            if (!z8) {
                if (this.mInsertStatement == null) {
                    if (this.mInsertSQL == null) {
                        buildSQL();
                    }
                    this.mInsertStatement = this.mDb.m25577compileStatement(this.mInsertSQL);
                }
                return this.mInsertStatement;
            }
            if (this.mReplaceStatement == null) {
                if (this.mInsertSQL == null) {
                    buildSQL();
                }
                this.mReplaceStatement = this.mDb.m25577compileStatement("INSERT OR REPLACE" + this.mInsertSQL.substring(6));
            }
            return this.mReplaceStatement;
        }

        private synchronized long insertInternal(ContentValues contentValues, boolean z8) {
            SQLiteStatement statement;
            try {
                statement = getStatement(z8);
                statement.clearBindings();
                for (Map.Entry<String, Object> entry : contentValues.valueSet()) {
                    DatabaseUtils.bindObjectToProgram(statement, getColumnIndex(entry.getKey()), entry.getValue());
                }
            } catch (SQLException e9) {
                Log.e(DatabaseUtils.TAG, "Error inserting " + contentValues + " into table  " + this.mTableName, e9);
                return -1L;
            }
            return statement.executeInsert();
        }

        public void bind(int i9, double d9) {
            this.mPreparedStatement.bindDouble(i9, d9);
        }

        public void bindNull(int i9) {
            this.mPreparedStatement.bindNull(i9);
        }

        public void close() {
            SQLiteStatement sQLiteStatement = this.mInsertStatement;
            if (sQLiteStatement != null) {
                sQLiteStatement.close();
                this.mInsertStatement = null;
            }
            SQLiteStatement sQLiteStatement2 = this.mReplaceStatement;
            if (sQLiteStatement2 != null) {
                sQLiteStatement2.close();
                this.mReplaceStatement = null;
            }
            this.mInsertSQL = null;
            this.mColumns = null;
        }

        public long execute() {
            SQLiteStatement sQLiteStatement = this.mPreparedStatement;
            if (sQLiteStatement == null) {
                throw new IllegalStateException("you must prepare this inserter before calling execute");
            }
            try {
                try {
                    return sQLiteStatement.executeInsert();
                } catch (SQLException e9) {
                    Log.e(DatabaseUtils.TAG, "Error executing InsertHelper with table " + this.mTableName, e9);
                    this.mPreparedStatement = null;
                    return -1L;
                }
            } finally {
                this.mPreparedStatement = null;
            }
        }

        public int getColumnIndex(String str) {
            getStatement(false);
            Integer num = this.mColumns.get(str);
            if (num != null) {
                return num.intValue();
            }
            throw new IllegalArgumentException("column '" + str + "' is invalid");
        }

        public long insert(ContentValues contentValues) {
            return insertInternal(contentValues, false);
        }

        public void prepareForInsert() {
            SQLiteStatement statement = getStatement(false);
            this.mPreparedStatement = statement;
            statement.clearBindings();
        }

        public void prepareForReplace() {
            SQLiteStatement statement = getStatement(true);
            this.mPreparedStatement = statement;
            statement.clearBindings();
        }

        public long replace(ContentValues contentValues) {
            return insertInternal(contentValues, true);
        }

        public void bind(int i9, float f9) {
            this.mPreparedStatement.bindDouble(i9, f9);
        }

        public void bind(int i9, long j9) {
            this.mPreparedStatement.bindLong(i9, j9);
        }

        public void bind(int i9, int i10) {
            this.mPreparedStatement.bindLong(i9, i10);
        }

        public void bind(int i9, boolean z8) {
            this.mPreparedStatement.bindLong(i9, z8 ? 1L : 0L);
        }

        public void bind(int i9, byte[] bArr) {
            if (bArr == null) {
                this.mPreparedStatement.bindNull(i9);
            } else {
                this.mPreparedStatement.bindBlob(i9, bArr);
            }
        }

        public void bind(int i9, String str) {
            if (str == null) {
                this.mPreparedStatement.bindNull(i9);
            } else {
                this.mPreparedStatement.bindString(i9, str);
            }
        }
    }

    public static void appendEscapedSQLString(StringBuilder sb, String str) {
        sb.append('\'');
        if (str.indexOf(39) != -1) {
            int length = str.length();
            for (int i9 = 0; i9 < length; i9++) {
                char cCharAt = str.charAt(i9);
                if (cCharAt == '\'') {
                    sb.append('\'');
                }
                sb.append(cCharAt);
            }
        } else {
            sb.append(str);
        }
        sb.append('\'');
    }

    public static final void appendValueToSql(StringBuilder sb, Object obj) {
        if (obj == null) {
            sb.append("NULL");
            return;
        }
        if (!(obj instanceof Boolean)) {
            appendEscapedSQLString(sb, obj.toString());
        } else if (((Boolean) obj).booleanValue()) {
            sb.append('1');
        } else {
            sb.append('0');
        }
    }

    public static void bindObjectToProgram(SQLiteProgram sQLiteProgram, int i9, Object obj) {
        if (obj == null) {
            sQLiteProgram.bindNull(i9);
            return;
        }
        if ((obj instanceof Double) || (obj instanceof Float)) {
            sQLiteProgram.bindDouble(i9, ((Number) obj).doubleValue());
            return;
        }
        if (obj instanceof Number) {
            sQLiteProgram.bindLong(i9, ((Number) obj).longValue());
            return;
        }
        if (obj instanceof Boolean) {
            if (((Boolean) obj).booleanValue()) {
                sQLiteProgram.bindLong(i9, 1L);
                return;
            } else {
                sQLiteProgram.bindLong(i9, 0L);
                return;
            }
        }
        if (obj instanceof byte[]) {
            sQLiteProgram.bindBlob(i9, (byte[]) obj);
        } else {
            sQLiteProgram.bindString(i9, obj.toString());
        }
    }

    public static String concatenateWhere(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return str2;
        }
        if (TextUtils.isEmpty(str2)) {
            return str;
        }
        return "(" + str + ") AND (" + str2 + ")";
    }

    public static void cursorDoubleToContentValues(Cursor cursor, String str, ContentValues contentValues, String str2) {
        int columnIndex = cursor.getColumnIndex(str);
        if (cursor.isNull(columnIndex)) {
            contentValues.put(str2, (Double) null);
        } else {
            contentValues.put(str2, Double.valueOf(cursor.getDouble(columnIndex)));
        }
    }

    public static void cursorDoubleToContentValuesIfPresent(Cursor cursor, ContentValues contentValues, String str) throws IllegalArgumentException {
        int columnIndexOrThrow = cursor.getColumnIndexOrThrow(str);
        if (cursor.isNull(columnIndexOrThrow)) {
            return;
        }
        contentValues.put(str, Double.valueOf(cursor.getDouble(columnIndexOrThrow)));
    }

    public static void cursorDoubleToCursorValues(Cursor cursor, String str, ContentValues contentValues) {
        cursorDoubleToContentValues(cursor, str, contentValues, str);
    }

    public static void cursorFillWindow(Cursor cursor, int i9, android.database.CursorWindow cursorWindow) {
        boolean zPutNull;
        if (i9 < 0 || i9 >= cursor.getCount()) {
            return;
        }
        int position = cursor.getPosition();
        int columnCount = cursor.getColumnCount();
        cursorWindow.clear();
        cursorWindow.setStartPosition(i9);
        cursorWindow.setNumColumns(columnCount);
        if (cursor.moveToPosition(i9)) {
            while (cursorWindow.allocRow()) {
                int i10 = 0;
                while (true) {
                    if (i10 >= columnCount) {
                        break;
                    }
                    int type = cursor.getType(i10);
                    if (type == 0) {
                        zPutNull = cursorWindow.putNull(i9, i10);
                    } else if (type == 1) {
                        zPutNull = cursorWindow.putLong(cursor.getLong(i10), i9, i10);
                    } else if (type == 2) {
                        zPutNull = cursorWindow.putDouble(cursor.getDouble(i10), i9, i10);
                    } else if (type != 4) {
                        String string = cursor.getString(i10);
                        zPutNull = string != null ? cursorWindow.putString(string, i9, i10) : cursorWindow.putNull(i9, i10);
                    } else {
                        byte[] blob = cursor.getBlob(i10);
                        zPutNull = blob != null ? cursorWindow.putBlob(blob, i9, i10) : cursorWindow.putNull(i9, i10);
                    }
                    if (!zPutNull) {
                        cursorWindow.freeLastRow();
                        break;
                    }
                    i10++;
                }
                i9++;
                if (!cursor.moveToNext()) {
                    break;
                }
            }
        }
        cursor.moveToPosition(position);
    }

    public static void cursorFloatToContentValuesIfPresent(Cursor cursor, ContentValues contentValues, String str) throws IllegalArgumentException {
        int columnIndexOrThrow = cursor.getColumnIndexOrThrow(str);
        if (cursor.isNull(columnIndexOrThrow)) {
            return;
        }
        contentValues.put(str, Float.valueOf(cursor.getFloat(columnIndexOrThrow)));
    }

    public static void cursorIntToContentValues(Cursor cursor, String str, ContentValues contentValues) {
        cursorIntToContentValues(cursor, str, contentValues, str);
    }

    public static void cursorIntToContentValuesIfPresent(Cursor cursor, ContentValues contentValues, String str) throws IllegalArgumentException {
        int columnIndexOrThrow = cursor.getColumnIndexOrThrow(str);
        if (cursor.isNull(columnIndexOrThrow)) {
            return;
        }
        contentValues.put(str, Integer.valueOf(cursor.getInt(columnIndexOrThrow)));
    }

    public static void cursorLongToContentValues(Cursor cursor, String str, ContentValues contentValues) {
        cursorLongToContentValues(cursor, str, contentValues, str);
    }

    public static void cursorLongToContentValuesIfPresent(Cursor cursor, ContentValues contentValues, String str) throws IllegalArgumentException {
        int columnIndexOrThrow = cursor.getColumnIndexOrThrow(str);
        if (cursor.isNull(columnIndexOrThrow)) {
            return;
        }
        contentValues.put(str, Long.valueOf(cursor.getLong(columnIndexOrThrow)));
    }

    public static void cursorRowToContentValues(Cursor cursor, ContentValues contentValues) {
        AbstractWindowedCursor abstractWindowedCursor = cursor instanceof AbstractWindowedCursor ? (AbstractWindowedCursor) cursor : null;
        String[] columnNames = cursor.getColumnNames();
        int length = columnNames.length;
        for (int i9 = 0; i9 < length; i9++) {
            if (abstractWindowedCursor == null || !abstractWindowedCursor.isBlob(i9)) {
                contentValues.put(columnNames[i9], cursor.getString(i9));
            } else {
                contentValues.put(columnNames[i9], cursor.getBlob(i9));
            }
        }
    }

    public static void cursorShortToContentValuesIfPresent(Cursor cursor, ContentValues contentValues, String str) throws IllegalArgumentException {
        int columnIndexOrThrow = cursor.getColumnIndexOrThrow(str);
        if (cursor.isNull(columnIndexOrThrow)) {
            return;
        }
        contentValues.put(str, Short.valueOf(cursor.getShort(columnIndexOrThrow)));
    }

    public static void cursorStringToContentValues(Cursor cursor, String str, ContentValues contentValues) {
        cursorStringToContentValues(cursor, str, contentValues, str);
    }

    public static void cursorStringToContentValuesIfPresent(Cursor cursor, ContentValues contentValues, String str) throws IllegalArgumentException {
        int columnIndexOrThrow = cursor.getColumnIndexOrThrow(str);
        if (cursor.isNull(columnIndexOrThrow)) {
            return;
        }
        contentValues.put(str, cursor.getString(columnIndexOrThrow));
    }

    public static void cursorStringToInsertHelper(Cursor cursor, String str, InsertHelper insertHelper, int i9) {
        insertHelper.bind(i9, cursor.getString(cursor.getColumnIndexOrThrow(str)));
    }

    public static void dumpCurrentRow(Cursor cursor) {
        dumpCurrentRow(cursor, System.out);
    }

    public static String dumpCurrentRowToString(Cursor cursor) {
        StringBuilder sb = new StringBuilder();
        dumpCurrentRow(cursor, sb);
        return sb.toString();
    }

    public static void dumpCursor(Cursor cursor) {
        dumpCursor(cursor, System.out);
    }

    public static String dumpCursorToString(Cursor cursor) {
        StringBuilder sb = new StringBuilder();
        dumpCursor(cursor, sb);
        return sb.toString();
    }

    private static char[] encodeHex(byte[] bArr, char[] cArr) {
        char[] cArr2 = new char[bArr.length << 1];
        int i9 = 0;
        for (byte b9 : bArr) {
            int i10 = i9 + 1;
            cArr2[i9] = cArr[(b9 & 240) >>> 4];
            i9 = i10 + 1;
            cArr2[i10] = cArr[b9 & Ascii.f15389SI];
        }
        return cArr2;
    }

    public static String getCollationKey(String str) {
        byte[] collationKeyInBytes = getCollationKeyInBytes(str);
        try {
            return new String(collationKeyInBytes, 0, getKeyLen(collationKeyInBytes), "ISO8859_1");
        } catch (Exception unused) {
            return "";
        }
    }

    private static byte[] getCollationKeyInBytes(String str) {
        if (mColl == null) {
            Collator collator = Collator.getInstance();
            mColl = collator;
            collator.setStrength(0);
        }
        return mColl.getCollationKey(str).toByteArray();
    }

    public static String getHexCollationKey(String str) {
        byte[] collationKeyInBytes = getCollationKeyInBytes(str);
        return new String(encodeHex(collationKeyInBytes, HEX_DIGITS_LOWER), 0, getKeyLen(collationKeyInBytes) * 2);
    }

    private static int getKeyLen(byte[] bArr) {
        return bArr[bArr.length + (-1)] != 0 ? bArr.length : bArr.length - 1;
    }

    public static int getTypeOfObject(Object obj) {
        if (obj == null) {
            return 0;
        }
        if (obj instanceof byte[]) {
            return 4;
        }
        if ((obj instanceof Float) || (obj instanceof Double)) {
            return 2;
        }
        return ((obj instanceof Long) || (obj instanceof Integer)) ? 1 : 3;
    }

    public static long longForQuery(SQLiteDatabase sQLiteDatabase, String str, String[] strArr) {
        SQLiteStatement sQLiteStatementCompileStatement = sQLiteDatabase.m25577compileStatement(str);
        try {
            return longForQuery(sQLiteStatementCompileStatement, strArr);
        } finally {
            sQLiteStatementCompileStatement.close();
        }
    }

    public static long queryNumEntries(SQLiteDatabase sQLiteDatabase, String str) {
        Cursor cursorQuery = sQLiteDatabase.query(str, countProjection, null, null, null, null, null);
        try {
            cursorQuery.moveToFirst();
            return cursorQuery.getLong(0);
        } finally {
            cursorQuery.close();
        }
    }

    public static final void readExceptionFromParcel(Parcel parcel) {
        int i9 = parcel.readInt();
        if (i9 == 0) {
            return;
        }
        readExceptionFromParcel(parcel, parcel.readString(), i9);
    }

    public static void readExceptionWithFileNotFoundExceptionFromParcel(Parcel parcel) throws FileNotFoundException {
        int i9 = parcel.readInt();
        if (i9 == 0) {
            return;
        }
        String string = parcel.readString();
        if (i9 == 1) {
            throw new FileNotFoundException(string);
        }
        readExceptionFromParcel(parcel, string, i9);
    }

    public static void readExceptionWithOperationApplicationExceptionFromParcel(Parcel parcel) throws OperationApplicationException {
        int i9 = parcel.readInt();
        if (i9 == 0) {
            return;
        }
        String string = parcel.readString();
        if (i9 == 10) {
            throw new OperationApplicationException(string);
        }
        readExceptionFromParcel(parcel, string, i9);
    }

    public static String sqlEscapeString(String str) {
        StringBuilder sb = new StringBuilder();
        appendEscapedSQLString(sb, str);
        return sb.toString();
    }

    public static String stringForQuery(SQLiteDatabase sQLiteDatabase, String str, String[] strArr) {
        SQLiteStatement sQLiteStatementCompileStatement = sQLiteDatabase.m25577compileStatement(str);
        try {
            return stringForQuery(sQLiteStatementCompileStatement, strArr);
        } finally {
            sQLiteStatementCompileStatement.close();
        }
    }

    public static final void writeExceptionToParcel(Parcel parcel, Exception exc) {
        int i9;
        boolean z8;
        int i10 = 1;
        if (exc instanceof FileNotFoundException) {
            z8 = false;
        } else {
            if (exc instanceof IllegalArgumentException) {
                i9 = 2;
            } else if (exc instanceof UnsupportedOperationException) {
                i9 = 3;
            } else if (exc instanceof SQLiteAbortException) {
                i9 = 4;
            } else if (exc instanceof SQLiteConstraintException) {
                i9 = 5;
            } else if (exc instanceof SQLiteDatabaseCorruptException) {
                i9 = 6;
            } else if (exc instanceof SQLiteFullException) {
                i9 = 7;
            } else if (exc instanceof SQLiteDiskIOException) {
                i9 = 8;
            } else if (exc instanceof SQLiteException) {
                i9 = 9;
            } else {
                if (!(exc instanceof OperationApplicationException)) {
                    parcel.writeException(exc);
                    Log.e(TAG, "Writing exception to parcel", exc);
                    return;
                }
                i9 = 10;
            }
            i10 = i9;
            z8 = true;
        }
        parcel.writeInt(i10);
        parcel.writeString(exc.getMessage());
        if (z8) {
            Log.e(TAG, "Writing exception to parcel", exc);
        }
    }

    public static void cursorIntToContentValues(Cursor cursor, String str, ContentValues contentValues, String str2) {
        int columnIndex = cursor.getColumnIndex(str);
        if (cursor.isNull(columnIndex)) {
            contentValues.put(str2, (Integer) null);
        } else {
            contentValues.put(str2, Integer.valueOf(cursor.getInt(columnIndex)));
        }
    }

    public static void cursorLongToContentValues(Cursor cursor, String str, ContentValues contentValues, String str2) {
        int columnIndex = cursor.getColumnIndex(str);
        if (cursor.isNull(columnIndex)) {
            contentValues.put(str2, (Long) null);
        } else {
            contentValues.put(str2, Long.valueOf(cursor.getLong(columnIndex)));
        }
    }

    public static void cursorStringToContentValues(Cursor cursor, String str, ContentValues contentValues, String str2) {
        contentValues.put(str2, cursor.getString(cursor.getColumnIndexOrThrow(str)));
    }

    public static void dumpCurrentRow(Cursor cursor, PrintStream printStream) {
        String string;
        String[] columnNames = cursor.getColumnNames();
        printStream.println("" + cursor.getPosition() + " {");
        int length = columnNames.length;
        for (int i9 = 0; i9 < length; i9++) {
            try {
                string = cursor.getString(i9);
            } catch (SQLiteException unused) {
                string = "<unprintable>";
            }
            printStream.println("   " + columnNames[i9] + '=' + string);
        }
        printStream.println("}");
    }

    public static void dumpCursor(Cursor cursor, PrintStream printStream) {
        printStream.println(">>>>> Dumping cursor " + cursor);
        if (cursor != null) {
            int position = cursor.getPosition();
            cursor.moveToPosition(-1);
            while (cursor.moveToNext()) {
                dumpCurrentRow(cursor, printStream);
            }
            cursor.moveToPosition(position);
        }
        printStream.println("<<<<<");
    }

    private static final void readExceptionFromParcel(Parcel parcel, String str, int i9) {
        switch (i9) {
            case 2:
                throw new IllegalArgumentException(str);
            case 3:
                throw new UnsupportedOperationException(str);
            case 4:
                throw new SQLiteAbortException(str);
            case 5:
                throw new SQLiteConstraintException(str);
            case 6:
                throw new SQLiteDatabaseCorruptException(str);
            case 7:
                throw new SQLiteFullException(str);
            case 8:
                throw new SQLiteDiskIOException(str);
            case 9:
                throw new SQLiteException(str);
            default:
                parcel.readException(i9, str);
                return;
        }
    }

    public static long longForQuery(SQLiteStatement sQLiteStatement, String[] strArr) {
        if (strArr != null) {
            int length = strArr.length;
            int i9 = 0;
            while (i9 < length) {
                int i10 = i9 + 1;
                bindObjectToProgram(sQLiteStatement, i10, strArr[i9]);
                i9 = i10;
            }
        }
        return sQLiteStatement.simpleQueryForLong();
    }

    public static String stringForQuery(SQLiteStatement sQLiteStatement, String[] strArr) {
        if (strArr != null) {
            int length = strArr.length;
            int i9 = 0;
            while (i9 < length) {
                int i10 = i9 + 1;
                bindObjectToProgram(sQLiteStatement, i10, strArr[i9]);
                i9 = i10;
            }
        }
        return sQLiteStatement.simpleQueryForString();
    }

    public static void dumpCurrentRow(Cursor cursor, StringBuilder sb) {
        String string;
        String[] columnNames = cursor.getColumnNames();
        sb.append("" + cursor.getPosition() + " {\n");
        int length = columnNames.length;
        for (int i9 = 0; i9 < length; i9++) {
            try {
                string = cursor.getString(i9);
            } catch (SQLiteException unused) {
                string = "<unprintable>";
            }
            sb.append("   " + columnNames[i9] + '=' + string + "\n");
        }
        sb.append("}\n");
    }

    public static void dumpCursor(Cursor cursor, StringBuilder sb) {
        sb.append(">>>>> Dumping cursor " + cursor + "\n");
        if (cursor != null) {
            int position = cursor.getPosition();
            cursor.moveToPosition(-1);
            while (cursor.moveToNext()) {
                dumpCurrentRow(cursor, sb);
            }
            cursor.moveToPosition(position);
        }
        sb.append("<<<<<\n");
    }
}
