package org.webrtc;

import java.util.LinkedList;

/* loaded from: classes3.dex */
public class MediaStream {
    final long nativeStream;
    public final LinkedList<AudioTrack> audioTracks = new LinkedList<>();
    public final LinkedList<VideoTrack> videoTracks = new LinkedList<>();
    public final LinkedList<VideoTrack> preservedVideoTracks = new LinkedList<>();

    public MediaStream(long j9) {
        this.nativeStream = j9;
    }

    private static native void free(long j9);

    private static native boolean nativeAddAudioTrack(long j9, long j10);

    private static native boolean nativeAddVideoTrack(long j9, long j10);

    private static native String nativeLabel(long j9);

    private static native boolean nativeRemoveAudioTrack(long j9, long j10);

    private static native boolean nativeRemoveVideoTrack(long j9, long j10);

    public boolean addPreservedTrack(VideoTrack videoTrack) {
        if (!nativeAddVideoTrack(this.nativeStream, videoTrack.nativeTrack)) {
            return false;
        }
        this.preservedVideoTracks.add(videoTrack);
        return true;
    }

    public boolean addTrack(AudioTrack audioTrack) {
        if (!nativeAddAudioTrack(this.nativeStream, audioTrack.nativeTrack)) {
            return false;
        }
        this.audioTracks.add(audioTrack);
        return true;
    }

    public void dispose() {
        while (!this.audioTracks.isEmpty()) {
            AudioTrack first = this.audioTracks.getFirst();
            removeTrack(first);
            first.dispose();
        }
        while (!this.videoTracks.isEmpty()) {
            VideoTrack first2 = this.videoTracks.getFirst();
            removeTrack(first2);
            first2.dispose();
        }
        while (!this.preservedVideoTracks.isEmpty()) {
            removeTrack(this.preservedVideoTracks.getFirst());
        }
        free(this.nativeStream);
    }

    public String label() {
        return nativeLabel(this.nativeStream);
    }

    public boolean removeTrack(AudioTrack audioTrack) {
        this.audioTracks.remove(audioTrack);
        return nativeRemoveAudioTrack(this.nativeStream, audioTrack.nativeTrack);
    }

    public String toString() {
        return "[" + label() + ":A=" + this.audioTracks.size() + ":V=" + this.videoTracks.size() + "]";
    }

    public boolean addTrack(VideoTrack videoTrack) {
        if (!nativeAddVideoTrack(this.nativeStream, videoTrack.nativeTrack)) {
            return false;
        }
        this.videoTracks.add(videoTrack);
        return true;
    }

    public boolean removeTrack(VideoTrack videoTrack) {
        this.videoTracks.remove(videoTrack);
        this.preservedVideoTracks.remove(videoTrack);
        return nativeRemoveVideoTrack(this.nativeStream, videoTrack.nativeTrack);
    }
}
