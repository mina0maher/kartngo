package com.mina.kartngo.screens.utils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import java.util.function.BiPredicate;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import java.util.function.BiPredicate;

public class GenericDiffCallback<T> extends DiffUtil.ItemCallback<T> {

    private final BiPredicate<T, T> areItemsTheSamePredicate;
    private final BiPredicate<T, T> areContentsTheSamePredicate;

    public GenericDiffCallback(@NonNull BiPredicate<T, T> areItemsTheSamePredicate,
                               @NonNull BiPredicate<T, T> areContentsTheSamePredicate) {
        this.areItemsTheSamePredicate = areItemsTheSamePredicate;
        this.areContentsTheSamePredicate = areContentsTheSamePredicate;
    }

    @Override
    public boolean areItemsTheSame(@NonNull T oldItem, @NonNull T newItem) {
        return areItemsTheSamePredicate.test(oldItem, newItem);
    }

    @Override
    public boolean areContentsTheSame(@NonNull T oldItem, @NonNull T newItem) {
        return areContentsTheSamePredicate.test(oldItem, newItem);
    }
}

