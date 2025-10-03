package org.jivesoftware.smack.compression;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;
import org.jivesoftware.smack.compression.XMPPInputOutputStream;

/* renamed from: org.jivesoftware.smack.compression.a */
/* loaded from: classes.dex */
public class C5584a extends XMPPInputOutputStream {

    /* renamed from: c */
    public static final Method f19206c;

    /* renamed from: d */
    public static final boolean f19207d;

    /* renamed from: org.jivesoftware.smack.compression.a$a */
    public class a extends InflaterInputStream {
        public a(InputStream inputStream, Inflater inflater, int i9) {
            super(inputStream, inflater, i9);
        }

        @Override // java.util.zip.InflaterInputStream, java.io.FilterInputStream, java.io.InputStream
        public int available() {
            if (((InflaterInputStream) this).inf.needsInput()) {
                return 0;
            }
            return super.available();
        }
    }

    /* renamed from: org.jivesoftware.smack.compression.a$b */
    public class b extends DeflaterOutputStream {

        /* renamed from: b */
        public final /* synthetic */ int f19209b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(OutputStream outputStream, Deflater deflater, int i9) {
            super(outputStream, deflater);
            this.f19209b = i9;
        }

        @Override // java.util.zip.DeflaterOutputStream, java.io.FilterOutputStream, java.io.OutputStream, java.io.Flushable
        public void flush() throws IOException {
            if (!C5584a.f19207d) {
                super.flush();
                return;
            }
            while (true) {
                try {
                    int iIntValue = ((Integer) C5584a.f19206c.invoke(((DeflaterOutputStream) this).def, ((DeflaterOutputStream) this).buf, 0, Integer.valueOf(((DeflaterOutputStream) this).buf.length), Integer.valueOf(this.f19209b))).intValue();
                    if (iIntValue == 0) {
                        super.flush();
                        return;
                    }
                    ((DeflaterOutputStream) this).out.write(((DeflaterOutputStream) this).buf, 0, iIntValue);
                } catch (IllegalAccessException unused) {
                    throw new IOException("Can't flush");
                } catch (IllegalArgumentException unused2) {
                    throw new IOException("Can't flush");
                } catch (InvocationTargetException unused3) {
                    throw new IOException("Can't flush");
                }
            }
        }
    }

    static {
        Method method;
        try {
            Class cls = Integer.TYPE;
            method = Deflater.class.getMethod("deflate", byte[].class, cls, cls, cls);
        } catch (NoSuchMethodException | SecurityException unused) {
            method = null;
        }
        f19206c = method;
        f19207d = method != null;
    }

    public C5584a() {
        this.f19202a = "zlib";
    }

    @Override // org.jivesoftware.smack.compression.XMPPInputOutputStream
    /* renamed from: b */
    public InputStream mo22027b(InputStream inputStream) {
        return new a(inputStream, new Inflater(), 512);
    }

    @Override // org.jivesoftware.smack.compression.XMPPInputOutputStream
    /* renamed from: c */
    public OutputStream mo22028c(OutputStream outputStream) {
        return new b(outputStream, new Deflater(-1), XMPPInputOutputStream.f19201b == XMPPInputOutputStream.FlushMethod.SYNC_FLUSH ? 2 : 3);
    }

    @Override // org.jivesoftware.smack.compression.XMPPInputOutputStream
    /* renamed from: d */
    public boolean mo22029d() {
        return f19207d;
    }
}
