package okhttp3.internal.connection;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.collections.C5225h;
import kotlin.collections.C5226i;
import kotlin.collections.C5231n;
import okhttp3.AbstractC5516q;
import okhttp3.C5482a;
import okhttp3.C5485b0;
import okhttp3.C5518s;
import okhttp3.InterfaceC5488e;
import p007a6.C0040d;
import p007a6.C0042f;
import p098i6.C5057d;

/* renamed from: okhttp3.internal.connection.h */
/* loaded from: classes.dex */
public final class C5502h {

    /* renamed from: i */
    public static final a f18739i = new a(null);

    /* renamed from: a */
    public final C5482a f18740a;

    /* renamed from: b */
    public final C5501g f18741b;

    /* renamed from: c */
    public final InterfaceC5488e f18742c;

    /* renamed from: d */
    public final AbstractC5516q f18743d;

    /* renamed from: e */
    public List<? extends Proxy> f18744e;

    /* renamed from: f */
    public int f18745f;

    /* renamed from: g */
    public List<? extends InetSocketAddress> f18746g;

    /* renamed from: h */
    public final List<C5485b0> f18747h;

    /* renamed from: okhttp3.internal.connection.h$a */
    public static final class a {
        public a() {
        }

        public /* synthetic */ a(C0040d c0040d) {
            this();
        }

        /* renamed from: a */
        public final String m21389a(InetSocketAddress inetSocketAddress) {
            C0042f.m158e(inetSocketAddress, "<this>");
            InetAddress address = inetSocketAddress.getAddress();
            if (address == null) {
                String hostName = inetSocketAddress.getHostName();
                C0042f.m157d(hostName, "hostName");
                return hostName;
            }
            String hostAddress = address.getHostAddress();
            C0042f.m157d(hostAddress, "address.hostAddress");
            return hostAddress;
        }
    }

    /* renamed from: okhttp3.internal.connection.h$b */
    public static final class b {

        /* renamed from: a */
        public final List<C5485b0> f18748a;

        /* renamed from: b */
        public int f18749b;

        public b(List<C5485b0> list) {
            C0042f.m158e(list, "routes");
            this.f18748a = list;
        }

        /* renamed from: a */
        public final List<C5485b0> m21390a() {
            return this.f18748a;
        }

        /* renamed from: b */
        public final boolean m21391b() {
            return this.f18749b < this.f18748a.size();
        }

        /* renamed from: c */
        public final C5485b0 m21392c() {
            if (!m21391b()) {
                throw new NoSuchElementException();
            }
            List<C5485b0> list = this.f18748a;
            int i9 = this.f18749b;
            this.f18749b = i9 + 1;
            return list.get(i9);
        }
    }

    public C5502h(C5482a c5482a, C5501g c5501g, InterfaceC5488e interfaceC5488e, AbstractC5516q abstractC5516q) {
        C0042f.m158e(c5482a, "address");
        C0042f.m158e(c5501g, "routeDatabase");
        C0042f.m158e(interfaceC5488e, "call");
        C0042f.m158e(abstractC5516q, "eventListener");
        this.f18740a = c5482a;
        this.f18741b = c5501g;
        this.f18742c = interfaceC5488e;
        this.f18743d = abstractC5516q;
        this.f18744e = C5226i.m20400f();
        this.f18746g = C5226i.m20400f();
        this.f18747h = new ArrayList();
        m21388f(c5482a.m21228l(), c5482a.m21223g());
    }

    /* renamed from: g */
    public static final List<Proxy> m21382g(Proxy proxy, C5518s c5518s, C5502h c5502h) {
        if (proxy != null) {
            return C5225h.m20396b(proxy);
        }
        URI uriM21662q = c5518s.m21662q();
        if (uriM21662q.getHost() == null) {
            return C5057d.m19809w(Proxy.NO_PROXY);
        }
        List<Proxy> listSelect = c5502h.f18740a.m21225i().select(uriM21662q);
        List<Proxy> list = listSelect;
        if (list == null || list.isEmpty()) {
            return C5057d.m19809w(Proxy.NO_PROXY);
        }
        C0042f.m157d(listSelect, "proxiesOrNull");
        return C5057d.m19779S(listSelect);
    }

    /* renamed from: a */
    public final boolean m21383a() {
        return m21384b() || (this.f18747h.isEmpty() ^ true);
    }

