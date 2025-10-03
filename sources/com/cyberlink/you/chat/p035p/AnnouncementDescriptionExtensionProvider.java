package com.cyberlink.you.chat.p035p;

import com.cyberlink.you.chat.C2898i;
import com.cyberlink.you.utility.ULogUtility;
import com.google.android.gms.plus.PlusShare;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.IOException;
import org.jivesoftware.smack.packet.InterfaceC5595c;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import p008a7.InterfaceC0051e;

/* loaded from: classes.dex */
public class AnnouncementDescriptionExtensionProvider implements InterfaceC0051e {
    @Override // p008a7.InterfaceC0051e
    /* renamed from: a */
    public InterfaceC5595c mo181a(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        C2898i c2898i = new C2898i(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION, "urn:xmpp:announcement:description:0");
        boolean z8 = false;
        while (true) {
            if (z8) {
                break;
            }
            int next = xmlPullParser.next();
            if (next != 2) {
                if (next == 4) {
                    c2898i.m14375g(FirebaseAnalytics.Param.CONTENT, xmlPullParser.getText());
                } else if (next == 3) {
                    if (xmlPullParser.getName().equals(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION)) {
                        z8 = true;
                    }
                } else if (next == 1) {
                    ULogUtility.m16676l("AnnouncementDescriptionExtensionProvider", "Parsing end document tag is not expect");
                    break;
                }
            }
        }
        return c2898i;
    }
}
