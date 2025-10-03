package com.google.i18n.phonenumbers;

import com.google.i18n.phonenumbers.nano.Phonemetadata;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes2.dex */
public class AsYouTypeFormatter {
    private static final Pattern CHARACTER_CLASS_PATTERN;
    private static final Pattern DIGIT_PATTERN;
    private static final String DIGIT_PLACEHOLDER = "\u2008";
    private static final Pattern ELIGIBLE_FORMAT_PATTERN;
    private static final Phonemetadata.PhoneMetadata EMPTY_METADATA;
    private static final int MIN_LEADING_DIGITS_LENGTH = 3;
    private static final Pattern NATIONAL_PREFIX_SEPARATORS_PATTERN;
    private static final char SEPARATOR_BEFORE_NATIONAL_NUMBER = ' ';
    private static final Pattern STANDALONE_DIGIT_PATTERN;
    private Phonemetadata.PhoneMetadata currentMetadata;
    private String defaultCountry;
    private Phonemetadata.PhoneMetadata defaultMetadata;
    private String currentOutput = "";
    private StringBuilder formattingTemplate = new StringBuilder();
    private String currentFormattingPattern = "";
    private StringBuilder accruedInput = new StringBuilder();
    private StringBuilder accruedInputWithoutFormatting = new StringBuilder();
    private boolean ableToFormat = true;
    private boolean inputHasFormatting = false;
    private boolean isCompleteNumber = false;
    private boolean isExpectingCountryCallingCode = false;
    private final PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
    private int lastMatchPosition = 0;
    private int originalPosition = 0;
    private int positionToRemember = 0;
    private StringBuilder prefixBeforeNationalNumber = new StringBuilder();
    private boolean shouldAddSpaceAfterNationalPrefix = false;
    private String extractedNationalPrefix = "";
    private StringBuilder nationalNumber = new StringBuilder();
    private List<Phonemetadata.NumberFormat> possibleFormats = new ArrayList();
    private RegexCache regexCache = new RegexCache(64);

    static {
        Phonemetadata.PhoneMetadata phoneMetadata = new Phonemetadata.PhoneMetadata();
        EMPTY_METADATA = phoneMetadata;
        phoneMetadata.internationalPrefix = "NA";
        CHARACTER_CLASS_PATTERN = Pattern.compile("\\[([^\\[\\]])*\\]");
        STANDALONE_DIGIT_PATTERN = Pattern.compile("\\d(?=[^,}][^,}])");
        ELIGIBLE_FORMAT_PATTERN = Pattern.compile("[-x‐-―−ー－-／  \u00ad\u200b\u2060\u3000()（）［］.\\[\\]/~⁓∼～]*(\\$\\d[-x‐-―−ー－-／  \u00ad\u200b\u2060\u3000()（）［］.\\[\\]/~⁓∼～]*)+");
        NATIONAL_PREFIX_SEPARATORS_PATTERN = Pattern.compile("[- ]");
        DIGIT_PATTERN = Pattern.compile(DIGIT_PLACEHOLDER);
    }

    public AsYouTypeFormatter(String str) {
        this.defaultCountry = str;
        Phonemetadata.PhoneMetadata metadataForRegion = getMetadataForRegion(str);
        this.currentMetadata = metadataForRegion;
        this.defaultMetadata = metadataForRegion;
    }

    private boolean ableToExtractLongerNdd() {
        if (this.extractedNationalPrefix.length() > 0) {
            this.nationalNumber.insert(0, this.extractedNationalPrefix);
            this.prefixBeforeNationalNumber.setLength(this.prefixBeforeNationalNumber.lastIndexOf(this.extractedNationalPrefix));
        }
        return !this.extractedNationalPrefix.equals(removeNationalPrefixFromNationalNumber());
    }

