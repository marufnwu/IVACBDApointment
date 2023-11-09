package com.logicline.ivacbdapointment.models;

public abstract class ScreenState<T> {
    private final T data;
    private final String message;

    public ScreenState(T data, String message) {
        this.data = data;
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public static class Success<T> extends ScreenState<T> {
        public Success(T data, String message) {
            super(data, message);
        }
    }

    public static class Loading<T> extends ScreenState<T> {
        public Loading(T data) {
            super(data, null);
        }
    }

    public static class Error<T> extends ScreenState<T> {
        public Error(T data, String message) {
            super(data, message);
        }
    }


    /*public <T> void handleScreenState(ScreenState<T> screenState, LoadingDialog loadingDialog) {
        handleScreenState(screenState, loadingDialog, () -> {
            if (loadingDialog != null) {
                loadingDialog.show();
            }
        }, (String message) -> {
            if (loadingDialog != null) {
                loadingDialog.hide();
            }
            if (message != null) {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }
        }, (T data, String message) -> {
            if (loadingDialog != null) {
                loadingDialog.hide();
            }
            if (message != null) {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public <T> void handleScreenState(ScreenState<T> screenState, LoadingDialog loadingDialog, Runnable loadingAction, Consumer<String> errorAction, BiConsumer<T, String> successAction) {
        if (screenState instanceof ScreenState.Loading) {
            loadingAction.run();
        } else if (screenState instanceof ScreenState.Error) {
            errorAction.accept(((ScreenState.Error<T>) screenState).getMessage());
        } else if (screenState instanceof ScreenState.Success) {
            ScreenState.Success<T> successState = (ScreenState.Success<T>) screenState;
            successAction.accept(successState.getData(), successState.getMessage());
        }
    }*/
}
