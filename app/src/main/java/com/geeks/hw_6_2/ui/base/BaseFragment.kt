package com.geeks.hw_6_2.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.viewbinding.ViewBinding
import com.geeks.hw_6_2.utils.Resource
import com.geeks.hw_6_2.utils.showToast

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<VB: ViewBinding>(
    private val inflate: Inflate<VB>
) : Fragment() {

    private var _binding: VB? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews(view)
        observeViewModel()
    }

    protected open fun setupViews(view: View) {
    }

    protected open fun observeViewModel() {
    }

    protected fun <T> LiveData<Resource<T>>.handleResource(
        isLoading: (Boolean) -> Unit,
        onSuccess: (T?) -> Unit
    ) {
        this.observe(viewLifecycleOwner) { resource ->
            isLoading.invoke(resource is Resource.Loading)
            when (resource) {
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                    onSuccess(resource.data)
                }
                is Resource.Error -> {
                    showToast(resource.message)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}