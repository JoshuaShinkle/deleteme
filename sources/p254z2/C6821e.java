package p254z2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.activity.chatdialog.message.SelfDestructView;
import com.cyberlink.you.sticker.view.GifImageView;

/* renamed from: z2.e */
/* loaded from: classes.dex */
public class C6821e {
    /* renamed from: a */
    public static void m25423a(C6819c c6819c, View view) {
        c6819c.f22613b = (ImageView) view.findViewById(R.id.ChatMessageAvatar);
        c6819c.f22611a = (TextView) view.findViewById(R.id.ChatMessageNameTextView);
        c6819c.f22623k = (TextView) view.findViewById(R.id.ChatMessageTimeTextView);
        c6819c.f22596L = view.findViewById(R.id.itemCheckBox);
    }

    /* renamed from: b */
    public static View m25424b(C6819c c6819c, LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View viewInflate = layoutInflater.inflate(R.layout.view_item_recv_animpng_sticker_msg, viewGroup, false);
        m25423a(c6819c, viewInflate);
        ImageView imageView = (ImageView) viewInflate.findViewById(R.id.ChatMessageContentStickerView);
        c6819c.f22616d = imageView;
        imageView.setContentDescription("[AID]Chat_ReceiveAnimPNGStickerMsg");
        c6819c.f22627o = c6819c.f22616d;
        c6819c.f22625m = (SelfDestructView) viewInflate.findViewById(R.id.selfDestructImage);
        c6819c.f22628p = (TextView) viewInflate.findViewById(R.id.ttl);
        c6819c.f22626n = (ImageView) viewInflate.findViewById(R.id.explode);
        c6819c.f22606V = (ImageView) viewInflate.findViewById(R.id.ChatMessageBroadcasterTag);
        return viewInflate;
    }

    /* renamed from: c */
    public static View m25425c(C6819c c6819c, LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View viewInflate = layoutInflater.inflate(R.layout.view_item_recv_anim_sticker_msg, viewGroup, false);
        m25423a(c6819c, viewInflate);
        GifImageView gifImageView = (GifImageView) viewInflate.findViewById(R.id.ChatMessageContentGifView);
        c6819c.f22619g = gifImageView;
        gifImageView.setContentDescription("[AID]Chat_ReceiveAnimStickerMsg");
        c6819c.f22627o = c6819c.f22619g;
        c6819c.f22625m = (SelfDestructView) viewInflate.findViewById(R.id.selfDestructImage);
        c6819c.f22628p = (TextView) viewInflate.findViewById(R.id.ttl);
        c6819c.f22626n = (ImageView) viewInflate.findViewById(R.id.explode);
        c6819c.f22606V = (ImageView) viewInflate.findViewById(R.id.ChatMessageBroadcasterTag);
        return viewInflate;
    }

    /* renamed from: d */
    public static View m25426d(C6819c c6819c, LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View viewInflate = layoutInflater.inflate(R.layout.view_item_recv_audio_msg, viewGroup, false);
        m25423a(c6819c, viewInflate);
        c6819c.f22617e = (ImageView) viewInflate.findViewById(R.id.ChatMessageAudioOpView);
        c6819c.f22620h = (TextView) viewInflate.findViewById(R.id.ChatMessageAudioDuration);
        c6819c.f22586B = (ImageView) viewInflate.findViewById(R.id.ChatMessageStopView);
        c6819c.f22627o = viewInflate.findViewById(R.id.contentLayout2);
        c6819c.f22628p = (TextView) viewInflate.findViewById(R.id.ttl);
        c6819c.f22626n = (ImageView) viewInflate.findViewById(R.id.explode);
        RelativeLayout relativeLayout = (RelativeLayout) viewInflate.findViewById(R.id.ChatMessageClickLayout);
        c6819c.f22638z = relativeLayout;
        relativeLayout.setContentDescription("[AID]Chat_ReceiveAudioMsg");
        c6819c.f22593I = (ImageView) viewInflate.findViewById(R.id.ChatMessageSendFail);
        c6819c.f22625m = (SelfDestructView) viewInflate.findViewById(R.id.selfDestructImage);
        c6819c.f22595K = viewInflate.findViewById(R.id.downloadProgressbar);
        c6819c.f22606V = (ImageView) viewInflate.findViewById(R.id.ChatMessageBroadcasterTag);
        return viewInflate;
    }

