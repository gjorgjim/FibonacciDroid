# FibonacciDroid

## Architecture

The FibonacciDroid application uses the data/domain/presentation architecture.

### Data
The data packages contain data classes used for the business logic of the features in the app usually
connected with the responses from BE.

### Domain
The domain packages contain use cases that execute the business logic and provide results back
to the presentation layer so the UI is updated properly.

### Presentation
The presentation packages contain classes connected to the UI of the features(activity, fragments, view models).

I think this architecture alongside with Single Responsibility principle, provides very clean and
readable code where each class(use case, view model) executes one part of the logic and can be
easily tested in isolation.

This is a vertical slice architecture where the lower packages don't know about the packages above them.
For this to be true, the domain package doesn't have imports from the presentation package.
The data package doesn't have import from the domain package.

### Common

#### UI

In this app, which is a single activity app, the FibonacciActivity's UI is created with android views
and holds the fragment container.
The fragments' UI is created with Compose that is connected to the view states from the view models.
The view models only hold and update the state, without making any decisions on how to update it.
They only know how to map the results from the use cases' they use.

#### Business logic

Most of the use cases return a partial view state connected to that view model, which only updates
part of the screen that's connected to the business logic of that use case and all of them are emiting
multiple flow events on invocation.

#### BE
This app is connected to Firebase for easy and fast implementation of authentication and database.
Authentication is only available with simple credential login(email, password).
The app is connected to a Firebase real-time database for writing/reading data about the user and
the previous actions that he/she took in the app(history of previous Fibonacci sequences).