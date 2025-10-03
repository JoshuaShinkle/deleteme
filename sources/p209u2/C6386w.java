package p209u2;

import android.util.Log;
import java.util.Stack;
import org.apache.commons.lang3.StringUtils;

/* renamed from: u2.w */
/* loaded from: classes.dex */
public class C6386w {

    /* renamed from: a */
    public Stack<Long> f21561a = new Stack<>();

    /* renamed from: a */
    public void m24530a(long j9, String str) {
        Log.e("TicTac2", "X_X [tic = N/A, tac = " + j9 + "] : " + str);
    }

    /* renamed from: b */
    public void mo15035b(String str) {
        Log.v("TicTac2", str);
    }

    /* renamed from: c */
    public void m24531c() {
        this.f21561a.clear();
    }

    /* renamed from: d */
    public void m24532d(String str) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (this.f21561a.size() < 1) {
            m24530a(jCurrentTimeMillis, str);
            return;
        }
        long jLongValue = this.f21561a.pop().longValue();
        StringBuilder sb = new StringBuilder();
        int size = this.f21561a.size();
        for (int i9 = 0; i9 < size; i9++) {
            sb.append(StringUtils.SPACE);
        }
        sb.append("[");
        sb.append(jCurrentTimeMillis - jLongValue);
        sb.append("] : ");
        sb.append(str);
        mo15035b(sb.toString());
    }

    /* renamed from: e */
    public void m24533e() {
        this.f21561a.push(Long.valueOf(System.currentTimeMillis()));
    }
}
