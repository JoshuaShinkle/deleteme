package com.google.android.gms.common.internal.safeparcel;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.common.zzag;
import java.util.ArrayList;
import java.util.Iterator;
import org.jsoup.select.Elements;

@VisibleForTesting
@KeepForSdk
/* loaded from: classes2.dex */
public final class SafeParcelableSerializer {
    private SafeParcelableSerializer() {
    }

    @KeepForSdk
    public static <T extends SafeParcelable> T deserializeFromBytes(byte[] bArr, Parcelable.Creator<T> creator) {
        Preconditions.checkNotNull(creator);
        Parcel parcelObtain = Parcel.obtain();
        parcelObtain.unmarshall(bArr, 0, bArr.length);
        parcelObtain.setDataPosition(0);
        T tCreateFromParcel = creator.createFromParcel(parcelObtain);
        parcelObtain.recycle();
        return tCreateFromParcel;
    }

    @KeepForSdk
    public static <T extends SafeParcelable> T deserializeFromIntentExtra(Intent intent, String str, Parcelable.Creator<T> creator) {
        byte[] byteArrayExtra = intent.getByteArrayExtra(str);
        if (byteArrayExtra == null) {
            return null;
        }
        return (T) deserializeFromBytes(byteArrayExtra, creator);
    }

    @KeepForSdk
    public static <T extends SafeParcelable> T deserializeFromString(String str, Parcelable.Creator<T> creator) {
        return (T) deserializeFromBytes(Base64Utils.decodeUrlSafe(str), creator);
    }

    @Deprecated
    public static <T extends SafeParcelable> ArrayList<T> deserializeIterableFromBundle(Bundle bundle, String str, Parcelable.Creator<T> creator) {
        ArrayList arrayList = (ArrayList) bundle.getSerializable(str);
        if (arrayList == null) {
            return null;
        }
        Elements elements = (ArrayList<T>) new ArrayList(arrayList.size());
        int size = arrayList.size();
        for (int i9 = 0; i9 < size; i9++) {
            elements.add(deserializeFromBytes((byte[]) arrayList.get(i9), creator));
        }
        return elements;
    }

    @KeepForSdk
    public static <T extends SafeParcelable> ArrayList<T> deserializeIterableFromBundleSafe(Bundle bundle, String str, Parcelable.Creator<T> creator) {
        return deserializeIterableFromBytes(bundle.getByteArray(str), creator);
    }

    public static <T extends SafeParcelable> ArrayList<T> deserializeIterableFromBytes(byte[] bArr, Parcelable.Creator<T> creator) {
        if (bArr == null) {
            return null;
        }
        int length = bArr.length;
        Parcel parcelObtain = Parcel.obtain();
        parcelObtain.unmarshall(bArr, 0, length);
        parcelObtain.setDataPosition(0);
        try {
            ArrayList<T> arrayList = new ArrayList<>();
            parcelObtain.readTypedList(arrayList, creator);
            return arrayList;
        } finally {
            parcelObtain.recycle();
        }
    }

    @KeepForSdk
    @Deprecated
    public static <T extends SafeParcelable> ArrayList<T> deserializeIterableFromIntentExtra(Intent intent, String str, Parcelable.Creator<T> creator) {
        ArrayList arrayList = (ArrayList) intent.getSerializableExtra(str);
        if (arrayList == null) {
            return null;
        }
        Elements elements = (ArrayList<T>) new ArrayList(arrayList.size());
        int size = arrayList.size();
        for (int i9 = 0; i9 < size; i9++) {
            elements.add(deserializeFromBytes((byte[]) arrayList.get(i9), creator));
        }
        return elements;
    }

    @KeepForSdk
    public static <T extends SafeParcelable> ArrayList<T> deserializeIterableFromIntentExtraSafe(Intent intent, String str, Parcelable.Creator<T> creator) {
        return deserializeIterableFromBytes(intent.getByteArrayExtra(str), creator);
    }

    @Deprecated
    public static <T extends SafeParcelable> void serializeIterableToBundle(Iterable<T> iterable, Bundle bundle, String str) {
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = iterable.iterator();
        while (it.hasNext()) {
            arrayList.add(serializeToBytes(it.next()));
        }
        bundle.putSerializable(str, arrayList);
    }

    public static <T extends SafeParcelable> void serializeIterableToBundleSafe(Iterable<T> iterable, Bundle bundle, String str) {
        bundle.putByteArray(str, zza(iterable));
    }

    @KeepForSdk
    @Deprecated
    public static <T extends SafeParcelable> void serializeIterableToIntentExtra(Iterable<T> iterable, Intent intent, String str) {
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = iterable.iterator();
        while (it.hasNext()) {
            arrayList.add(serializeToBytes(it.next()));
        }
        intent.putExtra(str, arrayList);
    }

    @KeepForSdk
    public static <T extends SafeParcelable> void serializeIterableToIntentExtraSafe(Iterable<T> iterable, Intent intent, String str) {
        intent.putExtra(str, zza(iterable));
    }

    @KeepForSdk
    public static <T extends SafeParcelable> byte[] serializeToBytes(T t8) {
        Parcel parcelObtain = Parcel.obtain();
        t8.writeToParcel(parcelObtain, 0);
        byte[] bArrMarshall = parcelObtain.marshall();
        parcelObtain.recycle();
        return bArrMarshall;
    }

    @KeepForSdk
    public static <T extends SafeParcelable> void serializeToIntentExtra(T t8, Intent intent, String str) {
        intent.putExtra(str, serializeToBytes(t8));
    }

    @KeepForSdk
    public static <T extends SafeParcelable> String serializeToString(T t8) {
        return Base64Utils.encodeUrlSafe(serializeToBytes(t8));
    }

    private static byte[] zza(Iterable iterable) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeTypedList(zzag.zzj(iterable));
            return parcelObtain.marshall();
        } finally {
            parcelObtain.recycle();
        }
    }
}
