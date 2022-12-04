package com.oracle.urlshortner.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class URLShortnerUtil {
	public static String getMd5Hash(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(input.getBytes());
			BigInteger no = new BigInteger(1, messageDigest);
			String hashtext = no.toString(16);
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			return hashtext;
		}
		catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String getHashURL(String url){
		return Base64.getEncoder().encode(getMd5Hash(url).getBytes()).toString();
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		//String url="https://stackoverflow.com/questions/4992317/illegal-character-in-path-at-index-16";
		String url="https://stackoverflow.com[B@630b6040";
		String encodedURL=URLEncoder.encode(url, "UTF-8");
		String decodeURL=URLDecoder.decode(encodedURL, "UTF-8");
		System.out.println(url.equals(decodeURL)); 
		System.out.println(encodedURL);
	}

	
	public static URL validateURl(String url) throws MalformedURLException, URISyntaxException{
		return new URL(url);
	}
	
}
