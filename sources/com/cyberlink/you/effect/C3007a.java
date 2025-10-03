package com.cyberlink.you.effect;

import com.cyberlink.you.effect.C3008b;
import java.util.Locale;

/* renamed from: com.cyberlink.you.effect.a */
/* loaded from: classes.dex */
public class C3007a {
    /* renamed from: a */
    public static String m15317a(C3008b.d dVar, boolean z8) {
        if (z8) {
            return dVar.f13320a.f13318o;
        }
        String language = Locale.getDefault().getLanguage();
        String str = language.split("_")[0];
        String str2 = str.equals("en") ? dVar.f13320a.f13304a : language.equals("zh_TW") ? dVar.f13320a.f13305b : language.equals("zh_CN") ? dVar.f13320a.f13306c : str.equals("zh") ? Locale.getDefault().getCountry().equals("CN") ? dVar.f13320a.f13306c : dVar.f13320a.f13305b : str.equals("ja") ? dVar.f13320a.f13307d : str.equals("ko") ? dVar.f13320a.f13308e : str.equals("de") ? dVar.f13320a.f13309f : str.equals("es") ? dVar.f13320a.f13310g : str.equals("fr") ? dVar.f13320a.f13311h : str.equals("it") ? dVar.f13320a.f13312i : str.equals("pl") ? dVar.f13320a.f13313j : language.equals("pt_BR") ? dVar.f13320a.f13315l : language.equals("pt_PT") ? dVar.f13320a.f13314k : str.equals("ru") ? dVar.f13320a.f13316m : str.equals("nl") ? dVar.f13320a.f13317n : dVar.f13320a.f13304a;
        return str2.equals("") ? dVar.f13320a.f13318o : str2;
    }
}
