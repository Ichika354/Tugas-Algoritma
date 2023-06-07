package reza;

import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Nilai {

	private JFrame frame;
	private JTextField txtNpm;
	private JTextField txtNama;
	private JTextField txtMatkul;
	private JTextField txtNilai;
	private JTextField cariNpm;
	public String sql = "";
	public Statement stat;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Nilai window = new Nilai();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Nilai() {
		initialize();
		Connect();
//		table_load();
	}

	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTextField textField;
	private JTextField matkulEdit;
	private JTextField nilaiEdit;
	private JTextField namaEdit;

	public void Connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pemasukan_nilai", "root", "");
		} catch (ClassNotFoundException ex) {

		} catch (SQLException ex) {

		}
	}

//	public void table_load() {
//		try {
//			pst = con.prepareStatement("SELECT * FROM nilai");
//			rs = pst.executeQuery();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 904, 499);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Pemasukan Nilai");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel.setBounds(378, 11, 131, 30);
		frame.getContentPane().add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Pemasukan Nilai", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		panel.setBounds(10, 52, 427, 230);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("NPM");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(34, 32, 101, 25);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Nama Mahasiswa");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(34, 84, 117, 23);
		panel.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Matkul");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1_1.setBounds(34, 135, 101, 25);
		panel.add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_1_1_2 = new JLabel("Nilai");
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1_2.setBounds(34, 190, 101, 25);
		panel.add(lblNewLabel_1_1_2);

		txtNpm = new JTextField();
		txtNpm.setBounds(161, 32, 211, 24);
		panel.add(txtNpm);
		txtNpm.setColumns(10);

		txtNama = new JTextField();
		txtNama.setColumns(10);
		txtNama.setBounds(161, 84, 211, 23);
		panel.add(txtNama);

		txtMatkul = new JTextField();
		txtMatkul.setColumns(10);
		txtMatkul.setBounds(161, 135, 211, 24);
		panel.add(txtMatkul);

		txtNilai = new JTextField();
		txtNilai.setColumns(10);
		txtNilai.setBounds(161, 190, 211, 24);
		panel.add(txtNilai);

		JButton btnNewButton = new JButton("INPUT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String npm, nama, matkul, nilai;

				npm = txtNpm.getText();
				nama = txtNama.getText();
				matkul = txtMatkul.getText();
				nilai = txtNilai.getText();
				try {
					pst = con.prepareStatement("INSERT INTO nilai(npm,nama,matkul,nilai) VALUES (?,?,?,?)");
					pst.setString(1, npm);
					pst.setString(2, nama);
					pst.setString(3, matkul);
					pst.setString(4, nilai);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Nilai Berhasil Di input");
//					javax.swing.JOptionPane.showMessageDialog(null,"Data berhasil disimpan");
//					table_load();
					txtNpm.setText("");
					txtNama.setText("");
					txtMatkul.setText("");
					txtNilai.setText("");
					txtNpm.requestFocus();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});
		btnNewButton.setBounds(171, 293, 89, 23);
		frame.getContentPane().add(btnNewButton);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Cari", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 325, 427, 106);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel_1_2 = new JLabel("NPM");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2.setBounds(34, 37, 59, 26);
		panel_2.add(lblNewLabel_1_2);

		cariNpm = new JTextField();
		cariNpm.setColumns(10);
		cariNpm.setBounds(88, 40, 211, 24);
		panel_2.add(cariNpm);

		JButton btnNewButton_1_2 = new JButton("CARI");
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pemasukan_nilai", "root", "");
					sql = "select * from nilai where npm ='" + cariNpm.getText() + "'";
					stat = con.createStatement();
					rs = stat.executeQuery(sql);
					if (rs.next()) {
						namaEdit.setText(rs.getString("nama"));
						matkulEdit.setText(rs.getString("matkul"));
						nilaiEdit.setText(rs.getString("nilai"));
					} else {
						javax.swing.JOptionPane.showMessageDialog(null, "Data gagal ditemukan ");
					}
				} catch (Exception ex) {
					javax.swing.JOptionPane.showMessageDialog(null, "Terjadi kesalahan" + ex);
				}

			}
		});
		btnNewButton_1_2.setBounds(327, 27, 72, 50);
		panel_2.add(btnNewButton_1_2);

		JButton btnNewButton_1 = new JButton("EDIT");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String npm,nama, matkul, nilai;

				nama = namaEdit.getText();
				matkul = matkulEdit.getText();
				nilai = nilaiEdit.getText();
				npm = cariNpm.getText();
				
				try {
					pst = con.prepareStatement("UPDATE nilai SET nama = ?, matkul = ?,nilai = ? WHERE npm = ?");
					pst.setString(1, nama);
					pst.setString(2, matkul);
					pst.setString(3, nilai);
					pst.setString(4, npm);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Data Berhasil Di ubah");
					namaEdit.setText("");
					matkulEdit.setText("");
					nilaiEdit.setText("");
					txtNpm.requestFocus();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Data gagal Di ubah");
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(505, 346, 108, 74);
		frame.getContentPane().add(btnNewButton_1);

		JButton btnNewButton_1_1 = new JButton("DELETE");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String npm;

				npm = cariNpm.getText();
				
				try {
					pst = con.prepareStatement("DELETE FROM nilai WHERE npm = ?");
					pst.setString(1, npm);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
					namaEdit.setText("");
					matkulEdit.setText("");
					nilaiEdit.setText("");
					txtNpm.requestFocus();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Data gagal Dihapus");
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1_1.setBounds(707, 346, 108, 74);
		frame.getContentPane().add(btnNewButton_1_1);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Ubah Data", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(447, 52, 431, 229);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1_3 = new JLabel("Matkul");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_3.setBounds(28, 87, 101, 25);
		panel_1.add(lblNewLabel_1_3);

//		textField.setColumns(10);
//		textField.setBounds(131, 35, 211, 24);
//		panel_1.add(textField);

		JLabel lblNewLabel_1_3_1 = new JLabel("Nilai");
		lblNewLabel_1_3_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_3_1.setBounds(28, 147, 101, 25);
		panel_1.add(lblNewLabel_1_3_1);

		matkulEdit = new JTextField();
		matkulEdit.setColumns(10);
		matkulEdit.setBounds(131, 89, 211, 24);
		panel_1.add(matkulEdit);

		JLabel lblNewLabel_1_3_2 = new JLabel("Nama");
		lblNewLabel_1_3_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_3_2.setBounds(28, 33, 101, 25);
		panel_1.add(lblNewLabel_1_3_2);

		nilaiEdit = new JTextField();
		nilaiEdit.setColumns(10);
		nilaiEdit.setBounds(131, 151, 211, 24);
		panel_1.add(nilaiEdit);

		namaEdit = new JTextField();
		namaEdit.setColumns(10);
		namaEdit.setBounds(131, 37, 211, 24);
		panel_1.add(namaEdit);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(0, 0, 888, 460);
		frame.getContentPane().add(lblNewLabel_2);
	}
}