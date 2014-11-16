import org.springframework.http.HttpMethod;
import org.springframework.security.oauth.consumer.ProtectedResourceDetails;
import org.springframework.security.oauth.consumer.client.OAuthRestTemplate;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.util.UriTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

public class TwitterOAuthRestTemplate extends OAuthRestTemplate {
    public TwitterOAuthRestTemplate(ProtectedResourceDetails resource) {
        super(resource);
    }

    @Override
    public <T> T execute(String url, HttpMethod method, RequestCallback requestCallback,
                         ResponseExtractor<T> responseExtractor, Object... urlVariables) throws RestClientException {

        URI expanded = new UriTemplate(url).expand(urlVariables);
        URI encodedUri = encodeUriVariables(expanded);

        return doExecute(encodedUri, method, requestCallback, responseExtractor);
    }

    // FIXME: Method to override URL encoding that Spring's OAuth does NOT do for colon queries, e.g. (from:abc since:2010-10-10)
    private URI encodeUriVariables(URI expanded) {
        try {
            String endedUrl = expanded.toASCIIString().substring(0, 49) + URLEncoder.encode(expanded.toASCIIString().substring(49), "UTF-8");
            return new URI(endedUrl);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
}

