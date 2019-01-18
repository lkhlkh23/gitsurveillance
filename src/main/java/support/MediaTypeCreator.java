package support;

import org.springframework.http.MediaType;

public class MediaTypeCreator {
    public static final String GIT_VALUE = "application/vnd.github.cloak-preview";

    public static final String GIT_V3_VALUE = "application/vnd.github.mercy-preview+json";

    public static final MediaType GIT_JSON;

    public static final MediaType GIT_V3_JSON;

    static {
        GIT_JSON = MediaType.valueOf(GIT_VALUE);

        GIT_V3_JSON = MediaType.valueOf(GIT_V3_VALUE);
    }

}
