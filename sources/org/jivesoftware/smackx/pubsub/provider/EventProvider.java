package org.jivesoftware.smackx.pubsub.provider;

import java.util.List;
import java.util.Map;
import org.jivesoftware.smack.packet.InterfaceC5595c;
import org.jivesoftware.smack.provider.EmbeddedExtensionProvider;
import org.jivesoftware.smackx.pubsub.EventElementType;
import p232w7.C6551d;
import p232w7.C6554g;

/* loaded from: classes.dex */
public class EventProvider extends EmbeddedExtensionProvider {
    @Override // org.jivesoftware.smack.provider.EmbeddedExtensionProvider
    /* renamed from: b */
    public InterfaceC5595c mo22169b(String str, String str2, Map<String, String> map, List<? extends InterfaceC5595c> list) {
        return new C6551d(EventElementType.valueOf(list.get(0).mo191b()), (C6554g) list.get(0));
    }
}
