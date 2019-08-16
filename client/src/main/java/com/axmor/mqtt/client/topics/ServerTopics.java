package com.axmor.mqtt.client.topics;

/**
 * Request topics from server
 */
public class ServerTopics {

    public static String SERVER_TOPIC = "server";

    /**
     * Response for one client
     */
    public static String SERVER_CLIENT_TOPIC = SERVER_TOPIC + "/client";

    public static String SERVER_CLIENT_ID_LIST_TOPIC = SERVER_CLIENT_TOPIC + "/%s/list";

    public static String SERVER_CLIENT_ID_KICK_TOPIC = SERVER_CLIENT_TOPIC + "/%s/kick";

    public static String SERVER_CLIENT_ID_ERROR_TOPIC = SERVER_CLIENT_TOPIC + "/%s/error";

    public static String SERVER_CLIENT_LIST_TOPIC = SERVER_CLIENT_TOPIC + "/+/list";

    public static String SERVER_CLIENT_KICK_TOPIC = SERVER_CLIENT_TOPIC + "/+/kick";

    public static String SERVER_CLIENT_ERROR_TOPIC = SERVER_CLIENT_TOPIC + "/+/error";

    /**
     * Response for all client
     */

    public static String SERVER_ALL_TOPIC_FOR_SUBSCRIBE = SERVER_TOPIC + "/all/#";

    public static String SERVER_ALL_TOPIC = SERVER_TOPIC + "/all";

    public static String SERVER_ALL_MESSAGE_TOPIC = SERVER_ALL_TOPIC + "/message";

    public static String SERVER_ALL_SHUTDOWN_TOPIC = SERVER_ALL_TOPIC + "/shutdown";

}

