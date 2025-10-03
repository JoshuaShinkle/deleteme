package com.google.i18n.phonenumbers.internal;

import com.google.i18n.phonenumbers.nano.Phonemetadata;

/* loaded from: classes2.dex */
public interface MatcherApi {
    boolean matchesNationalNumber(String str, Phonemetadata.PhoneNumberDesc phoneNumberDesc, boolean z8);

    boolean matchesPossibleNumber(String str, Phonemetadata.PhoneNumberDesc phoneNumberDesc);
}
