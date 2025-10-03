package com.google.android.exoplayer2.trackselection;

import android.util.SparseArray;
import android.util.SparseBooleanArray;
import com.google.android.exoplayer2.RendererCapabilities;
import com.google.android.exoplayer2.RendererConfiguration;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.util.Util;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public abstract class MappingTrackSelector extends TrackSelector {
    private MappedTrackInfo currentMappedTrackInfo;
    private final SparseArray<Map<TrackGroupArray, SelectionOverride>> selectionOverrides = new SparseArray<>();
    private final SparseBooleanArray rendererDisabledFlags = new SparseBooleanArray();
    private int tunnelingAudioSessionId = 0;

    public static final class SelectionOverride {
        public final TrackSelection.Factory factory;
        public final int groupIndex;
        public final int length;
        public final int[] tracks;

        public SelectionOverride(TrackSelection.Factory factory, int i9, int... iArr) {
            this.factory = factory;
            this.groupIndex = i9;
            this.tracks = iArr;
            this.length = iArr.length;
        }

        public boolean containsTrack(int i9) {
            for (int i10 : this.tracks) {
                if (i10 == i9) {
                    return true;
                }
            }
            return false;
        }

        public TrackSelection createTrackSelection(TrackGroupArray trackGroupArray) {
            return this.factory.createTrackSelection(trackGroupArray.get(this.groupIndex), this.tracks);
        }
    }

    private boolean[] determineEnabledRenderers(RendererCapabilities[] rendererCapabilitiesArr, TrackSelection[] trackSelectionArr) {
        int length = trackSelectionArr.length;
        boolean[] zArr = new boolean[length];
        for (int i9 = 0; i9 < length; i9++) {
            zArr[i9] = !this.rendererDisabledFlags.get(i9) && (rendererCapabilitiesArr[i9].getTrackType() == 5 || trackSelectionArr[i9] != null);
        }
        return zArr;
    }

    private static int findRenderer(RendererCapabilities[] rendererCapabilitiesArr, TrackGroup trackGroup) {
        int length = rendererCapabilitiesArr.length;
        int i9 = 0;
        for (int i10 = 0; i10 < rendererCapabilitiesArr.length; i10++) {
            RendererCapabilities rendererCapabilities = rendererCapabilitiesArr[i10];
            for (int i11 = 0; i11 < trackGroup.length; i11++) {
                int iSupportsFormat = rendererCapabilities.supportsFormat(trackGroup.getFormat(i11)) & 7;
                if (iSupportsFormat > i9) {
                    if (iSupportsFormat == 4) {
                        return i10;
                    }
                    length = i10;
                    i9 = iSupportsFormat;
                }
            }
        }
        return length;
    }

    private static int[] getFormatSupport(RendererCapabilities rendererCapabilities, TrackGroup trackGroup) {
        int[] iArr = new int[trackGroup.length];
        for (int i9 = 0; i9 < trackGroup.length; i9++) {
            iArr[i9] = rendererCapabilities.supportsFormat(trackGroup.getFormat(i9));
        }
        return iArr;
    }

    private static int[] getMixedMimeTypeAdaptationSupport(RendererCapabilities[] rendererCapabilitiesArr) {
        int length = rendererCapabilitiesArr.length;
        int[] iArr = new int[length];
        for (int i9 = 0; i9 < length; i9++) {
            iArr[i9] = rendererCapabilitiesArr[i9].supportsMixedMimeTypeAdaptation();
        }
        return iArr;
    }

    private static void maybeConfigureRenderersForTunneling(RendererCapabilities[] rendererCapabilitiesArr, TrackGroupArray[] trackGroupArrayArr, int[][][] iArr, RendererConfiguration[] rendererConfigurationArr, TrackSelection[] trackSelectionArr, int i9) {
        boolean z8;
        if (i9 == 0) {
            return;
        }
        boolean z9 = false;
        int i10 = -1;
        int i11 = -1;
        for (int i12 = 0; i12 < rendererCapabilitiesArr.length; i12++) {
            int trackType = rendererCapabilitiesArr[i12].getTrackType();
            TrackSelection trackSelection = trackSelectionArr[i12];
            if ((trackType == 1 || trackType == 2) && trackSelection != null && rendererSupportsTunneling(iArr[i12], trackGroupArrayArr[i12], trackSelection)) {
                if (trackType == 1) {
                    if (i11 != -1) {
                        z8 = false;
                        break;
                    }
                    i11 = i12;
                } else {
                    if (i10 != -1) {
                        z8 = false;
                        break;
                    }
                    i10 = i12;
                }
            }
        }
        z8 = true;
        if (i11 != -1 && i10 != -1) {
            z9 = true;
        }
        if (z8 && z9) {
            RendererConfiguration rendererConfiguration = new RendererConfiguration(i9);
            rendererConfigurationArr[i11] = rendererConfiguration;
            rendererConfigurationArr[i10] = rendererConfiguration;
        }
    }

    private static boolean rendererSupportsTunneling(int[][] iArr, TrackGroupArray trackGroupArray, TrackSelection trackSelection) {
        if (trackSelection == null) {
            return false;
        }
        int iIndexOf = trackGroupArray.indexOf(trackSelection.getTrackGroup());
        for (int i9 = 0; i9 < trackSelection.length(); i9++) {
            if ((iArr[iIndexOf][trackSelection.getIndexInTrackGroup(i9)] & 32) != 32) {
                return false;
            }
        }
        return true;
    }

    public final void clearSelectionOverride(int i9, TrackGroupArray trackGroupArray) {
        Map<TrackGroupArray, SelectionOverride> map = this.selectionOverrides.get(i9);
        if (map == null || !map.containsKey(trackGroupArray)) {
            return;
        }
        map.remove(trackGroupArray);
        if (map.isEmpty()) {
            this.selectionOverrides.remove(i9);
        }
        invalidate();
    }

    public final void clearSelectionOverrides(int i9) {
        Map<TrackGroupArray, SelectionOverride> map = this.selectionOverrides.get(i9);
        if (map == null || map.isEmpty()) {
            return;
        }
        this.selectionOverrides.remove(i9);
        invalidate();
    }

    public final MappedTrackInfo getCurrentMappedTrackInfo() {
        return this.currentMappedTrackInfo;
    }

    public final boolean getRendererDisabled(int i9) {
        return this.rendererDisabledFlags.get(i9);
    }

    public final SelectionOverride getSelectionOverride(int i9, TrackGroupArray trackGroupArray) {
        Map<TrackGroupArray, SelectionOverride> map = this.selectionOverrides.get(i9);
        if (map != null) {
            return map.get(trackGroupArray);
        }
        return null;
    }

    public final boolean hasSelectionOverride(int i9, TrackGroupArray trackGroupArray) {
        Map<TrackGroupArray, SelectionOverride> map = this.selectionOverrides.get(i9);
        return map != null && map.containsKey(trackGroupArray);
    }

    @Override // com.google.android.exoplayer2.trackselection.TrackSelector
    public final void onSelectionActivated(Object obj) {
        this.currentMappedTrackInfo = (MappedTrackInfo) obj;
    }

    @Override // com.google.android.exoplayer2.trackselection.TrackSelector
    public final TrackSelectorResult selectTracks(RendererCapabilities[] rendererCapabilitiesArr, TrackGroupArray trackGroupArray) {
        int[] iArr = new int[rendererCapabilitiesArr.length + 1];
        int length = rendererCapabilitiesArr.length + 1;
        TrackGroup[][] trackGroupArr = new TrackGroup[length][];
        int[][][] iArr2 = new int[rendererCapabilitiesArr.length + 1][][];
        for (int i9 = 0; i9 < length; i9++) {
            int i10 = trackGroupArray.length;
            trackGroupArr[i9] = new TrackGroup[i10];
            iArr2[i9] = new int[i10][];
        }
        int[] mixedMimeTypeAdaptationSupport = getMixedMimeTypeAdaptationSupport(rendererCapabilitiesArr);
        for (int i11 = 0; i11 < trackGroupArray.length; i11++) {
            TrackGroup trackGroup = trackGroupArray.get(i11);
            int iFindRenderer = findRenderer(rendererCapabilitiesArr, trackGroup);
            int[] formatSupport = iFindRenderer == rendererCapabilitiesArr.length ? new int[trackGroup.length] : getFormatSupport(rendererCapabilitiesArr[iFindRenderer], trackGroup);
            int i12 = iArr[iFindRenderer];
            trackGroupArr[iFindRenderer][i12] = trackGroup;
            iArr2[iFindRenderer][i12] = formatSupport;
            iArr[iFindRenderer] = i12 + 1;
        }
        TrackGroupArray[] trackGroupArrayArr = new TrackGroupArray[rendererCapabilitiesArr.length];
        int[] iArr3 = new int[rendererCapabilitiesArr.length];
        for (int i13 = 0; i13 < rendererCapabilitiesArr.length; i13++) {
            int i14 = iArr[i13];
            trackGroupArrayArr[i13] = new TrackGroupArray((TrackGroup[]) Arrays.copyOf(trackGroupArr[i13], i14));
            iArr2[i13] = (int[][]) Arrays.copyOf(iArr2[i13], i14);
            iArr3[i13] = rendererCapabilitiesArr[i13].getTrackType();
        }
        TrackGroupArray trackGroupArray2 = new TrackGroupArray((TrackGroup[]) Arrays.copyOf(trackGroupArr[rendererCapabilitiesArr.length], iArr[rendererCapabilitiesArr.length]));
        TrackSelection[] trackSelectionArrSelectTracks = selectTracks(rendererCapabilitiesArr, trackGroupArrayArr, iArr2);
        int i15 = 0;
        while (true) {
            if (i15 >= rendererCapabilitiesArr.length) {
                break;
            }
            if (this.rendererDisabledFlags.get(i15)) {
                trackSelectionArrSelectTracks[i15] = null;
            } else {
                TrackGroupArray trackGroupArray3 = trackGroupArrayArr[i15];
                if (hasSelectionOverride(i15, trackGroupArray3)) {
                    SelectionOverride selectionOverride = this.selectionOverrides.get(i15).get(trackGroupArray3);
                    trackSelectionArrSelectTracks[i15] = selectionOverride != null ? selectionOverride.createTrackSelection(trackGroupArray3) : null;
                }
            }
            i15++;
        }
        boolean[] zArrDetermineEnabledRenderers = determineEnabledRenderers(rendererCapabilitiesArr, trackSelectionArrSelectTracks);
        MappedTrackInfo mappedTrackInfo = new MappedTrackInfo(iArr3, trackGroupArrayArr, mixedMimeTypeAdaptationSupport, iArr2, trackGroupArray2);
        RendererConfiguration[] rendererConfigurationArr = new RendererConfiguration[rendererCapabilitiesArr.length];
        for (int i16 = 0; i16 < rendererCapabilitiesArr.length; i16++) {
            rendererConfigurationArr[i16] = zArrDetermineEnabledRenderers[i16] ? RendererConfiguration.DEFAULT : null;
        }
        maybeConfigureRenderersForTunneling(rendererCapabilitiesArr, trackGroupArrayArr, iArr2, rendererConfigurationArr, trackSelectionArrSelectTracks, this.tunnelingAudioSessionId);
        return new TrackSelectorResult(trackGroupArray, zArrDetermineEnabledRenderers, new TrackSelectionArray(trackSelectionArrSelectTracks), mappedTrackInfo, rendererConfigurationArr);
    }

    public abstract TrackSelection[] selectTracks(RendererCapabilities[] rendererCapabilitiesArr, TrackGroupArray[] trackGroupArrayArr, int[][][] iArr);

    public final void setRendererDisabled(int i9, boolean z8) {
        if (this.rendererDisabledFlags.get(i9) == z8) {
            return;
        }
        this.rendererDisabledFlags.put(i9, z8);
        invalidate();
    }

    public final void setSelectionOverride(int i9, TrackGroupArray trackGroupArray, SelectionOverride selectionOverride) {
        Map<TrackGroupArray, SelectionOverride> map = this.selectionOverrides.get(i9);
        if (map == null) {
            map = new HashMap<>();
            this.selectionOverrides.put(i9, map);
        }
        if (map.containsKey(trackGroupArray) && Util.areEqual(map.get(trackGroupArray), selectionOverride)) {
            return;
        }
        map.put(trackGroupArray, selectionOverride);
        invalidate();
    }

    public void setTunnelingAudioSessionId(int i9) {
        if (this.tunnelingAudioSessionId != i9) {
            this.tunnelingAudioSessionId = i9;
            invalidate();
        }
    }

    public final void clearSelectionOverrides() {
        if (this.selectionOverrides.size() == 0) {
            return;
        }
        this.selectionOverrides.clear();
        invalidate();
    }

    public static final class MappedTrackInfo {
        public static final int RENDERER_SUPPORT_EXCEEDS_CAPABILITIES_TRACKS = 2;
        public static final int RENDERER_SUPPORT_NO_TRACKS = 0;
        public static final int RENDERER_SUPPORT_PLAYABLE_TRACKS = 3;
        public static final int RENDERER_SUPPORT_UNSUPPORTED_TRACKS = 1;
        private final int[][][] formatSupport;
        public final int length;
        private final int[] mixedMimeTypeAdaptiveSupport;
        private final int[] rendererTrackTypes;
        private final TrackGroupArray[] trackGroups;
        private final TrackGroupArray unassociatedTrackGroups;

        public MappedTrackInfo(int[] iArr, TrackGroupArray[] trackGroupArrayArr, int[] iArr2, int[][][] iArr3, TrackGroupArray trackGroupArray) {
            this.rendererTrackTypes = iArr;
            this.trackGroups = trackGroupArrayArr;
            this.formatSupport = iArr3;
            this.mixedMimeTypeAdaptiveSupport = iArr2;
            this.unassociatedTrackGroups = trackGroupArray;
            this.length = trackGroupArrayArr.length;
        }

        public int getAdaptiveSupport(int i9, int i10, boolean z8) {
            int i11 = this.trackGroups[i9].get(i10).length;
            int[] iArr = new int[i11];
            int i12 = 0;
            for (int i13 = 0; i13 < i11; i13++) {
                int trackFormatSupport = getTrackFormatSupport(i9, i10, i13);
                if (trackFormatSupport == 4 || (z8 && trackFormatSupport == 3)) {
                    iArr[i12] = i13;
                    i12++;
                }
            }
            return getAdaptiveSupport(i9, i10, Arrays.copyOf(iArr, i12));
        }

        /* JADX WARN: Code restructure failed: missing block: B:16:0x0025, code lost:
        
            r1 = r1 + 1;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public int getRendererSupport(int i9) {
            int i10;
            int[][] iArr = this.formatSupport[i9];
            int i11 = 0;
            int iMax = 0;
            while (i11 < iArr.length) {
                int i12 = 0;
                while (true) {
                    int[] iArr2 = iArr[i11];
                    if (i12 < iArr2.length) {
                        int i13 = iArr2[i12] & 7;
                        if (i13 == 3) {
                            i10 = 2;
                        } else {
                            if (i13 == 4) {
                                return 3;
                            }
                            i10 = 1;
                        }
                        iMax = Math.max(iMax, i10);
                        i12++;
                    }
                }
            }
            return iMax;
        }

        public int getTrackFormatSupport(int i9, int i10, int i11) {
            return this.formatSupport[i9][i10][i11] & 7;
        }

        public TrackGroupArray getTrackGroups(int i9) {
            return this.trackGroups[i9];
        }

        public int getTrackTypeRendererSupport(int i9) {
            int iMax = 0;
            for (int i10 = 0; i10 < this.length; i10++) {
                if (this.rendererTrackTypes[i10] == i9) {
                    iMax = Math.max(iMax, getRendererSupport(i10));
                }
            }
            return iMax;
        }

        public TrackGroupArray getUnassociatedTrackGroups() {
            return this.unassociatedTrackGroups;
        }

        public int getAdaptiveSupport(int i9, int i10, int[] iArr) {
            int i11 = 0;
            int iMin = 16;
            String str = null;
            boolean z8 = false;
            int i12 = 0;
            while (i11 < iArr.length) {
                String str2 = this.trackGroups[i9].get(i10).getFormat(iArr[i11]).sampleMimeType;
                int i13 = i12 + 1;
                if (i12 == 0) {
                    str = str2;
                } else {
                    z8 |= !Util.areEqual(str, str2);
                }
                iMin = Math.min(iMin, this.formatSupport[i9][i10][i11] & 24);
                i11++;
                i12 = i13;
            }
            return z8 ? Math.min(iMin, this.mixedMimeTypeAdaptiveSupport[i9]) : iMin;
        }
    }
}
