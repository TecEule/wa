package wa;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Vorschau extends JPanel {

	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	
	/**
	 * Create the panel.
	 */
	public Vorschau() {
		setLayout(new BorderLayout(0, 0));
		setPreferredSize(new Dimension(130, 90));
		setVisible(true);
		
		lblNewLabel = new JLabel("Wochentag", JLabel.CENTER);
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		add(lblNewLabel, BorderLayout.NORTH);
		
		lblNewLabel_2 = new JLabel("Temp", JLabel.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		add(lblNewLabel_2, BorderLayout.SOUTH);

		
		lblNewLabel_1 = new JLabel("Icons",JLabel.CENTER);
		add(lblNewLabel_1, BorderLayout.CENTER);
		
	}
	
	public void setVorschauTag(String wochentag)
	{
		lblNewLabel.setText(wochentag);
	}
	
	public void setVorschauIcon(Image img)
	{
		ImageIcon icon = new ImageIcon(img.getScaledInstance(55, 55, Image.SCALE_DEFAULT));
		lblNewLabel_1.setIcon(icon);		
		lblNewLabel_1.setText("");
	}
	
	public void setVorschauTemperature(double maxTemp, double minTemp)
	{
		lblNewLabel_2.setText("<html><body>Max. " + String.valueOf(maxTemp) + "°C<br>Min. "+ String.valueOf(minTemp)+ "°C</body></html>");
	}

}
