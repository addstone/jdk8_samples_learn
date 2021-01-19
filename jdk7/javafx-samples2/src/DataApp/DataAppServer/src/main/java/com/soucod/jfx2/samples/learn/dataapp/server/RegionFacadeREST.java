package com.soucod.jfx2.samples.learn.dataapp.server;

/**
 * Description:  此类描述
 * Author: LuDaShi
 * Date: 2021-01-13-15:38
 * UpdateDate: 2021-01-13-15:38
 * FileName: RegionFacadeREST
 * Version: 0.0.0.1
 * Since: 0.0.0.1
 */

@Stateless
@Path("com.javafx.experiments.dataapp.model.region")
public class RegionFacadeREST extends AbstractFacade<Region> {

    @PersistenceContext(unitName = "DataAppLibraryPU")
    private EntityManager em;

    public RegionFacadeREST() {
        super(Region.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @GET
    @Path("/international/{international}")
    @Produces({"application/xml", "application/json"})
    public List<Region> findInternational(@PathParam("international")
            Integer international) {
        Query q = getEntityManager().createNamedQuery("Region.findByInternational");
        Parameter<Integer> p = q.getParameter("international", Integer.class);
        q.setParameter(p, international);
        return q.getResultList();
    }
}
