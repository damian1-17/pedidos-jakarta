package org.eclipse.jakarta.EJB;


import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import org.eclipse.jakarta.entities.Pedido;

@Stateless
public class PedidoService {

    @PersistenceContext(unitName = "delacruzPU")
    private EntityManager em;

    public List<Pedido> findAll() {
        return em.createNamedQuery("Pedido.findAll", Pedido.class).getResultList();
    }

    public Pedido findById(Long id) {
        return em.find(Pedido.class, id);
    }

    public void save(Pedido pedido) {
        em.persist(pedido);
    }

    public void update(Pedido pedido) {
        em.merge(pedido);
    }

    public void delete(Long id) {
        Pedido pedido = em.find(Pedido.class, id);
        if (pedido != null) {
            em.remove(pedido);
        }
    }
}