package org.jivesoftware.smackx.pubsub.provider;

import java.util.List;
import java.util.Map;
import org.jivesoftware.smack.packet.InterfaceC5595c;
import org.jivesoftware.smack.provider.EmbeddedExtensionProvider;
import p009a8.C0055a;
import p232w7.C6549b;
import p232w7.C6550c;

/* loaded from: classes.dex */
public class ConfigEventProvider extends EmbeddedExtensionProvider {
    @Override // org.jivesoftware.smack.provider.EmbeddedExtensionProvider
    /* renamed from: b */
    public InterfaceC5595c mo22169b(String str, String str2, Map<String, String> map, List<? extends InterfaceC5595c> list) {
        return list.size() == 0 ? new C6549b(map.get("node")) : new C6549b(map.get("node"), new C6550c((C0055a) list.iterator().next()));
    }
}
