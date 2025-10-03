package com.cyberlink.mediacodec;

/* renamed from: com.cyberlink.mediacodec.b */
/* loaded from: classes.dex */
public class C1245b<ObjectType> extends C1247d<ObjectType> {

    /* renamed from: f */
    public static final String f6142f = "b";

    public C1245b(String str) {
        this(str, 4);
    }

    /* renamed from: b */
    private void m5536b(String str, Object... objArr) {
    }

    @Override // com.cyberlink.mediacodec.C1247d
    /* renamed from: a */
    public synchronized boolean mo5537a(ObjectType objecttype) {
        int iMo5530a;
        if (this.f6151c) {
            m5536b("Add at EOS", new Object[0]);
            return false;
        }
        if (!m5549d()) {
            m5536b("Add while no vacancy", new Object[0]);
            return false;
        }
        InterfaceC1244a interfaceC1244a = (InterfaceC1244a) objecttype;
        if (interfaceC1244a == null) {
            m5536b("Add a non-adjustable object", new Object[0]);
            this.f6152d.add(objecttype);
            return true;
        }
        int size = this.f6152d.size();
        while (true) {
            if (size <= 0) {
                break;
            }
            InterfaceC1244a interfaceC1244a2 = (InterfaceC1244a) this.f6152d.get(size - 1);
            if (interfaceC1244a2 != null && (iMo5530a = interfaceC1244a.mo5530a(interfaceC1244a2)) <= 0) {
                if (iMo5530a == 0) {
                    interfaceC1244a.mo5531b(interfaceC1244a2);
                    break;
                }
                size--;
            }
        }
        this.f6152d.add(size, objecttype);
        return true;
    }

    @Override // com.cyberlink.mediacodec.C1247d
    /* renamed from: e */
    public synchronized boolean mo5538e() {
        if (this.f6151c) {
            return this.f6152d.size() > 0;
        }
        return this.f6152d.size() > 1;
    }

    public C1245b(String str, int i9) {
        super(str, i9);
        if (str == null) {
            this.f6149a = f6142f;
            return;
        }
        this.f6149a = f6142f + "(" + str + ")";
    }
}
