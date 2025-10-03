package androidx.media;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.session.IMediaSession;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.os.ResultReceiver;
import android.text.TextUtils;
import android.util.Log;
import androidx.media.C0423a;
import androidx.media.C0426d;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import p021c0.C0698d;
import p123l0.C5265d;
import p123l0.C5266e;
import p132m.C5302a;
import p188s.C6229d;

/* loaded from: classes.dex */
public abstract class MediaBrowserServiceCompat extends Service {

    /* renamed from: g */
    public static final boolean f2263g = Log.isLoggable("MBServiceCompat", 3);

    /* renamed from: b */
    public InterfaceC0413g f2264b;

    /* renamed from: d */
    public C0412f f2266d;

    /* renamed from: f */
    public MediaSessionCompat.Token f2268f;

    /* renamed from: c */
    public final C5302a<IBinder, C0412f> f2265c = new C5302a<>();

    /* renamed from: e */
    public final HandlerC0422p f2267e = new HandlerC0422p();

    /* renamed from: androidx.media.MediaBrowserServiceCompat$a */
    public class C0407a extends C0418l<List<MediaBrowserCompat.MediaItem>> {

        /* renamed from: f */
        public final /* synthetic */ C0412f f2269f;

        /* renamed from: g */
        public final /* synthetic */ String f2270g;

        /* renamed from: h */
        public final /* synthetic */ Bundle f2271h;

        /* renamed from: i */
        public final /* synthetic */ Bundle f2272i;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C0407a(Object obj, C0412f c0412f, String str, Bundle bundle, Bundle bundle2) {
            super(obj);
            this.f2269f = c0412f;
            this.f2270g = str;
            this.f2271h = bundle;
            this.f2272i = bundle2;
        }

        @Override // androidx.media.MediaBrowserServiceCompat.C0418l
        /* renamed from: h, reason: merged with bridge method [inline-methods] */
        public void mo2153d(List<MediaBrowserCompat.MediaItem> list) {
            if (MediaBrowserServiceCompat.this.f2265c.get(this.f2269f.f2285f.asBinder()) != this.f2269f) {
                if (MediaBrowserServiceCompat.f2263g) {
                    Log.d("MBServiceCompat", "Not sending onLoadChildren result for connection that has been disconnected. pkg=" + this.f2269f.f2280a + " id=" + this.f2270g);
                    return;
                }
                return;
            }
            if ((m2168a() & 1) != 0) {
                list = MediaBrowserServiceCompat.this.m2138b(list, this.f2271h);
            }
            try {
                this.f2269f.f2285f.mo2182a(this.f2270g, list, this.f2271h, this.f2272i);
            } catch (RemoteException unused) {
                Log.w("MBServiceCompat", "Calling onLoadChildren() failed for id=" + this.f2270g + " package=" + this.f2269f.f2280a);
            }
        }
    }

    /* renamed from: androidx.media.MediaBrowserServiceCompat$b */
    public class C0408b extends C0418l<MediaBrowserCompat.MediaItem> {

        /* renamed from: f */
        public final /* synthetic */ ResultReceiver f2274f;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C0408b(Object obj, ResultReceiver resultReceiver) {
            super(obj);
            this.f2274f = resultReceiver;
        }

        @Override // androidx.media.MediaBrowserServiceCompat.C0418l
        /* renamed from: h, reason: merged with bridge method [inline-methods] */
        public void mo2153d(MediaBrowserCompat.MediaItem mediaItem) {
            if ((m2168a() & 2) != 0) {
                this.f2274f.send(-1, null);
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putParcelable("media_item", mediaItem);
            this.f2274f.send(0, bundle);
        }
    }

    /* renamed from: androidx.media.MediaBrowserServiceCompat$c */
    public class C0409c extends C0418l<List<MediaBrowserCompat.MediaItem>> {

        /* renamed from: f */
        public final /* synthetic */ ResultReceiver f2276f;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C0409c(Object obj, ResultReceiver resultReceiver) {
            super(obj);
            this.f2276f = resultReceiver;
        }

        @Override // androidx.media.MediaBrowserServiceCompat.C0418l
        /* renamed from: h, reason: merged with bridge method [inline-methods] */
        public void mo2153d(List<MediaBrowserCompat.MediaItem> list) {
            if ((m2168a() & 4) != 0 || list == null) {
                this.f2276f.send(-1, null);
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putParcelableArray("search_results", (Parcelable[]) list.toArray(new MediaBrowserCompat.MediaItem[0]));
            this.f2276f.send(0, bundle);
        }
    }

    /* renamed from: androidx.media.MediaBrowserServiceCompat$d */
    public class C0410d extends C0418l<Bundle> {

        /* renamed from: f */
        public final /* synthetic */ ResultReceiver f2278f;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C0410d(Object obj, ResultReceiver resultReceiver) {
            super(obj);
            this.f2278f = resultReceiver;
        }

        @Override // androidx.media.MediaBrowserServiceCompat.C0418l
        /* renamed from: c */
        public void mo2157c(Bundle bundle) {
            this.f2278f.send(-1, bundle);
        }

