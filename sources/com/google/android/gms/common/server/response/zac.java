package com.google.android.gms.common.server.response;

import com.google.android.gms.common.server.response.FastParser;
import java.io.BufferedReader;

/* loaded from: classes2.dex */
final class zac implements FastParser.zaa<Long> {
    @Override // com.google.android.gms.common.server.response.FastParser.zaa
    public final /* synthetic */ Long zaa(FastParser fastParser, BufferedReader bufferedReader) {
        return Long.valueOf(fastParser.zae(bufferedReader));
    }
}
