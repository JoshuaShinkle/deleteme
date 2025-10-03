package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.decoder.CryptoInfo;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.source.SampleMetadataQueue;
import com.google.android.exoplayer2.upstream.Allocation;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.common.base.Ascii;
import com.google.common.primitives.UnsignedBytes;
import java.io.EOFException;
import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public final class SampleQueue implements TrackOutput {
    public static final int ADVANCE_FAILED = -1;
    private static final int INITIAL_SCRATCH_SIZE = 32;
    private final int allocationLength;
    private final Allocator allocator;
    private Format downstreamFormat;
    private final SampleMetadataQueue.SampleExtrasHolder extrasHolder;
    private AllocationNode firstAllocationNode;
    private Format lastUnadjustedFormat;
    private final SampleMetadataQueue metadataQueue;
    private boolean pendingFormatAdjustment;
    private boolean pendingSplice;
    private AllocationNode readAllocationNode;
    private long sampleOffsetUs;
    private final ParsableByteArray scratch;
    private long totalBytesWritten;
    private UpstreamFormatChangedListener upstreamFormatChangeListener;
    private AllocationNode writeAllocationNode;

    public static final class AllocationNode {
        public Allocation allocation;
        public final long endPosition;
        public AllocationNode next;
        public final long startPosition;
        public boolean wasInitialized;

        public AllocationNode(long j9, int i9) {
            this.startPosition = j9;
            this.endPosition = j9 + i9;
        }

        public AllocationNode clear() {
            this.allocation = null;
            AllocationNode allocationNode = this.next;
            this.next = null;
            return allocationNode;
        }

        public void initialize(Allocation allocation, AllocationNode allocationNode) {
            this.allocation = allocation;
            this.next = allocationNode;
            this.wasInitialized = true;
        }

        public int translateOffset(long j9) {
            return ((int) (j9 - this.startPosition)) + this.allocation.offset;
        }
    }

    public interface UpstreamFormatChangedListener {
        void onUpstreamFormatChanged(Format format);
    }

    public SampleQueue(Allocator allocator) {
        this.allocator = allocator;
        int individualAllocationLength = allocator.getIndividualAllocationLength();
        this.allocationLength = individualAllocationLength;
        this.metadataQueue = new SampleMetadataQueue();
        this.extrasHolder = new SampleMetadataQueue.SampleExtrasHolder();
        this.scratch = new ParsableByteArray(32);
        AllocationNode allocationNode = new AllocationNode(0L, individualAllocationLength);
        this.firstAllocationNode = allocationNode;
        this.readAllocationNode = allocationNode;
        this.writeAllocationNode = allocationNode;
    }

    private void advanceReadTo(long j9) {
        while (true) {
            AllocationNode allocationNode = this.readAllocationNode;
            if (j9 < allocationNode.endPosition) {
                return;
            } else {
                this.readAllocationNode = allocationNode.next;
            }
        }
    }

    private void clearAllocationNodes(AllocationNode allocationNode) {
        if (allocationNode.wasInitialized) {
            AllocationNode allocationNode2 = this.writeAllocationNode;
            boolean z8 = allocationNode2.wasInitialized;
            int i9 = (z8 ? 1 : 0) + (((int) (allocationNode2.startPosition - allocationNode.startPosition)) / this.allocationLength);
            Allocation[] allocationArr = new Allocation[i9];
            for (int i10 = 0; i10 < i9; i10++) {
                allocationArr[i10] = allocationNode.allocation;
                allocationNode = allocationNode.clear();
            }
            this.allocator.release(allocationArr);
        }
    }

    private void discardDownstreamTo(long j9) {
        AllocationNode allocationNode;
        if (j9 == -1) {
            return;
        }
        while (true) {
            allocationNode = this.firstAllocationNode;
            if (j9 < allocationNode.endPosition) {
                break;
            }
            this.allocator.release(allocationNode.allocation);
            this.firstAllocationNode = this.firstAllocationNode.clear();
        }
        if (this.readAllocationNode.startPosition < allocationNode.startPosition) {
            this.readAllocationNode = allocationNode;
        }
    }

    private static Format getAdjustedSampleFormat(Format format, long j9) {
        if (format == null) {
            return null;
        }
        if (j9 == 0) {
            return format;
        }
        long j10 = format.subsampleOffsetUs;
        return j10 != Long.MAX_VALUE ? format.copyWithSubsampleOffsetUs(j10 + j9) : format;
    }

    private void postAppend(int i9) {
        long j9 = this.totalBytesWritten + i9;
        this.totalBytesWritten = j9;
        AllocationNode allocationNode = this.writeAllocationNode;
        if (j9 == allocationNode.endPosition) {
            this.writeAllocationNode = allocationNode.next;
        }
    }

    private int preAppend(int i9) {
        AllocationNode allocationNode = this.writeAllocationNode;
        if (!allocationNode.wasInitialized) {
            allocationNode.initialize(this.allocator.allocate(), new AllocationNode(this.writeAllocationNode.endPosition, this.allocationLength));
        }
        return Math.min(i9, (int) (this.writeAllocationNode.endPosition - this.totalBytesWritten));
    }

    private void readData(long j9, ByteBuffer byteBuffer, int i9) {
        advanceReadTo(j9);
        while (i9 > 0) {
            int iMin = Math.min(i9, (int) (this.readAllocationNode.endPosition - j9));
            AllocationNode allocationNode = this.readAllocationNode;
            byteBuffer.put(allocationNode.allocation.data, allocationNode.translateOffset(j9), iMin);
            i9 -= iMin;
            j9 += iMin;
            AllocationNode allocationNode2 = this.readAllocationNode;
            if (j9 == allocationNode2.endPosition) {
                this.readAllocationNode = allocationNode2.next;
            }
        }
    }

    private void readEncryptionData(DecoderInputBuffer decoderInputBuffer, SampleMetadataQueue.SampleExtrasHolder sampleExtrasHolder) {
        long j9 = sampleExtrasHolder.offset;
        int unsignedShort = 1;
        this.scratch.reset(1);
        readData(j9, this.scratch.data, 1);
        long j10 = j9 + 1;
        byte b9 = this.scratch.data[0];
        boolean z8 = (b9 & UnsignedBytes.MAX_POWER_OF_TWO) != 0;
        int i9 = b9 & Ascii.DEL;
        CryptoInfo cryptoInfo = decoderInputBuffer.cryptoInfo;
        if (cryptoInfo.f15302iv == null) {
            cryptoInfo.f15302iv = new byte[16];
        }
        readData(j10, cryptoInfo.f15302iv, i9);
        long j11 = j10 + i9;
        if (z8) {
            this.scratch.reset(2);
            readData(j11, this.scratch.data, 2);
            j11 += 2;
            unsignedShort = this.scratch.readUnsignedShort();
        }
        int i10 = unsignedShort;
        CryptoInfo cryptoInfo2 = decoderInputBuffer.cryptoInfo;
        int[] iArr = cryptoInfo2.numBytesOfClearData;
        if (iArr == null || iArr.length < i10) {
            iArr = new int[i10];
        }
        int[] iArr2 = iArr;
        int[] iArr3 = cryptoInfo2.numBytesOfEncryptedData;
        if (iArr3 == null || iArr3.length < i10) {
            iArr3 = new int[i10];
        }
        int[] iArr4 = iArr3;
        if (z8) {
            int i11 = i10 * 6;
            this.scratch.reset(i11);
            readData(j11, this.scratch.data, i11);
            j11 += i11;
            this.scratch.setPosition(0);
            for (int i12 = 0; i12 < i10; i12++) {
                iArr2[i12] = this.scratch.readUnsignedShort();
                iArr4[i12] = this.scratch.readUnsignedIntToInt();
            }
        } else {
            iArr2[0] = 0;
            iArr4[0] = sampleExtrasHolder.size - ((int) (j11 - sampleExtrasHolder.offset));
        }
        TrackOutput.CryptoData cryptoData = sampleExtrasHolder.cryptoData;
        CryptoInfo cryptoInfo3 = decoderInputBuffer.cryptoInfo;
        cryptoInfo3.set(i10, iArr2, iArr4, cryptoData.encryptionKey, cryptoInfo3.f15302iv, cryptoData.cryptoMode, cryptoData.encryptedBlocks, cryptoData.clearBlocks);
        long j12 = sampleExtrasHolder.offset;
        int i13 = (int) (j11 - j12);
        sampleExtrasHolder.offset = j12 + i13;
        sampleExtrasHolder.size -= i13;
    }

    public int advanceTo(long j9, boolean z8, boolean z9) {
        return this.metadataQueue.advanceTo(j9, z8, z9);
    }

    public int advanceToEnd() {
        return this.metadataQueue.advanceToEnd();
    }

    public void discardTo(long j9, boolean z8, boolean z9) {
        discardDownstreamTo(this.metadataQueue.discardTo(j9, z8, z9));
    }

    public void discardToEnd() {
        discardDownstreamTo(this.metadataQueue.discardToEnd());
    }

    public void discardToRead() {
        discardDownstreamTo(this.metadataQueue.discardToRead());
    }

    public void discardUpstreamSamples(int i9) {
        long jDiscardUpstreamSamples = this.metadataQueue.discardUpstreamSamples(i9);
        this.totalBytesWritten = jDiscardUpstreamSamples;
        if (jDiscardUpstreamSamples != 0) {
            AllocationNode allocationNode = this.firstAllocationNode;
            if (jDiscardUpstreamSamples != allocationNode.startPosition) {
                while (this.totalBytesWritten > allocationNode.endPosition) {
                    allocationNode = allocationNode.next;
                }
                AllocationNode allocationNode2 = allocationNode.next;
                clearAllocationNodes(allocationNode2);
                AllocationNode allocationNode3 = new AllocationNode(allocationNode.endPosition, this.allocationLength);
                allocationNode.next = allocationNode3;
                if (this.totalBytesWritten == allocationNode.endPosition) {
                    allocationNode = allocationNode3;
                }
                this.writeAllocationNode = allocationNode;
                if (this.readAllocationNode == allocationNode2) {
                    this.readAllocationNode = allocationNode3;
                    return;
                }
                return;
            }
        }
        clearAllocationNodes(this.firstAllocationNode);
        AllocationNode allocationNode4 = new AllocationNode(this.totalBytesWritten, this.allocationLength);
        this.firstAllocationNode = allocationNode4;
        this.readAllocationNode = allocationNode4;
        this.writeAllocationNode = allocationNode4;
    }

    @Override // com.google.android.exoplayer2.extractor.TrackOutput
    public void format(Format format) {
        Format adjustedSampleFormat = getAdjustedSampleFormat(format, this.sampleOffsetUs);
        boolean z8 = this.metadataQueue.format(adjustedSampleFormat);
        this.lastUnadjustedFormat = format;
        this.pendingFormatAdjustment = false;
        UpstreamFormatChangedListener upstreamFormatChangedListener = this.upstreamFormatChangeListener;
        if (upstreamFormatChangedListener == null || !z8) {
            return;
        }
        upstreamFormatChangedListener.onUpstreamFormatChanged(adjustedSampleFormat);
    }

    public int getFirstIndex() {
        return this.metadataQueue.getFirstIndex();
    }

    public long getFirstTimestampUs() {
        return this.metadataQueue.getFirstTimestampUs();
    }

    public long getLargestQueuedTimestampUs() {
        return this.metadataQueue.getLargestQueuedTimestampUs();
    }

    public int getReadIndex() {
        return this.metadataQueue.getReadIndex();
    }

    public Format getUpstreamFormat() {
        return this.metadataQueue.getUpstreamFormat();
    }

    public int getWriteIndex() {
        return this.metadataQueue.getWriteIndex();
    }

    public boolean hasNextSample() {
        return this.metadataQueue.hasNextSample();
    }

    public int peekSourceId() {
        return this.metadataQueue.peekSourceId();
    }

    public int read(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, boolean z8, boolean z9, long j9) {
        int i9 = this.metadataQueue.read(formatHolder, decoderInputBuffer, z8, z9, this.downstreamFormat, this.extrasHolder);
        if (i9 == -5) {
            this.downstreamFormat = formatHolder.format;
            return -5;
        }
        if (i9 != -4) {
            if (i9 == -3) {
                return -3;
            }
            throw new IllegalStateException();
        }
        if (!decoderInputBuffer.isEndOfStream()) {
            if (decoderInputBuffer.timeUs < j9) {
                decoderInputBuffer.addFlag(Integer.MIN_VALUE);
            }
            if (decoderInputBuffer.isEncrypted()) {
                readEncryptionData(decoderInputBuffer, this.extrasHolder);
            }
            decoderInputBuffer.ensureSpaceForWrite(this.extrasHolder.size);
            SampleMetadataQueue.SampleExtrasHolder sampleExtrasHolder = this.extrasHolder;
            readData(sampleExtrasHolder.offset, decoderInputBuffer.data, sampleExtrasHolder.size);
        }
        return -4;
    }

    public void reset() {
        reset(false);
    }

    public void rewind() {
        this.metadataQueue.rewind();
        this.readAllocationNode = this.firstAllocationNode;
    }

    @Override // com.google.android.exoplayer2.extractor.TrackOutput
    public int sampleData(ExtractorInput extractorInput, int i9, boolean z8) throws EOFException {
        int iPreAppend = preAppend(i9);
        AllocationNode allocationNode = this.writeAllocationNode;
        int i10 = extractorInput.read(allocationNode.allocation.data, allocationNode.translateOffset(this.totalBytesWritten), iPreAppend);
        if (i10 != -1) {
            postAppend(i10);
            return i10;
        }
        if (z8) {
            return -1;
        }
        throw new EOFException();
    }

    @Override // com.google.android.exoplayer2.extractor.TrackOutput
    public void sampleMetadata(long j9, int i9, int i10, int i11, TrackOutput.CryptoData cryptoData) {
        if (this.pendingFormatAdjustment) {
            format(this.lastUnadjustedFormat);
        }
        if (this.pendingSplice) {
            if ((i9 & 1) == 0 || !this.metadataQueue.attemptSplice(j9)) {
                return;
            } else {
                this.pendingSplice = false;
            }
        }
        this.metadataQueue.commitSample(j9 + this.sampleOffsetUs, i9, (this.totalBytesWritten - i10) - i11, i10, cryptoData);
    }

    public boolean setReadPosition(int i9) {
        return this.metadataQueue.setReadPosition(i9);
    }

    public void setSampleOffsetUs(long j9) {
        if (this.sampleOffsetUs != j9) {
            this.sampleOffsetUs = j9;
            this.pendingFormatAdjustment = true;
        }
    }

    public void setUpstreamFormatChangeListener(UpstreamFormatChangedListener upstreamFormatChangedListener) {
        this.upstreamFormatChangeListener = upstreamFormatChangedListener;
    }

    public void sourceId(int i9) {
        this.metadataQueue.sourceId(i9);
    }

    public void splice() {
        this.pendingSplice = true;
    }

    public void reset(boolean z8) {
        this.metadataQueue.reset(z8);
        clearAllocationNodes(this.firstAllocationNode);
        AllocationNode allocationNode = new AllocationNode(0L, this.allocationLength);
        this.firstAllocationNode = allocationNode;
        this.readAllocationNode = allocationNode;
        this.writeAllocationNode = allocationNode;
        this.totalBytesWritten = 0L;
        this.allocator.trim();
    }

    private void readData(long j9, byte[] bArr, int i9) {
        advanceReadTo(j9);
        int i10 = i9;
        while (i10 > 0) {
            int iMin = Math.min(i10, (int) (this.readAllocationNode.endPosition - j9));
            AllocationNode allocationNode = this.readAllocationNode;
            System.arraycopy(allocationNode.allocation.data, allocationNode.translateOffset(j9), bArr, i9 - i10, iMin);
            i10 -= iMin;
            j9 += iMin;
            AllocationNode allocationNode2 = this.readAllocationNode;
            if (j9 == allocationNode2.endPosition) {
                this.readAllocationNode = allocationNode2.next;
            }
        }
    }

    @Override // com.google.android.exoplayer2.extractor.TrackOutput
    public void sampleData(ParsableByteArray parsableByteArray, int i9) {
        while (i9 > 0) {
            int iPreAppend = preAppend(i9);
            AllocationNode allocationNode = this.writeAllocationNode;
            parsableByteArray.readBytes(allocationNode.allocation.data, allocationNode.translateOffset(this.totalBytesWritten), iPreAppend);
            i9 -= iPreAppend;
            postAppend(iPreAppend);
        }
    }
}
