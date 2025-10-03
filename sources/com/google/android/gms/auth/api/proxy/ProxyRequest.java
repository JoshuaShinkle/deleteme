package com.google.android.gms.auth.api.proxy;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Patterns;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.common.annotation.KeepForSdkWithMembers;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

@KeepForSdkWithMembers
@SafeParcelable.Class(creator = "ProxyRequestCreator")
/* loaded from: classes2.dex */
public class ProxyRequest extends AbstractSafeParcelable {
    public static final int VERSION_CODE = 2;

    @SafeParcelable.Field(m17520id = 4)
    public final byte[] body;

    @SafeParcelable.Field(m17520id = 2)
    public final int httpMethod;

    @SafeParcelable.Field(m17520id = 3)
    public final long timeoutMillis;

    @SafeParcelable.Field(m17520id = 1)
    public final String url;

    @SafeParcelable.VersionField(m17523id = CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT)
    private final int versionCode;

    @SafeParcelable.Field(m17520id = 5)
    private Bundle zzby;
    public static final Parcelable.Creator<ProxyRequest> CREATOR = new zza();
    public static final int HTTP_METHOD_GET = 0;
    public static final int HTTP_METHOD_POST = 1;
    public static final int HTTP_METHOD_PUT = 2;
    public static final int HTTP_METHOD_DELETE = 3;
    public static final int HTTP_METHOD_HEAD = 4;
    public static final int HTTP_METHOD_OPTIONS = 5;
    public static final int HTTP_METHOD_TRACE = 6;
    public static final int HTTP_METHOD_PATCH = 7;
    public static final int LAST_CODE = 7;

    @KeepForSdkWithMembers
    public static class Builder {
        private String zzbz;
        private int zzca = ProxyRequest.HTTP_METHOD_GET;
        private long zzcb = 3000;
        private byte[] zzcc = null;
        private Bundle zzcd = new Bundle();

        public Builder(String str) {
            Preconditions.checkNotEmpty(str);
            if (Patterns.WEB_URL.matcher(str).matches()) {
                this.zzbz = str;
                return;
            }
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 51);
            sb.append("The supplied url [ ");
            sb.append(str);
            sb.append("] is not match Patterns.WEB_URL!");
            throw new IllegalArgumentException(sb.toString());
        }

        public ProxyRequest build() {
            if (this.zzcc == null) {
                this.zzcc = new byte[0];
            }
            return new ProxyRequest(2, this.zzbz, this.zzca, this.zzcb, this.zzcc, this.zzcd);
        }

        public Builder putHeader(String str, String str2) {
            Preconditions.checkNotEmpty(str, "Header name cannot be null or empty!");
            Bundle bundle = this.zzcd;
            if (str2 == null) {
                str2 = "";
            }
            bundle.putString(str, str2);
            return this;
        }

        public Builder setBody(byte[] bArr) {
            this.zzcc = bArr;
            return this;
        }

        public Builder setHttpMethod(int i9) {
            Preconditions.checkArgument(i9 >= 0 && i9 <= ProxyRequest.LAST_CODE, "Unrecognized http method code.");
            this.zzca = i9;
            return this;
        }

        public Builder setTimeoutMillis(long j9) {
            Preconditions.checkArgument(j9 >= 0, "The specified timeout must be non-negative.");
            this.zzcb = j9;
            return this;
        }
    }

    @SafeParcelable.Constructor
    public ProxyRequest(@SafeParcelable.Param(m17521id = CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT) int i9, @SafeParcelable.Param(m17521id = 1) String str, @SafeParcelable.Param(m17521id = 2) int i10, @SafeParcelable.Param(m17521id = 3) long j9, @SafeParcelable.Param(m17521id = 4) byte[] bArr, @SafeParcelable.Param(m17521id = 5) Bundle bundle) {
        this.versionCode = i9;
        this.url = str;
        this.httpMethod = i10;
        this.timeoutMillis = j9;
        this.body = bArr;
        this.zzby = bundle;
    }

    public Map<String, String> getHeaderMap() {
        LinkedHashMap linkedHashMap = new LinkedHashMap(this.zzby.size());
        for (String str : this.zzby.keySet()) {
            linkedHashMap.put(str, this.zzby.getString(str));
        }
        return Collections.unmodifiableMap(linkedHashMap);
    }

    public String toString() {
        String str = this.url;
        int i9 = this.httpMethod;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 42);
        sb.append("ProxyRequest[ url: ");
        sb.append(str);
        sb.append(", method: ");
        sb.append(i9);
        sb.append(" ]");
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i9) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.url, false);
        SafeParcelWriter.writeInt(parcel, 2, this.httpMethod);
        SafeParcelWriter.writeLong(parcel, 3, this.timeoutMillis);
        SafeParcelWriter.writeByteArray(parcel, 4, this.body, false);
        SafeParcelWriter.writeBundle(parcel, 5, this.zzby, false);
        SafeParcelWriter.writeInt(parcel, CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT, this.versionCode);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
