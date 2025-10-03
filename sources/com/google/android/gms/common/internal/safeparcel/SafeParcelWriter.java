package com.google.android.gms.common.internal.safeparcel;

import android.app.PendingIntent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.util.SparseLongArray;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/* loaded from: classes2.dex */
public class SafeParcelWriter {
    private SafeParcelWriter() {
    }

    public static int beginObjectHeader(Parcel parcel) {
        return zza(parcel, 20293);
    }

    public static void finishObjectHeader(Parcel parcel, int i9) {
        zzb(parcel, i9);
    }

    public static void writeBigDecimal(Parcel parcel, int i9, BigDecimal bigDecimal, boolean z8) {
        if (bigDecimal == null) {
            if (z8) {
                zzc(parcel, i9, 0);
            }
        } else {
            int iZza = zza(parcel, i9);
            parcel.writeByteArray(bigDecimal.unscaledValue().toByteArray());
            parcel.writeInt(bigDecimal.scale());
            zzb(parcel, iZza);
        }
    }

    public static void writeBigDecimalArray(Parcel parcel, int i9, BigDecimal[] bigDecimalArr, boolean z8) {
        if (bigDecimalArr == null) {
            if (z8) {
                zzc(parcel, i9, 0);
                return;
            }
            return;
        }
        int iZza = zza(parcel, i9);
        int length = bigDecimalArr.length;
        parcel.writeInt(length);
        for (int i10 = 0; i10 < length; i10++) {
            parcel.writeByteArray(bigDecimalArr[i10].unscaledValue().toByteArray());
            parcel.writeInt(bigDecimalArr[i10].scale());
        }
        zzb(parcel, iZza);
    }

    public static void writeBigInteger(Parcel parcel, int i9, BigInteger bigInteger, boolean z8) {
        if (bigInteger == null) {
            if (z8) {
                zzc(parcel, i9, 0);
            }
        } else {
            int iZza = zza(parcel, i9);
            parcel.writeByteArray(bigInteger.toByteArray());
            zzb(parcel, iZza);
        }
    }

    public static void writeBigIntegerArray(Parcel parcel, int i9, BigInteger[] bigIntegerArr, boolean z8) {
        if (bigIntegerArr == null) {
            if (z8) {
                zzc(parcel, i9, 0);
                return;
            }
            return;
        }
        int iZza = zza(parcel, i9);
        parcel.writeInt(bigIntegerArr.length);
        for (BigInteger bigInteger : bigIntegerArr) {
            parcel.writeByteArray(bigInteger.toByteArray());
        }
        zzb(parcel, iZza);
    }

    public static void writeBoolean(Parcel parcel, int i9, boolean z8) {
        zzc(parcel, i9, 4);
        parcel.writeInt(z8 ? 1 : 0);
    }

    public static void writeBooleanArray(Parcel parcel, int i9, boolean[] zArr, boolean z8) {
        if (zArr == null) {
            if (z8) {
                zzc(parcel, i9, 0);
            }
        } else {
            int iZza = zza(parcel, i9);
            parcel.writeBooleanArray(zArr);
            zzb(parcel, iZza);
        }
    }

    public static void writeBooleanList(Parcel parcel, int i9, List<Boolean> list, boolean z8) {
        if (list == null) {
            if (z8) {
                zzc(parcel, i9, 0);
                return;
            }
            return;
        }
        int iZza = zza(parcel, i9);
        int size = list.size();
        parcel.writeInt(size);
        for (int i10 = 0; i10 < size; i10++) {
            parcel.writeInt(list.get(i10).booleanValue() ? 1 : 0);
        }
        zzb(parcel, iZza);
    }

    public static void writeBooleanObject(Parcel parcel, int i9, Boolean bool, boolean z8) {
        if (bool != null) {
            zzc(parcel, i9, 4);
            parcel.writeInt(bool.booleanValue() ? 1 : 0);
        } else if (z8) {
            zzc(parcel, i9, 0);
        }
    }

