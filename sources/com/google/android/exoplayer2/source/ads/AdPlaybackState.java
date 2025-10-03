package com.google.android.exoplayer2.source.ads;

import android.net.Uri;
import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.util.Assertions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;

/* loaded from: classes.dex */
public final class AdPlaybackState {
    public static final int AD_STATE_AVAILABLE = 1;
    public static final int AD_STATE_ERROR = 4;
    public static final int AD_STATE_PLAYED = 3;
    public static final int AD_STATE_SKIPPED = 2;
    public static final int AD_STATE_UNAVAILABLE = 0;
    public static final AdPlaybackState NONE = new AdPlaybackState(new long[0]);
    public final int adGroupCount;
    public final long[] adGroupTimesUs;
    public final AdGroup[] adGroups;
    public final long adResumePositionUs;
    public final long contentDurationUs;

    public static final class AdGroup {
        public final int count;
        public final long[] durationsUs;
        public final int[] states;
        public final Uri[] uris;

        public AdGroup() {
            this(-1, new int[0], new Uri[0], new long[0]);
        }

        private static long[] copyDurationsUsWithSpaceForAdCount(long[] jArr, int i9) {
            int length = jArr.length;
            int iMax = Math.max(i9, length);
            long[] jArrCopyOf = Arrays.copyOf(jArr, iMax);
            Arrays.fill(jArrCopyOf, length, iMax, C3322C.TIME_UNSET);
            return jArrCopyOf;
        }

        private static int[] copyStatesWithSpaceForAdCount(int[] iArr, int i9) {
            int length = iArr.length;
            int iMax = Math.max(i9, length);
            int[] iArrCopyOf = Arrays.copyOf(iArr, iMax);
            Arrays.fill(iArrCopyOf, length, iMax, 0);
            return iArrCopyOf;
        }

        public int getFirstAdIndexToPlay() {
            return getNextAdIndexToPlay(-1);
        }

        public int getNextAdIndexToPlay(int i9) {
            int i10;
            int i11 = i9 + 1;
            while (true) {
                int[] iArr = this.states;
                if (i11 >= iArr.length || (i10 = iArr[i11]) == 0 || i10 == 1) {
                    break;
                }
                i11++;
            }
            return i11;
        }

        public boolean hasUnplayedAds() {
            return this.count == -1 || getFirstAdIndexToPlay() < this.count;
        }

        public AdGroup withAdCount(int i9) {
            Assertions.checkArgument(this.count == -1 && this.states.length <= i9);
            return new AdGroup(i9, copyStatesWithSpaceForAdCount(this.states, i9), (Uri[]) Arrays.copyOf(this.uris, i9), copyDurationsUsWithSpaceForAdCount(this.durationsUs, i9));
        }

        public AdGroup withAdDurationsUs(long[] jArr) {
            Assertions.checkArgument(this.count == -1 || jArr.length <= this.uris.length);
            int length = jArr.length;
            Uri[] uriArr = this.uris;
            if (length < uriArr.length) {
                jArr = copyDurationsUsWithSpaceForAdCount(jArr, uriArr.length);
            }
            return new AdGroup(this.count, this.states, this.uris, jArr);
        }

        public AdGroup withAdState(int i9, int i10) {
            int i11 = this.count;
            Assertions.checkArgument(i11 == -1 || i10 < i11);
            int[] iArrCopyStatesWithSpaceForAdCount = copyStatesWithSpaceForAdCount(this.states, i10 + 1);
            int i12 = iArrCopyStatesWithSpaceForAdCount[i10];
            Assertions.checkArgument(i12 == 0 || i12 == 1 || i12 == i9);
            long[] jArrCopyDurationsUsWithSpaceForAdCount = this.durationsUs;
            if (jArrCopyDurationsUsWithSpaceForAdCount.length != iArrCopyStatesWithSpaceForAdCount.length) {
                jArrCopyDurationsUsWithSpaceForAdCount = copyDurationsUsWithSpaceForAdCount(jArrCopyDurationsUsWithSpaceForAdCount, iArrCopyStatesWithSpaceForAdCount.length);
            }
            Uri[] uriArr = this.uris;
            if (uriArr.length != iArrCopyStatesWithSpaceForAdCount.length) {
                uriArr = (Uri[]) Arrays.copyOf(uriArr, iArrCopyStatesWithSpaceForAdCount.length);
            }
            iArrCopyStatesWithSpaceForAdCount[i10] = i9;
            return new AdGroup(this.count, iArrCopyStatesWithSpaceForAdCount, uriArr, jArrCopyDurationsUsWithSpaceForAdCount);
        }

