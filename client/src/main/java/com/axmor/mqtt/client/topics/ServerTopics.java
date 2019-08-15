package com.axmor.mqtt.client.topics;

/**
 * Request topics from server
 */
public class ServerTopics {

    public static String SERVER_TOPIC = "server";

    /**
     * Response for one client
     */
    public static String SERVER_CLIENT_ID_TOPIC = SERVER_TOPIC + "/client/%s";

    public static String SERVER_CLIENT_ID_LIST_TOPIC = SERVER_CLIENT_ID_TOPIC + "/list";

    public static String SERVER_CLIENT_ID_KICK_TOPIC = SERVER_CLIENT_ID_TOPIC + "/kick";

    public static String SERVER_CLIENT_ID_BYE_TOPIC = SERVER_CLIENT_ID_TOPIC + "/bye";

    public static String SERVER_CLIENT_ID_JOIN_TOPIC = SERVER_CLIENT_ID_TOPIC + "/join";

    /**
     * Response for all client
     */
    public static String SERVER_ALL_TOPIC = SERVER_TOPIC + "/all";

    public static String SERVER_ALL_MESSAGE_TOPIC = SERVER_ALL_TOPIC + "/message";

    public static String SERVER_ALL_SHUTDOWN_TOPIC = SERVER_ALL_TOPIC + "/shutdown";

}

