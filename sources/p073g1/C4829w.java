package p073g1;

import android.content.ContentResolver;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import p073g1.InterfaceC4820n;
import p217v1.C6451c;
import p243y0.C6592e;
import p252z0.C6802a;
import p252z0.C6810i;
import p252z0.C6815n;
import p252z0.InterfaceC6805d;

/* renamed from: g1.w */
/* loaded from: classes.dex */
public class C4829w<Data> implements InterfaceC4820n<Uri, Data> {

    /* renamed from: b */
    public static final Set<String> f16811b = Collections.unmodifiableSet(new HashSet(Arrays.asList("file", "android.resource", FirebaseAnalytics.Param.CONTENT)));

    /* renamed from: a */
    public final c<Data> f16812a;

    /* renamed from: g1.w$a */
    public static final class a implements InterfaceC4821o<Uri, AssetFileDescriptor>, c<AssetFileDescriptor> {

        /* renamed from: a */
        public final ContentResolver f16813a;

        public a(ContentResolver contentResolver) {
            this.f16813a = contentResolver;
        }

        @Override // p073g1.InterfaceC4821o
        /* renamed from: a */
        public InterfaceC4820n<Uri, AssetFileDescriptor> mo3831a(C4824r c4824r) {
            return new C4829w(this);
        }

        @Override // p073g1.InterfaceC4821o
        /* renamed from: b */
        public void mo3832b() {
        }

        @Override // p073g1.C4829w.c
        /* renamed from: c */
        public InterfaceC6805d<AssetFileDescriptor> mo19164c(Uri uri) {
            return new C6802a(this.f16813a, uri);
        }
    }

    /* renamed from: g1.w$b */
    public static class b implements InterfaceC4821o<Uri, ParcelFileDescriptor>, c<ParcelFileDescriptor> {

        /* renamed from: a */
        public final ContentResolver f16814a;

        public b(ContentResolver contentResolver) {
            this.f16814a = contentResolver;
        }

        @Override // p073g1.InterfaceC4821o
        /* renamed from: a */
        public InterfaceC4820n<Uri, ParcelFileDescriptor> mo3831a(C4824r c4824r) {
            return new C4829w(this);
        }

        @Override // p073g1.InterfaceC4821o
        /* renamed from: b */
        public void mo3832b() {
        }

        @Override // p073g1.C4829w.c
        /* renamed from: c */
        public InterfaceC6805d<ParcelFileDescriptor> mo19164c(Uri uri) {
            return new C6810i(this.f16814a, uri);
        }
    }

    /* renamed from: g1.w$c */
    public interface c<Data> {
        /* renamed from: c */
        InterfaceC6805d<Data> mo19164c(Uri uri);
    }

    /* renamed from: g1.w$d */
    public static class d implements InterfaceC4821o<Uri, InputStream>, c<InputStream> {

        /* renamed from: a */
        public final ContentResolver f16815a;

        public d(ContentResolver contentResolver) {
            this.f16815a = contentResolver;
        }

        @Override // p073g1.InterfaceC4821o
        /* renamed from: a */
        public InterfaceC4820n<Uri, InputStream> mo3831a(C4824r c4824r) {
            return new C4829w(this);
        }

        @Override // p073g1.InterfaceC4821o
        /* renamed from: b */
        public void mo3832b() {
        }

        @Override // p073g1.C4829w.c
        /* renamed from: c */
        public InterfaceC6805d<InputStream> mo19164c(Uri uri) {
            return new C6815n(this.f16815a, uri);
        }
    }

    public C4829w(c<Data> cVar) {
        this.f16812a = cVar;
    }

    @Override // p073g1.InterfaceC4820n
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public InterfaceC4820n.a<Data> mo3827b(Uri uri, int i9, int i10, C6592e c6592e) {
        return new InterfaceC4820n.a<>(new C6451c(uri), this.f16812a.mo19164c(uri));
    }

    @Override // p073g1.InterfaceC4820n
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean mo3826a(Uri uri) {
        return f16811b.contains(uri.getScheme());
    }
}
