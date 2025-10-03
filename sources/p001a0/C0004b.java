package p001a0;

import android.content.ContentUris;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.CancellationSignal;
import android.os.Handler;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import p001a0.C0005c;
import p021c0.C0702h;
import p132m.C5306e;
import p132m.C5308g;
import p206u.C6347a;
import p206u.C6348b;
import p215v.C6430d;
import p215v.C6445s;

/* renamed from: a0.b */
/* loaded from: classes.dex */
public class C0004b {

    /* renamed from: a */
    public static final C5306e<String, Typeface> f7a = new C5306e<>(16);

    /* renamed from: b */
    public static final C0005c f8b = new C0005c("fonts", 10, 10000);

    /* renamed from: c */
    public static final Object f9c = new Object();

    /* renamed from: d */
    public static final C5308g<String, ArrayList<C0005c.d<g>>> f10d = new C5308g<>();

    /* renamed from: e */
    public static final Comparator<byte[]> f11e = new d();

    /* renamed from: a0.b$a */
    public class a implements Callable<g> {

        /* renamed from: b */
        public final /* synthetic */ Context f12b;

        /* renamed from: c */
        public final /* synthetic */ C0003a f13c;

        /* renamed from: d */
        public final /* synthetic */ int f14d;

        /* renamed from: e */
        public final /* synthetic */ String f15e;

        public a(Context context, C0003a c0003a, int i9, String str) {
            this.f12b = context;
            this.f13c = c0003a;
            this.f14d = i9;
            this.f15e = str;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public g call() {
            g gVarM24f = C0004b.m24f(this.f12b, this.f13c, this.f14d);
            Typeface typeface = gVarM24f.f26a;
            if (typeface != null) {
                C0004b.f7a.put(this.f15e, typeface);
            }
            return gVarM24f;
        }
    }

    /* renamed from: a0.b$b */
    public class b implements C0005c.d<g> {

        /* renamed from: a */
        public final /* synthetic */ C6348b.a f16a;

        /* renamed from: b */
        public final /* synthetic */ Handler f17b;

        public b(C6348b.a aVar, Handler handler) {
            this.f16a = aVar;
            this.f17b = handler;
        }

        @Override // p001a0.C0005c.d
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void mo29a(g gVar) {
            if (gVar == null) {
                this.f16a.callbackFailAsync(1, this.f17b);
                return;
            }
            int i9 = gVar.f27b;
            if (i9 == 0) {
                this.f16a.callbackSuccessAsync(gVar.f26a, this.f17b);
            } else {
                this.f16a.callbackFailAsync(i9, this.f17b);
            }
        }
    }

    /* renamed from: a0.b$c */
    public class c implements C0005c.d<g> {

        /* renamed from: a */
        public final /* synthetic */ String f18a;

        public c(String str) {
            this.f18a = str;
        }

        @Override // p001a0.C0005c.d
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void mo29a(g gVar) {
            synchronized (C0004b.f9c) {
                C5308g<String, ArrayList<C0005c.d<g>>> c5308g = C0004b.f10d;
                ArrayList<C0005c.d<g>> arrayList = c5308g.get(this.f18a);
                if (arrayList == null) {
                    return;
                }
                c5308g.remove(this.f18a);
                for (int i9 = 0; i9 < arrayList.size(); i9++) {
                    arrayList.get(i9).mo29a(gVar);
                }
            }
        }
    }

    /* renamed from: a0.b$d */
    public class d implements Comparator<byte[]> {
        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(byte[] bArr, byte[] bArr2) {
            if (bArr.length != bArr2.length) {
                return bArr.length - bArr2.length;
            }
            for (int i9 = 0; i9 < bArr.length; i9++) {
                byte b9 = bArr[i9];
                byte b10 = bArr2[i9];
                if (b9 != b10) {
                    return b9 - b10;
                }
            }
            return 0;
        }
    }

    /* renamed from: a0.b$e */
    public static class e {

        /* renamed from: a */
        public final int f19a;

        /* renamed from: b */
        public final f[] f20b;

        public e(int i9, f[] fVarArr) {
            this.f19a = i9;
            this.f20b = fVarArr;
        }

        /* renamed from: a */
        public f[] m33a() {
            return this.f20b;
        }

        /* renamed from: b */
        public int m34b() {
            return this.f19a;
        }
    }

    /* renamed from: a0.b$f */
    public static class f {

