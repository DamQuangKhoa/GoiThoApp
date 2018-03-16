package com.demo.architect.domain.usecase;


import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Created by uyminhduc on 4/5/17.
 */

public abstract class BaseUseCase {
    //check rxJava sequence and parallel
    protected Subscription subscription = Subscriptions.empty();
    protected UseCaseCallback useCaseCallback;
    protected RequestValues requestValues;


    protected BaseUseCase() {
    }

    /**
     * Builds an {@link Observable} which will be used when executing the current UseCase.
     */
    protected abstract Observable buildUseCaseObservable();

    protected abstract Subscriber buildUseCaseSubscriber();


    /**
     * Executes the current use case.
     *
     * @param useCaseSubscriber The guy who will be listen to the observable build
     *                          with {@link #buildUseCaseObservable()}.
     */
    @SuppressWarnings("unchecked")
    public void executeIO(RequestValues requestValues, UseCaseCallback useCaseCallback) {
        this.useCaseCallback = useCaseCallback;
        this.requestValues = requestValues;
        this.subscription = this.buildUseCaseObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this.buildUseCaseSubscriber());
    }

        /**
         * Unsubscribes from current
         */
    public void unsubscribe() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }


    /**
     * Data passed to a request.
     */
    public interface RequestValues {
    }

    /**
     * Data received from a request.
     */
    public interface ResponseValues {
    }

    public interface ErrorValues {
    }

    public interface UseCaseCallback<R, E> {
        void onSuccess(R successResponse);

        void onError(E errorResponse);
    }
}

