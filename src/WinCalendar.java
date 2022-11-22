import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WinCalendar extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Calendar now;
	private JPanel panelCalendar;
	private JComboBox cbMonth;
	private JComboBox cbYear;
	private JPanel panel_1;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private String retDate;
	private String sday;

	public String getDate() {
			
		return retDate;
	}
	
	
	

	/**
	 * Create the dialog.
	 */
	public WinCalendar() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				Component compList[] = panelCalendar.getComponents();
				for(Component c : compList) {
					if(c instanceof JButton) {
						panelCalendar.remove(c);
					}
					panelCalendar.revalidate();
					panelCalendar.repaint();
				}
				
				String day[] = {"일","월","화","수","목","금","토"};
				int Months[] = {31,28,31,30,31,30,31,31,30,31,30,31};
				int year = (int)cbYear.getSelectedItem();
				int month = (int)cbMonth.getSelectedItem();
				for(int i=0; i<7; i++) {
					JButton btn = new JButton(day[i]);
					btn.setBackground(Color.pink);
					panelCalendar.add(btn);
				}
				
				
				
				
				// 1922년 1월 1일 일요일 시작
				int index = 0;
				int sum = 0;
				
				for(int i =1922; i<year; i++) {
					if(i%4==0 && i%100 !=0 || i%400==0) {
						sum = sum + 366;
					}
					else {
						sum = sum + 365;
					}
				}
				
				int lastDay =0;
				
				for(int i = 0; i<month-1; i++) {
					if(i ==1 && (year % 4 ==0 && year%100!=0 || year%400==0)) {
						sum = sum + ++Months[i];
						
					}
					else {
						sum = sum + Months[i];
					}
					
				}
				if(month == 2 && (year % 4 ==0 && year%100!=0 || year%400==0)) {
					lastDay = ++Months[month-1];
				}
				else {
					lastDay = Months[month-1];
				}
				
				index = (index+sum)%7;
				
				for(int i =1 ; i<=index; i++) {
					JButton btn = new JButton("");
					panelCalendar.add(btn);
					btn.setVisible(false);
				}
				
				for(int i=1; i<=lastDay; i++) {
					JButton btn = new JButton(i+"");
					panelCalendar.add(btn);
					
					//-------------------- 달력 버튼 입력 시 값 가져오기 ------------------------------//
					btn.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							JButton btn1 = (JButton)e.getSource();
							retDate = cbYear.getSelectedItem() + "-" + cbMonth.getSelectedItem() + "-" + btn1.getText();
							setVisible(false);
						}
					});
					//-------------------- 달력 버튼 입력 시 값 가져오기 ------------------------------//
				}
				panelCalendar.revalidate();
			}
		});
		setTitle("달력");
		setBounds(100, 100, 514, 385);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.NORTH);
			{
				cbYear = new JComboBox();
				cbYear.setEditable(true);
				
				
				for(int i = 1922; i<=2100; i++) {
					cbYear.addItem(i);
				}
				now = Calendar.getInstance();
				{
					btnNewButton_4 = new JButton("<<");
					btnNewButton_4.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							int year = cbYear.getSelectedIndex();
							year --;
							if(year<0) {
								year = cbYear.getItemCount();
								
							}
							cbYear.setSelectedIndex(year);
						}
					});
					panel.add(btnNewButton_4);
					
				}
				{
					btnNewButton_3 = new JButton("<");
					btnNewButton_3.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							int month = cbMonth.getSelectedIndex();
							month --;
							if(month<0) {
								month = 11;
								
							}
							cbMonth.setSelectedIndex(month);
							
						}
					});
					panel.add(btnNewButton_3);
				}
				cbYear.setSelectedItem(now.get(Calendar.YEAR));
				panel.add(cbYear);
				cbYear.addActionListener(new ActionListener() {
					
					
					
					public void actionPerformed(ActionEvent e) {
						Component compList[] = panelCalendar.getComponents();
						for(Component c : compList) {
							if(c instanceof JButton) {
								panelCalendar.remove(c);
							}
							panelCalendar.revalidate();
							panelCalendar.repaint();
						}
						
						String day[] = {"일","월","화","수","목","금","토"};
						int Months[] = {31,28,31,30,31,30,31,31,30,31,30,31};
						int year = (int)cbYear.getSelectedItem();
						int month = (int)cbMonth.getSelectedItem();
						for(int i=0; i<7; i++) {
							JButton btn = new JButton(day[i]);
							btn.setBackground(Color.pink);
							panelCalendar.add(btn);
						}
						
						// 1922년 1월 1일 일요일 시작
						int index = 0;
						int sum = 0;
						
						for(int i =1922; i<year; i++) {
							if(i%4==0 && i%100 !=0 || i%400==0) {
								sum = sum + 366;
							}
							else {
								sum = sum + 365;
							}
						}
						
						int lastDay =0;
						
						for(int i = 0; i<month-1; i++) {
							if(i ==1 && (year % 4 ==0 && year%100!=0 || year%400==0)) {
								sum = sum + ++Months[i];
								
							}
							else {
								sum = sum + Months[i];
							}
							
						}
						if(month == 2 && (year % 4 ==0 && year%100!=0 || year%400==0)) {
							lastDay = ++Months[month-1];
						}
						else {
							lastDay = Months[month-1];
						}
						
						index = (index+sum)%7;
						
						for(int i =1 ; i<=index; i++) {
							JButton btn = new JButton("");
							panelCalendar.add(btn);
							btn.setVisible(false);
						}
						
						for(int i=1; i<=lastDay; i++) {
							JButton btn = new JButton(i+"일");
							panelCalendar.add(btn);
							
							//-------------------- 달력 버튼 입력 시 값 가져오기 ------------------------------//
							btn.addActionListener(new ActionListener() {
								
								@Override
								public void actionPerformed(ActionEvent e) {
									// TODO Auto-generated method stub
									JButton btn1 = (JButton)e.getSource();
									retDate = cbYear.getSelectedItem() + "-" + cbMonth.getSelectedItem() + "-" + btn1.getText();
									setVisible(false);
								}
							});
							//-------------------- 달력 버튼 입력 시 값 가져오기 ------------------------------//
						}
						
						panelCalendar.revalidate();
					}
				});
			}
			{
				cbMonth = new JComboBox();
				cbMonth.setEditable(true);
				
				for(int i = 1; i<=12; i++) {
					cbMonth.addItem(i);
				}
				cbMonth.setSelectedItem(now.get(Calendar.MONTH)+1);
				panel.add(cbMonth);
				
				cbMonth.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						
						
						Component compList[] = panelCalendar.getComponents();
						for(Component c : compList) {
							if(c instanceof JButton) {
								panelCalendar.remove(c);
							}
							panelCalendar.revalidate();
							panelCalendar.repaint();
						}
						
						String day[] = {"일","월","화","수","목","금","토"};
						int Months[] = {31,28,31,30,31,30,31,31,30,31,30,31};
						int year = (int)cbYear.getSelectedItem();
						int month = (int)cbMonth.getSelectedItem();
						for(int i=0; i<7; i++) {
							JButton btn = new JButton(day[i]);
							btn.setBackground(Color.pink);
							panelCalendar.add(btn);
						}
						
						// 1922년 1월 1일 일요일 시작
						int index = 0;
						int sum = 0;
						
						for(int i =1922; i<year; i++) {
							if(i%4==0 && i%100 !=0 || i%400==0) {
								sum = sum + 366;
							}
							else {
								sum = sum + 365;
							}
						}
						
						int lastDay =0;
						
						for(int i = 0; i<month-1; i++) {
							if(i ==1 && (year % 4 ==0 && year%100!=0 || year%400==0)) {
								sum = sum + ++Months[i];
								
							}
							else {
								sum = sum + Months[i];
							}
							
						}
						if(month == 2 && (year % 4 ==0 && year%100!=0 || year%400==0)) {
							lastDay = ++Months[month-1];
						}
						else {
							lastDay = Months[month-1];
						}
						
						index = (index+sum)%7;
						
						for(int i =1 ; i<=index; i++) {
							JButton btn = new JButton("");
							panelCalendar.add(btn);
							btn.setVisible(false);
						}
						
						for(int i=1; i<=lastDay; i++) {
							JButton btn = new JButton(i+"일");
							panelCalendar.add(btn);
							
							//-------------------- 달력 버튼 입력 시 값 가져오기 ------------------------------//
							btn.addActionListener(new ActionListener() {
								
								@Override
								public void actionPerformed(ActionEvent e) {
									// TODO Auto-generated method stub
									JButton btn1 = (JButton)e.getSource();
									retDate = cbYear.getSelectedItem() + "-" + cbMonth.getSelectedItem() + "-" + btn1.getText();
									setVisible(false);
								}
							});
							//-------------------- 달력 버튼 입력 시 값 가져오기 ------------------------------//
						}
						
						panelCalendar.revalidate();
					}
				});
			}
			{
				btnNewButton_1 = new JButton(">");
				btnNewButton_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int month = cbMonth.getSelectedIndex();
						month ++;
						if(month>=12) {
							month = 0;
							
						}
						cbMonth.setSelectedIndex(month);
					}
				});
				panel.add(btnNewButton_1);
			}
			{
				btnNewButton_2 = new JButton(">>");
				btnNewButton_2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int year = cbYear.getSelectedIndex();
						year ++;
						if(year>=cbYear.getItemCount()) {
							year = 0;
							
						}
						cbYear.setSelectedIndex(year);
					}
				});
				panel.add(btnNewButton_2);
			}
		}
		{
			panelCalendar = new JPanel();
			contentPanel.add(panelCalendar, BorderLayout.CENTER);
			panelCalendar.setLayout(new GridLayout(0, 7, 0, 0));
		}
		{
			panel_1 = new JPanel();
			contentPanel.add(panel_1, BorderLayout.SOUTH);
			panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				btnNewButton = new JButton("확인");
				panel_1.add(btnNewButton);
			}
		}
	}

}
