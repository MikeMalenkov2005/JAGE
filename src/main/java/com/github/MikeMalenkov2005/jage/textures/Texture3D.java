package com.github.MikeMalenkov2005.jage.textures;

import com.github.MikeMalenkov2005.jage.enums.DataType;
import com.github.MikeMalenkov2005.jage.enums.ImageFormat;
import com.github.MikeMalenkov2005.jage.enums.PixelFormat;
import org.lwjgl.BufferUtils;
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
        ByteBuffer buffer = BufferUtils.createByteBuffer(data.length);
        buffer.put(data);
        glTextureSubImage3D(id, level, x, y, z, width, height, depth, format.id, type.id, buffer);
    }

    public void write(int level, int x, int y, int z, int width, int height, int depth, PixelFormat format, DataType type, short... data) {
        exceptInvalid("Texture3D");
        ShortBuffer buffer = BufferUtils.createShortBuffer(data.length);
        buffer.put(data);
        glTextureSubImage3D(id, level, x, y, z, width, height, depth, format.id, type.id, buffer);
    }

    public void write(int level, int x, int y, int z, int width, int height, int depth, PixelFormat format, DataType type, int... data) {
        exceptInvalid("Texture3D");
        IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
        buffer.put(data);
        glTextureSubImage3D(id, level, x, y, z, width, height, depth, format.id, type.id, buffer);
    }

    public void write(int level, int x, int y, int z, int width, int height, int depth, PixelFormat format, DataType type, float... data) {
        exceptInvalid("Texture3D");
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
        buffer.put(data);
        glTextureSubImage3D(id, level, x, y, z, width, height, depth, format.id, type.id, buffer);
    }

    public void write(int level, int x, int y, int z, int width, int height, int depth, PixelFormat format, DataType type, double... data) {
        exceptInvalid("Texture3D");
        DoubleBuffer buffer = BufferUtils.createDoubleBuffer(data.length);
        buffer.put(data);
        glTextureSubImage3D(id, level, x, y, z, width, height, depth, format.id, type.id, buffer);
    }

    public void read(int level, int x, int y, int z, int width, int height, int depth, PixelFormat format, DataType type, byte[] data) {
        exceptInvalid("Texture3D");
        ByteBuffer buffer = BufferUtils.createByteBuffer(data.length);
        glGetTextureSubImage(id, level, x, y, z, width, height, depth, format.id, type.id, buffer);
        buffer.get(data);
    }

    public void read(int level, int x, int y, int z, int width, int height, int depth, PixelFormat format, DataType type, short[] data) {
        exceptInvalid("Texture3D");
        ShortBuffer buffer = BufferUtils.createShortBuffer(data.length);
        glGetTextureSubImage(id, level, x, y, z, width, height, depth, format.id, type.id, buffer);
        buffer.get(data);
    }

    public void read(int level, int x, int y, int z, int width, int height, int depth, PixelFormat format, DataType type, int[] data) {
        exceptInvalid("Texture3D");
        IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
        glGetTextureSubImage(id, level, x, y, z, width, height, depth, format.id, type.id, buffer);
        buffer.get(data);
    }

    public void read(int level, int x, int y, int z, int width, int height, int depth, PixelFormat format, DataType type, float[] data) {
        exceptInvalid("Texture3D");
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
        glGetTextureSubImage(id, level, x, y, z, width, height, depth, format.id, type.id, buffer);
        buffer.get(data);
    }

    public void read(int level, int x, int y, int z, int width, int height, int depth, PixelFormat format, DataType type, double[] data) {
        exceptInvalid("Texture3D");
        DoubleBuffer buffer = BufferUtils.createDoubleBuffer(data.length);
        glGetTextureSubImage(id, level, x, y, z, width, height, depth, format.id, type.id, buffer);
        buffer.get(data);
    }
}
