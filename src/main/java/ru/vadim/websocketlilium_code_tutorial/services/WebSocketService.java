package ru.vadim.websocketlilium_code_tutorial.services;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import ru.vadim.websocketlilium_code_tutorial.dto.ResponseMessage;

@Service
@RequiredArgsConstructor
public class WebSocketService {

    private final SimpMessagingTemplate messagingTemplate;

    public void notifyFrontend(final String message) {
        ResponseMessage response = new ResponseMessage(message);
        messagingTemplate.convertAndSend("/topic/messages", response);
    }

    public void notifyUser(final String id, final String message) {
        ResponseMessage response = new ResponseMessage(message);
        messagingTemplate.convertAndSendToUser(id, "/topic/private-message", response);
    }
}
