package p078g6;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import p068f6.InterfaceC4789a;
import p088h6.C5027a;
import p088h6.C5028b;

/* renamed from: g6.d */
/* loaded from: classes2.dex */
public class C4963d implements InterfaceC4789a {

    /* renamed from: a */
    public static int f17057a = -1;

    @Override // p068f6.InterfaceC4789a
    /* renamed from: a */
    public List<String> mo19020a() {
        return Collections.singletonList("com.oppo.launcher");
    }

    @Override // p068f6.InterfaceC4789a
    @TargetApi(11)
    /* renamed from: b */
    public void mo19021b(Context context, ComponentName componentName, int i9) {
        if (i9 == 0) {
            i9 = -1;
        }
        Intent intent = new Intent("com.oppo.unsettledevent");
        intent.putExtra("pakeageName", componentName.getPackageName());
        intent.putExtra("number", i9);
        intent.putExtra("upgradeNumber", i9);
        if (C5027a.m19603a(context, intent)) {
            context.sendBroadcast(intent);
        } else if (m19240g() == 6) {
            Bundle bundle = new Bundle();
            bundle.putInt("app_badge_count", i9);
            context.getContentResolver().call(Uri.parse("content://com.android.badge/badge"), "setAppBadgeCount", (String) null, bundle);
        }
    }

    /* renamed from: c */
    public final boolean m19236c(Object obj) {
        return obj == null || obj.toString().equals("") || obj.toString().trim().equals("null");
    }

    /* renamed from: d */
    public final Object m19237d(Class cls, String str, Class[] clsArr, Object[] objArr) throws SecurityException {
        Method methodM19239f;
        if (cls == null || m19236c(str) || (methodM19239f = m19239f(cls, str, clsArr)) == null) {
            return null;
        }
        methodM19239f.setAccessible(true);
        try {
            return methodM19239f.invoke(null, objArr);
        } catch (IllegalAccessException e9) {
            e9.printStackTrace();
            return null;
        } catch (InvocationTargetException e10) {
            e10.printStackTrace();
            return null;
        }
    }

    /* renamed from: e */
    public final Class m19238e(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    /* renamed from: f */
    public final Method m19239f(Class cls, String str, Class[] clsArr) throws SecurityException {
        if (cls == null || m19236c(str)) {
            return null;
        }
        try {
            try {
                cls.getMethods();
                cls.getDeclaredMethods();
                return cls.getDeclaredMethod(str, clsArr);
            } catch (Exception unused) {
                if (cls.getSuperclass() != null) {
                    return m19239f(cls.getSuperclass(), str, clsArr);
                }
                return null;
            }
        } catch (Exception unused2) {
            return cls.getMethod(str, clsArr);
        }
    }

    /* renamed from: g */
    public final int m19240g() throws Throwable {
        int iIntValue;
        int i9 = f17057a;
        if (i9 >= 0) {
            return i9;
        }
        try {
            iIntValue = ((Integer) m19237d(m19238e("com.color.os.ColorBuild"), "getColorOSVERSION", null, null)).intValue();
        } catch (Exception unused) {
            iIntValue = 0;
        }
        if (iIntValue == 0) {
            try {
                String strM19241h = m19241h("ro.build.version.opporom");
                if (strM19241h.startsWith("V1.4")) {
                    return 3;
                }
                if (strM19241h.startsWith("V2.0")) {
                    return 4;
                }
                if (strM19241h.startsWith("V2.1")) {
                    return 5;
                }
            } catch (Exception unused2) {
            }
        }
        f17057a = iIntValue;
        return iIntValue;
    }

    /* renamed from: h */
    public final String m19241h(String str) throws Throwable {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2 = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("getprop " + str).getInputStream()), UserMetadata.MAX_ATTRIBUTE_SIZE);
            try {
                String line = bufferedReader.readLine();
                bufferedReader.close();
                C5028b.m19605b(bufferedReader);
                return line;
            } catch (IOException unused) {
                C5028b.m19605b(bufferedReader);
                return null;
            } catch (Throwable th) {
                th = th;
                bufferedReader2 = bufferedReader;
                C5028b.m19605b(bufferedReader2);
                throw th;
            }
        } catch (IOException unused2) {
            bufferedReader = null;
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