        /* renamed from: a */
        public final Uri f21a;

        /* renamed from: b */
        public final int f22b;

        /* renamed from: c */
        public final int f23c;

        /* renamed from: d */
        public final boolean f24d;

        /* renamed from: e */
        public final int f25e;

        public f(Uri uri, int i9, int i10, boolean z8, int i11) {
            this.f21a = (Uri) C0702h.m3468b(uri);
            this.f22b = i9;
            this.f23c = i10;
            this.f24d = z8;
            this.f25e = i11;
        }

        /* renamed from: a */
        public int m35a() {
            return this.f25e;
        }

        /* renamed from: b */
        public int m36b() {
            return this.f22b;
        }

        /* renamed from: c */
        public Uri m37c() {
            return this.f21a;
        }

        /* renamed from: d */
        public int m38d() {
            return this.f23c;
        }

        /* renamed from: e */
        public boolean m39e() {
            return this.f24d;
        }
    }

    /* renamed from: a0.b$g */
    public static final class g {

        /* renamed from: a */
        public final Typeface f26a;

        /* renamed from: b */
        public final int f27b;

        public g(Typeface typeface, int i9) {
            this.f26a = typeface;
            this.f27b = i9;
        }
    }

    /* renamed from: a */
    public static List<byte[]> m19a(Signature[] signatureArr) {
        ArrayList arrayList = new ArrayList();
        for (Signature signature : signatureArr) {
            arrayList.add(signature.toByteArray());
        }
        return arrayList;
    }

