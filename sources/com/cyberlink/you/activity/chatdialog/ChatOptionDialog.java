package com.cyberlink.you.activity.chatdialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.utility.CLUtility;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import p116k4.C5179r0;

/* loaded from: classes.dex */
public class ChatOptionDialog extends Dialog {

    /* renamed from: b */
    public Map<ChatOptionButton, C2025b> f10243b;

    public enum ChatOptionButton {
        Invite(R.string.chat_group_more_dialog_invite_friend, R.drawable.dropdown_icon_invite),
        TurnOffNotification(R.string.chat_group_more_dialog_close_allert, R.drawable.dropdown_icon_turn_off_alberts),
        EditName(R.string.chat_group_more_dialog_edit_name, R.drawable.dropdown_icon_edit_name),
        Call(R.string.free_call, R.drawable.dropdown_icons_free_call),
        Block(R.string.friends_friends_title_frieds_block, R.drawable.dropdown_icon_block),
        EditGroup(R.string.edit_group, R.drawable.dropdown_icon_edit_group),
        CreateGroup(R.string.create_group, R.drawable.dropdown_icon_creat_a_group),
        AddToHome(R.string.add_to_home, R.drawable.dropdown_icons_add_home),
        Contents(0, 0),
        Leave(R.string.chat_group_more_dialog_leave_chat, R.drawable.dropdown_icon_leave_chat),
        Profile(R.string.more_fragment_profile, R.drawable.dropdown_icon_profile),
        Photo(R.string.chat_group_more_dialog_photos, R.drawable.dropdown_icon_photos),
        Album(R.string.chat_group_more_dialog_group_album, R.drawable.dropdown_icon_albums),
        Video(R.string.videos, R.drawable.dropdown_icon_video),
        File(R.string.chat_option_dialog_files, R.drawable.dropdown_icons_file),
        GroupProfile(R.string.more_fragment_group_profile, R.drawable.dropdown_icon_gprofile),
        InviteByLink(R.string.chat_group_more_dialog_invite_friend_by_link, R.drawable.dropdown_icon_invite_by_link),
        BreakUp(R.string.break_up, R.drawable.dropdown_icon_breakup),
        Forum(R.string.bulletins, R.drawable.dropdown_icons_forums),
        Poll(R.string.polls, R.drawable.dropdown_icons_polls);

        private int imageResId;
        private int textResId;

        ChatOptionButton(int i9, int i10) {
            this.imageResId = i10;
            this.textResId = i9;
        }

        /* renamed from: a */
        public int m11993a() {
            return this.imageResId;
        }

