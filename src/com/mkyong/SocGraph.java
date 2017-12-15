package com.mkyong;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

public class SocGraph /*HTTP Connection*/{

	private final String USER_AGENT = "Mozilla/5.0";
	private int publics = 0;		//amount of public repositories calculated
	public static void main(String[] args) throws Exception {

		SocGraph http = new SocGraph();

		System.out.println("Testing 1 - Send Http GET request for all Github Users");
		//http.sendGetUser();
		//http.sendGetDefault();
		//http.sendGetSpecified("ConorM16");
		//http.queryRate();
		http.authenticate();
		//http.queryRate();
		//System.out.println("\nTesting 2 - Send Http POST request");
		//http.sendPost();

	}
	
	//authenticate me
	private void authenticate() throws Exception{
		String url = "https://api.github.com/users/?access_token=26fb5fb67db15c01e97c3f8da21414b3c65d61a2"; 
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		//con.setRequestProperty("User-Agent", USER_AGENT);
		con.addRequestProperty("User-Agent", USER_AGENT);	//USER_AGENT = "Mozilla/5.0";

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		//String response = "";
		while ((inputLine = in.readLine()) != null) {
			response = response.append(inputLine);
			//System.out.println("\n");
		}
		in.close();
		String splIn = response.toString();
		String[] splitInput = splIn.split(",");
		for(int i = 0; i < splitInput.length; i++)
		{
			System.out.println(splitInput[i]);
		}

	}
	// getting query rate
	private void queryRate() throws Exception {
		String url = "https://api.github.com/rate_limit"; 
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		//con.setRequestProperty("User-Agent", USER_AGENT);
		con.addRequestProperty("User-Agent", /*"Mozilla/4.76"*/USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		//String response = "";
		while ((inputLine = in.readLine()) != null) {
			response = response.append(inputLine);
			//System.out.println("\n");
		}
		in.close();
		String splIn = response.toString();
		String[] splitInput = splIn.split(",");
		for(int i = 0; i < splitInput.length; i++)
		{
			System.out.println(splitInput[i]);
		}
	}
	
	// HTTP GET request - default github accounts
	private void sendGetDefault() throws Exception {

		String url = "https://api.github.com/users";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		// optional default is GET
		con.setRequestMethod("GET");
	
		//add request header
		// con.setRequestProperty("User-Agent", USER_AGENT);
		con.addRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		// String response = "";
		while ((inputLine = in.readLine()) != null) {
			response = response.append(inputLine);
			// System.out.println("\n");
		}
		in.close();
		String splIn = response.toString();
		String[] splitInput = splIn.split(",");
		// print result
		// for(int i = 0; i < splitInput.length; i++)
		// {
		// System.out.println(splitInput[i]);
		// }
		String[] usernames = findUsernames(splitInput);
		printRepos(usernames);
	}
	
	/**
	 * 	
	 * @param username - github account we want to access
	 * @param info - piece of info we want to extract from account
	 * @throws Exception
	 */
	private void sendGetSpecified(String username) throws Exception {

			String url = "https://api.github.com/users/" + username;
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	
			// optional default is GET
			con.setRequestMethod("GET");
	
			//add request header
			//con.addRequestProperty("User-Agent", USER_AGENT);
			con.addRequestProperty("User-Agent", USER_AGENT);
	
			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);
	
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			//String response = "";
			while ((inputLine = in.readLine()) != null) {
				response = response.append(inputLine);
				//System.out.println("\n");
			}
			in.close();
			String splIn = response.toString();
			String[] splitInput = splIn.split(",");
			//print result
//			for(int i = 0; i < splitInput.length; i++)
//			{
//				System.out.println(splitInput[i]);
//			}
			publicRepos(splitInput, username);
		}
		
	private void publicRepos(String [] input, String username){
		int i = 0;
		int pubRepos;
		String repos = "public_repos";
		for(int j = 0; j < input.length; j++)
		{
			if(input[j].contains(repos))
			{
				i = j;
			}
		}
		String[] splitRepos = input[i].split(":");
		System.out.println("\n" + username + "'s public repos: " + splitRepos[splitRepos.length-1]);
		pubRepos = Integer.parseInt(splitRepos[splitRepos.length-1]);
		publics = publics + pubRepos;
	}
	
	private String [] findUsernames(String [] input){
		ArrayList<String> names = new ArrayList<String>();
		String lookup = "login";
		String [] splitArr;
		String noQuotes = "";					//username without the quotation marks, "mark" -> mark
		int i;
		for(i = 0; i < input.length; i++)
		{
			if(input[i].contains(lookup))
			{
				splitArr = input[i].split(":");
				noQuotes = splitArr[splitArr.length - 1].replace("\"", "");
				names.add(noQuotes);
			}
		}
		Object [] objList = names.toArray();
		String [] usernames = Arrays.copyOf(objList,  objList.length, String[].class);
		printArray(usernames);
		return usernames;
	}
	
	// prints out array
	private void printArray(String [] array){
		for(int i = 0; i < array.length; i++)
		{
			System.out.println(array[i]);
		}
	}
	
	private void printRepos(String [] users) throws Exception {
		SocGraph http = new SocGraph();
		int i;
		for(i = 0; i< users.length; i++)
		{
			http.sendGetSpecified(users[i]);
		}
	}
//	private void removeChar(String [] input, CharSequence remove){
//		for(int i = 0; i < input.length; i++)
//		{
//			input[i] = input[i].replace(remove,"");
//		}
//	}

	// HTTP POST request
	private void sendPost() throws Exception {

		String url = "https://selfsolve.apple.com/wcResults.do";
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

		//add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

		String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";

		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());

	}

}