package com.google.android.exoplayer2.source.smoothstreaming.manifest;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public final class TrackKey implements Parcelable, Comparable<TrackKey> {
    public static final Parcelable.Creator<TrackKey> CREATOR = new Parcelable.Creator<TrackKey>() { // from class: com.google.android.exoplayer2.source.smoothstreaming.manifest.TrackKey.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TrackKey createFromParcel(Parcel parcel) {
            return new TrackKey(parcel.readInt(), parcel.readInt());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TrackKey[] newArray(int i9) {
            return new TrackKey[i9];
        }
    };
    public final int streamElementIndex;
    public final int trackIndex;

    public TrackKey(int i9, int i10) {
        this.streamElementIndex = i9;
        this.trackIndex = i10;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return this.streamElementIndex + "." + this.trackIndex;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i9) {
        parcel.writeInt(this.streamElementIndex);
        parcel.writeInt(this.trackIndex);
    }

    @Override // java.lang.Comparable
    public int compareTo(TrackKey trackKey) {
        int i9 = this.streamElementIndex - trackKey.streamElementIndex;
        return i9 == 0 ? this.trackIndex - trackKey.trackIndex : i9;
    }
}
