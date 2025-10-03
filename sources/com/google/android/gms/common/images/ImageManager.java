package com.google.android.gms.common.images;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.util.Log;
import android.widget.ImageView;
import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.common.internal.Constants;
import com.google.android.gms.internal.base.zaj;
import com.google.android.gms.internal.base.zal;
import com.google.android.gms.internal.base.zap;
import com.google.android.gms.internal.base.zaq;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import org.apache.commons.lang3.time.DateUtils;
import p132m.C5306e;

/* loaded from: classes2.dex */
public final class ImageManager {
    private static final Object zaa = new Object();
    private static HashSet<Uri> zab = new HashSet<>();
    private static ImageManager zac;
    private final Context zad;
    private final Handler zae = new zap(Looper.getMainLooper());
    private final ExecutorService zaf = zal.zaa().zaa(4, zaq.zab);
    private final zaa zag = null;
    private final zaj zah = new zaj();
    private final Map<com.google.android.gms.common.images.zab, ImageReceiver> zai = new HashMap();
    private final Map<Uri, ImageReceiver> zaj = new HashMap();
    private final Map<Uri, Long> zak = new HashMap();

    public interface OnImageLoadedListener {
        void onImageLoaded(@RecentlyNonNull Uri uri, Drawable drawable, @RecentlyNonNull boolean z8);
    }

    public static final class zaa extends C5306e<com.google.android.gms.common.images.zaa, Bitmap> {
        @Override // p132m.C5306e
        public final /* synthetic */ void entryRemoved(boolean z8, com.google.android.gms.common.images.zaa zaaVar, Bitmap bitmap, Bitmap bitmap2) {
            throw new NoSuchMethodError();
        }

        @Override // p132m.C5306e
        public final /* synthetic */ int sizeOf(com.google.android.gms.common.images.zaa zaaVar, Bitmap bitmap) {
            throw new NoSuchMethodError();
        }
    }

    public final class zab implements Runnable {
        private final com.google.android.gms.common.images.zab zaa;

        public zab(com.google.android.gms.common.images.zab zabVar) {
            this.zaa = zabVar;
        }

        @Override // java.lang.Runnable
        public final void run() {
            Asserts.checkMainThread("LoadImageRunnable must be executed on the main thread");
            ImageReceiver imageReceiver = (ImageReceiver) ImageManager.this.zai.get(this.zaa);
            if (imageReceiver != null) {
                ImageManager.this.zai.remove(this.zaa);
                imageReceiver.zab(this.zaa);
            }
            com.google.android.gms.common.images.zab zabVar = this.zaa;
            com.google.android.gms.common.images.zaa zaaVar = zabVar.zaa;
            if (zaaVar.zaa == null) {
                zabVar.zaa(ImageManager.this.zad, ImageManager.this.zah, true);
                return;
            }
            Bitmap bitmapZaa = ImageManager.this.zaa(zaaVar);
            if (bitmapZaa != null) {
                this.zaa.zaa(ImageManager.this.zad, bitmapZaa, true);
                return;
            }
            Long l9 = (Long) ImageManager.this.zak.get(zaaVar.zaa);
            if (l9 != null) {
                if (SystemClock.elapsedRealtime() - l9.longValue() < DateUtils.MILLIS_PER_HOUR) {
                    this.zaa.zaa(ImageManager.this.zad, ImageManager.this.zah, true);
                    return;
                }
                ImageManager.this.zak.remove(zaaVar.zaa);
            }
            this.zaa.zaa(ImageManager.this.zad, ImageManager.this.zah);
            ImageReceiver imageReceiver2 = (ImageReceiver) ImageManager.this.zaj.get(zaaVar.zaa);
            if (imageReceiver2 == null) {
                imageReceiver2 = ImageManager.this.new ImageReceiver(zaaVar.zaa);
                ImageManager.this.zaj.put(zaaVar.zaa, imageReceiver2);
            }
            imageReceiver2.zaa(this.zaa);
            if (!(this.zaa instanceof com.google.android.gms.common.images.zac)) {
                ImageManager.this.zai.put(this.zaa, imageReceiver2);
            }
            synchronized (ImageManager.zaa) {
                if (!ImageManager.zab.contains(zaaVar.zaa)) {
                    ImageManager.zab.add(zaaVar.zaa);
                    imageReceiver2.zaa();
                }
            }
        }
    }

    public final class zac implements Runnable {
        private final Uri zaa;
        private final ParcelFileDescriptor zab;

        public zac(Uri uri, ParcelFileDescriptor parcelFileDescriptor) {
            this.zaa = uri;
            this.zab = parcelFileDescriptor;
        }

        @Override // java.lang.Runnable
        public final void run() throws InterruptedException, IOException {
            Asserts.checkNotMainThread("LoadBitmapFromDiskRunnable can't be executed in the main thread");
            ParcelFileDescriptor parcelFileDescriptor = this.zab;
            boolean z8 = false;
            Bitmap bitmapDecodeFileDescriptor = null;
            if (parcelFileDescriptor != null) {
                try {
                    bitmapDecodeFileDescriptor = BitmapFactory.decodeFileDescriptor(parcelFileDescriptor.getFileDescriptor());
                } catch (OutOfMemoryError e9) {
                    String strValueOf = String.valueOf(this.zaa);
                    StringBuilder sb = new StringBuilder(strValueOf.length() + 34);
                    sb.append("OOM while loading bitmap for uri: ");
                    sb.append(strValueOf);
                    Log.e("ImageManager", sb.toString(), e9);
                    z8 = true;
                }
                try {
                    this.zab.close();
                } catch (IOException e10) {
                    Log.e("ImageManager", "closed failed", e10);
                }
            }
            CountDownLatch countDownLatch = new CountDownLatch(1);
            ImageManager.this.zae.post(ImageManager.this.new zad(this.zaa, bitmapDecodeFileDescriptor, z8, countDownLatch));
            try {
                countDownLatch.await();
            } catch (InterruptedException unused) {
                String strValueOf2 = String.valueOf(this.zaa);
                StringBuilder sb2 = new StringBuilder(strValueOf2.length() + 32);
                sb2.append("Latch interrupted while posting ");
                sb2.append(strValueOf2);
                Log.w("ImageManager", sb2.toString());
            }
        }
    }

