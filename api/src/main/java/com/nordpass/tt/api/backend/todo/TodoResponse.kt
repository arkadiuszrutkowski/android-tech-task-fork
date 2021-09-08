package com.nordpass.tt.api.backend.todo

internal class TodoResponse {
    var id: Int? = null
    var userId: Int? = null
    var title: String? = null
    var status: String? = null //One of completed or pending
//    var dueOn: String? = null //"2021-09-30T00:00:00.000+05:30"
}