        @Override // androidx.media.MediaBrowserServiceCompat.C0418l
        /* renamed from: h, reason: merged with bridge method [inline-methods] */
        public void mo2153d(Bundle bundle) {
            this.f2278f.send(0, bundle);
        }
    }

    /* renamed from: androidx.media.MediaBrowserServiceCompat$e */
    public static final class C0411e {
    }

    /* renamed from: androidx.media.MediaBrowserServiceCompat$f */
    public class C0412f implements IBinder.DeathRecipient {

        /* renamed from: a */
        public final String f2280a;

        /* renamed from: b */
        public final int f2281b;

        /* renamed from: c */
        public final int f2282c;

        /* renamed from: d */
        public final C5266e f2283d;

        /* renamed from: e */
        public final Bundle f2284e;

        /* renamed from: f */
        public final InterfaceC0420n f2285f;

        /* renamed from: g */
        public final HashMap<String, List<C0698d<IBinder, Bundle>>> f2286g = new HashMap<>();

        /* renamed from: androidx.media.MediaBrowserServiceCompat$f$a */
        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                C0412f c0412f = C0412f.this;
                MediaBrowserServiceCompat.this.f2265c.remove(c0412f.f2285f.asBinder());
            }
        }

        public C0412f(String str, int i9, int i10, Bundle bundle, InterfaceC0420n interfaceC0420n) {
            this.f2280a = str;
            this.f2281b = i9;
            this.f2282c = i10;
            this.f2283d = new C5266e(str, i9, i10);
            this.f2284e = bundle;
            this.f2285f = interfaceC0420n;
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            MediaBrowserServiceCompat.this.f2267e.post(new a());
        }
    }

    /* renamed from: androidx.media.MediaBrowserServiceCompat$g */
    public interface InterfaceC0413g {
        /* renamed from: a */
        void mo2159a();

        /* renamed from: d */
        IBinder mo2160d(Intent intent);
    }

    /* renamed from: androidx.media.MediaBrowserServiceCompat$h */
    public class C0414h implements InterfaceC0413g, C0423a.d {

        /* renamed from: a */
        public final List<Bundle> f2289a = new ArrayList();

        /* renamed from: b */
        public Object f2290b;

        /* renamed from: c */
        public Messenger f2291c;

        /* renamed from: androidx.media.MediaBrowserServiceCompat$h$a */
        public class a extends C0418l<List<MediaBrowserCompat.MediaItem>> {

            /* renamed from: f */
            public final /* synthetic */ C0423a.c f2293f;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(Object obj, C0423a.c cVar) {
                super(obj);
                this.f2293f = cVar;
            }

            @Override // androidx.media.MediaBrowserServiceCompat.C0418l
            /* renamed from: h, reason: merged with bridge method [inline-methods] */
            public void mo2153d(List<MediaBrowserCompat.MediaItem> list) {
                ArrayList arrayList;
                if (list != null) {
                    arrayList = new ArrayList();
                    for (MediaBrowserCompat.MediaItem mediaItem : list) {
                        Parcel parcelObtain = Parcel.obtain();
                        mediaItem.writeToParcel(parcelObtain, 0);
                        arrayList.add(parcelObtain);
                    }
                } else {
                    arrayList = null;
                }
                this.f2293f.m2189b(arrayList);
            }
        }

        public C0414h() {
        }

        @Override // androidx.media.C0423a.d
        /* renamed from: c */
        public void mo2161c(String str, C0423a.c<List<Parcel>> cVar) {
            MediaBrowserServiceCompat.this.m2142f(str, new a(str, cVar));
        }

        @Override // androidx.media.MediaBrowserServiceCompat.InterfaceC0413g
        /* renamed from: d */
        public IBinder mo2160d(Intent intent) {
            return C0423a.m2186a(this.f2290b, intent);
        }

        @Override // androidx.media.C0423a.d
        /* renamed from: f */
        public C0423a.a mo2162f(String str, int i9, Bundle bundle) {
            if (bundle != null && bundle.getInt("extra_client_version", 0) != 0) {
                bundle.remove("extra_client_version");
                this.f2291c = new Messenger(MediaBrowserServiceCompat.this.f2267e);
                Bundle bundle2 = new Bundle();
                bundle2.putInt("extra_service_version", 2);
                C6229d.m23807b(bundle2, "extra_messenger", this.f2291c.getBinder());
                MediaSessionCompat.Token token = MediaBrowserServiceCompat.this.f2268f;
                if (token != null) {
                    IMediaSession extraBinder = token.getExtraBinder();
                    C6229d.m23807b(bundle2, "extra_session_binder", extraBinder == null ? null : extraBinder.asBinder());
                } else {
                    this.f2289a.add(bundle2);
                }
            }
            MediaBrowserServiceCompat mediaBrowserServiceCompat = MediaBrowserServiceCompat.this;
            mediaBrowserServiceCompat.f2266d = mediaBrowserServiceCompat.new C0412f(str, -1, i9, bundle, null);
            MediaBrowserServiceCompat.this.m2141e(str, i9, bundle);
            MediaBrowserServiceCompat.this.f2266d = null;
            return null;
        }
    }

