package teamproJect;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

import teamproJect.Seat_panAb;

public class Seat extends Seat_panAb implements ActionListener, MouseListener{
	//�¼��� ���� �ʵ��
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
   MenuItem pm_item1 = new MenuItem("���");
   
   int set=0;
   @SuppressWarnings("deprecation")
   public Seat(int i) {
	   num = i;
      this.setLayout(new BorderLayout());
      // �˾��޴��� �޴������� �߰�
      pm.add(pm_item1);
     
      
      add(pm); // �˾��޴��� �����ӿ� �߰�
         for(int j=0;j<btn.length;j++) {
        	names="���ڸ�";
            label[j]= new JLabel((i + 1) +". "+names);
            la[j]= new JLabel("��� : " + money +"��");
            la1[j] = new JLabel("�ð� : " + time + " : " + min);
            label[j].setForeground(new Color(0, 200, 150));
            la[j].setForeground(new Color(0, 200, 150));
            la1[j].setForeground(new Color(0, 200, 150));
            label[j].setFont(new Font("����ǹ��� �ѳ�", 1, 13));
            la[j].setFont(new Font("����ǹ��� �ѳ�", 1, 13));
            la1[j].setFont(new Font("����ǹ��� �ѳ�", 1, 13));
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
            			//��Ŭ�� && üũ ����
            			ImageIcon icon = new ImageIcon("images/check.png");//üũ�̹����� �ٲ�
            			btn.setIcon(icon);
            			isChecked=true;
            			
            		}
            		else if(e.getModifiers()==MouseEvent.BUTTON3_MASK&&isChecked==true) {
            			//��Ŭ��&&üũ�Ǿ�������
            			if(minute==0&&hour==0||money==0) {//���ڸ��϶�
            				ImageIcon icon = new ImageIcon("images/gameOff.png");//���� ������ �ٲٱ�
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
					label[set].setText((num + 1) +". "+"���ڸ�");
					la[set].setText("��� : " + money+"��");
					la1[set].setText("�ð� : 00 : 00");
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
         setLocationRelativeTo(null);//ȭ�� ��� ������
         JLabel name = new JLabel("�̸�");
         JLabel count = new JLabel("���");
         JLabel timer = new JLabel("���ð�");
         JLabel si = new JLabel("�ð�");
         JLabel bun = new JLabel("��");
         
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
            	   label[a].setText((num+1) + ". ���ڸ�");
            	   la[a].setText("��� : " + "0��");
            	   la1[a].setText("�ð� : 00 : 00");
            	   ImageIcon icon = new ImageIcon("images/gameOff.png");
            	   btn[a].setIcon(icon);
               }
               else {
            	set=a;
            	label[a].setText((num+1)+". "+nameField.getText());
               	la[a].setText("���: "+aCount.getText()+"��");
               	la1[a].setText("�ð� : " + timeCount.getText() 
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
              			 //60�ʺ��� ī��Ʈ �ٿ�
                             if (minute > 0) {//���� 0 ���� ũ�� 1���� -> 59��
                                 --minute;
                             }
                             else if (hour > 0) {//���� 0�ε� �ð��� 0���� ũ�� �ð��� -1 ����, ���� 59�� ����
                                 --hour;
                                 minute = 59;
                             }
                             else {
                            	 
                                 timer.cancel();
                             }
                     	la1[a].setText("�ð� : " + hour + " : " + minute);
                     	if(hour==0&&minute==0)
                     		la1[a].setText("�������");
              		}
              	};
              	timer.schedule(task, 60000, 60000);//60�� �� ����, 60�ʸ��� �ݺ�
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