package com.lymno.cmoney.network;

import com.lymno.cmoney.model.Wallet;
import com.lymno.cmoney.model.export.BaseWalletInfo;
import com.lymno.cmoney.model.export.BaseWalletOperation;
import com.lymno.cmoney.model.export.LoginData;
import com.lymno.cmoney.model.export.User;
import com.lymno.cmoney.model.export.WalletID;
import com.lymno.cmoney.model.imported.Token;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Query;

public interface Api {
    @POST("/api/user/registration")
    void register(@Body User user, Callback<Void> cb);

    @POST("/api/user/entrance")
    void entrance(@Body LoginData user, Callback<Token> cb);

    @POST("/api/wallet/CreateWallet")
    void addWallet(@Header("Token") String token, @Body BaseWalletInfo user, Callback<Wallet> cb);

    @POST("/api/payment/Add")
    void addOperation(@Header("Token") String token, @Body BaseWalletOperation op, Callback<Void> cb);

    @GET("/api/wallet/GetAllWallets")
    void updateWallets(@Header("Token") String token, Callback<ArrayList<Wallet>> callback);

    @POST("/api/wallet/AddUserToWallet")
    void addUserToWallet(@Header("Token") String token, @Body WalletID id, Callback<Wallet> cb);

//
//    //Получить токен по логину и паролю (вход)
//    @POST("/api/account/getToken")
//    void auth(@Body User user, Callback<Token> cb);
//
//    //Получить продукты пользователя
//    @GET("/api/userproduct/getUserProducts")
//    void syncProducts(@Header("Authorization") String token, Callback<ArrayList<UserProduct>> callback);
//
//    //Получить рецепты, строго подходящие пользователю
//    @GET("/api/recipes/getSimpleRecipes")
//    void getRecipesSimple(@Header("Authorization") String token, Callback<ArrayList<Recipe>> callback);
//
//    //Проверить, если продукт с таким штрих-кодом в базе
//    @GET("/api/userproduct/productSearchByBarcode")
//    void searchBarcode(@Header("Authorization") String token, @Query("barcode") String code, Callback<ProductSearchResult> callback);
//
//
//    //Добавляет продукт, если такой уже есть в базе на сервере
//    @POST("/api/userproduct/addProduct")
//    void addProd(@Header("Authorization") String token, @Body UserProductExisting product, Callback<UserProductId> callback);
//
//    //Синхронизирует список категорий, который используется для автокомплита при добавлении продукта
//    @GET("/api/sync/GetCategories")
//    void syncCategories(Callback<ArrayList<Category>> callback);
//
//
//    @POST("/api/userproduct/addNewProduct")
//    void addNewProduct(@Header("Authorization") String token, @Body UserProductNotExisting product, Callback<NewProductAddResult> callback);
//
//    //обновляется
//    @PUT("/api/userproduct/AmountChange")
//    void changeAmount(@Header("Authorization") String token, @Body NewAmount amount, Callback<Void> callback);
}
