package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.util.Assertions;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: classes.dex */
public abstract class CompositeMediaSource<T> implements MediaSource {
    private final HashMap<T, MediaSource> childSources = new HashMap<>();
    private ExoPlayer player;

    @Override // com.google.android.exoplayer2.source.MediaSource
    public void maybeThrowSourceInfoRefreshError() {
        Iterator<MediaSource> it = this.childSources.values().iterator();
        while (it.hasNext()) {
            it.next().maybeThrowSourceInfoRefreshError();
        }
    }

    public abstract void onChildSourceInfoRefreshed(T t8, MediaSource mediaSource, Timeline timeline, Object obj);

    public void prepareChildSource(final T t8, final MediaSource mediaSource) {
        Assertions.checkArgument(!this.childSources.containsKey(t8));
        this.childSources.put(t8, mediaSource);
        mediaSource.prepareSource(this.player, false, new MediaSource.Listener() { // from class: com.google.android.exoplayer2.source.CompositeMediaSource.1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.android.exoplayer2.source.MediaSource.Listener
            public void onSourceInfoRefreshed(MediaSource mediaSource2, Timeline timeline, Object obj) {
                CompositeMediaSource.this.onChildSourceInfoRefreshed(t8, mediaSource, timeline, obj);
            }
        });
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public void prepareSource(ExoPlayer exoPlayer, boolean z8, MediaSource.Listener listener) {
        this.player = exoPlayer;
    }

    public void releaseChildSource(T t8) {
        this.childSources.remove(t8).releaseSource();
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public void releaseSource() {
        Iterator<MediaSource> it = this.childSources.values().iterator();
        while (it.hasNext()) {
            it.next().releaseSource();
        }
        this.childSources.clear();
        this.player = null;
    }
}
