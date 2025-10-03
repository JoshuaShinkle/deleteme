package p196s7;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.packet.InterfaceC5595c;
import org.jivesoftware.smack.util.C5616j;
import org.jivesoftware.smack.util.C5618l;

/* renamed from: s7.a */
/* loaded from: classes.dex */
public class C6272a implements InterfaceC5595c {

    /* renamed from: b */
    public static final Logger f21153b = Logger.getLogger(C6272a.class.getName());

    /* renamed from: a */
    public final Map<String, Object> f21154a;

    public C6272a() {
        this.f21154a = new HashMap();
    }

    /* JADX WARN: Removed duplicated region for block: B:69:0x0106 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0101 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:91:? A[SYNTHETIC] */
    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public CharSequence mo190a() throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream;
        ObjectOutputStream objectOutputStream;
        String strM22338c;
        String str;
        C5618l c5618l = new C5618l(this);
        c5618l.m22370u();
        for (String str2 : m24021d()) {
            Object objM24020c = m24020c(str2);
            c5618l.m22365p("property");
            c5618l.m22361l(AppMeasurementSdk.ConditionalUserProperty.NAME, str2);
            c5618l.m22364o("value");
            if (objM24020c instanceof Integer) {
                strM22338c = Integer.toString(((Integer) objM24020c).intValue());
                str = "integer";
            } else if (objM24020c instanceof Long) {
                strM22338c = Long.toString(((Long) objM24020c).longValue());
                str = "long";
            } else if (objM24020c instanceof Float) {
                strM22338c = Float.toString(((Float) objM24020c).floatValue());
                str = "float";
            } else if (objM24020c instanceof Double) {
                strM22338c = Double.toString(((Double) objM24020c).doubleValue());
                str = "double";
            } else if (objM24020c instanceof Boolean) {
                strM22338c = Boolean.toString(((Boolean) objM24020c).booleanValue());
                str = "boolean";
            } else if (objM24020c instanceof String) {
                strM22338c = (String) objM24020c;
                str = "string";
            } else {
                ObjectOutputStream objectOutputStream2 = null;
                objectOutputStream2 = null;
                ByteArrayOutputStream byteArrayOutputStream2 = null;
                try {
                    byteArrayOutputStream = new ByteArrayOutputStream();
                } catch (Exception e9) {
                    e = e9;
                    objectOutputStream = null;
                } catch (Throwable th) {
                    th = th;
                    byteArrayOutputStream = null;
                }
                try {
                    objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                    try {
                        objectOutputStream.writeObject(objM24020c);
                        strM22338c = C5616j.m22338c(byteArrayOutputStream.toByteArray());
                        try {
                            objectOutputStream.close();
                        } catch (Exception unused) {
                        }
                        try {
                            byteArrayOutputStream.close();
                        } catch (Exception unused2) {
                        }
                    } catch (Exception e10) {
                        e = e10;
                        byteArrayOutputStream2 = byteArrayOutputStream;
                        try {
                            f21153b.log(Level.SEVERE, "Error encoding java object", (Throwable) e);
                            strM22338c = "Serializing error: " + e.getMessage();
                            if (objectOutputStream != null) {
                                try {
                                    objectOutputStream.close();
                                } catch (Exception unused3) {
                                }
                            }
                            if (byteArrayOutputStream2 != null) {
                                byteArrayOutputStream2.close();
                            }
                            str = "java-object";
                            c5618l.m22355f("type", str);
                            c5618l.m22370u();
                            c5618l.m22363n(strM22338c);
                            c5618l.m22356g("value");
                            c5618l.m22356g("property");
                        } catch (Throwable th2) {
                            th = th2;
                            byteArrayOutputStream = byteArrayOutputStream2;
                            objectOutputStream2 = objectOutputStream;
                            if (objectOutputStream2 != null) {
                                try {
                                    objectOutputStream2.close();
                                } catch (Exception unused4) {
                                }
                            }
                            if (byteArrayOutputStream == null) {
                                throw th;
                            }
                            try {
                                byteArrayOutputStream.close();
                                throw th;
                            } catch (Exception unused5) {
                                throw th;
                            }
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        objectOutputStream2 = objectOutputStream;
                        if (objectOutputStream2 != null) {
                        }
                        if (byteArrayOutputStream == null) {
                        }
                    }
                } catch (Exception e11) {
                    e = e11;
                    objectOutputStream = null;
                } catch (Throwable th4) {
                    th = th4;
                    if (objectOutputStream2 != null) {
                    }
                    if (byteArrayOutputStream == null) {
                    }
                }
                str = "java-object";
            }
            c5618l.m22355f("type", str);
            c5618l.m22370u();
            c5618l.m22363n(strM22338c);
            c5618l.m22356g("value");
            c5618l.m22356g("property");
        }
        c5618l.m22357h(this);
        return c5618l;
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    /* renamed from: b */
    public String mo191b() {
        return "properties";
    }

    /* renamed from: c */
    public synchronized Object m24020c(String str) {
        Map<String, Object> map = this.f21154a;
        if (map == null) {
            return null;
        }
        return map.get(str);
    }

    /* renamed from: d */
    public synchronized Collection<String> m24021d() {
        if (this.f21154a == null) {
            return Collections.emptySet();
        }
        return Collections.unmodifiableSet(new HashSet(this.f21154a.keySet()));
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    public String getNamespace() {
        return "http://www.jivesoftware.com/xmlns/xmpp/properties";
    }

    public C6272a(Map<String, Object> map) {
        this.f21154a = map;
    }
}
