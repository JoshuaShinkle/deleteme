package p201t3;

import com.cyberlink.you.Globals;
import com.cyberlink.you.database.C2973l0;
import com.cyberlink.you.friends.Friend;
import java.util.ArrayList;
import java.util.List;
import p201t3.C6288b;

/* renamed from: t3.c */
/* loaded from: classes.dex */
public class C6289c {
    /* renamed from: a */
    public static C6288b<String> m24102a(List<C2973l0> list, C6288b.d<String> dVar, C6288b.h hVar) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        for (int i9 = 0; i9 < list.size(); i9++) {
            arrayList.add(new C6301o("mediaId", String.valueOf(list.get(i9).m15144p())));
        }
        return new C6288b.c("media", "deleteMedia", arrayList, String.class).m24097b(dVar).m24099d(hVar).m24096a();
    }

    /* renamed from: b */
    public static C6288b<C2973l0> m24103b(String str, int i9, int i10, C6288b.d<List<C2973l0>> dVar, C6288b.h hVar) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("albumId", str));
        arrayList.add(new C6301o("pageIndex", String.valueOf(i9)));
        arrayList.add(new C6301o("pageSize", String.valueOf(i10)));
        return new C6288b.c("media", "listFile", arrayList, C2973l0.class).m24097b(dVar).m24099d(hVar).m24100e().m24098c().m24096a();
    }

    /* renamed from: c */
    public static C6288b<Friend> m24104c(String str, C6288b.d<Friend> dVar, C6288b.h hVar) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("userId", str));
        return new C6288b.c("user", "userInfoV2", arrayList, Friend.class).m24097b(dVar).m24099d(hVar).m24096a();
    }
}
