package com.mina.kartngo.screens.login;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.mina.kartngo.data.remote.auth.AuthRepository;
import com.mina.kartngo.data.remote.entites.auth.AssignStoreAndBringPropertiesResponse;
import com.mina.kartngo.data.remote.entites.auth.LoginResponse;
import com.mina.kartngo.data.util.Result;

public class AuthViewModel extends ViewModel {

    private final AuthRepository authRepository;

    public AuthViewModel(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public LiveData<Result<LoginResponse>> login(String username, String password) {
        return authRepository.login(username, password);
    }

    public LiveData<Result<AssignStoreAndBringPropertiesResponse>> assignStore(String storeId) {
        return authRepository.assignStoreAndBringProperties(storeId);
    }
}
