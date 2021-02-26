package com.vaslufi.castles.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.vaslufi.castles.databinding.FragmentCastleListBinding
import com.vaslufi.castles.extension.exhaustive

class CastleListFragment : Fragment() {

    private object Flipper {
        const val LOADING = 0
        const val CONTENT = 1
        const val ERROR = 2
    }

    private var _binding: FragmentCastleListBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: CastleListViewModel
    private lateinit var listAdapter: CastleListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCastleListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO Use dependency injection
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(CastleListViewModel::class.java)

        // TODO Use dependency injection
        listAdapter = CastleListAdapter(requireActivity())

        with(binding.castleListRecyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
        }

        viewModel.viewState.observe(viewLifecycleOwner, ::render)

        viewModel.loadCastleList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun render(viewState: CastleListViewState) {
        when (viewState) {
            Loading ->
                binding.viewFlipper.displayedChild = Flipper.LOADING
            Error ->
                binding.viewFlipper.displayedChild = Flipper.ERROR
            is CastleListLoaded -> {
                binding.viewFlipper.displayedChild = Flipper.CONTENT
                listAdapter.items = viewState.castleList
            }
        }.exhaustive
    }
}
