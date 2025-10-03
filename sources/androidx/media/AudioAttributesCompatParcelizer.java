package androidx.media;

import androidx.versionedparcelable.VersionedParcel;
import java.lang.reflect.InvocationTargetException;
import p123l0.InterfaceC5262a;

/* loaded from: classes.dex */
public final class AudioAttributesCompatParcelizer {
    public static AudioAttributesCompat read(VersionedParcel versionedParcel) {
        AudioAttributesCompat audioAttributesCompat = new AudioAttributesCompat();
        audioAttributesCompat.f2262a = (InterfaceC5262a) versionedParcel.m3162v(audioAttributesCompat.f2262a, 1);
        return audioAttributesCompat;
    }

    public static void write(AudioAttributesCompat audioAttributesCompat, VersionedParcel versionedParcel) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        versionedParcel.m3164x(false, false);
        versionedParcel.m3139M(audioAttributesCompat.f2262a, 1);
    }
}
