package com.github.MikeMalenkov2005.jage.enums;

public enum GLSLStruct {
    SCALAR(1),
    VECTOR_2D(2),
    VECTOR_3D(3),
    VECTOR_4D(4),
    MATRIX_2X2(4),
    MATRIX_2X3(6),
    MATRIX_2X4(8),
    MATRIX_3X2(6),
    MATRIX_3X3(9),
    MATRIX_3X4(12),
    MATRIX_4X2(8),
    MATRIX_4X3(12),
    MATRIX_4X4(16);

    public final int size;

    GLSLStruct(int size) {
        this.size = size;
    }
}
