package com.github.MikeMalenkov2005.jage.textures;

import com.github.MikeMalenkov2005.jage.enums.DataType;
import com.github.MikeMalenkov2005.jage.enums.ImageFormat;
import com.github.MikeMalenkov2005.jage.enums.PixelFormat;

import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL46.*;

public class Texture1D extends Texture {
    public final ImageFormat format;

    public Texture1D(int levels, ImageFormat format, int width) {
        super(GL_TEXTURE_1D);
        this.format = format;
        glTextureStorage1D(id, levels, format.id, width);
    }

    public void write(int level, int x, int width, PixelFormat format, DataType type, byte... data) {
        glTextureSubImage1D(id, level, x, width, format.id, type.id, ByteBuffer.wrap(data));
    }

    public void write(int level, int x, int width, PixelFormat format, DataType type, short... data) {
        glTextureSubImage1D(id, level, x, width, format.id, type.id, data);
    }

    public void write(int level, int x, int width, PixelFormat format, DataType type, int... data) {
        glTextureSubImage1D(id, level, x, width, format.id, type.id, data);
    }

    public void write(int level, int x, int width, PixelFormat format, DataType type, float... data) {
        glTextureSubImage1D(id, level, x, width, format.id, type.id, data);
    }

    public void write(int level, int x, int width, PixelFormat format, DataType type, double... data) {
        glTextureSubImage1D(id, level, x, width, format.id, type.id, data);
    }

    public void read(int level, int x, int width, PixelFormat format, DataType type, byte[] data) {
        glGetTextureSubImage(id, level, x, 0, 0, width, 1, 1, format.id, type.id, ByteBuffer.wrap(data));
    }

    public void read(int level, int x, int width, PixelFormat format, DataType type, short[] data) {
        glGetTextureSubImage(id, level, x, 0, 0, width, 1, 1, format.id, type.id, data);
    }

    public void read(int level, int x, int width, PixelFormat format, DataType type, int[] data) {
        glGetTextureSubImage(id, level, x, 0, 0, width, 1, 1, format.id, type.id, data);
    }

    public void read(int level, int x, int width, PixelFormat format, DataType type, float[] data) {
        glGetTextureSubImage(id, level, x, 0, 0, width, 1, 1, format.id, type.id, data);
    }

    public void read(int level, int x, int width, PixelFormat format, DataType type, double[] data) {
        glGetTextureSubImage(id, level, x, 0, 0, width, 1, 1, format.id, type.id, data);
    }
}
