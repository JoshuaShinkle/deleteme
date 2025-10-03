package p008a7;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.packet.AbstractC5586IQ;
import org.jivesoftware.smack.packet.InterfaceC5595c;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

/* renamed from: a7.f */
/* loaded from: classes.dex */
public class C0052f implements InterfaceC0053g {

    /* renamed from: d */
    public static final Logger f142d = Logger.getLogger(C0052f.class.getName());

    /* renamed from: c */
    public List<Exception> f145c = new LinkedList();

    /* renamed from: a */
    public Collection<C0050d> f143a = new ArrayList();

    /* renamed from: b */
    public Collection<C0048b> f144b = new ArrayList();

    public C0052f(InputStream inputStream, ClassLoader classLoader) throws IOException {
        try {
            try {
                XmlPullParser xmlPullParserNewPullParser = XmlPullParserFactory.newInstance().newPullParser();
                xmlPullParserNewPullParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
                xmlPullParserNewPullParser.setInput(inputStream, "UTF-8");
                int eventType = xmlPullParserNewPullParser.getEventType();
                do {
                    if (eventType == 2) {
                        String name = xmlPullParserNewPullParser.getName();
                        try {
                            if (!"smackProviders".equals(name)) {
                                xmlPullParserNewPullParser.next();
                                xmlPullParserNewPullParser.next();
                                String strNextText = xmlPullParserNewPullParser.nextText();
                                xmlPullParserNewPullParser.next();
                                xmlPullParserNewPullParser.next();
                                String strNextText2 = xmlPullParserNewPullParser.nextText();
                                xmlPullParserNewPullParser.next();
                                xmlPullParserNewPullParser.next();
                                try {
                                    Class<?> clsLoadClass = classLoader.loadClass(xmlPullParserNewPullParser.nextText());
                                    if ("iqProvider".equals(name)) {
                                        if (InterfaceC0049c.class.isAssignableFrom(clsLoadClass)) {
                                            this.f143a.add(new C0050d(strNextText, strNextText2, (InterfaceC0049c) clsLoadClass.newInstance()));
                                        } else if (AbstractC5586IQ.class.isAssignableFrom(clsLoadClass)) {
                                            this.f143a.add(new C0050d(strNextText, strNextText2, (Class<? extends AbstractC5586IQ>) clsLoadClass));
                                        }
                                    } else if (InterfaceC0051e.class.isAssignableFrom(clsLoadClass)) {
                                        this.f144b.add(new C0048b(strNextText, strNextText2, (InterfaceC0051e) clsLoadClass.newInstance()));
                                    } else if (InterfaceC5595c.class.isAssignableFrom(clsLoadClass)) {
                                        this.f144b.add(new C0048b(strNextText, strNextText2, clsLoadClass));
                                    }
                                } catch (ClassNotFoundException e9) {
                                    f142d.log(Level.SEVERE, "Could not find provider class", (Throwable) e9);
                                    this.f145c.add(e9);
                                }
                            }
                        } catch (IllegalArgumentException e10) {
                            f142d.log(Level.SEVERE, "Invalid provider type found [" + name + "] when expecting iqProvider or extensionProvider", (Throwable) e10);
                            this.f145c.add(e10);
                        }
                    }
                    eventType = xmlPullParserNewPullParser.next();
                } while (eventType != 1);
            } catch (Exception e11) {
                f142d.log(Level.SEVERE, "Unknown error occurred while parsing provider file", (Throwable) e11);
                this.f145c.add(e11);
            }
        } finally {
            try {
                inputStream.close();
            } catch (Exception unused) {
            }
        }
    }

    @Override // p008a7.InterfaceC0053g
    /* renamed from: a */
    public Collection<C0048b> mo182a() {
        return this.f144b;
    }

    @Override // p008a7.InterfaceC0053g
    /* renamed from: b */
    public Collection<C0050d> mo183b() {
        return this.f143a;
    }

    /* renamed from: c */
    public List<Exception> m184c() {
        return Collections.unmodifiableList(this.f145c);
    }
}