    public static void writeBundle(Parcel parcel, int i9, Bundle bundle, boolean z8) {
        if (bundle == null) {
            if (z8) {
                zzc(parcel, i9, 0);
            }
        } else {
            int iZza = zza(parcel, i9);
            parcel.writeBundle(bundle);
            zzb(parcel, iZza);
        }
    }

    public static void writeByte(Parcel parcel, int i9, byte b9) {
        zzc(parcel, i9, 4);
        parcel.writeInt(b9);
    }

    public static void writeByteArray(Parcel parcel, int i9, byte[] bArr, boolean z8) {
        if (bArr == null) {
            if (z8) {
                zzc(parcel, i9, 0);
            }
        } else {
            int iZza = zza(parcel, i9);
            parcel.writeByteArray(bArr);
            zzb(parcel, iZza);
        }
    }

    public static void writeByteArrayArray(Parcel parcel, int i9, byte[][] bArr, boolean z8) {
        if (bArr == null) {
            if (z8) {
                zzc(parcel, i9, 0);
                return;
            }
            return;
        }
        int iZza = zza(parcel, i9);
        parcel.writeInt(bArr.length);
        for (byte[] bArr2 : bArr) {
            parcel.writeByteArray(bArr2);
        }
        zzb(parcel, iZza);
    }

    public static void writeByteArraySparseArray(Parcel parcel, int i9, SparseArray<byte[]> sparseArray, boolean z8) {
        if (sparseArray == null) {
            if (z8) {
                zzc(parcel, i9, 0);
                return;
            }
            return;
        }
        int iZza = zza(parcel, i9);
        int size = sparseArray.size();
        parcel.writeInt(size);
        for (int i10 = 0; i10 < size; i10++) {
            parcel.writeInt(sparseArray.keyAt(i10));
            parcel.writeByteArray(sparseArray.valueAt(i10));
        }
        zzb(parcel, iZza);
    }

    public static void writeChar(Parcel parcel, int i9, char c9) {
        zzc(parcel, i9, 4);
        parcel.writeInt(c9);
    }

    public static void writeCharArray(Parcel parcel, int i9, char[] cArr, boolean z8) {
        if (cArr == null) {
            if (z8) {
                zzc(parcel, i9, 0);
            }
        } else {
            int iZza = zza(parcel, i9);
            parcel.writeCharArray(cArr);
            zzb(parcel, iZza);
        }
    }

    public static void writeDouble(Parcel parcel, int i9, double d9) {
        zzc(parcel, i9, 8);
        parcel.writeDouble(d9);
    }

    public static void writeDoubleArray(Parcel parcel, int i9, double[] dArr, boolean z8) {
        if (dArr == null) {
            if (z8) {
                zzc(parcel, i9, 0);
            }
        } else {
            int iZza = zza(parcel, i9);
            parcel.writeDoubleArray(dArr);
            zzb(parcel, iZza);
        }
    }

    public static void writeDoubleList(Parcel parcel, int i9, List<Double> list, boolean z8) {
        if (list == null) {
            if (z8) {
                zzc(parcel, i9, 0);
                return;
            }
            return;
        }
        int iZza = zza(parcel, i9);
        int size = list.size();
        parcel.writeInt(size);
        for (int i10 = 0; i10 < size; i10++) {
            parcel.writeDouble(list.get(i10).doubleValue());
        }
        zzb(parcel, iZza);
    }

    public static void writeDoubleObject(Parcel parcel, int i9, Double d9, boolean z8) {
        if (d9 != null) {
            zzc(parcel, i9, 8);
            parcel.writeDouble(d9.doubleValue());
        } else if (z8) {
            zzc(parcel, i9, 0);
        }
    }

    public static void writeDoubleSparseArray(Parcel parcel, int i9, SparseArray<Double> sparseArray, boolean z8) {
        if (sparseArray == null) {
            if (z8) {
                zzc(parcel, i9, 0);
                return;
            }
            return;
        }
        int iZza = zza(parcel, i9);
        int size = sparseArray.size();
        parcel.writeInt(size);
        for (int i10 = 0; i10 < size; i10++) {
            parcel.writeInt(sparseArray.keyAt(i10));
            parcel.writeDouble(sparseArray.valueAt(i10).doubleValue());
        }
        zzb(parcel, iZza);
    }

