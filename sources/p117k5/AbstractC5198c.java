package p117k5;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.perfectcorp.ycl.network.downloader.task.ResponseError;

/* renamed from: k5.c */
/* loaded from: classes2.dex */
public abstract class AbstractC5198c<Result> {

    /* renamed from: a */
    public final SettableFuture<Result> f17807a = SettableFuture.create();

    /* renamed from: a */
    public final void m20320a(ResponseError responseError) {
        this.f17807a.setException(responseError.getCause());
    }

    /* renamed from: b */
    public final ListenableFuture<Result> m20321b() {
        return this.f17807a;
    }

    /* renamed from: c */
    public abstract void mo20319c();
}