    /* renamed from: androidx.media.MediaBrowserServiceCompat$i */
    public class C0415i extends C0414h implements InterfaceC0425c {

        /* renamed from: androidx.media.MediaBrowserServiceCompat$i$a */
        public class a extends C0418l<MediaBrowserCompat.MediaItem> {

            /* renamed from: f */
            public final /* synthetic */ C0423a.c f2296f;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(Object obj, C0423a.c cVar) {
                super(obj);
                this.f2296f = cVar;
            }

            @Override // androidx.media.MediaBrowserServiceCompat.C0418l
            /* renamed from: h, reason: merged with bridge method [inline-methods] */
            public void mo2153d(MediaBrowserCompat.MediaItem mediaItem) {
                if (mediaItem == null) {
                    this.f2296f.m2189b(null);
                    return;
                }
                Parcel parcelObtain = Parcel.obtain();
                mediaItem.writeToParcel(parcelObtain, 0);
                this.f2296f.m2189b(parcelObtain);
            }
        }

        public C0415i() {
            super();
        }

        @Override // androidx.media.InterfaceC0425c
        /* renamed from: b */
        public void mo2164b(String str, C0423a.c<Parcel> cVar) {
            MediaBrowserServiceCompat.this.m2144h(str, new a(str, cVar));
        }
    }

    /* renamed from: androidx.media.MediaBrowserServiceCompat$j */
    public class C0416j extends C0415i implements C0426d.c {

        /* renamed from: androidx.media.MediaBrowserServiceCompat$j$a */
        public class a extends C0418l<List<MediaBrowserCompat.MediaItem>> {

            /* renamed from: f */
            public final /* synthetic */ C0426d.b f2299f;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(Object obj, C0426d.b bVar) {
                super(obj);
                this.f2299f = bVar;
            }

            @Override // androidx.media.MediaBrowserServiceCompat.C0418l
            /* renamed from: h, reason: merged with bridge method [inline-methods] */
            public void mo2153d(List<MediaBrowserCompat.MediaItem> list) throws IllegalAccessException, IllegalArgumentException {
                ArrayList arrayList;
                if (list != null) {
                    arrayList = new ArrayList();
                    for (MediaBrowserCompat.MediaItem mediaItem : list) {
                        Parcel parcelObtain = Parcel.obtain();
                        mediaItem.writeToParcel(parcelObtain, 0);
                        arrayList.add(parcelObtain);
                    }
                } else {
                    arrayList = null;
                }
                this.f2299f.m2192b(arrayList, m2168a());
            }
        }

        public C0416j() {
            super();
        }

        @Override // androidx.media.MediaBrowserServiceCompat.InterfaceC0413g
        /* renamed from: a */
        public void mo2159a() {
            Object objM2190a = C0426d.m2190a(MediaBrowserServiceCompat.this, this);
            this.f2290b = objM2190a;
            C0423a.m2187b(objM2190a);
        }

        @Override // androidx.media.C0426d.c
        /* renamed from: e */
        public void mo2166e(String str, C0426d.b bVar, Bundle bundle) {
            MediaBrowserServiceCompat.this.m2143g(str, new a(str, bVar), bundle);
        }
    }

    /* renamed from: androidx.media.MediaBrowserServiceCompat$k */
    public class C0417k extends C0416j {
        public C0417k() {
            super();
        }
    }

    /* renamed from: androidx.media.MediaBrowserServiceCompat$l */
    public static class C0418l<T> {

        /* renamed from: a */
        public final Object f2302a;

        /* renamed from: b */
        public boolean f2303b;

        /* renamed from: c */
        public boolean f2304c;

        /* renamed from: d */
        public boolean f2305d;

        /* renamed from: e */
        public int f2306e;

        public C0418l(Object obj) {
            this.f2302a = obj;
        }

        /* renamed from: a */
        public int m2168a() {
            return this.f2306e;
        }

        /* renamed from: b */
        public boolean m2169b() {
            return this.f2303b || this.f2304c || this.f2305d;
        }

        /* renamed from: c */
        public void mo2157c(Bundle bundle) {
            throw new UnsupportedOperationException("It is not supported to send an error for " + this.f2302a);
        }

        /* renamed from: d */
        public void mo2153d(T t8) {
            throw null;
        }

        /* renamed from: e */
        public void m2170e(Bundle bundle) {
            if (!this.f2304c && !this.f2305d) {
                this.f2305d = true;
                mo2157c(bundle);
            } else {
                throw new IllegalStateException("sendError() called when either sendResult() or sendError() had already been called for: " + this.f2302a);
            }
        }

        /* renamed from: f */
        public void m2171f(T t8) {
            if (!this.f2304c && !this.f2305d) {
                this.f2304c = true;
                mo2153d(t8);
            } else {
                throw new IllegalStateException("sendResult() called when either sendResult() or sendError() had already been called for: " + this.f2302a);
            }
        }