    /* renamed from: e */
    public static View m25427e(C6819c c6819c, LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View viewInflate = layoutInflater.inflate(R.layout.view_item_recv_call_msg, viewGroup, false);
        m25423a(c6819c, viewInflate);
        c6819c.f22617e = (ImageView) viewInflate.findViewById(R.id.ChatMessageAudioOpView);
        c6819c.f22586B = (ImageView) viewInflate.findViewById(R.id.ChatMessageStopView);
        c6819c.f22627o = viewInflate.findViewById(R.id.contentLayout2);
        c6819c.f22628p = (TextView) viewInflate.findViewById(R.id.ttl);
        c6819c.f22626n = (ImageView) viewInflate.findViewById(R.id.explode);
        c6819c.f22638z = (RelativeLayout) viewInflate.findViewById(R.id.ChatMessageClickLayout);
        c6819c.f22593I = (ImageView) viewInflate.findViewById(R.id.ChatMessageSendFail);
        c6819c.f22625m = (SelfDestructView) viewInflate.findViewById(R.id.selfDestructImage);
        c6819c.f22595K = viewInflate.findViewById(R.id.downloadProgressbar);
        return viewInflate;
    }

    /* renamed from: f */
    public static View m25428f(C6819c c6819c, LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View viewInflate = layoutInflater.inflate(R.layout.view_item_recv_encrypted_msg, viewGroup, false);
        m25423a(c6819c, viewInflate);
        c6819c.f22615c = (ImageView) viewInflate.findViewById(R.id.ChatMessageContentPhotoView);
        c6819c.f22627o = viewInflate.findViewById(R.id.contentLayout);
        c6819c.f22606V = (ImageView) viewInflate.findViewById(R.id.ChatMessageBroadcasterTag);
        return viewInflate;
    }

    /* renamed from: g */
    public static View m25429g(C6819c c6819c, LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View viewInflate = layoutInflater.inflate(R.layout.view_item_recv_file_msg, viewGroup, false);
        m25423a(c6819c, viewInflate);
        c6819c.f22618f = (ImageView) viewInflate.findViewById(R.id.fileImage);
        c6819c.f22620h = (TextView) viewInflate.findViewById(R.id.ChatMessageContentTextView);
        RelativeLayout relativeLayout = (RelativeLayout) viewInflate.findViewById(R.id.fileContentView);
        c6819c.f22638z = relativeLayout;
        relativeLayout.setContentDescription("[AID]Chat_ReceiveFileMsg");
        c6819c.f22627o = c6819c.f22620h;
        c6819c.f22631s = (TextView) viewInflate.findViewById(R.id.FileInfoTextView);
        c6819c.f22625m = (SelfDestructView) viewInflate.findViewById(R.id.selfDestructImage);
        c6819c.f22628p = (TextView) viewInflate.findViewById(R.id.ttl);
        c6819c.f22626n = (ImageView) viewInflate.findViewById(R.id.explode);
        return viewInflate;
    }

    /* renamed from: h */
    public static View m25430h(C6819c c6819c, LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View viewInflate = layoutInflater.inflate(R.layout.view_item_recv_forward_text_msg, viewGroup, false);
        m25423a(c6819c, viewInflate);
        TextView textView = (TextView) viewInflate.findViewById(R.id.ChatMessageContentTextView);
        c6819c.f22620h = textView;
        textView.setContentDescription("[AID]Chat_ReceiveForwardTextMsg");
        c6819c.f22623k = (TextView) viewInflate.findViewById(R.id.ChatMessageTimeTextView);
        c6819c.f22627o = viewInflate.findViewById(R.id.ChatMessageContentLayout2);
        c6819c.f22628p = (TextView) viewInflate.findViewById(R.id.ttl);
        c6819c.f22596L = viewInflate.findViewById(R.id.itemCheckBox);
        c6819c.f22605U = (TextView) viewInflate.findViewById(R.id.sourceSenderTextView);
        c6819c.f22607W = (ImageView) viewInflate.findViewById(R.id.urlImage);
        c6819c.f22608X = (TextView) viewInflate.findViewById(R.id.urlTitle);
        c6819c.f22609Y = (TextView) viewInflate.findViewById(R.id.urlDescription);
        c6819c.f22610Z = (LinearLayout) viewInflate.findViewById(R.id.urlMultiPreview);
        return viewInflate;
    }

