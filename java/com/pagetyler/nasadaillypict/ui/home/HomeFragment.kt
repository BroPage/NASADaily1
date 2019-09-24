package com.pagetyler.nasadaillypict.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.pagetyler.nasadaillypict.R
import com.pagetyler.nasadaillypict.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.nav_header_main.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    private val viewModel: HomeViewModel by lazy {
        ViewModelProviders.of(this).get(HomeViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {

       // homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        val binding = FragmentHomeBinding.inflate(inflater)

        binding.setLifecycleOwner (this)

        binding.viewModel = viewModel

        binding.photosGrid.adapter = PhotoGridAdapter(PhotoGridAdapter.OnClickListener {
            viewModel.displayPictureDetails(it)
        })

        viewModel.navigateToSelectedPicture.observe(this, Observer {
            if ( null != it ) {
                this.findNavController().navigate(
                    HomeFragmentDirections.actionShowDetail(it))
                viewModel.displayPictureDetailsComplete()
            }
        })

        setHasOptionsMenu(true)
        return binding.root
    }
}