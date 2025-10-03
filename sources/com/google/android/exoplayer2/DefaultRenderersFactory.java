package com.google.android.exoplayer2;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.google.android.exoplayer2.audio.AudioCapabilities;
import com.google.android.exoplayer2.audio.AudioProcessor;
import com.google.android.exoplayer2.audio.AudioRendererEventListener;
import com.google.android.exoplayer2.audio.MediaCodecAudioRenderer;
import com.google.android.exoplayer2.drm.DrmSessionManager;
import com.google.android.exoplayer2.drm.FrameworkMediaCrypto;
import com.google.android.exoplayer2.mediacodec.MediaCodecSelector;
import com.google.android.exoplayer2.metadata.MetadataOutput;
import com.google.android.exoplayer2.metadata.MetadataRenderer;
import com.google.android.exoplayer2.text.TextOutput;
import com.google.android.exoplayer2.text.TextRenderer;
import com.google.android.exoplayer2.video.MediaCodecVideoRenderer;
import com.google.android.exoplayer2.video.VideoRendererEventListener;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class DefaultRenderersFactory implements RenderersFactory {
    public static final long DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS = 5000;
    public static final int EXTENSION_RENDERER_MODE_OFF = 0;
    public static final int EXTENSION_RENDERER_MODE_ON = 1;
    public static final int EXTENSION_RENDERER_MODE_PREFER = 2;
    protected static final int MAX_DROPPED_VIDEO_FRAME_COUNT_TO_NOTIFY = 50;
    private static final String TAG = "DefaultRenderersFactory";
    private final long allowedVideoJoiningTimeMs;
    private final Context context;
    private final DrmSessionManager<FrameworkMediaCrypto> drmSessionManager;
    private final int extensionRendererMode;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ExtensionRendererMode {
    }

    public DefaultRenderersFactory(Context context) {
        this(context, null);
    }

    public AudioProcessor[] buildAudioProcessors() {
        return new AudioProcessor[0];
    }

    public void buildAudioRenderers(Context context, DrmSessionManager<FrameworkMediaCrypto> drmSessionManager, AudioProcessor[] audioProcessorArr, Handler handler, AudioRendererEventListener audioRendererEventListener, int i9, ArrayList<Renderer> arrayList) {
        int i10;
        arrayList.add(new MediaCodecAudioRenderer(MediaCodecSelector.DEFAULT, drmSessionManager, true, handler, audioRendererEventListener, AudioCapabilities.getCapabilities(context), audioProcessorArr));
        if (i9 == 0) {
            return;
        }
        int size = arrayList.size();
        if (i9 == 2) {
            size--;
        }
        try {
            try {
                i10 = size + 1;
            } catch (Exception e9) {
                throw new RuntimeException("Error instantiating Opus extension", e9);
            }
        } catch (ClassNotFoundException unused) {
        }
        try {
            arrayList.add(size, (Renderer) Class.forName("com.google.android.exoplayer2.ext.opus.LibopusAudioRenderer").getConstructor(Handler.class, AudioRendererEventListener.class, AudioProcessor[].class).newInstance(handler, audioRendererEventListener, audioProcessorArr));
            Log.i(TAG, "Loaded LibopusAudioRenderer.");
        } catch (ClassNotFoundException unused2) {
            size = i10;
            i10 = size;
            try {
                int i11 = i10 + 1;
                try {
                    arrayList.add(i10, (Renderer) Class.forName("com.google.android.exoplayer2.ext.flac.LibflacAudioRenderer").getConstructor(Handler.class, AudioRendererEventListener.class, AudioProcessor[].class).newInstance(handler, audioRendererEventListener, audioProcessorArr));
                    Log.i(TAG, "Loaded LibflacAudioRenderer.");
                } catch (ClassNotFoundException unused3) {
                    i10 = i11;
                    i11 = i10;
                    arrayList.add(i11, (Renderer) Class.forName("com.google.android.exoplayer2.ext.ffmpeg.FfmpegAudioRenderer").getConstructor(Handler.class, AudioRendererEventListener.class, AudioProcessor[].class).newInstance(handler, audioRendererEventListener, audioProcessorArr));
                    Log.i(TAG, "Loaded FfmpegAudioRenderer.");
                }
                arrayList.add(i11, (Renderer) Class.forName("com.google.android.exoplayer2.ext.ffmpeg.FfmpegAudioRenderer").getConstructor(Handler.class, AudioRendererEventListener.class, AudioProcessor[].class).newInstance(handler, audioRendererEventListener, audioProcessorArr));
                Log.i(TAG, "Loaded FfmpegAudioRenderer.");
            } catch (Exception e10) {
                throw new RuntimeException("Error instantiating FLAC extension", e10);
            }
        }
        try {
            int i112 = i10 + 1;
            arrayList.add(i10, (Renderer) Class.forName("com.google.android.exoplayer2.ext.flac.LibflacAudioRenderer").getConstructor(Handler.class, AudioRendererEventListener.class, AudioProcessor[].class).newInstance(handler, audioRendererEventListener, audioProcessorArr));
            Log.i(TAG, "Loaded LibflacAudioRenderer.");
        } catch (ClassNotFoundException unused4) {
        }
        try {
            arrayList.add(i112, (Renderer) Class.forName("com.google.android.exoplayer2.ext.ffmpeg.FfmpegAudioRenderer").getConstructor(Handler.class, AudioRendererEventListener.class, AudioProcessor[].class).newInstance(handler, audioRendererEventListener, audioProcessorArr));
            Log.i(TAG, "Loaded FfmpegAudioRenderer.");
        } catch (ClassNotFoundException unused5) {
        } catch (Exception e11) {
            throw new RuntimeException("Error instantiating FFmpeg extension", e11);
        }
    }

    public void buildMetadataRenderers(Context context, MetadataOutput metadataOutput, Looper looper, int i9, ArrayList<Renderer> arrayList) {
        arrayList.add(new MetadataRenderer(metadataOutput, looper));
    }

    public void buildMiscellaneousRenderers(Context context, Handler handler, int i9, ArrayList<Renderer> arrayList) {
    }

    public void buildTextRenderers(Context context, TextOutput textOutput, Looper looper, int i9, ArrayList<Renderer> arrayList) {
        arrayList.add(new TextRenderer(textOutput, looper));
    }

    public void buildVideoRenderers(Context context, DrmSessionManager<FrameworkMediaCrypto> drmSessionManager, long j9, Handler handler, VideoRendererEventListener videoRendererEventListener, int i9, ArrayList<Renderer> arrayList) {
        arrayList.add(new MediaCodecVideoRenderer(context, MediaCodecSelector.DEFAULT, j9, drmSessionManager, false, handler, videoRendererEventListener, 50));
        if (i9 == 0) {
            return;
        }
        int size = arrayList.size();
        if (i9 == 2) {
            size--;
        }
        try {
            arrayList.add(size, (Renderer) Class.forName("com.google.android.exoplayer2.ext.vp9.LibvpxVideoRenderer").getConstructor(Boolean.TYPE, Long.TYPE, Handler.class, VideoRendererEventListener.class, Integer.TYPE).newInstance(Boolean.TRUE, Long.valueOf(j9), handler, videoRendererEventListener, 50));
            Log.i(TAG, "Loaded LibvpxVideoRenderer.");
        } catch (ClassNotFoundException unused) {
        } catch (Exception e9) {
            throw new RuntimeException("Error instantiating VP9 extension", e9);
        }
    }

    @Override // com.google.android.exoplayer2.RenderersFactory
    public Renderer[] createRenderers(Handler handler, VideoRendererEventListener videoRendererEventListener, AudioRendererEventListener audioRendererEventListener, TextOutput textOutput, MetadataOutput metadataOutput) {
        ArrayList<Renderer> arrayList = new ArrayList<>();
        buildVideoRenderers(this.context, this.drmSessionManager, this.allowedVideoJoiningTimeMs, handler, videoRendererEventListener, this.extensionRendererMode, arrayList);
        buildAudioRenderers(this.context, this.drmSessionManager, buildAudioProcessors(), handler, audioRendererEventListener, this.extensionRendererMode, arrayList);
        buildTextRenderers(this.context, textOutput, handler.getLooper(), this.extensionRendererMode, arrayList);
        buildMetadataRenderers(this.context, metadataOutput, handler.getLooper(), this.extensionRendererMode, arrayList);
        buildMiscellaneousRenderers(this.context, handler, this.extensionRendererMode, arrayList);
        return (Renderer[]) arrayList.toArray(new Renderer[arrayList.size()]);
    }

    public DefaultRenderersFactory(Context context, DrmSessionManager<FrameworkMediaCrypto> drmSessionManager) {
        this(context, drmSessionManager, 0);
    }

    public DefaultRenderersFactory(Context context, DrmSessionManager<FrameworkMediaCrypto> drmSessionManager, int i9) {
        this(context, drmSessionManager, i9, DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS);
    }

    public DefaultRenderersFactory(Context context, DrmSessionManager<FrameworkMediaCrypto> drmSessionManager, int i9, long j9) {
        this.context = context;
        this.drmSessionManager = drmSessionManager;
        this.extensionRendererMode = i9;
        this.allowedVideoJoiningTimeMs = j9;
    }
}
