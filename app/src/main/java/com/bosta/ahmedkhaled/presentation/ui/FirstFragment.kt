package com.bosta.ahmedkhaled.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.bosta.ahmedkhaled.data.model.response.GetUsersResponse
import com.bosta.ahmedkhaled.databinding.FragmentFirstBinding
import com.bosta.ahmedkhaled.presentation.viewmodels.AlbumsViewModel
import com.bosta.ahmedkhaled.presentation.viewmodels.UsersViewModel
import com.bosta.ahmedkhaled.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "TagFirstFragment"

@AndroidEntryPoint
class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding
    private val viewModelUsers: UsersViewModel by viewModels()
    private val viewModelAlbums: AlbumsViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFirstBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initResponseApiForUsers()
        initResponseApiForAlbums()

    }


    private fun initResponseApiForUsers() {
        viewModelUsers.usersData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {}

                is Resource.Success -> {
                    setRandomUser(it.data)
                }

                is Resource.Error -> {
                    Log.d(TAG, "initResponseApiForUsers Error: ${it.message!!}")
                    showToast(it.message)
                }
            }
        }
    }

    private fun setRandomUser(data: MutableList<GetUsersResponse>?) {
        data!!.shuffle()
        val randomUser = data.random()

        viewModelAlbums.albumsData(randomUser.id)

        Log.d(TAG, "initResponseApiForUsers: ${randomUser.name}")
        showToast(randomUser.id.toString())
    }


    private fun initResponseApiForAlbums() {
        viewModelAlbums.albumsData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {}

                is Resource.Success -> {
                    Log.d(TAG, "initResponseApiForAlbums: ${it.data!![0].title}")
                    showToast(it.data[0].title)
                }

                is Resource.Error -> {
                    Log.d(TAG, "initResponseApiForAlbums Error: ${it.message!!}")
                    showToast(it.message)
                }
            }
        }
    }



    private fun showToast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }
}