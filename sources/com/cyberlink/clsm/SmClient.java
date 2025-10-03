package com.cyberlink.clsm;

import androidx.annotation.Keep;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import p209u2.AbstractC6381r;
import p209u2.C6385v;
import p209u2.ThreadFactoryC6373j;

/* loaded from: classes.dex */
public final class SmClient {

    /* renamed from: a */
    public final IRemoteClient f5798a;

    /* renamed from: b */
    public final IKeyStore f5799b;

    /* renamed from: c */
    public final ISessionCacheStore f5800c;

    /* renamed from: d */
    public volatile boolean f5801d;

    /* renamed from: e */
    public volatile boolean f5802e;

    /* renamed from: f */
    public final Object f5803f;

    /* renamed from: g */
    public final ConcurrentHashMap<String, ThreadPoolExecutor> f5804g;

    /* renamed from: h */
    public final ConcurrentHashMap<String, ThreadPoolExecutor> f5805h;

    @Keep
    private final long nativeClient;

    static {
        System.loadLibrary("CLSM");
    }

    public SmClient(IRemoteClient iRemoteClient, IKeyStore iKeyStore, ISessionCacheStore iSessionCacheStore) {
        this(C1206h.m5278a().m5279b(), iRemoteClient, iKeyStore, iSessionCacheStore);
    }

    /* renamed from: nC */
    private native long m5242nC(String str, Class cls, Object obj, Object obj2, Object obj3);

    /* renamed from: nD */
    private native String m5243nD(String str, byte[] bArr, long j9);

    /* renamed from: nE */
    private native String m5244nE(String str, byte[] bArr);

    /* renamed from: nI */
    private native boolean m5245nI();

    /* renamed from: nN */
    private native boolean m5246nN(String str, long j9);

    /* renamed from: nR */
    private native boolean m5247nR();

    /* renamed from: nS */
    private native String m5248nS(boolean z8);

