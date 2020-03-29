package de.borisskert.sudoku.core;

/**
 * Represent a tuple of values used to accumulate values within stream transfer.
 * Every instance of {@link Tuple} is immutable.
 *
 * @param <A> the first element type.
 * @param <B> the second element type.
 */
class Tuple<A, B> {

    /* *****************************************************************************************************************
     * Readonly fields
     **************************************************************************************************************** */

    private final A a;
    private final B b;

    /* *****************************************************************************************************************
     * Constructor(s)
     **************************************************************************************************************** */

    private Tuple(A a, B b) {
        this.a = a;
        this.b = b;
    }

    private Tuple(A a) {
        this.a = a;
        this.b = null;
    }

    /* *****************************************************************************************************************
     * Accessor methods
     **************************************************************************************************************** */

    public A getA() {
        return a;
    }

    public B getB() {
        return b;
    }

    /* *****************************************************************************************************************
     * Wither method(s)
     **************************************************************************************************************** */

    public <Z> Tuple<A, Z> with(Z b) {
        return new Tuple<>(a, b);
    }

    /* *****************************************************************************************************************
     * Factory method(s)
     **************************************************************************************************************** */

    public static <A, B> Tuple<A, B> create(A a) {
        return new Tuple<>(a);
    }
}
