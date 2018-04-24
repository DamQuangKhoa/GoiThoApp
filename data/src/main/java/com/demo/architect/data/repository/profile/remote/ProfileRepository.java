package com.demo.architect.data.repository.profile.remote;

import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.PointEntity;
import com.demo.architect.data.model.ResetPasswordEntity;
import com.demo.architect.data.model.UpdateUserEntity;
import com.demo.architect.data.model.UserEntity;
import com.demo.architect.data.model.VerificationEntity;

import rx.Observable;

/**
 * Created by Skull on 04/01/2018.
 */

public interface ProfileRepository {
    Observable<BaseResponse<ResetPasswordEntity>> resetPassword(String username, String phone);
    Observable<BaseResponse<VerificationEntity>> resetPhoneNumber(String username, String password, String phone);
    Observable<BaseResponse> activeResetPassword(String userId, String authCode, String newPassword);
    Observable<BaseResponse> activeResetPhoneNumber(String userId, String authCode, String newPhoneNumber);
    Observable<BaseResponse<UpdateUserEntity>> updateUserProfile(UserEntity entity);
    Observable<BaseResponse<PointEntity>> getInfoPoint(String userId);
}
