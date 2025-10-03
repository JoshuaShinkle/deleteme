package com.google.i18n.phonenumbers;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberMatcher;
import com.google.i18n.phonenumbers.Phonenumber;
import com.google.i18n.phonenumbers.nano.Phonemetadata;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.p159io.IOUtils;

/* loaded from: classes2.dex */
public class PhoneNumberUtil {
    private static final Map<Character, Character> ALL_PLUS_NUMBER_GROUPING_SYMBOLS;
    private static final Map<Character, Character> ALPHA_MAPPINGS;
    private static final Map<Character, Character> ALPHA_PHONE_MAPPINGS;
    private static final Pattern CAPTURING_DIGIT_PATTERN;
    private static final String CAPTURING_EXTN_DIGITS = "(\\p{Nd}{1,7})";
    private static final Pattern CC_PATTERN;
    private static final String COLOMBIA_MOBILE_TO_FIXED_LINE_PREFIX = "3";
    private static final String DEFAULT_EXTN_PREFIX = " ext. ";
    private static final Map<Character, Character> DIALLABLE_CHAR_MAPPINGS;
    private static final String DIGITS = "\\p{Nd}";
    private static final Pattern EXTN_PATTERN;
    static final String EXTN_PATTERNS_FOR_MATCHING;
    private static final String EXTN_PATTERNS_FOR_PARSING;
    private static final Pattern FG_PATTERN;
    private static final Pattern FIRST_GROUP_ONLY_PREFIX_PATTERN;
    private static final Pattern FIRST_GROUP_PATTERN;
    private static final Set<Integer> GEO_MOBILE_COUNTRIES;
    private static final int MAX_INPUT_STRING_LENGTH = 250;
    static final int MAX_LENGTH_COUNTRY_CODE = 3;
    static final int MAX_LENGTH_FOR_NSN = 17;
    private static final int MIN_LENGTH_FOR_NSN = 2;
    private static final Map<Integer, String> MOBILE_TOKEN_MAPPINGS;
    private static final int NANPA_COUNTRY_CODE = 1;
    static final Pattern NON_DIGITS_PATTERN;
    private static final Pattern NP_PATTERN;
    static final String PLUS_CHARS = "+＋";
    static final Pattern PLUS_CHARS_PATTERN;
    static final char PLUS_SIGN = '+';
    static final int REGEX_FLAGS = 66;
    public static final String REGION_CODE_FOR_NON_GEO_ENTITY = "001";
    private static final String RFC3966_EXTN_PREFIX = ";ext=";
    private static final String RFC3966_ISDN_SUBADDRESS = ";isub=";
    private static final String RFC3966_PHONE_CONTEXT = ";phone-context=";
    private static final String RFC3966_PREFIX = "tel:";
    private static final String SECOND_NUMBER_START = "[\\\\/] *x";
    static final Pattern SECOND_NUMBER_START_PATTERN;
    private static final Pattern SEPARATOR_PATTERN;
    private static final char STAR_SIGN = '*';
    private static final Pattern UNIQUE_INTERNATIONAL_PREFIX;
    private static final String UNKNOWN_REGION = "ZZ";
    private static final String UNWANTED_END_CHARS = "[[\\P{N}&&\\P{L}]&&[^#]]+$";
    static final Pattern UNWANTED_END_CHAR_PATTERN;
    private static final String VALID_ALPHA;
    private static final Pattern VALID_ALPHA_PHONE_PATTERN;
    private static final String VALID_PHONE_NUMBER;
    private static final Pattern VALID_PHONE_NUMBER_PATTERN;
    static final String VALID_PUNCTUATION = "-x‐-―−ー－-／  \u00ad\u200b\u2060\u3000()（）［］.\\[\\]/~⁓∼～";
    private static final String VALID_START_CHAR = "[+＋\\p{Nd}]";
    private static final Pattern VALID_START_CHAR_PATTERN;
    private static PhoneNumberUtil instance;
    private final Map<Integer, List<String>> countryCallingCodeToRegionCodeMap;
    private final MetadataSource metadataSource;
    static final MetadataLoader DEFAULT_METADATA_LOADER = new MetadataLoader() { // from class: com.google.i18n.phonenumbers.PhoneNumberUtil.1
        @Override // com.google.i18n.phonenumbers.MetadataLoader
        public InputStream loadMetadata(String str) {
            return PhoneNumberUtil.class.getResourceAsStream(str);
        }
    };
    private static final Logger logger = Logger.getLogger(PhoneNumberUtil.class.getName());
    private final Set<String> nanpaRegions = new HashSet(35);
    private final RegexCache regexCache = new RegexCache(100);
    private final Set<String> supportedRegions = new HashSet(320);
    private final Set<Integer> countryCodesForNonGeographicalRegion = new HashSet();

