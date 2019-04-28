package sk.zilak.pacman_ai.level;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.Graph;
import com.badlogic.gdx.utils.Array;

public class LevelGraph implements Graph<LevelGraphNode> {

    @Override
    public Array<Connection<LevelGraphNode>> getConnections(LevelGraphNode fromNode) {
        return fromNode.getConnections();
    }
}
