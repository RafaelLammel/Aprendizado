package classes;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.CardLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class TelaFatorial extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaFatorial frame = new TelaFatorial();
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
	public TelaFatorial() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 436, 108);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JSpinner txtNum = new JSpinner();
		
		JButton btnFatorial = new JButton("!");
		
		JLabel lblFormula = new JLabel("");
		
		JLabel lblResultado = new JLabel("");
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		contentPane.add(txtNum);
		contentPane.add(btnFatorial);
		contentPane.add(lblFormula);
		contentPane.add(lblResultado);
		
		btnFatorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Fatorial fat = new Fatorial();
				fat.setValor(Integer.parseInt(txtNum.getValue().toString()));
				lblFormula.setText(fat.getFormula());
				lblResultado.setText(Integer.toString(fat.getFatorial()));
			}
		});
	}

}
