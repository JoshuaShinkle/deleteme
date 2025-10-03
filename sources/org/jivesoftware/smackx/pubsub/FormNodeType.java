package org.jivesoftware.smackx.pubsub;

import java.util.Locale;
import org.jivesoftware.smackx.pubsub.packet.PubSubNamespace;

/* loaded from: classes.dex */
public enum FormNodeType {
    CONFIGURE_OWNER,
    CONFIGURE,
    OPTIONS,
    DEFAULT;

    /* renamed from: b */
    public static FormNodeType m22681b(String str, String str2) {
        return ("configure".equals(str) && PubSubNamespace.OWNER.m22695a().equals(str2)) ? CONFIGURE_OWNER : valueOf(str.toUpperCase(Locale.US));
    }

    /* renamed from: a */
    public PubSubElementType m22682a() {
        return PubSubElementType.valueOf(toString());
    }
}
