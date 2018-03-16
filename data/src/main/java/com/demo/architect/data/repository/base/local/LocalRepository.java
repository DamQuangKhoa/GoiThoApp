package com.demo.architect.data.repository.base.local;

import com.demo.architect.data.model.MessageModel;

import java.util.List;

import rx.Observable;

public interface LocalRepository {

    Observable<String> add(MessageModel model);

    Observable<List<MessageModel>> findAll();

}
