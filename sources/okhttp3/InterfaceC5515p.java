package okhttp3;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import kotlin.collections.C5223f;
import p007a6.C0042f;

/* renamed from: okhttp3.p */
/* loaded from: classes2.dex */
public interface InterfaceC5515p {

    /* renamed from: a */
    public static final a f18933a = a.f18935a;

    /* renamed from: b */
    public static final InterfaceC5515p f18934b = new a.C6877a();

    /* renamed from: okhttp3.p$a */
    public static final class a {

        /* renamed from: a */
        public static final /* synthetic */ a f18935a = new a();

        /* renamed from: okhttp3.p$a$a, reason: collision with other inner class name */
        public static final class C6877a implements InterfaceC5515p {
            @Override // okhttp3.InterfaceC5515p
            /* renamed from: a */
            public List<InetAddress> mo21597a(String str) throws UnknownHostException {
                C0042f.m158e(str, "hostname");
                try {
                    InetAddress[] allByName = InetAddress.getAllByName(str);
                    C0042f.m157d(allByName, "getAllByName(hostname)");
                    return C5223f.m20392o(allByName);
                } catch (NullPointerException e9) {
                    UnknownHostException unknownHostException = new UnknownHostException("Broken system behaviour for dns lookup of " + str);
                    unknownHostException.initCause(e9);
                    throw unknownHostException;
                }
            }
        }
    }

    /* renamed from: a */
    List<InetAddress> mo21597a(String str);
}
