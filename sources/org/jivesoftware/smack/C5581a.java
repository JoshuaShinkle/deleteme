package org.jivesoftware.smack;

import java.util.Map;
import java.util.WeakHashMap;
import org.jivesoftware.smack.packet.Registration;
import p222v6.AbstractC6491d;

/* renamed from: org.jivesoftware.smack.a */
/* loaded from: classes.dex */
public class C5581a extends AbstractC6491d {

    /* renamed from: d */
    public static final Map<XMPPConnection, C5581a> f19194d = new WeakHashMap();

    /* renamed from: b */
    public Registration f19195b;

    /* renamed from: c */
    public boolean f19196c;

    public C5581a(XMPPConnection xMPPConnection) {
        super(xMPPConnection);
        this.f19195b = null;
        this.f19196c = false;
        f19194d.put(xMPPConnection, this);
    }

    /* renamed from: c */
    public static synchronized C5581a m22019c(XMPPConnection xMPPConnection) {
        C5581a c5581a;
        c5581a = f19194d.get(xMPPConnection);
        if (c5581a == null) {
            c5581a = new C5581a(xMPPConnection);
        }
        return c5581a;
    }

    /* renamed from: d */
    public void m22020d(boolean z8) {
        this.f19196c = z8;
    }
}
