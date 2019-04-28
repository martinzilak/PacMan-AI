package sk.zilak.pacman_ai.level;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.DefaultConnection;
import com.badlogic.gdx.utils.Array;

public class LevelGraphNode {

    private Array<Connection<LevelGraphNode>> connections = new Array<>();

    public Array<Connection<LevelGraphNode>> getConnections() {
        return connections;
    }

    public Connection<LevelGraphNode> connectWith(LevelGraphNode node) {
        Connection<LevelGraphNode> connection = new DefaultConnection<>(this, node);
        connections.add(connection);
        return connection;
    }
}
