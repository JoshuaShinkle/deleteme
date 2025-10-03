package p116k4;

import android.text.InputFilter;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.widget.EditText;
import com.cyberlink.p030U.R;
import com.cyberlink.you.Globals;
import java.net.URLDecoder;
import java.util.Arrays;
import p197t.C6273a;

/* renamed from: k4.o0 */
/* loaded from: classes.dex */
public final class C5170o0 {
    /* renamed from: a */
    public static String m20166a(String str) {
        char[] charArray = str.toCharArray();
        char[] cArr = new char[charArray.length];
        int i9 = 0;
        for (int i10 = 0; i10 < charArray.length; i10++) {
            if (m20171f(charArray[i10])) {
                cArr[i9] = charArray[i10];
                i9++;
            }
        }
        return new String(Arrays.copyOf(cArr, i9));
    }

    /* renamed from: b */
    public static String m20167b(String str, int i9) {
        if (m20170e(str)) {
            return "";
        }
        EditText editText = new EditText(Globals.m7372O());
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(i9)});
        editText.setText(str);
        return editText.getText().toString();
    }

    /* renamed from: c */
    public static SpannableString m20168c(String str, String str2) {
        SpannableString spannableString = new SpannableString(str);
        if (Globals.m7372O() != null && !m20170e(str2)) {
            StringBuilder sb = new StringBuilder(str.toLowerCase());
            String lowerCase = str2.toLowerCase();
            int length = 0;
            while (true) {
                int iIndexOf = sb.indexOf(lowerCase, length);
                if (iIndexOf == -1) {
                    break;
                }
                spannableString.setSpan(new BackgroundColorSpan(C6273a.m24024c(Globals.m7372O(), R.color.you_color_search_keyword)), iIndexOf, str2.length() + iIndexOf, 33);
                length = iIndexOf + str2.length();
            }
        }
        return spannableString;
    }

    /* renamed from: d */
    public static boolean m20169d(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }

    /* renamed from: e */
    public static boolean m20170e(CharSequence charSequence) {
        return m20169d(charSequence) || "null".equalsIgnoreCase(charSequence.toString());
    }

    /* renamed from: f */
    public static boolean m20171f(char c9) {
        return c9 >= ' ' || c9 == '\t' || c9 == '\n' || c9 == '\r';
    }

    /* renamed from: g */
    public static String m20172g(String str, int i9, char c9) {
        if (m20170e(str)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i10 = 0; i10 < str.length(); i10++) {
            if (i10 % i9 == 0 && i10 != 0) {
                sb.append(c9);
            }
            sb.append(str.charAt(i10));
        }
        return sb.toString();
    }

    /* renamed from: h */
    public static String m20173h(String str) {
        if (m20170e(str)) {
            return "";
        }
        try {
            return URLDecoder.decode(str, "UTF-8");
        } catch (Exception unused) {
            return "";
        }
    }
}
