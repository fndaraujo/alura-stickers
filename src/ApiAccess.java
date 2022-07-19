public class ApiAccess {
    private static String apiUrl = "https://alura-filmes.herokuapp.com/conteudos";
    private static String apiKey = "";

    public static String getApiAccess() {
        return apiUrl + apiKey;
    }
}
