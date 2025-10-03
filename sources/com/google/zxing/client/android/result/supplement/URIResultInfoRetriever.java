package com.google.zxing.client.android.result.supplement;

import android.content.Context;
import android.widget.TextView;
import com.google.zxing.client.android.C4453R;
import com.google.zxing.client.android.HttpHelper;
import com.google.zxing.client.android.history.HistoryManager;
import com.google.zxing.client.result.URIParsedResult;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/* loaded from: classes2.dex */
final class URIResultInfoRetriever extends SupplementalInfoRetriever {
    private static final int MAX_REDIRECTS = 5;
    private final String redirectString;
    private final URIParsedResult result;

    public URIResultInfoRetriever(TextView textView, URIParsedResult uRIParsedResult, HistoryManager historyManager, Context context) {
        super(textView, historyManager);
        this.redirectString = context.getString(C4453R.string.msg_redirect);
        this.result = uRIParsedResult;
    }

    @Override // com.google.zxing.client.android.result.supplement.SupplementalInfoRetriever
    public void retrieveSupplementalInfo() throws IOException {
        try {
            URI uri = new URI(this.result.getURI());
            URI uriUnredirect = HttpHelper.unredirect(uri);
            int i9 = 0;
            while (true) {
                int i10 = i9 + 1;
                if (i9 >= 5 || uri.equals(uriUnredirect)) {
                    return;
                }
                append(this.result.getDisplayResult(), null, new String[]{this.redirectString + " : " + uriUnredirect}, uriUnredirect.toString());
                i9 = i10;
                URI uri2 = uriUnredirect;
                uriUnredirect = HttpHelper.unredirect(uriUnredirect);
                uri = uri2;
            }
        } catch (URISyntaxException unused) {
        }
    }
}
