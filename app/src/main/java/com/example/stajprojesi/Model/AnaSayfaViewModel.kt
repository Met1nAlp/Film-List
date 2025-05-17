package com.example.stajprojesi.Model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stajprojesi.RetrofitIstemci
import kotlinx.coroutines.launch

class AnaSayfaViewModel : ViewModel()
{
    private val _films = MutableLiveData<List<Film>>()

    val films: LiveData<List<Film>> get() = _films

    private val apiKey = "d26c67a973562d910f6640258b2247d4"

    fun fetchPopularMovies()
    {
        viewModelScope.launch {
            try
            {
                val sayfa1 = RetrofitIstemci.tmdbApi.getPopularMovies(apiKey, page = 1)
                val sayfa2 = RetrofitIstemci.tmdbApi.getPopularMovies(apiKey, page = 2)
                val sayfa3 = RetrofitIstemci.tmdbApi.getPopularMovies(apiKey, page = 3)


                val Filmler = sayfa1.results + sayfa2.results + sayfa3.results
                _films.postValue(Filmler)
            }
            catch (e: Exception)
            {
                e.printStackTrace()
            }
        }
    }
}