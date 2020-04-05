package com.example

import javafx.scene.image.Image

class Projectile : Actor {
    constructor(svgData: String, xLocation: Double, yLocation: Double, vararg spriteCels: Image)
            : super(svgData, xLocation, yLocation, *spriteCels) {
        isFixed = true
        isBonus = true
        hasValu = true
    }

    override fun update() {

    }
}