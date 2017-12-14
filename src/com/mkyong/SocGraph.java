package com.mkyong;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

public class SocGraph /*HTTP Connection*/{

	private final String USER_AGENT = "Mozilla/5.0";

	public static void main(String[] args) throws Exception {

		SocGraph http = new SocGraph();

		System.out.println("Testing 1 - Send Http GET request");
		http.sendGet();

		//System.out.println("\nTesting 2 - Send Http POST request");
		//http.sendPost();

	}

	// HTTP GET request
	private void sendGet() throws Exception {

		Scanner input = new Scanner(System.in);
		String username = "";
		int end = 0;
		while(end != 1)
		{
			System.out.println("Please provide your github username: ");
			username = input.next();
			if(username.equals("quit"))
			{
				end = 1;
			}
			else
			{
				//String url = "http://www.google.com/search?q=mkyong";
				String url = "https://api.github.com/users/" + username;// + "/repos";
				URL obj = new URL(url);
				HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		
				// optional default is GET
				con.setRequestMethod("GET");
		
				//add request header
				con.setRequestProperty("User-Agent", USER_AGENT);
		
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
				for(int i = 0; i < splitInput.length; i++)
				{
					System.out.println(splitInput[i]);
				}
				//countRepos(splitInput);
				publicRepos(splitInput, username);
			}
		}
	}
	
	private int publicRepos(String [] input, String username){
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
		return pubRepos;
	}
	/**
	 * 
	 * @param input = array of input from GET command
	 * @return amount of repos for user
	 */
	private int countRepos(String [] input){
		int repos = 0;
		int i;
		String repoSym = "full_name";
		removeChar(input, ":");
		for(i = 0; i < input.length; i++)
		{
			if(input[i].contains(repoSym))
			{
				repos++;
			}
		}
		System.out.println("Amount of public repos: " + repos );
		return repos;
	}
	
	private void removeChar(String [] input, CharSequence remove){
		for(int i = 0; i < input.length; i++)
		{
			input[i] = input[i].replace(remove,"");
		}
	}

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