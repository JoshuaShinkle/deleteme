package okhttp3;

import java.nio.charset.Charset;
import okio.ByteString;
import p007a6.C0042f;

/* renamed from: okhttp3.n */
/* loaded from: classes2.dex */
public final class C5513n {

    /* renamed from: a */
    public static final C5513n f18925a = new C5513n();

    /* renamed from: a */
    public static final String m21587a(String str, String str2, Charset charset) {
        C0042f.m158e(str, "username");
        C0042f.m158e(str2, "password");
        C0042f.m158e(charset, "charset");
        return "Basic " + ByteString.f19095d.m21900c(str + ':' + str2, charset).mo21875a();
    }
}
