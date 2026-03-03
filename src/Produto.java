import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public abstract class Produto {
	
	private static final double MARGEM_PADRAO = 0.2;
	protected String descricao;
	protected double precoCusto;
	protected double margemLucro;
	
	/**
     * Inicializador privado. Os valores default, em caso de erro, são:
     * "Produto sem descrição", R$ 0.00, 0.0  
     * @param desc Descrição do produto (mínimo de 3 caracteres)
     * @param precoCusto Preço do produto (mínimo 0.01)
     * @param margemLucro Margem de lucro (mínimo 0.01)
     */
	private void init(String desc, double precoCusto, double margemLucro) {
		
		if ((desc.length() >= 3) && (precoCusto > 0.0) && (margemLucro > 0.0)) {
			descricao = desc;
			this.precoCusto = precoCusto;
			this.margemLucro = margemLucro;
		} else {
			throw new IllegalArgumentException("Valores inválidos para os dados do produto.");
		}
	}
	
	/**
     * Construtor completo. Os valores default, em caso de erro, são:
     * "Produto sem descrição", R$ 0.00, 0.0  
     * @param desc Descrição do produto (mínimo de 3 caracteres)
     * @param precoCusto Preço do produto (mínimo 0.01)
     * @param margemLucro Margem de lucro (mínimo 0.01)
     */
	protected Produto(String desc, double precoCusto, double margemLucro) {
		init(desc, precoCusto, margemLucro);
	}
	
	/**
     * Construtor sem margem de lucro - fica considerado o valor padrão de margem de lucro.
     * Os valores default, em caso de erro, são:
     * "Produto sem descrição", R$ 0.00 
     * @param desc Descrição do produto (mínimo de 3 caracteres)
     * @param precoCusto Preço do produto (mínimo 0.01)
     */
	protected Produto(String desc, double precoCusto) {
		init(desc, precoCusto, MARGEM_PADRAO);
	}
	
	 /**
     * Retorna o valor de venda do produto, considerando seu preço de custo e margem de lucro.
     * @return Valor de venda do produto (double, positivo)
     */
	public double valorDeVenda() {
		return (precoCusto * (1.0 + margemLucro));
	}

	/**
	* Gera uma linha de texto a partir dos dados do produto
	* @return Uma string no formato "tipo; descrição;preçoDeCusto;margemDeLucro;[dataDeValidade]"
	*/
	public abstract String gerarDadosTexto();

	/**
	* Cria um produto a partir de uma linha de dados em formato texto. A linha de dados deve estar de acordo com a
	formatação
	* "tipo; descrição;preçoDeCusto;margemDeLucro;[dataDeValidade]"
	* ou o funcionamento não será garantido. Os tipos são 1 para produto não perecível e 2 para perecível.
	* @param linha Linha com os dados do produto a ser criado.
	* @return Um produto com os dados recebidos
	*/
	static Produto criarDoTexto(String linha){
		Produto novoProduto = null;
		String[] dados = linha.split(";");
		int tipo = Integer.parseInt(dados[0]);
		String descricao = dados[1];
		double preco = Double.parseDouble(dados[2]);
		double margem = Double.parseDouble(dados[3]);

		if (Integer.parseInt(dados[0]) == 1) {
			novoProduto = new ProdutoNaoPerecivel(descricao, preco, margem);
		}else{
			LocalDate validade = formatarData(dados[4]);
			novoProduto = new ProdutoPerecivel(descricao, preco, margem, validade);
		}
		
		/*Você deve implementar aqui a lógica que separa os dados existentes na String linha, verifica se o produto é do
		tipo 1 ou 2 e constrói o objeto adequado, com os dados fornecidos de acordo com seu tipo. O objeto construído é
		retornado pelo método*/
		return novoProduto;
	}

	private static LocalDate formatarData(String dados){
		String[] data = dados.split("/");
		int dia = Integer.parseInt(data[0]);
		int mes = Integer.parseInt(data[1]);
		int ano = Integer.parseInt(data[2]);
		LocalDate validade = LocalDate.of(ano, mes, dia);
		return validade;
	}

	@Override
	public boolean equals(Object obj){
		Produto outro = (Produto)obj;
		return this.descricao.toLowerCase().equals(outro.descricao.toLowerCase());
	}
	
	/**
     * Descrição, em string, do produto, contendo sua descrição e o valor de venda.
     *  @return String com o formato:
     * [NOME]: R$ [VALOR DE VENDA]
     */
    @Override
	public String toString() {
    	
    	NumberFormat moeda = NumberFormat.getCurrencyInstance();
    	
		return String.format("NOME: " + descricao + ": " + moeda.format(valorDeVenda()));
	}
}