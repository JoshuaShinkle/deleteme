package org.jivesoftware.smackx.amp.provider;

import com.google.firebase.messaging.Constants;
import java.io.IOException;
import java.util.logging.Logger;
import org.jivesoftware.smack.packet.InterfaceC5595c;
import org.jivesoftware.smackx.amp.AMPDeliverCondition;
import org.jivesoftware.smackx.amp.AMPMatchResourceCondition;
import org.jivesoftware.smackx.amp.packet.AMPExtension;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import p008a7.InterfaceC0051e;
import p059e7.C4768a;

/* loaded from: classes.dex */
public class AMPExtensionProvider implements InterfaceC0051e {

    /* renamed from: a */
    public static final Logger f19559a = Logger.getLogger(AMPExtensionProvider.class.getName());

    @Override // p008a7.InterfaceC0051e
    /* renamed from: a */
    public InterfaceC5595c mo181a(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        AMPExtension.Status statusValueOf;
        AMPExtension.Action actionValueOf;
        String attributeValue = xmlPullParser.getAttributeValue(null, Constants.MessagePayloadKeys.FROM);
        String attributeValue2 = xmlPullParser.getAttributeValue(null, "to");
        String attributeValue3 = xmlPullParser.getAttributeValue(null, "status");
        if (attributeValue3 != null) {
            try {
                statusValueOf = AMPExtension.Status.valueOf(attributeValue3);
            } catch (IllegalArgumentException unused) {
                f19559a.severe("Found invalid amp status " + attributeValue3);
            }
        } else {
            statusValueOf = null;
        }
        AMPExtension aMPExtension = new AMPExtension(attributeValue, attributeValue2, statusValueOf);
        String attributeValue4 = xmlPullParser.getAttributeValue(null, "per-hop");
        if (attributeValue4 != null) {
            aMPExtension.m22375e(Boolean.parseBoolean(attributeValue4));
        }
        boolean z8 = false;
        while (!z8) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals("rule")) {
                    String attributeValue5 = xmlPullParser.getAttributeValue(null, "action");
                    AMPExtension.InterfaceC5619a interfaceC5619aM22379b = m22379b(xmlPullParser.getAttributeValue(null, "condition"), xmlPullParser.getAttributeValue(null, "value"));
                    if (attributeValue5 != null) {
                        try {
                            actionValueOf = AMPExtension.Action.valueOf(attributeValue5);
                        } catch (IllegalArgumentException unused2) {
                            f19559a.severe("Found invalid rule action value " + attributeValue5);
                        }
                        if (actionValueOf != null || interfaceC5619aM22379b == null) {
                            f19559a.severe("Rule is skipped because either it's action or it's condition is invalid");
                        } else {
                            aMPExtension.m22373c(new AMPExtension.C5620b(actionValueOf, interfaceC5619aM22379b));
                        }
                    } else {
                        actionValueOf = null;
                        if (actionValueOf != null) {
                        }
                        f19559a.severe("Rule is skipped because either it's action or it's condition is invalid");
                    }
                }
            } else if (next == 3 && xmlPullParser.getName().equals("amp")) {
                z8 = true;
            }
        }
        return aMPExtension;
    }

    /* renamed from: b */
    public final AMPExtension.InterfaceC5619a m22379b(String str, String str2) {
        if (str == null || str2 == null) {
            f19559a.severe("Can't create rule condition from null name and/or value");
            return null;
        }
        if ("deliver".equals(str)) {
            try {
                return new AMPDeliverCondition(AMPDeliverCondition.Value.valueOf(str2));
            } catch (IllegalArgumentException unused) {
                f19559a.severe("Found invalid rule delivery condition value " + str2);
                return null;
            }
        }
        if ("expire-at".equals(str)) {
            return new C4768a(str2);
        }
        if (!"match-resource".equals(str)) {
            f19559a.severe("Found unknown rule condition name " + str);
            return null;
        }
        try {
            return new AMPMatchResourceCondition(AMPMatchResourceCondition.Value.valueOf(str2));
        } catch (IllegalArgumentException unused2) {
            f19559a.severe("Found invalid rule match-resource condition value " + str2);
            return null;
        }
    }
}
