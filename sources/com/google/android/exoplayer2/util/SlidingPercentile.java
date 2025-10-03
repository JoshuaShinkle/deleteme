package com.google.android.exoplayer2.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/* loaded from: classes.dex */
public class SlidingPercentile {
    private static final int MAX_RECYCLED_SAMPLES = 5;
    private static final int SORT_ORDER_BY_INDEX = 1;
    private static final int SORT_ORDER_BY_VALUE = 0;
    private static final int SORT_ORDER_NONE = -1;
    private final int maxWeight;
    private int nextSampleIndex;
    private int recycledSampleCount;
    private int totalWeight;
    private static final Comparator<Sample> INDEX_COMPARATOR = new Comparator<Sample>() { // from class: com.google.android.exoplayer2.util.SlidingPercentile.1
        @Override // java.util.Comparator
        public int compare(Sample sample, Sample sample2) {
            return sample.index - sample2.index;
        }
    };
    private static final Comparator<Sample> VALUE_COMPARATOR = new Comparator<Sample>() { // from class: com.google.android.exoplayer2.util.SlidingPercentile.2
        @Override // java.util.Comparator
        public int compare(Sample sample, Sample sample2) {
            float f9 = sample.value;
            float f10 = sample2.value;
            if (f9 < f10) {
                return -1;
            }
            return f10 < f9 ? 1 : 0;
        }
    };
    private final Sample[] recycledSamples = new Sample[5];
    private final ArrayList<Sample> samples = new ArrayList<>();
    private int currentSortOrder = -1;

    public static class Sample {
        public int index;
        public float value;
        public int weight;

        private Sample() {
        }
    }

    public SlidingPercentile(int i9) {
        this.maxWeight = i9;
    }

    private void ensureSortedByIndex() {
        if (this.currentSortOrder != 1) {
            Collections.sort(this.samples, INDEX_COMPARATOR);
            this.currentSortOrder = 1;
        }
    }

    private void ensureSortedByValue() {
        if (this.currentSortOrder != 0) {
            Collections.sort(this.samples, VALUE_COMPARATOR);
            this.currentSortOrder = 0;
        }
    }

    public void addSample(int i9, float f9) {
        Sample sample;
        ensureSortedByIndex();
        int i10 = this.recycledSampleCount;
        if (i10 > 0) {
            Sample[] sampleArr = this.recycledSamples;
            int i11 = i10 - 1;
            this.recycledSampleCount = i11;
            sample = sampleArr[i11];
        } else {
            sample = new Sample();
        }
        int i12 = this.nextSampleIndex;
        this.nextSampleIndex = i12 + 1;
        sample.index = i12;
        sample.weight = i9;
        sample.value = f9;
        this.samples.add(sample);
        this.totalWeight += i9;
        while (true) {
            int i13 = this.totalWeight;
            int i14 = this.maxWeight;
            if (i13 <= i14) {
                return;
            }
            int i15 = i13 - i14;
            Sample sample2 = this.samples.get(0);
            int i16 = sample2.weight;
            if (i16 <= i15) {
                this.totalWeight -= i16;
                this.samples.remove(0);
                int i17 = this.recycledSampleCount;
                if (i17 < 5) {
                    Sample[] sampleArr2 = this.recycledSamples;
                    this.recycledSampleCount = i17 + 1;
                    sampleArr2[i17] = sample2;
                }
            } else {
                sample2.weight = i16 - i15;
                this.totalWeight -= i15;
            }
        }
    }

    public float getPercentile(float f9) {
        ensureSortedByValue();
        float f10 = f9 * this.totalWeight;
        int i9 = 0;
        for (int i10 = 0; i10 < this.samples.size(); i10++) {
            Sample sample = this.samples.get(i10);
            i9 += sample.weight;
            if (i9 >= f10) {
                return sample.value;
            }
        }
        if (this.samples.isEmpty()) {
            return Float.NaN;
        }
        return this.samples.get(r5.size() - 1).value;
    }
}
