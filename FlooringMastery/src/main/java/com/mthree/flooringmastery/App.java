package com.mthree.flooringmastery;

import com.mthree.flooringmastery.controller.FlooringController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(String[] args) {

        Runtime.getRuntime().addShutdownHook(new Thread() {   //Catches ctrl+c escape

            public void run() {

                //Importing spring DI
                ApplicationContext ctx
                        = new ClassPathXmlApplicationContext("applicationContext.xml");
                FlooringController controller
                        = ctx.getBean("controller", FlooringController.class);
                controller.run();
            }
        });

    }
}
