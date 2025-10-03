package com.google.android.exoplayer2.metadata.scte35;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.TimestampAdjuster;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public final class SpliceInsertCommand extends SpliceCommand {
    public static final Parcelable.Creator<SpliceInsertCommand> CREATOR = new Parcelable.Creator<SpliceInsertCommand>() { // from class: com.google.android.exoplayer2.metadata.scte35.SpliceInsertCommand.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SpliceInsertCommand createFromParcel(Parcel parcel) {
            return new SpliceInsertCommand(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SpliceInsertCommand[] newArray(int i9) {
            return new SpliceInsertCommand[i9];
        }
    };
    public final boolean autoReturn;
    public final int availNum;
    public final int availsExpected;
    public final long breakDurationUs;
    public final List<ComponentSplice> componentSpliceList;
    public final boolean outOfNetworkIndicator;
    public final boolean programSpliceFlag;
    public final long programSplicePlaybackPositionUs;
    public final long programSplicePts;
    public final boolean spliceEventCancelIndicator;
    public final long spliceEventId;
    public final boolean spliceImmediateFlag;
    public final int uniqueProgramId;

    public static final class ComponentSplice {
        public final long componentSplicePlaybackPositionUs;
        public final long componentSplicePts;
        public final int componentTag;

        public static ComponentSplice createFromParcel(Parcel parcel) {
            return new ComponentSplice(parcel.readInt(), parcel.readLong(), parcel.readLong());
        }

        public void writeToParcel(Parcel parcel) {
            parcel.writeInt(this.componentTag);
            parcel.writeLong(this.componentSplicePts);
            parcel.writeLong(this.componentSplicePlaybackPositionUs);
        }

        private ComponentSplice(int i9, long j9, long j10) {
            this.componentTag = i9;
            this.componentSplicePts = j9;
            this.componentSplicePlaybackPositionUs = j10;
        }
    }

    public static SpliceInsertCommand parseFromSection(ParsableByteArray parsableByteArray, long j9, TimestampAdjuster timestampAdjuster) {
        List list;
        boolean z8;
        boolean z9;
        long j10;
        boolean z10;
        long j11;
        int unsignedShort;
        int unsignedByte;
        int unsignedByte2;
        boolean z11;
        boolean z12;
        long unsignedInt;
        long unsignedInt2 = parsableByteArray.readUnsignedInt();
        boolean z13 = (parsableByteArray.readUnsignedByte() & 128) != 0;
        List listEmptyList = Collections.emptyList();
        if (z13) {
            list = listEmptyList;
            z8 = false;
            z9 = false;
            j10 = C3322C.TIME_UNSET;
            z10 = false;
            j11 = C3322C.TIME_UNSET;
            unsignedShort = 0;
            unsignedByte = 0;
            unsignedByte2 = 0;
            z11 = false;
        } else {
            int unsignedByte3 = parsableByteArray.readUnsignedByte();
            boolean z14 = (unsignedByte3 & 128) != 0;
            boolean z15 = (unsignedByte3 & 64) != 0;
            boolean z16 = (unsignedByte3 & 32) != 0;
            boolean z17 = (unsignedByte3 & 16) != 0;
            long spliceTime = (!z15 || z17) ? C3322C.TIME_UNSET : TimeSignalCommand.parseSpliceTime(parsableByteArray, j9);
            if (!z15) {
                int unsignedByte4 = parsableByteArray.readUnsignedByte();
                ArrayList arrayList = new ArrayList(unsignedByte4);
                for (int i9 = 0; i9 < unsignedByte4; i9++) {
                    int unsignedByte5 = parsableByteArray.readUnsignedByte();
                    long spliceTime2 = !z17 ? TimeSignalCommand.parseSpliceTime(parsableByteArray, j9) : C3322C.TIME_UNSET;
                    arrayList.add(new ComponentSplice(unsignedByte5, spliceTime2, timestampAdjuster.adjustTsTimestamp(spliceTime2)));
                }
                listEmptyList = arrayList;
            }
            if (z16) {
                long unsignedByte6 = parsableByteArray.readUnsignedByte();
                boolean z18 = (128 & unsignedByte6) != 0;
                unsignedInt = ((((unsignedByte6 & 1) << 32) | parsableByteArray.readUnsignedInt()) * 1000) / 90;
                z12 = z18;
            } else {
                z12 = false;
                unsignedInt = C3322C.TIME_UNSET;
            }
            unsignedShort = parsableByteArray.readUnsignedShort();
            z11 = z15;
            unsignedByte = parsableByteArray.readUnsignedByte();
            unsignedByte2 = parsableByteArray.readUnsignedByte();
            list = listEmptyList;
            long j12 = spliceTime;
            z10 = z12;
            j11 = unsignedInt;
            z9 = z17;
            z8 = z14;
            j10 = j12;
        }
        return new SpliceInsertCommand(unsignedInt2, z13, z8, z11, z9, j10, timestampAdjuster.adjustTsTimestamp(j10), list, z10, j11, unsignedShort, unsignedByte, unsignedByte2);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i9) {
        parcel.writeLong(this.spliceEventId);
        parcel.writeByte(this.spliceEventCancelIndicator ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.outOfNetworkIndicator ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.programSpliceFlag ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.spliceImmediateFlag ? (byte) 1 : (byte) 0);
        parcel.writeLong(this.programSplicePts);
        parcel.writeLong(this.programSplicePlaybackPositionUs);
        int size = this.componentSpliceList.size();
        parcel.writeInt(size);
        for (int i10 = 0; i10 < size; i10++) {
            this.componentSpliceList.get(i10).writeToParcel(parcel);
        }
        parcel.writeByte(this.autoReturn ? (byte) 1 : (byte) 0);
        parcel.writeLong(this.breakDurationUs);
        parcel.writeInt(this.uniqueProgramId);
        parcel.writeInt(this.availNum);
        parcel.writeInt(this.availsExpected);
    }

    private SpliceInsertCommand(long j9, boolean z8, boolean z9, boolean z10, boolean z11, long j10, long j11, List<ComponentSplice> list, boolean z12, long j12, int i9, int i10, int i11) {
        this.spliceEventId = j9;
        this.spliceEventCancelIndicator = z8;
        this.outOfNetworkIndicator = z9;
        this.programSpliceFlag = z10;
        this.spliceImmediateFlag = z11;
        this.programSplicePts = j10;
        this.programSplicePlaybackPositionUs = j11;
        this.componentSpliceList = Collections.unmodifiableList(list);
        this.autoReturn = z12;
        this.breakDurationUs = j12;
        this.uniqueProgramId = i9;
        this.availNum = i10;
        this.availsExpected = i11;
    }

    private SpliceInsertCommand(Parcel parcel) {
        this.spliceEventId = parcel.readLong();
        this.spliceEventCancelIndicator = parcel.readByte() == 1;
        this.outOfNetworkIndicator = parcel.readByte() == 1;
        this.programSpliceFlag = parcel.readByte() == 1;
        this.spliceImmediateFlag = parcel.readByte() == 1;
        this.programSplicePts = parcel.readLong();
        this.programSplicePlaybackPositionUs = parcel.readLong();
        int i9 = parcel.readInt();
        ArrayList arrayList = new ArrayList(i9);
        for (int i10 = 0; i10 < i9; i10++) {
            arrayList.add(ComponentSplice.createFromParcel(parcel));
        }
        this.componentSpliceList = Collections.unmodifiableList(arrayList);
        this.autoReturn = parcel.readByte() == 1;
        this.breakDurationUs = parcel.readLong();
        this.uniqueProgramId = parcel.readInt();
        this.availNum = parcel.readInt();
        this.availsExpected = parcel.readInt();
    }
}