    public final class zad implements Runnable {
        private final Uri zaa;
        private final Bitmap zab;
        private final CountDownLatch zac;
        private boolean zad;

        public zad(Uri uri, Bitmap bitmap, boolean z8, CountDownLatch countDownLatch) {
            this.zaa = uri;
            this.zab = bitmap;
            this.zad = z8;
            this.zac = countDownLatch;
        }

        @Override // java.lang.Runnable
        public final void run() {
            Asserts.checkMainThread("OnBitmapLoadedRunnable must be executed in the main thread");
            boolean z8 = this.zab != null;
            ImageReceiver imageReceiver = (ImageReceiver) ImageManager.this.zaj.remove(this.zaa);
            if (imageReceiver != null) {
                ArrayList arrayList = imageReceiver.zab;
                int size = arrayList.size();
                for (int i9 = 0; i9 < size; i9++) {
                    com.google.android.gms.common.images.zab zabVar = (com.google.android.gms.common.images.zab) arrayList.get(i9);
                    if (this.zab == null || !z8) {
                        ImageManager.this.zak.put(this.zaa, Long.valueOf(SystemClock.elapsedRealtime()));
                        zabVar.zaa(ImageManager.this.zad, ImageManager.this.zah, false);
                    } else {
                        zabVar.zaa(ImageManager.this.zad, this.zab, false);
                    }
                    if (!(zabVar instanceof com.google.android.gms.common.images.zac)) {
                        ImageManager.this.zai.remove(zabVar);
                    }
                }
            }
            this.zac.countDown();
            synchronized (ImageManager.zaa) {
                ImageManager.zab.remove(this.zaa);
            }
        }
    }

    private ImageManager(Context context, boolean z8) {
        this.zad = context.getApplicationContext();
    }

    @RecentlyNonNull
    public static ImageManager create(@RecentlyNonNull Context context) {
        if (zac == null) {
            zac = new ImageManager(context, false);
        }
        return zac;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Bitmap zaa(com.google.android.gms.common.images.zaa zaaVar) {
        return null;
    }

    private final void zaa(com.google.android.gms.common.images.zab zabVar) {
        Asserts.checkMainThread("ImageManager.loadImage() must be called in the main thread");
        new zab(zabVar).run();
    }

    public final void loadImage(@RecentlyNonNull ImageView imageView, @RecentlyNonNull Uri uri) {
        zaa(new com.google.android.gms.common.images.zad(imageView, uri));
    }

    @KeepName
    public final class ImageReceiver extends ResultReceiver {
        private final Uri zaa;
        private final ArrayList<com.google.android.gms.common.images.zab> zab;

        public ImageReceiver(Uri uri) {
            super(new zap(Looper.getMainLooper()));
            this.zaa = uri;
            this.zab = new ArrayList<>();
        }

        @Override // android.os.ResultReceiver
        public final void onReceiveResult(int i9, Bundle bundle) {
            ImageManager.this.zaf.execute(ImageManager.this.new zac(this.zaa, (ParcelFileDescriptor) bundle.getParcelable("com.google.android.gms.extra.fileDescriptor")));
        }

        public final void zaa(com.google.android.gms.common.images.zab zabVar) {
            Asserts.checkMainThread("ImageReceiver.addImageRequest() must be called in the main thread");
            this.zab.add(zabVar);
        }

        public final void zab(com.google.android.gms.common.images.zab zabVar) {
            Asserts.checkMainThread("ImageReceiver.removeImageRequest() must be called in the main thread");
            this.zab.remove(zabVar);
        }

        public final void zaa() {
            Intent intent = new Intent(Constants.ACTION_LOAD_IMAGE);
            intent.setPackage("com.google.android.gms");
            intent.putExtra(Constants.EXTRA_URI, this.zaa);
            intent.putExtra(Constants.EXTRA_RESULT_RECEIVER, this);
            intent.putExtra(Constants.EXTRA_PRIORITY, 3);
            ImageManager.this.zad.sendBroadcast(intent);
        }
    }

    public final void loadImage(@RecentlyNonNull ImageView imageView, @RecentlyNonNull int i9) {
        zaa(new com.google.android.gms.common.images.zad(imageView, i9));
    }

    public final void loadImage(@RecentlyNonNull ImageView imageView, @RecentlyNonNull Uri uri, @RecentlyNonNull int i9) {
        com.google.android.gms.common.images.zad zadVar = new com.google.android.gms.common.images.zad(imageView, uri);
        zadVar.zab = i9;
        zaa(zadVar);
    }

    public final void loadImage(@RecentlyNonNull OnImageLoadedListener onImageLoadedListener, @RecentlyNonNull Uri uri) {
        zaa(new com.google.android.gms.common.images.zac(onImageLoadedListener, uri));
    }

    public final void loadImage(@RecentlyNonNull OnImageLoadedListener onImageLoadedListener, @RecentlyNonNull Uri uri, @RecentlyNonNull int i9) {
        com.google.android.gms.common.images.zac zacVar = new com.google.android.gms.common.images.zac(onImageLoadedListener, uri);
        zacVar.zab = i9;
        zaa(zacVar);
    }
}
