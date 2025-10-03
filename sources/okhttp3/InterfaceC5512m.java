package okhttp3;

import java.util.List;
import kotlin.collections.C5226i;
import p007a6.C0042f;

/* renamed from: okhttp3.m */
/* loaded from: classes2.dex */
public interface InterfaceC5512m {

    /* renamed from: a */
    public static final a f18922a = a.f18924a;

    /* renamed from: b */
    public static final InterfaceC5512m f18923b = new a.C6876a();

    /* renamed from: okhttp3.m$a */
    public static final class a {

        /* renamed from: a */
        public static final /* synthetic */ a f18924a = new a();

        /* renamed from: okhttp3.m$a$a, reason: collision with other inner class name */
        public static final class C6876a implements InterfaceC5512m {
            @Override // okhttp3.InterfaceC5512m
            /* renamed from: a */
            public void mo21585a(C5518s c5518s, List<C5511l> list) {
                C0042f.m158e(c5518s, "url");
                C0042f.m158e(list, "cookies");
            }

            @Override // okhttp3.InterfaceC5512m
            /* renamed from: b */
            public List<C5511l> mo21586b(C5518s c5518s) {
                C0042f.m158e(c5518s, "url");
                return C5226i.m20400f();
            }
        }
    }

    /* renamed from: a */
    void mo21585a(C5518s c5518s, List<C5511l> list);

    /* renamed from: b */
    List<C5511l> mo21586b(C5518s c5518s);
}
