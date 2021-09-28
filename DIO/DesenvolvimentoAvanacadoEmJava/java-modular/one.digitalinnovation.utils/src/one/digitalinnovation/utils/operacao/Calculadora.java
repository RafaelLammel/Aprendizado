package one.digitalinnovation.utils.operacao;

import one.digitalinnovation.utils.operacao.internal.DivHelper;
import one.digitalinnovation.utils.operacao.internal.MultHelper;
import one.digitalinnovation.utils.operacao.internal.SubHelper;
import one.digitalinnovation.utils.operacao.internal.SumHelper;

public class Calculadora {

    private SumHelper sumHelper;
    private SubHelper subHelper;
    private MultHelper multHelper;
    private DivHelper divHelper;

    public Calculadora() {
        this.sumHelper = new SumHelper();
        this.subHelper = new SubHelper();
        this.multHelper = new MultHelper();
        this.divHelper = new DivHelper();
    }

    public int sum(int a, int b) {
        return sumHelper.execute(a, b);
    }

    public int sub(int a, int b) {
        return subHelper.execute(a, b);
    }

    public int mult(int a, int b) {
        return multHelper.execute(a, b);
    }

    public int div(int a, int b) {
        return divHelper.execute(a, b);
    }

}
