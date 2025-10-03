package kotlin.text;

import java.util.ArrayList;
import java.util.List;
import kotlin.collections.C5226i;
import kotlin.collections.C5234q;
import p007a6.C0042f;
import p257z5.InterfaceC6832b;

/* loaded from: classes2.dex */
public class StringsKt__IndentKt extends C5248e {
    /* renamed from: b */
    public static final InterfaceC6832b<String, String> m20443b(final String str) {
        return str.length() == 0 ? new InterfaceC6832b<String, String>() { // from class: kotlin.text.StringsKt__IndentKt$getIndentFunction$1
            @Override // p257z5.InterfaceC6832b
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public final String mo20353b(String str2) {
                C0042f.m158e(str2, "line");
                return str2;
            }
        } : new InterfaceC6832b<String, String>() { // from class: kotlin.text.StringsKt__IndentKt$getIndentFunction$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // p257z5.InterfaceC6832b
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public final String mo20353b(String str2) {
                C0042f.m158e(str2, "line");
                return str + str2;
            }
        };
    }

    /* renamed from: c */
    public static final String m20444c(String str, String str2, String str3) {
        int i9;
        String strMo20353b;
        C0042f.m158e(str, "<this>");
        C0042f.m158e(str2, "newIndent");
        C0042f.m158e(str3, "marginPrefix");
        if (!(!C5255l.m20514m(str3))) {
            throw new IllegalArgumentException("marginPrefix must be non-blank string.".toString());
        }
        List<String> listM20470V = StringsKt__StringsKt.m20470V(str);
        int length = str.length() + (str2.length() * listM20470V.size());
        InterfaceC6832b<String, String> interfaceC6832bM20443b = m20443b(str2);
        int iM20401g = C5226i.m20401g(listM20470V);
        ArrayList arrayList = new ArrayList();
        int i10 = 0;
        for (Object obj : listM20470V) {
            int i11 = i10 + 1;
            if (i10 < 0) {
                C5226i.m20407m();
            }
            String str4 = (String) obj;
            String strSubstring = null;
            if ((i10 == 0 || i10 == iM20401g) && C5255l.m20514m(str4)) {
                str4 = null;
            } else {
                int length2 = str4.length();
                int i12 = 0;
                while (true) {
                    if (i12 >= length2) {
                        i9 = -1;
                        break;
                    }
                    if (!C5244a.m20496c(str4.charAt(i12))) {
                        i9 = i12;
                        break;
                    }
                    i12++;
                }
                if (i9 != -1) {
                    int i13 = i9;
                    if (C5255l.m20524w(str4, str3, i9, false, 4, null)) {
                        int length3 = i13 + str3.length();
                        C0042f.m156c(str4, "null cannot be cast to non-null type java.lang.String");
                        strSubstring = str4.substring(length3);
                        C0042f.m157d(strSubstring, "substring(...)");
                    }
                }
                if (strSubstring != null && (strMo20353b = interfaceC6832bM20443b.mo20353b(strSubstring)) != null) {
                    str4 = strMo20353b;
                }
            }
            if (str4 != null) {
                arrayList.add(str4);
            }
            i10 = i11;
        }
        String string = ((StringBuilder) C5234q.m20426v(arrayList, new StringBuilder(length), (124 & 2) != 0 ? ", " : "\n", (124 & 4) != 0 ? "" : null, (124 & 8) == 0 ? null : "", (124 & 16) != 0 ? -1 : 0, (124 & 32) != 0 ? "..." : null, (124 & 64) != 0 ? null : null)).toString();
        C0042f.m157d(string, "toString(...)");
        return string;
    }

    /* renamed from: d */
    public static final String m20445d(String str, String str2) {
        C0042f.m158e(str, "<this>");
        C0042f.m158e(str2, "marginPrefix");
        return m20444c(str, "", str2);
    }

    /* renamed from: e */
    public static /* synthetic */ String m20446e(String str, String str2, int i9, Object obj) {
        if ((i9 & 1) != 0) {
            str2 = "|";
        }
        return m20445d(str, str2);
    }
}
