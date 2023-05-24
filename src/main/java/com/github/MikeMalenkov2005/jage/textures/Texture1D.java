package com.github.MikeMalenkov2005.jage.textures;

import com.github.MikeMalenkov2005.jage.InvalidGLResourceException;
import com.github.MikeMalenkov2005.jage.enums.DataType;
import com.github.MikeMalenkov2005.jage.enums.ImageFormat;
import com.github.MikeMalenkov2005.jage.enums.PixelFormat;

import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL46.*;

public class Texture1D extends Texture {
    public Texture1D(int levels, ImageFormat format, int width) {
        super(format, GL_TEXTURE_1D);
        glTextureStorage1D(id, levels, format.id, width);
    }

    public void write(int level, int x, int width, PixelFormat format, DataType type, byte... data) {
        if (!isValid()) throw new InvalidGLResourceException("Invalid Texture1D");
        glTextureSubImage1D(id, level, x, width, format.id, type.id, ByteBuffer.wrap(data));
    }

    public void write(int level, int x, int width, PixelFormat format, DataType type, short... data) {
        if (!isValid()) throw new InvalidGLResourceException("Invalid Texture1D");
        glTextureSubImage1D(id, level, x, width, format.id, type.id, data);
    }

    public void write(int level, int x, int width, PixelFormat format, DataType type, int... data) {
        if (!isValid()) throw new InvalidGLResourceException("Invalid Texture1D");
        glTextureSubImage1D(id, level, x, width, format.id, type.id, data);
    }

    public void write(int level, int x, int width, PixelFormat format, DataType type, float... data) {
        if (!isValid()) throw new InvalidGLResourceException("Invalid Texture1D");
        glTextureSubImage1D(id, level, x, width, format.id, type.id, data);
    }

    public void write(int level, int x, int width, PixelFormat format, DataType type, double... data) {
        if (!isValid()) throw new InvalidGLResourceException("Invalid Texture1D");
        glTextureSubImage1D(id, level, x, width, format.id, type.id, data);
    }

    public void read(int level, int x, int width, PixelFormat format, DataType type, byte[] data) {
        if (!isValid()) throw new InvalidGLResourceException("Invalid Texture1D");
        glGetTextureSubImage(id, level, x, 0, 0, width, 1, 1, format.id, type.id, ByteBuffer.wrap(data));
    }

    public void read(int level, int x, int width, PixelFormat format, DataType type, short[] data) {
        if (!isValid()) throw new InvalidGLResourceException("Invalid Texture1D");
        glGetTextureSubImage(id, level, x, 0, 0, width, 1, 1, format.id, type.id, data);
    }

    public void read(int level, int x, int width, PixelFormat format, DataType type, int[] data) {
        if (!isValid()) throw new InvalidGLResourceException("Invalid Texture1D");
        glGetTextureSubImage(id, level, x, 0, 0, width, 1, 1, format.id, type.id, data);
    }

    public void read(int level, int x, int width, PixelFormat format, DataType type, float[] data) {
        if (!isValid()) throw new InvalidGLResourceException("Invalid Texture1D");
        glGetTextureSubImage(id, level, x, 0, 0, width, 1, 1, format.id, type.id, data);
    }

    public void read(int level, int x, int width, PixelFormat format, DataType type, double[] data) {
        if (!isValid()) throw new InvalidGLResourceException("Invalid Texture1D");
        glGetTextureSubImage(id, level, x, 0, 0, width, 1, 1, format.id, type.id, data);
    }
}
