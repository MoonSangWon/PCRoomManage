package teamproJect;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

import teamproJect.Seat_panAb;

public class Seat extends Seat_panAb implements ActionListener, MouseListener{
	//좌석에 대한 필드들
   private ImageIcon icon = new ImageIcon("images/gameOff.png");
   private static final long serialVersionUID = 1L;
   private int money=0;
   private String time="00";
   private String min="00";
   private int x=170,y=100;
   public JButton [] btn = new JButton[50];
   String names="";
   JLabel[] label=new JLabel[50];
   JLabel[] la=new JLabel[50];
   JLabel[] la1=new JLabel[50];
   int hour,minute;
   PopupMenu pm = new PopupMenu();
   MenuItem pm_item1 = new MenuItem("계산");
   
   int set=0;
   @SuppressWarnings("deprecation")
   public Seat(int i) {
	   num = i;
      this.setLayout(new BorderLayout());
      // 팝업메뉴에 메뉴아이템 추가
      pm.add(pm_item1);
     
      
      add(pm); // 팝업메뉴를 프레임에 추가
         for(int j=0;j<btn.length;j++) {
        	names="빈자리";
            label[j]= new JLabel((i + 1) +". "+names);
            la[j]= new JLabel("요금 : " + money +"원");
            la1[j] = new JLabel("시간 : " + time + " : " + min);
            label[j].setForeground(new Color(0, 200, 150));
            la[j].setForeground(new Color(0, 200, 150));
            la1[j].setForeground(new Color(0, 200, 150));
            label[j].setFont(new Font("배달의민족 한나", 1, 13));
            la[j].setFont(new Font("배달의민족 한나", 1, 13));
            la1[j].setFont(new Font("배달의민족 한나", 1, 13));
            la[j].setHorizontalAlignment(JLabel.LEFT);
            
            btn[j] = new JButton(Integer.toString(j));
            btn[j].setLayout(new FlowLayout(FlowLayout.CENTER));
            btn[j].setIcon(icon);
            btn[j].setBounds(x, y, 99, 99);
            btn[j].setBackground(Color.BLACK);
            btn[j].setBorderPainted(false);
            btn[j].add(label[j]);
            btn[j].add(la[j]);
            btn[j].add(la1[j]);
            
            btn[j].addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                  try {
                     String a=e.getActionCommand();
                     //set =Integer.parseInt(e.getActionCommand());
                     MyDialog dialog = new MyDialog(Integer.parseInt(a));
                      dialog.setVisible(true);
                  }
                  catch(NumberFormatException e1) {}   
               }
            }); 
            btn[j].addMouseListener(new MouseAdapter() {
            	@Override
            	public void mousePressed(MouseEvent e) {
            		JButton btn = (JButton)e.getSource();
            		
            		if(e.getModifiers()==MouseEvent.BUTTON3_MASK&&isChecked==false) {
            			//우클릭 && 체크 해제
            			ImageIcon icon = new ImageIcon("images/check.png");//체크이미지로 바꿈
            			btn.setIcon(icon);
            			isChecked=true;
            			
            		}
            		else if(e.getModifiers()==MouseEvent.BUTTON3_MASK&&isChecked==true) {
            			//우클릭&&체크되어있을때
            			if(minute==0&&hour==0||money==0) {//빈자리일때
            				ImageIcon icon = new ImageIcon("images/gameOff.png");//게임 오프로 바꾸기
                			btn.setIcon(icon);
                			isChecked=false;
                		
            			}
            			else {
            				ImageIcon icon = new ImageIcon("images/gameOn.png");
                			btn.setIcon(icon);
                			isChecked=false;
                			pm.show(btn, e.getX(), e.getY());
            			}
            		}
            	}
            });
            
            add(btn[j]);
         }
         pm_item1.addActionListener((ActionListener) new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					SalesToday sa = new SalesToday();
					sa.setName(names);
					sa.setField(num+1,hour,minute,money);
					sa.setMoney();
					money=0;
					label[set].setText((num + 1) +". "+"빈자리");
					la[set].setText("요금 : " + money+"원");
					la1[set].setText("시간 : 00 : 00");
					ImageIcon icon = new ImageIcon("images/gameOff.png");
        			btn[set].setIcon(icon);
				}
				
			});
        
      setVisible(true);
      setOpaque(false);
      setFocusable(true);
   }
   class MyDialog extends JDialog{
      private JTextField nameField = new JTextField(10);
      private JTextField aCount = new JTextField(10);
      private JTextField timeCount = new JTextField(2);
      private JTextField minCount = new JTextField(2);
      private JButton okButton = new JButton("Ok");
      private JButton noButton = new JButton("Cancel");
      int a=0;
      
      public MyDialog(int a) {
         this.a=a;
         
         setLayout(null);
         setLocationRelativeTo(null);//화면 가운데 나오게
         JLabel name = new JLabel("이름");
         JLabel count = new JLabel("요금");
         JLabel timer = new JLabel("사용시간");
         JLabel si = new JLabel("시간");
         JLabel bun = new JLabel("분");
         
         name.setBounds(50, 5, 50,50);
         nameField.setBounds(80, 15, 120, 30);
         count.setBounds(50,40,50,50);
         aCount.setBounds(80, 50, 120, 30);
         timer.setBounds(25, 75, 80, 50);
         timeCount.setBounds(80, 85, 30, 30);
         si.setBounds(110, 75, 80, 50);
         minCount.setBounds(140, 85, 30, 30);
         bun.setBounds(170, 75, 80, 50);
         okButton.setBounds(30, 150, 100, 50);
         noButton.setBounds(130, 150, 100,50);
         
         add(name);add(nameField);add(count);add(aCount);
         add(timer);add(timeCount);add(si);add(minCount);add(bun);
         add(okButton);add(noButton);
         setSize(300,250);
         okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               setVisible(false);
               if(nameField.getText().trim().equals("") || aCount.getText().trim().equals("")
            		   || timeCount.getText().trim().equals("") || minCount.getText().trim().equals(""))
               {
            	   label[a].setText((num+1) + ". 빈자리");
            	   la[a].setText("요금 : " + "0원");
            	   la1[a].setText("시간 : 00 : 00");
            	   ImageIcon icon = new ImageIcon("images/gameOff.png");
            	   btn[a].setIcon(icon);
               }
               else {
            	set=a;
            	label[a].setText((num+1)+". "+nameField.getText());
               	la[a].setText("요금: "+aCount.getText()+"원");
               	la1[a].setText("시간 : " + timeCount.getText() 
               				+ " : " + minCount.getText());
               	
               	ImageIcon icon = new ImageIcon("images/gameOn.png");
                btn[a].setIcon(icon);
                money =Integer.parseInt(aCount.getText());
                hour = Integer.parseInt(timeCount.getText());
              	minute = Integer.parseInt(minCount.getText());
              	names=nameField.getText();
              	Timer timer = new Timer();
              	TimerTask task = new TimerTask() {
              		@Override
              		public void run() {
              			 //60초부터 카운트 다운
                             if (minute > 0) {//분이 0 보다 크면 1감소 -> 59초
                                 --minute;
                             }
                             else if (hour > 0) {//분은 0인데 시간이 0보다 크면 시간을 -1 감소, 분을 59로 설정
                                 --hour;
                                 minute = 59;
                             }
                             else {
                            	 
                                 timer.cancel();
                             }
                     	la1[a].setText("시간 : " + hour + " : " + minute);
                     	if(hour==0&&minute==0)
                     		la1[a].setText("사용종료");
              		}
              	};
              	timer.schedule(task, 60000, 60000);//60초 뒤 시작, 60초마다 반복
               }
               //aCount.setText(null);nameField.setText(null);
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
@Override
public void mouseClicked(MouseEvent e) {
	// TODO Auto-generated method stub
	
}
@Override
public void mouseEntered(MouseEvent e) {
	// TODO Auto-generated method stub
	
}
@Override
public void mouseExited(MouseEvent e) {
	// TODO Auto-generated method stub
	
}
@Override
public void mousePressed(MouseEvent e) {
	// TODO Auto-generated method stub
	
}
@Override
public void mouseReleased(MouseEvent e) {
	// TODO Auto-generated method stub
	
}
@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	
}
@Override
public void turnOn() {
	// TODO Auto-generated method stub
	
}
@Override
public void turnOff() {
	// TODO Auto-generated method stub
	
}
@Override
public void checkOn() {
	// TODO Auto-generated method stub
	
}
@Override
public void checkOff() {
	// TODO Auto-generated method stub
	
}
}