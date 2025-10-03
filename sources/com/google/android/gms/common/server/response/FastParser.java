package com.google.android.gms.common.server.response;

import android.util.Log;
import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.server.response.FastJsonResponse;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.common.util.JsonUtils;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import org.jsoup.select.Elements;

@ShowFirstParty
@KeepForSdk
/* loaded from: classes2.dex */
public class FastParser<T extends FastJsonResponse> {
    private static final char[] zaf = {'u', 'l', 'l'};
    private static final char[] zag = {'r', 'u', 'e'};
    private static final char[] zah = {'r', 'u', 'e', '\"'};
    private static final char[] zai = {'a', 'l', 's', 'e'};
    private static final char[] zaj = {'a', 'l', 's', 'e', '\"'};
    private static final char[] zak = {'\n'};
    private static final zaa<Integer> zam = new com.google.android.gms.common.server.response.zaa();
    private static final zaa<Long> zan = new zac();
    private static final zaa<Float> zao = new zab();
    private static final zaa<Double> zap = new zae();
    private static final zaa<Boolean> zaq = new zad();
    private static final zaa<String> zar = new zag();
    private static final zaa<BigInteger> zas = new zaf();
    private static final zaa<BigDecimal> zat = new zah();
    private final char[] zaa = new char[1];
    private final char[] zab = new char[32];
    private final char[] zac = new char[UserMetadata.MAX_ATTRIBUTE_SIZE];
    private final StringBuilder zad = new StringBuilder(32);
    private final StringBuilder zae = new StringBuilder(UserMetadata.MAX_ATTRIBUTE_SIZE);
    private final Stack<Integer> zal = new Stack<>();

    @ShowFirstParty
    @KeepForSdk
    public static class ParseException extends Exception {
        public ParseException(@RecentlyNonNull String str) {
            super(str);
        }

        public ParseException(@RecentlyNonNull String str, @RecentlyNonNull Throwable th) {
            super(str, th);
        }

        public ParseException(@RecentlyNonNull Throwable th) {
            super(th);
        }
    }

