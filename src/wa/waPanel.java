package wa;

import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.swing.SwingConstants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ps.ParameterBase;

import java.awt.BorderLayout;
import java.awt.Color;


public class waPanel extends JPanel {

	
	JLabel lblTemperatur;
	JLabel lblIcon;
	JLabel lblWeatherDescription;
	JLabel lblMaxmintemp;
	JLabel lblWeatherskydescription;
	private JLabel lblWeatherskydescription_1;
	
	Vorschau vorschau1;
	Vorschau vorschau2;
	Vorschau vorschau3;
	Vorschau vorschau4;
	Vorschau vorschau5;
	Vorschau vorschau6;
	
	private static int panelBreite, panelHoehe;
	
	/**
	 * Create the panel.
	 */
	public waPanel() {
		setBackground(Color.BLACK);
		setLayout(null);
		setPreferredSize(new Dimension(500,300));	
		setVisible(true);
		
	
		lblIcon = new JLabel("WeatherIcon");
		lblIcon.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIcon.setBounds(10, 11, 65, 65);
		add(lblIcon);
		
		lblTemperatur = new JLabel("18 C");
		lblTemperatur.setForeground(Color.WHITE);
		lblTemperatur.setFont(new Font("Arial", Font.BOLD, 48));
		lblTemperatur.setBounds(85, 11, 173, 65);
		add(lblTemperatur);
		
		lblWeatherDescription = new JLabel("Weather description");
		lblWeatherDescription.setForeground(Color.WHITE);
		lblWeatherDescription.setFont(new Font("Arial", Font.BOLD, 20));
		lblWeatherDescription.setBounds(85, 76, 101, 36);
		add(lblWeatherDescription);
		
		lblMaxmintemp = new JLabel("Max_Min_Temp");
		lblMaxmintemp.setForeground(Color.WHITE);
		lblMaxmintemp.setFont(new Font("Arial", Font.BOLD, 12));
		lblMaxmintemp.setBounds(268, 30, 144, 29);
		add(lblMaxmintemp);
		
		lblWeatherskydescription = new JLabel("WeatherSkydescription");
		lblWeatherskydescription.setForeground(Color.WHITE);
		lblWeatherskydescription.setFont(new Font("Arial", Font.BOLD, 20));
		lblWeatherskydescription.setBounds(10, 134, 236, 36);
		add(lblWeatherskydescription);
		
		lblWeatherskydescription_1 = new JLabel("WeatherSkydescription2");
		lblWeatherskydescription_1.setForeground(Color.WHITE);
		lblWeatherskydescription_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblWeatherskydescription_1.setBounds(10, 157, 236, 36);
		add(lblWeatherskydescription_1);
		
//		panel = new JPanel();
//		panel.setBounds(10, 204, 72, 85);
//		add(panel);
//		panel.setLayout(new BorderLayout(0, 0));

		vorschau1 = new Vorschau();
		vorschau1.setBounds(10, 204, 72, 85);
		add(vorschau1);
				
		vorschau2 = new Vorschau();
		vorschau2.setBounds(87, 204, 72, 85);
		add(vorschau2);
		
		vorschau3 = new Vorschau();
		vorschau3.setBounds(164, 204, 72, 85);
		add(vorschau3);
		
		vorschau4 = new Vorschau();
		vorschau4.setBounds(241, 204, 72, 85);
		add(vorschau4);
		
		vorschau5 = new Vorschau();
		vorschau5.setBounds(318, 204, 72, 85);
		add(vorschau5);
		
		vorschau6 = new Vorschau();
		vorschau6.setBounds(395, 204, 72, 85);
		add(vorschau6);
		
		initwaPanel();
		
	}
	
	private static String HereAppID = "";
	private static String HereAppCode = "";
	
	private static void readHereAppKeys() {
		HereAppID = ParameterBase.getParameterValue("DeveloperKey", "HereAppID");
		HereAppCode = ParameterBase.getParameterValue("DeveloperKey", "HereAppCode");

	}
	
	public int getBreite()
	{
		return this.getWidth();
	}
	
	public int getHoehe()
	{
		return this.getHeight();
	}
	
