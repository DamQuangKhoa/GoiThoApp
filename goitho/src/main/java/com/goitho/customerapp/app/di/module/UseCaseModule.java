package com.goitho.customerapp.app.di.module;

import com.demo.architect.data.repository.auth.remote.AuthRepository;
import com.demo.architect.data.repository.notification.remote.NotificationRepository;
import com.demo.architect.data.repository.order.remote.OrderRepository;
import com.demo.architect.data.repository.profile.remote.ProfileRepository;
import com.demo.architect.data.repository.upload.remote.UploadRepository;
import com.demo.architect.domain.usecase.ActiveResetPasswordUsecase;
import com.demo.architect.domain.usecase.ActiveResetPhoneNumberUsecase;
import com.demo.architect.domain.usecase.ActiveUserUsecase;
import com.demo.architect.domain.usecase.BookingUsecase;
import com.demo.architect.domain.usecase.CheckSaleUsecase;
import com.demo.architect.domain.usecase.GetInfoPointUsecase;
import com.demo.architect.domain.usecase.GetListNotificationsUsecase;
import com.demo.architect.domain.usecase.GetListPromotionsUsecase;
import com.demo.architect.domain.usecase.LoginUsecase;
import com.demo.architect.domain.usecase.RegisterUsecase;
import com.demo.architect.domain.usecase.ResetPasswordUsecase;
import com.demo.architect.domain.usecase.ResetPhoneNumberUsecase;
import com.demo.architect.domain.usecase.SendActiveUsecase;
import com.demo.architect.domain.usecase.UpdateUserProfileUsecase;
import com.demo.architect.domain.usecase.UploadImageSetCalenderUsecase;

import dagger.Module;
import dagger.Provides;

/**
 * Created by uyminhduc on 12/16/16.
 */
@Module
public class UseCaseModule {
    public UseCaseModule() {
    }

    @Provides
    ResetPhoneNumberUsecase provideResetPhoneNumberUsecase(ProfileRepository remoteRepository) {
        return new ResetPhoneNumberUsecase(remoteRepository);
    }

    @Provides
    LoginUsecase provideLoginUsecase(AuthRepository remoteRepository) {
        return new LoginUsecase(remoteRepository);
    }

    @Provides
    RegisterUsecase provideRegisterUsecase(AuthRepository remoteRepository) {
        return new RegisterUsecase(remoteRepository);
    }

    @Provides
    SendActiveUsecase provideSendActiveUsecase(AuthRepository remoteRepository) {
        return new SendActiveUsecase(remoteRepository);
    }

    @Provides
    ActiveResetPasswordUsecase provideActiveResetPasswordUsecase(ProfileRepository remoteRepository) {
        return new ActiveResetPasswordUsecase(remoteRepository);
    }

    @Provides
    ActiveResetPhoneNumberUsecase provideActiveResetUsecase(ProfileRepository remoteRepository) {
        return new ActiveResetPhoneNumberUsecase(remoteRepository);
    }

    @Provides
    ActiveUserUsecase provideActiveUserUsecase(AuthRepository remoteRepository) {
        return new ActiveUserUsecase(remoteRepository);
    }

    @Provides
    ResetPasswordUsecase provideResetPasswordUsecase(ProfileRepository remoteRepository) {
        return new ResetPasswordUsecase(remoteRepository);
    }

    @Provides
    UpdateUserProfileUsecase provideUpdateUserProfileUsecase(ProfileRepository remoteRepository) {
        return new UpdateUserProfileUsecase(remoteRepository);
    }

    @Provides
    GetListNotificationsUsecase provideGetListNotificationsUsecase(NotificationRepository remoteRepository) {
        return new GetListNotificationsUsecase(remoteRepository);
    }

    @Provides
    GetListPromotionsUsecase provideGetListPromotionsUsecase(NotificationRepository remoteRepository) {
        return new GetListPromotionsUsecase(remoteRepository);
    }

    @Provides
    BookingUsecase provideBookingUsecase(OrderRepository remoteRepository) {
        return new BookingUsecase(remoteRepository);
    }

    @Provides
    CheckSaleUsecase provideCheckSaleUsecase(OrderRepository remoteRepository) {
        return new CheckSaleUsecase(remoteRepository);
    }

    @Provides
    UploadImageSetCalenderUsecase provideUploadImageSetCalenderUsecase(UploadRepository remoteRepository) {
        return new UploadImageSetCalenderUsecase(remoteRepository);
    }

    @Provides
    GetInfoPointUsecase provideGetInfoPointUsecase(ProfileRepository remoteRepository) {
        return new GetInfoPointUsecase(remoteRepository);
    }
}

