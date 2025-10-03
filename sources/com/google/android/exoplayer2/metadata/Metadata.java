package com.google.android.exoplayer2.metadata;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
public final class Metadata implements Parcelable {
    public static final Parcelable.Creator<Metadata> CREATOR = new Parcelable.Creator<Metadata>() { // from class: com.google.android.exoplayer2.metadata.Metadata.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Metadata[] newArray(int i9) {
            return new Metadata[0];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Metadata createFromParcel(Parcel parcel) {
            return new Metadata(parcel);
        }
    };
    private final Entry[] entries;

    public interface Entry extends Parcelable {
    }

    public Metadata(Entry... entryArr) {
        this.entries = entryArr == null ? new Entry[0] : entryArr;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || Metadata.class != obj.getClass()) {
            return false;
        }
        return Arrays.equals(this.entries, ((Metadata) obj).entries);
    }

    public Entry get(int i9) {
        return this.entries[i9];
    }

    public int hashCode() {
        return Arrays.hashCode(this.entries);
    }

    public int length() {
        return this.entries.length;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i9) {
        parcel.writeInt(this.entries.length);
        for (Entry entry : this.entries) {
            parcel.writeParcelable(entry, 0);
        }
    }

    public Metadata(List<? extends Entry> list) {
        if (list != null) {
            Entry[] entryArr = new Entry[list.size()];
            this.entries = entryArr;
            list.toArray(entryArr);
            return;
        }
        this.entries = new Entry[0];
    }

    public Metadata(Parcel parcel) {
        this.entries = new Entry[parcel.readInt()];
        int i9 = 0;
        while (true) {
            Entry[] entryArr = this.entries;
            if (i9 >= entryArr.length) {
                return;
            }
            entryArr[i9] = (Entry) parcel.readParcelable(Entry.class.getClassLoader());
            i9++;
        }
    }
}
