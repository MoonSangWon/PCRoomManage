package teamproJect;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class MenuSet extends JFrame {
	private JFrame frame = new JFrame("������");
	private JTextField searchField = new JTextField(7);
	private JTable table = new JTable();
	private JTextArea ta = new JTextArea(12, 38);//�Ʒ� �гο� ����
	private JScrollPane scroll = new JScrollPane(ta);
	private JPanel centerpanel = new JPanel();
	private JPanel southpanel = new JPanel();
	private JPanel otherpanel = new JPanel();
	private DefaultTableModel dtm;

	private JLabel searchLabel = new JLabel("ǰ��˻�");
	private JButton btn_a = new JButton("��ȸ");
	private JButton btn_b = new JButton("ǰ���߰�");
	private JButton btn_c = new JButton("ǰ�� ��ü �����");
	private JButton btn_d = new JButton("ǰ�����");
	private JButton searchBtn = new JButton("�˻�");
	private Dimension dimen_a;
	private Dimension dimen_b;
	private int xpos, ypos;
	public static boolean doSearch = false; // ��ȸ��ư�� �������� �Ǻ��ϱ� ���� ���
	static boolean doItemSearch = true;
	
	public MenuSet() {// ������
		init();

		frame.setSize(600, 600);
		dimen_a = Toolkit.getDefaultToolkit().getScreenSize();
		dimen_b = Toolkit.getDefaultToolkit().getScreenSize();
		xpos = dimen_a.width / 5;
		ypos = dimen_b.height / 5;

		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.setLocation(xpos, ypos);
		frame.setResizable(false);
		frame.setVisible(true);

	}

	public void init() {// ȭ�� ���� ���Ϻ�
		String[] str = { "ǰ������", "ǰ��", "����", "���" };
		dtm = new DefaultTableModel(str, 0) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		southpanel.setBackground(Color.blue);

		// �̺�Ʈ ó����
		btn_a.addActionListener(new SearchEvent());
		btn_b.addActionListener(new InsertEvent());
		btn_c.addActionListener(new ChangeEvent());
		btn_d.addActionListener(new DeleteEvent());
		searchBtn.addActionListener(new ItemSearchEvent());
		
		ta.setFont(new Font("Serif",Font.BOLD, 20));
		ta.setEditable(true);
		scroll.setPreferredSize(new Dimension(100, 100));
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		// ���պ�
		table = new JTable(dtm);
		scroll = new JScrollPane(table);
		otherpanel.add(scroll);
		centerpanel.add(scroll);
		southpanel.add(btn_a);
		southpanel.add(btn_b);
		southpanel.add(btn_c);
		southpanel.add(btn_d);
		southpanel.add(searchLabel);
		southpanel.add(searchField);
		southpanel.add(searchBtn);
	
		frame.add(scroll,"Center");
		frame.add(southpanel, "South");

	}

	// ��ȸ ��ư ó���ϱ� ���� �̺�Ʈ Ŭ���� ����
	private class SearchEvent implements ActionListener {
		public void actionPerformed(ActionEvent e) {
				resetRow();
				doSearch = true;
				String[] stock = { "����", "���ڱ�", "1000", "17"};
				String[] stock1 ={"����", "��������Ʈ", "2500", "10"};
				String[] stock2 ={"����", "���̼Ҵ�(������)", "1000", "20"};
				String[] stock3 ={"���", "���������", "99", "89"};
				String[] stock4 ={"����", "������", "500", "50"};
				String[] stock5 ={"����", "����", "900", "30"};
				String[] stock6 ={"����", "�Ұ�����", "1500", "0"};
				String[] stock7 ={"���", "�Ҵߺ�����", "1200", "0"};
				String[] stock8 ={"����", "�����", "1500", "20"};
				String[] stock9 ={"����", "�����̽�ġŲ����", "2000", "5"};
				String[] stock10 ={"���", "�Ŷ��", "1200", "0"};
				String[] stock11 ={"����", "�������꽺", "1200", "30"};
				String[] stock12 ={"����", "��û����", "1300", "40"};
				String[] stock13 ={"���", "¥¥�δ�", "1200", "5"};
				String[] stock14 ={"���", "�������", "1000", "2"};
				String[] stock15 ={"����", "ĥ�����̴�", "900", "50"};
				String[] stock16 ={"����", "���ڹ���", "1500", "10"};
				String[] stock17 ={"����", "�ֽĽ�", "1000", "20"};
				DefaultTableModel dtm = (DefaultTableModel) table.getModel();
				dtm.addRow(stock);dtm.addRow(stock1);dtm.addRow(stock2);dtm.addRow(stock3);
				dtm.addRow(stock4);dtm.addRow(stock5);dtm.addRow(stock6);dtm.addRow(stock7);
				dtm.addRow(stock8);dtm.addRow(stock9);dtm.addRow(stock10);dtm.addRow(stock11);
				dtm.addRow(stock12);dtm.addRow(stock13);dtm.addRow(stock14);dtm.addRow(stock15);
				dtm.addRow(stock16);dtm.addRow(stock17);
				
			
			}
		}
	// ��ȸ ó���� ���� â�� ���� ���� Ŭ���� ����

	// ǰ�� �߰� â�� ���� ���� �̺�Ʈ Ŭ��������
	private class InsertEvent implements ActionListener {
		public void actionPerformed(ActionEvent e) {
	        try {                
                myDialog dialog = new myDialog(ypos);
                 dialog.setVisible(true);
             }
             catch(NumberFormatException e1) {}   
          }
	}


	// ǰ�� �߰� â�� ���� ���� �̺�Ʈ Ŭ��������
	// ��� ���� â�� ���� ���� �̺�Ʈ Ŭ���� ����
	private class ChangeEvent implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			resetRow();
		
		}
	}
	// ��� ���渦 ���� â�� ���� ���� Ŭ���� ����
	 class myDialog extends JDialog{
	      private JTextField stockF = new JTextField(7);
	      private JTextField nameF = new JTextField(7);
	      private JTextField priceF = new JTextField(7);
	      private JTextField countF = new JTextField(7);
	      private JButton okButton = new JButton("�߰�");
	      private JButton noButton = new JButton("���");
	      
	      public myDialog(int a) {
	          setLayout(null);
	          JLabel stock = new JLabel("ǰ������");
	          JLabel name = new JLabel("ǰ���̸�");
	          JLabel price = new JLabel("����");
	          JLabel count = new JLabel("���");

	          stock.setBounds(20, 5, 70,50);
	          stockF.setBounds(80, 15, 120, 30);
	          name.setBounds(20,40,70,50);
	          nameF.setBounds(80, 50, 120, 30);
	          price.setBounds(30, 75, 80, 50);
	          priceF.setBounds(80, 85, 60, 30);
	          count.setBounds(30, 110, 80, 50);
	          countF.setBounds(80, 120, 60, 30);
	          okButton.setBounds(30, 150, 100, 50);
	          noButton.setBounds(130, 150, 100,50);
	          add(stock);add(stockF);add(name);add(nameF);
	          add(price);add(priceF);add(count);add(countF);
	          add(okButton);add(noButton);
	          setSize(300,250);
	          okButton.addActionListener(new ActionListener() {
	             @Override
	             public void actionPerformed(ActionEvent e) {
	                try { //Int���� �ޱ�
	    				Integer.parseInt(priceF.getText());
	    				Integer.parseInt(countF.getText());
	    			} catch (NumberFormatException g) {
	    				JOptionPane.showMessageDialog(null, "���� �� ���� ���� ���ڸ� �����մϴ�");
	    				return;
	    			}
	                if(stockF.getText().trim().equals("") || nameF.getText().trim().equals("")
	             		   || priceF.getText().trim().equals("") || countF.getText().trim().equals(""))
	                {
	                	JOptionPane.showMessageDialog(null, "�� ĭ�� �Է��� �ּ���");
	                }
	                else {
	                	String[] insert = new String[4];
	                	insert[0] = stockF.getText();
	                	insert[1] = nameF.getText();
	                	insert[2] = priceF.getText();
	                	insert[3] = countF.getText();
	                	
	                	DefaultTableModel dtm = (DefaultTableModel) table.getModel();
	                	dtm.addRow(insert); 
	                	setVisible(false);
	                }
	             }
	          });
	          noButton.addActionListener(new ActionListener() {
	             @Override
	             public void actionPerformed(ActionEvent e) {
	                setVisible(false);
	                //aCount.setText(null);nameField.setText(null);
	             }
	          });
	      }
	 }
	// ���� ó���� ���� â�� ���� ���� Ŭ���� ����
	private class DeleteEvent implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if (doSearch == true) { //��ȸ ��ư ������ �� 
				//row�� -1�̶�� ���õ� ���� ���� ���̱� ������ -1�� ����
				if(table.getSelectedRow() != -1) { //������ ���̺��� �����ϱ� 
						String item = (String) dtm.getValueAt(table.getSelectedRow(), 1); //���õ� ���� ù��° ���� �� ��������
						DefaultTableModel dtm =  (DefaultTableModel) table.getModel();
						dtm.removeRow(table.getSelectedRow());
						JOptionPane.showMessageDialog(null,  "ǰ�� \'" + item + "\'�� �����Ǿ����ϴ�");
			}else {
				JOptionPane.showMessageDialog(null, "������ ǰ���� ���� ������ �ּ���");
			}
		}else {
				JOptionPane.showMessageDialog(null, "������ ���� ��ȸ�� ���� ���ּ���");
			}				
		}
	}
	// ���� ó���� ���� â�� ���� ���� Ŭ���� ����
	// ǰ��˻� ó���� ���� Ŭ���� ����
	private class ItemSearchEvent implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			String item;
			if (doSearch == true) {
				if (searchField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "�˻��� ǰ����� �Է� ���ּ���");
					return;
				}

				if (dtm.getRowCount() > 0) {
					item = searchField.getText();
					for (int i = 0; i < dtm.getRowCount(); i++) {
						if (item.equals((String) ((Vector) dtm.getDataVector().elementAt(i)).elementAt(1))) {
							doItemSearch = true;
							String itemType = (String) ((Vector) dtm.getDataVector().elementAt(i)).elementAt(0);
							item = (String) ((Vector) dtm.getDataVector().elementAt(i)).elementAt(1);
							String price = (String) ((Vector) dtm.getDataVector().elementAt(i)).elementAt(2);
							String stock = (String) ((Vector) dtm.getDataVector().elementAt(i)).elementAt(3);
							resetRow();
							String[] add = { itemType, item, price, stock };
							dtm.addRow(add);
							JOptionPane.showMessageDialog(null, "ǰ��\'" + item+ "\'�� �˻��Ǿ����ϴ�.");
							searchField.setText(""); //�˻��ϸ� �ؽ�Ʈ�ʵ� �ʱ�ȭ
							doSearch = false; //�ʱ�ȭ �� �����̱⿡ ����ȸ�ϱ�
							return;
						}
					}
					JOptionPane.showMessageDialog(null, "�˻��� ǰ���� ���� ���� �ʽ��ϴ�.");
				} else {
					JOptionPane.showMessageDialog(null, "�˻��� ǰ���� ���� ���� �ʽ��ϴ�.");
				}

			} else {
				JOptionPane.showMessageDialog(null, "��ȸ�� ���� ���ּ���");
			}
		}
		

	}
	// ǰ��˻� ó���� ���� Ŭ���� ����

	// ���̺��� ��� �����͸� �����ϴ� �޼ҵ� ����
	private void resetRow() {
		if (dtm.getRowCount() > 0) {
			for (int i = dtm.getRowCount() - 1; i > -1; i--) {
				dtm.removeRow(i);
			}
		}
	}
	
}