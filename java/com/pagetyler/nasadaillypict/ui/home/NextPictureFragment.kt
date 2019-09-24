package com.pagetyler.nasadaillypict.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.pagetyler.nasadaillypict.databinding.FragmentHomeBinding
import com.pagetyler.nasadaillypict.databinding.FragmentNextpictureBinding
import java.util.zip.Inflater

class NextPictureFragment : Fragment(){
    private lateinit var NextPictureViewModel: NextPictureViewModel

    private val viewModel: NextPictureViewModel by lazy {
        ViewModelProviders.of(this).get(com.pagetyler.nasadaillypict.ui.home.NextPictureViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        val binding = FragmentNextpictureBinding.inflate(inflater)

        binding.setLifecycleOwner (this)

        binding.viewModel2= viewModel

        binding.nextphotosGrid.adapter  = PhotoGridAdapter(PhotoGridAdapter.OnClickListener {
            viewModel.displayNextPictureDetails(it)
        })

        viewModel.navigateToNextPicture.observe(this, Observer {
            if ( null != it ) {
                this.findNavController().navigate(
                    NextPictureFragmentDirections.actionShowNextDetail(it))
                viewModel.displayNextPictureDetailsComplete()
            }
        })

        setHasOptionsMenu(true)
        return binding.root
    }
}