package big2048

import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent

class Controller(private val model: Model) : KeyAdapter() {
    val view: View = View(this)

    private val winTile = 2048

    val gameTiles: Array<Array<Tile>>
        get() = model.gameTiles

    val score: Int
        get() = model.score

    fun resetGame() {
        model.score = 0
        model.resetGameTiles()
        view.isGameLost = false
        view.isGameWon = false

    }

    override fun keyPressed(e: KeyEvent?) {
        if (e!!.keyCode == KeyEvent.VK_ESCAPE) resetGame()
        if (!view.isGameLost && !view.isGameWon) {
            when (e.keyCode) {
                KeyEvent.VK_LEFT -> model.left()
                KeyEvent.VK_RIGHT -> model.right()
                KeyEvent.VK_UP -> model.up()
                KeyEvent.VK_DOWN -> model.down()
            }
        }
        if (!model.canMove()) view.isGameLost = true
        if (model.maxTile == winTile) view.isGameWon = true
        view.repaint()
    }


}