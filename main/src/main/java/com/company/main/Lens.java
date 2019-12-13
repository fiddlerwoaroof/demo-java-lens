package com.company.main;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;

public interface Lens<S, T, A, B> extends java.util.function.BiFunction<A, Function<T,S>, B> {
    B over(A rec, Function<T, S> fun);

    @Override
    default B apply(A a, Function<T, S> tsFunction) {
        return over(a, tsFunction);
    }

    default B set(A rec, S value) {
        return over(rec, Constant.ly(value));
    }

    default T get(A rec) {
        final AtomicReference<T> store = new AtomicReference<>();
        over(rec, (T v) -> {
            store.set(v);
            return null;
        });
        return store.get();
    }

    default <W, X> Lens<W, X, A, B> compose(Lens<W, X, T, S> b) {
        return Lens.staticCompose(this, b);
    }

    static <S, T, A, B, U, V> Lens<S, T, A, B> staticCompose(Lens<V, U, A, B> a, Lens<S, T, U, V> b) {
        return (rec, fun) -> a.set(rec, b.set(a.get(rec), fun.apply(b.get(a.get(rec)))));
    }
}


class Constant<T, R> implements Function<T, R> {
    private R v;

    private Constant(R v) {
        this.v = v;
    }

    @Override
    public R apply(T o) {
        return v;
    }

    static <T, R> Constant<T, R> ly(R v) {
        return new Constant<>(v);
    }
}

