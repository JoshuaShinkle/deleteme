package p157o6;

import java.util.List;
import okhttp3.internal.http2.ErrorCode;
import p007a6.C0042f;
import p204t6.InterfaceC6324e;

/* renamed from: o6.f */
/* loaded from: classes.dex */
public interface InterfaceC5474f {

    /* renamed from: a */
    public static final a f18448a = a.f18450a;

    /* renamed from: b */
    public static final InterfaceC5474f f18449b = new a.C6872a();

    /* renamed from: o6.f$a */
    public static final class a {

        /* renamed from: a */
        public static final /* synthetic */ a f18450a = new a();

        /* renamed from: o6.f$a$a, reason: collision with other inner class name */
        public static final class C6872a implements InterfaceC5474f {
            @Override // p157o6.InterfaceC5474f
            /* renamed from: a */
            public boolean mo21173a(int i9, List<C5469a> list) {
                C0042f.m158e(list, "requestHeaders");
                return true;
            }

            @Override // p157o6.InterfaceC5474f
            /* renamed from: b */
            public boolean mo21174b(int i9, List<C5469a> list, boolean z8) {
                C0042f.m158e(list, "responseHeaders");
                return true;
            }

            @Override // p157o6.InterfaceC5474f
            /* renamed from: c */
            public boolean mo21175c(int i9, InterfaceC6324e interfaceC6324e, int i10, boolean z8) {
                C0042f.m158e(interfaceC6324e, "source");
                interfaceC6324e.skip(i10);
                return true;
            }

            @Override // p157o6.InterfaceC5474f
            /* renamed from: d */
            public void mo21176d(int i9, ErrorCode errorCode) {
                C0042f.m158e(errorCode, "errorCode");
            }
        }
    }

    /* renamed from: a */
    boolean mo21173a(int i9, List<C5469a> list);

    /* renamed from: b */
    boolean mo21174b(int i9, List<C5469a> list, boolean z8);

    /* renamed from: c */
    boolean mo21175c(int i9, InterfaceC6324e interfaceC6324e, int i10, boolean z8);

    /* renamed from: d */
    void mo21176d(int i9, ErrorCode errorCode);
}
