package com.perfectcorp.ycl.network;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import com.google.common.util.concurrent.ListenableFuture;
import com.perfectcorp.ycl.commons.utility.Log;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import p047d5.C4677a;
import p087h5.AbstractAsyncTaskC5023a;
import p117k5.AbstractC5198c;
import p117k5.AsyncTaskC5199d;

/* loaded from: classes2.dex */
public enum NetworkManager {
    INSTANCE;


    /* renamed from: c */
    public static final String f16011c;

    /* renamed from: d */
    public static final String f16012d;

    /* renamed from: e */
    public static Exception f16013e;
    private final C4576b mExecutor = new C4576b();

    public static final class NoConnectionException extends RuntimeException {
    }

    public enum ResponseStatus {
        OK,
        ERROR
    }

    public enum Status {
        READY,
        BUSY
    }

    public enum TaskPriority {
        LOWEST_TASK_PRIORITY,
        HIGHEST_TASK_PRIORITY
    }

    /* renamed from: com.perfectcorp.ycl.network.NetworkManager$b */
    public final class C4576b extends AbstractC4577c<AbstractC5198c<?>> {
        public C4576b() {
            super();
        }

        @Override // com.perfectcorp.ycl.network.NetworkManager.AbstractC4577c
        /* renamed from: b */
        public void mo18161b() {
            int size = this.f16027c.size();
            Log.m18147a("TaskExecutor", "[runNext] size: " + size);
            if (size <= 0) {
                this.f16029e = Status.READY;
                return;
            }
            for (int i9 = 0; i9 < size; i9++) {
                AbstractC5198c abstractC5198c = (AbstractC5198c) this.f16027c.remove();
                Log.m18147a("TaskExecutor", "[runNext] task: " + abstractC5198c);
                new AsyncTaskC5199d(abstractC5198c).m19589b(this.f16028d, this.f16026b, new Void[0]);
            }
        }
    }

    /* renamed from: com.perfectcorp.ycl.network.NetworkManager$c */
    public abstract class AbstractC4577c<T extends AbstractC5198c<?>> {

        /* renamed from: a */
        public final String f16025a = "TaskExecutor";

        /* renamed from: b */
        public final ExecutorService f16026b = Executors.newFixedThreadPool(20);

        /* renamed from: c */
        public final BlockingDeque<T> f16027c = new LinkedBlockingDeque();

        /* renamed from: d */
        public final AbstractAsyncTaskC5023a.b<Void> f16028d = new a();

        /* renamed from: e */
        public Status f16029e = Status.READY;

        /* renamed from: com.perfectcorp.ycl.network.NetworkManager$c$a */
        public class a<Result> extends AbstractAsyncTaskC5023a.a<Result> {
            public a() {
            }

            @Override // p087h5.AbstractAsyncTaskC5023a.a, p087h5.AbstractAsyncTaskC5023a.b
            /* renamed from: a */
            public void mo18163a(AbstractAsyncTaskC5023a<?, ?, Result> abstractAsyncTaskC5023a, Throwable th) {
                AbstractC4577c.this.mo18161b();
            }

            @Override // p087h5.AbstractAsyncTaskC5023a.a, p087h5.AbstractAsyncTaskC5023a.b
            /* renamed from: b */
            public void mo18164b(AbstractAsyncTaskC5023a<?, ?, Result> abstractAsyncTaskC5023a, Result result) {
            }

            @Override // p087h5.AbstractAsyncTaskC5023a.a, p087h5.AbstractAsyncTaskC5023a.b
            /* renamed from: c */
            public void mo18165c(AbstractAsyncTaskC5023a<?, ?, Result> abstractAsyncTaskC5023a, Result result) {
                AbstractC4577c.this.mo18161b();
            }

            @Override // p087h5.AbstractAsyncTaskC5023a.a, p087h5.AbstractAsyncTaskC5023a.b
            /* renamed from: d */
            public void mo18166d(AbstractAsyncTaskC5023a<?, ?, Result> abstractAsyncTaskC5023a, Result result) {
            }
        }

        public AbstractC4577c() {
        }

        /* renamed from: a */
        public void m18162a(T t8, TaskPriority taskPriority) {
            Log.m18147a("TaskExecutor", "[add] task: " + t8);
            if (taskPriority == TaskPriority.HIGHEST_TASK_PRIORITY) {
                this.f16027c.addFirst(t8);
            } else {
                this.f16027c.add(t8);
            }
            Status status = this.f16029e;
            Status status2 = Status.BUSY;
            if (status == status2) {
                Log.m18147a("TaskExecutor", "[add] State.BUSY");
                return;
            }
            this.f16029e = status2;
            Log.m18147a("TaskExecutor", "[add] runNext");
            mo18161b();
        }

        /* renamed from: b */
        public abstract void mo18161b();
    }

    static {
        String str = Environment.getExternalStorageDirectory().getAbsolutePath() + "/cyberlink/ycl/";
        f16011c = str;
        f16012d = str + "livefps.config";
        f16013e = new Exception("VERSION_TOO_LOW");
    }

    NetworkManager() {
    }

    /* renamed from: b */
    public static final String m18157b() {
        return m18158c() + C4677a.f16366c + "download";
    }

    /* renamed from: c */
    public static String m18158c() {
        return C4677a.m18710n().getFilesDir().getAbsolutePath();
    }

    /* renamed from: d */
    public static boolean m18159d() {
        NetworkInfo activeNetworkInfo;
        Object systemService = C4677a.m18710n().getSystemService("connectivity");
        return (systemService instanceof ConnectivityManager) && (activeNetworkInfo = ((ConnectivityManager) systemService).getActiveNetworkInfo()) != null && activeNetworkInfo.isConnected();
    }

    /* renamed from: a */
    public synchronized <Result> ListenableFuture<Result> m18160a(AbstractC5198c<Result> abstractC5198c, TaskPriority taskPriority) {
        this.mExecutor.m18162a(abstractC5198c, taskPriority);
        return abstractC5198c.m20321b();
    }
}
