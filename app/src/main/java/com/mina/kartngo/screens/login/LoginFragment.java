package com.mina.kartngo.screens.login;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavOptions;
import androidx.navigation.fragment.NavHostFragment;

import com.mina.kartngo.R;
import com.mina.kartngo.data.remote.ApiClient;
import com.mina.kartngo.data.remote.auth.AuthRepository;
import com.mina.kartngo.data.util.Result;
import com.mina.kartngo.screens.utils.ViewModelFactory;

public class LoginFragment extends Fragment {

    private AuthViewModel viewModel;

    private EditText etUsername, etPassword;
    private CardView btnLogin;

    private static final String HARDCODED_STORE_ID = "2c346a67-943e-4b77-bc4e-a29fab885ef5";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etUsername = view.findViewById(R.id.etUsername);
        etPassword = view.findViewById(R.id.etPassword);
        btnLogin = view.findViewById(R.id.btnLogin);

        setupViewModel();

        btnLogin.setOnClickListener(v -> {
            String username = etUsername.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "من فضلك أدخل اسم المستخدم وكلمة المرور", Toast.LENGTH_SHORT).show();
                return;
            }

            viewModel.login(username, password).observe(getViewLifecycleOwner(), result -> {
                if (result instanceof Result.Success) {
                    Toast.makeText(requireContext(), "تم تسجيل الدخول بنجاح", Toast.LENGTH_SHORT).show();

                    // Call assignStoreAndBringProperties
                    viewModel.assignStore(HARDCODED_STORE_ID)
                            .observe(getViewLifecycleOwner(), assignResult -> {
                                if (assignResult instanceof Result.Success) {
                                    Toast.makeText(requireContext(), "Assigned to store", Toast.LENGTH_SHORT).show();

                                    NavOptions navOptions = new NavOptions.Builder()
                                            .setPopUpTo(R.id.loginFragment, true) // Clear back stack
                                            .build();

                                    NavHostFragment.findNavController(this)
                                            .navigate(R.id.action_loginFragment_to_productsFragment, null, navOptions);
                                } else if (assignResult instanceof Result.Error) {
                                    Toast.makeText(requireContext(),  ((Result.Error<?>) assignResult).getMessage(), Toast.LENGTH_SHORT).show();
                                    Log.d("API_LOG",((Result.Error<?>) assignResult).getMessage());
                                }
                            });

                } else if (result instanceof Result.Error) {
                    Toast.makeText(requireContext(), ((Result.Error<?>) result).getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    private void setupViewModel() {
        AuthRepository repository = new AuthRepository(ApiClient.getAuthApi(requireContext()), requireContext());
        ViewModelFactory factory = new ViewModelFactory(repository);
        viewModel = new ViewModelProvider(this, factory).get(AuthViewModel.class);
    }
}
