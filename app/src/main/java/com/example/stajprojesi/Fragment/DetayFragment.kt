package com.example.stajprojesi.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.navOptions
import com.bumptech.glide.Glide
import com.example.stajprojesi.R
import com.example.stajprojesi.databinding.FragmentDetayBinding
import java.text.SimpleDateFormat
import java.util.Locale

class DetayFragment : Fragment()
{
    private val args: DetayFragmentArgs by navArgs()
    private var _binding: FragmentDetayBinding? = null
    private val binding get() = _binding!!

    private val genreMap = mapOf(
        28 to "Aksiyon",
        12 to "Macera",
        16 to "Animasyon",
        35 to "Komedi",
        80 to "Suç",
        99 to "Belgesel",
        18 to "Drama",
        10751 to "Aile",
        14 to "Fantastik",
        36 to "Tarih",
        27 to "Korku",
        10402 to "Müzik",
        9648 to "Gizem",
        10749 to "Romantik",
        878 to "Bilim Kurgu",
        10770 to "TV Filmi",
        53 to "Gerilim",
        10752 to "Savaş",
        37 to "Western"
    )

    override fun onCreateView
    (
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View
    {
        _binding = FragmentDetayBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        val film = args.film
        binding.DetayFilmAdiTextView.text = film.adi
        binding.DetayFilmOzetiTextView.text = film.ozet

        val filmTuru = film.tur.mapNotNull {

        genreMap[it] }.joinToString(", ")
        binding.DetayFilmTuruTextView.text =   (filmTuru.takeIf { it.isNotEmpty() } ?: getString(R.string.unknown))
        binding.DetayFilmSuresiTextView.text =  (film.sure?.let { "$it dk" } ?: getString(R.string.unknown))

        val releaseDate = try
        {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val outputFormat = SimpleDateFormat("dd MMMM yyyy", Locale("tr"))
            film.cikisTarihi.let { date ->
                inputFormat.parse(date)?.let { outputFormat.format(it) } ?: film.cikisTarihi
            }
        }
        catch (e: Exception)
        {
            film.cikisTarihi
        }

        binding.DetayFilmCikisTarihiTextView.text =(releaseDate.takeIf { it.isNotEmpty() } ?: getString(
            R.string.unknown
        ))

        film.gorsel?.let { path ->

            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500$path")
                .into(binding.DetayFilmGorseliImageView)
        }

        binding.DetayAnaSayfayaDonButton.setOnClickListener {

            findNavController().navigate(R.id.action_detayFragment_to_anaSayfaFragment) ;

        }

        val geriDon = object : OnBackPressedCallback(true)
        {
            override fun handleOnBackPressed()
            {
                findNavController().navigate(
                    R.id.action_detayFragment_to_anaSayfaFragment,
                    null,
                    navOptions {
                        popUpTo(R.id.anaSayfaFragment) { inclusive = true }
                    }
                )
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, geriDon)
    }


    override fun onDestroyView()
    {
        super.onDestroyView()
        _binding = null
    }
}