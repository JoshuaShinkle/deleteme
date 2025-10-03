package com.cyberlink.media;

import android.annotation.TargetApi;
import android.media.MediaCodecInfo;
import com.cyberlink.media.CLMediaCodec;
import java.util.List;

/* renamed from: com.cyberlink.media.e */
/* loaded from: classes.dex */
public class C1218e {
    @TargetApi(16)
    /* renamed from: a */
    public static MediaCodecInfo m5382a(String str) {
        List<MediaCodecInfo> listM5331c = CLMediaCodec.C1213f.m5331c(str);
        if (listM5331c.isEmpty()) {
            return null;
        }
        return listM5331c.get(0);
    }
}
