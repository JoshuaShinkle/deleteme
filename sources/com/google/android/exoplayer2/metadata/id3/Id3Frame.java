package com.google.android.exoplayer2.metadata.id3;

import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.util.Assertions;

/* loaded from: classes.dex */
public abstract class Id3Frame implements Metadata.Entry {

    /* renamed from: id */
    public final String f15312id;

    public Id3Frame(String str) {
        this.f15312id = (String) Assertions.checkNotNull(str);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }
}
