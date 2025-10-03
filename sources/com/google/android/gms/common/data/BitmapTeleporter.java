package com.google.android.gms.common.data;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.util.Log;
import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RecentlyNullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import net.sqlcipher.database.SQLiteDatabase;

@ShowFirstParty
@KeepForSdk
@SafeParcelable.Class(creator = "BitmapTeleporterCreator")
/* loaded from: classes2.dex */
public class BitmapTeleporter extends AbstractSafeParcelable implements ReflectedParcelable {

    @RecentlyNonNull
    @KeepForSdk
    public static final Parcelable.Creator<BitmapTeleporter> CREATOR = new zaa();

    @SafeParcelable.VersionField(m17523id = 1)
    private final int zaa;

    @SafeParcelable.Field(m17520id = 2)
    private ParcelFileDescriptor zab;

    @SafeParcelable.Field(m17520id = 3)
    private final int zac;
    private Bitmap zad;
    private boolean zae;
    private File zaf;

    @SafeParcelable.Constructor
    public BitmapTeleporter(@SafeParcelable.Param(m17521id = 1) int i9, @SafeParcelable.Param(m17521id = 2) ParcelFileDescriptor parcelFileDescriptor, @SafeParcelable.Param(m17521id = 3) int i10) {
        this.zaa = i9;
        this.zab = parcelFileDescriptor;
        this.zac = i10;
        this.zad = null;
        this.zae = false;
    }

    private final FileOutputStream zaa() throws IOException {
        File file = this.zaf;
        if (file == null) {
            throw new IllegalStateException("setTempDir() must be called before writing this object to a parcel");
        }
        try {
            File fileCreateTempFile = File.createTempFile("teleporter", ".tmp", file);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(fileCreateTempFile);
                this.zab = ParcelFileDescriptor.open(fileCreateTempFile, SQLiteDatabase.CREATE_IF_NECESSARY);
                fileCreateTempFile.delete();
                return fileOutputStream;
            } catch (FileNotFoundException unused) {
                throw new IllegalStateException("Temporary file is somehow already deleted");
            }
        } catch (IOException e9) {
            throw new IllegalStateException("Could not create temporary file", e9);
        }
    }

    @RecentlyNullable
    @KeepForSdk
    public Bitmap get() throws IOException {
        if (!this.zae) {
            DataInputStream dataInputStream = new DataInputStream(new ParcelFileDescriptor.AutoCloseInputStream((ParcelFileDescriptor) Preconditions.checkNotNull(this.zab)));
            try {
                try {
                    byte[] bArr = new byte[dataInputStream.readInt()];
                    int i9 = dataInputStream.readInt();
                    int i10 = dataInputStream.readInt();
                    Bitmap.Config configValueOf = Bitmap.Config.valueOf(dataInputStream.readUTF());
                    dataInputStream.read(bArr);
                    zaa(dataInputStream);
                    ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
                    Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i9, i10, configValueOf);
                    bitmapCreateBitmap.copyPixelsFromBuffer(byteBufferWrap);
                    this.zad = bitmapCreateBitmap;
                    this.zae = true;
                } catch (IOException e9) {
                    throw new IllegalStateException("Could not read from parcel file descriptor", e9);
                }
            } catch (Throwable th) {
                zaa(dataInputStream);
                throw th;
            }
        }
        return this.zad;
    }

    @KeepForSdk
    public void release() throws IOException {
        if (this.zae) {
            return;
        }
        try {
            ((ParcelFileDescriptor) Preconditions.checkNotNull(this.zab)).close();
        } catch (IOException e9) {
            Log.w("BitmapTeleporter", "Could not close PFD", e9);
        }
    }

    @KeepForSdk
    public void setTempDir(@RecentlyNonNull File file) {
        if (file == null) {
            throw new NullPointerException("Cannot set null temp directory");
        }
        this.zaf = file;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@RecentlyNonNull Parcel parcel, @RecentlyNonNull int i9) throws IOException {
        if (this.zab == null) {
            Bitmap bitmap = (Bitmap) Preconditions.checkNotNull(this.zad);
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(bitmap.getRowBytes() * bitmap.getHeight());
            bitmap.copyPixelsToBuffer(byteBufferAllocate);
            byte[] bArrArray = byteBufferAllocate.array();
            DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(zaa()));
            try {
                try {
                    dataOutputStream.writeInt(bArrArray.length);
                    dataOutputStream.writeInt(bitmap.getWidth());
                    dataOutputStream.writeInt(bitmap.getHeight());
                    dataOutputStream.writeUTF(bitmap.getConfig().toString());
                    dataOutputStream.write(bArrArray);
                } catch (IOException e9) {
                    throw new IllegalStateException("Could not write into unlinked file", e9);
                }
            } finally {
                zaa(dataOutputStream);
            }
        }
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zaa);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zab, i9 | 1, false);
        SafeParcelWriter.writeInt(parcel, 3, this.zac);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
        this.zab = null;
    }

    @KeepForSdk
    public BitmapTeleporter(@RecentlyNonNull Bitmap bitmap) {
        this.zaa = 1;
        this.zab = null;
        this.zac = 0;
        this.zad = bitmap;
        this.zae = true;
    }

    private static void zaa(Closeable closeable) throws IOException {
        try {
            closeable.close();
        } catch (IOException e9) {
            Log.w("BitmapTeleporter", "Could not close stream", e9);
        }
    }
}
