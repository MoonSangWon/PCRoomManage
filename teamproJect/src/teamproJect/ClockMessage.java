package teamproJect;

import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;

class ClockMessage extends JPanel implements Runnable {
    int i = Calendar.getInstance().get(Calendar.AM_PM);
    String[] ampm = { "AM", "PM" };
    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
    String time = sdf.format(new Date());
    JLabel timeLabel, ampmLabel;

    public ClockMessage() {
        this.setLayout(null);

        timeLabel = new JLabel(time);
        timeLabel.setBounds(0, 0, 200, 100);
        timeLabel.setForeground(new Color(0, 0, 0));
        timeLabel.setFont(new Font("배달의민족 한나", Font.BOLD, 25));

        ampmLabel = new JLabel(ampm[i]);
        ampmLabel.setBounds(25, 20, 200, 130);
        ampmLabel.setForeground(new Color(0, 0, 0));
        ampmLabel.setFont(new Font("배달의민족 한나", Font.BOLD, 25));

        add(timeLabel);
        add(ampmLabel);
    }

    @Override
    public void run() {
        do {
            try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            timeLabel.setText(sdf.format(new Date()));
        } while (true);
    }
}