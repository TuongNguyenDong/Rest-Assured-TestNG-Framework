package com.spotify.oauth2.tests;

import com.spotify.oauth2.api.StatusCode;
import com.spotify.oauth2.api.applicationApi.PlaylistApi;
import com.spotify.oauth2.pojo.Error;
import com.spotify.oauth2.pojo.Playlist;
import com.spotify.oauth2.utils.DataLoader;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.spotify.oauth2.utils.FakerUtils.generateDescription;
import static com.spotify.oauth2.utils.FakerUtils.generateName;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
@Epic("Spotify Oauth 2.0")
@Feature("Playlist API")
public class PlaylistTests extends BaseTest {
    @Step
    public Playlist playlistBuilder(String name, String description, boolean _public){
    return  Playlist.builder().
               name(name).
               description(description).
               _public(_public).
               build();
//        Playlist playlist = new Playlist();
//        playlist.setName(name);
//        playlist.setDescription(description);
//        playlist.set_public(_public);
//        return  playlist;


    }
    @Step
    public void assertPlaylistEqual(Playlist responsePlaylist, Playlist requestplaylist) {
        assertThat(responsePlaylist.getName(), equalTo(requestplaylist.getName()));
        assertThat(responsePlaylist.getDescription(), equalTo(requestplaylist.getDescription()));
        assertThat(responsePlaylist.get_public(), equalTo(requestplaylist.get_public()));


    }
    @Step
    public void assertStatusCode (int actualStatusCode, int expectedStatusCode) {
        assertThat(actualStatusCode, equalTo(expectedStatusCode));

    }
    @Step
    public void assertError (Error responseErr, int expectedStatusCode, String expectedMsg) {
        assertThat(responseErr.getError().getStatus(), equalTo(expectedStatusCode));
        assertThat(responseErr.getError().getMessage(), equalTo(expectedMsg));

    }

    @Story("Create a playlist story")
    @Description(" this is a description")
    @Test (description = "should be able to create a playlist")
    public void CreateAPIPlayList() {
        Playlist requestplaylist = playlistBuilder(generateName(),generateDescription(),false);

        Response response = PlaylistApi.post(requestplaylist);
        assertStatusCode(response.statusCode(), StatusCode.CODE_201.getCode());
//        assertThat(response.statusCode(), equalTo(201));
//        Playlist responsePlaylist = response.as(Playlist.class);
        assertPlaylistEqual(response.as(Playlist.class),requestplaylist);
//        assertThat(responsePlaylist.getName(), equalTo(requestplaylist.getName()));
//        assertThat(responsePlaylist.getDescription(), equalTo(requestplaylist.getDescription()));
//        assertThat(responsePlaylist.getPublic(), equalTo(requestplaylist.getPublic()));

    }
    @Test
    public void GetAPIPlayList() {
        Playlist requestplaylist = playlistBuilder("Playlist _4N-,s__-,","Description _++&amp;&amp;Eb.s&amp;&amp;##4s_&amp;_++#q#__G#2#+_+oo_3508#Q#+_&amp;_#b#_",true);

        Response response = PlaylistApi.get(DataLoader.getInstance().getPlaylistId());
        assertStatusCode(response.statusCode(), StatusCode.CODE_200.getCode());
        assertPlaylistEqual(response.as(Playlist.class),requestplaylist);

    }
    @Test
    public void UpdateAPIPlayList() {
        Playlist requestplaylist = playlistBuilder(generateName(),generateDescription(),false);

        Response response = PlaylistApi.update(DataLoader.getInstance().getUpdatePlaylistID(),requestplaylist);
        assertStatusCode(response.statusCode(), StatusCode.CODE_200.getCode());

    }
    @Story("Create a playlist story")
    @Test
    public void ShouldNotBeAbleToCreateAPIPlayListWithName() {

        Playlist requestplaylist = playlistBuilder("",generateDescription(),false);

        Response response = PlaylistApi.post(requestplaylist);
        assertStatusCode(response.statusCode(), StatusCode.CODE_400.getCode());

//        Error error = response.as(Error.class);
//        assertThat(error.getError().getStatus(), equalTo(400));
//        assertThat(error.getError().getMessage(), equalTo("Missing required field: name"));
        assertError(response.as(Error.class), StatusCode.CODE_400.getCode(),StatusCode.CODE_400.getMsg());

    }
    @Story("Create a playlist story")
    @Test
    public void ShouldNotBeAbleToCreateAPIPlayListWithExpiredToken() {
        String invalid_token = "1234sdfg";

        Playlist requestplaylist = playlistBuilder(generateName(),generateDescription(),false);

        Response response = PlaylistApi.post(invalid_token,requestplaylist);
        assertStatusCode(response.statusCode(), StatusCode.CODE_401.getCode());
        assertError(response.as(Error.class), StatusCode.CODE_401.getCode(),StatusCode.CODE_401.getMsg());
//        Error error = response.as(Error.class);
//        assertThat(error.getError().getStatus(), equalTo(401));
//        assertThat(error.getError().getMessage(), equalTo("Invalid access token"));

    }
}
