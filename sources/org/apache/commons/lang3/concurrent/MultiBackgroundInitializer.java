package org.apache.commons.lang3.concurrent;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.ExecutorService;

/* loaded from: classes.dex */
public class MultiBackgroundInitializer extends BackgroundInitializer<MultiBackgroundInitializerResults> {
    private final Map<String, BackgroundInitializer<?>> childInitializers;

    public static class MultiBackgroundInitializerResults {
        private final Map<String, ConcurrentException> exceptions;
        private final Map<String, BackgroundInitializer<?>> initializers;
        private final Map<String, Object> resultObjects;

        private BackgroundInitializer<?> checkName(String str) {
            BackgroundInitializer<?> backgroundInitializer = this.initializers.get(str);
            if (backgroundInitializer != null) {
                return backgroundInitializer;
            }
            throw new NoSuchElementException("No child initializer with name " + str);
        }

        public ConcurrentException getException(String str) {
            checkName(str);
            return this.exceptions.get(str);
        }

        public BackgroundInitializer<?> getInitializer(String str) {
            return checkName(str);
        }

        public Object getResultObject(String str) {
            checkName(str);
            return this.resultObjects.get(str);
        }

        public Set<String> initializerNames() {
            return Collections.unmodifiableSet(this.initializers.keySet());
        }

        public boolean isException(String str) {
            checkName(str);
            return this.exceptions.containsKey(str);
        }

        public boolean isSuccessful() {
            return this.exceptions.isEmpty();
        }

        private MultiBackgroundInitializerResults(Map<String, BackgroundInitializer<?>> map, Map<String, Object> map2, Map<String, ConcurrentException> map3) {
            this.initializers = map;
            this.resultObjects = map2;
            this.exceptions = map3;
        }
    }

    public MultiBackgroundInitializer() {
        this.childInitializers = new HashMap();
    }

    public void addInitializer(String str, BackgroundInitializer<?> backgroundInitializer) {
        if (str == null) {
            throw new IllegalArgumentException("Name of child initializer must not be null!");
        }
        if (backgroundInitializer == null) {
            throw new IllegalArgumentException("Child initializer must not be null!");
        }
        synchronized (this) {
            if (isStarted()) {
                throw new IllegalStateException("addInitializer() must not be called after start()!");
            }
            this.childInitializers.put(str, backgroundInitializer);
        }
    }

    @Override // org.apache.commons.lang3.concurrent.BackgroundInitializer
    public int getTaskCount() {
        Iterator<BackgroundInitializer<?>> it = this.childInitializers.values().iterator();
        int taskCount = 1;
        while (it.hasNext()) {
            taskCount += it.next().getTaskCount();
        }
        return taskCount;
    }

    @Override // org.apache.commons.lang3.concurrent.BackgroundInitializer
    public MultiBackgroundInitializerResults initialize() {
        HashMap map;
        synchronized (this) {
            map = new HashMap(this.childInitializers);
        }
        ExecutorService activeExecutor = getActiveExecutor();
        for (BackgroundInitializer backgroundInitializer : map.values()) {
            if (backgroundInitializer.getExternalExecutor() == null) {
                backgroundInitializer.setExternalExecutor(activeExecutor);
            }
            backgroundInitializer.start();
        }
        HashMap map2 = new HashMap();
        HashMap map3 = new HashMap();
        for (Map.Entry entry : map.entrySet()) {
            try {
                map2.put(entry.getKey(), ((BackgroundInitializer) entry.getValue()).get());
            } catch (ConcurrentException e9) {
                map3.put(entry.getKey(), e9);
            }
        }
        return new MultiBackgroundInitializerResults(map, map2, map3);
    }

    public MultiBackgroundInitializer(ExecutorService executorService) {
        super(executorService);
        this.childInitializers = new HashMap();
    }
}
