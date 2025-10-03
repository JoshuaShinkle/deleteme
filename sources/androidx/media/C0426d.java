package androidx.media;

import android.content.Context;
import android.media.browse.MediaBrowser;
import android.os.Bundle;
import android.os.Parcel;
import android.service.media.MediaBrowserService;
import android.support.v4.media.session.MediaSessionCompat;
import android.util.Log;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/* renamed from: androidx.media.d */
/* loaded from: classes.dex */
public class C0426d {

    /* renamed from: a */
    public static Field f2354a;

    /* renamed from: androidx.media.d$a */
    public static class a extends C0424b {
        public a(Context context, c cVar) {
            super(context, cVar);
        }

        @Override // android.service.media.MediaBrowserService
        public void onLoadChildren(String str, MediaBrowserService.Result<List<MediaBrowser.MediaItem>> result, Bundle bundle) {
            MediaSessionCompat.ensureClassLoader(bundle);
            ((c) this.f2352b).mo2166e(str, new b(result), bundle);
        }
    }

    /* renamed from: androidx.media.d$b */
    public static class b {

        /* renamed from: a */
        public MediaBrowserService.Result f2355a;

        public b(MediaBrowserService.Result result) {
            this.f2355a = result;
        }

        /* renamed from: a */
        public List<MediaBrowser.MediaItem> m2191a(List<Parcel> list) {
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

        /* renamed from: b */
        public void m2192b(List<Parcel> list, int i9) throws IllegalAccessException, IllegalArgumentException {
            try {
                C0426d.f2354a.setInt(this.f2355a, i9);
            } catch (IllegalAccessException e9) {
                Log.w("MBSCompatApi26", e9);
            }
            this.f2355a.sendResult(m2191a(list));
        }
    }

    /* renamed from: androidx.media.d$c */
    public interface c extends InterfaceC0425c {
        /* renamed from: e */
        void mo2166e(String str, b bVar, Bundle bundle);
    }

    static {
        try {
            Field declaredField = MediaBrowserService.Result.class.getDeclaredField("mFlags");
            f2354a = declaredField;
            declaredField.setAccessible(true);
        } catch (NoSuchFieldException e9) {
            Log.w("MBSCompatApi26", e9);
        }
    }

    /* renamed from: a */
    public static Object m2190a(Context context, c cVar) {
        return new a(context, cVar);
    }
}
