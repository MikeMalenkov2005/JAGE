package com.github.MikeMalenkov2005.jage.shaders;

import com.github.MikeMalenkov2005.jage.Deletable;
import com.github.MikeMalenkov2005.jage.InvalidGLResourceException;

import static org.lwjgl.opengl.GL46.*;

public class ShaderProgram implements Deletable {
    private static ShaderProgram bound = null;
    private final int id;
    private boolean valid = true;

    public ShaderProgram(Shader... shaders) throws ShaderProgramLinkingFailedException, ShaderProgramValidationFailedException {
        id = glCreateProgram();
        for (Shader shader : shaders) {
            if (!shader.isValid()) throw new InvalidGLResourceException("Invalid Shader");
            glAttachShader(id, shader.id);
        }

        glLinkProgram(id);
        if (glGetProgrami(id, GL_LINK_STATUS) == GL_FALSE)
            throw new ShaderProgramLinkingFailedException(id);

        glValidateProgram(id);
        if (glGetProgrami(id, GL_VALIDATE_STATUS) == GL_FALSE)
            throw new ShaderProgramValidationFailedException(id);
    }

    public ShaderProgram bind() {
        if (!valid) throw new InvalidGLResourceException("Invalid ShaderProgram");
        ShaderProgram prev = bound;
        glUseProgram(id);
        bound = this;
        return prev;
    }

    public void unbind() {
        if (!valid) throw new InvalidGLResourceException("Invalid ShaderProgram");
        if (this == bound) {
            glUseProgram(0);
            bound = null;
        }
    }

    public void useIn(Runnable action) {
        ShaderProgram program = bind();
        action.run();
        unbind();
        if (program != null) program.bind();
    }

    public void compute(int xGroups, int yGroups, int zGroups) {
        useIn(() -> glDispatchCompute(xGroups, yGroups, zGroups));
    }

    @Override
    public void delete() {
        if (valid) {
            unbind();
            glDeleteProgram(id);
            valid = false;
        }
    }

    @Override
    public boolean isValid() {
        return valid;
    }
}
