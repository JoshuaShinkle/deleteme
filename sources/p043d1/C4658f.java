package p043d1;

import android.content.Context;
import java.io.File;
import p043d1.C4656d;

/* renamed from: d1.f */
/* loaded from: classes.dex */
public final class C4658f extends C4656d {

    /* renamed from: d1.f$a */
    public class a implements C4656d.a {

        /* renamed from: a */
        public final /* synthetic */ Context f16303a;

        /* renamed from: b */
        public final /* synthetic */ String f16304b;

        public a(Context context, String str) {
            this.f16303a = context;
            this.f16304b = str;
        }

        @Override // p043d1.C4656d.a
        /* renamed from: a */
        public File mo18605a() {
            File externalCacheDir;
            File fileM18609b = m18609b();
            return ((fileM18609b == null || !fileM18609b.exists()) && (externalCacheDir = this.f16303a.getExternalCacheDir()) != null && externalCacheDir.canWrite()) ? this.f16304b != null ? new File(externalCacheDir, this.f16304b) : externalCacheDir : fileM18609b;
        }

        /* renamed from: b */
        public final File m18609b() {
            File cacheDir = this.f16303a.getCacheDir();
            if (cacheDir == null) {
                return null;
            }
            return this.f16304b != null ? new File(cacheDir, this.f16304b) : cacheDir;
        }
    }

    public C4658f(Context context, String str, long j9) {
        super(new a(context, str), j9);
    }
}
