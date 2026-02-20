package org.eclipse.jakarta.rest;


import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import org.eclipse.jakarta.EJB.PedidoService;
import org.eclipse.jakarta.entities.Pedido;

@Path("/pedidos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PedidoResource {

    @EJB
    private PedidoService pedidoService;

    @GET
    public List<Pedido> getAll() {
        return pedidoService.findAll();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        Pedido p = pedidoService.findById(id);
        if (p == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(p).build();
    }

    @POST
    public Response create(Pedido pedido) {
        pedidoService.save(pedido);
        return Response.status(Response.Status.CREATED).entity(pedido).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, Pedido pedido) {
        pedido.setId(id);
        pedidoService.update(pedido);
        return Response.ok(pedido).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        pedidoService.delete(id);
        return Response.noContent().build();
    }
}