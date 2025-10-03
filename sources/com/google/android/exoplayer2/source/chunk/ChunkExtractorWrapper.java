package com.google.android.exoplayer2.source.chunk;

import android.util.SparseArray;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.DummyTrackOutput;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ParsableByteArray;

/* loaded from: classes.dex */
public final class ChunkExtractorWrapper implements ExtractorOutput {
    private final SparseArray<BindingTrackOutput> bindingTrackOutputs = new SparseArray<>();
    public final Extractor extractor;
    private boolean extractorInitialized;
    private final Format primaryTrackManifestFormat;
    private final int primaryTrackType;
    private Format[] sampleFormats;
    private SeekMap seekMap;
    private TrackOutputProvider trackOutputProvider;

    public static final class BindingTrackOutput implements TrackOutput {

        /* renamed from: id */
        private final int f15316id;
        private final Format manifestFormat;
        public Format sampleFormat;
        private TrackOutput trackOutput;
        private final int type;

        public BindingTrackOutput(int i9, int i10, Format format) {
            this.f15316id = i9;
            this.type = i10;
            this.manifestFormat = format;
        }

        public void bind(TrackOutputProvider trackOutputProvider) {
            if (trackOutputProvider == null) {
                this.trackOutput = new DummyTrackOutput();
                return;
            }
            TrackOutput trackOutputTrack = trackOutputProvider.track(this.f15316id, this.type);
            this.trackOutput = trackOutputTrack;
            Format format = this.sampleFormat;
            if (format != null) {
                trackOutputTrack.format(format);
            }
        }

        @Override // com.google.android.exoplayer2.extractor.TrackOutput
        public void format(Format format) {
            Format format2 = this.manifestFormat;
            if (format2 != null) {
                format = format.copyWithManifestFormatInfo(format2);
            }
            this.sampleFormat = format;
            this.trackOutput.format(format);
        }

        @Override // com.google.android.exoplayer2.extractor.TrackOutput
        public int sampleData(ExtractorInput extractorInput, int i9, boolean z8) {
            return this.trackOutput.sampleData(extractorInput, i9, z8);
        }

        @Override // com.google.android.exoplayer2.extractor.TrackOutput
        public void sampleMetadata(long j9, int i9, int i10, int i11, TrackOutput.CryptoData cryptoData) {
            this.trackOutput.sampleMetadata(j9, i9, i10, i11, cryptoData);
        }

        @Override // com.google.android.exoplayer2.extractor.TrackOutput
        public void sampleData(ParsableByteArray parsableByteArray, int i9) {
            this.trackOutput.sampleData(parsableByteArray, i9);
        }
    }

    public interface TrackOutputProvider {
        TrackOutput track(int i9, int i10);
    }

    public ChunkExtractorWrapper(Extractor extractor, int i9, Format format) {
        this.extractor = extractor;
        this.primaryTrackType = i9;
        this.primaryTrackManifestFormat = format;
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorOutput
    public void endTracks() {
        Format[] formatArr = new Format[this.bindingTrackOutputs.size()];
        for (int i9 = 0; i9 < this.bindingTrackOutputs.size(); i9++) {
            formatArr[i9] = this.bindingTrackOutputs.valueAt(i9).sampleFormat;
        }
        this.sampleFormats = formatArr;
    }

    public Format[] getSampleFormats() {
        return this.sampleFormats;
    }

    public SeekMap getSeekMap() {
        return this.seekMap;
    }

    public void init(TrackOutputProvider trackOutputProvider) {
        this.trackOutputProvider = trackOutputProvider;
        if (!this.extractorInitialized) {
            this.extractor.init(this);
            this.extractorInitialized = true;
            return;
        }
        this.extractor.seek(0L, 0L);
        for (int i9 = 0; i9 < this.bindingTrackOutputs.size(); i9++) {
            this.bindingTrackOutputs.valueAt(i9).bind(trackOutputProvider);
        }
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorOutput
    public void seekMap(SeekMap seekMap) {
        this.seekMap = seekMap;
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorOutput
    public TrackOutput track(int i9, int i10) {
        BindingTrackOutput bindingTrackOutput = this.bindingTrackOutputs.get(i9);
        if (bindingTrackOutput == null) {
            Assertions.checkState(this.sampleFormats == null);
            bindingTrackOutput = new BindingTrackOutput(i9, i10, i10 == this.primaryTrackType ? this.primaryTrackManifestFormat : null);
            bindingTrackOutput.bind(this.trackOutputProvider);
            this.bindingTrackOutputs.put(i9, bindingTrackOutput);
        }
        return bindingTrackOutput;
    }
}
