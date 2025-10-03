package p254z2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.sticker.view.GifImageView;

/* renamed from: z2.f */
/* loaded from: classes.dex */
public class C6822f {
    /* renamed from: a */
    public static View m25445a(C6819c c6819c, LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View viewInflate = layoutInflater.inflate(R.layout.view_item_send_animpng_sticker_msg, viewGroup, false);
        c6819c.f22622j = (TextView) viewInflate.findViewById(R.id.ChatMessageReadCountTextView);
        ImageView imageView = (ImageView) viewInflate.findViewById(R.id.ChatMessageContentStickerView);
        c6819c.f22616d = imageView;
        imageView.setContentDescription("[AID]Chat_SendAnimPNGStickerMsg");
        c6819c.f22623k = (TextView) viewInflate.findViewById(R.id.ChatMessageTimeTextView);
        c6819c.f22629q = viewInflate.findViewById(R.id.scheduleSendMask);
        c6819c.f22627o = c6819c.f22616d;
        c6819c.f22628p = (TextView) viewInflate.findViewById(R.id.ttl);
        c6819c.f22626n = (ImageView) viewInflate.findViewById(R.id.explode);
        c6819c.f22593I = (ImageView) viewInflate.findViewById(R.id.ChatMessageSendFail);
        c6819c.f22594J = viewInflate.findViewById(R.id.sendingProgressbar);
        c6819c.f22596L = viewInflate.findViewById(R.id.itemCheckBox);
        return viewInflate;
    }

    /* renamed from: b */
    public static View m25446b(C6819c c6819c, LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View viewInflate = layoutInflater.inflate(R.layout.view_item_send_anim_sticker_msg, viewGroup, false);
        c6819c.f22622j = (TextView) viewInflate.findViewById(R.id.ChatMessageReadCountTextView);
        GifImageView gifImageView = (GifImageView) viewInflate.findViewById(R.id.ChatMessageContentGifView);
        c6819c.f22619g = gifImageView;
        gifImageView.setContentDescription("[AID]Chat_SendAnimStickerMsg");
        c6819c.f22623k = (TextView) viewInflate.findViewById(R.id.ChatMessageTimeTextView);
        c6819c.f22629q = viewInflate.findViewById(R.id.scheduleSendMask);
        c6819c.f22627o = c6819c.f22619g;
        c6819c.f22628p = (TextView) viewInflate.findViewById(R.id.ttl);
        c6819c.f22626n = (ImageView) viewInflate.findViewById(R.id.explode);
        c6819c.f22594J = viewInflate.findViewById(R.id.sendingProgressbar);
        c6819c.f22596L = viewInflate.findViewById(R.id.itemCheckBox);
        return viewInflate;
    }

    /* renamed from: c */
    public static View m25447c(C6819c c6819c, LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View viewInflate = layoutInflater.inflate(R.layout.view_item_send_audio_msg, viewGroup, false);
        c6819c.f22622j = (TextView) viewInflate.findViewById(R.id.ChatMessageReadCountTextView);
        c6819c.f22617e = (ImageView) viewInflate.findViewById(R.id.ChatMessageAudioOpView);
        c6819c.f22586B = (ImageView) viewInflate.findViewById(R.id.ChatMessageStopView);
        c6819c.f22620h = (TextView) viewInflate.findViewById(R.id.ChatMessageAudioDuration);
        c6819c.f22623k = (TextView) viewInflate.findViewById(R.id.ChatMessageTimeTextView);
        c6819c.f22624l = (ProgressBar) viewInflate.findViewById(R.id.progressBar);
        c6819c.f22629q = viewInflate.findViewById(R.id.scheduleSendMask);
        c6819c.f22627o = viewInflate.findViewById(R.id.ChatMessageLayout);
        c6819c.f22628p = (TextView) viewInflate.findViewById(R.id.ttl);
        c6819c.f22626n = (ImageView) viewInflate.findViewById(R.id.explode);
        c6819c.f22638z = (RelativeLayout) viewInflate.findViewById(R.id.ChatMessageClickLayout);
        c6819c.f22593I = (ImageView) viewInflate.findViewById(R.id.ChatMessageSendFail);
        c6819c.f22594J = viewInflate.findViewById(R.id.sendingProgressbar);
        c6819c.f22596L = viewInflate.findViewById(R.id.itemCheckBox);
        return viewInflate;
    }

