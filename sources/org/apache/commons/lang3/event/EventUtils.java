package org.apache.commons.lang3.event;

import com.google.android.gms.analytics.ecommerce.ProductAction;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang3.reflect.MethodUtils;

/* loaded from: classes.dex */
public class EventUtils {

    public static class EventBindingInvocationHandler implements InvocationHandler {
        private final Set<String> eventTypes;
        private final String methodName;
        private final Object target;

        public EventBindingInvocationHandler(Object obj, String str, String[] strArr) {
            this.target = obj;
            this.methodName = str;
            this.eventTypes = new HashSet(Arrays.asList(strArr));
        }

        private boolean hasMatchingParametersMethod(Method method) {
            return MethodUtils.getAccessibleMethod(this.target.getClass(), this.methodName, method.getParameterTypes()) != null;
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) {
            if (this.eventTypes.isEmpty() || this.eventTypes.contains(method.getName())) {
                return hasMatchingParametersMethod(method) ? MethodUtils.invokeMethod(this.target, this.methodName, objArr) : MethodUtils.invokeMethod(this.target, this.methodName, new Object[0]);
            }
            return null;
        }
    }

    public static <L> void addEventListener(Object obj, Class<L> cls, L l9) {
        try {
            MethodUtils.invokeMethod(obj, ProductAction.ACTION_ADD + cls.getSimpleName(), l9);
        } catch (IllegalAccessException unused) {
            throw new IllegalArgumentException("Class " + obj.getClass().getName() + " does not have an accessible add" + cls.getSimpleName() + " method which takes a parameter of type " + cls.getName() + ".");
        } catch (NoSuchMethodException unused2) {
            throw new IllegalArgumentException("Class " + obj.getClass().getName() + " does not have a public add" + cls.getSimpleName() + " method which takes a parameter of type " + cls.getName() + ".");
        } catch (InvocationTargetException e9) {
            throw new RuntimeException("Unable to add listener.", e9.getCause());
        }
    }

    public static <L> void bindEventsToMethod(Object obj, String str, Object obj2, Class<L> cls, String... strArr) {
        addEventListener(obj2, cls, cls.cast(Proxy.newProxyInstance(obj.getClass().getClassLoader(), new Class[]{cls}, new EventBindingInvocationHandler(obj, str, strArr))));
    }
}
