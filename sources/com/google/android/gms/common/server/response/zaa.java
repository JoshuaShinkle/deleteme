package com.google.android.gms.common.server.response;

import com.google.android.gms.common.server.response.FastParser;
import java.io.BufferedReader;

/* loaded from: classes2.dex */
final class zaa implements FastParser.zaa<Integer> {
    @Override // com.google.android.gms.common.server.response.FastParser.zaa
    public final /* synthetic */ Integer zaa(FastParser fastParser, BufferedReader bufferedReader) {
        return Integer.valueOf(fastParser.zad(bufferedReader));
    }
}
