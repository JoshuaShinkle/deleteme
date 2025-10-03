package kotlin.text;

/* renamed from: kotlin.text.b */
/* loaded from: classes2.dex */
public class C5245b extends C5244a {
    /* renamed from: d */
    public static final boolean m20497d(char c9, char c10, boolean z8) {
        if (c9 == c10) {
            return true;
        }
        if (!z8) {
            return false;
        }
        char upperCase = Character.toUpperCase(c9);
        char upperCase2 = Character.toUpperCase(c10);
        return upperCase == upperCase2 || Character.toLowerCase(upperCase) == Character.toLowerCase(upperCase2);
    }
}
