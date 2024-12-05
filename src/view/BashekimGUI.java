package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import model.*;
import model.Clinic;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.awt.Point;
import java.sql.SQLException;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import helper.*;
import javax.swing.JComboBox;

public class BashekimGUI extends JFrame {
	
	
	
	
	
	

	private static final long serialVersionUID = 1L;
	private JPanel w_pane;

	Clinic clinic = new Clinic();
	static Bashekim bashekim = new Bashekim();
	private JTextField fld_dName;
	private JTextField fld_dTcno;
	private JPasswordField fld_dPass;
	private JTextField fld_doctorID;
	private JTable table_doctor;
	private DefaultTableModel doctorModel = null; // tabloya veri eklemek için
	private Object[] doctorData = null; // içine veri atılacak
	private JTable table_clinic;
	private JTextField fld_clinicName;
	private DefaultTableModel clinicModel = null;
	private Object[] clinicData = null;
	private JPopupMenu clinicMenu;
	private JTable table_worker;
	private JTable table;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BashekimGUI frame = new BashekimGUI(bashekim);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public BashekimGUI(Bashekim bashekim) throws SQLException {

		// doktor model

		doctorModel = new DefaultTableModel();
		Object[] colDoctorName = new Object[4]; // gösterilecek kolonlar
		colDoctorName[0] = "ID";
		colDoctorName[1] = "Ad Soyad";
		colDoctorName[2] = "TC NO";
		colDoctorName[3] = "Şifre";

		doctorModel.setColumnIdentifiers(colDoctorName);
		doctorData = new Object[4];
		for (int i = 0; i < bashekim.getDoctorList().size(); i++) {
			doctorData[0] = bashekim.getDoctorList().get(i).getId();
			doctorData[1] = bashekim.getDoctorList().get(i).getName();
			doctorData[2] = bashekim.getDoctorList().get(i).getTcno();
			doctorData[3] = bashekim.getDoctorList().get(i).getPassword();

			doctorModel.addRow(doctorData); // modelin içine aktar
		}

		// clinic model
		clinicModel = new DefaultTableModel();
		Object[] colClinic = new Object[2]; // gösterilecek kolonlar
		colClinic[0] = "ID";
		colClinic[1] = "Poliklinik Adı";
		clinicModel.setColumnIdentifiers(colClinic);
		clinicData = new Object[2];

		for (int i = 0; i < clinic.getList().size(); i++) {

			clinicData[0] = clinic.getList().get(i).getId();
			clinicData[1] = clinic.getList().get(i).getName();

			clinicModel.addRow(clinicData); // modelin içine aktar
		}
		
		
		//worker model
		
		DefaultTableModel workerModel=new DefaultTableModel();
		Object[] colWorker = new Object[2];
		colWorker[0] = "ID";
		colWorker[1] = "Ad Soyad";
		workerModel.setColumnIdentifiers(colWorker);
		Object[] workerData= new Object[2];
		
		
		
		
		
		
		
		


		setTitle("Hastane Yönetim Sistemi");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);

