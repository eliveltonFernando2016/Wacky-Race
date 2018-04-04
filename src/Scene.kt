class Scene {
    var id: Int = 0
    var titulo: String = ""
    var descricao: String = ""
    var itens = arrayListOf<Objects>()

    constructor(id: Int, titulo: String, descricao: String) {
        this.id = id
        this.titulo = titulo
        this.descricao = descricao
    }

    constructor()

    override fun toString(): String {
        return "Scene(id=$id, titulo='$titulo', descricao='$descricao')"
    }
}