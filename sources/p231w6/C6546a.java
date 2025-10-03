package p231w6;

import java.io.Reader;
import java.io.Writer;
import org.jivesoftware.smack.InterfaceC5583c;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.util.C5613g;
import org.jivesoftware.smack.util.C5614h;
import org.jivesoftware.smack.util.C5616j;
import org.jivesoftware.smack.util.InterfaceC5615i;
import org.jivesoftware.smack.util.InterfaceC5617k;
import p222v6.InterfaceC6490c;

/* renamed from: w6.a */
/* loaded from: classes.dex */
public class C6546a implements InterfaceC6547b {

    /* renamed from: h */
    public static final /* synthetic */ int f22041h = 0;

    /* renamed from: a */
    public XMPPConnection f22042a;

    /* renamed from: b */
    public InterfaceC5583c f22043b;

    /* renamed from: c */
    public InterfaceC6490c f22044c;

    /* renamed from: d */
    public Writer f22045d;

    /* renamed from: e */
    public Reader f22046e;

    /* renamed from: f */
    public InterfaceC5615i f22047f;

    /* renamed from: g */
    public InterfaceC5617k f22048g;

    @Override // p231w6.InterfaceC6547b
    public Reader getReader() {
        return this.f22046e;
    }

    @Override // p231w6.InterfaceC6547b
    public InterfaceC5583c getReaderListener() {
        return this.f22043b;
    }

    @Override // p231w6.InterfaceC6547b
    public Writer getWriter() {
        return this.f22045d;
    }

    @Override // p231w6.InterfaceC6547b
    public InterfaceC5583c getWriterListener() {
        return null;
    }

    @Override // p231w6.InterfaceC6547b
    public Reader newConnectionReader(Reader reader) {
        ((C5613g) this.f22046e).m22332u(this.f22047f);
        C5613g c5613g = new C5613g(reader);
        c5613g.m22331f(this.f22047f);
        this.f22046e = c5613g;
        return c5613g;
    }

    @Override // p231w6.InterfaceC6547b
    public Writer newConnectionWriter(Writer writer) {
        ((C5614h) this.f22045d).m22335v(this.f22048g);
        C5614h c5614h = new C5614h(writer);
        c5614h.m22333f(this.f22048g);
        this.f22045d = c5614h;
        return c5614h;
    }

    @Override // p231w6.InterfaceC6547b
    public void userHasLogged(String str) {
        boolean zEquals = "".equals(C5616j.m22346k(str));
        StringBuilder sb = new StringBuilder();
        sb.append("User logged (");
        sb.append(this.f22042a.hashCode());
        sb.append("): ");
        sb.append(zEquals ? "" : C5616j.m22345j(str));
        sb.append("@");
        sb.append(this.f22042a.m21966C());
        sb.append(":");
        sb.append(this.f22042a.m22014z());
        System.out.println(sb.toString() + "/" + C5616j.m22347l(str));
        this.f22042a.m21992c(this.f22044c);
    }
}
