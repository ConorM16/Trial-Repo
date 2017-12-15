package com.mkyong;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;

import javax.net.ssl.HttpsURLConnection;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
public class SocGraph /*HTTP Connection*/{

	private final String USER_AGENT = "Mozilla/5.0";
	private static int publics = 0;		//amount of public repositories calculated
	public static void main(String[] args) throws Exception {
		SocGraph http = new SocGraph();
		
		//FileWriter fw = new FileWriter("SocGraph1.csv",true);
		//BufferedWriter bw = new BufferedWriter(fw);
		//PrintWriter pw = new PrintWriter(bw);
		PrintWriter pw = null;
		try {
		    pw = new PrintWriter(new File("NewData.csv"));
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		}
		StringBuilder builder = new StringBuilder();
		String columnNames = "Username, Repos";
		builder.append(columnNames + "\n");
		
		System.out.println("Testing 1 - Send Http GET request for all Github Users");
		//http.sendGetUser();
		http.sendGetDefault(builder);
		System.out.println("Total public repos: " + publics);
		//http.sendGetSpecified("ConorM16");
		//http.queryRate();
		//http.queryRate();
		writeCSV(builder,pw);

	}
	
	public static void writeCSV(StringBuilder builder, PrintWriter pw){
		pw.write(builder.toString());
		pw.flush();
		pw.close();
//		try 
//		{
//		    FileWriter fw = new FileWriter("SocGraph1.csv",true);
//		    BufferedWriter bw = new BufferedWriter(fw);
//		    PrintWriter pw = new PrintWriter(bw);
//		    pw.append(username + "," + repos);
//		    pw.flush();
//		    pw.close();
//		    System.out.println("Data written");
//		} catch (FileNotFoundException e) {
//		    e.printStackTrace();
//		} catch (IOException e) {
//			System.out.println("INFORMATION NOT SAVED");
//			e.printStackTrace();
//		}
		
		//StringBuilder builder = new StringBuilder();
		//String ColumnNamesList = "Username,Repos";
//		builder.append(ColumnNamesList +"\n");
//		builder.append("Steven" + ",");
//		builder.append("\n");
//		builder.append("2");
	}
	
	// getting query rate
	private void queryRate() throws Exception {
		String url = "https://api.github.com/rate_limit?access_token= "; 
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
	private void sendGetDefault(StringBuilder builder) throws Exception {

		String url = "https://api.github.com/users?access_token=7af0bb592a832b5ca10e390576a069b6dd03394c";
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
		printRepos(builder,usernames);
	}
	
	/**
	 * 	
	 * @param username - github account we want to access
	 * @param info - piece of info we want to extract from account
	 * @throws Exception
	 */
	private void sendGetSpecified(StringBuilder builder,String username) throws Exception {

			String url = "https://api.github.com/users/" + username + "?access_token=7af0bb592a832b5ca10e390576a069b6dd03394c";
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
			publicRepos(builder,splitInput, username);
		}
		
	private void publicRepos(StringBuilder builder,String [] input, String username){
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
		builder.append(username + "," + pubRepos + "\n");
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
	
	private void printRepos(StringBuilder builder,String [] users) throws Exception {
		SocGraph http = new SocGraph();
		int i;
		for(i = 0; i< users.length; i++)
		{
			http.sendGetSpecified(builder,users[i]);
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