package de.borisskert.sudoku.core;

class Tuple<A, B> {

    private final A a;
    private final B b;


    private Tuple(A a, B b) {
        this.a = a;
        this.b = b;
    }

    private Tuple(A a) {
        this.a = a;
        this.b = null;
    }

    public A getA() {
        return a;
    }

    public B getB() {
        return b;
    }

    public static <A, B> Tuple<A, B> create(A a) {
        return new Tuple<>(a);
    }

    public <Z> Tuple<A, Z> with(Z b) {
        return new Tuple<>(a, b);
    }
}
