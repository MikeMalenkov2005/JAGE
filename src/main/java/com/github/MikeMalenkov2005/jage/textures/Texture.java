package com.github.MikeMalenkov2005.jage.textures;

import com.github.MikeMalenkov2005.jage.Deletable;
import com.github.MikeMalenkov2005.jage.InvalidGLResourceException;
import com.github.MikeMalenkov2005.jage.enums.Access;
import com.github.MikeMalenkov2005.jage.enums.ImageFormat;
import com.github.MikeMalenkov2005.jage.enums.WrapMode;

import java.util.*;

import static org.lwjgl.opengl.GL46.*;

public class Texture implements Deletable {
    private final static Map<Integer, Map<Integer, Texture>> bound = new HashMap<>();
    private final static Map<Integer, Map<Integer, Texture>> boundImages = new HashMap<>();

    protected final int id;
    private final int target;
    private boolean valid, filtered;
    private WrapMode wrapMode;

    protected Texture(int target) {
        this.id = glCreateTextures(target);
        this.target = target;
        setFiltered(true);
        setWrapMode(WrapMode.CLAMP_TO_BORDER);
    }

    public boolean isFiltered() {
        if (!valid) throw new InvalidGLResourceException("Invalid Texture");
        return filtered;
    }

    public void setFiltered(boolean filtered) {
        if (!valid) throw new InvalidGLResourceException("Invalid Texture");
        this.filtered = filtered;
        glTextureParameteri(id, GL_TEXTURE_MIN_FILTER, filtered ? GL_LINEAR : GL_NEAREST);
        glTextureParameteri(id, GL_TEXTURE_MAG_FILTER, filtered ? GL_LINEAR : GL_NEAREST);
    }

    public WrapMode getWrapMode() {
        if (!valid) throw new InvalidGLResourceException("Invalid Texture");
        return wrapMode;
    }

    public void setWrapMode(WrapMode wrapMode) {
        if (!valid) throw new InvalidGLResourceException("Invalid Texture");
        this.wrapMode = wrapMode;
        glTextureParameteri(id, GL_TEXTURE_WRAP_S, wrapMode.id);
        glTextureParameteri(id, GL_TEXTURE_WRAP_T, wrapMode.id);
        glTextureParameteri(id, GL_TEXTURE_WRAP_R, wrapMode.id);
    }

    public Texture bind(int unit) {
        if (!valid) throw new InvalidGLResourceException("Invalid Texture");
        Texture prev = bound.computeIfAbsent(target, k -> new HashMap<>()).getOrDefault(unit, null);
        glBindTextureUnit(unit, id);
        bound.get(target).put(unit, this);
        return prev;
    }

    public void unbind(int unit) {
        if (!valid) throw new InvalidGLResourceException("Invalid Texture");
        if (bound.computeIfAbsent(target, k -> new HashMap<>()).getOrDefault(unit, null) == this) {
            glBindTextureUnit(unit, 0);
            bound.get(target).remove(unit);
        }
    }

    public Texture bindImage(int unit, int level, Access access, ImageFormat format) {
        if (!valid) throw new InvalidGLResourceException("Invalid Texture");
        Texture prev = boundImages.computeIfAbsent(target, k -> new HashMap<>()).getOrDefault(unit, null);
        glBindImageTexture(unit, id, level, false, 0, access.id, format.id);
        boundImages.get(target).put(unit, this);
        return prev;
    }

    public void unbindImage(int unit) {
        if (!valid) throw new InvalidGLResourceException("Invalid Texture");
        if (boundImages.computeIfAbsent(target, k -> new HashMap<>()).getOrDefault(unit, null) == this) {
            glBindImageTexture(unit, 0, 0, false, 0, 0, 0);
            boundImages.get(target).remove(unit);
        }
    }

    @Override
    public void delete() {
        if (valid) {
            new HashSet<>(bound.computeIfAbsent(target, k -> new HashMap<>()).keySet()).forEach(this::unbind);
            new HashSet<>(boundImages.computeIfAbsent(target, k -> new HashMap<>()).keySet()).forEach(this::unbindImage);
            glDeleteTextures(id);
            valid = false;
        }
    }

    @Override
    public boolean isValid() {
        return valid;
    }
}
