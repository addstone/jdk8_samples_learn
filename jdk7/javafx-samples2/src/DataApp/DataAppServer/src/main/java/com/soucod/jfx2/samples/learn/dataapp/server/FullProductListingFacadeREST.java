package com.soucod.jfx2.samples.learn.dataapp.server;

/**
 * Description:  此类描述
 * Author: LuDaShi
 * Date: 2021-01-13-15:38
 * UpdateDate: 2021-01-13-15:38
 * FileName: FullProductListingFacadeREST
 * Version: 0.0.0.1
 * Since: 0.0.0.1
 */

@Stateless
@Path("com.javafx.experiments.dataapp.model.fullproductlisting")
public class FullProductListingFacadeREST extends AbstractFacade<FullProductListing> {

    @PersistenceContext(unitName = "DataAppLibraryPU")
    private EntityManager em;

    public FullProductListingFacadeREST() {
        super(FullProductListing.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(FullProductListing entity) {
        super.create(entity);
    }

    @PUT
    @Override
    @Consumes({"application/xml", "application/json"})
    public void edit(FullProductListing entity) {
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
    public FullProductListing find(@PathParam("id")
            Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<FullProductListing> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<FullProductListing> findRange(@PathParam("from")
            Integer from, @PathParam("to")
            Integer to) {
        return super.findRange(new int[]{from, to});
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