    /* renamed from: d */
    public static View m25448d(C6819c c6819c, LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View viewInflate = layoutInflater.inflate(R.layout.view_item_send_call_msg, viewGroup, false);
        c6819c.f22622j = (TextView) viewInflate.findViewById(R.id.ChatMessageReadCountTextView);
        c6819c.f22617e = (ImageView) viewInflate.findViewById(R.id.ChatMessageAudioOpView);
        c6819c.f22586B = (ImageView) viewInflate.findViewById(R.id.ChatMessageStopView);
        c6819c.f22623k = (TextView) viewInflate.findViewById(R.id.ChatMessageTimeTextView);
        c6819c.f22624l = (ProgressBar) viewInflate.findViewById(R.id.progressBar);
        c6819c.f22629q = viewInflate.findViewById(R.id.scheduleSendMask);
        c6819c.f22627o = viewInflate.findViewById(R.id.ChatMessageLayout);
        c6819c.f22628p = (TextView) viewInflate.findViewById(R.id.ttl);
        c6819c.f22626n = (ImageView) viewInflate.findViewById(R.id.explode);
        c6819c.f22638z = (RelativeLayout) viewInflate.findViewById(R.id.ChatMessageClickLayout);
        c6819c.f22593I = (ImageView) viewInflate.findViewById(R.id.ChatMessageSendFail);
        c6819c.f22594J = viewInflate.findViewById(R.id.sendingProgressbar);
        c6819c.f22596L = viewInflate.findViewById(R.id.itemCheckBox);
        return viewInflate;
    }

    /* renamed from: e */
    public static View m25449e(C6819c c6819c, LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View viewInflate = layoutInflater.inflate(R.layout.view_item_send_encrypted_msg, viewGroup, false);
        c6819c.f22622j = (TextView) viewInflate.findViewById(R.id.ChatMessageReadCountTextView);
        ImageView imageView = (ImageView) viewInflate.findViewById(R.id.ChatMessageContentPhotoView);
        c6819c.f22615c = imageView;
        imageView.setImageDrawable(layoutInflater.getContext().getResources().getDrawable(R.drawable.chat_list_lock));
        c6819c.f22623k = (TextView) viewInflate.findViewById(R.id.ChatMessageTimeTextView);
        c6819c.f22627o = viewInflate.findViewById(R.id.contentLayout);
        c6819c.f22596L = viewInflate.findViewById(R.id.itemCheckBox);
        return viewInflate;
    }

    /* renamed from: f */
    public static View m25450f(C6819c c6819c, LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View viewInflate = layoutInflater.inflate(R.layout.view_item_send_file_msg, viewGroup, false);
        c6819c.f22622j = (TextView) viewInflate.findViewById(R.id.ChatMessageReadCountTextView);
        c6819c.f22618f = (ImageView) viewInflate.findViewById(R.id.fileImage);
        c6819c.f22620h = (TextView) viewInflate.findViewById(R.id.ChatMessageContentTextView);
        c6819c.f22631s = (TextView) viewInflate.findViewById(R.id.FileInfoTextView);
        RelativeLayout relativeLayout = (RelativeLayout) viewInflate.findViewById(R.id.fileContentView);
        c6819c.f22638z = relativeLayout;
        relativeLayout.setContentDescription("[AID]Chat_SendFileMsg");
        c6819c.f22623k = (TextView) viewInflate.findViewById(R.id.ChatMessageTimeTextView);
        c6819c.f22629q = viewInflate.findViewById(R.id.scheduleSendMask);
        c6819c.f22627o = c6819c.f22620h;
        c6819c.f22628p = (TextView) viewInflate.findViewById(R.id.ttl);
        c6819c.f22626n = (ImageView) viewInflate.findViewById(R.id.explode);
        c6819c.f22593I = (ImageView) viewInflate.findViewById(R.id.ChatMessageSendFail);
        c6819c.f22594J = viewInflate.findViewById(R.id.sendingProgressbar);
        c6819c.f22596L = viewInflate.findViewById(R.id.itemCheckBox);
        c6819c.f22624l = (ProgressBar) viewInflate.findViewById(R.id.progressBar);
        return viewInflate;
    }

