package GUI;

import Model.Interpreter.Interpreter;
import Model.Lexer.Token;
import Model.Lexer.Tokenizer;
import Model.Parser.Parser;
import Model.Utils.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class KalkulatorGUI extends JFrame {
    private JPanel panel;
    private ArrayList<JPanel> rows;

    public KalkulatorGUI() {
        setTitle("Kalkulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,600);
        setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        rows = new ArrayList<>();

        addRow();

        JButton addButton = new JButton("+");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addRow();
            }
        });

        JButton calculateButton = new JButton("Oblicz wszystko");
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateAll();
            }
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(addButton);
        bottomPanel.add(calculateButton);

        JPanel legendPanel = createLegendPanel();

        //getContentPane().add(BorderLayout.CENTER, new JScrollPane(panel));
        setLayout(new BorderLayout());
        getContentPane().add(new JScrollPane(panel), BorderLayout.CENTER);
        getContentPane().add(BorderLayout.EAST, legendPanel);
        getContentPane().add(BorderLayout.SOUTH, bottomPanel);
        setVisible(true);
    }

    private void addRow() {
        JPanel row = new JPanel();
        row.setLayout(new FlowLayout(FlowLayout.LEFT));
        //row.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        JTextField inputField = new JTextField(20);
        inputField.setPreferredSize(new Dimension(150, 30));
        JTextField resultField = new JTextField(10);
        resultField.setPreferredSize(new Dimension(150, 30));
        resultField.setEditable(false);
        resultField.setBackground(Color.LIGHT_GRAY);

        JButton removeButton = new JButton("Usun");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.remove(row);
                rows.remove(row);
                panel.revalidate();
                panel.repaint();
            }
        });

        row.add(inputField);
        row.add(resultField);
        row.add(removeButton);
        panel.add(row);
        rows.add(row);

        panel.revalidate();
        panel.repaint();
    }

    private void calculateAll() {
        Tokenizer tokenizer = new Tokenizer();
        Parser parser = new Parser();
        Interpreter interpreter = new Interpreter();
        for(JPanel row : rows) {
            JTextField inputField = (JTextField) row.getComponent(0);
            JTextField resultField = (JTextField) row.getComponent(1);
            String expression = inputField.getText();

            try{
                ArrayList<Token> tokens = tokenizer.tokenize(expression);
                Node node = parser.parse(tokens);
                double result = interpreter.execute(node);
                resultField.setText(String.valueOf(result));
            } catch (Exception e){
                System.out.println(e.getMessage());
                e.printStackTrace();
                return;
            }
        }
    }

    private JPanel createLegendPanel() {
        JPanel legendPanel = new JPanel();
        legendPanel.setLayout(new BoxLayout(legendPanel, BoxLayout.Y_AXIS));

        JLabel legendTitle = new JLabel("Predefiniowane funkcje i stałe:");
        legendTitle.setFont(new Font("Arial", Font.BOLD, 16));
        JPanel legendInfo = new JPanel();
        legendInfo.setLayout(new BoxLayout(legendInfo, BoxLayout.Y_AXIS));
        JLabel legend_sin = new JLabel("sin(x)");
        JLabel legend_cos = new JLabel("cos(x)");
        JLabel legend_tg = new JLabel("tg(x)");
        JLabel legend_pi = new JLabel("pi");
        JLabel legend_e = new JLabel("e");
        JLabel legend_sqrt = new JLabel("sqrt(x)");
        JLabel legend_3root = new JLabel("_3root");
        JLabel legend_arcsin = new JLabel("arcsin(x)");
        JLabel legend_arccos = new JLabel("arccos(x)");
        JLabel legend_arctg = new JLabel("arctg(x)");
        JLabel legend_ln = new JLabel("ln(x)");
        JLabel legend_log = new JLabel("log(x)");
        JLabel legend_ceil = new JLabel("ceil(x)");
        JLabel legend_floor = new JLabel("floor(x)");
        JLabel legend_abs = new JLabel("abs(x)");
        JLabel legend_sinh = new JLabel("sinh(x)");
        JLabel legend_cosh = new JLabel("cosh(x)");
        JLabel legend_tgh = new JLabel("tgh(x)");


        legendInfo.add(legend_pi);
        legendInfo.add(legend_e);
        legendInfo.add(legend_sin);
        legendInfo.add(legend_cos);
        legendInfo.add(legend_tg);
        legendInfo.add(legend_sqrt);
        legendInfo.add(legend_3root);
        legendInfo.add(legend_arcsin);
        legendInfo.add(legend_arccos);
        legendInfo.add(legend_arctg);
        legendInfo.add(legend_ln);
        legendInfo.add(legend_log);
        legendInfo.add(legend_ceil);
        legendInfo.add(legend_floor);
        legendInfo.add(legend_abs);
        legendInfo.add(legend_sinh);
        legendInfo.add(legend_cosh);
        legendInfo.add(legend_tgh);

        legendPanel.add(legendTitle);
        legendPanel.add(legendInfo);

        return legendPanel;
    }
}
