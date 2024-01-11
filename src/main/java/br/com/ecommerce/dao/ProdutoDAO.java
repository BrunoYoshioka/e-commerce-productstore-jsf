package br.com.ecommerce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.ecommerce.entity.Produto;
import br.com.ecommerce.util.ConexaoMySQL;

public class ProdutoDAO {
    private static final String INSERIR_PRODUTO = "INSERT INTO produto (nome, imagem, descricao) VALUES (?, ?, ?)";
    private static final String ATUALIZAR_PRODUTO = "UPDATE produto SET nome=?, imagem=?, descricao=? WHERE id=?";
    private static final String EXCLUIR_PRODUTO = "DELETE FROM produto WHERE id=?";
    private static final String LISTAR_TODOS_PRODUTOS = "SELECT * FROM produto";

    public void salvar(Produto produto) {
        try (Connection conexao = ConexaoMySQL.getConexao();
             PreparedStatement ps = conexao.prepareStatement(INSERIR_PRODUTO)) {

            ps.setString(1, produto.getNome());
            ps.setString(2, produto.getImagem());
            ps.setString(3, produto.getDescricao());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Produto produto) {
        try (Connection conexao = ConexaoMySQL.getConexao();
             PreparedStatement ps = conexao.prepareStatement(ATUALIZAR_PRODUTO)) {

            ps.setString(1, produto.getNome());
            ps.setString(2, produto.getImagem());
            ps.setString(3, produto.getDescricao());
            ps.setLong(4, produto.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(Long produtoId) {
        try (Connection conexao = ConexaoMySQL.getConexao();
             PreparedStatement ps = conexao.prepareStatement(EXCLUIR_PRODUTO)) {

            ps.setLong(1, produtoId);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Produto> listarTodos() {
        List<Produto> produtos = new ArrayList<>();

        try (Connection conexao = ConexaoMySQL.getConexao();
             PreparedStatement ps = conexao.prepareStatement(LISTAR_TODOS_PRODUTOS);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getLong("id"));
                produto.setNome(rs.getString("nome"));
                produto.setImagem(rs.getString("imagem"));
                produto.setDescricao(rs.getString("descricao"));
                produtos.add(produto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produtos;
    }
}
