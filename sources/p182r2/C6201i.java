package p182r2;

import com.cyberlink.you.database.MessageObj;
import p182r2.C6199g;

/* renamed from: r2.i */
/* loaded from: classes.dex */
public final class C6201i extends AbstractC6189a {

    /* renamed from: b */
    public final String f20883b;

    /* renamed from: c */
    public final MessageObj f20884c;

    /* renamed from: d */
    public final boolean f20885d;

    /* renamed from: e */
    public final boolean f20886e;

    /* renamed from: f */
    public boolean f20887f;

    /* renamed from: g */
    public C6199g.a f20888g;

    /* renamed from: r2.i$a */
    public static /* synthetic */ class a {

        /* renamed from: a */
        public static final /* synthetic */ int[] f20889a;

        static {
            int[] iArr = new int[MessageObj.MessageType.values().length];
            f20889a = iArr;
            try {
                iArr[MessageObj.MessageType.Text.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f20889a[MessageObj.MessageType.Sticker.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f20889a[MessageObj.MessageType.AnimSticker.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f20889a[MessageObj.MessageType.AnimPngSticker.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f20889a[MessageObj.MessageType.Photo.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f20889a[MessageObj.MessageType.File.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f20889a[MessageObj.MessageType.ReplyText.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f20889a[MessageObj.MessageType.CreateMedia.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f20889a[MessageObj.MessageType.DeleteMedia.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f20889a[MessageObj.MessageType.VideoDelete.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f20889a[MessageObj.MessageType.FileDelete.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
        }
    }

    public C6201i(String str, MessageObj messageObj) {
        super(messageObj.m14777o());
        this.f20887f = false;
        this.f20883b = str;
        this.f20884c = messageObj;
        MessageObj.MessageType messageTypeM14778p = messageObj.m14778p();
        this.f20885d = m23717c(messageTypeM14778p);
        this.f20886e = m23716b(messageTypeM14778p);
    }

    /* renamed from: b */
    public static boolean m23716b(MessageObj.MessageType messageType) {
        switch (a.f20889a[messageType.ordinal()]) {
            case 8:
            case 9:
            case 10:
            case 11:
                return true;
            default:
                return false;
        }
    }

    /* renamed from: c */
    public static boolean m23717c(MessageObj.MessageType messageType) {
        switch (a.f20889a[messageType.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                return true;
            default:
                return m23716b(messageType);
        }
    }

    /* renamed from: a */
    public C6199g.a m23718a() {
        return this.f20888g;
    }

    /* renamed from: d */
    public void m23719d(C6199g.a aVar) {
        this.f20888g = aVar;
    }

    /* renamed from: e */
    public void m23720e(boolean z8) {
        this.f20887f = z8;
    }

    /* renamed from: f */
    public boolean m23721f() {
        MessageObj.MessageType messageTypeM14778p = this.f20884c.m14778p();
        return messageTypeM14778p.equals(MessageObj.MessageType.Photo) || messageTypeM14778p.equals(MessageObj.MessageType.PhotoNote) || messageTypeM14778p.equals(MessageObj.MessageType.Audio) || messageTypeM14778p.equals(MessageObj.MessageType.Video) || messageTypeM14778p.equals(MessageObj.MessageType.File) || messageTypeM14778p.equals(MessageObj.MessageType.ShareLocation);
    }
}
