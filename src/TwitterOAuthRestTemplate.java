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

    private static final String BLANK_SPACE = "%20";

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

    // FIXME: Method to override the URL encoding that Spring's OAuth does NOT do for colon in queries, e.g. (from:abc since:2010-10-10)
    private URI encodeUriVariables(URI uri) {
        try {
            String encodedUrl = uri.toASCIIString().substring(0, 49) + encodeQueryStringParameters(uri.toASCIIString().substring(49).split(BLANK_SPACE));
            return new URI(encodedUrl);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String encodeQueryStringParameters(String... queryString) throws UnsupportedEncodingException {

        StringBuffer output = new StringBuffer();

        for (String param : queryString) {
            output.append(URLEncoder.encode(param, "UTF-8"));
            output.append(BLANK_SPACE);
        }

        return output.toString();

    }
}