        public AdGroup withAdUri(Uri uri, int i9) {
            int i10 = this.count;
            Assertions.checkArgument(i10 == -1 || i9 < i10);
            int[] iArrCopyStatesWithSpaceForAdCount = copyStatesWithSpaceForAdCount(this.states, i9 + 1);
            Assertions.checkArgument(iArrCopyStatesWithSpaceForAdCount[i9] == 0);
            long[] jArrCopyDurationsUsWithSpaceForAdCount = this.durationsUs;
            if (jArrCopyDurationsUsWithSpaceForAdCount.length != iArrCopyStatesWithSpaceForAdCount.length) {
                jArrCopyDurationsUsWithSpaceForAdCount = copyDurationsUsWithSpaceForAdCount(jArrCopyDurationsUsWithSpaceForAdCount, iArrCopyStatesWithSpaceForAdCount.length);
            }
            Uri[] uriArr = (Uri[]) Arrays.copyOf(this.uris, iArrCopyStatesWithSpaceForAdCount.length);
            uriArr[i9] = uri;
            iArrCopyStatesWithSpaceForAdCount[i9] = 1;
            return new AdGroup(this.count, iArrCopyStatesWithSpaceForAdCount, uriArr, jArrCopyDurationsUsWithSpaceForAdCount);
        }

        public AdGroup withAllAdsSkipped() {
            if (this.count == -1) {
                return new AdGroup(0, new int[0], new Uri[0], new long[0]);
            }
            int[] iArr = this.states;
            int length = iArr.length;
            int[] iArrCopyOf = Arrays.copyOf(iArr, length);
            for (int i9 = 0; i9 < length; i9++) {
                int i10 = iArrCopyOf[i9];
                if (i10 == 1 || i10 == 0) {
                    iArrCopyOf[i9] = 2;
                }
            }
            return new AdGroup(length, iArrCopyOf, this.uris, this.durationsUs);
        }

