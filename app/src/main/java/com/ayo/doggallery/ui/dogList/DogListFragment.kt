package com.ayo.doggallery.ui.dogList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.ayo.doggallery.databinding.FragmentDogListBinding
import com.ayo.doggallery.di.ViewModelFactory
import com.ayo.doggallery.ui.extensions.showToast
import com.ayo.doggallery.ui.listeners.SearchQueryListener
import com.ayo.doggallery.ui.viewmodel.MainViewModel
import com.ayo.doggallery.utils.Resource
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class DogListFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val dogListAdapter: DogListAdapter by lazy {
        DogListAdapter(dogListener)
    }

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }

    private lateinit var binding: FragmentDogListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDogListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpListView()
        setUpListeners()
        observeData()
    }

    private fun observeData() {

        viewModel.dogsLiveData.observe(viewLifecycleOwner, Observer { resource ->
            when (resource) {
                is Resource.Success -> {
                    dogListAdapter.update(resource.data)
                    isLoading(false)
                }
                is Resource.Loading -> isLoading(resource.loading)
                is Resource.Failure -> {
                    isLoading(false)
                    showToast(resource.msg)
                }
            }

        })
    }


    private val dogListener = object : DogListAdapter.Listener {
        override fun onClick(position: Int, sharedElementView: ImageView) {
            val dog = dogListAdapter.getItem(position)
            val directions = DogListFragmentDirections.actionDogListFragmentToDogDetailFragment(dog)
            findNavController().navigate(directions)
        }
    }

    private fun setUpListeners() {
        binding.searchBox.addTextChangedListener(searchQueryListener)
        binding.searchButton.setOnClickListener {
            val queryText = binding.searchBox.text.toString()
            searchQueryListener.onSearchButtonClicked(queryText)
        }
    }

    private fun isLoading(isLoading: Boolean) {
        binding.loading.visibility =
            if (isLoading) View.VISIBLE else View.INVISIBLE
    }

    private val searchQueryListener =
        SearchQueryListener(this.lifecycle,
            { queryText ->
                queryText.let {
                    if (it.isNotBlank()) viewModel.searchDogs(it)
                }
            },
            { viewModel.getDogsList() })


    private fun setUpListView() {
        binding.dogList.apply {
            adapter = dogListAdapter
            layoutManager = GridLayoutManager(requireContext(), 3)
        }
    }
}
