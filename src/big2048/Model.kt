package big2048

class Model {
    private val FIELD_WIDTH = 4

    var maxTile: Int = 0
    var score: Int = 0

    lateinit var gameTiles: Array<Array<Tile>>

    private val emptyTiles: List<Tile>
        get() {
            val tiles = ArrayList<Tile>()

            for (i in 0 until FIELD_WIDTH) {
                for (j in 0 until FIELD_WIDTH) {
                    if (gameTiles[i][j].isEmpty) {
                        tiles.add(gameTiles[i][j]);
                    }
                }
            }

            return tiles
        }

    init {
        resetGameTiles()
    }

    fun resetGameTiles() {
        gameTiles = arrayOf()

        for (row in 0 until FIELD_WIDTH) {
            var array = arrayOf<Tile>()
            for (cell in 0 until FIELD_WIDTH) {
                array += Tile()
            }
            gameTiles += array
        }
        addTile()
        addTile()

    }

    private fun addTile() {
        if (!emptyTiles.isEmpty()) {
            val tile = emptyTiles[(Math.random() * emptyTiles.size).toInt()]
            tile.value = if (Math.random() < 0.9) 2 else 4
        }
    }

    private fun mergeTiles(tiles: Array<Tile>): Boolean {
        var change = false

        for (i in 0 until tiles.size - 1) {
            if (!tiles[i].isEmpty && !tiles[i + 1].isEmpty && tiles[i].value == tiles[i + 1].value) {
                tiles[i].value *= 2
                tiles[i + 1] = Tile()
                maxTile = if (tiles[i].value > maxTile) tiles[i].value else maxTile
                score += tiles[i].value
                change = true
                compressTiles(tiles)
            }
        }

        return change
    }

    private fun compressTiles(tiles: Array<Tile>): Boolean {
        var change: Boolean = false

        for (i in tiles.indices) {
            for (j in 0 until tiles.size - 1) {
                if (tiles[j].isEmpty && !tiles[j + 1].isEmpty) {
                    tiles[j] = tiles[j + 1]
                    tiles[j + 1] = Tile()
                    change = true
                }
            }
        }

        return change
    }

    private fun rotation() {
        var temp: Array<Array<Tile>> = arrayOf()

        for (i in 0 until FIELD_WIDTH) {
            var array = arrayOf<Tile>()
            for (j in 0 until FIELD_WIDTH) {
                array += gameTiles[i][j]
            }
            temp += array
        }

        for (i in 0 until FIELD_WIDTH) {
            for (j in 0 until FIELD_WIDTH) {
                gameTiles[i][j] = temp[gameTiles.size - j - 1][i]
            }
        }
    }

    fun left() {
        var change = false

        for (i in 0 until FIELD_WIDTH) {
            //change = (compressTiles(gameTiles[i]) or mergeTiles(gameTiles[i]))
            if (compressTiles(gameTiles[i]) or mergeTiles(gameTiles[i])) change = true
        }

        if (change) addTile()

    }

    fun right() {
        rotation()
        rotation()
        left()
        rotation()
        rotation()
    }

    fun up() {
        rotation()
        rotation()
        rotation()
        left()
        rotation()
    }

    fun down() {
        rotation()
        left()
        rotation()
        rotation()
        rotation()
    }

    fun canMove(): Boolean {
        if (emptyTiles.isNotEmpty()) return  true

        for (i in 0 until FIELD_WIDTH - 1) {
            for (j in 0 until FIELD_WIDTH - 1) {
                if (gameTiles[i][j].value == gameTiles[i][j + 1].value) return true
                if (gameTiles[i + 1][j].value == gameTiles[i][j].value) return true
            }
        }


        return false
    }


}