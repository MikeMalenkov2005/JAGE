package com.github.MikeMalenkov2005.jage.buffers;

import com.github.MikeMalenkov2005.jage.Deletable;
import com.github.MikeMalenkov2005.jage.InvalidGLResourceException;

import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL46.*;

public class GLBuffer implements Deletable {
    public final int id;
    public final long size;
    private boolean valid = true;

    public GLBuffer(long size) {
        this.id = glCreateBuffers();
        this.size = size;
        glNamedBufferStorage(id, size, GL_DYNAMIC_STORAGE_BIT);
    }

    public void write(long offset, byte... data) {
        if (!valid) throw new InvalidGLResourceException("Invalid GLBuffer");
        glNamedBufferSubData(id, offset, ByteBuffer.wrap(data));
    }

    public void write(long offset, short... data) {
        if (!valid) throw new InvalidGLResourceException("Invalid GLBuffer");
        glNamedBufferSubData(id, offset, data);
    }

    public void write(long offset, int... data) {
        if (!valid) throw new InvalidGLResourceException("Invalid GLBuffer");
        glNamedBufferSubData(id, offset, data);
    }

    public void write(long offset, long... data) {
        if (!valid) throw new InvalidGLResourceException("Invalid GLBuffer");
        glNamedBufferSubData(id, offset, data);
    }

    public void write(long offset, float... data) {
        if (!valid) throw new InvalidGLResourceException("Invalid GLBuffer");
        glNamedBufferSubData(id, offset, data);
    }

    public void write(long offset, double... data) {
        if (!valid) throw new InvalidGLResourceException("Invalid GLBuffer");
        glNamedBufferSubData(id, offset, data);
    }

    public void read(long offset, byte[] data) {
        if (!valid) throw new InvalidGLResourceException("Invalid GLBuffer");
        glGetNamedBufferSubData(id, offset, ByteBuffer.wrap(data));
    }

    public void read(long offset, short[] data) {
        if (!valid) throw new InvalidGLResourceException("Invalid GLBuffer");
        glGetNamedBufferSubData(id, offset, data);
    }

    public void read(long offset, int[] data) {
        if (!valid) throw new InvalidGLResourceException("Invalid GLBuffer");
        glGetNamedBufferSubData(id, offset, data);
    }

    public void read(long offset, long[] data) {
        if (!valid) throw new InvalidGLResourceException("Invalid GLBuffer");
        glGetNamedBufferSubData(id, offset, data);
    }

    public void read(long offset, float[] data) {
        if (!valid) throw new InvalidGLResourceException("Invalid GLBuffer");
        glGetNamedBufferSubData(id, offset, data);
    }

    public void read(long offset, double[] data) {
        if (!valid) throw new InvalidGLResourceException("Invalid GLBuffer");
        glGetNamedBufferSubData(id, offset, data);
    }

    public byte[] readAllBytes() {
        byte[] data = new byte[(int) size];
        read(0, data);
        return data;
    }

    public short[] readAllShorts() {
        short[] data = new short[(int) ((size / 2) + (size % 2 == 0 ? 0 : 1))];
        read(0, data);
        return data;
    }

    public int[] readAllInts() {
        int[] data = new int[(int) ((size / 4) + (size % 4 == 0 ? 0 : 1))];
        read(0, data);
        return data;
    }

    public long[] readAllLongs() {
        long[] data = new long[(int) ((size / 8) + (size % 8 == 0 ? 0 : 1))];
        read(0, data);
        return data;
    }

    public float[] readAllFloats() {
        float[] data = new float[(int) ((size / 4) + (size % 4 == 0 ? 0 : 1))];
        read(0, data);
        return data;
    }

    public double[] readAllDoubles() {
        double[] data = new double[(int) ((size / 8) + (size % 8 == 0 ? 0 : 1))];
        read(0, data);
        return data;
    }

    @Override
    public void delete() {
        if (valid) {
            glDeleteBuffers(id);
            valid = false;
        }
    }

    public boolean isValid() {
        return valid;
    }

    public static GLBuffer of(byte... data) {
        GLBuffer buffer = new GLBuffer(data.length);
        buffer.write(0, data);
        return buffer;
    }

    public static GLBuffer of(short... data) {
        GLBuffer buffer = new GLBuffer(data.length * 2L);
        buffer.write(0, data);
        return buffer;
    }

    public static GLBuffer of(int... data) {
        GLBuffer buffer = new GLBuffer(data.length * 4L);
        buffer.write(0, data);
        return buffer;
    }

    public static GLBuffer of(long... data) {
        GLBuffer buffer = new GLBuffer(data.length * 8L);
        buffer.write(0, data);
        return buffer;
    }

    public static GLBuffer of(float... data) {
        GLBuffer buffer = new GLBuffer(data.length * 4L);
        buffer.write(0, data);
        return buffer;
    }

    public static GLBuffer of(double... data) {
        GLBuffer buffer = new GLBuffer(data.length * 8L);
        buffer.write(0, data);
        return buffer;
    }
}
