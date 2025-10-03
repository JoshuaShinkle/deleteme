package com.google.android.exoplayer2.metadata.scte35;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public final class SpliceScheduleCommand extends SpliceCommand {
    public static final Parcelable.Creator<SpliceScheduleCommand> CREATOR = new Parcelable.Creator<SpliceScheduleCommand>() { // from class: com.google.android.exoplayer2.metadata.scte35.SpliceScheduleCommand.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SpliceScheduleCommand createFromParcel(Parcel parcel) {
            return new SpliceScheduleCommand(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SpliceScheduleCommand[] newArray(int i9) {
            return new SpliceScheduleCommand[i9];
        }
    };
    public final List<Event> events;

    public static final class ComponentSplice {
        public final int componentTag;
        public final long utcSpliceTime;

        /* JADX INFO: Access modifiers changed from: private */
        public static ComponentSplice createFromParcel(Parcel parcel) {
            return new ComponentSplice(parcel.readInt(), parcel.readLong());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void writeToParcel(Parcel parcel) {
            parcel.writeInt(this.componentTag);
            parcel.writeLong(this.utcSpliceTime);
        }

        private ComponentSplice(int i9, long j9) {
            this.componentTag = i9;
            this.utcSpliceTime = j9;
        }
    }

    public static SpliceScheduleCommand parseFromSection(ParsableByteArray parsableByteArray) {
        int unsignedByte = parsableByteArray.readUnsignedByte();
        ArrayList arrayList = new ArrayList(unsignedByte);
        for (int i9 = 0; i9 < unsignedByte; i9++) {
            arrayList.add(Event.parseFromSection(parsableByteArray));
        }
        return new SpliceScheduleCommand(arrayList);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i9) {
        int size = this.events.size();
        parcel.writeInt(size);
        for (int i10 = 0; i10 < size; i10++) {
            this.events.get(i10).writeToParcel(parcel);
        }
    }

    private SpliceScheduleCommand(List<Event> list) {
        this.events = Collections.unmodifiableList(list);
    }

    private SpliceScheduleCommand(Parcel parcel) {
        int i9 = parcel.readInt();
        ArrayList arrayList = new ArrayList(i9);
        for (int i10 = 0; i10 < i9; i10++) {
            arrayList.add(Event.createFromParcel(parcel));
        }
        this.events = Collections.unmodifiableList(arrayList);
    }

    public static final class Event {
        public final boolean autoReturn;
        public final int availNum;
        public final int availsExpected;
        public final long breakDurationUs;
        public final List<ComponentSplice> componentSpliceList;
        public final boolean outOfNetworkIndicator;
        public final boolean programSpliceFlag;
        public final boolean spliceEventCancelIndicator;
        public final long spliceEventId;
        public final int uniqueProgramId;
        public final long utcSpliceTime;

        private Event(long j9, boolean z8, boolean z9, boolean z10, List<ComponentSplice> list, long j10, boolean z11, long j11, int i9, int i10, int i11) {
            this.spliceEventId = j9;
            this.spliceEventCancelIndicator = z8;
            this.outOfNetworkIndicator = z9;
            this.programSpliceFlag = z10;
            this.componentSpliceList = Collections.unmodifiableList(list);
            this.utcSpliceTime = j10;
            this.autoReturn = z11;
            this.breakDurationUs = j11;
            this.uniqueProgramId = i9;
            this.availNum = i10;
            this.availsExpected = i11;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Event createFromParcel(Parcel parcel) {
            return new Event(parcel);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Event parseFromSection(ParsableByteArray parsableByteArray) {
            ArrayList arrayList;
            boolean z8;
            long j9;
            boolean z9;
            long j10;
            int i9;
            int i10;
            int unsignedByte;
            boolean z10;
            boolean z11;
            long unsignedInt;
            long unsignedInt2 = parsableByteArray.readUnsignedInt();
            boolean z12 = (parsableByteArray.readUnsignedByte() & 128) != 0;
            ArrayList arrayList2 = new ArrayList();
            if (z12) {
                arrayList = arrayList2;
                z8 = false;
                j9 = C3322C.TIME_UNSET;
                z9 = false;
                j10 = C3322C.TIME_UNSET;
                i9 = 0;
                i10 = 0;
                unsignedByte = 0;
                z10 = false;
            } else {
                int unsignedByte2 = parsableByteArray.readUnsignedByte();
                boolean z13 = (unsignedByte2 & 128) != 0;
                boolean z14 = (unsignedByte2 & 64) != 0;
                boolean z15 = (unsignedByte2 & 32) != 0;
                long unsignedInt3 = z14 ? parsableByteArray.readUnsignedInt() : C3322C.TIME_UNSET;
                if (!z14) {
                    int unsignedByte3 = parsableByteArray.readUnsignedByte();
                    ArrayList arrayList3 = new ArrayList(unsignedByte3);
                    for (int i11 = 0; i11 < unsignedByte3; i11++) {
                        arrayList3.add(new ComponentSplice(parsableByteArray.readUnsignedByte(), parsableByteArray.readUnsignedInt()));
                    }
                    arrayList2 = arrayList3;
                }
                if (z15) {
                    long unsignedByte4 = parsableByteArray.readUnsignedByte();
                    boolean z16 = (128 & unsignedByte4) != 0;
                    unsignedInt = ((((unsignedByte4 & 1) << 32) | parsableByteArray.readUnsignedInt()) * 1000) / 90;
                    z11 = z16;
                } else {
                    z11 = false;
                    unsignedInt = C3322C.TIME_UNSET;
                }
                int unsignedShort = parsableByteArray.readUnsignedShort();
                int unsignedByte5 = parsableByteArray.readUnsignedByte();
                z10 = z14;
                unsignedByte = parsableByteArray.readUnsignedByte();
                j10 = unsignedInt;
                arrayList = arrayList2;
                long j11 = unsignedInt3;
                i9 = unsignedShort;
                i10 = unsignedByte5;
                j9 = j11;
                boolean z17 = z13;
                z9 = z11;
                z8 = z17;
            }
            return new Event(unsignedInt2, z12, z8, z10, arrayList, j9, z9, j10, i9, i10, unsignedByte);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void writeToParcel(Parcel parcel) {
            parcel.writeLong(this.spliceEventId);
            parcel.writeByte(this.spliceEventCancelIndicator ? (byte) 1 : (byte) 0);
            parcel.writeByte(this.outOfNetworkIndicator ? (byte) 1 : (byte) 0);
            parcel.writeByte(this.programSpliceFlag ? (byte) 1 : (byte) 0);
            int size = this.componentSpliceList.size();
            parcel.writeInt(size);
            for (int i9 = 0; i9 < size; i9++) {
                this.componentSpliceList.get(i9).writeToParcel(parcel);
            }
            parcel.writeLong(this.utcSpliceTime);
            parcel.writeByte(this.autoReturn ? (byte) 1 : (byte) 0);
            parcel.writeLong(this.breakDurationUs);
            parcel.writeInt(this.uniqueProgramId);
            parcel.writeInt(this.availNum);
            parcel.writeInt(this.availsExpected);
        }

        private Event(Parcel parcel) {
            this.spliceEventId = parcel.readLong();
            this.spliceEventCancelIndicator = parcel.readByte() == 1;
            this.outOfNetworkIndicator = parcel.readByte() == 1;
            this.programSpliceFlag = parcel.readByte() == 1;
            int i9 = parcel.readInt();
            ArrayList arrayList = new ArrayList(i9);
            for (int i10 = 0; i10 < i9; i10++) {
                arrayList.add(ComponentSplice.createFromParcel(parcel));
            }
            this.componentSpliceList = Collections.unmodifiableList(arrayList);
            this.utcSpliceTime = parcel.readLong();
            this.autoReturn = parcel.readByte() == 1;
            this.breakDurationUs = parcel.readLong();
            this.uniqueProgramId = parcel.readInt();
            this.availNum = parcel.readInt();
            this.availsExpected = parcel.readInt();
        }
    }
}
