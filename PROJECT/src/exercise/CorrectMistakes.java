package exercise;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CorrectMistakes extends Exercise implements ActionListener {

	JPanel panel;
	JTextArea textArea;
	JLabel neededOutput;
	JButton submit;
	JTextField result;
	int numOfTries;
	String rightAns;

	public CorrectMistakes() {
		numOfTries = 0;
		rightAns = "class HelloWorld {\r\n" + "    public static void main(String[] args) {\r\n"
				+ "        System.out.println(\"Hello, World!\"); \r\n" + "    }\r\n" + "}";

		setLayout(null);
		setBackground(new Color(255, 165, 0));

		textArea = new JTextArea();
		textArea.setFont(new Font("Serif", Font.BOLD, 20));
		textArea.setBounds(40, 40, 600, 500);
		textArea.setText("class HelloWorld {\r\n" + "    public static void main(String[] args) {\r\n"
				+ "        System.out.println(\"Hello, World!\"); \r\n" + "    }\r\n" + "}");
		add(textArea);

		submit = new JButton("Submit");
		submit.setBounds(680, 310, 150, 60);
		submit.addActionListener(this);
		add(submit);

		neededOutput = new JLabel();
		neededOutput.setBounds(870, 40, 600, 500);
		neededOutput.setBackground(Color.WHITE);
		neededOutput.setFont(new Font("Serif", Font.BOLD, 15));
		neededOutput.setText("<html>" + "Hello, World!" + "</html>");
		neededOutput.setOpaque(true);
		add(neededOutput);

		result = new JTextField();
		result.setText("Answer is: ***** " + "|| Number of Tries:" + numOfTries);
		result.setOpaque(true);
		result.setBounds(550, 300, 400, 100);
		result.setFont(new Font("Serif", Font.BOLD, 25));
		result.setBackground(Color.white);
		result.setEditable(false);
		add(result);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Compiler c = new Compiler();
		List<String> errors = new ArrayList<>();
		try {
			errors = c.compile(textArea.getText());
		} catch (IOException e2) {
			e2.printStackTrace();
		}


			if (errors.size() == 0) {
				result.setBackground(Color.green);
				result.setText("Answer is: Right " + "|| Number of Tries:" + numOfTries);
				textArea.setEditable(false);
				((JButton) e.getSource()).setEnabled(false);
			} else {
				numOfTries++;

				result.setBackground(Color.red);
				String str = "";
				for (int i = 0; i < errors.size(); i++)
					str = str + errors.get(i) + "\n";
				result.setText(str + "Number of Tries:" + numOfTries);
			}

		}

	}


