package p222v6;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HostnameVerifier;
import org.jivesoftware.smack.compression.C5584a;
import org.jivesoftware.smack.compression.XMPPInputOutputStream;
import org.jivesoftware.smack.util.C5610d;
import org.jivesoftware.smack.util.C5611e;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import p249y6.InterfaceC6792a;
import p258z6.AbstractC6835b;
import p258z6.C6834a;

/* renamed from: v6.e */
/* loaded from: classes.dex */
public final class C6492e {

    /* renamed from: h */
    public static boolean f21821h;

    /* renamed from: i */
    public static AbstractC6835b f21822i;

    /* renamed from: j */
    public static HostnameVerifier f21823j;

    /* renamed from: b */
    public static final Logger f21815b = Logger.getLogger(C6492e.class.getName());

    /* renamed from: c */
    public static int f21816c = 5000;

    /* renamed from: d */
    public static int f21817d = 5000;

    /* renamed from: e */
    public static List<String> f21818e = new ArrayList();

    /* renamed from: f */
    public static Set<String> f21819f = new HashSet();

    /* renamed from: g */
    public static final List<XMPPInputOutputStream> f21820g = new ArrayList(2);

    /* renamed from: a */
    public static final String f21814a = "aSmack 4.0.3 [CL]";

    static {
        f21821h = false;
        String property = System.getProperty("smack.disabledClasses");
        if (property != null) {
            for (String str : property.split(",")) {
                f21819f.add(str);
            }
        }
        try {
            C5611e.m22323a("classpath:org.jivesoftware.smack/disabledClasses", f21819f);
            try {
                String[] strArr = (String[]) Class.forName("org.jivesoftware.smack.CustomSmackConfiguration").getField("DISABLED_SMACK_CLASSES").get(null);
                if (strArr != null) {
                    for (String str2 : strArr) {
                        f21819f.add(str2);
                    }
                }
            } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchFieldException | SecurityException unused) {
            }
            try {
                try {
                    m24833i(new ByteArrayInputStream("<?xml version=\"1.0\"?><smack><startupClasses><className>org.jivesoftware.smack.initializer.VmArgInitializer</className><className>org.jivesoftware.smack.ReconnectionManager</className></startupClasses><optionalStartupClasses><className>org.jivesoftware.smack.util.dns.javax.JavaxResolver</className><className>org.jivesoftware.smack.initializer.extensions.ExtensionsInitializer</className><className>org.jivesoftware.smack.initializer.experimental.ExperimentalInitializer</className><className>org.jivesoftware.smack.initializer.legacy.LegacyInitializer</className></optionalStartupClasses></smack>".getBytes()), null);
                    f21820g.add(new C5584a());
                    try {
                        f21821h = Boolean.getBoolean("smack.debugEnabled");
                    } catch (Exception unused2) {
                    }
                    C5610d.m22318b();
                    f21822i = new C6834a();
                } catch (Exception e9) {
                    throw new IllegalStateException(e9);
                }
            } catch (Exception e10) {
                throw new IllegalStateException(e10);
            }
        } catch (Exception e11) {
            throw new IllegalStateException(e11);
        }
    }

    /* renamed from: a */
    public static List<XMPPInputOutputStream> m24825a() {
        List<XMPPInputOutputStream> list = f21820g;
        ArrayList arrayList = new ArrayList(list.size());
        for (XMPPInputOutputStream xMPPInputOutputStream : list) {
            if (xMPPInputOutputStream.mo22029d()) {
                arrayList.add(xMPPInputOutputStream);
            }
        }
        return arrayList;
    }

    /* renamed from: b */
    public static HostnameVerifier m24826b() {
        return f21823j;
    }

    /* renamed from: c */
    public static int m24827c() {
        if (f21816c <= 0) {
            f21816c = 5000;
        }
        return f21816c;
    }

    /* renamed from: d */
    public static AbstractC6835b m24828d() {
        return f21822i;
    }

    /* renamed from: e */
    public static int m24829e() {
        return f21817d;
    }

    /* renamed from: f */
    public static String m24830f() {
        return f21814a;
    }

    /* renamed from: g */
    public static void m24831g(String str, boolean z8, ClassLoader classLoader) throws ClassNotFoundException {
        try {
            Class<?> cls = Class.forName(str, true, classLoader);
            if (!InterfaceC6792a.class.isAssignableFrom(cls)) {
                f21815b.log(Level.FINE, "Loaded " + str);
                return;
            }
            List<Exception> listMo22045a = ((InterfaceC6792a) cls.newInstance()).mo22045a();
            if (listMo22045a.size() != 0) {
                Iterator<Exception> it = listMo22045a.iterator();
                while (it.hasNext()) {
                    f21815b.log(Level.SEVERE, "Exception in loadSmackClass", (Throwable) it.next());
                }
            } else {
                f21815b.log(Level.FINE, "Loaded SmackInitializer " + str);
            }
        } catch (ClassNotFoundException e9) {
            Level level = z8 ? Level.FINE : Level.WARNING;
            f21815b.log(level, "A startup class '" + str + "' could not be loaded.");
            if (!z8) {
                throw e9;
            }
        }
    }

    /* renamed from: h */
    public static void m24832h(XmlPullParser xmlPullParser, boolean z8, Collection<Exception> collection, ClassLoader classLoader) throws Exception {
        String name = xmlPullParser.getName();
        while (true) {
            int next = xmlPullParser.next();
            String name2 = xmlPullParser.getName();
            if (next == 2 && "className".equals(name2)) {
                String strNextText = xmlPullParser.nextText();
                if (f21819f.contains(strNextText)) {
                    f21815b.info("Not loading disabled Smack class " + strNextText);
                } else {
                    try {
                        m24831g(strNextText, z8, classLoader);
                    } catch (Exception e9) {
                        if (collection == null) {
                            throw e9;
                        }
                        collection.add(e9);
                    }
                }
            }
            if (next == 3 && name.equals(name2)) {
                return;
            }
        }
    }

    /* renamed from: i */
    public static void m24833i(InputStream inputStream, Collection<Exception> collection) throws Exception {
        m24834j(inputStream, collection, C6492e.class.getClassLoader());
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0044, code lost:
    
        r4 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0045, code lost:
    
        p222v6.C6492e.f21815b.log(java.util.logging.Level.SEVERE, "Error while closing config file input stream", (java.lang.Throwable) r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x004e, code lost:
    
        return;
     */
    /* renamed from: j */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void m24834j(InputStream inputStream, Collection<Exception> collection, ClassLoader classLoader) throws Exception {
        XmlPullParser xmlPullParserNewPullParser = XmlPullParserFactory.newInstance().newPullParser();
        xmlPullParserNewPullParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
        xmlPullParserNewPullParser.setInput(inputStream, "UTF-8");
        int eventType = xmlPullParserNewPullParser.getEventType();
        do {
            if (eventType == 2) {
                if (xmlPullParserNewPullParser.getName().equals("startupClasses")) {
                    m24832h(xmlPullParserNewPullParser, false, collection, classLoader);
                } else if (xmlPullParserNewPullParser.getName().equals("optionalStartupClasses")) {
                    m24832h(xmlPullParserNewPullParser, true, collection, classLoader);
                }
            }
            eventType = xmlPullParserNewPullParser.next();
        } while (eventType != 1);
        inputStream.close();
    }

    /* renamed from: k */
    public static void m24835k(int i9) {
        if (i9 <= 0) {
            throw new IllegalArgumentException();
        }
        f21816c = i9;
    }
}
