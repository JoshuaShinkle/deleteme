package com.cyberlink.media;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.net.Uri;
import java.io.FileDescriptor;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Locale;
import java.util.Map;
import p125l2.C5278b;

@TargetApi(16)
/* renamed from: com.cyberlink.media.b */
/* loaded from: classes.dex */
public final class C1215b {

    /* renamed from: a */
    public b f5882a;

    /* renamed from: com.cyberlink.media.b$a */
    public interface a {
        /* renamed from: a */
        void mo5340a(String str, Map<String, String> map);

        /* renamed from: c */
        void mo5342c(FileDescriptor fileDescriptor, long j9, long j10);

        /* renamed from: d */
        void mo5343d(String str);

        /* renamed from: e */
        void mo5344e(Context context, Uri uri, Map<String, String> map);
    }

    @TargetApi(16)
    /* renamed from: com.cyberlink.media.b$b */
    public interface b extends a {
        boolean advance();

        /* renamed from: b */
        MediaFormat mo5341b(int i9);

        int getSampleFlags();

        long getSampleTime();

        int getSampleTrackIndex();

        int getTrackCount();

        int readSampleData(ByteBuffer byteBuffer, int i9);

        void release();

        void selectTrack(int i9);
    }

    public C1215b(b bVar) {
        if (bVar == null) {
            throw new IllegalStateException("extractor implementation is null.");
        }
        this.f5882a = bVar;
    }

    /* renamed from: b */
    public static C1215b m5350b(C1217d c1217d) {
        return m5351c(c1217d, m5353j(c1217d));
    }

    /* renamed from: c */
    public static C1215b m5351c(C1217d c1217d, boolean z8) {
        m5352d();
        b cLMediaExtractorExtra = z8 ? new CLMediaExtractorExtra() : AbstractC1216c.m5364f(new MediaExtractor());
        m5354n(cLMediaExtractorExtra, c1217d);
        return new C1215b(cLMediaExtractorExtra);
    }

    /* renamed from: d */
    public static void m5352d() {
    }

    /* renamed from: j */
    public static boolean m5353j(C1217d c1217d) {
        m5352d();
        String str = c1217d.f5884a;
        return (str != null && CLMediaExtractorExtra.m5338h(C5278b.m20551c(str).toLowerCase(Locale.US))) || CLMediaExtractorExtra.m5339i(c1217d.m5367c()) || CLMediaExtractorExtra.m5337g(c1217d);
    }

    /* renamed from: n */
    public static void m5354n(a aVar, C1217d c1217d) {
        Uri uri;
        Context context;
        try {
            if (!c1217d.m5370f() && !c1217d.m5369e() && (uri = c1217d.f5886c) != null && (context = c1217d.f5885b) != null) {
                aVar.mo5344e(context, uri, c1217d.f5887d);
                return;
            }
            FileDescriptor fileDescriptor = c1217d.f5888e;
            if (fileDescriptor != null) {
                aVar.mo5342c(fileDescriptor, c1217d.f5889f, c1217d.f5890g);
                return;
            }
            Map<String, String> map = c1217d.f5887d;
            if (map != null) {
                aVar.mo5340a(c1217d.f5884a, map);
            } else {
                aVar.mo5343d(c1217d.f5884a);
            }
        } catch (IOException e9) {
            throw new IllegalStateException(e9);
        }
    }

    /* renamed from: a */
    public boolean m5355a() {
        return this.f5882a.advance();
    }

    /* renamed from: e */
    public int m5356e() {
        return this.f5882a.getSampleFlags();
    }

    /* renamed from: f */
    public long m5357f() {
        return this.f5882a.getSampleTime();
    }

    /* renamed from: g */
    public int m5358g() {
        return this.f5882a.getSampleTrackIndex();
    }

    /* renamed from: h */
    public int m5359h() {
        return this.f5882a.getTrackCount();
    }

    /* renamed from: i */
    public MediaFormat m5360i(int i9) {
        return this.f5882a.mo5341b(i9);
    }

    /* renamed from: k */
    public int m5361k(ByteBuffer byteBuffer, int i9) {
        return this.f5882a.readSampleData(byteBuffer, i9);
    }

    /* renamed from: l */
    public void m5362l() {
        b bVar = this.f5882a;
        if (bVar != null) {
            bVar.release();
            this.f5882a = null;
        }
    }

    /* renamed from: m */
    public void m5363m(int i9) {
        this.f5882a.selectTrack(i9);
    }

    public String toString() {
        return "CLMediaExtractor [" + this.f5882a + "]";
    }
}
