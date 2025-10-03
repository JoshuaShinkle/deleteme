package androidx.media;

import android.media.AudioAttributes;
import androidx.versionedparcelable.VersionedParcel;
import p123l0.C5263b;

/* loaded from: classes.dex */
public final class AudioAttributesImplApi21Parcelizer {
    public static C5263b read(VersionedParcel versionedParcel) {
        C5263b c5263b = new C5263b();
        c5263b.f17864a = (AudioAttributes) versionedParcel.m3158r(c5263b.f17864a, 1);
        c5263b.f17865b = versionedParcel.m3156p(c5263b.f17865b, 2);
        return c5263b;
    }

    public static void write(C5263b c5263b, VersionedParcel versionedParcel) {
        versionedParcel.m3164x(false, false);
        versionedParcel.m3134H(c5263b.f17864a, 1);
        versionedParcel.m3132F(c5263b.f17865b, 2);
    }
}
