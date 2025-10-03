package com.google.android.gms.common.server.response;

import com.google.android.gms.common.server.response.FastParser;
import java.io.BufferedReader;

/* loaded from: classes2.dex */
final class zab implements FastParser.zaa<Float> {
    @Override // com.google.android.gms.common.server.response.FastParser.zaa
    public final /* synthetic */ Float zaa(FastParser fastParser, BufferedReader bufferedReader) {
        return Float.valueOf(fastParser.zag(bufferedReader));
    }
}
