package com.google.android.gms.common.server.response;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RecentlyNullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.server.response.FastJsonResponse;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.common.util.JsonUtils;
import com.google.android.gms.common.util.MapUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@VisibleForTesting
@KeepForSdk
@SafeParcelable.Class(creator = "SafeParcelResponseCreator")
/* loaded from: classes2.dex */
public class SafeParcelResponse extends FastSafeParcelableJsonResponse {

    @RecentlyNonNull
    @KeepForSdk
    public static final Parcelable.Creator<SafeParcelResponse> CREATOR = new zap();

    @SafeParcelable.VersionField(getter = "getVersionCode", m17523id = 1)
    private final int zaa;

    @SafeParcelable.Field(getter = "getParcel", m17520id = 2)
    private final Parcel zab;
    private final int zac;

    @SafeParcelable.Field(getter = "getFieldMappingDictionary", m17520id = 3)
    private final zal zad;
    private final String zae;
    private int zaf;
    private int zag;

    public SafeParcelResponse(zal zalVar, String str) {
        this.zaa = 1;
        this.zab = Parcel.obtain();
        this.zac = 0;
        this.zad = (zal) Preconditions.checkNotNull(zalVar);
        this.zae = (String) Preconditions.checkNotNull(str);
        this.zaf = 0;
    }

