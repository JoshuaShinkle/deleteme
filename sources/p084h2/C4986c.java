package p084h2;

import android.telephony.PhoneNumberUtils;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.util.Patterns;
import android.webkit.URLUtil;
import android.webkit.WebView;
import android.widget.TextView;
import com.cyberlink.link.detect.CustomURLSpan;
import com.google.i18n.phonenumbers.PhoneNumberMatch;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.linkedin.urls.detection.UrlDetector;
import com.linkedin.urls.detection.UrlDetectorOptions;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import p238x4.C6573a;

/* renamed from: h2.c */
/* loaded from: classes.dex */
public class C4986c {

    /* renamed from: a */
    public static final b f17164a = new b() { // from class: h2.a
        @Override // p084h2.C4986c.b
        /* renamed from: a */
        public final boolean mo19346a(CharSequence charSequence, int i9, int i10) {
            return C4986c.m19357j(charSequence, i9, i10);
        }
    };

    /* renamed from: b */
    public static c f17165b = new c() { // from class: h2.b
        @Override // p084h2.C4986c.c
        /* renamed from: a */
        public final String mo19347a(Matcher matcher, String str) {
            return C4986c.m19358k(matcher, str);
        }
    };

    /* renamed from: h2.c$a */
    public class a implements Comparator<C4988e> {
        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final int compare(C4988e c4988e, C4988e c4988e2) {
            int i9 = c4988e.f17180b;
            int i10 = c4988e2.f17180b;
            if (i9 < i10) {
                return -1;
            }
            if (i9 > i10) {
                return 1;
            }
            return Integer.compare(c4988e2.f17181c, c4988e.f17181c);
        }
    }

    /* renamed from: h2.c$b */
    public interface b {
        /* renamed from: a */
        boolean mo19346a(CharSequence charSequence, int i9, int i10);
    }

    /* renamed from: h2.c$c */
    public interface c {
        /* renamed from: a */
        String mo19347a(Matcher matcher, String str);
    }