    private String appendNationalNumber(String str) {
        int length = this.prefixBeforeNationalNumber.length();
        if (!this.shouldAddSpaceAfterNationalPrefix || length <= 0 || this.prefixBeforeNationalNumber.charAt(length - 1) == ' ') {
            String strValueOf = String.valueOf(this.prefixBeforeNationalNumber);
            String strValueOf2 = String.valueOf(str);
            StringBuilder sb = new StringBuilder(strValueOf.length() + 0 + strValueOf2.length());
            sb.append(strValueOf);
            sb.append(strValueOf2);
            return sb.toString();
        }
        String str2 = new String(this.prefixBeforeNationalNumber);
        String strValueOf3 = String.valueOf(str);
        StringBuilder sb2 = new StringBuilder(str2.length() + 1 + strValueOf3.length());
        sb2.append(str2);
        sb2.append(SEPARATOR_BEFORE_NATIONAL_NUMBER);
        sb2.append(strValueOf3);
        return sb2.toString();
    }

    private String attemptToChooseFormattingPattern() {
        if (this.nationalNumber.length() < 3) {
            return appendNationalNumber(this.nationalNumber.toString());
        }
        getAvailableFormats(this.nationalNumber.toString());
        String strAttemptToFormatAccruedDigits = attemptToFormatAccruedDigits();
        return strAttemptToFormatAccruedDigits.length() > 0 ? strAttemptToFormatAccruedDigits : maybeCreateNewTemplate() ? inputAccruedNationalNumber() : this.accruedInput.toString();
    }

    private String attemptToChoosePatternWithPrefixExtracted() {
        this.ableToFormat = true;
        this.isExpectingCountryCallingCode = false;
        this.possibleFormats.clear();
        this.lastMatchPosition = 0;
        this.formattingTemplate.setLength(0);
        this.currentFormattingPattern = "";
        return attemptToChooseFormattingPattern();
    }

    private boolean attemptToExtractCountryCallingCode() {
        StringBuilder sb;
        int iExtractCountryCode;
        if (this.nationalNumber.length() == 0 || (iExtractCountryCode = this.phoneUtil.extractCountryCode(this.nationalNumber, (sb = new StringBuilder()))) == 0) {
            return false;
        }
        this.nationalNumber.setLength(0);
        this.nationalNumber.append((CharSequence) sb);
        String regionCodeForCountryCode = this.phoneUtil.getRegionCodeForCountryCode(iExtractCountryCode);
        if (PhoneNumberUtil.REGION_CODE_FOR_NON_GEO_ENTITY.equals(regionCodeForCountryCode)) {
            this.currentMetadata = this.phoneUtil.getMetadataForNonGeographicalRegion(iExtractCountryCode);
        } else if (!regionCodeForCountryCode.equals(this.defaultCountry)) {
            this.currentMetadata = getMetadataForRegion(regionCodeForCountryCode);
        }
        String string = Integer.toString(iExtractCountryCode);
        StringBuilder sb2 = this.prefixBeforeNationalNumber;
        sb2.append(string);
        sb2.append(SEPARATOR_BEFORE_NATIONAL_NUMBER);
        this.extractedNationalPrefix = "";
        return true;
    }

    private boolean attemptToExtractIdd() {
        RegexCache regexCache = this.regexCache;
        String strValueOf = String.valueOf(this.currentMetadata.internationalPrefix);
        Matcher matcher = regexCache.getPatternForRegex(strValueOf.length() != 0 ? "\\+|".concat(strValueOf) : new String("\\+|")).matcher(this.accruedInputWithoutFormatting);
        if (!matcher.lookingAt()) {
            return false;
        }
        this.isCompleteNumber = true;
        int iEnd = matcher.end();
        this.nationalNumber.setLength(0);
        this.nationalNumber.append(this.accruedInputWithoutFormatting.substring(iEnd));
        this.prefixBeforeNationalNumber.setLength(0);
        this.prefixBeforeNationalNumber.append(this.accruedInputWithoutFormatting.substring(0, iEnd));
        if (this.accruedInputWithoutFormatting.charAt(0) != '+') {
            this.prefixBeforeNationalNumber.append(SEPARATOR_BEFORE_NATIONAL_NUMBER);
        }
        return true;
    }