	private void initwaPanel()
	{
		
		JSONObject json = new JSONObject();
		try {

			readHereAppKeys();

			String weather = "";

			weather = "https://weather.api.here.com/weather/1.0/report.json?product=observation&latitude=51.90693&longitude=8.37853&oneobservation=true&language=de&app_id="
					+ HereAppID + "&app_code=" + HereAppCode;

			json = JSONFrame.readJsonFromUrl(weather);
			JSONObject observationObject = json.getJSONObject("observations");
			JSONArray locationArray = observationObject.getJSONArray("location");
			JSONObject tempObject = locationArray.getJSONObject(0);

			JSONArray locationDetails = tempObject.getJSONArray("observation");
			JSONObject locationObjectDetails = locationDetails.getJSONObject(0);
			String pictureUrl = locationObjectDetails.getString("iconLink");
			
			
			Image img = null;
			try {
				URL url = new URL(pictureUrl);
				img = ImageIO.read(url);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			setCurrentTemperature(locationObjectDetails.getDouble("temperature"));
			setMaxMinTemperature(locationObjectDetails.getDouble("highTemperature"), locationObjectDetails.getDouble("lowTemperature"));
			setWeatherDescription(locationObjectDetails.getString("temperatureDesc"),locationObjectDetails.getString("skyDescription"),locationObjectDetails.getString("precipitationDesc"));
			setWeatherIcon(img);
		    		    
			json = null;
			
			weather = "https://weather.api.here.com/weather/1.0/report.json?product=forecast_7days_simple&name=Guetersloh&app_id="+ HereAppID + "&app_code=" + HereAppCode;
			json = JSONFrame.readJsonFromUrl(weather);
			
			setVorschau(json);
						
			json = null;

		} catch (JSONException | IOException e) {

			e.printStackTrace();
		}

		
	}

	
	
	
	public void setVorschauTag(Vorschau vorschauPanel,String wochentag)
	{		
		vorschauPanel.setVorschauTag(wochentag);
	}
	
	public void setVorschauIcon(Vorschau vorschauPanel,Image img)
	{
		vorschauPanel.setVorschauIcon(img);
	}
	
	public void setVorschauTemperature(Vorschau vorschauPanel, double maxTemp, double minTemp)
	{
		vorschauPanel.setVorschauTemperature(maxTemp, minTemp);
	}
	
	
	public void setVorschau(JSONObject json)
	{
		if (json != null)
		{
			JSONObject dailyForcast = json.getJSONObject("dailyForecasts");
			JSONObject forecastLocation = dailyForcast.getJSONObject("forecastLocation");
			JSONArray forecastWeek = forecastLocation.getJSONArray("forecast");
			
			for(int indexWeek = 0; indexWeek<forecastWeek.length(); indexWeek++)
			{
				JSONObject forecast = forecastWeek.getJSONObject(indexWeek);			
				if (indexWeek == 0)
					setVorschau1(forecast);
				else if (indexWeek == 1)
					setVorschau2(forecast);
				else if (indexWeek == 2)
					setVorschau3(forecast);
				else if (indexWeek == 3)
					setVorschau4(forecast);
				else if (indexWeek == 4)
					setVorschau5(forecast);
				else if (indexWeek == 5)
					setVorschau6(forecast);
			}
			
			
		}
	}
	
	private void setVorschau1(JSONObject forecast)
	{
		String pictureUrl = forecast.getString("iconLink");
		
		Image img = null;
		try {
			URL url = new URL(pictureUrl);
			img = ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setVorschauTag(vorschau1, forecast.getString("weekday"));
		setVorschauTemperature(vorschau1,forecast.getDouble("highTemperature"), forecast.getDouble("lowTemperature"));
		setVorschauIcon(vorschau1,img);	
	}
	
	private void setVorschau2(JSONObject forecast)
	{
		String pictureUrl = forecast.getString("iconLink");
		
		Image img = null;
		try {
			URL url = new URL(pictureUrl);
			img = ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setVorschauTag(vorschau2, forecast.getString("weekday"));
		setVorschauTemperature(vorschau2,forecast.getDouble("highTemperature"), forecast.getDouble("lowTemperature"));
		setVorschauIcon(vorschau2,img);	
	}
	
	private void setVorschau3(JSONObject forecast)
	{
		String pictureUrl = forecast.getString("iconLink");
		
		Image img = null;
		try {
			URL url = new URL(pictureUrl);
			img = ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setVorschauTag(vorschau3, forecast.getString("weekday"));
		setVorschauTemperature(vorschau3,forecast.getDouble("highTemperature"), forecast.getDouble("lowTemperature"));
		setVorschauIcon(vorschau3,img);	
	}
	
	private void setVorschau4(JSONObject forecast)
	{
		String pictureUrl = forecast.getString("iconLink");
		
		Image img = null;
		try {
			URL url = new URL(pictureUrl);
			img = ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setVorschauTag(vorschau4, forecast.getString("weekday"));
		setVorschauTemperature(vorschau4,forecast.getDouble("highTemperature"), forecast.getDouble("lowTemperature"));
		setVorschauIcon(vorschau4,img);	
	}
	
	private void setVorschau5(JSONObject forecast)
	{
		String pictureUrl = forecast.getString("iconLink");
		
		Image img = null;
		try {
			URL url = new URL(pictureUrl);
			img = ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setVorschauTag(vorschau5, forecast.getString("weekday"));
		setVorschauTemperature(vorschau5,forecast.getDouble("highTemperature"), forecast.getDouble("lowTemperature"));
		setVorschauIcon(vorschau5,img);	
	}
	
	private void setVorschau6(JSONObject forecast)
	{
		String pictureUrl = forecast.getString("iconLink");
		
		Image img = null;
		try {
			URL url = new URL(pictureUrl);
			img = ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setVorschauTag(vorschau6, forecast.getString("weekday"));
		setVorschauTemperature(vorschau6,forecast.getDouble("highTemperature"), forecast.getDouble("lowTemperature"));
		setVorschauIcon(vorschau6,img);	
	}
	
	public void setCurrentTemperature(double currentTemperatur)
	{
		lblTemperatur.setText(String.valueOf(currentTemperatur) + "°C");
	}
	
	public void setMaxMinTemperature(double maxTemperature, double minTemperature)
	{
		lblMaxmintemp.setText("<html><body>" + String.valueOf(maxTemperature) + "°C<br>"+ String.valueOf(minTemperature)+ "°C</body></html>");
	}
	
	public void setWeatherIcon(Image weatherIcon)
	{
		ImageIcon icon = new ImageIcon(weatherIcon.getScaledInstance(lblIcon.getWidth(), lblIcon.getHeight(), Image.SCALE_DEFAULT));
		lblIcon.setIcon(icon);		
		lblIcon.setText("");
	}
	
	public void setWeatherDescription(String weatherDescription, String skyDescription, String desc2)
	{
		lblWeatherDescription.setText(weatherDescription);		
		lblWeatherskydescription.setText(skyDescription);
		lblWeatherskydescription_1.setText(desc2);
	}
}
