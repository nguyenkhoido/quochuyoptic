package com.vn.quochuyapplication.ui.import

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.vn.quochuyapplication.databinding.FragmentImportBinding

class ImportFragment : Fragment() {

    private lateinit var importViewModel: ImportViewModel
    private var _binding: FragmentImportBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        importViewModel =
            ViewModelProvider(this).get(ImportViewModel::class.java)
        _binding = FragmentImportBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}