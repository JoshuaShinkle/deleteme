package p234x0;

import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.HttpException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import okhttp3.AbstractC5483a0;
import okhttp3.C5523x;
import okhttp3.C5525z;
import okhttp3.InterfaceC5488e;
import okhttp3.InterfaceC5489f;
import p073g1.C4813g;
import p226w1.C6510c;
import p226w1.C6516i;
import p252z0.InterfaceC6805d;

/* renamed from: x0.a */
/* loaded from: classes.dex */
public class C6562a implements InterfaceC6805d<InputStream>, InterfaceC5489f {

    /* renamed from: b */
    public final InterfaceC5488e.a f22063b;

    /* renamed from: c */
    public final C4813g f22064c;

    /* renamed from: d */
    public InputStream f22065d;

    /* renamed from: e */
    public AbstractC5483a0 f22066e;

    /* renamed from: f */
    public InterfaceC6805d.a<? super InputStream> f22067f;

    /* renamed from: g */
    public volatile InterfaceC5488e f22068g;

    public C6562a(InterfaceC5488e.a aVar, C4813g c4813g) {
        this.f22063b = aVar;
        this.f22064c = c4813g;
    }

    @Override // p252z0.InterfaceC6805d
    /* renamed from: a */
    public Class<InputStream> mo56a() {
        return InputStream.class;
    }

    @Override // p252z0.InterfaceC6805d
    /* renamed from: b */
    public void mo57b() throws IOException {
        try {
            InputStream inputStream = this.f22065d;
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (IOException unused) {
        }
        AbstractC5483a0 abstractC5483a0 = this.f22066e;
        if (abstractC5483a0 != null) {
            abstractC5483a0.close();
        }
        this.f22067f = null;
    }

    @Override // okhttp3.InterfaceC5489f
    /* renamed from: c */
    public void mo7305c(InterfaceC5488e interfaceC5488e, C5525z c5525z) {
        this.f22066e = c5525z.m21849f();
        if (!c5525z.m21840D()) {
            this.f22067f.mo3902c(new HttpException(c5525z.m21841E(), c5525z.m21853x()));
            return;
        }
        InputStream inputStreamM24917u = C6510c.m24917u(this.f22066e.m21229f(), ((AbstractC5483a0) C6516i.m24938d(this.f22066e)).mo20968v());
        this.f22065d = inputStreamM24917u;
        this.f22067f.mo3903f(inputStreamM24917u);
    }

    @Override // p252z0.InterfaceC6805d
    public void cancel() {
        InterfaceC5488e interfaceC5488e = this.f22068g;
        if (interfaceC5488e != null) {
            interfaceC5488e.cancel();
        }
    }

    @Override // okhttp3.InterfaceC5489f
    /* renamed from: d */
    public void mo7306d(InterfaceC5488e interfaceC5488e, IOException iOException) {
        if (Log.isLoggable("OkHttpFetcher", 3)) {
            Log.d("OkHttpFetcher", "OkHttp failed to obtain result", iOException);
        }
        this.f22067f.mo3902c(iOException);
    }

    @Override // p252z0.InterfaceC6805d
    /* renamed from: e */
    public DataSource mo58e() {
        return DataSource.REMOTE;
    }

    @Override // p252z0.InterfaceC6805d
    /* renamed from: g */
    public void mo59g(Priority priority, InterfaceC6805d.a<? super InputStream> aVar) {
        C5523x.a aVarM21820i = new C5523x.a().m21820i(this.f22064c.m19109h());
        for (Map.Entry<String, String> entry : this.f22064c.m19106e().entrySet()) {
            aVarM21820i.m21812a(entry.getKey(), entry.getValue());
        }
        C5523x c5523xM21813b = aVarM21820i.m21813b();
        this.f22067f = aVar;
        this.f22068g = this.f22063b.mo21256a(c5523xM21813b);
        this.f22068g.mo21255f(this);
    }
}
