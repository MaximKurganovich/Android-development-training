package com.example.coroutines.ui.current_user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.coroutines.R
import com.example.coroutines.databinding.CurrentUserFragmentBinding
import com.example.coroutines.ui.current_user.adapter.UserAdapter
import com.example.coroutines.ui.repository_list.adapter.RepositoryAdapter
import com.skillbox.github.utils.autoCleared
import jp.wasabeef.recyclerview.animators.ScaleInAnimator

class CurrentUserFragment : Fragment(R.layout.current_user_fragment) {

    private var followingAdapter: UserAdapter by autoCleared()
    private lateinit var binding: CurrentUserFragmentBinding
    private val userInformationViewModel: UserInformationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CurrentUserFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initialSettings()
        userInformation()
    }

    private fun initialSettings() {
        followingAdapter = UserAdapter()
        binding.followingListValue.apply {
            adapter = followingAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
            itemAnimator = ScaleInAnimator()
        }
    }

    private fun userInformation() {
        userInformationViewModel.userInformation()
        userInformationViewModel.showErrorDialog.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        }
        userInformationViewModel.userList.observe(viewLifecycleOwner) {
            Glide.with(this).load(it.avatar).transform(CircleCrop())
                .placeholder(R.drawable.empty_avatar)
                .into(binding.avatar)

            binding.name.text = it.name
            binding.type.text = it.type
            binding.followersValue.text = it.followers.toString()
            binding.followingValue.text = it.following.toString()
        }

        userInformationViewModel.followingList.observe(viewLifecycleOwner) {
            followingAdapter.items = it
        }
    }

}