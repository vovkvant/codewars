package com.selfdev.chemistkata;

/**
 * Created by vovkv on 8/14/2019.
 */
    /*
    Symbol:           H     B     C     N     O     F    Mg     P     S    Cl    Br
    Valence number:   1     3     4     3     2     1     2     3     2     1     1
    Atomic weight:  1.0  10.8  12.0  14.0  16.0  19.0  24.3  31.0  32.1  35.5  80.0  (in g/mol)
    */
public enum AtomEnum {
    //C, H, O, B, Br, Cl, F, Mg, N, P, S
    C(4, 12.0, 0),
    H(1, 1.0, 1),
    O(2, 16.0, 2),
    B(3, 10.8, 3),
    Br(1, 80.0, 4),
    Cl(1, 35.5, 5),
    F(1, 19.0, 6),
    Mg(2, 24.3, 7),
    N(3, 14.0, 8),
    P(3, 31.0, 9),
    S(2, 32.1, 10);

    private int valenceNumber;
    private double atomicWeight;
    private final int order;

    //
    AtomEnum(int valenceNumber, double atomicWeight, int order) {
        this.valenceNumber = valenceNumber;
        this.atomicWeight = atomicWeight;
        this.order = order;
    }

    public int getValenceNumber() {
        return valenceNumber;
    }

    public double getAtomicWeight() {
        return atomicWeight;
    }

    public int getOrder() {
        return order;
    }
}
