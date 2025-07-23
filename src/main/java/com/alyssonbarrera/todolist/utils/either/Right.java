package com.alyssonbarrera.todolist.utils.either;

import java.util.Objects;

public class Right<L, R> implements Either<L, R> {
    private final R value;

    public Right(R value) {
        this.value = value;
    }

    public boolean isLeft() {
        return false;
    }

    public boolean isRight() {
        return true;
    }

    public L getLeft() {
        throw new IllegalStateException("Cannot get left value from Right");
    }

    public R getRight() {
        return value;
    }

    @Override
    public String toString() {
        return "Right(" + value + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Right<?, ?> right = (Right<?, ?>) obj;
        return Objects.equals(value, right.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}