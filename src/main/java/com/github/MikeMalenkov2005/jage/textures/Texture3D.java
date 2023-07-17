package com.github.MikeMalenkov2005.jage.textures;

import com.github.MikeMalenkov2005.jage.InvalidGLResourceException;
import com.github.MikeMalenkov2005.jage.enums.DataType;
import com.github.MikeMalenkov2005.jage.enums.ImageFormat;
import com.github.MikeMalenkov2005.jage.enums.PixelFormat;
import org.lwjgl.system.MemoryUtil;

import java.nio.*;

import static org.lwjgl.opengl.GL46.*;

public class Texture3D extends Texture {
    public final int width, height, depth;

    public Texture3D(int levels, ImageFormat format, int width, int height, int depth) {
        super(format, GL_TEXTURE_3D);
        this.width = width;
        this.height = height;
        this.depth = depth;
        glTextureStorage3D(id, levels, format.id, width, height, depth);
    }

    public void write(int level, int x, int y, int z, int width, int height, int depth, PixelFormat format, DataType type, byte... data) {
        exceptInvalid("Texture3D");
        glTextureSubImage3D(id, level, x, y, z, width, height, depth, format.id, type.id, MemoryUtil.memAddress(ByteBuffer.wrap(data)));
    }

    public void write(int level, int x, int y, int z, int width, int height, int depth, PixelFormat format, DataType type, short... data) {
        exceptInvalid("Texture3D");
        glTextureSubImage3D(id, level, x, y, z, width, height, depth, format.id, type.id, MemoryUtil.memAddress(ShortBuffer.wrap(data)));
    }

    public void write(int level, int x, int y, int z, int width, int height, int depth, PixelFormat format, DataType type, int... data) {
        exceptInvalid("Texture3D");
        glTextureSubImage3D(id, level, x, y, z, width, height, depth, format.id, type.id, MemoryUtil.memAddress(IntBuffer.wrap(data)));
    }

    public void write(int level, int x, int y, int z, int width, int height, int depth, PixelFormat format, DataType type, float... data) {
        exceptInvalid("Texture3D");
        glTextureSubImage3D(id, level, x, y, z, width, height, depth, format.id, type.id, MemoryUtil.memAddress(FloatBuffer.wrap(data)));
    }

    public void write(int level, int x, int y, int z, int width, int height, int depth, PixelFormat format, DataType type, double... data) {
        exceptInvalid("Texture3D");
        glTextureSubImage3D(id, level, x, y, z, width, height, depth, format.id, type.id, MemoryUtil.memAddress(DoubleBuffer.wrap(data)));
    }

    public void read(int level, int x, int y, int z, int width, int height, int depth, PixelFormat format, DataType type, byte[] data) {
        exceptInvalid("Texture3D");
        glGetTextureSubImage(id, level, x, y, z, width, height, depth, format.id, type.id, ByteBuffer.wrap(data));
    }

    public void read(int level, int x, int y, int z, int width, int height, int depth, PixelFormat format, DataType type, short[] data) {
        exceptInvalid("Texture3D");
        glGetTextureSubImage(id, level, x, y, z, width, height, depth, format.id, type.id, ShortBuffer.wrap(data));
    }

    public void read(int level, int x, int y, int z, int width, int height, int depth, PixelFormat format, DataType type, int[] data) {
        exceptInvalid("Texture3D");
        glGetTextureSubImage(id, level, x, y, z, width, height, depth, format.id, type.id, IntBuffer.wrap(data));
    }

    public void read(int level, int x, int y, int z, int width, int height, int depth, PixelFormat format, DataType type, float[] data) {
        exceptInvalid("Texture3D");
        glGetTextureSubImage(id, level, x, y, z, width, height, depth, format.id, type.id, FloatBuffer.wrap(data));
    }

    public void read(int level, int x, int y, int z, int width, int height, int depth, PixelFormat format, DataType type, double[] data) {
        exceptInvalid("Texture3D");
        glGetTextureSubImage(id, level, x, y, z, width, height, depth, format.id, type.id, DoubleBuffer.wrap(data));
    }
}
