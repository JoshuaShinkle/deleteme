package com.google.android.exoplayer2.text.cea;

import com.google.android.exoplayer2.text.Subtitle;
import com.google.android.exoplayer2.text.SubtitleDecoder;
import com.google.android.exoplayer2.text.SubtitleInputBuffer;
import com.google.android.exoplayer2.text.SubtitleOutputBuffer;
import com.google.android.exoplayer2.util.Assertions;
import java.util.LinkedList;
import java.util.PriorityQueue;

/* loaded from: classes.dex */
abstract class CeaDecoder implements SubtitleDecoder {
    private static final int NUM_INPUT_BUFFERS = 10;
    private static final int NUM_OUTPUT_BUFFERS = 2;
    private final LinkedList<CeaInputBuffer> availableInputBuffers = new LinkedList<>();
    private final LinkedList<SubtitleOutputBuffer> availableOutputBuffers;
    private CeaInputBuffer dequeuedInputBuffer;
    private long playbackPositionUs;
    private long queuedInputBufferCount;
    private final PriorityQueue<CeaInputBuffer> queuedInputBuffers;

    public static final class CeaInputBuffer extends SubtitleInputBuffer implements Comparable<CeaInputBuffer> {
        private long queuedInputBufferCount;

        private CeaInputBuffer() {
        }

        @Override // java.lang.Comparable
        public int compareTo(CeaInputBuffer ceaInputBuffer) {
            if (isEndOfStream() != ceaInputBuffer.isEndOfStream()) {
                return isEndOfStream() ? 1 : -1;
            }
            long j9 = this.timeUs - ceaInputBuffer.timeUs;
            if (j9 == 0) {
                j9 = this.queuedInputBufferCount - ceaInputBuffer.queuedInputBufferCount;
                if (j9 == 0) {
                    return 0;
                }
            }
            return j9 > 0 ? 1 : -1;
        }
    }

    public final class CeaOutputBuffer extends SubtitleOutputBuffer {
        private CeaOutputBuffer() {
        }

        @Override // com.google.android.exoplayer2.text.SubtitleOutputBuffer, com.google.android.exoplayer2.decoder.OutputBuffer
        public final void release() {
            CeaDecoder.this.releaseOutputBuffer(this);
        }
    }

    public CeaDecoder() {
        int i9 = 0;
        while (true) {
            if (i9 >= 10) {
                break;
            }
            this.availableInputBuffers.add(new CeaInputBuffer());
            i9++;
        }
        this.availableOutputBuffers = new LinkedList<>();
        for (int i10 = 0; i10 < 2; i10++) {
            this.availableOutputBuffers.add(new CeaOutputBuffer());
        }
        this.queuedInputBuffers = new PriorityQueue<>();
    }

    private void releaseInputBuffer(CeaInputBuffer ceaInputBuffer) {
        ceaInputBuffer.clear();
        this.availableInputBuffers.add(ceaInputBuffer);
    }

    public abstract Subtitle createSubtitle();

    public abstract void decode(SubtitleInputBuffer subtitleInputBuffer);

    @Override // com.google.android.exoplayer2.decoder.Decoder
    public void flush() {
        this.queuedInputBufferCount = 0L;
        this.playbackPositionUs = 0L;
        while (!this.queuedInputBuffers.isEmpty()) {
            releaseInputBuffer(this.queuedInputBuffers.poll());
        }
        CeaInputBuffer ceaInputBuffer = this.dequeuedInputBuffer;
        if (ceaInputBuffer != null) {
            releaseInputBuffer(ceaInputBuffer);
            this.dequeuedInputBuffer = null;
        }
    }

    @Override // com.google.android.exoplayer2.decoder.Decoder
    public abstract String getName();

    public abstract boolean isNewSubtitleDataAvailable();

    @Override // com.google.android.exoplayer2.decoder.Decoder
    public void release() {
    }

    public void releaseOutputBuffer(SubtitleOutputBuffer subtitleOutputBuffer) {
        subtitleOutputBuffer.clear();
        this.availableOutputBuffers.add(subtitleOutputBuffer);
    }

    @Override // com.google.android.exoplayer2.text.SubtitleDecoder
    public void setPositionUs(long j9) {
        this.playbackPositionUs = j9;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.android.exoplayer2.decoder.Decoder
    public SubtitleInputBuffer dequeueInputBuffer() {
        Assertions.checkState(this.dequeuedInputBuffer == null);
        if (this.availableInputBuffers.isEmpty()) {
            return null;
        }
        CeaInputBuffer ceaInputBufferPollFirst = this.availableInputBuffers.pollFirst();
        this.dequeuedInputBuffer = ceaInputBufferPollFirst;
        return ceaInputBufferPollFirst;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.android.exoplayer2.decoder.Decoder
    public SubtitleOutputBuffer dequeueOutputBuffer() {
        if (this.availableOutputBuffers.isEmpty()) {
            return null;
        }
        while (!this.queuedInputBuffers.isEmpty() && this.queuedInputBuffers.peek().timeUs <= this.playbackPositionUs) {
            CeaInputBuffer ceaInputBufferPoll = this.queuedInputBuffers.poll();
            if (ceaInputBufferPoll.isEndOfStream()) {
                SubtitleOutputBuffer subtitleOutputBufferPollFirst = this.availableOutputBuffers.pollFirst();
                subtitleOutputBufferPollFirst.addFlag(4);
                releaseInputBuffer(ceaInputBufferPoll);
                return subtitleOutputBufferPollFirst;
            }
            decode(ceaInputBufferPoll);
            if (isNewSubtitleDataAvailable()) {
                Subtitle subtitleCreateSubtitle = createSubtitle();
                if (!ceaInputBufferPoll.isDecodeOnly()) {
                    SubtitleOutputBuffer subtitleOutputBufferPollFirst2 = this.availableOutputBuffers.pollFirst();
                    subtitleOutputBufferPollFirst2.setContent(ceaInputBufferPoll.timeUs, subtitleCreateSubtitle, Long.MAX_VALUE);
                    releaseInputBuffer(ceaInputBufferPoll);
                    return subtitleOutputBufferPollFirst2;
                }
            }
            releaseInputBuffer(ceaInputBufferPoll);
        }
        return null;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.android.exoplayer2.decoder.Decoder
    public void queueInputBuffer(SubtitleInputBuffer subtitleInputBuffer) {
        Assertions.checkArgument(subtitleInputBuffer == this.dequeuedInputBuffer);
        if (subtitleInputBuffer.isDecodeOnly()) {
            releaseInputBuffer(this.dequeuedInputBuffer);
        } else {
            CeaInputBuffer ceaInputBuffer = this.dequeuedInputBuffer;
            long j9 = this.queuedInputBufferCount;
            this.queuedInputBufferCount = 1 + j9;
            ceaInputBuffer.queuedInputBufferCount = j9;
            this.queuedInputBuffers.add(this.dequeuedInputBuffer);
        }
        this.dequeuedInputBuffer = null;
    }
}
