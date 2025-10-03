package p043d1;

import android.content.Context;
import java.io.File;
import p043d1.C4656d;

/* renamed from: d1.g */
/* loaded from: classes.dex */
public final class C4659g extends C4656d {

    /* renamed from: d1.g$a */
    public class a implements C4656d.a {

        /* renamed from: a */
        public final /* synthetic */ Context f16305a;

        /* renamed from: b */
        public final /* synthetic */ String f16306b;

        public a(Context context, String str) {
            this.f16305a = context;
            this.f16306b = str;
        }

        @Override // p043d1.C4656d.a
        /* renamed from: a */
        public File mo18605a() {
            File cacheDir = this.f16305a.getCacheDir();
            if (cacheDir == null) {
                return null;
            }
            return this.f16306b != null ? new File(cacheDir, this.f16306b) : cacheDir;
        }
    }

    public C4659g(Context context) {
        this(context, "image_manager_disk_cache", 262144000L);
    }

    public C4659g(Context context, String str, long j9) {
        super(new a(context, str), j9);
    }
}
