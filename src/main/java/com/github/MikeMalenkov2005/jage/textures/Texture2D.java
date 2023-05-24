package com.github.MikeMalenkov2005.jage.textures;

import com.github.MikeMalenkov2005.jage.InvalidGLResourceException;
import com.github.MikeMalenkov2005.jage.enums.DataType;
import com.github.MikeMalenkov2005.jage.enums.ImageFormat;
import com.github.MikeMalenkov2005.jage.enums.PixelFormat;

import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL46.*;

public class Texture2D extends Texture {
    public Texture2D(int levels, ImageFormat format, int width, int height) {
        super(format, GL_TEXTURE_2D);
        glTextureStorage2D(id, levels, format.id, width, height);
    }

    public void write(int level, int x, int y, int width, int height, PixelFormat format, DataType type, byte... data) {
        if (!isValid()) throw new InvalidGLResourceException("Invalid Texture2D");
        glTextureSubImage2D(id, level, x, y, width, height, format.id, type.id, ByteBuffer.wrap(data));
    }

    public void write(int level, int x, int y, int width, int height, PixelFormat format, DataType type, short... data) {
        if (!isValid()) throw new InvalidGLResourceException("Invalid Texture2D");
        glTextureSubImage2D(id, level, x, y, width, height, format.id, type.id, data);
    }

    public void write(int level, int x, int y, int width, int height, PixelFormat format, DataType type, int... data) {
        if (!isValid()) throw new InvalidGLResourceException("Invalid Texture2D");
        glTextureSubImage2D(id, level, x, y, width, height, format.id, type.id, data);
    }

    public void write(int level, int x, int y, int width, int height, PixelFormat format, DataType type, float... data) {
        if (!isValid()) throw new InvalidGLResourceException("Invalid Texture2D");
        glTextureSubImage2D(id, level, x, y, width, height, format.id, type.id, data);
    }

    public void write(int level, int x, int y, int width, int height, PixelFormat format, DataType type, double... data) {
        if (!isValid()) throw new InvalidGLResourceException("Invalid Texture2D");
        glTextureSubImage2D(id, level, x, y, width, height, format.id, type.id, data);
    }

    public void read(int level, int x, int y, int width, int height, PixelFormat format, DataType type, byte[] data) {
        if (!isValid()) throw new InvalidGLResourceException("Invalid Texture2D");
        glGetTextureSubImage(id, level, x, y, 0, width, height, 1, format.id, type.id, ByteBuffer.wrap(data));
    }

    public void read(int level, int x, int y, int width, int height, PixelFormat format, DataType type, short[] data) {
        if (!isValid()) throw new InvalidGLResourceException("Invalid Texture2D");
        glGetTextureSubImage(id, level, x, y, 0, width, height, 1, format.id, type.id, data);
    }

    public void read(int level, int x, int y, int width, int height, PixelFormat format, DataType type, int[] data) {
        if (!isValid()) throw new InvalidGLResourceException("Invalid Texture2D");
        glGetTextureSubImage(id, level, x, y, 0, width, height, 1, format.id, type.id, data);
    }

    public void read(int level, int x, int y, int width, int height, PixelFormat format, DataType type, float[] data) {
        if (!isValid()) throw new InvalidGLResourceException("Invalid Texture2D");
        glGetTextureSubImage(id, level, x, y, 0, width, height, 1, format.id, type.id, data);
    }

    public void read(int level, int x, int y, int width, int height, PixelFormat format, DataType type, double[] data) {
        if (!isValid()) throw new InvalidGLResourceException("Invalid Texture2D");
        glGetTextureSubImage(id, level, x, y, 0, width, height, 1, format.id, type.id, data);
    }
}
