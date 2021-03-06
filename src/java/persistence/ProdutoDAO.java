package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Produto;

public class ProdutoDAO {

    private static ProdutoDAO instance = new ProdutoDAO();
    private PreparedStatement operacaoInsereProduto;
    private PreparedStatement operacaoListaProduto;
    private PreparedStatement operacaoAtualizaProduto;
    private PreparedStatement operacaoDeletaProduto;

    public static ProdutoDAO getInstance() {
        return instance;
    }

    public void saveProduto(Produto produto) throws SQLException, ClassNotFoundException {
        operacaoInsereProduto = DatabaseLocator.getInstance().getConnection().prepareStatement("insert into produto (nome, valor, dificuldade, tipoProduto, restaurantecod) values (?, ?, ?, ?, ?)");
        operacaoInsereProduto.clearParameters();
        operacaoInsereProduto.setString(1, produto.getNome());
        operacaoInsereProduto.setDouble(2, produto.getValor());
        operacaoInsereProduto.setInt(3, produto.getDificuldade());
        operacaoInsereProduto.setInt(4, produto.getTipoItem());
        operacaoInsereProduto.setInt(5, produto.getRestaurantecod());
        operacaoInsereProduto.execute();
    }

    public List<Produto> listAllFromRestaurante(Integer idRestaurante) throws SQLException, ClassNotFoundException {
        List<Produto> produtos = new ArrayList<>();
        operacaoListaProduto = DatabaseLocator.getInstance().getConnection().prepareStatement("select * from produto where restaurantecod = ?");
        operacaoListaProduto.clearParameters();
        operacaoListaProduto.setInt(1, idRestaurante);
        ResultSet resultado = operacaoListaProduto.executeQuery();
        while (resultado.next()) {
            Produto produto = new Produto();
            produto.setValor(resultado.getDouble("valor"));
            produto.setProdutocod(resultado.getInt("produtocod"));
            produto.setNome(resultado.getString("nome"));
            produto.setDificuldade(resultado.getInt("dificuldade"));
            produto.setTipoItem(resultado.getInt("tipoProduto"));
            produto.setRestaurantecod(idRestaurante);
            produtos.add(produto);
        }
        return produtos;
    }

    public Produto listProduto(Integer idProduto) throws SQLException, ClassNotFoundException {
        Produto produto = new Produto();
        operacaoListaProduto = DatabaseLocator.getInstance().getConnection().prepareStatement("select * from produto where produtocod = ?");
        operacaoListaProduto.clearParameters();
        operacaoListaProduto.setInt(1, idProduto);
        ResultSet resultado = operacaoListaProduto.executeQuery();
        while (resultado.next()) {
            produto.setValor(resultado.getDouble("valor"));
            produto.setProdutocod(resultado.getInt("produtocod"));
            produto.setNome(resultado.getString("nome"));
            produto.setDificuldade(resultado.getInt("dificuldade"));
            produto.setTipoItem(resultado.getInt("tipoProduto"));
            produto.setRestaurantecod(resultado.getInt("restaurantecod"));
        }
        return produto;
    }

    public void updateProduto(Produto produto) throws SQLException, ClassNotFoundException {
        operacaoAtualizaProduto = DatabaseLocator.getInstance().getConnection().prepareStatement("update produto set nome = ?, valor = ?, tipoProduto = ?, dificuldade = ? where produtocod = ?");
        operacaoAtualizaProduto.clearParameters();
        operacaoAtualizaProduto.setString(1, produto.getNome());
        operacaoAtualizaProduto.setDouble(2, produto.getValor());
        operacaoAtualizaProduto.setInt(3, produto.getTipoItem());
        operacaoAtualizaProduto.setInt(4, produto.getDificuldade());
        operacaoAtualizaProduto.setInt(5, produto.getProdutocod());
        operacaoAtualizaProduto.executeUpdate();
    }

    public void deleteProduto(Integer produtoCod) throws SQLException, ClassNotFoundException {
        operacaoDeletaProduto = DatabaseLocator.getInstance().getConnection().prepareStatement("delete from produto where produtocod = ?");
        operacaoDeletaProduto.clearParameters();
        operacaoDeletaProduto.setInt(1, produtoCod);
        operacaoDeletaProduto.execute();
    }
}
