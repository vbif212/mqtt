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

    public static String CLIENT_ID_TOPIC = CLIENT_TOPIC + "/%s";

    public static String CLIENT_ID_LIST_TOPIC = CLIENT_ID_TOPIC + "/list";

    public static String CLIENT_LIST_TOPIC = CLIENT_TOPIC + "/+/list";

    public static String CLIENT_ID_KICK_TOPIC = CLIENT_ID_TOPIC + "/kick";

    public static String CLIENT_KICK_TOPIC = CLIENT_TOPIC + "/+/kick";

    public static String CLIENT_ID_BYE_TOPIC = CLIENT_ID_TOPIC + "/bye";

    public static String CLIENT_BYE_TOPIC = CLIENT_TOPIC + "/+/bye";

    public static String CLIENT_ID_JOIN_TOPIC = CLIENT_ID_TOPIC + "/join";

    public static String CLIENT_JOIN_TOPIC = CLIENT_TOPIC +  "/+/join";

    public static String CLIENT_ID_MESSAGE_TOPIC = CLIENT_ID_TOPIC + "/message";

    public static String CLIENT_MESSAGE_TOPIC = CLIENT_TOPIC + "/+/message";

}
