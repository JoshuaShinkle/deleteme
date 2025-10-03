package p108j6;

import com.google.common.net.HttpHeaders;
import java.net.Authenticator;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.SocketAddress;
import java.util.List;
import kotlin.collections.C5234q;
import kotlin.text.C5255l;
import okhttp3.C5482a;
import okhttp3.C5485b0;
import okhttp3.C5490g;
import okhttp3.C5513n;
import okhttp3.C5518s;
import okhttp3.C5523x;
import okhttp3.C5525z;
import okhttp3.InterfaceC5484b;
import okhttp3.InterfaceC5515p;
import p007a6.C0040d;
import p007a6.C0042f;

/* renamed from: j6.a */
/* loaded from: classes2.dex */
public final class C5104a implements InterfaceC5484b {

    /* renamed from: d */
    public final InterfaceC5515p f17565d;

    /* renamed from: j6.a$a */
    public /* synthetic */ class a {

        /* renamed from: a */
        public static final /* synthetic */ int[] f17566a;

        static {
            int[] iArr = new int[Proxy.Type.values().length];
            try {
                iArr[Proxy.Type.DIRECT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            f17566a = iArr;
        }
    }

    public C5104a(InterfaceC5515p interfaceC5515p) {
        C0042f.m158e(interfaceC5515p, "defaultDns");
        this.f17565d = interfaceC5515p;
    }

    @Override // okhttp3.InterfaceC5484b
    /* renamed from: a */
    public C5523x mo19952a(C5485b0 c5485b0, C5525z c5525z) {
        Proxy proxyM21236b;
        InterfaceC5515p interfaceC5515pM21219c;
        PasswordAuthentication passwordAuthenticationRequestPasswordAuthentication;
        C5482a c5482aM21235a;
        C0042f.m158e(c5525z, "response");
        List<C5490g> listM21852w = c5525z.m21852w();
        C5523x c5523xM21847K = c5525z.m21847K();
        C5518s c5518sM21811i = c5523xM21847K.m21811i();
        boolean z8 = c5525z.m21853x() == 407;
        if (c5485b0 == null || (proxyM21236b = c5485b0.m21236b()) == null) {
            proxyM21236b = Proxy.NO_PROXY;
        }
        for (C5490g c5490g : listM21852w) {
            if (C5255l.m20513l("Basic", c5490g.m21259c(), true)) {
                if (c5485b0 == null || (c5482aM21235a = c5485b0.m21235a()) == null || (interfaceC5515pM21219c = c5482aM21235a.m21219c()) == null) {
                    interfaceC5515pM21219c = this.f17565d;
                }
                if (z8) {
                    SocketAddress socketAddressAddress = proxyM21236b.address();
                    C0042f.m156c(socketAddressAddress, "null cannot be cast to non-null type java.net.InetSocketAddress");
                    InetSocketAddress inetSocketAddress = (InetSocketAddress) socketAddressAddress;
                    String hostName = inetSocketAddress.getHostName();
                    C0042f.m157d(proxyM21236b, "proxy");
                    passwordAuthenticationRequestPasswordAuthentication = Authenticator.requestPasswordAuthentication(hostName, m19953b(proxyM21236b, c5518sM21811i, interfaceC5515pM21219c), inetSocketAddress.getPort(), c5518sM21811i.m21661p(), c5490g.m21258b(), c5490g.m21259c(), c5518sM21811i.m21663r(), Authenticator.RequestorType.PROXY);
                } else {
                    String strM21653h = c5518sM21811i.m21653h();
                    C0042f.m157d(proxyM21236b, "proxy");
                    passwordAuthenticationRequestPasswordAuthentication = Authenticator.requestPasswordAuthentication(strM21653h, m19953b(proxyM21236b, c5518sM21811i, interfaceC5515pM21219c), c5518sM21811i.m21657l(), c5518sM21811i.m21661p(), c5490g.m21258b(), c5490g.m21259c(), c5518sM21811i.m21663r(), Authenticator.RequestorType.SERVER);
                }
                if (passwordAuthenticationRequestPasswordAuthentication != null) {
                    String str = z8 ? HttpHeaders.PROXY_AUTHORIZATION : HttpHeaders.AUTHORIZATION;
                    String userName = passwordAuthenticationRequestPasswordAuthentication.getUserName();
                    C0042f.m157d(userName, "auth.userName");
                    char[] password = passwordAuthenticationRequestPasswordAuthentication.getPassword();
                    C0042f.m157d(password, "auth.password");
                    return c5523xM21847K.m21810h().m21815d(str, C5513n.m21587a(userName, new String(password), c5490g.m21257a())).m21813b();
                }
            }
        }
        return null;
    }

    /* renamed from: b */
    public final InetAddress m19953b(Proxy proxy, C5518s c5518s, InterfaceC5515p interfaceC5515p) {
        Proxy.Type type = proxy.type();
        if ((type == null ? -1 : a.f17566a[type.ordinal()]) == 1) {
            return (InetAddress) C5234q.m20425u(interfaceC5515p.mo21597a(c5518s.m21653h()));
        }
        SocketAddress socketAddressAddress = proxy.address();
        C0042f.m156c(socketAddressAddress, "null cannot be cast to non-null type java.net.InetSocketAddress");
        InetAddress address = ((InetSocketAddress) socketAddressAddress).getAddress();
        C0042f.m157d(address, "address() as InetSocketAddress).address");
        return address;
    }

    public /* synthetic */ C5104a(InterfaceC5515p interfaceC5515p, int i9, C0040d c0040d) {
        this((i9 & 1) != 0 ? InterfaceC5515p.f18934b : interfaceC5515p);
    }
}
