package p191s2;

import android.content.Context;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/* renamed from: s2.e */
/* loaded from: classes.dex */
public class C6257e implements InterfaceC6254b {
    public C6257e(Context context, String str, String str2, Map<String, Object> map) {
        C6255c.m23968a().m7262d(context.getApplicationContext(), str, str2, map);
    }

    @Override // p191s2.InterfaceC6254b
    /* renamed from: a */
    public int mo23964a() {
        return C6255c.m23968a().m7261c();
    }

    @Override // p191s2.InterfaceC6254b
    /* renamed from: b */
    public void mo23965b() throws ExecutionException, InterruptedException {
        C6255c.m23968a().m7268k();
    }

    @Override // p191s2.InterfaceC6254b
    /* renamed from: c */
    public void mo23966c(String str, int i9, String str2) {
        C6255c.m23968a().m7270m(str, i9, str2);
    }

    @Override // p191s2.InterfaceC6254b
    /* renamed from: d */
    public void mo23967d(Map<String, Object> map) throws ExecutionException, InterruptedException {
        C6255c.m23968a().m7275r(map);
    }

    @Override // p191s2.InterfaceC6254b
    public void onStart() {
        C6255c.m23968a().m7263f();
    }

    @Override // p191s2.InterfaceC6254b
    public void onStop() {
        C6255c.m23968a().m7265h();
    }
}
