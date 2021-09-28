package classes;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class TelaEquacao extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaEquacao frame = new TelaEquacao();
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
	public TelaEquacao() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 321, 224);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JSpinner txtA = new JSpinner();
		txtA.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
			}
		});
		txtA.setBounds(24, 15, 36, 20);
		contentPane.add(txtA);
		
		JLabel lblXSquare = new JLabel("x\u00B2 + ");
		lblXSquare.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblXSquare.setBounds(70, 11, 43, 22);
		contentPane.add(lblXSquare);
		
		JLabel lblX = new JLabel("x + ");
		lblX.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblX.setBounds(168, 11, 43, 22);
		contentPane.add(lblX);
		
		JSpinner txtB = new JSpinner();
		txtB.setBounds(122, 15, 36, 20);
		contentPane.add(txtB);
		
		JSpinner txtC = new JSpinner();
		txtC.setBounds(203, 15, 36, 20);
		contentPane.add(txtC);
		
		JLabel lblEquals = new JLabel("= 0");
		lblEquals.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEquals.setBounds(249, 13, 43, 22);
		contentPane.add(lblEquals);
		
		JLabel lblDelta = new JLabel("<html>&Delta = </html>");
		lblDelta.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDelta.setBounds(80, 51, 43, 20);
		contentPane.add(lblDelta);
		
		JLabel lblB = new JLabel("B");
		lblB.setForeground(Color.RED);
		lblB.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblB.setBounds(120, 51, 43, 20);
		contentPane.add(lblB);
		
		JLabel lblA = new JLabel("A");
		lblA.setForeground(Color.RED);
		lblA.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblA.setBounds(173, 51, 43, 20);
		contentPane.add(lblA);
		
		JLabel lblC = new JLabel("C");
		lblC.setForeground(Color.RED);
		lblC.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblC.setBounds(200, 51, 43, 20);
		contentPane.add(lblC);
		
		JLabel lblSquare = new JLabel("2");
		lblSquare.setBounds(132, 44, 14, 14);
		contentPane.add(lblSquare);
		
		JLabel lblMinusFour = new JLabel("-4");
		lblMinusFour.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMinusFour.setBounds(144, 54, 46, 14);
		contentPane.add(lblMinusFour);
		
		JLabel lblMultiply = new JLabel(".");
		lblMultiply.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMultiply.setBounds(166, 54, 14, 14);
		contentPane.add(lblMultiply);
		
		JLabel lblMultiply2 = new JLabel(".");
		lblMultiply2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMultiply2.setBounds(190, 54, 19, 14);
		contentPane.add(lblMultiply2);
		
		JButton btnCalc = new JButton("<html>Calcular &Delta</html>");
		btnCalc.setBounds(90, 82, 110, 33);
		contentPane.add(btnCalc);
		
		JLabel lblDeltaResult = new JLabel("");
		lblDeltaResult.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDeltaResult.setBounds(24, 126, 256, 22);
		contentPane.add(lblDeltaResult);
		
		JLabel lblType = new JLabel("");
		lblType.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblType.setBounds(24, 154, 256, 20);
		contentPane.add(lblType);
		
		txtA.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				lblA.setText(txtA.getValue().toString());
			}
		});
		
		txtB.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				lblB.setText(txtB.getValue().toString());
			}
		});
		
		txtC.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				lblC.setText(txtC.getValue().toString());
			}
		});
		
		btnCalc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int A = Integer.parseInt(txtA.getValue().toString());
				int B = Integer.parseInt(txtB.getValue().toString());
				int C = Integer.parseInt(txtC.getValue().toString());
				
				double delta = Math.pow(B, 2) - (4*A*C);
				
				String deltaResult = String.format("<html>&Delta = %.2f</html>", delta);
				
				String typeResult = ""; 
				if(delta < 0) {
					typeResult = "Tipo = Não existem raízes reais";
				}
				else if(delta == 0) {
					typeResult = "Tipo = Uma única raíz real";
				}
				if(delta > 0) {
					typeResult = "Tipo = Duas raízes reais";
				}
				
				lblDeltaResult.setText(deltaResult);
				lblType.setText(typeResult);
			}
		});
	}
}
