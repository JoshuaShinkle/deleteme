package com.google.i18n.phonenumbers;

import com.google.i18n.phonenumbers.nano.Phonemetadata;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes2.dex */
final class MultiFileMetadataSourceImpl implements MetadataSource {
    private static final String META_DATA_FILE_PREFIX = "/com/google/i18n/phonenumbers/data/PhoneNumberMetadataProto";
    private static final Logger logger = Logger.getLogger(MultiFileMetadataSourceImpl.class.getName());
    private final Map<Integer, Phonemetadata.PhoneMetadata> countryCodeToNonGeographicalMetadataMap;
    private final String filePrefix;
    private final MetadataLoader metadataLoader;
    private final Map<String, Phonemetadata.PhoneMetadata> regionToMetadataMap;

    public MultiFileMetadataSourceImpl(String str, MetadataLoader metadataLoader) {
        this.regionToMetadataMap = Collections.synchronizedMap(new HashMap());
        this.countryCodeToNonGeographicalMetadataMap = Collections.synchronizedMap(new HashMap());
        this.filePrefix = str;
        this.metadataLoader = metadataLoader;
    }

    private static Phonemetadata.PhoneMetadataCollection loadMetadataAndCloseInput(ObjectInputStream objectInputStream) throws IOException {
        Phonemetadata.PhoneMetadataCollection phoneMetadataCollection = new Phonemetadata.PhoneMetadataCollection();
        try {
            try {
                try {
                    phoneMetadataCollection.mergeFrom(MetadataManager.convertStreamToByteBuffer(objectInputStream, 16384));
                    objectInputStream.close();
                } catch (IOException e9) {
                    logger.log(Level.WARNING, "error closing input stream (ignored)", (Throwable) e9);
                }
            } catch (IOException e10) {
                logger.log(Level.WARNING, "error reading input (ignored)", (Throwable) e10);
                objectInputStream.close();
            }
            return phoneMetadataCollection;
        } catch (Throwable th) {
            try {
                objectInputStream.close();
            } catch (IOException e11) {
                logger.log(Level.WARNING, "error closing input stream (ignored)", (Throwable) e11);
            }
            throw th;
        }
    }

    @Override // com.google.i18n.phonenumbers.MetadataSource
    public Phonemetadata.PhoneMetadata getMetadataForNonGeographicalRegion(int i9) {
        synchronized (this.countryCodeToNonGeographicalMetadataMap) {
            if (!this.countryCodeToNonGeographicalMetadataMap.containsKey(Integer.valueOf(i9))) {
                List<String> list = CountryCodeToRegionCodeMap.getCountryCodeToRegionCodeMap().get(Integer.valueOf(i9));
                if (list.size() == 1 && PhoneNumberUtil.REGION_CODE_FOR_NON_GEO_ENTITY.equals(list.get(0))) {
                    loadMetadataFromFile(PhoneNumberUtil.REGION_CODE_FOR_NON_GEO_ENTITY, i9);
                }
            }
        }
        return this.countryCodeToNonGeographicalMetadataMap.get(Integer.valueOf(i9));
    }

    @Override // com.google.i18n.phonenumbers.MetadataSource
    public Phonemetadata.PhoneMetadata getMetadataForRegion(String str) {
        synchronized (this.regionToMetadataMap) {
            if (!this.regionToMetadataMap.containsKey(str)) {
                loadMetadataFromFile(str, 0);
            }
        }
        return this.regionToMetadataMap.get(str);
    }

    public void loadMetadataFromFile(String str, int i9) {
        boolean zEquals = PhoneNumberUtil.REGION_CODE_FOR_NON_GEO_ENTITY.equals(str);
        String strValueOf = String.valueOf(this.filePrefix);
        String strValueOf2 = String.valueOf(zEquals ? String.valueOf(i9) : str);
        StringBuilder sb = new StringBuilder(strValueOf.length() + 1 + strValueOf2.length());
        sb.append(strValueOf);
        sb.append("_");
        sb.append(strValueOf2);
        String string = sb.toString();
        InputStream inputStreamLoadMetadata = this.metadataLoader.loadMetadata(string);
        if (inputStreamLoadMetadata == null) {
            Logger logger2 = logger;
            Level level = Level.SEVERE;
            String strValueOf3 = String.valueOf(string);
            logger2.log(level, strValueOf3.length() != 0 ? "missing metadata: ".concat(strValueOf3) : new String("missing metadata: "));
            String strValueOf4 = String.valueOf(string);
            throw new IllegalStateException(strValueOf4.length() != 0 ? "missing metadata: ".concat(strValueOf4) : new String("missing metadata: "));
        }
        try {
            Phonemetadata.PhoneMetadata[] phoneMetadataArr = loadMetadataAndCloseInput(new ObjectInputStream(inputStreamLoadMetadata)).metadata;
            if (phoneMetadataArr.length == 0) {
                Logger logger3 = logger;
                Level level2 = Level.SEVERE;
                String strValueOf5 = String.valueOf(string);
                logger3.log(level2, strValueOf5.length() != 0 ? "empty metadata: ".concat(strValueOf5) : new String("empty metadata: "));
                String strValueOf6 = String.valueOf(string);
                throw new IllegalStateException(strValueOf6.length() != 0 ? "empty metadata: ".concat(strValueOf6) : new String("empty metadata: "));
            }
            if (phoneMetadataArr.length > 1) {
                Logger logger4 = logger;
                Level level3 = Level.WARNING;
                String strValueOf7 = String.valueOf(string);
                logger4.log(level3, strValueOf7.length() != 0 ? "invalid metadata (too many entries): ".concat(strValueOf7) : new String("invalid metadata (too many entries): "));
            }
            Phonemetadata.PhoneMetadata phoneMetadata = phoneMetadataArr[0];
            if (zEquals) {
                this.countryCodeToNonGeographicalMetadataMap.put(Integer.valueOf(i9), phoneMetadata);
            } else {
                this.regionToMetadataMap.put(str, phoneMetadata);
            }
        } catch (IOException e9) {
            Logger logger5 = logger;
            Level level4 = Level.SEVERE;
            String strValueOf8 = String.valueOf(string);
            logger5.log(level4, strValueOf8.length() != 0 ? "cannot load/parse metadata: ".concat(strValueOf8) : new String("cannot load/parse metadata: "), (Throwable) e9);
            String strValueOf9 = String.valueOf(string);
            throw new RuntimeException(strValueOf9.length() != 0 ? "cannot load/parse metadata: ".concat(strValueOf9) : new String("cannot load/parse metadata: "), e9);
        }
    }

    public MultiFileMetadataSourceImpl(MetadataLoader metadataLoader) {
        this(META_DATA_FILE_PREFIX, metadataLoader);
    }
}
