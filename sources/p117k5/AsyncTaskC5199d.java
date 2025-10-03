package p117k5;

import com.perfectcorp.ycl.network.NetworkManager;
import com.perfectcorp.ycl.network.downloader.task.ResponseError;
import p087h5.AbstractAsyncTaskC5023a;

/* renamed from: k5.d */
/* loaded from: classes2.dex */
public class AsyncTaskC5199d extends AbstractAsyncTaskC5023a<Void, Void, Void> {

    /* renamed from: d */
    public final AbstractC5198c f17808d;

    public AsyncTaskC5199d(AbstractC5198c abstractC5198c) {
        this.f17808d = abstractC5198c;
    }

    @Override // p087h5.AbstractAsyncTaskC5023a
    /* renamed from: f, reason: merged with bridge method [inline-methods] */
    public Void mo19588a(Void... voidArr) {
        try {
            if (NetworkManager.m18159d()) {
                this.f17808d.mo20319c();
            } else {
                this.f17808d.m20320a(new ResponseError(null, new NetworkManager.NoConnectionException()));
            }
        } catch (Exception unused) {
        }
        return null;
    }
}
