package com.vn.quochuyapplication.ui.export

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.vn.quochuyapplication.databinding.FragmentExportBinding

class ExportFragment : Fragment() {

    private lateinit var exportViewModel: ExportViewModel
    private var _binding: FragmentExportBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        exportViewModel =
            ViewModelProvider(this).get(ExportViewModel::class.java)

        _binding = FragmentExportBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}