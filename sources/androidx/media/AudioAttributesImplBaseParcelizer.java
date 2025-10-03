package androidx.media;

import androidx.versionedparcelable.VersionedParcel;
import p123l0.C5264c;

/* loaded from: classes.dex */
public final class AudioAttributesImplBaseParcelizer {
    public static C5264c read(VersionedParcel versionedParcel) {
        C5264c c5264c = new C5264c();
        c5264c.f17866a = versionedParcel.m3156p(c5264c.f17866a, 1);
        c5264c.f17867b = versionedParcel.m3156p(c5264c.f17867b, 2);
        c5264c.f17868c = versionedParcel.m3156p(c5264c.f17868c, 3);
        c5264c.f17869d = versionedParcel.m3156p(c5264c.f17869d, 4);
        return c5264c;
    }

    public static void write(C5264c c5264c, VersionedParcel versionedParcel) {
        versionedParcel.m3164x(false, false);
        versionedParcel.m3132F(c5264c.f17866a, 1);
        versionedParcel.m3132F(c5264c.f17867b, 2);
        versionedParcel.m3132F(c5264c.f17868c, 3);
        versionedParcel.m3132F(c5264c.f17869d, 4);
    }
}
