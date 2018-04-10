class Game {
    var scenes = arrayListOf<Scene>()
    var cena_atual: Int = 0

    constructor(scenes: ArrayList<Scene>, cena_atual: Int) {
        this.scenes = scenes
        this.cena_atual = cena_atual
    }
}