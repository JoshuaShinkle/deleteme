package p094i2;

import com.cyberlink.link.preview.LIFOBlockingDeque;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import p209u2.ThreadFactoryC6373j;

/* renamed from: i2.a */
/* loaded from: classes.dex */
public class C5044a {

    /* renamed from: a */
    public final ThreadPoolExecutor f17411a;

    /* renamed from: b */
    public final ThreadPoolExecutor f17412b;

    /* renamed from: i2.a$a */
    public interface a<K, V> {
        /* renamed from: a */
        void mo19700a(K k9, V v8);
    }

    public C5044a() {
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        this.f17411a = new ThreadPoolExecutor(1, 1, 0L, timeUnit, new LIFOBlockingDeque(), new ThreadFactoryC6373j("PreviewMultipleData"));
        this.f17412b = new ThreadPoolExecutor(3, 5, 10L, timeUnit, new LinkedBlockingDeque(), new ThreadFactoryC6373j("PreviewDataTask"));
    }

    /* renamed from: a */
    public void m19697a() {
        this.f17411a.shutdownNow();
        this.f17412b.shutdownNow();
    }

    /* renamed from: b */
    public void m19698b(String str, List<String> list, a<String, List<C5047d>> aVar) {
        this.f17411a.execute(new RunnableC5046c(str, list, aVar, this.f17412b));
    }

    /* renamed from: c */
    public boolean m19699c() {
        return this.f17411a.isShutdown();
    }
}
