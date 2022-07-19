public class ApiAccess {
    private static String apiUrl = "https://api.mocki.io/v2/549a5d8b/Top250Movies";
    private static String apiKey = "";

    public static String getApiAccess() {
        return apiUrl + apiKey;
    }
}
