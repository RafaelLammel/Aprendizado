package classes;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JSpinner;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaCalculadora extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCalculadora frame = new TelaCalculadora();
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
	public TelaCalculadora() {
		
		boolean flag = false;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("Super Calculadora");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(5, 5, 424, 36);
		contentPane.add(lblTitle);
		
		JLabel lblValue = new JLabel("Informe o valor:");
		lblValue.setBounds(31, 67, 97, 14);
		contentPane.add(lblValue);
		
		JSpinner txtNum = new JSpinner();
		txtNum.setBounds(138, 64, 55, 20);
		contentPane.add(txtNum);
		
		JButton btnCalc = new JButton("Calcular");
		btnCalc.setBounds(256, 52, 105, 44);
		contentPane.add(btnCalc);
		
		JLabel lblDivideTwo = new JLabel("Resto da Divis\u00E3o por 2");
		lblDivideTwo.setBounds(53, 123, 162, 14);
		contentPane.add(lblDivideTwo);
		lblDivideTwo.setVisible(false);
		
		JLabel lblCube = new JLabel("Elevado ao Cubo");
		lblCube.setBounds(53, 149, 162, 14);
		contentPane.add(lblCube);
		lblCube.setVisible(false);
		
		JLabel lblSquare = new JLabel("Ra\u00EDz Quadrada");
		lblSquare.setBounds(53, 176, 162, 14);
		contentPane.add(lblSquare);
		lblSquare.setVisible(false);
		
		JLabel lblCubic = new JLabel("Ra\u00EDz C\u00FAbica");
		lblCubic.setBounds(53, 200, 162, 14);
		contentPane.add(lblCubic);
		lblCubic.setVisible(false);
		
		JLabel lblAbs = new JLabel("Valor Absoluto");
		lblAbs.setBounds(53, 225, 162, 14);
		contentPane.add(lblAbs);
		lblAbs.setVisible(false);
		
		JLabel lblDivideTwoValue = new JLabel("");
		lblDivideTwoValue.setBounds(248, 123, 46, 14);
		contentPane.add(lblDivideTwoValue);
		
		JLabel lblCubeValue = new JLabel("");
		lblCubeValue.setBounds(248, 148, 46, 14);
		contentPane.add(lblCubeValue);
		
		JLabel lblSquareValue = new JLabel("");
		lblSquareValue.setBounds(248, 176, 46, 14);
		contentPane.add(lblSquareValue);
		
		JLabel lblCubicValue = new JLabel("");
		lblCubicValue.setBounds(248, 200, 46, 14);
		contentPane.add(lblCubicValue);
		
		JLabel lblAbsValue = new JLabel("");
		lblAbsValue.setBounds(248, 225, 46, 14);
		contentPane.add(lblAbsValue);
		
		btnCalc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int number = Integer.parseInt(txtNum.getValue().toString());
				
				int divideTwo = number % 2;
				double cube = Math.pow(number, 3);
				double square = Math.sqrt(number);
				double cubic = Math.cbrt(number);
				int abs = Math.abs(number);
				
				if(!flag) {
					lblDivideTwo.setVisible(true);
					lblCube.setVisible(true);
					lblSquare.setVisible(true);
					lblCubic.setVisible(true);
					lblAbs.setVisible(true);
				}
				
				lblDivideTwoValue.setText(Integer.toString(divideTwo));
				lblCubeValue.setText(String.format("%.2f", cube));
				lblSquareValue.setText(String.format("%.2f", square));
				lblCubicValue.setText(String.format("%.2f", cubic));
				lblAbsValue.setText(Integer.toString(abs));
			}
		});
	}

}