        private AdGroup(int i9, int[] iArr, Uri[] uriArr, long[] jArr) {
            Assertions.checkArgument(iArr.length == uriArr.length);
            this.count = i9;
            this.states = iArr;
            this.uris = uriArr;
            this.durationsUs = jArr;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface AdState {
    }

    public AdPlaybackState(long... jArr) {
        int length = jArr.length;
        this.adGroupCount = length;
        this.adGroupTimesUs = Arrays.copyOf(jArr, length);
        this.adGroups = new AdGroup[length];
        for (int i9 = 0; i9 < length; i9++) {
            this.adGroups[i9] = new AdGroup();
        }
        this.adResumePositionUs = 0L;
        this.contentDurationUs = C3322C.TIME_UNSET;
    }

    public int getAdGroupIndexAfterPositionUs(long j9) {
        int i9 = 0;
        while (true) {
            long[] jArr = this.adGroupTimesUs;
            if (i9 >= jArr.length) {
                break;
            }
            long j10 = jArr[i9];
            if (j10 == Long.MIN_VALUE || (j9 < j10 && this.adGroups[i9].hasUnplayedAds())) {
                break;
            }
            i9++;
        }
        if (i9 < this.adGroupTimesUs.length) {
            return i9;
        }
        return -1;
    }

    public int getAdGroupIndexForPositionUs(long j9) {
        int length = this.adGroupTimesUs.length - 1;
        while (length >= 0) {
            long j10 = this.adGroupTimesUs[length];
            if (j10 != Long.MIN_VALUE && j10 <= j9) {
                break;
            }
            length--;
        }
        if (length < 0 || !this.adGroups[length].hasUnplayedAds()) {
            return -1;
        }
        return length;
    }

    public AdPlaybackState withAdCount(int i9, int i10) {
        Assertions.checkArgument(i10 > 0);
        AdGroup[] adGroupArr = this.adGroups;
        if (adGroupArr[i9].count == i10) {
            return this;
        }
        AdGroup[] adGroupArr2 = (AdGroup[]) Arrays.copyOf(adGroupArr, adGroupArr.length);
        adGroupArr2[i9] = this.adGroups[i9].withAdCount(i10);
        return new AdPlaybackState(this.adGroupTimesUs, adGroupArr2, this.adResumePositionUs, this.contentDurationUs);
    }

    public AdPlaybackState withAdDurationsUs(long[][] jArr) {
        AdGroup[] adGroupArr = this.adGroups;
        AdGroup[] adGroupArr2 = (AdGroup[]) Arrays.copyOf(adGroupArr, adGroupArr.length);
        for (int i9 = 0; i9 < this.adGroupCount; i9++) {
            adGroupArr2[i9] = adGroupArr2[i9].withAdDurationsUs(jArr[i9]);
        }
        return new AdPlaybackState(this.adGroupTimesUs, adGroupArr2, this.adResumePositionUs, this.contentDurationUs);
    }

    public AdPlaybackState withAdLoadError(int i9, int i10) {
        AdGroup[] adGroupArr = this.adGroups;
        AdGroup[] adGroupArr2 = (AdGroup[]) Arrays.copyOf(adGroupArr, adGroupArr.length);
        adGroupArr2[i9] = adGroupArr2[i9].withAdState(4, i10);
        return new AdPlaybackState(this.adGroupTimesUs, adGroupArr2, this.adResumePositionUs, this.contentDurationUs);
    }

    public AdPlaybackState withAdResumePositionUs(long j9) {
        return this.adResumePositionUs == j9 ? this : new AdPlaybackState(this.adGroupTimesUs, this.adGroups, j9, this.contentDurationUs);
    }

    public AdPlaybackState withAdUri(int i9, int i10, Uri uri) {
        AdGroup[] adGroupArr = this.adGroups;
        AdGroup[] adGroupArr2 = (AdGroup[]) Arrays.copyOf(adGroupArr, adGroupArr.length);
        adGroupArr2[i9] = adGroupArr2[i9].withAdUri(uri, i10);
        return new AdPlaybackState(this.adGroupTimesUs, adGroupArr2, this.adResumePositionUs, this.contentDurationUs);
    }

    public AdPlaybackState withContentDurationUs(long j9) {
        return this.contentDurationUs == j9 ? this : new AdPlaybackState(this.adGroupTimesUs, this.adGroups, this.adResumePositionUs, j9);
    }

    public AdPlaybackState withPlayedAd(int i9, int i10) {
        AdGroup[] adGroupArr = this.adGroups;
        AdGroup[] adGroupArr2 = (AdGroup[]) Arrays.copyOf(adGroupArr, adGroupArr.length);
        adGroupArr2[i9] = adGroupArr2[i9].withAdState(3, i10);
        return new AdPlaybackState(this.adGroupTimesUs, adGroupArr2, this.adResumePositionUs, this.contentDurationUs);
    }

    public AdPlaybackState withSkippedAdGroup(int i9) {
        AdGroup[] adGroupArr = this.adGroups;
        AdGroup[] adGroupArr2 = (AdGroup[]) Arrays.copyOf(adGroupArr, adGroupArr.length);
        adGroupArr2[i9] = adGroupArr2[i9].withAllAdsSkipped();
        return new AdPlaybackState(this.adGroupTimesUs, adGroupArr2, this.adResumePositionUs, this.contentDurationUs);
    }

    private AdPlaybackState(long[] jArr, AdGroup[] adGroupArr, long j9, long j10) {
        this.adGroupCount = adGroupArr.length;
        this.adGroupTimesUs = jArr;
        this.adGroups = adGroupArr;
        this.adResumePositionUs = j9;
        this.contentDurationUs = j10;
    }
}
