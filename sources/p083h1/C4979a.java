package p083h1;

import com.google.android.exoplayer2.DefaultLoadControl;
import java.io.InputStream;
import p073g1.C4813g;
import p073g1.C4819m;
import p073g1.C4824r;
import p073g1.InterfaceC4820n;
import p073g1.InterfaceC4821o;
import p243y0.C6591d;
import p243y0.C6592e;
import p252z0.C6811j;

/* renamed from: h1.a */
/* loaded from: classes.dex */
public class C4979a implements InterfaceC4820n<C4813g, InputStream> {

    /* renamed from: b */
    public static final C6591d<Integer> f17154b = C6591d.m25204f("com.bumptech.glide.load.model.stream.HttpGlideUrlLoader.Timeout", Integer.valueOf(DefaultLoadControl.DEFAULT_BUFFER_FOR_PLAYBACK_MS));

    /* renamed from: a */
    public final C4819m<C4813g, C4813g> f17155a;

    /* renamed from: h1.a$a */
    public static class a implements InterfaceC4821o<C4813g, InputStream> {

        /* renamed from: a */
        public final C4819m<C4813g, C4813g> f17156a = new C4819m<>(500);

        @Override // p073g1.InterfaceC4821o
        /* renamed from: a */
        public InterfaceC4820n<C4813g, InputStream> mo3831a(C4824r c4824r) {
            return new C4979a(this.f17156a);
        }

        @Override // p073g1.InterfaceC4821o
        /* renamed from: b */
        public void mo3832b() {
        }
    }

    public C4979a(C4819m<C4813g, C4813g> c4819m) {
        this.f17155a = c4819m;
    }

    @Override // p073g1.InterfaceC4820n
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public InterfaceC4820n.a<InputStream> mo3827b(C4813g c4813g, int i9, int i10, C6592e c6592e) {
        C4819m<C4813g, C4813g> c4819m = this.f17155a;
        if (c4819m != null) {
            C4813g c4813gM19120a = c4819m.m19120a(c4813g, 0, 0);
            if (c4813gM19120a == null) {
                this.f17155a.m19121b(c4813g, 0, 0, c4813g);
            } else {
                c4813g = c4813gM19120a;
            }
        }
        return new InterfaceC4820n.a<>(c4813g, new C6811j(c4813g, ((Integer) c6592e.m25209c(f17154b)).intValue()));
    }

    @Override // p073g1.InterfaceC4820n
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean mo3826a(C4813g c4813g) {
        return true;
    }
}
