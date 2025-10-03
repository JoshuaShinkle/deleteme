package p025c4;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import com.cyberlink.p030U.R;
import com.cyberlink.you.pages.photoimport.ImageItem;
import com.cyberlink.you.pages.photoimport.VideoItem;
import com.cyberlink.you.utility.CLUtility;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import p025c4.C0726d;

/* renamed from: c4.d */
/* loaded from: classes.dex */
public class C0726d {

    /* renamed from: g */
    public static final Uri f3419g = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

    /* renamed from: h */
    public static final Uri f3420h = MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI;

    /* renamed from: a */
    public Context f3421a;

    /* renamed from: b */
    public ContentResolver f3422b;

    /* renamed from: c */
    public String f3423c = "DESC";

    /* renamed from: d */
    public final int f3424d = 3;

    /* renamed from: e */
    public final int f3425e = 2;

    /* renamed from: f */
    public final int f3426f = 1;

    /* renamed from: c4.d$b */
    public static class b {

        /* renamed from: a */
        public String f3427a;

        /* renamed from: b */
        public long f3428b = 0;

        /* renamed from: c */
        public long f3429c = -1;
    }

    /* renamed from: c4.d$c */
    public static class c {

        /* renamed from: a */
        public String f3430a;

        /* renamed from: b */
        public long f3431b;

        /* renamed from: c */
        public String f3432c;

        /* renamed from: d */
        public String f3433d;

        /* renamed from: e */
        public Uri f3434e;

        /* renamed from: f */
        public int f3435f;

        public c() {
            this.f3435f = 0;
        }

        public String toString() {
            return "Bucket{bucketId='" + this.f3430a + "', imageId=" + this.f3431b + ", bucket='" + this.f3432c + "', path='" + this.f3433d + "', uri='" + this.f3434e + "', childCount=" + this.f3435f + '}';
        }
    }

