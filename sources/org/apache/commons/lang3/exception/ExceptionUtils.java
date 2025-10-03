package org.apache.commons.lang3.exception;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;

/* loaded from: classes.dex */
public class ExceptionUtils {
    private static final String[] CAUSE_METHOD_NAMES = {"getCause", "getNextException", "getTargetException", "getException", "getSourceException", "getRootCause", "getCausedByException", "getNested", "getLinkedException", "getNestedException", "getLinkedCause", "getThrowable"};
    static final String WRAPPED_MARKER = " [wrapped] ";

    @Deprecated
    public static Throwable getCause(Throwable th) {
        return getCause(th, CAUSE_METHOD_NAMES);
    }

    private static Throwable getCauseUsingMethodName(Throwable th, String str) throws NoSuchMethodException, SecurityException {
        Method method;
        try {
            method = th.getClass().getMethod(str, new Class[0]);
        } catch (NoSuchMethodException | SecurityException unused) {
            method = null;
        }
        if (method != null && Throwable.class.isAssignableFrom(method.getReturnType())) {
            try {
                return (Throwable) method.invoke(th, new Object[0]);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException unused2) {
            }
        }
        return null;
    }

    @Deprecated
    public static String[] getDefaultCauseMethodNames() {
        return (String[]) ArrayUtils.clone(CAUSE_METHOD_NAMES);
    }

    public static String getMessage(Throwable th) {
        if (th == null) {
            return "";
        }
        return ClassUtils.getShortClassName(th, null) + ": " + StringUtils.defaultString(th.getMessage());
    }

    public static Throwable getRootCause(Throwable th) {
        List<Throwable> throwableList = getThrowableList(th);
        if (throwableList.size() < 2) {
            return null;
        }
        return throwableList.get(throwableList.size() - 1);
    }

    public static String getRootCauseMessage(Throwable th) {
        Throwable rootCause = getRootCause(th);
        if (rootCause != null) {
            th = rootCause;
        }
        return getMessage(th);
    }

    public static String[] getRootCauseStackTrace(Throwable th) {
        List<String> stackFrameList;
        if (th == null) {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        }
        Throwable[] throwables = getThrowables(th);
        int length = throwables.length;
        ArrayList arrayList = new ArrayList();
        int i9 = length - 1;
        List<String> stackFrameList2 = getStackFrameList(throwables[i9]);
        while (true) {
            length--;
            if (length < 0) {
                return (String[]) arrayList.toArray(new String[arrayList.size()]);
            }
            if (length != 0) {
                stackFrameList = getStackFrameList(throwables[length - 1]);
                removeCommonFrames(stackFrameList2, stackFrameList);
            } else {
                stackFrameList = stackFrameList2;
            }
            if (length == i9) {
                arrayList.add(throwables[length].toString());
            } else {
                arrayList.add(WRAPPED_MARKER + throwables[length].toString());
            }
            for (int i10 = 0; i10 < stackFrameList2.size(); i10++) {
                arrayList.add(stackFrameList2.get(i10));
            }
            stackFrameList2 = stackFrameList;
        }
    }

    public static List<String> getStackFrameList(Throwable th) {
        StringTokenizer stringTokenizer = new StringTokenizer(getStackTrace(th), SystemUtils.LINE_SEPARATOR);
        ArrayList arrayList = new ArrayList();
        boolean z8 = false;
        while (stringTokenizer.hasMoreTokens()) {
            String strNextToken = stringTokenizer.nextToken();
            int iIndexOf = strNextToken.indexOf("at");
            if (iIndexOf != -1 && strNextToken.substring(0, iIndexOf).trim().isEmpty()) {
                arrayList.add(strNextToken);
                z8 = true;
            } else if (z8) {
                break;
            }
        }
        return arrayList;
    }

    public static String[] getStackFrames(Throwable th) {
        return th == null ? ArrayUtils.EMPTY_STRING_ARRAY : getStackFrames(getStackTrace(th));
    }

