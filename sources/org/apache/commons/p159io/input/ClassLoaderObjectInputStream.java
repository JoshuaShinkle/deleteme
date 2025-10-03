package org.apache.commons.p159io.input;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;

/* loaded from: classes.dex */
public class ClassLoaderObjectInputStream extends ObjectInputStream {
    private ClassLoader classLoader;

    public ClassLoaderObjectInputStream(ClassLoader classLoader, InputStream inputStream) {
        super(inputStream);
        this.classLoader = classLoader;
    }

    @Override // java.io.ObjectInputStream
    public Class resolveClass(ObjectStreamClass objectStreamClass) throws ClassNotFoundException {
        Class<?> cls = Class.forName(objectStreamClass.getName(), false, this.classLoader);
        return cls != null ? cls : super.resolveClass(objectStreamClass);
    }
}
