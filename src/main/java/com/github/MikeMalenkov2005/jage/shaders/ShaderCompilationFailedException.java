package com.github.MikeMalenkov2005.jage.shaders;

import static org.lwjgl.opengl.GL20.glGetShaderInfoLog;

public class ShaderCompilationFailedException extends ShaderException {
    public ShaderCompilationFailedException(int id) {
        super("Shader compilation failed: " + glGetShaderInfoLog(id));
    }
}
