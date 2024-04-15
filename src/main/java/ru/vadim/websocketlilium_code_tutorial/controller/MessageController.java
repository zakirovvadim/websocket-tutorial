package ru.vadim.websocketlilium_code_tutorial.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;
import ru.vadim.websocketlilium_code_tutorial.dto.Message;
import ru.vadim.websocketlilium_code_tutorial.dto.ResponseMessage;

import java.security.Principal;

@Controller
public class MessageController {

    @MessageMapping("/message")
    @SendTo("/topic/messages")
    private ResponseMessage getMessage(final Message message) throws InterruptedException {
        Thread.sleep(1000);
        return new ResponseMessage(HtmlUtils.htmlEscape(message.getMessageContent()));
    }

    @MessageMapping("/private-message")
    @SendToUser("/topic/private-message")
    private ResponseMessage getPrivateMessage(final Message message,
                                              final Principal principal) throws InterruptedException {
        Thread.sleep(1000);
        return new ResponseMessage(HtmlUtils.htmlEscape("Sending private message to user " + principal.getName() + ": " + message.getMessageContent()));
    }
}