    public C0726d(Context context) {
        this.f3421a = context;
        this.f3422b = context.getContentResolver();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: m */
    public /* synthetic */ int m3562m(c cVar, c cVar2) {
        int iM3571j = m3571j(cVar);
        int iM3571j2 = m3571j(cVar2);
        return iM3571j == iM3571j2 ? cVar.f3432c.compareToIgnoreCase(cVar2.f3432c) : iM3571j2 - iM3571j;
    }

    /* renamed from: b */
    public final void m3563b(b bVar, ArrayList<ImageItem> arrayList) {
        Cursor cursorQuery = this.f3422b.query(f3419g, new String[]{"_id", "bucket_id", "_data"}, "bucket_id = ? AND mime_type IN (?, ?)", new String[]{bVar.f3427a, "image/jpeg", "image/png"}, "date_modified " + this.f3423c);
        if (cursorQuery == null) {
            return;
        }
        if (cursorQuery.moveToFirst()) {
            int columnIndex = cursorQuery.getColumnIndex("_id");
            int columnIndex2 = cursorQuery.getColumnIndex("bucket_id");
            int columnIndex3 = cursorQuery.getColumnIndex("_data");
            do {
                String string = cursorQuery.getString(columnIndex2);
                long j9 = cursorQuery.getLong(columnIndex);
                arrayList.add(new ImageItem(string, j9, "-", cursorQuery.getString(columnIndex3), ContentUris.withAppendedId(f3419g, j9).toString(), "-", 0, -1, ""));
            } while (cursorQuery.moveToNext());
        }
        cursorQuery.close();
    }

    /* renamed from: c */
    public final void m3564c(b bVar, ArrayList<ImageItem> arrayList) throws IllegalArgumentException {
        Cursor cursorQuery = this.f3422b.query(f3419g, new String[]{"_id", "bucket_id", "bucket_display_name", "_data"}, "mime_type IN (?, ?)", new String[]{"image/jpeg", "image/png"}, "date_modified " + this.f3423c);
        if (cursorQuery == null) {
            return;
        }
        int columnIndexOrThrow = cursorQuery.getColumnIndexOrThrow("_id");
        int columnIndexOrThrow2 = cursorQuery.getColumnIndexOrThrow("bucket_id");
        int columnIndexOrThrow3 = cursorQuery.getColumnIndexOrThrow("bucket_display_name");
        int columnIndexOrThrow4 = cursorQuery.getColumnIndexOrThrow("_data");
        ArrayList arrayList2 = new ArrayList();
        int count = cursorQuery.getCount();
        int i9 = 0;
        while (i9 < count) {
            cursorQuery.moveToPosition(i9);
            String string = cursorQuery.getString(columnIndexOrThrow4);
            long j9 = cursorQuery.getLong(columnIndexOrThrow);
            Uri uriWithAppendedId = ContentUris.withAppendedId(f3419g, j9);
            String string2 = cursorQuery.getString(columnIndexOrThrow2);
            int i10 = columnIndexOrThrow;
            c cVar = new c();
            cVar.f3430a = string2;
            String string3 = cursorQuery.getString(columnIndexOrThrow3);
            cVar.f3432c = string3;
            cVar.f3431b = j9;
            cVar.f3433d = string;
            cVar.f3434e = uriWithAppendedId;
            cVar.f3435f = 1;
            if (string3 == null) {
                cVar.f3432c = m3570i(string);
            }
            arrayList2.add(cVar);
            i9++;
            columnIndexOrThrow = i10;
        }
        cursorQuery.close();
        int size = arrayList2.size();
        long j10 = bVar.f3428b;
        if (j10 < 0 || j10 >= size) {
            return;
        }
        int i11 = (int) j10;
        int i12 = 0;
        while (i11 < size) {
            long j11 = bVar.f3429c;
            if (j11 >= 0 && i12 >= j11) {
                return;
            }
            c cVar2 = (c) arrayList2.get(i11);
            arrayList.add(new ImageItem(cVar2.f3430a, cVar2.f3431b, cVar2.f3432c, cVar2.f3433d, cVar2.f3434e.toString(), "-", cVar2.f3435f, -1, ""));
            i11++;
            i12++;
        }
    }

    /* renamed from: d */
    public final void m3565d(b bVar, ArrayList<ImageItem> arrayList) throws IllegalArgumentException {
        int i9;
        int i10;
        int i11;
        boolean z8;
        Log.v("MediaStoreLibrary", "[browseRoot] start");
        Cursor cursorQuery = this.f3422b.query(f3419g, new String[]{"_id", "bucket_id", "bucket_display_name", "_data"}, "mime_type IN (?, ?)", new String[]{"image/jpeg", "image/png"}, "date_modified " + this.f3423c);
        if (cursorQuery == null) {
            return;
        }
        int columnIndexOrThrow = cursorQuery.getColumnIndexOrThrow("_id");
        int columnIndexOrThrow2 = cursorQuery.getColumnIndexOrThrow("bucket_id");
        int columnIndexOrThrow3 = cursorQuery.getColumnIndexOrThrow("bucket_display_name");
        int columnIndexOrThrow4 = cursorQuery.getColumnIndexOrThrow("_data");
        ArrayList<c> arrayList2 = new ArrayList<>();
        int count = cursorQuery.getCount();
        int i12 = 0;
        int i13 = 0;
        while (i12 < count) {
            cursorQuery.moveToPosition(i12);
            String string = cursorQuery.getString(columnIndexOrThrow4);
            Uri uriWithAppendedId = ContentUris.withAppendedId(f3419g, cursorQuery.getLong(columnIndexOrThrow));
            String string2 = cursorQuery.getString(columnIndexOrThrow2);
            if (string2 == null) {
                i9 = columnIndexOrThrow2;
                i10 = columnIndexOrThrow4;
                i11 = count;
            } else {
                int size = arrayList2.size();
                i9 = columnIndexOrThrow2;
                int i14 = 0;
                while (true) {
                    if (i14 >= size) {
                        i10 = columnIndexOrThrow4;
                        i11 = count;
                        z8 = false;
                        break;
                    }
                    i10 = columnIndexOrThrow4;
                    c cVar = arrayList2.get(i14);
                    i11 = count;
                    if (cVar != null && string2.equals(cVar.f3430a)) {
                        cVar.f3435f++;
                        i13++;
                        z8 = true;
                        break;
                    } else {
                        i14++;
                        count = i11;
                        columnIndexOrThrow4 = i10;
                    }
                }
                if (!z8) {
                    c cVar2 = new c();
                    cVar2.f3430a = string2;
                    cVar2.f3432c = cursorQuery.getString(columnIndexOrThrow3);
                    cVar2.f3431b = cursorQuery.getLong(columnIndexOrThrow);
                    cVar2.f3433d = string;
                    cVar2.f3434e = uriWithAppendedId;
                    cVar2.f3435f++;
                    i13++;
                    if (cVar2.f3432c == null) {
                        cVar2.f3432c = m3570i(string);
                    }
                    arrayList2.add(cVar2);
                    Log.i("MediaStoreLibrary", "browseRoot add bucket:" + cVar2);
                }
            }
            i12++;
            columnIndexOrThrow2 = i9;
            count = i11;
            columnIndexOrThrow4 = i10;
        }
        if (arrayList2.size() == 0) {
            Log.v("MediaStoreLibrary", "[browseRoot] The size of bucketArrayList is 0, return now");
            return;
        }
        arrayList2.add(m3572k(arrayList2, i13));
        Collections.sort(arrayList2, m3569h());
        cursorQuery.close();
        int size2 = arrayList2.size();
        long j9 = bVar.f3428b;
        if (j9 < 0 || j9 >= size2) {
            return;
        }
        int i15 = (int) j9;
        int i16 = 0;
        while (i15 < size2) {
            long j10 = bVar.f3429c;
            if (j10 >= 0 && i16 >= j10) {
                return;
            }
            c cVar3 = arrayList2.get(i15);
            arrayList.add(new ImageItem(cVar3.f3430a, cVar3.f3431b, cVar3.f3432c, cVar3.f3433d, cVar3.f3434e.toString(), "-", cVar3.f3435f, -1, ""));
            i15++;
            i16++;
        }
    }

    /* renamed from: e */
    public void m3566e(String str, ArrayList<ImageItem> arrayList) throws IllegalArgumentException {
        b bVar = new b();
        bVar.f3427a = str;
        if (str.equals("TotalPhotoBucketID")) {
            m3564c(bVar, arrayList);
        } else {
            m3563b(bVar, arrayList);
        }
    }

    /* renamed from: f */
    public void m3567f(ArrayList<ImageItem> arrayList) throws IllegalArgumentException {
        b bVar = new b();
        bVar.f3427a = "";
        m3565d(bVar, arrayList);
    }

    /* renamed from: g */
    public String m3568g(long j9) {
        int columnIndex;
        String[] strArr = {"_id", "_data"};
        String[] strArr2 = {String.valueOf(j9)};
        String string = "";
        try {
            Cursor cursorQuery = this.f3422b.query(f3420h, strArr, "video_id = ?", strArr2, null);
            if (cursorQuery != null) {
                try {
                    if (cursorQuery.moveToFirst() && (columnIndex = cursorQuery.getColumnIndex("_data")) >= 0) {
                        string = cursorQuery.getString(columnIndex);
                    }
                } finally {
                }
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        } catch (Exception e9) {
            e9.printStackTrace();
        }
        return string;
    }

    /* renamed from: h */
    public final Comparator<c> m3569h() {
        return new Comparator() { // from class: c4.c
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return this.f3418b.m3562m((C0726d.c) obj, (C0726d.c) obj2);
            }
        };
    }

    /* renamed from: i */
    public final String m3570i(String str) {
        if (str == null) {
            str = "";
        }
        File parentFile = new File(str).getParentFile();
        if (parentFile == null) {
            parentFile = new File("/");
        }
        return parentFile.getName();
    }

    /* renamed from: j */
    public final int m3571j(c cVar) {
        if (cVar.f3432c.equals(this.f3421a.getString(R.string.albums_allPhotos))) {
            return 3;
        }
        return cVar.f3432c.equalsIgnoreCase("camera") ? 2 : 1;
    }

    /* renamed from: k */
    public final c m3572k(ArrayList<c> arrayList, int i9) {
        c cVar = new c();
        cVar.f3430a = "TotalPhotoBucketID";
        cVar.f3432c = this.f3421a.getString(R.string.albums_allPhotos);
        cVar.f3431b = arrayList.get(0).f3431b;
        cVar.f3433d = arrayList.get(0).f3433d;
        cVar.f3434e = arrayList.get(0).f3434e;
        cVar.f3435f = i9;
        return cVar;
    }

    /* renamed from: l */
    public VideoItem m3573l(Uri uri) {
        VideoItem videoItem = null;
        if (uri == null) {
            return null;
        }
        Cursor cursorQuery = this.f3422b.query(uri, null, null, null, null);
        if (cursorQuery != null) {
            try {
                if (cursorQuery.moveToFirst()) {
                    int columnIndex = cursorQuery.getColumnIndex("bucket_id");
                    int columnIndex2 = cursorQuery.getColumnIndex("_id");
                    int columnIndex3 = cursorQuery.getColumnIndex("_display_name");
                    int columnIndex4 = cursorQuery.getColumnIndex("duration");
                    int columnIndex5 = cursorQuery.getColumnIndex("_data");
                    String string = "";
                    String string2 = columnIndex < 0 ? "" : cursorQuery.getString(columnIndex);
                    long j9 = columnIndex2 < 0 ? -1L : cursorQuery.getLong(columnIndex2);
                    String string3 = cursorQuery.getString(columnIndex3);
                    long j10 = columnIndex4 < 0 ? 0L : cursorQuery.getLong(columnIndex4);
                    if (columnIndex5 >= 0) {
                        string = cursorQuery.getString(columnIndex5);
                    }
                    String str = string;
                    videoItem = new VideoItem(string2, j9, str, uri.toString(), "", string3, j10 == 0 ? CLUtility.m16440G0(str, uri) : j10, false, 0, 0);
                }
            } finally {
            }
        }
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        return videoItem;
    }
}