        /* renamed from: g */
        public void m2172g(int i9) {
            this.f2306e = i9;
        }
    }

    /* renamed from: androidx.media.MediaBrowserServiceCompat$m */
    public class C0419m {

        /* renamed from: androidx.media.MediaBrowserServiceCompat$m$a */
        public class a implements Runnable {

            /* renamed from: b */
            public final /* synthetic */ InterfaceC0420n f2308b;

            /* renamed from: c */
            public final /* synthetic */ String f2309c;

            /* renamed from: d */
            public final /* synthetic */ int f2310d;

            /* renamed from: e */
            public final /* synthetic */ int f2311e;

            /* renamed from: f */
            public final /* synthetic */ Bundle f2312f;

            public a(InterfaceC0420n interfaceC0420n, String str, int i9, int i10, Bundle bundle) {
                this.f2308b = interfaceC0420n;
                this.f2309c = str;
                this.f2310d = i9;
                this.f2311e = i10;
                this.f2312f = bundle;
            }

            @Override // java.lang.Runnable
            public void run() {
                MediaBrowserServiceCompat.this.f2265c.remove(this.f2308b.asBinder());
                C0412f c0412f = MediaBrowserServiceCompat.this.new C0412f(this.f2309c, this.f2310d, this.f2311e, this.f2312f, this.f2308b);
                MediaBrowserServiceCompat mediaBrowserServiceCompat = MediaBrowserServiceCompat.this;
                mediaBrowserServiceCompat.f2266d = c0412f;
                mediaBrowserServiceCompat.m2141e(this.f2309c, this.f2311e, this.f2312f);
                MediaBrowserServiceCompat.this.f2266d = null;
                Log.i("MBServiceCompat", "No root for client " + this.f2309c + " from service " + getClass().getName());
                try {
                    this.f2308b.mo2183b();
                } catch (RemoteException unused) {
                    Log.w("MBServiceCompat", "Calling onConnectFailed() failed. Ignoring. pkg=" + this.f2309c);
                }
            }
        }

        /* renamed from: androidx.media.MediaBrowserServiceCompat$m$b */
        public class b implements Runnable {

            /* renamed from: b */
            public final /* synthetic */ InterfaceC0420n f2314b;

            public b(InterfaceC0420n interfaceC0420n) {
                this.f2314b = interfaceC0420n;
            }

            @Override // java.lang.Runnable
            public void run() {
                C0412f c0412fRemove = MediaBrowserServiceCompat.this.f2265c.remove(this.f2314b.asBinder());
                if (c0412fRemove != null) {
                    c0412fRemove.f2285f.asBinder().unlinkToDeath(c0412fRemove, 0);
                }
            }
        }

        /* renamed from: androidx.media.MediaBrowserServiceCompat$m$c */
        public class c implements Runnable {

            /* renamed from: b */
            public final /* synthetic */ InterfaceC0420n f2316b;

            /* renamed from: c */
            public final /* synthetic */ String f2317c;

            /* renamed from: d */
            public final /* synthetic */ IBinder f2318d;

            /* renamed from: e */
            public final /* synthetic */ Bundle f2319e;

            public c(InterfaceC0420n interfaceC0420n, String str, IBinder iBinder, Bundle bundle) {
                this.f2316b = interfaceC0420n;
                this.f2317c = str;
                this.f2318d = iBinder;
                this.f2319e = bundle;
            }

            @Override // java.lang.Runnable
            public void run() {
                C0412f c0412f = MediaBrowserServiceCompat.this.f2265c.get(this.f2316b.asBinder());
                if (c0412f != null) {
                    MediaBrowserServiceCompat.this.m2137a(this.f2317c, c0412f, this.f2318d, this.f2319e);
                    return;
                }
                Log.w("MBServiceCompat", "addSubscription for callback that isn't registered id=" + this.f2317c);
            }
        }

        /* renamed from: androidx.media.MediaBrowserServiceCompat$m$d */
        public class d implements Runnable {

            /* renamed from: b */
            public final /* synthetic */ InterfaceC0420n f2321b;

            /* renamed from: c */
            public final /* synthetic */ String f2322c;

            /* renamed from: d */
            public final /* synthetic */ IBinder f2323d;

            public d(InterfaceC0420n interfaceC0420n, String str, IBinder iBinder) {
                this.f2321b = interfaceC0420n;
                this.f2322c = str;
                this.f2323d = iBinder;
            }

            @Override // java.lang.Runnable
            public void run() {
                C0412f c0412f = MediaBrowserServiceCompat.this.f2265c.get(this.f2321b.asBinder());
                if (c0412f == null) {
                    Log.w("MBServiceCompat", "removeSubscription for callback that isn't registered id=" + this.f2322c);
                    return;
                }
                if (MediaBrowserServiceCompat.this.m2152p(this.f2322c, c0412f, this.f2323d)) {
                    return;
                }
                Log.w("MBServiceCompat", "removeSubscription called for " + this.f2322c + " which is not subscribed");
            }
        }

