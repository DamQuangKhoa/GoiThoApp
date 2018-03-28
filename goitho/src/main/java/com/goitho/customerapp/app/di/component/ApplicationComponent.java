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
import com.goitho.customerapp.screen.dashboard.DashboardComponent;
import com.goitho.customerapp.screen.dashboard.DashboardModule;
import com.goitho.customerapp.screen.delete_action.DeleteActionComponent;
import com.goitho.customerapp.screen.delete_action.DeleteActionModule;
import com.goitho.customerapp.screen.detail_diary.DetailDiaryComponent;
import com.goitho.customerapp.screen.detail_diary.DetailDiaryModule;
import com.goitho.customerapp.screen.detail_order.DetailOrderActivity;
import com.goitho.customerapp.screen.detail_order.DetailOrderComponent;
import com.goitho.customerapp.screen.detail_order.DetailOrderModule;
import com.goitho.customerapp.screen.diary.DiaryComponent;
import com.goitho.customerapp.screen.diary.DiaryModule;
import com.goitho.customerapp.screen.edit_address.EditAddressComponent;
import com.goitho.customerapp.screen.edit_address.EditAddressModule;
import com.goitho.customerapp.screen.edit_detail_diary.EditDetailDiaryComponent;
import com.goitho.customerapp.screen.edit_detail_diary.EditDetailDiaryModule;
import com.goitho.customerapp.screen.edit_profile.EditProfileComponent;
import com.goitho.customerapp.screen.edit_profile.EditProfileModule;
import com.goitho.customerapp.screen.farmer.FarmerComponent;
import com.goitho.customerapp.screen.farmer.FarmerModule;
import com.goitho.customerapp.screen.history.HistoryComponent;
import com.goitho.customerapp.screen.history.HistoryModule;
import com.goitho.customerapp.screen.home.HomeComponent;
import com.goitho.customerapp.screen.home.HomeModule;
import com.goitho.customerapp.screen.landing.LandingComponent;
import com.goitho.customerapp.screen.landing.LandingModule;
import com.goitho.customerapp.screen.login.LoginComponent;
import com.goitho.customerapp.screen.login.LoginModule;
import com.goitho.customerapp.screen.notification.NotificationComponent;
import com.goitho.customerapp.screen.notification.NotificationModule;
import com.goitho.customerapp.screen.order.OrderComponent;
import com.goitho.customerapp.screen.order.OrderModule;
import com.goitho.customerapp.screen.order_cancel.OrderCancelComponent;
import com.goitho.customerapp.screen.order_cancel.OrderCancelModule;
import com.goitho.customerapp.screen.order_doing.OrderDoingComponent;
import com.goitho.customerapp.screen.order_doing.OrderDoingModule;
import com.goitho.customerapp.screen.order_done.OrderDoneComponent;
import com.goitho.customerapp.screen.order_done.OrderDoneModule;
import com.goitho.customerapp.screen.order_repair.OrderRepairContract;
import com.goitho.customerapp.screen.order_repair.OrderRepairModule;
import com.goitho.customerapp.screen.phone_verification.PhoneVerificationComponent;
import com.goitho.customerapp.screen.phone_verification.PhoneVerificationModule;
import com.goitho.customerapp.screen.rating.RatingComponent;
import com.goitho.customerapp.screen.rating.RatingModule;
import com.goitho.customerapp.screen.register.RegisterComponent;
import com.goitho.customerapp.screen.register.RegisterModule;
import com.goitho.customerapp.screen.register_success.RegisterSuccessComponent;
import com.goitho.customerapp.screen.register_success.RegisterSuccessModule;
import com.goitho.customerapp.screen.user.UserComponent;
import com.goitho.customerapp.screen.user.UserModule;

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


    LoginComponent plus(LoginModule loginModule);

    UserComponent plus(UserModule userModule);

    EditAddressComponent plus(EditAddressModule editAddressModule);

    LandingComponent plus(LandingModule landingModule);

    RegisterComponent plus(RegisterModule registerModule);

    PhoneVerificationComponent plus(PhoneVerificationModule phoneVerificationModule);

    FarmerComponent plus(FarmerModule farmerModule);

    DiaryComponent plus(DiaryModule diaryModule);

    DetailDiaryComponent plus(DetailDiaryModule detailDiaryModule);

    EditProfileComponent plus(EditProfileModule editProfileModule);

    CertificateComponent plus(CertificateModule certificateModule);

    EditDetailDiaryComponent plus(EditDetailDiaryModule editDetailDiaryModule);

    AddActionComponent plus(AddActionModule addActionModule);

    DeleteActionComponent plus(DeleteActionModule deleteActionModule);

    HistoryComponent plus(HistoryModule historyModule);

    DashboardComponent plus(HomeModule homeModule, OrderModule orderModule,
                            OrderRepairModule orderRepairModule,
                            NotificationModule notificationModule, UserModule userModule);

    NotificationComponent plus(NotificationModule notificationModule);

    RegisterSuccessComponent plus(RegisterSuccessModule registerModule);

    OrderDoingComponent plus(OrderDoingModule exhibitionDoingModule);

    OrderDoneComponent plus(OrderDoneModule exhibitionDoingModule);

    OrderCancelComponent plus(OrderCancelModule exhibitionDoingModule);

    HomeComponent plus(HomeModule homeModule);

    DetailOrderComponent plus(DetailOrderModule detailOrderModule);

    OrderComponent plus(OrderModule orderModule);

    RatingComponent plus(RatingModule ratingModule);

}
