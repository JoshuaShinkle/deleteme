package p097i5;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.perfectcorp.ycl.commons.concurrent.CallingThread;
import java.util.concurrent.Executor;
import p077g5.C4959a;

/* renamed from: i5.b */
/* loaded from: classes2.dex */
public final class C5053b {

    /* renamed from: i5.b$a */
    public static class a<V> implements FutureCallback<V> {

        /* renamed from: a */
        public final InterfaceC5052a<V> f17439a;

        public a(InterfaceC5052a<V> interfaceC5052a) {
            this.f17439a = (InterfaceC5052a) C4959a.m19234a(interfaceC5052a);
        }

        @Override // com.google.common.util.concurrent.FutureCallback
        public void onFailure(Throwable th) {
            try {
                this.f17439a.onFailure(th);
            } finally {
                this.f17439a.onFinish();
            }
        }

        @Override // com.google.common.util.concurrent.FutureCallback
        public void onSuccess(V v8) {
            try {
                this.f17439a.onSuccess(v8);
            } finally {
                this.f17439a.onFinish();
            }
        }
    }

    /* renamed from: a */
    public static <V> ListenableFuture<V> m19752a(ListenableFuture<V> listenableFuture, FutureCallback<? super V> futureCallback) {
        return m19753b(listenableFuture, futureCallback, CallingThread.MAIN);
    }

    /* renamed from: b */
    public static <V> ListenableFuture<V> m19753b(ListenableFuture<V> listenableFuture, FutureCallback<? super V> futureCallback, Executor executor) {
        if (futureCallback instanceof InterfaceC5052a) {
            futureCallback = m19754c((InterfaceC5052a) futureCallback);
        }
        Futures.addCallback(listenableFuture, futureCallback, executor);
        return listenableFuture;
    }

    /* renamed from: c */
    public static <V> FutureCallback<V> m19754c(InterfaceC5052a<V> interfaceC5052a) {
        return new a(interfaceC5052a);
    }
}