    /* renamed from: com.google.i18n.phonenumbers.PhoneNumberUtil$3 */
    public static /* synthetic */ class C43103 {

        /* renamed from: $SwitchMap$com$google$i18n$phonenumbers$PhoneNumberUtil$PhoneNumberFormat */
        static final /* synthetic */ int[] f15591xd187ceb9;

        /* renamed from: $SwitchMap$com$google$i18n$phonenumbers$PhoneNumberUtil$PhoneNumberType */
        static final /* synthetic */ int[] f15592xa7892e7c;

        /* renamed from: $SwitchMap$com$google$i18n$phonenumbers$Phonenumber$PhoneNumber$CountryCodeSource */
        static final /* synthetic */ int[] f15593x9de98e3a;

        static {
            int[] iArr = new int[PhoneNumberType.values().length];
            f15592xa7892e7c = iArr;
            try {
                iArr[PhoneNumberType.PREMIUM_RATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f15592xa7892e7c[PhoneNumberType.TOLL_FREE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f15592xa7892e7c[PhoneNumberType.MOBILE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f15592xa7892e7c[PhoneNumberType.FIXED_LINE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f15592xa7892e7c[PhoneNumberType.FIXED_LINE_OR_MOBILE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f15592xa7892e7c[PhoneNumberType.SHARED_COST.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f15592xa7892e7c[PhoneNumberType.VOIP.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f15592xa7892e7c[PhoneNumberType.PERSONAL_NUMBER.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f15592xa7892e7c[PhoneNumberType.PAGER.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f15592xa7892e7c[PhoneNumberType.UAN.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f15592xa7892e7c[PhoneNumberType.VOICEMAIL.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            int[] iArr2 = new int[PhoneNumberFormat.values().length];
            f15591xd187ceb9 = iArr2;
            try {
                iArr2[PhoneNumberFormat.E164.ordinal()] = 1;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f15591xd187ceb9[PhoneNumberFormat.INTERNATIONAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f15591xd187ceb9[PhoneNumberFormat.RFC3966.ordinal()] = 3;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f15591xd187ceb9[PhoneNumberFormat.NATIONAL.ordinal()] = 4;
            } catch (NoSuchFieldError unused15) {
            }
            int[] iArr3 = new int[Phonenumber.PhoneNumber.CountryCodeSource.values().length];
            f15593x9de98e3a = iArr3;
            try {
                iArr3[Phonenumber.PhoneNumber.CountryCodeSource.FROM_NUMBER_WITH_PLUS_SIGN.ordinal()] = 1;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                f15593x9de98e3a[Phonenumber.PhoneNumber.CountryCodeSource.FROM_NUMBER_WITH_IDD.ordinal()] = 2;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                f15593x9de98e3a[Phonenumber.PhoneNumber.CountryCodeSource.FROM_NUMBER_WITHOUT_PLUS_SIGN.ordinal()] = 3;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                f15593x9de98e3a[Phonenumber.PhoneNumber.CountryCodeSource.FROM_DEFAULT_COUNTRY.ordinal()] = 4;
            } catch (NoSuchFieldError unused19) {
            }
        }
    }

    public enum Leniency {
        POSSIBLE { // from class: com.google.i18n.phonenumbers.PhoneNumberUtil.Leniency.1
            @Override // com.google.i18n.phonenumbers.PhoneNumberUtil.Leniency
            public boolean verify(Phonenumber.PhoneNumber phoneNumber, String str, PhoneNumberUtil phoneNumberUtil) {
                return phoneNumberUtil.isPossibleNumber(phoneNumber);
            }
        },
        VALID { // from class: com.google.i18n.phonenumbers.PhoneNumberUtil.Leniency.2
            @Override // com.google.i18n.phonenumbers.PhoneNumberUtil.Leniency
            public boolean verify(Phonenumber.PhoneNumber phoneNumber, String str, PhoneNumberUtil phoneNumberUtil) {
                if (phoneNumberUtil.isValidNumber(phoneNumber) && PhoneNumberMatcher.containsOnlyValidXChars(phoneNumber, str, phoneNumberUtil)) {
                    return PhoneNumberMatcher.isNationalPrefixPresentIfRequired(phoneNumber, phoneNumberUtil);
                }
                return false;
            }
        },
        STRICT_GROUPING { // from class: com.google.i18n.phonenumbers.PhoneNumberUtil.Leniency.3
            @Override // com.google.i18n.phonenumbers.PhoneNumberUtil.Leniency
            public boolean verify(Phonenumber.PhoneNumber phoneNumber, String str, PhoneNumberUtil phoneNumberUtil) {
                if (phoneNumberUtil.isValidNumber(phoneNumber) && PhoneNumberMatcher.containsOnlyValidXChars(phoneNumber, str, phoneNumberUtil) && !PhoneNumberMatcher.containsMoreThanOneSlashInNationalNumber(phoneNumber, str) && PhoneNumberMatcher.isNationalPrefixPresentIfRequired(phoneNumber, phoneNumberUtil)) {
                    return PhoneNumberMatcher.checkNumberGroupingIsValid(phoneNumber, str, phoneNumberUtil, new PhoneNumberMatcher.NumberGroupingChecker() { // from class: com.google.i18n.phonenumbers.PhoneNumberUtil.Leniency.3.1
                        @Override // com.google.i18n.phonenumbers.PhoneNumberMatcher.NumberGroupingChecker
                        public boolean checkGroups(PhoneNumberUtil phoneNumberUtil2, Phonenumber.PhoneNumber phoneNumber2, StringBuilder sb, String[] strArr) {
                            return PhoneNumberMatcher.allNumberGroupsRemainGrouped(phoneNumberUtil2, phoneNumber2, sb, strArr);
                        }
                    });
                }
                return false;
            }
        },
        EXACT_GROUPING { // from class: com.google.i18n.phonenumbers.PhoneNumberUtil.Leniency.4
            @Override // com.google.i18n.phonenumbers.PhoneNumberUtil.Leniency
            public boolean verify(Phonenumber.PhoneNumber phoneNumber, String str, PhoneNumberUtil phoneNumberUtil) {
                if (phoneNumberUtil.isValidNumber(phoneNumber) && PhoneNumberMatcher.containsOnlyValidXChars(phoneNumber, str, phoneNumberUtil) && !PhoneNumberMatcher.containsMoreThanOneSlashInNationalNumber(phoneNumber, str) && PhoneNumberMatcher.isNationalPrefixPresentIfRequired(phoneNumber, phoneNumberUtil)) {
                    return PhoneNumberMatcher.checkNumberGroupingIsValid(phoneNumber, str, phoneNumberUtil, new PhoneNumberMatcher.NumberGroupingChecker() { // from class: com.google.i18n.phonenumbers.PhoneNumberUtil.Leniency.4.1
                        @Override // com.google.i18n.phonenumbers.PhoneNumberMatcher.NumberGroupingChecker
                        public boolean checkGroups(PhoneNumberUtil phoneNumberUtil2, Phonenumber.PhoneNumber phoneNumber2, StringBuilder sb, String[] strArr) {
                            return PhoneNumberMatcher.allNumberGroupsAreExactlyPresent(phoneNumberUtil2, phoneNumber2, sb, strArr);
                        }
                    });
                }
                return false;
            }
        };

        public abstract boolean verify(Phonenumber.PhoneNumber phoneNumber, String str, PhoneNumberUtil phoneNumberUtil);
    }

    public enum MatchType {
        NOT_A_NUMBER,
        NO_MATCH,
        SHORT_NSN_MATCH,
        NSN_MATCH,
        EXACT_MATCH
    }

    public enum PhoneNumberFormat {
        E164,
        INTERNATIONAL,
        NATIONAL,
        RFC3966
    }

    public enum PhoneNumberType {
        FIXED_LINE,
        MOBILE,
        FIXED_LINE_OR_MOBILE,
        TOLL_FREE,
        PREMIUM_RATE,
        SHARED_COST,
        VOIP,
        PERSONAL_NUMBER,
        PAGER,
        UAN,
        VOICEMAIL,
        UNKNOWN
    }

    public enum ValidationResult {
        IS_POSSIBLE,
        INVALID_COUNTRY_CODE,
        TOO_SHORT,
        TOO_LONG
    }

    static {
        HashMap map = new HashMap();
        map.put(52, "1");
        map.put(54, "9");
        MOBILE_TOKEN_MAPPINGS = Collections.unmodifiableMap(map);
        HashSet hashSet = new HashSet();
        hashSet.add(52);
        hashSet.add(54);
        hashSet.add(55);
        GEO_MOBILE_COUNTRIES = Collections.unmodifiableSet(hashSet);
        HashMap map2 = new HashMap();
        map2.put('0', '0');
        map2.put('1', '1');
        map2.put('2', '2');
        map2.put('3', '3');
        map2.put('4', '4');
        map2.put('5', '5');
        map2.put('6', '6');
        map2.put('7', '7');
        map2.put('8', '8');
        map2.put('9', '9');
        HashMap map3 = new HashMap(40);
        map3.put('A', '2');
        map3.put('B', '2');
        map3.put('C', '2');
        map3.put('D', '3');
        map3.put('E', '3');
        map3.put('F', '3');
        map3.put('G', '4');
        map3.put('H', '4');
        map3.put('I', '4');
        map3.put('J', '5');
        map3.put('K', '5');
        map3.put('L', '5');
        map3.put('M', '6');
        map3.put('N', '6');
        map3.put('O', '6');
        map3.put('P', '7');
        map3.put('Q', '7');
        map3.put('R', '7');
        map3.put('S', '7');
        map3.put('T', '8');
        map3.put('U', '8');
        map3.put('V', '8');
        map3.put('W', '9');
        map3.put('X', '9');
        map3.put('Y', '9');
        map3.put('Z', '9');
        Map<Character, Character> mapUnmodifiableMap = Collections.unmodifiableMap(map3);
        ALPHA_MAPPINGS = mapUnmodifiableMap;
        HashMap map4 = new HashMap(100);
        map4.putAll(mapUnmodifiableMap);
        map4.putAll(map2);
        ALPHA_PHONE_MAPPINGS = Collections.unmodifiableMap(map4);
        HashMap map5 = new HashMap();
        map5.putAll(map2);
        Character chValueOf = Character.valueOf(PLUS_SIGN);
        map5.put(chValueOf, chValueOf);
        Character chValueOf2 = Character.valueOf(STAR_SIGN);
        map5.put(chValueOf2, chValueOf2);
        DIALLABLE_CHAR_MAPPINGS = Collections.unmodifiableMap(map5);
        HashMap map6 = new HashMap();
        Iterator<Character> it = mapUnmodifiableMap.keySet().iterator();
        while (it.hasNext()) {
            char cCharValue = it.next().charValue();
            map6.put(Character.valueOf(Character.toLowerCase(cCharValue)), Character.valueOf(cCharValue));
            map6.put(Character.valueOf(cCharValue), Character.valueOf(cCharValue));
        }
        map6.putAll(map2);
        map6.put('-', '-');
        map6.put((char) 65293, '-');
        map6.put((char) 8208, '-');
        map6.put((char) 8209, '-');
        map6.put((char) 8210, '-');
        map6.put((char) 8211, '-');
        map6.put((char) 8212, '-');
        map6.put((char) 8213, '-');
        map6.put((char) 8722, '-');
        map6.put(Character.valueOf(IOUtils.DIR_SEPARATOR_UNIX), Character.valueOf(IOUtils.DIR_SEPARATOR_UNIX));
        map6.put((char) 65295, Character.valueOf(IOUtils.DIR_SEPARATOR_UNIX));
        map6.put(' ', ' ');
        map6.put((char) 12288, ' ');
        map6.put((char) 8288, ' ');
        map6.put(Character.valueOf(ClassUtils.PACKAGE_SEPARATOR_CHAR), Character.valueOf(ClassUtils.PACKAGE_SEPARATOR_CHAR));
        map6.put((char) 65294, Character.valueOf(ClassUtils.PACKAGE_SEPARATOR_CHAR));
        ALL_PLUS_NUMBER_GROUPING_SYMBOLS = Collections.unmodifiableMap(map6);
        UNIQUE_INTERNATIONAL_PREFIX = Pattern.compile("[\\d]+(?:[~⁓∼～][\\d]+)?");
        Map<Character, Character> map7 = ALPHA_MAPPINGS;
        String strValueOf = String.valueOf(Arrays.toString(map7.keySet().toArray()).replaceAll("[, \\[\\]]", ""));
        String strValueOf2 = String.valueOf(Arrays.toString(map7.keySet().toArray()).toLowerCase().replaceAll("[, \\[\\]]", ""));
        String strConcat = strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf);
        VALID_ALPHA = strConcat;
        PLUS_CHARS_PATTERN = Pattern.compile("[+＋]+");
        SEPARATOR_PATTERN = Pattern.compile("[-x‐-―−ー－-／  \u00ad\u200b\u2060\u3000()（）［］.\\[\\]/~⁓∼～]+");
        CAPTURING_DIGIT_PATTERN = Pattern.compile("(\\p{Nd})");
        VALID_START_CHAR_PATTERN = Pattern.compile(VALID_START_CHAR);
        SECOND_NUMBER_START_PATTERN = Pattern.compile(SECOND_NUMBER_START);
        UNWANTED_END_CHAR_PATTERN = Pattern.compile(UNWANTED_END_CHARS);
        VALID_ALPHA_PHONE_PATTERN = Pattern.compile("(?:.*?[A-Za-z]){3}.*");
        String strValueOf3 = String.valueOf(strConcat);
        StringBuilder sb = new StringBuilder("\\p{Nd}{2}|[+＋]*+(?:[-x‐-―−ー－-／  \u00ad\u200b\u2060\u3000()（）［］.\\[\\]/~⁓∼～*]*\\p{Nd}){3,}[-x‐-―−ー－-／  \u00ad\u200b\u2060\u3000()（）［］.\\[\\]/~⁓∼～*".length() + 2 + strValueOf3.length() + DIGITS.length());
        sb.append("\\p{Nd}{2}|[+＋]*+(?:[-x‐-―−ー－-／  \u00ad\u200b\u2060\u3000()（）［］.\\[\\]/~⁓∼～*]*\\p{Nd}){3,}[-x‐-―−ー－-／  \u00ad\u200b\u2060\u3000()（）［］.\\[\\]/~⁓∼～*");
        sb.append(strValueOf3);
        sb.append(DIGITS);
        sb.append("]*");
        String string = sb.toString();
        VALID_PHONE_NUMBER = string;
        String strCreateExtnPattern = createExtnPattern("xｘ#＃~～".length() != 0 ? ",".concat("xｘ#＃~～") : new String(","));
        EXTN_PATTERNS_FOR_PARSING = strCreateExtnPattern;
        EXTN_PATTERNS_FOR_MATCHING = createExtnPattern("xｘ#＃~～");
        String strValueOf4 = String.valueOf(strCreateExtnPattern);
        StringBuilder sb2 = new StringBuilder(strValueOf4.length() + 5);
        sb2.append("(?:");
        sb2.append(strValueOf4);
        sb2.append(")$");
        EXTN_PATTERN = Pattern.compile(sb2.toString(), 66);
        String strValueOf5 = String.valueOf(string);
        String strValueOf6 = String.valueOf(strCreateExtnPattern);
        StringBuilder sb3 = new StringBuilder(strValueOf5.length() + 5 + strValueOf6.length());
        sb3.append(strValueOf5);
        sb3.append("(?:");
        sb3.append(strValueOf6);
        sb3.append(")?");
        VALID_PHONE_NUMBER_PATTERN = Pattern.compile(sb3.toString(), 66);
        NON_DIGITS_PATTERN = Pattern.compile("(\\D+)");
        FIRST_GROUP_PATTERN = Pattern.compile("(\\$\\d)");
        NP_PATTERN = Pattern.compile("\\$NP");
        FG_PATTERN = Pattern.compile("\\$FG");
        CC_PATTERN = Pattern.compile("\\$CC");
        FIRST_GROUP_ONLY_PREFIX_PATTERN = Pattern.compile("\\(?\\$1\\)?");
        instance = null;
    }

    public PhoneNumberUtil(MetadataSource metadataSource, Map<Integer, List<String>> map) {
        this.metadataSource = metadataSource;
        this.countryCallingCodeToRegionCodeMap = map;
        for (Map.Entry<Integer, List<String>> entry : map.entrySet()) {
            List<String> value = entry.getValue();
            if (value.size() == 1 && REGION_CODE_FOR_NON_GEO_ENTITY.equals(value.get(0))) {
                this.countryCodesForNonGeographicalRegion.add(entry.getKey());
            } else {
                this.supportedRegions.addAll(value);
            }
        }
        if (this.supportedRegions.remove(REGION_CODE_FOR_NON_GEO_ENTITY)) {
            logger.log(Level.WARNING, "invalid metadata (country calling code was mapped to the non-geo entity as well as specific region(s))");
        }
        this.nanpaRegions.addAll(map.get(1));
    }

    private void buildNationalNumberForParsing(String str, StringBuilder sb) {
        int iIndexOf = str.indexOf(RFC3966_PHONE_CONTEXT);
        if (iIndexOf > 0) {
            int i9 = iIndexOf + 15;
            if (str.charAt(i9) == '+') {
                int iIndexOf2 = str.indexOf(59, i9);
                if (iIndexOf2 > 0) {
                    sb.append(str.substring(i9, iIndexOf2));
                } else {
                    sb.append(str.substring(i9));
                }
            }
            int iIndexOf3 = str.indexOf(RFC3966_PREFIX);
            sb.append(str.substring(iIndexOf3 >= 0 ? iIndexOf3 + 4 : 0, iIndexOf));
        } else {
            sb.append(extractPossibleNumber(str));
        }
        int iIndexOf4 = sb.indexOf(RFC3966_ISDN_SUBADDRESS);
        if (iIndexOf4 > 0) {
            sb.delete(iIndexOf4, sb.length());
        }
    }

    private boolean checkRegionForParsing(String str, String str2) {
        if (isValidRegionCode(str2)) {
            return true;
        }
        return (str == null || str.length() == 0 || !PLUS_CHARS_PATTERN.matcher(str).lookingAt()) ? false : true;
    }

    public static String convertAlphaCharactersInNumber(String str) {
        return normalizeHelper(str, ALPHA_PHONE_MAPPINGS, false);
    }

    public static Phonemetadata.NumberFormat copyNumberFormat(Phonemetadata.NumberFormat numberFormat) {
        Phonemetadata.NumberFormat numberFormat2 = new Phonemetadata.NumberFormat();
        numberFormat2.pattern = numberFormat.pattern;
        numberFormat2.format = numberFormat.format;
        int length = numberFormat.leadingDigitsPattern.length;
        numberFormat2.leadingDigitsPattern = new String[length];
        for (int i9 = 0; i9 < length; i9++) {
            numberFormat2.leadingDigitsPattern[i9] = numberFormat.leadingDigitsPattern[i9];
        }
        numberFormat2.nationalPrefixFormattingRule = numberFormat.nationalPrefixFormattingRule;
        numberFormat2.domesticCarrierCodeFormattingRule = numberFormat.domesticCarrierCodeFormattingRule;
        numberFormat2.nationalPrefixOptionalWhenFormatting = numberFormat.nationalPrefixOptionalWhenFormatting;
        return numberFormat2;
    }

    private static String createExtnPattern(String str) {
        String strValueOf = String.valueOf(str);
        StringBuilder sb = new StringBuilder(";ext=(\\p{Nd}{1,7})|[  \\t,]*(?:e?xt(?:ensi(?:ó?|ó))?n?|ｅ?ｘｔｎ?|[".length() + 48 + strValueOf.length() + CAPTURING_EXTN_DIGITS.length() + DIGITS.length());
        sb.append(";ext=(\\p{Nd}{1,7})|[  \\t,]*(?:e?xt(?:ensi(?:ó?|ó))?n?|ｅ?ｘｔｎ?|[");
        sb.append(strValueOf);
        sb.append("]|int|anexo|ｉｎｔ)");
        sb.append("[:\\.．]?[  \\t,-]*");
        sb.append(CAPTURING_EXTN_DIGITS);
        sb.append("#?|");
        sb.append("[- ]+(");
        sb.append(DIGITS);
        sb.append("{1,5})#");
        return sb.toString();
    }

    public static PhoneNumberUtil createInstance(MetadataSource metadataSource) {
        if (metadataSource != null) {
            return new PhoneNumberUtil(metadataSource, CountryCodeToRegionCodeMap.getCountryCodeToRegionCodeMap());
        }
        throw new IllegalArgumentException("metadataSource could not be null.");
    }

    public static String extractPossibleNumber(String str) {
        Matcher matcher = VALID_START_CHAR_PATTERN.matcher(str);
        if (!matcher.find()) {
            return "";
        }
        String strSubstring = str.substring(matcher.start());
        Matcher matcher2 = UNWANTED_END_CHAR_PATTERN.matcher(strSubstring);
        if (matcher2.find()) {
            strSubstring = strSubstring.substring(0, matcher2.start());
            Logger logger2 = logger;
            Level level = Level.FINER;
            String strValueOf = String.valueOf(strSubstring);
            logger2.log(level, strValueOf.length() != 0 ? "Stripped trailing characters: ".concat(strValueOf) : new String("Stripped trailing characters: "));
        }
        Matcher matcher3 = SECOND_NUMBER_START_PATTERN.matcher(strSubstring);
        return matcher3.find() ? strSubstring.substring(0, matcher3.start()) : strSubstring;
    }

    private String formatNsn(String str, Phonemetadata.PhoneMetadata phoneMetadata, PhoneNumberFormat phoneNumberFormat) {
        return formatNsn(str, phoneMetadata, phoneNumberFormat, null);
    }

    public static boolean formattingRuleHasFirstGroupOnly(String str) {
        return str.length() == 0 || FIRST_GROUP_ONLY_PREFIX_PATTERN.matcher(str).matches();
    }

    private int getCountryCodeForValidRegion(String str) {
        Phonemetadata.PhoneMetadata metadataForRegion = getMetadataForRegion(str);
        if (metadataForRegion != null) {
            return metadataForRegion.countryCode;
        }
        String strValueOf = String.valueOf(str);
        throw new IllegalArgumentException(strValueOf.length() != 0 ? "Invalid region code: ".concat(strValueOf) : new String("Invalid region code: "));
    }

    public static String getCountryMobileToken(int i9) {
        Map<Integer, String> map = MOBILE_TOKEN_MAPPINGS;
        return map.containsKey(Integer.valueOf(i9)) ? map.get(Integer.valueOf(i9)) : "";
    }

    public static synchronized PhoneNumberUtil getInstance() {
        if (instance == null) {
            setInstance(createInstance(DEFAULT_METADATA_LOADER));
        }
        return instance;
    }

    private Phonemetadata.PhoneMetadata getMetadataForRegionOrCallingCode(int i9, String str) {
        return REGION_CODE_FOR_NON_GEO_ENTITY.equals(str) ? getMetadataForNonGeographicalRegion(i9) : getMetadataForRegion(str);
    }

    private PhoneNumberType getNumberTypeHelper(String str, Phonemetadata.PhoneMetadata phoneMetadata) {
        if (!isNumberMatchingDesc(str, phoneMetadata.generalDesc)) {
            return PhoneNumberType.UNKNOWN;
        }
        if (isNumberMatchingDesc(str, phoneMetadata.premiumRate)) {
            return PhoneNumberType.PREMIUM_RATE;
        }
        if (isNumberMatchingDesc(str, phoneMetadata.tollFree)) {
            return PhoneNumberType.TOLL_FREE;
        }
        if (isNumberMatchingDesc(str, phoneMetadata.sharedCost)) {
            return PhoneNumberType.SHARED_COST;
        }
        if (isNumberMatchingDesc(str, phoneMetadata.voip)) {
            return PhoneNumberType.VOIP;
        }
        if (isNumberMatchingDesc(str, phoneMetadata.personalNumber)) {
            return PhoneNumberType.PERSONAL_NUMBER;
        }
        if (isNumberMatchingDesc(str, phoneMetadata.pager)) {
            return PhoneNumberType.PAGER;
        }
        if (isNumberMatchingDesc(str, phoneMetadata.uan)) {
            return PhoneNumberType.UAN;
        }
        if (isNumberMatchingDesc(str, phoneMetadata.voicemail)) {
            return PhoneNumberType.VOICEMAIL;
        }
        if (!isNumberMatchingDesc(str, phoneMetadata.fixedLine)) {
            return (phoneMetadata.sameMobileAndFixedLinePattern || !isNumberMatchingDesc(str, phoneMetadata.mobile)) ? PhoneNumberType.UNKNOWN : PhoneNumberType.MOBILE;
        }
        if (!phoneMetadata.sameMobileAndFixedLinePattern && !isNumberMatchingDesc(str, phoneMetadata.mobile)) {
            return PhoneNumberType.FIXED_LINE;
        }
        return PhoneNumberType.FIXED_LINE_OR_MOBILE;
    }

    private String getRegionCodeForNumberFromRegionList(Phonenumber.PhoneNumber phoneNumber, List<String> list) {
        String nationalSignificantNumber = getNationalSignificantNumber(phoneNumber);
        for (String str : list) {
            Phonemetadata.PhoneMetadata metadataForRegion = getMetadataForRegion(str);
            if (metadataForRegion.leadingDigits.equals("")) {
                if (getNumberTypeHelper(nationalSignificantNumber, metadataForRegion) != PhoneNumberType.UNKNOWN) {
                    return str;
                }
            } else if (this.regexCache.getPatternForRegex(metadataForRegion.leadingDigits).matcher(nationalSignificantNumber).lookingAt()) {
                return str;
            }
        }
        return null;
    }

    private boolean hasFormattingPatternForNumber(Phonenumber.PhoneNumber phoneNumber) {
        int countryCode = phoneNumber.getCountryCode();
        Phonemetadata.PhoneMetadata metadataForRegionOrCallingCode = getMetadataForRegionOrCallingCode(countryCode, getRegionCodeForCountryCode(countryCode));
        if (metadataForRegionOrCallingCode == null) {
            return false;
        }
        return chooseFormattingPatternForNumber(metadataForRegionOrCallingCode.numberFormat, getNationalSignificantNumber(phoneNumber)) != null;
    }

    private boolean hasUnexpectedItalianLeadingZero(Phonenumber.PhoneNumber phoneNumber) {
        return phoneNumber.isItalianLeadingZero() && !isLeadingZeroPossible(phoneNumber.getCountryCode());
    }

    private boolean hasValidCountryCallingCode(int i9) {
        return this.countryCallingCodeToRegionCodeMap.containsKey(Integer.valueOf(i9));
    }

    private boolean isNationalNumberSuffixOfTheOther(Phonenumber.PhoneNumber phoneNumber, Phonenumber.PhoneNumber phoneNumber2) {
        String strValueOf = String.valueOf(phoneNumber.getNationalNumber());
        String strValueOf2 = String.valueOf(phoneNumber2.getNationalNumber());
        return strValueOf.endsWith(strValueOf2) || strValueOf2.endsWith(strValueOf);
    }

    private boolean isShorterThanPossibleNormalNumber(Phonemetadata.PhoneMetadata phoneMetadata, String str) {
        return testNumberLengthAgainstPattern(this.regexCache.getPatternForRegex(phoneMetadata.generalDesc.possibleNumberPattern), str) == ValidationResult.TOO_SHORT;
    }

    private boolean isValidRegionCode(String str) {
        return str != null && this.supportedRegions.contains(str);
    }

    public static boolean isViablePhoneNumber(String str) {
        if (str.length() < 2) {
            return false;
        }
        return VALID_PHONE_NUMBER_PATTERN.matcher(str).matches();
    }

    private void maybeAppendFormattedExtension(Phonenumber.PhoneNumber phoneNumber, Phonemetadata.PhoneMetadata phoneMetadata, PhoneNumberFormat phoneNumberFormat, StringBuilder sb) {
        if (!phoneNumber.hasExtension() || phoneNumber.getExtension().length() <= 0) {
            return;
        }
        if (phoneNumberFormat == PhoneNumberFormat.RFC3966) {
            sb.append(RFC3966_EXTN_PREFIX);
            sb.append(phoneNumber.getExtension());
        } else if (phoneMetadata.preferredExtnPrefix.equals("")) {
            sb.append(DEFAULT_EXTN_PREFIX);
            sb.append(phoneNumber.getExtension());
        } else {
            sb.append(phoneMetadata.preferredExtnPrefix);
            sb.append(phoneNumber.getExtension());
        }
    }

    public static String normalize(String str) {
        return VALID_ALPHA_PHONE_PATTERN.matcher(str).matches() ? normalizeHelper(str, ALPHA_PHONE_MAPPINGS, true) : normalizeDigitsOnly(str);
    }

    public static String normalizeDiallableCharsOnly(String str) {
        return normalizeHelper(str, DIALLABLE_CHAR_MAPPINGS, true);
    }

    public static StringBuilder normalizeDigits(String str, boolean z8) {
        StringBuilder sb = new StringBuilder(str.length());
        for (char c9 : str.toCharArray()) {
            int iDigit = Character.digit(c9, 10);
            if (iDigit != -1) {
                sb.append(iDigit);
            } else if (z8) {
                sb.append(c9);
            }
        }
        return sb;
    }

    public static String normalizeDigitsOnly(String str) {
        return normalizeDigits(str, false).toString();
    }

    private static String normalizeHelper(String str, Map<Character, Character> map, boolean z8) {
        StringBuilder sb = new StringBuilder(str.length());
        for (int i9 = 0; i9 < str.length(); i9++) {
            char cCharAt = str.charAt(i9);
            Character ch = map.get(Character.valueOf(Character.toUpperCase(cCharAt)));
            if (ch != null) {
                sb.append(ch);
            } else if (!z8) {
                sb.append(cCharAt);
            }
        }
        return sb.toString();
    }

    private void parseHelper(String str, String str2, boolean z8, boolean z9, Phonenumber.PhoneNumber phoneNumber) throws NumberParseException, NumberFormatException {
        int iMaybeExtractCountryCode;
        if (str == null) {
            throw new NumberParseException(NumberParseException.ErrorType.NOT_A_NUMBER, "The phone number supplied was null.");
        }
        if (str.length() > 250) {
            throw new NumberParseException(NumberParseException.ErrorType.TOO_LONG, "The string supplied was too long to parse.");
        }
        StringBuilder sb = new StringBuilder();
        buildNationalNumberForParsing(str, sb);
        if (!isViablePhoneNumber(sb.toString())) {
            throw new NumberParseException(NumberParseException.ErrorType.NOT_A_NUMBER, "The string supplied did not seem to be a phone number.");
        }
        if (z9 && !checkRegionForParsing(sb.toString(), str2)) {
            throw new NumberParseException(NumberParseException.ErrorType.INVALID_COUNTRY_CODE, "Missing or invalid default region.");
        }
        if (z8) {
            phoneNumber.setRawInput(str);
        }
        String strMaybeStripExtension = maybeStripExtension(sb);
        if (strMaybeStripExtension.length() > 0) {
            phoneNumber.setExtension(strMaybeStripExtension);
        }
        Phonemetadata.PhoneMetadata metadataForRegion = getMetadataForRegion(str2);
        StringBuilder sb2 = new StringBuilder();
        try {
            iMaybeExtractCountryCode = maybeExtractCountryCode(sb.toString(), metadataForRegion, sb2, z8, phoneNumber);
        } catch (NumberParseException e9) {
            Matcher matcher = PLUS_CHARS_PATTERN.matcher(sb.toString());
            NumberParseException.ErrorType errorType = e9.getErrorType();
            NumberParseException.ErrorType errorType2 = NumberParseException.ErrorType.INVALID_COUNTRY_CODE;
            if (errorType != errorType2 || !matcher.lookingAt()) {
                throw new NumberParseException(e9.getErrorType(), e9.getMessage());
            }
            iMaybeExtractCountryCode = maybeExtractCountryCode(sb.substring(matcher.end()), metadataForRegion, sb2, z8, phoneNumber);
            if (iMaybeExtractCountryCode == 0) {
                throw new NumberParseException(errorType2, "Could not interpret numbers after plus-sign.");
            }
        }
        if (iMaybeExtractCountryCode != 0) {
            String regionCodeForCountryCode = getRegionCodeForCountryCode(iMaybeExtractCountryCode);
            if (!regionCodeForCountryCode.equals(str2)) {
                metadataForRegion = getMetadataForRegionOrCallingCode(iMaybeExtractCountryCode, regionCodeForCountryCode);
            }
        } else {
            normalize(sb);
            sb2.append((CharSequence) sb);
            if (str2 != null) {
                phoneNumber.setCountryCode(metadataForRegion.countryCode);
            } else if (z8) {
                phoneNumber.clearCountryCodeSource();
            }
        }
        if (sb2.length() < 2) {
            throw new NumberParseException(NumberParseException.ErrorType.TOO_SHORT_NSN, "The string supplied is too short to be a phone number.");
        }
        if (metadataForRegion != null) {
            StringBuilder sb3 = new StringBuilder();
            StringBuilder sb4 = new StringBuilder(sb2);
            maybeStripNationalPrefixAndCarrierCode(sb4, metadataForRegion, sb3);
            if (!isShorterThanPossibleNormalNumber(metadataForRegion, sb4.toString())) {
                if (z8) {
                    phoneNumber.setPreferredDomesticCarrierCode(sb3.toString());
                }
                sb2 = sb4;
            }
        }
        int length = sb2.length();
        if (length < 2) {
            throw new NumberParseException(NumberParseException.ErrorType.TOO_SHORT_NSN, "The string supplied is too short to be a phone number.");
        }
        if (length > 17) {
            throw new NumberParseException(NumberParseException.ErrorType.TOO_LONG, "The string supplied is too long to be a phone number.");
        }
        setItalianLeadingZerosForPhoneNumber(sb2.toString(), phoneNumber);
        phoneNumber.setNationalNumber(Long.parseLong(sb2.toString()));
    }

    private boolean parsePrefixAsIdd(Pattern pattern, StringBuilder sb) {
        Matcher matcher = pattern.matcher(sb);
        if (!matcher.lookingAt()) {
            return false;
        }
        int iEnd = matcher.end();
        Matcher matcher2 = CAPTURING_DIGIT_PATTERN.matcher(sb.substring(iEnd));
        if (matcher2.find() && normalizeDigitsOnly(matcher2.group(1)).equals("0")) {
            return false;
        }
        sb.delete(0, iEnd);
        return true;
    }

    private void prefixNumberWithCountryCallingCode(int i9, PhoneNumberFormat phoneNumberFormat, StringBuilder sb) {
        int i10 = C43103.f15591xd187ceb9[phoneNumberFormat.ordinal()];
        if (i10 == 1) {
            sb.insert(0, i9).insert(0, PLUS_SIGN);
        } else if (i10 == 2) {
            sb.insert(0, StringUtils.SPACE).insert(0, i9).insert(0, PLUS_SIGN);
        } else {
            if (i10 != 3) {
                return;
            }
            sb.insert(0, "-").insert(0, i9).insert(0, PLUS_SIGN).insert(0, RFC3966_PREFIX);
        }
    }

    private boolean rawInputContainsNationalPrefix(String str, String str2, String str3) {
        String strNormalizeDigitsOnly = normalizeDigitsOnly(str);
        if (strNormalizeDigitsOnly.startsWith(str2)) {
            try {
                return isValidNumber(parse(strNormalizeDigitsOnly.substring(str2.length()), str3));
            } catch (NumberParseException unused) {
            }
        }
        return false;
    }

    public static synchronized void setInstance(PhoneNumberUtil phoneNumberUtil) {
        instance = phoneNumberUtil;
    }

    public static void setItalianLeadingZerosForPhoneNumber(String str, Phonenumber.PhoneNumber phoneNumber) {
        if (str.length() <= 1 || str.charAt(0) != '0') {
            return;
        }
        phoneNumber.setItalianLeadingZero(true);
        int i9 = 1;
        while (i9 < str.length() - 1 && str.charAt(i9) == '0') {
            i9++;
        }
        if (i9 != 1) {
            phoneNumber.setNumberOfLeadingZeros(i9);
        }
    }

    private ValidationResult testNumberLengthAgainstPattern(Pattern pattern, String str) {
        Matcher matcher = pattern.matcher(str);
        return matcher.matches() ? ValidationResult.IS_POSSIBLE : matcher.lookingAt() ? ValidationResult.TOO_LONG : ValidationResult.TOO_SHORT;
    }

    public boolean canBeInternationallyDialled(Phonenumber.PhoneNumber phoneNumber) {
        if (getMetadataForRegion(getRegionCodeForNumber(phoneNumber)) == null) {
            return true;
        }
        return !isNumberMatchingDesc(getNationalSignificantNumber(phoneNumber), r0.noInternationalDialling);
    }

    public Phonemetadata.NumberFormat chooseFormattingPatternForNumber(Phonemetadata.NumberFormat[] numberFormatArr, String str) {
        for (Phonemetadata.NumberFormat numberFormat : numberFormatArr) {
            String[] strArr = numberFormat.leadingDigitsPattern;
            int length = strArr.length;
            if ((length == 0 || this.regexCache.getPatternForRegex(strArr[length - 1]).matcher(str).lookingAt()) && this.regexCache.getPatternForRegex(numberFormat.pattern).matcher(str).matches()) {
                return numberFormat;
            }
        }
        return null;
    }

    public int extractCountryCode(StringBuilder sb, StringBuilder sb2) throws NumberFormatException {
        if (sb.length() != 0 && sb.charAt(0) != '0') {
            int length = sb.length();
            for (int i9 = 1; i9 <= 3 && i9 <= length; i9++) {
                int i10 = Integer.parseInt(sb.substring(0, i9));
                if (this.countryCallingCodeToRegionCodeMap.containsKey(Integer.valueOf(i10))) {
                    sb2.append(sb.substring(i9));
                    return i10;
                }
            }
        }
        return 0;
    }

    public Iterable<PhoneNumberMatch> findNumbers(CharSequence charSequence, String str) {
        return findNumbers(charSequence, str, Leniency.VALID, Long.MAX_VALUE);
    }

    public String format(Phonenumber.PhoneNumber phoneNumber, PhoneNumberFormat phoneNumberFormat) {
        if (phoneNumber.getNationalNumber() == 0 && phoneNumber.hasRawInput()) {
            String rawInput = phoneNumber.getRawInput();
            if (rawInput.length() > 0) {
                return rawInput;
            }
        }
        StringBuilder sb = new StringBuilder(20);
        format(phoneNumber, phoneNumberFormat, sb);
        return sb.toString();
    }

    public String formatByPattern(Phonenumber.PhoneNumber phoneNumber, PhoneNumberFormat phoneNumberFormat, List<Phonemetadata.NumberFormat> list) {
        int countryCode = phoneNumber.getCountryCode();
        String nationalSignificantNumber = getNationalSignificantNumber(phoneNumber);
        if (!hasValidCountryCallingCode(countryCode)) {
            return nationalSignificantNumber;
        }
        Phonemetadata.PhoneMetadata metadataForRegionOrCallingCode = getMetadataForRegionOrCallingCode(countryCode, getRegionCodeForCountryCode(countryCode));
        StringBuilder sb = new StringBuilder(20);
        Phonemetadata.NumberFormat numberFormatChooseFormattingPatternForNumber = chooseFormattingPatternForNumber((Phonemetadata.NumberFormat[]) list.toArray(new Phonemetadata.NumberFormat[list.size()]), nationalSignificantNumber);
        if (numberFormatChooseFormattingPatternForNumber == null) {
            sb.append(nationalSignificantNumber);
        } else {
            Phonemetadata.NumberFormat numberFormatCopyNumberFormat = copyNumberFormat(numberFormatChooseFormattingPatternForNumber);
            String str = numberFormatChooseFormattingPatternForNumber.nationalPrefixFormattingRule;
            if (str.length() > 0) {
                String str2 = metadataForRegionOrCallingCode.nationalPrefix;
                if (str2.length() > 0) {
                    numberFormatCopyNumberFormat.nationalPrefixFormattingRule = FG_PATTERN.matcher(NP_PATTERN.matcher(str).replaceFirst(str2)).replaceFirst("\\$1");
                } else {
                    numberFormatCopyNumberFormat.nationalPrefixFormattingRule = "";
                }
            }
            sb.append(formatNsnUsingPattern(nationalSignificantNumber, numberFormatCopyNumberFormat, phoneNumberFormat));
        }
        maybeAppendFormattedExtension(phoneNumber, metadataForRegionOrCallingCode, phoneNumberFormat, sb);
        prefixNumberWithCountryCallingCode(countryCode, phoneNumberFormat, sb);
        return sb.toString();
    }

    public String formatInOriginalFormat(Phonenumber.PhoneNumber phoneNumber, String str) {
        String outOfCountryCallingNumber;
        String str2;
        int iIndexOf;
        if (phoneNumber.hasRawInput() && (hasUnexpectedItalianLeadingZero(phoneNumber) || !hasFormattingPatternForNumber(phoneNumber))) {
            return phoneNumber.getRawInput();
        }
        if (!phoneNumber.hasCountryCodeSource()) {
            return format(phoneNumber, PhoneNumberFormat.NATIONAL);
        }
        int i9 = C43103.f15593x9de98e3a[phoneNumber.getCountryCodeSource().ordinal()];
        if (i9 == 1) {
            outOfCountryCallingNumber = format(phoneNumber, PhoneNumberFormat.INTERNATIONAL);
        } else if (i9 == 2) {
            outOfCountryCallingNumber = formatOutOfCountryCallingNumber(phoneNumber, str);
        } else if (i9 != 3) {
            String regionCodeForCountryCode = getRegionCodeForCountryCode(phoneNumber.getCountryCode());
            String nddPrefixForRegion = getNddPrefixForRegion(regionCodeForCountryCode, true);
            PhoneNumberFormat phoneNumberFormat = PhoneNumberFormat.NATIONAL;
            outOfCountryCallingNumber = format(phoneNumber, phoneNumberFormat);
            if (nddPrefixForRegion != null && nddPrefixForRegion.length() != 0 && !rawInputContainsNationalPrefix(phoneNumber.getRawInput(), nddPrefixForRegion, regionCodeForCountryCode)) {
                Phonemetadata.NumberFormat numberFormatChooseFormattingPatternForNumber = chooseFormattingPatternForNumber(getMetadataForRegion(regionCodeForCountryCode).numberFormat, getNationalSignificantNumber(phoneNumber));
                if (numberFormatChooseFormattingPatternForNumber != null && (iIndexOf = (str2 = numberFormatChooseFormattingPatternForNumber.nationalPrefixFormattingRule).indexOf("$1")) > 0 && normalizeDigitsOnly(str2.substring(0, iIndexOf)).length() != 0) {
                    Phonemetadata.NumberFormat numberFormatCopyNumberFormat = copyNumberFormat(numberFormatChooseFormattingPatternForNumber);
                    numberFormatCopyNumberFormat.nationalPrefixFormattingRule = "";
                    ArrayList arrayList = new ArrayList(1);
                    arrayList.add(numberFormatCopyNumberFormat);
                    outOfCountryCallingNumber = formatByPattern(phoneNumber, phoneNumberFormat, arrayList);
                }
            }
        } else {
            outOfCountryCallingNumber = format(phoneNumber, PhoneNumberFormat.INTERNATIONAL).substring(1);
        }
        String rawInput = phoneNumber.getRawInput();
        return (outOfCountryCallingNumber == null || rawInput.length() <= 0 || normalizeDiallableCharsOnly(outOfCountryCallingNumber).equals(normalizeDiallableCharsOnly(rawInput))) ? outOfCountryCallingNumber : rawInput;
    }

    public String formatNationalNumberWithCarrierCode(Phonenumber.PhoneNumber phoneNumber, String str) {
        int countryCode = phoneNumber.getCountryCode();
        String nationalSignificantNumber = getNationalSignificantNumber(phoneNumber);
        if (!hasValidCountryCallingCode(countryCode)) {
            return nationalSignificantNumber;
        }
        Phonemetadata.PhoneMetadata metadataForRegionOrCallingCode = getMetadataForRegionOrCallingCode(countryCode, getRegionCodeForCountryCode(countryCode));
        StringBuilder sb = new StringBuilder(20);
        PhoneNumberFormat phoneNumberFormat = PhoneNumberFormat.NATIONAL;
        sb.append(formatNsn(nationalSignificantNumber, metadataForRegionOrCallingCode, phoneNumberFormat, str));
        maybeAppendFormattedExtension(phoneNumber, metadataForRegionOrCallingCode, phoneNumberFormat, sb);
        prefixNumberWithCountryCallingCode(countryCode, phoneNumberFormat, sb);
        return sb.toString();
    }

    public String formatNationalNumberWithPreferredCarrierCode(Phonenumber.PhoneNumber phoneNumber, String str) {
        if (phoneNumber.hasPreferredDomesticCarrierCode()) {
            str = phoneNumber.getPreferredDomesticCarrierCode();
        }
        return formatNationalNumberWithCarrierCode(phoneNumber, str);
    }

    public String formatNsnUsingPattern(String str, Phonemetadata.NumberFormat numberFormat, PhoneNumberFormat phoneNumberFormat) {
        return formatNsnUsingPattern(str, numberFormat, phoneNumberFormat, null);
    }

    public String formatNumberForMobileDialing(Phonenumber.PhoneNumber phoneNumber, String str, boolean z8) {
        String string;
        int countryCode = phoneNumber.getCountryCode();
        String nationalNumberWithPreferredCarrierCode = "";
        if (!hasValidCountryCallingCode(countryCode)) {
            return phoneNumber.hasRawInput() ? phoneNumber.getRawInput() : "";
        }
        Phonenumber.PhoneNumber phoneNumberClearExtension = new Phonenumber.PhoneNumber().mergeFrom(phoneNumber).clearExtension();
        String regionCodeForCountryCode = getRegionCodeForCountryCode(countryCode);
        PhoneNumberType numberType = getNumberType(phoneNumberClearExtension);
        boolean z9 = numberType != PhoneNumberType.UNKNOWN;
        if (str.equals(regionCodeForCountryCode)) {
            PhoneNumberType phoneNumberType = PhoneNumberType.FIXED_LINE;
            boolean z10 = numberType == phoneNumberType || numberType == PhoneNumberType.MOBILE || numberType == PhoneNumberType.FIXED_LINE_OR_MOBILE;
            if (regionCodeForCountryCode.equals("CO") && numberType == phoneNumberType) {
                string = formatNationalNumberWithCarrierCode(phoneNumberClearExtension, COLOMBIA_MOBILE_TO_FIXED_LINE_PREFIX);
            } else if (regionCodeForCountryCode.equals("BR") && z10) {
                if (phoneNumberClearExtension.hasPreferredDomesticCarrierCode()) {
                    nationalNumberWithPreferredCarrierCode = formatNationalNumberWithPreferredCarrierCode(phoneNumberClearExtension, "");
                }
            } else if (z9 && regionCodeForCountryCode.equals("HU")) {
                String strValueOf = String.valueOf(getNddPrefixForRegion(regionCodeForCountryCode, true));
                String strValueOf2 = String.valueOf(format(phoneNumberClearExtension, PhoneNumberFormat.NATIONAL));
                StringBuilder sb = new StringBuilder(strValueOf.length() + 1 + strValueOf2.length());
                sb.append(strValueOf);
                sb.append(StringUtils.SPACE);
                sb.append(strValueOf2);
                string = sb.toString();
            } else if (countryCode == 1) {
                string = (!canBeInternationallyDialled(phoneNumberClearExtension) || isShorterThanPossibleNormalNumber(getMetadataForRegion(str), getNationalSignificantNumber(phoneNumberClearExtension))) ? format(phoneNumberClearExtension, PhoneNumberFormat.NATIONAL) : format(phoneNumberClearExtension, PhoneNumberFormat.INTERNATIONAL);
            } else {
                string = ((regionCodeForCountryCode.equals(REGION_CODE_FOR_NON_GEO_ENTITY) || ((regionCodeForCountryCode.equals("MX") || regionCodeForCountryCode.equals("CL")) && z10)) && canBeInternationallyDialled(phoneNumberClearExtension)) ? format(phoneNumberClearExtension, PhoneNumberFormat.INTERNATIONAL) : format(phoneNumberClearExtension, PhoneNumberFormat.NATIONAL);
            }
            nationalNumberWithPreferredCarrierCode = string;
        } else if (z9 && canBeInternationallyDialled(phoneNumberClearExtension)) {
            return format(phoneNumberClearExtension, z8 ? PhoneNumberFormat.INTERNATIONAL : PhoneNumberFormat.E164);
        }
        return z8 ? nationalNumberWithPreferredCarrierCode : normalizeDiallableCharsOnly(nationalNumberWithPreferredCarrierCode);
    }

    public String formatOutOfCountryCallingNumber(Phonenumber.PhoneNumber phoneNumber, String str) {
        if (!isValidRegionCode(str)) {
            Logger logger2 = logger;
            Level level = Level.WARNING;
            String strValueOf = String.valueOf(str);
            StringBuilder sb = new StringBuilder(strValueOf.length() + 79);
            sb.append("Trying to format number from invalid region ");
            sb.append(strValueOf);
            sb.append(". International formatting applied.");
            logger2.log(level, sb.toString());
            return format(phoneNumber, PhoneNumberFormat.INTERNATIONAL);
        }
        int countryCode = phoneNumber.getCountryCode();
        String nationalSignificantNumber = getNationalSignificantNumber(phoneNumber);
        if (!hasValidCountryCallingCode(countryCode)) {
            return nationalSignificantNumber;
        }
        if (countryCode == 1) {
            if (isNANPACountry(str)) {
                String strValueOf2 = String.valueOf(format(phoneNumber, PhoneNumberFormat.NATIONAL));
                StringBuilder sb2 = new StringBuilder(strValueOf2.length() + 12);
                sb2.append(countryCode);
                sb2.append(StringUtils.SPACE);
                sb2.append(strValueOf2);
                return sb2.toString();
            }
        } else if (countryCode == getCountryCodeForValidRegion(str)) {
            return format(phoneNumber, PhoneNumberFormat.NATIONAL);
        }
        Phonemetadata.PhoneMetadata metadataForRegion = getMetadataForRegion(str);
        String str2 = metadataForRegion.internationalPrefix;
        if (!UNIQUE_INTERNATIONAL_PREFIX.matcher(str2).matches()) {
            str2 = !metadataForRegion.preferredInternationalPrefix.equals("") ? metadataForRegion.preferredInternationalPrefix : "";
        }
        Phonemetadata.PhoneMetadata metadataForRegionOrCallingCode = getMetadataForRegionOrCallingCode(countryCode, getRegionCodeForCountryCode(countryCode));
        PhoneNumberFormat phoneNumberFormat = PhoneNumberFormat.INTERNATIONAL;
        StringBuilder sb3 = new StringBuilder(formatNsn(nationalSignificantNumber, metadataForRegionOrCallingCode, phoneNumberFormat));
        maybeAppendFormattedExtension(phoneNumber, metadataForRegionOrCallingCode, phoneNumberFormat, sb3);
        if (str2.length() > 0) {
            sb3.insert(0, StringUtils.SPACE).insert(0, countryCode).insert(0, StringUtils.SPACE).insert(0, str2);
        } else {
            prefixNumberWithCountryCallingCode(countryCode, phoneNumberFormat, sb3);
        }
        return sb3.toString();
    }

    public String formatOutOfCountryKeepingAlphaChars(Phonenumber.PhoneNumber phoneNumber, String str) {
        String str2;
        int iIndexOf;
        String rawInput = phoneNumber.getRawInput();
        if (rawInput.length() == 0) {
            return formatOutOfCountryCallingNumber(phoneNumber, str);
        }
        int countryCode = phoneNumber.getCountryCode();
        if (!hasValidCountryCallingCode(countryCode)) {
            return rawInput;
        }
        String strNormalizeHelper = normalizeHelper(rawInput, ALL_PLUS_NUMBER_GROUPING_SYMBOLS, true);
        String nationalSignificantNumber = getNationalSignificantNumber(phoneNumber);
        if (nationalSignificantNumber.length() > 3 && (iIndexOf = strNormalizeHelper.indexOf(nationalSignificantNumber.substring(0, 3))) != -1) {
            strNormalizeHelper = strNormalizeHelper.substring(iIndexOf);
        }
        Phonemetadata.PhoneMetadata metadataForRegion = getMetadataForRegion(str);
        if (countryCode == 1) {
            if (isNANPACountry(str)) {
                String strValueOf = String.valueOf(strNormalizeHelper);
                StringBuilder sb = new StringBuilder(strValueOf.length() + 12);
                sb.append(countryCode);
                sb.append(StringUtils.SPACE);
                sb.append(strValueOf);
                return sb.toString();
            }
        } else if (metadataForRegion != null && countryCode == getCountryCodeForValidRegion(str)) {
            Phonemetadata.NumberFormat numberFormatChooseFormattingPatternForNumber = chooseFormattingPatternForNumber(metadataForRegion.numberFormat, nationalSignificantNumber);
            if (numberFormatChooseFormattingPatternForNumber == null) {
                return strNormalizeHelper;
            }
            Phonemetadata.NumberFormat numberFormatCopyNumberFormat = copyNumberFormat(numberFormatChooseFormattingPatternForNumber);
            numberFormatCopyNumberFormat.pattern = "(\\d+)(.*)";
            numberFormatCopyNumberFormat.format = "$1$2";
            return formatNsnUsingPattern(strNormalizeHelper, numberFormatCopyNumberFormat, PhoneNumberFormat.NATIONAL);
        }
        if (metadataForRegion != null) {
            str2 = metadataForRegion.internationalPrefix;
            if (!UNIQUE_INTERNATIONAL_PREFIX.matcher(str2).matches()) {
                str2 = metadataForRegion.preferredInternationalPrefix;
            }
        } else {
            str2 = "";
        }
        StringBuilder sb2 = new StringBuilder(strNormalizeHelper);
        Phonemetadata.PhoneMetadata metadataForRegionOrCallingCode = getMetadataForRegionOrCallingCode(countryCode, getRegionCodeForCountryCode(countryCode));
        PhoneNumberFormat phoneNumberFormat = PhoneNumberFormat.INTERNATIONAL;
        maybeAppendFormattedExtension(phoneNumber, metadataForRegionOrCallingCode, phoneNumberFormat, sb2);
        if (str2.length() > 0) {
            sb2.insert(0, StringUtils.SPACE).insert(0, countryCode).insert(0, StringUtils.SPACE).insert(0, str2);
        } else {
            if (!isValidRegionCode(str)) {
                Logger logger2 = logger;
                Level level = Level.WARNING;
                String strValueOf2 = String.valueOf(str);
                StringBuilder sb3 = new StringBuilder(strValueOf2.length() + 79);
                sb3.append("Trying to format number from invalid region ");
                sb3.append(strValueOf2);
                sb3.append(". International formatting applied.");
                logger2.log(level, sb3.toString());
            }
            prefixNumberWithCountryCallingCode(countryCode, phoneNumberFormat, sb2);
        }
        return sb2.toString();
    }

    public AsYouTypeFormatter getAsYouTypeFormatter(String str) {
        return new AsYouTypeFormatter(str);
    }

    public int getCountryCodeForRegion(String str) {
        if (isValidRegionCode(str)) {
            return getCountryCodeForValidRegion(str);
        }
        Logger logger2 = logger;
        Level level = Level.WARNING;
        if (str == null) {
            str = "null";
        }
        StringBuilder sb = new StringBuilder(str.length() + 43);
        sb.append("Invalid or missing region code (");
        sb.append(str);
        sb.append(") provided.");
        logger2.log(level, sb.toString());
        return 0;
    }

    public Phonenumber.PhoneNumber getExampleNumber(String str) {
        return getExampleNumberForType(str, PhoneNumberType.FIXED_LINE);
    }

    public Phonenumber.PhoneNumber getExampleNumberForNonGeoEntity(int i9) {
        Phonemetadata.PhoneMetadata metadataForNonGeographicalRegion = getMetadataForNonGeographicalRegion(i9);
        if (metadataForNonGeographicalRegion == null) {
            Logger logger2 = logger;
            Level level = Level.WARNING;
            StringBuilder sb = new StringBuilder(61);
            sb.append("Invalid or unknown country calling code provided: ");
            sb.append(i9);
            logger2.log(level, sb.toString());
            return null;
        }
        Phonemetadata.PhoneNumberDesc phoneNumberDesc = metadataForNonGeographicalRegion.generalDesc;
        try {
            if (phoneNumberDesc.exampleNumber.equals("")) {
                return null;
            }
            String strValueOf = String.valueOf(phoneNumberDesc.exampleNumber);
            StringBuilder sb2 = new StringBuilder(strValueOf.length() + 12);
            sb2.append("+");
            sb2.append(i9);
            sb2.append(strValueOf);
            return parse(sb2.toString(), UNKNOWN_REGION);
        } catch (NumberParseException e9) {
            logger.log(Level.SEVERE, e9.toString());
            return null;
        }
    }

    public Phonenumber.PhoneNumber getExampleNumberForType(String str, PhoneNumberType phoneNumberType) {
        if (!isValidRegionCode(str)) {
            Logger logger2 = logger;
            Level level = Level.WARNING;
            String strValueOf = String.valueOf(str);
            logger2.log(level, strValueOf.length() != 0 ? "Invalid or unknown region code provided: ".concat(strValueOf) : new String("Invalid or unknown region code provided: "));
            return null;
        }
        Phonemetadata.PhoneNumberDesc numberDescByType = getNumberDescByType(getMetadataForRegion(str), phoneNumberType);
        try {
            if (!numberDescByType.exampleNumber.equals("")) {
                return parse(numberDescByType.exampleNumber, str);
            }
        } catch (NumberParseException e9) {
            logger.log(Level.SEVERE, e9.toString());
        }
        return null;
    }

    /*  JADX ERROR: NullPointerException in pass: BlockProcessor
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.nodes.BlockNode.getPredecessors()" because "to" is null
        	at jadx.core.dex.visitors.blocks.BlockSplitter.connect(BlockSplitter.java:159)
        	at jadx.core.dex.visitors.blocks.BlockExceptionHandler.connectSplittersAndHandlers(BlockExceptionHandler.java:480)
        	at jadx.core.dex.visitors.blocks.BlockExceptionHandler.wrapBlocksWithTryCatch(BlockExceptionHandler.java:381)
        	at jadx.core.dex.visitors.blocks.BlockExceptionHandler.connectExcHandlers(BlockExceptionHandler.java:90)
        	at jadx.core.dex.visitors.blocks.BlockExceptionHandler.process(BlockExceptionHandler.java:61)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.independentBlockTreeMod(BlockProcessor.java:372)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:56)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:49)
        */
    public com.google.i18n.phonenumbers.Phonenumber.PhoneNumber getInvalidExampleNumber(java.lang.String r6) {
        /*
            r5 = this;
            boolean r0 = r5.isValidRegionCode(r6)
            r1 = 0
            if (r0 != 0) goto L25
            java.util.logging.Logger r0 = com.google.i18n.phonenumbers.PhoneNumberUtil.logger
            java.util.logging.Level r2 = java.util.logging.Level.WARNING
            java.lang.String r6 = java.lang.String.valueOf(r6)
            int r3 = r6.length()
            java.lang.String r4 = "Invalid or unknown region code provided: "
            if (r3 == 0) goto L1c
            java.lang.String r6 = r4.concat(r6)
            goto L21
        L1c:
            java.lang.String r6 = new java.lang.String
            r6.<init>(r4)
        L21:
            r0.log(r2, r6)
            return r1
        L25:
            com.google.i18n.phonenumbers.nano.Phonemetadata$PhoneMetadata r0 = r5.getMetadataForRegion(r6)
            com.google.i18n.phonenumbers.PhoneNumberUtil$PhoneNumberType r2 = com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberType.FIXED_LINE
            com.google.i18n.phonenumbers.nano.Phonemetadata$PhoneNumberDesc r0 = r5.getNumberDescByType(r0, r2)
            java.lang.String r2 = r0.exampleNumber
            java.lang.String r3 = ""
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L3a
            return r1
        L3a:
            java.lang.String r0 = r0.exampleNumber
            int r2 = r0.length()
            int r2 = r2 + (-1)
        L42:
            r3 = 2
            if (r2 < r3) goto L58
            r3 = 0
            java.lang.String r3 = r0.substring(r3, r2)
            com.google.i18n.phonenumbers.Phonenumber$PhoneNumber r3 = r5.parse(r3, r6)     // Catch: com.google.i18n.phonenumbers.NumberParseException -> L55
            boolean r4 = r5.isValidNumber(r3)     // Catch: com.google.i18n.phonenumbers.NumberParseException -> L55
            if (r4 != 0) goto L55
            return r3
        L55:
            int r2 = r2 + (-1)
            goto L42
        L58:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.i18n.phonenumbers.PhoneNumberUtil.getInvalidExampleNumber(java.lang.String):com.google.i18n.phonenumbers.Phonenumber$PhoneNumber");
    }

    public int getLengthOfGeographicalAreaCode(Phonenumber.PhoneNumber phoneNumber) {
        Phonemetadata.PhoneMetadata metadataForRegion = getMetadataForRegion(getRegionCodeForNumber(phoneNumber));
        if (metadataForRegion == null) {
            return 0;
        }
        if ((!metadataForRegion.nationalPrefix.equals("") || phoneNumber.isItalianLeadingZero()) && isNumberGeographical(phoneNumber)) {
            return getLengthOfNationalDestinationCode(phoneNumber);
        }
        return 0;
    }

    public int getLengthOfNationalDestinationCode(Phonenumber.PhoneNumber phoneNumber) {
        Phonenumber.PhoneNumber phoneNumber2;
        if (phoneNumber.hasExtension()) {
            phoneNumber2 = new Phonenumber.PhoneNumber();
            phoneNumber2.mergeFrom(phoneNumber);
            phoneNumber2.clearExtension();
        } else {
            phoneNumber2 = phoneNumber;
        }
        String[] strArrSplit = NON_DIGITS_PATTERN.split(format(phoneNumber2, PhoneNumberFormat.INTERNATIONAL));
        if (strArrSplit.length <= 3) {
            return 0;
        }
        return (getNumberType(phoneNumber) != PhoneNumberType.MOBILE || getCountryMobileToken(phoneNumber.getCountryCode()).equals("")) ? strArrSplit[2].length() : strArrSplit[2].length() + strArrSplit[3].length();
    }

    public Phonemetadata.PhoneMetadata getMetadataForNonGeographicalRegion(int i9) {
        if (this.countryCallingCodeToRegionCodeMap.containsKey(Integer.valueOf(i9))) {
            return this.metadataSource.getMetadataForNonGeographicalRegion(i9);
        }
        return null;
    }

    public Phonemetadata.PhoneMetadata getMetadataForRegion(String str) {
        if (isValidRegionCode(str)) {
            return this.metadataSource.getMetadataForRegion(str);
        }
        return null;
    }

    public String getNationalSignificantNumber(Phonenumber.PhoneNumber phoneNumber) {
        StringBuilder sb = new StringBuilder();
        if (phoneNumber.isItalianLeadingZero()) {
            char[] cArr = new char[phoneNumber.getNumberOfLeadingZeros()];
            Arrays.fill(cArr, '0');
            sb.append(new String(cArr));
        }
        sb.append(phoneNumber.getNationalNumber());
        return sb.toString();
    }

    public String getNddPrefixForRegion(String str, boolean z8) {
        Phonemetadata.PhoneMetadata metadataForRegion = getMetadataForRegion(str);
        if (metadataForRegion != null) {
            String str2 = metadataForRegion.nationalPrefix;
            if (str2.length() == 0) {
                return null;
            }
            return z8 ? str2.replace("~", "") : str2;
        }
        Logger logger2 = logger;
        Level level = Level.WARNING;
        if (str == null) {
            str = "null";
        }
        StringBuilder sb = new StringBuilder(str.length() + 43);
        sb.append("Invalid or missing region code (");
        sb.append(str);
        sb.append(") provided.");
        logger2.log(level, sb.toString());
        return null;
    }

    public Phonemetadata.PhoneNumberDesc getNumberDescByType(Phonemetadata.PhoneMetadata phoneMetadata, PhoneNumberType phoneNumberType) {
        switch (C43103.f15592xa7892e7c[phoneNumberType.ordinal()]) {
            case 1:
                return phoneMetadata.premiumRate;
            case 2:
                return phoneMetadata.tollFree;
            case 3:
                return phoneMetadata.mobile;
            case 4:
            case 5:
                return phoneMetadata.fixedLine;
            case 6:
                return phoneMetadata.sharedCost;
            case 7:
                return phoneMetadata.voip;
            case 8:
                return phoneMetadata.personalNumber;
            case 9:
                return phoneMetadata.pager;
            case 10:
                return phoneMetadata.uan;
            case 11:
                return phoneMetadata.voicemail;
            default:
                return phoneMetadata.generalDesc;
        }
    }

    public PhoneNumberType getNumberType(Phonenumber.PhoneNumber phoneNumber) {
        Phonemetadata.PhoneMetadata metadataForRegionOrCallingCode = getMetadataForRegionOrCallingCode(phoneNumber.getCountryCode(), getRegionCodeForNumber(phoneNumber));
        return metadataForRegionOrCallingCode == null ? PhoneNumberType.UNKNOWN : getNumberTypeHelper(getNationalSignificantNumber(phoneNumber), metadataForRegionOrCallingCode);
    }

    public String getRegionCodeForCountryCode(int i9) {
        List<String> list = this.countryCallingCodeToRegionCodeMap.get(Integer.valueOf(i9));
        return list == null ? UNKNOWN_REGION : list.get(0);
    }

    public String getRegionCodeForNumber(Phonenumber.PhoneNumber phoneNumber) {
        int countryCode = phoneNumber.getCountryCode();
        List<String> list = this.countryCallingCodeToRegionCodeMap.get(Integer.valueOf(countryCode));
        if (list != null) {
            return list.size() == 1 ? list.get(0) : getRegionCodeForNumberFromRegionList(phoneNumber, list);
        }
        String nationalSignificantNumber = getNationalSignificantNumber(phoneNumber);
        Logger logger2 = logger;
        Level level = Level.INFO;
        String strValueOf = String.valueOf(nationalSignificantNumber);
        StringBuilder sb = new StringBuilder(strValueOf.length() + 54);
        sb.append("Missing/invalid country_code (");
        sb.append(countryCode);
        sb.append(") for number ");
        sb.append(strValueOf);
        logger2.log(level, sb.toString());
        return null;
    }

    public List<String> getRegionCodesForCountryCode(int i9) {
        List<String> arrayList = this.countryCallingCodeToRegionCodeMap.get(Integer.valueOf(i9));
        if (arrayList == null) {
            arrayList = new ArrayList<>(0);
        }
        return Collections.unmodifiableList(arrayList);
    }

    public Set<Integer> getSupportedGlobalNetworkCallingCodes() {
        return Collections.unmodifiableSet(this.countryCodesForNonGeographicalRegion);
    }

    public Set<String> getSupportedRegions() {
        return Collections.unmodifiableSet(this.supportedRegions);
    }

    public boolean isAlphaNumber(String str) {
        if (!isViablePhoneNumber(str)) {
            return false;
        }
        StringBuilder sb = new StringBuilder(str);
        maybeStripExtension(sb);
        return VALID_ALPHA_PHONE_PATTERN.matcher(sb).matches();
    }

    public boolean isLeadingZeroPossible(int i9) {
        Phonemetadata.PhoneMetadata metadataForRegionOrCallingCode = getMetadataForRegionOrCallingCode(i9, getRegionCodeForCountryCode(i9));
        if (metadataForRegionOrCallingCode == null) {
            return false;
        }
        return metadataForRegionOrCallingCode.leadingZeroPossible;
    }

    public boolean isMobileNumberPortableRegion(String str) {
        Phonemetadata.PhoneMetadata metadataForRegion = getMetadataForRegion(str);
        if (metadataForRegion != null) {
            return metadataForRegion.mobileNumberPortableRegion;
        }
        Logger logger2 = logger;
        Level level = Level.WARNING;
        String strValueOf = String.valueOf(str);
        logger2.log(level, strValueOf.length() != 0 ? "Invalid or unknown region code provided: ".concat(strValueOf) : new String("Invalid or unknown region code provided: "));
        return false;
    }

    public boolean isNANPACountry(String str) {
        return this.nanpaRegions.contains(str);
    }

    public boolean isNumberGeographical(Phonenumber.PhoneNumber phoneNumber) {
        PhoneNumberType numberType = getNumberType(phoneNumber);
        return numberType == PhoneNumberType.FIXED_LINE || numberType == PhoneNumberType.FIXED_LINE_OR_MOBILE || (GEO_MOBILE_COUNTRIES.contains(Integer.valueOf(phoneNumber.getCountryCode())) && numberType == PhoneNumberType.MOBILE);
    }

    public MatchType isNumberMatch(Phonenumber.PhoneNumber phoneNumber, Phonenumber.PhoneNumber phoneNumber2) {
        Phonenumber.PhoneNumber phoneNumber3 = new Phonenumber.PhoneNumber();
        phoneNumber3.mergeFrom(phoneNumber);
        Phonenumber.PhoneNumber phoneNumber4 = new Phonenumber.PhoneNumber();
        phoneNumber4.mergeFrom(phoneNumber2);
        phoneNumber3.clearRawInput();
        phoneNumber3.clearCountryCodeSource();
        phoneNumber3.clearPreferredDomesticCarrierCode();
        phoneNumber4.clearRawInput();
        phoneNumber4.clearCountryCodeSource();
        phoneNumber4.clearPreferredDomesticCarrierCode();
        if (phoneNumber3.hasExtension() && phoneNumber3.getExtension().length() == 0) {
            phoneNumber3.clearExtension();
        }
        if (phoneNumber4.hasExtension() && phoneNumber4.getExtension().length() == 0) {
            phoneNumber4.clearExtension();
        }
        if (phoneNumber3.hasExtension() && phoneNumber4.hasExtension() && !phoneNumber3.getExtension().equals(phoneNumber4.getExtension())) {
            return MatchType.NO_MATCH;
        }
        int countryCode = phoneNumber3.getCountryCode();
        int countryCode2 = phoneNumber4.getCountryCode();
        if (countryCode != 0 && countryCode2 != 0) {
            return phoneNumber3.exactlySameAs(phoneNumber4) ? MatchType.EXACT_MATCH : (countryCode == countryCode2 && isNationalNumberSuffixOfTheOther(phoneNumber3, phoneNumber4)) ? MatchType.SHORT_NSN_MATCH : MatchType.NO_MATCH;
        }
        phoneNumber3.setCountryCode(countryCode2);
        return phoneNumber3.exactlySameAs(phoneNumber4) ? MatchType.NSN_MATCH : isNationalNumberSuffixOfTheOther(phoneNumber3, phoneNumber4) ? MatchType.SHORT_NSN_MATCH : MatchType.NO_MATCH;
    }

    public boolean isNumberMatchingDesc(String str, Phonemetadata.PhoneNumberDesc phoneNumberDesc) {
        return isNumberPossibleForDesc(str, phoneNumberDesc) && this.regexCache.getPatternForRegex(phoneNumberDesc.nationalNumberPattern).matcher(str).matches();
    }

    public boolean isNumberPossibleForDesc(String str, Phonemetadata.PhoneNumberDesc phoneNumberDesc) {
        return this.regexCache.getPatternForRegex(phoneNumberDesc.possibleNumberPattern).matcher(str).matches();
    }

    public boolean isPossibleNumber(Phonenumber.PhoneNumber phoneNumber) {
        return isPossibleNumberWithReason(phoneNumber) == ValidationResult.IS_POSSIBLE;
    }

    public ValidationResult isPossibleNumberWithReason(Phonenumber.PhoneNumber phoneNumber) {
        String nationalSignificantNumber = getNationalSignificantNumber(phoneNumber);
        int countryCode = phoneNumber.getCountryCode();
        if (!hasValidCountryCallingCode(countryCode)) {
            return ValidationResult.INVALID_COUNTRY_CODE;
        }
        return testNumberLengthAgainstPattern(this.regexCache.getPatternForRegex(getMetadataForRegionOrCallingCode(countryCode, getRegionCodeForCountryCode(countryCode)).generalDesc.possibleNumberPattern), nationalSignificantNumber);
    }

    public boolean isValidNumber(Phonenumber.PhoneNumber phoneNumber) {
        return isValidNumberForRegion(phoneNumber, getRegionCodeForNumber(phoneNumber));
    }

    public boolean isValidNumberForRegion(Phonenumber.PhoneNumber phoneNumber, String str) {
        int countryCode = phoneNumber.getCountryCode();
        Phonemetadata.PhoneMetadata metadataForRegionOrCallingCode = getMetadataForRegionOrCallingCode(countryCode, str);
        if (metadataForRegionOrCallingCode != null) {
            return (REGION_CODE_FOR_NON_GEO_ENTITY.equals(str) || countryCode == getCountryCodeForValidRegion(str)) && getNumberTypeHelper(getNationalSignificantNumber(phoneNumber), metadataForRegionOrCallingCode) != PhoneNumberType.UNKNOWN;
        }
        return false;
    }

    public int maybeExtractCountryCode(String str, Phonemetadata.PhoneMetadata phoneMetadata, StringBuilder sb, boolean z8, Phonenumber.PhoneNumber phoneNumber) throws NumberParseException, NumberFormatException {
        if (str.length() == 0) {
            return 0;
        }
        StringBuilder sb2 = new StringBuilder(str);
        Phonenumber.PhoneNumber.CountryCodeSource countryCodeSourceMaybeStripInternationalPrefixAndNormalize = maybeStripInternationalPrefixAndNormalize(sb2, phoneMetadata != null ? phoneMetadata.internationalPrefix : "NonMatch");
        if (z8) {
            phoneNumber.setCountryCodeSource(countryCodeSourceMaybeStripInternationalPrefixAndNormalize);
        }
        if (countryCodeSourceMaybeStripInternationalPrefixAndNormalize != Phonenumber.PhoneNumber.CountryCodeSource.FROM_DEFAULT_COUNTRY) {
            if (sb2.length() <= 2) {
                throw new NumberParseException(NumberParseException.ErrorType.TOO_SHORT_AFTER_IDD, "Phone number had an IDD, but after this was not long enough to be a viable phone number.");
            }
            int iExtractCountryCode = extractCountryCode(sb2, sb);
            if (iExtractCountryCode == 0) {
                throw new NumberParseException(NumberParseException.ErrorType.INVALID_COUNTRY_CODE, "Country calling code supplied was not recognised.");
            }
            phoneNumber.setCountryCode(iExtractCountryCode);
            return iExtractCountryCode;
        }
        if (phoneMetadata != null) {
            int i9 = phoneMetadata.countryCode;
            String strValueOf = String.valueOf(i9);
            String string = sb2.toString();
            if (string.startsWith(strValueOf)) {
                StringBuilder sb3 = new StringBuilder(string.substring(strValueOf.length()));
                Phonemetadata.PhoneNumberDesc phoneNumberDesc = phoneMetadata.generalDesc;
                Pattern patternForRegex = this.regexCache.getPatternForRegex(phoneNumberDesc.nationalNumberPattern);
                maybeStripNationalPrefixAndCarrierCode(sb3, phoneMetadata, null);
                Pattern patternForRegex2 = this.regexCache.getPatternForRegex(phoneNumberDesc.possibleNumberPattern);
                if ((!patternForRegex.matcher(sb2).matches() && patternForRegex.matcher(sb3).matches()) || testNumberLengthAgainstPattern(patternForRegex2, sb2.toString()) == ValidationResult.TOO_LONG) {
                    sb.append((CharSequence) sb3);
                    if (z8) {
                        phoneNumber.setCountryCodeSource(Phonenumber.PhoneNumber.CountryCodeSource.FROM_NUMBER_WITHOUT_PLUS_SIGN);
                    }
                    phoneNumber.setCountryCode(i9);
                    return i9;
                }
            }
        }
        phoneNumber.setCountryCode(0);
        return 0;
    }

    public String maybeStripExtension(StringBuilder sb) {
        Matcher matcher = EXTN_PATTERN.matcher(sb);
        if (!matcher.find() || !isViablePhoneNumber(sb.substring(0, matcher.start()))) {
            return "";
        }
        int iGroupCount = matcher.groupCount();
        for (int i9 = 1; i9 <= iGroupCount; i9++) {
            if (matcher.group(i9) != null) {
                String strGroup = matcher.group(i9);
                sb.delete(matcher.start(), sb.length());
                return strGroup;
            }
        }
        return "";
    }

    public Phonenumber.PhoneNumber.CountryCodeSource maybeStripInternationalPrefixAndNormalize(StringBuilder sb, String str) {
        if (sb.length() == 0) {
            return Phonenumber.PhoneNumber.CountryCodeSource.FROM_DEFAULT_COUNTRY;
        }
        Matcher matcher = PLUS_CHARS_PATTERN.matcher(sb);
        if (matcher.lookingAt()) {
            sb.delete(0, matcher.end());
            normalize(sb);
            return Phonenumber.PhoneNumber.CountryCodeSource.FROM_NUMBER_WITH_PLUS_SIGN;
        }
        Pattern patternForRegex = this.regexCache.getPatternForRegex(str);
        normalize(sb);
        return parsePrefixAsIdd(patternForRegex, sb) ? Phonenumber.PhoneNumber.CountryCodeSource.FROM_NUMBER_WITH_IDD : Phonenumber.PhoneNumber.CountryCodeSource.FROM_DEFAULT_COUNTRY;
    }

    public boolean maybeStripNationalPrefixAndCarrierCode(StringBuilder sb, Phonemetadata.PhoneMetadata phoneMetadata, StringBuilder sb2) {
        int length = sb.length();
        String str = phoneMetadata.nationalPrefixForParsing;
        if (length != 0 && str.length() != 0) {
            Matcher matcher = this.regexCache.getPatternForRegex(str).matcher(sb);
            if (matcher.lookingAt()) {
                Pattern patternForRegex = this.regexCache.getPatternForRegex(phoneMetadata.generalDesc.nationalNumberPattern);
                boolean zMatches = patternForRegex.matcher(sb).matches();
                int iGroupCount = matcher.groupCount();
                String str2 = phoneMetadata.nationalPrefixTransformRule;
                if (str2 == null || str2.length() == 0 || matcher.group(iGroupCount) == null) {
                    if (zMatches && !patternForRegex.matcher(sb.substring(matcher.end())).matches()) {
                        return false;
                    }
                    if (sb2 != null && iGroupCount > 0 && matcher.group(iGroupCount) != null) {
                        sb2.append(matcher.group(1));
                    }
                    sb.delete(0, matcher.end());
                    return true;
                }
                StringBuilder sb3 = new StringBuilder(sb);
                sb3.replace(0, length, matcher.replaceFirst(str2));
                if (zMatches && !patternForRegex.matcher(sb3.toString()).matches()) {
                    return false;
                }
                if (sb2 != null && iGroupCount > 1) {
                    sb2.append(matcher.group(1));
                }
                sb.replace(0, sb.length(), sb3.toString());
                return true;
            }
        }
        return false;
    }

    public Phonenumber.PhoneNumber parse(String str, String str2) throws NumberParseException, NumberFormatException {
        Phonenumber.PhoneNumber phoneNumber = new Phonenumber.PhoneNumber();
        parse(str, str2, phoneNumber);
        return phoneNumber;
    }

    public Phonenumber.PhoneNumber parseAndKeepRawInput(String str, String str2) throws NumberParseException, NumberFormatException {
        Phonenumber.PhoneNumber phoneNumber = new Phonenumber.PhoneNumber();
        parseAndKeepRawInput(str, str2, phoneNumber);
        return phoneNumber;
    }

    public boolean truncateTooLongNumber(Phonenumber.PhoneNumber phoneNumber) {
        if (isValidNumber(phoneNumber)) {
            return true;
        }
        Phonenumber.PhoneNumber phoneNumber2 = new Phonenumber.PhoneNumber();
        phoneNumber2.mergeFrom(phoneNumber);
        long nationalNumber = phoneNumber.getNationalNumber();
        do {
            nationalNumber /= 10;
            phoneNumber2.setNationalNumber(nationalNumber);
            if (isPossibleNumberWithReason(phoneNumber2) == ValidationResult.TOO_SHORT || nationalNumber == 0) {
                return false;
            }
        } while (!isValidNumber(phoneNumber2));
        phoneNumber.setNationalNumber(nationalNumber);
        return true;
    }

    private String formatNsn(String str, Phonemetadata.PhoneMetadata phoneMetadata, PhoneNumberFormat phoneNumberFormat, String str2) {
        Phonemetadata.NumberFormat[] numberFormatArr = phoneMetadata.intlNumberFormat;
        if (numberFormatArr.length == 0 || phoneNumberFormat == PhoneNumberFormat.NATIONAL) {
            numberFormatArr = phoneMetadata.numberFormat;
        }
        Phonemetadata.NumberFormat numberFormatChooseFormattingPatternForNumber = chooseFormattingPatternForNumber(numberFormatArr, str);
        return numberFormatChooseFormattingPatternForNumber == null ? str : formatNsnUsingPattern(str, numberFormatChooseFormattingPatternForNumber, phoneNumberFormat, str2);
    }

    private String formatNsnUsingPattern(String str, Phonemetadata.NumberFormat numberFormat, PhoneNumberFormat phoneNumberFormat, String str2) {
        String strReplaceAll;
        String str3 = numberFormat.format;
        Matcher matcher = this.regexCache.getPatternForRegex(numberFormat.pattern).matcher(str);
        PhoneNumberFormat phoneNumberFormat2 = PhoneNumberFormat.NATIONAL;
        if (phoneNumberFormat != phoneNumberFormat2 || str2 == null || str2.length() <= 0 || numberFormat.domesticCarrierCodeFormattingRule.length() <= 0) {
            String str4 = numberFormat.nationalPrefixFormattingRule;
            strReplaceAll = (phoneNumberFormat != phoneNumberFormat2 || str4 == null || str4.length() <= 0) ? matcher.replaceAll(str3) : matcher.replaceAll(FIRST_GROUP_PATTERN.matcher(str3).replaceFirst(str4));
        } else {
            strReplaceAll = matcher.replaceAll(FIRST_GROUP_PATTERN.matcher(str3).replaceFirst(CC_PATTERN.matcher(numberFormat.domesticCarrierCodeFormattingRule).replaceFirst(str2)));
        }
        if (phoneNumberFormat != PhoneNumberFormat.RFC3966) {
            return strReplaceAll;
        }
        Matcher matcher2 = SEPARATOR_PATTERN.matcher(strReplaceAll);
        if (matcher2.lookingAt()) {
            strReplaceAll = matcher2.replaceFirst("");
        }
        return matcher2.reset(strReplaceAll).replaceAll("-");
    }

    public Iterable<PhoneNumberMatch> findNumbers(final CharSequence charSequence, final String str, final Leniency leniency, final long j9) {
        return new Iterable<PhoneNumberMatch>() { // from class: com.google.i18n.phonenumbers.PhoneNumberUtil.2
            @Override // java.lang.Iterable
            public Iterator<PhoneNumberMatch> iterator() {
                return new PhoneNumberMatcher(PhoneNumberUtil.this, charSequence, str, leniency, j9);
            }
        };
    }

    public boolean isPossibleNumber(String str, String str2) {
        try {
            return isPossibleNumber(parse(str, str2));
        } catch (NumberParseException unused) {
            return false;
        }
    }

    public static PhoneNumberUtil createInstance(MetadataLoader metadataLoader) {
        if (metadataLoader != null) {
            return createInstance(new MultiFileMetadataSourceImpl(metadataLoader));
        }
        throw new IllegalArgumentException("metadataLoader could not be null.");
    }

    public void parse(String str, String str2, Phonenumber.PhoneNumber phoneNumber) throws NumberParseException, NumberFormatException {
        parseHelper(str, str2, false, true, phoneNumber);
    }

    public void parseAndKeepRawInput(String str, String str2, Phonenumber.PhoneNumber phoneNumber) throws NumberParseException, NumberFormatException {
        parseHelper(str, str2, true, true, phoneNumber);
    }

    public static void normalize(StringBuilder sb) {
        sb.replace(0, sb.length(), normalize(sb.toString()));
    }

    public void format(Phonenumber.PhoneNumber phoneNumber, PhoneNumberFormat phoneNumberFormat, StringBuilder sb) {
        sb.setLength(0);
        int countryCode = phoneNumber.getCountryCode();
        String nationalSignificantNumber = getNationalSignificantNumber(phoneNumber);
        PhoneNumberFormat phoneNumberFormat2 = PhoneNumberFormat.E164;
        if (phoneNumberFormat == phoneNumberFormat2) {
            sb.append(nationalSignificantNumber);
            prefixNumberWithCountryCallingCode(countryCode, phoneNumberFormat2, sb);
        } else {
            if (!hasValidCountryCallingCode(countryCode)) {
                sb.append(nationalSignificantNumber);
                return;
            }
            Phonemetadata.PhoneMetadata metadataForRegionOrCallingCode = getMetadataForRegionOrCallingCode(countryCode, getRegionCodeForCountryCode(countryCode));
            sb.append(formatNsn(nationalSignificantNumber, metadataForRegionOrCallingCode, phoneNumberFormat));
            maybeAppendFormattedExtension(phoneNumber, metadataForRegionOrCallingCode, phoneNumberFormat, sb);
            prefixNumberWithCountryCallingCode(countryCode, phoneNumberFormat, sb);
        }
    }

    public Phonenumber.PhoneNumber getExampleNumberForType(PhoneNumberType phoneNumberType) {
        Iterator<String> it = getSupportedRegions().iterator();
        while (it.hasNext()) {
            Phonenumber.PhoneNumber exampleNumberForType = getExampleNumberForType(it.next(), phoneNumberType);
            if (exampleNumberForType != null) {
                return exampleNumberForType;
            }
        }
        Iterator<Integer> it2 = getSupportedGlobalNetworkCallingCodes().iterator();
        while (it2.hasNext()) {
            int iIntValue = it2.next().intValue();
            Phonemetadata.PhoneNumberDesc numberDescByType = getNumberDescByType(getMetadataForNonGeographicalRegion(iIntValue), phoneNumberType);
            try {
            } catch (NumberParseException e9) {
                logger.log(Level.SEVERE, e9.toString());
            }
            if (!numberDescByType.exampleNumber.equals("")) {
                String strValueOf = String.valueOf(numberDescByType.exampleNumber);
                StringBuilder sb = new StringBuilder(strValueOf.length() + 12);
                sb.append("+");
                sb.append(iIntValue);
                sb.append(strValueOf);
                return parse(sb.toString(), UNKNOWN_REGION);
            }
            continue;
        }
        return null;
    }

    public MatchType isNumberMatch(String str, String str2) throws NumberFormatException {
        try {
            return isNumberMatch(parse(str, UNKNOWN_REGION), str2);
        } catch (NumberParseException e9) {
            if (e9.getErrorType() == NumberParseException.ErrorType.INVALID_COUNTRY_CODE) {
                try {
                    return isNumberMatch(parse(str2, UNKNOWN_REGION), str);
                } catch (NumberParseException e10) {
                    if (e10.getErrorType() == NumberParseException.ErrorType.INVALID_COUNTRY_CODE) {
                        try {
                            Phonenumber.PhoneNumber phoneNumber = new Phonenumber.PhoneNumber();
                            Phonenumber.PhoneNumber phoneNumber2 = new Phonenumber.PhoneNumber();
                            parseHelper(str, null, false, false, phoneNumber);
                            parseHelper(str2, null, false, false, phoneNumber2);
                            return isNumberMatch(phoneNumber, phoneNumber2);
                        } catch (NumberParseException unused) {
                            return MatchType.NOT_A_NUMBER;
                        }
                    }
                    return MatchType.NOT_A_NUMBER;
                }
            }
            return MatchType.NOT_A_NUMBER;
        }
    }

    public MatchType isNumberMatch(Phonenumber.PhoneNumber phoneNumber, String str) throws NumberFormatException {
        try {
            return isNumberMatch(phoneNumber, parse(str, UNKNOWN_REGION));
        } catch (NumberParseException e9) {
            if (e9.getErrorType() == NumberParseException.ErrorType.INVALID_COUNTRY_CODE) {
                String regionCodeForCountryCode = getRegionCodeForCountryCode(phoneNumber.getCountryCode());
                try {
                    if (!regionCodeForCountryCode.equals(UNKNOWN_REGION)) {
                        MatchType matchTypeIsNumberMatch = isNumberMatch(phoneNumber, parse(str, regionCodeForCountryCode));
                        return matchTypeIsNumberMatch == MatchType.EXACT_MATCH ? MatchType.NSN_MATCH : matchTypeIsNumberMatch;
                    }
                    Phonenumber.PhoneNumber phoneNumber2 = new Phonenumber.PhoneNumber();
                    parseHelper(str, null, false, false, phoneNumber2);
                    return isNumberMatch(phoneNumber, phoneNumber2);
                } catch (NumberParseException unused) {
                    return MatchType.NOT_A_NUMBER;
                }
            }
            return MatchType.NOT_A_NUMBER;
        }
    }
}
