package com.example.coroutines.ui.repository_list.detail_information

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.coroutines.R
import com.example.coroutines.databinding.DetailFragmentBinding
import com.example.coroutines.ui.repository_list.detail_information.DetailViewModel

class DetailFragment : Fragment(R.layout.detail_fragment) {

    private lateinit var binding: DetailFragmentBinding
    private val detailFragmentArgs: DetailFragmentArgs by navArgs()
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getDetailRepo()
        checkStar()
        binding.star.setOnClickListener {
            viewModel.starred(detailFragmentArgs.owner, detailFragmentArgs.repo)
            viewModel.showErrorDialog.observe(viewLifecycleOwner) {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getDetailRepo() {
        viewModel.getDetailRepo(detailFragmentArgs.owner, detailFragmentArgs.repo)
        viewModel.detailRepo.observe(viewLifecycleOwner) { setValue(it) }
        viewModel.showErrorDialog.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
        viewModel.isLoading.observe(viewLifecycleOwner, ::updateLoadingState)
    }

    private fun checkStar() {
        viewModel.checkStar(
            detailFragmentArgs.owner,
            detailFragmentArgs.repo
        )
        viewModel.showErrorDialog.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setValue(repo: DetailRepo) {
        Glide.with(this).load(repo.owner.avatar).diskCacheStrategy(
            DiskCacheStrategy.ALL
        ).placeholder(R.drawable.empty_avatar).into(binding.avatar)

        binding.name.text = "name ${repo.name}"
        binding.owner.text = "owner ${repo.owner.login}"
        binding.type.text = "type ${repo.owner.type}"

        viewModel.isStarred.observe(viewLifecycleOwner) {
            when (it) {
                true -> binding.star.setImageResource(android.R.drawable.star_big_on)
                false -> binding.star.setImageResource(android.R.drawable.star_big_off)
            }
        }
    }

    private fun updateLoadingState(isLoading: Boolean) {
        binding.progressBar.isVisible = isLoading
    }

}