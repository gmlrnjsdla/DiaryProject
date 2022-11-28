import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.net.http.HttpRequest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class WinLogin extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfId;
	private JTextField tfPw;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			WinLogin dialog = new WinLogin();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public WinLogin() {
		setTitle("Login");
		setBounds(100, 100, 452, 213);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(63, 61, 57, 15);
		contentPanel.add(lblNewLabel);
		
		tfId = new JTextField();
		tfId.setBounds(132, 58, 144, 21);
		contentPanel.add(tfId);
		tfId.setColumns(10);
		
		JLabel lblPw = new JLabel("PW");
		lblPw.setHorizontalAlignment(SwingConstants.CENTER);
		lblPw.setBounds(63, 95, 57, 15);
		contentPanel.add(lblPw);
		
		tfPw = new JTextField();
		tfPw.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					Go();
					
				}
			}

			
		});
		tfPw.setColumns(10);
		tfPw.setBounds(132, 92, 144, 21);
		contentPanel.add(tfPw);
		
		JButton btnNewButton = new JButton("로그인");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Go();
				
			}
			
		});
		btnNewButton.setBounds(292, 57, 78, 58);
		contentPanel.add(btnNewButton);
	}
	
	private void Go() {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection
					("jdbc:mysql://localhost:3306/sqldb", "root","1234");
			
			String sql = "SELECT pw,name FROM logintbl WHERE id='"+tfId.getText()+"'";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				String dbpw = rs.getString("pw");
				String pw = tfPw.getText();
				if(dbpw.equals(pw)) {
					String id = tfId.getText();
					String name = rs.getString("name");
					WinMain winmain = new WinMain(id,name);
					setVisible(false);
					winmain.setModal(true);
					winmain.setVisible(true);
					
				}
				else {
					JOptionPane.showMessageDialog(null, "비밀번호가 틀렸습니다. 다시확인해주세요");
					tfPw.requestFocus();
				}
			
			}
			else {
				JOptionPane.showMessageDialog(null, "없는 아이디입니다. 다시확인해주세요");
				tfId.requestFocus();
			}
			
			} catch (Exception e1) {
			e1.printStackTrace();
			}
		
	}
	
}
