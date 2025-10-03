package com.google.android.gms.internal.maps;

import android.os.IInterface;

/* loaded from: classes2.dex */
public interface zzac extends IInterface {
    void clearTileCache();

    boolean getFadeIn();

    String getId();

    float getTransparency();

    float getZIndex();

    boolean isVisible();

    void remove();

    void setFadeIn(boolean z8);

    void setTransparency(float f9);

    void setVisible(boolean z8);

    void setZIndex(float f9);

    boolean zza(zzac zzacVar);

    int zzj();
}
