package classes;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSpinner;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaVetor extends JFrame {

	private JPanel contentPane;
	private int posicao;
	private int vetor[];
	private DefaultListModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaVetor frame = new TelaVetor();
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
	public TelaVetor() {
		
		posicao = 0;
		vetor = new int[10];
		model = new DefaultListModel();
		Arrays.fill(vetor, 0);
		for(int i = 0; i < vetor.length; i++) {
			model.addElement("[" + i + "] => " + vetor[i]);
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 371, 307);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JSpinner spinnerValue = new JSpinner();
		spinnerValue.setBounds(10, 11, 57, 20);
		contentPane.add(spinnerValue);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setBounds(77, 10, 89, 23);
		contentPane.add(btnAdicionar);
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.setBounds(77, 42, 89, 23);
		contentPane.add(btnRemover);
		
		JButton btnOrdenar = new JButton("Ordenar");
		btnOrdenar.setBounds(77, 76, 89, 23);
		contentPane.add(btnOrdenar);
		
		JList listResult = new JList();
		listResult.setBounds(176, 61, 169, 196);
		listResult.setModel(this.model);
		contentPane.add(listResult);
		
		JLabel lblVetor = new JLabel("Vetor");
		lblVetor.setBounds(176, 33, 46, 14);
		contentPane.add(lblVetor);
		
		JLabel lblVetorSel = new JLabel("[0]");
		lblVetorSel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblVetorSel.setBounds(299, 33, 46, 14);
		contentPane.add(lblVetorSel);
		
		listResult.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				posicao = listResult.getSelectedIndex();
				lblVetorSel.setText("[" + Integer.toString(posicao) + "]");
			}
		});
		
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int valor = Integer.parseInt(spinnerValue.getValue().toString());
				vetor[posicao] = valor;
				model.setElementAt("[" + posicao + "] => " + vetor[posicao], posicao);
			}
		});
		
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vetor[posicao] = 0;
				model.setElementAt("[" + posicao + "] => " + vetor[posicao], posicao);
			}
		});
		
		btnOrdenar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Arrays.sort(vetor);
				for(int i = 0; i < vetor.length; i++) {
					model.setElementAt("[" + i + "] => " + vetor[i], i);
				}
			}
		});
	}
}
