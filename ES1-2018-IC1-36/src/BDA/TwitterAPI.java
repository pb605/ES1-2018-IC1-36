package BDA;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.ObjectInputFilter.Status;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.restfb.json.JsonArray;
import com.restfb.json.JsonValue;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;


public class TwitterApi {
	
	private InterfaceTwitter it;
	private int Nregistos;
	
	public TwitterApi() {
		
		connect();
	}
	
	public void startInterface(InterfaceGeral ig) {
		 it = new InterfaceTwitter(ig);
		
		readFile();
	}
	
	
	public void connect() {
		JSONArray jarray = new JSONArray();
		try {
        	ConfigurationBuilder cb = new ConfigurationBuilder();
        	cb.setDebugEnabled(true)
        	  .setOAuthConsumerKey("W1f0VvgWPfT8OBqVxvy4Mw")
        	  .setOAuthConsumerSecret("zKH2yAtRyefwsgOO8h8Szc4kru68iEm95QmIG7svw")
        	  .setOAuthAccessToken("36481851-VhzByC4f9MSsZES1QZQ4e4iBvA9bWGLyv9HKFpy7c")
        	  .setOAuthAccessTokenSecret("OahDuXF2Lhl5xlNYALhYZir6xSflAxKP9Zh89T05po");
        	TwitterFactory tf = new TwitterFactory(cb.build());
        	Twitter twitter = tf.getInstance();        		
            List<twitter4j.Status> statuses = twitter.getHomeTimeline();
            System.out.println("------------------------\n Showing home timeline \n------------------------");
    		int counter=0;
    		int counterTotal = 0;
    		
            for (twitter4j.Status status : statuses) {
				// Filters only tweets from user "catarina"
				//if (status.getUser().getName() != null && status.getUser().getName().contains("catarina")) {
					System.out.println(status.getUser().getName() + ":" + status.getText());
					counter++;
					JSONObject obj = new JSONObject();
					obj.put("name", status.getUser().getName());
					obj.put("texto", status.getText());
					jarray.put(obj);
					
				//}
				counterTotal++;
            }
            
            Nregistos = counterTotal;
    		System.out.println("-------------\nNº of Results: " + counter+"/"+counterTotal);
    		savetofile(jarray);
        } catch (Exception e) { System.out.println(e.getMessage()); }
		
		
	}
	
	public void savetofile(JSONArray jarray) {

		try {
			PrintWriter out = new PrintWriter("Twitter.txt");

			for(int i = 0 ; i<jarray.length(); i++) {
				System.out.println("writing...");
				out.println(jarray.get(i).toString());
				System.out.println(jarray.get(i).toString());

			}
			out.close();

		} catch (FileNotFoundException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Done Save");


	}

	public void readFile() {
		BufferedReader br;

		JSONArray jarray = new JSONArray();
		try {
			Scanner sc = new Scanner(new File("Twitter.txt"));
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
		
		
		it.fillJlist(jarray);

	}
	
	public int getNregistos() {
		return Nregistos;
	}
	

}