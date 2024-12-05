package view;
import java.awt.EventQueue;
import javax.swing.ImageIcon;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

import helper.*;
import model.Bashekim;
import model.Doctor;
import model.Hasta;


public class LoginGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel w_pane;
	private JTextField fld_hastaTc;
	private JTextField fld_hastaPass;
	private JTextField fld_doctorTc;
	private JTextField fld_doctorPass;
	
	private SQLConnection conn= new SQLConnection();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
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
	public LoginGUI() {
		setResizable(false);
		setTitle("Hastane Yönetim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		w_pane = new JPanel();
		w_pane.setBackground(new Color(255, 255, 255));
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lbl_logo = new JLabel(new ImageIcon("C:\\Users\\tolga\\Desktop\\hastane.png"));
		lbl_logo.setBounds(184, 11, 106, 90);
		w_pane.add(lbl_logo);
		
		JLabel lblNewLabel = new JLabel("Hastane Yönetim Sistemine Hoşgeldiniz");
		lblNewLabel.setFont(new Font("Yu Gothic", Font.BOLD, 20));
		lblNewLabel.setBounds(42, 104, 396, 41);
		w_pane.add(lblNewLabel);
		
		JTabbedPane w_tabpane = new JTabbedPane(JTabbedPane.TOP);
		w_tabpane.setBounds(10, 135, 464, 215);
		w_pane.add(w_tabpane);
		
		JPanel w_hastaLogin = new JPanel();
		w_hastaLogin.setBackground(new Color(255, 255, 255));
		w_tabpane.addTab("Hasta Girişi", null, w_hastaLogin, null);
		w_hastaLogin.setLayout(null);
		
		JLabel lblTcNumaranz = new JLabel("T.C Numaranız:");
		lblTcNumaranz.setFont(new Font("Yu Gothic", Font.BOLD, 20));
		lblTcNumaranz.setBounds(11, 18, 164, 41);
		w_hastaLogin.add(lblTcNumaranz);
		
		JLabel lblifre = new JLabel("Şifre:");
		lblifre.setFont(new Font("Yu Gothic", Font.BOLD, 20));
		lblifre.setBounds(11, 70, 164, 41);
		w_hastaLogin.add(lblifre);
		
		fld_hastaTc = new JTextField();
		fld_hastaTc.setFont(new Font("Yu Gothic Light", Font.BOLD, 16));
		fld_hastaTc.setBounds(185, 24, 206, 35);
		w_hastaLogin.add(fld_hastaTc);
		fld_hastaTc.setColumns(10);
		
		fld_hastaPass = new JTextField();
		fld_hastaPass.setFont(new Font("Yu Gothic Light", Font.BOLD, 16));
		fld_hastaPass.setColumns(10);
		fld_hastaPass.setBounds(185, 76, 206, 35);
		w_hastaLogin.add(fld_hastaPass);
		
		JButton btn_register = new JButton("Kayıt Ol");
		btn_register.setFont(new Font("Arial", Font.PLAIN, 20));
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				RegisterGUI rGUI =new RegisterGUI();
				rGUI.setVisible(true);
				dispose();
				
				
				
			}
		});
		btn_register.setBounds(21, 135, 164, 41);
		w_hastaLogin.add(btn_register);
		
		JButton btn_HastaLogin = new JButton("Giriş Yap");
		btn_HastaLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if(fld_hastaTc.getText().length()==0||fld_hastaPass.getText().length()==0) {
					helper.showMsg("fill");
					
				}else {
					boolean key =true;
					try {
						Connection con = conn.connSQL();
						Statement st =con.createStatement();
						ResultSet rs =st.executeQuery("SELECT * FROM user");
						
						
						
						while(rs.next()) {
							
							if(fld_hastaTc.getText().equals(rs.getString("tcno"))
									&&fld_hastaPass.getText().equals(rs.getString("password"))) {
								if(rs.getString("type").equals("hasta")) {
									Hasta hasta =new Hasta();
									hasta.setId(rs.getInt("id"));
									hasta.setPassword("password");
									hasta.setTcno(rs.getString("tcno"));
									hasta.setName(rs.getString("name"));
									hasta.setType(rs.getString("type"));
									
									
									//bashekimGUI ekranına geçirir
									HastaGUI hGUI= new HastaGUI(hasta);
									hGUI.setVisible(true);
									dispose();
									key=false;
									
								}
						
							}			
						}
			
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					if(key) 
						helper.showMsg("böyle bir hasta bulunamadı lütfen kayıt olunuz!");
					
					}
				}
		});
		btn_HastaLogin.setFont(new Font("Arial", Font.PLAIN, 20));
		btn_HastaLogin.setBounds(227, 135, 164, 41);
		w_hastaLogin.add(btn_HastaLogin);
		
		JPanel w_doctorLogin = new JPanel();
		w_doctorLogin.setBackground(new Color(255, 255, 255));
		w_tabpane.addTab("Doktor Girişi", null, w_doctorLogin, null);
		w_doctorLogin.setLayout(null);
		
		JLabel lblTcNumaranz_1 = new JLabel("T.C Numaranız:");
		lblTcNumaranz_1.setFont(new Font("Yu Gothic", Font.BOLD, 20));
		lblTcNumaranz_1.setBounds(10, 18, 164, 41);
		w_doctorLogin.add(lblTcNumaranz_1);
		
		fld_doctorTc = new JTextField();
		fld_doctorTc.setFont(new Font("Yu Gothic Light", Font.BOLD, 16));
		fld_doctorTc.setColumns(10);
		fld_doctorTc.setBounds(184, 24, 206, 35);
		w_doctorLogin.add(fld_doctorTc);
		
		fld_doctorPass = new JTextField();
		fld_doctorPass.setFont(new Font("Yu Gothic Light", Font.BOLD, 16));
		fld_doctorPass.setColumns(10);
		fld_doctorPass.setBounds(184, 76, 206, 35);
		w_doctorLogin.add(fld_doctorPass);
		
		JLabel lblifre_1 = new JLabel("Şifre:");
		lblifre_1.setFont(new Font("Yu Gothic", Font.BOLD, 20));
		lblifre_1.setBounds(10, 70, 164, 41);
		w_doctorLogin.add(lblifre_1);
		
		JButton btn_DoctorLogin = new JButton("Giriş Yap");
		btn_DoctorLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//kullanıcı alanı doldurmadıysa
				if(fld_doctorTc.getText().length()==0||fld_doctorPass.getText().length()==0) {
				
					helper.showMsg("fill");  //hata mesajı helper sınıfından geliyor.
				 
					
					//eğer içi doluysa ssql de varmı yokmu kontrol
				}else {
					
					try {
						Connection con = conn.connSQL();
						Statement st =con.createStatement();
						ResultSet rs =st.executeQuery("SELECT * FROM user");
						while(rs.next()) {
							if(fld_doctorTc.getText().equals(rs.getString("tcno"))
									&&fld_doctorPass.getText().equals(rs.getString("password"))) {
								
								if(rs.getString("type").equals("bashekim")) {
									Bashekim bhekim =new Bashekim();
									bhekim.setId(rs.getInt("id"));
									bhekim.setPassword("password");
									bhekim.setTcno(rs.getString("tcno"));
									bhekim.setName(rs.getString("name"));
									bhekim.setType(rs.getString("type"));
									
									
									//bashekimGUI ekranına geçirir
									BashekimGUI bGUI= new BashekimGUI(bhekim);
									bGUI.setVisible(true);
									dispose();
									
								}
								if(rs.getString("type").equals("doktor")) {
									Doctor doctor =new Doctor();
									doctor.setId(rs.getInt("id"));
									doctor.setPassword("password");
									doctor.setTcno(rs.getString("tcno"));
									doctor.setName(rs.getString("name"));
									doctor.setType(rs.getString("type"));
									DoctorGUI dGUI =new DoctorGUI(doctor);
									dGUI.setVisible(true);
									dispose();
									
								}
								

								
							}
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				
				
			}
		});
		btn_DoctorLogin.setFont(new Font("Arial", Font.PLAIN, 20));
		btn_DoctorLogin.setBounds(10, 135, 380, 41);
		w_doctorLogin.add(btn_DoctorLogin);
		
		
	}
}
