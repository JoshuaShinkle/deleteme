package com.cyberlink.you.chat.p035p;

import com.cyberlink.you.chat.p035p.MetaExtension;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.gms.plus.PlusShare;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.Constants;
import org.jivesoftware.smackx.carbons.provider.CarbonManagerProvider;
import org.jivesoftware.smackx.delay.provider.DelayInfoProvider;
import org.jivesoftware.smackx.forward.provider.ForwardedProvider;
import org.jivesoftware.smackx.receipts.DeliveryReceipt;
import org.jivesoftware.smackx.receipts.DeliveryReceiptRequest;
import p008a7.C0054h;

/* renamed from: com.cyberlink.you.chat.p.a */
/* loaded from: classes.dex */
public final class C2913a {
    /* renamed from: a */
    public static void m14540a() {
        C0054h.m185a("media", "U", new MediaExtensionProvider());
        C0054h.m185a("sticker", "U", new StickerExtensionProvider());
        C0054h.m185a("request", new DeliveryReceiptRequest().getNamespace(), new DeliveryReceiptRequest.Provider());
        C0054h.m185a("received", "urn:xmpp:receipts", new DeliveryReceipt.Provider());
        C0054h.m185a("delay", "urn:xmpp:delay", new DelayInfoProvider());
        C0054h.m185a("forwarded", "urn:xmpp:forward:0", new ForwardedProvider());
        C0054h.m185a("sent", "urn:xmpp:carbons:2", new CarbonManagerProvider());
        C0054h.m185a("received", "urn:xmpp:carbons:2", new CarbonManagerProvider());
        C0054h.m185a("result", "urn:xmpp:mam:tmp", new ResultExtensionProvider("urn:xmpp:mam:tmp"));
        C0054h.m185a("result", "urn:xmpp:mam:0", new ResultExtensionProvider("urn:xmpp:mam:0"));
        C0054h.m185a("result", "urn:xmpp:mam:3", new ResultExtensionProvider("urn:xmpp:mam:3"));
        C0054h.m185a("message", "urn:xmpp:forward:0", new ArchiveMsgExtensionProvider());
        C0054h.m185a("event", "urn:xmpp:custom:event", new EventExtensionProvider());
        C0054h.m185a(Constants.FirelogAnalytics.PARAM_TTL, "U", new TTLExtensionProvider());
        C0054h.m185a("schedule", "U", new ScheduleExtensionProvider());
        C0054h.m185a("photoComment", "U", new CommentExtensionProvider());
        C0054h.m185a("doodleComment", "U", new DoodleCommentExtensionProvider());
        C0054h.m185a("groupAlbum", "U", new GroupAlbumExtensionProvider());
        C0054h.m185a("photoDelete", "U", new PhotoDeleteExtensionProvider());
        C0054h.m185a("photoCommentUpdate", "U", new CommentUpdateExtensionProvider());
        C0054h.m185a(MimeTypes.BASE_TYPE_AUDIO, "U", new AudioExtensionProvider());
        C0054h.m185a("userContact", "urn:xmpp:contact:0", new UserContactExtensionProvider());
        C0054h.m185a("announcement", "urn:xmpp:announcement:0", new AnnouncementExtensionProvider());
        C0054h.m185a("image", "urn:xmpp:announcement:image:0", new AnnouncementImageExtensionProvider());
        C0054h.m185a(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION, "urn:xmpp:announcement:description:0", new AnnouncementDescriptionExtensionProvider());
        C0054h.m185a(MimeTypes.BASE_TYPE_VIDEO, "U", new VideoExtensionProvider());
        C0054h.m185a(MimeTypes.BASE_TYPE_VIDEO, "urn:xmpp:video:0", new VideoExtensionProvider());
        C0054h.m185a(FirebaseAnalytics.Param.LOCATION, "urn:xmpp:location:0", new LocationExtensionProvider());
        C0054h.m185a("file", "U", new FileExtensionProvider());
        C0054h.m185a("call", "urn:xmpp:call:0", new CallExtensionProvider());
        C0054h.m185a("poll", "U", new PollExtensionProvider());
        C0054h.m185a("pollPost", "U", new PollPostExtensionProvider());
        C0054h.m185a("reportToAdmin", "urn:xmpp:reportadmin:0", new ReportToAdminExtensionProvider());
        C0054h.m185a("textReply", "urn:xmpp:textreply:0", new ReplyMessageExtensionProvider());
        C0054h.m185a("videoDelete", "U", new VideoDeleteExtensionProvider());
        C0054h.m185a("fileDelete", "U", new FileDeleteExtensionProvider());
        C0054h.m185a("encrypted", "U", new EncryptedExtensionProvider());
        C0054h.m185a("meta", "urn:xmpp:forward:0", new MetaExtension.Provider());
        C0054h.m185a("meta", "jabber:client", new MetaExtension.Provider());
        C0054h.m185a("info", "U", new ForwardInfoExtensionProvider());
        C0054h.m185a("broadcast", "U", new BroadcasterExtensionProvider());
        C0054h.m185a("broadcast", "jabber:client", new BroadcasterExtensionProvider());
    }
}
