package com.github.MikeMalenkov2005.jage.shaders;

import com.github.MikeMalenkov2005.jage.Deletable;
import com.github.MikeMalenkov2005.jage.InvalidGLResourceException;
import com.github.MikeMalenkov2005.jage.enums.GLSLStruct;

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
        boolean needBinding = this != bound;
        ShaderProgram program = null;
        if (needBinding) program = bind();
        action.run();
        if (needBinding) {
            if (program == null) unbind();
            else program.bind();
        }
    }

    public int getUniformLocation(String name) {
        if (!valid) throw new InvalidGLResourceException("Invalid ShaderProgram");
        return glGetUniformLocation(id, name);
    }

    public void setUniform(int location, GLSLStruct struct, boolean signed, int... data) throws ShaderException {
        if (!valid) throw new InvalidGLResourceException("Invalid ShaderProgram");
        switch (struct) {
            case SCALAR -> {
                if (signed) glProgramUniform1iv(id, location, data);
                else glProgramUniform1uiv(id, location, data);
            }
            case VECTOR_2D -> {
                if (signed) glProgramUniform2iv(id, location, data);
                else glProgramUniform2uiv(id, location, data);
            }
            case VECTOR_3D -> {
                if (signed) glProgramUniform3iv(id, location, data);
                else glProgramUniform3uiv(id, location, data);
            }
            case VECTOR_4D -> {
                if (signed) glProgramUniform4iv(id, location, data);
                else glProgramUniform4uiv(id, location, data);
            }
            default -> throw new ShaderException("Integer matrices are not supported in GLSL");
        }
    }

    public void setUniform(int location, GLSLStruct struct, float... data) throws ShaderException {
        if (!valid) throw new InvalidGLResourceException("Invalid ShaderProgram");
        switch (struct) {
            case SCALAR -> glProgramUniform1fv(id, location, data);
            case VECTOR_2D -> glProgramUniform2fv(id, location, data);
            case VECTOR_3D -> glProgramUniform3fv(id, location, data);
            case VECTOR_4D -> glProgramUniform4fv(id, location, data);
            case MATRIX_2X2 -> glProgramUniformMatrix2fv(id, location, false, data);
            case MATRIX_2X3 -> glProgramUniformMatrix2x3fv(id, location, false, data);
            case MATRIX_2X4 -> glProgramUniformMatrix2x4fv(id, location, false, data);
            case MATRIX_3X2 -> glProgramUniformMatrix3x2fv(id, location, false, data);
            case MATRIX_3X3 -> glProgramUniformMatrix3fv(id, location, false, data);
            case MATRIX_3X4 -> glProgramUniformMatrix3x4fv(id, location, false, data);
            case MATRIX_4X2 -> glProgramUniformMatrix4x2fv(id, location, false, data);
            case MATRIX_4X3 -> glProgramUniformMatrix4x3fv(id, location, false, data);
            case MATRIX_4X4 -> glProgramUniformMatrix4fv(id, location, false, data);
        }
    }

    public void setUniform(int location, GLSLStruct struct, double... data) throws ShaderException {
        if (!valid) throw new InvalidGLResourceException("Invalid ShaderProgram");
        switch (struct) {
            case SCALAR -> glProgramUniform1dv(id, location, data);
            case VECTOR_2D -> glProgramUniform2dv(id, location, data);
            case VECTOR_3D -> glProgramUniform3dv(id, location, data);
            case VECTOR_4D -> glProgramUniform4dv(id, location, data);
            case MATRIX_2X2 -> glProgramUniformMatrix2dv(id, location, false, data);
            case MATRIX_2X3 -> glProgramUniformMatrix2x3dv(id, location, false, data);
            case MATRIX_2X4 -> glProgramUniformMatrix2x4dv(id, location, false, data);
            case MATRIX_3X2 -> glProgramUniformMatrix3x2dv(id, location, false, data);
            case MATRIX_3X3 -> glProgramUniformMatrix3dv(id, location, false, data);
            case MATRIX_3X4 -> glProgramUniformMatrix3x4dv(id, location, false, data);
            case MATRIX_4X2 -> glProgramUniformMatrix4x2dv(id, location, false, data);
            case MATRIX_4X3 -> glProgramUniformMatrix4x3dv(id, location, false, data);
            case MATRIX_4X4 -> glProgramUniformMatrix4dv(id, location, false, data);
        }
    }

    public void getUniform(int location, boolean signed, int[] data) {
        if (!valid) throw new InvalidGLResourceException("Invalid ShaderProgram");
        if (signed) glGetUniformiv(id, location, data);
        else glGetUniformuiv(id, location, data);
    }

    public void getUniform(int location, float[] data) {
        if (!valid) throw new InvalidGLResourceException("Invalid ShaderProgram");
        glGetUniformfv(id, location, data);
    }

    public void getUniform(int location, double[] data) {
        if (!valid) throw new InvalidGLResourceException("Invalid ShaderProgram");
        glGetUniformdv(id, location, data);
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
