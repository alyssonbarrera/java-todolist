package com.alyssonbarrera.todolist.utils.either;

public interface Either<L, R> {
    boolean isLeft();

    boolean isRight();

    L getLeft();
    R getRight();

    static <L, R> Either<L, R> left(L value) {
        return new Left<>(value);
    }

    static <L, R> Either<L, R> right(R value) {
        return new Right<>(value);
    }
}