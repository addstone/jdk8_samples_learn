package com.soucod.jfx2.samples.learn.dataapp.server;

/**
 * Description:  此类描述
 * Author: LuDaShi
 * Date: 2021-01-13-15:38
 * UpdateDate: 2021-01-13-15:38
 * FileName: DailySalesHeatMapFacadeREST
 * Version: 0.0.0.1
 * Since: 0.0.0.1
 */

@Stateless
@Path("com.javafx.experiments.dataapp.model.heatmap")
public class DailySalesHeatMapFacadeREST {

    @PersistenceContext(unitName = "DataAppLibraryPU")
    private EntityManager em;

    private static final String BASE_QUERY =
            "select "
                    + "sum(ds1.quantity) ,"
                    + "ds1.stateProvCd "
                    + "from "
                    + "DailySales ds1 "
                    + "where "
                    + "(ds1.date >= :oldStartDate and ds1.date <= :oldEndDate) "
                    + "group by ds1.stateProvCd ";

    private static final String BASE_TYPE_QUERY =
            "select "
                    + "sum(ds1.quantity), "
                    + "ds1.stateProvCd "
                    + "from "
                    + "DailySales ds1 "
                    + "left join ds1.productId p "
                    + "where "
                    + "(ds1.date >= :oldStartDate and ds1.date <= :oldEndDate) and "
                    + "p.productTypeId.productTypeId = :productTypeId "
                    + "group by ds1.stateProvCd ";


    private static final String RANGE_QUERY = "select max(d.date), min(d.date) from DailySales d ";

    public DailySalesHeatMapFacadeREST() {
    }

    protected EntityManager getEntityManager() {
        return em;
    }

    private HashMap<String, Long> runBaseQuery(Date date) {
        Calendar cal = Calendar.getInstance();
        long DIFF, TIME = System.currentTimeMillis(), START_TIME = System.currentTimeMillis();
        Query baseQuery = em.createQuery(BASE_QUERY);
        HashMap<String, Long> result = new HashMap<String, Long>();
        {
            cal.setTime(date);
            int dayMin = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
            int dayMax = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            cal.set(Calendar.DAY_OF_MONTH, dayMin);
            Parameter<Date> p1 = baseQuery.getParameter("oldStartDate", Date.class);
            baseQuery.setParameter(p1, cal.getTime());
            cal.set(Calendar.DAY_OF_MONTH, dayMax);
            Parameter<Date> p2 = baseQuery.getParameter("oldEndDate", Date.class);
            baseQuery.setParameter(p2, cal.getTime());

            List<Object[]> resultList = baseQuery.getResultList();

            DIFF = (System.currentTimeMillis() - TIME);
            System.out.println("    Q TIME = " + DIFF + "ms");

            for (int i = 0; i < resultList.size(); i++) {
                Object o[] = resultList.get(i);
                result.put((String) o[1], (Long) o[0]);
            }
        }
        return result;
    }

    private HashMap<String, Long> runProductTypeQuery(Date date1, Integer productTypeId) {
        long DIFF, TIME = System.currentTimeMillis(), START_TIME = System.currentTimeMillis();
        Query baseQuery = em.createQuery(BASE_TYPE_QUERY);
        Calendar cal = Calendar.getInstance();

        HashMap<String, Long> result = new HashMap<String, Long>();
        {
            cal.setTime(date1);
            int dayMin = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
            int dayMax = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            cal.set(Calendar.DAY_OF_MONTH, dayMin);
            Parameter<Date> p1 = baseQuery.getParameter("oldStartDate", Date.class);
            baseQuery.setParameter(p1, cal.getTime());
            cal.set(Calendar.DAY_OF_MONTH, dayMax);
            Parameter<Date> p2 = baseQuery.getParameter("oldEndDate", Date.class);
            baseQuery.setParameter(p2, cal.getTime());
            Parameter<Integer> p3 = baseQuery.getParameter("productTypeId", Integer.class);
            baseQuery.setParameter(p3, productTypeId);

            List<Object[]> resultList = baseQuery.getResultList();

            DIFF = (System.currentTimeMillis() - TIME);
            System.out.println("    Q TIME = " + DIFF + "ms");

            for (int i = 0; i < resultList.size(); i++) {
                Object o[] = resultList.get(i);
                result.put((String) o[1], (Long) o[0]);
            }
        }

        return result;
    }

    @GET
    @Produces({"application/xml", "application/json"})
    @Path("/range/")
    public HeatMapRange findRange() {
        Query baseRangeQuery = em.createQuery(RANGE_QUERY);
        HeatMapRange result = new HeatMapRange();
        Object[] queryResult = (Object[]) baseRangeQuery.getSingleResult();
        result.setMaxDate((Date) queryResult[0]);
        result.setMinDate((Date) queryResult[1]);
        return result;
    }

    @GET
    @Produces({"application/xml", "application/json"})
    @Path("/base/{date1}/{date2}")
    public List<HeatMapQuantity> find(@PathParam("date1") Long longDate1, @PathParam("date2") Long longDate2) {

        Date date1 = new Date(longDate1);
        Date date2 = new Date(longDate2);

        HashMap<String, Long> resultMap1 = runBaseQuery(date1);
        HashMap<String, Long> resultMap2 = runBaseQuery(date2);

        List<HeatMapQuantity> results = new ArrayList<HeatMapQuantity>();
        for (String state : Region.ALL_STATES) {
            Long v1 = resultMap1.get(state);
            if (v1 == null) {
                v1 = 0l;
            }
            Long v2 = resultMap2.get(state);
            if (v2 == null) {
                v2 = 0l;
            }
            results.add(new HeatMapQuantity(v1 - v2, state, Region.getRegionName(state)));
        }
        return results;
    }

    @GET
    @Produces({"application/xml", "application/json"})
    @Path("/producttype/{date1}/{date2}/{productTypeId1}/{productTypeId2}")
    public List<HeatMapQuantity> findProductType(@PathParam("date1") Long longDate1, @PathParam("date2") Long longDate2, @PathParam("productTypeId1") Integer productTypeId1, @PathParam("productTypeId2") Integer productTypeId2) {

        Date date1 = new Date(longDate1);
        Date date2 = new Date(longDate2);

        HashMap<String, Long> resultMap1;
        HashMap<String, Long> resultMap2;
        if (productTypeId1 == -1) {
            resultMap1 = runBaseQuery(date1);
        } else {
            resultMap1 = runProductTypeQuery(date1, productTypeId1);
        }
        if (productTypeId2 == -1) {
            resultMap2 = runBaseQuery(date2);
        } else {
            resultMap2 = runProductTypeQuery(date2, productTypeId2);
        }

        List<HeatMapQuantity> results = new ArrayList<HeatMapQuantity>();
        for (String state : Region.ALL_STATES) {
            Long v1 = resultMap1.get(state);
            if (v1 == null) {
                v1 = 0l;
            }
            Long v2 = resultMap2.get(state);
            if (v2 == null) {
                v2 = 0l;
            }
            results.add(new HeatMapQuantity(v1 - v2, state, Region.getRegionName(state)));
        }
        return results;
    }
}
