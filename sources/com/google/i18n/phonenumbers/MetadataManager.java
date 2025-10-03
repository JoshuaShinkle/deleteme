package com.google.i18n.phonenumbers;

import com.google.i18n.phonenumbers.nano.Phonemetadata;
import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.nano.CodedInputByteBufferNano;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes2.dex */
class MetadataManager {
    private static final String ALTERNATE_FORMATS_FILE_PREFIX = "/com/google/i18n/phonenumbers/data/PhoneNumberAlternateFormatsProto";
    private static final int BUFFER_SIZE = 16384;
    private static final String SHORT_NUMBER_METADATA_FILE_PREFIX = "/com/google/i18n/phonenumbers/data/ShortNumberMetadataProto";
    private static final Logger LOGGER = Logger.getLogger(MetadataManager.class.getName());
    private static final Map<Integer, Phonemetadata.PhoneMetadata> callingCodeToAlternateFormatsMap = Collections.synchronizedMap(new HashMap());
    private static final Map<String, Phonemetadata.PhoneMetadata> regionCodeToShortNumberMetadataMap = Collections.synchronizedMap(new HashMap());
    private static final Set<Integer> countryCodeSet = AlternateFormatsCountryCodeSet.getCountryCodeSet();
    private static final Set<String> regionCodeSet = ShortNumbersRegionCodeSet.getRegionCodeSet();

    private MetadataManager() {
    }

    private static void close(InputStream inputStream) throws IOException {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e9) {
                LOGGER.log(Level.WARNING, e9.toString());
            }
        }
    }

    public static CodedInputByteBufferNano convertStreamToByteBuffer(ObjectInputStream objectInputStream, int i9) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[i9];
        while (true) {
            int i10 = objectInputStream.read(bArr, 0, i9);
            if (i10 == -1) {
                byteArrayOutputStream.flush();
                return CodedInputByteBufferNano.newInstance(byteArrayOutputStream.toByteArray());
            }
            byteArrayOutputStream.write(bArr, 0, i10);
        }
    }

    public static Phonemetadata.PhoneMetadata getAlternateFormatsForCountry(int i9) {
        if (!countryCodeSet.contains(Integer.valueOf(i9))) {
            return null;
        }
        Map<Integer, Phonemetadata.PhoneMetadata> map = callingCodeToAlternateFormatsMap;
        synchronized (map) {
            if (!map.containsKey(Integer.valueOf(i9))) {
                loadAlternateFormatsMetadataFromFile(i9);
            }
        }
        return map.get(Integer.valueOf(i9));
    }

    public static Phonemetadata.PhoneMetadata getShortNumberMetadataForRegion(String str) {
        if (!regionCodeSet.contains(str)) {
            return null;
        }
        Map<String, Phonemetadata.PhoneMetadata> map = regionCodeToShortNumberMetadataMap;
        synchronized (map) {
            if (!map.containsKey(str)) {
                loadShortNumberMetadataFromFile(str);
            }
        }
        return map.get(str);
    }

    public static Set<String> getShortNumberMetadataSupportedRegions() {
        return regionCodeSet;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static void loadAlternateFormatsMetadataFromFile(int i9) throws Throwable {
        ObjectInputStream objectInputStream;
        StringBuilder sb = new StringBuilder("/com/google/i18n/phonenumbers/data/PhoneNumberAlternateFormatsProto_".length() + 11);
        sb.append("/com/google/i18n/phonenumbers/data/PhoneNumberAlternateFormatsProto_");
        sb.append(i9);
        ObjectInputStream objectInputStream2 = null;
        ObjectInputStream objectInputStream3 = null;
        try {
            try {
                objectInputStream = new ObjectInputStream(PhoneNumberMatcher.class.getResourceAsStream(sb.toString()));
            } catch (Throwable th) {
                th = th;
            }
        } catch (IOException e9) {
            e = e9;
        }
        try {
            CodedInputByteBufferNano codedInputByteBufferNanoConvertStreamToByteBuffer = convertStreamToByteBuffer(objectInputStream, BUFFER_SIZE);
            Phonemetadata.PhoneMetadataCollection phoneMetadataCollection = new Phonemetadata.PhoneMetadataCollection();
            phoneMetadataCollection.mergeFrom(codedInputByteBufferNanoConvertStreamToByteBuffer);
            Phonemetadata.PhoneMetadata[] phoneMetadataArr = phoneMetadataCollection.metadata;
            int length = phoneMetadataArr.length;
            for (Phonemetadata.PhoneMetadata phoneMetadata : phoneMetadataArr) {
                callingCodeToAlternateFormatsMap.put(Integer.valueOf(phoneMetadata.countryCode), phoneMetadata);
            }
            close(objectInputStream);
            objectInputStream2 = length;
        } catch (IOException e10) {
            e = e10;
            objectInputStream3 = objectInputStream;
            LOGGER.log(Level.WARNING, e.toString());
            close(objectInputStream3);
            objectInputStream2 = objectInputStream3;
        } catch (Throwable th2) {
            th = th2;
            objectInputStream2 = objectInputStream;
            close(objectInputStream2);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static void loadShortNumberMetadataFromFile(String str) throws Throwable {
        ObjectInputStream objectInputStream;
        String strValueOf = String.valueOf(str);
        String strConcat = strValueOf.length() != 0 ? "/com/google/i18n/phonenumbers/data/ShortNumberMetadataProto_".concat(strValueOf) : new String("/com/google/i18n/phonenumbers/data/ShortNumberMetadataProto_");
        ObjectInputStream objectInputStream2 = null;
        ObjectInputStream objectInputStream3 = null;
        try {
            try {
                objectInputStream = new ObjectInputStream(PhoneNumberMatcher.class.getResourceAsStream(strConcat));
            } catch (Throwable th) {
                th = th;
            }
        } catch (IOException e9) {
            e = e9;
        }
        try {
            CodedInputByteBufferNano codedInputByteBufferNanoConvertStreamToByteBuffer = convertStreamToByteBuffer(objectInputStream, BUFFER_SIZE);
            Phonemetadata.PhoneMetadataCollection phoneMetadataCollection = new Phonemetadata.PhoneMetadataCollection();
            phoneMetadataCollection.mergeFrom(codedInputByteBufferNanoConvertStreamToByteBuffer);
            Phonemetadata.PhoneMetadata[] phoneMetadataArr = phoneMetadataCollection.metadata;
            int length = phoneMetadataArr.length;
            for (Phonemetadata.PhoneMetadata phoneMetadata : phoneMetadataArr) {
                regionCodeToShortNumberMetadataMap.put(str, phoneMetadata);
            }
            close(objectInputStream);
            objectInputStream2 = length;
        } catch (IOException e10) {
            e = e10;
            objectInputStream3 = objectInputStream;
            LOGGER.log(Level.WARNING, e.toString());
            close(objectInputStream3);
            objectInputStream2 = objectInputStream3;
        } catch (Throwable th2) {
            th = th2;
            objectInputStream2 = objectInputStream;
            close(objectInputStream2);
            throw th;
        }
    }
}
