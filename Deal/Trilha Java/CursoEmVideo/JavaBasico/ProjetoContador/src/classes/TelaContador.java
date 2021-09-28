package classes;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JSlider;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JList;
import javax.swing.DefaultListModel;

public class TelaContador extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaContador frame = new TelaContador();
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
	public TelaContador() {
		setTitle("Contador");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblInicio = new JLabel("In\u00EDcio");
		lblInicio.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblInicio.setBounds(10, 11, 46, 26);
		contentPane.add(lblInicio);
		
		JSlider sliderInicio = new JSlider();
		sliderInicio.setValue(0);
		sliderInicio.setMaximum(5);
		sliderInicio.setBounds(55, 11, 149, 26);
		contentPane.add(sliderInicio);
		
		JLabel lblInicioValue = new JLabel("0");
		lblInicioValue.setEnabled(false);
		lblInicioValue.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblInicioValue.setBounds(210, 11, 55, 26);
		contentPane.add(lblInicioValue);
		
		JLabel lblFim = new JLabel("Fim");
		lblFim.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFim.setBounds(10, 48, 46, 26);
		contentPane.add(lblFim);
		
		JSlider sliderFim = new JSlider();
		sliderFim.setMinimum(6);
		sliderFim.setValue(6);
		sliderFim.setMaximum(20);
		sliderFim.setBounds(55, 48, 149, 26);
		contentPane.add(sliderFim);
		
		JLabel lblFimValue = new JLabel("6");
		lblFimValue.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFimValue.setEnabled(false);
		lblFimValue.setBounds(210, 48, 55, 26);
		contentPane.add(lblFimValue);
		
		JLabel lblPasso = new JLabel("Passo");
		lblPasso.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPasso.setBounds(10, 85, 46, 26);
		contentPane.add(lblPasso);
		
		JSlider sliderPasso = new JSlider();
		sliderPasso.setMinimum(1);
		sliderPasso.setMaximum(4);
		sliderPasso.setValue(1);
		sliderPasso.setBounds(55, 85, 149, 26);
		contentPane.add(sliderPasso);
		
		JLabel lblPassoValue = new JLabel("1");
		lblPassoValue.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassoValue.setEnabled(false);
		lblPassoValue.setBounds(210, 85, 55, 26);
		contentPane.add(lblPassoValue);
		
		JButton btnContar = new JButton("Contar");
		btnContar.setBounds(81, 135, 89, 23);
		contentPane.add(btnContar);
		
		JList listResult = new JList();
		listResult.setVisibleRowCount(20);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(249, 11, 175, 239);
		scrollPane.setViewportView(listResult);
		contentPane.add(scrollPane);
		
		sliderInicio.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				lblInicioValue.setText(Integer.toString(sliderInicio.getValue()));
			}
		});
		
		sliderFim.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				lblFimValue.setText(Integer.toString(sliderFim.getValue()));
			}
		});
		
		sliderPasso.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				lblPassoValue.setText(Integer.toString(sliderPasso.getValue()));
			}
		});
		
		btnContar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int inicio = sliderInicio.getValue();
				int fim = sliderFim.getValue();
				int passo = sliderPasso.getValue();
				
				DefaultListModel list = new DefaultListModel();
				for(int i = inicio; i <= fim; i+=passo) {
					list.addElement(i);					
				}
				listResult.setModel(list);
				
			}
		});
	}
}
