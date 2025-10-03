package p073g1;

import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import p226w1.C6508a;
import p243y0.C6592e;
import p243y0.InterfaceC6588a;

/* renamed from: g1.c */
/* loaded from: classes.dex */
public class C4809c implements InterfaceC6588a<ByteBuffer> {
    @Override // p243y0.InterfaceC6588a
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public boolean mo19086b(ByteBuffer byteBuffer, File file, C6592e c6592e) throws Throwable {
        try {
            C6508a.m24915d(byteBuffer, file);
            return true;
        } catch (IOException e9) {
            if (Log.isLoggable("ByteBufferEncoder", 3)) {
                Log.d("ByteBufferEncoder", "Failed to write data", e9);
            }
            return false;
        }
    }
}
