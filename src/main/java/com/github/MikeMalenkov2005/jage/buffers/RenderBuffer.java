package com.github.MikeMalenkov2005.jage.buffers;

import com.github.MikeMalenkov2005.jage.GLResource;
import com.github.MikeMalenkov2005.jage.enums.ImageFormat;

import static org.lwjgl.opengl.GL46.*;

public class RenderBuffer extends GLResource {

    public final int id, width, height;
    public final ImageFormat format;

    public RenderBuffer(int width, int height, ImageFormat format) {
        id = glCreateRenderbuffers();
        this.width = width;
        this.height = height;
        this.format = format;
        glNamedRenderbufferStorage(id, format.id, width, height);
    }

    @Override
    protected void cleanUp() {
        glDeleteRenderbuffers(id);
    }
}
