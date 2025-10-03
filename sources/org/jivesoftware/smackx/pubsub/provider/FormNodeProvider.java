package org.jivesoftware.smackx.pubsub.provider;

import java.util.List;
import java.util.Map;
import org.jivesoftware.smack.packet.InterfaceC5595c;
import org.jivesoftware.smack.provider.EmbeddedExtensionProvider;
import org.jivesoftware.smackx.pubsub.FormNodeType;
import p009a8.C0055a;
import p232w7.C6552e;
import p259z7.C6837a;

/* loaded from: classes.dex */
public class FormNodeProvider extends EmbeddedExtensionProvider {
    @Override // org.jivesoftware.smack.provider.EmbeddedExtensionProvider
    /* renamed from: b */
    public InterfaceC5595c mo22169b(String str, String str2, Map<String, String> map, List<? extends InterfaceC5595c> list) {
        return new C6552e(FormNodeType.m22681b(str, str2), map.get("node"), new C6837a((C0055a) list.iterator().next()));
    }
}
