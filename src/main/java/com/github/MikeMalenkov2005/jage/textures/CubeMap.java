package com.github.MikeMalenkov2005.jage.textures;

import com.github.MikeMalenkov2005.jage.enums.*;

import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL46.*;

public class CubeMap extends Texture {
    public final ImageFormat format;

    protected CubeMap(ImageFormat format, int width, int height) {
        super(GL_TEXTURE_CUBE_MAP);
        this.format = format;
        glTextureStorage2D(id, 1, format.id, width, height);
        setWrapMode(WrapMode.CLAMP_TO_EDGE);
    }

    public void write(CubeSide side, int x, int y, int width, int height, PixelFormat format, DataType type, byte... data) {
        glTextureSubImage3D(id, 0, x, y, side.id, width, height, 1, format.id, type.id, ByteBuffer.wrap(data));
    }

    public void write(CubeSide side, int x, int y, int width, int height, PixelFormat format, DataType type, short... data) {
        glTextureSubImage3D(id, 0, x, y, side.id, width, height, 1, format.id, type.id, data);
    }

    public void write(CubeSide side, int x, int y, int width, int height, PixelFormat format, DataType type, int... data) {
        glTextureSubImage3D(id, 0, x, y, side.id, width, height, 1, format.id, type.id, data);
    }

    public void write(CubeSide side, int x, int y, int width, int height, PixelFormat format, DataType type, float... data) {
        glTextureSubImage3D(id, 0, x, y, side.id, width, height, 1, format.id, type.id, data);
    }

    public void write(CubeSide side, int x, int y, int width, int height, PixelFormat format, DataType type, double... data) {
        glTextureSubImage3D(id, 0, x, y, side.id, width, height, 1, format.id, type.id, data);
    }

    public void read(CubeSide side, int x, int y, int width, int height, PixelFormat format, DataType type, byte[] data) {
        glGetTextureSubImage(id, 0, x, y, side.id, width, height, 1, format.id, type.id, ByteBuffer.wrap(data));
    }

    public void read(CubeSide side, int x, int y, int width, int height, PixelFormat format, DataType type, short[] data) {
        glGetTextureSubImage(id, 0, x, y, side.id, width, height, 1, format.id, type.id, data);
    }

    public void read(CubeSide side, int x, int y, int width, int height, PixelFormat format, DataType type, int[] data) {
        glGetTextureSubImage(id, 0, x, y, side.id, width, height, 1, format.id, type.id, data);
    }

    public void read(CubeSide side, int x, int y, int width, int height, PixelFormat format, DataType type, float[] data) {
        glGetTextureSubImage(id, 0, x, y, side.id, width, height, 1, format.id, type.id, data);
    }

    public void read(CubeSide side, int x, int y, int width, int height, PixelFormat format, DataType type, double[] data) {
        glGetTextureSubImage(id, 0, x, y, side.id, width, height, 1, format.id, type.id, data);
    }
}
