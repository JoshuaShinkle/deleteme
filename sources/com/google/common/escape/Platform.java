package com.google.common.escape;

import com.google.common.annotations.GwtCompatible;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;

@GwtCompatible(emulated = true)
/* loaded from: classes2.dex */
final class Platform {
    private static final ThreadLocal<char[]> DEST_TL = new ThreadLocal<char[]>() { // from class: com.google.common.escape.Platform.1
        @Override // java.lang.ThreadLocal
        public char[] initialValue() {
            return new char[UserMetadata.MAX_ATTRIBUTE_SIZE];
        }
    };

    private Platform() {
    }

    public static char[] charBufferFromThreadLocal() {
        return DEST_TL.get();
    }
}
