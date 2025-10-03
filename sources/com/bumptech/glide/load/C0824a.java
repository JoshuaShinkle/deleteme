package com.bumptech.glide.load;

import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.resource.bitmap.RecyclableBufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;
import p022c1.InterfaceC0705b;

/* renamed from: com.bumptech.glide.load.a */
/* loaded from: classes.dex */
public final class C0824a {
    /* renamed from: a */
    public static int m3836a(List<ImageHeaderParser> list, InputStream inputStream, InterfaceC0705b interfaceC0705b) throws IOException {
        if (inputStream == null) {
            return -1;
        }
        if (!inputStream.markSupported()) {
            inputStream = new RecyclableBufferedInputStream(inputStream, interfaceC0705b);
        }
        inputStream.mark(5242880);
        int size = list.size();
        for (int i9 = 0; i9 < size; i9++) {
            try {
                int iMo3835c = list.get(i9).mo3835c(inputStream, interfaceC0705b);
                if (iMo3835c != -1) {
                    return iMo3835c;
                }
                inputStream.reset();
            } finally {
                inputStream.reset();
            }
        }
        return -1;
    }

    /* renamed from: b */
    public static ImageHeaderParser.ImageType m3837b(List<ImageHeaderParser> list, InputStream inputStream, InterfaceC0705b interfaceC0705b) throws IOException {
        if (inputStream == null) {
            return ImageHeaderParser.ImageType.UNKNOWN;
        }
        if (!inputStream.markSupported()) {
            inputStream = new RecyclableBufferedInputStream(inputStream, interfaceC0705b);
        }
        inputStream.mark(5242880);
        int size = list.size();
        for (int i9 = 0; i9 < size; i9++) {
            try {
                ImageHeaderParser.ImageType imageTypeMo3834b = list.get(i9).mo3834b(inputStream);
                if (imageTypeMo3834b != ImageHeaderParser.ImageType.UNKNOWN) {
                    return imageTypeMo3834b;
                }
                inputStream.reset();
            } finally {
                inputStream.reset();
            }
        }
        return ImageHeaderParser.ImageType.UNKNOWN;
    }

    /* renamed from: c */
    public static ImageHeaderParser.ImageType m3838c(List<ImageHeaderParser> list, ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            return ImageHeaderParser.ImageType.UNKNOWN;
        }
        int size = list.size();
        for (int i9 = 0; i9 < size; i9++) {
            ImageHeaderParser.ImageType imageTypeMo3833a = list.get(i9).mo3833a(byteBuffer);
            if (imageTypeMo3833a != ImageHeaderParser.ImageType.UNKNOWN) {
                return imageTypeMo3833a;
            }
        }
        return ImageHeaderParser.ImageType.UNKNOWN;
    }
}
