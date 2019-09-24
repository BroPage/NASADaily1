package com.pagetyler.nasadaillypict.detail


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import com.pagetyler.nasadaillypict.R
import com.pagetyler.nasadaillypict.databinding.FragmentDetailBinding

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        @Suppress("UNUSED_VARIABLE")
        val application = requireNotNull(activity).application
        val binding = FragmentDetailBinding.inflate(inflater)
        binding.setLifecycleOwner(this)
        val NasaPicture = DetailFragmentArgs.fromBundle(arguments!!).selectedPicture

         val viewModelFactory = DetailViewModelFactory(NasaPicture, application)
         binding.viewModel = ViewModelProviders.of(
                 this, viewModelFactory).get(DetailViewModel::class.java)
        return binding.root
    }
}