package org.jivesoftware.smackx.pubsub.provider;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.List;
import java.util.Map;
import org.jivesoftware.smack.packet.InterfaceC5595c;
import org.jivesoftware.smack.provider.EmbeddedExtensionProvider;
import org.jivesoftware.smackx.pubsub.Affiliation;

/* loaded from: classes.dex */
public class AffiliationProvider extends EmbeddedExtensionProvider {
    @Override // org.jivesoftware.smack.provider.EmbeddedExtensionProvider
    /* renamed from: b */
    public InterfaceC5595c mo22169b(String str, String str2, Map<String, String> map, List<? extends InterfaceC5595c> list) {
        return new Affiliation(map.get("node"), Affiliation.Type.valueOf(map.get(FirebaseAnalytics.Param.AFFILIATION)));
    }
}
