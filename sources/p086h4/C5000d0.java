package p086h4;

import com.cyberlink.you.Globals;
import com.cyberlink.you.database.StickerPackObj;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import p201t3.C6288b;
import p201t3.C6301o;

/* renamed from: h4.d0 */
/* loaded from: classes.dex */
public class C5000d0 {

    /* renamed from: h4.d0$a */
    public class a implements C6288b.d<List<C5017u>> {

        /* renamed from: a */
        public final /* synthetic */ C6288b.d f17226a;

        /* renamed from: b */
        public final /* synthetic */ C6288b.h f17227b;

        public a(C6288b.d dVar, C6288b.h hVar) {
            this.f17226a = dVar;
            this.f17227b = hVar;
        }

        @Override // p201t3.C6288b.d
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onComplete(List<C5017u> list) {
            C5000d0.m19412c(list, this.f17226a, this.f17227b).m24088p();
        }
    }

    /* renamed from: b */
    public static C6288b<C5017u> m19411b(C6288b.d<List<StickerPackObj>> dVar, C6288b.h hVar) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        return new C6288b.c("sticker", "user.pack.list", arrayList, C5017u.class).m24100e().m24097b(new a(dVar, hVar)).m24099d(hVar).m24096a();
    }

    /* renamed from: c */
    public static C6288b<StickerPackObj> m19412c(List<C5017u> list, C6288b.d<List<StickerPackObj>> dVar, C6288b.h hVar) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        Iterator<C5017u> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(new C6301o("packId", Long.toString(it.next().m19533b())));
        }
        return new C6288b.c("sticker", "pack.info", arrayList, StickerPackObj.class).m24100e().m24098c().m24097b(dVar).m24099d(hVar).m24096a();
    }
}