    /* renamed from: i */
    public static View m25431i(C6819c c6819c, LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View viewInflate = layoutInflater.inflate(R.layout.view_item_recv_location_msg, viewGroup, false);
        m25423a(c6819c, viewInflate);
        c6819c.f22588D = (TextView) viewInflate.findViewById(R.id.PlaceNameTextView);
        c6819c.f22620h = (TextView) viewInflate.findViewById(R.id.ChatMessageContentTextView);
        c6819c.f22590F = (RelativeLayout) viewInflate.findViewById(R.id.ChatMessageNoteLayout);
        c6819c.f22615c = (ImageView) viewInflate.findViewById(R.id.ChatMessageContentPhotoView);
        c6819c.f22589E = (ImageView) viewInflate.findViewById(R.id.ChatMessageNoteArrowLayout);
        c6819c.f22615c.setContentDescription("[AID]Chat_ReceiveLocationMsg");
        c6819c.f22637y = (RelativeLayout) viewInflate.findViewById(R.id.ChatMessageTextLayout);
        c6819c.f22592H = (TextView) viewInflate.findViewById(R.id.commentStringText);
        c6819c.f22591G = (RelativeLayout) viewInflate.findViewById(R.id.ChatPhotoCommentCntView);
        c6819c.f22627o = c6819c.f22615c;
        c6819c.f22625m = (SelfDestructView) viewInflate.findViewById(R.id.selfDestructImage);
        c6819c.f22628p = (TextView) viewInflate.findViewById(R.id.ttl);
        c6819c.f22626n = (ImageView) viewInflate.findViewById(R.id.explode);
        c6819c.f22638z = (RelativeLayout) viewInflate.findViewById(R.id.ChatMessageClickLayout);
        return viewInflate;
    }

    /* renamed from: j */
    public static View m25432j(C6819c c6819c, LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View viewInflate = layoutInflater.inflate(R.layout.view_item_recv_media_create_msg, viewGroup, false);
        m25423a(c6819c, viewInflate);
        ImageView imageView = (ImageView) viewInflate.findViewById(R.id.ChatMessageContentPhotoView);
        c6819c.f22615c = imageView;
        imageView.setContentDescription("[AID]Chat_ReceiveMediaCreateMsg");
        c6819c.f22620h = (TextView) viewInflate.findViewById(R.id.ChatMessageContentTextView);
        c6819c.f22588D = (TextView) viewInflate.findViewById(R.id.ChatMessageTitleTextView);
        c6819c.f22638z = (RelativeLayout) viewInflate.findViewById(R.id.ChatMessageClickLayout);
        c6819c.f22627o = c6819c.f22615c;
        c6819c.f22625m = (SelfDestructView) viewInflate.findViewById(R.id.selfDestructImage);
        c6819c.f22628p = (TextView) viewInflate.findViewById(R.id.ttl);
        c6819c.f22626n = (ImageView) viewInflate.findViewById(R.id.explode);
        return viewInflate;
    }

    /* renamed from: k */
    public static View m25433k(C6819c c6819c, LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View viewInflate = layoutInflater.inflate(R.layout.view_item_recv_need_update_msg, viewGroup, false);
        viewInflate.setContentDescription("[AID]Chat_ReceiveNeedUpdateMsg");
        c6819c.f22620h = (TextView) viewInflate.findViewById(R.id.ChatMessageContentTextView);
        c6819c.f22623k = (TextView) viewInflate.findViewById(R.id.ChatMessageTimeTextView);
        c6819c.f22597M = (TextView) viewInflate.findViewById(R.id.update_text);
        return viewInflate;
    }

