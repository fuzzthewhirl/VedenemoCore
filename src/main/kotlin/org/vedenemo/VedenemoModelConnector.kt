package org.vedenemo

import io.quarkus.websocket.WebSocket
import io.quarkus.websocket.OnOpen
import io.quarkus.websocket.OnClose
import jakarta.websocket.Session
import jakarta.enterprise.context.ApplicationScoped
import java.util.concurrent.CopyOnWriteArraySet

@WebSocket("/modelConnector")
@ApplicationScoped
class VedenemoModelConnector {

    private val sessions: MutableSet<Session> = CopyOnWriteArraySet()

    @OnOpen
    fun onOpen(session: Session) {
        sessions.add(session)
        println("Client connected: ${session.id}")
    }

    @OnClose
    fun onClose(session: Session) {
        sessions.remove(session)
        println("Client disconnected: ${session.id}")
    }

    fun broadcastToClients(message: String): Boolean {
        if (sessions.isEmpty()) return false
        sessions.forEach { it.basicRemote.sendText(message) }
        return true
    }
}
