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
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEntryById(@PathParam("id") Long id, DiaryEntry updatedEntry ) {
        logger.info("updateing entry with id: " + id);
        DiaryEntry existing = diaryEntryService.getByIdPlain(id);
        if (existing == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existing.setTitle(updatedEntry.getTitle());
        existing.setContent(updatedEntry.getContent());
        return diaryEntryService.update(existing);
    }

    @GET
    @Path("nsa")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEntries() {
        return diaryEntryService.getAll();
    }

    @GET
    @Path("/{user_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByUserId(@PathParam("user_id") String user_id) {
        return diaryEntryService.findByUserId(user_id);
    }


    @POST
    @Path("create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createEntry(DiaryEntry entry) {
        return diaryEntryService.create(entry);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteEntry(@PathParam("id") Long id) {
        diaryEntryService.delete(id);
        return Response.noContent().build();
    }
}
