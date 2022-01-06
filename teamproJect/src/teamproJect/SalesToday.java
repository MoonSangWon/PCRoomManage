package teamproJect;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class SalesToday extends JFrame implements ActionListener {
	private JTextField tf1 = new JTextField(10);//자리 받는 필드
	private JTextField tf2 = new JTextField(10);//
	private JTextField tf3 = new JTextField(2);//시간 필드
	private JTextField tf4 = new JTextField(2);//분 필드
	private JTextField tf5 = new JTextField(10);//요금 받는 필드(액션 작용하는거)
	private JTextArea ta = new JTextArea(12, 38);//아래 패널에 적용
	Date today=new Date();//오늘 날짜 받는 함수
	SimpleDateFormat sdf1=new SimpleDateFormat();//년도 월 일로 포멧하는 함수
	JLabel money= new JLabel("");//출력할 요금 라벨
	private int sum = 0;
	private int hour,minute,num,mon;
	private String name="";
	
	public SalesToday() {		
		setSize(600, 600);
		setTitle("오늘의 매상");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout(4, 4));
		
		Date today = new Date();
		
		SimpleDateFormat sdf1= new SimpleDateFormat();
		String fileName = sdf1.format(today)+".dat"; 
		
		// 프레임 화면 중앙 배열
		Dimension frameSize = this.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screenSize.width - frameSize.width) / 2,
				(screenSize.height - frameSize.height) / 2);
		

		// 센터들어가는 중간패널
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		// panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		Border blackBorder = BorderFactory.createLineBorder(Color.BLACK);
		panel.setBorder(blackBorder);
		JLabel la = new JLabel("** 정보 입력 **");
		JLabel time = new JLabel("시간");
		JLabel min = new JLabel("분");
		panel.add(la);
		panel.add(tf1); panel.add(tf2); 
		panel.add(tf3); panel.add(time);
		panel.add(tf4); panel.add(min); panel.add(tf5);
		tf5.addActionListener(this);
		ta.setFont(new Font("Serif",Font.BOLD, 20));
		ta.setBounds(20, 60, 200, 400);
		ta.setEditable(false);
		panel.add(ta);
		
		// 테이블
		JPanel 위패널 = new JPanel();
		JLabel ip = new JLabel("자리번호    |" + "    닉네임    |" 
					+ "    시간     |" + "    요금 ");
		ip.setFont(new Font("Serif", Font.BOLD, 30));
		ip.setForeground(Color.BLACK);
		위패널.add(ip);
		
		// 맨아래들어가는 아래패널
		
		JPanel 아래패널 = new JPanel();
		money= new JLabel(sdf1.format(today)+" 총 매상 : "+ sum +"원");
		money.setFont(new Font("Serif", Font.BOLD, 30));
		money.setForeground(Color.red);
		아래패널.add(money);
		
		
		add(위패널 , BorderLayout.NORTH);
		add(panel, BorderLayout.CENTER);
		add(아래패널, BorderLayout.SOUTH);
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i = 0; i<4;i++) {
			switch(i) {
			case 0:ta.append(tf1.getText() + "번 자리 " + " | ");break;
			case 1:ta.append(tf2.getText() + " | ");break;
			case 2:ta.append(tf3.getText() + "시간 " + tf4.getText() + "분" + " | ");break;
			case 3:ta.append(tf5.getText() + " 원 " + "\n");break;
			}
		}
		sum+=Integer.parseInt(tf5.getText());
		money.setText(sdf1.format(today)+" 총 매상 : "+ sum +"원");
		 tf1.setText("");tf2.setText("");
	      tf3.setText("");tf4.setText("");
	      tf5.setText("");
	}
	
	
	public void setField(int num, int hour, int minute, int mon) {
		
		this.mon=mon;
		this.hour=hour;
		this.minute=minute;
		this.num=num;
		sum+=mon;
		
	}
	public void setName(String name) {
		this.name=name;
	}
	public void setMoney() {
		ta.append(num+"번 자리"+ " | "+name+" | "+hour+"시간 "+minute+"분"+" | "+mon+"원"+"\n");
		money.setText(sdf1.format(today)+" 총 매상 : "+ sum +"원");
		
	}
	
	public static void main(String[] args) {
		new SalesToday();
	}

}