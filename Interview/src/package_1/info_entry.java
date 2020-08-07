package package_1;

import java.awt.BorderLayout;
import java.sql.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.border.LineBorder;

public class info_entry extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTextField age;
	private JTextField email;
	private JTextField pan_number;
	private JTextField gender;
	private JTextField city;
	private JTable table;
	DefaultTableModel dm;
	 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					info_entry frame = new info_entry();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	
	public void fetch(){
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sql_1","root","");
	  PreparedStatement p=cn.prepareStatement("Select * from information");
		
		  ResultSet r = p.executeQuery();
		  table.setModel(DbUtils.resultSetToTableModel(r));
		 
		
		}
		catch(Exception e){
		JOptionPane.showMessageDialog(null,e); 
		 }
		}
	
	

	/**
	 * Create the frame.
	 */
	public info_entry() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 577, 578);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(175, 238, 238));
		contentPane.setForeground(new Color(175, 238, 238));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(28, 59, 91, 34);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Age");
		lblNewLabel_1.setBounds(28, 106, 91, 26);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("E-mail");
		lblNewLabel_2.setBounds(28, 153, 91, 26);
		contentPane.add(lblNewLabel_2);
		
		name = new JTextField();
		name.setBounds(113, 65, 116, 22);
		contentPane.add(name);
		name.setColumns(10);
		
		age = new JTextField();
		age.setBounds(113, 106, 116, 22);
		contentPane.add(age);
		age.setColumns(10);
		
		email = new JTextField();
		email.setBounds(113, 155, 424, 22);
		contentPane.add(email);
		email.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Pan number");
		lblNewLabel_3.setBounds(335, 68, 74, 25);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Gender");
		lblNewLabel_4.setBounds(335, 111, 65, 26);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("City");
		lblNewLabel_5.setBounds(25, 200, 65, 16);
		contentPane.add(lblNewLabel_5);
		
		pan_number = new JTextField();
		pan_number.setColumns(10);
		pan_number.setBounds(421, 65, 116, 22);
		contentPane.add(pan_number);
		
		gender = new JTextField();
		gender.setColumns(10);
		gender.setBounds(421, 115, 116, 22);
		contentPane.add(gender);
		
		city = new JTextField();
		city.setColumns(10);
		city.setBounds(113, 197, 116, 22);
		contentPane.add(city);
		
		JButton search = new JButton("Search");
		search.setBackground(new Color(250, 235, 215));
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try{

					
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sql_1","root","");
					
					  PreparedStatement p=con.prepareStatement("SELECT * FROM information WHERE PAN_number='"+pan_number.getText()+"'");
						
					  ResultSet rs = p.executeQuery();

					  if(rs.first()){

						name.setText(rs.getString("Name"));
						age.setText(rs.getString("Age"));
						email.setText(rs.getString("Email"));
						gender.setText(rs.getString("Gender"));
						city.setText(rs.getString("City"));
					}
				}
					catch(Exception e){e.printStackTrace();}
				
		
			}
		});
		search.setBounds(283, 227, 97, 25);
		contentPane.add(search);
		
		JButton btnNewButton_1 = new JButton("Add");
		btnNewButton_1.setBackground(new Color(250, 235, 215));
		btnNewButton_1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				try{
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sql_1","root","");
					String sql="INSERT into information(Name,PAN_number,Age,Gender,Email,City)values(?,?,?,?,?,?)";
				//	Statement stmt=con.createStatement();  
		//			PreparedStatement pat=con.prepareStatement(sql);                 
					java.sql.PreparedStatement statement = con.prepareStatement(sql);
					statement.setString(1, name.getText()); 
			        statement.setString(3, age.getText());
			        statement.setString(2, pan_number.getText());
			        statement.setString(5, email.getText());						
			        statement.setString(4, gender.getText());
			        statement.setString(6, city.getText());
			        statement.executeUpdate();
					JOptionPane.showMessageDialog(null,"Inserter Successfully!");
					
					
				}catch(Exception e){System.out.print(e);}
fetch();				
			}
		});
	
		btnNewButton_1.setBounds(420, 227, 97, 25);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_6 = new JLabel("PERSON INFORMATION");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setForeground(new Color(0, 0, 0));
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblNewLabel_6.setBounds(28, 12, 509, 34);
		contentPane.add(lblNewLabel_6);
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		table.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column", "New column", "New column"
			}
		));
	
		dm=(DefaultTableModel) table.getModel();
		table.setBounds(28, 275, 499, 229);
		contentPane.add(table);
	}
	}
