package p063f1;

import android.os.Handler;
import android.os.Looper;
import com.bumptech.glide.load.DecodeFormat;
import p022c1.InterfaceC0707d;
import p043d1.InterfaceC4661i;

/* renamed from: f1.a */
/* loaded from: classes.dex */
public final class C4777a {

    /* renamed from: a */
    public final InterfaceC4661i f16619a;

    /* renamed from: b */
    public final InterfaceC0707d f16620b;

    /* renamed from: c */
    public final DecodeFormat f16621c;

    /* renamed from: d */
    public final Handler f16622d = new Handler(Looper.getMainLooper());

    public C4777a(InterfaceC4661i interfaceC4661i, InterfaceC0707d interfaceC0707d, DecodeFormat decodeFormat) {
        this.f16619a = interfaceC4661i;
        this.f16620b = interfaceC0707d;
        this.f16621c = decodeFormat;
    }
}
