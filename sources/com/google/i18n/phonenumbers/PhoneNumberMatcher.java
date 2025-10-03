package com.google.i18n.phonenumbers;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.google.i18n.phonenumbers.nano.Phonemetadata;
import java.lang.Character;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes2.dex */
final class PhoneNumberMatcher implements Iterator<PhoneNumberMatch> {
    private static final Pattern LEAD_CLASS;
    private static final Pattern MATCHING_BRACKETS;
    private static final Pattern PATTERN;
    private final PhoneNumberUtil.Leniency leniency;
    private long maxTries;
    private final PhoneNumberUtil phoneUtil;
    private final String preferredRegion;
    private final CharSequence text;
    private static final Pattern PUB_PAGES = Pattern.compile("\\d{1,5}-+\\d{1,5}\\s{0,4}\\(\\d{1,4}");
    private static final Pattern SLASH_SEPARATED_DATES = Pattern.compile("(?:(?:[0-3]?\\d/[01]?\\d)|(?:[01]?\\d/[0-3]?\\d))/(?:[12]\\d)?\\d{2}");
    private static final Pattern TIME_STAMPS = Pattern.compile("[12]\\d{3}[-/]?[01]\\d[-/]?[0-3]\\d +[0-2]\\d$");
    private static final Pattern TIME_STAMPS_SUFFIX = Pattern.compile(":[0-5]\\d");
    private static final Pattern[] INNER_MATCHES = {Pattern.compile("/+(.*)"), Pattern.compile("(\\([^(]*)"), Pattern.compile("(?:\\p{Z}-|-\\p{Z})\\p{Z}*(.+)"), Pattern.compile("[‒-―－]\\p{Z}*(.+)"), Pattern.compile("\\.+\\p{Z}*([^.]+)"), Pattern.compile("\\p{Z}+(\\P{Z}+)")};
    private State state = State.NOT_READY;
    private PhoneNumberMatch lastMatch = null;
    private int searchIndex = 0;

    public interface NumberGroupingChecker {
        boolean checkGroups(PhoneNumberUtil phoneNumberUtil, Phonenumber.PhoneNumber phoneNumber, StringBuilder sb, String[] strArr);
    }

    public enum State {
        NOT_READY,
        READY,
        DONE
    }

