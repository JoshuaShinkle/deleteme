package com.google.android.exoplayer2.upstream.cache;

import android.util.Log;
import android.util.SparseArray;
import com.google.android.exoplayer2.upstream.cache.Cache;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.AtomicFile;
import com.google.android.exoplayer2.util.ReusableBufferedOutputStream;
import com.google.android.exoplayer2.util.Util;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes.dex */
class CachedContentIndex {
    public static final String FILE_NAME = "cached_content_index.exi";
    private static final int FLAG_ENCRYPTED_INDEX = 1;
    private static final String TAG = "CachedContentIndex";
    private static final int VERSION = 1;
    private final AtomicFile atomicFile;
    private ReusableBufferedOutputStream bufferedOutputStream;
    private boolean changed;
    private final Cipher cipher;
    private final boolean encrypt;
    private final SparseArray<String> idToKey;
    private final HashMap<String, CachedContent> keyToContent;
    private final SecretKeySpec secretKeySpec;

    public CachedContentIndex(File file) {
        this(file, null);
    }

    private void add(CachedContent cachedContent) {
        this.keyToContent.put(cachedContent.key, cachedContent);
        this.idToKey.put(cachedContent.f15325id, cachedContent.key);
    }

    private static Cipher getCipher() {
        if (Util.SDK_INT == 18) {
            try {
                return Cipher.getInstance("AES/CBC/PKCS5PADDING", "BC");
            } catch (Throwable unused) {
            }
        }
        return Cipher.getInstance("AES/CBC/PKCS5PADDING");
    }

    public static int getNewId(SparseArray<String> sparseArray) {
        int size = sparseArray.size();
        int i9 = 0;
        int iKeyAt = size == 0 ? 0 : sparseArray.keyAt(size - 1) + 1;
        if (iKeyAt >= 0) {
            return iKeyAt;
        }
        while (i9 < size && i9 == sparseArray.keyAt(i9)) {
            i9++;
        }
        return i9;
    }

