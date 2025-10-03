package org.apache.commons.lang3.time;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class DateUtils {
    public static final long MILLIS_PER_DAY = 86400000;
    public static final long MILLIS_PER_HOUR = 3600000;
    public static final long MILLIS_PER_MINUTE = 60000;
    public static final long MILLIS_PER_SECOND = 1000;
    private static final int MODIFY_CEILING = 2;
    private static final int MODIFY_ROUND = 1;
    private static final int MODIFY_TRUNCATE = 0;
    public static final int RANGE_MONTH_MONDAY = 6;
    public static final int RANGE_MONTH_SUNDAY = 5;
    public static final int RANGE_WEEK_CENTER = 4;
    public static final int RANGE_WEEK_MONDAY = 2;
    public static final int RANGE_WEEK_RELATIVE = 3;
    public static final int RANGE_WEEK_SUNDAY = 1;
    public static final int SEMI_MONTH = 1001;
    private static final int[][] fields = {new int[]{14}, new int[]{13}, new int[]{12}, new int[]{11, 10}, new int[]{5, 5, 9}, new int[]{2, 1001}, new int[]{1}, new int[]{0}};

    public static class DateIterator implements Iterator<Calendar> {
        private final Calendar endFinal;
        private final Calendar spot;

        public DateIterator(Calendar calendar, Calendar calendar2) {
            this.endFinal = calendar2;
            this.spot = calendar;
            calendar.add(5, -1);
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.spot.before(this.endFinal);
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Iterator
        public Calendar next() {
            if (this.spot.equals(this.endFinal)) {
                throw new NoSuchElementException();
            }
            this.spot.add(5, 1);
            return (Calendar) this.spot.clone();
        }
    }

    private static Date add(Date date, int i9, int i10) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(i9, i10);
        return calendar.getTime();
    }

    public static Date addDays(Date date, int i9) {
        return add(date, 5, i9);
    }

    public static Date addHours(Date date, int i9) {
        return add(date, 11, i9);
    }

    public static Date addMilliseconds(Date date, int i9) {
        return add(date, 14, i9);
    }

    public static Date addMinutes(Date date, int i9) {
        return add(date, 12, i9);
    }

    public static Date addMonths(Date date, int i9) {
        return add(date, 2, i9);
    }

    public static Date addSeconds(Date date, int i9) {
        return add(date, 13, i9);
    }

    public static Date addWeeks(Date date, int i9) {
        return add(date, 3, i9);
    }

    public static Date addYears(Date date, int i9) {
        return add(date, 1, i9);
    }

    public static Date ceiling(Date date, int i9) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        modify(calendar, i9, 2);
        return calendar.getTime();
    }

    private static long getFragment(Date date, int i9, TimeUnit timeUnit) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getFragment(calendar, i9, timeUnit);
    }

    public static long getFragmentInDays(Date date, int i9) {
        return getFragment(date, i9, TimeUnit.DAYS);
    }

    public static long getFragmentInHours(Date date, int i9) {
        return getFragment(date, i9, TimeUnit.HOURS);
    }

    public static long getFragmentInMilliseconds(Date date, int i9) {
        return getFragment(date, i9, TimeUnit.MILLISECONDS);
    }

    public static long getFragmentInMinutes(Date date, int i9) {
        return getFragment(date, i9, TimeUnit.MINUTES);
    }

    public static long getFragmentInSeconds(Date date, int i9) {
        return getFragment(date, i9, TimeUnit.SECONDS);
    }

    public static boolean isSameDay(Date date, Date date2) {
        if (date == null || date2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);
        return isSameDay(calendar, calendar2);
    }

    public static boolean isSameInstant(Date date, Date date2) {
        if (date == null || date2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        return date.getTime() == date2.getTime();
    }

    public static boolean isSameLocalTime(Calendar calendar, Calendar calendar2) {
        if (calendar == null || calendar2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        return calendar.get(14) == calendar2.get(14) && calendar.get(13) == calendar2.get(13) && calendar.get(12) == calendar2.get(12) && calendar.get(11) == calendar2.get(11) && calendar.get(6) == calendar2.get(6) && calendar.get(1) == calendar2.get(1) && calendar.get(0) == calendar2.get(0) && calendar.getClass() == calendar2.getClass();
    }

    public static Iterator<Calendar> iterator(Date date, int i9) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return iterator(calendar, i9);
    }

    /* JADX WARN: Removed duplicated region for block: B:59:0x00c5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static void modify(Calendar calendar, int i9, int i10) {
        char c9;
        int i11;
        boolean z8;
        char c10;
        if (calendar.get(1) > 280000000) {
            throw new ArithmeticException("Calendar value too large for accurate calculations");
        }
        if (i9 == 14) {
            return;
        }
        Date time = calendar.getTime();
        long time2 = time.getTime();
        int i12 = calendar.get(14);
        if (i10 == 0 || i12 < 500) {
            time2 -= i12;
        }
        boolean z9 = i9 == 13;
        int i13 = calendar.get(13);
        if (!z9 && (i10 == 0 || i13 < 30)) {
            time2 -= i13 * 1000;
        }
        if (i9 == 12) {
            z9 = true;
        }
        int i14 = calendar.get(12);
        if (!z9 && (i10 == 0 || i14 < 30)) {
            time2 -= i14 * 60000;
        }
        if (time.getTime() != time2) {
            time.setTime(time2);
            calendar.setTime(time);
        }
        boolean z10 = false;
        for (int[] iArr : fields) {
            for (int i15 : iArr) {
                if (i15 == i9) {
                    if (i10 == 2 || (i10 == 1 && z10)) {
                        if (i9 == 1001) {
                            if (calendar.get(5) == 1) {
                                calendar.add(5, 15);
                                return;
                            } else {
                                calendar.add(5, -15);
                                calendar.add(2, 1);
                                return;
                            }
                        }
                        if (i9 != 9) {
                            calendar.add(iArr[0], 1);
                            return;
                        } else if (calendar.get(11) == 0) {
                            calendar.add(11, 12);
                            return;
                        } else {
                            calendar.add(11, -12);
                            calendar.add(5, 1);
                            return;
                        }
                    }
                    return;
                }
            }
            if (i9 != 9) {
                if (i9 == 1001 && iArr[0] == 5) {
                    i11 = calendar.get(5) - 1;
                    if (i11 >= 15) {
                        i11 -= 15;
                    }
                    z10 = i11 > 7;
                    z8 = true;
                    c9 = '\f';
                } else {
                    c9 = '\f';
                    i11 = 0;
                    z8 = false;
                }
            } else if (iArr[0] == 11) {
                int i16 = calendar.get(11);
                c9 = '\f';
                if (i16 >= 12) {
                    i16 -= 12;
                }
                int i17 = i16;
                z10 = i17 >= 6;
                i11 = i17;
                z8 = true;
            }
            if (z8) {
                c10 = 0;
            } else {
                c10 = 0;
                int actualMinimum = calendar.getActualMinimum(iArr[0]);
                int actualMaximum = calendar.getActualMaximum(iArr[0]);
                int i18 = calendar.get(iArr[0]) - actualMinimum;
                z10 = i18 > (actualMaximum - actualMinimum) / 2;
                i11 = i18;
            }
            if (i11 != 0) {
                int i19 = iArr[c10];
                calendar.set(i19, calendar.get(i19) - i11);
            }
        }
        throw new IllegalArgumentException("The field " + i9 + " is not supported");
    }

    public static Date parseDate(String str, String... strArr) {
        return parseDate(str, null, strArr);
    }

    public static Date parseDateStrictly(String str, String... strArr) {
        return parseDateStrictly(str, null, strArr);
    }

    private static Date parseDateWithLeniency(String str, Locale locale, String[] strArr, boolean z8) throws ParseException {
        if (str == null || strArr == null) {
            throw new IllegalArgumentException("Date and Patterns must not be null");
        }
        SimpleDateFormat simpleDateFormat = locale == null ? new SimpleDateFormat() : new SimpleDateFormat("", locale);
        simpleDateFormat.setLenient(z8);
        ParsePosition parsePosition = new ParsePosition(0);
        for (String str2 : strArr) {
            simpleDateFormat.applyPattern(str2.endsWith("ZZ") ? str2.substring(0, str2.length() - 1) : str2);
            parsePosition.setIndex(0);
            String strReplaceAll = str2.endsWith("ZZ") ? str.replaceAll("([-+][0-9][0-9]):([0-9][0-9])$", "$1$2") : str;
            Date date = simpleDateFormat.parse(strReplaceAll, parsePosition);
            if (date != null && parsePosition.getIndex() == strReplaceAll.length()) {
                return date;
            }
        }
        throw new ParseException("Unable to parse the date: " + str, -1);
    }

    public static Date round(Date date, int i9) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        modify(calendar, i9, 1);
        return calendar.getTime();
    }

    private static Date set(Date date, int i9, int i10) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setLenient(false);
        calendar.setTime(date);
        calendar.set(i9, i10);
        return calendar.getTime();
    }

    public static Date setDays(Date date, int i9) {
        return set(date, 5, i9);
    }

    public static Date setHours(Date date, int i9) {
        return set(date, 11, i9);
    }

    public static Date setMilliseconds(Date date, int i9) {
        return set(date, 14, i9);
    }

    public static Date setMinutes(Date date, int i9) {
        return set(date, 12, i9);
    }

    public static Date setMonths(Date date, int i9) {
        return set(date, 2, i9);
    }

    public static Date setSeconds(Date date, int i9) {
        return set(date, 13, i9);
    }

    public static Date setYears(Date date, int i9) {
        return set(date, 1, i9);
    }

    public static Calendar toCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    public static Date truncate(Date date, int i9) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        modify(calendar, i9, 0);
        return calendar.getTime();
    }

    public static int truncatedCompareTo(Calendar calendar, Calendar calendar2, int i9) {
        return truncate(calendar, i9).compareTo(truncate(calendar2, i9));
    }

    public static boolean truncatedEquals(Calendar calendar, Calendar calendar2, int i9) {
        return truncatedCompareTo(calendar, calendar2, i9) == 0;
    }

    public static long getFragmentInDays(Calendar calendar, int i9) {
        return getFragment(calendar, i9, TimeUnit.DAYS);
    }

    public static long getFragmentInHours(Calendar calendar, int i9) {
        return getFragment(calendar, i9, TimeUnit.HOURS);
    }

    public static long getFragmentInMilliseconds(Calendar calendar, int i9) {
        return getFragment(calendar, i9, TimeUnit.MILLISECONDS);
    }

    public static long getFragmentInMinutes(Calendar calendar, int i9) {
        return getFragment(calendar, i9, TimeUnit.MINUTES);
    }

    public static long getFragmentInSeconds(Calendar calendar, int i9) {
        return getFragment(calendar, i9, TimeUnit.SECONDS);
    }

    public static Date parseDate(String str, Locale locale, String... strArr) {
        return parseDateWithLeniency(str, locale, strArr, true);
    }

    public static Date parseDateStrictly(String str, Locale locale, String... strArr) {
        return parseDateWithLeniency(str, null, strArr, false);
    }

    public static boolean truncatedEquals(Date date, Date date2, int i9) {
        return truncatedCompareTo(date, date2, i9) == 0;
    }

    public static boolean isSameInstant(Calendar calendar, Calendar calendar2) {
        if (calendar == null || calendar2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        return calendar.getTime().getTime() == calendar2.getTime().getTime();
    }

    public static int truncatedCompareTo(Date date, Date date2, int i9) {
        return truncate(date, i9).compareTo(truncate(date2, i9));
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0051  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static long getFragment(Calendar calendar, int i9, TimeUnit timeUnit) {
        long jConvert;
        if (calendar != null) {
            TimeUnit timeUnit2 = TimeUnit.DAYS;
            int i10 = timeUnit == timeUnit2 ? 0 : 1;
            long jConvert2 = 0;
            if (i9 == 1) {
                jConvert = timeUnit.convert(calendar.get(6) - i10, timeUnit2);
            } else {
                if (i9 == 2) {
                    jConvert = timeUnit.convert(calendar.get(5) - i10, timeUnit2);
                }
                if (i9 != 1 || i9 == 2 || i9 == 5 || i9 == 6) {
                    jConvert2 += timeUnit.convert(calendar.get(11), TimeUnit.HOURS);
                } else {
                    switch (i9) {
                        case 11:
                            break;
                        case 12:
                            jConvert2 += timeUnit.convert(calendar.get(13), TimeUnit.SECONDS);
                        case 13:
                            return jConvert2 + timeUnit.convert(calendar.get(14), TimeUnit.MILLISECONDS);
                        case 14:
                            return jConvert2;
                        default:
                            throw new IllegalArgumentException("The fragment " + i9 + " is not supported");
                    }
                }
                jConvert2 += timeUnit.convert(calendar.get(12), TimeUnit.MINUTES);
                jConvert2 += timeUnit.convert(calendar.get(13), TimeUnit.SECONDS);
                return jConvert2 + timeUnit.convert(calendar.get(14), TimeUnit.MILLISECONDS);
            }
            jConvert2 = 0 + jConvert;
            if (i9 != 1) {
                jConvert2 += timeUnit.convert(calendar.get(11), TimeUnit.HOURS);
                jConvert2 += timeUnit.convert(calendar.get(12), TimeUnit.MINUTES);
                jConvert2 += timeUnit.convert(calendar.get(13), TimeUnit.SECONDS);
            }
            return jConvert2 + timeUnit.convert(calendar.get(14), TimeUnit.MILLISECONDS);
        }
        throw new IllegalArgumentException("The date must not be null");
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x007d A[LOOP:0: B:30:0x0077->B:32:0x007d, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0087 A[LOOP:1: B:33:0x0081->B:35:0x0087, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static Iterator<Calendar> iterator(Calendar calendar, int i9) {
        Calendar calendarTruncate;
        Calendar calendarTruncate2;
        int i10;
        if (calendar != null) {
            int i11 = 2;
            switch (i9) {
                case 1:
                case 2:
                case 3:
                case 4:
                    calendarTruncate = truncate(calendar, 5);
                    calendarTruncate2 = truncate(calendar, 5);
                    if (i9 == 2) {
                        i10 = 1;
                    } else if (i9 == 3) {
                        i11 = calendar.get(7);
                        i10 = i11 - 1;
                    } else if (i9 != 4) {
                        i11 = 1;
                        i10 = 7;
                    } else {
                        int i12 = calendar.get(7) - 3;
                        i10 = calendar.get(7) + 3;
                        i11 = i12;
                    }
                    if (i11 < 1) {
                        i11 += 7;
                    }
                    if (i11 > 7) {
                        i11 -= 7;
                    }
                    if (i10 < 1) {
                        i10 += 7;
                    }
                    if (i10 > 7) {
                        i10 -= 7;
                    }
                    while (calendarTruncate.get(7) != i11) {
                        calendarTruncate.add(5, -1);
                    }
                    while (calendarTruncate2.get(7) != i10) {
                        calendarTruncate2.add(5, 1);
                    }
                    return new DateIterator(calendarTruncate, calendarTruncate2);
                case 5:
                case 6:
                    Calendar calendarTruncate3 = truncate(calendar, 2);
                    Calendar calendar2 = (Calendar) calendarTruncate3.clone();
                    calendar2.add(2, 1);
                    calendar2.add(5, -1);
                    if (i9 == 6) {
                        calendarTruncate2 = calendar2;
                        calendarTruncate = calendarTruncate3;
                        i10 = 1;
                        if (i11 < 1) {
                        }
                        if (i11 > 7) {
                        }
                        if (i10 < 1) {
                        }
                        if (i10 > 7) {
                        }
                        while (calendarTruncate.get(7) != i11) {
                        }
                        while (calendarTruncate2.get(7) != i10) {
                        }
                        return new DateIterator(calendarTruncate, calendarTruncate2);
                    }
                    i11 = 1;
                    calendarTruncate2 = calendar2;
                    calendarTruncate = calendarTruncate3;
                    i10 = 7;
                    if (i11 < 1) {
                    }
                    if (i11 > 7) {
                    }
                    if (i10 < 1) {
                    }
                    if (i10 > 7) {
                    }
                    while (calendarTruncate.get(7) != i11) {
                    }
                    while (calendarTruncate2.get(7) != i10) {
                    }
                    return new DateIterator(calendarTruncate, calendarTruncate2);
                default:
                    throw new IllegalArgumentException("The range style " + i9 + " is not valid.");
            }
        }
        throw new IllegalArgumentException("The date must not be null");
    }

    public static Calendar ceiling(Calendar calendar, int i9) {
        if (calendar != null) {
            Calendar calendar2 = (Calendar) calendar.clone();
            modify(calendar2, i9, 2);
            return calendar2;
        }
        throw new IllegalArgumentException("The date must not be null");
    }

    public static Calendar round(Calendar calendar, int i9) {
        if (calendar != null) {
            Calendar calendar2 = (Calendar) calendar.clone();
            modify(calendar2, i9, 1);
            return calendar2;
        }
        throw new IllegalArgumentException("The date must not be null");
    }

    public static Calendar truncate(Calendar calendar, int i9) {
        if (calendar != null) {
            Calendar calendar2 = (Calendar) calendar.clone();
            modify(calendar2, i9, 0);
            return calendar2;
        }
        throw new IllegalArgumentException("The date must not be null");
    }

    public static boolean isSameDay(Calendar calendar, Calendar calendar2) {
        if (calendar == null || calendar2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        return calendar.get(0) == calendar2.get(0) && calendar.get(1) == calendar2.get(1) && calendar.get(6) == calendar2.get(6);
    }

    public static Date ceiling(Object obj, int i9) {
        if (obj != null) {
            if (obj instanceof Date) {
                return ceiling((Date) obj, i9);
            }
            if (obj instanceof Calendar) {
                return ceiling((Calendar) obj, i9).getTime();
            }
            throw new ClassCastException("Could not find ceiling of for type: " + obj.getClass());
        }
        throw new IllegalArgumentException("The date must not be null");
    }

    public static Date round(Object obj, int i9) {
        if (obj != null) {
            if (obj instanceof Date) {
                return round((Date) obj, i9);
            }
            if (obj instanceof Calendar) {
                return round((Calendar) obj, i9).getTime();
            }
            throw new ClassCastException("Could not round " + obj);
        }
        throw new IllegalArgumentException("The date must not be null");
    }

    public static Date truncate(Object obj, int i9) {
        if (obj != null) {
            if (obj instanceof Date) {
                return truncate((Date) obj, i9);
            }
            if (obj instanceof Calendar) {
                return truncate((Calendar) obj, i9).getTime();
            }
            throw new ClassCastException("Could not truncate " + obj);
        }
        throw new IllegalArgumentException("The date must not be null");
    }

    public static Iterator<?> iterator(Object obj, int i9) {
        if (obj != null) {
            if (obj instanceof Date) {
                return iterator((Date) obj, i9);
            }
            if (obj instanceof Calendar) {
                return iterator((Calendar) obj, i9);
            }
            throw new ClassCastException("Could not iterate based on " + obj);
        }
        throw new IllegalArgumentException("The date must not be null");
    }
}
