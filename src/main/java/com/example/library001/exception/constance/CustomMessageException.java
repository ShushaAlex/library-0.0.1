package com.example.library001.exception.constance;

public enum CustomMessageException {
    NOT_BORROWED("This book is not borrowed"),
    NOT_AVAILABLE("This book is not available");

    private final String message;

    CustomMessageException(String message) {
        this.message = message;
    }

    public String getCustomMessage() {
        return message;
    }
}
