package com.google.android.gms.common.server.response;

import com.google.android.gms.common.server.response.FastParser;
import java.io.BufferedReader;

/* loaded from: classes2.dex */
final class zae implements FastParser.zaa<Double> {
    @Override // com.google.android.gms.common.server.response.FastParser.zaa
    public final /* synthetic */ Double zaa(FastParser fastParser, BufferedReader bufferedReader) {
        return Double.valueOf(fastParser.zah(bufferedReader));
    }
}
