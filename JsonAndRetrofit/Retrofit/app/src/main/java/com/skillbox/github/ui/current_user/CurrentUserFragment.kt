package com.skillbox.github.ui.current_user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.skillbox.github.R
import com.skillbox.github.databinding.CurrentUserFragmentBinding
import com.skillbox.github.ui.current_user.adapter.UserAdapter
import com.skillbox.github.utils.autoCleared
import jp.wasabeef.recyclerview.animators.ScaleInAnimator

class CurrentUserFragment : Fragment(R.layout.current_user_fragment) {

    private var userAdapter: UserAdapter by autoCleared()
    private lateinit var binding: CurrentUserFragmentBinding
    private val userInformationViewModel: UserInformationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CurrentUserFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
        userInformation()
    }

    private fun init() {
        userAdapter = UserAdapter()
        binding.userList.apply {
            adapter = userAdapter
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
        userInformationViewModel.userList.observe(viewLifecycleOwner) { userAdapter.items = it }
    }

}