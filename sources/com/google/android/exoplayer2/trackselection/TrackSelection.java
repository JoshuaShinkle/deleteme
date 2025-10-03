package com.google.android.exoplayer2.trackselection;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.chunk.MediaChunk;
import java.util.List;

/* loaded from: classes.dex */
public interface TrackSelection {

    public interface Factory {
        TrackSelection createTrackSelection(TrackGroup trackGroup, int... iArr);
    }

    boolean blacklist(int i9, long j9);

    void disable();

    void enable();

    int evaluateQueueSize(long j9, List<? extends MediaChunk> list);

    Format getFormat(int i9);

    int getIndexInTrackGroup(int i9);

    Format getSelectedFormat();

    int getSelectedIndex();

    int getSelectedIndexInTrackGroup();

    Object getSelectionData();

    int getSelectionReason();

    TrackGroup getTrackGroup();

    int indexOf(int i9);

    int indexOf(Format format);

    int length();

    void onPlaybackSpeed(float f9);

    void updateSelectedTrack(long j9, long j10, long j11);
}
