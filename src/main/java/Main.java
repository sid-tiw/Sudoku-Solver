import javax.swing.*;
import java.awt.*;


public class Main {


    public static void main(String[] args) {

        JFrame mainActivity;

        mainActivity = new JFrame("Sudoku Solver");

        mainActivity.setLayout(null);

        mainActivity.setSize(600, 600);

        mainActivity.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        int n;  //Size of the sudoku

        Object[] options = {"4", "9", "16", "25"};

        String s = (String) JOptionPane.showInputDialog(mainActivity, "Enter the size of the sudoku : ", "Provide Size", JOptionPane.PLAIN_MESSAGE, null, options, "9");

        n = Integer.parseInt(s);

        JPanel show = new JPanel(new GridLayout(n, n, 5, 5), true);

        show.setSize(400, 400);

        show.setVisible(true);

        show.setLocation(100, 0);

        mainActivity.add(show);

        JTextField[] arr = new JTextField[n * n];

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                arr[n * i + j] = new JTextField(Integer.toString(0), 5);

                arr[n * i + j].setFont(new Font(new Fonts().s[13], Font.BOLD, 10));

                show.add(arr[n * i + j]);

            }

        }

        mainActivity.setVisible(true);

        JButton jb = new JButton("Solve");

        jb.setLocation(260, 440);

        jb.setSize(80, 40);

        mainActivity.add(jb);

        jb.addActionListener(e -> {

            int[][] matrix = new int[n][n];

            for (int i = 0; i < n; i++)

                for (int j = 0; j < n; j++)

                    matrix[i][j] = Integer.parseInt(arr[n * i + j].getText());

            try {

                if (Solve.checkForZeroes(matrix, n).size() == 0 && !Solve.checkValidity(matrix, n)) {

                    JOptionPane.showMessageDialog(mainActivity, "You Entered an unsolvable Sudoku!!!", "Error", JOptionPane.ERROR_MESSAGE);

                }

                Solve.solve(matrix, n);

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(mainActivity, "You Entered an unsolvable Sudoku!!!", "Error", JOptionPane.ERROR_MESSAGE);

            }

            for (int i = 0; i < n; i++)

                for (int j = 0; j < n; j++)

                    arr[n * i + j].setText(Integer.toString(matrix[i][j]));

        });

    }

}
