package com.oracle.urlshortner.controller;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oracle.urlshortner.utils.URLShortnerUtil;

@RequestMapping("/api/v1")
@RestController
public class URLShortnerController {
	
	Map<String,String> map=new HashMap<>();

	@PostMapping(value="/short-url")
	public String getShortURL(@RequestBody String longURL) throws MalformedURLException, URISyntaxException, UnsupportedEncodingException {
		String decodedURL=URLDecoder.decode(longURL, "UTF-8");
		System.out.println("######"+decodedURL);
		URL url=URLShortnerUtil.validateURl(URLDecoder.decode(longURL, "UTF-8"));
		URL output= new URL(url.getProtocol(), url.getHost(),URLShortnerUtil.getHashURL(url.getFile()));
		map.put(output.toString(), decodedURL);
		return longURL+"  =====>  "+output.toString();
	}
	
	@PostMapping(value="/long-url")
	public String getLongURL(@RequestBody String shortURL) throws MalformedURLException, URISyntaxException, UnsupportedEncodingException {
		String decodedURL=URLDecoder.decode(shortURL, "UTF-8");
		return map.get(decodedURL);
	}
}
