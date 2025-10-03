package com.google.common.p039io;

import com.google.common.annotations.GwtIncompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.p159io.IOUtils;

@GwtIncompatible
/* loaded from: classes2.dex */
abstract class LineBuffer {
    private StringBuilder line = new StringBuilder();
    private boolean sawReturn;

    @CanIgnoreReturnValue
    private boolean finishLine(boolean z8) {
        handleLine(this.line.toString(), this.sawReturn ? z8 ? IOUtils.LINE_SEPARATOR_WINDOWS : StringUtils.f19107CR : z8 ? "\n" : "");
        this.line = new StringBuilder();
        this.sawReturn = false;
        return z8;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x001a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void add(char[] cArr, int i9, int i10) {
        int i11;
        if (!this.sawReturn || i10 <= 0) {
            i11 = i9;
        } else {
            if (finishLine(cArr[i9] == '\n')) {
                i11 = i9 + 1;
            }
        }
        int i12 = i9 + i10;
        int i13 = i11;
        while (i11 < i12) {
            char c9 = cArr[i11];
            if (c9 == '\n') {
                this.line.append(cArr, i13, i11 - i13);
                finishLine(true);
            } else if (c9 != '\r') {
                i11++;
            } else {
                this.line.append(cArr, i13, i11 - i13);
                this.sawReturn = true;
                int i14 = i11 + 1;
                if (i14 < i12) {
                    if (finishLine(cArr[i14] == '\n')) {
                        i11 = i14;
                    }
                }
            }
            i13 = i11 + 1;
            i11++;
        }
        this.line.append(cArr, i13, i12 - i13);
    }

    public void finish() {
        if (this.sawReturn || this.line.length() > 0) {
            finishLine(false);
        }
    }

    public abstract void handleLine(String str, String str2);
}
