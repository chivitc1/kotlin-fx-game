package com.example

import javafx.scene.image.Image
import kotlin.random.Random

class Enemy : Actor {
    constructor(invinciBagel: InvinciBagel, svgData: String, xLocation: Double, yLocation: Double, vararg spriteCels: Image)
            : super(svgData, xLocation, yLocation, *spriteCels) {
        this.invinciBagel = invinciBagel
        isAlive = true
        isBonus = true
        hasValu = true
    }
    private var iBagelLocation = 0.0
    private var launchIt = false
    private var shootBullet = false
    private var bulletType = false
    private var invinciBagel: InvinciBagel
    var attackCounter = 0
    var attackFrequency = 250
    val attackBoundary = 300.0
    private var pauseCounter = 0.0

    var takeSides = false
    var onScreen = false
    var callAttack = false

    var spriteMoveR = 0.0
    var spriteMoveL = 0.0
    var destination = 0.0

    var randomLocation = 0.0
    var randomOffset = 0.0
    var bulletRange = 0.0
    var bulletOffset = 0.0
    var bulletGravity = 0.2
    var cheeseGravity = 0.1

    companion object {
        val randomNum = Random(100)
    }

    override fun update() {
        if(!callAttack) {
            if (attackCounter >= attackFrequency) {
                attackCounter = 0
                spriteMoveR = 700.0
                spriteMoveL = -70.0
                randomLocation = getRandom()
                takeSides = randomNum.nextBoolean()
                bulletType = randomNum.nextBoolean()
                iBagelLocation = invinciBagel.iBagel!!.iY
                if (bulletType) {
                    spriteFrame.translateY = randomLocation
                    randomOffset = randomLocation + 5
                } else {
                    spriteFrame.translateY = iBagelLocation
                    randomOffset = iBagelLocation + 5
                }

                callAttack = true
            } else {
                attackCounter++
            }
        } else {
            initiateAttack()
        }

        if (shootBullet) {
            shootProjectile()
            if (pauseCounter >= 60.0) {
                launchIt = true
                pauseCounter = 0.0
            } else {
                pauseCounter++
            }
        }
    }

    private fun getRandom() : Double {
        return randomNum.nextDouble(attackBoundary)
    }

    private fun initiateAttack() {
        if (!takeSides) {
            spriteFrame.scaleX = 1.0
            this.isFlipH = false
            if (!onScreen) {
                destination = 500.0
                if (spriteMoveR >= destination) {
                    spriteMoveR -= 2
                    spriteFrame.translateX = spriteMoveR
                } else {
                    bulletOffset = 480.0
                    shootBullet = true
                    onScreen = true
                }
            }
            if (onScreen && launchIt){
                destination = 700.0
                if (spriteMoveR <= destination) {
                    spriteMoveR += 1
                    spriteFrame.translateX = spriteMoveR
                } else {
                    onScreen = false
                    callAttack = false
                    launchIt = false
                    loadBullet()
                    loadCheese()
                    loadEnemy()
                    attackFrequency = 60 + randomNum.nextInt(480)
                }
            }
        } else {
            spriteFrame.scaleX = -1.0
            this.isFlipH = true

            if (!onScreen) {
                destination = 100.0
                if (spriteMoveL <= destination) {
                    spriteMoveL += 2
                    spriteFrame.translateX = spriteMoveL
                } else {
                    bulletOffset = 120.0
                    shootBullet = true
                    onScreen = true
                }

            }
            if (onScreen && launchIt) {
                destination = -70.0
                if (spriteMoveL >= destination) {
                    spriteMoveL -= 1
                    spriteFrame.translateX = spriteMoveL
                } else {
                    onScreen = false
                    callAttack = false
                    launchIt = false
                    loadBullet()
                    loadCheese()
                    loadEnemy()
                    attackFrequency = 60 + randomNum.nextInt(480)
                }
            }
        }
    }

    private fun shootProjectile() {
        if (!takeSides && !bulletType) {
            invinciBagel.iBullet!!.spriteFrame.translateY = randomOffset
            invinciBagel.iBullet!!.spriteFrame.scaleX = -0.5
            invinciBagel.iBullet!!.spriteFrame.scaleY = 0.5
            bulletRange = -50.0
            if (bulletOffset >= bulletRange) {
                bulletOffset -= 6
                invinciBagel.iBullet!!.spriteFrame.translateX = bulletOffset
                randomOffset += bulletGravity
            } else {
                shootBullet = false
            }
        }

        if (takeSides && !bulletType) {
            invinciBagel.iBullet!!.spriteFrame.translateY = randomOffset
            invinciBagel.iBullet!!.spriteFrame.scaleX = 0.5
            invinciBagel.iBullet!!.spriteFrame.scaleY = 0.5
            bulletRange = 674.0
            if (bulletOffset <= bulletRange) {
                bulletOffset += 6
                invinciBagel.iBullet!!.spriteFrame.translateX = bulletOffset
                randomOffset += bulletGravity
            } else {
                shootBullet = false
            }
        }

        if (!takeSides && bulletType) {
            invinciBagel.iCheese!!.spriteFrame.translateY = randomOffset
            invinciBagel.iCheese!!.spriteFrame.scaleX = -0.5
            invinciBagel.iCheese!!.spriteFrame.scaleY = 0.5
            bulletRange = -50.0
            if (bulletOffset >= bulletRange) {
                bulletOffset -= 4
                invinciBagel.iCheese!!.spriteFrame.translateX = bulletOffset
                randomOffset += cheeseGravity
            } else {
                shootBullet = false
            }
        }

        if (takeSides && bulletType) {
            invinciBagel.iCheese!!.spriteFrame.translateY = randomOffset
            invinciBagel.iCheese!!.spriteFrame.scaleX = 0.5
            invinciBagel.iCheese!!.spriteFrame.scaleY = 0.5
            bulletRange = 624.0
            if (bulletOffset <= bulletRange) {
                bulletOffset += 4
                invinciBagel.iCheese!!.spriteFrame.translateX = bulletOffset
                randomOffset += cheeseGravity
            } else {
                shootBullet = false
            }
        }
    }


    private fun loadBullet() {
        invinciBagel.castDirector!!.CURRENT_CAST.forEach {
            if (it == invinciBagel.iBullet) return
        }
        invinciBagel.castDirector!!.addCurrentCast(invinciBagel.iBullet!!)
        invinciBagel.root.children.add(invinciBagel.iBullet!!.spriteFrame)
    }

    private fun loadCheese() {
        invinciBagel.castDirector!!.CURRENT_CAST.forEach {
            if (it == invinciBagel.iCheese) return
        }
        invinciBagel.castDirector!!.addCurrentCast(invinciBagel.iCheese!!)
        invinciBagel.root.children.add(invinciBagel.iCheese!!.spriteFrame)
    }

    private fun loadEnemy() {
        invinciBagel.castDirector!!.CURRENT_CAST.forEach {
            if (it == invinciBagel.iBeagle) return
        }
        invinciBagel.castDirector!!.addCurrentCast(invinciBagel.iBeagle!!)
        invinciBagel.root.children.add(invinciBagel.iBeagle!!.spriteFrame)
    }
}