        /* renamed from: androidx.media.MediaBrowserServiceCompat$m$e */
        public class e implements Runnable {

            /* renamed from: b */
            public final /* synthetic */ InterfaceC0420n f2325b;

            /* renamed from: c */
            public final /* synthetic */ String f2326c;

            /* renamed from: d */
            public final /* synthetic */ ResultReceiver f2327d;

            public e(InterfaceC0420n interfaceC0420n, String str, ResultReceiver resultReceiver) {
                this.f2325b = interfaceC0420n;
                this.f2326c = str;
                this.f2327d = resultReceiver;
            }

            @Override // java.lang.Runnable
            public void run() {
                C0412f c0412f = MediaBrowserServiceCompat.this.f2265c.get(this.f2325b.asBinder());
                if (c0412f != null) {
                    MediaBrowserServiceCompat.this.m2150n(this.f2326c, c0412f, this.f2327d);
                    return;
                }
                Log.w("MBServiceCompat", "getMediaItem for callback that isn't registered id=" + this.f2326c);
            }
        }

        /* renamed from: androidx.media.MediaBrowserServiceCompat$m$f */
        public class f implements Runnable {

            /* renamed from: b */
            public final /* synthetic */ InterfaceC0420n f2329b;

            /* renamed from: c */
            public final /* synthetic */ String f2330c;

            /* renamed from: d */
            public final /* synthetic */ int f2331d;

            /* renamed from: e */
            public final /* synthetic */ int f2332e;

            /* renamed from: f */
            public final /* synthetic */ Bundle f2333f;

            public f(InterfaceC0420n interfaceC0420n, String str, int i9, int i10, Bundle bundle) {
                this.f2329b = interfaceC0420n;
                this.f2330c = str;
                this.f2331d = i9;
                this.f2332e = i10;
                this.f2333f = bundle;
            }

            @Override // java.lang.Runnable
            public void run() throws RemoteException {
                IBinder iBinderAsBinder = this.f2329b.asBinder();
                MediaBrowserServiceCompat.this.f2265c.remove(iBinderAsBinder);
                C0412f c0412f = MediaBrowserServiceCompat.this.new C0412f(this.f2330c, this.f2331d, this.f2332e, this.f2333f, this.f2329b);
                MediaBrowserServiceCompat.this.f2265c.put(iBinderAsBinder, c0412f);
                try {
                    iBinderAsBinder.linkToDeath(c0412f, 0);
                } catch (RemoteException unused) {
                    Log.w("MBServiceCompat", "IBinder is already dead.");
                }
            }
        }

        /* renamed from: androidx.media.MediaBrowserServiceCompat$m$g */
        public class g implements Runnable {

            /* renamed from: b */
            public final /* synthetic */ InterfaceC0420n f2335b;

            public g(InterfaceC0420n interfaceC0420n) {
                this.f2335b = interfaceC0420n;
            }

            @Override // java.lang.Runnable
            public void run() {
                IBinder iBinderAsBinder = this.f2335b.asBinder();
                C0412f c0412fRemove = MediaBrowserServiceCompat.this.f2265c.remove(iBinderAsBinder);
                if (c0412fRemove != null) {
                    iBinderAsBinder.unlinkToDeath(c0412fRemove, 0);
                }
            }
        }

        /* renamed from: androidx.media.MediaBrowserServiceCompat$m$h */
        public class h implements Runnable {

            /* renamed from: b */
            public final /* synthetic */ InterfaceC0420n f2337b;

            /* renamed from: c */
            public final /* synthetic */ String f2338c;

            /* renamed from: d */
            public final /* synthetic */ Bundle f2339d;

            /* renamed from: e */
            public final /* synthetic */ ResultReceiver f2340e;

            public h(InterfaceC0420n interfaceC0420n, String str, Bundle bundle, ResultReceiver resultReceiver) {
                this.f2337b = interfaceC0420n;
                this.f2338c = str;
                this.f2339d = bundle;
                this.f2340e = resultReceiver;
            }

            @Override // java.lang.Runnable
            public void run() {
                C0412f c0412f = MediaBrowserServiceCompat.this.f2265c.get(this.f2337b.asBinder());
                if (c0412f != null) {
                    MediaBrowserServiceCompat.this.m2151o(this.f2338c, this.f2339d, c0412f, this.f2340e);
                    return;
                }
                Log.w("MBServiceCompat", "search for callback that isn't registered query=" + this.f2338c);
            }
        }

        /* renamed from: androidx.media.MediaBrowserServiceCompat$m$i */
        public class i implements Runnable {

            /* renamed from: b */
            public final /* synthetic */ InterfaceC0420n f2342b;

            /* renamed from: c */
            public final /* synthetic */ String f2343c;

            /* renamed from: d */
            public final /* synthetic */ Bundle f2344d;

            /* renamed from: e */
            public final /* synthetic */ ResultReceiver f2345e;

