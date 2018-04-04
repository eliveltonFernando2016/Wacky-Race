import java.io.File
import java.io.InputStream

fun fileReader(): ArrayList<Scene>{
    val listaCenas = ArrayList<Scene>()

    val stream: InputStream = File("/home/elivelton/Dropbox/LP/Wacky-Race/src/Cenas.txt").inputStream()
    val str = stream.bufferedReader().use { it.readText() }
    val reg = Regex(";")
    var list = str.split(reg)

    for (i in list){
        /*for(j in i){
            val regAux = Regex("\n")
            val listAux = i.split(regAux)
            val scene = Scene(Integer.parseInt(listAux[0]), listAux[1], listAux[2])

            listaCenas.add(scene)
        }*/
        val regAux = Regex("\n")
        val listAux = i.split(regAux)

        for(j in listAux){
            println(j)
        }
    }

    return listaCenas
}

fun main(args: Array<String>) {
    var cenas = fileReader()

    for (i in cenas){
        println(i.toString())
    }
}