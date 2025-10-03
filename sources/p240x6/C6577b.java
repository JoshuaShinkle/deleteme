package p240x6;

import java.util.Locale;
import org.jivesoftware.smack.packet.AbstractC5594b;
import org.jivesoftware.smack.util.C5616j;

/* renamed from: x6.b */
/* loaded from: classes.dex */
public class C6577b implements InterfaceC6582g {

    /* renamed from: a */
    public String f22119a;

    /* renamed from: b */
    public boolean f22120b;

    public C6577b(String str, boolean z8) {
        this.f22120b = false;
        this.f22119a = str == null ? null : str.toLowerCase(Locale.US);
        this.f22120b = z8;
    }

    /* renamed from: b */
    public static C6577b m25193b(String str) {
        return new C6577b(str == null ? null : C5616j.m22345j(str), true);
    }

    /* renamed from: c */
    public static C6577b m25194c(String str) {
        return new C6577b(str, false);
    }

    @Override // p240x6.InterfaceC6582g
    /* renamed from: a */
    public boolean mo25192a(AbstractC5594b abstractC5594b) {
        String strM22160j = abstractC5594b.m22160j();
        if (strM22160j == null) {
            return this.f22119a == null;
        }
        String lowerCase = strM22160j.toLowerCase(Locale.US);
        if (this.f22120b) {
            lowerCase = C5616j.m22345j(lowerCase);
        }
        return lowerCase.equals(this.f22119a);
    }

    public String toString() {
        return "FromMatchesFilter (" + (this.f22120b ? "bare" : "full") + "): " + this.f22119a;
    }
}
