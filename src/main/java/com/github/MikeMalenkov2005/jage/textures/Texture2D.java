package com.github.MikeMalenkov2005.jage.textures;

import com.github.MikeMalenkov2005.jage.InvalidGLResourceException;
import com.github.MikeMalenkov2005.jage.enums.DataType;
import com.github.MikeMalenkov2005.jage.enums.ImageFormat;
import com.github.MikeMalenkov2005.jage.enums.PixelFormat;
import org.lwjgl.BufferUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL46.*;

public class Texture2D extends Texture {
    public final int width, height;

    public Texture2D(int levels, ImageFormat format, int width, int height) {
        super(format, GL_TEXTURE_2D);
        this.width = width;
        this.height = height;
        glTextureStorage2D(id, levels, format.id, width, height);
    }

    public void write(int level, int x, int y, int width, int height, PixelFormat format, DataType type, byte... data) {
        exceptInvalid("Texture2D");
        glTextureSubImage2D(id, level, x, y, width, height, format.id, type.id, ByteBuffer.wrap(data));
    }

    public void write(int level, int x, int y, int width, int height, PixelFormat format, DataType type, short... data) {
        exceptInvalid("Texture2D");
        glTextureSubImage2D(id, level, x, y, width, height, format.id, type.id, data);
    }

    public void write(int level, int x, int y, int width, int height, PixelFormat format, DataType type, int... data) {
        exceptInvalid("Texture2D");
        glTextureSubImage2D(id, level, x, y, width, height, format.id, type.id, data);
    }

    public void write(int level, int x, int y, int width, int height, PixelFormat format, DataType type, float... data) {
        exceptInvalid("Texture2D");
        glTextureSubImage2D(id, level, x, y, width, height, format.id, type.id, data);
    }

    public void write(int level, int x, int y, int width, int height, PixelFormat format, DataType type, double... data) {
        exceptInvalid("Texture2D");
        glTextureSubImage2D(id, level, x, y, width, height, format.id, type.id, data);
    }

    public void read(int level, int x, int y, int width, int height, PixelFormat format, DataType type, byte[] data) {
        exceptInvalid("Texture2D");
        glGetTextureSubImage(id, level, x, y, 0, width, height, 1, format.id, type.id, ByteBuffer.wrap(data));
    }

    public void read(int level, int x, int y, int width, int height, PixelFormat format, DataType type, short[] data) {
        exceptInvalid("Texture2D");
        glGetTextureSubImage(id, level, x, y, 0, width, height, 1, format.id, type.id, data);
    }

    public void read(int level, int x, int y, int width, int height, PixelFormat format, DataType type, int[] data) {
        exceptInvalid("Texture2D");
        glGetTextureSubImage(id, level, x, y, 0, width, height, 1, format.id, type.id, data);
    }

    public void read(int level, int x, int y, int width, int height, PixelFormat format, DataType type, float[] data) {
        exceptInvalid("Texture2D");
        glGetTextureSubImage(id, level, x, y, 0, width, height, 1, format.id, type.id, data);
    }

    public void read(int level, int x, int y, int width, int height, PixelFormat format, DataType type, double[] data) {
        exceptInvalid("Texture2D");
        glGetTextureSubImage(id, level, x, y, 0, width, height, 1, format.id, type.id, data);
    }

    public static Texture2D load(URL imageURL, int levels) throws IOException {
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
        Texture2D texture = new Texture2D(levels, ImageFormat.RGBA8, image.getWidth(), image.getHeight());
        texture.write(0, 0, 0, image.getWidth(), image.getHeight(), PixelFormat.RGBA, DataType.UNSIGNED_BYTE, buffer.array());
        return texture;
    }
}
