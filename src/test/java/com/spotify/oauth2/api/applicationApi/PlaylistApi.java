package com.spotify.oauth2.api.applicationApi;

import com.spotify.oauth2.api.RestResource;
import com.spotify.oauth2.pojo.Playlist;
import com.spotify.oauth2.utils.ConfigLoader;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static com.spotify.oauth2.api.Route.PLAYLIST;
import static com.spotify.oauth2.api.Route.USERS;
import static com.spotify.oauth2.api.TokenManager.getToken;


public class PlaylistApi {

    //static String access_token = "BQB3ZTL_wOFQ3tBsB44z0pPAFjoVNcVC5PCp4D-BfBVRL97GQFta5BeS5xexA68qzW2X2dym1Zxi0-VVSyAPQ1HY0t4eNJhEeS_oGjJiIyZ54_KgWcUhSWWT5aI4BHQgnu-_vB7FPDn9v6KKUKLkRhunHxzAP_RXKt_jk3EnwKQ4wh71K9Hz-1MYIn2b-dyT8U54-BdhV55KxbrN7JdfTP9frY97rLlOgBdf5evAVc5ViqM6loD8aJtjE4TSwYOxQS87dblU-XuXVsYk";

    @Step()
    public static  Response post(Playlist requestplaylist) {
        return RestResource.post(USERS +"/" + ConfigLoader.getInstance().getUserId()
                + PLAYLIST ,getToken(),requestplaylist);

    }
    @Step
    public static  Response post(String token, Playlist requestplaylist) {
        return RestResource.post(USERS + "/" + ConfigLoader.getInstance().getUserId()
                + PLAYLIST ,token,requestplaylist);

    }
    @Step
    public static Response get (String playlistId) {
        return RestResource.get(PLAYLIST + "/" + playlistId, getToken());

    }
    @Step
    public static Response update (String playlistId,Playlist requestplaylist) {
        return RestResource.update(PLAYLIST + "/" + playlistId, getToken(), requestplaylist);

    }
}