    /* renamed from: g */
    public static View m25451g(C6819c c6819c, LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View viewInflate = layoutInflater.inflate(R.layout.view_item_send_forward_text_msg, viewGroup, false);
        c6819c.f22622j = (TextView) viewInflate.findViewById(R.id.ChatMessageReadCountTextView);
        TextView textView = (TextView) viewInflate.findViewById(R.id.ChatMessageContentTextView);
        c6819c.f22620h = textView;
        textView.setContentDescription("[AID]Chat_SendForwardTextMsg");
        c6819c.f22623k = (TextView) viewInflate.findViewById(R.id.ChatMessageTimeTextView);
        c6819c.f22627o = viewInflate.findViewById(R.id.ChatMessageContentLayout2);
        c6819c.f22628p = (TextView) viewInflate.findViewById(R.id.ttl);
        c6819c.f22593I = (ImageView) viewInflate.findViewById(R.id.ChatMessageSendFail);
        c6819c.f22594J = viewInflate.findViewById(R.id.sendingProgressbar);
        c6819c.f22596L = viewInflate.findViewById(R.id.itemCheckBox);
        c6819c.f22603S = (TextView) viewInflate.findViewById(R.id.LastForwarderTextView);
        c6819c.f22604T = viewInflate.findViewById(R.id.SourceLayout);
        c6819c.f22605U = (TextView) viewInflate.findViewById(R.id.SourceTextView);
        c6819c.f22607W = (ImageView) viewInflate.findViewById(R.id.urlImage);
        c6819c.f22608X = (TextView) viewInflate.findViewById(R.id.urlTitle);
        c6819c.f22609Y = (TextView) viewInflate.findViewById(R.id.urlDescription);
        c6819c.f22610Z = (LinearLayout) viewInflate.findViewById(R.id.urlMultiPreview);
        return viewInflate;
    }

    /* renamed from: h */
    public static View m25452h(C6819c c6819c, LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View viewInflate = layoutInflater.inflate(R.layout.view_item_send_location_msg, viewGroup, false);
        c6819c.f22622j = (TextView) viewInflate.findViewById(R.id.ChatMessageReadCountTextView);
        ImageView imageView = (ImageView) viewInflate.findViewById(R.id.ChatMessageContentPhotoView);
        c6819c.f22615c = imageView;
        imageView.setContentDescription("[AID]Chat_SendLocationMsg");
        c6819c.f22588D = (TextView) viewInflate.findViewById(R.id.PlaceNameTextView);
        c6819c.f22590F = (RelativeLayout) viewInflate.findViewById(R.id.ChatMessageNoteLayout);
        c6819c.f22589E = (ImageView) viewInflate.findViewById(R.id.ChatMessageNoteArrowLayout);
        c6819c.f22623k = (TextView) viewInflate.findViewById(R.id.ChatMessageTimeTextView);
        ProgressBar progressBar = (ProgressBar) viewInflate.findViewById(R.id.progressBar);
        c6819c.f22624l = progressBar;
        progressBar.setMax(100);
        c6819c.f22620h = (TextView) viewInflate.findViewById(R.id.ChatMessageContentTextView);
        c6819c.f22637y = (RelativeLayout) viewInflate.findViewById(R.id.ChatMessageTextLayout);
        c6819c.f22638z = (RelativeLayout) viewInflate.findViewById(R.id.ChatMessageClickLayout);
        c6819c.f22629q = viewInflate.findViewById(R.id.scheduleSendMask);
        c6819c.f22627o = c6819c.f22615c;
        c6819c.f22628p = (TextView) viewInflate.findViewById(R.id.ttl);
        c6819c.f22626n = (ImageView) viewInflate.findViewById(R.id.explode);
        c6819c.f22593I = (ImageView) viewInflate.findViewById(R.id.ChatMessageSendFail);
        c6819c.f22594J = viewInflate.findViewById(R.id.sendingProgressbar);
        c6819c.f22596L = viewInflate.findViewById(R.id.itemCheckBox);
        return viewInflate;
    }

