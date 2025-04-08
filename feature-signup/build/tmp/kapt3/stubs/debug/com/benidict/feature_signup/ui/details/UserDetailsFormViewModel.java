package com.benidict.feature_signup.ui.details;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006\u00a8\u0006\t"}, d2 = {"Lcom/benidict/feature_signup/ui/details/UserDetailsFormViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "isFormFieldsValid", "", "email", "", "firstName", "lastName", "feature-signup_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class UserDetailsFormViewModel extends androidx.lifecycle.ViewModel {
    
    @javax.inject.Inject()
    public UserDetailsFormViewModel() {
        super();
    }
    
    public final boolean isFormFieldsValid(@org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    java.lang.String firstName, @org.jetbrains.annotations.NotNull()
    java.lang.String lastName) {
        return false;
    }
}