package com.example.ecommerce.model.listners;

import com.example.ecommerce.config.RBConfig;
import com.example.ecommerce.model.entities.Customers;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailListener {
    @Autowired
    private JavaMailSender javaMailSender;

    @RabbitListener(queues = RBConfig.MAIL_QUEUE)
    public void listener(Customers customers) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("kumarishwar623@gmail.com");
        simpleMailMessage.setTo(customers.getEmail());
        simpleMailMessage.setSubject("Order placed successfully");
        simpleMailMessage.setText("Your order is placed successfully");
        javaMailSender.send(simpleMailMessage);

    }

}
