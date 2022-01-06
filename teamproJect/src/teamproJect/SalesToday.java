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
	private JTextField tf1 = new JTextField(10);//�ڸ� �޴� �ʵ�
	private JTextField tf2 = new JTextField(10);//
	private JTextField tf3 = new JTextField(2);//�ð� �ʵ�
	private JTextField tf4 = new JTextField(2);//�� �ʵ�
	private JTextField tf5 = new JTextField(10);//��� �޴� �ʵ�(�׼� �ۿ��ϴ°�)
	private JTextArea ta = new JTextArea(12, 38);//�Ʒ� �гο� ����
	Date today=new Date();//���� ��¥ �޴� �Լ�
	SimpleDateFormat sdf1=new SimpleDateFormat();//�⵵ �� �Ϸ� �����ϴ� �Լ�
	JLabel money= new JLabel("");//����� ��� ��
	private int sum = 0;
	private int hour,minute,num,mon;
	private String name="";
	
	public SalesToday() {		
		setSize(600, 600);
		setTitle("������ �Ż�");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout(4, 4));
		
		Date today = new Date();
		
		SimpleDateFormat sdf1= new SimpleDateFormat();
		String fileName = sdf1.format(today)+".dat"; 
		
		// ������ ȭ�� �߾� �迭
		Dimension frameSize = this.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screenSize.width - frameSize.width) / 2,
				(screenSize.height - frameSize.height) / 2);
		

		// ���͵��� �߰��г�
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		// panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		Border blackBorder = BorderFactory.createLineBorder(Color.BLACK);
		panel.setBorder(blackBorder);
		JLabel la = new JLabel("** ���� �Է� **");
		JLabel time = new JLabel("�ð�");
		JLabel min = new JLabel("��");
		panel.add(la);
		panel.add(tf1); panel.add(tf2); 
		panel.add(tf3); panel.add(time);
		panel.add(tf4); panel.add(min); panel.add(tf5);
		tf5.addActionListener(this);
		ta.setFont(new Font("Serif",Font.BOLD, 20));
		ta.setBounds(20, 60, 200, 400);
		ta.setEditable(false);
		panel.add(ta);
		
		// ���̺�
		JPanel ���г� = new JPanel();
		JLabel ip = new JLabel("�ڸ���ȣ    |" + "    �г���    |" 
					+ "    �ð�     |" + "    ��� ");
		ip.setFont(new Font("Serif", Font.BOLD, 30));
		ip.setForeground(Color.BLACK);
		���г�.add(ip);
		
		// �ǾƷ����� �Ʒ��г�
		
		JPanel �Ʒ��г� = new JPanel();
		money= new JLabel(sdf1.format(today)+" �� �Ż� : "+ sum +"��");
		money.setFont(new Font("Serif", Font.BOLD, 30));
		money.setForeground(Color.red);
		�Ʒ��г�.add(money);
		
		
		add(���г� , BorderLayout.NORTH);
		add(panel, BorderLayout.CENTER);
		add(�Ʒ��г�, BorderLayout.SOUTH);
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i = 0; i<4;i++) {
			switch(i) {
			case 0:ta.append(tf1.getText() + "�� �ڸ� " + " | ");break;
			case 1:ta.append(tf2.getText() + " | ");break;
			case 2:ta.append(tf3.getText() + "�ð� " + tf4.getText() + "��" + " | ");break;
			case 3:ta.append(tf5.getText() + " �� " + "\n");break;
			}
		}
		sum+=Integer.parseInt(tf5.getText());
		money.setText(sdf1.format(today)+" �� �Ż� : "+ sum +"��");
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
		ta.append(num+"�� �ڸ�"+ " | "+name+" | "+hour+"�ð� "+minute+"��"+" | "+mon+"��"+"\n");
		money.setText(sdf1.format(today)+" �� �Ż� : "+ sum +"��");
		
	}
	
	public static void main(String[] args) {
		new SalesToday();
	}

}