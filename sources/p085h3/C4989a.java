package p085h3;

import android.content.Intent;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.android.Intents;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/* renamed from: h3.a */
/* loaded from: classes.dex */
public final class C4989a {

    /* renamed from: a */
    public static final Pattern f17182a = Pattern.compile(",");

    /* renamed from: b */
    public static final Set<BarcodeFormat> f17183b;

    /* renamed from: c */
    public static final Map<String, Set<BarcodeFormat>> f17184c;

    static {
        EnumSet enumSetOf = EnumSet.of(BarcodeFormat.QR_CODE);
        f17183b = enumSetOf;
        HashMap map = new HashMap();
        f17184c = map;
        map.put(Intents.Scan.QR_CODE_MODE, enumSetOf);
    }

    /* renamed from: a */
    public static Set<BarcodeFormat> m19362a(Intent intent) {
        String stringExtra = intent.getStringExtra(Intents.Scan.FORMATS);
        return m19363b(stringExtra != null ? Arrays.asList(f17182a.split(stringExtra)) : null, intent.getStringExtra(Intents.Scan.MODE));
    }

    /* renamed from: b */
    public static Set<BarcodeFormat> m19363b(Iterable<String> iterable, String str) {
        if (iterable != null) {
            EnumSet enumSetNoneOf = EnumSet.noneOf(BarcodeFormat.class);
            try {
                Iterator<String> it = iterable.iterator();
                while (it.hasNext()) {
                    enumSetNoneOf.add(BarcodeFormat.valueOf(it.next()));
                }
                return enumSetNoneOf;
            } catch (IllegalArgumentException unused) {
            }
        }
        if (str != null) {
            return f17184c.get(str);
        }
        return null;
    }
}
