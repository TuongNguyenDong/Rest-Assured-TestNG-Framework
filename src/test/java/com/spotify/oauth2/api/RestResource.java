package com.spotify.oauth2.api;

import io.restassured.response.Response;

import java.util.HashMap;

import static com.spotify.oauth2.api.Route.API;
import static com.spotify.oauth2.api.Route.TOKEN;
import static com.spotify.oauth2.api.SpecBuilder.*;
import static io.restassured.RestAssured.given;

public class RestResource {

   // static String access_token = "BQB3ZTL_wOFQ3tBsB44z0pPAFjoVNcVC5PCp4D-BfBVRL97GQFta5BeS5xexA68qzW2X2dym1Zxi0-VVSyAPQ1HY0t4eNJhEeS_oGjJiIyZ54_KgWcUhSWWT5aI4BHQgnu-_vB7FPDn9v6KKUKLkRhunHxzAP_RXKt_jk3EnwKQ4wh71K9Hz-1MYIn2b-dyT8U54-BdhV55KxbrN7JdfTP9frY97rLlOgBdf5evAVc5ViqM6loD8aJtjE4TSwYOxQS87dblU-XuXVsYk";


    public static  Response post(String path,String token, Object requestplaylist) {

        return  given(getRequestSpec()).
                body(requestplaylist).
                auth().oauth2(token).
//                header("Authorization", "Bearer " + token).
        when().
                post(path).
        then().spec(getResponseSpec()).
                extract().
                response();


    }
    public static Response postAccount(HashMap<String, String>formParams) {
       return  given(getAccountRequestSpec()).
                formParams(formParams).
                when().
                post(API +TOKEN).
                then().
                spec(getResponseSpec()).
                extract().
                response();
    }


    public static Response get (String path,String token ) {
        return given(getRequestSpec()).
                auth().oauth2(token).
//                header("Authorization", "Bearer " + token).
        when().
                get(path).
        then().spec(getResponseSpec()).
                extract().
                response();


    }

    public static Response update (String path,String token, Object requestplaylist) {
        return given(getRequestSpec()).
                body(requestplaylist).
                auth().oauth2(token).
//                header("Authorization", "Bearer " + token).
        when().
                put(path).
        then().spec(getResponseSpec()).
                extract().
                response();

    }
}
