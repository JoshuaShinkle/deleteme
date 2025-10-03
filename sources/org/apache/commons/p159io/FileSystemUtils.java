package org.apache.commons.p159io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes.dex */
public class FileSystemUtils {
    private static final int INIT_PROBLEM = -1;
    private static final FileSystemUtils INSTANCE = new FileSystemUtils();

    /* renamed from: OS */
    private static final int f19104OS;
    private static final int OTHER = 0;
    private static final int POSIX_UNIX = 3;
    private static final int UNIX = 2;
    private static final int WINDOWS = 1;

    static {
        String property;
        int i9 = -1;
        try {
            property = System.getProperty("os.name");
        } catch (Exception unused) {
        }
        if (property == null) {
            throw new IOException("os.name not found");
        }
        String lowerCase = property.toLowerCase();
        i9 = lowerCase.indexOf("windows") != -1 ? 1 : (lowerCase.indexOf("linux") == -1 && lowerCase.indexOf("sun os") == -1 && lowerCase.indexOf("sunos") == -1 && lowerCase.indexOf("solaris") == -1 && lowerCase.indexOf("mpe/ix") == -1 && lowerCase.indexOf("freebsd") == -1 && lowerCase.indexOf("irix") == -1 && lowerCase.indexOf("digital unix") == -1 && lowerCase.indexOf("unix") == -1 && lowerCase.indexOf("mac os x") == -1) ? (lowerCase.indexOf("hp-ux") == -1 && lowerCase.indexOf("aix") == -1) ? 0 : 3 : 2;
        f19104OS = i9;
    }

    public static long freeSpace(String str) {
        return INSTANCE.freeSpaceOS(str, f19104OS, false);
    }

    public static long freeSpaceKb(String str) {
        return INSTANCE.freeSpaceOS(str, f19104OS, true);
    }

    public long freeSpaceOS(String str, int i9, boolean z8) throws Throwable {
        if (str == null) {
            throw new IllegalArgumentException("Path must not be empty");
        }
        if (i9 == 0) {
            throw new IllegalStateException("Unsupported operating system");
        }
        if (i9 == 1) {
            long jFreeSpaceWindows = freeSpaceWindows(str);
            return z8 ? jFreeSpaceWindows / 1024 : jFreeSpaceWindows;
        }
        if (i9 == 2) {
            return freeSpaceUnix(str, z8, false);
        }
        if (i9 == 3) {
            return freeSpaceUnix(str, z8, true);
        }
        throw new IllegalStateException("Exception caught when determining operating system");
    }

