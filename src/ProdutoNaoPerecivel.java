public class ProdutoNaoPerecivel extends Produto {
    private double valorVenda;

    public ProdutoNaoPerecivel(String desc, double precoCusto, double margemLucro){
        super(desc, precoCusto, margemLucro);
    }

    public ProdutoNaoPerecivel(String desc, double precoCusto){
        super(desc, precoCusto);
    }

    @Override
    public double valorDeVenda() {
        return precoCusto + (precoCusto * margemLucro);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public String gerarDadosTexto() {
        String precoFormatado = String.format("%.2f", precoCusto).replace("," , ".");
        String margemFormatado = String.format("%.2f", margemLucro).replace("," , ".");
        return String.format("1;%s;%s;%s", descricao, precoFormatado, margemFormatado);
    }
}
