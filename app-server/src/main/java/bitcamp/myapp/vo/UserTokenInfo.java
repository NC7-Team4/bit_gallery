package bitcamp.myapp.vo;


public class UserTokenInfo {
    private String grantType;
    private String accessToken;
    private String refreshToken;

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getGrantType() {
        return grantType;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}
