package com.github.MikeMalenkov2005.jage.textures;

import com.github.MikeMalenkov2005.jage.enums.DataType;
import com.github.MikeMalenkov2005.jage.enums.ImageFormat;
import com.github.MikeMalenkov2005.jage.enums.PixelFormat;
import org.lwjgl.BufferUtils;

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
        ByteBuffer buffer = BufferUtils.createByteBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        glTextureSubImage1D(id, level, x, width, format.id, type.id, buffer);
    }

    public void write(int level, int x, int width, PixelFormat format, DataType type, short... data) {
        exceptInvalid("Texture1D");
        ShortBuffer buffer = BufferUtils.createShortBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        glTextureSubImage1D(id, level, x, width, format.id, type.id, buffer);
    }

    public void write(int level, int x, int width, PixelFormat format, DataType type, int... data) {
        exceptInvalid("Texture1D");
        IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        glTextureSubImage1D(id, level, x, width, format.id, type.id, buffer);
    }

    public void write(int level, int x, int width, PixelFormat format, DataType type, float... data) {
        exceptInvalid("Texture1D");
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        glTextureSubImage1D(id, level, x, width, format.id, type.id, buffer);
    }

    public void write(int level, int x, int width, PixelFormat format, DataType type, double... data) {
        exceptInvalid("Texture1D");
        DoubleBuffer buffer = BufferUtils.createDoubleBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        glTextureSubImage1D(id, level, x, width, format.id, type.id, buffer);
    }

    public void read(int level, int x, int width, PixelFormat format, DataType type, byte[] data) {
        exceptInvalid("Texture1D");
        ByteBuffer buffer = BufferUtils.createByteBuffer(data.length);
        glGetTextureSubImage(id, level, x, 0, 0, width, 1, 1, format.id, type.id, buffer);
        buffer.flip();
        buffer.get(data);
    }

    public void read(int level, int x, int width, PixelFormat format, DataType type, short[] data) {
        exceptInvalid("Texture1D");
        ShortBuffer buffer = BufferUtils.createShortBuffer(data.length);
        glGetTextureSubImage(id, level, x, 0, 0, width, 1, 1, format.id, type.id, buffer);
        buffer.flip();
        buffer.get(data);
    }

    public void read(int level, int x, int width, PixelFormat format, DataType type, int[] data) {
        exceptInvalid("Texture1D");
        IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
        glGetTextureSubImage(id, level, x, 0, 0, width, 1, 1, format.id, type.id, buffer);
        buffer.flip();
        buffer.get(data);
    }

    public void read(int level, int x, int width, PixelFormat format, DataType type, float[] data) {
        exceptInvalid("Texture1D");
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
        glGetTextureSubImage(id, level, x, 0, 0, width, 1, 1, format.id, type.id, buffer);
        buffer.flip();
        buffer.get(data);
    }

    public void read(int level, int x, int width, PixelFormat format, DataType type, double[] data) {
        exceptInvalid("Texture1D");
        DoubleBuffer buffer = BufferUtils.createDoubleBuffer(data.length);
        glGetTextureSubImage(id, level, x, 0, 0, width, 1, 1, format.id, type.id, buffer);
        buffer.flip();
        buffer.get(data);
    }
}
