package br.com.movieapp.search_movie_feature.di

import br.com.movieapp.core.data.remote.MovieService
import br.com.movieapp.search_movie_feature.data.repository.MovieSearchRepositoryImpl
import br.com.movieapp.search_movie_feature.data.source.MovieSourceSearchRemoteDataSourceImpl
import br.com.movieapp.search_movie_feature.domain.repository.MovieSearchRepository
import br.com.movieapp.search_movie_feature.domain.source.MovieSourceSearchRemoteDataSource
import br.com.movieapp.search_movie_feature.domain.usecase.GetMovieSearchUseCase
import br.com.movieapp.search_movie_feature.domain.usecase.GetMovieSearchUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieSearchFeatureModule {

    @Provides
    @Singleton
    fun provideMovieSearchDataSource(service: MovieService): MovieSourceSearchRemoteDataSource {
        return MovieSourceSearchRemoteDataSourceImpl(service)
    }

    @Provides
    @Singleton
    fun provideMovieSearchRepository(dataSource: MovieSourceSearchRemoteDataSource): MovieSearchRepository {
        return MovieSearchRepositoryImpl(dataSource)
    }

    @Provides
    @Singleton
    fun provideMovieSearchUseCase(repository: MovieSearchRepository): GetMovieSearchUseCase {
        return GetMovieSearchUseCaseImpl(repository)
    }

}