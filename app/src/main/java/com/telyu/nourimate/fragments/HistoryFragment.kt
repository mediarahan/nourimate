package com.telyu.nourimate.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.telyu.nourimate.adapter.history.HistoryItemAdapter
import com.telyu.nourimate.data.local.models.History
import com.telyu.nourimate.data.local.models.NestedHistory
import com.telyu.nourimate.databinding.FragmentHistoryBinding

class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var mList: MutableList<History>
    private lateinit var adapter: HistoryItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.historyrecyclerview.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
        }

        binding.backButton.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        mList = mutableListOf()

        val nestedList1 = listOf(
            NestedHistory("200", "10g", "5g", "30g", "60kg", "62kg"),
            NestedHistory("150", "8g", "4g", "25g", "60kg", "61kg")
        )

        val nestedList2 = listOf(
            NestedHistory("100", "5g", "2g", "15g", "60kg", "60.5kg"),
            NestedHistory("200", "10g", "5g", "30g", "60kg", "61kg")
        )

        val nestedList3 = listOf(
            NestedHistory("300", "15g", "7g", "40g", "60kg", "62kg"),
            NestedHistory("250", "12g", "6g", "35g", "60kg", "61.5kg")
        )

        mList.add(History("Weight Loss Program", "2023-01-01", "2023-03-01", nestedList1))
        mList.add(History("Muscle Gain Program", "2023-02-01", "2023-04-01", nestedList2))
        mList.add(History("Maintenance Program", "2023-03-01", "2023-05-01", nestedList3))

        adapter = HistoryItemAdapter(mList)
        binding.historyrecyclerview.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
