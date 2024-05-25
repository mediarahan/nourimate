package com.telyu.nourimate.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.telyu.nourimate.adapter.history.HistoryItemAdapter
import com.telyu.nourimate.adapter.recipe.RecipeAdapter
import com.telyu.nourimate.data.local.models.History
import com.telyu.nourimate.databinding.FragmentHistoryBinding
import com.telyu.nourimate.databinding.FragmentRecipeBinding
import com.telyu.nourimate.viewmodels.HistoryViewModel
import com.telyu.nourimate.viewmodels.ProgramViewModel
import com.telyu.nourimate.viewmodels.ViewModelFactory

class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding
    private val adapter = HistoryItemAdapter()

    private val viewModel by viewModels<HistoryViewModel> {
        ViewModelFactory.getInstance(
            requireContext().applicationContext
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //setup back button
        binding.backButton.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        setupRecyclerView()

        viewModel.userHistories.observe(viewLifecycleOwner) {histories ->
            adapter.submitList(histories)
        }

    }

    private fun setupRecyclerView() {
        binding.historyrecyclerview.adapter = adapter
        binding.historyrecyclerview.layoutManager = LinearLayoutManager(requireContext())
        binding.historyrecyclerview.setHasFixedSize(true)
    }
}
