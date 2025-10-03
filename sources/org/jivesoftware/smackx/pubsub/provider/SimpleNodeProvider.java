package org.jivesoftware.smackx.pubsub.provider;

import java.util.List;
import java.util.Map;
import org.jivesoftware.smack.packet.InterfaceC5595c;
import org.jivesoftware.smack.provider.EmbeddedExtensionProvider;
import org.jivesoftware.smackx.pubsub.PubSubElementType;
import p232w7.C6554g;

/* loaded from: classes.dex */
public class SimpleNodeProvider extends EmbeddedExtensionProvider {
    @Override // org.jivesoftware.smack.provider.EmbeddedExtensionProvider
    /* renamed from: b */
    public InterfaceC5595c mo22169b(String str, String str2, Map<String, String> map, List<? extends InterfaceC5595c> list) {
        return new C6554g(PubSubElementType.m22685c(str, str2), map.get("node"));
    }
}
