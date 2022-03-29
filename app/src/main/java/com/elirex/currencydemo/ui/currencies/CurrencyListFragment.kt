package com.elirex.currencydemo.ui.currencies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.elirex.currencydemo.R
import com.elirex.currencydemo.ui.DemoViewModel
import com.elirex.currencydemo.databinding.FragmentCurrencyListBinding
import com.elirex.currencydemo.model.CurrencyInfo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrencyListFragment : Fragment() {

    private val viewModel: DemoViewModel by activityViewModels()
    private val currencyInfoListAdapter: CurrencyInfoListAdapter by lazy {
        CurrencyInfoListAdapter(object : CurrencyInfoListAdapter.ActionListener {
            override fun onItemClicked(currencyInfo: CurrencyInfo) {
                Toast.makeText(requireContext(), getString(R.string.clicked_currency_info, currencyInfo.id), Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentCurrencyListBinding.inflate(layoutInflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@CurrencyListFragment.viewModel
            currencyRecyclerView.apply {
                adapter = currencyInfoListAdapter
            }
        }.root
    }
}