		w_pane = new JPanel();
		w_pane.setBackground(new Color(255, 255, 255));
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Hosşgeldiniz Sayın " + bashekim.getName());
		lblNewLabel.setFont(new Font("Yu Gothic", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 11, 336, 30);
		w_pane.add(lblNewLabel);

		JButton btnNewButton = new JButton("Çıkış Yap");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login =new LoginGUI();
				login.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(625, 13, 99, 28);
		w_pane.add(btnNewButton);

		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBounds(10, 80, 714, 370);
		w_pane.add(w_tab);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		w_tab.addTab("Doktor Yönetimi", null, panel, null);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Ad Soyad");
		lblNewLabel_1.setFont(new Font("Yu Gothic", Font.BOLD, 15));
		lblNewLabel_1.setBounds(549, 8, 150, 24);
		panel.add(lblNewLabel_1);

		fld_dName = new JTextField();
		fld_dName.setBounds(549, 30, 150, 24);
		panel.add(fld_dName);
		fld_dName.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("T.C. No");
		lblNewLabel_1_1.setFont(new Font("Yu Gothic", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(549, 65, 150, 24);
		panel.add(lblNewLabel_1_1);

		fld_dTcno = new JTextField();
		fld_dTcno.setColumns(10);
		fld_dTcno.setBounds(549, 87, 150, 24);
		panel.add(fld_dTcno);

		JLabel lblNewLabel_1_2 = new JLabel("Şifre");
		lblNewLabel_1_2.setFont(new Font("Yu Gothic", Font.BOLD, 15));
		lblNewLabel_1_2.setBounds(549, 122, 150, 24);
		panel.add(lblNewLabel_1_2);

		fld_dPass = new JPasswordField();
		fld_dPass.setBounds(549, 144, 150, 24);
		panel.add(fld_dPass);

		JButton btnNewButton_1 = new JButton("Ekle");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// dolu mu boş mu kontrol
				if (fld_dName.getText().length() == 0 || fld_dPass.getText().length() == 0
						|| fld_dTcno.getText().length() == 0) {
					helper.showMsg("fill");
				} else {
					try {
						boolean control = bashekim.addDoctor(fld_dTcno.getText(), fld_dPass.getText(),
								fld_dName.getText());

						if (control) {
							helper.showMsg("success");
							fld_dName.setText(null);
							fld_dTcno.setText(null);
							fld_dPass.setText(null);
							updateDoctorModel();

						}

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

			}
		});
		btnNewButton_1.setForeground(new Color(0, 0, 0));
		btnNewButton_1.setBackground(UIManager.getColor("Button.darkShadow"));
		btnNewButton_1.setFont(new Font("Arial", Font.BOLD, 20));
		btnNewButton_1.setBounds(549, 179, 152, 33);
		panel.add(btnNewButton_1);

		JLabel lblNewLabel_1_3 = new JLabel("Kullanıcı ID");
		lblNewLabel_1_3.setFont(new Font("Yu Gothic", Font.BOLD, 15));
		lblNewLabel_1_3.setBounds(549, 223, 150, 24);
		panel.add(lblNewLabel_1_3);

		fld_doctorID = new JTextField();
		fld_doctorID.setColumns(10);
		fld_doctorID.setBounds(549, 255, 150, 24);
		panel.add(fld_doctorID);

		JButton btnNewButton_1_1 = new JButton("Sil");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (fld_doctorID.getText().length() == 0) {
					helper.showMsg("Lütfen geçerli bir doktor seçiniz!");

				} else {
					if (helper.confirm("sure")) {
						int selectID = Integer.parseInt(fld_doctorID.getText());
						try {
							boolean control = bashekim.deleteDoctor(selectID);
							if (control) {
								helper.showMsg("success");
								updateDoctorModel();
								fld_doctorID.setText(null);

							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}

			}
		});
		btnNewButton_1_1.setBackground(UIManager.getColor("Button.background"));
		btnNewButton_1_1.setFont(new Font("Arial", Font.BOLD, 20));
		btnNewButton_1_1.setBounds(549, 298, 152, 33);
		panel.add(btnNewButton_1_1);

		JScrollPane w_scrollDoctor = new JScrollPane();
		w_scrollDoctor.setBounds(10, 10, 533, 320);
		panel.add(w_scrollDoctor);

		table_doctor = new JTable(doctorModel);
		w_scrollDoctor.setViewportView(table_doctor);

		// seçilen satıra tıklanınca hangi satır tıklandığı okunacak
		table_doctor.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub

				try {
					fld_doctorID.setText(table_doctor.getValueAt(table_doctor.getSelectedRow(), 0).toString());

				} catch (Exception ex) {
					// TODO: handle exception
				}
			}

		});

		table_doctor.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {

				// tablodaki yazıyı değiştirdiğimizde güncelleme
				if (e.getType() == TableModelEvent.UPDATE) {

					int selectID = Integer
							.parseInt(table_doctor.getValueAt(table_doctor.getSelectedRow(), 0).toString());
					String selcetName = table_doctor.getValueAt(table_doctor.getSelectedRow(), 1).toString();
					String selcetTcno = table_doctor.getValueAt(table_doctor.getSelectedRow(), 2).toString();
					String selcetPass = table_doctor.getValueAt(table_doctor.getSelectedRow(), 3).toString();

					try {
						boolean control = bashekim.updateDoctor(selectID, selcetTcno, selcetPass, selcetName);

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}

		});
		JPanel w_scrollclinic = new JPanel();
		w_scrollclinic.setBackground(new Color(255, 255, 255));
		w_tab.addTab("Poliklinikler", null, w_scrollclinic, null);
		w_scrollclinic.setLayout(null);

		JScrollPane scrollClinic = new JScrollPane();
		scrollClinic.setBounds(10, 11, 268, 320);
		w_scrollclinic.add(scrollClinic);

		// pop up menu oluşturma

		clinicMenu = new JPopupMenu();
		JMenuItem updateMenu = new JMenuItem("Güncelle");
		JMenuItem deleteMenu = new JMenuItem("Sil");
		clinicMenu.add(updateMenu);
		clinicMenu.add(deleteMenu);

		updateMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int selID = Integer.parseInt(table_clinic.getValueAt(table_clinic.getSelectedRow(), 0).toString());
				Clinic selectClinic = clinic.getFetch(selID);
				UpdateClinicGUI updateGUI = new UpdateClinicGUI(selectClinic);
				updateGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				updateGUI.setVisible(true);

				updateGUI.addWindowListener(new WindowAdapter() {

					@Override
					public void windowClosed(WindowEvent e) {
						// TODO Auto-generated method stub
						try {
							updateClinicModel();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
				});
			}

		});

		deleteMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (helper.confirm("sure")) {
					int selID = Integer.parseInt(table_clinic.getValueAt(table_clinic.getSelectedRow(), 0).toString());

					try {
						if (clinic.deleteClinic(selID)) {
							helper.showMsg("success");
							updateClinicModel();
						} else {
							helper.showMsg("error");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});

		table_clinic = new JTable(clinicModel);
		table_clinic.setComponentPopupMenu(clinicMenu);

		// basıldığı tuşun hangi row da olacağnı görüyor
		table_clinic.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {

				Point point = e.getPoint();
				int selectedRow = table_clinic.rowAtPoint(point);
				table_clinic.setRowSelectionInterval(selectedRow, selectedRow);

			}

		});

		scrollClinic.setViewportView(table_clinic);

		JLabel lblNewLabel_1_4 = new JLabel("Poliklinik Adı");
		lblNewLabel_1_4.setFont(new Font("Yu Gothic", Font.BOLD, 15));
		lblNewLabel_1_4.setBounds(288, 11, 150, 24);
		w_scrollclinic.add(lblNewLabel_1_4);

		fld_clinicName = new JTextField();
		fld_clinicName.setColumns(10);
		fld_clinicName.setBounds(288, 33, 128, 24);
		w_scrollclinic.add(fld_clinicName);

		JButton btn_addClinic = new JButton("Ekle");
		btn_addClinic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (fld_clinicName.getText().length() == 0) {
					helper.showMsg("fill");

				} else {
					try {
						if (clinic.addClinic(fld_clinicName.getText())) {
							helper.showMsg("success");
							fld_clinicName.setText(null);
							updateClinicModel();
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}

		});

		btn_addClinic.setForeground(Color.BLACK);
		btn_addClinic.setFont(new Font("Arial", Font.BOLD, 20));
		btn_addClinic.setBackground(UIManager.getColor("Button.darkShadow"));
		btn_addClinic.setBounds(286, 68, 130, 33);
		w_scrollclinic.add(btn_addClinic);

		JScrollPane w_scrollWorker = new JScrollPane();
		w_scrollWorker.setBounds(436, 11, 263, 320);
		w_scrollclinic.add(w_scrollWorker);
		
		table_worker = new JTable();
		w_scrollWorker.setViewportView(table_worker);
		
		JComboBox select_doctor = new JComboBox();
		select_doctor.setBounds(288, 253, 128, 33);
		for (int i = 0; i < bashekim.getDoctorList().size(); i++) {
			select_doctor.addItem(new Item(bashekim.getDoctorList().get(i).getId(),bashekim.getDoctorList().get(i).getName()));
	
		}     //comboboxtaki değerleri döndürür
		select_doctor.addActionListener(e ->{
			JComboBox c =(JComboBox)e.getSource();
			Item item = (Item) c.getSelectedItem();
	        System.out.println(item.getKey()+":"+item.getValue());
		
		});
		w_scrollclinic.add(select_doctor);
		
		JButton btn_addWorker = new JButton("Ekle");
		btn_addWorker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int selRow =table_clinic.getSelectedRow();
				if(selRow>=0) {
					String selClinic =table_clinic.getModel().getValueAt(selRow, 0).toString();
					int selClinicID=Integer.parseInt(selClinic);
					Item doctorItem =(Item) select_doctor.getSelectedItem();
					try {
						boolean control =bashekim.addWorker(doctorItem.getKey(), selClinicID);
						if(control) {
							helper.showMsg("success");
							DefaultTableModel clearModel= (DefaultTableModel) table_worker.getModel();
							clearModel.setRowCount(0);
							for (int i = 0; i < bashekim.getClinicDoctorList(selClinicID).size(); i++) {
								workerData[0]=bashekim.getClinicDoctorList(selClinicID).get(i).getId();
								workerData[1]=bashekim.getClinicDoctorList(selClinicID).get(i).getName();
								workerModel.addRow(workerData);

							}
							table_worker.setModel(workerModel);

						}else {
							helper.showMsg("error");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}else {
					helper.showMsg("Lütfen bir poliklinik seçiniz !");
				}
				
				
				
			}
		});
		btn_addWorker.setForeground(Color.BLACK);
		btn_addWorker.setFont(new Font("Arial", Font.BOLD, 20));
		btn_addWorker.setBackground(UIManager.getColor("Button.darkShadow"));
		btn_addWorker.setBounds(288, 297, 130, 33);
		w_scrollclinic.add(btn_addWorker);
		
		table = new JTable();
		table.setBounds(437, 123, 261, 0);
		w_scrollclinic.add(table);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(288, 144, 128, 24);
		w_scrollclinic.add(textField);
		
		JLabel lblNewLabel_1_4_1 = new JLabel("Poliklinik Adı");
		lblNewLabel_1_4_1.setFont(new Font("Yu Gothic", Font.BOLD, 15));
		lblNewLabel_1_4_1.setBounds(288, 122, 150, 24);
		w_scrollclinic.add(lblNewLabel_1_4_1);
		
		JButton btn_worker_Select = new JButton("Seç");
		btn_worker_Select.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int selRow =table_clinic.getSelectedRow();
				if(selRow>=0) {
					String selClinic =table_clinic.getModel().getValueAt(selRow, 0).toString();
					int selClinicID=Integer.parseInt(selClinic);
					DefaultTableModel clearModel= (DefaultTableModel) table_worker.getModel();
					clearModel.setRowCount(0);
					
					try {
						for (int i = 0; i < bashekim.getClinicDoctorList(selClinicID).size(); i++) {
							workerData[0]=bashekim.getClinicDoctorList(selClinicID).get(i).getId();
							workerData[1]=bashekim.getClinicDoctorList(selClinicID).get(i).getName();
							workerModel.addRow(workerData);

						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					table_worker.setModel(workerModel);
					
				}else {
					helper.showMsg("Lütfen bir poliklinik seçiniz");
				}
				
				
				
				
			}
		});
		btn_worker_Select.setForeground(Color.BLACK);
		btn_worker_Select.setFont(new Font("Arial", Font.BOLD, 20));
		btn_worker_Select.setBackground(UIManager.getColor("Button.darkShadow"));
		btn_worker_Select.setBounds(286, 179, 130, 33);
		w_scrollclinic.add(btn_worker_Select);
	}

	// tabloyu güncelleyen metod
	public void updateDoctorModel() throws SQLException {

		DefaultTableModel clearModel = (DefaultTableModel) table_doctor.getModel();
		clearModel.setRowCount(0); // bütün satırlar silincek

		// yeniden içine aktar
		for (int i = 0; i < bashekim.getDoctorList().size(); i++) {
			doctorData[0] = bashekim.getDoctorList().get(i).getId();
			doctorData[1] = bashekim.getDoctorList().get(i).getName();
			doctorData[2] = bashekim.getDoctorList().get(i).getTcno();
			doctorData[3] = bashekim.getDoctorList().get(i).getPassword();

			doctorModel.addRow(doctorData); // modelin içine aktar
		}
	}

	public void updateClinicModel() throws SQLException {

		DefaultTableModel clearModel = (DefaultTableModel) table_clinic.getModel();
		clearModel.setRowCount(0); // bütün satırlar silincek

		// yeniden içine aktar
		for (int i = 0; i < clinic.getList().size(); i++) {
			clinicData[0] = clinic.getList().get(i).getId();
			clinicData[1] = clinic.getList().get(i).getName();

			clinicModel.addRow(clinicData); // modelin içine aktar
		}
	}
}
