package p073g1;

import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import java.io.InputStream;
import org.apache.commons.p159io.IOUtils;
import p073g1.InterfaceC4820n;
import p243y0.C6592e;

/* renamed from: g1.s */
/* loaded from: classes.dex */
public class C4825s<Data> implements InterfaceC4820n<Integer, Data> {

    /* renamed from: a */
    public final InterfaceC4820n<Uri, Data> f16800a;

    /* renamed from: b */
    public final Resources f16801b;

    /* renamed from: g1.s$a */
    public static final class a implements InterfaceC4821o<Integer, AssetFileDescriptor> {

        /* renamed from: a */
        public final Resources f16802a;

        public a(Resources resources) {
            this.f16802a = resources;
        }

        @Override // p073g1.InterfaceC4821o
        /* renamed from: a */
        public InterfaceC4820n<Integer, AssetFileDescriptor> mo3831a(C4824r c4824r) {
            return new C4825s(this.f16802a, c4824r.m19142d(Uri.class, AssetFileDescriptor.class));
        }

        @Override // p073g1.InterfaceC4821o
        /* renamed from: b */
        public void mo3832b() {
        }
    }

    /* renamed from: g1.s$b */
    public static class b implements InterfaceC4821o<Integer, ParcelFileDescriptor> {

        /* renamed from: a */
        public final Resources f16803a;

        public b(Resources resources) {
            this.f16803a = resources;
        }

        @Override // p073g1.InterfaceC4821o
        /* renamed from: a */
        public InterfaceC4820n<Integer, ParcelFileDescriptor> mo3831a(C4824r c4824r) {
            return new C4825s(this.f16803a, c4824r.m19142d(Uri.class, ParcelFileDescriptor.class));
        }

        @Override // p073g1.InterfaceC4821o
        /* renamed from: b */
        public void mo3832b() {
        }
    }

    /* renamed from: g1.s$c */
    public static class c implements InterfaceC4821o<Integer, InputStream> {

        /* renamed from: a */
        public final Resources f16804a;

        public c(Resources resources) {
            this.f16804a = resources;
        }

        @Override // p073g1.InterfaceC4821o
        /* renamed from: a */
        public InterfaceC4820n<Integer, InputStream> mo3831a(C4824r c4824r) {
            return new C4825s(this.f16804a, c4824r.m19142d(Uri.class, InputStream.class));
        }

        @Override // p073g1.InterfaceC4821o
        /* renamed from: b */
        public void mo3832b() {
        }
    }

    /* renamed from: g1.s$d */
    public static class d implements InterfaceC4821o<Integer, Uri> {

        /* renamed from: a */
        public final Resources f16805a;

        public d(Resources resources) {
            this.f16805a = resources;
        }

        @Override // p073g1.InterfaceC4821o
        /* renamed from: a */
        public InterfaceC4820n<Integer, Uri> mo3831a(C4824r c4824r) {
            return new C4825s(this.f16805a, C4828v.m19160c());
        }

        @Override // p073g1.InterfaceC4821o
        /* renamed from: b */
        public void mo3832b() {
        }
    }

    public C4825s(Resources resources, InterfaceC4820n<Uri, Data> interfaceC4820n) {
        this.f16801b = resources;
        this.f16800a = interfaceC4820n;
    }

    @Override // p073g1.InterfaceC4820n
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public InterfaceC4820n.a<Data> mo3827b(Integer num, int i9, int i10, C6592e c6592e) {
        Uri uriM19153d = m19153d(num);
        if (uriM19153d == null) {
            return null;
        }
        return this.f16800a.mo3827b(uriM19153d, i9, i10, c6592e);
    }

    /* renamed from: d */
    public final Uri m19153d(Integer num) {
        try {
            return Uri.parse("android.resource://" + this.f16801b.getResourcePackageName(num.intValue()) + IOUtils.DIR_SEPARATOR_UNIX + this.f16801b.getResourceTypeName(num.intValue()) + IOUtils.DIR_SEPARATOR_UNIX + this.f16801b.getResourceEntryName(num.intValue()));
        } catch (Resources.NotFoundException e9) {
            if (!Log.isLoggable("ResourceLoader", 5)) {
                return null;
            }
            Log.w("ResourceLoader", "Received invalid resource id: " + num, e9);
            return null;
        }
    }

    @Override // p073g1.InterfaceC4820n
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public boolean mo3826a(Integer num) {
        return true;
    }
}
