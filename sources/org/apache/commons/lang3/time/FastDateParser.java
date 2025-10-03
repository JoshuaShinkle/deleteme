package org.apache.commons.lang3.time;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.SortedMap;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class FastDateParser implements DateParser, Serializable {
    private static final long serialVersionUID = 2;
    private final int century;
    private transient String currentFormatField;
    private final Locale locale;
    private transient Strategy nextStrategy;
    private transient Pattern parsePattern;
    private final String pattern;
    private final int startYear;
    private transient Strategy[] strategies;
    private final TimeZone timeZone;
    static final Locale JAPANESE_IMPERIAL = new Locale("ja", "JP", "JP");
    private static final Pattern formatPattern = Pattern.compile("D+|E+|F+|G+|H+|K+|M+|S+|W+|Z+|a+|d+|h+|k+|m+|s+|w+|y+|z+|''|'[^']++(''[^']*+)*+'|[^'A-Za-z]++");
    private static final ConcurrentMap<Locale, Strategy>[] caches = new ConcurrentMap[17];
    private static final Strategy ABBREVIATED_YEAR_STRATEGY = new NumberStrategy(1) { // from class: org.apache.commons.lang3.time.FastDateParser.1
        @Override // org.apache.commons.lang3.time.FastDateParser.NumberStrategy, org.apache.commons.lang3.time.FastDateParser.Strategy
        public void setCalendar(FastDateParser fastDateParser, Calendar calendar, String str) throws NumberFormatException {
            int iAdjustYear = Integer.parseInt(str);
            if (iAdjustYear < 100) {
                iAdjustYear = fastDateParser.adjustYear(iAdjustYear);
            }
            calendar.set(1, iAdjustYear);
        }
    };
    private static final Strategy NUMBER_MONTH_STRATEGY = new NumberStrategy(2) { // from class: org.apache.commons.lang3.time.FastDateParser.2
        @Override // org.apache.commons.lang3.time.FastDateParser.NumberStrategy
        public int modify(int i9) {
            return i9 - 1;
        }
    };
    private static final Strategy LITERAL_YEAR_STRATEGY = new NumberStrategy(1);
    private static final Strategy WEEK_OF_YEAR_STRATEGY = new NumberStrategy(3);
    private static final Strategy WEEK_OF_MONTH_STRATEGY = new NumberStrategy(4);
    private static final Strategy DAY_OF_YEAR_STRATEGY = new NumberStrategy(6);
    private static final Strategy DAY_OF_MONTH_STRATEGY = new NumberStrategy(5);
    private static final Strategy DAY_OF_WEEK_IN_MONTH_STRATEGY = new NumberStrategy(8);
    private static final Strategy HOUR_OF_DAY_STRATEGY = new NumberStrategy(11);
    private static final Strategy MODULO_HOUR_OF_DAY_STRATEGY = new NumberStrategy(11) { // from class: org.apache.commons.lang3.time.FastDateParser.3
        @Override // org.apache.commons.lang3.time.FastDateParser.NumberStrategy
        public int modify(int i9) {
            return i9 % 24;
        }
    };
    private static final Strategy MODULO_HOUR_STRATEGY = new NumberStrategy(10) { // from class: org.apache.commons.lang3.time.FastDateParser.4
        @Override // org.apache.commons.lang3.time.FastDateParser.NumberStrategy
        public int modify(int i9) {
            return i9 % 12;
        }
    };
    private static final Strategy HOUR_STRATEGY = new NumberStrategy(10);
    private static final Strategy MINUTE_STRATEGY = new NumberStrategy(12);
    private static final Strategy SECOND_STRATEGY = new NumberStrategy(13);
    private static final Strategy MILLISECOND_STRATEGY = new NumberStrategy(14);

    public static class CopyQuotedStrategy extends Strategy {
        private final String formatField;

        public CopyQuotedStrategy(String str) {
            super();
            this.formatField = str;
        }

        @Override // org.apache.commons.lang3.time.FastDateParser.Strategy
        public boolean addRegex(FastDateParser fastDateParser, StringBuilder sb) {
            FastDateParser.escapeRegex(sb, this.formatField, true);
            return false;
        }

        @Override // org.apache.commons.lang3.time.FastDateParser.Strategy
        public boolean isNumber() {
            char cCharAt = this.formatField.charAt(0);
            if (cCharAt == '\'') {
                cCharAt = this.formatField.charAt(1);
            }
            return Character.isDigit(cCharAt);
        }
    }

    public static class NumberStrategy extends Strategy {
        private final int field;

        public NumberStrategy(int i9) {
            super();
            this.field = i9;
        }

        @Override // org.apache.commons.lang3.time.FastDateParser.Strategy
        public boolean addRegex(FastDateParser fastDateParser, StringBuilder sb) {
            if (!fastDateParser.isNextNumber()) {
                sb.append("(\\p{Nd}++)");
                return true;
            }
            sb.append("(\\p{Nd}{");
            sb.append(fastDateParser.getFieldWidth());
            sb.append("}+)");
            return true;
        }

        @Override // org.apache.commons.lang3.time.FastDateParser.Strategy
        public boolean isNumber() {
            return true;
        }

        public int modify(int i9) {
            return i9;
        }

        @Override // org.apache.commons.lang3.time.FastDateParser.Strategy
        public void setCalendar(FastDateParser fastDateParser, Calendar calendar, String str) {
            calendar.set(this.field, modify(Integer.parseInt(str)));
        }
    }

    public static abstract class Strategy {
        private Strategy() {
        }

        public abstract boolean addRegex(FastDateParser fastDateParser, StringBuilder sb);

        public boolean isNumber() {
            return false;
        }

        public void setCalendar(FastDateParser fastDateParser, Calendar calendar, String str) {
        }
    }

    public static class TextStrategy extends Strategy {
        private final int field;
        private final Map<String, Integer> keyValues;

        public TextStrategy(int i9, Calendar calendar, Locale locale) {
            super();
            this.field = i9;
            this.keyValues = FastDateParser.getDisplayNames(i9, calendar, locale);
        }

        @Override // org.apache.commons.lang3.time.FastDateParser.Strategy
        public boolean addRegex(FastDateParser fastDateParser, StringBuilder sb) {
            sb.append('(');
            Iterator<String> it = this.keyValues.keySet().iterator();
            while (it.hasNext()) {
                FastDateParser.escapeRegex(sb, it.next(), false).append('|');
            }
            sb.setCharAt(sb.length() - 1, ')');
            return true;
        }

        @Override // org.apache.commons.lang3.time.FastDateParser.Strategy
        public void setCalendar(FastDateParser fastDateParser, Calendar calendar, String str) {
            Integer num = this.keyValues.get(str);
            if (num != null) {
                calendar.set(this.field, num.intValue());
                return;
            }
            StringBuilder sb = new StringBuilder(str);
            sb.append(" not in (");
            Iterator<String> it = this.keyValues.keySet().iterator();
            while (it.hasNext()) {
                sb.append(it.next());
                sb.append(' ');
            }
            sb.setCharAt(sb.length() - 1, ')');
            throw new IllegalArgumentException(sb.toString());
        }
    }

    public static class TimeZoneStrategy extends Strategy {

        /* renamed from: ID */
        private static final int f19119ID = 0;
        private static final int LONG_DST = 3;
        private static final int LONG_STD = 1;
        private static final int SHORT_DST = 4;
        private static final int SHORT_STD = 2;
        private final SortedMap<String, TimeZone> tzNames;
        private final String validTimeZoneChars;

        public TimeZoneStrategy(Locale locale) {
            super();
            this.tzNames = new TreeMap(String.CASE_INSENSITIVE_ORDER);
            for (String[] strArr : DateFormatSymbols.getInstance(locale).getZoneStrings()) {
                if (!strArr[0].startsWith("GMT")) {
                    TimeZone timeZone = TimeZone.getTimeZone(strArr[0]);
                    if (!this.tzNames.containsKey(strArr[1])) {
                        this.tzNames.put(strArr[1], timeZone);
                    }
                    if (!this.tzNames.containsKey(strArr[2])) {
                        this.tzNames.put(strArr[2], timeZone);
                    }
                    if (timeZone.useDaylightTime()) {
                        if (!this.tzNames.containsKey(strArr[3])) {
                            this.tzNames.put(strArr[3], timeZone);
                        }
                        if (!this.tzNames.containsKey(strArr[4])) {
                            this.tzNames.put(strArr[4], timeZone);
                        }
                    }
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append("(GMT[+\\-]\\d{0,1}\\d{2}|[+\\-]\\d{2}:?\\d{2}|");
            Iterator<String> it = this.tzNames.keySet().iterator();
            while (it.hasNext()) {
                FastDateParser.escapeRegex(sb, it.next(), false).append('|');
            }
            sb.setCharAt(sb.length() - 1, ')');
            this.validTimeZoneChars = sb.toString();
        }

        @Override // org.apache.commons.lang3.time.FastDateParser.Strategy
        public boolean addRegex(FastDateParser fastDateParser, StringBuilder sb) {
            sb.append(this.validTimeZoneChars);
            return true;
        }

        @Override // org.apache.commons.lang3.time.FastDateParser.Strategy
        public void setCalendar(FastDateParser fastDateParser, Calendar calendar, String str) {
            TimeZone timeZone;
            if (str.charAt(0) == '+' || str.charAt(0) == '-') {
                timeZone = TimeZone.getTimeZone("GMT" + str);
            } else if (str.startsWith("GMT")) {
                timeZone = TimeZone.getTimeZone(str);
            } else {
                timeZone = this.tzNames.get(str);
                if (timeZone == null) {
                    throw new IllegalArgumentException(str + " is not a supported timezone name");
                }
            }
            calendar.setTimeZone(timeZone);
        }
    }

    public FastDateParser(String str, TimeZone timeZone, Locale locale) {
        this(str, timeZone, locale, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int adjustYear(int i9) {
        int i10 = this.century + i9;
        return i9 >= this.startYear ? i10 : i10 + 100;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static StringBuilder escapeRegex(StringBuilder sb, String str, boolean z8) {
        sb.append("\\Q");
        int i9 = 0;
        while (i9 < str.length()) {
            char cCharAt = str.charAt(i9);
            if (cCharAt != '\'') {
                if (cCharAt == '\\' && (i9 = i9 + 1) != str.length()) {
                    sb.append(cCharAt);
                    cCharAt = str.charAt(i9);
                    if (cCharAt == 'E') {
                        sb.append("E\\\\E\\");
                        cCharAt = 'Q';
                    }
                }
            } else if (z8) {
                i9++;
                if (i9 == str.length()) {
                    return sb;
                }
                cCharAt = str.charAt(i9);
            } else {
                continue;
            }
            sb.append(cCharAt);
            i9++;
        }
        sb.append("\\E");
        return sb;
    }

    private static ConcurrentMap<Locale, Strategy> getCache(int i9) {
        ConcurrentMap<Locale, Strategy> concurrentMap;
        ConcurrentMap<Locale, Strategy>[] concurrentMapArr = caches;
        synchronized (concurrentMapArr) {
            if (concurrentMapArr[i9] == null) {
                concurrentMapArr[i9] = new ConcurrentHashMap(3);
            }
            concurrentMap = concurrentMapArr[i9];
        }
        return concurrentMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Map<String, Integer> getDisplayNames(int i9, Calendar calendar, Locale locale) {
        return calendar.getDisplayNames(i9, 0, locale);
    }

    private Strategy getLocaleSpecificStrategy(int i9, Calendar calendar) {
        ConcurrentMap<Locale, Strategy> cache = getCache(i9);
        Strategy timeZoneStrategy = cache.get(this.locale);
        if (timeZoneStrategy == null) {
            timeZoneStrategy = i9 == 15 ? new TimeZoneStrategy(this.locale) : new TextStrategy(i9, calendar, this.locale);
            Strategy strategyPutIfAbsent = cache.putIfAbsent(this.locale, timeZoneStrategy);
            if (strategyPutIfAbsent != null) {
                return strategyPutIfAbsent;
            }
        }
        return timeZoneStrategy;
    }

    private Strategy getStrategy(String str, Calendar calendar) {
        char cCharAt = str.charAt(0);
        if (cCharAt == 'y') {
            return str.length() > 2 ? LITERAL_YEAR_STRATEGY : ABBREVIATED_YEAR_STRATEGY;
        }
        if (cCharAt != 'z') {
            switch (cCharAt) {
                case '\'':
                    if (str.length() > 2) {
                        return new CopyQuotedStrategy(str.substring(1, str.length() - 1));
                    }
                    break;
                case 'K':
                    return HOUR_STRATEGY;
                case 'M':
                    return str.length() >= 3 ? getLocaleSpecificStrategy(2, calendar) : NUMBER_MONTH_STRATEGY;
                case 'S':
                    return MILLISECOND_STRATEGY;
                case 'W':
                    return WEEK_OF_MONTH_STRATEGY;
                case 'Z':
                    break;
                case 'a':
                    return getLocaleSpecificStrategy(9, calendar);
                case 'd':
                    return DAY_OF_MONTH_STRATEGY;
                case 'h':
                    return MODULO_HOUR_STRATEGY;
                case 'k':
                    return HOUR_OF_DAY_STRATEGY;
                case 'm':
                    return MINUTE_STRATEGY;
                case 's':
                    return SECOND_STRATEGY;
                case 'w':
                    return WEEK_OF_YEAR_STRATEGY;
                default:
                    switch (cCharAt) {
                        case 'D':
                            return DAY_OF_YEAR_STRATEGY;
                        case 'E':
                            return getLocaleSpecificStrategy(7, calendar);
                        case 'F':
                            return DAY_OF_WEEK_IN_MONTH_STRATEGY;
                        case 'G':
                            return getLocaleSpecificStrategy(0, calendar);
                        case 'H':
                            return MODULO_HOUR_OF_DAY_STRATEGY;
                    }
            }
            return new CopyQuotedStrategy(str);
        }
        return getLocaleSpecificStrategy(15, calendar);
    }

    private void init(Calendar calendar) {
        StringBuilder sb = new StringBuilder();
        ArrayList arrayList = new ArrayList();
        Matcher matcher = formatPattern.matcher(this.pattern);
        if (!matcher.lookingAt()) {
            throw new IllegalArgumentException("Illegal pattern character '" + this.pattern.charAt(matcher.regionStart()) + "'");
        }
        String strGroup = matcher.group();
        this.currentFormatField = strGroup;
        Strategy strategy = getStrategy(strGroup, calendar);
        while (true) {
            matcher.region(matcher.end(), matcher.regionEnd());
            if (!matcher.lookingAt()) {
                break;
            }
            String strGroup2 = matcher.group();
            this.nextStrategy = getStrategy(strGroup2, calendar);
            if (strategy.addRegex(this, sb)) {
                arrayList.add(strategy);
            }
            this.currentFormatField = strGroup2;
            strategy = this.nextStrategy;
        }
        this.nextStrategy = null;
        if (matcher.regionStart() == matcher.regionEnd()) {
            if (strategy.addRegex(this, sb)) {
                arrayList.add(strategy);
            }
            this.currentFormatField = null;
            this.strategies = (Strategy[]) arrayList.toArray(new Strategy[arrayList.size()]);
            this.parsePattern = Pattern.compile(sb.toString());
            return;
        }
        throw new IllegalArgumentException("Failed to parse \"" + this.pattern + "\" ; gave up at index " + matcher.regionStart());
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        init(Calendar.getInstance(this.timeZone, this.locale));
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof FastDateParser)) {
            return false;
        }
        FastDateParser fastDateParser = (FastDateParser) obj;
        return this.pattern.equals(fastDateParser.pattern) && this.timeZone.equals(fastDateParser.timeZone) && this.locale.equals(fastDateParser.locale);
    }

    public int getFieldWidth() {
        return this.currentFormatField.length();
    }

    @Override // org.apache.commons.lang3.time.DateParser, org.apache.commons.lang3.time.DatePrinter
    public Locale getLocale() {
        return this.locale;
    }

    public Pattern getParsePattern() {
        return this.parsePattern;
    }

    @Override // org.apache.commons.lang3.time.DateParser, org.apache.commons.lang3.time.DatePrinter
    public String getPattern() {
        return this.pattern;
    }

    @Override // org.apache.commons.lang3.time.DateParser, org.apache.commons.lang3.time.DatePrinter
    public TimeZone getTimeZone() {
        return this.timeZone;
    }

    public int hashCode() {
        return this.pattern.hashCode() + ((this.timeZone.hashCode() + (this.locale.hashCode() * 13)) * 13);
    }

    public boolean isNextNumber() {
        Strategy strategy = this.nextStrategy;
        return strategy != null && strategy.isNumber();
    }

    @Override // org.apache.commons.lang3.time.DateParser
    public Date parse(String str) throws ParseException {
        Date date = parse(str, new ParsePosition(0));
        if (date != null) {
            return date;
        }
        if (!this.locale.equals(JAPANESE_IMPERIAL)) {
            throw new ParseException("Unparseable date: \"" + str + "\" does not match " + this.parsePattern.pattern(), 0);
        }
        throw new ParseException("(The " + this.locale + " locale does not support dates before 1868 AD)\nUnparseable date: \"" + str + "\" does not match " + this.parsePattern.pattern(), 0);
    }

    @Override // org.apache.commons.lang3.time.DateParser
    public Object parseObject(String str) {
        return parse(str);
    }

    public String toString() {
        return "FastDateParser[" + this.pattern + "," + this.locale + "," + this.timeZone.getID() + "]";
    }

    public FastDateParser(String str, TimeZone timeZone, Locale locale, Date date) {
        int i9;
        this.pattern = str;
        this.timeZone = timeZone;
        this.locale = locale;
        Calendar calendar = Calendar.getInstance(timeZone, locale);
        if (date != null) {
            calendar.setTime(date);
            i9 = calendar.get(1);
        } else if (locale.equals(JAPANESE_IMPERIAL)) {
            i9 = 0;
        } else {
            calendar.setTime(new Date());
            i9 = calendar.get(1) - 80;
        }
        int i10 = (i9 / 100) * 100;
        this.century = i10;
        this.startYear = i9 - i10;
        init(calendar);
    }

    @Override // org.apache.commons.lang3.time.DateParser
    public Object parseObject(String str, ParsePosition parsePosition) {
        return parse(str, parsePosition);
    }

    @Override // org.apache.commons.lang3.time.DateParser
    public Date parse(String str, ParsePosition parsePosition) {
        int index = parsePosition.getIndex();
        Matcher matcher = this.parsePattern.matcher(str.substring(index));
        if (!matcher.lookingAt()) {
            return null;
        }
        Calendar calendar = Calendar.getInstance(this.timeZone, this.locale);
        calendar.clear();
        int i9 = 0;
        while (true) {
            Strategy[] strategyArr = this.strategies;
            if (i9 < strategyArr.length) {
                int i10 = i9 + 1;
                strategyArr[i9].setCalendar(this, calendar, matcher.group(i10));
                i9 = i10;
            } else {
                parsePosition.setIndex(index + matcher.end());
                return calendar.getTime();
            }
        }
    }
}
