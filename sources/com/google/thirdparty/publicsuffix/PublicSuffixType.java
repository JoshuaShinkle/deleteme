package com.google.thirdparty.publicsuffix;

import com.google.common.annotations.GwtCompatible;

/* JADX INFO: Access modifiers changed from: package-private */
@GwtCompatible
/* loaded from: classes2.dex */
public enum PublicSuffixType {
    PRIVATE(':', ','),
    ICANN('!', '?');

    private final char innerNodeCode;
    private final char leafNodeCode;

    PublicSuffixType(char c9, char c10) {
        this.innerNodeCode = c9;
        this.leafNodeCode = c10;
    }

    public static PublicSuffixType fromCode(char c9) {
        for (PublicSuffixType publicSuffixType : values()) {
            if (publicSuffixType.getInnerNodeCode() == c9 || publicSuffixType.getLeafNodeCode() == c9) {
                return publicSuffixType;
            }
        }
        throw new IllegalArgumentException("No enum corresponding to given code: " + c9);
    }

    public static PublicSuffixType fromIsPrivate(boolean z8) {
        return z8 ? PRIVATE : ICANN;
    }

    public char getInnerNodeCode() {
        return this.innerNodeCode;
    }

    public char getLeafNodeCode() {
        return this.leafNodeCode;
    }
}
