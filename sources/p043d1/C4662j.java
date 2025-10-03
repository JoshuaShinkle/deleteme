package p043d1;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.util.Log;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;

/* renamed from: d1.j */
/* loaded from: classes.dex */
public final class C4662j {

    /* renamed from: a */
    public final int f16308a;

    /* renamed from: b */
    public final int f16309b;

    /* renamed from: c */
    public final Context f16310c;

    /* renamed from: d */
    public final int f16311d;

    /* renamed from: d1.j$a */
    public static final class a {

        /* renamed from: i */
        public static final int f16312i = 1;

        /* renamed from: a */
        public final Context f16313a;

        /* renamed from: b */
        public ActivityManager f16314b;

        /* renamed from: c */
        public c f16315c;

        /* renamed from: e */
        public float f16317e;

        /* renamed from: d */
        public float f16316d = 2.0f;

        /* renamed from: f */
        public float f16318f = 0.4f;

        /* renamed from: g */
        public float f16319g = 0.33f;

        /* renamed from: h */
        public int f16320h = 4194304;

        public a(Context context) {
            this.f16317e = f16312i;
            this.f16313a = context;
            this.f16314b = (ActivityManager) context.getSystemService("activity");
            this.f16315c = new b(context.getResources().getDisplayMetrics());
            if (C4662j.m18620e(this.f16314b)) {
                this.f16317e = BitmapDescriptorFactory.HUE_RED;
            }
        }

        /* renamed from: a */
        public C4662j m18625a() {
            return new C4662j(this);
        }
    }

    /* renamed from: d1.j$b */
    public static final class b implements c {

        /* renamed from: a */
        public final DisplayMetrics f16321a;

        public b(DisplayMetrics displayMetrics) {
            this.f16321a = displayMetrics;
        }

        @Override // p043d1.C4662j.c
        /* renamed from: a */
        public int mo18626a() {
            return this.f16321a.heightPixels;
        }

        @Override // p043d1.C4662j.c
        /* renamed from: b */
        public int mo18627b() {
            return this.f16321a.widthPixels;
        }
    }

    /* renamed from: d1.j$c */
    public interface c {
        /* renamed from: a */
        int mo18626a();

        /* renamed from: b */
        int mo18627b();
    }

    public C4662j(a aVar) {
        this.f16310c = aVar.f16313a;
        int i9 = m18620e(aVar.f16314b) ? aVar.f16320h / 2 : aVar.f16320h;
        this.f16311d = i9;
        int iM18619c = m18619c(aVar.f16314b, aVar.f16318f, aVar.f16319g);
        float fMo18627b = aVar.f16315c.mo18627b() * aVar.f16315c.mo18626a() * 4;
        int iRound = Math.round(aVar.f16317e * fMo18627b);
        int iRound2 = Math.round(fMo18627b * aVar.f16316d);
        int i10 = iM18619c - i9;
        int i11 = iRound2 + iRound;
        if (i11 <= i10) {
            this.f16309b = iRound2;
            this.f16308a = iRound;
        } else {
            float f9 = i10;
            float f10 = aVar.f16317e;
            float f11 = aVar.f16316d;
            float f12 = f9 / (f10 + f11);
            this.f16309b = Math.round(f11 * f12);
            this.f16308a = Math.round(f12 * aVar.f16317e);
        }
        if (Log.isLoggable("MemorySizeCalculator", 3)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Calculation complete, Calculated memory cache size: ");
            sb.append(m18624f(this.f16309b));
            sb.append(", pool size: ");
            sb.append(m18624f(this.f16308a));
            sb.append(", byte array size: ");
            sb.append(m18624f(i9));
            sb.append(", memory class limited? ");
            sb.append(i11 > iM18619c);
            sb.append(", max size: ");
            sb.append(m18624f(iM18619c));
            sb.append(", memoryClass: ");
            sb.append(aVar.f16314b.getMemoryClass());
            sb.append(", isLowMemoryDevice: ");
            sb.append(m18620e(aVar.f16314b));
            Log.d("MemorySizeCalculator", sb.toString());
        }
    }

    /* renamed from: c */
    public static int m18619c(ActivityManager activityManager, float f9, float f10) {
        float memoryClass = activityManager.getMemoryClass() * UserMetadata.MAX_ATTRIBUTE_SIZE * UserMetadata.MAX_ATTRIBUTE_SIZE;
        if (m18620e(activityManager)) {
            f9 = f10;
        }
        return Math.round(memoryClass * f9);
    }

    @TargetApi(19)
    /* renamed from: e */
    public static boolean m18620e(ActivityManager activityManager) {
        return activityManager.isLowRamDevice();
    }

    /* renamed from: a */
    public int m18621a() {
        return this.f16311d;
    }

    /* renamed from: b */
    public int m18622b() {
        return this.f16308a;
    }

    /* renamed from: d */
    public int m18623d() {
        return this.f16309b;
    }

    /* renamed from: f */
    public final String m18624f(int i9) {
        return Formatter.formatFileSize(this.f16310c, i9);
    }
}