    public static void writeFloat(Parcel parcel, int i9, float f9) {
        zzc(parcel, i9, 4);
        parcel.writeFloat(f9);
    }

    public static void writeFloatArray(Parcel parcel, int i9, float[] fArr, boolean z8) {
        if (fArr == null) {
            if (z8) {
                zzc(parcel, i9, 0);
            }
        } else {
            int iZza = zza(parcel, i9);
            parcel.writeFloatArray(fArr);
            zzb(parcel, iZza);
        }
    }

    public static void writeFloatList(Parcel parcel, int i9, List<Float> list, boolean z8) {
        if (list == null) {
            if (z8) {
                zzc(parcel, i9, 0);
                return;
            }
            return;
        }
        int iZza = zza(parcel, i9);
        int size = list.size();
        parcel.writeInt(size);
        for (int i10 = 0; i10 < size; i10++) {
            parcel.writeFloat(list.get(i10).floatValue());
        }
        zzb(parcel, iZza);
    }

    public static void writeFloatObject(Parcel parcel, int i9, Float f9, boolean z8) {
        if (f9 != null) {
            zzc(parcel, i9, 4);
            parcel.writeFloat(f9.floatValue());
        } else if (z8) {
            zzc(parcel, i9, 0);
        }
    }

    public static void writeFloatSparseArray(Parcel parcel, int i9, SparseArray<Float> sparseArray, boolean z8) {
        if (sparseArray == null) {
            if (z8) {
                zzc(parcel, i9, 0);
                return;
            }
            return;
        }
        int iZza = zza(parcel, i9);
        int size = sparseArray.size();
        parcel.writeInt(size);
        for (int i10 = 0; i10 < size; i10++) {
            parcel.writeInt(sparseArray.keyAt(i10));
            parcel.writeFloat(sparseArray.valueAt(i10).floatValue());
        }
        zzb(parcel, iZza);
    }

    public static void writeIBinder(Parcel parcel, int i9, IBinder iBinder, boolean z8) {
        if (iBinder == null) {
            if (z8) {
                zzc(parcel, i9, 0);
            }
        } else {
            int iZza = zza(parcel, i9);
            parcel.writeStrongBinder(iBinder);
            zzb(parcel, iZza);
        }
    }

    public static void writeIBinderArray(Parcel parcel, int i9, IBinder[] iBinderArr, boolean z8) {
        if (iBinderArr == null) {
            if (z8) {
                zzc(parcel, i9, 0);
            }
        } else {
            int iZza = zza(parcel, i9);
            parcel.writeBinderArray(iBinderArr);
            zzb(parcel, iZza);
        }
    }

    public static void writeIBinderList(Parcel parcel, int i9, List<IBinder> list, boolean z8) {
        if (list == null) {
            if (z8) {
                zzc(parcel, i9, 0);
            }
        } else {
            int iZza = zza(parcel, i9);
            parcel.writeBinderList(list);
            zzb(parcel, iZza);
        }
    }

    public static void writeIBinderSparseArray(Parcel parcel, int i9, SparseArray<IBinder> sparseArray, boolean z8) {
        if (sparseArray == null) {
            if (z8) {
                zzc(parcel, i9, 0);
                return;
            }
            return;
        }
        int iZza = zza(parcel, i9);
        int size = sparseArray.size();
        parcel.writeInt(size);
        for (int i10 = 0; i10 < size; i10++) {
            parcel.writeInt(sparseArray.keyAt(i10));
            parcel.writeStrongBinder(sparseArray.valueAt(i10));
        }
        zzb(parcel, iZza);
    }

    public static void writeInt(Parcel parcel, int i9, int i10) {
        zzc(parcel, i9, 4);
        parcel.writeInt(i10);
    }

    public static void writeIntArray(Parcel parcel, int i9, int[] iArr, boolean z8) {
        if (iArr == null) {
            if (z8) {
                zzc(parcel, i9, 0);
            }
        } else {
            int iZza = zza(parcel, i9);
            parcel.writeIntArray(iArr);
            zzb(parcel, iZza);
        }
    }

