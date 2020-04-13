package classes;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import javax.swing.JSpinner;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import javax.swing.SpinnerNumberModel;

public class TelaIdade extends JFrame {

	private JPanel contentPane;
	private int currentYear;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaIdade frame = new TelaIdade();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaIdade() {
		
		currentYear = Calendar.getInstance().get(Calendar.YEAR);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 201, 175);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ano de Nascimento");
		lblNewLabel.setBounds(10, 41, 92, 14);
		contentPane.add(lblNewLabel);
		
		JSpinner txtAN = new JSpinner();
		txtAN.setModel(new SpinnerNumberModel(1900, 1900, 2020, 1));
		txtAN.setBounds(112, 36, 63, 25);
		contentPane.add(txtAN);
		
		JButton btnCalc = new JButton("Calcular");
		
		btnCalc.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCalc.setBounds(39, 66, 100, 25);
		contentPane.add(btnCalc);
		
		JLabel lblNewLabel_1 = new JLabel("Idade");
		lblNewLabel_1.setBounds(56, 102, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblIdade = new JLabel("0");
		lblIdade.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblIdade.setBounds(103, 102, 72, 14);
		contentPane.add(lblIdade);
		
		JLabel lblNewLabel_2 = new JLabel("Ano Atual: " + currentYear);
		lblNewLabel_2.setBounds(10, 11, 92, 14);
		contentPane.add(lblNewLabel_2);
		
		btnCalc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int an = Integer.parseInt(txtAN.getValue().toString());
				int id = currentYear - an;
				lblIdade.setText(Integer.toString(id));
			}
		});
	}
}
