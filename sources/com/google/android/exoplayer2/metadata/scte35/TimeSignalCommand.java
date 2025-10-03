package com.google.android.exoplayer2.metadata.scte35;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.TimestampAdjuster;

/* loaded from: classes.dex */
public final class TimeSignalCommand extends SpliceCommand {
    public static final Parcelable.Creator<TimeSignalCommand> CREATOR = new Parcelable.Creator<TimeSignalCommand>() { // from class: com.google.android.exoplayer2.metadata.scte35.TimeSignalCommand.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TimeSignalCommand createFromParcel(Parcel parcel) {
            return new TimeSignalCommand(parcel.readLong(), parcel.readLong());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TimeSignalCommand[] newArray(int i9) {
            return new TimeSignalCommand[i9];
        }
    };
    public final long playbackPositionUs;
    public final long ptsTime;

    public static TimeSignalCommand parseFromSection(ParsableByteArray parsableByteArray, long j9, TimestampAdjuster timestampAdjuster) {
        long spliceTime = parseSpliceTime(parsableByteArray, j9);
        return new TimeSignalCommand(spliceTime, timestampAdjuster.adjustTsTimestamp(spliceTime));
    }

    public static long parseSpliceTime(ParsableByteArray parsableByteArray, long j9) {
        long unsignedByte = parsableByteArray.readUnsignedByte();
        return (128 & unsignedByte) != 0 ? 8589934591L & ((((unsignedByte & 1) << 32) | parsableByteArray.readUnsignedInt()) + j9) : C3322C.TIME_UNSET;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i9) {
        parcel.writeLong(this.ptsTime);
        parcel.writeLong(this.playbackPositionUs);
    }

    private TimeSignalCommand(long j9, long j10) {
        this.ptsTime = j9;
        this.playbackPositionUs = j10;
    }
}
