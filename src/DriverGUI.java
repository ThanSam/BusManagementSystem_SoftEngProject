import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DriverGUI extends JFrame {

	private JPanel panel;
	private ImageIcon icon;
	private JLabel img;
	private JButton ScheduleButton;
	private JButton InboxButton;
	private JButton EmergencyButton;
	private JButton LogOutButton;
	private Driver aDriver;
	private Secretariat sec;
	private int id;
	private JFrame mainMenuFrame;

	public DriverGUI(Driver aDriver, Secretariat sec) {
		this.aDriver = aDriver;
		this.sec = sec;
		panel = new JPanel();
		icon = new ImageIcon("p2.png");
		img = new JLabel(new ImageIcon("p7.png"));
		ScheduleButton = new JButton("Show Schedule");
		InboxButton = new JButton("Show Messages");
		EmergencyButton = new JButton("Emergency!");
		LogOutButton = new JButton("Logout");
		ScheduleButton.setFont(new Font("Show Schedule", Font.PLAIN, 20));
		InboxButton.setFont(new Font("Messages", Font.PLAIN, 20));
		EmergencyButton.setFont(new Font("Emergency", Font.PLAIN, 20));
		EmergencyButton.setForeground(Color.RED);
		LogOutButton.setFont(new Font("Logout", Font.PLAIN, 15));
		LogOutButton.setBackground(Color.LIGHT_GRAY);

		ButtonListener listener = new ButtonListener();
		ScheduleButton.addActionListener(listener);
		InboxButton.addActionListener(listener);
		EmergencyButton.addActionListener(listener);
		LogOutButton.addActionListener(listener);

		panel.setLayout(null);
		img.setBounds(35, 5, 200, 200);
		ScheduleButton.setBounds(45, 225, 185, 40);
		InboxButton.setBounds(45, 285, 185, 40);
		EmergencyButton.setBounds(45, 345, 185, 40);
		LogOutButton.setBounds(155, 420, 95, 25);

		panel.add(img);
		panel.add(ScheduleButton);
		panel.add(InboxButton);
		panel.add(EmergencyButton);
		panel.add(LogOutButton);

		this.setContentPane(panel);
		this.setIconImage(icon.getImage());
		this.setTitle("Driver Page");
		this.setSize(300, 500);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

	class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			if (e.getSource().equals(ScheduleButton)) {
				ScheduleGUI sch = new ScheduleGUI(sec, aDriver);
				dispose();
			}
			if (e.getSource().equals(InboxButton)) {
				MessagesDriverGUI mess = new MessagesDriverGUI(sec, aDriver);
				dispose();
			}
			if (e.getSource().equals(EmergencyButton)) {
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d/M/yyyy");
				DateTimeFormatter h = DateTimeFormatter.ofPattern("H");
				LocalDateTime now = LocalDateTime.now();
				LocalDateTime now3 = LocalDateTime.now();
				String hour = h.format(now3);
				String time = dtf.format(now);
					if (hour.equals("8")||hour.equals("9")||hour.equals("10")||hour.equals("11")||hour.equals("12")||hour.equals("13")||hour.equals("14")||hour.equals("15")||hour.equals("16")) {
						time = time + " " + "8:00-16:00";
						sec.addEmergncy(aDriver, time);
						  sec.setTime(time);
					} else if (hour.equals("17")||hour.equals("18")||hour.equals("19")||hour.equals("20")||hour.equals("21")||hour.equals("22")||hour.equals("23")||hour.equals("00")){
						time = time + " " + "16:00-00:00";
						sec.addEmergncy(aDriver, time);
					   sec.setTime(time);
					
					}
					
			
					
				}
			if (e.getSource().equals(LogOutButton)) {
				dispose();
			      new MainFrame(sec);
			}

		}
	}

}
