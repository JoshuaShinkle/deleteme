package org.jivesoftware.smack.compression;

import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: classes.dex */
public abstract class XMPPInputOutputStream {

    /* renamed from: b */
    public static FlushMethod f19201b;

    /* renamed from: a */
    public String f19202a;

    public enum FlushMethod {
        FULL_FLUSH,
        SYNC_FLUSH
    }

    /* renamed from: a */
    public String m22026a() {
        return this.f19202a;
    }

    /* renamed from: b */
    public abstract InputStream mo22027b(InputStream inputStream);

    /* renamed from: c */
    public abstract OutputStream mo22028c(OutputStream outputStream);

    /* renamed from: d */
    public abstract boolean mo22029d();
}
