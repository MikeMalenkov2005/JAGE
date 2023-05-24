package com.github.MikeMalenkov2005.jage.shaders;

import com.github.MikeMalenkov2005.jage.Deletable;

import static org.lwjgl.opengl.GL46.*;

public class Shader implements Deletable {
    public final int id;
    private boolean valid = true;

    private Shader(int type, CharSequence source) throws ShaderCompilationFailedException {
        id = glCreateShader(type);
        glShaderSource(id, source);
        glCompileShader(id);
        if (glGetShaderi(id, GL_COMPILE_STATUS) == GL_FALSE) throw new ShaderCompilationFailedException(id);
    }

    @Override
    public void delete() {
        if (valid) {
            glDeleteShader(id);
            valid = false;
        }
    }

    @Override
    public boolean isValid() {
        return valid;
    }

    public static Shader vertex(CharSequence source) throws ShaderCompilationFailedException {
        return new Shader(GL_VERTEX_SHADER, source);
    }

    public static Shader fragment(CharSequence source) throws ShaderCompilationFailedException {
        return new Shader(GL_FRAGMENT_SHADER, source);
    }

    public static Shader geometry(CharSequence source) throws ShaderCompilationFailedException {
        return new Shader(GL_GEOMETRY_SHADER, source);
    }

    public static Shader tessControl(CharSequence source) throws ShaderCompilationFailedException {
        return new Shader(GL_TESS_CONTROL_SHADER, source);
    }

    public static Shader tessEvaluation(CharSequence source) throws ShaderCompilationFailedException {
        return new Shader(GL_TESS_EVALUATION_SHADER, source);
    }

    public static Shader compute(CharSequence source) throws ShaderCompilationFailedException {
        return new Shader(GL_VERTEX_SHADER, source);
    }
}
