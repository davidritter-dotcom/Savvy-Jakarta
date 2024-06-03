package ch.ffhs.service;

import ch.ffhs.model.DiaryEntry;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

import java.lang.invoke.MethodHandles;
import java.util.logging.Logger;

@ApplicationScoped
@Transactional
public class DiaryEntryService {
    private static final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

    @PersistenceContext(unitName = "diaryentry")
    private EntityManager entityManager;

    public Response createDiaryEntry(DiaryEntry diaryEntry) {
        entityManager.persist(diaryEntry);
        return Response.status(Response.Status.CREATED).entity(diaryEntry).build();
    }

    public Response getAll() {
        return Response.ok().entity(entityManager.createQuery("SELECT d FROM DiaryEntry d", DiaryEntry.class).getResultList()).build();
    }

    public Response getById(Long id) {
        return Response.ok().entity(entityManager.find(DiaryEntry.class, id)).build();
    }
}

