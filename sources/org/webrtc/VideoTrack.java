package org.webrtc;

import java.util.LinkedList;

/* loaded from: classes3.dex */
public class VideoTrack extends MediaStreamTrack {
    private final LinkedList<VideoRenderer> renderers;

    public VideoTrack(long j9) {
        super(j9);
        this.renderers = new LinkedList<>();
    }

    private static native void free(long j9);

    private static native void nativeAddRenderer(long j9, long j10);

    private static native void nativeRemoveRenderer(long j9, long j10);

    public void addRenderer(VideoRenderer videoRenderer) {
        this.renderers.add(videoRenderer);
        nativeAddRenderer(this.nativeTrack, videoRenderer.nativeVideoRenderer);
    }

    @Override // org.webrtc.MediaStreamTrack
    public void dispose() {
        while (!this.renderers.isEmpty()) {
            removeRenderer(this.renderers.getFirst());
        }
        super.dispose();
    }

    public void removeRenderer(VideoRenderer videoRenderer) {
        if (this.renderers.remove(videoRenderer)) {
            nativeRemoveRenderer(this.nativeTrack, videoRenderer.nativeVideoRenderer);
            videoRenderer.dispose();
        }
    }
}