    /* renamed from: l */
    public static View m25434l(C6819c c6819c, LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View viewInflate = layoutInflater.inflate(R.layout.view_item_recv_comment_msg, viewGroup, false);
        m25423a(c6819c, viewInflate);
        ImageView imageView = (ImageView) viewInflate.findViewById(R.id.ChatMessageContentPhotoView);
        c6819c.f22615c = imageView;
        imageView.setContentDescription("[AID]Chat_ReceivePhotoView");
        c6819c.f22598N = (ImageView) viewInflate.findViewById(R.id.ChatMessageContentPhotoPenView);
        c6819c.f22620h = (TextView) viewInflate.findViewById(R.id.ChatMessageContentTextView);
        c6819c.f22585A = (RelativeLayout) viewInflate.findViewById(R.id.voiceLayout);
        c6819c.f22587C = (TextView) viewInflate.findViewById(R.id.VoiceTimeTextView);
        c6819c.f22638z = (RelativeLayout) viewInflate.findViewById(R.id.commentLayout);
        c6819c.f22627o = viewInflate.findViewById(R.id.ChatMessageClickLayout);
        c6819c.f22625m = (SelfDestructView) viewInflate.findViewById(R.id.selfDestructImage);
        c6819c.f22628p = (TextView) viewInflate.findViewById(R.id.ttl);
        c6819c.f22626n = (ImageView) viewInflate.findViewById(R.id.explode);
        c6819c.f22607W = (ImageView) viewInflate.findViewById(R.id.urlImage);
        c6819c.f22608X = (TextView) viewInflate.findViewById(R.id.urlTitle);
        c6819c.f22609Y = (TextView) viewInflate.findViewById(R.id.urlDescription);
        c6819c.f22610Z = (LinearLayout) viewInflate.findViewById(R.id.urlMultiPreview);
        return viewInflate;
    }

    /* renamed from: m */
    public static View m25435m(C6819c c6819c, LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View viewInflate = layoutInflater.inflate(R.layout.view_item_recv_photo_msg, viewGroup, false);
        m25423a(c6819c, viewInflate);
        ImageView imageView = (ImageView) viewInflate.findViewById(R.id.ChatMessageContentPhotoView);
        c6819c.f22615c = imageView;
        imageView.setContentDescription("[AID]Chat_ReceivePhotoMsg");
        c6819c.f22592H = (TextView) viewInflate.findViewById(R.id.commentStringText);
        c6819c.f22591G = (RelativeLayout) viewInflate.findViewById(R.id.ChatPhotoCommentCntView);
        c6819c.f22627o = viewInflate.findViewById(R.id.contentLayout);
        c6819c.f22625m = (SelfDestructView) viewInflate.findViewById(R.id.selfDestructImage);
        c6819c.f22628p = (TextView) viewInflate.findViewById(R.id.ttl);
        c6819c.f22626n = (ImageView) viewInflate.findViewById(R.id.explode);
        c6819c.f22606V = (ImageView) viewInflate.findViewById(R.id.ChatMessageBroadcasterTag);
        return viewInflate;
    }

    /* renamed from: n */
    public static View m25436n(C6819c c6819c, LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View viewInflate = layoutInflater.inflate(R.layout.view_item_recv_photo_note_msg, viewGroup, false);
        m25423a(c6819c, viewInflate);
        c6819c.f22620h = (TextView) viewInflate.findViewById(R.id.ChatMessageContentTextView);
        ImageView imageView = (ImageView) viewInflate.findViewById(R.id.ChatMessageContentPhotoView);
        c6819c.f22615c = imageView;
        imageView.setContentDescription("[AID]Chat_ReceivePhotoNoteMsg");
        c6819c.f22587C = (TextView) viewInflate.findViewById(R.id.VoiceTimeTextView);
        c6819c.f22636x = (RelativeLayout) viewInflate.findViewById(R.id.ChatMessageVoicePlayLayout);
        c6819c.f22637y = (RelativeLayout) viewInflate.findViewById(R.id.ChatMessageTextLayout);
        c6819c.f22592H = (TextView) viewInflate.findViewById(R.id.commentStringText);
        c6819c.f22591G = (RelativeLayout) viewInflate.findViewById(R.id.ChatPhotoCommentCntView);
        c6819c.f22627o = c6819c.f22615c;
        c6819c.f22625m = (SelfDestructView) viewInflate.findViewById(R.id.selfDestructImage);
        c6819c.f22628p = (TextView) viewInflate.findViewById(R.id.ttl);
        c6819c.f22626n = (ImageView) viewInflate.findViewById(R.id.explode);
        c6819c.f22595K = viewInflate.findViewById(R.id.downloadProgressbar);
        c6819c.f22638z = (RelativeLayout) viewInflate.findViewById(R.id.ChatMessageClickLayout);
        c6819c.f22590F = (RelativeLayout) viewInflate.findViewById(R.id.ChatMessageNoteLayout);
        c6819c.f22589E = (ImageView) viewInflate.findViewById(R.id.ChatMessageNoteArrowLayout);
        c6819c.f22606V = (ImageView) viewInflate.findViewById(R.id.ChatMessageBroadcasterTag);
        return viewInflate;
    }

