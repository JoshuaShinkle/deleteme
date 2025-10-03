package p021c0;

import android.util.Log;
import java.io.Writer;

@Deprecated
/* renamed from: c0.b */
/* loaded from: classes.dex */
public class C0696b extends Writer {

    /* renamed from: b */
    public final String f3351b;

    /* renamed from: c */
    public StringBuilder f3352c = new StringBuilder(128);

    public C0696b(String str) {
        this.f3351b = str;
    }

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        m3460f();
    }

    /* renamed from: f */
    public final void m3460f() {
        if (this.f3352c.length() > 0) {
            Log.d(this.f3351b, this.f3352c.toString());
            StringBuilder sb = this.f3352c;
            sb.delete(0, sb.length());
        }
    }

    @Override // java.io.Writer, java.io.Flushable
    public void flush() {
        m3460f();
    }

    @Override // java.io.Writer
    public void write(char[] cArr, int i9, int i10) {
        for (int i11 = 0; i11 < i10; i11++) {
            char c9 = cArr[i9 + i11];
            if (c9 == '\n') {
                m3460f();
            } else {
                this.f3352c.append(c9);
            }
        }
    }
}
