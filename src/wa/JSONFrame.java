package wa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONFrame {

	private static String readAll(Reader rd) throws IOException {
//		StringBuilder sb = new StringBuilder();
//		int cp;
//		while ((cp = rd.read()) != -1) {
//			sb.append((char) cp);
//		}
//		return sb.toString();
		
		final StringBuilder sb = new StringBuilder();
		int counter;
		
		while((counter = rd.read()) != -1)
		{
			sb.append((char) counter);
		}
		return sb.toString();
		
		
	}

	public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {

		StringBuilder content = new StringBuilder();
		String inputline;

		JSONObject json = null;
		URL _url = new URL(url);
		HttpURLConnection con = (HttpURLConnection) _url.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("Content-Type", "application/json");
		con.setUseCaches(false);
		con.setDoOutput(true);

		if (con.getResponseCode() == 200) {
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), Charset.forName("UTF-8")));
			while ((inputline = in.readLine()) != null) {
				content.append(inputline);
			}

			in.close();
		}

//		System.out.println("Ausgabe Content");
//		System.out.println(content.toString());

		json = new JSONObject(content.toString());
//		System.out.println("Ausgabe json");
//		System.out.println(json.toString());

//		  	InputStream is = new URL(url).openStream();
//		    try {
//		      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
//		      String jsonText = readAll(rd);
//		      JSONObject jsontmp = new JSONObject(jsonText);
//		      System.out.println("Ausgabe json tmp");
//		      System.out.println(jsontmp.toString());
//				
//		    } finally {
//		      is.close();
//		    }

		return json;
	}

}
