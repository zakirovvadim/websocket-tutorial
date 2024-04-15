package ru.vadim.websocketlilium_code_tutorial.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.vadim.websocketlilium_code_tutorial.services.WebSocketService;
import ru.vadim.websocketlilium_code_tutorial.dto.Message;

@RestController
@RequiredArgsConstructor
public class WebSocketController {

    private final WebSocketService service;

    @PostMapping("/send-message")
    public void sendMessage(@RequestBody final Message message) {
        service.notifyFrontend(message.getMessageContent());
    }

    @PostMapping("/send-private-message/{id}")
    public void sendPrivateMessage(@PathVariable("id") final String id,
                                   @RequestBody final Message message) {
        service.notifyUser(id, message.getMessageContent());
    }
}