    public interface zaa<O> {
        O zaa(FastParser fastParser, BufferedReader bufferedReader);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:138:0x0297 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:140:0x0279 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final boolean zaa(BufferedReader bufferedReader, FastJsonResponse fastJsonResponse) throws ParseException, IOException {
        int i9;
        HashMap map;
        char cZaj;
        Map<String, FastJsonResponse.Field<?, ?>> fieldMappings = fastJsonResponse.getFieldMappings();
        String strZaa = zaa(bufferedReader);
        if (strZaa == null) {
            zaa(1);
            return false;
        }
        while (strZaa != null) {
            FastJsonResponse.Field<?, ?> field = fieldMappings.get(strZaa);
            if (field == null) {
                strZaa = zab(bufferedReader);
            } else {
                this.zal.push(4);
                switch (field.zaa) {
                    case 0:
                        if (field.zab) {
                            fastJsonResponse.zaa((FastJsonResponse.Field) field, (ArrayList<Integer>) zaa(bufferedReader, zam));
                        } else {
                            fastJsonResponse.zaa((FastJsonResponse.Field) field, zad(bufferedReader));
                        }
                        i9 = 4;
                        zaa(i9);
                        zaa(2);
                        cZaj = zaj(bufferedReader);
                        if (cZaj != ',') {
                            strZaa = zaa(bufferedReader);
                            break;
                        } else {
                            if (cZaj != '}') {
                                StringBuilder sb = new StringBuilder(55);
                                sb.append("Expected end of object or field separator, but found: ");
                                sb.append(cZaj);
                                throw new ParseException(sb.toString());
                            }
                            strZaa = null;
                            break;
                        }
                    case 1:
                        if (field.zab) {
                            fastJsonResponse.zab((FastJsonResponse.Field) field, (ArrayList<BigInteger>) zaa(bufferedReader, zas));
                        } else {
                            fastJsonResponse.zaa((FastJsonResponse.Field) field, zaf(bufferedReader));
                        }
                        i9 = 4;
                        zaa(i9);
                        zaa(2);
                        cZaj = zaj(bufferedReader);
                        if (cZaj != ',') {
                        }
                        break;
                    case 2:
                        if (field.zab) {
                            fastJsonResponse.zac(field, zaa(bufferedReader, zan));
                        } else {
                            fastJsonResponse.zaa((FastJsonResponse.Field) field, zae(bufferedReader));
                        }
                        i9 = 4;
                        zaa(i9);
                        zaa(2);
                        cZaj = zaj(bufferedReader);
                        if (cZaj != ',') {
                        }
                        break;
                    case 3:
                        if (field.zab) {
                            fastJsonResponse.zad(field, zaa(bufferedReader, zao));
                        } else {
                            fastJsonResponse.zaa((FastJsonResponse.Field) field, zag(bufferedReader));
                        }
                        i9 = 4;
                        zaa(i9);
                        zaa(2);
                        cZaj = zaj(bufferedReader);
                        if (cZaj != ',') {
                        }
                        break;
                    case 4:
                        if (field.zab) {
                            fastJsonResponse.zae(field, zaa(bufferedReader, zap));
                        } else {
                            fastJsonResponse.zaa(field, zah(bufferedReader));
                        }
                        i9 = 4;
                        zaa(i9);
                        zaa(2);
                        cZaj = zaj(bufferedReader);
                        if (cZaj != ',') {
                        }
                        break;
                    case 5:
                        if (field.zab) {
                            fastJsonResponse.zaf(field, zaa(bufferedReader, zat));
                        } else {
                            fastJsonResponse.zaa((FastJsonResponse.Field) field, zai(bufferedReader));
                        }
                        i9 = 4;
                        zaa(i9);
                        zaa(2);
                        cZaj = zaj(bufferedReader);
                        if (cZaj != ',') {
                        }
                        break;
                    case 6:
                        if (field.zab) {
                            fastJsonResponse.zag(field, zaa(bufferedReader, zaq));
                            i9 = 4;
                            zaa(i9);
                            zaa(2);
                            cZaj = zaj(bufferedReader);
                            if (cZaj != ',') {
                            }
                        } else {
                            fastJsonResponse.zaa(field, zaa(bufferedReader, false));
                            i9 = 4;
                            zaa(i9);
                            zaa(2);
                            cZaj = zaj(bufferedReader);
                            if (cZaj != ',') {
                            }
                        }
                        break;
                    case 7:
                        if (field.zab) {
                            fastJsonResponse.zah(field, zaa(bufferedReader, zar));
                        } else {
                            fastJsonResponse.zaa((FastJsonResponse.Field) field, zac(bufferedReader));
                        }
                        i9 = 4;
                        zaa(i9);
                        zaa(2);
                        cZaj = zaj(bufferedReader);
                        if (cZaj != ',') {
                        }
                        break;
                    case 8:
                        fastJsonResponse.zaa((FastJsonResponse.Field) field, Base64Utils.decode(zaa(bufferedReader, this.zac, this.zae, zak)));
                        i9 = 4;
                        zaa(i9);
                        zaa(2);
                        cZaj = zaj(bufferedReader);
                        if (cZaj != ',') {
                        }
                        break;
                    case 9:
                        fastJsonResponse.zaa((FastJsonResponse.Field) field, Base64Utils.decodeUrlSafe(zaa(bufferedReader, this.zac, this.zae, zak)));
                        i9 = 4;
                        zaa(i9);
                        zaa(2);
                        cZaj = zaj(bufferedReader);
                        if (cZaj != ',') {
                        }
                        break;
                    case 10:
                        char cZaj2 = zaj(bufferedReader);
                        if (cZaj2 == 'n') {
                            zab(bufferedReader, zaf);
                            map = null;
                        } else {
                            if (cZaj2 != '{') {
                                throw new ParseException("Expected start of a map object");
                            }
                            this.zal.push(1);
                            map = new HashMap();
                            while (true) {
                                char cZaj3 = zaj(bufferedReader);
                                if (cZaj3 == 0) {
                                    throw new ParseException("Unexpected EOF");
                                }
                                if (cZaj3 == '\"') {
                                    String strZab = zab(bufferedReader, this.zab, this.zad, null);
                                    if (zaj(bufferedReader) != ':') {
                                        String strValueOf = String.valueOf(strZab);
                                        throw new ParseException(strValueOf.length() != 0 ? "No map value found for key ".concat(strValueOf) : new String("No map value found for key "));
                                    }
                                    if (zaj(bufferedReader) != '\"') {
                                        String strValueOf2 = String.valueOf(strZab);
                                        throw new ParseException(strValueOf2.length() != 0 ? "Expected String value for key ".concat(strValueOf2) : new String("Expected String value for key "));
                                    }
                                    map.put(strZab, zab(bufferedReader, this.zab, this.zad, null));
                                    char cZaj4 = zaj(bufferedReader);
                                    if (cZaj4 != ',') {
                                        if (cZaj4 != '}') {
                                            StringBuilder sb2 = new StringBuilder(48);
                                            sb2.append("Unexpected character while parsing string map: ");
                                            sb2.append(cZaj4);
                                            throw new ParseException(sb2.toString());
                                        }
                                        zaa(1);
                                    }
                                } else if (cZaj3 == '}') {
                                    zaa(1);
                                }
                                i9 = 4;
                                zaa(i9);
                                zaa(2);
                                cZaj = zaj(bufferedReader);
                                if (cZaj != ',') {
                                }
                            }
                        }
                        fastJsonResponse.zaa((FastJsonResponse.Field) field, (Map<String, String>) map);
                        i9 = 4;
                        zaa(i9);
                        zaa(2);
                        cZaj = zaj(bufferedReader);
                        if (cZaj != ',') {
                        }
                        break;
                    case 11:
                        if (field.zab) {
                            char cZaj5 = zaj(bufferedReader);
                            if (cZaj5 == 'n') {
                                zab(bufferedReader, zaf);
                                fastJsonResponse.addConcreteTypeArrayInternal(field, field.zae, null);
                            } else {
                                this.zal.push(5);
                                if (cZaj5 != '[') {
                                    throw new ParseException("Expected array start");
                                }
                                fastJsonResponse.addConcreteTypeArrayInternal(field, field.zae, zaa(bufferedReader, field));
                            }
                        } else {
                            char cZaj6 = zaj(bufferedReader);
                            if (cZaj6 == 'n') {
                                zab(bufferedReader, zaf);
                                fastJsonResponse.addConcreteTypeInternal(field, field.zae, null);
                            } else {
                                this.zal.push(1);
                                if (cZaj6 != '{') {
                                    throw new ParseException("Expected start of object");
                                }
                                try {
                                    FastJsonResponse fastJsonResponseZac = field.zac();
                                    zaa(bufferedReader, fastJsonResponseZac);
                                    fastJsonResponse.addConcreteTypeInternal(field, field.zae, fastJsonResponseZac);
                                } catch (IllegalAccessException e9) {
                                    throw new ParseException("Error instantiating inner object", e9);
                                } catch (InstantiationException e10) {
                                    throw new ParseException("Error instantiating inner object", e10);
                                }
                            }
                        }
                        i9 = 4;
                        zaa(i9);
                        zaa(2);
                        cZaj = zaj(bufferedReader);
                        if (cZaj != ',') {
                        }
                        break;
                    default:
                        int i10 = field.zaa;
                        StringBuilder sb3 = new StringBuilder(30);
                        sb3.append("Invalid field type ");
                        sb3.append(i10);
                        throw new ParseException(sb3.toString());
                }
            }
        }
        zaa(1);
        return true;
    }

