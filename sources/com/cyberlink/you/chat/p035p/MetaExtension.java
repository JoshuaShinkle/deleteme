package com.cyberlink.you.chat.p035p;

import com.cyberlink.you.utility.ULogUtility;
import java.io.IOException;
import org.apache.commons.lang3.StringUtils;
import org.jivesoftware.smack.packet.InterfaceC5595c;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import p008a7.InterfaceC0051e;
import p209u2.C6383t;

/* loaded from: classes.dex */
public class MetaExtension implements InterfaceC5595c {

    /* renamed from: a */
    public final String f12797a;

    /* renamed from: b */
    public final String f12798b;

    /* renamed from: c */
    public final String f12799c;

    public static class Provider implements InterfaceC0051e {
        @Override // p008a7.InterfaceC0051e
        /* renamed from: a */
        public InterfaceC5595c mo181a(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
            String name = null;
            String attributeValue = null;
            boolean z8 = false;
            String attributeValue2 = null;
            while (true) {
                if (z8) {
                    break;
                }
                int next = xmlPullParser.next();
                if (2 != next) {
                    if (3 == next) {
                        if ("meta".equals(xmlPullParser.getName())) {
                            z8 = true;
                        }
                    } else {
                        if (next == 1) {
                            ULogUtility.m16676l("MetaExtension", "Parsing end document tag is not expect");
                            break;
                        }
                        name = xmlPullParser.getName();
                        attributeValue2 = xmlPullParser.getAttributeValue("", "mediaId");
                        attributeValue = xmlPullParser.getAttributeValue("", "commentId");
                    }
                }
            }
            return new MetaExtension(name, attributeValue2, attributeValue);
        }
    }

    public MetaExtension(String str, String str2, String str3) {
        this.f12797a = str;
        this.f12798b = str2;
        this.f12799c = str3;
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    /* renamed from: a */
    public CharSequence mo190a() {
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(mo191b());
        sb.append(">");
        sb.append("<");
        sb.append(this.f12797a);
        if (!C6383t.m24517f(this.f12798b)) {
            sb.append(StringUtils.SPACE);
            sb.append("mediaId");
            sb.append("=\"");
            sb.append(this.f12798b);
            sb.append("\"");
        }
        if (!C6383t.m24517f(this.f12799c)) {
            sb.append(StringUtils.SPACE);
            sb.append("commentId");
            sb.append("=\"");
            sb.append(this.f12799c);
            sb.append("\"");
        }
        sb.append("/></");
        sb.append(mo191b());
        sb.append(">");
        return sb.toString();
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    /* renamed from: b */
    public String mo191b() {
        return "meta";
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    public String getNamespace() {
        return "U";
    }
}