    static {
        StringBuilder sb = new StringBuilder("(\\[（［".length() + 3 + ")\\]）］".length());
        sb.append("[^");
        sb.append("(\\[（［");
        sb.append(")\\]）］");
        sb.append("]");
        String string = sb.toString();
        String strLimit = limit(0, 3);
        String strValueOf = String.valueOf(string);
        String strValueOf2 = String.valueOf(string);
        String strValueOf3 = String.valueOf(string);
        String strValueOf4 = String.valueOf(strLimit);
        String strValueOf5 = String.valueOf(string);
        StringBuilder sb2 = new StringBuilder("(\\[（［".length() + 26 + strValueOf.length() + ")\\]）］".length() + strValueOf2.length() + "(\\[（［".length() + strValueOf3.length() + ")\\]）］".length() + strValueOf4.length() + strValueOf5.length());
        sb2.append("(?:[");
        sb2.append("(\\[（［");
        sb2.append("])?");
        sb2.append("(?:");
        sb2.append(strValueOf);
        sb2.append("+");
        sb2.append("[");
        sb2.append(")\\]）］");
        sb2.append("])?");
        sb2.append(strValueOf2);
        sb2.append("+");
        sb2.append("(?:[");
        sb2.append("(\\[（［");
        sb2.append("]");
        sb2.append(strValueOf3);
        sb2.append("+[");
        sb2.append(")\\]）］");
        sb2.append("])");
        sb2.append(strValueOf4);
        sb2.append(strValueOf5);
        sb2.append("*");
        MATCHING_BRACKETS = Pattern.compile(sb2.toString());
        String strLimit2 = limit(0, 2);
        String strLimit3 = limit(0, 4);
        String strLimit4 = limit(0, 20);
        String strValueOf6 = String.valueOf(strLimit3);
        String strConcat = strValueOf6.length() != 0 ? "[-x‐-―−ー－-／  \u00ad\u200b\u2060\u3000()（）［］.\\[\\]/~⁓∼～]".concat(strValueOf6) : new String("[-x‐-―−ー－-／  \u00ad\u200b\u2060\u3000()（）［］.\\[\\]/~⁓∼～]");
        String strValueOf7 = String.valueOf(limit(1, 20));
        String strConcat2 = strValueOf7.length() != 0 ? "\\p{Nd}".concat(strValueOf7) : new String("\\p{Nd}");
        String strValueOf8 = String.valueOf("+＋".length() != 0 ? "(\\[（［".concat("+＋") : new String("(\\[（［"));
        StringBuilder sb3 = new StringBuilder(strValueOf8.length() + 2);
        sb3.append("[");
        sb3.append(strValueOf8);
        sb3.append("]");
        String string2 = sb3.toString();
        LEAD_CLASS = Pattern.compile(string2);
        String strValueOf9 = String.valueOf(string2);
        String strValueOf10 = String.valueOf(strConcat);
        String strValueOf11 = String.valueOf(strLimit2);
        String strValueOf12 = String.valueOf(strConcat2);
        String strValueOf13 = String.valueOf(strConcat);
        String strValueOf14 = String.valueOf(strConcat2);
        String strValueOf15 = String.valueOf(strLimit4);
        String strValueOf16 = String.valueOf(PhoneNumberUtil.EXTN_PATTERNS_FOR_MATCHING);
        StringBuilder sb4 = new StringBuilder(strValueOf9.length() + 13 + strValueOf10.length() + strValueOf11.length() + strValueOf12.length() + strValueOf13.length() + strValueOf14.length() + strValueOf15.length() + strValueOf16.length());
        sb4.append("(?:");
        sb4.append(strValueOf9);
        sb4.append(strValueOf10);
        sb4.append(")");
        sb4.append(strValueOf11);
        sb4.append(strValueOf12);
        sb4.append("(?:");
        sb4.append(strValueOf13);
        sb4.append(strValueOf14);
        sb4.append(")");
        sb4.append(strValueOf15);
        sb4.append("(?:");
        sb4.append(strValueOf16);
        sb4.append(")?");
        PATTERN = Pattern.compile(sb4.toString(), 66);
    }

    public PhoneNumberMatcher(PhoneNumberUtil phoneNumberUtil, String str, String str2, PhoneNumberUtil.Leniency leniency, long j9) {
        if (phoneNumberUtil == null || leniency == null) {
            throw null;
        }
        if (j9 < 0) {
            throw new IllegalArgumentException();
        }
        this.phoneUtil = phoneNumberUtil;
        this.text = str == null ? "" : str;
        this.preferredRegion = str2;
        this.leniency = leniency;
        this.maxTries = j9;
    }

    public static boolean allNumberGroupsAreExactlyPresent(PhoneNumberUtil phoneNumberUtil, Phonenumber.PhoneNumber phoneNumber, StringBuilder sb, String[] strArr) {
        String[] strArrSplit = PhoneNumberUtil.NON_DIGITS_PATTERN.split(sb.toString());
        int length = phoneNumber.hasExtension() ? strArrSplit.length - 2 : strArrSplit.length - 1;
        if (strArrSplit.length == 1 || strArrSplit[length].contains(phoneNumberUtil.getNationalSignificantNumber(phoneNumber))) {
            return true;
        }
        int length2 = strArr.length - 1;
        while (length2 > 0 && length >= 0) {
            if (!strArrSplit[length].equals(strArr[length2])) {
                return false;
            }
            length2--;
            length--;
        }
        return length >= 0 && strArrSplit[length].endsWith(strArr[0]);
    }

