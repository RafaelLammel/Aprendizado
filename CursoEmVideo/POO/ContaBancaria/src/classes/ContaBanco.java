package classes;

public class ContaBanco {

    public int numConta;
    protected String tipo;
    private String dono;
    private float saldo;
    private boolean status;

    public ContaBanco() {
        this.status = false;
        this.saldo = 0;
    }

    public void abrirConta(String tipo, String dono) {
        if(tipo.equals("CC") || tipo.equals("CP")) {
            this.status = true;
            this.tipo = tipo;
            if(this.tipo.equals("CC")) {
                this.saldo = 50;
            } else if(this.tipo.equals("CP")) {
                this.saldo = 150;
            }
            this.dono = dono;
        }
    }

    public void fecharConta() {
        if(this.saldo == 0) {
            this.status = false;
        }
    }

    public void depositar(float deposito) {
        if(this.status) {
            this.saldo += deposito;
        }
    }

    public void sacar(float saque) {
        if(this.status && this.saldo >= saque) {
            this.saldo -= saque;
        }
    }

    public void pagarMensalidade() {
        if(this.tipo == "CC") {
            this.saldo -= 12;
        } else if(this.tipo == "CP") {
            this.saldo -= 20;
        }
    }

    public int getNumConta() {
        return numConta;
    }

    public void setNumConta(int numConta) {
        this.numConta = numConta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDono() {
        return dono;
    }

    public void setDono(String dono) {
        this.dono = dono;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
