import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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

public class WinMain extends JDialog {

	private final JPanel contentPanel = new JPanel();
	String sid, sname;


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
}
