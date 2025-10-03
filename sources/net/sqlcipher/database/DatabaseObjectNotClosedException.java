package net.sqlcipher.database;

/* loaded from: classes2.dex */
public class DatabaseObjectNotClosedException extends RuntimeException {

    /* renamed from: s */
    private static final String f18282s = "Application did not close the cursor or database object that was opened here";

    public DatabaseObjectNotClosedException() {
        super(f18282s);
    }
}
