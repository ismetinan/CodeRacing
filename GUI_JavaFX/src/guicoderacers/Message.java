public class Message {
    private String type;  // e.g., "request", "response", "error"
    private String content;  // e.g., username or status message

    // Constructor
    public Message(String type, String content) {
        this.type = type;
        this.content = content;
    }

    // Getters and setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}