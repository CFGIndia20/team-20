package com.example.umeedcfg.ui.login;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.example.umeedcfg.R;

public class LoginFragmentDirections {
  private LoginFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionLoginFragmentToRegisterFragment() {
    return new ActionOnlyNavDirections(R.id.action_loginFragment_to_registerFragment);
  }
}
