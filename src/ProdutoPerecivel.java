import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

public class ProdutoPerecivel extends Produto{

    private double DESCONTO = 0.25;
    private int PRAZO_DESCONTO = 7;
    private LocalDate dataValidade;

    public ProdutoPerecivel(String desc, double precoCusto, double margemLucro, LocalDate dataValidade){
        super(desc, precoCusto, margemLucro);
        if(dataValidade.isBefore(LocalDate.now())){
            throw new IllegalArgumentException("Data de validade menor que a data atual");
        }
        this.dataValidade = dataValidade;
    }

    @Override
    public double valorDeVenda(){
        double valor = precoCusto + (precoCusto * margemLucro);
        int diasAteAhValidade = LocalDate.now().until(dataValidade).getDays();
        if (diasAteAhValidade <= PRAZO_DESCONTO) {
            valor = (precoCusto + (precoCusto * margemLucro));
            double valorComDesconto = valor - (valor * DESCONTO);
            valor = valorComDesconto;
        }

        return valor;
    }

    public boolean teste(){
        boolean t = false;

        if (ChronoUnit.DAYS.between(LocalDate.now(), dataValidade) <= PRAZO_DESCONTO) {
            t = true;
        }

        return t;
    }

    @Override
    public String toString() {
        StringBuilder texto = new StringBuilder(toString());
        return super.toString();
    }

}
