import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.exceptions.RSAException;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class WinMain extends JDialog {

	private final JPanel contentPanel = new JPanel();
	String sid, sname;
	DefaultTableModel model;
	private JTable table;


	/**
	 * Create the dialog.
	 */
	public WinMain(String id, String name) {
		
		sid = id;
		sname = name;
		setTitle(sname+"님의 다이어리");
		
		setBounds(100, 100, 471, 361);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{
				String header[]= {"NO","Name","Title","Date"};
				DefaultTableModel model=new DefaultTableModel(header,0);
				
				table = new JTable(model);
				scrollPane.setViewportView(table);
				diaryList(id);
			}
			
			
		}
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
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
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		{
			JMenu mnNewMenu = new JMenu("Diary");
			mnNewMenu.setMnemonic('D');
			menuBar.add(mnNewMenu);
			{
				JMenuItem mnInsertDiary = new JMenuItem("다이어리 쓰기...");
				mnInsertDiary.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						WinInsertDiary windiary = new WinInsertDiary(id, name);
						
						windiary.setModal(true);
						windiary.setVisible(true);
					}
				});
				mnNewMenu.add(mnInsertDiary);
			}
			{
				JMenuItem mnUpdateDiary = new JMenuItem("다이어리 수정...");
				mnUpdateDiary.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						WinUpdateSearch winupdatesearch = new WinUpdateSearch(id,name);
						winupdatesearch.setModal(true);
						winupdatesearch.setVisible(true);						
						
					}
				});
				mnNewMenu.add(mnUpdateDiary);
			}
			{
				JMenuItem mnDeleteDiary = new JMenuItem("다이어리 삭제...");
				mnDeleteDiary.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						WinDateSearch windatesearch = new WinDateSearch(id,name);
						windatesearch.setModal(true);
						windatesearch.setVisible(true);						
						
					}
				});
				mnNewMenu.add(mnDeleteDiary);
			}
		}
	}


	private void diaryList(String sid) {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection
					("jdbc:mysql://localhost:3306/sqldb", "root","1234");
			
			String sql ="SELECT * FROM diarytbl WHERE id='"+sid+"' ORDER BY idx DESC"; 
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String idx = rs.getString("idx");
				String id = rs.getString("id");
				String title = rs.getString("title");
				String date = rs.getString("mdate");
				
				
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				String record[] = new String [4];
				record[0] = idx;
				record[1] = id;
				record[2] = title;
				record[3] = date;
				
				
				model.addRow(record);
			}
			
			} catch (Exception e1) {
			e1.printStackTrace();
			}
	}
	
	
}