    public static void writeIntegerList(Parcel parcel, int i9, List<Integer> list, boolean z8) {
        if (list == null) {
            if (z8) {
                zzc(parcel, i9, 0);
                return;
            }
            return;
        }
        int iZza = zza(parcel, i9);
        int size = list.size();
        parcel.writeInt(size);
        for (int i10 = 0; i10 < size; i10++) {
            parcel.writeInt(list.get(i10).intValue());
        }
        zzb(parcel, iZza);
    }

    public static void writeIntegerObject(Parcel parcel, int i9, Integer num, boolean z8) {
        if (num != null) {
            zzc(parcel, i9, 4);
            parcel.writeInt(num.intValue());
        } else if (z8) {
            zzc(parcel, i9, 0);
        }
    }

    public static void writeList(Parcel parcel, int i9, List list, boolean z8) {
        if (list == null) {
            if (z8) {
                zzc(parcel, i9, 0);
            }
        } else {
            int iZza = zza(parcel, i9);
            parcel.writeList(list);
            zzb(parcel, iZza);
        }
    }

    public static void writeLong(Parcel parcel, int i9, long j9) {
        zzc(parcel, i9, 8);
        parcel.writeLong(j9);
    }

    public static void writeLongArray(Parcel parcel, int i9, long[] jArr, boolean z8) {
        if (jArr == null) {
            if (z8) {
                zzc(parcel, i9, 0);
            }
        } else {
            int iZza = zza(parcel, i9);
            parcel.writeLongArray(jArr);
            zzb(parcel, iZza);
        }
    }

    public static void writeLongList(Parcel parcel, int i9, List<Long> list, boolean z8) {
        if (list == null) {
            if (z8) {
                zzc(parcel, i9, 0);
                return;
            }
            return;
        }
        int iZza = zza(parcel, i9);
        int size = list.size();
        parcel.writeInt(size);
        for (int i10 = 0; i10 < size; i10++) {
            parcel.writeLong(list.get(i10).longValue());
        }
        zzb(parcel, iZza);
    }

    public static void writeLongObject(Parcel parcel, int i9, Long l9, boolean z8) {
        if (l9 != null) {
            zzc(parcel, i9, 8);
            parcel.writeLong(l9.longValue());
        } else if (z8) {
            zzc(parcel, i9, 0);
        }
    }

    public static void writeParcel(Parcel parcel, int i9, Parcel parcel2, boolean z8) {
        if (parcel2 == null) {
            if (z8) {
                zzc(parcel, i9, 0);
            }
        } else {
            int iZza = zza(parcel, i9);
            parcel.appendFrom(parcel2, 0, parcel2.dataSize());
            zzb(parcel, iZza);
        }
    }

    public static void writeParcelArray(Parcel parcel, int i9, Parcel[] parcelArr, boolean z8) {
        if (parcelArr == null) {
            if (z8) {
                zzc(parcel, i9, 0);
                return;
            }
            return;
        }
        int iZza = zza(parcel, i9);
        parcel.writeInt(parcelArr.length);
        for (Parcel parcel2 : parcelArr) {
            if (parcel2 != null) {
                parcel.writeInt(parcel2.dataSize());
                parcel.appendFrom(parcel2, 0, parcel2.dataSize());
            } else {
                parcel.writeInt(0);
            }
        }
        zzb(parcel, iZza);
    }

    public static void writeParcelList(Parcel parcel, int i9, List<Parcel> list, boolean z8) {
        if (list == null) {
            if (z8) {
                zzc(parcel, i9, 0);
                return;
            }
            return;
        }
        int iZza = zza(parcel, i9);
        int size = list.size();
        parcel.writeInt(size);
        for (int i10 = 0; i10 < size; i10++) {
            Parcel parcel2 = list.get(i10);
            if (parcel2 != null) {
                parcel.writeInt(parcel2.dataSize());
                parcel.appendFrom(parcel2, 0, parcel2.dataSize());
            } else {
                parcel.writeInt(0);
            }
        }
        zzb(parcel, iZza);
    }

