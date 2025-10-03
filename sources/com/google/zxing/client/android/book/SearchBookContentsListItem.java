package com.google.zxing.client.android.book;

import android.content.Context;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.zxing.client.android.C4453R;
import java.util.Locale;

/* loaded from: classes2.dex */
public final class SearchBookContentsListItem extends LinearLayout {
    private TextView pageNumberView;
    private TextView snippetView;

    public SearchBookContentsListItem(Context context) {
        super(context);
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.pageNumberView = (TextView) findViewById(C4453R.id.page_number_view);
        this.snippetView = (TextView) findViewById(C4453R.id.snippet_view);
    }

    public void set(SearchBookContentsResult searchBookContentsResult) {
        this.pageNumberView.setText(searchBookContentsResult.getPageNumber());
        String snippet = searchBookContentsResult.getSnippet();
        if (snippet.isEmpty()) {
            this.snippetView.setText("");
            return;
        }
        if (!searchBookContentsResult.getValidSnippet()) {
            this.snippetView.setText(snippet);
            return;
        }
        String lowerCase = SearchBookContentsResult.getQuery().toLowerCase(Locale.getDefault());
        String lowerCase2 = snippet.toLowerCase(Locale.getDefault());
        SpannableString spannableString = new SpannableString(snippet);
        StyleSpan styleSpan = new StyleSpan(1);
        int length = lowerCase.length();
        int i9 = 0;
        while (true) {
            int iIndexOf = lowerCase2.indexOf(lowerCase, i9);
            if (iIndexOf < 0) {
                this.snippetView.setText(spannableString);
                return;
            } else {
                int i10 = iIndexOf + length;
                spannableString.setSpan(styleSpan, iIndexOf, i10, 0);
                i9 = i10;
            }
        }
    }

    public SearchBookContentsListItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
