package com.perfectcorp.utility;

import android.annotation.TargetApi;
import android.app.Activity;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: com.perfectcorp.utility.c */
/* loaded from: classes2.dex */
public class C4508c {

    /* renamed from: a */
    public static a f15944a;

    /* renamed from: b */
    public static Map<String, Boolean> f15945b;

    /* renamed from: com.perfectcorp.utility.c$a */
    public interface a {
        /* renamed from: a */
        void mo13742a(boolean z8);

        /* renamed from: b */
        void mo13743b();
    }

    /* renamed from: a */
    public static void m18114a(Activity activity, List<String> list, a aVar) {
        if (activity == null || aVar == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            for (int i9 = 0; i9 < list.size(); i9++) {
                if (activity.checkSelfPermission(list.get(i9)) != 0) {
                    f15945b = new HashMap();
                    arrayList.add(list.get(i9));
                    f15945b.put(list.get(i9), Boolean.valueOf(activity.shouldShowRequestPermissionRationale(list.get(i9))));
                }
            }
        }
        if (arrayList.size() > 0) {
            f15944a = aVar;
            activity.requestPermissions((String[]) arrayList.toArray(new String[0]), CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT);
        }
    }

    /* renamed from: b */
    public static boolean m18115b(Activity activity, List<String> list) {
        if (activity == null || list == null) {
            return false;
        }
        for (int i9 = 0; i9 < list.size(); i9++) {
            if (activity.checkSelfPermission(list.get(i9)) != 0) {
                return false;
            }
        }
        return true;
    }

    @TargetApi(23)
    /* renamed from: c */
    public static void m18116c(Activity activity, int i9, String[] strArr, int[] iArr) {
        a aVar;
        if (activity == null || (aVar = f15944a) == null || i9 != 1000) {
            return;
        }
        if (strArr.length <= 0) {
            aVar.mo13742a(false);
            return;
        }
        if (m18117d(iArr)) {
            f15944a.mo13743b();
            return;
        }
        boolean z8 = true;
        for (int i10 = 0; i10 < Math.min(strArr.length, iArr.length); i10++) {
            if (iArr[i10] == -1) {
                z8 &= !activity.shouldShowRequestPermissionRationale(strArr[i10]);
            }
        }
        f15944a.mo13742a(z8);
    }

    /* renamed from: d */
    public static boolean m18117d(int[] iArr) {
        boolean z8 = iArr.length > 0;
        for (int i9 : iArr) {
            z8 &= i9 == 0;
        }
        return z8;
    }

    /* renamed from: e */
    public static void m18118e(Activity activity, List<String> list, a aVar) {
        if (activity == null || aVar == null) {
            return;
        }
        if (m18115b(activity, list)) {
            aVar.mo13743b();
        } else {
            m18114a(activity, list, aVar);
        }
    }
}
