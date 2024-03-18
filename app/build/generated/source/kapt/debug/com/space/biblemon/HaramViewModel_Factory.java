// Generated by Dagger (https://dagger.dev).
package com.space.biblemon;

import com.space.domain.usecase.auth.AuthStateUseCase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class HaramViewModel_Factory implements Factory<HaramViewModel> {
  private final Provider<AuthStateUseCase> authStateUseCaseProvider;

  public HaramViewModel_Factory(Provider<AuthStateUseCase> authStateUseCaseProvider) {
    this.authStateUseCaseProvider = authStateUseCaseProvider;
  }

  @Override
  public HaramViewModel get() {
    return newInstance(authStateUseCaseProvider.get());
  }

  public static HaramViewModel_Factory create(Provider<AuthStateUseCase> authStateUseCaseProvider) {
    return new HaramViewModel_Factory(authStateUseCaseProvider);
  }

  public static HaramViewModel newInstance(AuthStateUseCase authStateUseCase) {
    return new HaramViewModel(authStateUseCase);
  }
}
