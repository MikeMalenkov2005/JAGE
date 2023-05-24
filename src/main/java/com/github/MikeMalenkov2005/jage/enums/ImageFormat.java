package com.github.MikeMalenkov2005.jage.enums;

import static org.lwjgl.opengl.GL46.*;

public enum ImageFormat {
    RGBA32F(GL_RGBA32F),
    RGBA16F(GL_RGBA16F),
    RGBA16(GL_RGBA16),
    RGBA32I(GL_RGBA16I),
    RGBA16UI(GL_RGBA16UI),
    RGBA16_SNORM(GL_RGBA16_SNORM),
    RGBA8(GL_RGBA8),
    RGBA8I(GL_RGBA8I),
    RGBA8UI(GL_RGBA8UI),
    RGBA8_SNORM(GL_RGBA8_SNORM),

    RGB32F(GL_RGB32F),
    RGB16F(GL_RGB16F),
    RGB16(GL_RGB16),
    RGB32I(GL_RGB16I),
    RGB16UI(GL_RGB16UI),
    RGB16_SNORM(GL_RGB16_SNORM),
    RGB8(GL_RGB8),
    RGB8I(GL_RGB8I),
    RGB8UI(GL_RGB8UI),
    RGB8_SNORM(GL_RGB8_SNORM),

    RG32F(GL_RG32F),
    RG16F(GL_RG16F),
    RG16(GL_RG16),
    RG32I(GL_RG16I),
    RG16UI(GL_RG16UI),
    RG16_SNORM(GL_RG16_SNORM),
    RG8(GL_RG8),
    RG8I(GL_RG8I),
    RG8UI(GL_RG8UI),
    RG8_SNORM(GL_RG8_SNORM),

    R32F(GL_R32F),
    R16F(GL_R16F),
    R16(GL_R16),
    R32I(GL_R16I),
    R16UI(GL_R16UI),
    R16_SNORM(GL_R16_SNORM),
    R8(GL_R8),
    R8I(GL_R8I),
    R8UI(GL_R8UI),
    R8_SNORM(GL_R8_SNORM);

    public final int id;

    ImageFormat(int id) {
        this.id = id;
    }
}
