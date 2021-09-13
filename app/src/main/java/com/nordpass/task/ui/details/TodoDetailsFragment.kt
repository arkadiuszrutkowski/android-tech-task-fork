package com.nordpass.task.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.nordpass.task.R
import com.nordpass.task.databinding.FragmentDetailsBinding
import com.nordpass.task.ui.base.BaseFragment
import com.nordpass.task.ui.base.ext.handleNavigationResult
import com.nordpass.task.ui.details.TodoDetailsViewModel.*
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
        val binding = FragmentDetailsBinding.bind(view)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        handleNavigationResult(R.id.todoDetailsFragment, viewModel::init)

        viewModel.eventStream.observe(viewLifecycleOwner) { event ->
            when (event) {
                is Event.GoToUpdateTodoDetails -> {
                    findNavController().navigate(
                        TodoDetailsFragmentDirections.actionUpdateTodo(
                            event.todo
                        )
                    )
                }
            }
        }
    }
}