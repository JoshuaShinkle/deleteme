package com.google.android.gms.common.server.response;

import com.google.android.gms.common.server.response.FastParser;
import java.io.BufferedReader;
import java.math.BigDecimal;

/* loaded from: classes2.dex */
final class zah implements FastParser.zaa<BigDecimal> {
    @Override // com.google.android.gms.common.server.response.FastParser.zaa
    public final /* synthetic */ BigDecimal zaa(FastParser fastParser, BufferedReader bufferedReader) {
        return fastParser.zai(bufferedReader);
    }
}
