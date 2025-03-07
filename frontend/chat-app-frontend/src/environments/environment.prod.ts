const API_URL = "http://localhost:8080/api/v1/";

export const environment = {
    production: true,
    apiUrl: API_URL,
    loginUrl: `${API_URL}auth/login`,
    registerUrl: `${API_URL}user/register`,
    conversationUrl: `${API_URL}conversation`,
    messageUrl: `${API_URL}message`,
    userUrl: `${API_URL}user`
  };