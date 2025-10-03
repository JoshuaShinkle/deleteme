package p100i8;

import org.jsoup.nodes.AbstractC5690g;

/* renamed from: i8.b */
/* loaded from: classes.dex */
public class C5063b {
    /* renamed from: a */
    public static void m19832a(InterfaceC5064c interfaceC5064c, AbstractC5690g abstractC5690g) {
        AbstractC5690g abstractC5690gM22951i = abstractC5690g;
        int i9 = 0;
        while (abstractC5690gM22951i != null) {
            interfaceC5064c.mo19830a(abstractC5690gM22951i, i9);
            if (abstractC5690gM22951i.mo19259j() > 0) {
                abstractC5690gM22951i = abstractC5690gM22951i.m22951i(0);
                i9++;
            } else {
                while (abstractC5690gM22951i.m22956u() == null && i9 > 0) {
                    interfaceC5064c.mo19831b(abstractC5690gM22951i, i9);
                    abstractC5690gM22951i = abstractC5690gM22951i.m22936D();
                    i9--;
                }
                interfaceC5064c.mo19831b(abstractC5690gM22951i, i9);
                if (abstractC5690gM22951i == abstractC5690g) {
                    return;
                } else {
                    abstractC5690gM22951i = abstractC5690gM22951i.m22956u();
                }
            }
        }
    }
}
