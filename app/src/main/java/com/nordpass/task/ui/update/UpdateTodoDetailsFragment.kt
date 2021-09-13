package com.nordpass.task.ui.update

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.nordpass.task.R
import com.nordpass.task.databinding.FragmentUpdateBinding
import com.nordpass.task.ui.base.BaseFragment
import com.nordpass.task.ui.base.ext.setNavigationResult
import com.nordpass.task.ui.update.UpdateTodoDetailsViewModel.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateTodoDetailsFragment : BaseFragment(R.layout.fragment_update) {

    private val viewModel: UpdateTodoDetailsViewModel by viewModels()
    private val args: UpdateTodoDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            viewModel.init(args.todo)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentUpdateBinding.bind(view)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        viewModel.navigationEvents.observe(viewLifecycleOwner) { event ->
            when (event) {
                is NavigationEvent.TodoUpdateFinishedEvent -> {
                    setNavigationResult(event.updatedTodo)
                    findNavController().popBackStack()
                }
            }
        }
    }
}