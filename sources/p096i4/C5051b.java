package p096i4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.cyberlink.p030U.R;
import com.cyberlink.you.sticker.Emoji.EmojiCategory;
import com.cyberlink.you.widgetpool.clhorizontalgridview.AbsListView;
import java.util.List;

/* renamed from: i4.b */
/* loaded from: classes.dex */
public class C5051b extends ArrayAdapter<EmojiCategory> {

    /* renamed from: b */
    public int f17435b;

    /* renamed from: i4.b$b */
    public class b {

        /* renamed from: a */
        public ImageView f17436a;

        /* renamed from: b */
        public RelativeLayout f17437b;

        public b() {
        }
    }

    public C5051b(Context context, int i9, List<EmojiCategory> list) {
        super(context, i9, list);
        this.f17435b = 0;
    }

    /* renamed from: a */
    public void m19751a(int i9) {
        this.f17435b = i9;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i9, View view, ViewGroup viewGroup) {
        b bVar;
        View view2;
        if (view == null) {
            RelativeLayout relativeLayout = new RelativeLayout(getContext());
            View viewInflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.view_item_emoji_category, viewGroup, false);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(13, -1);
            viewInflate.setLayoutParams(layoutParams);
            bVar = new b();
            bVar.f17436a = (ImageView) viewInflate.findViewById(R.id.emoji_category_image);
            bVar.f17437b = (RelativeLayout) viewInflate.findViewById(R.id.emoji_category_background);
            relativeLayout.addView(viewInflate);
            relativeLayout.setLayoutParams(new AbsListView.C3217g(-1, -1));
            relativeLayout.setTag(bVar);
            view2 = relativeLayout;
        } else {
            bVar = (b) view.getTag();
            view2 = view;
        }
        bVar.f17436a.setImageBitmap(C5050a.m19748a(getContext(), (EmojiCategory) getItem(i9)));
        if (this.f17435b == i9) {
            bVar.f17437b.setBackgroundResource(R.drawable.bg_emoji_category_selected);
        } else {
            bVar.f17437b.setBackgroundColor(getContext().getResources().getColor(R.color.you_color_hint));
        }
        return view2;
    }
}
