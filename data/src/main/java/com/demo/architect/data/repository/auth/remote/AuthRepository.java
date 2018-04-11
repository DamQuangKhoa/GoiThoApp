package com.demo.architect.data.repository.auth.remote;

import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.VerificationEntity;
import com.demo.architect.data.model.SendActiveEntity;
import com.demo.architect.data.model.UserEntity;

import rx.Observable;

/**
 * Created by Skull on 04/01/2018.
 */

public interface AuthRepository {
    Observable<BaseResponse<UserEntity>> login(String username, String password);
    Observable<BaseResponse<SendActiveEntity>> sendActive(String userId);
    Observable<BaseResponse> activeUser(String userId, String authCode);
    Observable<BaseResponse<VerificationEntity>> register(String username, String password, String phone);
}
