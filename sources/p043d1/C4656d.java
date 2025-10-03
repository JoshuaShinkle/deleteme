package p043d1;

import java.io.File;
import p043d1.InterfaceC4653a;

/* renamed from: d1.d */
/* loaded from: classes.dex */
public class C4656d implements InterfaceC4653a.a {

    /* renamed from: a */
    public final long f16296a;

    /* renamed from: b */
    public final a f16297b;

    /* renamed from: d1.d$a */
    public interface a {
        /* renamed from: a */
        File mo18605a();
    }

    public C4656d(a aVar, long j9) {
        this.f16296a = j9;
        this.f16297b = aVar;
    }

    @Override // p043d1.InterfaceC4653a.a
    public InterfaceC4653a build() {
        File fileMo18605a = this.f16297b.mo18605a();
        if (fileMo18605a == null) {
            return null;
        }
        if (fileMo18605a.mkdirs() || (fileMo18605a.exists() && fileMo18605a.isDirectory())) {
            return C4657e.m18606c(fileMo18605a, this.f16296a);
        }
        return null;
    }
}
