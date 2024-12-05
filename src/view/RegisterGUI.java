package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import helper.*;
import model.Hasta;
import model.user;

public class RegisterGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel w_pane;
	private JTextField fld_name;
	private JTextField fld_tcno;
	private JPasswordField fld_pass;
	private Hasta hasta=new Hasta();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI();
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
	public RegisterGUI() {
		setResizable(false);
		setTitle("Hastane Yönetim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 330);
		w_pane = new JPanel();
		w_pane.setBackground(new Color(255, 255, 255));
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Ad Soyad");
		lblNewLabel_1.setFont(new Font("Yu Gothic", Font.BOLD, 15));
		lblNewLabel_1.setBounds(10, 11, 150, 24);
		w_pane.add(lblNewLabel_1);
		
		fld_name = new JTextField();
		fld_name.setColumns(10);
		fld_name.setBounds(10, 33, 264, 24);
		w_pane.add(fld_name);
		
		JLabel lblNewLabel_1_1 = new JLabel("T.C. Numarası");
		lblNewLabel_1_1.setFont(new Font("Yu Gothic", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(10, 75, 150, 24);
		w_pane.add(lblNewLabel_1_1);
		
		fld_tcno = new JTextField();
		fld_tcno.setColumns(10);
		fld_tcno.setBounds(10, 97, 264, 24);
		w_pane.add(fld_tcno);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Şifre");
		lblNewLabel_1_1_1.setFont(new Font("Yu Gothic", Font.BOLD, 15));
		lblNewLabel_1_1_1.setBounds(10, 132, 150, 24);
		w_pane.add(lblNewLabel_1_1_1);
		
		fld_pass = new JPasswordField();
		fld_pass.setBounds(10, 156, 264, 24);
		w_pane.add(fld_pass);
		
		JButton btn_register = new JButton("Kayıt Ol");
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(fld_tcno.getText().length()==0||
						fld_pass.getText().length()==0||
						fld_name.getText().length()==0) {
					
					helper.showMsg("fill");
					
				}else {
					try {
						boolean control = hasta.register(fld_tcno.getText(),
								fld_pass.getText(), fld_name.getText());
						if(control) {
							helper.showMsg("success");
							LoginGUI login =new LoginGUI();
							login.setVisible(true);
							dispose();
						}else {
							helper.showMsg("error");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				
				
				
				
				
				
				
				
				
			}
		});
		btn_register.setForeground(Color.BLACK);
		btn_register.setFont(new Font("Arial", Font.PLAIN, 20));
		btn_register.setBackground(new Color(192, 192, 192));
		btn_register.setBounds(8, 203, 266, 33);
		w_pane.add(btn_register);
		
		JButton btn_back = new JButton("Geri Dön");
		btn_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				LoginGUI login =new LoginGUI();
				login.setVisible(true);
				dispose();
				
				
			}
		});
		btn_back.setForeground(Color.BLACK);
		btn_back.setFont(new Font("Arial", Font.PLAIN, 20));
		btn_back.setBackground(new Color(192, 192, 192));
		btn_back.setBounds(10, 247, 266, 33);
		w_pane.add(btn_back);
	}
}
