package ss.kutil

import java.io.File
import java.io.OutputStream
import java.io.PrintWriter

fun buildStream(outputStream: OutputStream = System.out, builderAction: Appendable.() -> Unit) {
    val out = PrintWriter(outputStream)
    out.builderAction()
    out.flush()
}

fun buildFile(f: File, builderAction: Appendable.() -> Unit) {
    f.bufferedWriter().use {
        it.builderAction()
        it.flush()
    }
}