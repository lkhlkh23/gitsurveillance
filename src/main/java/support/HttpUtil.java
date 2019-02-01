package support;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class HttpUtil {

    public static HttpHeaders createHttpHeader(MediaType mediaType) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(mediaType);

        return httpHeaders;
    }

    public static HttpEntity createResource() {
        return new HttpEntity(createHttpHeader(MediaType.APPLICATION_FORM_URLENCODED));
    }
}
