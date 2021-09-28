package classes;

import javax.swing.JOptionPane;

public class Repita {

	public static void main(String[] args) {
		
		int n, s = 0, total = 0, pares = 0, impares = 0, acimaCem = 0;
		float media = 0;
		
		do {
			n = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe um numero: (valor 0 interrompe)"));
			
			if(n != 0) {
				s += n;
				total++;
				
				if(n % 2 == 0)
					pares++;
				else
					impares++;
				
				if(n > 100)
					acimaCem++;
				
				media = (float)s/(float)total;
			}
			
		} while(n != 0);
		JOptionPane.showMessageDialog(null, "<html>Resultado final <hr>" + 
				"Total de valores: " + total + 
				"<br>Total de valores pares: " + pares +
				"<br>Total de valores impares: " + impares +
				"<br>Total de valores acima de cem: " + acimaCem +
				"<br>Média dos valores (arredondada): " + Math.round(media) +
				"</html>");
	}

}
