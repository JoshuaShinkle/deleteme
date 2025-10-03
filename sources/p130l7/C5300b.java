package p130l7;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import org.jivesoftware.smack.packet.InterfaceC5595c;

/* renamed from: l7.b */
/* loaded from: classes.dex */
public class C5300b implements InterfaceC5595c {

    /* renamed from: d */
    public static final DateFormat f17995d;

    /* renamed from: a */
    public Date f17996a;

    /* renamed from: b */
    public String f17997b;

    /* renamed from: c */
    public String f17998c;

    static {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd'T'HH:mm:ss");
        f17995d = simpleDateFormat;
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    public C5300b(Date date) {
        this.f17996a = date;
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    /* renamed from: b */
    public String mo191b() {
        return "x";
    }

    /* renamed from: c */
    public String mo20684c() {
        return this.f17997b;
    }

    /* renamed from: d */
    public String mo20685d() {
        return this.f17998c;
    }

    /* renamed from: e */
    public Date mo20686e() {
        return this.f17996a;
    }

    /* renamed from: f */
    public void mo20687f(String str) {
        this.f17997b = str;
    }

    /* renamed from: g */
    public void mo20688g(String str) {
        this.f17998c = str;
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    public String getNamespace() {
        return "jabber:x:delay";
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    /* renamed from: h, reason: merged with bridge method [inline-methods] */
    public String mo190a() {
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(mo191b());
        sb.append(" xmlns=\"");
        sb.append(getNamespace());
        sb.append("\"");
        sb.append(" stamp=\"");
        DateFormat dateFormat = f17995d;
        synchronized (dateFormat) {
            sb.append(dateFormat.format(this.f17996a));
        }
        sb.append("\"");
        String str = this.f17997b;
        if (str != null && str.length() > 0) {
            sb.append(" from=\"");
            sb.append(this.f17997b);
            sb.append("\"");
        }
        sb.append(">");
        String str2 = this.f17998c;
        if (str2 != null && str2.length() > 0) {
            sb.append(this.f17998c);
        }
        sb.append("</");
        sb.append(mo191b());
        sb.append(">");
        return sb.toString();
    }
}
