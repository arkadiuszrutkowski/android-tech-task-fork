# Android Tech Task

The app fetches todo list from backend api, stores it to local database and shows the list to the
user.

## Tech stack:

* Network - <a href="https://square.github.io/retrofit/">Retrofit</a>
* Database - <a href="https://developer.android.com/training/data-storage/room">Room</a>
* Concurrency - <a href="https://github.com/ReactiveX/RxJava">RxJava2</a>
* Dependency injection - <a href="https://developer.android.com/training/dependency-injection/hilt-android">Hilt</a>
* UI - <a href="https://developer.android.com/topic/libraries/architecture/viewmodel">MVVM</a>, <a href="https://developer.android.com/topic/libraries/data-binding">DataBinding</a>, <a href="https://developer.android.com/guide/navigation/navigation-getting-started">NavComponents</a>.
* Unit tests - JUnit4, <a href="https://site.mockito.org/">Mockito</a>

## Structure:
App is modularized. Modules used in the app:
* api - network requests
* app - app and ui
* storage - local database
* usecase - business logic

## Screens and whats going on:
1. Splash - todo items are synced
2. TodoList - todo list is shown to user. Completed todo items are shown like this: <strike>This todo is completed</strike>
3. TodoDetails - details of the todo item.

## Main tasks
1. Our backend api just changed. `dueOn` properties was added to `TodoResonse`. Your task is to
   update the response, `Todo` item and local database.
   (p.s. Don't forget to handle database migration).

2. Users would like to see pending items with least amount of time left to complete at the top 
   of the screen. Your task is to implement items sorting. 
   Items in the `TodoList` screen should be sorted by the`dueOn` and `isCompleted`.

3. In the TodoDetails screen, when `Finished` or `Todo` button is clicked, app should update
   item's `isCompleted` and `updateAt` values and update the database. Changes should be reflected
   in`TodoDetails` and `TodoList` screens. If needed, you can use Time.now() to get current datetime 
   as a String.

4. When user clicks on the `Edit` button in the `TodoDetails` screen, a new, `UpdateTodo` screen
   should be shown (design is
   in  <a href="https://github.com/NordPass/android-tech-task/tree/master/uiSamples/editScreen.jpg">
   uiSamples/editScreen</a>). Here, user should be able to edit `Todo` item's title and save it. 
   Your task is to create a new screen, called `UpdateTodo`. It should have`EditText` and a save 
   button. `EditText` should be prefilled with the title of `Todo` item. Once save is clicked item's
   `title` and `updatedAt` values should be updated and saved in the local database. 
   `Title can not be empty` error should be shown if title is empty when user clicks save button and
   hidden when text is entered to the input field. 
   See UI in<a href="https://github.com/NordPass/android-tech-task/tree/master/uiSamples/editScreenError.jpg">
   uiSamples/editScreenError</a>. Changes should be reflected in `TodoDetails` and `TodoList`
   screens.

## Bonus tasks
* There are some bugs in the app. If you notice some, please fix.
* App does not work properly on release version. Could you figure out what is wrong and maybe fix it?
* Write some tests