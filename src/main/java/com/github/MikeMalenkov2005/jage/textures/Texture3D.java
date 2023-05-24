package com.github.MikeMalenkov2005.jage.textures;

import com.github.MikeMalenkov2005.jage.enums.DataType;
import com.github.MikeMalenkov2005.jage.enums.ImageFormat;
import com.github.MikeMalenkov2005.jage.enums.PixelFormat;

import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL46.*;

public class Texture3D extends Texture {
    public final ImageFormat format;

    public Texture3D(int levels, ImageFormat format, int width, int height, int depth) {
        super(GL_TEXTURE_3D);
        this.format = format;
        glTextureStorage3D(id, levels, format.id, width, height, depth);
    }

    public void write(int level, int x, int y, int z, int width, int height, int depth, PixelFormat format, DataType type, byte... data) {
        glTextureSubImage3D(id, level, x, y, z, width, height, depth, format.id, type.id, ByteBuffer.wrap(data));
    }

    public void write(int level, int x, int y, int z, int width, int height, int depth, PixelFormat format, DataType type, short... data) {
        glTextureSubImage3D(id, level, x, y, z, width, height, depth, format.id, type.id, data);
    }

    public void write(int level, int x, int y, int z, int width, int height, int depth, PixelFormat format, DataType type, int... data) {
        glTextureSubImage3D(id, level, x, y, z, width, height, depth, format.id, type.id, data);
    }

    public void write(int level, int x, int y, int z, int width, int height, int depth, PixelFormat format, DataType type, float... data) {
        glTextureSubImage3D(id, level, x, y, z, width, height, depth, format.id, type.id, data);
    }

    public void write(int level, int x, int y, int z, int width, int height, int depth, PixelFormat format, DataType type, double... data) {
        glTextureSubImage3D(id, level, x, y, z, width, height, depth, format.id, type.id, data);
    }

    public void read(int level, int x, int y, int z, int width, int height, int depth, PixelFormat format, DataType type, byte[] data) {
        glGetTextureSubImage(id, level, x, y, z, width, height, depth, format.id, type.id, ByteBuffer.wrap(data));
    }

    public void read(int level, int x, int y, int z, int width, int height, int depth, PixelFormat format, DataType type, short[] data) {
        glGetTextureSubImage(id, level, x, y, z, width, height, depth, format.id, type.id, data);
    }

    public void read(int level, int x, int y, int z, int width, int height, int depth, PixelFormat format, DataType type, int[] data) {
        glGetTextureSubImage(id, level, x, y, z, width, height, depth, format.id, type.id, data);
    }

    public void read(int level, int x, int y, int z, int width, int height, int depth, PixelFormat format, DataType type, float[] data) {
        glGetTextureSubImage(id, level, x, y, z, width, height, depth, format.id, type.id, data);
    }

    public void read(int level, int x, int y, int z, int width, int height, int depth, PixelFormat format, DataType type, double[] data) {
        glGetTextureSubImage(id, level, x, y, z, width, height, depth, format.id, type.id, data);
    }
}
