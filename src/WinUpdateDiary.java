import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class WinUpdateDiary extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfDate;
	private JTextField tfTitle;
	private JTextField tfId;
	private JTextField tfPw;
	private JLabel lblpic;
	private ImageIcon icon;
	private JButton okButton;
	String id, name;

	

	/**
	 * Create the dialog.
	 * @param pw 
	 * @param content 
	 * @param title 
	 * @param weather 
	 * @param mdate 
	 * @param id 
	 * @param name 
	 */
	public WinUpdateDiary(String sid, String sname, String sdate, String weather, String title, String content, String pw) {
		id = sid;
		name = sname;
		setTitle(name+"님의 다이어리 수정");
		setBounds(100, 100, 412, 552);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("날짜 : ");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(28, 57, 57, 15);
		contentPanel.add(lblNewLabel);
		
		tfDate = new JTextField();
		tfDate.setText(sdate);
		tfDate.setBounds(102, 54, 116, 21);
		contentPanel.add(tfDate);
		tfDate.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("날씨 : ");
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(28, 121, 57, 15);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("제목 : ");
		lblNewLabel_2.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(28, 177, 57, 15);
		contentPanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("내용 : ");
		lblNewLabel_3.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(28, 227, 57, 15);
		contentPanel.add(lblNewLabel_3);
		
		
		
		JComboBox cbWeather = new JComboBox();
		cbWeather.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				icon = new ImageIcon(".\\weather\\"+cbWeather.getSelectedItem()+".png");
				Image img = icon.getImage();
				Image ChangeImg = img.getScaledInstance(130, 106,Image.SCALE_SMOOTH);
				ImageIcon changeIcon = new ImageIcon(ChangeImg);
				lblpic.setIcon(changeIcon);
			}
		});
		cbWeather.setModel(new DefaultComboBoxModel(new String[] {"선택...", "맑음", "흐림", "비", "바람", "번개", "눈"}));
		cbWeather.setBounds(106, 117, 85, 23);
		contentPanel.add(cbWeather);
		
		lblpic = new JLabel("");
		lblpic.setBackground(UIManager.getColor("Button.disabledShadow"));
		lblpic.setBounds(230, 86, 136, 106);
		lblpic.setOpaque(true);
//		lblpic.setIcon(new ImageIcon(".\\weather\\맑음.png"));
		contentPanel.add(lblpic);
		
		cbWeather.setSelectedItem(weather);
		
		JButton btnNewButton = new JButton("...");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinCalendar winCalendar = new WinCalendar();
				winCalendar.setModal(true);
				winCalendar.setVisible(true);
				tfDate.setText(winCalendar.getDate());
			}
		});
		btnNewButton.setBounds(230, 53, 57, 23);
		contentPanel.add(btnNewButton);
		
		tfTitle = new JTextField();
		tfTitle.setText(title);
		tfTitle.setColumns(10);
		tfTitle.setBounds(102, 174, 116, 21);
		contentPanel.add(tfTitle);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(102, 227, 233, 197);
		contentPanel.add(scrollPane);
		
		JTextPane tfContent = new JTextPane();
		tfContent.setText(content);
		scrollPane.setViewportView(tfContent);
		
		tfId = new JTextField();
		tfId.setEditable(false);
		tfId.setText(id);
		tfId.setBounds(63, 449, 116, 21);
		contentPanel.add(tfId);
		tfId.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("아이디 : ");
		lblNewLabel_4.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		lblNewLabel_4.setBounds(12, 452, 57, 15);
		contentPanel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_4_1 = new JLabel("일기PW : ");
		lblNewLabel_4_1.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		lblNewLabel_4_1.setBounds(191, 452, 72, 15);
		contentPanel.add(lblNewLabel_4_1);
		
		tfPw = new JTextField();
		tfPw.setToolTipText("입력 후 Enter");
		tfPw.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					int length = tfPw.getText().length();
					if(length >= 4) {
						String value = JOptionPane.showInputDialog("비밀번호 입력(확인)");
						if(tfPw.getText().equals(value) && tfPw.getText().equals(pw)) {
							JOptionPane.showMessageDialog(null, "일치합니다.");
							okButton.setEnabled(true);
						}
						else {
							JOptionPane.showMessageDialog(null, "비밀번호가 틀렸습니다!");
							tfPw.setText("");
							tfPw.requestFocus();
						}
					}else {
						
					}
					
				}
			}
		});
		tfPw.setColumns(10);
		tfPw.setBounds(255, 449, 116, 21);
		contentPanel.add(tfPw);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("수정");
				okButton.setEnabled(false);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							Class.forName("com.mysql.cj.jdbc.Driver");
							Connection conn = DriverManager.getConnection
									("jdbc:mysql://localhost:3306/sqldb", "root","1234");
							
							String mdate = tfDate.getText();
							String weather = (String)cbWeather.getSelectedItem();
							String pic = "./weather/"+cbWeather.getSelectedItem()+".png";
							String title = tfTitle.getText();
							String content = tfContent.getText();
							
							
							String sql = "UPDATE diarytbl SET mdate='"+mdate+"', weather='"+weather+"', pic='"+pic+"', "
									+ "title='"+title+"', content='"+content+"' WHERE mdate='"+sdate+"' and id='"+sid+"' ";
							Statement stmt = conn.createStatement();
							stmt.executeUpdate(sql);
							
							JOptionPane.showMessageDialog(null, "수정 성공!");
							
							setVisible(false);
							
							} catch (Exception e1) {
							e1.printStackTrace();
							}
						
					}
				});
//				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
//				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
