package classes;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.SpinnerNumberModel;

public class TelaJogo extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaJogo frame = new TelaJogo();
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaJogo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 347, 160);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JSpinner txtNum = new JSpinner();
		txtNum.setModel(new SpinnerNumberModel(1, 1, 5, 1));
		txtNum.setBounds(190, 54, 47, 20);
		contentPane.add(txtNum);
		
		JLabel lblNum = new JLabel("Escolha um n\u00FAmero:");
		lblNum.setBounds(58, 57, 155, 14);
		contentPane.add(lblNum);
		
		JButton btnConfirma = new JButton("Confirma");
		btnConfirma.setBounds(124, 85, 89, 23);
		contentPane.add(btnConfirma);
		
		JLabel lblAdivinha = new JLabel("Adivinhe o n\u00FAmero que estou pensando de 1 a 5");
		lblAdivinha.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdivinha.setBounds(10, 28, 311, 14);
		contentPane.add(lblAdivinha);
		
		btnConfirma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int num = Integer.parseInt(txtNum.getValue().toString());
				Random seed = new Random();
				int randNum = 1+seed.nextInt(5);
				String result = num == randNum ? "Acertou!" : "Errou! O Número era: " + randNum;
				lblAdivinha.setText(result);
			}
		});
	}
}
