package com.google.thirdparty.publicsuffix;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import java.util.List;

@GwtCompatible
/* loaded from: classes2.dex */
final class TrieParser {
    private static final Joiner PREFIX_JOINER = Joiner.m17550on("");

    private static int doParseTrieToBuilder(List<CharSequence> list, CharSequence charSequence, ImmutableMap.Builder<String, PublicSuffixType> builder) {
        int length = charSequence.length();
        int i9 = 0;
        char cCharAt = 0;
        while (i9 < length && (cCharAt = charSequence.charAt(i9)) != '&' && cCharAt != '?' && cCharAt != '!' && cCharAt != ':' && cCharAt != ',') {
            i9++;
        }
        list.add(0, reverse(charSequence.subSequence(0, i9)));
        if (cCharAt == '!' || cCharAt == '?' || cCharAt == ':' || cCharAt == ',') {
            String strJoin = PREFIX_JOINER.join(list);
            if (strJoin.length() > 0) {
                builder.put(strJoin, PublicSuffixType.fromCode(cCharAt));
            }
        }
        int iDoParseTrieToBuilder = i9 + 1;
        if (cCharAt != '?' && cCharAt != ',') {
            while (iDoParseTrieToBuilder < length) {
                iDoParseTrieToBuilder += doParseTrieToBuilder(list, charSequence.subSequence(iDoParseTrieToBuilder, length), builder);
                if (charSequence.charAt(iDoParseTrieToBuilder) == '?' || charSequence.charAt(iDoParseTrieToBuilder) == ',') {
                    iDoParseTrieToBuilder++;
                    break;
                }
            }
        }
        list.remove(0);
        return iDoParseTrieToBuilder;
    }

    public static ImmutableMap<String, PublicSuffixType> parseTrie(CharSequence charSequence) {
        ImmutableMap.Builder builder = ImmutableMap.builder();
        int length = charSequence.length();
        int iDoParseTrieToBuilder = 0;
        while (iDoParseTrieToBuilder < length) {
            iDoParseTrieToBuilder += doParseTrieToBuilder(Lists.newLinkedList(), charSequence.subSequence(iDoParseTrieToBuilder, length), builder);
        }
        return builder.build();
    }

    private static CharSequence reverse(CharSequence charSequence) {
        return new StringBuilder(charSequence).reverse();
    }
}
