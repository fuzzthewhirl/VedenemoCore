package org.vedenemo.rest

import jakarta.ws.rs.*
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.core.MediaType
import jakarta.inject.Inject

@Path("/model")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
class VedenemoCoreModel {

    @Inject
    lateinit var connector: VedenemoModelConnector

    @POST
    @Path("/echo")
    fun echo(payload: String): Response {
        return if (VedenemoModelConnector.broadcastToClients(payload)){
            Response.ok("Echoed to WebSocket clients.").build()
        } else {
            Response.status(Response.Status.SERVICE_UNAVAILABLE)
                .entity("No WebSocket clients connected.").build()
        }
    }
}
