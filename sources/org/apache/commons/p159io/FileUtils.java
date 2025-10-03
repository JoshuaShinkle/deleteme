package org.apache.commons.p159io;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import java.util.zip.Checksum;
import org.apache.commons.p159io.filefilter.DirectoryFileFilter;
import org.apache.commons.p159io.filefilter.FalseFileFilter;
import org.apache.commons.p159io.filefilter.FileFilterUtils;
import org.apache.commons.p159io.filefilter.IOFileFilter;
import org.apache.commons.p159io.filefilter.SuffixFileFilter;
import org.apache.commons.p159io.filefilter.TrueFileFilter;
import org.apache.commons.p159io.output.NullOutputStream;

/* loaded from: classes.dex */
public class FileUtils {
    public static final File[] EMPTY_FILE_ARRAY = new File[0];
    public static final long ONE_GB = 1073741824;
    public static final long ONE_KB = 1024;
    public static final long ONE_MB = 1048576;

    public static String byteCountToDisplaySize(long j9) {
        long j10 = j9 / ONE_GB;
        if (j10 > 0) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(String.valueOf(j10));
            stringBuffer.append(" GB");
            return stringBuffer.toString();
        }
        long j11 = j9 / 1048576;
        if (j11 > 0) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append(String.valueOf(j11));
            stringBuffer2.append(" MB");
            return stringBuffer2.toString();
        }
        long j12 = j9 / 1024;
        if (j12 > 0) {
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append(String.valueOf(j12));
            stringBuffer3.append(" KB");
            return stringBuffer3.toString();
        }
        StringBuffer stringBuffer4 = new StringBuffer();
        stringBuffer4.append(String.valueOf(j9));
        stringBuffer4.append(" bytes");
        return stringBuffer4.toString();
    }

    public static Checksum checksum(File file, Checksum checksum) throws Throwable {
        CheckedInputStream checkedInputStream;
        if (file.isDirectory()) {
            throw new IllegalArgumentException("Checksums can't be computed on directories");
        }
        CheckedInputStream checkedInputStream2 = null;
        try {
            checkedInputStream = new CheckedInputStream(new FileInputStream(file), checksum);
        } catch (Throwable th) {
            th = th;
        }
        try {
            IOUtils.copy(checkedInputStream, new NullOutputStream());
            IOUtils.closeQuietly(checkedInputStream);
            return checksum;
        } catch (Throwable th2) {
            th = th2;
            checkedInputStream2 = checkedInputStream;
            IOUtils.closeQuietly(checkedInputStream2);
            throw th;
        }
    }

    public static long checksumCRC32(File file) throws Throwable {
        CRC32 crc32 = new CRC32();
        checksum(file, crc32);
        return crc32.getValue();
    }

    public static void cleanDirectory(File file) throws IOException {
        if (!file.exists()) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(file);
            stringBuffer.append(" does not exist");
            throw new IllegalArgumentException(stringBuffer.toString());
        }
        if (!file.isDirectory()) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append(file);
            stringBuffer2.append(" is not a directory");
            throw new IllegalArgumentException(stringBuffer2.toString());
        }
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles == null) {
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("Failed to list contents of ");
            stringBuffer3.append(file);
            throw new IOException(stringBuffer3.toString());
        }
        IOException e9 = null;
        for (File file2 : fileArrListFiles) {
            try {
                forceDelete(file2);
            } catch (IOException e10) {
                e9 = e10;
            }
        }
        if (e9 != null) {
            throw e9;
        }
    }

    private static void cleanDirectoryOnExit(File file) throws IOException {
        if (!file.exists()) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(file);
            stringBuffer.append(" does not exist");
            throw new IllegalArgumentException(stringBuffer.toString());
        }
        if (!file.isDirectory()) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append(file);
            stringBuffer2.append(" is not a directory");
            throw new IllegalArgumentException(stringBuffer2.toString());
        }
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles == null) {
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("Failed to list contents of ");
            stringBuffer3.append(file);
            throw new IOException(stringBuffer3.toString());
        }
        IOException e9 = null;
        for (File file2 : fileArrListFiles) {
            try {
                forceDeleteOnExit(file2);
            } catch (IOException e10) {
                e9 = e10;
            }
        }
        if (e9 != null) {
            throw e9;
        }
    }

    public static boolean contentEquals(File file, File file2) throws Throwable {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2;
        boolean zExists = file.exists();
        if (zExists != file2.exists()) {
            return false;
        }
        if (!zExists) {
            return true;
        }
        if (file.isDirectory() || file2.isDirectory()) {
            throw new IOException("Can't compare directories, only files");
        }
        if (file.length() != file2.length()) {
            return false;
        }
        if (file.getCanonicalFile().equals(file2.getCanonicalFile())) {
            return true;
        }
        FileInputStream fileInputStream3 = null;
        try {
            fileInputStream2 = new FileInputStream(file);
            try {
                fileInputStream = new FileInputStream(file2);
            } catch (Throwable th) {
                th = th;
                fileInputStream = null;
            }
        } catch (Throwable th2) {
            th = th2;
            fileInputStream = null;
        }
        try {
            boolean zContentEquals = IOUtils.contentEquals(fileInputStream2, fileInputStream);
            IOUtils.closeQuietly(fileInputStream2);
            IOUtils.closeQuietly(fileInputStream);
            return zContentEquals;
        } catch (Throwable th3) {
            th = th3;
            fileInputStream3 = fileInputStream2;
            IOUtils.closeQuietly(fileInputStream3);
            IOUtils.closeQuietly(fileInputStream);
            throw th;
        }
    }

    public static File[] convertFileCollectionToFileArray(Collection collection) {
        return (File[]) collection.toArray(new File[collection.size()]);
    }

    public static void copyDirectory(File file, File file2) throws IOException {
        copyDirectory(file, file2, true);
    }

    public static void copyDirectoryToDirectory(File file, File file2) throws IOException {
        if (file == null) {
            throw new NullPointerException("Source must not be null");
        }
        if (file.exists() && !file.isDirectory()) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Source '");
            stringBuffer.append(file2);
            stringBuffer.append("' is not a directory");
            throw new IllegalArgumentException(stringBuffer.toString());
        }
        if (file2 == null) {
            throw new NullPointerException("Destination must not be null");
        }
        if (!file2.exists() || file2.isDirectory()) {
            copyDirectory(file, new File(file2, file.getName()), true);
            return;
        }
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append("Destination '");
        stringBuffer2.append(file2);
        stringBuffer2.append("' is not a directory");
        throw new IllegalArgumentException(stringBuffer2.toString());
    }

    public static void copyFile(File file, File file2) throws IOException {
        copyFile(file, file2, true);
    }

    public static void copyFileToDirectory(File file, File file2) throws IOException {
        copyFileToDirectory(file, file2, true);
    }

    public static void copyURLToFile(URL url, File file) throws IOException {
        InputStream inputStreamOpenStream = url.openStream();
        try {
            FileOutputStream fileOutputStreamOpenOutputStream = openOutputStream(file);
            try {
                IOUtils.copy(inputStreamOpenStream, fileOutputStreamOpenOutputStream);
            } finally {
                IOUtils.closeQuietly(fileOutputStreamOpenOutputStream);
            }
        } finally {
            IOUtils.closeQuietly(inputStreamOpenStream);
        }
    }

    public static void deleteDirectory(File file) throws IOException {
        if (file.exists()) {
            cleanDirectory(file);
            if (file.delete()) {
                return;
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Unable to delete directory ");
            stringBuffer.append(file);
            stringBuffer.append(".");
            throw new IOException(stringBuffer.toString());
        }
    }

    private static void deleteDirectoryOnExit(File file) {
        if (file.exists()) {
            cleanDirectoryOnExit(file);
            file.deleteOnExit();
        }
    }

    private static void doCopyDirectory(File file, File file2, boolean z8) throws IOException {
        if (!file2.exists()) {
            if (!file2.mkdirs()) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Destination '");
                stringBuffer.append(file2);
                stringBuffer.append("' directory cannot be created");
                throw new IOException(stringBuffer.toString());
            }
            if (z8) {
                file2.setLastModified(file.lastModified());
            }
        } else if (!file2.isDirectory()) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Destination '");
            stringBuffer2.append(file2);
            stringBuffer2.append("' exists but is not a directory");
            throw new IOException(stringBuffer2.toString());
        }
        if (!file2.canWrite()) {
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("Destination '");
            stringBuffer3.append(file2);
            stringBuffer3.append("' cannot be written to");
            throw new IOException(stringBuffer3.toString());
        }
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles == null) {
            StringBuffer stringBuffer4 = new StringBuffer();
            stringBuffer4.append("Failed to list contents of ");
            stringBuffer4.append(file);
            throw new IOException(stringBuffer4.toString());
        }
        for (int i9 = 0; i9 < fileArrListFiles.length; i9++) {
            File file3 = new File(file2, fileArrListFiles[i9].getName());
            if (fileArrListFiles[i9].isDirectory()) {
                doCopyDirectory(fileArrListFiles[i9], file3, z8);
            } else {
                doCopyFile(fileArrListFiles[i9], file3, z8);
            }
        }
    }

    private static void doCopyFile(File file, File file2, boolean z8) throws IOException {
        if (file2.exists() && file2.isDirectory()) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Destination '");
            stringBuffer.append(file2);
            stringBuffer.append("' exists but is a directory");
            throw new IOException(stringBuffer.toString());
        }
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            try {
                IOUtils.copy(fileInputStream, fileOutputStream);
                IOUtils.closeQuietly(fileInputStream);
                if (file.length() == file2.length()) {
                    if (z8) {
                        file2.setLastModified(file.lastModified());
                    }
                } else {
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append("Failed to copy full contents from '");
                    stringBuffer2.append(file);
                    stringBuffer2.append("' to '");
                    stringBuffer2.append(file2);
                    stringBuffer2.append("'");
                    throw new IOException(stringBuffer2.toString());
                }
            } finally {
                IOUtils.closeQuietly(fileOutputStream);
            }
        } catch (Throwable th) {
            IOUtils.closeQuietly(fileInputStream);
            throw th;
        }
    }

    public static void forceDelete(File file) throws IOException {
        if (file.isDirectory()) {
            deleteDirectory(file);
            return;
        }
        if (!file.exists()) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("File does not exist: ");
            stringBuffer.append(file);
            throw new FileNotFoundException(stringBuffer.toString());
        }
        if (file.delete()) {
            return;
        }
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append("Unable to delete file: ");
        stringBuffer2.append(file);
        throw new IOException(stringBuffer2.toString());
    }

    public static void forceDeleteOnExit(File file) {
        if (file.isDirectory()) {
            deleteDirectoryOnExit(file);
        } else {
            file.deleteOnExit();
        }
    }

    public static void forceMkdir(File file) throws IOException {
        if (!file.exists()) {
            if (file.mkdirs()) {
                return;
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Unable to create directory ");
            stringBuffer.append(file);
            throw new IOException(stringBuffer.toString());
        }
        if (file.isFile()) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("File ");
            stringBuffer2.append(file);
            stringBuffer2.append(" exists and is ");
            stringBuffer2.append("not a directory. Unable to create directory.");
            throw new IOException(stringBuffer2.toString());
        }
    }

    private static void innerListFiles(Collection collection, File file, IOFileFilter iOFileFilter) {
        File[] fileArrListFiles = file.listFiles((FileFilter) iOFileFilter);
        if (fileArrListFiles != null) {
            for (int i9 = 0; i9 < fileArrListFiles.length; i9++) {
                if (fileArrListFiles[i9].isDirectory()) {
                    innerListFiles(collection, fileArrListFiles[i9], iOFileFilter);
                } else {
                    collection.add(fileArrListFiles[i9]);
                }
            }
        }
    }

    public static boolean isFileNewer(File file, File file2) {
        if (file2 == null) {
            throw new IllegalArgumentException("No specified reference file");
        }
        if (file2.exists()) {
            return isFileNewer(file, file2.lastModified());
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("The reference file '");
        stringBuffer.append(file);
        stringBuffer.append("' doesn't exist");
        throw new IllegalArgumentException(stringBuffer.toString());
    }

    public static boolean isFileOlder(File file, File file2) {
        if (file2 == null) {
            throw new IllegalArgumentException("No specified reference file");
        }
        if (file2.exists()) {
            return isFileOlder(file, file2.lastModified());
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("The reference file '");
        stringBuffer.append(file);
        stringBuffer.append("' doesn't exist");
        throw new IllegalArgumentException(stringBuffer.toString());
    }

    public static Iterator iterateFiles(File file, IOFileFilter iOFileFilter, IOFileFilter iOFileFilter2) {
        return listFiles(file, iOFileFilter, iOFileFilter2).iterator();
    }

    public static LineIterator lineIterator(File file, String str) throws IOException {
        FileInputStream fileInputStreamOpenInputStream = null;
        try {
            fileInputStreamOpenInputStream = openInputStream(file);
            return IOUtils.lineIterator(fileInputStreamOpenInputStream, str);
        } catch (IOException e9) {
            IOUtils.closeQuietly(fileInputStreamOpenInputStream);
            throw e9;
        } catch (RuntimeException e10) {
            IOUtils.closeQuietly(fileInputStreamOpenInputStream);
            throw e10;
        }
    }

    public static Collection listFiles(File file, IOFileFilter iOFileFilter, IOFileFilter iOFileFilter2) {
        if (!file.isDirectory()) {
            throw new IllegalArgumentException("Parameter 'directory' is not a directory");
        }
        if (iOFileFilter == null) {
            throw new NullPointerException("Parameter 'fileFilter' is null");
        }
        IOFileFilter iOFileFilter3 = DirectoryFileFilter.INSTANCE;
        IOFileFilter iOFileFilterAndFileFilter = FileFilterUtils.andFileFilter(iOFileFilter, FileFilterUtils.notFileFilter(iOFileFilter3));
        IOFileFilter iOFileFilterAndFileFilter2 = iOFileFilter2 == null ? FalseFileFilter.INSTANCE : FileFilterUtils.andFileFilter(iOFileFilter2, iOFileFilter3);
        LinkedList linkedList = new LinkedList();
        innerListFiles(linkedList, file, FileFilterUtils.orFileFilter(iOFileFilterAndFileFilter, iOFileFilterAndFileFilter2));
        return linkedList;
    }

    public static FileInputStream openInputStream(File file) throws IOException {
        if (!file.exists()) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("File '");
            stringBuffer.append(file);
            stringBuffer.append("' does not exist");
            throw new FileNotFoundException(stringBuffer.toString());
        }
        if (file.isDirectory()) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("File '");
            stringBuffer2.append(file);
            stringBuffer2.append("' exists but is a directory");
            throw new IOException(stringBuffer2.toString());
        }
        if (file.canRead()) {
            return new FileInputStream(file);
        }
        StringBuffer stringBuffer3 = new StringBuffer();
        stringBuffer3.append("File '");
        stringBuffer3.append(file);
        stringBuffer3.append("' cannot be read");
        throw new IOException(stringBuffer3.toString());
    }

    public static FileOutputStream openOutputStream(File file) throws IOException {
        if (!file.exists()) {
            File parentFile = file.getParentFile();
            if (parentFile != null && !parentFile.exists() && !parentFile.mkdirs()) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("File '");
                stringBuffer.append(file);
                stringBuffer.append("' could not be created");
                throw new IOException(stringBuffer.toString());
            }
        } else {
            if (file.isDirectory()) {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("File '");
                stringBuffer2.append(file);
                stringBuffer2.append("' exists but is a directory");
                throw new IOException(stringBuffer2.toString());
            }
            if (!file.canWrite()) {
                StringBuffer stringBuffer3 = new StringBuffer();
                stringBuffer3.append("File '");
                stringBuffer3.append(file);
                stringBuffer3.append("' cannot be written to");
                throw new IOException(stringBuffer3.toString());
            }
        }
        return new FileOutputStream(file);
    }

    public static byte[] readFileToByteArray(File file) throws Throwable {
        FileInputStream fileInputStreamOpenInputStream;
        try {
            fileInputStreamOpenInputStream = openInputStream(file);
            try {
                byte[] byteArray = IOUtils.toByteArray(fileInputStreamOpenInputStream);
                IOUtils.closeQuietly(fileInputStreamOpenInputStream);
                return byteArray;
            } catch (Throwable th) {
                th = th;
                IOUtils.closeQuietly(fileInputStreamOpenInputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            fileInputStreamOpenInputStream = null;
        }
    }

    public static String readFileToString(File file, String str) throws Throwable {
        FileInputStream fileInputStreamOpenInputStream;
        try {
            fileInputStreamOpenInputStream = openInputStream(file);
            try {
                String string = IOUtils.toString(fileInputStreamOpenInputStream, str);
                IOUtils.closeQuietly(fileInputStreamOpenInputStream);
                return string;
            } catch (Throwable th) {
                th = th;
                IOUtils.closeQuietly(fileInputStreamOpenInputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            fileInputStreamOpenInputStream = null;
        }
    }

    public static List readLines(File file, String str) throws Throwable {
        FileInputStream fileInputStreamOpenInputStream;
        try {
            fileInputStreamOpenInputStream = openInputStream(file);
            try {
                List lines = IOUtils.readLines(fileInputStreamOpenInputStream, str);
                IOUtils.closeQuietly(fileInputStreamOpenInputStream);
                return lines;
            } catch (Throwable th) {
                th = th;
                IOUtils.closeQuietly(fileInputStreamOpenInputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            fileInputStreamOpenInputStream = null;
        }
    }

    public static long sizeOfDirectory(File file) {
        if (!file.exists()) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(file);
            stringBuffer.append(" does not exist");
            throw new IllegalArgumentException(stringBuffer.toString());
        }
        if (!file.isDirectory()) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append(file);
            stringBuffer2.append(" is not a directory");
            throw new IllegalArgumentException(stringBuffer2.toString());
        }
        File[] fileArrListFiles = file.listFiles();
        long jSizeOfDirectory = 0;
        if (fileArrListFiles == null) {
            return 0L;
        }
        for (File file2 : fileArrListFiles) {
            jSizeOfDirectory += file2.isDirectory() ? sizeOfDirectory(file2) : file2.length();
        }
        return jSizeOfDirectory;
    }

    public static File toFile(URL url) {
        if (url == null || !url.getProtocol().equals("file")) {
            return null;
        }
        String strReplace = url.getFile().replace(IOUtils.DIR_SEPARATOR_UNIX, File.separatorChar);
        int iIndexOf = 0;
        while (true) {
            iIndexOf = strReplace.indexOf(37, iIndexOf);
            if (iIndexOf < 0) {
                return new File(strReplace);
            }
            if (iIndexOf + 2 < strReplace.length()) {
                int i9 = iIndexOf + 3;
                char c9 = (char) Integer.parseInt(strReplace.substring(iIndexOf + 1, i9), 16);
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(strReplace.substring(0, iIndexOf));
                stringBuffer.append(c9);
                stringBuffer.append(strReplace.substring(i9));
                strReplace = stringBuffer.toString();
            }
        }
    }

    public static File[] toFiles(URL[] urlArr) {
        if (urlArr == null || urlArr.length == 0) {
            return EMPTY_FILE_ARRAY;
        }
        File[] fileArr = new File[urlArr.length];
        for (int i9 = 0; i9 < urlArr.length; i9++) {
            URL url = urlArr[i9];
            if (url != null) {
                if (!url.getProtocol().equals("file")) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("URL could not be converted to a File: ");
                    stringBuffer.append(url);
                    throw new IllegalArgumentException(stringBuffer.toString());
                }
                fileArr[i9] = toFile(url);
            }
        }
        return fileArr;
    }

    private static String[] toSuffixes(String[] strArr) {
        String[] strArr2 = new String[strArr.length];
        for (int i9 = 0; i9 < strArr.length; i9++) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(".");
            stringBuffer.append(strArr[i9]);
            strArr2[i9] = stringBuffer.toString();
        }
        return strArr2;
    }

    public static URL[] toURLs(File[] fileArr) {
        int length = fileArr.length;
        URL[] urlArr = new URL[length];
        for (int i9 = 0; i9 < length; i9++) {
            urlArr[i9] = fileArr[i9].toURL();
        }
        return urlArr;
    }

    public static void touch(File file) throws IOException {
        if (!file.exists()) {
            IOUtils.closeQuietly(openOutputStream(file));
        }
        if (file.setLastModified(System.currentTimeMillis())) {
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Unable to set the last modification time for ");
        stringBuffer.append(file);
        throw new IOException(stringBuffer.toString());
    }

    public static boolean waitFor(File file, int i9) throws InterruptedException {
        int i10 = 0;
        int i11 = 0;
        while (!file.exists()) {
            int i12 = i10 + 1;
            if (i10 >= 10) {
                int i13 = i11 + 1;
                if (i11 > i9) {
                    return false;
                }
                i11 = i13;
                i10 = 0;
            } else {
                i10 = i12;
            }
            try {
                Thread.sleep(100L);
            } catch (InterruptedException unused) {
            } catch (Exception unused2) {
                return true;
            }
        }
        return true;
    }

    public static void writeByteArrayToFile(File file, byte[] bArr) throws Throwable {
        FileOutputStream fileOutputStreamOpenOutputStream;
        try {
            fileOutputStreamOpenOutputStream = openOutputStream(file);
            try {
                fileOutputStreamOpenOutputStream.write(bArr);
                IOUtils.closeQuietly(fileOutputStreamOpenOutputStream);
            } catch (Throwable th) {
                th = th;
                IOUtils.closeQuietly(fileOutputStreamOpenOutputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            fileOutputStreamOpenOutputStream = null;
        }
    }

    public static void writeLines(File file, String str, Collection collection) throws Throwable {
        writeLines(file, str, collection, null);
    }

    public static void writeStringToFile(File file, String str, String str2) throws Throwable {
        FileOutputStream fileOutputStreamOpenOutputStream;
        try {
            fileOutputStreamOpenOutputStream = openOutputStream(file);
            try {
                IOUtils.write(str, fileOutputStreamOpenOutputStream, str2);
                IOUtils.closeQuietly(fileOutputStreamOpenOutputStream);
            } catch (Throwable th) {
                th = th;
                IOUtils.closeQuietly(fileOutputStreamOpenOutputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            fileOutputStreamOpenOutputStream = null;
        }
    }

    public static void copyDirectory(File file, File file2, boolean z8) throws IOException {
        if (file == null) {
            throw new NullPointerException("Source must not be null");
        }
        if (file2 == null) {
            throw new NullPointerException("Destination must not be null");
        }
        if (!file.exists()) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Source '");
            stringBuffer.append(file);
            stringBuffer.append("' does not exist");
            throw new FileNotFoundException(stringBuffer.toString());
        }
        if (!file.isDirectory()) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Source '");
            stringBuffer2.append(file);
            stringBuffer2.append("' exists but is not a directory");
            throw new IOException(stringBuffer2.toString());
        }
        if (!file.getCanonicalPath().equals(file2.getCanonicalPath())) {
            doCopyDirectory(file, file2, z8);
            return;
        }
        StringBuffer stringBuffer3 = new StringBuffer();
        stringBuffer3.append("Source '");
        stringBuffer3.append(file);
        stringBuffer3.append("' and destination '");
        stringBuffer3.append(file2);
        stringBuffer3.append("' are the same");
        throw new IOException(stringBuffer3.toString());
    }

    public static void copyFile(File file, File file2, boolean z8) throws IOException {
        if (file == null) {
            throw new NullPointerException("Source must not be null");
        }
        if (file2 == null) {
            throw new NullPointerException("Destination must not be null");
        }
        if (!file.exists()) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Source '");
            stringBuffer.append(file);
            stringBuffer.append("' does not exist");
            throw new FileNotFoundException(stringBuffer.toString());
        }
        if (file.isDirectory()) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Source '");
            stringBuffer2.append(file);
            stringBuffer2.append("' exists but is a directory");
            throw new IOException(stringBuffer2.toString());
        }
        if (file.getCanonicalPath().equals(file2.getCanonicalPath())) {
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("Source '");
            stringBuffer3.append(file);
            stringBuffer3.append("' and destination '");
            stringBuffer3.append(file2);
            stringBuffer3.append("' are the same");
            throw new IOException(stringBuffer3.toString());
        }
        if (file2.getParentFile() != null && !file2.getParentFile().exists() && !file2.getParentFile().mkdirs()) {
            StringBuffer stringBuffer4 = new StringBuffer();
            stringBuffer4.append("Destination '");
            stringBuffer4.append(file2);
            stringBuffer4.append("' directory cannot be created");
            throw new IOException(stringBuffer4.toString());
        }
        if (!file2.exists() || file2.canWrite()) {
            doCopyFile(file, file2, z8);
            return;
        }
        StringBuffer stringBuffer5 = new StringBuffer();
        stringBuffer5.append("Destination '");
        stringBuffer5.append(file2);
        stringBuffer5.append("' exists but is read-only");
        throw new IOException(stringBuffer5.toString());
    }

    public static void copyFileToDirectory(File file, File file2, boolean z8) throws IOException {
        if (file2 == null) {
            throw new NullPointerException("Destination must not be null");
        }
        if (!file2.exists() || file2.isDirectory()) {
            copyFile(file, new File(file2, file.getName()), z8);
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Destination '");
        stringBuffer.append(file2);
        stringBuffer.append("' is not a directory");
        throw new IllegalArgumentException(stringBuffer.toString());
    }

    public static Iterator iterateFiles(File file, String[] strArr, boolean z8) {
        return listFiles(file, strArr, z8).iterator();
    }

    public static void writeLines(File file, Collection collection) throws Throwable {
        writeLines(file, null, collection, null);
    }

    public static void writeLines(File file, String str, Collection collection, String str2) throws Throwable {
        FileOutputStream fileOutputStreamOpenOutputStream;
        try {
            fileOutputStreamOpenOutputStream = openOutputStream(file);
            try {
                IOUtils.writeLines(collection, str2, fileOutputStreamOpenOutputStream, str);
                IOUtils.closeQuietly(fileOutputStreamOpenOutputStream);
            } catch (Throwable th) {
                th = th;
                IOUtils.closeQuietly(fileOutputStreamOpenOutputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            fileOutputStreamOpenOutputStream = null;
        }
    }

    public static String readFileToString(File file) {
        return readFileToString(file, null);
    }

    public static List readLines(File file) {
        return readLines(file, null);
    }

    public static void writeStringToFile(File file, String str) throws Throwable {
        writeStringToFile(file, str, null);
    }

    public static boolean isFileNewer(File file, Date date) {
        if (date != null) {
            return isFileNewer(file, date.getTime());
        }
        throw new IllegalArgumentException("No specified date");
    }

    public static boolean isFileOlder(File file, Date date) {
        if (date != null) {
            return isFileOlder(file, date.getTime());
        }
        throw new IllegalArgumentException("No specified date");
    }

    public static void writeLines(File file, Collection collection, String str) throws Throwable {
        writeLines(file, null, collection, str);
    }

    public static boolean isFileNewer(File file, long j9) {
        if (file != null) {
            return file.exists() && file.lastModified() > j9;
        }
        throw new IllegalArgumentException("No specified file");
    }

    public static boolean isFileOlder(File file, long j9) {
        if (file != null) {
            return file.exists() && file.lastModified() < j9;
        }
        throw new IllegalArgumentException("No specified file");
    }

    public static LineIterator lineIterator(File file) {
        return lineIterator(file, null);
    }

    public static Collection listFiles(File file, String[] strArr, boolean z8) {
        IOFileFilter suffixFileFilter;
        if (strArr == null) {
            suffixFileFilter = TrueFileFilter.INSTANCE;
        } else {
            suffixFileFilter = new SuffixFileFilter(toSuffixes(strArr));
        }
        return listFiles(file, suffixFileFilter, z8 ? TrueFileFilter.INSTANCE : FalseFileFilter.INSTANCE);
    }
}
