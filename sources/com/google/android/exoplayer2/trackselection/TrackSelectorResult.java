package com.google.android.exoplayer2.trackselection;

import com.google.android.exoplayer2.RendererConfiguration;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.util.Util;

/* loaded from: classes.dex */
public final class TrackSelectorResult {
    public final TrackGroupArray groups;
    public final Object info;
    public final RendererConfiguration[] rendererConfigurations;
    public final boolean[] renderersEnabled;
    public final TrackSelectionArray selections;

    public TrackSelectorResult(TrackGroupArray trackGroupArray, boolean[] zArr, TrackSelectionArray trackSelectionArray, Object obj, RendererConfiguration[] rendererConfigurationArr) {
        this.groups = trackGroupArray;
        this.renderersEnabled = zArr;
        this.selections = trackSelectionArray;
        this.info = obj;
        this.rendererConfigurations = rendererConfigurationArr;
    }

    public boolean isEquivalent(TrackSelectorResult trackSelectorResult) {
        if (trackSelectorResult == null || trackSelectorResult.selections.length != this.selections.length) {
            return false;
        }
        for (int i9 = 0; i9 < this.selections.length; i9++) {
            if (!isEquivalent(trackSelectorResult, i9)) {
                return false;
            }
        }
        return true;
    }

    public boolean isEquivalent(TrackSelectorResult trackSelectorResult, int i9) {
        return trackSelectorResult != null && this.renderersEnabled[i9] == trackSelectorResult.renderersEnabled[i9] && Util.areEqual(this.selections.get(i9), trackSelectorResult.selections.get(i9)) && Util.areEqual(this.rendererConfigurations[i9], trackSelectorResult.rendererConfigurations[i9]);
    }
}
