package com.google.i18n.phonenumbers;

import com.google.i18n.phonenumbers.nano.Phonemetadata;

/* loaded from: classes2.dex */
interface MetadataSource {
    Phonemetadata.PhoneMetadata getMetadataForNonGeographicalRegion(int i9);

    Phonemetadata.PhoneMetadata getMetadataForRegion(String str);
}
