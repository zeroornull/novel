package io.github.zeroornull.novel.core.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.elasticsearch.RestClientBuilderCustomizer;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * Elasticsearch 相关配置
 *
 * @author pax
 * @since 2024-10-11 23:58:27
 */
@Configuration
@Slf4j
public class EsConfig {
    /**
     * fix `sun.security.validator.ValidatorException: PKIX path building failed: sun.security.provider.certpath.SunCertPathBuilderException:
     * unable to find valid certification path to requested target`
     */
    RestClient elasticSearchRestClient(RestClientBuilder restClientBuilder, ObjectProvider<RestClientBuilderCustomizer> builderCustomizers) {
        restClientBuilder.setHttpClientConfigCallback((HttpAsyncClientBuilder clientBuilder) -> {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            }};
            SSLContext sc = null;
            try {
                sc = SSLContext.getInstance("SSL");
                sc.init(null, trustAllCerts, new SecureRandom());
            } catch (KeyManagementException | NoSuchAlgorithmException e) {
                log.error("Elasticsearch RestClient 配置失败！", e);
            }
            assert sc != null;
            clientBuilder.setSSLContext(sc);
            clientBuilder.setSSLHostnameVerifier((hostname, session) -> true);
            builderCustomizers.orderedStream().forEach((customizer) -> customizer.customize(clientBuilder));

            return clientBuilder;
        });
        return restClientBuilder.build();
    }
}
