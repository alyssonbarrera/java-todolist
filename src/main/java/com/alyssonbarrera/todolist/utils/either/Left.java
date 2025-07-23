package com.alyssonbarrera.todolist.utils.either;

import java.util.Objects;

public class Left<L, R> implements Either<L, R> {
    private final L value;

    public Left(L value) {
        this.value = value;
    }

    public boolean isLeft() {
        return true;
    }

    public boolean isRight() {
        return false;
    }

    public L getLeft() {
        return value;
    }

    public R getRight() {
        throw new IllegalStateException("Cannot get right value from Left");
    }

    @Override
    public String toString() {
        return "Left(" + value + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Left<?, ?> left = (Left<?, ?>) obj;
        return Objects.equals(value, left.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}