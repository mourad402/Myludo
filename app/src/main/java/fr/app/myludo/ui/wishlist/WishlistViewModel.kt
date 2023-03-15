package fr.app.myludo.ui.wishlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.app.myludo.data.Game
import kotlinx.coroutines.launch

class WishlistViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Wishlist Fragment"
    }
    val text: LiveData<String> = _text

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<GameApiStatus>()

    // The external immutable LiveData for the request status
    val status: LiveData<GameApiStatus> = _status

    // Internally, we use a MutableLiveData, because we will be updating the List of GamePhoto
    // with new values
    private val _games = MutableLiveData<List<Game>>()

    // The external LiveData interface to the property is immutable, so only this class can modify
    val games: LiveData<List<Game>> = _games

    /**
     * Call getGamePhotos() on init so we can display status immediately.
     */
    init {
        getGamePhotos()
    }

    /**
     * Gets Game games information from the Game API Retrofit service and updates the
     * [GamePhoto] [List] [LiveData].
     */
    private fun getGamePhotos() {

        viewModelScope.launch {
            _status.value = GameApiStatus.LOADING
            val tempGames = ArrayList<Game>()
            for (i in 1..100) {
                tempGames.add(Game(i.toString(), "Game $i", "https://www.myludo.fr/img/jeux/1664459680/jpg/ch/59045.jpg"))
            }
            _games.value = tempGames
            try {
                //_games.value = GameApi.retrofitService.getPhotos()
                _status.value = GameApiStatus.DONE
            } catch (e: Exception) {
                _status.value = GameApiStatus.ERROR
                _games.value = listOf()
            }
        }
    }

    enum class GameApiStatus { LOADING, ERROR, DONE }

}