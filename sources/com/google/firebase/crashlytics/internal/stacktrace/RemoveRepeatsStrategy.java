package com.google.firebase.crashlytics.internal.stacktrace;

import java.util.HashMap;

/* loaded from: classes2.dex */
public class RemoveRepeatsStrategy implements StackTraceTrimmingStrategy {
    private final int maxRepetitions;

    public RemoveRepeatsStrategy() {
        this(1);
    }

    private static boolean isRepeatingSequence(StackTraceElement[] stackTraceElementArr, int i9, int i10) {
        int i11 = i10 - i9;
        if (i10 + i11 > stackTraceElementArr.length) {
            return false;
        }
        for (int i12 = 0; i12 < i11; i12++) {
            if (!stackTraceElementArr[i9 + i12].equals(stackTraceElementArr[i10 + i12])) {
                return false;
            }
        }
        return true;
    }

    private static StackTraceElement[] trimRepeats(StackTraceElement[] stackTraceElementArr, int i9) {
        int i10;
        HashMap map = new HashMap();
        StackTraceElement[] stackTraceElementArr2 = new StackTraceElement[stackTraceElementArr.length];
        int i11 = 0;
        int i12 = 0;
        int i13 = 1;
        while (i11 < stackTraceElementArr.length) {
            StackTraceElement stackTraceElement = stackTraceElementArr[i11];
            Integer num = (Integer) map.get(stackTraceElement);
            if (num == null || !isRepeatingSequence(stackTraceElementArr, num.intValue(), i11)) {
                stackTraceElementArr2[i12] = stackTraceElementArr[i11];
                i12++;
                i13 = 1;
                i10 = i11;
            } else {
                int iIntValue = i11 - num.intValue();
                if (i13 < i9) {
                    System.arraycopy(stackTraceElementArr, i11, stackTraceElementArr2, i12, iIntValue);
                    i12 += iIntValue;
                    i13++;
                }
                i10 = (iIntValue - 1) + i11;
            }
            map.put(stackTraceElement, Integer.valueOf(i11));
            i11 = i10 + 1;
        }
        StackTraceElement[] stackTraceElementArr3 = new StackTraceElement[i12];
        System.arraycopy(stackTraceElementArr2, 0, stackTraceElementArr3, 0, i12);
        return stackTraceElementArr3;
    }

    @Override // com.google.firebase.crashlytics.internal.stacktrace.StackTraceTrimmingStrategy
    public StackTraceElement[] getTrimmedStackTrace(StackTraceElement[] stackTraceElementArr) {
        StackTraceElement[] stackTraceElementArrTrimRepeats = trimRepeats(stackTraceElementArr, this.maxRepetitions);
        return stackTraceElementArrTrimRepeats.length < stackTraceElementArr.length ? stackTraceElementArrTrimRepeats : stackTraceElementArr;
    }

    public RemoveRepeatsStrategy(int i9) {
        this.maxRepetitions = i9;
    }
}
