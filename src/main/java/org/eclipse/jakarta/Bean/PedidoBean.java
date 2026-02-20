package org.eclipse.jakarta.Bean;

import org.eclipse.jakarta.EJB.PedidoService;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
// import jakarta.enterprise.context.SessionScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import org.eclipse.jakarta.entities.Pedido;

@Named
@RequestScoped
public class PedidoBean implements Serializable {

    @EJB
    private PedidoService pedidoService;

    private Pedido pedido = new Pedido();
    private List<Pedido> pedidos;

    @PostConstruct
    public void init() {
        pedidos = pedidoService.findAll();
    }

    public void prepararNuevo() {
        pedido = new Pedido();
    }

    public void guardar() {
        if (pedido.getId() == null) {
            pedidoService.save(pedido);
        } else {
            pedidoService.update(pedido);
        }
        pedido = new Pedido();
        pedidos = null; // refrescar lista
    }

    public void editar(Pedido p) {
        this.pedido = p;
    }

    public void eliminar(Pedido p) {
        pedidoService.delete(p.getId());
        pedidos = null;
    }

    public List<Pedido> getPedidos() {
        if (pedidos == null) {
            pedidos = pedidoService.findAll();
        }
        return pedidos;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}