import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import java.awt.CardLayout;
import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;
import java.util.List;


public class Unifier {

	private JFrame frame;
	private JTextField x;
	private JTextField y;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Unifier window = new Unifier();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Unifier() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnUn = new JButton("Unifier");
		btnUn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Unificateur unificateur=new Unificateur();
				
				List<String> exp1 = unificateur.extractExpression(x.getText());
				List<String> exp2 = unificateur.extractExpression(y.getText());
				String Resultat=unificateur.unifier(exp1, exp2);
				JTextArea text=new JTextArea(6,25);
				text.setText(Resultat);
				text.setEditable(false);
				JScrollPane s = new JScrollPane(text);
				JOptionPane.showMessageDialog(frame,s);
				
				
				// Add Result to file 
				try(FileWriter fw = new FileWriter("/Users/medbeji/Desktop/traceUnification.txt", true);
					    BufferedWriter bw = new BufferedWriter(fw);
					    PrintWriter out = new PrintWriter(bw))
					{
					    out.println("Unifier("+x.getText()+","+y.getText()+") = "+text.getText());
					    //more code
					    out.println(" ");
					    //more code
					} catch (IOException e1) {
					    //exception handling left as an exercise for the reader
					}
				
			}
		});
		btnUn.setBounds(155, 138, 117, 25);
		frame.getContentPane().add(btnUn);
		
		x = new JTextField();
		x.setBounds(76, 62, 114, 19);
		frame.getContentPane().add(x);
		x.setColumns(10);
		
		y = new JTextField();
		y.setText("");
		y.setBounds(257, 62, 114, 19);
		frame.getContentPane().add(y);
		y.setColumns(10);
	}
}
