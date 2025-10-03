package com.cyberlink.media;

import com.google.android.exoplayer2.util.MimeTypes;

/* loaded from: classes.dex */
public class InsufficientLicenseException extends RuntimeException {
    private static final long serialVersionUID = 2915167539262579716L;
    private final Component mComponent;

    public enum Component {
        DECODER_AC3,
        DECODER_DTS;

        /* renamed from: a */
        public static Component m5347a(String str) {
            if (MimeTypes.AUDIO_AC3.equals(str) || MimeTypes.AUDIO_E_AC3.equals(str)) {
                return DECODER_AC3;
            }
            if ("audio/dts".equals(str)) {
                return DECODER_DTS;
            }
            return null;
        }
    }

    public InsufficientLicenseException(String str) {
        this(Component.m5347a(str));
    }

    public InsufficientLicenseException(Component component) {
        super("No license for " + component);
        this.mComponent = component;
    }
}