    public static void writeParcelSparseArray(Parcel parcel, int i9, SparseArray<Parcel> sparseArray, boolean z8) {
        if (sparseArray == null) {
            if (z8) {
                zzc(parcel, i9, 0);
                return;
            }
            return;
        }
        int iZza = zza(parcel, i9);
        int size = sparseArray.size();
        parcel.writeInt(size);
        for (int i10 = 0; i10 < size; i10++) {
            parcel.writeInt(sparseArray.keyAt(i10));
            Parcel parcelValueAt = sparseArray.valueAt(i10);
            if (parcelValueAt != null) {
                parcel.writeInt(parcelValueAt.dataSize());
                parcel.appendFrom(parcelValueAt, 0, parcelValueAt.dataSize());
            } else {
                parcel.writeInt(0);
            }
        }
        zzb(parcel, iZza);
    }

    public static void writeParcelable(Parcel parcel, int i9, Parcelable parcelable, int i10, boolean z8) {
        if (parcelable == null) {
            if (z8) {
                zzc(parcel, i9, 0);
            }
        } else {
            int iZza = zza(parcel, i9);
            parcelable.writeToParcel(parcel, i10);
            zzb(parcel, iZza);
        }
    }

    public static void writePendingIntent(Parcel parcel, int i9, PendingIntent pendingIntent, boolean z8) {
        if (pendingIntent == null) {
            if (z8) {
                zzc(parcel, i9, 0);
            }
        } else {
            int iZza = zza(parcel, i9);
            PendingIntent.writePendingIntentOrNullToParcel(pendingIntent, parcel);
            zzb(parcel, iZza);
        }
    }

    public static void writeShort(Parcel parcel, int i9, short s8) {
        zzc(parcel, i9, 4);
        parcel.writeInt(s8);
    }

    public static void writeSparseBooleanArray(Parcel parcel, int i9, SparseBooleanArray sparseBooleanArray, boolean z8) {
        if (sparseBooleanArray == null) {
            if (z8) {
                zzc(parcel, i9, 0);
            }
        } else {
            int iZza = zza(parcel, i9);
            parcel.writeSparseBooleanArray(sparseBooleanArray);
            zzb(parcel, iZza);
        }
    }

    public static void writeSparseIntArray(Parcel parcel, int i9, SparseIntArray sparseIntArray, boolean z8) {
        if (sparseIntArray == null) {
            if (z8) {
                zzc(parcel, i9, 0);
                return;
            }
            return;
        }
        int iZza = zza(parcel, i9);
        int size = sparseIntArray.size();
        parcel.writeInt(size);
        for (int i10 = 0; i10 < size; i10++) {
            parcel.writeInt(sparseIntArray.keyAt(i10));
            parcel.writeInt(sparseIntArray.valueAt(i10));
        }
        zzb(parcel, iZza);
    }

    public static void writeSparseLongArray(Parcel parcel, int i9, SparseLongArray sparseLongArray, boolean z8) {
        if (sparseLongArray == null) {
            if (z8) {
                zzc(parcel, i9, 0);
                return;
            }
            return;
        }
        int iZza = zza(parcel, i9);
        int size = sparseLongArray.size();
        parcel.writeInt(size);
        for (int i10 = 0; i10 < size; i10++) {
            parcel.writeInt(sparseLongArray.keyAt(i10));
            parcel.writeLong(sparseLongArray.valueAt(i10));
        }
        zzb(parcel, iZza);
    }

    public static void writeString(Parcel parcel, int i9, String str, boolean z8) {
        if (str == null) {
            if (z8) {
                zzc(parcel, i9, 0);
            }
        } else {
            int iZza = zza(parcel, i9);
            parcel.writeString(str);
            zzb(parcel, iZza);
        }
    }

    public static void writeStringArray(Parcel parcel, int i9, String[] strArr, boolean z8) {
        if (strArr == null) {
            if (z8) {
                zzc(parcel, i9, 0);
            }
        } else {
            int iZza = zza(parcel, i9);
            parcel.writeStringArray(strArr);
            zzb(parcel, iZza);
        }
    }

