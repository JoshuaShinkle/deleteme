package com.cyberlink.clgpuimage;

import android.graphics.PointF;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class CLMakeupLiveFilter$LiveFrameInformation {
    public ArrayList<PointF> feature_points = new ArrayList<>();
    public boolean is_flipped;
    public int rotation;

    public void Copy(CLMakeupLiveFilter$LiveFrameInformation cLMakeupLiveFilter$LiveFrameInformation) {
        this.feature_points = new ArrayList<>(cLMakeupLiveFilter$LiveFrameInformation.feature_points);
        this.rotation = cLMakeupLiveFilter$LiveFrameInformation.rotation;
        this.is_flipped = cLMakeupLiveFilter$LiveFrameInformation.is_flipped;
    }
}
