package ch.ffhs.service;

import ch.ffhs.model.DiaryEntry;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
@Transactional
public class DiaryEntryService {

    @PersistenceContext(unitName = "diary")
    private EntityManager em;

    public Response create(DiaryEntry diaryEntry) {
        em.persist(diaryEntry);
        return Response.status(Response.Status.CREATED).entity(diaryEntry).build();
    }

    public Response update(DiaryEntry diaryEntry) {
        em.merge(diaryEntry);
        return Response.status(Response.Status.OK).entity(diaryEntry).build();
    }

    public Response getAll() {
        return Response.ok().entity(em.createQuery("SELECT d FROM DiaryEntry d", DiaryEntry.class).getResultList()).build();
    }


    public Response findByUserId(Long user_id) {
        TypedQuery<DiaryEntry> query = em.createQuery("SELECT e FROM DiaryEntry e WHERE e.user_id = :user_id", DiaryEntry.class);
        query.setParameter("user_id", user_id);
        return Response.ok().entity(query.getResultList()).build();
    }

    public Response getById(Long id) {
        return Response.ok().entity(em.find(DiaryEntry.class, id)).build();
    }

    public DiaryEntry getByIdPlain(Long id) {
        return em.find(DiaryEntry.class, id);
    }

    public void delete(Long id) {
        DiaryEntry entry = em.find(DiaryEntry.class, id);
        if (entry != null) {
            em.remove(entry);
        }
    }
}

