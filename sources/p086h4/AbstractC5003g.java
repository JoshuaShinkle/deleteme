package p086h4;

import android.content.Context;
import android.widget.ImageView;
import com.cyberlink.you.database.StickerPackObj;
import java.util.ArrayList;
import java.util.List;

/* renamed from: h4.g */
/* loaded from: classes.dex */
public abstract class AbstractC5003g {

    /* renamed from: a */
    public StickerPackObj f17240a;

    /* renamed from: b */
    public Context f17241b;

    public AbstractC5003g(Context context) {
        this.f17241b = context;
    }

    /* renamed from: a */
    public static AbstractC5003g m19435a(Context context) {
        return new C4995b(context);
    }

    /* renamed from: b */
    public static AbstractC5003g m19436b(Context context) {
        return new C4999d(context);
    }

    /* renamed from: c */
    public static AbstractC5003g m19437c(Context context) {
        return new C5007k(context);
    }

    /* renamed from: d */
    public static List<AbstractC5003g> m19438d(List<StickerPackObj> list, Context context) {
        ArrayList arrayList = new ArrayList();
        for (int i9 = 0; i9 < list.size(); i9++) {
            arrayList.add(new C5018v(list.get(i9), context));
        }
        return arrayList;
    }

    /* renamed from: e */
    public StickerPackObj m19439e() {
        return this.f17240a;
    }

    /* renamed from: f */
    public void m19440f(StickerPackObj stickerPackObj) {
        this.f17240a = stickerPackObj;
    }

    /* renamed from: g */
    public abstract void mo19378g(ImageView imageView);
}
