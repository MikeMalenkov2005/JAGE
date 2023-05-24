package com.github.MikeMalenkov2005.jage.textures;

import com.github.MikeMalenkov2005.jage.InvalidGLResourceException;
import com.github.MikeMalenkov2005.jage.enums.*;
import org.lwjgl.BufferUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
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

    public void loadSide(CubeSide side, URL imageURL) throws IOException {
        BufferedImage image = ImageIO.read(imageURL);
        int[] pixels = new int[image.getWidth() * image.getHeight()];
        image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());
        ByteBuffer buffer = BufferUtils.createByteBuffer(image.getWidth() * image.getHeight() * 4);
        for (int y = image.getHeight() - 1; y >= 0; y--) {
            for (int x = 0; x < image.getWidth(); x++) {
                int pixel = pixels[y * image.getWidth() + x];
                buffer.put((byte) ((pixel >> 16) & 0xFF));
                buffer.put((byte) ((pixel >> 8) & 0xFF));
                buffer.put((byte) (pixel & 0xFF));
                buffer.put((byte) ((pixel >> 24) & 0xFF));
            }
        }
        buffer.flip();
        write(side, 0, 0, image.getWidth(), image.getHeight(), PixelFormat.RGBA, DataType.UNSIGNED_BYTE, buffer.array());
    }
}
