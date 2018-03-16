package com.demo.architect.domain.usecase.demo;

import android.util.Log;

import com.demo.architect.data.model.IMDBEntity;
import com.demo.architect.data.model.MessageModel;
import com.demo.architect.data.repository.base.local.LocalRepository;
import com.demo.architect.data.repository.bill.remote.BillRepository;
import com.demo.architect.domain.usecase.BaseUseCase;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by admin on 7/6/17.
 */

public class IMDBUsecase extends BaseUseCase {

    private final BillRepository billRepository;
    private final LocalRepository localRepository;

    public IMDBUsecase(BillRepository billRepository, LocalRepository localRepository) {
        this.billRepository = billRepository;
        this.localRepository = localRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        // localRepository.add(MessageModel.newBuilder().content("CONTENT2").build());

//        localRepository.add(MessageModel.newBuilder().content("CONTENT2").build())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<String>() {
//                    @Override
//                    public void call(String s) {
//                       Log.d("Yuki","added");
//                    }
//                });

//        Observable
//                .combineLatest(apiRepository.getIMDB(), apiRepository.getIMDB(), new Func2<IMDBEntity, IMDBEntity, String>() {
//                            @Override
//                            public String call(IMDBEntity imdbEntity, IMDBEntity imdbEntity2) {
//                                return null;
//                            }
//                        });


        return billRepository.getIMDB("http://www.imdb.com/xml/find?json=1&nr=1&nm=on&q=jeniffer+garner").map(new Func1<IMDBEntity, String>() {

            @Override
            public String call(IMDBEntity imdbEntity) {
                String output = "";
                for (int i = 0; i < imdbEntity.getName_approx().size(); i++) {
                    output += imdbEntity.getName_approx().get(i).getName() + "\n";
                    localRepository.add(MessageModel.newBuilder().content(imdbEntity.getName_approx().get(i).getName()).build())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Action1<String>() {
                                @Override
                                public void call(String s) {
                                    Log.d("YuKi", "added");
                                }
                            });
                }

                return output;
            }
        });
    }

    @Override
    protected Subscriber buildUseCaseSubscriber() {
        return null;
    }

}
