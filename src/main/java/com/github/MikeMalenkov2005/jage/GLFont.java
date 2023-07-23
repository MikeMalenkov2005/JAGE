package com.github.MikeMalenkov2005.jage;

import com.github.MikeMalenkov2005.jage.textures.Texture2D;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.text.AttributedCharacterIterator;
import java.util.Map;

public class GLFont {
    private final Font font;

    public GLFont(Font font) {
        this.font = font;
    }

    public Font getFont() {
        return this.font;
    }

    public GLFont deriveFont(float size) {
        return new GLFont(this.font.deriveFont(size));
    }

    public GLFont deriveFont(int style, float size) {
        return new GLFont(this.font.deriveFont(style, size));
    }

    public GLFont deriveFont(int style, AffineTransform transform) {
        return new GLFont(this.font.deriveFont(style, transform));
    }

    public GLFont deriveFont(AffineTransform transform) {
        return new GLFont(this.font.deriveFont(transform));
    }

    public GLFont deriveFont(int style) {
        return new GLFont(this.font.deriveFont(style));
    }

    public GLFont deriveFont(Map<? extends AttributedCharacterIterator.Attribute, ?> attributes) {
        return new GLFont(this.font.deriveFont(attributes));
    }

    public Texture2D stringTexture(String str, int levels) {
        BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.setFont(this.font);
        FontMetrics metrics = g.getFontMetrics();
        int width = metrics.stringWidth(str);
        int height = metrics.getHeight();
        g.dispose();

        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        g = image.createGraphics();
        metrics = g.getFontMetrics();
        g.setColor(Color.BLACK);
        g.drawString(str, 0, metrics.getAscent());
        g.dispose();
        return Texture2D.load(image, levels);
    }
}