        /* renamed from: b */
        public int m11994b() {
            return this.textResId;
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatOptionDialog$a */
    public static class C2024a {

        /* renamed from: a */
        public List<Pair<ChatOptionButton, View.OnClickListener>> f10265a = new ArrayList();

        /* renamed from: a */
        public void m11995a(ChatOptionButton chatOptionButton, View.OnClickListener onClickListener) {
            this.f10265a.add(Pair.create(chatOptionButton, onClickListener));
        }

        /* renamed from: b */
        public ChatOptionDialog m11996b(Activity activity, View view) {
            ChatOptionDialog chatOptionDialog = new ChatOptionDialog(activity, R.style.ChatMoreOptionTheme);
            chatOptionDialog.setContentView(R.layout.dialog_chat_option);
            m12001g(activity, view, chatOptionDialog);
            m12000f(activity, chatOptionDialog);
            return chatOptionDialog;
        }

        /* renamed from: c */
        public final int m11997c(Activity activity, View view) throws Resources.NotFoundException {
            if (view == null) {
                return 0;
            }
            int[] iArr = new int[2];
            view.getLocationOnScreen(iArr);
            activity.getWindowManager().getDefaultDisplay().getMetrics(new DisplayMetrics());
            return ((iArr[1] - CLUtility.m16533f1(activity)) + view.getHeight()) - activity.getResources().getDimensionPixelSize(R.dimen.t20dp);
        }

        /* renamed from: d */
        public final int m11998d(int i9) {
            return i9 % 4 == 0 ? i9 / 4 : (i9 / 4) + 1;
        }

        /* renamed from: e */
        public final void m11999e(Context context, Dialog dialog, int i9) {
            LinearLayout linearLayout = (LinearLayout) dialog.findViewById(CLUtility.m16491T0(context, "chatOptionLayout", TtmlNode.ATTR_ID, context.getPackageName()));
            for (int i10 = 4; i10 > 4 - i9; i10--) {
                View viewFindViewById = dialog.findViewById(CLUtility.m16491T0(context, "chatOptionLayoutRow" + i10, TtmlNode.ATTR_ID, context.getPackageName()));
                if (viewFindViewById != null) {
                    viewFindViewById.setVisibility(8);
                }
            }
            ViewGroup.LayoutParams layoutParams = linearLayout.getLayoutParams();
            int dimension = layoutParams.height - (((int) context.getResources().getDimension(R.dimen.t80dp)) * i9);
            layoutParams.height = dimension;
            if (i9 > 2) {
                layoutParams.height = dimension - ((int) context.getResources().getDimension(R.dimen.t5dp));
            }
        }

        /* renamed from: f */
        public final void m12000f(Activity activity, ChatOptionDialog chatOptionDialog) {
            int i9 = 0;
            for (int i10 = 1; i10 <= 4; i10++) {
                int i11 = 1;
                while (i11 <= 4) {
                    C2025b c2025b = new C2025b();
                    c2025b.f10266a = (TextView) chatOptionDialog.findViewById(CLUtility.m16491T0(activity, "button" + i10 + "_" + i11 + "_Text", TtmlNode.ATTR_ID, activity.getPackageName()));
                    c2025b.f10267b = (ImageView) chatOptionDialog.findViewById(CLUtility.m16491T0(activity, "button" + i10 + "_" + i11 + "_Image", TtmlNode.ATTR_ID, activity.getPackageName()));
                    c2025b.f10268c = chatOptionDialog.findViewById(CLUtility.m16491T0(activity, "button" + i10 + "_" + i11 + "_Area", TtmlNode.ATTR_ID, activity.getPackageName()));
                    if (i9 < this.f10265a.size()) {
                        Pair<ChatOptionButton, View.OnClickListener> pair = this.f10265a.get(i9);
                        ChatOptionButton chatOptionButton = (ChatOptionButton) pair.first;
                        View.OnClickListener onClickListener = (View.OnClickListener) pair.second;
                        c2025b.f10266a.setText(chatOptionButton.m11994b());
                        C5179r0.m20247b(c2025b.f10266a, 2);
                        c2025b.f10267b.setImageResource(chatOptionButton.m11993a());
                        c2025b.f10268c.setOnClickListener(onClickListener);
                        chatOptionDialog.m11990a(chatOptionButton, c2025b);
                    } else {
                        c2025b.f10266a.setText("");
                        c2025b.f10267b.setImageBitmap(null);
                    }
                    i11++;
                    i9++;
                }
            }
            int iM11998d = m11998d(this.f10265a.size());
            if (iM11998d < 4) {
                m11999e(activity, chatOptionDialog, 4 - iM11998d);
            }
        }

        /* renamed from: g */
        public final void m12001g(Activity activity, View view, Dialog dialog) {
            WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            attributes.gravity = 53;
            attributes.y = m11997c(activity, view);
            attributes.width = displayMetrics.widthPixels;
            dialog.getWindow().setAttributes(attributes);
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatOptionDialog$b */
    public static class C2025b {

        /* renamed from: a */
        public TextView f10266a;

        /* renamed from: b */
        public ImageView f10267b;

        /* renamed from: c */
        public View f10268c;
    }

    public ChatOptionDialog(Context context, int i9) {
        super(context, i9);
        this.f10243b = new HashMap();
    }

    /* renamed from: a */
    public void m11990a(ChatOptionButton chatOptionButton, C2025b c2025b) {
        this.f10243b.put(chatOptionButton, c2025b);
    }

    /* renamed from: b */
    public C2025b m11991b(ChatOptionButton chatOptionButton) {
        return this.f10243b.get(chatOptionButton);
    }

    /* renamed from: c */
    public boolean m11992c(ChatOptionButton chatOptionButton) {
        return this.f10243b.get(chatOptionButton) != null;
    }

    @Override // android.app.Dialog
    public boolean onTouchEvent(MotionEvent motionEvent) {
        dismiss();
        return super.onTouchEvent(motionEvent);
    }
}
