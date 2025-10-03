package com.cyberlink.you.chat;

import android.util.Pair;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.jivesoftware.smack.util.C5616j;

/* renamed from: com.cyberlink.you.chat.x */
/* loaded from: classes.dex */
public class C2929x {
    /* renamed from: a */
    public static String m14650a(String str, String str2, String str3, List<Pair<String, String>> list) {
        if (str3 == null) {
            str3 = "";
        }
        String str4 = "<" + str + " xmlns='" + str3 + "'";
        if (list != null) {
            for (Pair<String, String> pair : list) {
                str4 = str4 + StringUtils.SPACE + ((String) pair.first) + "='" + ((Object) C5616j.m22341f((String) pair.second)) + "'";
            }
        }
        String str5 = str4 + ">";
        if (str2 != null) {
            str5 = str5 + ((Object) C5616j.m22341f(str2));
        }
        return str5 + "</" + str + ">";
    }
}
