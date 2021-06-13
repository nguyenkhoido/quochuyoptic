package com.vn.quochuyapplication.ui.money

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.vn.quochuyapplication.databinding.FragmentMoneyBinding

class MoneyFragment : Fragment() {

    private lateinit var moneyViewModel: MoneyViewModel
    private var _binding: FragmentMoneyBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        moneyViewModel =
            ViewModelProvider(this).get(MoneyViewModel::class.java)

        _binding = FragmentMoneyBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}