package org.vedenemo.rest

import jakarta.websocket.OnClose
import jakarta.websocket.OnOpen
import jakarta.websocket.Session
import jakarta.websocket.server.ServerEndpoint
import jakarta.enterprise.context.ApplicationScoped
import java.util.concurrent.CopyOnWriteArraySet

@ServerEndpoint("/modelConnector")
@ApplicationScoped
class VedenemoModelConnector {

    companion object {
        private val sessions: MutableSet<Session> = CopyOnWriteArraySet()

        fun broadcastToClients(message: String): Boolean {
            if (sessions.isEmpty()) return false
            sessions.forEach { it.basicRemote.sendText(message) }
            return true
        }
    }

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
}
