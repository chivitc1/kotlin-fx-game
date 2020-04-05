package com.example

import javafx.scene.image.Image

class Prop : Actor {
    constructor(svgData: String, xLocation: Double, yLocation: Double, vararg spriteCels: Image)
            : super(svgData, xLocation, yLocation, *spriteCels) {
        spriteFrame.translateX = xLocation
        spriteFrame.translateY = yLocation
    }

    override fun update() {
    }
}