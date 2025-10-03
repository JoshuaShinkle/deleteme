package kotlin.text;

import java.io.Serializable;
import java.util.Set;
import java.util.regex.Pattern;
import p007a6.C0040d;
import p007a6.C0042f;

/* loaded from: classes2.dex */
public final class Regex implements Serializable {

    /* renamed from: b */
    public static final C5243a f17842b = new C5243a(null);
    private Set<Object> _options;
    private final Pattern nativePattern;

    public static final class Serialized implements Serializable {

        /* renamed from: b */
        public static final C5242a f17843b = new C5242a(null);
        private static final long serialVersionUID = 0;
        private final int flags;
        private final String pattern;

        /* renamed from: kotlin.text.Regex$Serialized$a */
        public static final class C5242a {
            public C5242a() {
            }

            public /* synthetic */ C5242a(C0040d c0040d) {
                this();
            }
        }

        public Serialized(String str, int i9) {
            C0042f.m158e(str, "pattern");
            this.pattern = str;
            this.flags = i9;
        }

        private final Object readResolve() {
            Pattern patternCompile = Pattern.compile(this.pattern, this.flags);
            C0042f.m157d(patternCompile, "compile(...)");
            return new Regex(patternCompile);
        }
    }

    /* renamed from: kotlin.text.Regex$a */
    public static final class C5243a {
        public C5243a() {
        }

        public /* synthetic */ C5243a(C0040d c0040d) {
            this();
        }
    }

    public Regex(Pattern pattern) {
        C0042f.m158e(pattern, "nativePattern");
        this.nativePattern = pattern;
    }

    private final Object writeReplace() {
        String strPattern = this.nativePattern.pattern();
        C0042f.m157d(strPattern, "pattern(...)");
        return new Serialized(strPattern, this.nativePattern.flags());
    }

    /* renamed from: a */
    public final boolean m20441a(CharSequence charSequence) {
        C0042f.m158e(charSequence, "input");
        return this.nativePattern.matcher(charSequence).matches();
    }

    /* renamed from: b */
    public final String m20442b(CharSequence charSequence, String str) {
        C0042f.m158e(charSequence, "input");
        C0042f.m158e(str, "replacement");
        String strReplaceAll = this.nativePattern.matcher(charSequence).replaceAll(str);
        C0042f.m157d(strReplaceAll, "replaceAll(...)");
        return strReplaceAll;
    }

    public String toString() {
        String string = this.nativePattern.toString();
        C0042f.m157d(string, "toString(...)");
        return string;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public Regex(String str) {
        C0042f.m158e(str, "pattern");
        Pattern patternCompile = Pattern.compile(str);
        C0042f.m157d(patternCompile, "compile(...)");
        this(patternCompile);
    }
}