    private boolean createFormattingTemplate(Phonemetadata.NumberFormat numberFormat) {
        String str = numberFormat.pattern;
        if (str.indexOf(124) != -1) {
            return false;
        }
        String strReplaceAll = STANDALONE_DIGIT_PATTERN.matcher(CHARACTER_CLASS_PATTERN.matcher(str).replaceAll("\\\\d")).replaceAll("\\\\d");
        this.formattingTemplate.setLength(0);
        String formattingTemplate = getFormattingTemplate(strReplaceAll, numberFormat.format);
        if (formattingTemplate.length() <= 0) {
            return false;
        }
        this.formattingTemplate.append(formattingTemplate);
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x000c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void getAvailableFormats(String str) {
        Phonemetadata.NumberFormat[] numberFormatArr;
        if (this.isCompleteNumber) {
            numberFormatArr = this.currentMetadata.intlNumberFormat;
            if (numberFormatArr.length <= 0) {
                numberFormatArr = this.currentMetadata.numberFormat;
            }
        }
        boolean z8 = !this.currentMetadata.nationalPrefix.equals("");
        for (Phonemetadata.NumberFormat numberFormat : numberFormatArr) {
            if ((!z8 || this.isCompleteNumber || numberFormat.nationalPrefixOptionalWhenFormatting || PhoneNumberUtil.formattingRuleHasFirstGroupOnly(numberFormat.nationalPrefixFormattingRule)) && isFormatEligible(numberFormat.format)) {
                this.possibleFormats.add(numberFormat);
            }
        }
        narrowDownPossibleFormats(str);
    }

    private String getFormattingTemplate(String str, String str2) {
        Matcher matcher = this.regexCache.getPatternForRegex(str).matcher("999999999999999");
        matcher.find();
        String strGroup = matcher.group();
        return strGroup.length() < this.nationalNumber.length() ? "" : strGroup.replaceAll(str, str2).replaceAll("9", DIGIT_PLACEHOLDER);
    }

    private Phonemetadata.PhoneMetadata getMetadataForRegion(String str) {
        Phonemetadata.PhoneMetadata metadataForRegion = this.phoneUtil.getMetadataForRegion(this.phoneUtil.getRegionCodeForCountryCode(this.phoneUtil.getCountryCodeForRegion(str)));
        return metadataForRegion != null ? metadataForRegion : EMPTY_METADATA;
    }

    private String inputAccruedNationalNumber() {
        int length = this.nationalNumber.length();
        if (length <= 0) {
            return this.prefixBeforeNationalNumber.toString();
        }
        String strInputDigitHelper = "";
        for (int i9 = 0; i9 < length; i9++) {
            strInputDigitHelper = inputDigitHelper(this.nationalNumber.charAt(i9));
        }
        return this.ableToFormat ? appendNationalNumber(strInputDigitHelper) : this.accruedInput.toString();
    }

    private String inputDigitHelper(char c9) {
        Matcher matcher = DIGIT_PATTERN.matcher(this.formattingTemplate);
        if (!matcher.find(this.lastMatchPosition)) {
            if (this.possibleFormats.size() == 1) {
                this.ableToFormat = false;
            }
            this.currentFormattingPattern = "";
            return this.accruedInput.toString();
        }
        String strReplaceFirst = matcher.replaceFirst(Character.toString(c9));
        this.formattingTemplate.replace(0, strReplaceFirst.length(), strReplaceFirst);
        int iStart = matcher.start();
        this.lastMatchPosition = iStart;
        return this.formattingTemplate.substring(0, iStart + 1);
    }

    private String inputDigitWithOptionToRememberPosition(char c9, boolean z8) {
        this.accruedInput.append(c9);
        if (z8) {
            this.originalPosition = this.accruedInput.length();
        }
        if (isDigitOrLeadingPlusSign(c9)) {
            c9 = normalizeAndAccrueDigitsAndPlusSign(c9, z8);
        } else {
            this.ableToFormat = false;
            this.inputHasFormatting = true;
        }
        if (!this.ableToFormat) {
            if (this.inputHasFormatting) {
                return this.accruedInput.toString();
            }
            if (attemptToExtractIdd()) {
                if (attemptToExtractCountryCallingCode()) {
                    return attemptToChoosePatternWithPrefixExtracted();
                }
            } else if (ableToExtractLongerNdd()) {
                this.prefixBeforeNationalNumber.append(SEPARATOR_BEFORE_NATIONAL_NUMBER);
                return attemptToChoosePatternWithPrefixExtracted();
            }
            return this.accruedInput.toString();
        }
        int length = this.accruedInputWithoutFormatting.length();
        if (length == 0 || length == 1 || length == 2) {
            return this.accruedInput.toString();
        }
        if (length == 3) {
            if (!attemptToExtractIdd()) {
                this.extractedNationalPrefix = removeNationalPrefixFromNationalNumber();
                return attemptToChooseFormattingPattern();
            }
            this.isExpectingCountryCallingCode = true;
        }
        if (this.isExpectingCountryCallingCode) {
            if (attemptToExtractCountryCallingCode()) {
                this.isExpectingCountryCallingCode = false;
            }
            String strValueOf = String.valueOf(this.prefixBeforeNationalNumber);
            String strValueOf2 = String.valueOf(this.nationalNumber.toString());
            StringBuilder sb = new StringBuilder(strValueOf.length() + 0 + strValueOf2.length());
            sb.append(strValueOf);
            sb.append(strValueOf2);
            return sb.toString();
        }
        if (this.possibleFormats.size() <= 0) {
            return attemptToChooseFormattingPattern();
        }
        String strInputDigitHelper = inputDigitHelper(c9);
        String strAttemptToFormatAccruedDigits = attemptToFormatAccruedDigits();
        if (strAttemptToFormatAccruedDigits.length() > 0) {
            return strAttemptToFormatAccruedDigits;
        }
        narrowDownPossibleFormats(this.nationalNumber.toString());
        return maybeCreateNewTemplate() ? inputAccruedNationalNumber() : this.ableToFormat ? appendNationalNumber(strInputDigitHelper) : this.accruedInput.toString();
    }

    private boolean isDigitOrLeadingPlusSign(char c9) {
        if (Character.isDigit(c9)) {
            return true;
        }
        return this.accruedInput.length() == 1 && PhoneNumberUtil.PLUS_CHARS_PATTERN.matcher(Character.toString(c9)).matches();
    }

    private boolean isFormatEligible(String str) {
        return ELIGIBLE_FORMAT_PATTERN.matcher(str).matches();
    }

    private boolean isNanpaNumberWithNationalPrefix() {
        return this.currentMetadata.countryCode == 1 && this.nationalNumber.charAt(0) == '1' && this.nationalNumber.charAt(1) != '0' && this.nationalNumber.charAt(1) != '1';
    }

    private boolean maybeCreateNewTemplate() {
        Iterator<Phonemetadata.NumberFormat> it = this.possibleFormats.iterator();
        while (it.hasNext()) {
            Phonemetadata.NumberFormat next = it.next();
            String str = next.pattern;
            if (this.currentFormattingPattern.equals(str)) {
                return false;
            }
            if (createFormattingTemplate(next)) {
                this.currentFormattingPattern = str;
                this.shouldAddSpaceAfterNationalPrefix = NATIONAL_PREFIX_SEPARATORS_PATTERN.matcher(next.nationalPrefixFormattingRule).find();
                this.lastMatchPosition = 0;
                return true;
            }
            it.remove();
        }
        this.ableToFormat = false;
        return false;
    }

    private void narrowDownPossibleFormats(String str) {
        int length = str.length() - 3;
        Iterator<Phonemetadata.NumberFormat> it = this.possibleFormats.iterator();
        while (it.hasNext()) {
            Phonemetadata.NumberFormat next = it.next();
            if (next.leadingDigitsPattern.length != 0) {
                if (!this.regexCache.getPatternForRegex(next.leadingDigitsPattern[Math.min(length, r3.length - 1)]).matcher(str).lookingAt()) {
                    it.remove();
                }
            }
        }
    }

    private char normalizeAndAccrueDigitsAndPlusSign(char c9, boolean z8) {
        if (c9 == '+') {
            this.accruedInputWithoutFormatting.append(c9);
        } else {
            c9 = Character.forDigit(Character.digit(c9, 10), 10);
            this.accruedInputWithoutFormatting.append(c9);
            this.nationalNumber.append(c9);
        }
        if (z8) {
            this.positionToRemember = this.accruedInputWithoutFormatting.length();
        }
        return c9;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0051  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private String removeNationalPrefixFromNationalNumber() {
        int iEnd = 1;
        if (isNanpaNumberWithNationalPrefix()) {
            StringBuilder sb = this.prefixBeforeNationalNumber;
            sb.append('1');
            sb.append(SEPARATOR_BEFORE_NATIONAL_NUMBER);
            this.isCompleteNumber = true;
        } else if (!this.currentMetadata.nationalPrefixForParsing.equals("")) {
            Matcher matcher = this.regexCache.getPatternForRegex(this.currentMetadata.nationalPrefixForParsing).matcher(this.nationalNumber);
            if (!matcher.lookingAt() || matcher.end() <= 0) {
                iEnd = 0;
            } else {
                this.isCompleteNumber = true;
                iEnd = matcher.end();
                this.prefixBeforeNationalNumber.append(this.nationalNumber.substring(0, iEnd));
            }
        }
        String strSubstring = this.nationalNumber.substring(0, iEnd);
        this.nationalNumber.delete(0, iEnd);
        return strSubstring;
    }

    public String attemptToFormatAccruedDigits() {
        for (Phonemetadata.NumberFormat numberFormat : this.possibleFormats) {
            Matcher matcher = this.regexCache.getPatternForRegex(numberFormat.pattern).matcher(this.nationalNumber);
            if (matcher.matches()) {
                this.shouldAddSpaceAfterNationalPrefix = NATIONAL_PREFIX_SEPARATORS_PATTERN.matcher(numberFormat.nationalPrefixFormattingRule).find();
                return appendNationalNumber(matcher.replaceAll(numberFormat.format));
            }
        }
        return "";
    }

    public void clear() {
        this.currentOutput = "";
        this.accruedInput.setLength(0);
        this.accruedInputWithoutFormatting.setLength(0);
        this.formattingTemplate.setLength(0);
        this.lastMatchPosition = 0;
        this.currentFormattingPattern = "";
        this.prefixBeforeNationalNumber.setLength(0);
        this.extractedNationalPrefix = "";
        this.nationalNumber.setLength(0);
        this.ableToFormat = true;
        this.inputHasFormatting = false;
        this.positionToRemember = 0;
        this.originalPosition = 0;
        this.isCompleteNumber = false;
        this.isExpectingCountryCallingCode = false;
        this.possibleFormats.clear();
        this.shouldAddSpaceAfterNationalPrefix = false;
        if (this.currentMetadata.equals(this.defaultMetadata)) {
            return;
        }
        this.currentMetadata = getMetadataForRegion(this.defaultCountry);
    }

    public String getExtractedNationalPrefix() {
        return this.extractedNationalPrefix;
    }

    public int getRememberedPosition() {
        if (!this.ableToFormat) {
            return this.originalPosition;
        }
        int i9 = 0;
        int i10 = 0;
        while (i9 < this.positionToRemember && i10 < this.currentOutput.length()) {
            if (this.accruedInputWithoutFormatting.charAt(i9) == this.currentOutput.charAt(i10)) {
                i9++;
            }
            i10++;
        }
        return i10;
    }

    public String inputDigit(char c9) {
        String strInputDigitWithOptionToRememberPosition = inputDigitWithOptionToRememberPosition(c9, false);
        this.currentOutput = strInputDigitWithOptionToRememberPosition;
        return strInputDigitWithOptionToRememberPosition;
    }

    public String inputDigitAndRememberPosition(char c9) {
        String strInputDigitWithOptionToRememberPosition = inputDigitWithOptionToRememberPosition(c9, true);
        this.currentOutput = strInputDigitWithOptionToRememberPosition;
        return strInputDigitWithOptionToRememberPosition;
    }
}