    /* renamed from: o */
    public static View m25437o(C6819c c6819c, LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View viewInflate = layoutInflater.inflate(R.layout.view_item_recv_poll_msg, viewGroup, false);
        m25423a(c6819c, viewInflate);
        c6819c.f22620h = (TextView) viewInflate.findViewById(R.id.ChatMessageContentTextView);
        RelativeLayout relativeLayout = (RelativeLayout) viewInflate.findViewById(R.id.contentLayout);
        c6819c.f22638z = relativeLayout;
        relativeLayout.setContentDescription("[AID]Chat_ReceivePollMsg");
        c6819c.f22612a0 = (TextView) viewInflate.findViewById(R.id.textViewVote);
        c6819c.f22614b0 = (ImageView) viewInflate.findViewById(R.id.imageViewGoto);
        return viewInflate;
    }

    /* renamed from: p */
    public static View m25438p(C6819c c6819c, LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View viewInflate = layoutInflater.inflate(R.layout.view_item_recv_pollpost_msg, viewGroup, false);
        m25423a(c6819c, viewInflate);
        c6819c.f22620h = (TextView) viewInflate.findViewById(R.id.ChatMessageContentTextView);
        c6819c.f22599O = (TextView) viewInflate.findViewById(R.id.reply);
        RelativeLayout relativeLayout = (RelativeLayout) viewInflate.findViewById(R.id.contentLayout);
        c6819c.f22638z = relativeLayout;
        relativeLayout.setContentDescription("[AID]Chat_ReceivePollPostMsg");
        return viewInflate;
    }

    /* renamed from: q */
    public static View m25439q(C6819c c6819c, LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View viewInflate = layoutInflater.inflate(R.layout.view_item_recv_reply_text, viewGroup, false);
        m25423a(c6819c, viewInflate);
        c6819c.f22620h = (TextView) viewInflate.findViewById(R.id.ChatMessageContentTextView);
        RelativeLayout relativeLayout = (RelativeLayout) viewInflate.findViewById(R.id.contentLayout);
        c6819c.f22638z = relativeLayout;
        relativeLayout.setContentDescription("[AID]Chat_ReceiveReplyTextMsg");
        c6819c.f22599O = (TextView) viewInflate.findViewById(R.id.replyMessage);
        c6819c.f22600P = (TextView) viewInflate.findViewById(R.id.replyName);
        c6819c.f22627o = c6819c.f22620h;
        c6819c.f22607W = (ImageView) viewInflate.findViewById(R.id.urlImage);
        c6819c.f22608X = (TextView) viewInflate.findViewById(R.id.urlTitle);
        c6819c.f22609Y = (TextView) viewInflate.findViewById(R.id.urlDescription);
        c6819c.f22610Z = (LinearLayout) viewInflate.findViewById(R.id.urlMultiPreview);
        c6819c.f22601Q = (ImageView) viewInflate.findViewById(R.id.symbolleft);
        c6819c.f22602R = (ImageView) viewInflate.findViewById(R.id.symbolright);
        c6819c.f22618f = (ImageView) viewInflate.findViewById(R.id.fileIcon);
        return viewInflate;
    }

    /* renamed from: r */
    public static View m25440r(C6819c c6819c, LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View viewInflate = layoutInflater.inflate(R.layout.view_item_recv_report_to_admin_msg, viewGroup, false);
        m25423a(c6819c, viewInflate);
        c6819c.f22620h = (TextView) viewInflate.findViewById(R.id.ChatMessageContentTextView);
        RelativeLayout relativeLayout = (RelativeLayout) viewInflate.findViewById(R.id.contentLayout);
        c6819c.f22638z = relativeLayout;
        relativeLayout.setContentDescription("[AID]Chat_ReceiveReportToAdminMsg");
        return viewInflate;
    }

