package com.vaslufi.castles.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.vaslufi.castles.databinding.FragmentCastleListBinding
import com.vaslufi.castles.extension.exhaustive
import com.vaslufi.castles.model.CastleListItemViewModel
import com.vaslufi.castles.navigator.AppNavigator
import com.vaslufi.castles.ui.list.impl.CastleListViewModelImpl
import com.vaslufi.castles.util.extension.collectIn
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CastleListFragment : Fragment() {

    private object Flipper {
        const val LOADING = 0
        const val CONTENT = 1
        const val ERROR = 2
    }

    private var _binding: FragmentCastleListBinding? = null
    private val binding get() = _binding!!

    private lateinit var listAdapter: CastleListAdapter

    @Inject
    lateinit var navigator: AppNavigator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = FragmentCastleListBinding.inflate(inflater, container, false).also { _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listAdapter = CastleListAdapter(requireActivity()).apply {
            onItemClickedListener = object : CastleListAdapter.OnItemClickedListener {
                override fun onItemClicked(model: CastleListItemViewModel) {
                    // TODO move to the view model
                    navigator.navigateToCastleDetails(model.id)
                }
            }
        }

        with(binding.castleListRecyclerView) {
            layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
            adapter = listAdapter
        }

        val viewModel = ViewModelProvider(this).get(CastleListViewModelImpl::class.java)

        viewModel.viewState.collectIn(lifecycleScope, ::render)
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
