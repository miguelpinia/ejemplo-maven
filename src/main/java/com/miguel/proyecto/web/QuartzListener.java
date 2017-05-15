package com.miguel.proyecto.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.quartz.impl.StdSchedulerFactory;

import com.miguel.proyecto.controller.TestJob;

@WebListener
public class QuartzListener extends QuartzInitializerListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        super.contextInitialized(sce);
        ServletContext ctx = sce.getServletContext();
        StdSchedulerFactory factory = (StdSchedulerFactory) ctx.getAttribute(QUARTZ_FACTORY_KEY);
        try {
            Scheduler scheduler = factory.getScheduler();
            JobDetail jobDetail = JobBuilder.newJob(TestJob.class).build();
            // Podemos calcular otras expresiones con http://www.cronmaker.com/
            Trigger trigger = TriggerBuilder.newTrigger().
                withIdentity("simple").
                withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * 1/1 * ? *")). // Ejecutamos la tarea una vez cada minuto.
                startNow().
                build();
            scheduler.scheduleJob(jobDetail, trigger);
            scheduler.start();
        } catch (Exception e) {
            ctx.log("Hubo un error al calendarizar la tarea.", e);
        }
    }

}