    /* renamed from: i */
    public static View m25453i(C6819c c6819c, LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View viewInflate = layoutInflater.inflate(R.layout.view_item_send_media_create_msg, viewGroup, false);
        c6819c.f22622j = (TextView) viewInflate.findViewById(R.id.ChatMessageReadCountTextView);
        ImageView imageView = (ImageView) viewInflate.findViewById(R.id.ChatMessageContentPhotoView);
        c6819c.f22615c = imageView;
        imageView.setContentDescription("[AID]Chat_SendMediaCreateMsg");
        c6819c.f22620h = (TextView) viewInflate.findViewById(R.id.ChatMessageContentTextView);
        c6819c.f22623k = (TextView) viewInflate.findViewById(R.id.ChatMessageTimeTextView);
        c6819c.f22588D = (TextView) viewInflate.findViewById(R.id.ChatMessageTitleTextView);
        RelativeLayout relativeLayout = (RelativeLayout) viewInflate.findViewById(R.id.ChatMessageClickLayout);
        c6819c.f22638z = relativeLayout;
        c6819c.f22627o = relativeLayout;
        c6819c.f22629q = viewInflate.findViewById(R.id.scheduleSendMask);
        c6819c.f22627o = c6819c.f22615c;
        c6819c.f22628p = (TextView) viewInflate.findViewById(R.id.ttl);
        c6819c.f22626n = (ImageView) viewInflate.findViewById(R.id.explode);
        c6819c.f22593I = (ImageView) viewInflate.findViewById(R.id.ChatMessageSendFail);
        c6819c.f22594J = viewInflate.findViewById(R.id.sendingProgressbar);
        c6819c.f22596L = viewInflate.findViewById(R.id.itemCheckBox);
        return viewInflate;
    }

    /* renamed from: j */
    public static View m25454j(C6819c c6819c, LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View viewInflate = layoutInflater.inflate(R.layout.view_item_send_comment_msg, viewGroup, false);
        ImageView imageView = (ImageView) viewInflate.findViewById(R.id.ChatMessageContentPhotoView);
        c6819c.f22615c = imageView;
        imageView.setContentDescription("[AID]Chat_SendPhotoView");
        c6819c.f22598N = (ImageView) viewInflate.findViewById(R.id.ChatMessageContentPhotoPenView);
        c6819c.f22585A = (RelativeLayout) viewInflate.findViewById(R.id.voiceLayout);
        c6819c.f22587C = (TextView) viewInflate.findViewById(R.id.VoiceTimeTextView);
        c6819c.f22622j = (TextView) viewInflate.findViewById(R.id.ChatMessageReadCountTextView);
        c6819c.f22620h = (TextView) viewInflate.findViewById(R.id.ChatMessageContentTextView);
        c6819c.f22623k = (TextView) viewInflate.findViewById(R.id.ChatMessageTimeTextView);
        c6819c.f22638z = (RelativeLayout) viewInflate.findViewById(R.id.ChatMessageContentLayout2);
        c6819c.f22627o = viewInflate.findViewById(R.id.ChatMessageClickLayout);
        c6819c.f22629q = viewInflate.findViewById(R.id.scheduleSendMask);
        c6819c.f22628p = (TextView) viewInflate.findViewById(R.id.ttl);
        c6819c.f22626n = (ImageView) viewInflate.findViewById(R.id.explode);
        c6819c.f22593I = (ImageView) viewInflate.findViewById(R.id.ChatMessageSendFail);
        c6819c.f22594J = viewInflate.findViewById(R.id.sendingProgressbar);
        c6819c.f22596L = viewInflate.findViewById(R.id.itemCheckBox);
        c6819c.f22607W = (ImageView) viewInflate.findViewById(R.id.urlImage);
        c6819c.f22608X = (TextView) viewInflate.findViewById(R.id.urlTitle);
        c6819c.f22609Y = (TextView) viewInflate.findViewById(R.id.urlDescription);
        c6819c.f22610Z = (LinearLayout) viewInflate.findViewById(R.id.urlMultiPreview);
        return viewInflate;
    }

