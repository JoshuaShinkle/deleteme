package p240x6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.jivesoftware.smack.packet.AbstractC5594b;

/* renamed from: x6.a */
/* loaded from: classes.dex */
public class C6576a implements InterfaceC6582g {

    /* renamed from: a */
    public final List<InterfaceC6582g> f22118a;

    public C6576a(InterfaceC6582g... interfaceC6582gArr) {
        if (interfaceC6582gArr == null) {
            throw new IllegalArgumentException("Parameter must not be null.");
        }
        for (InterfaceC6582g interfaceC6582g : interfaceC6582gArr) {
            if (interfaceC6582g == null) {
                throw new IllegalArgumentException("Parameter must not be null.");
            }
        }
        this.f22118a = new ArrayList(Arrays.asList(interfaceC6582gArr));
    }

    @Override // p240x6.InterfaceC6582g
    /* renamed from: a */
    public boolean mo25192a(AbstractC5594b abstractC5594b) {
        Iterator<InterfaceC6582g> it = this.f22118a.iterator();
        while (it.hasNext()) {
            if (!it.next().mo25192a(abstractC5594b)) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        return this.f22118a.toString();
    }
}
