package com.github.MikeMalenkov2005.jage.textures;

import com.github.MikeMalenkov2005.jage.InvalidGLResourceException;
import com.github.MikeMalenkov2005.jage.enums.DataType;
import com.github.MikeMalenkov2005.jage.enums.ImageFormat;
import com.github.MikeMalenkov2005.jage.enums.PixelFormat;

import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL46.*;

public class Texture3D extends Texture {
    public Texture3D(int levels, ImageFormat format, int width, int height, int depth) {
        super(format, GL_TEXTURE_3D);
        glTextureStorage3D(id, levels, format.id, width, height, depth);
    }

    public void write(int level, int x, int y, int z, int width, int height, int depth, PixelFormat format, DataType type, byte... data) {
        if (!isValid()) throw new InvalidGLResourceException("Invalid Texture3D");
        glTextureSubImage3D(id, level, x, y, z, width, height, depth, format.id, type.id, ByteBuffer.wrap(data));
    }

    public void write(int level, int x, int y, int z, int width, int height, int depth, PixelFormat format, DataType type, short... data) {
        if (!isValid()) throw new InvalidGLResourceException("Invalid Texture3D");
        glTextureSubImage3D(id, level, x, y, z, width, height, depth, format.id, type.id, data);
    }

    public void write(int level, int x, int y, int z, int width, int height, int depth, PixelFormat format, DataType type, int... data) {
        if (!isValid()) throw new InvalidGLResourceException("Invalid Texture3D");
        glTextureSubImage3D(id, level, x, y, z, width, height, depth, format.id, type.id, data);
    }

    public void write(int level, int x, int y, int z, int width, int height, int depth, PixelFormat format, DataType type, float... data) {
        if (!isValid()) throw new InvalidGLResourceException("Invalid Texture3D");
        glTextureSubImage3D(id, level, x, y, z, width, height, depth, format.id, type.id, data);
    }

    public void write(int level, int x, int y, int z, int width, int height, int depth, PixelFormat format, DataType type, double... data) {
        if (!isValid()) throw new InvalidGLResourceException("Invalid Texture3D");
        glTextureSubImage3D(id, level, x, y, z, width, height, depth, format.id, type.id, data);
    }

    public void read(int level, int x, int y, int z, int width, int height, int depth, PixelFormat format, DataType type, byte[] data) {
        if (!isValid()) throw new InvalidGLResourceException("Invalid Texture3D");
        glGetTextureSubImage(id, level, x, y, z, width, height, depth, format.id, type.id, ByteBuffer.wrap(data));
    }

    public void read(int level, int x, int y, int z, int width, int height, int depth, PixelFormat format, DataType type, short[] data) {
        if (!isValid()) throw new InvalidGLResourceException("Invalid Texture3D");
        glGetTextureSubImage(id, level, x, y, z, width, height, depth, format.id, type.id, data);
    }

    public void read(int level, int x, int y, int z, int width, int height, int depth, PixelFormat format, DataType type, int[] data) {
        if (!isValid()) throw new InvalidGLResourceException("Invalid Texture3D");
        glGetTextureSubImage(id, level, x, y, z, width, height, depth, format.id, type.id, data);
    }

    public void read(int level, int x, int y, int z, int width, int height, int depth, PixelFormat format, DataType type, float[] data) {
        if (!isValid()) throw new InvalidGLResourceException("Invalid Texture3D");
        glGetTextureSubImage(id, level, x, y, z, width, height, depth, format.id, type.id, data);
    }

    public void read(int level, int x, int y, int z, int width, int height, int depth, PixelFormat format, DataType type, double[] data) {
        if (!isValid()) throw new InvalidGLResourceException("Invalid Texture3D");
        glGetTextureSubImage(id, level, x, y, z, width, height, depth, format.id, type.id, data);
    }
}
