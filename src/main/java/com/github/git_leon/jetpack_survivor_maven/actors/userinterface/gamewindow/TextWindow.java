package com.github.git_leon.jetpack_survivor_maven.actors.userinterface.gamewindow;

import com.github.git_leon.jfoot.sprite.Sprite;
import com.github.git_leon.jfoot.resources.JFootTextImageBuilder;
import greenfoot.Color;

public class TextWindow extends Sprite {

    private JFootTextImageBuilder imageBuilder;

    public TextWindow(JFootTextImageBuilder imageBuilder) {
        super(imageBuilder.build());
        this.imageBuilder = imageBuilder;
    }

    public TextWindow() {
        this(new JFootTextImageBuilder());
        imageBuilder.setString("")
                .setSize(0)
                .setBackground(Color.WHITE)
                .setForeground(Color.BLACK)
                .setOutline(Color.GREEN);
        updateImage();
    }

    public void updateForeground(Color foreground) {
        imageBuilder.setForeground(foreground);
        updateImage();
    }

    public void updateBackground(Color background) {
        imageBuilder.setBackground(background);
        updateImage();
    }

    public void updateOutline(Color outline) {
        imageBuilder.setOutline(outline);
        updateImage();
    }

    public void updateStr(String str) {
        imageBuilder.setString(str);
        updateImage();
    }

    public void updateSize(int size) {
        imageBuilder.setSize(size);
        updateImage();
    }

    private TextWindow updateImage() {
        setImage(imageBuilder.build());
        return this;
    }
}
