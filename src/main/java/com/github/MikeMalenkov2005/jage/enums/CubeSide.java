package com.github.MikeMalenkov2005.jage.enums;

public enum CubeSide {
    POSITIVE_X(0),
    NEGATIVE_X(1),
    POSITIVE_Y(2),
    NEGATIVE_Y(3),
    POSITIVE_Z(4),
    NEGATIVE_Z(5);

    public final int id;

    CubeSide(int id) {
        this.id = id;
    }
}
