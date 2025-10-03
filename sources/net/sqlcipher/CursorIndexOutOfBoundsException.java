package net.sqlcipher;

/* loaded from: classes2.dex */
public class CursorIndexOutOfBoundsException extends IndexOutOfBoundsException {
    public CursorIndexOutOfBoundsException(int i9, int i10) {
        super("Index " + i9 + " requested, with a size of " + i10);
    }

    public CursorIndexOutOfBoundsException(String str) {
        super(str);
    }
}
