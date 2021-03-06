package PadraoStateObserverMemento;

import java.util.Observable;

public class PedidoEstadoEnviar extends Observable implements PedidoEstado {

    String nome = "Enviar";

    public PedidoEstadoEnviar() {
        
    }

    @Override
    public void aberto(Pedido a) {

    }

    @Override
    public void preparando(Pedido a) {

    }

    @Override
    public void pronto(Pedido a) {

    }

    @Override
    public void enviado(Pedido a) {

    }

    @Override
    public void recebido(Pedido a) {
        a.setEstado(new PedidoEstadoReceber());
    }

    @Override
    public String getNomeEstado() {
        return nome;
    }

}
