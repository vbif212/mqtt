package com.axmor.mqtt.server.topics;

/**
 * Response topics to client
 */
public class ServerTopics {

    public static String SERVER_TOPIC = "server";

    /**
     * Response for one client
     */
    public static String SERVER_CLIENT_ID_TOPIC = SERVER_TOPIC + "/client/%s";

    public static String SERVER_CLIENT_ID_LIST_TOPIC = SERVER_CLIENT_ID_TOPIC + "/list";

    public static String SERVER_CLIENT_ID_KICK_TOPIC = SERVER_CLIENT_ID_TOPIC + "/kick";

    public static String SERVER_CLIENT_ID_ERROR_TOPIC = SERVER_CLIENT_ID_TOPIC + "/error";

    /**
     * Response for all client
     */
    public static String SERVER_ALL_TOPIC = SERVER_TOPIC + "/all";

    public static String SERVER_ALL_MESSAGE_TOPIC = SERVER_ALL_TOPIC + "/message";

    public static String SERVER_ALL_SHUTDOWN_TOPIC = SERVER_ALL_TOPIC + "/shutdown";

}
