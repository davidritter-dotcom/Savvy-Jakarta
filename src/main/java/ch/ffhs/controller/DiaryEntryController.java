package ch.ffhs.controller;

import ch.ffhs.model.DiaryEntry;
import ch.ffhs.service.DiaryEntryService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.lang.invoke.MethodHandles;
import java.util.logging.Logger;

@Path("/diary")
public class DiaryEntryController {
    private final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

    @Inject
    private DiaryEntryService diaryEntryService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEntryById(@PathParam("id") Long id) {
        logger.info("getting entry by id: " + id);
        return diaryEntryService.getById(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEntries() {
        return diaryEntryService.getAll();
    }

    @GET
    @Path("/create")
    public Response create(){
        DiaryEntry de = new DiaryEntry();
        de.setContent("hello my dude");
        return diaryEntryService.createDiaryEntry(de);
    }
}
