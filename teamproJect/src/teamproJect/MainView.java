package teamproJect;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import java.awt.image.BufferedImage;

import teamproJect.Manage;
import teamproJect.SalesToday;
import teamproJect.Seat;


public class MainView extends Manage implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	JPanel panel, pan_navi, pan_clock;
	public JButton bt[] = new JButton[4]; // 네비게이션 버튼 4개(화면, 회원, 재고, 매상)
	public JPanel seat50; // 50개 패널을 담기 위한 그릇
	int pX, pY;
	int x = 0, y = 0; // 좌표 계속 움직이게 해주는 x, y
	int sx = 77, sy = 0;
	
	BufferedImage img = null;
	JPopupMenu popup;
	JMenuItem Close, allOffSeat, turnOnSeat, turnOffSeat, calculSeat;
	JPanel pan_imgClock;
	Image image, image2, image3;

	public MainView() {
		// 프레임 초기 설정
		setSize(1600, 900);
		setTitle("MainFrame_HUD");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setBackground(Color.BLACK);
		setLocationRelativeTo(null);//화면 가운데 나오게
		// 프레임 화면 중앙 배열
		Dimension frameSize = this.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screenSize.width - frameSize.width) / 2,
				(screenSize.height - frameSize.height) / 2);

		// 가장 큰 JLayer패널= 레이어를 순서대로 올려줌
		JLayeredPane lpane = new JLayeredPane();
		lpane.setBounds(0, 0, 1600, 900);
		lpane.setLayout(null);

		// 배경패널
		panel = new MyPanel("images/background.png");
		panel.setBackground(Color.BLACK);
		panel.setLayout(null);
		panel.setBounds(0, -30, 1600, 900);
		
		// 시계글씨패널
		pan_clock = new MyClock();
		pan_clock.setBounds(80, 53, 100, 100);
		pan_clock.setOpaque(false); // 이걸 해줘야 뒤에가 투명해진다.

		// 네비게이션 패널 첫번째 1200,828
		pan_navi = new JPanel();
		pan_navi.setLayout(null);
		pan_navi.setBounds(1200, 798, 400, 70);
		pan_navi.setOpaque(false);
		// 네비게이션 안에 들어가는 버튼들
		int temp[] = { 91, 95, 80, 79 };
		for (int i = 0; i < 4; i++) {
			bt[i] = new JButton(new ImageIcon("images/bt_navi_" + i + ".png"));
			bt[i].setBorderPainted(false);
			bt[i].setFocusPainted(false);
			bt[i].setContentAreaFilled(false);
			bt[i].addActionListener(this);
			bt[i].setBounds(x, -2, temp[i], 60);
			x += temp[i];
			pan_navi.add(bt[i]);
		}

		// 좌석 패널 시작 시작점 165 129
		seat50 = new JPanel();
		seat50.setLayout(null);
		seat50.setOpaque(false);
		seat50.setBounds(165, 79, 1368, 686);
		x = 0;
		y = 0;
		for (int i = 0; i < 50; i++) {
			pan[i] = new teamproJect.Seat(i);
			if (i % 10 == 0 && i != 0) {
				x = 0;
				y += 140;
			}
			// System.out.print("x : " + x + " y :" + y + " ");
			pan[i].setBounds(x, y, 99, 99);
			pan[i].x = x + 165;
			pan[i].y = y + 79 + 30;
			x += 135;
		}

		// 셀렉트패널영역
		SelectPanel sPanel = new SelectPanel();
		sPanel.setBounds(0, -30, 1600, 900);
		sPanel.setForeground(new Color(36, 205, 198));
		sPanel.setOpaque(false);

		// 마지막 붙이기
		lpane.add(panel, new Integer(0), 0);
		
		lpane.add(pan_clock, new Integer(5), 0); // 시계패널은 최상단
		lpane.add(pan_navi, new Integer(2), 0);

		lpane.add(seat50, new Integer(2), 0);
		lpane.add(sPanel, new Integer(0), 0);

		getContentPane().add(lpane);
		setVisible(true);

		// 좌석 액션 후후.
		Thread th = new MyThread(1);
		th.start();

		// 화면 버튼 구현하기
		/** 여기서부터는 오른쪽 버튼 구현~ */
		popup = new JPopupMenu();
		Close = new JMenuItem("전체종료");
		allOffSeat = new JMenuItem("전체끄기");
		turnOnSeat = new JMenuItem("단체켜기");
		turnOffSeat = new JMenuItem("단체끄기");
	
		Close.addActionListener(this);
		allOffSeat.addActionListener(this);
		turnOnSeat.addActionListener(this);
		turnOffSeat.addActionListener(this);
		
		popup.add(Close);
		popup.add(allOffSeat);
		popup.add(turnOnSeat);
		popup.add(turnOffSeat);
		

	}
	
	// 이미지 그리기 위한 마이패널
	@SuppressWarnings("serial")
	class MyPanel extends JPanel {
		Image image;

		MyPanel(String img) {
			image = Toolkit.getDefaultToolkit().createImage(img);
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			if (image != null) {
				g.drawImage(image, 0, 0, this);
			}
		}

		public void update(Graphics g) {
			paintComponent(g);
		}

	}// 마이패널 종료

	public void reimg() {
		repaint();
	}
	// 선택영역 그리기 위한 패널
	@SuppressWarnings("serial")
	class SelectPanel extends JPanel implements MouseMotionListener,MouseListener {
		int x, y, pX, pY, iX, iY;

		SelectPanel() {
			addMouseListener(this);
			addMouseMotionListener(this);
			setFocusable(true);
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.draw3DRect(x, y, pX - x, pY - y, false);
		}

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		public void mouseDragged(MouseEvent e) {
			pX = e.getX();
			pY = e.getY();
			repaint();
		}

		public void mousePressed(MouseEvent e) {
			x = e.getX();
			iX = e.getX();
			y = e.getY();
			iY = e.getY();
			pX = e.getX();
			pY = e.getY();
			// System.out.println("x:" + x + " y:" + y);
		}

		public void mouseReleased(MouseEvent e) {
			// System.out.println("x:" + x + " y:" + y);
			// System.out.println("px:" + pX + " py:" + pY);
			for (int i = 0; i < 50; i++) {
				if (x < pan[i].x && pan[i].x < pX && y < pan[i].y
						&& pan[i].y < pY)
					pan[i].checkOn();
			}

			x = 0;
			y = 0;
			pX = 0;
			pY = 0;
			repaint();
		}

		public void mouseMoved(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}
	}

	// 좌석 쓰레드! 1일 경우 좌석, 2일경우 꺼짐켜짐으로 사용된다.
	class MyThread extends Thread {
		int i;

		MyThread(int i) {
			this.i = i;
		}

		public void run() {
			Set<Integer> hs = null;
			if (i == 1) {
				hs = new LinkedHashSet<Integer>();
				for (; hs.size() < 50;) {
					int x = (int) ((Math.random() * 50));
					hs.add(x);
				}
			} else {
				hs = new HashSet<Integer>();
				for (int a = 0; a < 50; a++)
					hs.add(a);
			}
			try {
				int tmp=0;
				for (Integer s : hs) {

					if (i == 1)
						Thread.sleep(50);
					else {
						Thread.sleep(25);
					}

					switch (i) {
					case 1:
						tmp++;
						if(tmp>30)
							Thread.sleep(s*10 -(s*5));
						if(tmp==50)
						{
							Thread.sleep(1000);
							System.out.println("50번째");
						}
							
						seat50.add(pan[s]);
						
						break;
					case 2:
				
						break;
					case 3:
						
						break;
					}
					repaint();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("serial")
	class MyClock extends JPanel {
		Calendar ctoday = Calendar.getInstance();
		int i = ctoday.get(Calendar.AM_PM);
		String[] ampm = { "AM", "PM" };
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
		String time = sdf.format(today);
		JLabel timeLabel;
		JLabel ampmLabel;

		public MyClock() {
			this.setLayout(null);

			timeLabel = new JLabel(time);
			timeLabel.setBounds(0, 0, 100, 20);
			timeLabel.setForeground(new Color(255, 255, 255));
			timeLabel.setFont(new Font("배달의민족 한나", Font.BOLD, 25));
			ampmLabel = new JLabel(ampm[i]);
			ampmLabel.setBounds(15, 20, 100, 30);
			ampmLabel.setForeground(new Color(255, 255, 255));
			ampmLabel.setFont(new Font("배달의민족 한나", Font.BOLD, 25));

			add(timeLabel, BorderLayout.NORTH);
			add(ampmLabel, BorderLayout.CENTER);
			Thread thread = new MyClockThread();
			thread.start();
		}

		class MyClockThread extends Thread {
			public void run() {
				while (true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					today = new Date();
					time = sdf.format(today);
					timeLabel.setText(time);
				}

			}
		}
	}
	public static void main(String[] args) {
		new MainView();
	}

	@Override
	// 액션퍼폼
	public void actionPerformed(ActionEvent e) {
		// 팝업메뉴나오게
				if (e.getSource() == bt[0]) {
					popup.show(MainView.this, 1253, 751);
					// 올온!
				} else if (e.getSource() == Close) {
					JOptionPane.showMessageDialog(null,  "프로그램을 종료합니다.");
					setVisible(false);
					// 올오프!
				} else if (e.getSource() == allOffSeat) {
					Thread seatThread = new MyThread(3);
					seatThread.start();
				} //재고 관리
				else if (e.getSource() == bt[2]) {
					new MenuSet();
				}
			// 오늘의 매상
				else if (e.getSource() == bt[3]) {
					new SalesToday();
		}

	}
}// 클래스 종료