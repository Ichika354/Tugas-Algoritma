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

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class DataBarang {

    private JFrame frame;
    private JTextField txtBarang;
    private JTextField txtStok;
    private JTextField txtHarga;
    private JTextField txtTanggal;
    private JTextField cariId;
    public String sql = "";
    public Statement stat;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DataBarang window = new DataBarang();
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
    public DataBarang() {
        initialize();
        Connect();
        table_load();
    }

    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    private JTextField textField;
    private JTable table;

    public void Connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/data_barang", "root", "");
        } catch (ClassNotFoundException ex) {

        } catch (SQLException ex) {

        }
    }

    public void table_load() {
        try {
            pst = con.prepareStatement("SELECT * FROM barang");
            rs = pst.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {

        }
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 904, 499);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Data Barang");
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
        lblNewLabel.setBounds(378, 11, 131, 30);
        frame.getContentPane().add(lblNewLabel);

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "Pemasukan Nilai", TitledBorder.LEADING, TitledBorder.TOP, null,
                new Color(0, 0, 0)));
        panel.setBounds(10, 52, 427, 230);
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("Nama Barang");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_1.setBounds(34, 32, 101, 25);
        panel.add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("Stok");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_1_1.setBounds(34, 84, 117, 23);
        panel.add(lblNewLabel_1_1);

        JLabel lblNewLabel_1_1_1 = new JLabel("Harga");
        lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_1_1_1.setBounds(34, 135, 101, 25);
        panel.add(lblNewLabel_1_1_1);

        JLabel lblNewLabel_1_1_2 = new JLabel("Tanggal");
        lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_1_1_2.setBounds(34, 190, 101, 25);
        panel.add(lblNewLabel_1_1_2);

        txtBarang = new JTextField();
        txtBarang.setBounds(161, 32, 211, 24);
        panel.add(txtBarang);
        txtBarang.setColumns(10);

        txtStok = new JTextField();
        txtStok.setColumns(10);
        txtStok.setBounds(161, 84, 211, 23);
        panel.add(txtStok);

        txtHarga = new JTextField();
        txtHarga.setColumns(10);
        txtHarga.setBounds(161, 135, 211, 24);
        panel.add(txtHarga);

        txtTanggal = new JTextField();
        txtTanggal.setColumns(10);
        txtTanggal.setBounds(161, 190, 211, 24);
        panel.add(txtTanggal);

        JButton btnNewButton = new JButton("INPUT");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String namaBarang, stok, harga, tanggal;

                namaBarang = txtBarang.getText();
                stok = txtStok.getText();
                harga = txtHarga.getText();
                tanggal = txtTanggal.getText();
                try {
                    pst = con.prepareStatement(
                            "INSERT INTO barang(nama_barang,stok,harga,tanggal_masuk) VALUES (?,?,?,?)");
                    pst.setString(1, namaBarang);
                    pst.setString(2, stok);
                    pst.setString(3, harga);
                    pst.setString(4, tanggal);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Barang Berhasil Di input");
                    // javax.swing.JOptionPane.showMessageDialog(null,"Data berhasil disimpan");
                    // table_load();
                    txtBarang.setText("");
                    txtStok.setText("");
                    txtHarga.setText("");
                    txtTanggal.setText("");
                    txtBarang.requestFocus();
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

        JLabel lblNewLabel_1_2 = new JLabel("ID");
        lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_1_2.setBounds(10, 37, 59, 26);
        panel_2.add(lblNewLabel_1_2);

        cariId = new JTextField();
        cariId.setColumns(10);
        cariId.setBounds(88, 40, 211, 24);
        panel_2.add(cariId);

        JButton btnNewButton_1_2 = new JButton("CARI");
        btnNewButton_1_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/data_barang", "root", "");
                    sql = "SELECT * FROM barang where id ='" + cariId.getText() + "'";
                    stat = con.createStatement();
                    rs = stat.executeQuery(sql);
                    if (rs.next()) {
                        txtBarang.setText(rs.getString("nama_barang"));
                        txtStok.setText(rs.getString("stok"));
                        txtHarga.setText(rs.getString("harga"));
                        txtTanggal.setText(rs.getString("tanggal_masuk"));
                    } else {
                        javax.swing.JOptionPane.showMessageDialog(null, "Data tidak ditemukan ");
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
                String id, nama, harga, tanggal;

                nama = txtStok.getText();
                harga = txtHarga.getText();
                tanggal = txtTanggal.getText();
                id = cariId.getText();

                try {
                    pst = con.prepareStatement(
                            "UPDATE barang SET nama_barang = ?, harga = ?,tanggal_masuk = ? WHERE id = ?");
                    pst.setString(1, nama);
                    pst.setString(2, harga);
                    pst.setString(3, tanggal);
                    pst.setString(4, id);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Data Berhasil Di ubah");
                    txtBarang.setText("");
                    txtStok.setText("");
                    txtHarga.setText("");
                    txtTanggal.setText("");
                    txtBarang.requestFocus();
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
                String id;

                id = cariId.getText();

                try {
                    pst = con.prepareStatement("DELETE FROM barang WHERE id = ?");
                    pst.setString(1, id);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
                    txtBarang.setText("");
                    txtStok.setText("");
                    txtHarga.setText("");
                    txtTanggal.setText("");
                } catch (SQLException e1) {
                    JOptionPane.showMessageDialog(null, "Data gagal Dihapus");
                    e1.printStackTrace();
                }
            }
        });
        btnNewButton_1_1.setBounds(707, 346, 108, 74);
        frame.getContentPane().add(btnNewButton_1_1);

        table = new JTable();
        table.setBounds(447, 52, 431, 264);
        frame.getContentPane().add(table);
    }
}