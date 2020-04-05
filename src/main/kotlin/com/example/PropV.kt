package com.example

import javafx.scene.image.Image

class PropV : Actor {
    constructor(svgData: String, xLocation: Double, yLocation: Double, vararg spriteCels: Image)
            : super(svgData, xLocation, yLocation, *spriteCels) {
        this.isFlipV = true
        spriteFrame.scaleY = -1.0
        spriteFrame.translateX = xLocation
        spriteFrame.translateY = yLocation
    }

    override fun update() {

    }
}