package org.apache.commons.lang3.time;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.text.DateFormatSymbols;
import java.text.FieldPosition;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.apache.commons.lang3.Validate;

/* loaded from: classes.dex */
public class FastDatePrinter implements DatePrinter, Serializable {
    public static final int FULL = 0;
    public static final int LONG = 1;
    public static final int MEDIUM = 2;
    public static final int SHORT = 3;
    private static final ConcurrentMap<TimeZoneDisplayKey, String> cTimeZoneDisplayCache = new ConcurrentHashMap(7);
    private static final long serialVersionUID = 1;
    private final Locale mLocale;
    private transient int mMaxLengthEstimate;
    private final String mPattern;
    private transient Rule[] mRules;
    private final TimeZone mTimeZone;

    public static class CharacterLiteral implements Rule {
        private final char mValue;

        public CharacterLiteral(char c9) {
            this.mValue = c9;
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(StringBuffer stringBuffer, Calendar calendar) {
            stringBuffer.append(this.mValue);
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return 1;
        }
    }

    public interface NumberRule extends Rule {
        void appendTo(StringBuffer stringBuffer, int i9);
    }

    public static class PaddedNumberField implements NumberRule {
        private final int mField;
        private final int mSize;

        public PaddedNumberField(int i9, int i10) {
            if (i10 < 3) {
                throw new IllegalArgumentException();
            }
            this.mField = i9;
            this.mSize = i10;
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(StringBuffer stringBuffer, Calendar calendar) {
            appendTo(stringBuffer, calendar.get(this.mField));
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return 4;
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.NumberRule
        public final void appendTo(StringBuffer stringBuffer, int i9) {
            int length;
            if (i9 < 100) {
                int i10 = this.mSize;
                while (true) {
                    i10--;
                    if (i10 < 2) {
                        stringBuffer.append((char) ((i9 / 10) + 48));
                        stringBuffer.append((char) ((i9 % 10) + 48));
                        return;
                    }
                    stringBuffer.append('0');
                }
            } else {
                if (i9 < 1000) {
                    length = 3;
                } else {
                    Validate.isTrue(i9 > -1, "Negative values should not be possible", i9);
                    length = Integer.toString(i9).length();
                }
                int i11 = this.mSize;
                while (true) {
                    i11--;
                    if (i11 < length) {
                        stringBuffer.append(Integer.toString(i9));
                        return;
                    }
                    stringBuffer.append('0');
                }
            }
        }
    }

    public interface Rule {
        void appendTo(StringBuffer stringBuffer, Calendar calendar);

        int estimateLength();
    }

    public static class StringLiteral implements Rule {
        private final String mValue;

        public StringLiteral(String str) {
            this.mValue = str;
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(StringBuffer stringBuffer, Calendar calendar) {
            stringBuffer.append(this.mValue);
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return this.mValue.length();
        }
    }

    public static class TextField implements Rule {
        private final int mField;
        private final String[] mValues;

        public TextField(int i9, String[] strArr) {
            this.mField = i9;
            this.mValues = strArr;
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(StringBuffer stringBuffer, Calendar calendar) {
            stringBuffer.append(this.mValues[calendar.get(this.mField)]);
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            int length = this.mValues.length;
            int i9 = 0;
            while (true) {
                length--;
                if (length < 0) {
                    return i9;
                }
                int length2 = this.mValues[length].length();
                if (length2 > i9) {
                    i9 = length2;
                }
            }
        }
    }

    public static class TimeZoneDisplayKey {
        private final Locale mLocale;
        private final int mStyle;
        private final TimeZone mTimeZone;

        public TimeZoneDisplayKey(TimeZone timeZone, boolean z8, int i9, Locale locale) {
            this.mTimeZone = timeZone;
            if (z8) {
                this.mStyle = Integer.MIN_VALUE | i9;
            } else {
                this.mStyle = i9;
            }
            this.mLocale = locale;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof TimeZoneDisplayKey)) {
                return false;
            }
            TimeZoneDisplayKey timeZoneDisplayKey = (TimeZoneDisplayKey) obj;
            return this.mTimeZone.equals(timeZoneDisplayKey.mTimeZone) && this.mStyle == timeZoneDisplayKey.mStyle && this.mLocale.equals(timeZoneDisplayKey.mLocale);
        }

        public int hashCode() {
            return (((this.mStyle * 31) + this.mLocale.hashCode()) * 31) + this.mTimeZone.hashCode();
        }
    }

    public static class TimeZoneNameRule implements Rule {
        private final String mDaylight;
        private final Locale mLocale;
        private final String mStandard;
        private final int mStyle;

        public TimeZoneNameRule(TimeZone timeZone, Locale locale, int i9) {
            this.mLocale = locale;
            this.mStyle = i9;
            this.mStandard = FastDatePrinter.getTimeZoneDisplay(timeZone, false, i9, locale);
            this.mDaylight = FastDatePrinter.getTimeZoneDisplay(timeZone, true, i9, locale);
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(StringBuffer stringBuffer, Calendar calendar) {
            TimeZone timeZone = calendar.getTimeZone();
            if (!timeZone.useDaylightTime() || calendar.get(16) == 0) {
                stringBuffer.append(FastDatePrinter.getTimeZoneDisplay(timeZone, false, this.mStyle, this.mLocale));
            } else {
                stringBuffer.append(FastDatePrinter.getTimeZoneDisplay(timeZone, true, this.mStyle, this.mLocale));
            }
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return Math.max(this.mStandard.length(), this.mDaylight.length());
        }
    }

    public static class TimeZoneNumberRule implements Rule {
        static final TimeZoneNumberRule INSTANCE_COLON = new TimeZoneNumberRule(true);
        static final TimeZoneNumberRule INSTANCE_NO_COLON = new TimeZoneNumberRule(false);
        final boolean mColon;

        public TimeZoneNumberRule(boolean z8) {
            this.mColon = z8;
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(StringBuffer stringBuffer, Calendar calendar) {
            int i9 = calendar.get(15) + calendar.get(16);
            if (i9 < 0) {
                stringBuffer.append('-');
                i9 = -i9;
            } else {
                stringBuffer.append('+');
            }
            int i10 = i9 / 3600000;
            stringBuffer.append((char) ((i10 / 10) + 48));
            stringBuffer.append((char) ((i10 % 10) + 48));
            if (this.mColon) {
                stringBuffer.append(':');
            }
            int i11 = (i9 / 60000) - (i10 * 60);
            stringBuffer.append((char) ((i11 / 10) + 48));
            stringBuffer.append((char) ((i11 % 10) + 48));
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return 5;
        }
    }

    public static class TwoDigitMonthField implements NumberRule {
        static final TwoDigitMonthField INSTANCE = new TwoDigitMonthField();

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(StringBuffer stringBuffer, Calendar calendar) {
            appendTo(stringBuffer, calendar.get(2) + 1);
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return 2;
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.NumberRule
        public final void appendTo(StringBuffer stringBuffer, int i9) {
            stringBuffer.append((char) ((i9 / 10) + 48));
            stringBuffer.append((char) ((i9 % 10) + 48));
        }
    }

    public static class TwoDigitNumberField implements NumberRule {
        private final int mField;

        public TwoDigitNumberField(int i9) {
            this.mField = i9;
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(StringBuffer stringBuffer, Calendar calendar) {
            appendTo(stringBuffer, calendar.get(this.mField));
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return 2;
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.NumberRule
        public final void appendTo(StringBuffer stringBuffer, int i9) {
            if (i9 >= 100) {
                stringBuffer.append(Integer.toString(i9));
            } else {
                stringBuffer.append((char) ((i9 / 10) + 48));
                stringBuffer.append((char) ((i9 % 10) + 48));
            }
        }
    }

    public static class TwoDigitYearField implements NumberRule {
        static final TwoDigitYearField INSTANCE = new TwoDigitYearField();

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(StringBuffer stringBuffer, Calendar calendar) {
            appendTo(stringBuffer, calendar.get(1) % 100);
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return 2;
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.NumberRule
        public final void appendTo(StringBuffer stringBuffer, int i9) {
            stringBuffer.append((char) ((i9 / 10) + 48));
            stringBuffer.append((char) ((i9 % 10) + 48));
        }
    }

    public static class UnpaddedMonthField implements NumberRule {
        static final UnpaddedMonthField INSTANCE = new UnpaddedMonthField();

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(StringBuffer stringBuffer, Calendar calendar) {
            appendTo(stringBuffer, calendar.get(2) + 1);
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return 2;
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.NumberRule
        public final void appendTo(StringBuffer stringBuffer, int i9) {
            if (i9 < 10) {
                stringBuffer.append((char) (i9 + 48));
            } else {
                stringBuffer.append((char) ((i9 / 10) + 48));
                stringBuffer.append((char) ((i9 % 10) + 48));
            }
        }
    }

    public static class UnpaddedNumberField implements NumberRule {
        private final int mField;

        public UnpaddedNumberField(int i9) {
            this.mField = i9;
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(StringBuffer stringBuffer, Calendar calendar) {
            appendTo(stringBuffer, calendar.get(this.mField));
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return 4;
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.NumberRule
        public final void appendTo(StringBuffer stringBuffer, int i9) {
            if (i9 < 10) {
                stringBuffer.append((char) (i9 + 48));
            } else if (i9 >= 100) {
                stringBuffer.append(Integer.toString(i9));
            } else {
                stringBuffer.append((char) ((i9 / 10) + 48));
                stringBuffer.append((char) ((i9 % 10) + 48));
            }
        }
    }

    public FastDatePrinter(String str, TimeZone timeZone, Locale locale) {
        this.mPattern = str;
        this.mTimeZone = timeZone;
        this.mLocale = locale;
        init();
    }

    private String applyRulesToString(Calendar calendar) {
        return applyRules(calendar, new StringBuffer(this.mMaxLengthEstimate)).toString();
    }

    public static String getTimeZoneDisplay(TimeZone timeZone, boolean z8, int i9, Locale locale) {
        TimeZoneDisplayKey timeZoneDisplayKey = new TimeZoneDisplayKey(timeZone, z8, i9, locale);
        ConcurrentMap<TimeZoneDisplayKey, String> concurrentMap = cTimeZoneDisplayCache;
        String str = concurrentMap.get(timeZoneDisplayKey);
        if (str != null) {
            return str;
        }
        String displayName = timeZone.getDisplayName(z8, i9, locale);
        String strPutIfAbsent = concurrentMap.putIfAbsent(timeZoneDisplayKey, displayName);
        return strPutIfAbsent != null ? strPutIfAbsent : displayName;
    }

    private void init() {
        List<Rule> pattern = parsePattern();
        Rule[] ruleArr = (Rule[]) pattern.toArray(new Rule[pattern.size()]);
        this.mRules = ruleArr;
        int length = ruleArr.length;
        int iEstimateLength = 0;
        while (true) {
            length--;
            if (length < 0) {
                this.mMaxLengthEstimate = iEstimateLength;
                return;
            }
            iEstimateLength += this.mRules[length].estimateLength();
        }
    }

    private GregorianCalendar newCalendar() {
        return new GregorianCalendar(this.mTimeZone, this.mLocale);
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        init();
    }

    public StringBuffer applyRules(Calendar calendar, StringBuffer stringBuffer) {
        for (Rule rule : this.mRules) {
            rule.appendTo(stringBuffer, calendar);
        }
        return stringBuffer;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof FastDatePrinter)) {
            return false;
        }
        FastDatePrinter fastDatePrinter = (FastDatePrinter) obj;
        return this.mPattern.equals(fastDatePrinter.mPattern) && this.mTimeZone.equals(fastDatePrinter.mTimeZone) && this.mLocale.equals(fastDatePrinter.mLocale);
    }

    @Override // org.apache.commons.lang3.time.DatePrinter
    public StringBuffer format(Object obj, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        if (obj instanceof Date) {
            return format((Date) obj, stringBuffer);
        }
        if (obj instanceof Calendar) {
            return format((Calendar) obj, stringBuffer);
        }
        if (obj instanceof Long) {
            return format(((Long) obj).longValue(), stringBuffer);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Unknown class: ");
        sb.append(obj == null ? "<null>" : obj.getClass().getName());
        throw new IllegalArgumentException(sb.toString());
    }

    @Override // org.apache.commons.lang3.time.DatePrinter
    public Locale getLocale() {
        return this.mLocale;
    }

    public int getMaxLengthEstimate() {
        return this.mMaxLengthEstimate;
    }

    @Override // org.apache.commons.lang3.time.DatePrinter
    public String getPattern() {
        return this.mPattern;
    }

    @Override // org.apache.commons.lang3.time.DatePrinter
    public TimeZone getTimeZone() {
        return this.mTimeZone;
    }

    public int hashCode() {
        return this.mPattern.hashCode() + ((this.mTimeZone.hashCode() + (this.mLocale.hashCode() * 13)) * 13);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r9v13, types: [org.apache.commons.lang3.time.FastDatePrinter$StringLiteral] */
    /* JADX WARN: Type inference failed for: r9v14, types: [org.apache.commons.lang3.time.FastDatePrinter$CharacterLiteral] */
    /* JADX WARN: Type inference failed for: r9v20, types: [org.apache.commons.lang3.time.FastDatePrinter$TextField] */
    /* JADX WARN: Type inference failed for: r9v21, types: [org.apache.commons.lang3.time.FastDatePrinter$TextField] */
    /* JADX WARN: Type inference failed for: r9v26, types: [org.apache.commons.lang3.time.FastDatePrinter$TimeZoneNumberRule] */
    /* JADX WARN: Type inference failed for: r9v27, types: [org.apache.commons.lang3.time.FastDatePrinter$TimeZoneNumberRule] */
    /* JADX WARN: Type inference failed for: r9v28, types: [org.apache.commons.lang3.time.FastDatePrinter$TextField] */
    /* JADX WARN: Type inference failed for: r9v43, types: [org.apache.commons.lang3.time.FastDatePrinter$TextField] */
    /* JADX WARN: Type inference failed for: r9v46, types: [org.apache.commons.lang3.time.FastDatePrinter$TextField] */
    /* JADX WARN: Type inference failed for: r9v8, types: [org.apache.commons.lang3.time.FastDatePrinter$TimeZoneNameRule] */
    /* JADX WARN: Type inference failed for: r9v9, types: [org.apache.commons.lang3.time.FastDatePrinter$TimeZoneNameRule] */
    public List<Rule> parsePattern() {
        int i9;
        NumberRule numberRuleSelectNumberRule;
        TwoDigitYearField timeZoneNameRule;
        NumberRule characterLiteral;
        DateFormatSymbols dateFormatSymbols = new DateFormatSymbols(this.mLocale);
        ArrayList arrayList = new ArrayList();
        String[] eras = dateFormatSymbols.getEras();
        String[] months = dateFormatSymbols.getMonths();
        String[] shortMonths = dateFormatSymbols.getShortMonths();
        String[] weekdays = dateFormatSymbols.getWeekdays();
        String[] shortWeekdays = dateFormatSymbols.getShortWeekdays();
        String[] amPmStrings = dateFormatSymbols.getAmPmStrings();
        int length = this.mPattern.length();
        int[] iArr = new int[1];
        int i10 = 0;
        int i11 = 0;
        while (i11 < length) {
            iArr[i10] = i11;
            String token = parseToken(this.mPattern, iArr);
            int i12 = iArr[i10];
            int length2 = token.length();
            if (length2 == 0) {
                return arrayList;
            }
            char cCharAt = token.charAt(i10);
            if (cCharAt == 'y') {
                i9 = 0;
                if (length2 == 2) {
                    timeZoneNameRule = TwoDigitYearField.INSTANCE;
                    numberRuleSelectNumberRule = timeZoneNameRule;
                } else {
                    if (length2 < 4) {
                        length2 = 4;
                    }
                    numberRuleSelectNumberRule = selectNumberRule(1, length2);
                }
            } else if (cCharAt != 'z') {
                switch (cCharAt) {
                    case '\'':
                        String strSubstring = token.substring(1);
                        characterLiteral = strSubstring.length() == 1 ? new CharacterLiteral(strSubstring.charAt(0)) : new StringLiteral(strSubstring);
                        numberRuleSelectNumberRule = characterLiteral;
                        i9 = 0;
                        break;
                    case 'K':
                        characterLiteral = selectNumberRule(10, length2);
                        numberRuleSelectNumberRule = characterLiteral;
                        i9 = 0;
                        break;
                    case 'M':
                        characterLiteral = length2 >= 4 ? new TextField(2, months) : length2 == 3 ? new TextField(2, shortMonths) : length2 == 2 ? TwoDigitMonthField.INSTANCE : UnpaddedMonthField.INSTANCE;
                        numberRuleSelectNumberRule = characterLiteral;
                        i9 = 0;
                        break;
                    case 'S':
                        characterLiteral = selectNumberRule(14, length2);
                        numberRuleSelectNumberRule = characterLiteral;
                        i9 = 0;
                        break;
                    case 'W':
                        characterLiteral = selectNumberRule(4, length2);
                        numberRuleSelectNumberRule = characterLiteral;
                        i9 = 0;
                        break;
                    case 'Z':
                        characterLiteral = length2 == 1 ? TimeZoneNumberRule.INSTANCE_NO_COLON : TimeZoneNumberRule.INSTANCE_COLON;
                        numberRuleSelectNumberRule = characterLiteral;
                        i9 = 0;
                        break;
                    case 'a':
                        characterLiteral = new TextField(9, amPmStrings);
                        numberRuleSelectNumberRule = characterLiteral;
                        i9 = 0;
                        break;
                    case 'd':
                        characterLiteral = selectNumberRule(5, length2);
                        numberRuleSelectNumberRule = characterLiteral;
                        i9 = 0;
                        break;
                    case 'h':
                        characterLiteral = new TwelveHourField(selectNumberRule(10, length2));
                        numberRuleSelectNumberRule = characterLiteral;
                        i9 = 0;
                        break;
                    case 'k':
                        numberRuleSelectNumberRule = new TwentyFourHourField(selectNumberRule(11, length2));
                        i9 = 0;
                        break;
                    case 'm':
                        characterLiteral = selectNumberRule(12, length2);
                        numberRuleSelectNumberRule = characterLiteral;
                        i9 = 0;
                        break;
                    case 's':
                        characterLiteral = selectNumberRule(13, length2);
                        numberRuleSelectNumberRule = characterLiteral;
                        i9 = 0;
                        break;
                    case 'w':
                        characterLiteral = selectNumberRule(3, length2);
                        numberRuleSelectNumberRule = characterLiteral;
                        i9 = 0;
                        break;
                    default:
                        switch (cCharAt) {
                            case 'D':
                                characterLiteral = selectNumberRule(6, length2);
                                numberRuleSelectNumberRule = characterLiteral;
                                i9 = 0;
                                break;
                            case 'E':
                                characterLiteral = new TextField(7, length2 < 4 ? shortWeekdays : weekdays);
                                numberRuleSelectNumberRule = characterLiteral;
                                i9 = 0;
                                break;
                            case 'F':
                                characterLiteral = selectNumberRule(8, length2);
                                numberRuleSelectNumberRule = characterLiteral;
                                i9 = 0;
                                break;
                            case 'G':
                                i9 = 0;
                                timeZoneNameRule = new TextField(0, eras);
                                numberRuleSelectNumberRule = timeZoneNameRule;
                                break;
                            case 'H':
                                characterLiteral = selectNumberRule(11, length2);
                                numberRuleSelectNumberRule = characterLiteral;
                                i9 = 0;
                                break;
                            default:
                                throw new IllegalArgumentException("Illegal pattern component: " + token);
                        }
                }
            } else if (length2 >= 4) {
                numberRuleSelectNumberRule = new TimeZoneNameRule(this.mTimeZone, this.mLocale, 1);
                i9 = 0;
            } else {
                i9 = 0;
                timeZoneNameRule = new TimeZoneNameRule(this.mTimeZone, this.mLocale, 0);
                numberRuleSelectNumberRule = timeZoneNameRule;
            }
            arrayList.add(numberRuleSelectNumberRule);
            i11 = i12 + 1;
            i10 = i9;
        }
        return arrayList;
    }

    public String parseToken(String str, int[] iArr) {
        StringBuilder sb = new StringBuilder();
        int i9 = iArr[0];
        int length = str.length();
        char cCharAt = str.charAt(i9);
        if ((cCharAt < 'A' || cCharAt > 'Z') && (cCharAt < 'a' || cCharAt > 'z')) {
            sb.append('\'');
            boolean z8 = false;
            while (i9 < length) {
                char cCharAt2 = str.charAt(i9);
                if (cCharAt2 != '\'') {
                    if (!z8 && ((cCharAt2 >= 'A' && cCharAt2 <= 'Z') || (cCharAt2 >= 'a' && cCharAt2 <= 'z'))) {
                        i9--;
                        break;
                    }
                    sb.append(cCharAt2);
                } else {
                    int i10 = i9 + 1;
                    if (i10 >= length || str.charAt(i10) != '\'') {
                        z8 = !z8;
                    } else {
                        sb.append(cCharAt2);
                        i9 = i10;
                    }
                }
                i9++;
            }
        } else {
            sb.append(cCharAt);
            while (true) {
                int i11 = i9 + 1;
                if (i11 >= length || str.charAt(i11) != cCharAt) {
                    break;
                }
                sb.append(cCharAt);
                i9 = i11;
            }
        }
        iArr[0] = i9;
        return sb.toString();
    }

    public NumberRule selectNumberRule(int i9, int i10) {
        return i10 != 1 ? i10 != 2 ? new PaddedNumberField(i9, i10) : new TwoDigitNumberField(i9) : new UnpaddedNumberField(i9);
    }

    public String toString() {
        return "FastDatePrinter[" + this.mPattern + "," + this.mLocale + "," + this.mTimeZone.getID() + "]";
    }

    public static class TwelveHourField implements NumberRule {
        private final NumberRule mRule;

        public TwelveHourField(NumberRule numberRule) {
            this.mRule = numberRule;
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(StringBuffer stringBuffer, Calendar calendar) {
            int leastMaximum = calendar.get(10);
            if (leastMaximum == 0) {
                leastMaximum = calendar.getLeastMaximum(10) + 1;
            }
            this.mRule.appendTo(stringBuffer, leastMaximum);
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return this.mRule.estimateLength();
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.NumberRule
        public void appendTo(StringBuffer stringBuffer, int i9) {
            this.mRule.appendTo(stringBuffer, i9);
        }
    }

    public static class TwentyFourHourField implements NumberRule {
        private final NumberRule mRule;

        public TwentyFourHourField(NumberRule numberRule) {
            this.mRule = numberRule;
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(StringBuffer stringBuffer, Calendar calendar) {
            int maximum = calendar.get(11);
            if (maximum == 0) {
                maximum = calendar.getMaximum(11) + 1;
            }
            this.mRule.appendTo(stringBuffer, maximum);
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return this.mRule.estimateLength();
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.NumberRule
        public void appendTo(StringBuffer stringBuffer, int i9) {
            this.mRule.appendTo(stringBuffer, i9);
        }
    }

    @Override // org.apache.commons.lang3.time.DatePrinter
    public String format(long j9) {
        GregorianCalendar gregorianCalendarNewCalendar = newCalendar();
        gregorianCalendarNewCalendar.setTimeInMillis(j9);
        return applyRulesToString(gregorianCalendarNewCalendar);
    }

    @Override // org.apache.commons.lang3.time.DatePrinter
    public String format(Date date) {
        GregorianCalendar gregorianCalendarNewCalendar = newCalendar();
        gregorianCalendarNewCalendar.setTime(date);
        return applyRulesToString(gregorianCalendarNewCalendar);
    }

    @Override // org.apache.commons.lang3.time.DatePrinter
    public String format(Calendar calendar) {
        return format(calendar, new StringBuffer(this.mMaxLengthEstimate)).toString();
    }

    @Override // org.apache.commons.lang3.time.DatePrinter
    public StringBuffer format(long j9, StringBuffer stringBuffer) {
        return format(new Date(j9), stringBuffer);
    }

    @Override // org.apache.commons.lang3.time.DatePrinter
    public StringBuffer format(Date date, StringBuffer stringBuffer) {
        GregorianCalendar gregorianCalendarNewCalendar = newCalendar();
        gregorianCalendarNewCalendar.setTime(date);
        return applyRules(gregorianCalendarNewCalendar, stringBuffer);
    }

    @Override // org.apache.commons.lang3.time.DatePrinter
    public StringBuffer format(Calendar calendar, StringBuffer stringBuffer) {
        return applyRules(calendar, stringBuffer);
    }
}