            public i(InterfaceC0420n interfaceC0420n, String str, Bundle bundle, ResultReceiver resultReceiver) {
                this.f2342b = interfaceC0420n;
                this.f2343c = str;
                this.f2344d = bundle;
                this.f2345e = resultReceiver;
            }

            @Override // java.lang.Runnable
            public void run() {
                C0412f c0412f = MediaBrowserServiceCompat.this.f2265c.get(this.f2342b.asBinder());
                if (c0412f != null) {
                    MediaBrowserServiceCompat.this.m2148l(this.f2343c, this.f2344d, c0412f, this.f2345e);
                    return;
                }
                Log.w("MBServiceCompat", "sendCustomAction for callback that isn't registered action=" + this.f2343c + ", extras=" + this.f2344d);
            }
        }

        public C0419m() {
        }

        /* renamed from: a */
        public void m2173a(String str, IBinder iBinder, Bundle bundle, InterfaceC0420n interfaceC0420n) {
            MediaBrowserServiceCompat.this.f2267e.m2185a(new c(interfaceC0420n, str, iBinder, bundle));
        }

        /* renamed from: b */
        public void m2174b(String str, int i9, int i10, Bundle bundle, InterfaceC0420n interfaceC0420n) {
            if (MediaBrowserServiceCompat.this.m2139c(str, i10)) {
                MediaBrowserServiceCompat.this.f2267e.m2185a(new a(interfaceC0420n, str, i9, i10, bundle));
                return;
            }
            throw new IllegalArgumentException("Package/uid mismatch: uid=" + i10 + " package=" + str);
        }

        /* renamed from: c */
        public void m2175c(InterfaceC0420n interfaceC0420n) {
            MediaBrowserServiceCompat.this.f2267e.m2185a(new b(interfaceC0420n));
        }

        /* renamed from: d */
        public void m2176d(String str, ResultReceiver resultReceiver, InterfaceC0420n interfaceC0420n) {
            if (TextUtils.isEmpty(str) || resultReceiver == null) {
                return;
            }
            MediaBrowserServiceCompat.this.f2267e.m2185a(new e(interfaceC0420n, str, resultReceiver));
        }

        /* renamed from: e */
        public void m2177e(InterfaceC0420n interfaceC0420n, String str, int i9, int i10, Bundle bundle) {
            MediaBrowserServiceCompat.this.f2267e.m2185a(new f(interfaceC0420n, str, i9, i10, bundle));
        }

        /* renamed from: f */
        public void m2178f(String str, IBinder iBinder, InterfaceC0420n interfaceC0420n) {
            MediaBrowserServiceCompat.this.f2267e.m2185a(new d(interfaceC0420n, str, iBinder));
        }

        /* renamed from: g */
        public void m2179g(String str, Bundle bundle, ResultReceiver resultReceiver, InterfaceC0420n interfaceC0420n) {
            if (TextUtils.isEmpty(str) || resultReceiver == null) {
                return;
            }
            MediaBrowserServiceCompat.this.f2267e.m2185a(new h(interfaceC0420n, str, bundle, resultReceiver));
        }

        /* renamed from: h */
        public void m2180h(String str, Bundle bundle, ResultReceiver resultReceiver, InterfaceC0420n interfaceC0420n) {
            if (TextUtils.isEmpty(str) || resultReceiver == null) {
                return;
            }
            MediaBrowserServiceCompat.this.f2267e.m2185a(new i(interfaceC0420n, str, bundle, resultReceiver));
        }

        /* renamed from: i */
        public void m2181i(InterfaceC0420n interfaceC0420n) {
            MediaBrowserServiceCompat.this.f2267e.m2185a(new g(interfaceC0420n));
        }
    }

    /* renamed from: androidx.media.MediaBrowserServiceCompat$n */
    public interface InterfaceC0420n {
        /* renamed from: a */
        void mo2182a(String str, List<MediaBrowserCompat.MediaItem> list, Bundle bundle, Bundle bundle2);

        IBinder asBinder();

        /* renamed from: b */
        void mo2183b();
    }

    /* renamed from: androidx.media.MediaBrowserServiceCompat$o */
    public static class C0421o implements InterfaceC0420n {

        /* renamed from: a */
        public final Messenger f2347a;

        public C0421o(Messenger messenger) {
            this.f2347a = messenger;
        }

        @Override // androidx.media.MediaBrowserServiceCompat.InterfaceC0420n
        /* renamed from: a */
        public void mo2182a(String str, List<MediaBrowserCompat.MediaItem> list, Bundle bundle, Bundle bundle2) throws RemoteException {
            Bundle bundle3 = new Bundle();
            bundle3.putString("data_media_item_id", str);
            bundle3.putBundle("data_options", bundle);
            bundle3.putBundle("data_notify_children_changed_options", bundle2);
            if (list != null) {
                bundle3.putParcelableArrayList("data_media_item_list", list instanceof ArrayList ? (ArrayList) list : new ArrayList<>(list));
            }
            m2184c(3, bundle3);
        }

        @Override // androidx.media.MediaBrowserServiceCompat.InterfaceC0420n
        public IBinder asBinder() {
            return this.f2347a.getBinder();
        }

