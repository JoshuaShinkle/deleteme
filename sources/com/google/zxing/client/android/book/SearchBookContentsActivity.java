package com.google.zxing.client.android.book;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.google.zxing.client.android.C4453R;
import com.google.zxing.client.android.HttpHelper;
import com.google.zxing.client.android.Intents;
import com.google.zxing.client.android.LocaleManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class SearchBookContentsActivity extends Activity {
    private static final String TAG = "SearchBookContentsActivity";
    private TextView headerView;
    private String isbn;
    private AsyncTask<String, ?, ?> networkTask;
    private View queryButton;
    private EditText queryTextView;
    private ListView resultListView;
    private static final Pattern TAG_PATTERN = Pattern.compile("\\<.*?\\>");
    private static final Pattern LT_ENTITY_PATTERN = Pattern.compile("&lt;");
    private static final Pattern GT_ENTITY_PATTERN = Pattern.compile("&gt;");
    private static final Pattern QUOTE_ENTITY_PATTERN = Pattern.compile("&#39;");
    private static final Pattern QUOT_ENTITY_PATTERN = Pattern.compile("&quot;");
    private final View.OnClickListener buttonListener = new View.OnClickListener() { // from class: com.google.zxing.client.android.book.SearchBookContentsActivity.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SearchBookContentsActivity.this.launchSearch();
        }
    };
    private final View.OnKeyListener keyListener = new View.OnKeyListener() { // from class: com.google.zxing.client.android.book.SearchBookContentsActivity.2
        @Override // android.view.View.OnKeyListener
        public boolean onKey(View view, int i9, KeyEvent keyEvent) {
            if (i9 != 66 || keyEvent.getAction() != 0) {
                return false;
            }
            SearchBookContentsActivity.this.launchSearch();
            return true;
        }
    };

    public final class NetworkTask extends AsyncTask<String, Object, JSONObject> {
        private NetworkTask() {
        }

        private void handleSearchResults(JSONObject jSONObject) throws JSONException {
            try {
                int i9 = jSONObject.getInt("number_of_results");
                SearchBookContentsActivity.this.headerView.setText(SearchBookContentsActivity.this.getString(C4453R.string.msg_sbc_results) + " : " + i9);
                if (i9 <= 0) {
                    if ("false".equals(jSONObject.optString("searchable"))) {
                        SearchBookContentsActivity.this.headerView.setText(C4453R.string.msg_sbc_book_not_searchable);
                    }
                    SearchBookContentsActivity.this.resultListView.setAdapter((ListAdapter) null);
                    return;
                }
                JSONArray jSONArray = jSONObject.getJSONArray("search_results");
                SearchBookContentsResult.setQuery(SearchBookContentsActivity.this.queryTextView.getText().toString());
                ArrayList arrayList = new ArrayList(i9);
                for (int i10 = 0; i10 < i9; i10++) {
                    arrayList.add(parseResult(jSONArray.getJSONObject(i10)));
                }
                SearchBookContentsActivity.this.resultListView.setOnItemClickListener(new BrowseBookListener(SearchBookContentsActivity.this, arrayList));
                SearchBookContentsActivity.this.resultListView.setAdapter((ListAdapter) new SearchBookContentsAdapter(SearchBookContentsActivity.this, arrayList));
            } catch (JSONException e9) {
                Log.w(SearchBookContentsActivity.TAG, "Bad JSON from book search", e9);
                SearchBookContentsActivity.this.resultListView.setAdapter((ListAdapter) null);
                SearchBookContentsActivity.this.headerView.setText(C4453R.string.msg_sbc_failed);
            }
        }

        private SearchBookContentsResult parseResult(JSONObject jSONObject) throws JSONException {
            String str;
            String strReplaceAll;
            boolean z8 = false;
            try {
                String string = jSONObject.getString("page_id");
                String strOptString = jSONObject.optString("page_number");
                String strOptString2 = jSONObject.optString("snippet_text");
                if (strOptString == null || strOptString.isEmpty()) {
                    str = "";
                } else {
                    str = SearchBookContentsActivity.this.getString(C4453R.string.msg_sbc_page) + ' ' + strOptString;
                }
                if (strOptString2 != null && !strOptString2.isEmpty()) {
                    z8 = true;
                }
                if (z8) {
                    strReplaceAll = SearchBookContentsActivity.QUOT_ENTITY_PATTERN.matcher(SearchBookContentsActivity.QUOTE_ENTITY_PATTERN.matcher(SearchBookContentsActivity.GT_ENTITY_PATTERN.matcher(SearchBookContentsActivity.LT_ENTITY_PATTERN.matcher(SearchBookContentsActivity.TAG_PATTERN.matcher(strOptString2).replaceAll("")).replaceAll("<")).replaceAll(">")).replaceAll("'")).replaceAll("\"");
                } else {
                    strReplaceAll = '(' + SearchBookContentsActivity.this.getString(C4453R.string.msg_sbc_snippet_unavailable) + ')';
                }
                return new SearchBookContentsResult(string, str, strReplaceAll, z8);
            } catch (JSONException e9) {
                Log.w(SearchBookContentsActivity.TAG, e9);
                return new SearchBookContentsResult(SearchBookContentsActivity.this.getString(C4453R.string.msg_sbc_no_page_returned), "", "", false);
            }
        }

        @Override // android.os.AsyncTask
        public JSONObject doInBackground(String... strArr) {
            String str;
            try {
                String str2 = strArr[0];
                String str3 = strArr[1];
                if (LocaleManager.isBookSearchUrl(str3)) {
                    str = "http://www.google.com/books?id=" + str3.substring(str3.indexOf(61) + 1) + "&jscmd=SearchWithinVolume2&q=" + str2;
                } else {
                    str = "http://www.google.com/books?vid=isbn" + str3 + "&jscmd=SearchWithinVolume2&q=" + str2;
                }
                return new JSONObject(HttpHelper.downloadViaHttp(str, HttpHelper.ContentType.JSON).toString());
            } catch (IOException e9) {
                Log.w(SearchBookContentsActivity.TAG, "Error accessing book search", e9);
                return null;
            } catch (JSONException e10) {
                Log.w(SearchBookContentsActivity.TAG, "Error accessing book search", e10);
                return null;
            }
        }

        @Override // android.os.AsyncTask
        public void onPostExecute(JSONObject jSONObject) throws JSONException {
            if (jSONObject == null) {
                SearchBookContentsActivity.this.headerView.setText(C4453R.string.msg_sbc_failed);
            } else {
                handleSearchResults(jSONObject);
            }
            SearchBookContentsActivity.this.queryTextView.setEnabled(true);
            SearchBookContentsActivity.this.queryTextView.selectAll();
            SearchBookContentsActivity.this.queryButton.setEnabled(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void launchSearch() {
        String string = this.queryTextView.getText().toString();
        if (string == null || string.isEmpty()) {
            return;
        }
        AsyncTask<String, ?, ?> asyncTask = this.networkTask;
        if (asyncTask != null) {
            asyncTask.cancel(true);
        }
        NetworkTask networkTask = new NetworkTask();
        this.networkTask = networkTask;
        networkTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, string, this.isbn);
        this.headerView.setText(C4453R.string.msg_sbc_searching_book);
        this.resultListView.setAdapter((ListAdapter) null);
        this.queryTextView.setEnabled(false);
        this.queryButton.setEnabled(false);
    }

    public String getISBN() {
        return this.isbn;
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        CookieSyncManager.createInstance(this);
        CookieManager.getInstance().removeExpiredCookie();
        Intent intent = getIntent();
        if (intent == null || !intent.getAction().equals(Intents.SearchBookContents.ACTION)) {
            finish();
            return;
        }
        String stringExtra = intent.getStringExtra(Intents.SearchBookContents.ISBN);
        this.isbn = stringExtra;
        if (LocaleManager.isBookSearchUrl(stringExtra)) {
            setTitle(getString(C4453R.string.sbc_name));
        } else {
            setTitle(getString(C4453R.string.sbc_name) + ": ISBN " + this.isbn);
        }
        setContentView(C4453R.layout.search_book_contents);
        this.queryTextView = (EditText) findViewById(C4453R.id.query_text_view);
        String stringExtra2 = intent.getStringExtra(Intents.SearchBookContents.QUERY);
        if (stringExtra2 != null && !stringExtra2.isEmpty()) {
            this.queryTextView.setText(stringExtra2);
        }
        this.queryTextView.setOnKeyListener(this.keyListener);
        View viewFindViewById = findViewById(C4453R.id.query_button);
        this.queryButton = viewFindViewById;
        viewFindViewById.setOnClickListener(this.buttonListener);
        this.resultListView = (ListView) findViewById(C4453R.id.result_list_view);
        TextView textView = (TextView) LayoutInflater.from(this).inflate(C4453R.layout.search_book_contents_header, (ViewGroup) this.resultListView, false);
        this.headerView = textView;
        this.resultListView.addHeaderView(textView);
    }

    @Override // android.app.Activity
    public void onPause() {
        AsyncTask<String, ?, ?> asyncTask = this.networkTask;
        if (asyncTask != null) {
            asyncTask.cancel(true);
            this.networkTask = null;
        }
        super.onPause();
    }

    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        this.queryTextView.selectAll();
    }
}
