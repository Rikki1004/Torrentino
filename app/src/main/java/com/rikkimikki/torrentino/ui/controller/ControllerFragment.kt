package com.rikkimikki.torrentino.ui.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.rikkimikki.torrentino.databinding.FragmentControllerBinding

class ControllerFragment : Fragment() {
    private var _binding: FragmentControllerBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ControllerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentControllerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.twController.text = "controller: "+ (0..100).random()
        viewModel = ViewModelProvider(this)[ControllerViewModel::class.java]
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}