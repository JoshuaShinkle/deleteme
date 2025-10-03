package p085h3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.google.zxing.DecodeHintType;
import java.util.EnumMap;
import java.util.Map;
import java.util.regex.Pattern;

/* renamed from: h3.c */
/* loaded from: classes.dex */
public final class C4991c {

    /* renamed from: a */
    public static final String f17189a = "c";

    /* renamed from: b */
    public static final Pattern f17190b = Pattern.compile(",");

    /* renamed from: a */
    public static Map<DecodeHintType, Object> m19366a(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras == null || extras.isEmpty()) {
            return null;
        }
        EnumMap enumMap = new EnumMap(DecodeHintType.class);
        for (DecodeHintType decodeHintType : DecodeHintType.values()) {
            if (decodeHintType != DecodeHintType.CHARACTER_SET && decodeHintType != DecodeHintType.NEED_RESULT_POINT_CALLBACK && decodeHintType != DecodeHintType.POSSIBLE_FORMATS) {
                String strName = decodeHintType.name();
                if (extras.containsKey(strName)) {
                    if (decodeHintType.getValueType().equals(Void.class)) {
                        enumMap.put((EnumMap) decodeHintType, (DecodeHintType) Boolean.TRUE);
                    } else {
                        Object obj = extras.get(strName);
                        if (decodeHintType.getValueType().isInstance(obj)) {
                            enumMap.put((EnumMap) decodeHintType, (DecodeHintType) obj);
                        } else {
                            Log.w(f17189a, "Ignoring hint " + decodeHintType + " because it is not assignable from " + obj);
                        }
                    }
                }
            }
        }
        Log.i(f17189a, "Hints from the Intent: " + enumMap);
        return enumMap;
    }
}
