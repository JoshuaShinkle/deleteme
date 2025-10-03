package p105j3;

import android.content.Context;
import com.cyberlink.p030U.R;

/* renamed from: j3.a */
/* loaded from: classes.dex */
public class C5094a {
    /* renamed from: a */
    public static String m19947a(Context context, String str) {
        return (context == null || str == null || str.isEmpty()) ? "" : str.equals("YouCamPerfect") ? context.getResources().getString(R.string.album_name_YCP) : str.equals("YouCamMakeup") ? context.getResources().getString(R.string.album_name_YMK) : str.equals("PhotoDirector") ? context.getResources().getString(R.string.album_name_PHD) : "";
    }
}
