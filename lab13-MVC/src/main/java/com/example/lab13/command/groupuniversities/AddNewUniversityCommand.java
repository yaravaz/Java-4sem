package com.example.lab13.command.groupuniversities;

import com.example.lab13.command.Command;
import com.example.lab13.command.CommandResult;
import com.example.lab13.exception.*;
import com.example.lab13.model.University;
import com.example.lab13.service.UniversityService;
import com.example.lab13.util.pages.Page;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import static com.example.lab13.command.groupuniversities.constants.GroupConstant.*;
import static com.microsoft.sqlserver.jdbc.StringUtils.isEmpty;

public class AddNewUniversityCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(AddNewUniversityCommand.class.getName());
    private QueueConnectionFactory connectionFactory;
    private Queue queue;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException, ServletException {
        // 14lab
        try {
            /*Properties env = new Properties();
            env.put(Context.INITIAL_CONTEXT_FACTORY,"org.apache.activemq.jndi.ActiveMQInitialContextFactory");
            env.put(Context.PROVIDER_URL, "tcp://localhost:61616");
            env.put("queue.queueSampleQueue2", "MyNewQueue2");

            InitialContext ctx = new InitialContext(env);
            queue = (Queue) ctx.lookup("queueSampleQueue2");
            connectionFactory = (QueueConnectionFactory) ctx.lookup("QueueConnectionFactory");*/
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            connectionFactory = (QueueConnectionFactory) envContext.lookup("jms/ConnectionFactory");
            queue = (Queue) envContext.lookup("jms/queue/MyQueue");
        } catch (Exception e) {
            throw new ServletException("Failed to initialize JMS resources", e);
        }
        //

        UniversityService universityService = new UniversityService();
        Optional<String> newName = Optional.ofNullable(request.getParameter(NEWNAME));
        Optional<String> newRating = Optional.ofNullable(request.getParameter(NEWRATING));
        Optional<String> newAddress = Optional.ofNullable(request.getParameter(NEWADDRESS));
        if (isEmpty(newName.get()) || isEmpty(newRating.get()) || isEmpty(newAddress.get())) {
            LOGGER.info("missing parameter for new university in addition mode");
            request.setAttribute(ERROR_MESSAGE, ERROR_MESSAGE_TEXT);
        } else {
            //14lab
            if(Integer.parseInt(newRating.get()) <= 3 ){
                try {
                    QueueConnection queueConn = connectionFactory.createQueueConnection();
                    QueueSession queueSession = queueConn.createQueueSession(false,Session.DUPS_OK_ACKNOWLEDGE);

                    QueueSender queueSender = queueSession.createSender(queue);
                    queueSender.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
                    TextMessage message = queueSession.createTextMessage("low rating");
                    queueSender.send(message);
                    System.out.println("sent: " + message.getText());

                    queueConn.close();
                } catch (Exception e) {
                    throw new ServletException("Failed to send JMS message", e);
                }
            }
            //
            University newuniversity = new University(newName.get(), newRating.get(), newAddress.get());
            universityService.save(newuniversity);
        }
        List<University> universities = universityService.findAll();
        if (!universities.isEmpty()) {
            request.setAttribute("universities", universities);
        }
        return new CommandResult(Page.WELCOME_PAGE.getPage(), false);
    }
}