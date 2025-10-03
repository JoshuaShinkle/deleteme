package com.google.android.exoplayer2.source;

import java.util.Arrays;

/* loaded from: classes.dex */
public final class TrackGroupArray {
    public static final TrackGroupArray EMPTY = new TrackGroupArray(new TrackGroup[0]);
    private int hashCode;
    public final int length;
    private final TrackGroup[] trackGroups;

    public TrackGroupArray(TrackGroup... trackGroupArr) {
        this.trackGroups = trackGroupArr;
        this.length = trackGroupArr.length;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || TrackGroupArray.class != obj.getClass()) {
            return false;
        }
        TrackGroupArray trackGroupArray = (TrackGroupArray) obj;
        return this.length == trackGroupArray.length && Arrays.equals(this.trackGroups, trackGroupArray.trackGroups);
    }

    public TrackGroup get(int i9) {
        return this.trackGroups[i9];
    }

    public int hashCode() {
        if (this.hashCode == 0) {
            this.hashCode = Arrays.hashCode(this.trackGroups);
        }
        return this.hashCode;
    }

    public int indexOf(TrackGroup trackGroup) {
        for (int i9 = 0; i9 < this.length; i9++) {
            if (this.trackGroups[i9] == trackGroup) {
                return i9;
            }
        }
        return -1;
    }

    public boolean isEmpty() {
        return this.length == 0;
    }
}
