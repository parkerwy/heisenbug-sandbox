package lab.heisenbug.sandbox.javassist;

import javassist.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by IntelliJ IDEA.
 * User: parker
 * Date: Jul 2, 2010
 * Time: 10:02:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class AnywhereLogger {

    private static final Logger logger = LoggerFactory.getLogger(AnywhereLogger.class);

    private static CtClass TOP_EXCEPTION;

    static {
        try {
            ClassPool pool = ClassPool.getDefault();
            pool.importPackage("org.heisenbug.sandbox.javassist");
            TOP_EXCEPTION = pool.get("java.lang.Throwable");
        } catch (NotFoundException e) {
            logger.error(e.getMessage(), e);
        }
    }

    private static String getInvoker() {
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        StackTraceElement invoker = elements[2];
        return invoker.getClassName() + "." + invoker.getMethodName();
    }


    public static void dumpInput(Object[] params) {
        String invoker = getInvoker();
        logger.info("{} IN: {}", invoker, params);
    }


    public static void dumpOutput(Object result) {
        String invoker = getInvoker();
        logger.info("{} OUT: {}", invoker, result);
    }

    public static void dumpVoid() {
        String invoker = getInvoker();
        logger.info("{} OUT: void", invoker);
    }

    public static void dumpException(Throwable t) {
        String invoker = getInvoker();
        logger.info(invoker, t);
    }

    public static void inject(CtClass klass, String methodName, CtClass[] paramTypes) throws NotFoundException, CannotCompileException {
        CtMethod method = klass.getDeclaredMethod(methodName, paramTypes);
        inject(method);
    }

    public static void inject(CtClass klass, String methodName) throws NotFoundException, CannotCompileException {
        CtMethod method = klass.getDeclaredMethod(methodName);
        inject(method);
    }

    public static void inject(CtClass klass) throws NotFoundException, CannotCompileException {
        CtConstructor[] constructors = klass.getConstructors();
        for (CtConstructor constructor : constructors) {
            inject(constructor);
        }
            
        CtMethod[] methods = klass.getDeclaredMethods();
        for (CtMethod method : methods) {
            inject(method);
        }
    }

    private static void inject(CtBehavior behavior) throws CannotCompileException, NotFoundException {
        behavior.insertBefore("{ AnywhereLogger.dumpInput($args); }");
        if (behavior instanceof CtMethod) {
            CtMethod method = (CtMethod) behavior;
            if (method.getReturnType() != CtClass.voidType) {
                behavior.insertAfter("{ AnywhereLogger.dumpOutput(($w)$_); }");
            } else {
                behavior.insertAfter("{ AnywhereLogger.dumpVoid(); }");
            }
        } else {
            behavior.insertAfter("{ AnywhereLogger.dumpVoid(); }");
        }

        behavior.addCatch("{ AnywhereLogger.dumpException($e); throw $e;}", TOP_EXCEPTION);
    }
}

