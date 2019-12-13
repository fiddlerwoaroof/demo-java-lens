package com.company.main;

public class IdBound<S, A> extends Bound<S, S, A, A> {
    public IdBound(Lens<S, S, A, A> lens, A rec) {
        super(lens, rec);
    }
}
