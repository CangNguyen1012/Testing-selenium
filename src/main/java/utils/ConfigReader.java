package utils;

import com.sun.jna.WString;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;
    private static final String CONFIG_FILE_NAME = "config.properties";

    // Hàm load file properties (tự động gọi khi cần)
    private static void loadProperties() {
        if (properties != null) return; // Đã load rồi thì không load lại

        properties = new Properties();
        try {
            InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream(CONFIG_FILE_NAME);
            if (inputStream != null) {
                properties.load(inputStream);
                inputStream.close();
            }
        } catch (Exception e) {
            System.out.println("Lỗi đọc config.properties: " + e.getMessage());
        }
    }

    // Hàm lấy giá trị từ properties theo key
    public static String getProperty(String key) {
        // Nếu properties chưa được load thì load trước
        if (properties == null) {
            loadProperties();
        }
        return properties.getProperty(key);
    }

    // Hàm lấy giá trị với giá trị mặc định nếu không tìm thấy
    public static String getProperty(String key, String defaultValue) {
        // Nếu properties chưa được load thì load trước
        if (properties == null) {
            loadProperties();
        }
        return properties.getProperty(key, defaultValue);
    }

//  Viet ham lay thong tin tu config.properties
    public static String getAdminUsername() {
        return getProperty("valid.username", "Admin");
    }

    public static String getAdminPassword() {
        return getProperty("valid.password", "test");
    }

    public static String getBrowser() {
        return getProperty("browser", "chrome");
    }

    public static String getReportPath() {
        return getProperty("report.path");
    }

    public static String getLoginUrl() {
        return getProperty("login.url");
    }
}

