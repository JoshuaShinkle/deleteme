package com.google.i18n.phonenumbers;

import com.google.i18n.phonenumbers.Phonenumber;
import com.google.i18n.phonenumbers.internal.MatcherApi;
import com.google.i18n.phonenumbers.internal.RegexBasedMatcher;
import com.google.i18n.phonenumbers.nano.Phonemetadata;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes2.dex */
public class ShortNumberInfo {
    private static final Set<String> REGIONS_WHERE_EMERGENCY_NUMBERS_MUST_BE_EXACT;
    private final Map<Integer, List<String>> countryCallingCodeToRegionCodeMap = CountryCodeToRegionCodeMap.getCountryCodeToRegionCodeMap();
    private final MatcherApi matcherApi;
    private static final Logger logger = Logger.getLogger(ShortNumberInfo.class.getName());
    private static final ShortNumberInfo INSTANCE = new ShortNumberInfo(RegexBasedMatcher.create());

    /* renamed from: com.google.i18n.phonenumbers.ShortNumberInfo$1 */
    public static /* synthetic */ class C43161 {

        /* renamed from: $SwitchMap$com$google$i18n$phonenumbers$ShortNumberInfo$ShortNumberCost */
        static final /* synthetic */ int[] f15594xe1b2aad7;

        static {
            int[] iArr = new int[ShortNumberCost.values().length];
            f15594xe1b2aad7 = iArr;
            try {
                iArr[ShortNumberCost.PREMIUM_RATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f15594xe1b2aad7[ShortNumberCost.UNKNOWN_COST.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f15594xe1b2aad7[ShortNumberCost.STANDARD_RATE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f15594xe1b2aad7[ShortNumberCost.TOLL_FREE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public enum ShortNumberCost {
        TOLL_FREE,
        STANDARD_RATE,
        PREMIUM_RATE,
        UNKNOWN_COST
    }

    static {
        HashSet hashSet = new HashSet();
        REGIONS_WHERE_EMERGENCY_NUMBERS_MUST_BE_EXACT = hashSet;
        hashSet.add("BR");
        hashSet.add("CL");
        hashSet.add("NI");
    }

    public ShortNumberInfo(MatcherApi matcherApi) {
        this.matcherApi = matcherApi;
    }

    public static ShortNumberInfo getInstance() {
        return INSTANCE;
    }

    private static String getNationalSignificantNumber(Phonenumber.PhoneNumber phoneNumber) {
        StringBuilder sb = new StringBuilder();
        if (phoneNumber.isItalianLeadingZero()) {
            char[] cArr = new char[phoneNumber.getNumberOfLeadingZeros()];
            Arrays.fill(cArr, '0');
            sb.append(new String(cArr));
        }
        sb.append(phoneNumber.getNationalNumber());
        return sb.toString();
    }

    private String getRegionCodeForShortNumberFromRegionList(Phonenumber.PhoneNumber phoneNumber, List<String> list) {
        if (list.size() == 0) {
            return null;
        }
        if (list.size() == 1) {
            return list.get(0);
        }
        String nationalSignificantNumber = getNationalSignificantNumber(phoneNumber);
        for (String str : list) {
            Phonemetadata.PhoneMetadata shortNumberMetadataForRegion = MetadataManager.getShortNumberMetadataForRegion(str);
            if (shortNumberMetadataForRegion != null && matchesPossibleNumberAndNationalNumber(nationalSignificantNumber, shortNumberMetadataForRegion.shortCode)) {
                return str;
            }
        }
        return null;
    }

    private List<String> getRegionCodesForCountryCode(int i9) {
        List<String> arrayList = this.countryCallingCodeToRegionCodeMap.get(Integer.valueOf(i9));
        if (arrayList == null) {
            arrayList = new ArrayList<>(0);
        }
        return Collections.unmodifiableList(arrayList);
    }

    private boolean matchesEmergencyNumberHelper(String str, String str2, boolean z8) {
        Phonemetadata.PhoneMetadata shortNumberMetadataForRegion;
        String strExtractPossibleNumber = PhoneNumberUtil.extractPossibleNumber(str);
        boolean z9 = false;
        if (PhoneNumberUtil.PLUS_CHARS_PATTERN.matcher(strExtractPossibleNumber).lookingAt() || (shortNumberMetadataForRegion = MetadataManager.getShortNumberMetadataForRegion(str2)) == null || shortNumberMetadataForRegion.emergency == null) {
            return false;
        }
        String strNormalizeDigitsOnly = PhoneNumberUtil.normalizeDigitsOnly(strExtractPossibleNumber);
        Phonemetadata.PhoneNumberDesc phoneNumberDesc = shortNumberMetadataForRegion.emergency;
        if (z8 && !REGIONS_WHERE_EMERGENCY_NUMBERS_MUST_BE_EXACT.contains(str2)) {
            z9 = true;
        }
        return this.matcherApi.matchesNationalNumber(strNormalizeDigitsOnly, phoneNumberDesc, z9);
    }

    private boolean matchesPossibleNumberAndNationalNumber(String str, Phonemetadata.PhoneNumberDesc phoneNumberDesc) {
        return this.matcherApi.matchesPossibleNumber(str, phoneNumberDesc) && this.matcherApi.matchesNationalNumber(str, phoneNumberDesc, false);
    }

    private boolean regionDialingFromMatchesNumber(Phonenumber.PhoneNumber phoneNumber, String str) {
        return getRegionCodesForCountryCode(phoneNumber.getCountryCode()).contains(str);
    }

    public boolean connectsToEmergencyNumber(String str, String str2) {
        return matchesEmergencyNumberHelper(str, str2, true);
    }

    public String getExampleShortNumber(String str) {
        Phonemetadata.PhoneMetadata shortNumberMetadataForRegion = MetadataManager.getShortNumberMetadataForRegion(str);
        if (shortNumberMetadataForRegion == null) {
            return "";
        }
        Phonemetadata.PhoneNumberDesc phoneNumberDesc = shortNumberMetadataForRegion.shortCode;
        return !phoneNumberDesc.exampleNumber.equals("") ? phoneNumberDesc.exampleNumber : "";
    }

    public String getExampleShortNumberForCost(String str, ShortNumberCost shortNumberCost) {
        Phonemetadata.PhoneMetadata shortNumberMetadataForRegion = MetadataManager.getShortNumberMetadataForRegion(str);
        if (shortNumberMetadataForRegion == null) {
            return "";
        }
        int i9 = C43161.f15594xe1b2aad7[shortNumberCost.ordinal()];
        Phonemetadata.PhoneNumberDesc phoneNumberDesc = i9 != 1 ? i9 != 3 ? i9 != 4 ? null : shortNumberMetadataForRegion.tollFree : shortNumberMetadataForRegion.standardRate : shortNumberMetadataForRegion.premiumRate;
        return (phoneNumberDesc == null || phoneNumberDesc.exampleNumber.equals("")) ? "" : phoneNumberDesc.exampleNumber;
    }

    public ShortNumberCost getExpectedCost(Phonenumber.PhoneNumber phoneNumber) {
        List<String> regionCodesForCountryCode = getRegionCodesForCountryCode(phoneNumber.getCountryCode());
        if (regionCodesForCountryCode.size() == 0) {
            return ShortNumberCost.UNKNOWN_COST;
        }
        if (regionCodesForCountryCode.size() == 1) {
            return getExpectedCostForRegion(phoneNumber, regionCodesForCountryCode.get(0));
        }
        ShortNumberCost shortNumberCost = ShortNumberCost.TOLL_FREE;
        Iterator<String> it = regionCodesForCountryCode.iterator();
        while (it.hasNext()) {
            ShortNumberCost expectedCostForRegion = getExpectedCostForRegion(phoneNumber, it.next());
            int i9 = C43161.f15594xe1b2aad7[expectedCostForRegion.ordinal()];
            if (i9 == 1) {
                return ShortNumberCost.PREMIUM_RATE;
            }
            if (i9 == 2) {
                shortNumberCost = ShortNumberCost.UNKNOWN_COST;
            } else if (i9 != 3) {
                if (i9 != 4) {
                    Logger logger2 = logger;
                    Level level = Level.SEVERE;
                    String strValueOf = String.valueOf(expectedCostForRegion);
                    StringBuilder sb = new StringBuilder(strValueOf.length() + 30);
                    sb.append("Unrecognised cost for region: ");
                    sb.append(strValueOf);
                    logger2.log(level, sb.toString());
                }
            } else if (shortNumberCost != ShortNumberCost.UNKNOWN_COST) {
                shortNumberCost = ShortNumberCost.STANDARD_RATE;
            }
        }
        return shortNumberCost;
    }

    @Deprecated
    public ShortNumberCost getExpectedCostForRegion(String str, String str2) {
        Phonemetadata.PhoneMetadata shortNumberMetadataForRegion = MetadataManager.getShortNumberMetadataForRegion(str2);
        return shortNumberMetadataForRegion == null ? ShortNumberCost.UNKNOWN_COST : matchesPossibleNumberAndNationalNumber(str, shortNumberMetadataForRegion.premiumRate) ? ShortNumberCost.PREMIUM_RATE : matchesPossibleNumberAndNationalNumber(str, shortNumberMetadataForRegion.standardRate) ? ShortNumberCost.STANDARD_RATE : matchesPossibleNumberAndNationalNumber(str, shortNumberMetadataForRegion.tollFree) ? ShortNumberCost.TOLL_FREE : isEmergencyNumber(str, str2) ? ShortNumberCost.TOLL_FREE : ShortNumberCost.UNKNOWN_COST;
    }

    public Set<String> getSupportedRegions() {
        return Collections.unmodifiableSet(MetadataManager.getShortNumberMetadataSupportedRegions());
    }

    public boolean isCarrierSpecific(Phonenumber.PhoneNumber phoneNumber) {
        String regionCodeForShortNumberFromRegionList = getRegionCodeForShortNumberFromRegionList(phoneNumber, getRegionCodesForCountryCode(phoneNumber.getCountryCode()));
        String nationalSignificantNumber = getNationalSignificantNumber(phoneNumber);
        Phonemetadata.PhoneMetadata shortNumberMetadataForRegion = MetadataManager.getShortNumberMetadataForRegion(regionCodeForShortNumberFromRegionList);
        return shortNumberMetadataForRegion != null && matchesPossibleNumberAndNationalNumber(nationalSignificantNumber, shortNumberMetadataForRegion.carrierSpecific);
    }

    public boolean isEmergencyNumber(String str, String str2) {
        return matchesEmergencyNumberHelper(str, str2, false);
    }

    public boolean isPossibleShortNumber(Phonenumber.PhoneNumber phoneNumber) {
        List<String> regionCodesForCountryCode = getRegionCodesForCountryCode(phoneNumber.getCountryCode());
        String nationalSignificantNumber = getNationalSignificantNumber(phoneNumber);
        Iterator<String> it = regionCodesForCountryCode.iterator();
        while (it.hasNext()) {
            Phonemetadata.PhoneMetadata shortNumberMetadataForRegion = MetadataManager.getShortNumberMetadataForRegion(it.next());
            if (shortNumberMetadataForRegion != null && this.matcherApi.matchesPossibleNumber(nationalSignificantNumber, shortNumberMetadataForRegion.generalDesc)) {
                return true;
            }
        }
        return false;
    }

    @Deprecated
    public boolean isPossibleShortNumberForRegion(String str, String str2) {
        Phonemetadata.PhoneMetadata shortNumberMetadataForRegion = MetadataManager.getShortNumberMetadataForRegion(str2);
        if (shortNumberMetadataForRegion == null) {
            return false;
        }
        return this.matcherApi.matchesPossibleNumber(str, shortNumberMetadataForRegion.generalDesc);
    }

    public boolean isValidShortNumber(Phonenumber.PhoneNumber phoneNumber) {
        List<String> regionCodesForCountryCode = getRegionCodesForCountryCode(phoneNumber.getCountryCode());
        String regionCodeForShortNumberFromRegionList = getRegionCodeForShortNumberFromRegionList(phoneNumber, regionCodesForCountryCode);
        if (regionCodesForCountryCode.size() <= 1 || regionCodeForShortNumberFromRegionList == null) {
            return isValidShortNumberForRegion(phoneNumber, regionCodeForShortNumberFromRegionList);
        }
        return true;
    }

    @Deprecated
    public boolean isValidShortNumberForRegion(String str, String str2) {
        Phonemetadata.PhoneMetadata shortNumberMetadataForRegion = MetadataManager.getShortNumberMetadataForRegion(str2);
        if (shortNumberMetadataForRegion != null && matchesPossibleNumberAndNationalNumber(str, shortNumberMetadataForRegion.generalDesc)) {
            return matchesPossibleNumberAndNationalNumber(str, shortNumberMetadataForRegion.shortCode);
        }
        return false;
    }

    public boolean isPossibleShortNumberForRegion(Phonenumber.PhoneNumber phoneNumber, String str) {
        Phonemetadata.PhoneMetadata shortNumberMetadataForRegion;
        if (regionDialingFromMatchesNumber(phoneNumber, str) && (shortNumberMetadataForRegion = MetadataManager.getShortNumberMetadataForRegion(str)) != null) {
            return this.matcherApi.matchesPossibleNumber(getNationalSignificantNumber(phoneNumber), shortNumberMetadataForRegion.generalDesc);
        }
        return false;
    }

    public boolean isValidShortNumberForRegion(Phonenumber.PhoneNumber phoneNumber, String str) {
        Phonemetadata.PhoneMetadata shortNumberMetadataForRegion;
        if (!regionDialingFromMatchesNumber(phoneNumber, str) || (shortNumberMetadataForRegion = MetadataManager.getShortNumberMetadataForRegion(str)) == null) {
            return false;
        }
        String nationalSignificantNumber = getNationalSignificantNumber(phoneNumber);
        if (matchesPossibleNumberAndNationalNumber(nationalSignificantNumber, shortNumberMetadataForRegion.generalDesc)) {
            return matchesPossibleNumberAndNationalNumber(nationalSignificantNumber, shortNumberMetadataForRegion.shortCode);
        }
        return false;
    }

    public ShortNumberCost getExpectedCostForRegion(Phonenumber.PhoneNumber phoneNumber, String str) {
        if (!regionDialingFromMatchesNumber(phoneNumber, str)) {
            return ShortNumberCost.UNKNOWN_COST;
        }
        Phonemetadata.PhoneMetadata shortNumberMetadataForRegion = MetadataManager.getShortNumberMetadataForRegion(str);
        if (shortNumberMetadataForRegion == null) {
            return ShortNumberCost.UNKNOWN_COST;
        }
        String nationalSignificantNumber = getNationalSignificantNumber(phoneNumber);
        if (matchesPossibleNumberAndNationalNumber(nationalSignificantNumber, shortNumberMetadataForRegion.premiumRate)) {
            return ShortNumberCost.PREMIUM_RATE;
        }
        if (matchesPossibleNumberAndNationalNumber(nationalSignificantNumber, shortNumberMetadataForRegion.standardRate)) {
            return ShortNumberCost.STANDARD_RATE;
        }
        if (matchesPossibleNumberAndNationalNumber(nationalSignificantNumber, shortNumberMetadataForRegion.tollFree)) {
            return ShortNumberCost.TOLL_FREE;
        }
        if (isEmergencyNumber(nationalSignificantNumber, str)) {
            return ShortNumberCost.TOLL_FREE;
        }
        return ShortNumberCost.UNKNOWN_COST;
    }
}
