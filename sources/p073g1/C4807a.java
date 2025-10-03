package p073g1;

import android.content.res.AssetManager;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import java.io.InputStream;
import p073g1.InterfaceC4820n;
import p217v1.C6451c;
import p243y0.C6592e;
import p252z0.C6809h;
import p252z0.C6814m;
import p252z0.InterfaceC6805d;

/* renamed from: g1.a */
/* loaded from: classes.dex */
public class C4807a<Data> implements InterfaceC4820n<Uri, Data> {

    /* renamed from: c */
    public static final int f16726c = 22;

    /* renamed from: a */
    public final AssetManager f16727a;

    /* renamed from: b */
    public final a<Data> f16728b;

    /* renamed from: g1.a$a */
    public interface a<Data> {
        /* renamed from: c */
        InterfaceC6805d<Data> mo19079c(AssetManager assetManager, String str);
    }

    /* renamed from: g1.a$b */
    public static class b implements InterfaceC4821o<Uri, ParcelFileDescriptor>, a<ParcelFileDescriptor> {

        /* renamed from: a */
        public final AssetManager f16729a;

        public b(AssetManager assetManager) {
            this.f16729a = assetManager;
        }

        @Override // p073g1.InterfaceC4821o
        /* renamed from: a */
        public InterfaceC4820n<Uri, ParcelFileDescriptor> mo3831a(C4824r c4824r) {
            return new C4807a(this.f16729a, this);
        }

        @Override // p073g1.InterfaceC4821o
        /* renamed from: b */
        public void mo3832b() {
        }

        @Override // p073g1.C4807a.a
        /* renamed from: c */
        public InterfaceC6805d<ParcelFileDescriptor> mo19079c(AssetManager assetManager, String str) {
            return new C6809h(assetManager, str);
        }
    }

    /* renamed from: g1.a$c */
    public static class c implements InterfaceC4821o<Uri, InputStream>, a<InputStream> {

        /* renamed from: a */
        public final AssetManager f16730a;

        public c(AssetManager assetManager) {
            this.f16730a = assetManager;
        }

        @Override // p073g1.InterfaceC4821o
        /* renamed from: a */
        public InterfaceC4820n<Uri, InputStream> mo3831a(C4824r c4824r) {
            return new C4807a(this.f16730a, this);
        }

        @Override // p073g1.InterfaceC4821o
        /* renamed from: b */
        public void mo3832b() {
        }

        @Override // p073g1.C4807a.a
        /* renamed from: c */
        public InterfaceC6805d<InputStream> mo19079c(AssetManager assetManager, String str) {
            return new C6814m(assetManager, str);
        }
    }

    public C4807a(AssetManager assetManager, a<Data> aVar) {
        this.f16727a = assetManager;
        this.f16728b = aVar;
    }

    @Override // p073g1.InterfaceC4820n
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public InterfaceC4820n.a<Data> mo3827b(Uri uri, int i9, int i10, C6592e c6592e) {
        return new InterfaceC4820n.a<>(new C6451c(uri), this.f16728b.mo19079c(this.f16727a, uri.toString().substring(f16726c)));
    }

    @Override // p073g1.InterfaceC4820n
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean mo3826a(Uri uri) {
        return "file".equals(uri.getScheme()) && !uri.getPathSegments().isEmpty() && "android_asset".equals(uri.getPathSegments().get(0));
    }
}
