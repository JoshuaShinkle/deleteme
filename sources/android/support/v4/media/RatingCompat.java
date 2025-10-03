package android.support.v4.media;

import android.media.Rating;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public final class RatingCompat implements Parcelable {
    public static final Parcelable.Creator<RatingCompat> CREATOR = new Parcelable.Creator<RatingCompat>() { // from class: android.support.v4.media.RatingCompat.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RatingCompat createFromParcel(Parcel parcel) {
            return new RatingCompat(parcel.readInt(), parcel.readFloat());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RatingCompat[] newArray(int i9) {
            return new RatingCompat[i9];
        }
    };
    public static final int RATING_3_STARS = 3;
    public static final int RATING_4_STARS = 4;
    public static final int RATING_5_STARS = 5;
    public static final int RATING_HEART = 1;
    public static final int RATING_NONE = 0;
    private static final float RATING_NOT_RATED = -1.0f;
    public static final int RATING_PERCENTAGE = 6;
    public static final int RATING_THUMB_UP_DOWN = 2;
    private static final String TAG = "Rating";
    private Object mRatingObj;
    private final int mRatingStyle;
    private final float mRatingValue;

    @Retention(RetentionPolicy.SOURCE)
    public @interface StarStyle {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Style {
    }

    public RatingCompat(int i9, float f9) {
        this.mRatingStyle = i9;
        this.mRatingValue = f9;
    }

    public static RatingCompat fromRating(Object obj) {
        RatingCompat ratingCompatNewUnratedRating = null;
        if (obj != null) {
            Rating rating = (Rating) obj;
            int ratingStyle = rating.getRatingStyle();
            if (rating.isRated()) {
                switch (ratingStyle) {
                    case 1:
                        ratingCompatNewUnratedRating = newHeartRating(rating.hasHeart());
                        break;
                    case 2:
                        ratingCompatNewUnratedRating = newThumbRating(rating.isThumbUp());
                        break;
                    case 3:
                    case 4:
                    case 5:
                        ratingCompatNewUnratedRating = newStarRating(ratingStyle, rating.getStarRating());
                        break;
                    case 6:
                        ratingCompatNewUnratedRating = newPercentageRating(rating.getPercentRating());
                        break;
                    default:
                        return null;
                }
            } else {
                ratingCompatNewUnratedRating = newUnratedRating(ratingStyle);
            }
            ratingCompatNewUnratedRating.mRatingObj = obj;
        }
        return ratingCompatNewUnratedRating;
    }

    public static RatingCompat newHeartRating(boolean z8) {
        return new RatingCompat(1, z8 ? 1.0f : BitmapDescriptorFactory.HUE_RED);
    }

    public static RatingCompat newPercentageRating(float f9) {
        if (f9 >= BitmapDescriptorFactory.HUE_RED && f9 <= 100.0f) {
            return new RatingCompat(6, f9);
        }
        Log.e(TAG, "Invalid percentage-based rating value");
        return null;
    }

    public static RatingCompat newStarRating(int i9, float f9) {
        float f10;
        if (i9 == 3) {
            f10 = 3.0f;
        } else if (i9 == 4) {
            f10 = 4.0f;
        } else {
            if (i9 != 5) {
                Log.e(TAG, "Invalid rating style (" + i9 + ") for a star rating");
                return null;
            }
            f10 = 5.0f;
        }
        if (f9 >= BitmapDescriptorFactory.HUE_RED && f9 <= f10) {
            return new RatingCompat(i9, f9);
        }
        Log.e(TAG, "Trying to set out of range star-based rating");
        return null;
    }

    public static RatingCompat newThumbRating(boolean z8) {
        return new RatingCompat(2, z8 ? 1.0f : BitmapDescriptorFactory.HUE_RED);
    }

    public static RatingCompat newUnratedRating(int i9) {
        switch (i9) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                return new RatingCompat(i9, -1.0f);
            default:
                return null;
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return this.mRatingStyle;
    }

    public float getPercentRating() {
        if (this.mRatingStyle == 6 && isRated()) {
            return this.mRatingValue;
        }
        return -1.0f;
    }

    public Object getRating() {
        if (this.mRatingObj == null) {
            if (isRated()) {
                int i9 = this.mRatingStyle;
                switch (i9) {
                    case 1:
                        this.mRatingObj = Rating.newHeartRating(hasHeart());
                        break;
                    case 2:
                        this.mRatingObj = Rating.newThumbRating(isThumbUp());
                        break;
                    case 3:
                    case 4:
                    case 5:
                        this.mRatingObj = Rating.newStarRating(i9, getStarRating());
                        break;
                    case 6:
                        this.mRatingObj = Rating.newPercentageRating(getPercentRating());
                        break;
                    default:
                        return null;
                }
            } else {
                this.mRatingObj = Rating.newUnratedRating(this.mRatingStyle);
            }
        }
        return this.mRatingObj;
    }

    public int getRatingStyle() {
        return this.mRatingStyle;
    }

    public float getStarRating() {
        int i9 = this.mRatingStyle;
        if ((i9 == 3 || i9 == 4 || i9 == 5) && isRated()) {
            return this.mRatingValue;
        }
        return -1.0f;
    }

    public boolean hasHeart() {
        return this.mRatingStyle == 1 && this.mRatingValue == 1.0f;
    }

    public boolean isRated() {
        return this.mRatingValue >= BitmapDescriptorFactory.HUE_RED;
    }

    public boolean isThumbUp() {
        return this.mRatingStyle == 2 && this.mRatingValue == 1.0f;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Rating:style=");
        sb.append(this.mRatingStyle);
        sb.append(" rating=");
        float f9 = this.mRatingValue;
        sb.append(f9 < BitmapDescriptorFactory.HUE_RED ? "unrated" : String.valueOf(f9));
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i9) {
        parcel.writeInt(this.mRatingStyle);
        parcel.writeFloat(this.mRatingValue);
    }
}
