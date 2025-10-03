package org.apache.commons.p159io.output;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import org.apache.commons.p159io.FileUtils;
import org.apache.commons.p159io.IOUtils;

/* loaded from: classes.dex */
public class LockableFileWriter extends Writer {
    private static final String LCK = ".lck";
    static /* synthetic */ Class class$org$apache$commons$io$output$LockableFileWriter;
    private final File lockFile;
    private final Writer out;

    public LockableFileWriter(String str) {
        this(str, false, (String) null);
    }

    public static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e9) {
            throw new NoClassDefFoundError(e9.getMessage());
        }
    }

    private void createLock() {
        Class clsClass$ = class$org$apache$commons$io$output$LockableFileWriter;
        if (clsClass$ == null) {
            clsClass$ = class$("org.apache.commons.io.output.LockableFileWriter");
            class$org$apache$commons$io$output$LockableFileWriter = clsClass$;
        }
        synchronized (clsClass$) {
            if (!this.lockFile.createNewFile()) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Can't write file, lock ");
                stringBuffer.append(this.lockFile.getAbsolutePath());
                stringBuffer.append(" exists");
                throw new IOException(stringBuffer.toString());
            }
            this.lockFile.deleteOnExit();
        }
    }

    private Writer initWriter(File file, String str, boolean z8) throws IOException {
        FileOutputStream fileOutputStream;
        OutputStreamWriter outputStreamWriter;
        boolean zExists = file.exists();
        try {
            if (str == null) {
                outputStreamWriter = new FileWriter(file.getAbsolutePath(), z8);
            } else {
                fileOutputStream = new FileOutputStream(file.getAbsolutePath(), z8);
                try {
                    outputStreamWriter = new OutputStreamWriter(fileOutputStream, str);
                } catch (IOException e9) {
                    e = e9;
                    IOUtils.closeQuietly((Writer) null);
                    IOUtils.closeQuietly(fileOutputStream);
                    this.lockFile.delete();
                    if (!zExists) {
                        file.delete();
                    }
                    throw e;
                } catch (RuntimeException e10) {
                    e = e10;
                    IOUtils.closeQuietly((Writer) null);
                    IOUtils.closeQuietly(fileOutputStream);
                    this.lockFile.delete();
                    if (!zExists) {
                        file.delete();
                    }
                    throw e;
                }
            }
            return outputStreamWriter;
        } catch (IOException e11) {
            e = e11;
            fileOutputStream = null;
        } catch (RuntimeException e12) {
            e = e12;
            fileOutputStream = null;
        }
    }

    private void testLockDir(File file) throws IOException {
        if (!file.exists()) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Could not find lockDir: ");
            stringBuffer.append(file.getAbsolutePath());
            throw new IOException(stringBuffer.toString());
        }
        if (file.canWrite()) {
            return;
        }
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append("Could not write to lockDir: ");
        stringBuffer2.append(file.getAbsolutePath());
        throw new IOException(stringBuffer2.toString());
    }

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        try {
            this.out.close();
        } finally {
            this.lockFile.delete();
        }
    }

    @Override // java.io.Writer, java.io.Flushable
    public void flush() throws IOException {
        this.out.flush();
    }

    @Override // java.io.Writer
    public void write(int i9) throws IOException {
        this.out.write(i9);
    }

    public LockableFileWriter(String str, boolean z8) {
        this(str, z8, (String) null);
    }

    @Override // java.io.Writer
    public void write(char[] cArr) throws IOException {
        this.out.write(cArr);
    }

    public LockableFileWriter(String str, boolean z8, String str2) {
        this(new File(str), z8, str2);
    }

    @Override // java.io.Writer
    public void write(char[] cArr, int i9, int i10) throws IOException {
        this.out.write(cArr, i9, i10);
    }

    public LockableFileWriter(File file) {
        this(file, false, (String) null);
    }

    @Override // java.io.Writer
    public void write(String str) throws IOException {
        this.out.write(str);
    }

    public LockableFileWriter(File file, boolean z8) {
        this(file, z8, (String) null);
    }

    @Override // java.io.Writer
    public void write(String str, int i9, int i10) throws IOException {
        this.out.write(str, i9, i10);
    }

    public LockableFileWriter(File file, boolean z8, String str) {
        this(file, null, z8, str);
    }

    public LockableFileWriter(File file, String str) {
        this(file, str, false, null);
    }

    public LockableFileWriter(File file, String str, boolean z8, String str2) throws IOException {
        File absoluteFile = file.getAbsoluteFile();
        if (absoluteFile.getParentFile() != null) {
            FileUtils.forceMkdir(absoluteFile.getParentFile());
        }
        if (!absoluteFile.isDirectory()) {
            File file2 = new File(str2 == null ? System.getProperty("java.io.tmpdir") : str2);
            FileUtils.forceMkdir(file2);
            testLockDir(file2);
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(absoluteFile.getName());
            stringBuffer.append(LCK);
            this.lockFile = new File(file2, stringBuffer.toString());
            createLock();
            this.out = initWriter(absoluteFile, str, z8);
            return;
        }
        throw new IOException("File specified is a directory");
    }
}
