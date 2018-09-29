package com.boot.utils.http;

import javax.net.ssl.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

public class SslContextUtils {

    private SSLContext ctx;
    private HostnameVerifier hostVerifier;

    private static final class DefaultTrustManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) {
        }
        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) {
        }
        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }

    private void initContext() {
        try {
            ctx = SSLContext.getInstance("TLS");
            ctx.init(new KeyManager[0], new TrustManager[] { new DefaultTrustManager() }, new SecureRandom());
        } catch (KeyManagementException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        hostVerifier = new HostnameVerifier() {
            @Override
            public boolean verify(String arg0, SSLSession arg1) {
                return true;
            }
        };
    }

  public SslContextUtils() {
        initContext();
  }

    public void initHttpsConnect(HttpsURLConnection httpsConn){
        httpsConn.setSSLSocketFactory(ctx.getSocketFactory());
        httpsConn.setHostnameVerifier(hostVerifier);
    }
}
