package com.cyberlink.media;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.net.Uri;
import com.cyberlink.media.C1215b;
import java.io.FileDescriptor;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Map;

/* renamed from: com.cyberlink.media.c */
/* loaded from: classes.dex */
public abstract class AbstractC1216c implements C1215b.b {

    /* renamed from: com.cyberlink.media.c$a */
    public static class a extends AbstractC1216c {

        /* renamed from: a */
        public final MediaExtractor f5883a;

        public a(MediaExtractor mediaExtractor) {
            this.f5883a = mediaExtractor;
        }

        @Override // com.cyberlink.media.C1215b.a
        /* renamed from: a */
        public void mo5340a(String str, Map<String, String> map) throws IOException {
            this.f5883a.setDataSource(str, map);
        }

        @Override // com.cyberlink.media.C1215b.b
        public boolean advance() {
            return this.f5883a.advance();
        }

        @Override // com.cyberlink.media.C1215b.b
        /* renamed from: b */
        public MediaFormat mo5341b(int i9) {
            return this.f5883a.getTrackFormat(i9);
        }

        @Override // com.cyberlink.media.C1215b.a
        /* renamed from: c */
        public void mo5342c(FileDescriptor fileDescriptor, long j9, long j10) throws IOException {
            this.f5883a.setDataSource(fileDescriptor, j9, j10);
        }

        @Override // com.cyberlink.media.C1215b.a
        /* renamed from: d */
        public void mo5343d(String str) throws IOException {
            this.f5883a.setDataSource(str);
        }

        @Override // com.cyberlink.media.C1215b.a
        /* renamed from: e */
        public void mo5344e(Context context, Uri uri, Map<String, String> map) throws IOException {
            this.f5883a.setDataSource(context, uri, map);
        }

        @Override // com.cyberlink.media.C1215b.b
        public int getSampleFlags() {
            return this.f5883a.getSampleFlags();
        }

        @Override // com.cyberlink.media.C1215b.b
        public long getSampleTime() {
            return this.f5883a.getSampleTime();
        }

        @Override // com.cyberlink.media.C1215b.b
        public int getSampleTrackIndex() {
            return this.f5883a.getSampleTrackIndex();
        }

        @Override // com.cyberlink.media.C1215b.b
        public int getTrackCount() {
            return this.f5883a.getTrackCount();
        }

        @Override // com.cyberlink.media.C1215b.b
        public int readSampleData(ByteBuffer byteBuffer, int i9) {
            return this.f5883a.readSampleData(byteBuffer, i9);
        }

        @Override // com.cyberlink.media.C1215b.b
        public void release() {
            this.f5883a.release();
        }

        @Override // com.cyberlink.media.C1215b.b
        public void selectTrack(int i9) {
            this.f5883a.selectTrack(i9);
        }
    }

    @TargetApi(18)
    /* renamed from: com.cyberlink.media.c$b */
    public static class b extends a {
        public b(MediaExtractor mediaExtractor) {
            super(mediaExtractor);
        }
    }

    /* renamed from: f */
    public static AbstractC1216c m5364f(MediaExtractor mediaExtractor) {
        if (mediaExtractor != null) {
            return new b(mediaExtractor);
        }
        throw new NullPointerException("extractor is null");
    }
}
