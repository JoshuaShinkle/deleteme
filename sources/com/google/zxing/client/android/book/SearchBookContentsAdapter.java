package com.google.zxing.client.android.book;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.google.zxing.client.android.C4453R;
import java.util.List;

/* loaded from: classes2.dex */
final class SearchBookContentsAdapter extends ArrayAdapter<SearchBookContentsResult> {
    public SearchBookContentsAdapter(Context context, List<SearchBookContentsResult> list) {
        super(context, C4453R.layout.search_book_contents_list_item, 0, list);
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i9, View view, ViewGroup viewGroup) {
        View view2;
        SearchBookContentsListItem searchBookContentsListItem;
        if (view != null) {
            boolean z8 = view instanceof SearchBookContentsListItem;
            view2 = view;
            if (z8) {
                searchBookContentsListItem = (SearchBookContentsListItem) view;
            }
            return view2;
        }
        searchBookContentsListItem = (SearchBookContentsListItem) LayoutInflater.from(getContext()).inflate(C4453R.layout.search_book_contents_list_item, viewGroup, false);
        searchBookContentsListItem.set((SearchBookContentsResult) getItem(i9));
        view2 = searchBookContentsListItem;
        return view2;
    }
}