        @Override // androidx.media.MediaBrowserServiceCompat.InterfaceC0420n
        /* renamed from: b */
        public void mo2183b() throws RemoteException {
            m2184c(2, null);
        }

        /* renamed from: c */
        public final void m2184c(int i9, Bundle bundle) throws RemoteException {
            Message messageObtain = Message.obtain();
            messageObtain.what = i9;
            messageObtain.arg1 = 2;
            messageObtain.setData(bundle);
            this.f2347a.send(messageObtain);
        }
    }

    /* renamed from: androidx.media.MediaBrowserServiceCompat$p */
    public final class HandlerC0422p extends Handler {

        /* renamed from: a */
        public final C0419m f2348a;

        public HandlerC0422p() {
            this.f2348a = MediaBrowserServiceCompat.this.new C0419m();
        }

        /* renamed from: a */
        public void m2185a(Runnable runnable) {
            if (Thread.currentThread() == getLooper().getThread()) {
                runnable.run();
            } else {
                post(runnable);
            }
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Bundle data = message.getData();
            switch (message.what) {
                case 1:
                    Bundle bundle = data.getBundle("data_root_hints");
                    MediaSessionCompat.ensureClassLoader(bundle);
                    this.f2348a.m2174b(data.getString("data_package_name"), data.getInt("data_calling_pid"), data.getInt("data_calling_uid"), bundle, new C0421o(message.replyTo));
                    break;
                case 2:
                    this.f2348a.m2175c(new C0421o(message.replyTo));
                    break;
                case 3:
                    Bundle bundle2 = data.getBundle("data_options");
                    MediaSessionCompat.ensureClassLoader(bundle2);
                    this.f2348a.m2173a(data.getString("data_media_item_id"), C6229d.m23806a(data, "data_callback_token"), bundle2, new C0421o(message.replyTo));
                    break;
                case 4:
                    this.f2348a.m2178f(data.getString("data_media_item_id"), C6229d.m23806a(data, "data_callback_token"), new C0421o(message.replyTo));
                    break;
                case 5:
                    this.f2348a.m2176d(data.getString("data_media_item_id"), (ResultReceiver) data.getParcelable("data_result_receiver"), new C0421o(message.replyTo));
                    break;
                case 6:
                    Bundle bundle3 = data.getBundle("data_root_hints");
                    MediaSessionCompat.ensureClassLoader(bundle3);
                    this.f2348a.m2177e(new C0421o(message.replyTo), data.getString("data_package_name"), data.getInt("data_calling_pid"), data.getInt("data_calling_uid"), bundle3);
                    break;
                case 7:
                    this.f2348a.m2181i(new C0421o(message.replyTo));
                    break;
                case 8:
                    Bundle bundle4 = data.getBundle("data_search_extras");
                    MediaSessionCompat.ensureClassLoader(bundle4);
                    this.f2348a.m2179g(data.getString("data_search_query"), bundle4, (ResultReceiver) data.getParcelable("data_result_receiver"), new C0421o(message.replyTo));
                    break;
                case 9:
                    Bundle bundle5 = data.getBundle("data_custom_action_extras");
                    MediaSessionCompat.ensureClassLoader(bundle5);
                    this.f2348a.m2180h(data.getString("data_custom_action"), bundle5, (ResultReceiver) data.getParcelable("data_result_receiver"), new C0421o(message.replyTo));
                    break;
                default:
                    Log.w("MBServiceCompat", "Unhandled message: " + message + "\n  Service version: 2\n  Client version: " + message.arg1);
                    break;
            }
        }

        @Override // android.os.Handler
        public boolean sendMessageAtTime(Message message, long j9) {
            Bundle data = message.getData();
            data.setClassLoader(MediaBrowserCompat.class.getClassLoader());
            data.putInt("data_calling_uid", Binder.getCallingUid());
            data.putInt("data_calling_pid", Binder.getCallingPid());
            return super.sendMessageAtTime(message, j9);
        }
    }

