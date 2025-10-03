package p168p6;

import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.iid.MessengerIpcClient;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLSocket;
import okhttp3.Protocol;
import p007a6.C0040d;
import p007a6.C0042f;

/* renamed from: p6.e */
/* loaded from: classes.dex */
public final class C6108e extends C6113j {

    /* renamed from: i */
    public static final b f20731i = new b(null);

    /* renamed from: d */
    public final Method f20732d;

    /* renamed from: e */
    public final Method f20733e;

    /* renamed from: f */
    public final Method f20734f;

    /* renamed from: g */
    public final Class<?> f20735g;

    /* renamed from: h */
    public final Class<?> f20736h;

    /* renamed from: p6.e$a */
    public static final class a implements InvocationHandler {

        /* renamed from: a */
        public final List<String> f20737a;

        /* renamed from: b */
        public boolean f20738b;

        /* renamed from: c */
        public String f20739c;

        public a(List<String> list) {
            C0042f.m158e(list, "protocols");
            this.f20737a = list;
        }

        /* renamed from: a */
        public final String m23427a() {
            return this.f20739c;
        }

        /* renamed from: b */
        public final boolean m23428b() {
            return this.f20738b;
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) {
            C0042f.m158e(obj, "proxy");
            C0042f.m158e(method, FirebaseAnalytics.Param.METHOD);
            if (objArr == null) {
                objArr = new Object[0];
            }
            String name = method.getName();
            Class<?> returnType = method.getReturnType();
            if (C0042f.m154a(name, "supports") && C0042f.m154a(Boolean.TYPE, returnType)) {
                return Boolean.TRUE;
            }
            if (C0042f.m154a(name, MessengerIpcClient.KEY_UNSUPPORTED) && C0042f.m154a(Void.TYPE, returnType)) {
                this.f20738b = true;
                return null;
            }
            if (C0042f.m154a(name, "protocols")) {
                if (objArr.length == 0) {
                    return this.f20737a;
                }
            }
            if ((C0042f.m154a(name, "selectProtocol") || C0042f.m154a(name, "select")) && C0042f.m154a(String.class, returnType) && objArr.length == 1) {
                Object obj2 = objArr[0];
                if (obj2 instanceof List) {
                    C0042f.m156c(obj2, "null cannot be cast to non-null type kotlin.collections.List<*>");
                    List list = (List) obj2;
                    int size = list.size();
                    if (size >= 0) {
                        int i9 = 0;
                        while (true) {
                            Object obj3 = list.get(i9);
                            C0042f.m156c(obj3, "null cannot be cast to non-null type kotlin.String");
                            String str = (String) obj3;
                            if (!this.f20737a.contains(str)) {
                                if (i9 == size) {
                                    break;
                                }
                                i9++;
                            } else {
                                this.f20739c = str;
                                return str;
                            }
                        }
                    }
                    String str2 = this.f20737a.get(0);
                    this.f20739c = str2;
                    return str2;
                }
            }
            if ((!C0042f.m154a(name, "protocolSelected") && !C0042f.m154a(name, "selected")) || objArr.length != 1) {
                return method.invoke(this, Arrays.copyOf(objArr, objArr.length));
            }
            Object obj4 = objArr[0];
            C0042f.m156c(obj4, "null cannot be cast to non-null type kotlin.String");
            this.f20739c = (String) obj4;
            return null;
        }
    }

    /* renamed from: p6.e$b */
    public static final class b {
        public b() {
        }

        public /* synthetic */ b(C0040d c0040d) {
            this();
        }

