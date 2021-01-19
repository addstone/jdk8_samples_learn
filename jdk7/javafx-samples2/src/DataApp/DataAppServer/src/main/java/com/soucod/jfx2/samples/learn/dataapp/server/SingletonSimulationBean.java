package com.soucod.jfx2.samples.learn.dataapp.server;

/**
 * Description:  此类描述
 * Author: LuDaShi
 * Date: 2021-01-13-15:38
 * UpdateDate: 2021-01-13-15:38
 * FileName: SingletonSimulationBean
 * Version: 0.0.0.1
 * Since: 0.0.0.1
 */

@Singleton
@LocalBean
@Startup
public class SingletonSimulationBean {

    @PersistenceContext(unitName = "DataAppLibraryPU")
    private EntityManager em;

    SalesSimulator sim;
    DailySalesGenerator hourlySalesGenerator;

    Timer simulationTimer;
    Timer hourlySalesTimer;

    @Resource
    TimerService timerService;

    @PostConstruct
    public void applicationStartup() {
        sim = new SalesSimulator(em);
        hourlySalesGenerator = new DailySalesGenerator(em);

        simulationTimer = timerService.createTimer(SalesSimulator.TIME_BETWEEN_SALES, SalesSimulator.TIME_BETWEEN_SALES, "Creating Auto Sales simulation");
        //run on startup, move over old sales
        hourlySalesTimer = timerService.createTimer(1000, 60 * 1000 * 60 * 24, "Creating data migration service");
    }

    @Timeout
    public void timeout(Timer timer) {
        if (timer.equals(simulationTimer)) {
            sim.run();
        } else if (timer.equals(hourlySalesTimer)) {
            hourlySalesGenerator.run();
        }
    }

}
