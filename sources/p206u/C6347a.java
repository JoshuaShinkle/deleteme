package p206u;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.Base64;
import android.util.Xml;
import com.google.firebase.iid.ServiceStarter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import p001a0.C0003a;
import p179r.C6180c;

/* renamed from: u.a */
/* loaded from: classes.dex */
public class C6347a {

    /* renamed from: u.a$a */
    public interface a {
    }

    /* renamed from: u.a$b */
    public static final class b implements a {

        /* renamed from: a */
        public final c[] f21406a;

        public b(c[] cVarArr) {
            this.f21406a = cVarArr;
        }

        /* renamed from: a */
        public c[] m24353a() {
            return this.f21406a;
        }
    }

    /* renamed from: u.a$c */
    public static final class c {

        /* renamed from: a */
        public final String f21407a;

        /* renamed from: b */
        public int f21408b;

        /* renamed from: c */
        public boolean f21409c;

        /* renamed from: d */
        public String f21410d;

        /* renamed from: e */
        public int f21411e;

        /* renamed from: f */
        public int f21412f;

        public c(String str, int i9, boolean z8, String str2, int i10, int i11) {
            this.f21407a = str;
            this.f21408b = i9;
            this.f21409c = z8;
            this.f21410d = str2;
            this.f21411e = i10;
            this.f21412f = i11;
        }

        /* renamed from: a */
        public String m24354a() {
            return this.f21407a;
        }

        /* renamed from: b */
        public int m24355b() {
            return this.f21412f;
        }

        /* renamed from: c */
        public int m24356c() {
            return this.f21411e;
        }

        /* renamed from: d */
        public String m24357d() {
            return this.f21410d;
        }

        /* renamed from: e */
        public int m24358e() {
            return this.f21408b;
        }

        /* renamed from: f */
        public boolean m24359f() {
            return this.f21409c;
        }
    }

    /* renamed from: u.a$d */
    public static final class d implements a {

        /* renamed from: a */
        public final C0003a f21413a;

        /* renamed from: b */
        public final int f21414b;

        /* renamed from: c */
        public final int f21415c;

        public d(C0003a c0003a, int i9, int i10) {
            this.f21413a = c0003a;
            this.f21415c = i9;
            this.f21414b = i10;
        }

        /* renamed from: a */
        public int m24360a() {
            return this.f21415c;
        }

        /* renamed from: b */
        public C0003a m24361b() {
            return this.f21413a;
        }

        /* renamed from: c */
        public int m24362c() {
            return this.f21414b;
        }
    }

    /* renamed from: a */
    public static int m24345a(TypedArray typedArray, int i9) {
        return typedArray.getType(i9);
    }

    /* renamed from: b */
    public static a m24346b(XmlPullParser xmlPullParser, Resources resources) throws XmlPullParserException, IOException {
        int next;
        do {
            next = xmlPullParser.next();
            if (next == 2) {
                break;
            }
        } while (next != 1);
        if (next == 2) {
            return m24348d(xmlPullParser, resources);
        }
        throw new XmlPullParserException("No start tag found");
    }

    /* renamed from: c */
    public static List<List<byte[]>> m24347c(Resources resources, int i9) throws Resources.NotFoundException {
        if (i9 == 0) {
            return Collections.emptyList();
        }
        TypedArray typedArrayObtainTypedArray = resources.obtainTypedArray(i9);
        try {
            if (typedArrayObtainTypedArray.length() == 0) {
                return Collections.emptyList();
            }
            ArrayList arrayList = new ArrayList();
            if (m24345a(typedArrayObtainTypedArray, 0) == 1) {
                for (int i10 = 0; i10 < typedArrayObtainTypedArray.length(); i10++) {
                    int resourceId = typedArrayObtainTypedArray.getResourceId(i10, 0);
                    if (resourceId != 0) {
                        arrayList.add(m24352h(resources.getStringArray(resourceId)));
                    }
                }
            } else {
                arrayList.add(m24352h(resources.getStringArray(i9)));
            }
            return arrayList;
        } finally {
            typedArrayObtainTypedArray.recycle();
        }
    }

    /* renamed from: d */
    public static a m24348d(XmlPullParser xmlPullParser, Resources resources) throws XmlPullParserException, IOException {
        xmlPullParser.require(2, null, "font-family");
        if (xmlPullParser.getName().equals("font-family")) {
            return m24349e(xmlPullParser, resources);
        }
        m24351g(xmlPullParser);
        return null;
    }