        /* renamed from: a */
        public final C6113j m23429a() {
            String property = System.getProperty("java.specification.version", "unknown");
            try {
                C0042f.m157d(property, "jvmVersion");
                if (Integer.parseInt(property) >= 9) {
                    return null;
                }
            } catch (NumberFormatException unused) {
            }
            try {
                Class<?> cls = Class.forName("org.eclipse.jetty.alpn.ALPN", true, null);
                Class<?> cls2 = Class.forName("org.eclipse.jetty.alpn.ALPN$Provider", true, null);
                Class<?> cls3 = Class.forName("org.eclipse.jetty.alpn.ALPN$ClientProvider", true, null);
                Class<?> cls4 = Class.forName("org.eclipse.jetty.alpn.ALPN$ServerProvider", true, null);
                Method method = cls.getMethod("put", SSLSocket.class, cls2);
                Method method2 = cls.getMethod("get", SSLSocket.class);
                Method method3 = cls.getMethod(ProductAction.ACTION_REMOVE, SSLSocket.class);
                C0042f.m157d(method, "putMethod");
                C0042f.m157d(method2, "getMethod");
                C0042f.m157d(method3, "removeMethod");
                C0042f.m157d(cls3, "clientProviderClass");
                C0042f.m157d(cls4, "serverProviderClass");
                return new C6108e(method, method2, method3, cls3, cls4);
            } catch (ClassNotFoundException | NoSuchMethodException unused2) {
                return null;
            }
        }
    }

    public C6108e(Method method, Method method2, Method method3, Class<?> cls, Class<?> cls2) {
        C0042f.m158e(method, "putMethod");
        C0042f.m158e(method2, "getMethod");
        C0042f.m158e(method3, "removeMethod");
        C0042f.m158e(cls, "clientProviderClass");
        C0042f.m158e(cls2, "serverProviderClass");
        this.f20732d = method;
        this.f20733e = method2;
        this.f20734f = method3;
        this.f20735g = cls;
        this.f20736h = cls2;
    }

    @Override // p168p6.C6113j
    /* renamed from: b */
    public void mo23426b(SSLSocket sSLSocket) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        C0042f.m158e(sSLSocket, "sslSocket");
        try {
            this.f20734f.invoke(null, sSLSocket);
        } catch (IllegalAccessException e9) {
            throw new AssertionError("failed to remove ALPN", e9);
        } catch (InvocationTargetException e10) {
            throw new AssertionError("failed to remove ALPN", e10);
        }
    }

    @Override // p168p6.C6113j
    /* renamed from: e */
    public void mo23403e(SSLSocket sSLSocket, String str, List<? extends Protocol> list) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        C0042f.m158e(sSLSocket, "sslSocket");
        C0042f.m158e(list, "protocols");
        try {
            this.f20732d.invoke(null, sSLSocket, Proxy.newProxyInstance(C6113j.class.getClassLoader(), new Class[]{this.f20735g, this.f20736h}, new a(C6113j.f20745a.m23442b(list))));
        } catch (IllegalAccessException e9) {
            throw new AssertionError("failed to set ALPN", e9);
        } catch (InvocationTargetException e10) {
            throw new AssertionError("failed to set ALPN", e10);
        }
    }

    @Override // p168p6.C6113j
    /* renamed from: g */
    public String mo23404g(SSLSocket sSLSocket) throws IllegalArgumentException {
        C0042f.m158e(sSLSocket, "sslSocket");
        try {
            InvocationHandler invocationHandler = Proxy.getInvocationHandler(this.f20733e.invoke(null, sSLSocket));
            C0042f.m156c(invocationHandler, "null cannot be cast to non-null type okhttp3.internal.platform.Jdk8WithJettyBootPlatform.AlpnProvider");
            a aVar = (a) invocationHandler;
            if (!aVar.m23428b() && aVar.m23427a() == null) {
                C6113j.m23439k(this, "ALPN callback dropped: HTTP/2 is disabled. Is alpn-boot on the boot class path?", 0, null, 6, null);
                return null;
            }
            if (aVar.m23428b()) {
                return null;
            }
            return aVar.m23427a();
        } catch (IllegalAccessException e9) {
            throw new AssertionError("failed to get ALPN selected protocol", e9);
        } catch (InvocationTargetException e10) {
            throw new AssertionError("failed to get ALPN selected protocol", e10);
        }
    }
}
