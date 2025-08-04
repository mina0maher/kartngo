package com.mina.kartngo.screens.utils;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.lang.reflect.InvocationTargetException;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final Object[] dependencies;

    public ViewModelFactory(Object... dependencies) {
        this.dependencies = dependencies;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        try {
            // Attempt to find a constructor that matches the provided dependencies
            for (java.lang.reflect.Constructor<?> constructor : modelClass.getConstructors()) {
                if (constructor.getParameterTypes().length == dependencies.length) {
                    boolean allDependenciesMatch = true;
                    for (int i = 0; i < dependencies.length; i++) {
                        if (!constructor.getParameterTypes()[i].isInstance(dependencies[i])) {
                            allDependenciesMatch = false;
                            break;
                        }
                    }
                    if (allDependenciesMatch) {
                        return (T) constructor.newInstance(dependencies);
                    }
                }
            }
            // If no matching constructor is found, try the default constructor
            return modelClass.newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Error creating ViewModel: " + modelClass.getName(), e);
        }
    }
}