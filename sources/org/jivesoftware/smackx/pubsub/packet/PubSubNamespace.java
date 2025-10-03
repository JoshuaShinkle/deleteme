package org.jivesoftware.smackx.pubsub.packet;

import java.util.Locale;

/* loaded from: classes.dex */
public enum PubSubNamespace {
    BASIC(null),
    ERROR("errors"),
    EVENT("event"),
    OWNER("owner");

    private String fragment;

    PubSubNamespace(String str) {
        this.fragment = str;
    }

    /* renamed from: b */
    public static PubSubNamespace m22694b(String str) {
        return str.lastIndexOf(35) != -1 ? valueOf(str.substring(str.lastIndexOf(35) + 1).toUpperCase(Locale.US)) : BASIC;
    }

    /* renamed from: a */
    public String m22695a() {
        if (this.fragment == null) {
            return "http://jabber.org/protocol/pubsub";
        }
        return "http://jabber.org/protocol/pubsub#" + this.fragment;
    }
}
