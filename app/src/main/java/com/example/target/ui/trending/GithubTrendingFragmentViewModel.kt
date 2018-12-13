package com.example.target.ui.trending

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.target.common.Constants
import com.example.target.data.User
import com.example.target.repository.AppRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject
import java.io.IOException

class GithubTrendingFragmentViewModel @Inject constructor (private var appRepository: AppRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val usersLiveData = MutableLiveData<List<User>>()
    private val isLoading = MutableLiveData<Boolean>()
    private val errorLiveData = MutableLiveData<Throwable>()

    fun getLoadingValue(): MutableLiveData<Boolean> {
        return isLoading
    }

    fun getUsers(): MutableLiveData<List<User>> {
        return usersLiveData
    }

    fun getErrorValue(): MutableLiveData<Throwable> {
        return errorLiveData
    }

    fun getTrendingUsers() {
        try {
            compositeDisposable.add(appRepository.getTrendingUsers(Constants.LANGUAGE, Constants.SINCE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onAPIStart() }
                .doOnTerminate { onAPIFinish() }
                .subscribeWith(object : DisposableObserver<Response<List<User>>>() {
                    override fun onNext(result: Response<List<User>>) {
                        result.body()?.let {
                            onSuccess(it)
                        }
                    }

                    override fun onComplete() {
                    }

                    override fun onError(e: Throwable) {
                        onAPIError(e)
                    }
                })
            )
        } catch (e: IOException) {
            onAPIError(e)
        }
    }

    private fun onAPIStart() {
        isLoading.value = true
    }

    private fun onAPIFinish() {
        isLoading.value = false
    }

    private fun onSuccess(users: List<User>) {
        usersLiveData.value = users
    }

    private fun onAPIError(throwable: Throwable) {
        isLoading.value = false
        errorLiveData.value = throwable
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}