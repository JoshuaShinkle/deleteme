package p086h4;

import android.content.Context;
import android.widget.ImageView;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.StickerPackObj;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.LoadImageUtils;
import java.io.File;

/* renamed from: h4.v */
/* loaded from: classes.dex */
public class C5018v extends AbstractC5003g {
    public C5018v(StickerPackObj stickerPackObj, Context context) {
        super(context);
        this.f17240a = stickerPackObj;
    }

    @Override // p086h4.AbstractC5003g
    /* renamed from: g */
    public void mo19378g(ImageView imageView) {
        m19534h(this.f17240a);
        LoadImageUtils.m16641z(this.f17241b, this.f17240a, imageView, true, false);
    }

    /* renamed from: h */
    public final void m19534h(StickerPackObj stickerPackObj) {
        String strM16541h1;
        if (!stickerPackObj.m14812p().f13077e.equals("_") || (strM16541h1 = CLUtility.m16541h1(stickerPackObj.m14803g())) == null) {
            return;
        }
        stickerPackObj.m14812p().f13077e = strM16541h1 + File.separator + "thumbnail";
        C2950b0.m14925x().m15301s(stickerPackObj.m14803g(), stickerPackObj);
    }
}
