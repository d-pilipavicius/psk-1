package com.example.psk_1.rest;

import com.example.psk_1.interceptors.Timed;
import com.example.psk_1.rest.contracts.GeneratedIdDTO;
import com.example.psk_1.service.DelayedIdGenerator;
import com.example.psk_1.service.IdGenerator;
import com.example.psk_1.service.RandomStudentIdGenerator;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@ApplicationScoped
@Timed
@Path("/generateId")
public class GenerateIdController {
    @Inject
    private DelayedIdGenerator delayedIdGenerator;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response prepareStudentId() {
        UUID uuid = delayedIdGenerator.startGeneration();

        return Response.ok(uuid).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudentId(@PathParam("id") String uuid) {
        try {
            String response = delayedIdGenerator.getItem(uuid);

            GeneratedIdDTO generatedIdDTO = new GeneratedIdDTO();

            generatedIdDTO.setResponse(response);

            return Response.ok(generatedIdDTO).build();
        } catch (InterruptedException | ExecutionException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
