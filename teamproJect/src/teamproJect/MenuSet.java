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
	private JFrame frame = new JFrame("재고관리");
	private JTextField searchField = new JTextField(7);
	private JTable table = new JTable();
	private JTextArea ta = new JTextArea(12, 38);//아래 패널에 적용
	private JScrollPane scroll = new JScrollPane(ta);
	private JPanel centerpanel = new JPanel();
	private JPanel southpanel = new JPanel();
	private JPanel otherpanel = new JPanel();
	private DefaultTableModel dtm;

	private JLabel searchLabel = new JLabel("품목검색");
	private JButton btn_a = new JButton("조회");
	private JButton btn_b = new JButton("품목추가");
	private JButton btn_c = new JButton("품목 전체 지우기");
	private JButton btn_d = new JButton("품목삭제");
	private JButton searchBtn = new JButton("검색");
	private Dimension dimen_a;
	private Dimension dimen_b;
	private int xpos, ypos;
	public static boolean doSearch = false; // 조회버튼을 눌렀는지 판별하기 위해 사용
	static boolean doItemSearch = true;
	
	public MenuSet() {// 생성부
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

	public void init() {// 화면 구성 보일부
		String[] str = { "품목종류", "품목", "가격", "재고" };
		dtm = new DefaultTableModel(str, 0) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		southpanel.setBackground(Color.blue);

		// 이벤트 처리부
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
		
		// 결합부
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

	// 조회 버튼 처리하기 위한 이벤트 클래스 시작
	private class SearchEvent implements ActionListener {
		public void actionPerformed(ActionEvent e) {
				resetRow();
				doSearch = true;
				String[] stock = { "과자", "감자깡", "1000", "17"};
				String[] stock1 ={"과자", "다이제스트", "2500", "10"};
				String[] stock2 ={"음료", "데미소다(포도맛)", "1000", "20"};
				String[] stock3 ={"라면", "류현진라면", "99", "89"};
				String[] stock4 ={"과자", "맛동산", "500", "50"};
				String[] stock5 ={"음료", "맥콜", "900", "30"};
				String[] stock6 ={"버거", "불고기버거", "1500", "0"};
				String[] stock7 ={"라면", "불닭볶음면", "1200", "0"};
				String[] stock8 ={"과자", "새우깡", "1500", "20"};
				String[] stock9 ={"버거", "스파이시치킨버거", "2000", "5"};
				String[] stock10 ={"라면", "신라면", "1200", "0"};
				String[] stock11 ={"음료", "오렌지쥬스", "1200", "30"};
				String[] stock12 ={"과자", "조청유과", "1300", "40"};
				String[] stock13 ={"라면", "짜짜로니", "1200", "5"};
				String[] stock14 ={"라면", "참깨라면", "1000", "2"};
				String[] stock15 ={"음료", "칠성사이다", "900", "50"};
				String[] stock16 ={"버거", "피자버거", "1500", "10"};
				String[] stock17 ={"음료", "핫식스", "1000", "20"};
				DefaultTableModel dtm = (DefaultTableModel) table.getModel();
				dtm.addRow(stock);dtm.addRow(stock1);dtm.addRow(stock2);dtm.addRow(stock3);
				dtm.addRow(stock4);dtm.addRow(stock5);dtm.addRow(stock6);dtm.addRow(stock7);
				dtm.addRow(stock8);dtm.addRow(stock9);dtm.addRow(stock10);dtm.addRow(stock11);
				dtm.addRow(stock12);dtm.addRow(stock13);dtm.addRow(stock14);dtm.addRow(stock15);
				dtm.addRow(stock16);dtm.addRow(stock17);
				
			
			}
		}
	// 조회 처리를 위한 창을 띄우기 위한 클래스 종료

	// 품목 추가 창을 띄우기 위한 이벤트 클래스시작
	private class InsertEvent implements ActionListener {
		public void actionPerformed(ActionEvent e) {
	        try {                
                myDialog dialog = new myDialog(ypos);
                 dialog.setVisible(true);
             }
             catch(NumberFormatException e1) {}   
          }
	}


	// 품목 추가 창을 띄우기 위한 이벤트 클래스종료
	// 재고 변경 창을 띄우기 위한 이벤트 클래스 시작
	private class ChangeEvent implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			resetRow();
		
		}
	}
	// 재고 변경를 위한 창을 띄우기 위한 클래스 종료
	 class myDialog extends JDialog{
	      private JTextField stockF = new JTextField(7);
	      private JTextField nameF = new JTextField(7);
	      private JTextField priceF = new JTextField(7);
	      private JTextField countF = new JTextField(7);
	      private JButton okButton = new JButton("추가");
	      private JButton noButton = new JButton("취소");
	      
	      public myDialog(int a) {
	          setLayout(null);
	          JLabel stock = new JLabel("품목종류");
	          JLabel name = new JLabel("품목이름");
	          JLabel price = new JLabel("가격");
	          JLabel count = new JLabel("재고");

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
	                try { //Int값만 받기
	    				Integer.parseInt(priceF.getText());
	    				Integer.parseInt(countF.getText());
	    			} catch (NumberFormatException g) {
	    				JOptionPane.showMessageDialog(null, "가격 및 수량 재고는 숫자만 가능합니다");
	    				return;
	    			}
	                if(stockF.getText().trim().equals("") || nameF.getText().trim().equals("")
	             		   || priceF.getText().trim().equals("") || countF.getText().trim().equals(""))
	                {
	                	JOptionPane.showMessageDialog(null, "빈 칸을 입력해 주세요");
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
	// 삭제 처리를 위한 창을 띄우기 위한 클래스 시작
	private class DeleteEvent implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if (doSearch == true) { //조회 버튼 눌렸을 시 
				//row가 -1이라면 선택된 행이 없는 것이기 때문에 -1을 리턴
				if(table.getSelectedRow() != -1) { //선택한 테이블을 삭제하기 
						String item = (String) dtm.getValueAt(table.getSelectedRow(), 1); //선택된 행의 첫번째 열의 값 가져오기
						DefaultTableModel dtm =  (DefaultTableModel) table.getModel();
						dtm.removeRow(table.getSelectedRow());
						JOptionPane.showMessageDialog(null,  "품목 \'" + item + "\'이 삭제되었습니다");
			}else {
				JOptionPane.showMessageDialog(null, "삭제할 품목을 먼저 선택해 주세요");
			}
		}else {
				JOptionPane.showMessageDialog(null, "삭제를 위해 조회를 먼저 해주세요");
			}				
		}
	}
	// 삭제 처리를 위한 창을 띄우기 위한 클래스 종료
	// 품목검색 처리를 위한 클래스 시작
	private class ItemSearchEvent implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			String item;
			if (doSearch == true) {
				if (searchField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "검색할 품목명을 입력 해주세요");
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
							JOptionPane.showMessageDialog(null, "품목\'" + item+ "\'이 검색되었습니다.");
							searchField.setText(""); //검색하면 텍스트필드 초기화
							doSearch = false; //초기화 후 공백이기에 재조회하기
							return;
						}
					}
					JOptionPane.showMessageDialog(null, "검색할 품목이 존재 하지 않습니다.");
				} else {
					JOptionPane.showMessageDialog(null, "검색할 품목이 존재 하지 않습니다.");
				}

			} else {
				JOptionPane.showMessageDialog(null, "조회를 먼저 해주세요");
			}
		}
		

	}
	// 품목검색 처리를 위한 클래스 종료

	// 테이블의 모든 데이터를 삭제하는 메소드 시작
	private void resetRow() {
		if (dtm.getRowCount() > 0) {
			for (int i = dtm.getRowCount() - 1; i > -1; i--) {
				dtm.removeRow(i);
			}
		}
	}
	
}