    /* renamed from: b */
    public final boolean m21384b() {
        return this.f18745f < this.f18744e.size();
    }

    /* renamed from: c */
    public final b m21385c() {
        if (!m21383a()) {
            throw new NoSuchElementException();
        }
        ArrayList arrayList = new ArrayList();
        while (m21384b()) {
            Proxy proxyM21386d = m21386d();
            Iterator<? extends InetSocketAddress> it = this.f18746g.iterator();
            while (it.hasNext()) {
                C5485b0 c5485b0 = new C5485b0(this.f18740a, proxyM21386d, it.next());
                if (this.f18741b.m21381c(c5485b0)) {
                    this.f18747h.add(c5485b0);
                } else {
                    arrayList.add(c5485b0);
                }
            }
            if (!arrayList.isEmpty()) {
                break;
            }
        }
        if (arrayList.isEmpty()) {
            C5231n.m20410p(arrayList, this.f18747h);
            this.f18747h.clear();
        }
        return new b(arrayList);
    }

    /* renamed from: d */
    public final Proxy m21386d() throws SocketException, UnknownHostException {
        if (m21384b()) {
            List<? extends Proxy> list = this.f18744e;
            int i9 = this.f18745f;
            this.f18745f = i9 + 1;
            Proxy proxy = list.get(i9);
            m21387e(proxy);
            return proxy;
        }
        throw new SocketException("No route to " + this.f18740a.m21228l().m21653h() + "; exhausted proxy configurations: " + this.f18744e);
    }

    /* renamed from: e */
    public final void m21387e(Proxy proxy) throws SocketException, UnknownHostException {
        String strM21653h;
        int iM21657l;
        List<InetAddress> listMo21597a;
        ArrayList arrayList = new ArrayList();
        this.f18746g = arrayList;
        if (proxy.type() == Proxy.Type.DIRECT || proxy.type() == Proxy.Type.SOCKS) {
            strM21653h = this.f18740a.m21228l().m21653h();
            iM21657l = this.f18740a.m21228l().m21657l();
        } else {
            SocketAddress socketAddressAddress = proxy.address();
            if (!(socketAddressAddress instanceof InetSocketAddress)) {
                throw new IllegalArgumentException(("Proxy.address() is not an InetSocketAddress: " + socketAddressAddress.getClass()).toString());
            }
            a aVar = f18739i;
            C0042f.m157d(socketAddressAddress, "proxyAddress");
            InetSocketAddress inetSocketAddress = (InetSocketAddress) socketAddressAddress;
            strM21653h = aVar.m21389a(inetSocketAddress);
            iM21657l = inetSocketAddress.getPort();
        }
        boolean z8 = false;
        if (1 <= iM21657l && iM21657l < 65536) {
            z8 = true;
        }
        if (!z8) {
            throw new SocketException("No route to " + strM21653h + ':' + iM21657l + "; port is out of range");
        }
        if (proxy.type() == Proxy.Type.SOCKS) {
            arrayList.add(InetSocketAddress.createUnresolved(strM21653h, iM21657l));
            return;
        }
        if (C5057d.m19795i(strM21653h)) {
            listMo21597a = C5225h.m20396b(InetAddress.getByName(strM21653h));
        } else {
            this.f18743d.m21612m(this.f18742c, strM21653h);
            listMo21597a = this.f18740a.m21219c().mo21597a(strM21653h);
            if (listMo21597a.isEmpty()) {
                throw new UnknownHostException(this.f18740a.m21219c() + " returned no addresses for " + strM21653h);
            }
            this.f18743d.m21611l(this.f18742c, strM21653h, listMo21597a);
        }
        Iterator<InetAddress> it = listMo21597a.iterator();
        while (it.hasNext()) {
            arrayList.add(new InetSocketAddress(it.next(), iM21657l));
        }
    }

    /* renamed from: f */
    public final void m21388f(C5518s c5518s, Proxy proxy) {
        this.f18743d.m21614o(this.f18742c, c5518s);
        List<Proxy> listM21382g = m21382g(proxy, c5518s, this);
        this.f18744e = listM21382g;
        this.f18745f = 0;
        this.f18743d.m21613n(this.f18742c, c5518s, listM21382g);
    }
}
