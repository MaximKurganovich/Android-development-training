package com.skillbox.github.ui.repository_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.skillbox.github.R
import com.skillbox.github.databinding.RepositoryListFragmentBinding
import com.skillbox.github.ui.repository_list.adapter.RepositoryAdapter
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
    ): View? {
        binding = RepositoryListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initialSettings()
        getRepository()
    }

    private fun initialSettings() {
        repositoryAdapter = RepositoryAdapter()
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

    private fun getRepository(sort: String = "created") {
        repositoryViewModel.getRepository(sort)
        repositoryViewModel.repositoryList.observe(viewLifecycleOwner) {
            repositoryAdapter.items = it
        }
    }

}