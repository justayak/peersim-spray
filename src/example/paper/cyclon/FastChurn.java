package example.paper.cyclon;

import example.paper.ChurnProtocol;
import peersim.core.Node;

/**
 * Created by julian on 4/1/15.
 */
public class FastChurn extends ChurnProtocol {

    public FastChurn(String n) {
        super(n, CyclonProtocol.PAR_PROT);
        ChurnProtocol.current = this;
    }

    @Override
    public void removeNode(Node node) {
        FastCyclon.removeFromNetwork(node);
    }

    @Override
    public void addNode(Node subscriber, Node contact) {
        FastCyclon.add(subscriber, contact);
    }
}
