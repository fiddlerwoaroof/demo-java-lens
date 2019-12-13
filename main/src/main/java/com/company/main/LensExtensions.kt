package com.company.main

operator fun <S,T,A,B> Lens<S,T,A,B>.invoke(r: A): T {
    return this.get(r)
}

operator fun <S,T,A,B> Bound<S,T,A,B>.invoke(): T {
    return this.get()
}

operator fun <W,X,S,T,A,B> Bound<S,T,A,B>.rangeTo(other: Lens<W,X,T,S>): Bound<W,X,A,B> {
    return this.compose<W, X>(other)
}
