package com.google.zxing.client.android.result.supplement;

import android.content.Context;
import android.widget.TextView;
import com.google.android.gms.plus.PlusShare;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.zxing.client.android.C4453R;
import com.google.zxing.client.android.HttpHelper;
import com.google.zxing.client.android.LocaleManager;
import com.google.zxing.client.android.history.HistoryManager;
import java.io.IOException;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/* loaded from: classes2.dex */
final class BookResultInfoRetriever extends SupplementalInfoRetriever {
    private final Context context;
    private final String isbn;
    private final String source;

    public BookResultInfoRetriever(TextView textView, String str, HistoryManager historyManager, Context context) {
        super(textView, historyManager);
        this.isbn = str;
        this.source = context.getString(C4453R.string.msg_google_books);
        this.context = context;
    }

    @Override // com.google.zxing.client.android.result.supplement.SupplementalInfoRetriever
    public void retrieveSupplementalInfo() throws IOException {
        JSONObject jSONObject;
        ArrayList arrayList;
        CharSequence charSequenceDownloadViaHttp = HttpHelper.downloadViaHttp("https://www.googleapis.com/books/v1/volumes?q=isbn:" + this.isbn, HttpHelper.ContentType.JSON);
        if (charSequenceDownloadViaHttp.length() == 0) {
            return;
        }
        try {
            JSONArray jSONArrayOptJSONArray = ((JSONObject) new JSONTokener(charSequenceDownloadViaHttp.toString()).nextValue()).optJSONArray(FirebaseAnalytics.Param.ITEMS);
            if (jSONArrayOptJSONArray != null) {
                if (jSONArrayOptJSONArray.isNull(0) || (jSONObject = ((JSONObject) jSONArrayOptJSONArray.get(0)).getJSONObject("volumeInfo")) == null) {
                    return;
                }
                String strOptString = jSONObject.optString(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE);
                String strOptString2 = jSONObject.optString("pageCount");
                JSONArray jSONArrayOptJSONArray2 = jSONObject.optJSONArray("authors");
                String str = null;
                if (jSONArrayOptJSONArray2 == null || jSONArrayOptJSONArray2.isNull(0)) {
                    arrayList = null;
                } else {
                    arrayList = new ArrayList(jSONArrayOptJSONArray2.length());
                    for (int i9 = 0; i9 < jSONArrayOptJSONArray2.length(); i9++) {
                        arrayList.add(jSONArrayOptJSONArray2.getString(i9));
                    }
                }
                ArrayList arrayList2 = new ArrayList();
                SupplementalInfoRetriever.maybeAddText(strOptString, arrayList2);
                SupplementalInfoRetriever.maybeAddTextSeries(arrayList, arrayList2);
                if (strOptString2 != null && !strOptString2.isEmpty()) {
                    str = strOptString2 + "pp.";
                }
                SupplementalInfoRetriever.maybeAddText(str, arrayList2);
                String str2 = "http://www.google." + LocaleManager.getBookSearchCountryTLD(this.context) + "/search?tbm=bks&source=zxing&q=";
                append(this.isbn, this.source, (String[]) arrayList2.toArray(new String[arrayList2.size()]), str2 + this.isbn);
            }
        } catch (JSONException e9) {
            throw new IOException(e9);
        }
    }
}
