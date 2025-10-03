package com.google.zxing.client.android.history;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.zxing.Result;
import com.google.zxing.client.android.C4453R;
import java.util.ArrayList;

/* loaded from: classes2.dex */
final class HistoryItemAdapter extends ArrayAdapter<HistoryItem> {
    private final Context activity;

    public HistoryItemAdapter(Context context) {
        super(context, C4453R.layout.history_list_item, new ArrayList());
        this.activity = context;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i9, View view, ViewGroup viewGroup) throws Resources.NotFoundException {
        String string;
        String string2;
        if (!(view instanceof LinearLayout)) {
            view = LayoutInflater.from(this.activity).inflate(C4453R.layout.history_list_item, viewGroup, false);
        }
        HistoryItem historyItem = (HistoryItem) getItem(i9);
        Result result = historyItem.getResult();
        if (result != null) {
            string = result.getText();
            string2 = historyItem.getDisplayAndDetails();
        } else {
            Resources resources = getContext().getResources();
            string = resources.getString(C4453R.string.history_empty);
            string2 = resources.getString(C4453R.string.history_empty_detail);
        }
        ((TextView) view.findViewById(C4453R.id.history_title)).setText(string);
        ((TextView) view.findViewById(C4453R.id.history_detail)).setText(string2);
        return view;
    }
}
