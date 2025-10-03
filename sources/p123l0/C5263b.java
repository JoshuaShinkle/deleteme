package p123l0;

import android.annotation.TargetApi;
import android.media.AudioAttributes;

@TargetApi(21)
/* renamed from: l0.b */
/* loaded from: classes.dex */
public class C5263b implements InterfaceC5262a {

    /* renamed from: a */
    public AudioAttributes f17864a;

    /* renamed from: b */
    public int f17865b = -1;

    public boolean equals(Object obj) {
        if (obj instanceof C5263b) {
            return this.f17864a.equals(((C5263b) obj).f17864a);
        }
        return false;
    }

    public int hashCode() {
        return this.f17864a.hashCode();
    }

    public String toString() {
        return "AudioAttributesCompat: audioattributes=" + this.f17864a;
    }
}
