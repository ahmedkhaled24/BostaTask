package com.bosta.ahmedkhaled.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bosta.ahmedkhaled.R
import com.bosta.ahmedkhaled.data.model.response.GetAlbumsResponse
import com.bosta.ahmedkhaled.data.model.response.GetUsersResponse
import com.bosta.ahmedkhaled.databinding.FragmentFirstBinding
import com.bosta.ahmedkhaled.presentation.adapters.AlbumsAdapter
import com.bosta.ahmedkhaled.presentation.navigators.AlbumsNavigator
import com.bosta.ahmedkhaled.presentation.viewmodels.AlbumsViewModel
import com.bosta.ahmedkhaled.presentation.viewmodels.UsersViewModel
import com.bosta.ahmedkhaled.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

//private const val TAG = "TagFirstFragment"

@AndroidEntryPoint
class FirstFragment : Fragment(), AlbumsNavigator {

    private lateinit var binding: FragmentFirstBinding
    private val viewModelUsers: UsersViewModel by viewModels()
    private val viewModelAlbums: AlbumsViewModel by viewModels()
    private lateinit var albumsAdapter: AlbumsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
                is Resource.Loading -> {
                    showProgress()
                }

                is Resource.Success -> {
                    setRandomUser(it.data)
                }

                is Resource.Error -> {
                    Toast.makeText(requireContext(), it.message!!, Toast.LENGTH_SHORT).show()
                    hideProgress()
                }
            }
        }
    }

    private fun setRandomUser(data: MutableList<GetUsersResponse>?) {
        data!!.shuffle()
        val randomUser = data.random()
        binding.nameUserTv.text = getString(R.string.name, randomUser.name)
        binding.addressUserTv.text = getString(
            R.string.address,
            randomUser.address.street, randomUser.address.city
        )

        //init albums recycler view
        setUpRecyclerAlbumsData()

        //call albums regarding on user
        viewModelAlbums.albumsData(randomUser.id)
    }


    private fun initResponseApiForAlbums() {
        viewModelAlbums.albumsData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    showProgress()
                }

                is Resource.Success -> {
                    albumsAdapter.differ.submitList(it.data!!)
                    hideProgress()
                }

                is Resource.Error -> {
                    Toast.makeText(requireContext(), it.message!!, Toast.LENGTH_SHORT).show()
                    hideProgress()
                }
            }
        }
    }

    private fun setUpRecyclerAlbumsData() {
        albumsAdapter = AlbumsAdapter(this)
        binding.albumsRecycler.adapter = albumsAdapter
    }


    private fun showProgress(){
        binding.progress.root.visibility = View.VISIBLE
    }


    private fun hideProgress(){
        binding.progress.root.visibility = View.GONE
    }


    override fun clickOnAlbumItem(item: GetAlbumsResponse) {
        findNavController().navigate(FirstFragmentDirections.actionFirstFragmentToSecondFragment(item))
    }
}