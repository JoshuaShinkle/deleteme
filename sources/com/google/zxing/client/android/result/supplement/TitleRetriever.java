package com.google.zxing.client.android.result.supplement;

import android.text.Html;
import android.widget.TextView;
import com.google.zxing.client.android.HttpHelper;
import com.google.zxing.client.android.history.HistoryManager;
import com.google.zxing.client.result.URIParsedResult;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes2.dex */
final class TitleRetriever extends SupplementalInfoRetriever {
    private static final int MAX_TITLE_LEN = 100;
    private static final Pattern TITLE_PATTERN = Pattern.compile("<title>([^<]+)");
    private final String httpUrl;

    public TitleRetriever(TextView textView, URIParsedResult uRIParsedResult, HistoryManager historyManager) {
        super(textView, historyManager);
        this.httpUrl = uRIParsedResult.getURI();
    }

    @Override // com.google.zxing.client.android.result.supplement.SupplementalInfoRetriever
    public void retrieveSupplementalInfo() {
        String strGroup;
        try {
            CharSequence charSequenceDownloadViaHttp = HttpHelper.downloadViaHttp(this.httpUrl, HttpHelper.ContentType.HTML, 4096);
            if (charSequenceDownloadViaHttp == null || charSequenceDownloadViaHttp.length() <= 0) {
                return;
            }
            Matcher matcher = TITLE_PATTERN.matcher(charSequenceDownloadViaHttp);
            if (!matcher.find() || (strGroup = matcher.group(1)) == null || strGroup.isEmpty()) {
                return;
            }
            String string = Html.fromHtml(strGroup).toString();
            if (string.length() > 100) {
                string = string.substring(0, 100) + "...";
            }
            String str = this.httpUrl;
            append(str, null, new String[]{string}, str);
        } catch (IOException unused) {
        }
    }
}
