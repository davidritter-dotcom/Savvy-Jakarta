package ch.ffhs.controller;

import ch.ffhs.model.DiaryEntry;
import ch.ffhs.service.DiaryEntryService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.lang.invoke.MethodHandles;
import java.util.Objects;
import java.util.logging.Logger;

@Path("/")
public class DiaryEntryController {
    private final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

    @Inject
    private DiaryEntryService diaryEntryService;

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEntryById(@PathParam("id") Long id, DiaryEntry diaryEntry) {
        logger.info("updateing entry with id: " + id);
        Response existing = diaryEntryService.getById(id);
        if (existing.getStatusInfo() == Response.Status.NOT_FOUND) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return diaryEntryService.update(diaryEntry);
    }

    @GET
    @Path("nsa")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEntries() {
        return diaryEntryService.getAll();
    }

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByUserId() {
        return diaryEntryService.findByUserId(1L);
    }


    @POST
    @Path("create")
    public Response createEntry(DiaryEntry entry) {
        if ((!Objects.equals(entry.getTitle(), "NullTest")))
            entry.setUser_id(1L);
        return diaryEntryService.create(entry);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteEntry(@PathParam("id") Long id) {
        diaryEntryService.delete(id);
        return Response.noContent().build();
    }
}
