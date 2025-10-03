package p055e3;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.RegisterActivity;
import com.cyberlink.you.activity.share.ShareMediaActivity;
import com.cyberlink.you.activity.share.ShareType;
import com.cyberlink.you.utility.CLUtility;
import com.google.android.exoplayer2.util.MimeTypes;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import p116k4.C5154j;
import p209u2.AbstractC6381r;
import p209u2.C6369f;
import p209u2.C6383t;

/* renamed from: e3.a */
/* loaded from: classes.dex */
public final class C4714a {

    /* renamed from: a */
    public static Intent f16472a;

    /* renamed from: a */
    public static Intent m18866a(Intent intent) {
        Intent intent2 = f16472a;
        return intent2 != null ? intent2 : intent;
    }

    /* renamed from: b */
    public static Void m18867b(Intent intent, AbstractC6381r<Intent, Void> abstractC6381r) {
        Intent intentM18866a = m18866a(intent);
        String action = intentM18866a.getAction();
        if (!"android.intent.action.SEND".equals(action) && !"android.intent.action.SEND_MULTIPLE".equals(action)) {
            return abstractC6381r.m24507e();
        }
        f16472a = intentM18866a;
        if (C6383t.m24517f(Globals.m7388i0().m7449L())) {
            return abstractC6381r.m24506d(new Intent(Globals.m7372O(), (Class<?>) RegisterActivity.class));
        }
        f16472a = null;
        String type = intentM18866a.getType();
        if (type == null) {
            type = "";
        }
        Bundle extras = intentM18866a.getExtras();
        if (extras == null) {
            extras = new Bundle();
        }
        Intent intent2 = new Intent(Globals.m7372O(), (Class<?>) ShareMediaActivity.class);
        if ("text/plain".equals(type) && extras.containsKey("android.intent.extra.TEXT")) {
            intent2.putExtra("android.intent.extra.TEXT", extras.getString("android.intent.extra.TEXT"));
            intent2.putExtra("shareType", ShareType.External_Text.toString());
            return abstractC6381r.m24506d(intent2);
        }
        if (!extras.containsKey("android.intent.extra.STREAM")) {
            return abstractC6381r.m24505c();
        }
        ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
        if ("android.intent.action.SEND".equals(action)) {
            arrayList.add((Uri) extras.getParcelable("android.intent.extra.STREAM"));
        } else if ("android.intent.action.SEND_MULTIPLE".equals(action)) {
            arrayList.addAll(extras.getParcelableArrayList("android.intent.extra.STREAM"));
        }
        for (int i9 = 0; i9 < arrayList.size(); i9++) {
            Uri uri = (Uri) arrayList.get(i9);
            Uri uriM16587t = CLUtility.m16587t(uri);
            if (uriM16587t == null) {
                String strM16552k0 = CLUtility.m16552k0(null, uri);
                if (CLUtility.m16572p0(null, uri) <= 52428800) {
                    File file = new File(CLUtility.m16520c0() + File.separator + strM16552k0);
                    if (file.exists()) {
                        file = C6369f.m24465g(file);
                    }
                    try {
                        if (C6369f.m24461c(null, uri, file)) {
                            uriM16587t = Uri.fromFile(file);
                        }
                    } catch (Exception e9) {
                        C5154j.m20076j(e9);
                    }
                }
            }
            if (uriM16587t != null && !uriM16587t.equals(arrayList.get(i9))) {
                arrayList.set(i9, uriM16587t);
            }
        }
        intent2.putParcelableArrayListExtra("android.intent.extra.STREAM", arrayList);
        ShareType shareType = (arrayList.size() == 1 && m18869d(type, (Uri) arrayList.get(0))) ? ShareType.External_Video : ((arrayList.size() == 1 && type.startsWith("image/")) || m18868c(arrayList)) ? ShareType.External_Media : ShareType.EXTERNAL_FILE;
        intent2.putExtra("shareType", shareType.toString());
        return abstractC6381r.m24506d(intent2);
    }

    /* renamed from: c */
    public static boolean m18868c(ArrayList<Uri> arrayList) {
        ContentResolver contentResolver = Globals.m7372O().getContentResolver();
        Iterator<Uri> it = arrayList.iterator();
        boolean z8 = true;
        while (it.hasNext()) {
            Uri next = it.next();
            String strM24472n = "file".equals(next.getScheme()) ? C6369f.m24472n(new File(next.toString()), null, null) : contentResolver.getType(next);
            z8 &= strM24472n != null && strM24472n.startsWith("image/");
        }
        return z8;
    }

    /* renamed from: d */
    public static boolean m18869d(String str, Uri uri) {
        if (str.equalsIgnoreCase(MimeTypes.VIDEO_MP4) || str.equalsIgnoreCase("video/ext-mp4")) {
            return true;
        }
        return MimeTypes.VIDEO_MP4.equals(CLUtility.m16452J0(CLUtility.m16417A1(uri) ? uri.getPath() : null, uri));
    }

    /* renamed from: e */
    public static void m18870e(Intent intent) {
        f16472a = intent;
    }
}
