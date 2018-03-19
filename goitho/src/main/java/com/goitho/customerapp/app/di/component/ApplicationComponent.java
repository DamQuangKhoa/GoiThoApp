package com.goitho.customerapp.app.di.component;

import com.goitho.customerapp.app.CoreApplication;
import com.goitho.customerapp.app.base.BaseActivity;
import com.goitho.customerapp.app.base.BaseFragment;
import com.goitho.customerapp.app.di.module.ApplicationModule;
import com.goitho.customerapp.app.di.module.NetModule;
import com.goitho.customerapp.app.di.module.RepositoryModule;
import com.goitho.customerapp.app.di.module.UseCaseModule;
import com.goitho.customerapp.screen.add_action.AddActionComponent;
import com.goitho.customerapp.screen.add_action.AddActionModule;
import com.goitho.customerapp.screen.certificate.CertificateComponent;
import com.goitho.customerapp.screen.certificate.CertificateModule;
import com.goitho.customerapp.screen.delete_action.DeleteActionComponent;
import com.goitho.customerapp.screen.delete_action.DeleteActionModule;
import com.goitho.customerapp.screen.detail_diary.DetailDiaryComponent;
import com.goitho.customerapp.screen.detail_diary.DetailDiaryModule;
import com.goitho.customerapp.screen.diary.DiaryComponent;
import com.goitho.customerapp.screen.diary.DiaryModule;
import com.goitho.customerapp.screen.edit_detail_diary.EditDetailDiaryComponent;
import com.goitho.customerapp.screen.edit_detail_diary.EditDetailDiaryModule;
import com.goitho.customerapp.screen.edit_profile.EditProfileComponent;
import com.goitho.customerapp.screen.edit_profile.EditProfileModule;
import com.goitho.customerapp.screen.farmer.FarmerComponent;
import com.goitho.customerapp.screen.farmer.FarmerModule;
import com.goitho.customerapp.screen.history.HistoryComponent;
import com.goitho.customerapp.screen.history.HistoryModule;
import com.goitho.customerapp.screen.login.LoginComponent;
import com.goitho.customerapp.screen.login.LoginModule;
import com.goitho.customerapp.screen.login_permission.LoginPermissionComponent;
import com.goitho.customerapp.screen.login_permission.LoginPermissionModule;
import com.goitho.customerapp.screen.phone_verification.PhoneVerificationComponent;
import com.goitho.customerapp.screen.phone_verification.PhoneVerificationModule;
import com.goitho.customerapp.screen.register.RegisterComponent;
import com.goitho.customerapp.screen.register.RegisterModule;
import com.goitho.customerapp.screen.splash.SplashComponent;
import com.goitho.customerapp.screen.splash.SplashModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by uyminhduc on 12/16/16.
 */

@Singleton
@Component(modules = {ApplicationModule.class,
        NetModule.class,
        UseCaseModule.class,
        RepositoryModule.class})
public interface ApplicationComponent {

    void inject(CoreApplication application);

    void inject(BaseActivity activity);

    void inject(BaseFragment fragment);

    SplashComponent plus(SplashModule splashModule);

    LoginComponent plus(LoginModule loginModule);

    RegisterComponent plus(RegisterModule registerModule);

    PhoneVerificationComponent plus(PhoneVerificationModule phoneVerificationModule);

    LoginPermissionComponent plus(LoginPermissionModule loginPermissionModule);

    FarmerComponent plus(FarmerModule farmerModule);

    DiaryComponent plus(DiaryModule diaryModule);

    DetailDiaryComponent plus(DetailDiaryModule detailDiaryModule);

    EditProfileComponent plus(EditProfileModule editProfileModule);

    CertificateComponent plus(CertificateModule certificateModule);

    EditDetailDiaryComponent plus(EditDetailDiaryModule editDetailDiaryModule);

    AddActionComponent plus(AddActionModule addActionModule);

    DeleteActionComponent plus(DeleteActionModule deleteActionModule);

    HistoryComponent plus(HistoryModule historyModule);
}
