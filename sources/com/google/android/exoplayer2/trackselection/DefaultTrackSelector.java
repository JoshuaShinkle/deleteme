package com.google.android.exoplayer2.trackselection;

import android.content.Context;
import android.graphics.Point;
import android.text.TextUtils;
import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.RendererCapabilities;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
public class DefaultTrackSelector extends MappingTrackSelector {
    private static final float FRACTION_TO_CONSIDER_FULLSCREEN = 0.98f;
    private static final int[] NO_TRACKS = new int[0];
    private static final int WITHIN_RENDERER_CAPABILITIES_BONUS = 1000;
    private final TrackSelection.Factory adaptiveTrackSelectionFactory;
    private final AtomicReference<Parameters> paramsReference;

    public static final class AudioConfigurationTuple {
        public final int channelCount;
        public final String mimeType;
        public final int sampleRate;

        public AudioConfigurationTuple(int i9, int i10, String str) {
            this.channelCount = i9;
            this.sampleRate = i10;
            this.mimeType = str;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || AudioConfigurationTuple.class != obj.getClass()) {
                return false;
            }
            AudioConfigurationTuple audioConfigurationTuple = (AudioConfigurationTuple) obj;
            return this.channelCount == audioConfigurationTuple.channelCount && this.sampleRate == audioConfigurationTuple.sampleRate && TextUtils.equals(this.mimeType, audioConfigurationTuple.mimeType);
        }

