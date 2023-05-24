package com.github.MikeMalenkov2005.jage.enums;

import static org.lwjgl.opengl.GL46.*;

public enum DataType {
    BYTE(GL_BYTE, 1),
    UNSIGNED_BYTE(GL_UNSIGNED_BYTE, 1),
    SHORT(GL_SHORT, 2),
    UNSIGNED_SHORT(GL_UNSIGNED_SHORT, 2),
    INT(GL_INT, 4),
    UNSIGNED_INT(GL_UNSIGNED_INT, 4),
    FLOAT(GL_FLOAT, 4),
    DOUBLE(GL_DOUBLE, 8);

    public final int id, size;

    DataType(int id, int size) {
        this.id = id;
        this.size = size;
    }
}
