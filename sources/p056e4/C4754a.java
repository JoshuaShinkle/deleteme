package p056e4;

import android.net.Uri;
import android.text.TextUtils;
import com.cyberlink.you.Globals;
import com.cyberlink.you.utility.CLUtility;
import java.io.InputStream;
import p082h0.C4978a;

/* renamed from: e4.a */
/* loaded from: classes.dex */
public class C4754a {
    /* renamed from: a */
    public static void m18871a(C4978a c4978a, C4978a c4978a2) {
        if (c4978a.m19287e("FNumber") != null) {
            c4978a2.m19280H("FNumber", c4978a.m19287e("FNumber"));
        }
        if (c4978a.m19287e("ExposureTime") != null) {
            c4978a2.m19280H("ExposureTime", c4978a.m19287e("ExposureTime"));
        }
        if (c4978a.m19287e("ISOSpeedRatings") != null) {
            c4978a2.m19280H("ISOSpeedRatings", c4978a.m19287e("ISOSpeedRatings"));
        }
        if (c4978a.m19287e("GPSAltitude") != null) {
            c4978a2.m19280H("GPSAltitude", c4978a.m19287e("GPSAltitude"));
        }
        if (c4978a.m19287e("GPSAltitudeRef") != null) {
            c4978a2.m19280H("GPSAltitudeRef", c4978a.m19287e("GPSAltitudeRef"));
        }
        if (c4978a.m19287e("FocalLength") != null) {
            c4978a2.m19280H("FocalLength", c4978a.m19287e("FocalLength"));
        }
        if (c4978a.m19287e("GPSDateStamp") != null) {
            c4978a2.m19280H("GPSDateStamp", c4978a.m19287e("GPSDateStamp"));
        }
        if (c4978a.m19287e("GPSProcessingMethod") != null) {
            c4978a2.m19280H("GPSProcessingMethod", c4978a.m19287e("GPSProcessingMethod"));
        }
        if (c4978a.m19287e("GPSTimeStamp") != null) {
            c4978a2.m19280H("GPSTimeStamp", "" + c4978a.m19287e("GPSTimeStamp"));
        }
        if (c4978a.m19287e("DateTime") != null) {
            c4978a2.m19280H("DateTime", c4978a.m19287e("DateTime"));
        }
        if (c4978a.m19287e("Flash") != null) {
            c4978a2.m19280H("Flash", c4978a.m19287e("Flash"));
        }
        if (c4978a.m19287e("GPSLatitude") != null) {
            c4978a2.m19280H("GPSLatitude", c4978a.m19287e("GPSLatitude"));
        }
        if (c4978a.m19287e("GPSLatitudeRef") != null) {
            c4978a2.m19280H("GPSLatitudeRef", c4978a.m19287e("GPSLatitudeRef"));
        }
        if (c4978a.m19287e("GPSLongitude") != null) {
            c4978a2.m19280H("GPSLongitude", c4978a.m19287e("GPSLongitude"));
        }
        if (c4978a.m19287e("GPSLongitudeRef") != null) {
            c4978a2.m19280H("GPSLongitudeRef", c4978a.m19287e("GPSLongitudeRef"));
        }
        if (c4978a.m19287e("ImageLength") != null) {
            c4978a2.m19280H("ImageLength", c4978a.m19287e("ImageLength"));
        }
        if (c4978a.m19287e("ImageWidth") != null) {
            c4978a2.m19280H("ImageWidth", c4978a.m19287e("ImageWidth"));
        }
        if (c4978a.m19287e("Make") != null) {
            c4978a2.m19280H("Make", c4978a.m19287e("Make"));
        }
        if (c4978a.m19287e("Model") != null) {
            c4978a2.m19280H("Model", c4978a.m19287e("Model"));
        }
        if (c4978a.m19287e("Orientation") != null) {
            c4978a2.m19280H("Orientation", c4978a.m19287e("Orientation"));
        }
        if (c4978a.m19287e("WhiteBalance") != null) {
            c4978a2.m19280H("WhiteBalance", c4978a.m19287e("WhiteBalance"));
        }
    }

    /* renamed from: b */
    public static int m18872b(String str) {
        int iM19288f = new C4978a(str).m19288f("Orientation", -1);
        if (iM19288f != -1) {
            if (iM19288f == 3) {
                return 180;
            }
            if (iM19288f == 6) {
                return 90;
            }
            if (iM19288f == 8) {
                return 270;
            }
        }
        return 0;
    }

    /* renamed from: c */
    public static int m18873c(String str, Uri uri) {
        Throwable th;
        C4978a c4978a;
        int iM19288f;
        C4978a c4978a2 = null;
        if (CLUtility.m16597v1(uri)) {
            try {
                InputStream inputStreamOpenInputStream = Globals.m7372O().getContentResolver().openInputStream(uri);
                try {
                    c4978a = new C4978a(inputStreamOpenInputStream);
                    try {
                        int iM19288f2 = c4978a.m19288f("Orientation", -1);
                        if (inputStreamOpenInputStream != null) {
                            try {
                                inputStreamOpenInputStream.close();
                            } catch (Exception unused) {
                            }
                        }
                        iM19288f = iM19288f2;
                        c4978a2 = c4978a;
                    } catch (Throwable th2) {
                        th = th2;
                        if (inputStreamOpenInputStream != null) {
                            try {
                                try {
                                    inputStreamOpenInputStream.close();
                                } catch (Throwable th3) {
                                    th.addSuppressed(th3);
                                }
                            } catch (Exception unused2) {
                                c4978a2 = c4978a;
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th4) {
                    th = th4;
                    c4978a = null;
                }
            } catch (Exception unused3) {
            }
        } else {
            iM19288f = -1;
        }
        if (c4978a2 == null && !TextUtils.isEmpty(str)) {
            iM19288f = new C4978a(str).m19288f("Orientation", -1);
        }
        if (iM19288f != -1) {
            if (iM19288f == 3) {
                return 180;
            }
            if (iM19288f == 6) {
                return 90;
            }
            if (iM19288f == 8) {
                return 270;
            }
        }
        return 0;
    }

    /* renamed from: d */
    public static boolean m18874d(String str) {
        return str.toLowerCase().endsWith(".png");
    }

    /* renamed from: e */
    public static void m18875e(String str, int i9) {
        C4978a c4978a = new C4978a(str);
        c4978a.m19280H("ImageLength", String.valueOf(i9));
        c4978a.m19278F();
    }

    /* renamed from: f */
    public static void m18876f(String str, int i9) {
        C4978a c4978a = new C4978a(str);
        c4978a.m19280H("Orientation", Integer.toString(i9 != 90 ? i9 != 180 ? i9 != 270 ? 1 : 8 : 3 : 6));
        c4978a.m19278F();
    }

    /* renamed from: g */
    public static void m18877g(String str, int i9) {
        C4978a c4978a = new C4978a(str);
        c4978a.m19280H("ImageWidth", String.valueOf(i9));
        c4978a.m19278F();
    }
}
