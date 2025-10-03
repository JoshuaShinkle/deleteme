package p240x6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.jivesoftware.smack.packet.AbstractC5594b;

/* renamed from: x6.e */
/* loaded from: classes.dex */
public class C6580e implements InterfaceC6582g {

    /* renamed from: a */
    public final List<InterfaceC6582g> f22129a;

    public C6580e() {
        this.f22129a = new ArrayList();
    }

    @Override // p240x6.InterfaceC6582g
    /* renamed from: a */
    public boolean mo25192a(AbstractC5594b abstractC5594b) {
        Iterator<InterfaceC6582g> it = this.f22129a.iterator();
        while (it.hasNext()) {
            if (it.next().mo25192a(abstractC5594b)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: b */
    public void m25195b(InterfaceC6582g interfaceC6582g) {
        if (interfaceC6582g == null) {
            throw new IllegalArgumentException("Parameter must not be null.");
        }
        this.f22129a.add(interfaceC6582g);
    }

    public String toString() {
        return this.f22129a.toString();
    }

    public C6580e(InterfaceC6582g... interfaceC6582gArr) {
        if (interfaceC6582gArr != null) {
            for (InterfaceC6582g interfaceC6582g : interfaceC6582gArr) {
                if (interfaceC6582g == null) {
                    throw new IllegalArgumentException("Parameter must not be null.");
                }
            }
            this.f22129a = new ArrayList(Arrays.asList(interfaceC6582gArr));
            return;
        }
        throw new IllegalArgumentException("Parameter must not be null.");
    }
}
