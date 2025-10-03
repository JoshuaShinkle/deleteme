package org.jivesoftware.smack.initializer;

import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public class VmArgInitializer extends UrlInitializer {
    @Override // org.jivesoftware.smack.initializer.UrlInitializer, p249y6.InterfaceC6792a
    /* renamed from: a */
    public List<Exception> mo22045a() {
        if (m22049e() != null) {
            super.mo22045a();
        }
        return Collections.emptyList();
    }

    /* renamed from: e */
    public String m22049e() {
        return System.getProperty("smack.provider.file");
    }
}
