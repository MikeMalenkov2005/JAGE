package com.github.MikeMalenkov2005.jage.textures;

import com.github.MikeMalenkov2005.jage.InvalidGLResourceException;
import com.github.MikeMalenkov2005.jage.enums.*;

import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL46.*;

public class CubeMap extends Texture {
    protected CubeMap(ImageFormat format, int width, int height) {
        super(format, GL_TEXTURE_CUBE_MAP);
        glTextureStorage2D(id, 1, format.id, width, height);
        setWrapMode(WrapMode.CLAMP_TO_EDGE);
    }

    public void write(CubeSide side, int x, int y, int width, int height, PixelFormat format, DataType type, byte... data) {
        if (!isValid()) throw new InvalidGLResourceException("Invalid CubeMap");
        glTextureSubImage3D(id, 0, x, y, side.id, width, height, 1, format.id, type.id, ByteBuffer.wrap(data));
    }

    public void write(CubeSide side, int x, int y, int width, int height, PixelFormat format, DataType type, short... data) {
        if (!isValid()) throw new InvalidGLResourceException("Invalid CubeMap");
        glTextureSubImage3D(id, 0, x, y, side.id, width, height, 1, format.id, type.id, data);
    }

    public void write(CubeSide side, int x, int y, int width, int height, PixelFormat format, DataType type, int... data) {
        if (!isValid()) throw new InvalidGLResourceException("Invalid CubeMap");
        glTextureSubImage3D(id, 0, x, y, side.id, width, height, 1, format.id, type.id, data);
    }

    public void write(CubeSide side, int x, int y, int width, int height, PixelFormat format, DataType type, float... data) {
        if (!isValid()) throw new InvalidGLResourceException("Invalid CubeMap");
        glTextureSubImage3D(id, 0, x, y, side.id, width, height, 1, format.id, type.id, data);
    }

    public void write(CubeSide side, int x, int y, int width, int height, PixelFormat format, DataType type, double... data) {
        if (!isValid()) throw new InvalidGLResourceException("Invalid CubeMap");
        glTextureSubImage3D(id, 0, x, y, side.id, width, height, 1, format.id, type.id, data);
    }

    public void read(CubeSide side, int x, int y, int width, int height, PixelFormat format, DataType type, byte[] data) {
        if (!isValid()) throw new InvalidGLResourceException("Invalid CubeMap");
        glGetTextureSubImage(id, 0, x, y, side.id, width, height, 1, format.id, type.id, ByteBuffer.wrap(data));
    }

    public void read(CubeSide side, int x, int y, int width, int height, PixelFormat format, DataType type, short[] data) {
        if (!isValid()) throw new InvalidGLResourceException("Invalid CubeMap");
        glGetTextureSubImage(id, 0, x, y, side.id, width, height, 1, format.id, type.id, data);
    }

    public void read(CubeSide side, int x, int y, int width, int height, PixelFormat format, DataType type, int[] data) {
        if (!isValid()) throw new InvalidGLResourceException("Invalid CubeMap");
        glGetTextureSubImage(id, 0, x, y, side.id, width, height, 1, format.id, type.id, data);
    }

    public void read(CubeSide side, int x, int y, int width, int height, PixelFormat format, DataType type, float[] data) {
        if (!isValid()) throw new InvalidGLResourceException("Invalid CubeMap");
        glGetTextureSubImage(id, 0, x, y, side.id, width, height, 1, format.id, type.id, data);
    }

    public void read(CubeSide side, int x, int y, int width, int height, PixelFormat format, DataType type, double[] data) {
        if (!isValid()) throw new InvalidGLResourceException("Invalid CubeMap");
        glGetTextureSubImage(id, 0, x, y, side.id, width, height, 1, format.id, type.id, data);
    }
}
