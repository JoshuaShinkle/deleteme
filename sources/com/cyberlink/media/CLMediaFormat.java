package com.cyberlink.media;

import android.annotation.TargetApi;
import android.media.MediaFormat;
import android.util.Log;
import com.google.android.exoplayer2.util.MimeTypes;
import java.nio.ByteBuffer;
import p104j2.C5092c;
import p125l2.C5277a;
import p125l2.C5279c;

@TargetApi(16)
/* loaded from: classes.dex */
public class CLMediaFormat {
    static {
        C5092c.m19924a();
    }

    /* renamed from: a */
    public static MediaFormat m5346a(MediaFormat mediaFormat) {
        if (C5279c.m20559c("rk30board")) {
            try {
                if (MimeTypes.AUDIO_AAC.equals(mediaFormat.getString("mime")) && mediaFormat.getInteger("channel-count") > 2) {
                    ByteBuffer byteBuffer = mediaFormat.getByteBuffer("csd-0");
                    if (byteBuffer.capacity() > 2) {
                        Log.w("CLMediaFormat", "RockChip AAC-LC codec private data workaround in use.");
                        byteBuffer.clear();
                        mediaFormat.setByteBuffer("csd-0", new C5277a(byteBuffer).m20548c(2).m20546a());
                    }
                }
            } catch (Throwable unused) {
            }
        }
        return mediaFormat;
    }
}
