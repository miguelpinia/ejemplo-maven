package com.miguel.proyecto.controller;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class TestJob implements Job {

    @Override
    public void execute(final JobExecutionContext ctx)  throws JobExecutionException {
        System.out.println("\n\n\n");
        System.out.println("Ejecutando tareas programadas.");
        Date now = new Date();
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy ' a las ' hh:mm:ss");
        System.out.println(String.format("Fecha de ejecución: %s", format.format(now)));
        System.out.println("\n\n\n");
    }

}