        public int hashCode() {
            int i9 = ((this.channelCount * 31) + this.sampleRate) * 31;
            String str = this.mimeType;
            return i9 + (str != null ? str.hashCode() : 0);
        }
    }

    public static final class AudioTrackScore implements Comparable<AudioTrackScore> {
        private final int bitrate;
        private final int channelCount;
        private final int defaultSelectionFlagScore;
        private final int matchLanguageScore;
        private final Parameters parameters;
        private final int sampleRate;
        private final int withinRendererCapabilitiesScore;

        public AudioTrackScore(Format format, Parameters parameters, int i9) {
            this.parameters = parameters;
            this.withinRendererCapabilitiesScore = DefaultTrackSelector.isSupported(i9, false) ? 1 : 0;
            this.matchLanguageScore = DefaultTrackSelector.formatHasLanguage(format, parameters.preferredAudioLanguage) ? 1 : 0;
            this.defaultSelectionFlagScore = (format.selectionFlags & 1) != 0 ? 1 : 0;
            this.channelCount = format.channelCount;
            this.sampleRate = format.sampleRate;
            this.bitrate = format.bitrate;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || AudioTrackScore.class != obj.getClass()) {
                return false;
            }
            AudioTrackScore audioTrackScore = (AudioTrackScore) obj;
            return this.withinRendererCapabilitiesScore == audioTrackScore.withinRendererCapabilitiesScore && this.matchLanguageScore == audioTrackScore.matchLanguageScore && this.defaultSelectionFlagScore == audioTrackScore.defaultSelectionFlagScore && this.channelCount == audioTrackScore.channelCount && this.sampleRate == audioTrackScore.sampleRate && this.bitrate == audioTrackScore.bitrate;
        }

        public int hashCode() {
            return (((((((((this.withinRendererCapabilitiesScore * 31) + this.matchLanguageScore) * 31) + this.defaultSelectionFlagScore) * 31) + this.channelCount) * 31) + this.sampleRate) * 31) + this.bitrate;
        }

        @Override // java.lang.Comparable
        public int compareTo(AudioTrackScore audioTrackScore) {
            int iCompareInts;
            int i9 = this.withinRendererCapabilitiesScore;
            int i10 = audioTrackScore.withinRendererCapabilitiesScore;
            if (i9 != i10) {
                return DefaultTrackSelector.compareInts(i9, i10);
            }
            int i11 = this.matchLanguageScore;
            int i12 = audioTrackScore.matchLanguageScore;
            if (i11 != i12) {
                return DefaultTrackSelector.compareInts(i11, i12);
            }
            int i13 = this.defaultSelectionFlagScore;
            int i14 = audioTrackScore.defaultSelectionFlagScore;
            if (i13 != i14) {
                return DefaultTrackSelector.compareInts(i13, i14);
            }
            if (this.parameters.forceLowestBitrate) {
                return DefaultTrackSelector.compareInts(audioTrackScore.bitrate, this.bitrate);
            }
            int i15 = i9 != 1 ? -1 : 1;
            int i16 = this.channelCount;
            int i17 = audioTrackScore.channelCount;
            if (i16 != i17) {
                iCompareInts = DefaultTrackSelector.compareInts(i16, i17);
            } else {
                int i18 = this.sampleRate;
                int i19 = audioTrackScore.sampleRate;
                iCompareInts = i18 != i19 ? DefaultTrackSelector.compareInts(i18, i19) : DefaultTrackSelector.compareInts(this.bitrate, audioTrackScore.bitrate);
            }
            return i15 * iCompareInts;
        }
    }

    public static final class Parameters {
        public static final Parameters DEFAULT = new Parameters();
        public final boolean allowMixedMimeAdaptiveness;
        public final boolean allowNonSeamlessAdaptiveness;
        public final int disabledTextTrackSelectionFlags;
        public final boolean exceedRendererCapabilitiesIfNecessary;
        public final boolean exceedVideoConstraintsIfNecessary;
        public final boolean forceLowestBitrate;
        public final int maxVideoBitrate;
        public final int maxVideoHeight;
        public final int maxVideoWidth;
        public final String preferredAudioLanguage;
        public final String preferredTextLanguage;
        public final boolean selectUndeterminedTextLanguage;
        public final int viewportHeight;
        public final boolean viewportOrientationMayChange;
        public final int viewportWidth;

        public ParametersBuilder buildUpon() {
            return new ParametersBuilder(this);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || Parameters.class != obj.getClass()) {
                return false;
            }
            Parameters parameters = (Parameters) obj;
            return this.selectUndeterminedTextLanguage == parameters.selectUndeterminedTextLanguage && this.disabledTextTrackSelectionFlags == parameters.disabledTextTrackSelectionFlags && this.forceLowestBitrate == parameters.forceLowestBitrate && this.allowMixedMimeAdaptiveness == parameters.allowMixedMimeAdaptiveness && this.allowNonSeamlessAdaptiveness == parameters.allowNonSeamlessAdaptiveness && this.maxVideoWidth == parameters.maxVideoWidth && this.maxVideoHeight == parameters.maxVideoHeight && this.exceedVideoConstraintsIfNecessary == parameters.exceedVideoConstraintsIfNecessary && this.exceedRendererCapabilitiesIfNecessary == parameters.exceedRendererCapabilitiesIfNecessary && this.viewportOrientationMayChange == parameters.viewportOrientationMayChange && this.viewportWidth == parameters.viewportWidth && this.viewportHeight == parameters.viewportHeight && this.maxVideoBitrate == parameters.maxVideoBitrate && TextUtils.equals(this.preferredAudioLanguage, parameters.preferredAudioLanguage) && TextUtils.equals(this.preferredTextLanguage, parameters.preferredTextLanguage);
        }

        public int hashCode() {
            return ((((((((((((((((((((((((((((this.selectUndeterminedTextLanguage ? 1 : 0) * 31) + this.disabledTextTrackSelectionFlags) * 31) + (this.forceLowestBitrate ? 1 : 0)) * 31) + (this.allowMixedMimeAdaptiveness ? 1 : 0)) * 31) + (this.allowNonSeamlessAdaptiveness ? 1 : 0)) * 31) + this.maxVideoWidth) * 31) + this.maxVideoHeight) * 31) + (this.exceedVideoConstraintsIfNecessary ? 1 : 0)) * 31) + (this.exceedRendererCapabilitiesIfNecessary ? 1 : 0)) * 31) + (this.viewportOrientationMayChange ? 1 : 0)) * 31) + this.viewportWidth) * 31) + this.viewportHeight) * 31) + this.maxVideoBitrate) * 31) + this.preferredAudioLanguage.hashCode()) * 31) + this.preferredTextLanguage.hashCode();
        }

        private Parameters() {
            this(null, null, false, 0, false, false, true, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, true, true, Integer.MAX_VALUE, Integer.MAX_VALUE, true);
        }

        private Parameters(String str, String str2, boolean z8, int i9, boolean z9, boolean z10, boolean z11, int i10, int i11, int i12, boolean z12, boolean z13, int i13, int i14, boolean z14) {
            this.preferredAudioLanguage = Util.normalizeLanguageCode(str);
            this.preferredTextLanguage = Util.normalizeLanguageCode(str2);
            this.selectUndeterminedTextLanguage = z8;
            this.disabledTextTrackSelectionFlags = i9;
            this.forceLowestBitrate = z9;
            this.allowMixedMimeAdaptiveness = z10;
            this.allowNonSeamlessAdaptiveness = z11;
            this.maxVideoWidth = i10;
            this.maxVideoHeight = i11;
            this.maxVideoBitrate = i12;
            this.exceedVideoConstraintsIfNecessary = z12;
            this.exceedRendererCapabilitiesIfNecessary = z13;
            this.viewportWidth = i13;
            this.viewportHeight = i14;
            this.viewportOrientationMayChange = z14;
        }
    }

    public static final class ParametersBuilder {
        private boolean allowMixedMimeAdaptiveness;
        private boolean allowNonSeamlessAdaptiveness;
        private int disabledTextTrackSelectionFlags;
        private boolean exceedRendererCapabilitiesIfNecessary;
        private boolean exceedVideoConstraintsIfNecessary;
        private boolean forceLowestBitrate;
        private int maxVideoBitrate;
        private int maxVideoHeight;
        private int maxVideoWidth;
        private String preferredAudioLanguage;
        private String preferredTextLanguage;
        private boolean selectUndeterminedTextLanguage;
        private int viewportHeight;
        private boolean viewportOrientationMayChange;
        private int viewportWidth;

        public Parameters build() {
            return new Parameters(this.preferredAudioLanguage, this.preferredTextLanguage, this.selectUndeterminedTextLanguage, this.disabledTextTrackSelectionFlags, this.forceLowestBitrate, this.allowMixedMimeAdaptiveness, this.allowNonSeamlessAdaptiveness, this.maxVideoWidth, this.maxVideoHeight, this.maxVideoBitrate, this.exceedVideoConstraintsIfNecessary, this.exceedRendererCapabilitiesIfNecessary, this.viewportWidth, this.viewportHeight, this.viewportOrientationMayChange);
        }

        public ParametersBuilder clearVideoSizeConstraints() {
            return setMaxVideoSize(Integer.MAX_VALUE, Integer.MAX_VALUE);
        }

        public ParametersBuilder clearViewportSizeConstraints() {
            return setViewportSize(Integer.MAX_VALUE, Integer.MAX_VALUE, true);
        }

        public ParametersBuilder setAllowMixedMimeAdaptiveness(boolean z8) {
            this.allowMixedMimeAdaptiveness = z8;
            return this;
        }

        public ParametersBuilder setAllowNonSeamlessAdaptiveness(boolean z8) {
            this.allowNonSeamlessAdaptiveness = z8;
            return this;
        }

        public ParametersBuilder setDisabledTextTrackSelectionFlags(int i9) {
            this.disabledTextTrackSelectionFlags = i9;
            return this;
        }

        public ParametersBuilder setExceedRendererCapabilitiesIfNecessary(boolean z8) {
            this.exceedRendererCapabilitiesIfNecessary = z8;
            return this;
        }

        public ParametersBuilder setExceedVideoConstraintsIfNecessary(boolean z8) {
            this.exceedVideoConstraintsIfNecessary = z8;
            return this;
        }

        public ParametersBuilder setForceLowestBitrate(boolean z8) {
            this.forceLowestBitrate = z8;
            return this;
        }

        public ParametersBuilder setMaxVideoBitrate(int i9) {
            this.maxVideoBitrate = i9;
            return this;
        }

        public ParametersBuilder setMaxVideoSize(int i9, int i10) {
            this.maxVideoWidth = i9;
            this.maxVideoHeight = i10;
            return this;
        }

        public ParametersBuilder setMaxVideoSizeSd() {
            return setMaxVideoSize(1279, 719);
        }

        public ParametersBuilder setPreferredAudioLanguage(String str) {
            this.preferredAudioLanguage = str;
            return this;
        }

        public ParametersBuilder setPreferredTextLanguage(String str) {
            this.preferredTextLanguage = str;
            return this;
        }

        public ParametersBuilder setSelectUndeterminedTextLanguage(boolean z8) {
            this.selectUndeterminedTextLanguage = z8;
            return this;
        }

        public ParametersBuilder setViewportSize(int i9, int i10, boolean z8) {
            this.viewportWidth = i9;
            this.viewportHeight = i10;
            this.viewportOrientationMayChange = z8;
            return this;
        }

        public ParametersBuilder setViewportSizeToPhysicalDisplaySize(Context context, boolean z8) {
            Point physicalDisplaySize = Util.getPhysicalDisplaySize(context);
            return setViewportSize(physicalDisplaySize.x, physicalDisplaySize.y, z8);
        }

        public ParametersBuilder() {
            this(Parameters.DEFAULT);
        }

        private ParametersBuilder(Parameters parameters) {
            this.preferredAudioLanguage = parameters.preferredAudioLanguage;
            this.preferredTextLanguage = parameters.preferredTextLanguage;
            this.selectUndeterminedTextLanguage = parameters.selectUndeterminedTextLanguage;
            this.disabledTextTrackSelectionFlags = parameters.disabledTextTrackSelectionFlags;
            this.forceLowestBitrate = parameters.forceLowestBitrate;
            this.allowMixedMimeAdaptiveness = parameters.allowMixedMimeAdaptiveness;
            this.allowNonSeamlessAdaptiveness = parameters.allowNonSeamlessAdaptiveness;
            this.maxVideoWidth = parameters.maxVideoWidth;
            this.maxVideoHeight = parameters.maxVideoHeight;
            this.maxVideoBitrate = parameters.maxVideoBitrate;
            this.exceedVideoConstraintsIfNecessary = parameters.exceedVideoConstraintsIfNecessary;
            this.exceedRendererCapabilitiesIfNecessary = parameters.exceedRendererCapabilitiesIfNecessary;
            this.viewportWidth = parameters.viewportWidth;
            this.viewportHeight = parameters.viewportHeight;
            this.viewportOrientationMayChange = parameters.viewportOrientationMayChange;
        }
    }

    public DefaultTrackSelector() {
        this((TrackSelection.Factory) null);
    }

    private static int compareFormatValues(int i9, int i10) {
        if (i9 == -1) {
            return i10 == -1 ? 0 : -1;
        }
        if (i10 == -1) {
            return 1;
        }
        return i9 - i10;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int compareInts(int i9, int i10) {
        if (i9 > i10) {
            return 1;
        }
        return i10 > i9 ? -1 : 0;
    }

    private static void filterAdaptiveVideoTrackCountForMimeType(TrackGroup trackGroup, int[] iArr, int i9, String str, int i10, int i11, int i12, List<Integer> list) {
        for (int size = list.size() - 1; size >= 0; size--) {
            int iIntValue = list.get(size).intValue();
            if (!isSupportedAdaptiveVideoTrack(trackGroup.getFormat(iIntValue), str, iArr[iIntValue], i9, i10, i11, i12)) {
                list.remove(size);
            }
        }
    }

    public static boolean formatHasLanguage(Format format, String str) {
        return str != null && TextUtils.equals(str, Util.normalizeLanguageCode(format.language));
    }

    public static boolean formatHasNoLanguage(Format format) {
        return TextUtils.isEmpty(format.language) || formatHasLanguage(format, C3322C.LANGUAGE_UNDETERMINED);
    }

    private static int getAdaptiveAudioTrackCount(TrackGroup trackGroup, int[] iArr, AudioConfigurationTuple audioConfigurationTuple) {
        int i9 = 0;
        for (int i10 = 0; i10 < trackGroup.length; i10++) {
            if (isSupportedAdaptiveAudioTrack(trackGroup.getFormat(i10), iArr[i10], audioConfigurationTuple)) {
                i9++;
            }
        }
        return i9;
    }

    private static int[] getAdaptiveAudioTracks(TrackGroup trackGroup, int[] iArr, boolean z8) {
        int adaptiveAudioTrackCount;
        HashSet hashSet = new HashSet();
        int i9 = 0;
        AudioConfigurationTuple audioConfigurationTuple = null;
        for (int i10 = 0; i10 < trackGroup.length; i10++) {
            Format format = trackGroup.getFormat(i10);
            AudioConfigurationTuple audioConfigurationTuple2 = new AudioConfigurationTuple(format.channelCount, format.sampleRate, z8 ? null : format.sampleMimeType);
            if (hashSet.add(audioConfigurationTuple2) && (adaptiveAudioTrackCount = getAdaptiveAudioTrackCount(trackGroup, iArr, audioConfigurationTuple2)) > i9) {
                i9 = adaptiveAudioTrackCount;
                audioConfigurationTuple = audioConfigurationTuple2;
            }
        }
        if (i9 <= 1) {
            return NO_TRACKS;
        }
        int[] iArr2 = new int[i9];
        int i11 = 0;
        for (int i12 = 0; i12 < trackGroup.length; i12++) {
            if (isSupportedAdaptiveAudioTrack(trackGroup.getFormat(i12), iArr[i12], audioConfigurationTuple)) {
                iArr2[i11] = i12;
                i11++;
            }
        }
        return iArr2;
    }

    private static int getAdaptiveVideoTrackCountForMimeType(TrackGroup trackGroup, int[] iArr, int i9, String str, int i10, int i11, int i12, List<Integer> list) {
        int i13 = 0;
        for (int i14 = 0; i14 < list.size(); i14++) {
            int iIntValue = list.get(i14).intValue();
            if (isSupportedAdaptiveVideoTrack(trackGroup.getFormat(iIntValue), str, iArr[iIntValue], i9, i10, i11, i12)) {
                i13++;
            }
        }
        return i13;
    }

    private static int[] getAdaptiveVideoTracksForGroup(TrackGroup trackGroup, int[] iArr, boolean z8, int i9, int i10, int i11, int i12, int i13, int i14, boolean z9) {
        String str;
        int adaptiveVideoTrackCountForMimeType;
        if (trackGroup.length < 2) {
            return NO_TRACKS;
        }
        List<Integer> viewportFilteredTrackIndices = getViewportFilteredTrackIndices(trackGroup, i13, i14, z9);
        if (viewportFilteredTrackIndices.size() < 2) {
            return NO_TRACKS;
        }
        if (z8) {
            str = null;
        } else {
            HashSet hashSet = new HashSet();
            String str2 = null;
            int i15 = 0;
            for (int i16 = 0; i16 < viewportFilteredTrackIndices.size(); i16++) {
                String str3 = trackGroup.getFormat(viewportFilteredTrackIndices.get(i16).intValue()).sampleMimeType;
                if (hashSet.add(str3) && (adaptiveVideoTrackCountForMimeType = getAdaptiveVideoTrackCountForMimeType(trackGroup, iArr, i9, str3, i10, i11, i12, viewportFilteredTrackIndices)) > i15) {
                    i15 = adaptiveVideoTrackCountForMimeType;
                    str2 = str3;
                }
            }
            str = str2;
        }
        filterAdaptiveVideoTrackCountForMimeType(trackGroup, iArr, i9, str, i10, i11, i12, viewportFilteredTrackIndices);
        return viewportFilteredTrackIndices.size() < 2 ? NO_TRACKS : Util.toArray(viewportFilteredTrackIndices);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0010  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static Point getMaxVideoSizeInViewport(boolean z8, int i9, int i10, int i11, int i12) {
        if (z8) {
            if ((i11 > i12) == (i9 > i10)) {
                i10 = i9;
                i9 = i10;
            }
        }
        int i13 = i11 * i9;
        int i14 = i12 * i10;
        return i13 >= i14 ? new Point(i10, Util.ceilDivide(i14, i11)) : new Point(Util.ceilDivide(i13, i12), i9);
    }

    private static List<Integer> getViewportFilteredTrackIndices(TrackGroup trackGroup, int i9, int i10, boolean z8) {
        int i11;
        ArrayList arrayList = new ArrayList(trackGroup.length);
        for (int i12 = 0; i12 < trackGroup.length; i12++) {
            arrayList.add(Integer.valueOf(i12));
        }
        if (i9 != Integer.MAX_VALUE && i10 != Integer.MAX_VALUE) {
            int i13 = Integer.MAX_VALUE;
            for (int i14 = 0; i14 < trackGroup.length; i14++) {
                Format format = trackGroup.getFormat(i14);
                int i15 = format.width;
                if (i15 > 0 && (i11 = format.height) > 0) {
                    Point maxVideoSizeInViewport = getMaxVideoSizeInViewport(z8, i9, i10, i15, i11);
                    int i16 = format.width;
                    int i17 = format.height;
                    int i18 = i16 * i17;
                    if (i16 >= ((int) (maxVideoSizeInViewport.x * FRACTION_TO_CONSIDER_FULLSCREEN)) && i17 >= ((int) (maxVideoSizeInViewport.y * FRACTION_TO_CONSIDER_FULLSCREEN)) && i18 < i13) {
                        i13 = i18;
                    }
                }
            }
            if (i13 != Integer.MAX_VALUE) {
                for (int size = arrayList.size() - 1; size >= 0; size--) {
                    int pixelCount = trackGroup.getFormat(((Integer) arrayList.get(size)).intValue()).getPixelCount();
                    if (pixelCount == -1 || pixelCount > i13) {
                        arrayList.remove(size);
                    }
                }
            }
        }
        return arrayList;
    }

    public static boolean isSupported(int i9, boolean z8) {
        int i10 = i9 & 7;
        return i10 == 4 || (z8 && i10 == 3);
    }

    private static boolean isSupportedAdaptiveAudioTrack(Format format, int i9, AudioConfigurationTuple audioConfigurationTuple) {
        if (!isSupported(i9, false) || format.channelCount != audioConfigurationTuple.channelCount || format.sampleRate != audioConfigurationTuple.sampleRate) {
            return false;
        }
        String str = audioConfigurationTuple.mimeType;
        return str == null || TextUtils.equals(str, format.sampleMimeType);
    }

    private static boolean isSupportedAdaptiveVideoTrack(Format format, String str, int i9, int i10, int i11, int i12, int i13) {
        if (!isSupported(i9, false) || (i9 & i10) == 0) {
            return false;
        }
        if (str != null && !Util.areEqual(format.sampleMimeType, str)) {
            return false;
        }
        int i14 = format.width;
        if (i14 != -1 && i14 > i11) {
            return false;
        }
        int i15 = format.height;
        if (i15 != -1 && i15 > i12) {
            return false;
        }
        int i16 = format.bitrate;
        return i16 == -1 || i16 <= i13;
    }

    private static TrackSelection selectAdaptiveVideoTrack(RendererCapabilities rendererCapabilities, TrackGroupArray trackGroupArray, int[][] iArr, Parameters parameters, TrackSelection.Factory factory) {
        int i9 = parameters.allowNonSeamlessAdaptiveness ? 24 : 16;
        boolean z8 = parameters.allowMixedMimeAdaptiveness && (rendererCapabilities.supportsMixedMimeTypeAdaptation() & i9) != 0;
        for (int i10 = 0; i10 < trackGroupArray.length; i10++) {
            TrackGroup trackGroup = trackGroupArray.get(i10);
            int[] adaptiveVideoTracksForGroup = getAdaptiveVideoTracksForGroup(trackGroup, iArr[i10], z8, i9, parameters.maxVideoWidth, parameters.maxVideoHeight, parameters.maxVideoBitrate, parameters.viewportWidth, parameters.viewportHeight, parameters.viewportOrientationMayChange);
            if (adaptiveVideoTracksForGroup.length > 0) {
                return factory.createTrackSelection(trackGroup, adaptiveVideoTracksForGroup);
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x0088  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static TrackSelection selectFixedVideoTrack(TrackGroupArray trackGroupArray, int[][] iArr, Parameters parameters) {
        int i9;
        int i10;
        int i11;
        TrackGroupArray trackGroupArray2 = trackGroupArray;
        int i12 = -1;
        int pixelCount = -1;
        int i13 = -1;
        int i14 = 0;
        TrackGroup trackGroup = null;
        int i15 = 0;
        int i16 = 0;
        while (i14 < trackGroupArray2.length) {
            TrackGroup trackGroup2 = trackGroupArray2.get(i14);
            List<Integer> viewportFilteredTrackIndices = getViewportFilteredTrackIndices(trackGroup2, parameters.viewportWidth, parameters.viewportHeight, parameters.viewportOrientationMayChange);
            int[] iArr2 = iArr[i14];
            int i17 = 0;
            while (i17 < trackGroup2.length) {
                if (isSupported(iArr2[i17], parameters.exceedRendererCapabilitiesIfNecessary)) {
                    Format format = trackGroup2.getFormat(i17);
                    boolean z8 = true;
                    boolean z9 = viewportFilteredTrackIndices.contains(Integer.valueOf(i17)) && ((i9 = format.width) == i12 || i9 <= parameters.maxVideoWidth) && (((i10 = format.height) == i12 || i10 <= parameters.maxVideoHeight) && ((i11 = format.bitrate) == i12 || i11 <= parameters.maxVideoBitrate));
                    if (z9 || parameters.exceedVideoConstraintsIfNecessary) {
                        int i18 = z9 ? 2 : 1;
                        boolean zIsSupported = isSupported(iArr2[i17], false);
                        if (zIsSupported) {
                            i18 += 1000;
                        }
                        boolean z10 = i18 > i16;
                        if (i18 == i16) {
                            if (parameters.forceLowestBitrate) {
                                if (compareFormatValues(format.bitrate, i13) >= 0) {
                                    z8 = false;
                                }
                                z10 = z8;
                            } else {
                                int pixelCount2 = format.getPixelCount();
                                int iCompareFormatValues = pixelCount2 != pixelCount ? compareFormatValues(pixelCount2, pixelCount) : compareFormatValues(format.bitrate, i13);
                                if (!zIsSupported || !z9 ? iCompareFormatValues >= 0 : iCompareFormatValues <= 0) {
                                }
                                z10 = z8;
                            }
                        }
                        if (z10) {
                            i13 = format.bitrate;
                            pixelCount = format.getPixelCount();
                            trackGroup = trackGroup2;
                            i15 = i17;
                            i16 = i18;
                        }
                    }
                }
                i17++;
                i12 = -1;
            }
            i14++;
            trackGroupArray2 = trackGroupArray;
            i12 = -1;
        }
        if (trackGroup == null) {
            return null;
        }
        return new FixedTrackSelection(trackGroup, i15);
    }

    public Parameters getParameters() {
        return this.paramsReference.get();
    }

    public TrackSelection selectAudioTrack(TrackGroupArray trackGroupArray, int[][] iArr, Parameters parameters, TrackSelection.Factory factory) {
        int i9 = -1;
        int i10 = -1;
        AudioTrackScore audioTrackScore = null;
        for (int i11 = 0; i11 < trackGroupArray.length; i11++) {
            TrackGroup trackGroup = trackGroupArray.get(i11);
            int[] iArr2 = iArr[i11];
            for (int i12 = 0; i12 < trackGroup.length; i12++) {
                if (isSupported(iArr2[i12], parameters.exceedRendererCapabilitiesIfNecessary)) {
                    AudioTrackScore audioTrackScore2 = new AudioTrackScore(trackGroup.getFormat(i12), parameters, iArr2[i12]);
                    if (audioTrackScore == null || audioTrackScore2.compareTo(audioTrackScore) > 0) {
                        i9 = i11;
                        i10 = i12;
                        audioTrackScore = audioTrackScore2;
                    }
                }
            }
        }
        if (i9 == -1) {
            return null;
        }
        TrackGroup trackGroup2 = trackGroupArray.get(i9);
        if (!parameters.forceLowestBitrate && factory != null) {
            int[] adaptiveAudioTracks = getAdaptiveAudioTracks(trackGroup2, iArr[i9], parameters.allowMixedMimeAdaptiveness);
            if (adaptiveAudioTracks.length > 0) {
                return factory.createTrackSelection(trackGroup2, adaptiveAudioTracks);
            }
        }
        return new FixedTrackSelection(trackGroup2, i10);
    }

    public TrackSelection selectOtherTrack(int i9, TrackGroupArray trackGroupArray, int[][] iArr, Parameters parameters) {
        TrackGroup trackGroup = null;
        int i10 = 0;
        int i11 = 0;
        for (int i12 = 0; i12 < trackGroupArray.length; i12++) {
            TrackGroup trackGroup2 = trackGroupArray.get(i12);
            int[] iArr2 = iArr[i12];
            for (int i13 = 0; i13 < trackGroup2.length; i13++) {
                if (isSupported(iArr2[i13], parameters.exceedRendererCapabilitiesIfNecessary)) {
                    int i14 = (trackGroup2.getFormat(i13).selectionFlags & 1) != 0 ? 2 : 1;
                    if (isSupported(iArr2[i13], false)) {
                        i14 += 1000;
                    }
                    if (i14 > i11) {
                        trackGroup = trackGroup2;
                        i10 = i13;
                        i11 = i14;
                    }
                }
            }
        }
        if (trackGroup == null) {
            return null;
        }
        return new FixedTrackSelection(trackGroup, i10);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public TrackSelection selectTextTrack(TrackGroupArray trackGroupArray, int[][] iArr, Parameters parameters) {
        int i9 = 0;
        int i10 = 0;
        TrackGroup trackGroup = null;
        for (int i11 = 0; i11 < trackGroupArray.length; i11++) {
            TrackGroup trackGroup2 = trackGroupArray.get(i11);
            int[] iArr2 = iArr[i11];
            for (int i12 = 0; i12 < trackGroup2.length; i12++) {
                if (isSupported(iArr2[i12], parameters.exceedRendererCapabilitiesIfNecessary)) {
                    Format format = trackGroup2.getFormat(i12);
                    int i13 = format.selectionFlags & (~parameters.disabledTextTrackSelectionFlags);
                    int i14 = 1;
                    Object[] objArr = (i13 & 1) != 0;
                    Object[] objArr2 = (i13 & 2) != 0;
                    boolean hasLanguage = formatHasLanguage(format, parameters.preferredTextLanguage);
                    if (hasLanguage || (parameters.selectUndeterminedTextLanguage && formatHasNoLanguage(format))) {
                        i14 = (objArr != false ? 8 : objArr2 == false ? 6 : 4) + (hasLanguage ? 1 : 0);
                    } else if (objArr == true) {
                        i14 = 3;
                    } else if (objArr2 != false) {
                        if (formatHasLanguage(format, parameters.preferredAudioLanguage)) {
                            i14 = 2;
                        }
                    }
                    if (isSupported(iArr2[i12], false)) {
                        i14 += 1000;
                    }
                    if (i14 > i10) {
                        trackGroup = trackGroup2;
                        i9 = i12;
                        i10 = i14;
                    }
                }
            }
        }
        if (trackGroup == null) {
            return null;
        }
        return new FixedTrackSelection(trackGroup, i9);
    }

    @Override // com.google.android.exoplayer2.trackselection.MappingTrackSelector
    public TrackSelection[] selectTracks(RendererCapabilities[] rendererCapabilitiesArr, TrackGroupArray[] trackGroupArrayArr, int[][][] iArr) {
        int length = rendererCapabilitiesArr.length;
        TrackSelection[] trackSelectionArr = new TrackSelection[length];
        Parameters parameters = this.paramsReference.get();
        boolean z8 = false;
        int i9 = 0;
        boolean z9 = false;
        while (true) {
            if (i9 >= length) {
                break;
            }
            if (2 == rendererCapabilitiesArr[i9].getTrackType()) {
                if (!z8) {
                    TrackSelection trackSelectionSelectVideoTrack = selectVideoTrack(rendererCapabilitiesArr[i9], trackGroupArrayArr[i9], iArr[i9], parameters, this.adaptiveTrackSelectionFactory);
                    trackSelectionArr[i9] = trackSelectionSelectVideoTrack;
                    z8 = trackSelectionSelectVideoTrack != null;
                }
                z9 |= trackGroupArrayArr[i9].length > 0;
            }
            i9++;
        }
        boolean z10 = false;
        boolean z11 = false;
        for (int i10 = 0; i10 < length; i10++) {
            int trackType = rendererCapabilitiesArr[i10].getTrackType();
            if (trackType != 1) {
                if (trackType != 2) {
                    if (trackType != 3) {
                        trackSelectionArr[i10] = selectOtherTrack(rendererCapabilitiesArr[i10].getTrackType(), trackGroupArrayArr[i10], iArr[i10], parameters);
                    } else if (!z11) {
                        TrackSelection trackSelectionSelectTextTrack = selectTextTrack(trackGroupArrayArr[i10], iArr[i10], parameters);
                        trackSelectionArr[i10] = trackSelectionSelectTextTrack;
                        z11 = trackSelectionSelectTextTrack != null;
                    }
                }
            } else if (!z10) {
                TrackSelection trackSelectionSelectAudioTrack = selectAudioTrack(trackGroupArrayArr[i10], iArr[i10], parameters, z9 ? null : this.adaptiveTrackSelectionFactory);
                trackSelectionArr[i10] = trackSelectionSelectAudioTrack;
                z10 = trackSelectionSelectAudioTrack != null;
            }
        }
        return trackSelectionArr;
    }

    public TrackSelection selectVideoTrack(RendererCapabilities rendererCapabilities, TrackGroupArray trackGroupArray, int[][] iArr, Parameters parameters, TrackSelection.Factory factory) {
        TrackSelection trackSelectionSelectAdaptiveVideoTrack = (parameters.forceLowestBitrate || factory == null) ? null : selectAdaptiveVideoTrack(rendererCapabilities, trackGroupArray, iArr, parameters, factory);
        return trackSelectionSelectAdaptiveVideoTrack == null ? selectFixedVideoTrack(trackGroupArray, iArr, parameters) : trackSelectionSelectAdaptiveVideoTrack;
    }

    public void setParameters(Parameters parameters) {
        Assertions.checkNotNull(parameters);
        if (this.paramsReference.getAndSet(parameters).equals(parameters)) {
            return;
        }
        invalidate();
    }

    public DefaultTrackSelector(BandwidthMeter bandwidthMeter) {
        this(new AdaptiveTrackSelection.Factory(bandwidthMeter));
    }

    public DefaultTrackSelector(TrackSelection.Factory factory) {
        this.adaptiveTrackSelectionFactory = factory;
        this.paramsReference = new AtomicReference<>(Parameters.DEFAULT);
    }
}
