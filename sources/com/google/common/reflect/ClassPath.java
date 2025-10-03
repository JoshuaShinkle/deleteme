package com.google.common.reflect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.CharMatcher;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Splitter;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.collect.MultimapBuilder;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.Sets;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.p039io.ByteSource;
import com.google.common.p039io.CharSource;
import com.google.common.p039io.Resources;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.logging.Logger;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.p159io.IOUtils;

@Beta
/* loaded from: classes2.dex */
public final class ClassPath {
    private static final String CLASS_FILE_NAME_EXTENSION = ".class";
    private final ImmutableSet<ResourceInfo> resources;
    private static final Logger logger = Logger.getLogger(ClassPath.class.getName());
    private static final Predicate<ClassInfo> IS_TOP_LEVEL = new Predicate<ClassInfo>() { // from class: com.google.common.reflect.ClassPath.1
        @Override // com.google.common.base.Predicate
        public boolean apply(ClassInfo classInfo) {
            return classInfo.className.indexOf(36) == -1;
        }
    };
    private static final Splitter CLASS_PATH_ATTRIBUTE_SEPARATOR = Splitter.m17559on(StringUtils.SPACE).omitEmptyStrings();

    @Beta
    public static final class ClassInfo extends ResourceInfo {
        private final String className;

        public ClassInfo(String str, ClassLoader classLoader) {
            super(str, classLoader);
            this.className = ClassPath.getClassName(str);
        }

        public String getName() {
            return this.className;
        }

        public String getPackageName() {
            return Reflection.getPackageName(this.className);
        }

        public String getSimpleName() {
            int iLastIndexOf = this.className.lastIndexOf(36);
            if (iLastIndexOf != -1) {
                return CharMatcher.digit().trimLeadingFrom(this.className.substring(iLastIndexOf + 1));
            }
            String packageName = getPackageName();
            return packageName.isEmpty() ? this.className : this.className.substring(packageName.length() + 1);
        }

        public Class<?> load() {
            try {
                return this.loader.loadClass(this.className);
            } catch (ClassNotFoundException e9) {
                throw new IllegalStateException(e9);
            }
        }

        @Override // com.google.common.reflect.ClassPath.ResourceInfo
        public String toString() {
            return this.className;
        }
    }

    @Beta
    public static class ResourceInfo {
        final ClassLoader loader;
        private final String resourceName;

        public ResourceInfo(String str, ClassLoader classLoader) {
            this.resourceName = (String) Preconditions.checkNotNull(str);
            this.loader = (ClassLoader) Preconditions.checkNotNull(classLoader);
        }

        /* renamed from: of */
        public static ResourceInfo m17708of(String str, ClassLoader classLoader) {
            return str.endsWith(ClassPath.CLASS_FILE_NAME_EXTENSION) ? new ClassInfo(str, classLoader) : new ResourceInfo(str, classLoader);
        }

        public final ByteSource asByteSource() {
            return Resources.asByteSource(url());
        }