    /* renamed from: k */
    public static View m25455k(C6819c c6819c, LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View viewInflate = layoutInflater.inflate(R.layout.view_item_send_photo_msg, viewGroup, false);
        c6819c.f22622j = (TextView) viewInflate.findViewById(R.id.ChatMessageReadCountTextView);
        ImageView imageView = (ImageView) viewInflate.findViewById(R.id.ChatMessageContentPhotoView);
        c6819c.f22615c = imageView;
        imageView.setContentDescription("[AID]Chat_SendPhotoMsg");
        c6819c.f22623k = (TextView) viewInflate.findViewById(R.id.ChatMessageTimeTextView);
        ProgressBar progressBar = (ProgressBar) viewInflate.findViewById(R.id.progressBar);
        c6819c.f22624l = progressBar;
        progressBar.setMax(200);
        c6819c.f22592H = (TextView) viewInflate.findViewById(R.id.commentStringText);
        c6819c.f22591G = (RelativeLayout) viewInflate.findViewById(R.id.ChatPhotoCommentCntView);
        c6819c.f22629q = viewInflate.findViewById(R.id.scheduleSendMask);
        c6819c.f22627o = viewInflate.findViewById(R.id.contentLayout);
        c6819c.f22628p = (TextView) viewInflate.findViewById(R.id.ttl);
        c6819c.f22626n = (ImageView) viewInflate.findViewById(R.id.explode);
        c6819c.f22593I = (ImageView) viewInflate.findViewById(R.id.ChatMessageSendFail);
        c6819c.f22594J = viewInflate.findViewById(R.id.sendingProgressbar);
        c6819c.f22596L = viewInflate.findViewById(R.id.itemCheckBox);
        return viewInflate;
    }

    /* renamed from: l */
    public static View m25456l(C6819c c6819c, LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View viewInflate = layoutInflater.inflate(R.layout.view_item_send_photo_note_msg, viewGroup, false);
        c6819c.f22622j = (TextView) viewInflate.findViewById(R.id.ChatMessageReadCountTextView);
        ImageView imageView = (ImageView) viewInflate.findViewById(R.id.ChatMessageContentPhotoView);
        c6819c.f22615c = imageView;
        imageView.setContentDescription("[AID]Chat_SendPhotoNoteMsg");
        c6819c.f22623k = (TextView) viewInflate.findViewById(R.id.ChatMessageTimeTextView);
        c6819c.f22624l = (ProgressBar) viewInflate.findViewById(R.id.progressBar);
        c6819c.f22620h = (TextView) viewInflate.findViewById(R.id.ChatMessageContentTextView);
        c6819c.f22587C = (TextView) viewInflate.findViewById(R.id.VoiceTimeTextView);
        c6819c.f22636x = (RelativeLayout) viewInflate.findViewById(R.id.ChatMessageVoicePlayLayout);
        c6819c.f22637y = (RelativeLayout) viewInflate.findViewById(R.id.ChatMessageTextLayout);
        c6819c.f22592H = (TextView) viewInflate.findViewById(R.id.commentStringText);
        c6819c.f22591G = (RelativeLayout) viewInflate.findViewById(R.id.ChatPhotoCommentCntView);
        c6819c.f22638z = (RelativeLayout) viewInflate.findViewById(R.id.ChatMessageClickLayout);
        c6819c.f22629q = viewInflate.findViewById(R.id.scheduleSendMask);
        c6819c.f22627o = c6819c.f22615c;
        c6819c.f22628p = (TextView) viewInflate.findViewById(R.id.ttl);
        c6819c.f22626n = (ImageView) viewInflate.findViewById(R.id.explode);
        c6819c.f22593I = (ImageView) viewInflate.findViewById(R.id.ChatMessageSendFail);
        c6819c.f22594J = viewInflate.findViewById(R.id.sendingProgressbar);
        c6819c.f22596L = viewInflate.findViewById(R.id.itemCheckBox);
        c6819c.f22590F = (RelativeLayout) viewInflate.findViewById(R.id.ChatMessageNoteLayout);
        c6819c.f22589E = (ImageView) viewInflate.findViewById(R.id.ChatMessageNoteArrowLayout);
        return viewInflate;
    }

