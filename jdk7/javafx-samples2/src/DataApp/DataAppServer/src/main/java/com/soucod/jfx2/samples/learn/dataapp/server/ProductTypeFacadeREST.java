package com.soucod.jfx2.samples.learn.dataapp.server;

/**
 * Description:  此类描述
 * Author: LuDaShi
 * Date: 2021-01-13-15:38
 * UpdateDate: 2021-01-13-15:38
 * FileName: ProductTypeFacadeREST
 * Version: 0.0.0.1
 * Since: 0.0.0.1
 */
@Stateless
@Path("com.javafx.experiments.dataapp.model.producttype")
public class ProductTypeFacadeREST extends AbstractFacade<ProductType> {

    @PersistenceContext(unitName = "DataAppLibraryPU")
    private EntityManager em;

    public ProductTypeFacadeREST() {
        super(ProductType.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<ProductType> findAll() {
        ;
        return super.findAll();
    }

}
