package big2048

import javax.swing.JFrame
import javax.swing.WindowConstants

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val controller = Controller(Model())
        val frame = JFrame()

        frame.apply {
            title = "2048"
            defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
            setSize(450, 500)
            isResizable = false

            add(controller.view)
            setLocationRelativeTo(null)
            isVisible = true
        }
    }
}