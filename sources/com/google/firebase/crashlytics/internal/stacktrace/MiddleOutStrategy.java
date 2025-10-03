package com.google.firebase.crashlytics.internal.stacktrace;

/* loaded from: classes2.dex */
public class MiddleOutStrategy implements StackTraceTrimmingStrategy {
    private final int trimmedSize;

    public MiddleOutStrategy(int i9) {
        this.trimmedSize = i9;
    }

    @Override // com.google.firebase.crashlytics.internal.stacktrace.StackTraceTrimmingStrategy
    public StackTraceElement[] getTrimmedStackTrace(StackTraceElement[] stackTraceElementArr) {
        int length = stackTraceElementArr.length;
        int i9 = this.trimmedSize;
        if (length <= i9) {
            return stackTraceElementArr;
        }
        int i10 = i9 / 2;
        int i11 = i9 - i10;
        StackTraceElement[] stackTraceElementArr2 = new StackTraceElement[i9];
        System.arraycopy(stackTraceElementArr, 0, stackTraceElementArr2, 0, i11);
        System.arraycopy(stackTraceElementArr, stackTraceElementArr.length - i10, stackTraceElementArr2, i11, i10);
        return stackTraceElementArr2;
    }
}
