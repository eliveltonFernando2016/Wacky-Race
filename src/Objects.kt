class Objects {
    var id: Int = 0
    var nome: String = ""
    var tipo: Int = 0
    var descricao: String = ""
    var comando_correto: String = ""
    var cena_alvo: Int = 0
    var resultado_positivo: String = ""
    var resultado_negativo: String = ""
    var resolvido: Boolean = false
    var obtido: Boolean = false

    constructor(id: Int, nome: String, tipo: Int, descricao: String, comando_correto: String, cena_alvo: Int) {
        this.id = id
        this.nome = nome
        this.tipo = tipo
        this.descricao = descricao
        this.comando_correto = comando_correto
        this.cena_alvo = cena_alvo
    }

    constructor()

    override fun toString(): String {
        return "Objects(id=$id, nome='$nome', tipo=$tipo, descricao='$descricao', comando_correto='$comando_correto', cena_alvo=$cena_alvo)"
    }
}