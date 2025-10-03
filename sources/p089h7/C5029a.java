package p089h7;

import org.apache.commons.lang3.StringUtils;
import org.jivesoftware.smack.packet.InterfaceC5595c;

/* renamed from: h7.a */
/* loaded from: classes.dex */
public class C5029a implements InterfaceC5595c {

    /* renamed from: a */
    public final String f17350a;

    /* renamed from: b */
    public final long f17351b;

    /* renamed from: c */
    public final String f17352c;

    public C5029a(String str, long j9, String str2) {
        if (str == null || "".equals(str)) {
            throw new IllegalArgumentException("Session ID must not be null or empty");
        }
        if (j9 < 0 || j9 > 65535) {
            throw new IllegalArgumentException("Sequence must not be between 0 and 65535");
        }
        if (str2 == null) {
            throw new IllegalArgumentException("Data must not be null");
        }
        this.f17350a = str;
        this.f17351b = j9;
        this.f17352c = str2;
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    /* renamed from: b */
    public String mo191b() {
        return "data";
    }

    /* renamed from: c */
    public String m19606c() {
        return this.f17350a;
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public String mo190a() {
        return "<" + mo191b() + StringUtils.SPACE + "xmlns=\"http://jabber.org/protocol/ibb\" seq=\"" + this.f17351b + "\" sid=\"" + this.f17350a + "\">" + this.f17352c + "</" + mo191b() + ">";
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    public String getNamespace() {
        return "http://jabber.org/protocol/ibb";
    }
}
