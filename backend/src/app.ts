import express, { Request, Response } from 'express';
import http from 'http';
import WebSocket from 'ws';

const app = express();
const server = http.createServer(app);
const wss = new WebSocket.Server({ server });

const clients: WebSocket[] = [];

app.get('/', (req: Request, res: Response) => {
    res.send('Chat Room Server is running!');
});


// WebSocket connection handling
wss.on('connection', (ws) => {
    console.log('New client connected');
    clients.push(ws);

    // Broadcast a message to all clients when a new client connects
    broadcastMessage('A new user has joined the chat.');

    // Message event handling
    ws.on('message', (message: string) => {
        console.log(`Received: ${message}`);
        broadcastMessage(message);
    });

    // Handle client disconnect
    ws.on('close', () => {
        console.log('Client disconnected');
        const index = clients.indexOf(ws);
        if (index !== -1) {
            clients.splice(index, 1);
        }
        broadcastMessage('A user has left the chat.');
    });
});

// Function to broadcast messages to all clients
function broadcastMessage(message: string) {
    clients.forEach(client => {
        if (client.readyState === WebSocket.OPEN) {
            client.send(message);
        }
    });
}

const PORT = process.env.PORT || 3000;
server.listen(PORT, () => {
    console.log(`Server is running on http://localhost:${PORT}`);
});