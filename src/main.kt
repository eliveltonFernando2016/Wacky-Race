import java.io.File
import java.io.InputStream

fun fileReader(): List<String>{
    val stream: InputStream = File("/home/elivelton/Dropbox/LP/Wacky Races/src/Cenas.txt").inputStream()
    val str = stream.bufferedReader().use { it.readText() }
    val reg = Regex(";")
    var list = str.split(reg)

    for((index, value) in list.withIndex()){

    }

    return list
}

fun main(args: Array<String>) {
    var game = Game()
    var scene = Scene()
    var inventory = Inventory()

    var listaCena: List<String> = fileReader()

    for((index, value) in listaCena.withIndex()){

    }
}