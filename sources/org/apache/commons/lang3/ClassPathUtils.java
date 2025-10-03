package org.apache.commons.lang3;

import org.apache.commons.p159io.IOUtils;

/* loaded from: classes.dex */
public class ClassPathUtils {
    public static String toFullyQualifiedName(Class<?> cls, String str) {
        Validate.notNull(cls, "Parameter '%s' must not be null!", "context");
        Validate.notNull(str, "Parameter '%s' must not be null!", "resourceName");
        return toFullyQualifiedName(cls.getPackage(), str);
    }

    public static String toFullyQualifiedPath(Class<?> cls, String str) {
        Validate.notNull(cls, "Parameter '%s' must not be null!", "context");
        Validate.notNull(str, "Parameter '%s' must not be null!", "resourceName");
        return toFullyQualifiedPath(cls.getPackage(), str);
    }

    public static String toFullyQualifiedName(Package r42, String str) {
        Validate.notNull(r42, "Parameter '%s' must not be null!", "context");
        Validate.notNull(str, "Parameter '%s' must not be null!", "resourceName");
        return r42.getName() + "." + str;
    }

    public static String toFullyQualifiedPath(Package r42, String str) {
        Validate.notNull(r42, "Parameter '%s' must not be null!", "context");
        Validate.notNull(str, "Parameter '%s' must not be null!", "resourceName");
        return r42.getName().replace(ClassUtils.PACKAGE_SEPARATOR_CHAR, IOUtils.DIR_SEPARATOR_UNIX) + "/" + str;
    }
}
