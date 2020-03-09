package tk.winsweb.myrestaurant.Retrofit;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import tk.winsweb.myrestaurant.Model.UpdateUserModel;
import tk.winsweb.myrestaurant.Model.UserModel;

public interface IMyRestaurantAPI {

    @GET("user")
    Observable<UserModel> getUser (
            @Query("Key") String apiKey,
            @Query("fbid") String fbid
    );
    @POST("user")
    @FormUrlEncoded
    Observable<UpdateUserModel> updateUserModle(
            @Field("Key") String apiKey,
            @Field("userPhone") String userPhone,
            @Field("userName") String userName,
            @Field("userAddress") String userAddress,
            @Field("fbid") String fbid
    );
}
