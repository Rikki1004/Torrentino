package com.rikkimikki.torrentino.presentation.ui.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.fragment.app.Fragment
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
        viewModel = ViewModelProvider(this)[ControllerViewModel::class.java]
        initObservers()
        initListeners()

    }

    private fun initListeners() {
        with(binding){
            buttonControllerBack.setOnClickListener{ viewModel.sendControls("prevFile") }
            buttonControllerNext.setOnClickListener{ viewModel.sendControls("nextFile") }
            buttonControllerStop.setOnClickListener{ viewModel.sendControls("stop") }
            buttonControllerPlay.setOnClickListener{ viewModel.sendControls("pause") }
            buttonControllerMute.setOnClickListener{ viewModel.sendControls("setMute") }
            seekBarControllerTime.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                    viewModel.sendControls("setPosition", (i / 100.0).toString())
                }
                override fun onStartTrackingTouch(seekBar: SeekBar) {}
                override fun onStopTrackingTouch(seekBar: SeekBar) {}
            })
            seekBarControllerVolume.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                    viewModel.sendControls("setVolume", i.toString())
                }
                override fun onStartTrackingTouch(seekBar: SeekBar) {}
                override fun onStopTrackingTouch(seekBar: SeekBar) {}
            })
        }

    }

    private fun initObservers(){
        viewModel.volume.observe(viewLifecycleOwner){
            binding.seekBarControllerVolume.progress = it
        }
        viewModel.position.observe(viewLifecycleOwner){
            binding.textViewControllerMainTitle.text = it.title ?: ""
            binding.textViewControllerEndTime.text = it.duration
            binding.textViewControllerStartTime.text = it.time
            binding.seekBarControllerTime.progress = (it.position.toFloat()*100).toInt()
            if (it.status == STATUS_PLAY)
                binding.buttonControllerStop.setImageResource(android.R.drawable.ic_media_pause)
            else
                binding.buttonControllerStop.setImageResource(android.R.drawable.ic_media_pause)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object{
        const val STATUS_PLAY = "State.Playing"
    }
}