    /* renamed from: b */
    public static boolean m20b(List<byte[]> list, List<byte[]> list2) {
        if (list.size() != list2.size()) {
            return false;
        }
        for (int i9 = 0; i9 < list.size(); i9++) {
            if (!Arrays.equals(list.get(i9), list2.get(i9))) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: c */
    public static e m21c(Context context, CancellationSignal cancellationSignal, C0003a c0003a) throws PackageManager.NameNotFoundException {
        ProviderInfo providerInfoM26h = m26h(context.getPackageManager(), c0003a, context.getResources());
        return providerInfoM26h == null ? new e(1, null) : new e(0, m23e(context, c0003a, providerInfoM26h.authority, cancellationSignal));
    }

    /* renamed from: d */
    public static List<List<byte[]>> m22d(C0003a c0003a, Resources resources) {
        return c0003a.m13a() != null ? c0003a.m13a() : C6347a.m24347c(resources, c0003a.m14b());
    }

    /* renamed from: e */
    public static f[] m23e(Context context, C0003a c0003a, String str, CancellationSignal cancellationSignal) {
        ArrayList arrayList = new ArrayList();
        Uri uriBuild = new Uri.Builder().scheme(FirebaseAnalytics.Param.CONTENT).authority(str).build();
        Uri uriBuild2 = new Uri.Builder().scheme(FirebaseAnalytics.Param.CONTENT).authority(str).appendPath("file").build();
        Cursor cursorQuery = null;
        try {
            cursorQuery = context.getContentResolver().query(uriBuild, new String[]{"_id", "file_id", "font_ttc_index", "font_variation_settings", "font_weight", "font_italic", "result_code"}, "query = ?", new String[]{c0003a.m18f()}, null, cancellationSignal);
            if (cursorQuery != null && cursorQuery.getCount() > 0) {
                int columnIndex = cursorQuery.getColumnIndex("result_code");
                ArrayList arrayList2 = new ArrayList();
                int columnIndex2 = cursorQuery.getColumnIndex("_id");
                int columnIndex3 = cursorQuery.getColumnIndex("file_id");
                int columnIndex4 = cursorQuery.getColumnIndex("font_ttc_index");
                int columnIndex5 = cursorQuery.getColumnIndex("font_weight");
                int columnIndex6 = cursorQuery.getColumnIndex("font_italic");
                while (cursorQuery.moveToNext()) {
                    int i9 = columnIndex != -1 ? cursorQuery.getInt(columnIndex) : 0;
                    arrayList2.add(new f(columnIndex3 == -1 ? ContentUris.withAppendedId(uriBuild, cursorQuery.getLong(columnIndex2)) : ContentUris.withAppendedId(uriBuild2, cursorQuery.getLong(columnIndex3)), columnIndex4 != -1 ? cursorQuery.getInt(columnIndex4) : 0, columnIndex5 != -1 ? cursorQuery.getInt(columnIndex5) : 400, columnIndex6 != -1 && cursorQuery.getInt(columnIndex6) == 1, i9));
                }
                arrayList = arrayList2;
            }
            return (f[]) arrayList.toArray(new f[0]);
        } finally {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        }
    }

    /* renamed from: f */
    public static g m24f(Context context, C0003a c0003a, int i9) {
        try {
            e eVarM21c = m21c(context, null, c0003a);
            if (eVarM21c.m34b() != 0) {
                return new g(null, eVarM21c.m34b() == 1 ? -2 : -3);
            }
            Typeface typefaceM24595b = C6430d.m24595b(context, null, eVarM21c.m33a(), i9);
            return new g(typefaceM24595b, typefaceM24595b != null ? 0 : -3);
        } catch (PackageManager.NameNotFoundException unused) {
            return new g(null, -1);
        }
    }

    /* renamed from: g */
    public static Typeface m25g(Context context, C0003a c0003a, C6348b.a aVar, Handler handler, boolean z8, int i9, int i10) {
        String str = c0003a.m15c() + "-" + i10;
        Typeface typeface = f7a.get(str);
        if (typeface != null) {
            if (aVar != null) {
                aVar.onFontRetrieved(typeface);
            }
            return typeface;
        }
        if (z8 && i9 == -1) {
            g gVarM24f = m24f(context, c0003a, i10);
            if (aVar != null) {
                int i11 = gVarM24f.f27b;
                if (i11 == 0) {
                    aVar.callbackSuccessAsync(gVarM24f.f26a, handler);
                } else {
                    aVar.callbackFailAsync(i11, handler);
                }
            }
            return gVarM24f.f26a;
        }
        a aVar2 = new a(context, c0003a, i10, str);
        if (z8) {
            try {
                return ((g) f8b.m44e(aVar2, i9)).f26a;
            } catch (InterruptedException unused) {
                return null;
            }
        }
        b bVar = aVar == null ? null : new b(aVar, handler);
        synchronized (f9c) {
            C5308g<String, ArrayList<C0005c.d<g>>> c5308g = f10d;
            ArrayList<C0005c.d<g>> arrayList = c5308g.get(str);
            if (arrayList != null) {
                if (bVar != null) {
                    arrayList.add(bVar);
                }
                return null;
            }
            if (bVar != null) {
                ArrayList<C0005c.d<g>> arrayList2 = new ArrayList<>();
                arrayList2.add(bVar);
                c5308g.put(str, arrayList2);
            }
            f8b.m43d(aVar2, new c(str));
            return null;
        }
    }

    /* renamed from: h */
    public static ProviderInfo m26h(PackageManager packageManager, C0003a c0003a, Resources resources) throws PackageManager.NameNotFoundException {
        String strM16d = c0003a.m16d();
        ProviderInfo providerInfoResolveContentProvider = packageManager.resolveContentProvider(strM16d, 0);
        if (providerInfoResolveContentProvider == null) {
            throw new PackageManager.NameNotFoundException("No package found for authority: " + strM16d);
        }
        if (!providerInfoResolveContentProvider.packageName.equals(c0003a.m17e())) {
            throw new PackageManager.NameNotFoundException("Found content provider " + strM16d + ", but package was not " + c0003a.m17e());
        }
        List<byte[]> listM19a = m19a(packageManager.getPackageInfo(providerInfoResolveContentProvider.packageName, 64).signatures);
        Collections.sort(listM19a, f11e);
        List<List<byte[]>> listM22d = m22d(c0003a, resources);
        for (int i9 = 0; i9 < listM22d.size(); i9++) {
            ArrayList arrayList = new ArrayList(listM22d.get(i9));
            Collections.sort(arrayList, f11e);
            if (m20b(listM19a, arrayList)) {
                return providerInfoResolveContentProvider;
            }
        }
        return null;
    }

    /* renamed from: i */
    public static Map<Uri, ByteBuffer> m27i(Context context, f[] fVarArr, CancellationSignal cancellationSignal) {
        HashMap map = new HashMap();
        for (f fVar : fVarArr) {
            if (fVar.m35a() == 0) {
                Uri uriM37c = fVar.m37c();
                if (!map.containsKey(uriM37c)) {
                    map.put(uriM37c, C6445s.m24640e(context, cancellationSignal, uriM37c));
                }
            }
        }
        return Collections.unmodifiableMap(map);
    }
}
