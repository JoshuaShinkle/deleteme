package p139m6;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import p007a6.C0042f;
import p098i6.C5057d;
import p203t5.C6319g;

/* renamed from: m6.c */
/* loaded from: classes.dex */
public final class C5330c {

    /* renamed from: a */
    public static final a f18163a = new a();

    /* renamed from: b */
    public static final String[] f18164b;

    /* renamed from: c */
    public static final DateFormat[] f18165c;

    /* renamed from: m6.c$a */
    public static final class a extends ThreadLocal<DateFormat> {
        @Override // java.lang.ThreadLocal
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public DateFormat initialValue() {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
            simpleDateFormat.setLenient(false);
            simpleDateFormat.setTimeZone(C5057d.f17448f);
            return simpleDateFormat;
        }
    }

    static {
        String[] strArr = {"EEE, dd MMM yyyy HH:mm:ss zzz", "EEEE, dd-MMM-yy HH:mm:ss zzz", "EEE MMM d HH:mm:ss yyyy", "EEE, dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MMM-yyyy HH-mm-ss z", "EEE, dd MMM yy HH:mm:ss z", "EEE dd-MMM-yyyy HH:mm:ss z", "EEE dd MMM yyyy HH:mm:ss z", "EEE dd-MMM-yyyy HH-mm-ss z", "EEE dd-MMM-yy HH:mm:ss z", "EEE dd MMM yy HH:mm:ss z", "EEE,dd-MMM-yy HH:mm:ss z", "EEE,dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MM-yyyy HH:mm:ss z", "EEE MMM d yyyy HH:mm:ss z"};
        f18164b = strArr;
        f18165c = new DateFormat[strArr.length];
    }

    /* renamed from: a */
    public static final Date m20934a(String str) {
        C0042f.m158e(str, "<this>");
        if (str.length() == 0) {
            return null;
        }
        ParsePosition parsePosition = new ParsePosition(0);
        Date date = f18163a.get().parse(str, parsePosition);
        if (parsePosition.getIndex() == str.length()) {
            return date;
        }
        String[] strArr = f18164b;
        synchronized (strArr) {
            int length = strArr.length;
            for (int i9 = 0; i9 < length; i9++) {
                DateFormat[] dateFormatArr = f18165c;
                DateFormat simpleDateFormat = dateFormatArr[i9];
                if (simpleDateFormat == null) {
                    simpleDateFormat = new SimpleDateFormat(f18164b[i9], Locale.US);
                    simpleDateFormat.setTimeZone(C5057d.f17448f);
                    dateFormatArr[i9] = simpleDateFormat;
                }
                parsePosition.setIndex(0);
                Date date2 = simpleDateFormat.parse(str, parsePosition);
                if (parsePosition.getIndex() != 0) {
                    return date2;
                }
            }
            C6319g c6319g = C6319g.f21314a;
            return null;
        }
    }

    /* renamed from: b */
    public static final String m20935b(Date date) {
        C0042f.m158e(date, "<this>");
        String str = f18163a.get().format(date);
        C0042f.m157d(str, "STANDARD_DATE_FORMAT.get().format(this)");
        return str;
    }
}