    /* renamed from: c */
    public static final void m19350c(TextView textView) {
        if ((textView.getMovementMethod() instanceof LinkMovementMethod) || !textView.getLinksClickable()) {
            return;
        }
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    /* renamed from: d */
    public static final List<C4988e> m19351d(Spannable spannable, int i9) throws UnsupportedEncodingException {
        int i10;
        int i11;
        int i12;
        ArrayList arrayList = new ArrayList();
        if (i9 == 0) {
            return arrayList;
        }
        int i13 = 0;
        URLSpan[] uRLSpanArr = (URLSpan[]) spannable.getSpans(0, spannable.length(), URLSpan.class);
        for (int length = uRLSpanArr.length - 1; length >= 0; length--) {
            spannable.removeSpan(uRLSpanArr[length]);
        }
        m19354g(arrayList, spannable, Pattern.compile("(ymk|ycp|ybc|ycf|ymkbc|ycpbc|u)://[^\\s]+"), new String[]{""}, null, f17165b);
        if ((i9 & 2) != 0 && !spannable.toString().replace(StringUtils.SPACE, "").startsWith("ftp://")) {
            m19354g(arrayList, spannable, C4987d.f17178m, new String[]{"mailto:"}, null, null);
        }
        if ((i9 & 1) != 0) {
            m19354g(arrayList, spannable, C4987d.f17177l, new String[]{"http://", "https://", "rtsp://"}, null, null);
            int length2 = 0;
            for (C6573a c6573a : new UrlDetector(spannable.toString(), UrlDetectorOptions.Default).m17856c()) {
                if (!TextUtils.isEmpty(c6573a.m25182l())) {
                    Iterator it = arrayList.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            i10 = i13;
                            i11 = i10;
                            break;
                        }
                        C4988e c4988e = (C4988e) it.next();
                        if (c4988e.f17179a.startsWith("mailto:")) {
                            String strReplaceFirst = c4988e.f17179a.replaceFirst("mailto:", "");
                            String strReplaceFirst2 = c6573a.m25173c().replaceFirst("http://", "");
                            int i14 = -1;
                            for (int i15 = i13; i15 < strReplaceFirst2.length(); i15++) {
                                if (strReplaceFirst2.charAt(i15) != '<') {
                                    if (strReplaceFirst2.charAt(i15) == '@') {
                                        break;
                                    }
                                } else {
                                    i14 = i15;
                                }
                            }
                            if (i14 == -1) {
                                i12 = 1;
                                i10 = 0;
                                strReplaceFirst2 = strReplaceFirst2.substring(0, strReplaceFirst2.length() - 1);
                            } else {
                                i12 = 1;
                                i10 = 0;
                                int i16 = i14 + 1;
                                if (i16 < strReplaceFirst2.length() - 1) {
                                    strReplaceFirst2 = strReplaceFirst2.substring(i16, strReplaceFirst2.length() - 1);
                                }
                            }
                            if (strReplaceFirst.equals(strReplaceFirst2)) {
                                length2 += strReplaceFirst.length();
                                i11 = i12;
                                break;
                            }
                        } else {
                            i10 = i13;
                        }
                        i13 = i10;
                    }
                    if (i11 != 0) {
                        break;
                    }
                    if (Patterns.WEB_URL.matcher(c6573a.toString()).matches()) {
                        C4988e c4988e2 = new C4988e();
                        int iIndexOf = length2 + spannable.toString().substring(length2).indexOf(c6573a.m25176f());
                        int length3 = c6573a.m25176f().length() + iIndexOf;
                        c4988e2.f17179a = c6573a.m25173c();
                        c4988e2.f17180b = iIndexOf;
                        c4988e2.f17181c = length3;
                        arrayList.add(c4988e2);
                        length2 = length3;
                    }
                    i13 = i10;
                }
            }
        }
        if ((i9 & 4) != 0) {
            m19356i(arrayList, spannable);
        }
        if ((i9 & 8) != 0) {
            m19355h(arrayList, spannable);
        }
        m19360m(arrayList);
        if (arrayList.size() == 0) {
            return arrayList;
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            C4988e c4988e3 = (C4988e) it2.next();
            m19353f(c4988e3.f17179a, c4988e3.f17180b, c4988e3.f17181c, spannable);
        }
        return arrayList;
    }

    /* renamed from: e */
    public static final List<String> m19352e(TextView textView, int i9) {
        ArrayList arrayList = new ArrayList();
        if (i9 == 0) {
            return arrayList;
        }
        CharSequence text = textView.getText();
        if (!(text instanceof Spannable)) {
            textView.setText(SpannableString.valueOf(text), TextView.BufferType.SPANNABLE);
            return m19352e(textView, i9);
        }
        List<C4988e> listM19351d = m19351d((Spannable) text, i9);
        if (listM19351d.size() > 0) {
            m19350c(textView);
        }
        for (C4988e c4988e : listM19351d) {
            if (URLUtil.isNetworkUrl(c4988e.f17179a)) {
                arrayList.add(c4988e.f17179a);
            }
        }
        return arrayList;
    }

    /* renamed from: f */
    public static final void m19353f(String str, int i9, int i10, Spannable spannable) {
        spannable.setSpan(new CustomURLSpan(str), i9, i10, 33);
    }

    /* renamed from: g */
    public static final void m19354g(ArrayList<C4988e> arrayList, Spannable spannable, Pattern pattern, String[] strArr, b bVar, c cVar) {
        Matcher matcher = pattern.matcher(spannable);
        while (matcher.find()) {
            int iStart = matcher.start();
            int iEnd = matcher.end();
            if (bVar == null || bVar.mo19346a(spannable, iStart, iEnd)) {
                C4988e c4988e = new C4988e();
                c4988e.f17179a = m19359l(matcher.group(0), strArr, matcher, cVar);
                c4988e.f17180b = iStart;
                c4988e.f17181c = iEnd;
                arrayList.add(c4988e);
            }
        }
    }

    /* renamed from: h */
    public static final void m19355h(ArrayList<C4988e> arrayList, Spannable spannable) throws UnsupportedEncodingException {
        int iIndexOf;
        String string = spannable.toString();
        int i9 = 0;
        while (true) {
            String strFindAddress = WebView.findAddress(string);
            if (strFindAddress == null || (iIndexOf = string.indexOf(strFindAddress)) < 0) {
                return;
            }
            C4988e c4988e = new C4988e();
            int length = strFindAddress.length() + iIndexOf;
            c4988e.f17180b = iIndexOf + i9;
            i9 += length;
            c4988e.f17181c = i9;
            string = string.substring(length);
            try {
                c4988e.f17179a = "geo:0,0?q=" + URLEncoder.encode(strFindAddress, "UTF-8");
                arrayList.add(c4988e);
            } catch (UnsupportedEncodingException unused) {
            }
        }
    }

    /* renamed from: i */
    public static final void m19356i(ArrayList<C4988e> arrayList, Spannable spannable) {
        for (PhoneNumberMatch phoneNumberMatch : PhoneNumberUtil.getInstance().findNumbers(spannable.toString(), Locale.getDefault().getCountry(), PhoneNumberUtil.Leniency.POSSIBLE, Long.MAX_VALUE)) {
            C4988e c4988e = new C4988e();
            c4988e.f17179a = "tel:" + PhoneNumberUtils.normalizeNumber(phoneNumberMatch.rawString());
            c4988e.f17180b = phoneNumberMatch.start();
            c4988e.f17181c = phoneNumberMatch.end();
            arrayList.add(c4988e);
        }
    }

    /* renamed from: j */
    public static /* synthetic */ boolean m19357j(CharSequence charSequence, int i9, int i10) {
        if (charSequence.subSequence(i9, i10).toString().matches("[\\x00-\\x7F]+")) {
            return i9 == 0 || charSequence.charAt(i9 - 1) != '@';
        }
        return false;
    }

    /* renamed from: k */
    public static /* synthetic */ String m19358k(Matcher matcher, String str) {
        String lowerCase = str.toLowerCase(Locale.getDefault());
        if (lowerCase.startsWith("https")) {
            return str.substring(0, 5).toLowerCase(Locale.getDefault()) + str.substring(5);
        }
        if (!lowerCase.startsWith("http") && !lowerCase.startsWith("rtsp")) {
            return str;
        }
        return str.substring(0, 4).toLowerCase(Locale.getDefault()) + str.substring(4);
    }

    /* renamed from: l */
    public static final String m19359l(String str, String[] strArr, Matcher matcher, c cVar) {
        boolean z8;
        if (cVar != null) {
            str = cVar.mo19347a(matcher, str);
        }
        int length = strArr.length;
        int i9 = 0;
        while (true) {
            if (i9 >= length) {
                z8 = false;
                break;
            }
            String str2 = strArr[i9];
            if (str.regionMatches(true, 0, str2, 0, str2.length())) {
                z8 = true;
                if (!str.regionMatches(false, 0, str2, 0, str2.length())) {
                    str = str2 + str.substring(str2.length());
                }
            } else {
                i9++;
            }
        }
        if (z8) {
            return str;
        }
        return strArr[0] + str;
    }

    /* renamed from: m */
    public static final void m19360m(ArrayList<C4988e> arrayList) {
        int i9;
        Collections.sort(arrayList, new a());
        int size = arrayList.size();
        int i10 = 0;
        while (i10 < size - 1) {
            C4988e c4988e = arrayList.get(i10);
            int i11 = i10 + 1;
            C4988e c4988e2 = arrayList.get(i11);
            int i12 = c4988e.f17180b;
            int i13 = c4988e2.f17180b;
            if (i12 <= i13 && (i9 = c4988e.f17181c) > i13) {
                int i14 = c4988e2.f17181c;
                int i15 = (i14 > i9 && i9 - i12 <= i14 - i13) ? i9 - i12 < i14 - i13 ? i10 : -1 : i11;
                if (i15 != -1) {
                    arrayList.remove(i15);
                    size--;
                }
            }
            i10 = i11;
        }
    }
}
