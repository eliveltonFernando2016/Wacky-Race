import java.io.File
import java.io.InputStream

fun fileReader(): ArrayList<Scene>{
    val listaCenas = ArrayList<Scene>()

    val stream: InputStream = File("/Users/cogeti/Documents/Wacky-Race/src/Cenas.txt").inputStream()
    val str = stream.bufferedReader().use { it.readText() }
    val reg = Regex(";;")
    var list = str.split(reg)

    for (i in list){
        val regAux = Regex(";")
        val listAux = i.split(regAux)

        val listaObjetos = ArrayList<Objects>()

        var j = 3

        while (j < (listAux.size)-1){
            var objAux = Objects(Integer.parseInt(listAux[j++]),
                                 listAux[j++],
                                 Integer.parseInt(listAux[j++]),
                                 listAux[j++],
                                 listAux[j++],
                                 Integer.parseInt(listAux[j++]))
            listaObjetos.add(objAux)
        }

        val scene = Scene(Integer.parseInt(listAux[0]), listAux[1], listAux[2], listaObjetos)
        listaCenas.add(scene)
    }

    return listaCenas
}

fun newGame(listaCenas: ArrayList<Scene>): Game{
    return Game(listaCenas, 0)
}

fun printMenu(){
    println("----WELCOME TO Wacky-Race:----\n" +
            "|                           |\n" +
            "| 1: New Game               |\n" +
            "| 2: End Game               |\n" +
            "-----------------------------")
}

fun menuOption(option: Int){
    printMenu()

    while (option != 2){

        println("Escolha uma opção:")
        val escolha:Int = readLine()!!.toInt()

        when(escolha){
            1 ->{
                println("Escolhi Novo Jogo!")
                newGame(fileReader())
            }
            2 -> {
                println("Fim de Jogo!")
                return
            }
        }
    }
}

fun main(args: Array<String>) {
    menuOption(0)

    //newGame(fileReader())



}