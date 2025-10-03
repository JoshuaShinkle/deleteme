package p116k4;

import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.widget.TextView;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/* renamed from: k4.x */
/* loaded from: classes.dex */
public class C5190x {

    /* renamed from: k4.x$a */
    public static class a {

        /* renamed from: a */
        public float f17770a;

        /* renamed from: b */
        public float f17771b;

        /* renamed from: c */
        public String[] f17772c;

        public a(float f9, float f10, String... strArr) {
            this.f17770a = f9;
            this.f17771b = f10;
            this.f17772c = strArr;
        }
    }

    /* renamed from: a */
    public static Map<String, a> m20275a(String str) {
        String[] strArrSplit = str.replaceAll(StringUtils.SPACE, "").split(";");
        HashMap map = new HashMap();
        for (String str2 : strArrSplit) {
            String[] strArrSplit2 = str2.split(":");
            try {
                for (String str3 : strArrSplit2[2].split(",")) {
                    map.put(str3, new a(Float.parseFloat(strArrSplit2[0]), Float.parseFloat(strArrSplit2[1]), new String[0]));
                }
            } catch (Exception e9) {
                Log.e("MultiLangTextSizeUtils", str2, e9);
            }
        }
        return map;
    }

    /* renamed from: b */
    public static void m20276b(TextView textView, boolean z8, Map<String, a> map) {
        DisplayMetrics displayMetrics = textView.getResources().getDisplayMetrics();
        a aVar = map.get(Locale.getDefault().getLanguage());
        a aVar2 = map.get("default");
        float f9 = displayMetrics.widthPixels / displayMetrics.density;
        if (aVar == null && aVar2 == null) {
            return;
        }
        float textSize = textView.getTextSize();
        if (aVar2 != null) {
            textSize = TypedValue.applyDimension(1, (aVar2.f17771b * f9) / aVar2.f17770a, displayMetrics);
        }
        if (aVar != null) {
            float fApplyDimension = TypedValue.applyDimension(1, (aVar.f17771b * f9) / aVar.f17770a, displayMetrics);
            textSize = z8 ? Math.min(textSize, fApplyDimension) : fApplyDimension;
        }
        textView.setTextSize(0, textSize);
    }
}
