package p177q6;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import javax.net.ssl.SSLSocket;
import kotlin.text.C5246c;
import kotlin.text.C5255l;
import okhttp3.Protocol;
import org.apache.commons.lang3.ClassUtils;
import p007a6.C0040d;
import p007a6.C0042f;
import p168p6.C6105b;
import p168p6.C6113j;
import p177q6.C6174l;

/* renamed from: q6.h */
/* loaded from: classes.dex */
public class C6170h implements InterfaceC6175m {

    /* renamed from: f */
    public static final a f20806f;

    /* renamed from: g */
    public static final C6174l.a f20807g;

    /* renamed from: a */
    public final Class<? super SSLSocket> f20808a;

    /* renamed from: b */
    public final Method f20809b;

    /* renamed from: c */
    public final Method f20810c;

    /* renamed from: d */
    public final Method f20811d;

    /* renamed from: e */
    public final Method f20812e;

    /* renamed from: q6.h$a */
    public static final class a {

        /* renamed from: q6.h$a$a, reason: collision with other inner class name */
        public static final class C6882a implements C6174l.a {

            /* renamed from: a */
            public final /* synthetic */ String f20813a;

            public C6882a(String str) {
                this.f20813a = str;
            }

            @Override // p177q6.C6174l.a
            /* renamed from: a */
            public boolean mo23636a(SSLSocket sSLSocket) {
                C0042f.m158e(sSLSocket, "sslSocket");
                String name = sSLSocket.getClass().getName();
                C0042f.m157d(name, "sslSocket.javaClass.name");
                return C5255l.m20525x(name, this.f20813a + ClassUtils.PACKAGE_SEPARATOR_CHAR, false, 2, null);
            }

            @Override // p177q6.C6174l.a
            /* renamed from: b */
            public InterfaceC6175m mo23637b(SSLSocket sSLSocket) {
                C0042f.m158e(sSLSocket, "sslSocket");
                return C6170h.f20806f.m23633b(sSLSocket.getClass());
            }
        }

        public a() {
        }

        public /* synthetic */ a(C0040d c0040d) {
            this();
        }

        /* renamed from: b */
        public final C6170h m23633b(Class<? super SSLSocket> cls) {
            Class<? super SSLSocket> superclass = cls;
            while (superclass != null && !C0042f.m154a(superclass.getSimpleName(), "OpenSSLSocketImpl")) {
                superclass = superclass.getSuperclass();
                if (superclass == null) {
                    throw new AssertionError("No OpenSSLSocketImpl superclass of socket of type " + cls);
                }
            }
            C0042f.m155b(superclass);
            return new C6170h(superclass);
        }

        /* renamed from: c */
        public final C6174l.a m23634c(String str) {
            C0042f.m158e(str, "packageName");
            return new C6882a(str);
        }

        /* renamed from: d */
        public final C6174l.a m23635d() {
            return C6170h.f20807g;
        }
    }

    static {
        a aVar = new a(null);
        f20806f = aVar;
        f20807g = aVar.m23634c("com.google.android.gms.org.conscrypt");
    }

    public C6170h(Class<? super SSLSocket> cls) throws NoSuchMethodException, SecurityException {
        C0042f.m158e(cls, "sslSocketClass");
        this.f20808a = cls;
        Method declaredMethod = cls.getDeclaredMethod("setUseSessionTickets", Boolean.TYPE);
        C0042f.m157d(declaredMethod, "sslSocketClass.getDeclar…:class.javaPrimitiveType)");
        this.f20809b = declaredMethod;
        this.f20810c = cls.getMethod("setHostname", String.class);
        this.f20811d = cls.getMethod("getAlpnSelectedProtocol", new Class[0]);
        this.f20812e = cls.getMethod("setAlpnProtocols", byte[].class);
    }

    @Override // p177q6.InterfaceC6175m
    /* renamed from: a */
    public boolean mo23617a(SSLSocket sSLSocket) {
        C0042f.m158e(sSLSocket, "sslSocket");
        return this.f20808a.isInstance(sSLSocket);
    }

    @Override // p177q6.InterfaceC6175m
    /* renamed from: b */
    public boolean mo23618b() {
        return C6105b.f20718f.m23414b();
    }

    @Override // p177q6.InterfaceC6175m
    /* renamed from: c */
    public String mo23619c(SSLSocket sSLSocket) {
        C0042f.m158e(sSLSocket, "sslSocket");
        if (!mo23617a(sSLSocket)) {
            return null;
        }
        try {
            byte[] bArr = (byte[]) this.f20811d.invoke(sSLSocket, new Object[0]);
            if (bArr != null) {
                return new String(bArr, C5246c.f17846b);
            }
            return null;
        } catch (IllegalAccessException e9) {
            throw new AssertionError(e9);
        } catch (InvocationTargetException e10) {
            Throwable cause = e10.getCause();
            if ((cause instanceof NullPointerException) && C0042f.m154a(((NullPointerException) cause).getMessage(), "ssl == null")) {
                return null;
            }
            throw new AssertionError(e10);
        }
    }

    @Override // p177q6.InterfaceC6175m
    /* renamed from: d */
    public void mo23620d(SSLSocket sSLSocket, String str, List<? extends Protocol> list) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        C0042f.m158e(sSLSocket, "sslSocket");
        C0042f.m158e(list, "protocols");
        if (mo23617a(sSLSocket)) {
            try {
                this.f20809b.invoke(sSLSocket, Boolean.TRUE);
                if (str != null) {
                    this.f20810c.invoke(sSLSocket, str);
                }
                this.f20812e.invoke(sSLSocket, C6113j.f20745a.m23443c(list));
            } catch (IllegalAccessException e9) {
                throw new AssertionError(e9);
            } catch (InvocationTargetException e10) {
                throw new AssertionError(e10);
            }
        }
    }
}
