package com.company.main;

import clojure.lang.IDeref;
import clojure.lang.Settable;

import java.util.function.Function;

public class Bound<S, T, A, B> implements IDeref, Settable {
    private final Lens<S, T, A, B> lens;
    private final A rec;

    public Bound(Lens<S, T, A, B> lens, A rec) {
        this.lens = lens;
        this.rec = rec;
    }

    public T get() {
        return lens.get(rec);
    }

    public B set(S v) {
        return lens.set(rec, v);
    }

    public B over(Function<T, S> fun) {
        return lens.over(rec, fun);
    }


    public <W, X> Bound<W, X, A, B> compose(Lens<W, X, T, S> b) {
        return new Bound<>(lens.compose(b), rec);
    }

    @Override
    public T deref() {
        return this.get();
    }

    @Override
    public B doSet(Object o) {
        if (o != null) {
            return this.set((S)o);
        }
        return null;
    }

    @Override
    public B doReset(Object o) {
        if (o != null) {
            return this.set((S)o);
        }
        return null;
    }
}
