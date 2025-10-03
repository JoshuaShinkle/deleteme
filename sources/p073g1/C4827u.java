package p073g1;

import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import java.io.File;
import java.io.InputStream;
import p073g1.InterfaceC4820n;
import p243y0.C6592e;

/* renamed from: g1.u */
/* loaded from: classes.dex */
public class C4827u<Data> implements InterfaceC4820n<String, Data> {

    /* renamed from: a */
    public final InterfaceC4820n<Uri, Data> f16807a;

    /* renamed from: g1.u$a */
    public static final class a implements InterfaceC4821o<String, AssetFileDescriptor> {
        @Override // p073g1.InterfaceC4821o
        /* renamed from: a */
        public InterfaceC4820n<String, AssetFileDescriptor> mo3831a(C4824r c4824r) {
            return new C4827u(c4824r.m19142d(Uri.class, AssetFileDescriptor.class));
        }

        @Override // p073g1.InterfaceC4821o
        /* renamed from: b */
        public void mo3832b() {
        }
    }

    /* renamed from: g1.u$b */
    public static class b implements InterfaceC4821o<String, ParcelFileDescriptor> {
        @Override // p073g1.InterfaceC4821o
        /* renamed from: a */
        public InterfaceC4820n<String, ParcelFileDescriptor> mo3831a(C4824r c4824r) {
            return new C4827u(c4824r.m19142d(Uri.class, ParcelFileDescriptor.class));
        }

        @Override // p073g1.InterfaceC4821o
        /* renamed from: b */
        public void mo3832b() {
        }
    }

    /* renamed from: g1.u$c */
    public static class c implements InterfaceC4821o<String, InputStream> {
        @Override // p073g1.InterfaceC4821o
        /* renamed from: a */
        public InterfaceC4820n<String, InputStream> mo3831a(C4824r c4824r) {
            return new C4827u(c4824r.m19142d(Uri.class, InputStream.class));
        }

        @Override // p073g1.InterfaceC4821o
        /* renamed from: b */
        public void mo3832b() {
        }
    }

    public C4827u(InterfaceC4820n<Uri, Data> interfaceC4820n) {
        this.f16807a = interfaceC4820n;
    }

    /* renamed from: e */
    public static Uri m19156e(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (str.charAt(0) == '/') {
            return m19157f(str);
        }
        Uri uri = Uri.parse(str);
        return uri.getScheme() == null ? m19157f(str) : uri;
    }

    /* renamed from: f */
    public static Uri m19157f(String str) {
        return Uri.fromFile(new File(str));
    }

    @Override // p073g1.InterfaceC4820n
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public InterfaceC4820n.a<Data> mo3827b(String str, int i9, int i10, C6592e c6592e) {
        Uri uriM19156e = m19156e(str);
        if (uriM19156e == null || !this.f16807a.mo3826a(uriM19156e)) {
            return null;
        }
        return this.f16807a.mo3827b(uriM19156e, i9, i10, c6592e);
    }

    @Override // p073g1.InterfaceC4820n
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean mo3826a(String str) {
        return true;
    }
}
