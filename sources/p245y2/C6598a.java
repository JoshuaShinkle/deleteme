package p245y2;

import com.cyberlink.you.database.MessageObj;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import p116k4.C5170o0;
import p254z2.C6818b;

/* renamed from: y2.a */
/* loaded from: classes.dex */
public class C6598a {
    /* renamed from: a */
    public static int m25239a(MessageObj messageObj) {
        if (messageObj == null) {
            return 47;
        }
        boolean zM25402m = C6818b.m25402m(messageObj);
        if (messageObj.m14778p() == MessageObj.MessageType.Event) {
            String strM14747I = messageObj.m14747I("statusV2");
            if (C5170o0.m20170e(strM14747I)) {
                messageObj.m14747I("status");
            }
            if ("meeting".equals(strM14747I) || TtmlNode.END.equals(strM14747I)) {
                return zM25402m ? 15 : 40;
            }
            return 48;
        }
        if (messageObj.m14778p() == MessageObj.MessageType.Date) {
            return 47;
        }
        if (messageObj.m14778p() == MessageObj.MessageType.DeleteMedia) {
            return 49;
        }
        if (messageObj.m14778p() == MessageObj.MessageType.UnreadLine) {
            return 50;
        }
        if (messageObj.m14778p() == MessageObj.MessageType.VideoDelete) {
            return 51;
        }
        if (messageObj.m14778p() == MessageObj.MessageType.FileDelete) {
            return 52;
        }
        if (zM25402m) {
            if (messageObj.m14778p() == MessageObj.MessageType.Text) {
                return messageObj.m14749K() ? 20 : 0;
            }
            if (messageObj.m14778p() == MessageObj.MessageType.Sticker) {
                return 1;
            }
            if (messageObj.m14778p() == MessageObj.MessageType.AnimSticker) {
                return 2;
            }
            if (messageObj.m14778p() == MessageObj.MessageType.AnimPngSticker) {
                return 4;
            }
            if (messageObj.m14778p() == MessageObj.MessageType.Comment) {
                return 5;
            }
            if (messageObj.m14778p() == MessageObj.MessageType.CreateMedia) {
                return 6;
            }
            if (messageObj.m14778p() == MessageObj.MessageType.CommentUpdate) {
                return 7;
            }
            if (messageObj.m14778p() == MessageObj.MessageType.PhotoNote) {
                return 8;
            }
            if (messageObj.m14778p() == MessageObj.MessageType.Audio) {
                return 9;
            }
            if (messageObj.m14778p() == MessageObj.MessageType.UserContact) {
                return 10;
            }
            if (messageObj.m14778p() == MessageObj.MessageType.StickerTypeUnknown) {
                return 11;
            }
            if (messageObj.m14778p() == MessageObj.MessageType.Video) {
                return 12;
            }
            if (messageObj.m14778p() == MessageObj.MessageType.File) {
                return 13;
            }
            if (messageObj.m14778p() == MessageObj.MessageType.ShareLocation) {
                return 14;
            }
            if (messageObj.m14778p() == MessageObj.MessageType.Call) {
                return 15;
            }
            if (messageObj.m14778p() == MessageObj.MessageType.Poll) {
                return 16;
            }
            if (messageObj.m14778p() == MessageObj.MessageType.PollPost) {
                return 17;
            }
            if (messageObj.m14778p() == MessageObj.MessageType.ReplyText) {
                return 18;
            }
            if (messageObj.m14778p() == MessageObj.MessageType.AnnouncementType02) {
                return 35;
            }
            if (messageObj.m14778p() == MessageObj.MessageType.ENCRYPTED_MSG) {
                return 19;
            }
            return messageObj.m14778p() == MessageObj.MessageType.NewVersion ? 39 : 3;
        }
        if (messageObj.m14778p() == MessageObj.MessageType.Text) {
            return messageObj.m14749K() ? 46 : 21;
        }
        if (messageObj.m14778p() == MessageObj.MessageType.Sticker) {
            return 22;
        }
        if (messageObj.m14778p() == MessageObj.MessageType.AnimSticker) {
            return 23;
        }
        if (messageObj.m14778p() == MessageObj.MessageType.AnimPngSticker) {
            return 25;
        }
        if (messageObj.m14778p() == MessageObj.MessageType.Comment) {
            return 26;
        }
        if (messageObj.m14778p() == MessageObj.MessageType.CreateMedia) {
            return 27;
        }
        if (messageObj.m14778p() == MessageObj.MessageType.CommentUpdate) {
            return 28;
        }
        if (messageObj.m14778p() == MessageObj.MessageType.PhotoNote) {
            return 29;
        }
        if (messageObj.m14778p() == MessageObj.MessageType.Audio) {
            return 30;
        }
        if (messageObj.m14778p() == MessageObj.MessageType.UserContact) {
            return 31;
        }
        if (messageObj.m14778p() == MessageObj.MessageType.StickerTypeUnknown) {
            return 32;
        }
        if (messageObj.m14778p() == MessageObj.MessageType.AnnouncementType01) {
            return 34;
        }
        if (messageObj.m14778p() == MessageObj.MessageType.AnnouncementType02) {
            return 35;
        }
        if (messageObj.m14778p() == MessageObj.MessageType.AnnouncementType03) {
            return 36;
        }
        if (messageObj.m14778p() == MessageObj.MessageType.Video) {
            return 33;
        }
        if (messageObj.m14778p() == MessageObj.MessageType.File) {
            return 37;
        }
        if (messageObj.m14778p() == MessageObj.MessageType.ShareLocation) {
            return 38;
        }
        if (messageObj.m14778p() == MessageObj.MessageType.Call) {
            return 40;
        }
        if (messageObj.m14778p() == MessageObj.MessageType.Poll) {
            return 41;
        }
        if (messageObj.m14778p() == MessageObj.MessageType.PollPost) {
            return 42;
        }
        if (messageObj.m14778p() == MessageObj.MessageType.ReplyText) {
            return 43;
        }
        if (messageObj.m14778p() == MessageObj.MessageType.ReportToAdmin) {
            return 45;
        }
        if (messageObj.m14778p() == MessageObj.MessageType.NewVersion) {
            return 39;
        }
        return messageObj.m14778p() == MessageObj.MessageType.ENCRYPTED_MSG ? 44 : 24;
    }

    /* renamed from: b */
    public static boolean m25240b(int i9) {
        return i9 == 13 || i9 == 37;
    }

    /* renamed from: c */
    public static boolean m25241c(int i9) {
        return i9 == 20 || i9 == 46;
    }

    /* renamed from: d */
    public static boolean m25242d(int i9) {
        return i9 == 17 || i9 == 42;
    }

    /* renamed from: e */
    public static boolean m25243e(int i9) {
        return i9 == 18 || i9 == 43;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x000b  */
    /* renamed from: f */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean m25244f(int i9, int i10) {
        boolean z8 = true;
        if (i9 == 0) {
            if (i10 < 0 || i10 >= 21) {
                z8 = false;
            }
        } else if (21 == i9) {
            if (i10 < 21 || i10 >= 47) {
            }
        } else {
            if (47 != i9) {
                return false;
            }
            if (i10 < 47) {
            }
        }
        return z8;
    }
}
