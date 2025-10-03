package org.jivesoftware.smackx.muc.packet;

import java.io.IOException;
import org.jivesoftware.smack.packet.InterfaceC5595c;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import p008a7.InterfaceC0051e;

/* loaded from: classes.dex */
public class GroupChatInvitation implements InterfaceC5595c {

    /* renamed from: a */
    public String f19754a;

    public static class Provider implements InterfaceC0051e {
        @Override // p008a7.InterfaceC0051e
        /* renamed from: a */
        public InterfaceC5595c mo181a(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
            String attributeValue = xmlPullParser.getAttributeValue("", "jid");
            xmlPullParser.next();
            return new GroupChatInvitation(attributeValue);
        }
    }

    public GroupChatInvitation(String str) {
        this.f19754a = str;
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    /* renamed from: b */
    public String mo191b() {
        return "x";
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public String mo190a() {
        return "<x xmlns=\"jabber:x:conference\" jid=\"" + this.f19754a + "\"/>";
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    public String getNamespace() {
        return "jabber:x:conference";
    }
}
