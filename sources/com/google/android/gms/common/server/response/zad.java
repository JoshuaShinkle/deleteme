package com.google.android.gms.common.server.response;

import com.google.android.gms.common.server.response.FastParser;
import java.io.BufferedReader;

/* loaded from: classes2.dex */
final class zad implements FastParser.zaa<Boolean> {
    @Override // com.google.android.gms.common.server.response.FastParser.zaa
    public final /* synthetic */ Boolean zaa(FastParser fastParser, BufferedReader bufferedReader) {
        return Boolean.valueOf(fastParser.zaa(bufferedReader, false));
    }
}
