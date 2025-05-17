package com.example.stajprojesi.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stajprojesi.Adapter.AnaSayfaAdapter
import com.example.stajprojesi.Model.AnaSayfaViewModel
import com.example.stajprojesi.databinding.FragmentAnaSayfaBinding

class AnaSayfaFragment : Fragment()
{
    private val viewModel: AnaSayfaViewModel by viewModels()
    private var _binding: FragmentAnaSayfaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView
    (
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ):
            View
    {
        _binding = FragmentAnaSayfaBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.AnaSayfaRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        val adapter = AnaSayfaAdapter(emptyList())
        { film ->
            val action = AnaSayfaFragmentDirections.actionAnaSayfaFragmentToDetayFragment(film)
            findNavController().navigate(action)
        }

        binding.AnaSayfaRecyclerView.adapter = adapter

        viewModel.films.observe(viewLifecycleOwner)
        { films ->
            adapter.updateFilms(films)
        }

        viewModel.fetchPopularMovies()

        return view
    }

    override fun onDestroyView()
    {
        super.onDestroyView()
        _binding = null
    }
}