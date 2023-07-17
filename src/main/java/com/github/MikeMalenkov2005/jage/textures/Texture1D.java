package com.github.MikeMalenkov2005.jage.textures;

import com.github.MikeMalenkov2005.jage.enums.DataType;
import com.github.MikeMalenkov2005.jage.enums.ImageFormat;
import com.github.MikeMalenkov2005.jage.enums.PixelFormat;
import org.lwjgl.system.MemoryUtil;

import java.nio.*;

import static org.lwjgl.opengl.GL46.*;

public class Texture1D extends Texture {
    public final int width;

    public Texture1D(int levels, ImageFormat format, int width) {
        super(format, GL_TEXTURE_1D);
        this.width = width;
        glTextureStorage1D(id, levels, format.id, width);
    }

    public void write(int level, int x, int width, PixelFormat format, DataType type, byte... data) {
        exceptInvalid("Texture1D");
        glTextureSubImage1D(id, level, x, width, format.id, type.id, MemoryUtil.memAddress(ByteBuffer.wrap(data)));
    }

    public void write(int level, int x, int width, PixelFormat format, DataType type, short... data) {
        exceptInvalid("Texture1D");
        glTextureSubImage1D(id, level, x, width, format.id, type.id, MemoryUtil.memAddress(ShortBuffer.wrap(data)));
    }

    public void write(int level, int x, int width, PixelFormat format, DataType type, int... data) {
        exceptInvalid("Texture1D");
        glTextureSubImage1D(id, level, x, width, format.id, type.id, MemoryUtil.memAddress(IntBuffer.wrap(data)));
    }

    public void write(int level, int x, int width, PixelFormat format, DataType type, float... data) {
        exceptInvalid("Texture1D");
        glTextureSubImage1D(id, level, x, width, format.id, type.id, MemoryUtil.memAddress(FloatBuffer.wrap(data)));
    }

    public void write(int level, int x, int width, PixelFormat format, DataType type, double... data) {
        exceptInvalid("Texture1D");
        glTextureSubImage1D(id, level, x, width, format.id, type.id, MemoryUtil.memAddress(DoubleBuffer.wrap(data)));
    }

    public void read(int level, int x, int width, PixelFormat format, DataType type, byte[] data) {
        exceptInvalid("Texture1D");
        glGetTextureSubImage(id, level, x, 0, 0, width, 1, 1, format.id, type.id, ByteBuffer.wrap(data));
    }

    public void read(int level, int x, int width, PixelFormat format, DataType type, short[] data) {
        exceptInvalid("Texture1D");
        glGetTextureSubImage(id, level, x, 0, 0, width, 1, 1, format.id, type.id, ShortBuffer.wrap(data));
    }

    public void read(int level, int x, int width, PixelFormat format, DataType type, int[] data) {
        exceptInvalid("Texture1D");
        glGetTextureSubImage(id, level, x, 0, 0, width, 1, 1, format.id, type.id, IntBuffer.wrap(data));
    }

    public void read(int level, int x, int width, PixelFormat format, DataType type, float[] data) {
        exceptInvalid("Texture1D");
        glGetTextureSubImage(id, level, x, 0, 0, width, 1, 1, format.id, type.id, FloatBuffer.wrap(data));
    }

    public void read(int level, int x, int width, PixelFormat format, DataType type, double[] data) {
        exceptInvalid("Texture1D");
        glGetTextureSubImage(id, level, x, 0, 0, width, 1, 1, format.id, type.id, DoubleBuffer.wrap(data));
    }
}
