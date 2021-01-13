package com.soucod.jfx2.samples.learn.brickbreaker;

import javafx.scene.Parent;
import javafx.scene.image.ImageView;

/**
 * Description:  此类描述
 * Author: LuDaShi
 * Date: 2021-01-11-17:34
 * UpdateDate: 2021-01-11-17:34
 * FileName: Ball
 * Version: 0.0.0.1
 * Since: 0.0.0.1
 */

public class Ball extends Parent {

    public static final int DEFAULT_SIZE = 2;

    public static final int MAX_SIZE = 5;

    private int size;

    private int diameter;
    private ImageView imageView;

    public Ball() {
        imageView = new ImageView();
        getChildren().add(imageView);
        changeSize(DEFAULT_SIZE);
        setMouseTransparent(true);
    }

    public int getSize() {
        return size;
    }

    public int getDiameter() {
        return diameter;
    }

    public void changeSize(int newSize) {
        size = newSize;
        imageView.setImage(Config.getImages().get(Config.IMAGE_BALL_0 + size));
        diameter = (int) imageView.getImage().getWidth() - Config.SHADOW_WIDTH;
    }

}