    /* renamed from: s */
    public static View m25441s(C6819c c6819c, LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View viewInflate = layoutInflater.inflate(R.layout.view_item_recv_sticker_msg, viewGroup, false);
        m25423a(c6819c, viewInflate);
        ImageView imageView = (ImageView) viewInflate.findViewById(R.id.ChatMessageContentStickerView);
        c6819c.f22616d = imageView;
        imageView.setContentDescription("[AID]Chat_ReceiveStickerMsg");
        c6819c.f22627o = c6819c.f22616d;
        c6819c.f22625m = (SelfDestructView) viewInflate.findViewById(R.id.selfDestructImage);
        c6819c.f22628p = (TextView) viewInflate.findViewById(R.id.ttl);
        c6819c.f22626n = (ImageView) viewInflate.findViewById(R.id.explode);
        c6819c.f22606V = (ImageView) viewInflate.findViewById(R.id.ChatMessageBroadcasterTag);
        return viewInflate;
    }

    /* renamed from: t */
    public static View m25442t(C6819c c6819c, LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View viewInflate = layoutInflater.inflate(R.layout.view_item_recv_text_msg, viewGroup, false);
        m25423a(c6819c, viewInflate);
        c6819c.f22621i = viewInflate.findViewById(R.id.TextContentArea);
        TextView textView = (TextView) viewInflate.findViewById(R.id.ChatMessageContentTextView);
        c6819c.f22620h = textView;
        textView.setContentDescription("[AID]Chat_ReceiveTextMsg");
        c6819c.f22627o = viewInflate.findViewById(R.id.ChatMessageContentLayout2);
        c6819c.f22625m = (SelfDestructView) viewInflate.findViewById(R.id.selfDestructImage);
        c6819c.f22628p = (TextView) viewInflate.findViewById(R.id.ttl);
        c6819c.f22626n = (ImageView) viewInflate.findViewById(R.id.explode);
        c6819c.f22606V = (ImageView) viewInflate.findViewById(R.id.ChatMessageBroadcasterTag);
        c6819c.f22607W = (ImageView) viewInflate.findViewById(R.id.urlImage);
        c6819c.f22608X = (TextView) viewInflate.findViewById(R.id.urlTitle);
        c6819c.f22609Y = (TextView) viewInflate.findViewById(R.id.urlDescription);
        c6819c.f22610Z = (LinearLayout) viewInflate.findViewById(R.id.urlMultiPreview);
        return viewInflate;
    }

    /* renamed from: u */
    public static View m25443u(C6819c c6819c, LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View viewInflate = layoutInflater.inflate(R.layout.view_item_recv_user_contact_msg, viewGroup, false);
        m25423a(c6819c, viewInflate);
        c6819c.f22615c = (ImageView) viewInflate.findViewById(R.id.ChatMessageContentPhotoView);
        c6819c.f22620h = (TextView) viewInflate.findViewById(R.id.ChatMessageContentTextView);
        RelativeLayout relativeLayout = (RelativeLayout) viewInflate.findViewById(R.id.ChatMessageClickLayout);
        c6819c.f22638z = relativeLayout;
        relativeLayout.setContentDescription("[AID]Chat_ReceiveUserContactMsg");
        c6819c.f22627o = c6819c.f22638z;
        c6819c.f22625m = (SelfDestructView) viewInflate.findViewById(R.id.selfDestructImage);
        c6819c.f22628p = (TextView) viewInflate.findViewById(R.id.ttl);
        c6819c.f22626n = (ImageView) viewInflate.findViewById(R.id.explode);
        return viewInflate;
    }

    /* renamed from: v */
    public static View m25444v(C6819c c6819c, LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View viewInflate = layoutInflater.inflate(R.layout.view_item_recv_video_msg, viewGroup, false);
        m25423a(c6819c, viewInflate);
        ImageView imageView = (ImageView) viewInflate.findViewById(R.id.ChatMessageContentPhotoView);
        c6819c.f22615c = imageView;
        imageView.setContentDescription("[AID]Chat_ReceiveVideoMsg");
        c6819c.f22592H = (TextView) viewInflate.findViewById(R.id.commentStringText);
        c6819c.f22591G = (RelativeLayout) viewInflate.findViewById(R.id.ChatPhotoCommentCntView);
        c6819c.f22630r = (TextView) viewInflate.findViewById(R.id.duration);
        c6819c.f22627o = c6819c.f22615c;
        c6819c.f22625m = (SelfDestructView) viewInflate.findViewById(R.id.selfDestructImage);
        c6819c.f22628p = (TextView) viewInflate.findViewById(R.id.ttl);
        c6819c.f22626n = (ImageView) viewInflate.findViewById(R.id.explode);
        return viewInflate;
    }
}