    /* renamed from: m */
    public static View m25457m(C6819c c6819c, LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View viewInflate = layoutInflater.inflate(R.layout.view_item_send_poll_msg, viewGroup, false);
        c6819c.f22596L = viewInflate.findViewById(R.id.itemCheckBox);
        c6819c.f22622j = (TextView) viewInflate.findViewById(R.id.ChatMessageReadCountTextView);
        c6819c.f22620h = (TextView) viewInflate.findViewById(R.id.ChatMessageContentTextView);
        c6819c.f22623k = (TextView) viewInflate.findViewById(R.id.ChatMessageTimeTextView);
        RelativeLayout relativeLayout = (RelativeLayout) viewInflate.findViewById(R.id.contentLayout);
        c6819c.f22638z = relativeLayout;
        relativeLayout.setContentDescription("[AID]Chat_SendPollMsg");
        c6819c.f22593I = (ImageView) viewInflate.findViewById(R.id.ChatMessageSendFail);
        c6819c.f22612a0 = (TextView) viewInflate.findViewById(R.id.textViewVote);
        c6819c.f22614b0 = (ImageView) viewInflate.findViewById(R.id.imageViewGoto);
        return viewInflate;
    }

    /* renamed from: n */
    public static View m25458n(C6819c c6819c, LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View viewInflate = layoutInflater.inflate(R.layout.view_item_send_pollpost_msg, viewGroup, false);
        c6819c.f22596L = viewInflate.findViewById(R.id.itemCheckBox);
        c6819c.f22622j = (TextView) viewInflate.findViewById(R.id.ChatMessageReadCountTextView);
        c6819c.f22620h = (TextView) viewInflate.findViewById(R.id.ChatMessageContentTextView);
        c6819c.f22623k = (TextView) viewInflate.findViewById(R.id.ChatMessageTimeTextView);
        c6819c.f22599O = (TextView) viewInflate.findViewById(R.id.reply);
        RelativeLayout relativeLayout = (RelativeLayout) viewInflate.findViewById(R.id.contentLayout);
        c6819c.f22638z = relativeLayout;
        relativeLayout.setContentDescription("[AID]Chat_SendPollPostMsg");
        c6819c.f22593I = (ImageView) viewInflate.findViewById(R.id.ChatMessageSendFail);
        return viewInflate;
    }

