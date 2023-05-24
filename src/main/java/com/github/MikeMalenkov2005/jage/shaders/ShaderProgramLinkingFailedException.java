package com.github.MikeMalenkov2005.jage.shaders;

import static org.lwjgl.opengl.GL20.glGetProgramInfoLog;

public class ShaderProgramLinkingFailedException extends ShaderException {
    public ShaderProgramLinkingFailedException(int id) {
        super("Shader program linking failed: " + glGetProgramInfoLog(id));
    }
}
