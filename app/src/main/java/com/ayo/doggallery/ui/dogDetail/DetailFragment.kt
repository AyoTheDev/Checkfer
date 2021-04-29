package com.ayo.doggallery.ui.dogDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.ayo.doggallery.R
import com.ayo.doggallery.databinding.FragmentDogDetailsBinding
import com.ayo.doggallery.utils.ImageLoaderUtils
import com.ayo.domain.model.DogDomain

class DetailFragment : Fragment() {

    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dog: DogDomain = args.dog
        val binding = FragmentDogDetailsBinding.inflate(layoutInflater)
        binding.apply {
            ImageLoaderUtils.loadImage(requireContext(), dog.imageId, binding.dogImage)
            displayName.text = getString(R.string.name, dog.name)
            temperament.text = getString(R.string.dog_temperament, dog.temperament)
        }
        return binding.root
    }

}
