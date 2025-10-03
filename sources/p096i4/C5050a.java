package p096i4;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.cyberlink.p030U.R;
import com.cyberlink.you.sticker.Emoji.EmojiCategory;
import com.rockerhieu.emojicon.EmojiconHandler;
import com.rockerhieu.emojicon.emoji.Emojicon;
import com.rockerhieu.emojicon.emoji.Nature;
import com.rockerhieu.emojicon.emoji.Objects;
import com.rockerhieu.emojicon.emoji.People;
import com.rockerhieu.emojicon.emoji.Places;
import com.rockerhieu.emojicon.emoji.Symbols;

/* renamed from: i4.a */
/* loaded from: classes.dex */
public class C5050a {
    /* renamed from: a */
    public static Bitmap m19748a(Context context, EmojiCategory emojiCategory) {
        if (emojiCategory.equals(EmojiCategory.People)) {
            return BitmapFactory.decodeResource(context.getResources(), R.drawable.orca_emoji_category_people);
        }
        if (emojiCategory.equals(EmojiCategory.Nature)) {
            return BitmapFactory.decodeResource(context.getResources(), R.drawable.orca_emoji_category_nature);
        }
        if (emojiCategory.equals(EmojiCategory.Objects)) {
            return BitmapFactory.decodeResource(context.getResources(), R.drawable.orca_emoji_category_objects);
        }
        if (emojiCategory.equals(EmojiCategory.Cars)) {
            return BitmapFactory.decodeResource(context.getResources(), R.drawable.orca_emoji_category_cars);
        }
        if (emojiCategory.equals(EmojiCategory.Punctuation)) {
            return BitmapFactory.decodeResource(context.getResources(), R.drawable.orca_emoji_category_punctuation);
        }
        return null;
    }

    /* renamed from: b */
    public static Emojicon[] m19749b(EmojiCategory emojiCategory) {
        if (emojiCategory.equals(EmojiCategory.People)) {
            return People.DATA;
        }
        if (emojiCategory.equals(EmojiCategory.Nature)) {
            return Nature.DATA;
        }
        if (emojiCategory.equals(EmojiCategory.Objects)) {
            return Objects.DATA;
        }
        if (emojiCategory.equals(EmojiCategory.Cars)) {
            return Places.DATA;
        }
        if (emojiCategory.equals(EmojiCategory.Punctuation)) {
            return Symbols.DATA;
        }
        return null;
    }

    /* renamed from: c */
    public static boolean m19750c(Context context, String str) {
        return (context == null || EmojiconHandler.getOtherEmojiResource(context, str) == 0) ? false : true;
    }
}
