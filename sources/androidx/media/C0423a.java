package androidx.media;

import android.content.Context;
import android.content.Intent;
import android.media.browse.MediaBrowser;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.service.media.MediaBrowserService;
import android.support.v4.media.session.MediaSessionCompat;
import java.util.ArrayList;
import java.util.List;

/* renamed from: androidx.media.a */
/* loaded from: classes.dex */
public class C0423a {

    /* renamed from: androidx.media.a$a */
    public static class a {

        /* renamed from: a */
        public final String f2350a;

        /* renamed from: b */
        public final Bundle f2351b;
    }

    /* renamed from: androidx.media.a$b */
    public static class b extends MediaBrowserService {

        /* renamed from: b */
        public final d f2352b;

        public b(Context context, d dVar) {
            attachBaseContext(context);
            this.f2352b = dVar;
        }

        @Override // android.service.media.MediaBrowserService
        public MediaBrowserService.BrowserRoot onGetRoot(String str, int i9, Bundle bundle) {
            MediaSessionCompat.ensureClassLoader(bundle);
            a aVarMo2162f = this.f2352b.mo2162f(str, i9, bundle == null ? null : new Bundle(bundle));
            if (aVarMo2162f == null) {
                return null;
            }
            return new MediaBrowserService.BrowserRoot(aVarMo2162f.f2350a, aVarMo2162f.f2351b);
        }

        @Override // android.service.media.MediaBrowserService
        public void onLoadChildren(String str, MediaBrowserService.Result<List<MediaBrowser.MediaItem>> result) {
            this.f2352b.mo2161c(str, new c<>(result));
        }
    }

    /* renamed from: androidx.media.a$c */
    public static class c<T> {

        /* renamed from: a */
        public MediaBrowserService.Result f2353a;

        public c(MediaBrowserService.Result result) {
            this.f2353a = result;
        }

        /* renamed from: a */
        public List<MediaBrowser.MediaItem> m2188a(List<Parcel> list) {
            if (list == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            for (Parcel parcel : list) {
                parcel.setDataPosition(0);
                arrayList.add(MediaBrowser.MediaItem.CREATOR.createFromParcel(parcel));
                parcel.recycle();
            }
            return arrayList;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* renamed from: b */
        public void m2189b(T t8) {
            if (t8 instanceof List) {
                this.f2353a.sendResult(m2188a((List) t8));
                return;
            }
            if (!(t8 instanceof Parcel)) {
                this.f2353a.sendResult(null);
                return;
            }
            Parcel parcel = (Parcel) t8;
            parcel.setDataPosition(0);
            this.f2353a.sendResult(MediaBrowser.MediaItem.CREATOR.createFromParcel(parcel));
            parcel.recycle();
        }
    }

    /* renamed from: androidx.media.a$d */
    public interface d {
        /* renamed from: c */
        void mo2161c(String str, c<List<Parcel>> cVar);

        /* renamed from: f */
        a mo2162f(String str, int i9, Bundle bundle);
    }

    /* renamed from: a */
    public static IBinder m2186a(Object obj, Intent intent) {
        return ((MediaBrowserService) obj).onBind(intent);
    }

    /* renamed from: b */
    public static void m2187b(Object obj) {
        ((MediaBrowserService) obj).onCreate();
    }
}
