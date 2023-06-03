package com.github.MikeMalenkov2005.jage.buffers;

import com.github.MikeMalenkov2005.jage.RenderAction;
import com.github.MikeMalenkov2005.jage.GLResource;
import com.github.MikeMalenkov2005.jage.Window;
import com.github.MikeMalenkov2005.jage.textures.Texture;

import static org.lwjgl.opengl.GL46.*;

public class FrameBuffer extends GLResource {
    private static FrameBuffer bound;
    private static Window currentWindow;

    private final int id;

    public FrameBuffer() {
        id = glCreateFramebuffers();
    }

    public void attachColorTexture(int index, Texture texture, int level) {
        exceptInvalid("FrameBuffer");
        texture.exceptInvalid("Texture");
        if (index < 0 || index >= 32) throw new IndexOutOfBoundsException(index);
        glNamedFramebufferTexture(id, GL_COLOR_ATTACHMENT0 + index, texture.id, level);
    }

    public void detachColorTexture(int index) {
        exceptInvalid("FrameBuffer");
        if (index < 0 || index >= 32) throw new IndexOutOfBoundsException(index);
        glNamedFramebufferTexture(id, GL_COLOR_ATTACHMENT0 + index, 0, 0);
    }

    public void attachDepthTexture(Texture texture, int level) {
        exceptInvalid("FrameBuffer");
        texture.exceptInvalid("Texture");
        glNamedFramebufferTexture(id, GL_DEPTH_ATTACHMENT, texture.id, level);
    }

    public void detachDepthTexture() {
        exceptInvalid("FrameBuffer");
        glNamedFramebufferTexture(id, GL_DEPTH_ATTACHMENT, 0, 0);
    }

    public void attachStencilTexture(Texture texture, int level) {
        exceptInvalid("FrameBuffer");
        texture.exceptInvalid("Texture");
        glNamedFramebufferTexture(id, GL_STENCIL_ATTACHMENT, texture.id, level);
    }

    public void detachStencilTexture() {
        exceptInvalid("FrameBuffer");
        glNamedFramebufferTexture(id, GL_STENCIL_ATTACHMENT, 0, 0);
    }

    public void attachDepthStencilTexture(Texture texture, int level) {
        exceptInvalid("FrameBuffer");
        texture.exceptInvalid("Texture");
        glNamedFramebufferTexture(id, GL_DEPTH_STENCIL_ATTACHMENT, texture.id, level);
    }

    public void detachDepthStencilTexture() {
        exceptInvalid("FrameBuffer");
        glNamedFramebufferTexture(id, GL_DEPTH_STENCIL_ATTACHMENT, 0, 0);
    }

    public void attachColorRenderBuffer(int index, RenderBuffer renderBuffer) {
        exceptInvalid("FrameBuffer");
        renderBuffer.exceptInvalid("RenderBuffer");
        if (index < 0 || index >= 32) throw new IndexOutOfBoundsException(index);
        glNamedFramebufferRenderbuffer(id, GL_COLOR_ATTACHMENT0 + index, GL_RENDERBUFFER, renderBuffer.id);
    }

    public void detachColorRenderBuffer(int index) {
        exceptInvalid("FrameBuffer");
        if (index < 0 || index >= 32) throw new IndexOutOfBoundsException(index);
        glNamedFramebufferRenderbuffer(id, GL_COLOR_ATTACHMENT0 + index, GL_RENDERBUFFER, 0);
    }

    public void attachDepthRenderBuffer(RenderBuffer renderBuffer) {
        exceptInvalid("FrameBuffer");
        renderBuffer.exceptInvalid("RenderBuffer");
        glNamedFramebufferRenderbuffer(id, GL_DEPTH_ATTACHMENT, GL_RENDERBUFFER, renderBuffer.id);
    }

    public void detachDepthRenderBuffer() {
        exceptInvalid("FrameBuffer");
        glNamedFramebufferRenderbuffer(id, GL_DEPTH_ATTACHMENT, GL_RENDERBUFFER, 0);
    }

    public void attachStencilRenderBuffer(RenderBuffer renderBuffer) {
        exceptInvalid("FrameBuffer");
        renderBuffer.exceptInvalid("RenderBuffer");
        glNamedFramebufferRenderbuffer(id, GL_STENCIL_ATTACHMENT, GL_RENDERBUFFER, renderBuffer.id);
    }

    public void detachStencilRenderBuffer() {
        exceptInvalid("FrameBuffer");
        glNamedFramebufferRenderbuffer(id, GL_STENCIL_ATTACHMENT, GL_RENDERBUFFER, 0);
    }

    public void attachDepthStencilRenderBuffer(RenderBuffer renderBuffer) {
        exceptInvalid("FrameBuffer");
        renderBuffer.exceptInvalid("RenderBuffer");
        glNamedFramebufferRenderbuffer(id, GL_DEPTH_STENCIL_ATTACHMENT, GL_RENDERBUFFER, renderBuffer.id);
    }

    public void detachDepthStencilRenderBuffer() {
        exceptInvalid("FrameBuffer");
        glNamedFramebufferRenderbuffer(id, GL_DEPTH_STENCIL_ATTACHMENT, GL_RENDERBUFFER, 0);
    }

    public FrameBuffer bind(int x, int y, int width, int height) {
        exceptInvalid("FrameBuffer");
        FrameBuffer prev = bound;
        glBindFramebuffer(GL_FRAMEBUFFER, id);
        glViewport(x, y, width, height);
        bound = this;
        return prev;
    }

    public void unbind() {
        exceptInvalid("FrameBuffer");
        if (this == bound) {
            glBindFramebuffer(GL_FRAMEBUFFER, 0);
            bound = null;
        }
        glViewport(0, 0, currentWindow.getRealWidth(), currentWindow.getRealHeight());
    }

    public void useIn(int x, int y, int width, int height, RenderAction action) {
        boolean needBinding = this != bound;
        FrameBuffer frameBuffer = null;
        if (needBinding) frameBuffer = bind(x, y, width, height);
        action.render(width, height);
        if (needBinding) {
            if (frameBuffer == null) unbind();
            else frameBuffer.bind(x, y, width, height);
        }
    }

    public static void useWindow(Window window) {
        currentWindow = window;
        if (bound == null) glViewport(0, 0, window.getRealWidth(), window.getRealHeight());
    }

    @Override
    protected void cleanUp() {
        glDeleteFramebuffers(id);
    }
}
