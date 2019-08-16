package com.axmor.mqtt.server.topics;

/**
 * Request topics from client
 */
public class ClientTopics {

    /**
     * Request for server
     */
    public static String CLIENT_TOPIC = "client";

    public static String CLIENT_TOPIC_ALL = CLIENT_TOPIC + "/#";

    public static String CLIENT_LIST_TOPIC = CLIENT_TOPIC + "/+/list";

    public static String CLIENT_KICK_TOPIC = CLIENT_TOPIC + "/+/kick/+";

    public static String CLIENT_BYE_TOPIC = CLIENT_TOPIC + "/+/bye";

    public static String CLIENT_WILL_TOPIC = CLIENT_TOPIC + "/+/will";

    public static String CLIENT_JOIN_TOPIC = CLIENT_TOPIC +  "/+/join";

    public static String CLIENT_MESSAGE_TOPIC = CLIENT_TOPIC + "/+/message";

}
