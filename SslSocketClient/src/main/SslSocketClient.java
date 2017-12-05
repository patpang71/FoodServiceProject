package main;

import java.io.IOException;

import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class SslSocketClient {
	
	public static void main(String[] args) {
		SSLSocketFactory f = (SSLSocketFactory) SSLSocketFactory.getDefault();
		try {
			SSLSocket c = (SSLSocket) f.createSocket("localhost", 8443);
			printSSLSocketInfo(c);
			c.close();
		} catch (IOException e) {
			System.err.println(e.toString());
		}
	}

	private static void printSSLSocketInfo(SSLSocket s) {
		System.out.println("SSLSocket class: " + s.getClass());
		System.out.println("   Remote address = " + s.getInetAddress().toString());
		System.out.println("   Remote port = " + s.getPort());
		System.out.println("   Local SSLSocket address = " + s.getLocalSocketAddress().toString());
		System.out.println("   Local address = " + s.getLocalAddress().toString());
		System.out.println("   Local port = " + s.getLocalPort());
		System.out.println("   Need client authentication = " + s.getNeedClientAuth());
		SSLSession ss = s.getSession();
		System.out.println("   Cipher suite = " + ss.getCipherSuite());
		System.out.println("   Protocol = " + ss.getProtocol());
	}

}
