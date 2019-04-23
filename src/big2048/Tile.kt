package big2048

import java.awt.Color

class Tile : Cloneable {
    var value: Int = 0

    val isEmpty: Boolean
        get() = value == 0

    val fontColor: Color
        get() = if (value < 16) Color(0x776e65) else Color(0xf9f6f2)

    val tileColor: Color
        get() = when (value) {
            0 -> Color(0xcdc1b4)
            2 -> Color(0xeee4da)
            4 -> Color(0xede0c8)
            8 -> Color(0xf2b179)
            16 -> Color(0xf59563)
            32 -> Color(0xf67c5f)
            64 -> Color(0xf65e3b)
            128 -> Color(0xedcf72)
            256 -> Color(0xedcc61)
            512 -> Color(0xedc850)
            1024 -> Color(0xedc53f)
            2048 -> Color(0xedc22e)
            else -> Color(0xff0000)
        }

    constructor() {
        value = 0
    }

    constructor(value: Int) {
        this.value = value
    }

    @Throws(CloneNotSupportedException::class)
    override fun clone(): Any {
        return super.clone()
    }

}