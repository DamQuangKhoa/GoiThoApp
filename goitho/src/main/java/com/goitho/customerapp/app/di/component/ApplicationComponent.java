package com.goitho.customerapp.app.di.component;

import com.goitho.customerapp.app.CoreApplication;
import com.goitho.customerapp.app.base.BaseActivity;
import com.goitho.customerapp.app.base.BaseFragment;
import com.goitho.customerapp.app.di.module.ApplicationModule;
import com.goitho.customerapp.app.di.module.NetModule;
import com.goitho.customerapp.app.di.module.RepositoryModule;
import com.goitho.customerapp.app.di.module.UseCaseModule;
import com.goitho.customerapp.screen.blog.BlogComponent;
import com.goitho.customerapp.screen.blog.BlogModule;
import com.goitho.customerapp.screen.booking.BookingModule;
import com.goitho.customerapp.screen.dashboard.DashboardComponent;
import com.goitho.customerapp.screen.dashboard.DashboardComponent2;
import com.goitho.customerapp.screen.dashboard.DashboardModule;
import com.goitho.customerapp.screen.detail_order.DetailOrderComponent;
import com.goitho.customerapp.screen.detail_order.DetailOrderModule;
import com.goitho.customerapp.screen.detail_promotion.DetailPromotionComponent;
import com.goitho.customerapp.screen.detail_promotion.DetailPromotionModule;
import com.goitho.customerapp.screen.edit_address.EditAddressComponent;
import com.goitho.customerapp.screen.edit_address.EditAddressModule;
import com.goitho.customerapp.screen.edit_profile.EditProfileComponent;
import com.goitho.customerapp.screen.edit_profile.EditProfileModule;
import com.goitho.customerapp.screen.home.HomeComponent;
import com.goitho.customerapp.screen.home.HomeModule;
import com.goitho.customerapp.screen.landing.LandingComponent;
import com.goitho.customerapp.screen.landing.LandingModule;
import com.goitho.customerapp.screen.list_promotion.ListPromotionComponent;
import com.goitho.customerapp.screen.list_promotion.ListPromotionModule;
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
import com.goitho.customerapp.screen.phone_verification.PhoneVerificationComponent;
import com.goitho.customerapp.screen.phone_verification.PhoneVerificationModule;
import com.goitho.customerapp.screen.post.PostComponent;
import com.goitho.customerapp.screen.post.PostModule;
import com.goitho.customerapp.screen.question.QuestionComponent;
import com.goitho.customerapp.screen.question.QuestionModule;
import com.goitho.customerapp.screen.rating.RatingComponent;
import com.goitho.customerapp.screen.rating.RatingModule;
import com.goitho.customerapp.screen.register.RegisterComponent;
import com.goitho.customerapp.screen.register.RegisterModule;
import com.goitho.customerapp.screen.register_success.RegisterSuccessComponent;
import com.goitho.customerapp.screen.register_success.RegisterSuccessModule;
import com.goitho.customerapp.screen.support_center.SupportCenterComponent;
import com.goitho.customerapp.screen.support_center.SupportCenterModule;
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

    EditProfileComponent plus(EditProfileModule editProfileModule);

    DashboardComponent plus(HomeModule homeModule, OrderModule orderModule,
                            BookingModule bookingModule,
                            NotificationModule notificationModule, UserModule userModule);

    NotificationComponent plus(NotificationModule notificationModule);

    RegisterSuccessComponent plus(RegisterSuccessModule registerModule);

    OrderDoingComponent plus(OrderDoingModule exhibitionDoingModule);

    OrderDoneComponent plus(OrderDoneModule exhibitionDoingModule);


    ListPromotionComponent plus(ListPromotionModule exhibitionDoingModule);

    DetailPromotionComponent plus(DetailPromotionModule exhibitionDoingModule);

    OrderCancelComponent plus(OrderCancelModule exhibitionDoingModule);

    HomeComponent plus(HomeModule homeModule);

    DetailOrderComponent plus(DetailOrderModule detailOrderModule);

    OrderComponent plus(OrderModule orderModule);

    RatingComponent plus(RatingModule ratingModule);

    DashboardComponent2 plus(DashboardModule dashboardModule);

    BlogComponent plus(BlogModule blogModule);

    PostComponent plus(PostModule postModule);

    SupportCenterComponent plus(SupportCenterModule supportCenterModule);

    QuestionComponent plus(QuestionModule questionModule);

}
