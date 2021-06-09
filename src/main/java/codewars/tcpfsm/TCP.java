package codewars.tcpfsm;

import java.util.HashMap;
import java.util.Map;

public class TCP {

    public static void main(String args[]) {
        //CLOSE_WAIT
        System.out.println(TCP.traverseStates(new String[] {"APP_ACTIVE_OPEN","RCV_SYN_ACK","RCV_FIN"}));
        //ESTABLISHED
        System.out.println(TCP.traverseStates(new String[] {"APP_PASSIVE_OPEN", "RCV_SYN","RCV_ACK"}));
        //LAST_ACK
        System.out.println(TCP.traverseStates(new String[] {"APP_ACTIVE_OPEN","RCV_SYN_ACK","RCV_FIN","APP_CLOSE"}));
        //SYN_SENT
        System.out.println(TCP.traverseStates(new String[] {"APP_ACTIVE_OPEN"}));
        //ERROR
        System.out.println(TCP.traverseStates(new String[] {"APP_PASSIVE_OPEN","RCV_SYN","RCV_ACK","APP_CLOSE","APP_SEND"}));

    }

    public static String traverseStates(String[] events) {
        String state = "CLOSED";                          // initial state, always
        // Your code here!
        HashMap<String, HashMap<String, String>> stateTransition = new HashMap<>();

        //CLOSED: APP_PASSIVE_OPEN -> LISTEN
        //CLOSED: APP_ACTIVE_OPEN  -> SYN_SENT
        stateTransition.put("CLOSED", new HashMap<String, String>() {{
            put("APP_PASSIVE_OPEN", "LISTEN");
            put("APP_ACTIVE_OPEN", "SYN_SENT");
        }});

        //LISTEN: RCV_SYN          -> SYN_RCVD
        //LISTEN: APP_SEND         -> SYN_SENT
        //LISTEN: APP_CLOSE        -> CLOSED
        stateTransition.put("LISTEN", new HashMap<String, String>() {{
            put("RCV_SYN", "SYN_RCVD");
            put("APP_SEND", "SYN_SENT");
            put("APP_CLOSE", "CLOSED");
        }});

        //SYN_RCVD: APP_CLOSE      -> FIN_WAIT_1
        //SYN_RCVD: RCV_ACK        -> ESTABLISHED
        stateTransition.put("SYN_RCVD", new HashMap<String, String>() {{
            put("APP_CLOSE", "FIN_WAIT_1");
            put("RCV_ACK", "ESTABLISHED");
        }});

        //SYN_SENT: RCV_SYN        -> SYN_RCVD
        //SYN_SENT: RCV_SYN_ACK    -> ESTABLISHED
        //SYN_SENT: APP_CLOSE      -> CLOSED
        stateTransition.put("SYN_SENT", new HashMap<String, String>() {{
            put("RCV_SYN", "SYN_RCVD");
            put("RCV_SYN_ACK", "ESTABLISHED");
            put("APP_CLOSE", "CLOSED");
        }});

        //ESTABLISHED: APP_CLOSE   -> FIN_WAIT_1
        //ESTABLISHED: RCV_FIN     -> CLOSE_WAIT
        stateTransition.put("ESTABLISHED", new HashMap<String, String>() {{
            put("APP_CLOSE", "FIN_WAIT_1");
            put("RCV_FIN", "CLOSE_WAIT");
        }});

        //FIN_WAIT_1: RCV_FIN      -> CLOSING
        //FIN_WAIT_1: RCV_FIN_ACK  -> TIME_WAIT
        //FIN_WAIT_1: RCV_ACK      -> FIN_WAIT_2
        stateTransition.put("FIN_WAIT_1", new HashMap<String, String>() {{
            put("RCV_FIN", "CLOSING");
            put("RCV_FIN_ACK", "TIME_WAIT");
            put("RCV_ACK", "FIN_WAIT_2");
        }});

        //CLOSING: RCV_ACK         -> TIME_WAIT
        stateTransition.put("CLOSING", new HashMap<String, String>() {{
            put("RCV_ACK", "TIME_WAIT");
        }});

        //FIN_WAIT_2: RCV_FIN      -> TIME_WAIT
        stateTransition.put("FIN_WAIT_2", new HashMap<String, String>() {{
            put("RCV_FIN", "TIME_WAIT");
        }});

        //TIME_WAIT: APP_TIMEOUT   -> CLOSED
        stateTransition.put("TIME_WAIT", new HashMap<String, String>() {{
            put("APP_TIMEOUT", "CLOSED");
        }});

        //CLOSE_WAIT: APP_CLOSE    -> LAST_ACK
        stateTransition.put("CLOSE_WAIT", new HashMap<String, String>() {{
            put("APP_CLOSE", "LAST_ACK");
        }});

        //LAST_ACK: RCV_ACK        -> CLOSED
        stateTransition.put("LAST_ACK", new HashMap<String, String>() {{
            put("RCV_ACK", "CLOSED");
        }});

        Map<String, String> currentPossibleState = stateTransition.get(state);
        String currentState = null;
        for (String input : events) {
            currentState = currentPossibleState.get(input);
            if(currentState == null) return "ERROR";
            currentPossibleState = stateTransition.get(currentState);
        }

        return currentState;
    }
}
