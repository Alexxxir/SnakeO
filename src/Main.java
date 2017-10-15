import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Main extends JFrame {

    private Rect rect;
    private JPanel cp;

    private static final int STEP = 10;
    private static final String ACTION_UP = "up";
    private static final String ACTION_DOWN = "down";
    private static final String ACTION_LEFT = "left";
    private static final String ACTION_RIGHT = "right";

    public void Print() {
        rect = new Rect();
        rect.setBackground(Color.red);
        cp.add(rect);

    }

    class PrimeThread extends Thread {
        public void run() {
            Print();
            rect.setLocation(rect.getX(), rect.getY() - STEP);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public Main() {
        super("Component Move Test");
        cp = new JPanel(null);
        JRootPane rp = getRootPane();
        setContentPane(cp);
        setSize(800, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        PrimeThread p = new PrimeThread();
        new Thread(p).start();
    }

    public static void main(String[] args) {
        new Main().setVisible(true);
    }

    private class UpMoveAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            rect.setLocation(rect.getX(), rect.getY() - STEP);
        }
    }

    private class DownMoveAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            rect.setLocation(rect.getX(), rect.getY() + STEP);
        }
    }

    private class LeftMoveAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            rect.setLocation(rect.getX() - STEP, rect.getY());
        }
    }

    private class RightMoveAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            rect.setLocation(rect.getX() + STEP, rect.getY());
        }
    }

    private class Rect extends JPanel {
        public Rect() {
            setSize(100, 100);
        }
    }

    public class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            System.out.println("TimerTask начал свое выполнение в:" + new Date());
            completeTask();
            System.out.println("TimerTask закончил свое выполнение в:" + new Date());
        }

        private void completeTask() {
            try {
                // допустим, выполнение займет 20 секунд
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}