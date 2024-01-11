package br.com.ecommerce.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.ecommerce.dao.ProdutoDAO;
import br.com.ecommerce.entity.Produto;

import java.util.List;

@ManagedBean
@ViewScoped
public class ProdutoBean {

    private Produto produto = new Produto();
    private List<Produto> produtos;

    private ProdutoDAO produtoDAO = new ProdutoDAO();

    // Método para salvar um novo produto no banco de dados
    public void salvarProduto() {
        produtoDAO.salvar(produto);
        limparFormulario();
        atualizarListaProdutos();
    }

    // Método para atualizar um produto existente no banco de dados
    public void atualizarProduto() {
        produtoDAO.atualizar(produto);
        limparFormulario();
        atualizarListaProdutos();
    }

    // Método para excluir um produto do banco de dados
    public void excluirProduto() {
        produtoDAO.excluir(produto.getId());
        limparFormulario();
        atualizarListaProdutos();
    }

    // Método para carregar a lista de produtos do banco de dados
    public void carregarListaProdutos() {
        produtos = produtoDAO.listarTodos();
    }

    // Método para limpar o formulário após uma operação
    private void limparFormulario() {
        produto = new Produto();
    }

    // Método para atualizar a lista de produtos
    private void atualizarListaProdutos() {
        carregarListaProdutos();
    }
}