    /* JADX WARN: Not initialized variable reg: 3, insn: 0x009a: MOVE (r1 I:??[OBJECT, ARRAY]) = (r3 I:??[OBJECT, ARRAY]), block:B:49:0x009a */
    /* JADX WARN: Removed duplicated region for block: B:51:0x009d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private boolean readFile() throws Throwable {
        DataInputStream dataInputStream;
        IOException e9;
        DataInputStream dataInputStream2;
        DataInputStream dataInputStream3 = null;
        try {
            try {
                BufferedInputStream bufferedInputStream = new BufferedInputStream(this.atomicFile.openRead());
                dataInputStream = new DataInputStream(bufferedInputStream);
                try {
                    if (dataInputStream.readInt() != 1) {
                        Util.closeQuietly(dataInputStream);
                        return false;
                    }
                    if ((dataInputStream.readInt() & 1) == 0) {
                        if (this.encrypt) {
                            this.changed = true;
                        }
                        dataInputStream3 = dataInputStream;
                    } else {
                        if (this.cipher == null) {
                            Util.closeQuietly(dataInputStream);
                            return false;
                        }
                        byte[] bArr = new byte[16];
                        dataInputStream.readFully(bArr);
                        try {
                            this.cipher.init(2, this.secretKeySpec, new IvParameterSpec(bArr));
                            dataInputStream3 = new DataInputStream(new CipherInputStream(bufferedInputStream, this.cipher));
                        } catch (InvalidAlgorithmParameterException e10) {
                            e = e10;
                            throw new IllegalStateException(e);
                        } catch (InvalidKeyException e11) {
                            e = e11;
                            throw new IllegalStateException(e);
                        }
                    }
                    int i9 = dataInputStream3.readInt();
                    int iHeaderHashCode = 0;
                    for (int i10 = 0; i10 < i9; i10++) {
                        CachedContent cachedContent = new CachedContent(dataInputStream3);
                        add(cachedContent);
                        iHeaderHashCode += cachedContent.headerHashCode();
                    }
                    if (dataInputStream3.readInt() != iHeaderHashCode) {
                        Util.closeQuietly(dataInputStream3);
                        return false;
                    }
                    Util.closeQuietly(dataInputStream3);
                    return true;
                } catch (FileNotFoundException unused) {
                    dataInputStream3 = dataInputStream;
                    if (dataInputStream3 != null) {
                        Util.closeQuietly(dataInputStream3);
                    }
                    return false;
                } catch (IOException e12) {
                    e9 = e12;
                    Log.e(TAG, "Error reading cache content index file.", e9);
                    if (dataInputStream != null) {
                        Util.closeQuietly(dataInputStream);
                    }
                    return false;
                }
            } catch (Throwable th) {
                th = th;
                dataInputStream3 = dataInputStream2;
                if (dataInputStream3 != null) {
                    Util.closeQuietly(dataInputStream3);
                }
                throw th;
            }
        } catch (FileNotFoundException unused2) {
        } catch (IOException e13) {
            dataInputStream = dataInputStream3;
            e9 = e13;
        } catch (Throwable th2) {
            th = th2;
            if (dataInputStream3 != null) {
            }
            throw th;
        }
    }

    private void writeFile() throws Throwable {
        DataOutputStream dataOutputStream;
        DataOutputStream dataOutputStream2 = null;
        try {
            try {
                OutputStream outputStreamStartWrite = this.atomicFile.startWrite();
                ReusableBufferedOutputStream reusableBufferedOutputStream = this.bufferedOutputStream;
                if (reusableBufferedOutputStream == null) {
                    this.bufferedOutputStream = new ReusableBufferedOutputStream(outputStreamStartWrite);
                } else {
                    reusableBufferedOutputStream.reset(outputStreamStartWrite);
                }
                dataOutputStream = new DataOutputStream(this.bufferedOutputStream);
            } catch (IOException e9) {
                e = e9;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            dataOutputStream.writeInt(1);
            int iHeaderHashCode = 0;
            dataOutputStream.writeInt(this.encrypt ? 1 : 0);
            if (this.encrypt) {
                byte[] bArr = new byte[16];
                new Random().nextBytes(bArr);
                dataOutputStream.write(bArr);
                try {
                    this.cipher.init(1, this.secretKeySpec, new IvParameterSpec(bArr));
                    dataOutputStream.flush();
                    dataOutputStream = new DataOutputStream(new CipherOutputStream(this.bufferedOutputStream, this.cipher));
                } catch (InvalidAlgorithmParameterException e10) {
                    e = e10;
                    throw new IllegalStateException(e);
                } catch (InvalidKeyException e11) {
                    e = e11;
                    throw new IllegalStateException(e);
                }
            }
            dataOutputStream.writeInt(this.keyToContent.size());
            for (CachedContent cachedContent : this.keyToContent.values()) {
                cachedContent.writeToStream(dataOutputStream);
                iHeaderHashCode += cachedContent.headerHashCode();
            }
            dataOutputStream.writeInt(iHeaderHashCode);
            this.atomicFile.endWrite(dataOutputStream);
            Util.closeQuietly((Closeable) null);
        } catch (IOException e12) {
            e = e12;
            dataOutputStream2 = dataOutputStream;
            throw new Cache.CacheException(e);
        } catch (Throwable th2) {
            th = th2;
            dataOutputStream2 = dataOutputStream;
            Util.closeQuietly(dataOutputStream2);
            throw th;
        }
    }

    public void addNew(CachedContent cachedContent) {
        add(cachedContent);
        this.changed = true;
    }

    public int assignIdForKey(String str) {
        return getOrAdd(str).f15325id;
    }

    public CachedContent get(String str) {
        return this.keyToContent.get(str);
    }

    public Collection<CachedContent> getAll() {
        return this.keyToContent.values();
    }

    public long getContentLength(String str) {
        CachedContent cachedContent = get(str);
        if (cachedContent == null) {
            return -1L;
        }
        return cachedContent.getLength();
    }

    public String getKeyForId(int i9) {
        return this.idToKey.get(i9);
    }

    public Set<String> getKeys() {
        return this.keyToContent.keySet();
    }

    public CachedContent getOrAdd(String str) {
        CachedContent cachedContent = this.keyToContent.get(str);
        return cachedContent == null ? addNew(str, -1L) : cachedContent;
    }

    public void load() {
        Assertions.checkState(!this.changed);
        if (readFile()) {
            return;
        }
        this.atomicFile.delete();
        this.keyToContent.clear();
        this.idToKey.clear();
    }

    public void maybeRemove(String str) {
        CachedContent cachedContent = this.keyToContent.get(str);
        if (cachedContent == null || !cachedContent.isEmpty() || cachedContent.isLocked()) {
            return;
        }
        this.keyToContent.remove(str);
        this.idToKey.remove(cachedContent.f15325id);
        this.changed = true;
    }

    public void removeEmpty() {
        int size = this.keyToContent.size();
        String[] strArr = new String[size];
        this.keyToContent.keySet().toArray(strArr);
        for (int i9 = 0; i9 < size; i9++) {
            maybeRemove(strArr[i9]);
        }
    }

    public void setContentLength(String str, long j9) {
        CachedContent cachedContent = get(str);
        if (cachedContent == null) {
            addNew(str, j9);
        } else if (cachedContent.getLength() != j9) {
            cachedContent.setLength(j9);
            this.changed = true;
        }
    }

    public void store() throws Throwable {
        if (this.changed) {
            writeFile();
            this.changed = false;
        }
    }

    public CachedContentIndex(File file, byte[] bArr) {
        this(file, bArr, bArr != null);
    }

    public CachedContentIndex(File file, byte[] bArr, boolean z8) {
        this.encrypt = z8;
        if (bArr != null) {
            Assertions.checkArgument(bArr.length == 16);
            try {
                this.cipher = getCipher();
                this.secretKeySpec = new SecretKeySpec(bArr, "AES");
            } catch (NoSuchAlgorithmException | NoSuchPaddingException e9) {
                throw new IllegalStateException(e9);
            }
        } else {
            Assertions.checkState(!z8);
            this.cipher = null;
            this.secretKeySpec = null;
        }
        this.keyToContent = new HashMap<>();
        this.idToKey = new SparseArray<>();
        this.atomicFile = new AtomicFile(new File(file, FILE_NAME));
    }

    private CachedContent addNew(String str, long j9) {
        CachedContent cachedContent = new CachedContent(getNewId(this.idToKey), str, j9);
        addNew(cachedContent);
        return cachedContent;
    }
}
