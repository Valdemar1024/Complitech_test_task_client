package task;

public class UrlConstant {

    public static final String REQUEST_PREFIX = "/user-manager/v1";
    public static final String REQUEST_CREATE = REQUEST_PREFIX + "/create";
    public static final String REQUEST_UPDATE = REQUEST_PREFIX + "/update";
    public static final String REQUEST_DELETE = REQUEST_PREFIX + "/delete";
    public static final String REQUEST_FIND = REQUEST_PREFIX + "/find";
    public static final String REQUEST_DELETE_RANGE = REQUEST_PREFIX + "/deleteRange";

    public static final String RESPONSE_TOPIC_PREFIX = "/topic/users";
    public static final String RESPONSE_CREATE = RESPONSE_TOPIC_PREFIX + "/create";
    public static final String RESPONSE_UPDATE = RESPONSE_TOPIC_PREFIX + "/update";
    public static final String RESPONSE_DELETE = RESPONSE_TOPIC_PREFIX + "/delete";
    public static final String RESPONSE_FIND = RESPONSE_TOPIC_PREFIX + "/find";
    public static final String RESPONSE_ACTION = RESPONSE_TOPIC_PREFIX + "/action";
    public static final String RESPONSE_ERROR = RESPONSE_TOPIC_PREFIX + "/error";
    public static final String RESPONSE_DELETE_RANGE = RESPONSE_TOPIC_PREFIX + "/deleteRange";
}
