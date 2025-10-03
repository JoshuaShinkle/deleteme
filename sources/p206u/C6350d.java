package p206u;

import android.content.res.TypedArray;
import org.xmlpull.v1.XmlPullParser;

/* renamed from: u.d */
/* loaded from: classes.dex */
public class C6350d {
    /* renamed from: a */
    public static int m24373a(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i9, int i10) {
        return !m24376d(xmlPullParser, str) ? i10 : typedArray.getInt(i9, i10);
    }

    /* renamed from: b */
    public static int m24374b(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i9, int i10) {
        return !m24376d(xmlPullParser, str) ? i10 : typedArray.getResourceId(i9, i10);
    }

    /* renamed from: c */
    public static String m24375c(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i9) {
        if (m24376d(xmlPullParser, str)) {
            return typedArray.getString(i9);
        }
        return null;
    }

    /* renamed from: d */
    public static boolean m24376d(XmlPullParser xmlPullParser, String str) {
        return xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", str) != null;
    }
}
