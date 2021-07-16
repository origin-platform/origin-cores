package com.jyusun.origin.core.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.UtilityClass;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 异常工具类
 *
 * @author jyusun
 */
@UtilityClass
public final class ThrowableUtil {

    /**
     * 获得完整消息，包括异常名，消息格式为：{SimpleClassName}: {ThrowableMessage}
     *
     * @param e 异常
     * @return 完整消息
     */
    public static String getMessage(Throwable e) {
        if (Objects.isNull(e)) {
            return StringUtil.NULL;
        }
        return String.format("{}: {}", e.getClass().getSimpleName(), e.getMessage());
    }

    /**
     * 获得消息，调用异常类的getMessage方法
     *
     * @param e 异常
     * @return 消息
     */
    public static String getSimpleMessage(Throwable e) {
        return (Objects.isNull(e)) ? StringUtil.NULL : e.getMessage();
    }

    /**
     * 使用运行时异常包装编译异常<br>
     * <p>
     * 如果
     *
     * @param throwable 异常
     * @return 运行时异常
     */
    public static RuntimeException wrapRuntime(Throwable throwable) {
        if (throwable instanceof RuntimeException) {
            return (RuntimeException) throwable;
        }
        return new RuntimeException(throwable);
    }


    /**
     * 包装异常并重新抛出此异常<br>
     * {@link RuntimeException} 和{@link Error}
     * 直接抛出，其它检查异常包装为{@link UndeclaredThrowableException} 后抛出
     *
     * @param throwable 异常
     */
    public static void wrapAndThrow(Throwable throwable) {
        if (throwable instanceof RuntimeException) {
            throw (RuntimeException) throwable;
        }
        if (throwable instanceof Error) {
            throw (Error) throwable;
        }
        throw new UndeclaredThrowableException(throwable);
    }

    /**
     * 剥离反射引发的InvocationTargetException、UndeclaredThrowableException中间异常，返回业务本身的异常
     *
     * @param wrapped 包装的异常
     * @return 剥离后的异常
     */
    public static Throwable unwrap(Throwable wrapped) {
        Throwable unwrapped = wrapped;
        while (true) {
            if (unwrapped instanceof InvocationTargetException) {
                unwrapped = ((InvocationTargetException) unwrapped).getTargetException();
            } else if (unwrapped instanceof UndeclaredThrowableException) {
                unwrapped = ((UndeclaredThrowableException) unwrapped).getUndeclaredThrowable();
            } else {
                return unwrapped;
            }
        }
    }

    /**
     * 获取当前栈信息
     *
     * @return 当前栈信息
     */
    public static StackTraceElement[] getStackElements() {
        return Thread.currentThread().getStackTrace();
    }

    /**
     * 获取指定层的堆栈信息
     *
     * @param i 层数
     * @return 指定层的堆栈信息
     * @since 4.1.4
     */
    public static StackTraceElement getStackElement(int i) {
        return getStackElements()[i];
    }

    /**
     * 获取入口堆栈信息
     *
     * @return 入口堆栈信息
     * @since 4.1.4
     */
    public static StackTraceElement getRootStackElement() {
        final StackTraceElement[] stackElements = getStackElements();
        return stackElements[stackElements.length - 1];
    }


    /**
     * 判断是否由指定异常类引起
     *
     * @param throwable    异常
     * @param causeClasses 定义的引起异常的类
     * @return 是否由指定异常类引起
     * @since 4.1.13
     */
    @SuppressWarnings("unchecked")
    public static boolean isCausedBy(Throwable throwable, Class<? extends Exception>... causeClasses) {
        return Objects.nonNull(getCausedBy(throwable, causeClasses));
    }

    /**
     * 获取由指定异常类引起的异常
     *
     * @param throwable    异常
     * @param causeClasses 定义的引起异常的类
     * @return 是否由指定异常类引起
     * @since 4.1.13
     */
    @SuppressWarnings("unchecked")
    public static Throwable getCausedBy(Throwable throwable, Class<? extends Exception>... causeClasses) {
        Throwable cause = throwable;
        while (cause != null) {
            for (Class<? extends Exception> causeClass : causeClasses) {
                if (causeClass.isInstance(cause)) {
                    return cause;
                }
            }
            cause = cause.getCause();
        }
        return null;
    }

