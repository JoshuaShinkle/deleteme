package com.google.android.exoplayer2.metadata.emsg;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.util.Util;
import java.util.Arrays;

/* loaded from: classes.dex */
public final class EventMessage implements Metadata.Entry {
    public static final Parcelable.Creator<EventMessage> CREATOR = new Parcelable.Creator<EventMessage>() { // from class: com.google.android.exoplayer2.metadata.emsg.EventMessage.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EventMessage createFromParcel(Parcel parcel) {
            return new EventMessage(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EventMessage[] newArray(int i9) {
            return new EventMessage[i9];
        }
    };
    public final long durationMs;
    private int hashCode;

    /* renamed from: id */
    public final long f15306id;
    public final byte[] messageData;
    public final long presentationTimeUs;
    public final String schemeIdUri;
    public final String value;

    public EventMessage(String str, String str2, long j9, long j10, byte[] bArr, long j11) {
        this.schemeIdUri = str;
        this.value = str2;
        this.durationMs = j9;
        this.f15306id = j10;
        this.messageData = bArr;
        this.presentationTimeUs = j11;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || EventMessage.class != obj.getClass()) {
            return false;
        }
        EventMessage eventMessage = (EventMessage) obj;
        return this.presentationTimeUs == eventMessage.presentationTimeUs && this.durationMs == eventMessage.durationMs && this.f15306id == eventMessage.f15306id && Util.areEqual(this.schemeIdUri, eventMessage.schemeIdUri) && Util.areEqual(this.value, eventMessage.value) && Arrays.equals(this.messageData, eventMessage.messageData);
    }

    public int hashCode() {
        if (this.hashCode == 0) {
            String str = this.schemeIdUri;
            int iHashCode = (527 + (str != null ? str.hashCode() : 0)) * 31;
            String str2 = this.value;
            int iHashCode2 = str2 != null ? str2.hashCode() : 0;
            long j9 = this.presentationTimeUs;
            int i9 = (((iHashCode + iHashCode2) * 31) + ((int) (j9 ^ (j9 >>> 32)))) * 31;
            long j10 = this.durationMs;
            int i10 = (i9 + ((int) (j10 ^ (j10 >>> 32)))) * 31;
            long j11 = this.f15306id;
            this.hashCode = ((i10 + ((int) (j11 ^ (j11 >>> 32)))) * 31) + Arrays.hashCode(this.messageData);
        }
        return this.hashCode;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i9) {
        parcel.writeString(this.schemeIdUri);
        parcel.writeString(this.value);
        parcel.writeLong(this.presentationTimeUs);
        parcel.writeLong(this.durationMs);
        parcel.writeLong(this.f15306id);
        parcel.writeByteArray(this.messageData);
    }

    public EventMessage(Parcel parcel) {
        this.schemeIdUri = parcel.readString();
        this.value = parcel.readString();
        this.presentationTimeUs = parcel.readLong();
        this.durationMs = parcel.readLong();
        this.f15306id = parcel.readLong();
        this.messageData = parcel.createByteArray();
    }
}
