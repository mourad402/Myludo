package fr.app.myludo.utils.listeners

import fr.app.myludo.data.Game


interface OnClickGameListener {
    fun onGameClick(game: Game)
}