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

	waPanel weatherPanel;

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
		
		weatherPanel = new waPanel();
		weatherPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(weatherPanel);
		weatherPanel.setLayout(null);
		
	}
	


}
