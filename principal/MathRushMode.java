package principal;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.SwingConstants;

public class MathRushMode extends JFrame {

    Player player;
    private JPanel contentPane;
    JLabel counterlabel, answerIs;
    char word = 0;
    JLabel lblNextoperation, lblVidas, lblPoints;
    Timer timer, timeoper;
    int seconds, minutes, ans, userans, k = 70, points = 0, health = 3, operationtype, mindigits = 2, maxdigits = 2, numsquantity = 2;
    String ddSecond, ddMinute, StrNextoperation;
    Operations operations;
    int[] numbers;
    JFormattedTextField formattedTextField;
    DecimalFormat dFormat = new DecimalFormat("00");
    Random rand = new Random();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MathRushMode frame = new MathRushMode();
                    frame.setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public MathRushMode() {
        player = new Player();
        player.setPoints(0);
        player.setHealth(3);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1280, 720);
        contentPane = new JPanel();
        contentPane.setBackground(Color.DARK_GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        counterlabel = new JLabel("aqui esoy");
        counterlabel.setFont(new Font("Malgun Gothic", Font.PLAIN, 40));
        counterlabel.setForeground(Color.RED);
        counterlabel.setBackground(new Color(255, 51, 51));
        counterlabel.setBounds(976, 53, 280, 34);
        counterlabel.setHorizontalAlignment(JLabel.CENTER);
        getContentPane().add(counterlabel);

        seconds = 5;
        minutes = 1;
        counterlabel.setText("01:05");

        lblPoints = new JLabel("Puntuacion: 0");
        lblPoints.setFont(new Font("Tahoma", Font.PLAIN, 22));
        lblPoints.setForeground(Color.LIGHT_GRAY);
        lblPoints.setBounds(21, 35, 280, 30);
        contentPane.add(lblPoints);

        lblVidas = new JLabel("Vidas:");
        lblVidas.setForeground(Color.LIGHT_GRAY);
        lblVidas.setFont(new Font("Tahoma", Font.PLAIN, 22));
        lblVidas.setBounds(21, 75, 280, 27);
        contentPane.add(lblVidas);

        lblNextoperation = new JLabel("");
        lblNextoperation.setBackground(Color.WHITE);
        lblNextoperation.setHorizontalAlignment(SwingConstants.CENTER);
        lblNextoperation.setForeground(Color.WHITE);
        lblNextoperation.setFont(new Font("Comic Sans MS", Font.PLAIN, 59));
        lblNextoperation.setBounds(21, 237, 1235, 83);
        contentPane.add(lblNextoperation);

        JLabel lblAns = new JLabel("Respuesta:");
        lblAns.setForeground(Color.LIGHT_GRAY);
        lblAns.setFont(new Font("Tahoma", Font.PLAIN, 22));
        lblAns.setBounds(281, 458, 132, 27);
        contentPane.add(lblAns);

        formattedTextField = new JFormattedTextField();
        formattedTextField.setFont(new Font("Cambria Math", Font.PLAIN, 30));
        formattedTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ((c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c != '-' && !(Character.isDigit(c)))) {
                    getToolkit().beep();
                    e.consume();
                }
            }
        });
        formattedTextField.setBounds(423, 446, 368, 61);
        contentPane.add(formattedTextField);

        operations = new Operations(2, 2, 2);
        numbers = new int[operations.getNumsquantity()];
        numbers = operations.CreateNumbersInt();
        StrNextoperation = numbers[0] + "+" + numbers[1];
        lblNextoperation.setText(StrNextoperation);
        ans = operations.SumOfNumbers(numbers);
        lblVidas.setText("Vidas: " + health);
        System.out.println(ans);

        answerIs = new JLabel("Bien o mal");
        answerIs.setHorizontalAlignment(SwingConstants.CENTER);
        answerIs.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 21));
        answerIs.setForeground(Color.WHITE);
        answerIs.setBounds(444, 53, 296, 98);
        contentPane.add(answerIs);

        JButton Nextexercise = new JButton("Siguiente");
        Nextexercise.setForeground(new Color(255, 255, 255));
        Nextexercise.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        Nextexercise.setBackground(new Color(255, 204, 0));
        Nextexercise.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                userans = Integer.parseInt(formattedTextField.getText());
                if (userans == ans) {
                    answerIs.setText("Bien hecho");
                    points += 100;
                    lblPoints.setText("Puntuacion: " + points);
                    answerIs.setForeground(Color.GREEN);
                    numsquantity++;
                    maxdigits++;
                    System.out.println("numsquantity: " + numsquantity + "\n mindigits: " + mindigits + "\nmaxdigits: " + maxdigits);
                    operations.setMindigits(mindigits);
                    operations.setMaxdigits(mindigits);
                    operations.setNumsquantity(numsquantity);
                    lblNextoperation.setFont(new Font("Comic Sans MS", Font.PLAIN, k));
                    numbers = new int[operations.getNumsquantity()];
                    numbers = operations.CreateNumbersInt();
                    StrNextoperation = "";
                    operationtype = rand.nextInt(2);

                    if (operationtype == 0) {
                        for (int j = 0; j < numbers.length; j++) {
                            StrNextoperation += Integer.toString(numbers[j]) + "+";
                        }
                        StrNextoperation += "0";
                        lblNextoperation.setText(StrNextoperation);
                        ans = operations.SumOfNumbers(numbers);
                    } else if (operationtype == 1) {
                        for (int j = 0; j < numbers.length; j++) {
                            StrNextoperation += Integer.toString(numbers[j]) + "x";
                        }
                        StrNextoperation += "1";
                        lblNextoperation.setText(StrNextoperation);
                        ans = operations.ProductOfNumbers(numbers);
                    }
                } else {
                    health--;
                    lblVidas.setText("Vidas: " + health);
                    player.setHealth(health);
                    answerIs.setText("Error, vuelve a intentarlo");
                    answerIs.setForeground(Color.RED);
                }

                if (health == 0) {
                    JOptionPane.showMessageDialog(null, "No tienes vidas: \n Tu puntuacion fue de:  " + points, "Alert", JOptionPane.WARNING_MESSAGE);
                    dispose();
                    GameModes frame = new GameModes();
                    frame.setVisible(true);
                }
                k -= 1;
                formattedTextField.setText("");
                System.out.println(ans);
                System.out.println(Arrays.toString(numbers));
            }

        });
        Nextexercise.setBounds(423, 534, 368, 49);
        contentPane.add(Nextexercise);

        countdownTimer();
        timer.start();
    }

    public void countdownTimer() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seconds--;
                ddSecond = dFormat.format(seconds);
                ddMinute = dFormat.format(minutes);
                counterlabel.setText(ddMinute + ":" + ddSecond);
                if (seconds == -1) {
                    seconds = 59;
                    minutes--;
                    ddSecond = dFormat.format(seconds);
                    ddMinute = dFormat.format(minutes);
                }
                if (minutes == 0 && seconds == 0) {
                    timer.stop();
                    JOptionPane.showMessageDialog(null, "Se acabo el tiempo: \n Tu puntuacion fue de:  " + points, "Alert", JOptionPane.WARNING_MESSAGE);
                    dispose();
                    GameModes frame = new GameModes();
                    frame.setVisible(true);
                }
            }
        });
    }
}
