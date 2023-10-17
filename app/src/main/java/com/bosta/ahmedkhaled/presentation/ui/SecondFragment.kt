package com.bosta.ahmedkhaled.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bosta.ahmedkhaled.R
import com.bosta.ahmedkhaled.databinding.FragmentFirstBinding
import com.bosta.ahmedkhaled.databinding.FragmentSecondBinding
import com.bosta.ahmedkhaled.presentation.viewmodels.PhotosViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding
    private val viewModelPhotos: PhotosViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSecondBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        viewModelPhotos.usersData()
    }

}