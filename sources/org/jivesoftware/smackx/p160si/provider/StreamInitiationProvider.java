package org.jivesoftware.smackx.p160si.provider;

import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.packet.AbstractC5586IQ;
import org.jivesoftware.smack.util.XmppDateTime;
import org.jivesoftware.smackx.p160si.packet.StreamInitiation;
import org.jivesoftware.smackx.xdata.provider.DataFormProvider;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import p008a7.InterfaceC0049c;
import p009a8.C0055a;

/* loaded from: classes.dex */
public class StreamInitiationProvider implements InterfaceC0049c {

    /* renamed from: a */
    public static final Logger f19907a = Logger.getLogger(StreamInitiationProvider.class.getName());

    /* JADX WARN: Removed duplicated region for block: B:40:0x0103  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00ea A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // p008a7.InterfaceC0049c
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public AbstractC5586IQ mo180b(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException, NumberFormatException {
        String str;
        XmlPullParser xmlPullParser2 = xmlPullParser;
        String str2 = "";
        String attributeValue = xmlPullParser2.getAttributeValue("", TtmlNode.ATTR_ID);
        String attributeValue2 = xmlPullParser2.getAttributeValue("", "mime-type");
        StreamInitiation streamInitiation = new StreamInitiation();
        DataFormProvider dataFormProvider = new DataFormProvider();
        boolean z8 = false;
        C0055a c0055a = null;
        String attributeValue3 = null;
        String attributeValue4 = null;
        String attributeValue5 = null;
        String attributeValue6 = null;
        String strNextText = null;
        boolean z9 = false;
        while (!z9) {
            int next = xmlPullParser.next();
            String name = xmlPullParser.getName();
            boolean z10 = z9;
            String namespace = xmlPullParser.getNamespace();
            C0055a c0055a2 = c0055a;
            String str3 = attributeValue2;
            if (next == 2) {
                if (name.equals("file")) {
                    attributeValue4 = xmlPullParser2.getAttributeValue(str2, AppMeasurementSdk.ConditionalUserProperty.NAME);
                    attributeValue3 = xmlPullParser2.getAttributeValue(str2, "size");
                    attributeValue5 = xmlPullParser2.getAttributeValue(str2, "hash");
                    attributeValue6 = xmlPullParser2.getAttributeValue(str2, "date");
                } else if (name.equals("desc")) {
                    strNextText = xmlPullParser.nextText();
                } else {
                    if (name.equals("range")) {
                        z9 = z10;
                        c0055a = c0055a2;
                        z8 = true;
                    } else {
                        if (name.equals("x") && namespace.equals("jabber:x:data")) {
                            c0055a = (C0055a) dataFormProvider.mo181a(xmlPullParser2);
                            z9 = z10;
                        }
                        str = str2;
                    }
                    attributeValue2 = str3;
                }
                z9 = z10;
                c0055a = c0055a2;
                attributeValue2 = str3;
            } else {
                if (next != 3) {
                    str = str2;
                } else if (name.equals("si")) {
                    c0055a = c0055a2;
                    z9 = true;
                } else if (name.equals("file")) {
                    String str4 = str2;
                    long j9 = 0;
                    if (attributeValue3 == null || attributeValue3.trim().length() == 0) {
                        str = str4;
                        Date date = new Date();
                        if (attributeValue6 != null) {
                            try {
                                date = XmppDateTime.m22271h(attributeValue6);
                            } catch (ParseException unused) {
                            }
                        }
                        StreamInitiation.C5670b c5670b = new StreamInitiation.C5670b(attributeValue4, j9);
                        c5670b.m22730k(attributeValue5);
                        c5670b.m22728i(date);
                        c5670b.m22729j(strNextText);
                        c5670b.m22731l(z8);
                        streamInitiation.m22718K(c5670b);
                    } else {
                        try {
                            j9 = Long.parseLong(attributeValue3);
                            str = str4;
                        } catch (NumberFormatException e9) {
                            Logger logger = f19907a;
                            Level level = Level.SEVERE;
                            StringBuilder sb = new StringBuilder();
                            str = str4;
                            sb.append("Failed to parse file size from ");
                            sb.append(0L);
                            logger.log(level, sb.toString(), (Throwable) e9);
                        }
                        Date date2 = new Date();
                        if (attributeValue6 != null) {
                        }
                        StreamInitiation.C5670b c5670b2 = new StreamInitiation.C5670b(attributeValue4, j9);
                        c5670b2.m22730k(attributeValue5);
                        c5670b2.m22728i(date2);
                        c5670b2.m22729j(strNextText);
                        c5670b2.m22731l(z8);
                        streamInitiation.m22718K(c5670b2);
                    }
                }
                attributeValue2 = str3;
            }
            xmlPullParser2 = xmlPullParser;
            z9 = z10;
            c0055a = c0055a2;
            str2 = str;
            attributeValue2 = str3;
        }
        streamInitiation.m22720M(attributeValue);
        streamInitiation.m22719L(attributeValue2);
        streamInitiation.m22717J(c0055a);
        return streamInitiation;
    }
}
