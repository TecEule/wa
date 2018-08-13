package wa;


import java.awt.EventQueue;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ps.ParameterBase;


public class waFrameGui extends JFrame {

	waGui weatherPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					waFrameGui frame = new waFrameGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public waFrameGui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 597, 446);
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		setContentPane(contentPane);
//		contentPane.setLayout(null);
		
		weatherPanel = new waGui();
		weatherPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(weatherPanel);
		weatherPanel.setLayout(null);
		
		
		initwaPanel();
	}
	
	private static String HereAppID = "";
	private static String HereAppCode = "";
	
	private static void readHereAppKeys() {
		System.out.println(ParameterBase.getXmlPath());

		HereAppID = ParameterBase.getParameterValue("DeveloperKey", "HereAppID");
		HereAppCode = ParameterBase.getParameterValue("DeveloperKey", "HereAppCode");

	}
	
	
	
	private void initwaPanel()
	{
		
		JSONObject json = new JSONObject();
		try {

			readHereAppKeys();

			String weather = "";

//weather = "https://weather.cit.api.here.com/weather/1.0/report.json?product=observation&name=Chicago&app_id=DemoAppId01082013GAL&app_code=AJKnXv84fjrb0KIHawS0Tg"; //&pretty=true&accept=application/json";
//			weather = "https://weather.api.here.com/weather/1.0/report.json?product=forecast_7days_simple&name=Berlin&app_id=" + HereAppID +"&app_code=" + HereAppCode; // &pretty=true&accept=application/json";
			weather = "https://weather.api.here.com/weather/1.0/report.json?product=observation&latitude=51.90693&longitude=8.37853&oneobservation=true&language=de&app_id="
					+ HereAppID + "&app_code=" + HereAppCode;

			json = JSONFrame.readJsonFromUrl(weather);
			JSONObject observationObject = json.getJSONObject("observations");
			JSONArray locationArray = observationObject.getJSONArray("location");
			JSONObject tempObject = locationArray.getJSONObject(0);

			System.out.println("Stadt: " + tempObject.getString("city"));
			System.out.println("Land: " + tempObject.getString("country"));
			System.out.println("Bundesland: " + tempObject.getString("state"));
			System.out.println("Timezone: " + tempObject.getInt("timezone"));

			JSONArray locationDetails = tempObject.getJSONArray("observation");
			JSONObject locationObjectDetails = locationDetails.getJSONObject(0);
			System.out.println("Beschreibung: " + locationObjectDetails.getString("description"));
			System.out.println("Temperatur: " + locationObjectDetails.getDouble("temperature"));
			System.out.println("Max./Min. Temperatur: " + locationObjectDetails.getDouble("highTemperature") + "/"
					+ locationObjectDetails.getDouble("lowTemperature"));
			System.out.println("Windrichtung: " + locationObjectDetails.getString("windDesc"));
			String pictureUrl = locationObjectDetails.getString("iconLink");

			System.out.println(pictureUrl);

			Image img = null;
			try {
				URL url = new URL(pictureUrl);
				img = ImageIO.read(url);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			weatherPanel.setCurrentTemperature(locationObjectDetails.getDouble("temperature"));
			weatherPanel.setMaxMinTemperature(locationObjectDetails.getDouble("highTemperature"), locationObjectDetails.getDouble("lowTemperature"));
			weatherPanel.setWeatherDescription(locationObjectDetails.getString("temperatureDesc"),locationObjectDetails.getString("skyDescription"),locationObjectDetails.getString("precipitationDesc"));
			weatherPanel.setWeatherIcon(img);
		    		    
			json = null;
			
			weather = "https://weather.api.here.com/weather/1.0/report.json?product=forecast_7days_simple&name=Guetersloh&app_id="+ HereAppID + "&app_code=" + HereAppCode;
			json = JSONFrame.readJsonFromUrl(weather);
			
			weatherPanel.setVorschau(json);
						
			json = null;

		} catch (JSONException | IOException e) {

			e.printStackTrace();
		}

		
	}
}
