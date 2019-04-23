package big2048


import javax.swing.*
import java.awt.*

class View(private val controller: Controller) : JPanel() {

    var isGameWon = false
    var isGameLost = false

    init {
        isFocusable = true
        addKeyListener(controller)
    }

    override fun paint(g: Graphics?) {
        super.paint(g)
        g!!.color = BG_COLOR
        g.fillRect(0, 0, this.size.width, this.size.height)
        for (x in 0..3) {
            for (y in 0..3) {
                drawTile(g, controller.gameTiles[y][x], x, y)
            }
        }

        g.drawString("Score: " + controller.score, 140, 465)

        if (isGameWon) {
            JOptionPane.showMessageDialog(this, "You've won!")
        } else if (isGameLost) {
            JOptionPane.showMessageDialog(this, "You've lost :(")
        }
    }

    private fun drawTile(g2: Graphics, tile: Tile, x: Int, y: Int) {
        val g = g2 as Graphics2D
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
        val value = tile.value
        val xOffset = offsetCoors(x)
        val yOffset = offsetCoors(y)
        g.color = tile.tileColor
        g.fillRoundRect(xOffset, yOffset, TILE_SIZE, TILE_SIZE, 8, 8)
        g.color = tile.fontColor
        val size = if (value < 100) 36 else if (value < 1000) 32 else 24
        val font = Font(FONT_NAME, Font.BOLD, size)
        g.font = font

        val s = value.toString()
        val fm = getFontMetrics(font)

        val w = fm.stringWidth(s)
        val h = -fm.getLineMetrics(s, g).baselineOffsets[2].toInt()

        if (value != 0)
            g.drawString(s, xOffset + (TILE_SIZE - w) / 2, yOffset + TILE_SIZE - (TILE_SIZE - h) / 2 - 2)
    }

    companion object {
        private val BG_COLOR = Color(0xbbada0)
        private val FONT_NAME = "Arial"
        private val TILE_SIZE = 96
        private val TILE_MARGIN = 12

        private fun offsetCoors(arg: Int): Int {
            return arg * (TILE_MARGIN + TILE_SIZE) + TILE_MARGIN
        }
    }
}
