package org.jivesoftware.smackx.delay.provider;

import com.google.firebase.messaging.Constants;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import org.jivesoftware.smack.packet.InterfaceC5595c;
import org.jivesoftware.smack.util.XmppDateTime;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import p008a7.InterfaceC0051e;
import p130l7.C5300b;

/* loaded from: classes.dex */
public class DelayInformationProvider implements InterfaceC0051e {
    @Override // p008a7.InterfaceC0051e
    /* renamed from: a */
    public InterfaceC5595c mo181a(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        Date date;
        try {
            date = XmppDateTime.m22271h(xmlPullParser.getAttributeValue("", "stamp"));
        } catch (ParseException unused) {
            date = new Date(0L);
        }
        C5300b c5300b = new C5300b(date);
        c5300b.mo20687f(xmlPullParser.getAttributeValue("", Constants.MessagePayloadKeys.FROM));
        String strNextText = xmlPullParser.nextText();
        if ("".equals(strNextText)) {
            strNextText = null;
        }
        c5300b.mo20688g(strNextText);
        return c5300b;
    }
}
