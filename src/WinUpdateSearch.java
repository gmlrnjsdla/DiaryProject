import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class WinUpdateSearch extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfdate;


	/**
	 * Create the dialog.
	 * @param name 
	 * @param id 
	 */
	public WinUpdateSearch(String id, String name) {
		setBounds(100, 100, 447, 174);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("날짜");
			lblNewLabel.setBounds(105, 46, 57, 15);
			contentPanel.add(lblNewLabel);
		}
		{
			tfdate = new JTextField();
			
			tfdate.setBounds(150, 43, 116, 21);
			contentPanel.add(tfdate);
			tfdate.setColumns(10);
		}
		{
			JButton btnNewButton = new JButton("...");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					WinCalendar wincal = new WinCalendar();
					wincal.setModal(true);
					wincal.setVisible(true);
					tfdate.setText(wincal.getDate());
				}
			});
			btnNewButton.setBounds(276, 42, 45, 23);
			contentPanel.add(btnNewButton);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String date = tfdate.getText();
						try {
							Class.forName("com.mysql.cj.jdbc.Driver");
							Connection conn = DriverManager.getConnection
									("jdbc:mysql://localhost:3306/sqldb", "root","1234");
							
							String sql = "SELECT * FROM diarytbl WHERE id='"+id+"' and mdate='"+date+"'";
							Statement stmt = conn.createStatement();
							ResultSet rs = stmt.executeQuery(sql);
							
							if(rs.next()) {
								String weather = rs.getString("weather");
								String title = rs.getString("title");
								String content = rs.getString("content");
								String mdate = rs.getString("mdate");
								String pw = rs.getString("curpw");
								WinUpdateDiary winupdate = new WinUpdateDiary(id, name,mdate,weather,title,content,pw);
								setVisible(false);
								winupdate.setModal(true);
								winupdate.setVisible(true);
							}else {
								JOptionPane.showMessageDialog(null, id+"님이 "+date+"에 쓰신 글이 없습니다.");
							}
							
							
							
							} catch (Exception e1) {
							e1.printStackTrace();
							}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
