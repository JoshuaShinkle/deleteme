package org.jivesoftware.smackx.commands.provider;

import com.google.firebase.messaging.Constants;
import java.io.IOException;
import org.jivesoftware.smack.packet.AbstractC5586IQ;
import org.jivesoftware.smack.packet.InterfaceC5595c;
import org.jivesoftware.smack.util.PacketParserUtils;
import org.jivesoftware.smackx.commands.AdHocCommand;
import org.jivesoftware.smackx.commands.AdHocCommandNote;
import org.jivesoftware.smackx.commands.packet.AdHocCommandData;
import org.jivesoftware.smackx.xdata.provider.DataFormProvider;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import p008a7.InterfaceC0049c;
import p008a7.InterfaceC0051e;
import p009a8.C0055a;

/* loaded from: classes.dex */
public class AdHocCommandDataProvider implements InterfaceC0049c {

    public static class BadActionError implements InterfaceC0051e {
        @Override // p008a7.InterfaceC0051e
        /* renamed from: a */
        public InterfaceC5595c mo181a(XmlPullParser xmlPullParser) {
            return new AdHocCommandData.C5632a(AdHocCommand.SpecificErrorCondition.badAction);
        }
    }

    public static class BadLocaleError implements InterfaceC0051e {
        @Override // p008a7.InterfaceC0051e
        /* renamed from: a */
        public InterfaceC5595c mo181a(XmlPullParser xmlPullParser) {
            return new AdHocCommandData.C5632a(AdHocCommand.SpecificErrorCondition.badLocale);
        }
    }

    public static class BadPayloadError implements InterfaceC0051e {
        @Override // p008a7.InterfaceC0051e
        /* renamed from: a */
        public InterfaceC5595c mo181a(XmlPullParser xmlPullParser) {
            return new AdHocCommandData.C5632a(AdHocCommand.SpecificErrorCondition.badPayload);
        }
    }

    public static class BadSessionIDError implements InterfaceC0051e {
        @Override // p008a7.InterfaceC0051e
        /* renamed from: a */
        public InterfaceC5595c mo181a(XmlPullParser xmlPullParser) {
            return new AdHocCommandData.C5632a(AdHocCommand.SpecificErrorCondition.badSessionid);
        }
    }

    public static class MalformedActionError implements InterfaceC0051e {
        @Override // p008a7.InterfaceC0051e
        /* renamed from: a */
        public InterfaceC5595c mo181a(XmlPullParser xmlPullParser) {
            return new AdHocCommandData.C5632a(AdHocCommand.SpecificErrorCondition.malformedAction);
        }
    }

    public static class SessionExpiredError implements InterfaceC0051e {
        @Override // p008a7.InterfaceC0051e
        /* renamed from: a */
        public InterfaceC5595c mo181a(XmlPullParser xmlPullParser) {
            return new AdHocCommandData.C5632a(AdHocCommand.SpecificErrorCondition.sessionExpired);
        }
    }

    @Override // p008a7.InterfaceC0049c
    /* renamed from: b */
    public AbstractC5586IQ mo180b(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        AdHocCommandData adHocCommandData = new AdHocCommandData();
        DataFormProvider dataFormProvider = new DataFormProvider();
        adHocCommandData.m22480V(xmlPullParser.getAttributeValue("", "sessionid"));
        adHocCommandData.m22479U(xmlPullParser.getAttributeValue("", "node"));
        String attributeValue = xmlPullParser.getAttributeValue("", "status");
        AdHocCommand.Status status = AdHocCommand.Status.executing;
        if (status.toString().equalsIgnoreCase(attributeValue)) {
            adHocCommandData.m22481W(status);
        } else {
            AdHocCommand.Status status2 = AdHocCommand.Status.completed;
            if (status2.toString().equalsIgnoreCase(attributeValue)) {
                adHocCommandData.m22481W(status2);
            } else {
                AdHocCommand.Status status3 = AdHocCommand.Status.canceled;
                if (status3.toString().equalsIgnoreCase(attributeValue)) {
                    adHocCommandData.m22481W(status3);
                }
            }
        }
        String attributeValue2 = xmlPullParser.getAttributeValue("", "action");
        if (attributeValue2 != null) {
            AdHocCommand.Action actionValueOf = AdHocCommand.Action.valueOf(attributeValue2);
            if (actionValueOf == null || actionValueOf.equals(AdHocCommand.Action.unknown)) {
                adHocCommandData.m22474P(AdHocCommand.Action.unknown);
            } else {
                adHocCommandData.m22474P(actionValueOf);
            }
        }
        boolean z8 = false;
        while (!z8) {
            int next = xmlPullParser.next();
            String name = xmlPullParser.getName();
            String namespace = xmlPullParser.getNamespace();
            if (next == 2) {
                if (xmlPullParser.getName().equals("actions")) {
                    String attributeValue3 = xmlPullParser.getAttributeValue("", "execute");
                    if (attributeValue3 != null) {
                        adHocCommandData.m22475Q(AdHocCommand.Action.valueOf(attributeValue3));
                    }
                } else if (xmlPullParser.getName().equals("next")) {
                    adHocCommandData.m22465G(AdHocCommand.Action.next);
                } else if (xmlPullParser.getName().equals("complete")) {
                    adHocCommandData.m22465G(AdHocCommand.Action.complete);
                } else if (xmlPullParser.getName().equals("prev")) {
                    adHocCommandData.m22465G(AdHocCommand.Action.prev);
                } else if (name.equals("x") && namespace.equals("jabber:x:data")) {
                    adHocCommandData.m22476R((C0055a) dataFormProvider.mo181a(xmlPullParser));
                } else if (xmlPullParser.getName().equals("note")) {
                    adHocCommandData.m22466H(new AdHocCommandNote(AdHocCommandNote.Type.valueOf(xmlPullParser.getAttributeValue("", "type")), xmlPullParser.nextText()));
                } else if (xmlPullParser.getName().equals(Constants.IPC_BUNDLE_KEY_SEND_ERROR)) {
                    adHocCommandData.m22164q(PacketParserUtils.m22248k(xmlPullParser));
                }
            } else if (next == 3 && xmlPullParser.getName().equals("command")) {
                z8 = true;
            }
        }
        return adHocCommandData;
    }
}
