package com.github.MikeMalenkov2005;

import java.util.HashSet;
import java.util.Set;

import static org.lwjgl.opengl.GL46.*;

public class Mesh {
    private final int vao, ebo, vc;
    private final Set<Integer> enabledAttributes = new HashSet<>();

    public Mesh(int... indices) {
        vc = indices.length;
        ebo = Window.loadBuffer(GL_STATIC_DRAW, indices);
        vao = glCreateVertexArrays();
        glVertexArrayElementBuffer(vao, ebo);
    }

    public void setupAttribute(int attribute, int binding, int size, int type, boolean normalized, int offset) {
        enableAttribute(attribute);
        glVertexArrayAttribBinding(vao, attribute, binding);
        glVertexArrayAttribFormat(vao, attribute, size, type, normalized, offset);
    }

    public void setupIAttribute(int attribute, int binding, int size, int type, int offset) {
        enableAttribute(attribute);
        glVertexArrayAttribBinding(vao, attribute, binding);
        glVertexArrayAttribIFormat(vao, attribute, size, type, offset);
    }

    public void enableAttribute(int attribute) {
        if (!enabledAttributes.contains(attribute)) {
            glEnableVertexArrayAttrib(vao, attribute);
            enabledAttributes.add(attribute);
        }
    }

    public void disableAttribute(int attribute) {
        if (enabledAttributes.contains(attribute)) {
            glDisableVertexArrayAttrib(vao, attribute);
            enabledAttributes.remove(attribute);
        }
    }

    public void bindBuffer(int binding, int buffer, int offset, int stride) {
        glVertexArrayVertexBuffer(vao, binding, buffer, offset, stride);
    }

    public void draw() {
        glBindVertexArray(vao);
        glDrawElements(GL_TRIANGLES, vc, GL_UNSIGNED_INT, 0);
        glBindVertexArray(0);
    }

    public void delete() {
        glDeleteVertexArrays(vao);
        glDeleteBuffers(ebo);
    }
}
