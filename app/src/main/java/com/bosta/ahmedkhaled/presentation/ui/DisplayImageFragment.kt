package com.bosta.ahmedkhaled.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.bosta.ahmedkhaled.databinding.FragmentDisplayImageBinding
import com.squareup.picasso.Picasso

class DisplayImageFragment : Fragment() {

    private lateinit var binding: FragmentDisplayImageBinding
    private val args: DisplayImageFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDisplayImageBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Toast.makeText(
            requireContext(),
            "Zooming is available by swiping with two fingers",
            Toast.LENGTH_LONG
        ).show()

        Picasso.get().load(args.url).into(binding.imageView)
    }

}