package org.jivesoftware.smackx.muc.provider;

import com.google.android.gms.actions.SearchIntents;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.IOException;
import org.jivesoftware.smack.packet.AbstractC5586IQ;
import org.jivesoftware.smack.util.PacketParserUtils;
import org.jivesoftware.smackx.muc.packet.MUCOwner;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import p008a7.InterfaceC0049c;

/* loaded from: classes.dex */
public class MUCOwnerProvider implements InterfaceC0049c {
    /* renamed from: a */
    public final MUCOwner.C5658a m22604a(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        MUCOwner.C5658a c5658a = new MUCOwner.C5658a();
        c5658a.m22588c(xmlPullParser.getAttributeValue("", "jid"));
        boolean z8 = false;
        while (!z8) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals("reason")) {
                    c5658a.m22589d(xmlPullParser.nextText());
                }
            } else if (next == 3 && xmlPullParser.getName().equals("destroy")) {
                z8 = true;
            }
        }
        return c5658a;
    }

    @Override // p008a7.InterfaceC0049c
    /* renamed from: b */
    public AbstractC5586IQ mo180b(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        MUCOwner mUCOwner = new MUCOwner();
        boolean z8 = false;
        while (!z8) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals("item")) {
                    mUCOwner.m22582G(m22605c(xmlPullParser));
                } else if (xmlPullParser.getName().equals("destroy")) {
                    mUCOwner.m22585J(m22604a(xmlPullParser));
                } else {
                    mUCOwner.m22154b(PacketParserUtils.m22252o(xmlPullParser.getName(), xmlPullParser.getNamespace(), xmlPullParser));
                }
            } else if (next == 3 && xmlPullParser.getName().equals(SearchIntents.EXTRA_QUERY)) {
                z8 = true;
            }
        }
        return mUCOwner;
    }

    /* renamed from: c */
    public final MUCOwner.C5659b m22605c(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        MUCOwner.C5659b c5659b = new MUCOwner.C5659b(xmlPullParser.getAttributeValue("", FirebaseAnalytics.Param.AFFILIATION));
        c5659b.m22599i(xmlPullParser.getAttributeValue("", "nick"));
        c5659b.m22601k(xmlPullParser.getAttributeValue("", "role"));
        c5659b.m22598h(xmlPullParser.getAttributeValue("", "jid"));
        boolean z8 = false;
        while (!z8) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals("actor")) {
                    c5659b.m22597g(xmlPullParser.getAttributeValue("", "jid"));
                }
                if (xmlPullParser.getName().equals("reason")) {
                    c5659b.m22600j(xmlPullParser.nextText());
                }
            } else if (next == 3 && xmlPullParser.getName().equals("item")) {
                z8 = true;
            }
        }
        return c5659b;
    }
}
