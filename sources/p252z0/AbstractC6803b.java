package p252z0;

import android.content.res.AssetManager;
import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import java.io.IOException;
import p252z0.InterfaceC6805d;

/* renamed from: z0.b */
/* loaded from: classes.dex */
public abstract class AbstractC6803b<T> implements InterfaceC6805d<T> {

    /* renamed from: b */
    public final String f22542b;

    /* renamed from: c */
    public final AssetManager f22543c;

    /* renamed from: d */
    public T f22544d;

    public AbstractC6803b(AssetManager assetManager, String str) {
        this.f22543c = assetManager;
        this.f22542b = str;
    }

    @Override // p252z0.InterfaceC6805d
    /* renamed from: b */
    public void mo57b() {
        T t8 = this.f22544d;
        if (t8 == null) {
            return;
        }
        try {
            mo25362c(t8);
        } catch (IOException unused) {
        }
    }

    /* renamed from: c */
    public abstract void mo25362c(T t8);

    @Override // p252z0.InterfaceC6805d
    public void cancel() {
    }

    /* renamed from: d */
    public abstract T mo25363d(AssetManager assetManager, String str);

    @Override // p252z0.InterfaceC6805d
    /* renamed from: e */
    public DataSource mo58e() {
        return DataSource.LOCAL;
    }

    @Override // p252z0.InterfaceC6805d
    /* renamed from: g */
    public void mo59g(Priority priority, InterfaceC6805d.a<? super T> aVar) {
        try {
            T tMo25363d = mo25363d(this.f22543c, this.f22542b);
            this.f22544d = tMo25363d;
            aVar.mo3903f(tMo25363d);
        } catch (IOException e9) {
            if (Log.isLoggable("AssetPathFetcher", 3)) {
                Log.d("AssetPathFetcher", "Failed to load data from asset manager", e9);
            }
            aVar.mo3902c(e9);
        }
    }
}
