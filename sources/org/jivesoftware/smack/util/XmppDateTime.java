package org.jivesoftware.smack.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class XmppDateTime {

    /* renamed from: a */
    public static final DateFormatType f19426a;

    /* renamed from: b */
    public static final Pattern f19427b;

    /* renamed from: c */
    public static final DateFormatType f19428c;

    /* renamed from: d */
    public static final Pattern f19429d;

    /* renamed from: e */
    public static final DateFormatType f19430e;

    /* renamed from: f */
    public static final Pattern f19431f;

    /* renamed from: g */
    public static final DateFormatType f19432g;

    /* renamed from: h */
    public static final Pattern f19433h;

    /* renamed from: i */
    public static final DateFormatType f19434i;

    /* renamed from: j */
    public static final Pattern f19435j;

    /* renamed from: k */
    public static final DateFormatType f19436k;

    /* renamed from: l */
    public static final Pattern f19437l;

    /* renamed from: m */
    public static final DateFormatType f19438m;

    /* renamed from: n */
    public static final Pattern f19439n;

    /* renamed from: o */
    public static final DateFormat f19440o;

    /* renamed from: p */
    public static final DateFormat f19441p;

    /* renamed from: q */
    public static final DateFormat f19442q;

    /* renamed from: r */
    public static final DateFormat f19443r;

    /* renamed from: s */
    public static final Pattern f19444s;

    /* renamed from: t */
    public static final List<C5606b> f19445t;

    public enum DateFormatType {
        XEP_0082_DATE_PROFILE("yyyy-MM-dd"),
        XEP_0082_DATETIME_PROFILE("yyyy-MM-dd'T'HH:mm:ssZ"),
        XEP_0082_DATETIME_MILLIS_PROFILE("yyyy-MM-dd'T'HH:mm:ss.SSSZ"),
        XEP_0082_TIME_PROFILE("hh:mm:ss"),
        XEP_0082_TIME_ZONE_PROFILE("hh:mm:ssZ"),
        XEP_0082_TIME_MILLIS_PROFILE("hh:mm:ss.SSS"),
        XEP_0082_TIME_MILLIS_ZONE_PROFILE("hh:mm:ss.SSSZ"),
        XEP_0091_DATETIME("yyyyMMdd'T'HH:mm:ss");

        private final boolean CONVERT_TIMEZONE;
        private final DateFormat FORMATTER;
        private final String FORMAT_STRING;

        DateFormatType(String str) {
            this.FORMAT_STRING = str;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str);
            this.FORMATTER = simpleDateFormat;
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            this.CONVERT_TIMEZONE = str.charAt(str.length() - 1) == 'Z';
        }

        /* renamed from: a */
        public String m22273a(Date date) {
            String str;
            synchronized (this.FORMATTER) {
                str = this.FORMATTER.format(date);
            }
            return this.CONVERT_TIMEZONE ? XmppDateTime.m22265b(str) : str;
        }

        /* renamed from: b */
        public Date m22274b(String str) {
            Date date;
            if (this.CONVERT_TIMEZONE) {
                str = XmppDateTime.m22266c(str);
            }
            synchronized (this.FORMATTER) {
                date = this.FORMATTER.parse(str);
            }
            return date;
        }
    }

    /* renamed from: org.jivesoftware.smack.util.XmppDateTime$a */
    public class C5605a implements Comparator<Calendar> {

        /* renamed from: b */
        public final /* synthetic */ Calendar f19455b;

        public C5605a(Calendar calendar) {
            this.f19455b = calendar;
        }

        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(Calendar calendar, Calendar calendar2) {
            return new Long(this.f19455b.getTimeInMillis() - calendar.getTimeInMillis()).compareTo(new Long(this.f19455b.getTimeInMillis() - calendar2.getTimeInMillis()));
        }
    }

    /* renamed from: org.jivesoftware.smack.util.XmppDateTime$b */
    public static class C5606b {

        /* renamed from: a */
        public final Pattern f19456a;

        /* renamed from: b */
        public final DateFormatType f19457b;

        public C5606b(Pattern pattern, DateFormatType dateFormatType) {
            this.f19456a = pattern;
            this.f19457b = dateFormatType;
        }
    }

    static {
        DateFormatType dateFormatType = DateFormatType.XEP_0082_DATE_PROFILE;
        f19426a = dateFormatType;
        Pattern patternCompile = Pattern.compile("^\\d+-\\d+-\\d+$");
        f19427b = patternCompile;
        DateFormatType dateFormatType2 = DateFormatType.XEP_0082_TIME_MILLIS_ZONE_PROFILE;
        f19428c = dateFormatType2;
        Pattern patternCompile2 = Pattern.compile("^(\\d+:){2}\\d+.\\d+(Z|([+-](\\d+:\\d+)))$");
        f19429d = patternCompile2;
        DateFormatType dateFormatType3 = DateFormatType.XEP_0082_TIME_MILLIS_PROFILE;
        f19430e = dateFormatType3;
        Pattern patternCompile3 = Pattern.compile("^(\\d+:){2}\\d+.\\d+$");
        f19431f = patternCompile3;
        DateFormatType dateFormatType4 = DateFormatType.XEP_0082_TIME_ZONE_PROFILE;
        f19432g = dateFormatType4;
        Pattern patternCompile4 = Pattern.compile("^(\\d+:){2}\\d+(Z|([+-](\\d+:\\d+)))$");
        f19433h = patternCompile4;
        DateFormatType dateFormatType5 = DateFormatType.XEP_0082_TIME_PROFILE;
        f19434i = dateFormatType5;
        Pattern patternCompile5 = Pattern.compile("^(\\d+:){2}\\d+$");
        f19435j = patternCompile5;
        DateFormatType dateFormatType6 = DateFormatType.XEP_0082_DATETIME_MILLIS_PROFILE;
        f19436k = dateFormatType6;
        Pattern patternCompile6 = Pattern.compile("^\\d+(-\\d+){2}+T(\\d+:){2}\\d+.\\d+(Z|([+-](\\d+:\\d+)))?$");
        f19437l = patternCompile6;
        DateFormatType dateFormatType7 = DateFormatType.XEP_0082_DATETIME_PROFILE;
        f19438m = dateFormatType7;
        Pattern patternCompile7 = Pattern.compile("^\\d+(-\\d+){2}+T(\\d+:){2}\\d+(Z|([+-](\\d+:\\d+)))?$");
        f19439n = patternCompile7;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd'T'HH:mm:ss");
        f19440o = simpleDateFormat;
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyyMd'T'HH:mm:ss");
        f19441p = simpleDateFormat2;
        SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("yyyyMdd'T'HH:mm:ss");
        f19442q = simpleDateFormat3;
        SimpleDateFormat simpleDateFormat4 = new SimpleDateFormat("yyyyMMd'T'HH:mm:ss");
        f19443r = simpleDateFormat4;
        f19444s = Pattern.compile("^\\d+T\\d+:\\d+:\\d+$");
        ArrayList arrayList = new ArrayList();
        f19445t = arrayList;
        TimeZone timeZone = TimeZone.getTimeZone("UTC");
        simpleDateFormat.setTimeZone(timeZone);
        simpleDateFormat2.setTimeZone(timeZone);
        simpleDateFormat3.setTimeZone(timeZone);
        simpleDateFormat3.setLenient(false);
        simpleDateFormat4.setTimeZone(timeZone);
        simpleDateFormat4.setLenient(false);
        arrayList.add(new C5606b(patternCompile, dateFormatType));
        arrayList.add(new C5606b(patternCompile6, dateFormatType6));
        arrayList.add(new C5606b(patternCompile7, dateFormatType7));
        arrayList.add(new C5606b(patternCompile2, dateFormatType2));
        arrayList.add(new C5606b(patternCompile3, dateFormatType3));
        arrayList.add(new C5606b(patternCompile4, dateFormatType4));
        arrayList.add(new C5606b(patternCompile5, dateFormatType5));
    }

    /* renamed from: a */
    public static String m22264a(TimeZone timeZone) {
        int rawOffset = timeZone.getRawOffset();
        int i9 = rawOffset / 3600000;
        return String.format("%+d:%02d", Integer.valueOf(i9), Integer.valueOf(Math.abs((rawOffset / 60000) - (i9 * 60))));
    }

    /* renamed from: b */
    public static String m22265b(String str) {
        int length = str.length();
        int i9 = length - 2;
        return (str.substring(0, i9) + ':') + str.substring(i9, length);
    }

    /* renamed from: c */
    public static String m22266c(String str) {
        return str.charAt(str.length() + (-1)) == 'Z' ? str.replace("Z", "+0000") : str.replaceAll("([\\+\\-]\\d\\d):(\\d\\d)", "$1$2");
    }

    /* renamed from: d */
    public static Calendar m22267d(Calendar calendar, List<Calendar> list) {
        Collections.sort(list, new C5605a(calendar));
        return list.get(0);
    }

    /* renamed from: e */
    public static List<Calendar> m22268e(Calendar calendar, Calendar... calendarArr) {
        ArrayList arrayList = new ArrayList();
        for (Calendar calendar2 : calendarArr) {
            if (calendar2 != null && calendar2.before(calendar)) {
                arrayList.add(calendar2);
            }
        }
        return arrayList;
    }

    /* renamed from: f */
    public static String m22269f(Date date) {
        String strM22273a;
        DateFormatType dateFormatType = f19436k;
        synchronized (dateFormatType) {
            strM22273a = dateFormatType.m22273a(date);
        }
        return strM22273a;
    }

    /* renamed from: g */
    public static Date m22270g(String str, int i9) {
        Date date;
        if (i9 == 6) {
            DateFormat dateFormat = f19441p;
            synchronized (dateFormat) {
                date = dateFormat.parse(str);
            }
            return date;
        }
        Calendar calendar = Calendar.getInstance();
        List<Calendar> listM22268e = m22268e(calendar, m22272i(str, f19442q), m22272i(str, f19443r));
        if (listM22268e.isEmpty()) {
            return null;
        }
        return m22267d(calendar, listM22268e).getTime();
    }

    /* renamed from: h */
    public static Date m22271h(String str) {
        Date dateM22274b;
        Date date;
        if (f19444s.matcher(str).matches()) {
            int length = str.split("T")[0].length();
            if (length >= 8) {
                DateFormat dateFormat = f19440o;
                synchronized (dateFormat) {
                    date = dateFormat.parse(str);
                }
                return date;
            }
            Date dateM22270g = m22270g(str, length);
            if (dateM22270g != null) {
                return dateM22270g;
            }
        } else {
            for (C5606b c5606b : f19445t) {
                if (c5606b.f19456a.matcher(str).matches()) {
                    return c5606b.f19457b.m22274b(str);
                }
            }
        }
        DateFormatType dateFormatType = f19438m;
        synchronized (dateFormatType) {
            dateM22274b = dateFormatType.m22274b(str);
        }
        return dateM22274b;
    }

    /* renamed from: i */
    public static synchronized Calendar m22272i(String str, DateFormat dateFormat) {
        try {
            dateFormat.parse(str);
        } catch (ParseException unused) {
            return null;
        }
        return dateFormat.getCalendar();
    }
}
