package com.bumptech.glide.load;

import java.io.InputStream;
import java.nio.ByteBuffer;
import p022c1.InterfaceC0705b;

/* loaded from: classes.dex */
public interface ImageHeaderParser {

    public enum ImageType {
        GIF(true),
        JPEG(false),
        RAW(false),
        PNG_A(true),
        PNG(false),
        WEBP_A(true),
        WEBP(false),
        UNKNOWN(false);

        private final boolean hasAlpha;

        ImageType(boolean z8) {
            this.hasAlpha = z8;
        }

        public boolean hasAlpha() {
            return this.hasAlpha;
        }
    }

    /* renamed from: a */
    ImageType mo3833a(ByteBuffer byteBuffer);

    /* renamed from: b */
    ImageType mo3834b(InputStream inputStream);

    /* renamed from: c */
    int mo3835c(InputStream inputStream, InterfaceC0705b interfaceC0705b);
}