    @RecentlyNonNull
    @KeepForSdk
    public static <T extends FastJsonResponse & SafeParcelable> SafeParcelResponse from(@RecentlyNonNull T t8) {
        String str = (String) Preconditions.checkNotNull(t8.getClass().getCanonicalName());
        zal zalVar = new zal(t8.getClass());
        zaa(zalVar, t8);
        zalVar.zab();
        zalVar.zaa();
        return new SafeParcelResponse(t8, zalVar, str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static void zaa(zal zalVar, FastJsonResponse fastJsonResponse) {
        Class<?> cls = fastJsonResponse.getClass();
        if (zalVar.zaa((Class<? extends FastJsonResponse>) cls)) {
            return;
        }
        Map<String, FastJsonResponse.Field<?, ?>> fieldMappings = fastJsonResponse.getFieldMappings();
        zalVar.zaa(cls, fieldMappings);
        Iterator<String> it = fieldMappings.keySet().iterator();
        while (it.hasNext()) {
            FastJsonResponse.Field<?, ?> field = fieldMappings.get(it.next());
            Class<? extends FastJsonResponse> cls2 = field.zag;
            if (cls2 != null) {
                try {
                    zaa(zalVar, cls2.newInstance());
                } catch (IllegalAccessException e9) {
                    String strValueOf = String.valueOf(((Class) Preconditions.checkNotNull(field.zag)).getCanonicalName());
                    throw new IllegalStateException(strValueOf.length() != 0 ? "Could not access object of type ".concat(strValueOf) : new String("Could not access object of type "), e9);
                } catch (InstantiationException e10) {
                    String strValueOf2 = String.valueOf(((Class) Preconditions.checkNotNull(field.zag)).getCanonicalName());
                    throw new IllegalStateException(strValueOf2.length() != 0 ? "Could not instantiate an object of type ".concat(strValueOf2) : new String("Could not instantiate an object of type "), e10);
                }
            }
        }
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    public <T extends FastJsonResponse> void addConcreteTypeArrayInternal(@RecentlyNonNull FastJsonResponse.Field<?, ?> field, @RecentlyNonNull String str, ArrayList<T> arrayList) {
        zaa(field);
        ArrayList arrayList2 = new ArrayList();
        ((ArrayList) Preconditions.checkNotNull(arrayList)).size();
        int size = arrayList.size();
        int i9 = 0;
        while (i9 < size) {
            T t8 = arrayList.get(i9);
            i9++;
            arrayList2.add(((SafeParcelResponse) t8).zaa());
        }
        SafeParcelWriter.writeParcelList(this.zab, field.getSafeParcelableFieldId(), arrayList2, true);
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    public <T extends FastJsonResponse> void addConcreteTypeInternal(@RecentlyNonNull FastJsonResponse.Field<?, ?> field, @RecentlyNonNull String str, @RecentlyNonNull T t8) {
        zaa(field);
        SafeParcelWriter.writeParcel(this.zab, field.getSafeParcelableFieldId(), ((SafeParcelResponse) t8).zaa(), true);
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    @RecentlyNullable
    public Map<String, FastJsonResponse.Field<?, ?>> getFieldMappings() {
        zal zalVar = this.zad;
        if (zalVar == null) {
            return null;
        }
        return zalVar.zaa((String) Preconditions.checkNotNull(this.zae));
    }

    @Override // com.google.android.gms.common.server.response.FastSafeParcelableJsonResponse, com.google.android.gms.common.server.response.FastJsonResponse
    @RecentlyNonNull
    public Object getValueObject(@RecentlyNonNull String str) {
        throw new UnsupportedOperationException("Converting to JSON does not require this method.");
    }

    @Override // com.google.android.gms.common.server.response.FastSafeParcelableJsonResponse, com.google.android.gms.common.server.response.FastJsonResponse
    @RecentlyNonNull
    public boolean isPrimitiveFieldSet(@RecentlyNonNull String str) {
        throw new UnsupportedOperationException("Converting to JSON does not require this method.");
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    public void setBooleanInternal(@RecentlyNonNull FastJsonResponse.Field<?, ?> field, @RecentlyNonNull String str, @RecentlyNonNull boolean z8) {
        zaa(field);
        SafeParcelWriter.writeBoolean(this.zab, field.getSafeParcelableFieldId(), z8);
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    public void setDecodedBytesInternal(@RecentlyNonNull FastJsonResponse.Field<?, ?> field, @RecentlyNonNull String str, byte[] bArr) {
        zaa(field);
        SafeParcelWriter.writeByteArray(this.zab, field.getSafeParcelableFieldId(), bArr, true);
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    public void setIntegerInternal(@RecentlyNonNull FastJsonResponse.Field<?, ?> field, @RecentlyNonNull String str, @RecentlyNonNull int i9) {
        zaa(field);
        SafeParcelWriter.writeInt(this.zab, field.getSafeParcelableFieldId(), i9);
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    public void setLongInternal(@RecentlyNonNull FastJsonResponse.Field<?, ?> field, @RecentlyNonNull String str, @RecentlyNonNull long j9) {
        zaa(field);
        SafeParcelWriter.writeLong(this.zab, field.getSafeParcelableFieldId(), j9);
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    public void setStringInternal(@RecentlyNonNull FastJsonResponse.Field<?, ?> field, @RecentlyNonNull String str, String str2) {
        zaa(field);
        SafeParcelWriter.writeString(this.zab, field.getSafeParcelableFieldId(), str2, true);
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    public void setStringMapInternal(@RecentlyNonNull FastJsonResponse.Field<?, ?> field, @RecentlyNonNull String str, Map<String, String> map) {
        zaa(field);
        Bundle bundle = new Bundle();
        for (String str2 : ((Map) Preconditions.checkNotNull(map)).keySet()) {
            bundle.putString(str2, map.get(str2));
        }
        SafeParcelWriter.writeBundle(this.zab, field.getSafeParcelableFieldId(), bundle, true);
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    public void setStringsInternal(@RecentlyNonNull FastJsonResponse.Field<?, ?> field, @RecentlyNonNull String str, ArrayList<String> arrayList) {
        zaa(field);
        int size = ((ArrayList) Preconditions.checkNotNull(arrayList)).size();
        String[] strArr = new String[size];
        for (int i9 = 0; i9 < size; i9++) {
            strArr[i9] = arrayList.get(i9);
        }
        SafeParcelWriter.writeStringArray(this.zab, field.getSafeParcelableFieldId(), strArr, true);
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    @RecentlyNonNull
    public String toString() {
        Preconditions.checkNotNull(this.zad, "Cannot convert to JSON on client side.");
        Parcel parcelZaa = zaa();
        parcelZaa.setDataPosition(0);
        StringBuilder sb = new StringBuilder(100);
        zaa(sb, (Map<String, FastJsonResponse.Field<?, ?>>) Preconditions.checkNotNull(this.zad.zaa((String) Preconditions.checkNotNull(this.zae))), parcelZaa);
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@RecentlyNonNull Parcel parcel, @RecentlyNonNull int i9) {
        zal zalVar;
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zaa);
        SafeParcelWriter.writeParcel(parcel, 2, zaa(), false);
        int i10 = this.zac;
        if (i10 == 0) {
            zalVar = null;
        } else {
            if (i10 != 1 && i10 != 2) {
                int i11 = this.zac;
                StringBuilder sb = new StringBuilder(34);
                sb.append("Invalid creation type: ");
                sb.append(i11);
                throw new IllegalStateException(sb.toString());
            }
            zalVar = this.zad;
        }
        SafeParcelWriter.writeParcelable(parcel, 3, zalVar, i9, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    public final void zab(@RecentlyNonNull FastJsonResponse.Field<?, ?> field, @RecentlyNonNull String str, ArrayList<BigInteger> arrayList) {
        zaa(field);
        int size = ((ArrayList) Preconditions.checkNotNull(arrayList)).size();
        BigInteger[] bigIntegerArr = new BigInteger[size];
        for (int i9 = 0; i9 < size; i9++) {
            bigIntegerArr[i9] = arrayList.get(i9);
        }
        SafeParcelWriter.writeBigIntegerArray(this.zab, field.getSafeParcelableFieldId(), bigIntegerArr, true);
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    public final void zac(@RecentlyNonNull FastJsonResponse.Field<?, ?> field, @RecentlyNonNull String str, ArrayList<Long> arrayList) {
        zaa(field);
        int size = ((ArrayList) Preconditions.checkNotNull(arrayList)).size();
        long[] jArr = new long[size];
        for (int i9 = 0; i9 < size; i9++) {
            jArr[i9] = arrayList.get(i9).longValue();
        }
        SafeParcelWriter.writeLongArray(this.zab, field.getSafeParcelableFieldId(), jArr, true);
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    public final void zad(@RecentlyNonNull FastJsonResponse.Field<?, ?> field, @RecentlyNonNull String str, ArrayList<Float> arrayList) {
        zaa(field);
        int size = ((ArrayList) Preconditions.checkNotNull(arrayList)).size();
        float[] fArr = new float[size];
        for (int i9 = 0; i9 < size; i9++) {
            fArr[i9] = arrayList.get(i9).floatValue();
        }
        SafeParcelWriter.writeFloatArray(this.zab, field.getSafeParcelableFieldId(), fArr, true);
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    public final void zae(@RecentlyNonNull FastJsonResponse.Field<?, ?> field, @RecentlyNonNull String str, ArrayList<Double> arrayList) {
        zaa(field);
        int size = ((ArrayList) Preconditions.checkNotNull(arrayList)).size();
        double[] dArr = new double[size];
        for (int i9 = 0; i9 < size; i9++) {
            dArr[i9] = arrayList.get(i9).doubleValue();
        }
        SafeParcelWriter.writeDoubleArray(this.zab, field.getSafeParcelableFieldId(), dArr, true);
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    public final void zaf(@RecentlyNonNull FastJsonResponse.Field<?, ?> field, @RecentlyNonNull String str, ArrayList<BigDecimal> arrayList) {
        zaa(field);
        int size = ((ArrayList) Preconditions.checkNotNull(arrayList)).size();
        BigDecimal[] bigDecimalArr = new BigDecimal[size];
        for (int i9 = 0; i9 < size; i9++) {
            bigDecimalArr[i9] = arrayList.get(i9);
        }
        SafeParcelWriter.writeBigDecimalArray(this.zab, field.getSafeParcelableFieldId(), bigDecimalArr, true);
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    public final void zag(@RecentlyNonNull FastJsonResponse.Field<?, ?> field, @RecentlyNonNull String str, ArrayList<Boolean> arrayList) {
        zaa(field);
        int size = ((ArrayList) Preconditions.checkNotNull(arrayList)).size();
        boolean[] zArr = new boolean[size];
        for (int i9 = 0; i9 < size; i9++) {
            zArr[i9] = arrayList.get(i9).booleanValue();
        }
        SafeParcelWriter.writeBooleanArray(this.zab, field.getSafeParcelableFieldId(), zArr, true);
    }

    private SafeParcelResponse(SafeParcelable safeParcelable, zal zalVar, String str) {
        this.zaa = 1;
        Parcel parcelObtain = Parcel.obtain();
        this.zab = parcelObtain;
        safeParcelable.writeToParcel(parcelObtain, 0);
        this.zac = 1;
        this.zad = (zal) Preconditions.checkNotNull(zalVar);
        this.zae = (String) Preconditions.checkNotNull(str);
        this.zaf = 2;
    }

    @SafeParcelable.Constructor
    public SafeParcelResponse(@SafeParcelable.Param(m17521id = 1) int i9, @SafeParcelable.Param(m17521id = 2) Parcel parcel, @SafeParcelable.Param(m17521id = 3) zal zalVar) {
        this.zaa = i9;
        this.zab = (Parcel) Preconditions.checkNotNull(parcel);
        this.zac = 2;
        this.zad = zalVar;
        if (zalVar == null) {
            this.zae = null;
        } else {
            this.zae = zalVar.zac();
        }
        this.zaf = 2;
    }

    private final Parcel zaa() {
        int i9 = this.zaf;
        if (i9 == 0) {
            this.zag = SafeParcelWriter.beginObjectHeader(this.zab);
        } else {
            if (i9 == 1) {
            }
            return this.zab;
        }
        SafeParcelWriter.finishObjectHeader(this.zab, this.zag);
        this.zaf = 2;
        return this.zab;
    }

    private final void zaa(FastJsonResponse.Field<?, ?> field) {
        if (field.zaf != -1) {
            Parcel parcel = this.zab;
            if (parcel != null) {
                int i9 = this.zaf;
                if (i9 == 0) {
                    this.zag = SafeParcelWriter.beginObjectHeader(parcel);
                    this.zaf = 1;
                    return;
                } else {
                    if (i9 != 1) {
                        if (i9 != 2) {
                            throw new IllegalStateException("Unknown parse state in SafeParcelResponse.");
                        }
                        throw new IllegalStateException("Attempted to parse JSON with a SafeParcelResponse object that is already filled with data.");
                    }
                    return;
                }
            }
            throw new IllegalStateException("Internal Parcel object is null.");
        }
        throw new IllegalStateException("Field does not have a valid safe parcelable field id.");
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    public final void zaa(@RecentlyNonNull FastJsonResponse.Field<?, ?> field, @RecentlyNonNull String str, ArrayList<Integer> arrayList) {
        zaa(field);
        int size = ((ArrayList) Preconditions.checkNotNull(arrayList)).size();
        int[] iArr = new int[size];
        for (int i9 = 0; i9 < size; i9++) {
            iArr[i9] = arrayList.get(i9).intValue();
        }
        SafeParcelWriter.writeIntArray(this.zab, field.getSafeParcelableFieldId(), iArr, true);
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    public final void zaa(@RecentlyNonNull FastJsonResponse.Field<?, ?> field, @RecentlyNonNull String str, BigInteger bigInteger) {
        zaa(field);
        SafeParcelWriter.writeBigInteger(this.zab, field.getSafeParcelableFieldId(), bigInteger, true);
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    public final void zaa(@RecentlyNonNull FastJsonResponse.Field<?, ?> field, @RecentlyNonNull String str, @RecentlyNonNull float f9) {
        zaa(field);
        SafeParcelWriter.writeFloat(this.zab, field.getSafeParcelableFieldId(), f9);
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    public final void zaa(@RecentlyNonNull FastJsonResponse.Field<?, ?> field, @RecentlyNonNull String str, @RecentlyNonNull double d9) {
        zaa(field);
        SafeParcelWriter.writeDouble(this.zab, field.getSafeParcelableFieldId(), d9);
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    public final void zaa(@RecentlyNonNull FastJsonResponse.Field<?, ?> field, @RecentlyNonNull String str, BigDecimal bigDecimal) {
        zaa(field);
        SafeParcelWriter.writeBigDecimal(this.zab, field.getSafeParcelableFieldId(), bigDecimal, true);
    }

    private final void zaa(StringBuilder sb, Map<String, FastJsonResponse.Field<?, ?>> map, Parcel parcel) {
        SparseArray sparseArray = new SparseArray();
        for (Map.Entry<String, FastJsonResponse.Field<?, ?>> entry : map.entrySet()) {
            sparseArray.put(entry.getValue().getSafeParcelableFieldId(), entry);
        }
        sb.append('{');
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        boolean z8 = false;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            Map.Entry entry2 = (Map.Entry) sparseArray.get(SafeParcelReader.getFieldId(header));
            if (entry2 != null) {
                if (z8) {
                    sb.append(",");
                }
                String str = (String) entry2.getKey();
                FastJsonResponse.Field field = (FastJsonResponse.Field) entry2.getValue();
                sb.append("\"");
                sb.append(str);
                sb.append("\":");
                if (field.zab()) {
                    switch (field.zac) {
                        case 0:
                            zaa(sb, (FastJsonResponse.Field<?, ?>) field, FastJsonResponse.zaa(field, Integer.valueOf(SafeParcelReader.readInt(parcel, header))));
                            break;
                        case 1:
                            zaa(sb, (FastJsonResponse.Field<?, ?>) field, FastJsonResponse.zaa(field, (Object) SafeParcelReader.createBigInteger(parcel, header)));
                            break;
                        case 2:
                            zaa(sb, (FastJsonResponse.Field<?, ?>) field, FastJsonResponse.zaa(field, Long.valueOf(SafeParcelReader.readLong(parcel, header))));
                            break;
                        case 3:
                            zaa(sb, (FastJsonResponse.Field<?, ?>) field, FastJsonResponse.zaa(field, Float.valueOf(SafeParcelReader.readFloat(parcel, header))));
                            break;
                        case 4:
                            zaa(sb, (FastJsonResponse.Field<?, ?>) field, FastJsonResponse.zaa(field, Double.valueOf(SafeParcelReader.readDouble(parcel, header))));
                            break;
                        case 5:
                            zaa(sb, (FastJsonResponse.Field<?, ?>) field, FastJsonResponse.zaa(field, (Object) SafeParcelReader.createBigDecimal(parcel, header)));
                            break;
                        case 6:
                            zaa(sb, (FastJsonResponse.Field<?, ?>) field, FastJsonResponse.zaa(field, Boolean.valueOf(SafeParcelReader.readBoolean(parcel, header))));
                            break;
                        case 7:
                            zaa(sb, (FastJsonResponse.Field<?, ?>) field, FastJsonResponse.zaa(field, (Object) SafeParcelReader.createString(parcel, header)));
                            break;
                        case 8:
                        case 9:
                            zaa(sb, (FastJsonResponse.Field<?, ?>) field, FastJsonResponse.zaa(field, (Object) SafeParcelReader.createByteArray(parcel, header)));
                            break;
                        case 10:
                            Bundle bundleCreateBundle = SafeParcelReader.createBundle(parcel, header);
                            HashMap map2 = new HashMap();
                            for (String str2 : bundleCreateBundle.keySet()) {
                                map2.put(str2, (String) Preconditions.checkNotNull(bundleCreateBundle.getString(str2)));
                            }
                            zaa(sb, (FastJsonResponse.Field<?, ?>) field, FastJsonResponse.zaa(field, (Object) map2));
                            break;
                        case 11:
                            throw new IllegalArgumentException("Method does not accept concrete type.");
                        default:
                            int i9 = field.zac;
                            StringBuilder sb2 = new StringBuilder(36);
                            sb2.append("Unknown field out type = ");
                            sb2.append(i9);
                            throw new IllegalArgumentException(sb2.toString());
                    }
                } else if (field.zad) {
                    sb.append("[");
                    switch (field.zac) {
                        case 0:
                            ArrayUtils.writeArray(sb, SafeParcelReader.createIntArray(parcel, header));
                            break;
                        case 1:
                            ArrayUtils.writeArray(sb, SafeParcelReader.createBigIntegerArray(parcel, header));
                            break;
                        case 2:
                            ArrayUtils.writeArray(sb, SafeParcelReader.createLongArray(parcel, header));
                            break;
                        case 3:
                            ArrayUtils.writeArray(sb, SafeParcelReader.createFloatArray(parcel, header));
                            break;
                        case 4:
                            ArrayUtils.writeArray(sb, SafeParcelReader.createDoubleArray(parcel, header));
                            break;
                        case 5:
                            ArrayUtils.writeArray(sb, SafeParcelReader.createBigDecimalArray(parcel, header));
                            break;
                        case 6:
                            ArrayUtils.writeArray(sb, SafeParcelReader.createBooleanArray(parcel, header));
                            break;
                        case 7:
                            ArrayUtils.writeStringArray(sb, SafeParcelReader.createStringArray(parcel, header));
                            break;
                        case 8:
                        case 9:
                        case 10:
                            throw new UnsupportedOperationException("List of type BASE64, BASE64_URL_SAFE, or STRING_MAP is not supported");
                        case 11:
                            Parcel[] parcelArrCreateParcelArray = SafeParcelReader.createParcelArray(parcel, header);
                            int length = parcelArrCreateParcelArray.length;
                            for (int i10 = 0; i10 < length; i10++) {
                                if (i10 > 0) {
                                    sb.append(",");
                                }
                                parcelArrCreateParcelArray[i10].setDataPosition(0);
                                zaa(sb, field.zad(), parcelArrCreateParcelArray[i10]);
                            }
                            break;
                        default:
                            throw new IllegalStateException("Unknown field type out.");
                    }
                    sb.append("]");
                } else {
                    switch (field.zac) {
                        case 0:
                            sb.append(SafeParcelReader.readInt(parcel, header));
                            break;
                        case 1:
                            sb.append(SafeParcelReader.createBigInteger(parcel, header));
                            break;
                        case 2:
                            sb.append(SafeParcelReader.readLong(parcel, header));
                            break;
                        case 3:
                            sb.append(SafeParcelReader.readFloat(parcel, header));
                            break;
                        case 4:
                            sb.append(SafeParcelReader.readDouble(parcel, header));
                            break;
                        case 5:
                            sb.append(SafeParcelReader.createBigDecimal(parcel, header));
                            break;
                        case 6:
                            sb.append(SafeParcelReader.readBoolean(parcel, header));
                            break;
                        case 7:
                            String strCreateString = SafeParcelReader.createString(parcel, header);
                            sb.append("\"");
                            sb.append(JsonUtils.escapeString(strCreateString));
                            sb.append("\"");
                            break;
                        case 8:
                            byte[] bArrCreateByteArray = SafeParcelReader.createByteArray(parcel, header);
                            sb.append("\"");
                            sb.append(Base64Utils.encode(bArrCreateByteArray));
                            sb.append("\"");
                            break;
                        case 9:
                            byte[] bArrCreateByteArray2 = SafeParcelReader.createByteArray(parcel, header);
                            sb.append("\"");
                            sb.append(Base64Utils.encodeUrlSafe(bArrCreateByteArray2));
                            sb.append("\"");
                            break;
                        case 10:
                            Bundle bundleCreateBundle2 = SafeParcelReader.createBundle(parcel, header);
                            Set<String> setKeySet = bundleCreateBundle2.keySet();
                            sb.append("{");
                            boolean z9 = true;
                            for (String str3 : setKeySet) {
                                if (!z9) {
                                    sb.append(",");
                                }
                                sb.append("\"");
                                sb.append(str3);
                                sb.append("\"");
                                sb.append(":");
                                sb.append("\"");
                                sb.append(JsonUtils.escapeString(bundleCreateBundle2.getString(str3)));
                                sb.append("\"");
                                z9 = false;
                            }
                            sb.append("}");
                            break;
                        case 11:
                            Parcel parcelCreateParcel = SafeParcelReader.createParcel(parcel, header);
                            parcelCreateParcel.setDataPosition(0);
                            zaa(sb, field.zad(), parcelCreateParcel);
                            break;
                        default:
                            throw new IllegalStateException("Unknown field type out");
                    }
                }
                z8 = true;
            }
        }
        if (parcel.dataPosition() == iValidateObjectHeader) {
            sb.append('}');
            return;
        }
        StringBuilder sb3 = new StringBuilder(37);
        sb3.append("Overread allowed size end=");
        sb3.append(iValidateObjectHeader);
        throw new SafeParcelReader.ParseException(sb3.toString(), parcel);
    }

    private static void zaa(StringBuilder sb, FastJsonResponse.Field<?, ?> field, Object obj) {
        if (field.zab) {
            ArrayList arrayList = (ArrayList) obj;
            sb.append("[");
            int size = arrayList.size();
            for (int i9 = 0; i9 < size; i9++) {
                if (i9 != 0) {
                    sb.append(",");
                }
                zaa(sb, field.zaa, arrayList.get(i9));
            }
            sb.append("]");
            return;
        }
        zaa(sb, field.zaa, obj);
    }

    private static void zaa(StringBuilder sb, int i9, Object obj) {
        switch (i9) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                sb.append(obj);
                return;
            case 7:
                sb.append("\"");
                sb.append(JsonUtils.escapeString(Preconditions.checkNotNull(obj).toString()));
                sb.append("\"");
                return;
            case 8:
                sb.append("\"");
                sb.append(Base64Utils.encode((byte[]) obj));
                sb.append("\"");
                return;
            case 9:
                sb.append("\"");
                sb.append(Base64Utils.encodeUrlSafe((byte[]) obj));
                sb.append("\"");
                return;
            case 10:
                MapUtils.writeStringMapToJson(sb, (HashMap) Preconditions.checkNotNull(obj));
                return;
            case 11:
                throw new IllegalArgumentException("Method does not accept concrete type.");
            default:
                StringBuilder sb2 = new StringBuilder(26);
                sb2.append("Unknown type = ");
                sb2.append(i9);
                throw new IllegalArgumentException(sb2.toString());
        }
    }
}
