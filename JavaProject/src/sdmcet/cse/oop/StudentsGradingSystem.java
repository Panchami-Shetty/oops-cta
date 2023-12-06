package sdmcet.cse.oop;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StudentsGradingSystem extends Exception implements ActionListener {
	JFrame f = new JFrame("Student Grading System");
	JLabel l = new JLabel("Grade Calculator");
	JLabel la1 = new JLabel("Enter IA-1 Marks:");
	JLabel la2 = new JLabel("Enter IA-2 Marks:");
	JLabel la3 = new JLabel("Enter IA-3 Marks:");
	JLabel la4 = new JLabel("Enter CTA Marks:");
	JLabel la5 = new JLabel("Enter SEE Marks:");
	JLabel latotal = new JLabel();
	JLabel lagrade = new JLabel();

	JButton bu1 = new JButton("CALCULATE");

	JPanel p = new JPanel();
	JPanel pa1 = new JPanel();
	JPanel pa2 = new JPanel();
	JPanel pa3 = new JPanel();
	JPanel pa4 = new JPanel();
	JPanel pa5 = new JPanel();
	JPanel pa6 = new JPanel();
	JPanel patotal = new JPanel();
	JPanel pagrade = new JPanel();

	JTextField tf1 = new JTextField(10);
	JTextField tf2 = new JTextField(10);
	JTextField tf3 = new JTextField(10);
	JTextField tf4 = new JTextField(10);
	JTextField tf5 = new JTextField(10);

	public StudentsGradingSystem() {
		bu1.setSize(100, 100);
		bu1.setForeground(Color.WHITE);
		bu1.setBackground(new Color(25, 25, 112));
		f.setVisible(true);
		f.setBounds(200, 200, 500, 500);

		p.add(l);
		l.setFont(new Font("Lucida Sans", Font.BOLD, 18));
		l.setForeground(new Color(0, 0, 128));

		pa1.add(la1);
		la1.setFont(new Font("Lucida Sans", Font.BOLD, 14));
		pa1.add(tf1);

		pa2.add(la2);
		la2.setFont(new Font("Lucida Sans", Font.BOLD, 14));
		pa2.add(tf2);

		pa3.add(la3);
		la3.setFont(new Font("Lucida Sans", Font.BOLD, 14));
		pa3.add(tf3);

		pa4.add(la4);
		la4.setFont(new Font("Lucida Sans", Font.BOLD, 14));
		pa4.add(tf4);

		pa5.add(la5);
		la5.setFont(new Font("Lucida Sans", Font.BOLD, 14));
		pa5.add(tf5);

		pa6.add(bu1);
		bu1.setFont(new Font("Lucida Sans", Font.BOLD, 14));

		patotal.add(latotal);
		patotal.add(lagrade);

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLayout(new GridLayout(10, 0));
		patotal.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 0));
		lagrade.setFont(new Font("Elephant", Font.BOLD, 16));
		lagrade.setForeground(Color.BLACK);
		bu1.addActionListener(this);

		f.add(p);
		f.add(pa1);
		f.add(pa2);
		f.add(pa3);
		f.add(pa4);
		f.add(pa5);
		f.add(pa6);
		f.add(patotal);
	}

	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == bu1) {

				int ia1 = Integer.parseInt(tf1.getText());
				int ia2 = Integer.parseInt(tf2.getText());
				int ia3 = Integer.parseInt(tf3.getText());
				int cta = Integer.parseInt(tf4.getText());
				double see = Integer.parseInt(tf5.getText());

				// Validate input marks
				if (ia1 < 0 || ia1 > 20 || ia2 < 0 || ia2 > 20 || ia3 < 0 || ia3 > 20 || cta < 0 || cta > 10 || see < 0
						|| see > 100) {
					lagrade.setText("The entered marks are invalid");
					return;
				}

				// Calculate CIE
				int cie;
				int sum1, sum2, largest;

				largest = ia1 + ia2;
				sum1 = ia2 + ia3;
				sum2 = ia1 + ia3;

				if (sum1 > largest) {
					largest = sum1;
				}
				if (sum2 > largest) {
					largest = sum2;
				}

				cie = largest + cta;

				// Upgrade SEE marks to 40 if 38 or 39
				if (see == 38 || see == 39) {
					see = 40;
				}

				// Calculate total marks
				double totalMarks = (cie + Math.round(see / 2));
				totalMarks = Math.round(totalMarks);

				// Check if student is detained
				if (cie < 20) {
					lagrade.setText("Student is detained from taking SEE");
					return;
				}

				// Calculate grade

				String grade;
				if (totalMarks >= 90) {
					grade = "S";
				} else if (totalMarks >= 80) {
					grade = "A";
				} else if (totalMarks >= 70) {
					grade = "B";
				} else if (totalMarks >= 60) {
					grade = "C";
				} else if (totalMarks >= 50) {
					grade = "D";
				} else if (totalMarks >= 40) {
					grade = "E";
				} else {
					grade = "F";
				}
				latotal.setText("TotalMarks:" + totalMarks);
				latotal.setFont(new Font("Lucida Sans", Font.BOLD, 16));
				lagrade.setText("Grade:" + grade);
				lagrade.setFont(new Font("Lucida Sans", Font.BOLD, 16));

				// student failed due to less marks in SEE
				if (see < 38) {
					// String grade="F";
					lagrade.setText("Grade:F");
					return;
				}
			}
		} catch (NumberFormatException nfe) {
			lagrade.setText("Invalid Numeric format Entered");
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new StudentsGradingSystem();
	}

}
