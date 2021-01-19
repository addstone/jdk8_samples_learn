package com.soucod.jfx2.samples.learn.dataapp.server;

/**
 * Description:  此类描述
 * Author: LuDaShi
 * Date: 2021-01-13-15:38
 * UpdateDate: 2021-01-13-15:38
 * FileName: LiveSalesListFacadeREST
 * Version: 0.0.0.1
 * Since: 0.0.0.1
 */

@Stateless
@Path("com.javafx.experiments.dataapp.model.livesaleslist")
public class LiveSalesListFacadeREST extends AbstractFacade<LiveSalesList> {

    @PersistenceContext(unitName = "DataAppLibraryPU")
    private EntityManager em;

    public LiveSalesListFacadeREST() {
        super(LiveSalesList.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(LiveSalesList entity) {
        super.create(entity);
    }

    @PUT
    @Override
    @Consumes({"application/xml", "application/json"})
    public void edit(LiveSalesList entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id")
            Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public LiveSalesList find(@PathParam("id")
            Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<LiveSalesList> findAll() {
        return super.findAll();
    }

    @GET
    @Produces({"application/xml", "application/json"})
    @Path("/recent/")
    public List<LiveSalesList> findRecent() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(LiveSalesList.class));
        Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(500);
        return q.getResultList();
    }

    @GET
    @Produces({"application/xml", "application/json"})
    @Path("/recent/region/{regionName}")
    public List<LiveSalesList> findRecentRegion(@PathParam("regionName") String regionName) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery cq = cb.createQuery();
        Root<LiveSalesList> liveSalesList = cq.from(LiveSalesList.class);
        cq.select(liveSalesList);
        cq.where(cb.equal(liveSalesList.get(LiveSalesList_.region), regionName));
        Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(500);
        return q.getResultList();
    }

    @GET
    @Produces({"application/xml", "application/json"})
    @Path("/recent/region/{regionName}/{orderLineId}")
    public List<LiveSalesList> findRecentRegionFrom(@PathParam("regionName") String regionName, @PathParam("orderLineId") Integer orderLineId) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery cq = cb.createQuery();
        Root<LiveSalesList> liveSalesList = cq.from(LiveSalesList.class);
        cq.select(liveSalesList);
        cq.where(cb.and(
                cb.equal(liveSalesList.get(LiveSalesList_.region), regionName),
                cb.gt(liveSalesList.get(LiveSalesList_.orderLineId), orderLineId)
        ));
        Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(500);
        return q.getResultList();
    }

    @GET
    @Produces({"application/xml", "application/json"})
    @Path("/recent/producttype/{id}")
    public List<LiveSalesList> findRecentProductType(@PathParam("id") Integer productTypeId) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery cq = cb.createQuery();
        Root<LiveSalesList> liveSalesList = cq.from(LiveSalesList.class);
        cq.select(liveSalesList);
        cq.where(cb.equal(liveSalesList.get(LiveSalesList_.productTypeId), productTypeId));
        Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(500);
        return q.getResultList();
    }

    @GET
    @Produces({"application/xml", "application/json"})
    @Path("/recent/producttype/{id}/{orderLineId}")
    public List<LiveSalesList> findRecentProductTypeFrom(@PathParam("id") Integer productTypeId, @PathParam("orderLineId") Integer orderLineId) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery cq = cb.createQuery();
        Root<LiveSalesList> liveSalesList = cq.from(LiveSalesList.class);
        cq.select(liveSalesList);
        cq.where(cb.and(
                cb.equal(liveSalesList.get(LiveSalesList_.productTypeId), productTypeId),
                cb.gt(liveSalesList.get(LiveSalesList_.orderLineId), orderLineId)
        ));
        Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(500);
        return q.getResultList();
    }

    @GET
    @Produces({"application/xml", "application/json"})
    @Path("/recent/region/producttype/{regionName}/{productTypeId}")
    public List<LiveSalesList> findRecentRegionProductType(@PathParam("regionName") String regionName, @PathParam("productTypeId") Integer productTypeId) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery cq = cb.createQuery();
        Root<LiveSalesList> liveSalesList = cq.from(LiveSalesList.class);
        cq.select(liveSalesList);
        cq.where(cb.and(
                cb.equal(liveSalesList.get(LiveSalesList_.productTypeId), productTypeId),
                cb.equal(liveSalesList.get(LiveSalesList_.region), regionName)
        ));
        Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(500);
        return q.getResultList();
    }

    @GET
    @Produces({"application/xml", "application/json"})
    @Path("/recent/region/producttype/{regionName}/{productTypeId}/{orderLineId}")
    public List<LiveSalesList> findRecentRegionProductTypeFrom(@PathParam("regionName") String regionName, @PathParam("productTypeId") Integer productTypeId, @PathParam("orderLineId") Integer orderLineId) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery cq = cb.createQuery();
        Root<LiveSalesList> liveSalesList = cq.from(LiveSalesList.class);
        cq.select(liveSalesList);
        cq.where(cb.and(
                cb.equal(liveSalesList.get(LiveSalesList_.productTypeId), productTypeId),
                cb.equal(liveSalesList.get(LiveSalesList_.region), regionName),
                cb.gt(liveSalesList.get(LiveSalesList_.orderLineId), orderLineId)
        ));
        Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(500);
        return q.getResultList();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<LiveSalesList> findRange(@PathParam("from")
            Integer from, @PathParam("to")
            Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("/date/{from}")
    @Produces({"application/xml", "application/json"})
    public List<LiveSalesList> findFrom(@PathParam("from") Integer from) {
        Query q = getEntityManager().createNamedQuery("LiveSalesList.findFromOrderLineId");
        Parameter<Integer> p = q.getParameter("orderLineId", Integer.class);
        q.setParameter(p, from);
        return q.getResultList();
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @java.lang.Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
