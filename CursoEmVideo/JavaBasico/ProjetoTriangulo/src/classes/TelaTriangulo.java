package classes;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class TelaTriangulo extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaTriangulo frame = new TelaTriangulo();
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
	public TelaTriangulo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 276, 314);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblA = new JLabel("Segmento a");
		lblA.setBounds(10, 15, 69, 26);
		contentPane.add(lblA);
		
		JSlider sliderA = new JSlider();
		sliderA.setValue(10);
		sliderA.setMaximum(20);
		sliderA.setBounds(89, 15, 122, 26);
		contentPane.add(sliderA);
		
		JLabel lblB = new JLabel("Segmento b");
		lblB.setBounds(10, 58, 69, 26);
		contentPane.add(lblB);
		
		JSlider sliderB = new JSlider();
		sliderB.setValue(10);
		sliderB.setMaximum(20);
		sliderB.setBounds(89, 58, 122, 26);
		contentPane.add(sliderB);
		
		JLabel lblC = new JLabel("Segmento c");
		lblC.setBounds(10, 103, 69, 26);
		contentPane.add(lblC);
		
		JSlider sliderC = new JSlider();
		sliderC.setValue(10);
		sliderC.setMaximum(20);
		sliderC.setBounds(89, 103, 122, 26);
		contentPane.add(sliderC);
		
		JButton btnVerificar = new JButton("Verificar");
		btnVerificar.setBounds(71, 151, 122, 37);
		contentPane.add(btnVerificar);
		
		JLabel labelValueA = new JLabel("10");
		labelValueA.setEnabled(false);
		labelValueA.setBounds(221, 21, 46, 14);
		contentPane.add(labelValueA);
		
		JLabel labelValueB = new JLabel("10");
		labelValueB.setEnabled(false);
		labelValueB.setBounds(221, 64, 46, 14);
		contentPane.add(labelValueB);
		
		JLabel labelValueC = new JLabel("10");
		labelValueC.setEnabled(false);
		labelValueC.setBounds(221, 109, 46, 14);
		contentPane.add(labelValueC);
		
		JLabel lblResult = new JLabel("");
		lblResult.setHorizontalAlignment(SwingConstants.CENTER);
		lblResult.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblResult.setForeground(Color.BLUE);
		lblResult.setBounds(10, 199, 240, 37);
		contentPane.add(lblResult);
		
		JLabel lblResultType = new JLabel("");
		lblResultType.setHorizontalAlignment(SwingConstants.CENTER);
		lblResultType.setForeground(Color.RED);
		lblResultType.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblResultType.setBounds(10, 238, 240, 26);
		contentPane.add(lblResultType);
		
		sliderA.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				labelValueA.setText(Integer.toString(sliderA.getValue()));
			}
		});
		
		sliderB.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				labelValueB.setText(Integer.toString(sliderB.getValue()));
			}
		});
		
		sliderC.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				labelValueC.setText(Integer.toString(sliderC.getValue()));
			}
		});
		
		btnVerificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int A = sliderA.getValue();
				int B = sliderB.getValue();
				int C = sliderC.getValue();
				
				String result = "";
				String resultType = "";
				if((Math.abs(B-C) < A && A < B+C)
				&& (Math.abs(A-C) < B && B < A+C)
				&& (Math.abs(A-B) < C && C < A+B)) {
					result = "É um triângulo!";
					if(A == B && B == C && A == C) {
						resultType = "Equilátero";
					} else if ((A == B) ^ (B == C) ^ (A == C)) {
						resultType = "Isóceles";
					}
					else {
						resultType = "Escaleno";
					}
				} else {
					result = "Não é um triângulo!";
				}
				
				lblResult.setText(result);
				lblResultType.setText(resultType);
			}
		});
	}
}
