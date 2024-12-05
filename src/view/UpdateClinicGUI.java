package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import helper.helper;
import model.Clinic;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class UpdateClinicGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField fld_clinicName;
	private static Clinic clinic;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateClinicGUI frame = new UpdateClinicGUI(clinic);
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
	public UpdateClinicGUI(Clinic clinic) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 225, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		fld_clinicName = new JTextField();
		fld_clinicName.setColumns(10);
		fld_clinicName.setBounds(10, 33, 189, 24);
		fld_clinicName.setText(clinic.getName());
		contentPane.add(fld_clinicName);
		
		JLabel lblNewLabel_1_4 = new JLabel("Poliklinik Adı");
		lblNewLabel_1_4.setFont(new Font("Yu Gothic", Font.BOLD, 15));
		lblNewLabel_1_4.setBounds(10, 11, 150, 24);
		contentPane.add(lblNewLabel_1_4);
		
		JButton btn_updateClinic = new JButton("Düzenle");
		btn_updateClinic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(helper.confirm("sure")) {
					try {
						clinic.updateClinic(clinic.getId(),fld_clinicName.getText());
						helper.showMsg("success");
						dispose();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				
			}
		});
		btn_updateClinic.setForeground(Color.BLACK);
		btn_updateClinic.setFont(new Font("Arial", Font.BOLD, 20));
		btn_updateClinic.setBackground(UIManager.getColor("Button.darkShadow"));
		btn_updateClinic.setBounds(8, 67, 191, 33);
		contentPane.add(btn_updateClinic);
	}
}
