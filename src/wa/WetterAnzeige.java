//package wa;
//
//import java.awt.Image;
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.Reader;
//import java.net.URL;
//import java.nio.charset.Charset;
//
//import javax.imageio.ImageIO;
//import javax.swing.ImageIcon;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import ps.ParameterBase;
//
//public class WetterAnzeige {
//
//	private static String HereAppID = "";
//	private static String HereAppCode = "";
//
//	public static void main(String[] args) {
//
//		JSONObject json = new JSONObject();
//		try {
//
//			readHereAppKeys();
//
//			String weather = "";
//
////weather = "https://weather.cit.api.here.com/weather/1.0/report.json?product=observation&name=Chicago&app_id=DemoAppId01082013GAL&app_code=AJKnXv84fjrb0KIHawS0Tg"; //&pretty=true&accept=application/json";
////			weather = "https://weather.api.here.com/weather/1.0/report.json?product=forecast_7days_simple&name=Berlin&app_id=" + HereAppID +"&app_code=" + HereAppCode; // &pretty=true&accept=application/json";
//			weather = "https://weather.api.here.com/weather/1.0/report.json?product=observation&latitude=51.90693&longitude=8.37853&oneobservation=true&language=de&app_id="
//					+ HereAppID + "&app_code=" + HereAppCode;
//
//			json = JSONFrame.readJsonFromUrl(weather);
//
//		
//						
//			System.out.println("Ausgabe JSON Object");
//			System.out.println(json.toString());
//
//			System.out.println("Metric: " + json.getBoolean("metric"));
//			System.out.println("Datumsabfrage: " + json.getString("feedCreation"));
//
//			JSONObject observationObject = json.getJSONObject("observations");
//
//			JSONArray locationArray = observationObject.getJSONArray("location");
//
//			JSONObject tempObject = locationArray.getJSONObject(0);
//
//			System.out.println("Stadt: " + tempObject.getString("city"));
//			System.out.println("Land: " + tempObject.getString("country"));
//			System.out.println("Bundesland: " + tempObject.getString("state"));
//			System.out.println("Timezone: " + tempObject.getInt("timezone"));
//
//			JSONArray locationDetails = tempObject.getJSONArray("observation");
//			JSONObject locationObjectDetails = locationDetails.getJSONObject(0);
//			System.out.println("Beschreibung: " + locationObjectDetails.getString("description"));
//			System.out.println("Temperatur: " + locationObjectDetails.getDouble("temperature"));
//			System.out.println("Max./Min. Temperatur: " + locationObjectDetails.getDouble("highTemperature") + "/"
//					+ locationObjectDetails.getDouble("lowTemperature"));
//			System.out.println("Windrichtung: " + locationObjectDetails.getString("windDesc"));
//			String pictureUrl = locationObjectDetails.getString("iconLink");
//
//			System.out.println(pictureUrl);
//
//			Image img = null;
//			try {
//				URL url = new URL(pictureUrl);
//				img = ImageIO.read(url);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			
//			waGui weatherGui = new waGui();
//			
//			weatherGui.setCurrentTemperature(locationObjectDetails.getDouble("temperature"));
//			weatherGui.setMaxMinTemperature(locationObjectDetails.getDouble("highTemperature"), locationObjectDetails.getDouble("lowTemperature"));
//			weatherGui.setWeatherDescription(locationObjectDetails.getString("description"));
//			weatherGui.setWeatherIcon(img);
//		
//		    weatherGui.setVisible(true);
//			
//		
//
////			JFrame jf = new JFrame();
////
////			jf.setSize(600, 600);
////			JLabel jl = new JLabel(new ImageIcon(img));
////			jf.add(jl);
//////			jf.getContentPane().add(jl); // new Bilder(pictureUrl));
////
////			jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
////			// jf.pack();
////			jf.setVisible(true);
//
//			json = null;
//
//		} catch (JSONException | IOException e) {
//
//			e.printStackTrace();
//		}
//
//	}
//
//	private static void readHereAppKeys() {
//		System.out.println(ParameterBase.getXmlPath());
//
//		HereAppID = ParameterBase.getParameterValue("DeveloperKey", "HereAppID");
//		HereAppCode = ParameterBase.getParameterValue("DeveloperKey", "HereAppCode");
//
//	}
//
//}
