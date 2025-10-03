package org.jivesoftware.smackx.jiveproperties.provider;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.packet.InterfaceC5595c;
import org.jivesoftware.smack.util.C5616j;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import p008a7.InterfaceC0051e;
import p187r7.C6225a;
import p196s7.C6272a;

/* loaded from: classes.dex */
public class JivePropertiesExtensionProvider implements InterfaceC0051e {

    /* renamed from: a */
    public static final Logger f19748a = Logger.getLogger(JivePropertiesExtensionProvider.class.getName());

    @Override // p008a7.InterfaceC0051e
    /* renamed from: a */
    public InterfaceC5595c mo181a(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException, NumberFormatException, ClassNotFoundException {
        HashMap map = new HashMap();
        while (true) {
            int next = xmlPullParser.next();
            if (next == 2 && xmlPullParser.getName().equals("property")) {
                String attributeValue = null;
                String strNextText = null;
                Object object = null;
                boolean z8 = false;
                String strNextText2 = null;
                while (!z8) {
                    int next2 = xmlPullParser.next();
                    if (next2 == 2) {
                        String name = xmlPullParser.getName();
                        if (name.equals(AppMeasurementSdk.ConditionalUserProperty.NAME)) {
                            strNextText = xmlPullParser.nextText();
                        } else if (name.equals("value")) {
                            attributeValue = xmlPullParser.getAttributeValue("", "type");
                            strNextText2 = xmlPullParser.nextText();
                        }
                    } else if (next2 == 3 && xmlPullParser.getName().equals("property")) {
                        if ("integer".equals(attributeValue)) {
                            object = Integer.valueOf(strNextText2);
                        } else if ("long".equals(attributeValue)) {
                            object = Long.valueOf(strNextText2);
                        } else if ("float".equals(attributeValue)) {
                            object = Float.valueOf(strNextText2);
                        } else if ("double".equals(attributeValue)) {
                            object = Double.valueOf(strNextText2);
                        } else if ("boolean".equals(attributeValue)) {
                            object = Boolean.valueOf(strNextText2);
                        } else if ("string".equals(attributeValue)) {
                            object = strNextText2;
                        } else if ("java-object".equals(attributeValue)) {
                            if (C6225a.m23782a()) {
                                try {
                                    object = new ObjectInputStream(new ByteArrayInputStream(C5616j.m22336a(strNextText2))).readObject();
                                } catch (Exception e9) {
                                    f19748a.log(Level.SEVERE, "Error parsing java object", (Throwable) e9);
                                }
                            } else {
                                f19748a.severe("JavaObject is not enabled. Enable with JivePropertiesManager.setJavaObjectEnabled(true)");
                            }
                        }
                        if (strNextText != null && object != null) {
                            map.put(strNextText, object);
                        }
                        z8 = true;
                    }
                }
            } else if (next == 3 && xmlPullParser.getName().equals("properties")) {
                return new C6272a(map);
            }
        }
    }
}
