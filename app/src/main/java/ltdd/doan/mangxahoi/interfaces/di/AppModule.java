package ltdd.doan.mangxahoi.interfaces.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import ltdd.doan.mangxahoi.api.ApiInterface;
import ltdd.doan.mangxahoi.api.ApiUtils;
import ltdd.doan.mangxahoi.data.repository.ChatRepository;
import ltdd.doan.mangxahoi.data.repository.ConversationRepository;
import ltdd.doan.mangxahoi.data.repository.NotificationRepository;
import ltdd.doan.mangxahoi.data.repository.PostRepository;
import ltdd.doan.mangxahoi.data.repository.UserRepository;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {
    @Provides
    @Singleton
    public UserRepository userRepositoryProvide(@ApplicationContext Context context, ApiInterface apiService){
        return new UserRepository(context, apiService);
    }


    @Provides
    @Singleton
    public PostRepository providePostRepository(@ApplicationContext Context context,ApiInterface apiService) {
        return new PostRepository(context,apiService);
    }

    @Provides
    @Singleton
    public NotificationRepository provideNotificationRepository(ApiInterface apiService) {
        return new NotificationRepository(apiService);
    }

    @Provides
    @Singleton
    public ApiInterface provideApiInterface() {
        return ApiUtils.getApiService();
    }

    @Provides
    @Singleton
    public ChatRepository provideChatRepository(@ApplicationContext Context context,ApiInterface apiService) {
        return new ChatRepository( context,apiService);
    }
    @Provides
    @Singleton
    public ConversationRepository provideConversationRepository(@ApplicationContext Context context, ApiInterface apiService, UserRepository uRepo) {
        return new ConversationRepository( context,apiService, uRepo);
    }
}
