## Explanation

I used the Arrow library for error/success response handling. Because of this, I found it unnecessary to use the `ApiResponse` class. I hope this does not break any rule or purpose of the challenge. I integrated it hoping I could extend error handling of mocked network calls, but the simplicity of the challenge kept me from extending it. It can only be demonstrated when there is a `Response` object where you could look at response codes and errorBody messages, so I stuck to a simple use of the library.

## Improvements that could be added (still sticking to the simplicity of the challenge)

1. **Use Hilt for Dependency Injection**:
   Instead of manually creating objects, Hilt can be used for dependency injection.

2. **Introduce Coroutines**:
   Inside of use case implementations, coroutines can be introduced to allow background executions of network requests.

3. **Modularize the Project**:
   As the project gets bigger, packages can be divided into layers as Android modules following clean architecture recommendations:
   - **Domain Module**: Contains use cases and global models to include in UI states.
   - **Repository Module**: Includes network-related components.
   - **App Module**: Includes both presentation and UI.

The final structure would be:
- `app` (UI and presentation)
- `repository`
- `domain`

Although this adds some complexity, it is really handy when doing migrations or implementing complete UI redesigns.