    public static void writeStringList(Parcel parcel, int i9, List<String> list, boolean z8) {
        if (list == null) {
            if (z8) {
                zzc(parcel, i9, 0);
            }
        } else {
            int iZza = zza(parcel, i9);
            parcel.writeStringList(list);
            zzb(parcel, iZza);
        }
    }

    public static void writeStringSparseArray(Parcel parcel, int i9, SparseArray<String> sparseArray, boolean z8) {
        if (sparseArray == null) {
            if (z8) {
                zzc(parcel, i9, 0);
                return;
            }
            return;
        }
        int iZza = zza(parcel, i9);
        int size = sparseArray.size();
        parcel.writeInt(size);
        for (int i10 = 0; i10 < size; i10++) {
            parcel.writeInt(sparseArray.keyAt(i10));
            parcel.writeString(sparseArray.valueAt(i10));
        }
        zzb(parcel, iZza);
    }

    public static <T extends Parcelable> void writeTypedArray(Parcel parcel, int i9, T[] tArr, int i10, boolean z8) {
        if (tArr == null) {
            if (z8) {
                zzc(parcel, i9, 0);
                return;
            }
            return;
        }
        int iZza = zza(parcel, i9);
        parcel.writeInt(tArr.length);
        for (T t8 : tArr) {
            if (t8 == null) {
                parcel.writeInt(0);
            } else {
                zzd(parcel, t8, i10);
            }
        }
        zzb(parcel, iZza);
    }

    public static <T extends Parcelable> void writeTypedList(Parcel parcel, int i9, List<T> list, boolean z8) {
        if (list == null) {
            if (z8) {
                zzc(parcel, i9, 0);
                return;
            }
            return;
        }
        int iZza = zza(parcel, i9);
        int size = list.size();
        parcel.writeInt(size);
        for (int i10 = 0; i10 < size; i10++) {
            T t8 = list.get(i10);
            if (t8 == null) {
                parcel.writeInt(0);
            } else {
                zzd(parcel, t8, 0);
            }
        }
        zzb(parcel, iZza);
    }

    public static <T extends Parcelable> void writeTypedSparseArray(Parcel parcel, int i9, SparseArray<T> sparseArray, boolean z8) {
        if (sparseArray == null) {
            if (z8) {
                zzc(parcel, i9, 0);
                return;
            }
            return;
        }
        int iZza = zza(parcel, i9);
        int size = sparseArray.size();
        parcel.writeInt(size);
        for (int i10 = 0; i10 < size; i10++) {
            parcel.writeInt(sparseArray.keyAt(i10));
            T tValueAt = sparseArray.valueAt(i10);
            if (tValueAt == null) {
                parcel.writeInt(0);
            } else {
                zzd(parcel, tValueAt, 0);
            }
        }
        zzb(parcel, iZza);
    }

    private static int zza(Parcel parcel, int i9) {
        parcel.writeInt(i9 | (-65536));
        parcel.writeInt(0);
        return parcel.dataPosition();
    }

    private static void zzb(Parcel parcel, int i9) {
        int iDataPosition = parcel.dataPosition();
        parcel.setDataPosition(i9 - 4);
        parcel.writeInt(iDataPosition - i9);
        parcel.setDataPosition(iDataPosition);
    }

    private static void zzc(Parcel parcel, int i9, int i10) {
        parcel.writeInt(i9 | (i10 << 16));
    }

    private static void zzd(Parcel parcel, Parcelable parcelable, int i9) {
        int iDataPosition = parcel.dataPosition();
        parcel.writeInt(1);
        int iDataPosition2 = parcel.dataPosition();
        parcelable.writeToParcel(parcel, i9);
        int iDataPosition3 = parcel.dataPosition();
        parcel.setDataPosition(iDataPosition);
        parcel.writeInt(iDataPosition3 - iDataPosition2);
        parcel.setDataPosition(iDataPosition3);
    }
}
