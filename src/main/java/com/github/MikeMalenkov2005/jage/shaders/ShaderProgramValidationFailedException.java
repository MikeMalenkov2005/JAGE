package com.github.MikeMalenkov2005.jage.shaders;

import static org.lwjgl.opengl.GL20.glGetProgramInfoLog;

public class ShaderProgramValidationFailedException extends ShaderException {
    public ShaderProgramValidationFailedException(int id) {
        super("Shader program validation failed: " + glGetProgramInfoLog(id));
    }
}