    /* renamed from: e */
    public static a m24349e(XmlPullParser xmlPullParser, Resources resources) throws XmlPullParserException, IOException {
        TypedArray typedArrayObtainAttributes = resources.obtainAttributes(Xml.asAttributeSet(xmlPullParser), C6180c.FontFamily);
        String string = typedArrayObtainAttributes.getString(C6180c.FontFamily_fontProviderAuthority);
        String string2 = typedArrayObtainAttributes.getString(C6180c.FontFamily_fontProviderPackage);
        String string3 = typedArrayObtainAttributes.getString(C6180c.FontFamily_fontProviderQuery);
        int resourceId = typedArrayObtainAttributes.getResourceId(C6180c.FontFamily_fontProviderCerts, 0);
        int integer = typedArrayObtainAttributes.getInteger(C6180c.FontFamily_fontProviderFetchStrategy, 1);
        int integer2 = typedArrayObtainAttributes.getInteger(C6180c.FontFamily_fontProviderFetchTimeout, ServiceStarter.ERROR_UNKNOWN);
        typedArrayObtainAttributes.recycle();
        if (string != null && string2 != null && string3 != null) {
            while (xmlPullParser.next() != 3) {
                m24351g(xmlPullParser);
            }
            return new d(new C0003a(string, string2, string3, m24347c(resources, resourceId)), integer, integer2);
        }
        ArrayList arrayList = new ArrayList();
        while (xmlPullParser.next() != 3) {
            if (xmlPullParser.getEventType() == 2) {
                if (xmlPullParser.getName().equals("font")) {
                    arrayList.add(m24350f(xmlPullParser, resources));
                } else {
                    m24351g(xmlPullParser);
                }
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return new b((c[]) arrayList.toArray(new c[arrayList.size()]));
    }

    /* renamed from: f */
    public static c m24350f(XmlPullParser xmlPullParser, Resources resources) throws XmlPullParserException, IOException {
        TypedArray typedArrayObtainAttributes = resources.obtainAttributes(Xml.asAttributeSet(xmlPullParser), C6180c.FontFamilyFont);
        int i9 = C6180c.FontFamilyFont_fontWeight;
        if (!typedArrayObtainAttributes.hasValue(i9)) {
            i9 = C6180c.FontFamilyFont_android_fontWeight;
        }
        int i10 = typedArrayObtainAttributes.getInt(i9, 400);
        int i11 = C6180c.FontFamilyFont_fontStyle;
        if (!typedArrayObtainAttributes.hasValue(i11)) {
            i11 = C6180c.FontFamilyFont_android_fontStyle;
        }
        boolean z8 = 1 == typedArrayObtainAttributes.getInt(i11, 0);
        int i12 = C6180c.FontFamilyFont_ttcIndex;
        if (!typedArrayObtainAttributes.hasValue(i12)) {
            i12 = C6180c.FontFamilyFont_android_ttcIndex;
        }
        int i13 = C6180c.FontFamilyFont_fontVariationSettings;
        if (!typedArrayObtainAttributes.hasValue(i13)) {
            i13 = C6180c.FontFamilyFont_android_fontVariationSettings;
        }
        String string = typedArrayObtainAttributes.getString(i13);
        int i14 = typedArrayObtainAttributes.getInt(i12, 0);
        int i15 = C6180c.FontFamilyFont_font;
        if (!typedArrayObtainAttributes.hasValue(i15)) {
            i15 = C6180c.FontFamilyFont_android_font;
        }
        int resourceId = typedArrayObtainAttributes.getResourceId(i15, 0);
        String string2 = typedArrayObtainAttributes.getString(i15);
        typedArrayObtainAttributes.recycle();
        while (xmlPullParser.next() != 3) {
            m24351g(xmlPullParser);
        }
        return new c(string2, i10, z8, string, i14, resourceId);
    }

    /* renamed from: g */
    public static void m24351g(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        int i9 = 1;
        while (i9 > 0) {
            int next = xmlPullParser.next();
            if (next == 2) {
                i9++;
            } else if (next == 3) {
                i9--;
            }
        }
    }

    /* renamed from: h */
    public static List<byte[]> m24352h(String[] strArr) {
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            arrayList.add(Base64.decode(str, 0));
        }
        return arrayList;
    }
}
