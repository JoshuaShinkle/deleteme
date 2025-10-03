package com.google.zxing.client.android.book;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;
import com.google.zxing.client.android.LocaleManager;
import java.util.List;

/* loaded from: classes2.dex */
final class BrowseBookListener implements AdapterView.OnItemClickListener {
    private final SearchBookContentsActivity activity;
    private final List<SearchBookContentsResult> items;

    public BrowseBookListener(SearchBookContentsActivity searchBookContentsActivity, List<SearchBookContentsResult> list) {
        this.activity = searchBookContentsActivity;
        this.items = list;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i9, long j9) {
        int i10;
        if (i9 >= 1 && (i10 = i9 - 1) < this.items.size()) {
            String pageId = this.items.get(i10).getPageId();
            String query = SearchBookContentsResult.getQuery();
            if (!LocaleManager.isBookSearchUrl(this.activity.getISBN()) || pageId.isEmpty()) {
                return;
            }
            String isbn = this.activity.getISBN();
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("http://books.google." + LocaleManager.getBookSearchCountryTLD(this.activity) + "/books?id=" + isbn.substring(isbn.indexOf(61) + 1) + "&pg=" + pageId + "&vq=" + query));
            intent.addFlags(524288);
            this.activity.startActivity(intent);
        }
    }
}
