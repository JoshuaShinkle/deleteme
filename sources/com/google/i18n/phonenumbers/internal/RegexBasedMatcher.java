package com.google.i18n.phonenumbers.internal;

import com.google.i18n.phonenumbers.RegexCache;
import com.google.i18n.phonenumbers.nano.Phonemetadata;
import java.util.regex.Matcher;

/* loaded from: classes2.dex */
public final class RegexBasedMatcher implements MatcherApi {
    private final RegexCache regexCache = new RegexCache(100);

    private RegexBasedMatcher() {
    }

    public static MatcherApi create() {
        return new RegexBasedMatcher();
    }

    @Override // com.google.i18n.phonenumbers.internal.MatcherApi
    public boolean matchesNationalNumber(String str, Phonemetadata.PhoneNumberDesc phoneNumberDesc, boolean z8) {
        Matcher matcher = this.regexCache.getPatternForRegex(phoneNumberDesc.nationalNumberPattern).matcher(str);
        return matcher.matches() || (z8 && matcher.lookingAt());
    }

    @Override // com.google.i18n.phonenumbers.internal.MatcherApi
    public boolean matchesPossibleNumber(String str, Phonemetadata.PhoneNumberDesc phoneNumberDesc) {
        return this.regexCache.getPatternForRegex(phoneNumberDesc.possibleNumberPattern).matcher(str).matches();
    }
}
