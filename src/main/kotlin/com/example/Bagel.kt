package com.example

import javafx.scene.image.Image
import javafx.scene.shape.SVGPath

open class Bagel(
        protected val invinciBagel: InvinciBagel,
        svgData: String,
        xLocation: Double,
        yLocation: Double,
        vararg spriteCels: Image): Hero(
        svgData,
        xLocation,
        yLocation,
        *spriteCels) {
    var animator = false
    var frameCounter = 0
    var runningSpeed = 6

    companion object {
        const val SPRITE_PIXELS_X = 81.0
        const val SPRITE_PIXELS_Y = 81.0
        const val rightBoundary = WIDTH - SPRITE_PIXELS_X
        const val leftBoundary = 0.0
        const val bottomBoundary = HEIGHT - SPRITE_PIXELS_Y
        const val topBoundary = 0.0
    }

    override fun update() {
        setXYLocation()
        setBoundaries()
        setImageState()
        moveInvinciBagel(iX, iY)
        playAudioClip()
        checkCollision()
    }

    private fun checkCollision() {
        var count = 0
        while (count < invinciBagel.castDirector!!.CURRENT_CAST!!.size) {
            val other = invinciBagel.castDirector!!.CURRENT_CAST[count++]
            if (collide(other)) {
                invinciBagel.playWSound()
                invinciBagel.castDirector!!.addToRemoveActors(other)
                invinciBagel.root.children.remove(other.spriteFrame)
                invinciBagel.castDirector!!.resetRemoveActors()
                scoreEngine(other)
            }
        }
    }

    private fun scoreEngine(other: Actor) {
        when(other) {
            is Prop -> {
                invinciBagel.gameScore -= 1
                invinciBagel.playSSound()
            }
            is PropV -> {
                invinciBagel.gameScore -= 2
                invinciBagel.playSSound()
            }
            is PropH -> {
                invinciBagel.gameScore -= 1
                invinciBagel.playSSound()
            }
            is PropB -> {
                invinciBagel.gameScore -= 2
                invinciBagel.playSSound()
            }
            is Treasure -> {
                invinciBagel.gameScore += 5
                invinciBagel.playWSound()
            }
            invinciBagel.iBullet -> {
                invinciBagel.gameScore -= 5
                invinciBagel.playSSound()
            }
            invinciBagel.iCheese -> {
                invinciBagel.gameScore += 2
                invinciBagel.playSSound()
            }
            invinciBagel.iBeagle -> {
                invinciBagel.gameScore += 10
                invinciBagel.playSSound()
            }
        }
        invinciBagel.scoreText?.text = invinciBagel!!.gameScore.toString()
    }

    private fun setImageState() {
        if (!invinciBagel.up && !invinciBagel.down && !invinciBagel.left && !invinciBagel.right) {
            spriteFrame.image = imageStates.get(0)
            animator = false
            frameCounter = 0
        }

        if (invinciBagel.right) {
            spriteFrame.scaleX = 1.0
            this.isFlipH = false
            if (!animator && (!invinciBagel.down && !invinciBagel.up)) {
                spriteFrame.image = imageStates[1]
                if (frameCounter >= runningSpeed) {
                    animator = true
                    frameCounter = 0
                } else {
                    frameCounter++
                }
            } else if (animator){
                spriteFrame.image = imageStates[2]
                if (frameCounter >= runningSpeed) {
                    animator = false
                    frameCounter = 0
                } else {
                    frameCounter++
                }
            }
        }
        if (invinciBagel.left) {
            spriteFrame.scaleX = -1.0
            this.isFlipH = true
            if (!animator && (!invinciBagel.up && !invinciBagel.down)) {
                spriteFrame.image = imageStates[1]
                if (frameCounter >= runningSpeed) {
                    animator = true
                    frameCounter = 0
                } else {
                    frameCounter++
                }
            } else if(animator){
                spriteFrame.image = imageStates[2]
                if (frameCounter >= runningSpeed) {
                    animator = false
                    frameCounter = 0
                } else {
                    frameCounter++
                }
            }
        }
        if (invinciBagel.down) {
            spriteFrame.image = imageStates[6]
        }
        if (invinciBagel.up) {
            spriteFrame.image = imageStates[4]
        }

        if (invinciBagel.wKey) {
            spriteFrame.image = imageStates[5]
        }
        if (invinciBagel.sKey) {
            spriteFrame.image = imageStates[8]
        }
    }
    private fun setBoundaries() {
        if (iX >= rightBoundary) {
            iX = rightBoundary
        }
        if (iX <= leftBoundary) {
            iX = leftBoundary
        }
        if (iY >= bottomBoundary) {
            iY = bottomBoundary
        }
        if (iY <= topBoundary) {
            iY = topBoundary
        }
    }

    private fun setXYLocation() {
        if (invinciBagel.right) iX += vX
        if (invinciBagel.left) iX -= vX
        if (invinciBagel.up) iY -= vY
        if (invinciBagel.down) iY += vY

    }

    private fun moveInvinciBagel(x: Double, y: Double) {
        spriteFrame.translateX = x
        spriteFrame.translateY = y
    }

    override fun collide(other: Actor): Boolean {

        if (invinciBagel.iBagel?.spriteFrame?.boundsInParent!!.intersects(other.spriteFrame.boundsInParent)) {
//            val intersection = SVGPath.intersect(invinciBagel!!.iBagel!!.spriteBound, other.spriteBound)
//            if (intersection.boundsInLocal.width == -1.0) {
//                return true
//            }
            return true
        }
        return false
    }
    private fun playAudioClip() {
        if (invinciBagel.left) {
            invinciBagel.playLeftSound()
        }
        if (invinciBagel.right) {
            invinciBagel.playRightSound()
        }
        if (invinciBagel.up) {
            invinciBagel.playUpSound()
        }
        if (invinciBagel.down) {
            invinciBagel.playDownSound()
        }
        if (invinciBagel.wKey) {
            invinciBagel.playWSound()
        }
        if (invinciBagel.sKey) {
            invinciBagel.playSSound()
        }
    }
}