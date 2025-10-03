package org.jivesoftware.smackx.chatstates.packet;

import org.jivesoftware.smack.packet.InterfaceC5595c;
import org.jivesoftware.smackx.chatstates.ChatState;
import org.xmlpull.v1.XmlPullParser;
import p008a7.InterfaceC0051e;

/* loaded from: classes.dex */
public class ChatStateExtension implements InterfaceC5595c {

    /* renamed from: a */
    public ChatState f19628a;

    public static class Provider implements InterfaceC0051e {
        @Override // p008a7.InterfaceC0051e
        /* renamed from: a */
        public InterfaceC5595c mo181a(XmlPullParser xmlPullParser) {
            ChatState chatStateValueOf;
            try {
                chatStateValueOf = ChatState.valueOf(xmlPullParser.getName());
            } catch (Exception unused) {
                chatStateValueOf = ChatState.active;
            }
            return new ChatStateExtension(chatStateValueOf);
        }
    }

    public ChatStateExtension(ChatState chatState) {
        this.f19628a = chatState;
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    /* renamed from: b */
    public String mo191b() {
        return this.f19628a.name();
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public String mo190a() {
        return "<" + mo191b() + " xmlns=\"" + getNamespace() + "\" />";
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    public String getNamespace() {
        return "http://jabber.org/protocol/chatstates";
    }
}
