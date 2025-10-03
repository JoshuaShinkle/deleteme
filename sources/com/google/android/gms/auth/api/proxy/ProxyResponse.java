package com.google.android.gms.auth.api.proxy;

import android.app.PendingIntent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.common.annotation.KeepForSdkWithMembers;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@KeepForSdkWithMembers
@SafeParcelable.Class(creator = "ProxyResponseCreator")
/* loaded from: classes2.dex */
public class ProxyResponse extends AbstractSafeParcelable {
    public static final Parcelable.Creator<ProxyResponse> CREATOR = new zzb();
    public static final int STATUS_CODE_NO_CONNECTION = -1;

    @SafeParcelable.Field(m17520id = 5)
    public final byte[] body;

    @SafeParcelable.Field(m17520id = 1)
    public final int googlePlayServicesStatusCode;

    @SafeParcelable.Field(m17520id = 2)
    public final PendingIntent recoveryAction;

    @SafeParcelable.Field(m17520id = 3)
    public final int statusCode;

    @SafeParcelable.VersionField(m17523id = CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT)
    private final int versionCode;

    @SafeParcelable.Field(m17520id = 4)
    private final Bundle zzby;

    @SafeParcelable.Constructor
    public ProxyResponse(@SafeParcelable.Param(m17521id = CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT) int i9, @SafeParcelable.Param(m17521id = 1) int i10, @SafeParcelable.Param(m17521id = 2) PendingIntent pendingIntent, @SafeParcelable.Param(m17521id = 3) int i11, @SafeParcelable.Param(m17521id = 4) Bundle bundle, @SafeParcelable.Param(m17521id = 5) byte[] bArr) {
        this.versionCode = i9;
        this.googlePlayServicesStatusCode = i10;
        this.statusCode = i11;
        this.zzby = bundle;
        this.body = bArr;
        this.recoveryAction = pendingIntent;
    }

    public static ProxyResponse createErrorProxyResponse(int i9, PendingIntent pendingIntent, int i10, Map<String, String> map, byte[] bArr) {
        return new ProxyResponse(1, i9, pendingIntent, i10, zza(map), bArr);
    }

    private static Bundle zza(Map<String, String> map) {
        Bundle bundle = new Bundle();
        if (map == null) {
            return bundle;
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            bundle.putString(entry.getKey(), entry.getValue());
        }
        return bundle;
    }

    public Map<String, String> getHeaders() {
        if (this.zzby == null) {
            return Collections.emptyMap();
        }
        HashMap map = new HashMap();
        for (String str : this.zzby.keySet()) {
            map.put(str, this.zzby.getString(str));
        }
        return map;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i9) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.googlePlayServicesStatusCode);
        SafeParcelWriter.writeParcelable(parcel, 2, this.recoveryAction, i9, false);
        SafeParcelWriter.writeInt(parcel, 3, this.statusCode);
        SafeParcelWriter.writeBundle(parcel, 4, this.zzby, false);
        SafeParcelWriter.writeByteArray(parcel, 5, this.body, false);
        SafeParcelWriter.writeInt(parcel, CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT, this.versionCode);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public ProxyResponse(int i9, PendingIntent pendingIntent, int i10, Bundle bundle, byte[] bArr) {
        this(1, i9, pendingIntent, i10, bundle, bArr);
    }

    private ProxyResponse(int i9, Bundle bundle, byte[] bArr) {
        this(1, 0, null, i9, bundle, bArr);
    }

    public ProxyResponse(int i9, Map<String, String> map, byte[] bArr) {
        this(i9, zza(map), bArr);
    }
}
