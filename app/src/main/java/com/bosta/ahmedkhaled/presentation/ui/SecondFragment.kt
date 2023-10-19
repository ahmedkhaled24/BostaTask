package com.bosta.ahmedkhaled.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bosta.ahmedkhaled.R
import com.bosta.ahmedkhaled.data.model.response.GetPhotosResponse
import com.bosta.ahmedkhaled.databinding.FragmentSecondBinding
import com.bosta.ahmedkhaled.presentation.adapters.PhotosAdapter
import com.bosta.ahmedkhaled.presentation.navigators.PhotosNavigator
import com.bosta.ahmedkhaled.presentation.viewmodels.PhotosViewModel
import com.bosta.ahmedkhaled.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SecondFragment : Fragment(), PhotosNavigator {

    private lateinit var binding: FragmentSecondBinding
    private val viewModelPhotos: PhotosViewModel by viewModels()
    private val args: SecondFragmentArgs by navArgs()
    private lateinit var photosAdapter: PhotosAdapter
    private var originalData: MutableList<GetPhotosResponse> = ArrayList()
    private var filteredData: MutableList<GetPhotosResponse> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSecondBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTitleAlbum()
        viewModelPhotos.usersData(args.album.id)
        initResponseApiForPhotos()
        setUpRecyclerPhotosData()
        setUpSearchViewForPhotos()
    }

    private fun setUpSearchViewForPhotos() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterData(newText ?: "")
                return true
            }
        })

    }

    private fun filterData(query: String) {
        filteredData = originalData
        filteredData = originalData.filter { item -> item.title.contains(query, ignoreCase = true) }
                as MutableList<GetPhotosResponse>
        photosAdapter.differ.submitList(filteredData)
    }

    private fun setTitleAlbum() {
        binding.titleAlbumTv.text = getString(R.string.album_name, args.album.title)
    }

    private fun initResponseApiForPhotos() {
        viewModelPhotos.photoData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    showProgress()
                }

                is Resource.Success -> {
                    originalData = it.data!!
                    photosAdapter.differ.submitList(originalData)
                    binding.searchView.queryHint = it.data[0].title
                    hideProgress()
                }

                is Resource.Error -> {
                    Toast.makeText(requireContext(), it.message!!, Toast.LENGTH_SHORT).show()
                    hideProgress()
                }
            }
        }
    }

    private fun setUpRecyclerPhotosData() {
        photosAdapter = PhotosAdapter(this)
        binding.photosRecycler.adapter = photosAdapter
    }


    private fun showProgress(){
        binding.progress.root.visibility = View.VISIBLE
    }


    private fun hideProgress(){
        binding.progress.root.visibility = View.GONE
    }


    override fun clickOnPhoto(url: String) {
        findNavController().navigate(SecondFragmentDirections.actionSecondFragmentToDisplayImageFragment(url))
    }

}