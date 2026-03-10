public class ItemDePedido {

    // Atributos encapsulados
    private Produto produto;
    private int quantidade;
    private double precoVenda;

    /**
     * Construtor da classe ItemDePedido.
     * O precoVenda deve ser capturado do produto no momento da criação do item,
     * garantindo que alterações futuras no preço do produto não afetem este pedido.
     */
    public ItemDePedido(Produto produto, int quantidade, double precoVenda) {
        this.produto = produto;
        this.precoVenda = precoVenda;
        if (quantidade > 0) {
            this.quantidade = quantidade;
        }
        else{
            throw new IllegalArgumentException("A quantidade do item deve ser maior que 0");
        }
        
    }

    public double calcularSubtotal() {
        return precoVenda * quantidade;
    }

    // --- Sobrescrita do método equals ---

    /**
     * Compara a igualdade entre dois itens de pedido.
     * A regra de negócio define que dois itens são iguais se possuírem o mesmo Produto.
     */
    @Override
    public boolean equals(Object obj) {
        ItemDePedido outro = (ItemDePedido) obj;
        return produto.equals(outro.produto);
    }

    public Produto produto(){
        return produto;
    }

    public int quantidadeDeProdutos(){
        return quantidade;
    }

    public void incrementarQuantidade(int incremento) {
        quantidade += incremento;
    }

    public double alterarPrecoVenda(double novoValor){
        precoVenda = novoValor;
        return precoVenda;
    }

    public double getPrecoVenda(){
        return precoVenda;
    }
}
