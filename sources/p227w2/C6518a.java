package p227w2;

import android.os.Environment;
import com.cyberlink.you.utility.CLUtility;
import com.google.android.gms.common.Scopes;
import java.io.File;

/* renamed from: w2.a */
/* loaded from: classes.dex */
public class C6518a {

    /* renamed from: a */
    public static final String[] f21929a = {Scopes.EMAIL, "public_profile"};

    /* renamed from: b */
    public static final String f21930b;

    /* renamed from: c */
    public static final String f21931c;

    /* renamed from: d */
    public static final String f21932d;

    /* renamed from: e */
    public static final String f21933e;

    /* renamed from: f */
    public static String f21934f;

    /* renamed from: g */
    public static final String[] f21935g;

    static {
        StringBuilder sb = new StringBuilder();
        sb.append(CLUtility.m16508Z());
        String str = File.separator;
        sb.append(str);
        sb.append("U");
        f21930b = sb.toString();
        f21931c = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + str + "U_PHOTO";
        f21932d = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES) + str + "U_VIDEO";
        f21933e = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + str + "U_DOWNLOAD";
        f21934f = "635165575013";
        f21935g = new String[]{"c1", "y2", "b3", "e4", "r5", "l6", "i7", "n8k"};
    }
}
