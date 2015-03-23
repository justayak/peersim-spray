package example.paper;

import example.PeerSamplingService;
import example.webrtc.data.DictGraph;
import peersim.config.Configuration;
import peersim.config.MissingParameterException;
import peersim.core.CommonState;
import peersim.core.Control;
import peersim.core.GeneralNode;
import peersim.core.Network;

/**
 * Created by julian on 3/15/15.
 */
public class Observer implements Control {

    private static final String PROTOCOL = "lnk";
    private static final String PROTOCOL_0 = "0";

    // =============================================
    // C T O R
    // =============================================

    private int pid;

    public Observer(String name) {

        try {
            this.pid = Configuration.lookupPid(PROTOCOL);
        } catch (MissingParameterException e) {
            this.pid = Configuration.lookupPid(PROTOCOL_0);
        }

    }

    // =============================================
    // E X E C
    // =============================================

    @Override
    public boolean execute() {

        final int STEP = 10;
        final DictGraph observer = DictGraph.getSingleton(Network.size());
        observer.reset();

        for (int i = 0; i < Network.size(); i++) {
            GeneralNode n = (GeneralNode) Network.get(i);
            PeerSamplingService pss = (PeerSamplingService) n.getProtocol(pid);
            //System.out.println(n);
            observer.add(n, pss);
        }

        if (false && CommonState.getTime() % STEP == 0) {
            System.out.println(observer.meanClusterCoefficient());
        } else if (false && CommonState.getTime() % STEP == 0) {
            double a = observer.avgReachablePaths(randomId()).avg;
            double b = observer.avgReachablePaths(randomId()).avg;
            double c = observer.avgReachablePaths(randomId()).avg;
            double d = observer.avgReachablePaths(randomId()).avg;
            double e = observer.avgReachablePaths(randomId()).avg;
            double f = observer.avgReachablePaths(randomId()).avg;
            double g = observer.avgReachablePaths(randomId()).avg;
            double h = observer.avgReachablePaths(randomId()).avg;
            double i = observer.avgReachablePaths(randomId()).avg;
            double j = observer.avgReachablePaths(randomId()).avg;

            double avg = (a + b + c + d + e + f + g + h + i + j) / 10;
            System.out.println(avg);
        } else if (false) {

            if (CommonState.getTime() == 499) {
                System.out.println(observer.toGraph());
            }

        } else if (true && CommonState.getTime() > 0 && CommonState.getTime() % 1 == 0) {
            //System.out.println(observer.meanClusterCoefficient());
            //System.out.println(observer.avgReachablePaths(0).reachQuota);
            System.out.println(observer.countArcs());
        }

        if (CommonState.getTime() == 9999) {
            System.out.println("indeg");
            int[] indegree = observer.inDegreeAsHistogram();
            for (int i = 0; i < indegree.length; i++) {
                System.out.println(indegree[i]);
                //System.out.println(observer.inDegreeAsHistogram());
            }
        }

        return false;
    }

    private long randomId() {
        return Network.get(CommonState.r.nextInt(Network.size())).getID();
    }
}
