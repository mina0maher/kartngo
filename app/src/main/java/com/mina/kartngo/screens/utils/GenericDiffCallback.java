package com.mina.kartngo.screens.utils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import java.util.function.BiPredicate;

public class GenericDiffCallback<T> extends DiffUtil.ItemCallback<T> {

    private final BiPredicate<T, T> areItemsSamePredicate;
    private final BiPredicate<T, T> areContentsSamePredicate;

    public GenericDiffCallback(BiPredicate<T, T> areItemsSamePredicate,
                               BiPredicate<T, T> areContentsSamePredicate) {
        this.areItemsSamePredicate = areItemsSamePredicate;
        this.areContentsSamePredicate = areContentsSamePredicate;
    }

    @Override
    public boolean areItemsTheSame(@NonNull T oldItem, @NonNull T newItem) {
        return areItemsSamePredicate.test(oldItem, newItem);
    }

    @Override
    public boolean areContentsTheSame(@NonNull T oldItem, @NonNull T newItem) {
        return areContentsSamePredicate.test(oldItem, newItem);
    }
}

