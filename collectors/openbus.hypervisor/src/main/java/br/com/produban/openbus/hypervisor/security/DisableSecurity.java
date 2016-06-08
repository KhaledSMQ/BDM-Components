package br.com.produban.openbus.hypervisor.security;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

// Disable certificate when no have certified verified
public class DisableSecurity {

	private static class TrustAllTrustManager implements javax.net.ssl.TrustManager, javax.net.ssl.X509TrustManager {

		public java.security.cert.X509Certificate[] getAcceptedIssuers() {
			return null;
		}

		public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType)
				throws java.security.cert.CertificateException {
			return;
		}

		public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType)
				throws java.security.cert.CertificateException {
			return;
		}
	}

	public static void trustEveryone() throws NoSuchAlgorithmException, KeyManagementException {
		javax.net.ssl.HostnameVerifier verifier = new HostnameVerifier() {
			public boolean verify(String urlHostName, SSLSession session) {
				return true;
			}
		};
		javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[1];
		javax.net.ssl.TrustManager trustManager = new TrustAllTrustManager();
		trustAllCerts[0] = trustManager;

		javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext.getInstance("SSL");

		javax.net.ssl.SSLSessionContext sslsc = sc.getServerSessionContext();

		sslsc.setSessionTimeout(0);
		sc.init(null, trustAllCerts, null);

		javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		HttpsURLConnection.setDefaultHostnameVerifier(verifier);
	}

}