    /* renamed from: o */
    public static View m25459o(C6819c c6819c, LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View viewInflate = layoutInflater.inflate(R.layout.view_item_send_reply_text, viewGroup, false);
        c6819c.f22596L = viewInflate.findViewById(R.id.itemCheckBox);
        c6819c.f22622j = (TextView) viewInflate.findViewById(R.id.ChatMessageReadCountTextView);
        c6819c.f22620h = (TextView) viewInflate.findViewById(R.id.replyText);
        c6819c.f22599O = (TextView) viewInflate.findViewById(R.id.replyMessage);
        c6819c.f22600P = (TextView) viewInflate.findViewById(R.id.replyName);
        c6819c.f22623k = (TextView) viewInflate.findViewById(R.id.ChatMessageTimeTextView);
        RelativeLayout relativeLayout = (RelativeLayout) viewInflate.findViewById(R.id.contentLayout);
        c6819c.f22638z = relativeLayout;
        relativeLayout.setContentDescription("[AID]Chat_SendReplyTextMsg");
        c6819c.f22593I = (ImageView) viewInflate.findViewById(R.id.ChatMessageSendFail);
        c6819c.f22594J = viewInflate.findViewById(R.id.sendingProgressbar);
        c6819c.f22596L = viewInflate.findViewById(R.id.itemCheckBox);
        c6819c.f22629q = viewInflate.findViewById(R.id.scheduleSendMask);
        c6819c.f22628p = (TextView) viewInflate.findViewById(R.id.ttl);
        c6819c.f22607W = (ImageView) viewInflate.findViewById(R.id.urlImage);
        c6819c.f22608X = (TextView) viewInflate.findViewById(R.id.urlTitle);
        c6819c.f22609Y = (TextView) viewInflate.findViewById(R.id.urlDescription);
        c6819c.f22610Z = (LinearLayout) viewInflate.findViewById(R.id.urlMultiPreview);
        c6819c.f22601Q = (ImageView) viewInflate.findViewById(R.id.symbolleft);
        c6819c.f22602R = (ImageView) viewInflate.findViewById(R.id.symbolright);
        c6819c.f22618f = (ImageView) viewInflate.findViewById(R.id.fileIcon);
        return viewInflate;
    }

    /* renamed from: p */
    public static View m25460p(C6819c c6819c, LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View viewInflate = layoutInflater.inflate(R.layout.view_item_send_sticker_msg, viewGroup, false);
        c6819c.f22622j = (TextView) viewInflate.findViewById(R.id.ChatMessageReadCountTextView);
        ImageView imageView = (ImageView) viewInflate.findViewById(R.id.ChatMessageContentStickerView);
        c6819c.f22616d = imageView;
        imageView.setContentDescription("[AID]Chat_SendStickerMsg");
        c6819c.f22623k = (TextView) viewInflate.findViewById(R.id.ChatMessageTimeTextView);
        c6819c.f22629q = viewInflate.findViewById(R.id.scheduleSendMask);
        c6819c.f22627o = c6819c.f22616d;
        c6819c.f22628p = (TextView) viewInflate.findViewById(R.id.ttl);
        c6819c.f22626n = (ImageView) viewInflate.findViewById(R.id.explode);
        c6819c.f22593I = (ImageView) viewInflate.findViewById(R.id.ChatMessageSendFail);
        c6819c.f22594J = viewInflate.findViewById(R.id.sendingProgressbar);
        c6819c.f22596L = viewInflate.findViewById(R.id.itemCheckBox);
        return viewInflate;
    }

    /* renamed from: q */
    public static View m25461q(C6819c c6819c, LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View viewInflate = layoutInflater.inflate(R.layout.view_item_send_text_msg, viewGroup, false);
        c6819c.f22622j = (TextView) viewInflate.findViewById(R.id.ChatMessageReadCountTextView);
        TextView textView = (TextView) viewInflate.findViewById(R.id.ChatMessageContentTextView);
        c6819c.f22620h = textView;
        textView.setContentDescription("[AID]Chat_SendTextMsg");
        c6819c.f22621i = viewInflate.findViewById(R.id.TextContentArea);
        c6819c.f22623k = (TextView) viewInflate.findViewById(R.id.ChatMessageTimeTextView);
        c6819c.f22629q = viewInflate.findViewById(R.id.scheduleSendMask);
        c6819c.f22627o = viewInflate.findViewById(R.id.ChatMessageContentLayout2);
        c6819c.f22628p = (TextView) viewInflate.findViewById(R.id.ttl);
        c6819c.f22626n = (ImageView) viewInflate.findViewById(R.id.explode);
        c6819c.f22593I = (ImageView) viewInflate.findViewById(R.id.ChatMessageSendFail);
        c6819c.f22594J = viewInflate.findViewById(R.id.sendingProgressbar);
        c6819c.f22596L = viewInflate.findViewById(R.id.itemCheckBox);
        c6819c.f22607W = (ImageView) viewInflate.findViewById(R.id.urlImage);
        c6819c.f22608X = (TextView) viewInflate.findViewById(R.id.urlTitle);
        c6819c.f22609Y = (TextView) viewInflate.findViewById(R.id.urlDescription);
        c6819c.f22610Z = (LinearLayout) viewInflate.findViewById(R.id.urlMultiPreview);
        return viewInflate;
    }

