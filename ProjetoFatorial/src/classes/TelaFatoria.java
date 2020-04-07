package classes;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class TelaFatoria extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaFatoria frame = new TelaFatoria();
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
	public TelaFatoria() {
		setTitle("Calculando Fatorial");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 370, 144);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JSpinner spinnerNum = new JSpinner();
		spinnerNum.setFont(new Font("Tahoma", Font.PLAIN, 21));
		spinnerNum.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinnerNum.setBounds(21, 36, 70, 35);
		contentPane.add(spinnerNum);
		
		JLabel lblEquals = new JLabel("! = ");
		lblEquals.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblEquals.setBounds(101, 36, 46, 35);
		contentPane.add(lblEquals);
		
		JLabel lblResult = new JLabel("1");
		lblResult.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblResult.setBounds(139, 36, 205, 35);
		contentPane.add(lblResult);
		
		spinnerNum.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int num = Integer.parseInt(spinnerNum.getValue().toString());
				
				int result = 1;
				while(num > 1) {
					result *= num--;
				}
				
				lblResult.setText(Integer.toString(result));
			}
		});
	}

}
