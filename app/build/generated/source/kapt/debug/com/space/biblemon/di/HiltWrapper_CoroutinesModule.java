package com.space.biblemon.di;

import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.codegen.OriginatingElement;
import dagger.hilt.components.SingletonComponent;

@OriginatingElement(
    topLevelClass = CoroutinesModule.class
)
@InstallIn(SingletonComponent.class)
@Module(
    includes = CoroutinesModule.class
)
public final class HiltWrapper_CoroutinesModule {
}