    /* renamed from: r */
    public static View m25462r(C6819c c6819c, LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View viewInflate = layoutInflater.inflate(R.layout.view_item_send_user_contact_msg, viewGroup, false);
        c6819c.f22615c = (ImageView) viewInflate.findViewById(R.id.ChatMessageContentPhotoView);
        c6819c.f22622j = (TextView) viewInflate.findViewById(R.id.ChatMessageReadCountTextView);
        c6819c.f22620h = (TextView) viewInflate.findViewById(R.id.ChatMessageContentTextView);
        c6819c.f22623k = (TextView) viewInflate.findViewById(R.id.ChatMessageTimeTextView);
        RelativeLayout relativeLayout = (RelativeLayout) viewInflate.findViewById(R.id.ChatMessageClickLayout);
        c6819c.f22638z = relativeLayout;
        c6819c.f22627o = relativeLayout;
        c6819c.f22629q = viewInflate.findViewById(R.id.scheduleSendMask);
        c6819c.f22628p = (TextView) viewInflate.findViewById(R.id.ttl);
        c6819c.f22626n = (ImageView) viewInflate.findViewById(R.id.explode);
        c6819c.f22593I = (ImageView) viewInflate.findViewById(R.id.ChatMessageSendFail);
        c6819c.f22594J = viewInflate.findViewById(R.id.sendingProgressbar);
        c6819c.f22596L = viewInflate.findViewById(R.id.itemCheckBox);
        return viewInflate;
    }

    /* renamed from: s */
    public static View m25463s(C6819c c6819c, LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View viewInflate = layoutInflater.inflate(R.layout.view_item_send_video_msg, viewGroup, false);
        c6819c.f22622j = (TextView) viewInflate.findViewById(R.id.ChatMessageReadCountTextView);
        ImageView imageView = (ImageView) viewInflate.findViewById(R.id.ChatMessageContentPhotoView);
        c6819c.f22615c = imageView;
        imageView.setContentDescription("[AID]Chat_SendVideoMsg");
        c6819c.f22623k = (TextView) viewInflate.findViewById(R.id.ChatMessageTimeTextView);
        ProgressBar progressBar = (ProgressBar) viewInflate.findViewById(R.id.progressBar);
        c6819c.f22624l = progressBar;
        progressBar.setMax(200);
        c6819c.f22592H = (TextView) viewInflate.findViewById(R.id.commentStringText);
        c6819c.f22591G = (RelativeLayout) viewInflate.findViewById(R.id.ChatPhotoCommentCntView);
        c6819c.f22630r = (TextView) viewInflate.findViewById(R.id.duration);
        c6819c.f22629q = viewInflate.findViewById(R.id.scheduleSendMask);
        c6819c.f22627o = c6819c.f22615c;
        c6819c.f22628p = (TextView) viewInflate.findViewById(R.id.ttl);
        c6819c.f22626n = (ImageView) viewInflate.findViewById(R.id.explode);
        c6819c.f22593I = (ImageView) viewInflate.findViewById(R.id.ChatMessageSendFail);
        c6819c.f22594J = viewInflate.findViewById(R.id.sendingProgressbar);
        c6819c.f22596L = viewInflate.findViewById(R.id.itemCheckBox);
        return viewInflate;
    }
}
