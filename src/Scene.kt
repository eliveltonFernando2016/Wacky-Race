class Scene {
    var id: Int = 0
    var titulo: String = ""
    var descricao: String = ""
    var itens = arrayListOf<Objects>()

    constructor()

    constructor(id: Int, titulo: String, descricao: String, itens: ArrayList<Objects>) {
        this.id = id
        this.titulo = titulo
        this.descricao = descricao
        this.itens = itens
    }

    override fun toString(): String {
        return "Scene(id=$id, titulo='$titulo', descricao='$descricao', itens=$itens)"
    }


}