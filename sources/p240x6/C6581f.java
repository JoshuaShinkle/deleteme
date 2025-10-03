package p240x6;

import org.jivesoftware.smack.packet.AbstractC5594b;

/* renamed from: x6.f */
/* loaded from: classes.dex */
public class C6581f implements InterfaceC6582g {

    /* renamed from: a */
    public String f22130a;

    /* renamed from: b */
    public String f22131b;

    public C6581f(String str, String str2) {
        this.f22130a = str;
        this.f22131b = str2;
    }

    @Override // p240x6.InterfaceC6582g
    /* renamed from: a */
    public boolean mo25192a(AbstractC5594b abstractC5594b) {
        return abstractC5594b.m22157g(this.f22130a, this.f22131b) != null;
    }

    public C6581f(String str) {
        this(null, str);
    }
}