    /* renamed from: nU */
    private native boolean m5249nU(String str, long j9, String str2, String str3);

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: p */
    public /* synthetic */ String m5250p(String str, String str2, long j9) {
        if (m5263o()) {
            return m5243nD(str, str2.getBytes(), j9);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: q */
    public /* synthetic */ String m5251q(String str, String str2) {
        if (m5263o()) {
            return m5244nE(str, str2.getBytes());
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: r */
    public /* synthetic */ void m5252r(AbstractC6381r abstractC6381r, Collection collection, boolean z8) {
        if (!m5263o()) {
            abstractC6381r.m24507e();
            return;
        }
        ArrayList<Device> arrayListMo5235b = this.f5798a.mo5235b(collection);
        if (arrayListMo5235b == null || arrayListMo5235b.isEmpty()) {
            abstractC6381r.m24507e();
            return;
        }
        HashMap map = new HashMap();
        Iterator<Device> it = arrayListMo5235b.iterator();
        while (it.hasNext()) {
            Device next = it.next();
            if (!z8 || C1206h.m5278a().m5282e() <= 0 || C1206h.m5278a().m5282e() != next.uId || !next.f5793id.equals(C1206h.m5278a().m5279b())) {
                List arrayList = (List) map.get(Long.valueOf(next.uId));
                if (arrayList == null) {
                    arrayList = new ArrayList();
                    map.put(Long.valueOf(next.uId), arrayList);
                }
                if (!arrayList.contains(next.f5794pk)) {
                    arrayList.add(next.f5794pk);
                }
            }
        }
        abstractC6381r.m24506d(map);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: s */
    public /* synthetic */ void m5253s(String str, long j9, AbstractC6381r abstractC6381r) {
        abstractC6381r.m24506d(Boolean.valueOf(m5263o() ? m5246nN(str, j9) : false));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: t */
    public /* synthetic */ void m5254t(String str, long j9, String str2, String str3, AbstractC6381r abstractC6381r) {
        abstractC6381r.m24506d(Boolean.valueOf(m5263o() ? m5249nU(str, j9, str2, str3) : false));
    }

    /* renamed from: g */
    public Decrypter m5255g(OutputStream outputStream, C1199a c1199a) {
        return new Decrypter(this.nativeClient, outputStream, c1199a);
    }

    /* renamed from: h */
    public Encrypter m5256h(OutputStream outputStream) {
        return new Encrypter(this.nativeClient, outputStream);
    }

    /* renamed from: i */
    public String m5257i(final String str, final String str2, final long j9) {
        return (String) m5259k(str).submit(new Callable() { // from class: com.cyberlink.clsm.d
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f5817b.m5250p(str, str2, j9);
            }
        }).get();
    }

    /* renamed from: j */
    public String m5258j(final String str, final String str2) {
        return (String) m5260l(str).submit(new Callable() { // from class: com.cyberlink.clsm.f
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f5827b.m5251q(str, str2);
            }
        }).get();
    }

    /* renamed from: k */
    public final ThreadPoolExecutor m5259k(String str) {
        ThreadPoolExecutor threadPoolExecutor = this.f5805h.get(str);
        if (threadPoolExecutor == null) {
            synchronized (this.f5805h) {
                threadPoolExecutor = this.f5805h.get(str);
                if (threadPoolExecutor == null) {
                    threadPoolExecutor = new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new ThreadFactoryC6373j("CLSM.Dec." + str));
                    this.f5805h.put(str, threadPoolExecutor);
                }
            }
        }
        return threadPoolExecutor;
    }

    /* renamed from: l */
    public final ThreadPoolExecutor m5260l(String str) {
        ThreadPoolExecutor threadPoolExecutor = this.f5804g.get(str);
        if (threadPoolExecutor == null) {
            synchronized (this.f5804g) {
                threadPoolExecutor = this.f5804g.get(str);
                if (threadPoolExecutor == null) {
                    threadPoolExecutor = new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new ThreadFactoryC6373j("CLSM.Enc." + str));
                    this.f5804g.put(str, threadPoolExecutor);
                }
            }
        }
        return threadPoolExecutor;
    }

    /* renamed from: m */
    public String m5261m(boolean z8) {
        if (m5263o()) {
            return m5248nS(z8);
        }
        return null;
    }

    /* renamed from: n */
    public void m5262n(final Collection<Long> collection, final boolean z8, final AbstractC6381r<Map<Long, List<String>>, Void> abstractC6381r) {
        C6385v.m24526d(new Runnable() { // from class: com.cyberlink.clsm.g
            @Override // java.lang.Runnable
            public final void run() {
                this.f5830b.m5252r(abstractC6381r, collection, z8);
            }
        });
    }

    /* renamed from: o */
    public final boolean m5263o() {
        if (this.f5802e) {
            throw new IllegalStateException("Instance has been released");
        }
        boolean zM5245nI = this.f5801d;
        if (!zM5245nI) {
            synchronized (this.f5803f) {
                zM5245nI = this.f5801d;
                if (!zM5245nI) {
                    zM5245nI = m5245nI();
                    this.f5801d = zM5245nI;
                }
            }
        }
        return zM5245nI;
    }

    /* renamed from: u */
    public void m5264u() {
        this.f5802e = true;
        m5247nR();
    }

    /* renamed from: v */
    public void m5265v(final String str, final long j9, final AbstractC6381r<Boolean, Void> abstractC6381r) {
        m5260l(str).submit(new Runnable() { // from class: com.cyberlink.clsm.c
            @Override // java.lang.Runnable
            public final void run() {
                this.f5813b.m5253s(str, j9, abstractC6381r);
            }
        });
    }

    /* renamed from: w */
    public boolean m5266w() {
        if (this.f5801d) {
            return true;
        }
        C6385v.m24526d(new Runnable() { // from class: com.cyberlink.clsm.b
            @Override // java.lang.Runnable
            public final void run() {
                this.f5812b.m5263o();
            }
        });
        return false;
    }

    /* renamed from: x */
    public void m5267x(final String str, final long j9, final String str2, final String str3, final AbstractC6381r<Boolean, Void> abstractC6381r) {
        m5259k(str).submit(new Runnable() { // from class: com.cyberlink.clsm.e
            @Override // java.lang.Runnable
            public final void run() {
                this.f5821b.m5254t(str, j9, str2, str3, abstractC6381r);
            }
        });
    }

    public SmClient(String str, IRemoteClient iRemoteClient, IKeyStore iKeyStore, ISessionCacheStore iSessionCacheStore) {
        this.f5801d = false;
        this.f5802e = false;
        this.f5803f = new Object();
        this.f5804g = new ConcurrentHashMap<>();
        this.f5805h = new ConcurrentHashMap<>();
        this.f5798a = iRemoteClient;
        this.f5799b = iKeyStore;
        this.f5800c = iSessionCacheStore;
        this.nativeClient = m5242nC(str, SmLog.class, iRemoteClient, iSessionCacheStore, iKeyStore);
        m5263o();
    }
}
