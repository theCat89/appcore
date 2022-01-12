package ru.horologer.base;

import java.util.function.Function;

/**
 * Class for executing method throwing RuntimeException
 * This class may be using if you not needed using variables from try in catch
 * There are no needs of finally if you don't throws exception in exception handler
 * if you do this i think you now what you do
 *
  * @param <T> type of method param
 * @param <R> returning type
 */
public class MethodExecutor<T, R> {

    T value;
    Function<T, R> main;
    Function<RuntimeException, R> exceptionHandler;

    public R execute() {
        try {
            return main.apply(value);
        } catch (RuntimeException e) {
            exceptionHandler.apply(e);
        }
        return null;
    }

    public MethodExecutor<T, R> setValue(T value) {
        this.value = value;
        return this;
    }

    public MethodExecutor<T, R> setMain(Function<T, R> main) {
        this.main = main;
        return this;
    }

    public MethodExecutor<T, R> setExceptionHandler(Function<RuntimeException, R> exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
        return this;
    }
}
