package p090h8;

import java.io.Reader;
import java.io.StringReader;
import org.jsoup.nodes.Document;
import org.jsoup.parser.AbstractC5797c;
import org.jsoup.parser.C5795a;
import org.jsoup.parser.C5798d;
import org.jsoup.parser.ParseErrorList;

/* renamed from: h8.d */
/* loaded from: classes.dex */
public class C5033d {

    /* renamed from: a */
    public AbstractC5797c f17367a;

    /* renamed from: b */
    public int f17368b = 0;

    /* renamed from: c */
    public ParseErrorList f17369c;

    /* renamed from: d */
    public C5032c f17370d;

    public C5033d(AbstractC5797c abstractC5797c) {
        this.f17367a = abstractC5797c;
        this.f17370d = abstractC5797c.mo23073b();
    }

    /* renamed from: a */
    public static C5033d m19645a() {
        return new C5033d(new C5795a());
    }

    /* renamed from: e */
    public static C5033d m19646e() {
        return new C5033d(new C5798d());
    }

    /* renamed from: b */
    public boolean m19647b() {
        return this.f17368b > 0;
    }

    /* renamed from: c */
    public Document m19648c(Reader reader, String str) {
        ParseErrorList parseErrorListM22988c = m19647b() ? ParseErrorList.m22988c(this.f17368b) : ParseErrorList.m22987b();
        this.f17369c = parseErrorListM22988c;
        return this.f17367a.m23141d(reader, str, parseErrorListM22988c, this.f17370d);
    }

    /* renamed from: d */
    public Document m19649d(String str, String str2) {
        this.f17369c = m19647b() ? ParseErrorList.m22988c(this.f17368b) : ParseErrorList.m22987b();
        return this.f17367a.m23141d(new StringReader(str), str2, this.f17369c, this.f17370d);
    }
}
