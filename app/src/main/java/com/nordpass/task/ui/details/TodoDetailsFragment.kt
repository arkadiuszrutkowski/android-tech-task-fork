package com.nordpass.task.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.nordpass.task.R
import com.nordpass.task.databinding.FragmentDetailsBinding
import com.nordpass.task.ui.base.BaseFragment
import com.nordpass.tt.usecase.common.Time
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodoDetailsFragment : BaseFragment(R.layout.fragment_details) {
    private val viewModel: TodoDetailsViewModel by viewModels()
    private val args: TodoDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            viewModel.init(args.todo)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        FragmentDetailsBinding.bind(view).viewModel = viewModel
    }
}