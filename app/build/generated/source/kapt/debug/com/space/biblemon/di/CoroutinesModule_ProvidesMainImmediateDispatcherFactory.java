// Generated by Dagger (https://dagger.dev).
package com.space.biblemon.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import kotlinx.coroutines.CoroutineDispatcher;

@ScopeMetadata
@QualifierMetadata("com.space.shared.common.annotation.MainImmediateDispatcher")
@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class CoroutinesModule_ProvidesMainImmediateDispatcherFactory implements Factory<CoroutineDispatcher> {
  @Override
  public CoroutineDispatcher get() {
    return providesMainImmediateDispatcher();
  }

  public static CoroutinesModule_ProvidesMainImmediateDispatcherFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static CoroutineDispatcher providesMainImmediateDispatcher() {
    return Preconditions.checkNotNullFromProvides(CoroutinesModule.INSTANCE.providesMainImmediateDispatcher());
  }

  private static final class InstanceHolder {
    private static final CoroutinesModule_ProvidesMainImmediateDispatcherFactory INSTANCE = new CoroutinesModule_ProvidesMainImmediateDispatcherFactory();
  }
}
