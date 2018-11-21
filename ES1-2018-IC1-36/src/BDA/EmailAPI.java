package BDA;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.Scanner;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.swing.JOptionPane;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.restfb.json.JsonArray;
import com.restfb.json.JsonObject;
import com.restfb.json.JsonValue;

public class EmailAPI {

	private static final int dayInMilis = 86400000;

	private Properties props;
	private Store store;

	private int nregistos; 
	private JSONArray jarray = new JSONArray();

	private String mail = "";
	private String pass = "";


	private InterfaceEmail IE;

	public EmailAPI() {
		//		mail = JOptionPane.showInputDialog("mail");
		//		pass = JOptionPane.showInputDialog("pass");
		props = System.getProperties();
		props.setProperty("mail.store.protocol", "imaps");

		iniSessao(mail, pass);
		caixaChegada();
		//caixaTemp(0);
	}

	public void iniSessao(String mail, String pass) {
		try {
			Session session = Session.getDefaultInstance(props, null);
			store = session.getStore("imaps");
			store.connect("smtp-mail.outlook.com", mail, pass);
			//System.out.println(store);
		} catch (Exception e) {
			System.out.println("Erro" + e);
		}
	}

	public void startInterface(InterfaceGeral ig) {
		IE = new InterfaceEmail(ig);

		readFile();
	}
	public void caixaTemp(int dias) {
		try {
			Folder inbox = store.getFolder("Inbox");
			inbox.open(Folder.READ_ONLY);
			Message messages[] = inbox.getMessages();
			System.out.println(messages.length);
			JsonArray jarray = new JsonArray();

			for(Message message : messages) { 
				if(message.getReceivedDate().getTime() > System.currentTimeMillis()-(dias*dayInMilis)) {
					JsonObject obj = new JsonObject();
					//obj.add("id", aPost.getId());
					obj.add("message", message.getSubject());
					obj.add("data", message.getReceivedDate().toString());
					jarray.add(obj);

					System.out.println(message);
					System.out.println(message.getSubject());
					System.out.println(message.getReceivedDate());
				}
			}

			savetofile(jarray);

		} catch (Exception e) {
			System.out.println("Erro2");
		}
	}

	public void savetofile(JsonArray jarray) {

		try {
			PrintWriter out = new PrintWriter("Emaildata.txt");

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
			Scanner sc = new Scanner(new File("Emaildata.txt"));
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

		IE.fillJlist(jarray);

	}


	public void caixaChegada() {
		try {
			Folder inbox = store.getFolder("Inbox");
			inbox.open(Folder.READ_ONLY);
			Message messages[] = inbox.getMessages();
			System.out.println(messages.length);
			System.out.println(messages.length);
			JsonArray jarray = new JsonArray();

			for(Message message : messages) { 
				JsonObject obj = new JsonObject();
				//obj.add("id", aPost.getId());
				obj.add("message", message.getSubject());
				obj.add("data", message.getReceivedDate().toString());
				jarray.add(obj);

				System.out.println(message);
				System.out.println(message.getSubject());
				System.out.println(message.getReceivedDate());

			}

			savetofile(jarray);


		} catch (Exception e) {
			System.out.println("Erro3"  + e);
		}
	}
}