        public final CharSource asCharSource(Charset charset) {
            return Resources.asCharSource(url(), charset);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof ResourceInfo)) {
                return false;
            }
            ResourceInfo resourceInfo = (ResourceInfo) obj;
            return this.resourceName.equals(resourceInfo.resourceName) && this.loader == resourceInfo.loader;
        }

        public final String getResourceName() {
            return this.resourceName;
        }

        public int hashCode() {
            return this.resourceName.hashCode();
        }

        public String toString() {
            return this.resourceName;
        }

        public final URL url() {
            URL resource = this.loader.getResource(this.resourceName);
            if (resource != null) {
                return resource;
            }
            throw new NoSuchElementException(this.resourceName);
        }
    }

    private ClassPath(ImmutableSet<ResourceInfo> immutableSet) {
        this.resources = immutableSet;
    }

    public static ClassPath from(ClassLoader classLoader) throws IOException {
        DefaultScanner defaultScanner = new DefaultScanner();
        defaultScanner.scan(classLoader);
        return new ClassPath(defaultScanner.getResources());
    }

    @VisibleForTesting
    public static String getClassName(String str) {
        return str.substring(0, str.length() - 6).replace(IOUtils.DIR_SEPARATOR_UNIX, ClassUtils.PACKAGE_SEPARATOR_CHAR);
    }

    @VisibleForTesting
    public static File toFile(URL url) {
        Preconditions.checkArgument(url.getProtocol().equals("file"));
        try {
            return new File(url.toURI());
        } catch (URISyntaxException unused) {
            return new File(url.getPath());
        }
    }

    public ImmutableSet<ClassInfo> getAllClasses() {
        return FluentIterable.from(this.resources).filter(ClassInfo.class).toSet();
    }

    public ImmutableSet<ResourceInfo> getResources() {
        return this.resources;
    }

    public ImmutableSet<ClassInfo> getTopLevelClasses() {
        return FluentIterable.from(this.resources).filter(ClassInfo.class).filter(IS_TOP_LEVEL).toSet();
    }

    public ImmutableSet<ClassInfo> getTopLevelClassesRecursive(String str) {
        Preconditions.checkNotNull(str);
        String str2 = str + ClassUtils.PACKAGE_SEPARATOR_CHAR;
        ImmutableSet.Builder builder = ImmutableSet.builder();
        UnmodifiableIterator<ClassInfo> it = getTopLevelClasses().iterator();
        while (it.hasNext()) {
            ClassInfo next = it.next();
            if (next.getName().startsWith(str2)) {
                builder.add((ImmutableSet.Builder) next);
            }
        }
        return builder.build();
    }

    public static abstract class Scanner {
        private final Set<File> scannedUris = Sets.newHashSet();

        @VisibleForTesting
        public static ImmutableMap<File, ClassLoader> getClassPathEntries(ClassLoader classLoader) {
            LinkedHashMap linkedHashMapNewLinkedHashMap = Maps.newLinkedHashMap();
            ClassLoader parent = classLoader.getParent();
            if (parent != null) {
                linkedHashMapNewLinkedHashMap.putAll(getClassPathEntries(parent));
            }
            if (classLoader instanceof URLClassLoader) {
                for (URL url : ((URLClassLoader) classLoader).getURLs()) {
                    if (url.getProtocol().equals("file")) {
                        File file = ClassPath.toFile(url);
                        if (!linkedHashMapNewLinkedHashMap.containsKey(file)) {
                            linkedHashMapNewLinkedHashMap.put(file, classLoader);
                        }
                    }
                }
            }
            return ImmutableMap.copyOf((Map) linkedHashMapNewLinkedHashMap);
        }

        @VisibleForTesting
        public static URL getClassPathEntry(File file, String str) {
            return new URL(file.toURI().toURL(), str);
        }

        @VisibleForTesting
        public static ImmutableSet<File> getClassPathFromManifest(File file, Manifest manifest) {
            if (manifest == null) {
                return ImmutableSet.m17615of();
            }
            ImmutableSet.Builder builder = ImmutableSet.builder();
            String value = manifest.getMainAttributes().getValue(Attributes.Name.CLASS_PATH.toString());
            if (value != null) {
                for (String str : ClassPath.CLASS_PATH_ATTRIBUTE_SEPARATOR.split(value)) {
                    try {
                        URL classPathEntry = getClassPathEntry(file, str);
                        if (classPathEntry.getProtocol().equals("file")) {
                            builder.add((ImmutableSet.Builder) ClassPath.toFile(classPathEntry));
                        }
                    } catch (MalformedURLException unused) {
                        ClassPath.logger.warning("Invalid Class-Path entry: " + str);
                    }
                }
            }
            return builder.build();
        }

        private void scanFrom(File file, ClassLoader classLoader) throws IOException {
            try {
                if (file.exists()) {
                    if (file.isDirectory()) {
                        scanDirectory(classLoader, file);
                    } else {
                        scanJar(file, classLoader);
                    }
                }
            } catch (SecurityException e9) {
                ClassPath.logger.warning("Cannot access " + file + ": " + e9);
            }
        }

        private void scanJar(File file, ClassLoader classLoader) throws IOException {
            try {
                JarFile jarFile = new JarFile(file);
                try {
                    UnmodifiableIterator<File> it = getClassPathFromManifest(file, jarFile.getManifest()).iterator();
                    while (it.hasNext()) {
                        scan(it.next(), classLoader);
                    }
                    scanJarFile(classLoader, jarFile);
                } finally {
                    try {
                        jarFile.close();
                    } catch (IOException unused) {
                    }
                }
            } catch (IOException unused2) {
            }
        }

        public final void scan(ClassLoader classLoader) throws IOException {
            UnmodifiableIterator<Map.Entry<File, ClassLoader>> it = getClassPathEntries(classLoader).entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<File, ClassLoader> next = it.next();
                scan(next.getKey(), next.getValue());
            }
        }

        public abstract void scanDirectory(ClassLoader classLoader, File file);

        public abstract void scanJarFile(ClassLoader classLoader, JarFile jarFile);

        @VisibleForTesting
        public final void scan(File file, ClassLoader classLoader) throws IOException {
            if (this.scannedUris.add(file.getCanonicalFile())) {
                scanFrom(file, classLoader);
            }
        }
    }

    public ImmutableSet<ClassInfo> getTopLevelClasses(String str) {
        Preconditions.checkNotNull(str);
        ImmutableSet.Builder builder = ImmutableSet.builder();
        UnmodifiableIterator<ClassInfo> it = getTopLevelClasses().iterator();
        while (it.hasNext()) {
            ClassInfo next = it.next();
            if (next.getPackageName().equals(str)) {
                builder.add((ImmutableSet.Builder) next);
            }
        }
        return builder.build();
    }

    @VisibleForTesting
    public static final class DefaultScanner extends Scanner {
        private final SetMultimap<ClassLoader, String> resources = MultimapBuilder.hashKeys().linkedHashSetValues().build();

        public ImmutableSet<ResourceInfo> getResources() {
            ImmutableSet.Builder builder = ImmutableSet.builder();
            for (Map.Entry<ClassLoader, String> entry : this.resources.entries()) {
                builder.add((ImmutableSet.Builder) ResourceInfo.m17708of(entry.getValue(), entry.getKey()));
            }
            return builder.build();
        }

        @Override // com.google.common.reflect.ClassPath.Scanner
        public void scanDirectory(ClassLoader classLoader, File file) throws IOException {
            HashSet hashSet = new HashSet();
            hashSet.add(file.getCanonicalFile());
            scanDirectory(file, classLoader, "", hashSet);
        }

        @Override // com.google.common.reflect.ClassPath.Scanner
        public void scanJarFile(ClassLoader classLoader, JarFile jarFile) {
            Enumeration<JarEntry> enumerationEntries = jarFile.entries();
            while (enumerationEntries.hasMoreElements()) {
                JarEntry jarEntryNextElement = enumerationEntries.nextElement();
                if (!jarEntryNextElement.isDirectory() && !jarEntryNextElement.getName().equals("META-INF/MANIFEST.MF")) {
                    this.resources.get((SetMultimap<ClassLoader, String>) classLoader).add(jarEntryNextElement.getName());
                }
            }
        }

        private void scanDirectory(File file, ClassLoader classLoader, String str, Set<File> set) throws IOException {
            File[] fileArrListFiles = file.listFiles();
            if (fileArrListFiles == null) {
                ClassPath.logger.warning("Cannot read directory " + file);
                return;
            }
            for (File file2 : fileArrListFiles) {
                String name = file2.getName();
                if (file2.isDirectory()) {
                    File canonicalFile = file2.getCanonicalFile();
                    if (set.add(canonicalFile)) {
                        scanDirectory(canonicalFile, classLoader, str + name + "/", set);
                        set.remove(canonicalFile);
                    }
                } else {
                    String str2 = str + name;
                    if (!str2.equals("META-INF/MANIFEST.MF")) {
                        this.resources.get((SetMultimap<ClassLoader, String>) classLoader).add(str2);
                    }
                }
            }
        }
    }
}
