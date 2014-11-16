import org.springframework.http.HttpMethod;
import org.springframework.security.oauth.common.signature.SharedConsumerSecretImpl;
import org.springframework.security.oauth.common.signature.SignatureSecret;
import org.springframework.security.oauth.consumer.ProtectedResourceDetails;

import java.util.Map;

public class TwitterOAuthCredentials implements ProtectedResourceDetails {

    @Override
    public String getId() {
        return null;
    }

    @Override
    public String getConsumerKey() {
        return "JUHGu3RdoWq0oVKGgsUQcdhQg";
    }

    @Override
    public String getSignatureMethod() {
        return "HMAC-SHA1";
    }

    @Override
    public SignatureSecret getSharedSecret() {
        return new SharedConsumerSecretImpl("jcASB9rE0qGaH5zluiGM5OXrtZuZWIowBFGFl2QL3MMPovGIqU");
    }

    @Override
    public String getRequestTokenURL() {
        return "https://api.twitter.com/oauth/request_token";
    }

    @Override
    public String getRequestTokenHttpMethod() {
        return HttpMethod.GET.name();
    }

    @Override
    public String getUserAuthorizationURL() {
        return "https://api.twitter.com/oauth/authorize";
    }

    @Override
    public String getAccessTokenURL() {
        return "https://api.twitter.com/oauth/access_token";
    }

    @Override
    public String getAccessTokenHttpMethod() {
        return HttpMethod.GET.name();
    }

    @Override
    public boolean isAcceptsAuthorizationHeader() {
        return true;
    }

    @Override
    public String getAuthorizationHeaderRealm() {
        return null;
    }

    @Override
    public boolean isUse10a() {
        return false;
    }

    @Override
    public Map<String, String> getAdditionalParameters() {
        return null;
    }

    @Override
    public Map<String, String> getAdditionalRequestHeaders() {
        return null;
    }
}
