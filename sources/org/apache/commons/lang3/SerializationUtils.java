package org.apache.commons.lang3;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class SerializationUtils {

    public static class ClassLoaderAwareObjectInputStream extends ObjectInputStream {
        private static final Map<String, Class<?>> primitiveTypes = new HashMap();
        private final ClassLoader classLoader;

        public ClassLoaderAwareObjectInputStream(InputStream inputStream, ClassLoader classLoader) {
            super(inputStream);
            this.classLoader = classLoader;
            Map<String, Class<?>> map = primitiveTypes;
            map.put("byte", Byte.TYPE);
            map.put("short", Short.TYPE);
            map.put("int", Integer.TYPE);
            map.put("long", Long.TYPE);
            map.put("float", Float.TYPE);
            map.put("double", Double.TYPE);
            map.put("boolean", Boolean.TYPE);
            map.put("char", Character.TYPE);
            map.put("void", Void.TYPE);
        }

        @Override // java.io.ObjectInputStream
        public Class<?> resolveClass(ObjectStreamClass objectStreamClass) throws ClassNotFoundException {
            String name = objectStreamClass.getName();
            try {
                try {
                    return Class.forName(name, false, this.classLoader);
                } catch (ClassNotFoundException e9) {
                    Class<?> cls = primitiveTypes.get(name);
                    if (cls != null) {
                        return cls;
                    }
                    throw e9;
                }
            } catch (ClassNotFoundException unused) {
                return Class.forName(name, false, Thread.currentThread().getContextClassLoader());
            }
        }
    }

    public static <T extends Serializable> T clone(T t8) throws Throwable {
        ClassLoaderAwareObjectInputStream classLoaderAwareObjectInputStream;
        ClassLoaderAwareObjectInputStream classLoaderAwareObjectInputStream2 = null;
        if (t8 == null) {
            return null;
        }
        try {
            try {
                classLoaderAwareObjectInputStream = new ClassLoaderAwareObjectInputStream(new ByteArrayInputStream(serialize(t8)), t8.getClass().getClassLoader());
            } catch (Throwable th) {
                th = th;
            }
        } catch (IOException e9) {
            e = e9;
        } catch (ClassNotFoundException e10) {
            e = e10;
        }
        try {
            T t9 = (T) classLoaderAwareObjectInputStream.readObject();
            try {
                classLoaderAwareObjectInputStream.close();
                return t9;
            } catch (IOException e11) {
                throw new SerializationException("IOException on closing cloned object data InputStream.", e11);
            }
        } catch (IOException e12) {
            e = e12;
            throw new SerializationException("IOException while reading cloned object data", e);
        } catch (ClassNotFoundException e13) {
            e = e13;
            throw new SerializationException("ClassNotFoundException while reading cloned object data", e);
        } catch (Throwable th2) {
            th = th2;
            classLoaderAwareObjectInputStream2 = classLoaderAwareObjectInputStream;
            if (classLoaderAwareObjectInputStream2 != null) {
                try {
                    classLoaderAwareObjectInputStream2.close();
                } catch (IOException e14) {
                    throw new SerializationException("IOException on closing cloned object data InputStream.", e14);
                }
            }
            throw th;
        }
    }

    public static <T> T deserialize(InputStream inputStream) throws Throwable {
        ObjectInputStream objectInputStream;
        if (inputStream == null) {
            throw new IllegalArgumentException("The InputStream must not be null");
        }
        ObjectInputStream objectInputStream2 = null;
        try {
            try {
                objectInputStream = new ObjectInputStream(inputStream);
            } catch (Throwable th) {
                th = th;
            }
        } catch (IOException e9) {
            e = e9;
        } catch (ClassCastException e10) {
            e = e10;
        } catch (ClassNotFoundException e11) {
            e = e11;
        }
        try {
            T t8 = (T) objectInputStream.readObject();
            try {
                objectInputStream.close();
            } catch (IOException unused) {
            }
            return t8;
        } catch (IOException e12) {
            e = e12;
            throw new SerializationException(e);
        } catch (ClassCastException e13) {
            e = e13;
            throw new SerializationException(e);
        } catch (ClassNotFoundException e14) {
            e = e14;
            throw new SerializationException(e);
        } catch (Throwable th2) {
            th = th2;
            objectInputStream2 = objectInputStream;
            if (objectInputStream2 != null) {
                try {
                    objectInputStream2.close();
                } catch (IOException unused2) {
                }
            }
            throw th;
        }
    }

    public static <T extends Serializable> T roundtrip(T t8) {
        return (T) deserialize(serialize(t8));
    }

    public static void serialize(Serializable serializable, OutputStream outputStream) throws Throwable {
        ObjectOutputStream objectOutputStream;
        if (outputStream == null) {
            throw new IllegalArgumentException("The OutputStream must not be null");
        }
        ObjectOutputStream objectOutputStream2 = null;
        try {
            try {
                objectOutputStream = new ObjectOutputStream(outputStream);
            } catch (IOException e9) {
                e = e9;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            objectOutputStream.writeObject(serializable);
            try {
                objectOutputStream.close();
            } catch (IOException unused) {
            }
        } catch (IOException e10) {
            e = e10;
            objectOutputStream2 = objectOutputStream;
            throw new SerializationException(e);
        } catch (Throwable th2) {
            th = th2;
            objectOutputStream2 = objectOutputStream;
            if (objectOutputStream2 != null) {
                try {
                    objectOutputStream2.close();
                } catch (IOException unused2) {
                }
            }
            throw th;
        }
    }

    public static byte[] serialize(Serializable serializable) throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(512);
        serialize(serializable, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static <T> T deserialize(byte[] bArr) {
        if (bArr != null) {
            return (T) deserialize(new ByteArrayInputStream(bArr));
        }
        throw new IllegalArgumentException("The byte[] must not be null");
    }
}
