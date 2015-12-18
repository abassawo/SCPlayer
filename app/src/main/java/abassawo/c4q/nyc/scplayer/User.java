package abassawo.c4q.nyc.scplayer;

/**
 * Created by c4q-Abass on 12/18/15.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
    private Integer id;

    @SerializedName("permalink")
    private String permalink;

    @SerializedName("username")
    private String username;

    @SerializedName("uri")
    private String uri;

    @SerializedName("permalink_url")
    private String permalinkUrl;

    @SerializedName("avatar_url")
    private String avatarUrl;

    @SerializedName("country")
    private String country;

    @SerializedName("full_name")
    private String fullName;

    @SerializedName("city")
    private String city;

    @SerializedName("description")
    private String description;

    @SerializedName("discogs_name")
    private Object discogsName;

    @SerializedName("myspace_name")
    private Object myspaceName;

    @SerializedName("website")
    private String website;

    @SerializedName("website_title")
    private String websiteTitle;

    @SerializedName("online")
    private Boolean online;

    @SerializedName("track_count")
    private Integer trackCount;

    @SerializedName("playlist_count")
    private Integer playlistCount;

    @SerializedName("followers_count")
    private Integer followersCount;

    @SerializedName("followings_count")
    private Integer followingsCount;

    @SerializedName("public_favorites_count")
    private Integer publicFavoritesCount;


    public Integer getId() {
        return id;
    }

    public String getPermalink() {
        return permalink;
    }


    public String getUsername() {
        return username;
    }


    public String getUri() {
        return uri;
    }


    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getPermalinkUrl() {
        return permalinkUrl;
    }


    public void setPermalinkUrl(String permalinkUrl) {
        this.permalinkUrl = permalinkUrl;
    }


    public String getAvatarUrl() {
        return avatarUrl;
    }


    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    /**
     *
     * @return
     * The country
     */
    public String getCountry() {
        return country;
    }


    public void setCountry(String country) {
        this.country = country;
    }


    public String getFullName() {
        return fullName;
    }



    public String getCity() {
        return city;
    }


    public String getDescription() {
        return description;
    }


    public Object getDiscogsName() {
        return discogsName;
    }


    public void setDiscogsName(Object discogsName) {
        this.discogsName = discogsName;
    }

    /**
     *
     * @return
     * The myspaceName
     */
    public Object getMyspaceName() {
        return myspaceName;
    }


    public String getWebsite() {
        return website;
    }


    public String getWebsiteTitle() {
        return websiteTitle;
    }

    public Boolean getOnline() {
        return online;
    }


    public Integer getTrackCount() {
        return trackCount;
    }


    public Integer getPlaylistCount() {
        return playlistCount;
    }



    public Integer getFollowersCount() {
        return followersCount;
    }


    public Integer getFollowingsCount() {
        return followingsCount;
    }


    public Integer getPublicFavoritesCount() {
        return publicFavoritesCount;
    }


    public void setPublicFavoritesCount(Integer publicFavoritesCount) {
        this.publicFavoritesCount = publicFavoritesCount;
    }

}