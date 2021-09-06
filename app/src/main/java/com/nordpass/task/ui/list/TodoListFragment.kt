package com.nordpass.task.ui.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.nordpass.task.R
import com.nordpass.task.ui.base.BaseFragment
import com.nordpass.tt.usecase.Todo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodoListFragment : BaseFragment(R.layout.fragment_list) {
    private val viewModel: TodoListViewModel by viewModels()
    private var adapter: TodoListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.showItem.observe(this, Observer(::showTodoDetails))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = TodoListAdapter(viewModel::onItemClicked)
        view.findViewById<RecyclerView>(R.id.todoRecycler)?.adapter = adapter

        viewModel.items.observe(viewLifecycleOwner, { adapter?.submitList(it) })
        viewModel.init()
    }

    private fun showTodoDetails(todo: Todo) {
        findNavController().navigate(TodoListFragmentDirections.actionTodoDetails(todo))
    }
}