package com.rockerhieu.emojicon;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.rockerhieu.emojicon.emoji.Emojicon;

/* loaded from: classes2.dex */
class EmojiAdapter extends ArrayAdapter<Emojicon> {

    public class ViewHolder {
        TextView icon;

        public ViewHolder() {
        }
    }

    public EmojiAdapter(Context context, Emojicon[] emojiconArr) {
        super(context, C4611R.layout.emojicon_item, emojiconArr);
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i9, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = View.inflate(getContext(), C4611R.layout.emojicon_item, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.icon = (TextView) view.findViewById(C4611R.id.emojicon_icon);
            view.setTag(viewHolder);
        }
        ((ViewHolder) view.getTag()).icon.setText(((Emojicon) getItem(i9)).getEmoji());
        return view;
    }
}
