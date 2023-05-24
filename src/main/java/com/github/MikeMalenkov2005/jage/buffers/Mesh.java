package com.github.MikeMalenkov2005.jage.buffers;

import com.github.MikeMalenkov2005.jage.Deletable;
import com.github.MikeMalenkov2005.jage.InvalidGLResourceException;
import com.github.MikeMalenkov2005.jage.enums.DataType;

import java.util.HashSet;
import java.util.Set;

import static org.lwjgl.opengl.GL46.*;

public class Mesh implements Deletable {
    private final int vao, vc;
    private final GLBuffer ebo;
    private final Set<Integer> enabledAttributes = new HashSet<>();
    private boolean valid = true;

    public Mesh(int... indices) {
        vc = indices.length;
        ebo = GLBuffer.of(indices);
        vao = glCreateVertexArrays();
        glVertexArrayElementBuffer(vao, ebo.id);
    }

    public void setupAttribute(int attribute, int binding, int size, DataType type, boolean normalized, int offset) {
        enableAttribute(attribute);
        glVertexArrayAttribBinding(vao, attribute, binding);
        glVertexArrayAttribFormat(vao, attribute, size, type.id, normalized, offset);
    }

    public void setupIAttribute(int attribute, int binding, int size, DataType type, int offset) {
        enableAttribute(attribute);
        glVertexArrayAttribBinding(vao, attribute, binding);
        glVertexArrayAttribIFormat(vao, attribute, size, type.id, offset);
    }

    public void enableAttribute(int attribute) {
        if (!valid) throw new InvalidGLResourceException("Invalid Mesh");
        if (!enabledAttributes.contains(attribute)) {
            glEnableVertexArrayAttrib(vao, attribute);
            enabledAttributes.add(attribute);
        }
    }

    public void disableAttribute(int attribute) {
        if (!valid) throw new InvalidGLResourceException("Invalid Mesh");
        if (enabledAttributes.contains(attribute)) {
            glDisableVertexArrayAttrib(vao, attribute);
            enabledAttributes.remove(attribute);
        }
    }

    public void bindBuffer(int binding, GLBuffer buffer, int offset, int stride) {
        if (!valid) throw new InvalidGLResourceException("Invalid Mesh");
        if (!buffer.isValid()) throw new InvalidGLResourceException("Invalid GLBuffer");
        glVertexArrayVertexBuffer(vao, binding, buffer.id, offset, stride);
    }

    public void draw() {
        if (!valid) throw new InvalidGLResourceException("Invalid Mesh");
        glBindVertexArray(vao);
        glDrawElements(GL_TRIANGLES, vc, GL_UNSIGNED_INT, 0);
        glBindVertexArray(0);
    }

    @Override
    public void delete() {
        if (valid) {
            glDeleteVertexArrays(vao);
            ebo.delete();
            valid = false;
        }
    }

    @Override
    public boolean isValid() {
        return valid;
    }
}
