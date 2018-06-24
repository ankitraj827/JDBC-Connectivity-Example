import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
  
class Login extends JFrame implements ActionListener
{
	private Connection conn;
	private Statement st;
	private ResultSet rs;
	private PreparedStatement myStmt;
	JButton SUBMIT,REGISTER;
	JPanel panel;
	JLabel label1,label2;
	final JTextField  text1,text2;
   	Login()
   	{
		try
		{
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/";
        	String dbName = "test54";
        	String userName = "root"; 
        	String password = "";
		conn=DriverManager.getConnection(url+dbName,userName,password);
		System.out.println("Ankit");
		st=conn.createStatement();
		//System.out.println("Ankit");
		}
		catch(Exception ex)
		{
		System.out.println("Error:"+ex);
		}
   		label1 = new JLabel();
   		label1.setText("Username:");
   		text1 = new JTextField(15);
 
   		label2 = new JLabel();
   		label2.setText("Password:");
   		text2 = new JPasswordField(15);
  
   		SUBMIT=new JButton("SUBMIT");
		REGISTER=new JButton("REGISTER");
   
   		panel=new JPanel(new GridLayout(20,1));
   		panel.add(label1);
   		panel.add(text1);
   		panel.add(label2);
   		panel.add(text2);
   		panel.add(SUBMIT);
		panel.add(REGISTER);
   		add(panel,BorderLayout.CENTER);
		panel.setBackground(Color.BLUE);
   		SUBMIT.addActionListener(this);
		REGISTER.addActionListener(this);
   		setTitle("LOGIN FORM");
   	}
  public void actionPerformed(ActionEvent ae)
   {
	if(ae.getSource()==SUBMIT)
	{
		String uname=(String)text1.getText();
		System.out.println(uname);
		String pass1=(String)text2.getText();
		try
		{
			String query="select * from ankit";
			rs=st.executeQuery(query);
			int flag=0;
			outer:
			while(rs.next())
			{
				String name=rs.getString("Name");
				String  pass=rs.getString("Password");
				System.out.println(name+" "+pass);
				if((uname.equals("")==true)||(pass1.equals("")==true))
				{
					JOptionPane.showMessageDialog(this,"Please fill the above details","Error",JOptionPane.ERROR_MESSAGE);	
					text1.setText("");
					text2.setText("");
					flag=2;
					break outer;
				}
				if(name.equals(uname)==true && pass.equals(pass1)==true)
				{
					NextPage page=new NextPage();
					page.setVisible(true);
					JLabel label = new JLabel("Welcome to the World of Ankit Raj");
					page.getContentPane().add(label);
					flag=1;
					break outer;	
				}
				
			}
			if(flag==0)
			{
				JOptionPane.showMessageDialog(this,"Login Denied","Error",JOptionPane.ERROR_MESSAGE);	
				text1.setText("");
				text2.setText("");
			}
				
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
	}
	if(ae.getSource()==REGISTER)
	{
		try
			{
				String name=(String)text1.getText();
				String  pass=(String)text2.getText();
				if(name.equals("")==false && name.equals("")==false)
				{
					String sql = "insert into ankit " + " ( Name, Password)" + " values (?, ?)";
					myStmt = conn.prepareStatement(sql);
					myStmt.setString(1, name);
					myStmt.setString(2, pass);
					myStmt.executeUpdate();
					text1.setText("");
					text2.setText("");
					NextPage page=new NextPage();
					page.setVisible(true);
					JLabel label = new JLabel("Welcome to the World of Ankit Raj && registered successfully");
					page.getContentPane().add(label);
				}
				else
				{
					JOptionPane.showMessageDialog(this,"Please fill the above details","Error",JOptionPane.ERROR_MESSAGE);
					text1.setText("");
					text2.setText("");
				}
				
			}
			catch(Exception ex)
			{
			System.out.println(ex);
			}

		}
	
		}
}
public class WoginDemo
 {
   public static void main(String arg[])
   {
   try
   {
   Login frame=new Login();
   frame.setSize(500,500);
   frame.setVisible(true);
   }
   catch(Exception e)
   {JOptionPane.showMessageDialog(null, e.getMessage());}
   }
 }