    public static String getStackTrace(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter((Writer) stringWriter, true));
        return stringWriter.getBuffer().toString();
    }

    public static int getThrowableCount(Throwable th) {
        return getThrowableList(th).size();
    }

    public static List<Throwable> getThrowableList(Throwable th) {
        ArrayList arrayList = new ArrayList();
        while (th != null && !arrayList.contains(th)) {
            arrayList.add(th);
            th = getCause(th);
        }
        return arrayList;
    }

    public static Throwable[] getThrowables(Throwable th) {
        List<Throwable> throwableList = getThrowableList(th);
        return (Throwable[]) throwableList.toArray(new Throwable[throwableList.size()]);
    }

    private static int indexOf(Throwable th, Class<?> cls, int i9, boolean z8) {
        if (th != null && cls != null) {
            if (i9 < 0) {
                i9 = 0;
            }
            Throwable[] throwables = getThrowables(th);
            if (i9 >= throwables.length) {
                return -1;
            }
            if (z8) {
                while (i9 < throwables.length) {
                    if (cls.isAssignableFrom(throwables[i9].getClass())) {
                        return i9;
                    }
                    i9++;
                }
            } else {
                while (i9 < throwables.length) {
                    if (cls.equals(throwables[i9].getClass())) {
                        return i9;
                    }
                    i9++;
                }
            }
        }
        return -1;
    }

    public static int indexOfThrowable(Throwable th, Class<?> cls) {
        return indexOf(th, cls, 0, false);
    }

    public static int indexOfType(Throwable th, Class<?> cls) {
        return indexOf(th, cls, 0, true);
    }

    public static void printRootCauseStackTrace(Throwable th) {
        printRootCauseStackTrace(th, System.err);
    }

    public static void removeCommonFrames(List<String> list, List<String> list2) {
        if (list == null || list2 == null) {
            throw new IllegalArgumentException("The List must not be null");
        }
        int size = list.size() - 1;
        for (int size2 = list2.size() - 1; size >= 0 && size2 >= 0; size2--) {
            if (list.get(size).equals(list2.get(size2))) {
                list.remove(size);
            }
            size--;
        }
    }

    @Deprecated
    public static Throwable getCause(Throwable th, String[] strArr) {
        Throwable causeUsingMethodName;
        if (th == null) {
            return null;
        }
        if (strArr == null) {
            strArr = CAUSE_METHOD_NAMES;
        }
        for (String str : strArr) {
            if (str != null && (causeUsingMethodName = getCauseUsingMethodName(th, str)) != null) {
                return causeUsingMethodName;
            }
        }
        return null;
    }

    public static int indexOfThrowable(Throwable th, Class<?> cls, int i9) {
        return indexOf(th, cls, i9, false);
    }

    public static int indexOfType(Throwable th, Class<?> cls, int i9) {
        return indexOf(th, cls, i9, true);
    }

    public static void printRootCauseStackTrace(Throwable th, PrintStream printStream) {
        if (th == null) {
            return;
        }
        if (printStream == null) {
            throw new IllegalArgumentException("The PrintStream must not be null");
        }
        for (String str : getRootCauseStackTrace(th)) {
            printStream.println(str);
        }
        printStream.flush();
    }

    public static String[] getStackFrames(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, SystemUtils.LINE_SEPARATOR);
        ArrayList arrayList = new ArrayList();
        while (stringTokenizer.hasMoreTokens()) {
            arrayList.add(stringTokenizer.nextToken());
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public static void printRootCauseStackTrace(Throwable th, PrintWriter printWriter) {
        if (th == null) {
            return;
        }
        if (printWriter != null) {
            for (String str : getRootCauseStackTrace(th)) {
                printWriter.println(str);
            }
            printWriter.flush();
            return;
        }
        throw new IllegalArgumentException("The PrintWriter must not be null");
    }
}
