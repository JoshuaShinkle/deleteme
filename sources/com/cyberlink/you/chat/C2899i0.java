package com.cyberlink.you.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.MessageObj;
import com.cyberlink.you.utility.CLUtility;
import java.util.Date;
import java.util.List;
import p173q2.C6127a;

/* renamed from: com.cyberlink.you.chat.i0 */
/* loaded from: classes.dex */
public class C2899i0 extends ArrayAdapter<MessageObj> {

    /* renamed from: b */
    public final String f12684b;

    /* renamed from: c */
    public List<MessageObj> f12685c;

    /* renamed from: d */
    public Context f12686d;

    /* renamed from: com.cyberlink.you.chat.i0$a */
    public class a {

        /* renamed from: a */
        public ImageView f12687a;

        /* renamed from: b */
        public TextView f12688b;

        /* renamed from: c */
        public TextView f12689c;

        /* renamed from: d */
        public TextView f12690d;

        /* renamed from: e */
        public TextView f12691e;

        public a() {
        }
    }

    public C2899i0(Context context, int i9, List<MessageObj> list) {
        super(context, i9, list);
        this.f12684b = "SearchChatAdapter";
        this.f12685c = list;
        this.f12686d = context;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i9, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_item_search_chat_item, viewGroup, false);
            aVar = new a();
            aVar.f12687a = (ImageView) view.findViewById(R.id.userAvatarView);
            aVar.f12688b = (TextView) view.findViewById(R.id.userNameView);
            aVar.f12689c = (TextView) view.findViewById(R.id.messageView);
            aVar.f12690d = (TextView) view.findViewById(R.id.messageDateView);
            aVar.f12691e = (TextView) view.findViewById(R.id.messageTimeView);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        MessageObj messageObj = this.f12685c.get(i9);
        if (messageObj != null) {
            String strM14745G = messageObj.m14745G();
            if (strM14745G == null || strM14745G.isEmpty() || strM14745G.equals("null")) {
                aVar.f12687a.setImageResource(R.drawable.pic_default);
            } else {
                C6127a.m23469j(this.f12686d, aVar.f12687a, C2950b0.m14899A().m15003C(strM14745G));
            }
            aVar.f12688b.setText(messageObj.m14746H());
            aVar.f12689c.setText(messageObj.m14779q());
            Date dateM14788z = messageObj.m14788z();
            aVar.f12690d.setText(CLUtility.m16458K2(dateM14788z));
            aVar.f12691e.setText(CLUtility.m16454J2(dateM14788z));
        }
        return view;
    }
}
