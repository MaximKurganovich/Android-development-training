package com.example.coroutines.ui.repository_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coroutines.R
import com.example.coroutines.databinding.RepositoryListFragmentBinding
import com.example.coroutines.ui.repository_list.adapter.RepositoryAdapter
import com.skillbox.github.utils.autoCleared
import jp.wasabeef.recyclerview.animators.ScaleInAnimator

class RepositoryListFragment : Fragment(R.layout.repository_list_fragment) {

    private var repositoryAdapter: RepositoryAdapter by autoCleared()
    private lateinit var binding: RepositoryListFragmentBinding
    private val repositoryViewModel: RepositoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RepositoryListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initialSettings()
        getRepository()
    }

    private fun initialSettings() {
        repositoryAdapter = RepositoryAdapter { position -> openDetailFragment(position) }
        binding.repositoryList.apply {
            adapter = repositoryAdapter
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

    private fun getRepository() {
        repositoryViewModel.getRepository()
        repositoryViewModel.repositoryList.observe(viewLifecycleOwner) {
            repositoryAdapter.items = it
        }
    }

    private fun openDetailFragment(position: Int) {
        val element = repositoryViewModel.repositoryList.value?.get(position)
        val action =
            RepositoryListFragmentDirections.actionRepositoryListFragmentToDetailFragment(element!!.owner.login, element.name)
        findNavController().navigate(action)
    }

}