    /* renamed from: a */
    public void m2137a(String str, C0412f c0412f, IBinder iBinder, Bundle bundle) {
        List<C0698d<IBinder, Bundle>> arrayList = c0412f.f2286g.get(str);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
        }
        for (C0698d<IBinder, Bundle> c0698d : arrayList) {
            if (iBinder == c0698d.f3353a && C5265d.m20531a(bundle, c0698d.f3354b)) {
                return;
            }
        }
        arrayList.add(new C0698d<>(iBinder, bundle));
        c0412f.f2286g.put(str, arrayList);
        m2149m(str, c0412f, bundle, null);
        this.f2266d = c0412f;
        m2146j(str, bundle);
        this.f2266d = null;
    }

    /* renamed from: b */
    public List<MediaBrowserCompat.MediaItem> m2138b(List<MediaBrowserCompat.MediaItem> list, Bundle bundle) {
        if (list == null) {
            return null;
        }
        int i9 = bundle.getInt(MediaBrowserCompat.EXTRA_PAGE, -1);
        int i10 = bundle.getInt(MediaBrowserCompat.EXTRA_PAGE_SIZE, -1);
        if (i9 == -1 && i10 == -1) {
            return list;
        }
        int i11 = i10 * i9;
        int size = i11 + i10;
        if (i9 < 0 || i10 < 1 || i11 >= list.size()) {
            return Collections.emptyList();
        }
        if (size > list.size()) {
            size = list.size();
        }
        return list.subList(i11, size);
    }

    /* renamed from: c */
    public boolean m2139c(String str, int i9) {
        if (str == null) {
            return false;
        }
        for (String str2 : getPackageManager().getPackagesForUid(i9)) {
            if (str2.equals(str)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: d */
    public void m2140d(String str, Bundle bundle, C0418l<Bundle> c0418l) {
        c0418l.m2170e(null);
    }

    @Override // android.app.Service
    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    /* renamed from: e */
    public abstract C0411e m2141e(String str, int i9, Bundle bundle);

    /* renamed from: f */
    public abstract void m2142f(String str, C0418l<List<MediaBrowserCompat.MediaItem>> c0418l);

    /* renamed from: g */
    public void m2143g(String str, C0418l<List<MediaBrowserCompat.MediaItem>> c0418l, Bundle bundle) {
        c0418l.m2172g(1);
        m2142f(str, c0418l);
    }

    /* renamed from: h */
    public void m2144h(String str, C0418l<MediaBrowserCompat.MediaItem> c0418l) {
        c0418l.m2172g(2);
        c0418l.m2171f(null);
    }

    /* renamed from: i */
    public void m2145i(String str, Bundle bundle, C0418l<List<MediaBrowserCompat.MediaItem>> c0418l) {
        c0418l.m2172g(4);
        c0418l.m2171f(null);
    }

    /* renamed from: j */
    public void m2146j(String str, Bundle bundle) {
    }

    /* renamed from: k */
    public void m2147k(String str) {
    }

    /* renamed from: l */
    public void m2148l(String str, Bundle bundle, C0412f c0412f, ResultReceiver resultReceiver) {
        C0410d c0410d = new C0410d(str, resultReceiver);
        this.f2266d = c0412f;
        m2140d(str, bundle, c0410d);
        this.f2266d = null;
        if (c0410d.m2169b()) {
            return;
        }
        throw new IllegalStateException("onCustomAction must call detach() or sendResult() or sendError() before returning for action=" + str + " extras=" + bundle);
    }

    /* renamed from: m */
    public void m2149m(String str, C0412f c0412f, Bundle bundle, Bundle bundle2) {
        C0407a c0407a = new C0407a(str, c0412f, str, bundle, bundle2);
        this.f2266d = c0412f;
        if (bundle == null) {
            m2142f(str, c0407a);
        } else {
            m2143g(str, c0407a, bundle);
        }
        this.f2266d = null;
        if (c0407a.m2169b()) {
            return;
        }
        throw new IllegalStateException("onLoadChildren must call detach() or sendResult() before returning for package=" + c0412f.f2280a + " id=" + str);
    }

    /* renamed from: n */
    public void m2150n(String str, C0412f c0412f, ResultReceiver resultReceiver) {
        C0408b c0408b = new C0408b(str, resultReceiver);
        this.f2266d = c0412f;
        m2144h(str, c0408b);
        this.f2266d = null;
        if (c0408b.m2169b()) {
            return;
        }
        throw new IllegalStateException("onLoadItem must call detach() or sendResult() before returning for id=" + str);
    }

    /* renamed from: o */
    public void m2151o(String str, Bundle bundle, C0412f c0412f, ResultReceiver resultReceiver) {
        C0409c c0409c = new C0409c(str, resultReceiver);
        this.f2266d = c0412f;
        m2145i(str, bundle, c0409c);
        this.f2266d = null;
        if (c0409c.m2169b()) {
            return;
        }
        throw new IllegalStateException("onSearch must call detach() or sendResult() before returning for query=" + str);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.f2264b.mo2160d(intent);
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= 28) {
            this.f2264b = new C0417k();
        } else {
            this.f2264b = new C0416j();
        }
        this.f2264b.mo2159a();
    }

    /* renamed from: p */
    public boolean m2152p(String str, C0412f c0412f, IBinder iBinder) {
        boolean z8 = false;
        try {
            if (iBinder == null) {
                return c0412f.f2286g.remove(str) != null;
            }
            List<C0698d<IBinder, Bundle>> list = c0412f.f2286g.get(str);
            if (list != null) {
                Iterator<C0698d<IBinder, Bundle>> it = list.iterator();
                while (it.hasNext()) {
                    if (iBinder == it.next().f3353a) {
                        it.remove();
                        z8 = true;
                    }
                }
                if (list.size() == 0) {
                    c0412f.f2286g.remove(str);
                }
            }
            return z8;
        } finally {
            this.f2266d = c0412f;
            m2147k(str);
            this.f2266d = null;
        }
    }
}
