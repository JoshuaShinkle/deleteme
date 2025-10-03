package org.jivesoftware.smackx.shim.provider;

import java.util.List;
import java.util.Map;
import org.jivesoftware.smack.packet.InterfaceC5595c;
import org.jivesoftware.smack.provider.EmbeddedExtensionProvider;
import p250y7.C6794b;

/* loaded from: classes.dex */
public class HeadersProvider extends EmbeddedExtensionProvider {
    @Override // org.jivesoftware.smack.provider.EmbeddedExtensionProvider
    /* renamed from: b */
    public InterfaceC5595c mo22169b(String str, String str2, Map<String, String> map, List<? extends InterfaceC5595c> list) {
        return new C6794b(list);
    }
}
