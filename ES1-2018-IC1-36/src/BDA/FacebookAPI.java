package BDA;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.*;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.json.JsonArray;
import com.restfb.json.JsonObject;
import com.restfb.json.JsonValue;
import com.restfb.types.Post;

public class FacebookAPI {
	
	private int nregistos; 
	private JSONArray jarray = new JSONArray();

	private String acessToken = "EAAEuGhd0BZAsBAL8fFMIUZCIJTv1CvXldtP3Bb32RiCuxQDm6LxUzOmWDGLP1ZBE4xJqkXVlYcyPkrqMXMrb6khkgIRLUlBfAW17i5BHrI6C8ZB774zeYYlYXcg2oQKvpUQRskDMZAwrNYaC9EAeESd4Dhb3W8ogZD";
//	private String acessToken = "EAAChy5A0busBADGHfWe6CvCd3ZBWgecZAZBC48VwnsOc1ZCjwqCQoa3EyVvDmpOBIKuIbqEZAlivj3bu7vy3Ubnoku4lUtNuAVYvbk73x12GDZB0QlFYmif6xsJLpkMKVlx3a5xVixYTZCD4BguDby7fLyTgra6MEkZD";
	
	private InterfaceFacebook fb;
	public FacebookAPI() {
		
		connect();

	}
	
	public void startInterface(InterfaceGeral ig) {
		fb = new InterfaceFacebook(ig);
		
		readFile();
	}
	
	public void connect() {
		

		String SearchWord = "";

		FacebookClient client = new DefaultFacebookClient(acessToken);

		Connection<Post> result = client.fetchConnection("me/feed/",Post.class);
		System.out.println("passou1");

		JsonArray jarray = new JsonArray();


		int counter5 = 0;
		int counterTotal = 0;
		for (List<Post> page : result) {
			for (Post aPost : page) {
				// Filters only posts that contain the word "Inform"
				//if (aPost.getMessage() != null && aPost.getMessage().contains(seachWord)) {
				if (aPost.getMessage() != null) {
					JsonObject obj = new JsonObject();
					//obj.add("id", aPost.getId());
					obj.add("message", aPost.getMessage());
					obj.add("data", aPost.getCreatedTime().toString());
					jarray.add(obj);
					System.out.println(obj.toString());

					System.out.println("---- Post "+ counter5 + " ----");
					System.out.println("Id: "+"fb.com/"+aPost.getId());
					System.out.println("Message: "+aPost.getMessage());
					System.out.println("Created: "+aPost.getCreatedTime());
					counter5++;
				}
				counterTotal++;
			}
		}
		
		nregistos = counter5;
		savetofile(jarray);

		
	}

	public void savetofile(JsonArray jarray) {

		try {
			PrintWriter out = new PrintWriter("facebookdata.txt");

			for(JsonValue obj:jarray) {
				System.out.println("writing...");
				out.println(obj.toString());
				System.out.println(obj.toString());

			}
			out.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Done Save");
	}
	
	public void readFile() {
		BufferedReader br;

		jarray = new JSONArray();
		try {
			Scanner sc = new Scanner(new File("facebookdata.txt"));
			System.out.println("ola1");

			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				System.out.println("ola2");
				try {
					JSONObject obj = new JSONObject(line);
					System.out.println("A Linha é " + line);
					System.out.println("O objeto é " + obj);
					jarray.put(obj);
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				
			}	
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		fb.fillJlist(jarray);

	}

	public int getNregistos() {
		return nregistos;
	}
	
}