    private final String zab(BufferedReader bufferedReader) throws ParseException, IOException {
        bufferedReader.mark(UserMetadata.MAX_ATTRIBUTE_SIZE);
        char cZaj = zaj(bufferedReader);
        if (cZaj == '\"') {
            if (bufferedReader.read(this.zaa) == -1) {
                throw new ParseException("Unexpected EOF while parsing string");
            }
            char c9 = this.zaa[0];
            boolean z8 = false;
            do {
                if (c9 != '\"' || z8) {
                    z8 = c9 == '\\' ? !z8 : false;
                    if (bufferedReader.read(this.zaa) == -1) {
                        throw new ParseException("Unexpected EOF while parsing string");
                    }
                    c9 = this.zaa[0];
                }
            } while (!Character.isISOControl(c9));
            throw new ParseException("Unexpected control character while reading string");
        }
        if (cZaj == ',') {
            throw new ParseException("Missing value");
        }
        int i9 = 1;
        if (cZaj == '[') {
            this.zal.push(5);
            bufferedReader.mark(32);
            if (zaj(bufferedReader) == ']') {
                zaa(5);
            } else {
                bufferedReader.reset();
                boolean z9 = false;
                boolean z10 = false;
                while (i9 > 0) {
                    char cZaj2 = zaj(bufferedReader);
                    if (cZaj2 == 0) {
                        throw new ParseException("Unexpected EOF while parsing array");
                    }
                    if (Character.isISOControl(cZaj2)) {
                        throw new ParseException("Unexpected control character while reading array");
                    }
                    if (cZaj2 == '\"' && !z9) {
                        z10 = !z10;
                    }
                    if (cZaj2 == '[' && !z10) {
                        i9++;
                    }
                    if (cZaj2 == ']' && !z10) {
                        i9--;
                    }
                    z9 = (cZaj2 == '\\' && z10) ? !z9 : false;
                }
                zaa(5);
            }
        } else if (cZaj != '{') {
            bufferedReader.reset();
            zaa(bufferedReader, this.zac);
        } else {
            this.zal.push(1);
            bufferedReader.mark(32);
            char cZaj3 = zaj(bufferedReader);
            if (cZaj3 == '}') {
                zaa(1);
            } else {
                if (cZaj3 != '\"') {
                    StringBuilder sb = new StringBuilder(18);
                    sb.append("Unexpected token ");
                    sb.append(cZaj3);
                    throw new ParseException(sb.toString());
                }
                bufferedReader.reset();
                zaa(bufferedReader);
                while (zab(bufferedReader) != null) {
                }
                zaa(1);
            }
        }
        char cZaj4 = zaj(bufferedReader);
        if (cZaj4 == ',') {
            zaa(2);
            return zaa(bufferedReader);
        }
        if (cZaj4 == '}') {
            zaa(2);
            return null;
        }
        StringBuilder sb2 = new StringBuilder(18);
        sb2.append("Unexpected token ");
        sb2.append(cZaj4);
        throw new ParseException(sb2.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String zac(BufferedReader bufferedReader) {
        return zaa(bufferedReader, this.zab, this.zad, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int zad(BufferedReader bufferedReader) throws ParseException, IOException {
        int i9;
        int i10;
        int iZaa = zaa(bufferedReader, this.zac);
        int i11 = 0;
        if (iZaa == 0) {
            return 0;
        }
        char[] cArr = this.zac;
        if (iZaa <= 0) {
            throw new ParseException("No number to parse");
        }
        if (cArr[0] == '-') {
            i9 = Integer.MIN_VALUE;
            i10 = 1;
        } else {
            i9 = -2147483647;
            i10 = 0;
        }
        int i12 = i10;
        if (i10 < iZaa) {
            int i13 = i10 + 1;
            int iDigit = Character.digit(cArr[i10], 10);
            if (iDigit < 0) {
                throw new ParseException("Unexpected non-digit character");
            }
            int i14 = -iDigit;
            i10 = i13;
            i11 = i14;
        }
        while (i10 < iZaa) {
            int i15 = i10 + 1;
            int iDigit2 = Character.digit(cArr[i10], 10);
            if (iDigit2 < 0) {
                throw new ParseException("Unexpected non-digit character");
            }
            if (i11 < -214748364) {
                throw new ParseException("Number too large");
            }
            int i16 = i11 * 10;
            if (i16 < i9 + iDigit2) {
                throw new ParseException("Number too large");
            }
            i11 = i16 - iDigit2;
            i10 = i15;
        }
        if (i12 == 0) {
            return -i11;
        }
        if (i10 > 1) {
            return i11;
        }
        throw new ParseException("No digits to parse");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long zae(BufferedReader bufferedReader) throws ParseException, IOException {
        long j9;
        int iZaa = zaa(bufferedReader, this.zac);
        long j10 = 0;
        if (iZaa == 0) {
            return 0L;
        }
        char[] cArr = this.zac;
        if (iZaa <= 0) {
            throw new ParseException("No number to parse");
        }
        int i9 = 0;
        if (cArr[0] == '-') {
            j9 = Long.MIN_VALUE;
            i9 = 1;
        } else {
            j9 = -9223372036854775807L;
        }
        int i10 = i9;
        int i11 = 10;
        if (i9 < iZaa) {
            int i12 = i9 + 1;
            int iDigit = Character.digit(cArr[i9], 10);
            if (iDigit < 0) {
                throw new ParseException("Unexpected non-digit character");
            }
            i9 = i12;
            j10 = -iDigit;
        }
        while (i9 < iZaa) {
            int i13 = i9 + 1;
            int iDigit2 = Character.digit(cArr[i9], i11);
            if (iDigit2 < 0) {
                throw new ParseException("Unexpected non-digit character");
            }
            if (j10 < -922337203685477580L) {
                throw new ParseException("Number too large");
            }
            long j11 = j10 * 10;
            long j12 = iDigit2;
            if (j11 < j9 + j12) {
                throw new ParseException("Number too large");
            }
            j10 = j11 - j12;
            i9 = i13;
            i11 = 10;
        }
        if (i10 == 0) {
            return -j10;
        }
        if (i9 > 1) {
            return j10;
        }
        throw new ParseException("No digits to parse");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final BigInteger zaf(BufferedReader bufferedReader) throws ParseException, IOException {
        int iZaa = zaa(bufferedReader, this.zac);
        if (iZaa == 0) {
            return null;
        }
        return new BigInteger(new String(this.zac, 0, iZaa));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final float zag(BufferedReader bufferedReader) throws ParseException, IOException {
        int iZaa = zaa(bufferedReader, this.zac);
        return iZaa == 0 ? BitmapDescriptorFactory.HUE_RED : Float.parseFloat(new String(this.zac, 0, iZaa));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final double zah(BufferedReader bufferedReader) throws ParseException, IOException {
        int iZaa = zaa(bufferedReader, this.zac);
        return iZaa == 0 ? FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE : Double.parseDouble(new String(this.zac, 0, iZaa));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final BigDecimal zai(BufferedReader bufferedReader) throws ParseException, IOException {
        int iZaa = zaa(bufferedReader, this.zac);
        if (iZaa == 0) {
            return null;
        }
        return new BigDecimal(new String(this.zac, 0, iZaa));
    }

    private final char zaj(BufferedReader bufferedReader) {
        if (bufferedReader.read(this.zaa) == -1) {
            return (char) 0;
        }
        while (Character.isWhitespace(this.zaa[0])) {
            if (bufferedReader.read(this.zaa) == -1) {
                return (char) 0;
            }
        }
        return this.zaa[0];
    }

    @KeepForSdk
    public void parse(@RecentlyNonNull InputStream inputStream, @RecentlyNonNull T t8) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream), UserMetadata.MAX_ATTRIBUTE_SIZE);
        try {
            try {
                this.zal.push(0);
                char cZaj = zaj(bufferedReader);
                if (cZaj == 0) {
                    throw new ParseException("No data to parse");
                }
                if (cZaj == '[') {
                    this.zal.push(5);
                    Map<String, FastJsonResponse.Field<?, ?>> fieldMappings = t8.getFieldMappings();
                    if (fieldMappings.size() != 1) {
                        throw new ParseException("Object array response class must have a single Field");
                    }
                    FastJsonResponse.Field<?, ?> value = fieldMappings.entrySet().iterator().next().getValue();
                    t8.addConcreteTypeArrayInternal(value, value.zae, zaa(bufferedReader, value));
                } else {
                    if (cZaj != '{') {
                        StringBuilder sb = new StringBuilder(19);
                        sb.append("Unexpected token: ");
                        sb.append(cZaj);
                        throw new ParseException(sb.toString());
                    }
                    this.zal.push(1);
                    zaa(bufferedReader, t8);
                }
                zaa(0);
            } catch (IOException e9) {
                throw new ParseException(e9);
            }
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException unused) {
                Log.w("FastParser", "Failed to close reader while parsing.");
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x0030 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static String zab(BufferedReader bufferedReader, char[] cArr, StringBuilder sb, char[] cArr2) throws ParseException, IOException {
        boolean z8;
        sb.setLength(0);
        bufferedReader.mark(cArr.length);
        boolean z9 = false;
        boolean z10 = false;
        while (true) {
            int i9 = bufferedReader.read(cArr);
            if (i9 != -1) {
                for (int i10 = 0; i10 < i9; i10++) {
                    char c9 = cArr[i10];
                    if (Character.isISOControl(c9)) {
                        if (cArr2 != null) {
                            for (char c10 : cArr2) {
                                if (c10 == c9) {
                                    z8 = true;
                                    break;
                                }
                            }
                            z8 = false;
                            if (!z8) {
                                throw new ParseException("Unexpected control character while reading string");
                            }
                        } else {
                            z8 = false;
                            if (!z8) {
                            }
                        }
                    }
                    if (c9 == '\"' && !z9) {
                        sb.append(cArr, 0, i10);
                        bufferedReader.reset();
                        bufferedReader.skip(i10 + 1);
                        if (z10) {
                            return JsonUtils.unescapeString(sb.toString());
                        }
                        return sb.toString();
                    }
                    if (c9 == '\\') {
                        z9 = !z9;
                        z10 = true;
                    } else {
                        z9 = false;
                    }
                }
                sb.append(cArr, 0, i9);
                bufferedReader.mark(cArr.length);
            } else {
                throw new ParseException("Unexpected EOF while parsing string");
            }
        }
    }

    private final void zab(BufferedReader bufferedReader, char[] cArr) throws ParseException, IOException {
        int i9 = 0;
        while (i9 < cArr.length) {
            int i10 = bufferedReader.read(this.zab, 0, cArr.length - i9);
            if (i10 == -1) {
                throw new ParseException("Unexpected EOF");
            }
            for (int i11 = 0; i11 < i10; i11++) {
                if (cArr[i11 + i9] != this.zab[i11]) {
                    throw new ParseException("Unexpected character");
                }
            }
            i9 += i10;
        }
    }

    private final String zaa(BufferedReader bufferedReader) throws ParseException, IOException {
        this.zal.push(2);
        char cZaj = zaj(bufferedReader);
        if (cZaj == '\"') {
            this.zal.push(3);
            String strZab = zab(bufferedReader, this.zab, this.zad, null);
            zaa(3);
            if (zaj(bufferedReader) == ':') {
                return strZab;
            }
            throw new ParseException("Expected key/value separator");
        }
        if (cZaj == ']') {
            zaa(2);
            zaa(1);
            zaa(5);
            return null;
        }
        if (cZaj == '}') {
            zaa(2);
            return null;
        }
        StringBuilder sb = new StringBuilder(19);
        sb.append("Unexpected token: ");
        sb.append(cZaj);
        throw new ParseException(sb.toString());
    }

    private final <O> ArrayList<O> zaa(BufferedReader bufferedReader, zaa<O> zaaVar) throws ParseException, IOException {
        char cZaj = zaj(bufferedReader);
        if (cZaj == 'n') {
            zab(bufferedReader, zaf);
            return null;
        }
        if (cZaj == '[') {
            this.zal.push(5);
            ArrayList<O> arrayList = new ArrayList<>();
            while (true) {
                bufferedReader.mark(UserMetadata.MAX_ATTRIBUTE_SIZE);
                char cZaj2 = zaj(bufferedReader);
                if (cZaj2 == 0) {
                    throw new ParseException("Unexpected EOF");
                }
                if (cZaj2 != ',') {
                    if (cZaj2 != ']') {
                        bufferedReader.reset();
                        arrayList.add(zaaVar.zaa(this, bufferedReader));
                    } else {
                        zaa(5);
                        return arrayList;
                    }
                }
            }
        } else {
            throw new ParseException("Expected start of array");
        }
    }

    private final String zaa(BufferedReader bufferedReader, char[] cArr, StringBuilder sb, char[] cArr2) throws ParseException, IOException {
        char cZaj = zaj(bufferedReader);
        if (cZaj == '\"') {
            return zab(bufferedReader, cArr, sb, cArr2);
        }
        if (cZaj == 'n') {
            zab(bufferedReader, zaf);
            return null;
        }
        throw new ParseException("Expected string");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean zaa(BufferedReader bufferedReader, boolean z8) throws ParseException, IOException {
        while (true) {
            char cZaj = zaj(bufferedReader);
            if (cZaj != '\"') {
                if (cZaj == 'f') {
                    zab(bufferedReader, z8 ? zaj : zai);
                    return false;
                }
                if (cZaj == 'n') {
                    zab(bufferedReader, zaf);
                    return false;
                }
                if (cZaj == 't') {
                    zab(bufferedReader, z8 ? zah : zag);
                    return true;
                }
                StringBuilder sb = new StringBuilder(19);
                sb.append("Unexpected token: ");
                sb.append(cZaj);
                throw new ParseException(sb.toString());
            }
            if (z8) {
                throw new ParseException("No boolean value found in string");
            }
            z8 = true;
        }
    }

    private final <T extends FastJsonResponse> ArrayList<T> zaa(BufferedReader bufferedReader, FastJsonResponse.Field<?, ?> field) throws ParseException, IOException {
        Elements elements = (ArrayList<T>) new ArrayList();
        char cZaj = zaj(bufferedReader);
        if (cZaj == ']') {
            zaa(5);
            return elements;
        }
        if (cZaj == 'n') {
            zab(bufferedReader, zaf);
            zaa(5);
            return null;
        }
        if (cZaj == '{') {
            this.zal.push(1);
            while (true) {
                try {
                    FastJsonResponse fastJsonResponseZac = field.zac();
                    if (!zaa(bufferedReader, fastJsonResponseZac)) {
                        return elements;
                    }
                    elements.add(fastJsonResponseZac);
                    char cZaj2 = zaj(bufferedReader);
                    if (cZaj2 != ',') {
                        if (cZaj2 == ']') {
                            zaa(5);
                            return elements;
                        }
                        StringBuilder sb = new StringBuilder(19);
                        sb.append("Unexpected token: ");
                        sb.append(cZaj2);
                        throw new ParseException(sb.toString());
                    }
                    if (zaj(bufferedReader) == '{') {
                        this.zal.push(1);
                    } else {
                        throw new ParseException("Expected start of next object in array");
                    }
                } catch (IllegalAccessException e9) {
                    throw new ParseException("Error instantiating inner object", e9);
                } catch (InstantiationException e10) {
                    throw new ParseException("Error instantiating inner object", e10);
                }
            }
        } else {
            StringBuilder sb2 = new StringBuilder(19);
            sb2.append("Unexpected token: ");
            sb2.append(cZaj);
            throw new ParseException(sb2.toString());
        }
    }

    private final int zaa(BufferedReader bufferedReader, char[] cArr) throws ParseException, IOException {
        int i9;
        char cZaj = zaj(bufferedReader);
        if (cZaj == 0) {
            throw new ParseException("Unexpected EOF");
        }
        if (cZaj == ',') {
            throw new ParseException("Missing value");
        }
        if (cZaj == 'n') {
            zab(bufferedReader, zaf);
            return 0;
        }
        bufferedReader.mark(UserMetadata.MAX_ATTRIBUTE_SIZE);
        if (cZaj == '\"') {
            i9 = 0;
            boolean z8 = false;
            while (i9 < cArr.length && bufferedReader.read(cArr, i9, 1) != -1) {
                char c9 = cArr[i9];
                if (Character.isISOControl(c9)) {
                    throw new ParseException("Unexpected control character while reading string");
                }
                if (c9 == '\"' && !z8) {
                    bufferedReader.reset();
                    bufferedReader.skip(i9 + 1);
                    return i9;
                }
                z8 = c9 == '\\' ? !z8 : false;
                i9++;
            }
        } else {
            cArr[0] = cZaj;
            i9 = 1;
            while (i9 < cArr.length && bufferedReader.read(cArr, i9, 1) != -1) {
                char c10 = cArr[i9];
                if (c10 == '}' || c10 == ',' || Character.isWhitespace(c10) || cArr[i9] == ']') {
                    bufferedReader.reset();
                    bufferedReader.skip(i9 - 1);
                    cArr[i9] = 0;
                    return i9;
                }
                i9++;
            }
        }
        if (i9 == cArr.length) {
            throw new ParseException("Absurdly long value");
        }
        throw new ParseException("Unexpected EOF");
    }

    private final void zaa(int i9) throws ParseException {
        if (!this.zal.isEmpty()) {
            int iIntValue = this.zal.pop().intValue();
            if (iIntValue == i9) {
                return;
            }
            StringBuilder sb = new StringBuilder(46);
            sb.append("Expected state ");
            sb.append(i9);
            sb.append(" but had ");
            sb.append(iIntValue);
            throw new ParseException(sb.toString());
        }
        StringBuilder sb2 = new StringBuilder(46);
        sb2.append("Expected state ");
        sb2.append(i9);
        sb2.append(" but had empty stack");
        throw new ParseException(sb2.toString());
    }
}