    /**
     * 判断指定异常是否来自或者包含指定异常
     *
     * @param throwable      异常
     * @param exceptionClass 定义的引起异常的类
     * @return true 来自或者包含
     * @since 4.3.2
     */
    public static boolean isFromOrSuppressedThrowable(Throwable throwable, Class<? extends Throwable> exceptionClass) {
        return convertFromOrSuppressedThrowable(throwable, exceptionClass, true) != null;
    }

    /**
     * 判断指定异常是否来自或者包含指定异常
     *
     * @param throwable      异常
     * @param exceptionClass 定义的引起异常的类
     * @param checkCause     判断cause
     * @return true 来自或者包含
     * @since 4.4.1
     */
    public static boolean isFromOrSuppressedThrowable(Throwable throwable, Class<? extends Throwable> exceptionClass,
                                                      boolean checkCause) {
        return convertFromOrSuppressedThrowable(throwable, exceptionClass, checkCause) != null;
    }

    /**
     * 转化指定异常为来自或者包含指定异常
     *
     * @param <T>            异常类型
     * @param throwable      异常
     * @param exceptionClass 定义的引起异常的类
     * @return 结果为null 不是来自或者包含
     * @since 4.3.2
     */
    public static <T extends Throwable> T convertFromOrSuppressedThrowable(Throwable throwable,
                                                                           Class<T> exceptionClass) {
        return convertFromOrSuppressedThrowable(throwable, exceptionClass, true);
    }

    /**
     * 转化指定异常为来自或者包含指定异常
     *
     * @param <T>            异常类型
     * @param throwable      异常
     * @param exceptionClass 定义的引起异常的类
     * @param checkCause     判断cause
     * @return 结果为null 不是来自或者包含
     * @since 4.4.1
     */
    @SuppressWarnings("unchecked")
    public static <T extends Throwable> T convertFromOrSuppressedThrowable(Throwable throwable,
                                                                           Class<T> exceptionClass,
                                                                           boolean checkCause) {
        if (throwable == null || exceptionClass == null) {
            return null;
        }
        if (exceptionClass.isAssignableFrom(throwable.getClass())) {
            return (T) throwable;
        }
        if (checkCause) {
            Throwable cause = throwable.getCause();
            if (cause != null && exceptionClass.isAssignableFrom(cause.getClass())) {
                return (T) cause;
            }
        }
        Throwable[] throwables = throwable.getSuppressed();
        if (ArrayUtil.isNotEmpty(throwables)) {
            for (Throwable throwable1 : throwables) {
                if (exceptionClass.isAssignableFrom(throwable1.getClass())) {
                    return (T) throwable1;
                }
            }
        }
        return null;
    }

    /**
     * 获取异常链上所有异常的集合，如果{@link Throwable} 对象没有cause，返回只有一个节点的List<br>
     * 如果传入null，返回空集合
     *
     * <p>
     * 此方法来自Apache-Commons-Lang3
     * </p>
     *
     * @param throwable 异常对象，可以为null
     * @return 异常链中所有异常集合
     * @since 4.6.2
     */
    public static List<Throwable> getThrowableList(Throwable throwable) {
        List<Throwable> list = new ArrayList<>();
        while (throwable != null && !list.contains(throwable)) {
            list.add(throwable);
            throwable = throwable.getCause();
        }
        return list;
    }

    /**
     * 获取异常链中最尾端的异常，即异常最早发生的异常对象。<br>
     * 此方法通过调用{@link Throwable#getCause()} 直到没有cause为止，如果异常本身没有cause，返回异常本身<br>
     * 传入null返回也为null
     *
     * <p>
     * 此方法来自Apache-Commons-Lang3
     * </p>
     *
     * @param throwable 异常对象，可能为null
     * @return 最尾端异常，传入null参数返回也为null
     */
    public static Throwable getRootCause(final Throwable throwable) {
        final List<Throwable> list = getThrowableList(throwable);
        return list.size() < 1 ? null : list.get(list.size() - 1);
    }

    /**
     * 获取异常链中最尾端的异常的消息，消息格式为：{SimpleClassName}: {ThrowableMessage}
     *
     * @param th 异常
     * @return 消息
     * @since 1.0.0
     */
    public static String getRootCauseMessage(final Throwable th) {
        return getMessage(getRootCause(th));
    }

}