    public long freeSpaceUnix(String str, boolean z8, boolean z9) throws Throwable {
        if (str.length() == 0) {
            throw new IllegalArgumentException("Path must not be empty");
        }
        String strNormalize = FilenameUtils.normalize(str);
        String string = "-";
        if (z8) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("-");
            stringBuffer.append("k");
            string = stringBuffer.toString();
        }
        if (z9) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append(string);
            stringBuffer2.append("P");
            string = stringBuffer2.toString();
        }
        List listPerformCommand = performCommand(string.length() > 1 ? new String[]{"df", string, strNormalize} : new String[]{"df", strNormalize}, 3);
        if (listPerformCommand.size() < 2) {
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("Command line 'df' did not return info as expected for path '");
            stringBuffer3.append(strNormalize);
            stringBuffer3.append("'- response was ");
            stringBuffer3.append(listPerformCommand);
            throw new IOException(stringBuffer3.toString());
        }
        StringTokenizer stringTokenizer = new StringTokenizer((String) listPerformCommand.get(1), StringUtils.SPACE);
        if (stringTokenizer.countTokens() >= 4) {
            stringTokenizer.nextToken();
        } else {
            if (stringTokenizer.countTokens() != 1 || listPerformCommand.size() < 3) {
                StringBuffer stringBuffer4 = new StringBuffer();
                stringBuffer4.append("Command line 'df' did not return data as expected for path '");
                stringBuffer4.append(strNormalize);
                stringBuffer4.append("'- check path is valid");
                throw new IOException(stringBuffer4.toString());
            }
            stringTokenizer = new StringTokenizer((String) listPerformCommand.get(2), StringUtils.SPACE);
        }
        stringTokenizer.nextToken();
        stringTokenizer.nextToken();
        return parseBytes(stringTokenizer.nextToken(), strNormalize);
    }

    public long freeSpaceWindows(String str) throws Throwable {
        String strNormalize = FilenameUtils.normalize(str);
        if (strNormalize.length() > 2 && strNormalize.charAt(1) == ':') {
            strNormalize = strNormalize.substring(0, 2);
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("dir /-c ");
        stringBuffer.append(strNormalize);
        List listPerformCommand = performCommand(new String[]{"cmd.exe", "/C", stringBuffer.toString()}, Integer.MAX_VALUE);
        for (int size = listPerformCommand.size() - 1; size >= 0; size--) {
            String str2 = (String) listPerformCommand.get(size);
            if (str2.length() > 0) {
                return parseDir(str2, strNormalize);
            }
        }
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append("Command line 'dir /-c' did not return any info for path '");
        stringBuffer2.append(strNormalize);
        stringBuffer2.append("'");
        throw new IOException(stringBuffer2.toString());
    }

    public Process openProcess(String[] strArr) {
        return Runtime.getRuntime().exec(strArr);
    }

    public long parseBytes(String str, String str2) throws NumberFormatException, IOException {
        try {
            long j9 = Long.parseLong(str);
            if (j9 >= 0) {
                return j9;
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Command line 'df' did not find free space in response for path '");
            stringBuffer.append(str2);
            stringBuffer.append("'- check path is valid");
            throw new IOException(stringBuffer.toString());
        } catch (NumberFormatException unused) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Command line 'df' did not return numeric data as expected for path '");
            stringBuffer2.append(str2);
            stringBuffer2.append("'- check path is valid");
            throw new IOException(stringBuffer2.toString());
        }
    }

    public long parseDir(String str, String str2) throws IOException {
        int i9;
        int i10;
        int i11;
        int length = str.length();
        while (true) {
            length--;
            i9 = 0;
            if (length < 0) {
                i10 = 0;
                break;
            }
            if (Character.isDigit(str.charAt(length))) {
                i10 = length + 1;
                break;
            }
        }
        while (true) {
            if (length < 0) {
                i11 = 0;
                break;
            }
            char cCharAt = str.charAt(length);
            if (!Character.isDigit(cCharAt) && cCharAt != ',' && cCharAt != '.') {
                i11 = length + 1;
                break;
            }
            length--;
        }
        if (length < 0) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Command line 'dir /-c' did not return valid info for path '");
            stringBuffer.append(str2);
            stringBuffer.append("'");
            throw new IOException(stringBuffer.toString());
        }
        StringBuffer stringBuffer2 = new StringBuffer(str.substring(i11, i10));
        while (i9 < stringBuffer2.length()) {
            if (stringBuffer2.charAt(i9) == ',' || stringBuffer2.charAt(i9) == '.') {
                stringBuffer2.deleteCharAt(i9);
                i9--;
            }
            i9++;
        }
        return parseBytes(stringBuffer2.toString(), str2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0104  */
    /* JADX WARN: Type inference failed for: r7v0 */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v10, types: [java.io.BufferedReader, java.io.Reader] */
    /* JADX WARN: Type inference failed for: r7v11 */
    /* JADX WARN: Type inference failed for: r7v12 */
    /* JADX WARN: Type inference failed for: r7v2, types: [java.io.Reader] */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r7v5 */
    /* JADX WARN: Type inference failed for: r7v6 */
    /* JADX WARN: Type inference failed for: r7v7 */
    /* JADX WARN: Type inference failed for: r7v8 */
    /* JADX WARN: Type inference failed for: r7v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public List performCommand(String[] strArr, int i9) throws Throwable {
        Process processOpenProcess;
        OutputStream outputStream;
        InputStream inputStream;
        InputStream errorStream;
        ?? bufferedReader;
        ?? r72;
        ArrayList arrayList = new ArrayList(20);
        InputStream inputStream2 = null;
        try {
            processOpenProcess = openProcess(strArr);
        } catch (InterruptedException e9) {
            e = e9;
            inputStream = null;
            outputStream = null;
            errorStream = null;
            bufferedReader = 0;
        } catch (Throwable th) {
            th = th;
            processOpenProcess = null;
            outputStream = null;
        }
        try {
            inputStream = processOpenProcess.getInputStream();
            try {
                outputStream = processOpenProcess.getOutputStream();
                try {
                    errorStream = processOpenProcess.getErrorStream();
                    try {
                        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                        try {
                            for (String line = bufferedReader.readLine(); line != null && arrayList.size() < i9; line = bufferedReader.readLine()) {
                                arrayList.add(line.toLowerCase().trim());
                            }
                            processOpenProcess.waitFor();
                            if (processOpenProcess.exitValue() != 0) {
                                StringBuffer stringBuffer = new StringBuffer();
                                stringBuffer.append("Command line returned OS error code '");
                                stringBuffer.append(processOpenProcess.exitValue());
                                stringBuffer.append("' for command ");
                                stringBuffer.append(Arrays.asList(strArr));
                                throw new IOException(stringBuffer.toString());
                            }
                            if (arrayList.size() == 0) {
                                StringBuffer stringBuffer2 = new StringBuffer();
                                stringBuffer2.append("Command line did not return any info for command ");
                                stringBuffer2.append(Arrays.asList(strArr));
                                throw new IOException(stringBuffer2.toString());
                            }
                            IOUtils.closeQuietly(inputStream);
                            IOUtils.closeQuietly(outputStream);
                            IOUtils.closeQuietly(errorStream);
                            IOUtils.closeQuietly((Reader) bufferedReader);
                            processOpenProcess.destroy();
                            return arrayList;
                        } catch (InterruptedException e10) {
                            e = e10;
                            inputStream2 = processOpenProcess;
                            bufferedReader = bufferedReader;
                            try {
                                StringBuffer stringBuffer3 = new StringBuffer();
                                stringBuffer3.append("Command line threw an InterruptedException '");
                                stringBuffer3.append(e.getMessage());
                                stringBuffer3.append("' for command ");
                                stringBuffer3.append(Arrays.asList(strArr));
                                throw new IOException(stringBuffer3.toString());
                            } catch (Throwable th2) {
                                th = th2;
                                processOpenProcess = inputStream2;
                                inputStream2 = inputStream;
                                r72 = bufferedReader;
                                IOUtils.closeQuietly(inputStream2);
                                IOUtils.closeQuietly(outputStream);
                                IOUtils.closeQuietly(errorStream);
                                IOUtils.closeQuietly((Reader) r72);
                                if (processOpenProcess != null) {
                                    processOpenProcess.destroy();
                                }
                                throw th;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            inputStream2 = inputStream;
                            r72 = bufferedReader;
                            IOUtils.closeQuietly(inputStream2);
                            IOUtils.closeQuietly(outputStream);
                            IOUtils.closeQuietly(errorStream);
                            IOUtils.closeQuietly((Reader) r72);
                            if (processOpenProcess != null) {
                            }
                            throw th;
                        }
                    } catch (InterruptedException e11) {
                        e = e11;
                        bufferedReader = 0;
                    } catch (Throwable th4) {
                        th = th4;
                        bufferedReader = 0;
                    }
                } catch (InterruptedException e12) {
                    e = e12;
                    errorStream = null;
                    bufferedReader = errorStream;
                    inputStream2 = processOpenProcess;
                    bufferedReader = bufferedReader;
                    StringBuffer stringBuffer32 = new StringBuffer();
                    stringBuffer32.append("Command line threw an InterruptedException '");
                    stringBuffer32.append(e.getMessage());
                    stringBuffer32.append("' for command ");
                    stringBuffer32.append(Arrays.asList(strArr));
                    throw new IOException(stringBuffer32.toString());
                } catch (Throwable th5) {
                    th = th5;
                    errorStream = null;
                    bufferedReader = errorStream;
                    inputStream2 = inputStream;
                    r72 = bufferedReader;
                    IOUtils.closeQuietly(inputStream2);
                    IOUtils.closeQuietly(outputStream);
                    IOUtils.closeQuietly(errorStream);
                    IOUtils.closeQuietly((Reader) r72);
                    if (processOpenProcess != null) {
                    }
                    throw th;
                }
            } catch (InterruptedException e13) {
                e = e13;
                outputStream = null;
                errorStream = outputStream;
                bufferedReader = errorStream;
                inputStream2 = processOpenProcess;
                bufferedReader = bufferedReader;
                StringBuffer stringBuffer322 = new StringBuffer();
                stringBuffer322.append("Command line threw an InterruptedException '");
                stringBuffer322.append(e.getMessage());
                stringBuffer322.append("' for command ");
                stringBuffer322.append(Arrays.asList(strArr));
                throw new IOException(stringBuffer322.toString());
            } catch (Throwable th6) {
                th = th6;
                outputStream = null;
                errorStream = null;
            }
        } catch (InterruptedException e14) {
            e = e14;
            inputStream = null;
            outputStream = null;
        } catch (Throwable th7) {
            th = th7;
            outputStream = null;
            errorStream = outputStream;
            r72 = errorStream;
            IOUtils.closeQuietly(inputStream2);
            IOUtils.closeQuietly(outputStream);
            IOUtils.closeQuietly(errorStream);
            IOUtils.closeQuietly((Reader) r72);
            if (processOpenProcess != null) {
            }
            throw th;
        }
    }
}
