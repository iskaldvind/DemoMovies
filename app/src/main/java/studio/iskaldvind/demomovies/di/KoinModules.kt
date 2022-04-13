package studio.iskaldvind.demomovies.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import studio.iskaldvind.demomovies.repository.IRepository
import studio.iskaldvind.demomovies.repository.IRepositoryAPI
import studio.iskaldvind.demomovies.repository.MoviesPageSource
import studio.iskaldvind.demomovies.repository.Repository
import studio.iskaldvind.demomovies.viewmodel.ListViewModel

val application = module {
    single { IRepositoryAPI.create() }
    single<IRepository> { Repository(api = get()) }
    single { MoviesPageSource(repository = get()) }
}

val list = module {
    viewModel { ListViewModel(pagingSource = get()) }
}