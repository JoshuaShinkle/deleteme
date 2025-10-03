package p054e2;

import com.cyberlink.clsm.SmLog;
import java.io.File;
import p209u2.C6369f;

/* renamed from: e2.a */
/* loaded from: classes.dex */
public abstract class AbstractC4710a {

    /* renamed from: a */
    public final File f16467a;

    public AbstractC4710a(File file) {
        this.f16467a = file;
    }

    /* renamed from: a */
    public byte[] m18853a(String str) {
        File file = new File(this.f16467a, str);
        if (file.exists()) {
            return C6369f.m24473o(file);
        }
        return null;
    }

    /* renamed from: b */
    public boolean m18854b(String str) {
        File file = new File(this.f16467a, str);
        if (!file.exists()) {
            SmLog.m5270c("File doesn't exist: " + str);
        }
        return C6369f.m24463e(file);
    }

    /* renamed from: c */
    public boolean m18855c(String str, byte[] bArr) {
        File file = new File(this.f16467a, str);
        if (file.exists()) {
            SmLog.m5270c("File will be overwritten: " + str);
        }
        return C6369f.m24475q(file, bArr);
    }

    public void clear() {
        C6369f.m24464f(this.f16467a);
    }
}