    public static boolean allNumberGroupsRemainGrouped(PhoneNumberUtil phoneNumberUtil, Phonenumber.PhoneNumber phoneNumber, StringBuilder sb, String[] strArr) {
        int length;
        if (phoneNumber.getCountryCodeSource() != Phonenumber.PhoneNumber.CountryCodeSource.FROM_DEFAULT_COUNTRY) {
            String string = Integer.toString(phoneNumber.getCountryCode());
            length = sb.indexOf(string) + string.length();
        } else {
            length = 0;
        }
        for (int i9 = 0; i9 < strArr.length; i9++) {
            int iIndexOf = sb.indexOf(strArr[i9], length);
            if (iIndexOf < 0) {
                return false;
            }
            length = iIndexOf + strArr[i9].length();
            if (i9 == 0 && length < sb.length() && phoneNumberUtil.getNddPrefixForRegion(phoneNumberUtil.getRegionCodeForCountryCode(phoneNumber.getCountryCode()), true) != null && Character.isDigit(sb.charAt(length))) {
                return sb.substring(length - strArr[i9].length()).startsWith(phoneNumberUtil.getNationalSignificantNumber(phoneNumber));
            }
        }
        return sb.substring(length).contains(phoneNumber.getExtension());
    }

    public static boolean checkNumberGroupingIsValid(Phonenumber.PhoneNumber phoneNumber, String str, PhoneNumberUtil phoneNumberUtil, NumberGroupingChecker numberGroupingChecker) {
        StringBuilder sbNormalizeDigits = PhoneNumberUtil.normalizeDigits(str, true);
        if (numberGroupingChecker.checkGroups(phoneNumberUtil, phoneNumber, sbNormalizeDigits, getNationalNumberGroups(phoneNumberUtil, phoneNumber, null))) {
            return true;
        }
        Phonemetadata.PhoneMetadata alternateFormatsForCountry = MetadataManager.getAlternateFormatsForCountry(phoneNumber.getCountryCode());
        if (alternateFormatsForCountry != null) {
            for (Phonemetadata.NumberFormat numberFormat : alternateFormatsForCountry.numberFormat) {
                if (numberGroupingChecker.checkGroups(phoneNumberUtil, phoneNumber, sbNormalizeDigits, getNationalNumberGroups(phoneNumberUtil, phoneNumber, numberFormat))) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean containsMoreThanOneSlashInNationalNumber(Phonenumber.PhoneNumber phoneNumber, String str) {
        int iIndexOf;
        int iIndexOf2 = str.indexOf(47);
        if (iIndexOf2 < 0 || (iIndexOf = str.indexOf(47, iIndexOf2 + 1)) < 0) {
            return false;
        }
        if ((phoneNumber.getCountryCodeSource() == Phonenumber.PhoneNumber.CountryCodeSource.FROM_NUMBER_WITH_PLUS_SIGN || phoneNumber.getCountryCodeSource() == Phonenumber.PhoneNumber.CountryCodeSource.FROM_NUMBER_WITHOUT_PLUS_SIGN) && PhoneNumberUtil.normalizeDigitsOnly(str.substring(0, iIndexOf2)).equals(Integer.toString(phoneNumber.getCountryCode()))) {
            return str.substring(iIndexOf + 1).contains("/");
        }
        return true;
    }

    public static boolean containsOnlyValidXChars(Phonenumber.PhoneNumber phoneNumber, String str, PhoneNumberUtil phoneNumberUtil) {
        int i9 = 0;
        while (i9 < str.length() - 1) {
            char cCharAt = str.charAt(i9);
            if (cCharAt == 'x' || cCharAt == 'X') {
                int i10 = i9 + 1;
                char cCharAt2 = str.charAt(i10);
                if (cCharAt2 == 'x' || cCharAt2 == 'X') {
                    if (phoneNumberUtil.isNumberMatch(phoneNumber, str.substring(i10)) != PhoneNumberUtil.MatchType.NSN_MATCH) {
                        return false;
                    }
                    i9 = i10;
                } else if (!PhoneNumberUtil.normalizeDigitsOnly(str.substring(i9)).equals(phoneNumber.getExtension())) {
                    return false;
                }
            }
            i9++;
        }
        return true;
    }

    private PhoneNumberMatch extractInnerMatch(String str, int i9) throws NumberFormatException {
        for (Pattern pattern : INNER_MATCHES) {
            Matcher matcher = pattern.matcher(str);
            boolean z8 = true;
            while (matcher.find() && this.maxTries > 0) {
                if (z8) {
                    PhoneNumberMatch andVerify = parseAndVerify(trimAfterFirstMatch(PhoneNumberUtil.UNWANTED_END_CHAR_PATTERN, str.substring(0, matcher.start())).toString(), i9);
                    if (andVerify != null) {
                        return andVerify;
                    }
                    this.maxTries--;
                    z8 = false;
                }
                PhoneNumberMatch andVerify2 = parseAndVerify(trimAfterFirstMatch(PhoneNumberUtil.UNWANTED_END_CHAR_PATTERN, matcher.group(1)).toString(), matcher.start(1) + i9);
                if (andVerify2 != null) {
                    return andVerify2;
                }
                this.maxTries--;
            }
        }
        return null;
    }

    private PhoneNumberMatch extractMatch(CharSequence charSequence, int i9) throws NumberFormatException {
        if (SLASH_SEPARATED_DATES.matcher(charSequence).find()) {
            return null;
        }
        if (TIME_STAMPS.matcher(charSequence).find()) {
            if (TIME_STAMPS_SUFFIX.matcher(this.text.toString().substring(charSequence.length() + i9)).lookingAt()) {
                return null;
            }
        }
        String string = charSequence.toString();
        PhoneNumberMatch andVerify = parseAndVerify(string, i9);
        return andVerify != null ? andVerify : extractInnerMatch(string, i9);
    }

    private PhoneNumberMatch find(int i9) throws NumberFormatException {
        Matcher matcher = PATTERN.matcher(this.text);
        while (this.maxTries > 0 && matcher.find(i9)) {
            int iStart = matcher.start();
            CharSequence charSequenceTrimAfterFirstMatch = trimAfterFirstMatch(PhoneNumberUtil.SECOND_NUMBER_START_PATTERN, this.text.subSequence(iStart, matcher.end()));
            PhoneNumberMatch phoneNumberMatchExtractMatch = extractMatch(charSequenceTrimAfterFirstMatch, iStart);
            if (phoneNumberMatchExtractMatch != null) {
                return phoneNumberMatchExtractMatch;
            }
            i9 = iStart + charSequenceTrimAfterFirstMatch.length();
            this.maxTries--;
        }
        return null;
    }

    private static String[] getNationalNumberGroups(PhoneNumberUtil phoneNumberUtil, Phonenumber.PhoneNumber phoneNumber, Phonemetadata.NumberFormat numberFormat) {
        if (numberFormat != null) {
            return phoneNumberUtil.formatNsnUsingPattern(phoneNumberUtil.getNationalSignificantNumber(phoneNumber), numberFormat, PhoneNumberUtil.PhoneNumberFormat.RFC3966).split("-");
        }
        String str = phoneNumberUtil.format(phoneNumber, PhoneNumberUtil.PhoneNumberFormat.RFC3966);
        int iIndexOf = str.indexOf(59);
        if (iIndexOf < 0) {
            iIndexOf = str.length();
        }
        return str.substring(str.indexOf(45) + 1, iIndexOf).split("-");
    }

    private static boolean isInvalidPunctuationSymbol(char c9) {
        return c9 == '%' || Character.getType(c9) == 26;
    }

    public static boolean isLatinLetter(char c9) {
        if (!Character.isLetter(c9) && Character.getType(c9) != 6) {
            return false;
        }
        Character.UnicodeBlock unicodeBlockOf = Character.UnicodeBlock.of(c9);
        return unicodeBlockOf.equals(Character.UnicodeBlock.BASIC_LATIN) || unicodeBlockOf.equals(Character.UnicodeBlock.LATIN_1_SUPPLEMENT) || unicodeBlockOf.equals(Character.UnicodeBlock.LATIN_EXTENDED_A) || unicodeBlockOf.equals(Character.UnicodeBlock.LATIN_EXTENDED_ADDITIONAL) || unicodeBlockOf.equals(Character.UnicodeBlock.LATIN_EXTENDED_B) || unicodeBlockOf.equals(Character.UnicodeBlock.COMBINING_DIACRITICAL_MARKS);
    }

    public static boolean isNationalPrefixPresentIfRequired(Phonenumber.PhoneNumber phoneNumber, PhoneNumberUtil phoneNumberUtil) {
        Phonemetadata.PhoneMetadata metadataForRegion;
        if (phoneNumber.getCountryCodeSource() != Phonenumber.PhoneNumber.CountryCodeSource.FROM_DEFAULT_COUNTRY || (metadataForRegion = phoneNumberUtil.getMetadataForRegion(phoneNumberUtil.getRegionCodeForCountryCode(phoneNumber.getCountryCode()))) == null) {
            return true;
        }
        Phonemetadata.NumberFormat numberFormatChooseFormattingPatternForNumber = phoneNumberUtil.chooseFormattingPatternForNumber(metadataForRegion.numberFormat, phoneNumberUtil.getNationalSignificantNumber(phoneNumber));
        if (numberFormatChooseFormattingPatternForNumber == null || numberFormatChooseFormattingPatternForNumber.nationalPrefixFormattingRule.length() <= 0 || numberFormatChooseFormattingPatternForNumber.nationalPrefixOptionalWhenFormatting || PhoneNumberUtil.formattingRuleHasFirstGroupOnly(numberFormatChooseFormattingPatternForNumber.nationalPrefixFormattingRule)) {
            return true;
        }
        return phoneNumberUtil.maybeStripNationalPrefixAndCarrierCode(new StringBuilder(PhoneNumberUtil.normalizeDigitsOnly(phoneNumber.getRawInput())), metadataForRegion, null);
    }

    private static String limit(int i9, int i10) {
        if (i9 < 0 || i10 <= 0 || i10 < i9) {
            throw new IllegalArgumentException();
        }
        StringBuilder sb = new StringBuilder(25);
        sb.append("{");
        sb.append(i9);
        sb.append(",");
        sb.append(i10);
        sb.append("}");
        return sb.toString();
    }

    private PhoneNumberMatch parseAndVerify(String str, int i9) throws NumberFormatException {
        try {
            if (MATCHING_BRACKETS.matcher(str).matches() && !PUB_PAGES.matcher(str).find()) {
                if (this.leniency.compareTo(PhoneNumberUtil.Leniency.VALID) >= 0) {
                    if (i9 > 0 && !LEAD_CLASS.matcher(str).lookingAt()) {
                        char cCharAt = this.text.charAt(i9 - 1);
                        if (isInvalidPunctuationSymbol(cCharAt) || isLatinLetter(cCharAt)) {
                            return null;
                        }
                    }
                    int length = str.length() + i9;
                    if (length < this.text.length()) {
                        char cCharAt2 = this.text.charAt(length);
                        if (isInvalidPunctuationSymbol(cCharAt2) || isLatinLetter(cCharAt2)) {
                            return null;
                        }
                    }
                }
                Phonenumber.PhoneNumber andKeepRawInput = this.phoneUtil.parseAndKeepRawInput(str, this.preferredRegion);
                if ((!this.phoneUtil.getRegionCodeForCountryCode(andKeepRawInput.getCountryCode()).equals("IL") || this.phoneUtil.getNationalSignificantNumber(andKeepRawInput).length() != 4 || (i9 != 0 && (i9 <= 0 || this.text.charAt(i9 - 1) == '*'))) && this.leniency.verify(andKeepRawInput, str, this.phoneUtil)) {
                    andKeepRawInput.clearCountryCodeSource();
                    andKeepRawInput.clearRawInput();
                    andKeepRawInput.clearPreferredDomesticCarrierCode();
                    return new PhoneNumberMatch(i9, str, andKeepRawInput);
                }
            }
        } catch (NumberParseException unused) {
        }
        return null;
    }

    private static CharSequence trimAfterFirstMatch(Pattern pattern, CharSequence charSequence) {
        Matcher matcher = pattern.matcher(charSequence);
        return matcher.find() ? charSequence.subSequence(0, matcher.start()) : charSequence;
    }

    @Override // java.util.Iterator
    public boolean hasNext() throws NumberFormatException {
        if (this.state == State.NOT_READY) {
            PhoneNumberMatch phoneNumberMatchFind = find(this.searchIndex);
            this.lastMatch = phoneNumberMatchFind;
            if (phoneNumberMatchFind == null) {
                this.state = State.DONE;
            } else {
                this.searchIndex = phoneNumberMatchFind.end();
                this.state = State.READY;
            }
        }
        return this.state == State.READY;
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Iterator
    public PhoneNumberMatch next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        PhoneNumberMatch phoneNumberMatch = this.lastMatch;
        this.lastMatch = null;
        this.state = State.NOT_READY;
        return phoneNumberMatch;
    }
}
