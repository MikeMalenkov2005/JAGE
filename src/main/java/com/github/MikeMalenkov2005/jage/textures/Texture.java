package com.github.MikeMalenkov2005.jage.textures;

import com.github.MikeMalenkov2005.jage.GLResource;
import com.github.MikeMalenkov2005.jage.enums.Access;
import com.github.MikeMalenkov2005.jage.enums.ImageFormat;
import com.github.MikeMalenkov2005.jage.enums.WrapMode;

import java.util.*;

import static org.lwjgl.opengl.GL46.*;

public class Texture extends GLResource {
    private final static Map<Integer, Map<Integer, Texture>> bound = new HashMap<>();
    private final static Map<Integer, Map<Integer, Texture>> boundImages = new HashMap<>();

    public final ImageFormat format;
    public final int id;
    private final int target;
    private boolean filtered;
    private WrapMode wrapMode;

    protected Texture(ImageFormat format, int target) {
        this.id = glCreateTextures(target);
        this.target = target;
        this.format = format;
        setFiltered(true);
        setWrapMode(WrapMode.CLAMP_TO_BORDER);
    }

    public boolean isFiltered() {
        exceptInvalid("Texture");
        return filtered;
    }

    public void setFiltered(boolean filtered) {
        exceptInvalid("Texture");
        this.filtered = filtered;
        glTextureParameteri(id, GL_TEXTURE_MIN_FILTER, filtered ? GL_LINEAR : GL_NEAREST);
        glTextureParameteri(id, GL_TEXTURE_MAG_FILTER, filtered ? GL_LINEAR : GL_NEAREST);
    }

    public WrapMode getWrapMode() {
        exceptInvalid("Texture");
        return wrapMode;
    }

    public void setWrapMode(WrapMode wrapMode) {
        exceptInvalid("Texture");
        this.wrapMode = wrapMode;
        glTextureParameteri(id, GL_TEXTURE_WRAP_S, wrapMode.id);
        glTextureParameteri(id, GL_TEXTURE_WRAP_T, wrapMode.id);
        glTextureParameteri(id, GL_TEXTURE_WRAP_R, wrapMode.id);
    }

    public Texture bind(int unit) {
        exceptInvalid("Texture");
        Texture prev = bound.computeIfAbsent(target, k -> new HashMap<>()).getOrDefault(unit, null);
        glBindTextureUnit(unit, id);
        bound.get(target).put(unit, this);
        return prev;
    }

    public void unbind(int unit) {
        exceptInvalid("Texture");
        if (bound.computeIfAbsent(target, k -> new HashMap<>()).getOrDefault(unit, null) == this) {
            glBindTextureUnit(unit, 0);
            bound.get(target).remove(unit);
        }
    }

    public Texture bindImage(int unit, int level, Access access, ImageFormat format) {
        exceptInvalid("Texture");
        Texture prev = boundImages.computeIfAbsent(target, k -> new HashMap<>()).getOrDefault(unit, null);
        glBindImageTexture(unit, id, level, false, 0, access.id, format.id);
        boundImages.get(target).put(unit, this);
        return prev;
    }

    public void unbindImage(int unit) {
        exceptInvalid("Texture");
        if (boundImages.computeIfAbsent(target, k -> new HashMap<>()).getOrDefault(unit, null) == this) {
            glBindImageTexture(unit, 0, 0, false, 0, 0, 0);
            boundImages.get(target).remove(unit);
        }
    }

    @Override
    protected void cleanUp() {
        new HashSet<>(bound.computeIfAbsent(target, k -> new HashMap<>()).keySet()).forEach(this::unbind);
        new HashSet<>(boundImages.computeIfAbsent(target, k -> new HashMap<>()).keySet()).forEach(this::unbindImage);
        glDeleteTextures(id);
    }
}
