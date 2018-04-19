import java.io.InputStream
import java.io.File

var inventory = Inventory()
var listaDeCenas = fileReader()

fun fileReader(): ArrayList<Scene>{
    val listaCenas = ArrayList<Scene>()

    val stream: InputStream = File(System.getProperty("user.dir") + "/src/Cenas.txt").inputStream()
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

fun runGame(jogo: Game): Int{
    do{
        println("Cena Atual: "+jogo.scenes[jogo.cena_atual].titulo)
        println("Descrição: "+jogo.scenes[jogo.cena_atual].descricao)
        println("Ação: ")
        var escolha: String = readLine()!!

        when(escolha){
            "2" -> {
                return 2
            }
            "3" -> {
                printInventario(inventory)
            }
            "4" -> {
                println("Digite o nome do Jogo:")
                var nomeJogo: String = readLine()!!
                saveGame(nomeJogo, concatenaString(jogo.cena_atual))
                return 2
            }
            "6" -> {
                printHelp()
            }
            else -> {
                val reg = Regex(" ")
                val list = escolha.split(reg)

                if(list.size == 1) {                        // asdas
                    println("Comando invalido!")
                }
                else{

                    var acao = list[0]
                    var objeto = list[1]

                    var objetoEncontrado = Objects()
                    var objetoOk = false

                    for (i in jogo.scenes[jogo.cena_atual].itens) {
                        if (i.nome.equals(objeto, true) && (i.comando_correto.equals(acao, true) || acao.equals("check", true))) {
                            objetoEncontrado = i
                            objetoOk = true
                        }
                    }

                    if (objetoOk) {
                        if(acao.equals("use", true)) {
                            jogo.cena_atual = objetoEncontrado.cena_alvo-1
                        }
                        else if(acao.equals("get", true)) {
                            objetoEncontrado.comando_correto = "USE"
                            inventory.itens.add(objetoEncontrado)
                        }
                        else if(acao.equals("check", true)){
                            println(objetoEncontrado.descricao)
                        }
                    } else {
                        println("Objeto ou Ação incorretos!")
                    }
                }
            }
        }
    }
    while (jogo.cena_atual != 15)

    println("Você venceu!")
    return 2
}

fun concatenaString(idCenaAtual: Int): String{
    var stringConcatenada = idCenaAtual.toString()
    var stringInventario = ""
    var retorno = ""

    if(inventory.itens.size > 0){
        for (i in inventory.itens){
            stringInventario += i.id.toString()
            stringInventario += ";"
        }

        retorno = stringConcatenada+";"+stringInventario
    }
    else{
        retorno = stringConcatenada
    }

    return retorno
}

fun saveGame(nomeJogo: String, conteudo: String){
    try {
        File(System.getProperty("user.dir") + "/src/$nomeJogo.txt").writeText(conteudo)
    } catch (e: Exception){
        e.printStackTrace()
    }
    println("Game saved!")
}

fun restartGame(): Game{
    println("Digite o nome do Jogo:")
    var nomeJogo: String = readLine()!!

    val stream: InputStream = File(System.getProperty("user.dir") + "/src/$nomeJogo.txt").inputStream() //a sa
    val str = stream.bufferedReader().use { it.readText() }
    val reg = Regex(";")
    var list = str.split(reg)

    var jogo = Game(listaDeCenas, Integer.parseInt(list[0]))

    if(!list.isEmpty()){
        for(i in list){
            var objeto = Objects()
            inventory.itens.add(objeto)
        }
    }

    return jogo
}

fun menuOption(){
    printMenu()

    var escolha = 0

    while (escolha != 2) {
        println("Escolha uma opção:")
        escolha = readLine()!!.toInt()

        when (escolha) {
            1 -> {
                escolha = runGame(newGame(listaDeCenas))
            }
            2 -> {
                println("Fim de Jogo!")
                return
            }
            3 -> {
                printInventario(inventory)
            }
            4 -> {
                println("Impossível salvar um jogo sem iniciá-lo!")
            }
            5 -> {
                escolha = runGame(restartGame())
            }
            6 -> {
                printHelp()
            }
        }
    }
}

fun printMenu(){
    println("----WELCOME TO Wacky-Race----\n" +
            "|                           |\n" +
            "| 1: New Game               |\n" +
            "| 2: End Game               |\n" +
            "| 3: Listar Inventário      |\n" +
            "| 4: Save Game              |\n" +
            "| 5: Restart Game           |\n" +
            "| 6: Help                   |\n" +
            "-----------------------------")
}

fun printInventario(listaInventario: Inventory){
    if(listaInventario.itens.size == 0){
        println("Inventário Vazio!")
    }
    else {
        println("Lista de objetos no Inventário:")
        for (i in listaInventario.itens) {
            println(i.nome)
        }
    }
}

fun printHelp(){
    println("------------HELP ME-------------\n" +
            "|                              |\n" +
            "| Comandos válidos:            |\n" +
            "| USE: usa um objeto.          |\n" +
            "| GET: pega um objeto.         |\n" +
            "| CHECK: descreve um objeto.   |\n" +
            "| Exemplo: USE/GET/CHECK objeto|\n" +
            "--------------------------------")
}

fun main(args: Array<String>) {
    menuOption()
}