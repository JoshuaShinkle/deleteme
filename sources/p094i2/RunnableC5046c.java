package p094i2;

import android.text.TextUtils;
import com.cyberlink.you.database.C2950b0;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ThreadPoolExecutor;
import p094i2.C5044a;

/* renamed from: i2.c */
/* loaded from: classes.dex */
public class RunnableC5046c implements Runnable {

    /* renamed from: b */
    public final String f17416b = "PreviewMultipleData";

    /* renamed from: c */
    public String f17417c;

    /* renamed from: d */
    public List<String> f17418d;

    /* renamed from: e */
    public C5044a.a<String, List<C5047d>> f17419e;

    /* renamed from: f */
    public CompletionService<C5047d> f17420f;

    public RunnableC5046c(String str, List<String> list, C5044a.a<String, List<C5047d>> aVar, ThreadPoolExecutor threadPoolExecutor) {
        this.f17417c = str;
        this.f17418d = list;
        this.f17419e = aVar;
        this.f17420f = new ExecutorCompletionService(threadPoolExecutor);
    }

    @Override // java.lang.Runnable
    public void run() {
        boolean z8;
        HashSet<String> hashSet = new HashSet(this.f17418d);
        List<C5047d> listM14966f = C2950b0.m14927z().m14966f(this.f17418d);
        ArrayList arrayList = new ArrayList();
        int i9 = 0;
        for (String str : hashSet) {
            Iterator<C5047d> it = listM14966f.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z8 = false;
                    break;
                }
                C5047d next = it.next();
                if (str.equals(next.m19714f())) {
                    if (!TextUtils.isEmpty(next.m19711c())) {
                        next.m19716h(CallableC5045b.m19701d(next.m19711c()));
                    }
                    arrayList.add(next);
                    z8 = true;
                }
            }
            if (!z8) {
                i9++;
                this.f17420f.submit(new CallableC5045b(str));
            }
        }
        for (int i10 = 0; i10 < i9; i10++) {
            try {
                arrayList.add(this.f17420f.take().get());
            } catch (Exception e9) {
                e9.printStackTrace();
            }
        }
        this.f17419e.mo19700a(this.f17417c, arrayList);
    }
}
